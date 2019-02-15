package com.poshist.sys;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

    public static void  main(String[] args){
        String pass = "111111";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(pass);
        System.out.println( encode.matches("111111",hashPass));
        System.out.println(hashPass);
    }
}
