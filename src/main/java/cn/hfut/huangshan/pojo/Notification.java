package cn.hfut.huangshan.pojo;

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
    private int id;
    private String sendTime;
    private String sendAdminName;
    private String title;
    private String content;
    private String type;
}
