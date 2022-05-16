package pro01.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

/**
 * program: ssm
 * Date: 2022-04-08  19:26
 * Author: cym
 * Description:
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode (CharSequence rawPassword) {
        return privateEncode(rawPassword);
    }

    @Override
    public boolean matches (CharSequence rawPassword,String databasePassword) {
        //明文密码加密
        String formPassword = encode(rawPassword);
        //比较加密密文跟数据库密文是否一致
        return Objects.equals(formPassword,databasePassword);

    }

    private String privateEncode(CharSequence rawPassword){
        try {
            String algorithm = "MD5";
            //获取一个MessageDigest对象
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            //获取rawPassword的字节数组
            byte[] input = ((String) rawPassword).getBytes();
            //将密码进行加密
            byte[] output = digest.digest(input);
            //将密码转换为16进制的字符,并返回
            return new BigInteger(1,output).toString(16).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main (String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        //$2a$10$ecjaoEXZz6zp8ahVmDnAKeNMBW9gLoGtl6Dpli9dH.dsDxNWQMHOK
        //$2a$10$.oDgFERAdN5zs37EtItA3uvGSgDIC3hPRxM4ozhfFFmaj9tq.iAoK
        System.out.println(passwordEncoder.matches("123456",encode)?"一致":"不一致");

    }
}

