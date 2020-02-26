package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    private long id;
    private String account;
    private String password;
    private String name;
    private String roleName;
    private String phone;
    private int workYear;
    private String birth;
    private int age;
    private String sex;
    private String introduction;
    private String headIcon;
}
