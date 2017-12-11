package com.cn.jiami.shuangxiangjiami.duichengjiami;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import static com.cn.jiami.danxiangjiami.HMACDemo.str16;

/**
 * Created by chenqi on 2017/4/15 0015.
 */
public class DES3Demo {
    public static void main(String[] args) {
        String data = "12341232222";
        String key = "123456781234567812345678";//密钥长度必须大于8个字节
        String iv = "12341234";
        try {
            // 从原始密钥数据创建DESKeySpec对象
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            //使用nopadding模式，data必须为8字节的整数倍
//             Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            IvParameterSpec param = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
            // 执行加密操作
            byte encryptedData[] = cipher.doFinal(data.getBytes());
            System.out.println(str16(encryptedData).length());
            System.out.println(str16(encryptedData));

        } catch (Exception e) {
            System.err.println("出错!");
            e.printStackTrace();
        }
    }
}
