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
    private int dateWeek;
    private int totalNum;
    private int eightNum;
    private int nineNum;
}
