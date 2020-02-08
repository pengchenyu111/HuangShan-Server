package cn.hfut.huangshan.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt 的 token 工具类
 */
@Component
public class TokenUtil {

    public static final int TOKEN_IN_REDIS_TIME = 7200;
    public static final int DEFAULT_REDIS_DB = 0;

    /**
     * 生成token
     * @param account
     * @return
     */
    public String createToken(String account){
        Algorithm algorithm = Algorithm.HMAC256("huangshan");
        Instant instant = LocalDateTime.now().plusMinutes(120).atZone(ZoneId.systemDefault()).toInstant();
        Date expire = Date.from(instant);

        String Token = JWT.create()
                .withIssuer("huangshan")
                .withSubject("userInfo")
                .withClaim("account",account)
                .withExpiresAt(expire)
                .sign(algorithm);
        return Token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public boolean parseToken(String token){
        boolean isExpires = false;
        try{
            Algorithm algorithm = Algorithm.HMAC256("huangshan");
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            if(jwt.getExpiresAt().before(new Date())){
                isExpires = true;
            }
        }catch (JWTVerificationException e){
            isExpires = true;
        }
        return isExpires;
    }
}