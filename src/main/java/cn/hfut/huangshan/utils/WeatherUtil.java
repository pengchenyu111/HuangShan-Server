package cn.hfut.huangshan.utils;

import java.util.HashMap;
import java.util.Map;

public class WeatherUtil {


    private static Map<String, Double> weatherMap;

    static {
        weatherMap = new HashMap<>();
        weatherMap.put("晴", 1.0);
        weatherMap.put("晴间多云", 0.98);
        weatherMap.put("多云", 0.97);
        weatherMap.put("阴", 0.93);
        weatherMap.put("小雨", 0.85);
        weatherMap.put("中雨", 0.75);
        weatherMap.put("大雨", 0.6);
        weatherMap.put("暴雨", 0.45);
        weatherMap.put("阵雨", 0.89);
        weatherMap.put("雷阵雨", 0.85);
        weatherMap.put("强阵雨", 0.78);
        weatherMap.put("小雪", 0.92);
        weatherMap.put("中雪", 0.8);
        weatherMap.put("大雪", 0.6);
        weatherMap.put("雨夹雪", 0.82);
        weatherMap.put("暴雪", 0.45);
    }

    /**
     * 天气比较
     * @return 影响结果
     */
    public static double weatherEffect(String newWeather, String oldWeather){
        double newScore = weatherMap.get(newWeather);
        double oldScore = weatherMap.get(oldWeather);
        return 1 + newScore - oldScore;
    }
}
