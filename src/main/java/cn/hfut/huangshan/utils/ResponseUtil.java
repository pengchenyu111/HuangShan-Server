package cn.hfut.huangshan.utils;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.response.ResultObj;

/**
 * 返回给前端的工具类
 * @author pcy
 */
public class ResponseUtil {

    /**
     * 成功
     * @param object 需要返回的数据
     * @return
     */
    public static ResultObj success(Object object){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(ErrorCode.OK);
        resultObj.setMessage(ErrorCode.OK_MSG);
        resultObj.setData(object);
        return resultObj;
    }

    /**
     * 失败
     * @param code 失败的返回码
     * @param message 失败信息
     * @param object 要返回的数据
     * @return
     */
    public static ResultObj error(int code,String message,Object object){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setMessage(message);
        resultObj.setData(object);
        return resultObj;
    }
}
