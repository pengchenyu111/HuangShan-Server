package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 一天数据
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNum implements Serializable {
    private String dateName;
    private String dateWeek;
    private int predictNum;
    private int todayEightNum;
    private int todayNineNum;
    private int todayTotalNum;
    private double deviationRate;
    private int orderNum;
    private String weatherName;
    private String moduleName;
    private String description;
}
