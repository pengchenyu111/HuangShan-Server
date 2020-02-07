//package cn.hfut.huangshan.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.auth0.jwt.JWT;
//import com.pivot.server.util.ResultUtils;
//import com.pivot.server.util.Token;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//@Component
//public class MyInterceptor implements HandlerInterceptor {
//    private Token token = new Token();
//    String tokens;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        if(String.valueOf(request.getRequestURL()).equals("http://localhost:6776/user/login")) return true;
//        if(new Date().getTime() - request.getSession().getLastAccessedTime() > request.getSession().getMaxInactiveInterval()) {
//            response.getWriter().print(JSON.toJSON(ResultUtils.error("4000","身份认证出错，请重新登录")));
//            return false;
//        }
//        tokens = String.valueOf(request.getSession().getAttribute("token"));
//        String code = token.parseToken(tokens);
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "application/json;charset=UTF-8");
//
//
//        if(!code.equals("0000")){
//            String jobNumber = JWT.decode(tokens).getClaim("jobNumber").asString();
//            if(!jobNumber.equals(request.getSession().getAttribute("jobNumber"))) {
//                response.getWriter().print(JSON.toJSON(ResultUtils.error(code,"身份认证出错，请重新登录")));
//                return false;
//            }
//            tokens = token.createToken(jobNumber,"2020");
//            request.getSession().setAttribute("token",tokens);
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {
////        tokens = token.createToken(jobNumber,"2020");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
//
//    }
//}
