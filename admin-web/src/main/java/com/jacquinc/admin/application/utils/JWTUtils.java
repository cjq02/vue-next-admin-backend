package com.jacquinc.admin.application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhengzheng on 2018/5/3.
 */
public class JWTUtils {

    /**
     * 创建jwt
     * @param id deviceuuid
     * @param subject null || user_id
     * @param ttlMillis 过期的时间长度
     * @param secret 密钥
     * @param claims 私有声明
     * @return jwt
     * @throws Exception
     */
    public static String createJWT(String id, String subject, long ttlMillis, String secret, Map<String,Object> claims) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;//指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(secret);//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setSubject(subject)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    /**
     * 解密jwt
     * @param secret
     * @param jwt
     * @return claims
     * @throws Exception
     */
    public static Claims parseJWT(String secret, String jwt) throws Exception{
        return Jwts.parser()//得到DefaultJwtParser
                .setSigningKey(generalKey(secret))//设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(String secret) {
        byte[] encodedKey = Encodes.encodeBase64(secret).getBytes();
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}