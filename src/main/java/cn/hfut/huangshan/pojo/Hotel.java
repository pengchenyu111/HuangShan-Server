package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 酒店
 * @author pcy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String hotelName;
    private int hotelStar;
    private String hotelInstruction;
    private String hotelLocation;
    private String hotelCheckIn;
    private String hotelLeave;
    private String hotelPhone;
    private String breakfastType;
    private double breakfastPrice;
    private String isTakePet;
    private String isHavePark;
    private String headIcon;
    private String albumUrl;

}
