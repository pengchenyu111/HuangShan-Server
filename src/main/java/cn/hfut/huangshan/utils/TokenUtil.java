package cn.hfut.huangshan.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * jwt 的 token 工具类
 */
public class TokenUtil {
    /**
     * 生成token
     * @param jobNumber
     * @param projectID
     * @return
     */
    public String createToken(String jobNumber, String projectID){
        Algorithm algorithm = Algorithm.HMAC256("huangshan");
        Instant instant = LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant();
        Date expire = Date.from(instant);

        String Token = JWT.create()
                .withIssuer("pivot")
                .withSubject("userInfo")
                .withClaim("jobNumber",jobNumber)
                .withClaim("projectID",projectID)
                .withExpiresAt(expire)
                .sign(algorithm);
        return Token;
    }

    public String parseToken(String token){
        String code;
        try{
            Algorithm algorithm = Algorithm.HMAC256("yzyx");
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            if(jwt.getExpiresAt().before(new Date())){
                code = "4100";
            }
            code = "0000";
        }catch (JWTVerificationException e){
            code = "4000";
        }
        return code;
    }
}