package com.jhxaa.yhj.utli;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


public class JwtUtil {

    // private static long TIME_60_SECONDS = ((1000 * 60) * 5) + System.currentTimeMillis();

    private final static String SING_KEY = "xiaoan";
    private final static String ISS_USER = "taobao.jhxaa.com";


    /**
     * 生成token
     *
     * @param userName
     * @param jsonStr
     * @return
     */
    public static <T> String create(String userName, T jsonStr) {
        long TIME_60_SECONDS = ((1000 * 60) * 5) + System.currentTimeMillis();
        String toJSONString = JSONObject.toJSONString(jsonStr);
        String uuid = UuidUtil.getUUID();
        JwtBuilder builder = Jwts.builder()
                .setId(uuid) //JWT_ID
                .setAudience(userName)   //接受者
                .setSubject(toJSONString) //主题
                .setIssuer(ISS_USER)      //签发者
                .setIssuedAt(new Date())//用于设置签发时间
//                .setNotBefore(new Date(TIME_60_SECONDS))
                .setExpiration(new Date(TIME_60_SECONDS))//过期时间
                .signWith(SignatureAlgorithm.HS256, SING_KEY);
        String token = builder.compact();
        return token;
    }


    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SING_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static void main(String[] args) {
        User user = new User();
        String s = create(user.name, JSON.toJSONString(user));

        System.out.println(checkToken(s));

//        System.out.println(s);
//        System.out.println(JSON.toJSONString(checkToken(s)));

//        long now = System.currentTimeMillis();//当前时间
//        long exp = now + 1000 * 60;//过期时间为1分钟
//
//        JwtBuilder builder = Jwts.builder().setId("888")
//                .setSubject("")
//                .setIssuedAt(new Date())//用于设置签发时间
//                .signWith(SignatureAlgorithm.HS256, "xiaoan")
//                .setExpiration(new Date(exp));
//        String token = builder.compact();
//        Claims claims = Jwts.parser().setSigningKey("xiaoan").parseClaimsJws(token).getBody();
//        System.out.println(token);
//        System.out.println(JSON.toJSONString(claims));
    }

    static class User {
        int id = 1;
        String name = "test";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
