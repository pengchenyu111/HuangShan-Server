package cn.hfut.huangshan.predict.inpput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 预测特征入参
 *
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factors implements Serializable {
    /**
     * 做预测时，这个值一般为空
     * 预测完之后将这个值回填
     */
    private double label;
    /**
     * 订票数
     */
    private double order;
    /**
     * 一天前人数
     */
    private double pre1Num;
    /**
     * 两天前人数
     */
    private double pre2Num;
    /**
     * 三天前人数
     */
    private double pre3Num;
    /**
     * 一周前人数
     */
    private double pre7Num;
    /**
     * 天气状况
     */
    private int weather;
    /**
     * 节日名做的分类
     */
    private int holidayType;
    /**
     * 工作日或周末
     */
    private int weekType;
    /**
     * 一年内分为6个时间段的分类
     */
    private int classifier;
    /**
     * 星期几
     */
    private int weekday;


}
