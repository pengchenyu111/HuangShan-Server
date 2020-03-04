package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String code;
    private String name;
    private String description;
    private String openTime;
    private double ticketPrice;
    private int isClose;
    private double longitude;
    private double latitude;
    private String photoUrl;
    private String videoUrl;
    private String audioUrl;
}
