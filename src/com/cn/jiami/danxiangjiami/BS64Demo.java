package com.cn.jiami.danxiangjiami;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by chenqi on 2017/4/15 0015.
 *
 * 简介

 严格来说，BASE64 是一种编码的方式，并不是真正意义上的加解密，不过，从另一个角度来考虑的话，就是把数据变为人不会用肉眼能分辨其真实性的角度来说，BASE64 也是属于加解密范畴的。而且，有的加密技术，也是需要通过 BASE64 来编码转换的。

 产生原因

 主要作用是能够将所有的东西转换为字符串
 所谓Base64，就是说选出64个字符—-小写字母a-z、大写字母A-Z、数字0-9、符号"+"、"/"（再加上作为垫字的"="，实际上是65个字符）—-作为一个基本字符集。然后，其他所有符号都转换成这个字符集中的字符。

 a）所有的二进制文件，都可以因此转化为可打印的文本编码，使用文本软件进行编辑；
 b）能够对文本进行简单的加密。
 转换过程

 第一步，将每三个字节作为一组，一共是24个二进制位。
 第二步，将这24个二进制位分为四组，每个组有6个二进制位。
 第三步，在每组前面加两个00，扩展成32个二进制位，即四个字节。
 第四步，根据下表，得到扩展后的每个字节的对应符号，这就是Base64的编码值。
 0　A　　17　R　　　34　i　　　51　z
 　　1　B　　18　S　　　35　j　　　52　0
 　　2　C　　19　T　　　36　k　　　53　1
 　　3　D　　20　U　　　37　l　　　54　2
 　　4　E　　21　V　　　38　m　　　55　3
 　　5　F　　22　W　　　39　n　　　56　4
 　　6　G　　23　X　　　40　o　　　57　5
 　　7　H　　24　Y　　　41　p　　　58　6
 　　8　I　　　25　Z　　　42　q　　　59　7
 　　9　J　　26　a　　　43　r　　　60　8
 　　10　K　　27　b　　　44　s　　　61　9
 　　11　L　　28　c　　　45　t　　　62　+
 　　12　M　　29　d　　　46　u　　　63　/
 　　13　N　　30　e　　　47　v
 　　14　O　　31　f　　　48　w　　　
 　　15　P　　32　g　　　49　x
 　　16　Q　　33　h　　　50　y
 */
public class BS64Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
       byte[] bytes=  Base64.encodeBase64("cheng".getBytes());
        System.out.println(Arrays.toString(bytes));
       String s = new String(Base64.encodeBase64("cheng".getBytes("UTF-8")));
        System.out.println(s);
        String ss = new String(Base64.decodeBase64(s.getBytes()));
        System.out.println(ss);
    }
}
