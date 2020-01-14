package com.clbee.pbcms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyPasswordEncoder {
    public static String changeSHA256(String str){
        String SHA = "";
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }

    static public String getRenewPassword(){
        String password="";
        for(int i= 0; i<8; i++){
            if(i%2 == 0){
                int rnum = (int)(Math.random() * 10);
                password += rnum;
            }else{
                char lowerStr = (char)(Math.random() * 26 + 97);
                password += lowerStr;
            }
        }

        return password;
    }
}
