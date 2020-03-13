package cn.hfut.huangshan.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户反馈
 * @author pcy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suggestion implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String suggestion;
    private String propounder;
    private String feedbackTime;
    private String contactWay;
}
