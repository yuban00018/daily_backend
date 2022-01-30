package com.daily.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daily.dao.UserDoMapper;
import com.daily.model.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import static com.daily.model.ConstantRepository.*;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@Component
public class JwtTool {
    @Value("${ENCODE_KEY}")
    private String ENCODE_KEY;

    private static JWTVerifier jwtVerifier;

    @Resource
    private UserDoMapper userDoMapper;

    private static String encode(String str) {
        return URLEncoder.encode(str);
    }

    /**
     * @Author: yuban00018
     * @Date: 2022/1/30
     * @Return: token
     * @Description: create Jwt token
     */
    public String createJwt(String id, String name) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, TOKEN_TIMEOUT_DAY);
        name = encode(name);
        Algorithm algorithm = Algorithm.HMAC256(ENCODE_KEY.getBytes());
        return TOKEN_HEADER + JWT.create()
                .withIssuedAt(currentDate)
                .withExpiresAt(calendar.getTime())
                .withSubject(id)
                .withClaim("name", name)
                .sign(algorithm);
    }

    /**
     * @Author: yuban00018
     * @Date: 2022/1/30
     * @Return: int -> is token valid
     * @Description: validate token
     */
    public int validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(ENCODE_KEY.getBytes());
            if (jwtVerifier == null) {
                //create verifier
                jwtVerifier = JWT.require(algorithm).build();
            }
            jwtVerifier.verify(token);
        } catch (SignatureVerificationException e) {
            log.info("Token check failed: {}", e.toString());
            return TOKEN_VERIFICATION_EXCEPTION;
        } catch (TokenExpiredException e) {
            log.info("Token expired: {}", e.toString());
            return TOKEN_EXPIRED_EXCEPTION;
        } catch (JWTDecodeException | IllegalArgumentException e) {
            log.info("Token Invalid: {}", e.toString());
            return TOKEN_FAKE_EXCEPTION;
        }
        DecodedJWT decodedJWT = JWT.decode(token);
        String id = decodedJWT.getSubject();
        UserDo userDo = userDoMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (userDo == null) {
            return TOKEN_FAKE_EXCEPTION;
        }
        String name = URLDecoder.decode(decodedJWT.getClaim("name").asString(), StandardCharsets.UTF_8);
        if (name.equals(userDo.getName())){
            return TOKEN_VALID;
        } else {
            return TOKEN_FAKE_EXCEPTION;
        }
    }
}
