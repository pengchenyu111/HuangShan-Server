package cn.hfut.huangshan.interceptor;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.utils.RedisUtil;
import cn.hfut.huangshan.utils.ResponseUtil;
import cn.hfut.huangshan.utils.TokenUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        //先放行登录和注册路径
        String passUrl = String.valueOf(request.getRequestURL());
        if(passUrl.equals(DefaultSetting.LOGIN_URL) || passUrl.equals(DefaultSetting.TOURIST_REGISTER_URL)) {
            return true;
        }
        //先看登陆没
        if (request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").equals("")){
            response.getWriter().print(JSON.toJSON(ResponseUtil.error(ErrorCode.NO_LOGIN,ErrorCode.NO_LOGIN_MSG,null)));
            return false;
        }
        //看session过期没:现在时间-最后访问session时间 > 最大存活时间
        if(new Date().getTime() - request.getSession().getLastAccessedTime() > request.getSession().getMaxInactiveInterval()) {
            response.getWriter().print(JSON.toJSON(ResponseUtil.error(ErrorCode.IDENTITY_INVALIDATION,ErrorCode.IDENTITY_INVALIDATION_MSG,null)));
            return false;
        }
        //session没过期，再看token是否过期
        String tokenString = String.valueOf(request.getSession().getAttribute("token"));
        boolean isExpires = tokenUtil.parseToken(tokenString);
        if(!isExpires){
            //没过期检查正确与否
            String account = JWT.decode(tokenString).getClaim("account").asString();
            if(!account.equals(request.getSession().getAttribute("account"))) {
                response.getWriter().print(JSON.toJSON(ResponseUtil.error(ErrorCode.IDENTITY_WRONG,ErrorCode.IDENTITY_WRONG_MSG,null)));
                return false;
            }
            tokenString = tokenUtil.createToken(account);
            request.getSession().setAttribute("token",tokenString);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){

    }
}
