package cn.hfut.huangshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 游客
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tourist implements Serializable {
    private long id;
    private String account;
    private String password;
    private String name;
    private String roleName;
    private String phone;
    private String birth;
    private int age;
    private String sex;
    private String headIcon;
}
