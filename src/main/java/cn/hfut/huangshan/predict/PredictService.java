package cn.hfut.huangshan.predict;

import cn.hfut.huangshan.predict.inpput.Factors;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.RandomForest;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.MLUtils;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.mllib.linalg.Vector;


/**
 * 预测服务
 **/
@Service
public class PredictService {

    // 文件路径
    private static String FILE_PATH = "D:\\MyOwnCodes\\IJIDEAJAVA\\huangshan\\src\\main\\resources\\data\\factors.txt";
    private static String PREDICT_FILE_PATH = "D:\\MyOwnCodes\\IJIDEAJAVA\\huangshan\\src\\main\\resources\\data\\predict_factors.txt";

    // 设置参数
    /**
     * 分类特征信息，指定哪些特征是分类的，以及每个特征可以采用的分类值
     */
    private static Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<>();
    /**
     * 树的数量，越多结果越准确，但是消耗性能
     */
    private static int numTrees = 30;
    /**
     * 每个节点上要考虑拆分的要素数量
     */
    private static String featureSubsetStrategy = "auto";
    /**
     * 用于信息增益计算的标准，其实就是决策树的生成算法,回归只能选variance
     */
    private static String impurity = "variance";
    /**
     * 树的最大深度，太深容易造成过拟合
     */
    private static int maxDepth = 8;
    /**
     * 用于拆分要素的最大数
     */
    private static int maxBins = 100;
    /**
     * 随机种子
     */
    private static int seed = 12345;


    /**
     * 用于训练模型
     * 可在以后设置定时任务，不断更新模型
     */
    private void modelTrainer() {
        // 设置环境
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("RandomForestRegression");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        Logger.getRootLogger().setLevel(Level.ERROR);

        // 导入数据
        JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), FILE_PATH).toJavaRDD();
        // 切分数据集
        JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[]{0.7, 0.3});
        JavaRDD<LabeledPoint> trainingData = splits[0];
        JavaRDD<LabeledPoint> testData = splits[1];

        // 训练模型
        RandomForestModel model = RandomForest.trainRegressor(trainingData, categoricalFeaturesInfo, numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);

        // 进行预测
        JavaPairRDD<Double, Double> predictionAndLabel = testData.mapToPair(
                p -> new Tuple2<>(model.predict(p.features()), p.label())
        );

        // 计算误差
        double predictMSE = predictionAndLabel.mapToDouble(pl -> {
            double diff = pl._1() - pl._2();
            return diff * diff;
        }).mean();
        double predictRMSE = Math.sqrt(predictMSE);
        System.out.println("Test Mean Squared Error: " + predictMSE);
        System.out.println("Test Root Mean Squared Error: " + predictRMSE);
        // 输出预测结果
        List<Tuple2<Double, Double>> predictions = predictionAndLabel.take(50);
        for (Tuple2<Double, Double> tuple2 : predictions) {
            String s = tuple2.toString();
            String[] result = s.substring(1, s.length() - 1).split(",");
            System.out.println("lable: " + result[0] + "\t\t" + "prediction: " + result[1]);
        }

        // 打印模型
        System.out.println("Learned regression forest model:\n" + model.toDebugString());
        // 保存模型
        model.save(jsc.sc(), "target/tmp/RandomForestRegressionModel");
        // 加载模型
        //RandomForestModel sameModel = RandomForestModel.load(jsc.sc(), "target/tmp/RandomForestRegressionModel");


        jsc.stop();
    }

    /**
     * 预测方法
     *
     * @param factors 特征列表
     * @return
     */
    private List<Factors> predictor(List<Factors> factors) {
        // 设置环境
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("RandomForestRegression");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        Logger.getRootLogger().setLevel(Level.ERROR);

        // 加载已有的模型
        RandomForestModel model = RandomForestModel.load(jsc.sc(), "target/tmp/RandomForestRegressionModel");

        // 转化为RDD
        List<LabeledPoint> labeledPointList = new ArrayList<>();
        for (Factors factor : factors) {
            Vector vector = Vectors.dense(
                    factor.getOrder(),
                    factor.getPre1Num(),
                    factor.getPre2Num(),
                    factor.getPre3Num(),
                    factor.getPre7Num(),
                    Double.parseDouble(String.valueOf(factor.getWeather())),
                    Double.parseDouble(String.valueOf(factor.getHolidayType())),
                    Double.parseDouble(String.valueOf(factor.getWeekType())),
                    Double.parseDouble(String.valueOf(factor.getClassifier())),
                    Double.parseDouble(String.valueOf(factor.getWeekday()))
            );
            LabeledPoint labeledPoint = new LabeledPoint(factor.getLabel(), vector);
            labeledPointList.add(labeledPoint);
        }
        JavaRDD<LabeledPoint> predictData = jsc.parallelize(labeledPointList);

        // 进行预测
        JavaPairRDD<Double, Double> predictionAndLabel = predictData.mapToPair(
                p -> new Tuple2<>(model.predict(p.features()), p.label())
        );
        // 回填预测结果
        List<Factors> result = new ArrayList<>();
        List<Tuple2<Double, Double>> predictions = predictionAndLabel.collect();
        for (int i = 0; i < predictions.size(); i++) {
            String s = predictions.get(i).toString();
            String[] strings = s.substring(1, s.length() - 1).split(",");
            Factors item = factors.get(i);
            item.setLabel(Double.parseDouble(strings[0]));
            result.add(item);
        }

        jsc.stop();


        return result;

    }


//    public static void main(String[] args) {
//
//        PredictService service = new PredictService();
//        Factors factors1 = new Factors(12354, 5647, 6638, 8427, 10053, 7474, 0, 1, 0, 6, 3);
//        Factors factors2 = new Factors(36581, 6636, 12354, 6638, 8427, 8238, 0, 1, 0, 6, 4);
//        List<Factors> factorsList = new ArrayList<>();
//        factorsList.add(factors1);
//        factorsList.add(factors2);
//        List<Factors> result = service.predictor(factorsList);
//        System.out.println("==============>" + result);
//    }


}
