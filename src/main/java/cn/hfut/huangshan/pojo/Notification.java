package cn.hfut.huangshan.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通知
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private String sendTime;
    private String sendAdminName;
    private String title;
    private String content;
    private String type;
    private String isClose;
}
