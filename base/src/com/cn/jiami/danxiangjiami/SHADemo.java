package com.cn.jiami.danxiangjiami;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenqi on 2017/4/15 0015.
 *
 *
 * 简介

 SHA 同样也是一个系列，它包括 SHA-1，SHA-224，SHA-256，SHA-384，和 SHA-512 等几种算法。其中，SHA-1，SHA-224 和 SHA-256 适用于长度不超过 2^64 二进制位的消息。SHA-384 和 SHA-512 适用于长度不超过 2^128 二进制位的消息。
 后面的224,256,384主要是代表了最后生成的消息摘要的位长度
 乍一说 SHA 你可能不知道，但说到散列和散列算法，你一定会知道，也就是平常所指的 Hash。
 SHA，全称为“Secure Hash Algorithm”，中文名“安全哈希算法”，主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）。对于长度小于 2^64 位的消息，SHA1 会产生一个 160 位的消息摘要。也就是20个字节，最终转成16进制的字符串的时候会生成40个字符。
 SHA-256会产生32字节，也就是256位

 特点

 不可以从消息摘要中复原信息
 两个不同的消息，不会产生同样的消息摘要
 原理

 SHA 将输入流按照每块 512 位（64 个字节）进行分块，并产生 20 个字节的被称为信息认证代码或信息摘要的输出。
 该算法输入报文的长度不限，产生的输出是一个 160 位的报文摘要。输入是按 512 位的分组进行处理的。SHA-1 是不可逆的、防冲突，并具有良好的雪崩效应。

 MD5和SHA的比较

 因为二者均由 MD4 导出，SHA-1 和 MD5 彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：

 对强行攻击的安全性

 最显著和最重要的区别是 SHA-1 摘要比 MD5 摘要长 32 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对 MD5 是 2^128 数量级的操作，而对 SHA-1 则是 2^160 数量级的操作。这样，SHA-1 对强行攻击有更大的强度。

 对密码分析的安全性

 由于 MD5 的设计，易受密码分析的攻击，SHA-1 显得不易受这样的攻击。

 速度

 在相同的硬件上，SHA-1 的运行速度比 MD5 慢。
 */
public class SHADemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");//也可以是SHA-256等
        md.update("kkk".getBytes());
        byte[] digest=md.digest();
        StringBuffer buffer = new StringBuffer();
        for (byte oneB : digest) {
            String str = Integer.toHexString(oneB & 0xff);
            if (str.length() == 1) {
                str="0"+str;
            }
            buffer.append(str);
        }
        System.out.println(buffer.toString());
    }
}
