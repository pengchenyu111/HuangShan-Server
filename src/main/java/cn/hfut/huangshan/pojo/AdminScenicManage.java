package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String scenicName;
    private String adminWorkDay;
    private String adminWorkTime;

}
