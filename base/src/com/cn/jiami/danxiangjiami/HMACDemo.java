package com.cn.jiami.danxiangjiami;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenqi on 2017/4/15 0015.
 *
 * 简介

 这种加密算法并不是那么常用，全称为“Hash Message Authentication Code”，中文名“散列消息鉴别码”，主要是利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出。

 过程

 MAC 的密钥可以是任意长度（比 B 长的密钥将首先被 H 处理）。但当密钥 长度小于 L 时，会降低函数的安全强度。长度大于 L 的密钥也是可以的，但额外的长度并不能显著的提高函数的安全强度
 定义 HMAC 需要一个加密用散列函数（表示为 H）和一个密钥 K。我们假设 H 是 一个将数据块用一个基本的迭代压缩函数来加密的散列函数。我们用 B 来表示数据块的字长。（以上提到的散列函数的分割数据块字长 B = 64），用 L 来表示散列函数的输出数据字长（MD5中 L = 16 , SHA-1 中 L = 20）。鉴别密钥的长度可以是小于等于数据块字长的任何正整数值。应用程序中使用的密钥长度若是比 B 大，则首先用使用散列函数 H 作用于它，然后用 H 输出的 L 长度字符串作为在 HMAC 中实际使用的密钥。一般情况下，推荐的最小密钥 K 长度是 L 个字长。（与 H 的输出数据长度相等）。

 我们将定义两个固定且不同的字符串 ipad，opad：（‘i’，‘o’表示内部与外部）

 ipad = the byte 0x36 repeated B times
 opad = the byte 0x5C repeated B times

 计算‘text’的 HMAC：

 H (K XOR opad, H (K XOR ipad, text))
 */
public class HMACDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {

        String encryptText="aaaa";
        String encryptKey = "bbb";
        String MAC_NAME = "HmacSHA1";
        byte[] data=encryptKey.getBytes("utf-8");
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "");//这个java类是不关注密钥对应的加密算法，不传空就可以。
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("utf-8");
        //完成 Mac 操作
        System.out.println(str16(mac.doFinal(text)));
        System.out.println(str16(mac.doFinal(text)).length());
    }

    public static String str16(byte[] b) {
        StringBuffer buffer = new StringBuffer();
        for (byte oneB : b) {
            String str = Integer.toHexString(oneB & 0xff);
            if (str.length() == 1) {
                str="0"+str;
            }

            buffer.append(str);
        }
        return buffer.toString();
    }
}
