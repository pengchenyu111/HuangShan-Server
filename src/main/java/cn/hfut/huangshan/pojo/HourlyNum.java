package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小时数据
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyNum {
    private String dateName;
    private String hour;
    private int hourNum;
}
