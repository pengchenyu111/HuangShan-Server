package cn.hfut.huangshan.pojo.DB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据库中实际的游客实体
 * @author pcy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBTourist implements Serializable {

    private long id;
    private String account;
    private String password;
    private String name;
    private long roleId;
    private String phone;
    private String birth;
    private String sex;
    private String headIcon;

}
