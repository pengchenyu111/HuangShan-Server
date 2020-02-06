package cn.hfut.huangshan.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回实体
 * @author pcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    int code;
    String message;
    Object data;
}
