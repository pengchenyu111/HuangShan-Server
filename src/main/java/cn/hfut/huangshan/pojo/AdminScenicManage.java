package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 简易的管理员管理景点
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminScenicManage implements Serializable {

    private long id;
    private String scenicName;
    private String adminWorkDay;
    private String adminWorkTime;

}
