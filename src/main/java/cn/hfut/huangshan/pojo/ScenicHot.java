package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 景点实时热度
 * @author pcy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenicHot implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;//即为景点Scenic的id
    private double latitude;
    private double longitude;
    private int hotNum;
}
