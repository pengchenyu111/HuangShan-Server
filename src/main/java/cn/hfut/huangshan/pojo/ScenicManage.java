package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员管理景点
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenicManage implements Serializable {
    private int id;
    private int adminId;
    private String adminHeadIcon;
    private String adminName;
    private String phone;
    private String sex;
    private int age;
    private int workYear;
    private String introduction;
    private String adminWorkDay;
    private String adminWorkTime;
    private int scenicId;
    private String scenicName;
    private String scenicHeadIcon;
    private int isClose;
    private double longitude;
    private double latitude;
}
