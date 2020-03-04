package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.utils.AliyunMessageUtil;
import cn.hfut.huangshan.utils.RedisUtil;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verification_code")
public class VerificationCodeController {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取验证码的请求
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{phone}", method = RequestMethod.GET)
    public ResultObj getVerificationCode(@PathVariable("phone") String phone){
        //从阿里云的短信接口给用户发送验证码
        String code = AliyunMessageUtil.sendVerificationCode(phone);
        //将验证码存入redis
        String key = "vercode:" + phone;
        redisUtil.set(key,code,0);
        redisUtil.expire(key,300,0);//5min后过期
        Boolean exists = redisUtil.exists(key);
        if (exists){
            return ResponseUtil.success(null);
        }
        return ResponseUtil.error(ErrorCode.VERIFICATION_CODE_GET_FAIL,ErrorCode.VERIFICATION_CODE_GET_FAIL_MSG,null);
    }
}
