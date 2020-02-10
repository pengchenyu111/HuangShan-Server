package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 景点
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenic implements Serializable {
    private int id;
    private String code;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
}
