package cn.hfut.huangshan.pojo.DB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 真正和数据库表对应的管理类
 * @author pcy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBAdmin implements Serializable {

    private long id;
    private String account;
    private String password;
    private String name;
    private long roleId;
    private String phone;
    private int workYear;
    private String birth;
    private String sex;
    private String introduction;
    private String headIcon;
}
