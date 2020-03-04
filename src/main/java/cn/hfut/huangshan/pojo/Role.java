package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String name;
    private String description;
    private int status;
}
