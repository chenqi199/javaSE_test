package com.cn.jiami.danxiangjiami;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static java.security.MessageDigest.*;

/**
 * Created by chenqi on 2017/4/15 0015.
 *
 * 简介

 全称为“Message Digest Algorithm 5”，中文名“消息摘要算法第五版”
 MD5 属于单向加密算法，是不可逆的加密方式，也就是说，采用了 MD5 加密方式加密之后，就不能对加密的结果进行解密，得到原有的字符串，这是不可以的。

 特点

 压缩性：任意长度的数据，算出的MD5值长度都是固定的。
 容易计算：从原数据计算出MD5值很容易。
 抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
 弱抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
 强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。
 因为这些特点，md5也常用来做一致性校验
 加密原理简介

 MD5 以 512 位分组来处理输入的信息，且每一分组又被划分为 16 个 32 位子分组，经过了一系列的处理后，算法的输出由四个 32 位分组组成，将这四个 32 位分组级联后将生成一个 128 位散列值。然后每四位组成一个十六进制数，所以md5的结果是32位的。传言中的16位是将32位去掉了前8后8

 过程

 填充

 在 MD5 算法中，首先需要对信息进行填充，使其位长对 512 求余的结果等于 448，并且填充必须进行，即使其位长对 512 求余的结果等于 448。因此，信息的位长（Bits Length）将被扩展至 N * 512 + 448，N 为一个非负整数，N 可以是零。

 填充的方法如下：
 1) 在信息的后面填充一个 1 和无数个 0，直到满足上面的条件时才停止用 0 对信息的填充。
 2) 在这个结果后面附加一个以 64 位二进制表示的填充前信息长度（单位为Bit），如果二 进制表示的填充前信息长度超过 64 位，则取低 64 位。

 经过这两步的处理，信息的位长 = N * 512 + 448 + 64 = （N + 1）* 512，即长度恰好是 512 的整数倍。这样做的原因是为满足后面处理中对信息长度的要求。

 初始化变量

 初始的 128 位值为初试链接变量，这些参数用于第一轮的运算，以大端字节序来表示，他们分别为： A = 0x01234567，B = 0x89ABCDEF，C = 0xFEDCBA98，D = 0x76543210。

 （每一个变量给出的数值是高字节存于内存低地址，低字节存于内存高地址，即大端字节序。在程序中变量 A、B、C、D 的值分别为0x67452301，0xEFCDAB89，0x98BADCFE，0x10325476）

 处理分组数据

 每一分组的算法流程如下：

 第一分组需要将上面四个链接变量复制到另外四个变量中：A 到 a，B 到 b，C 到 c，D 到 d。从第二分组开始的变量为上一分组的运算结果，即 A = a， B = b， C = c， D = d。

 主循环有四轮，每轮循环都很相似。第一轮进行 16 次操作。每次操作对 a、b、c 和 d 中的其中三个作一次非线性函数运算，然后将所得结果加上第四个变量，文本的一个子分组和一个常数。再将所得结果向左环移一个不定的数，并加上 a、b、c 或 d 中之一。最后用该结果取代 a、b、c 或 d 中之一。

 输出

 最后的输出是 a、b、c 和 d 的级联。
 */
public class MD5Demo{

    public static void main(String[] args) throws NoSuchAlgorithmException {


//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update("kkk".getBytes());
//        System.out.println( md.digest());
//        输出的是16说明是16个字节，128个位，直接打印成string的时候是乱码，说明不是常规的基本字符，所以一般转成16进制的
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("kkk".getBytes());
        System.out.println(new BigInteger(1, md.digest()).toString(16));

//        这样产生的便是一个16进制的字符串模式
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update("kkk".getBytes());
        byte[] digest=md.digest();
        System.out.println(digest.length);
        System.out.println(Arrays.toString(digest));
        System.out.println(new String(digest));
        StringBuffer buffer = new StringBuffer();
        for (byte oneB : digest) {
            String str = Integer.toHexString(oneB & 0xff);
            if (str.length() == 1) {
                str="0"+str;
            }
            System.out.println(str);
            buffer.append(str);
        }
        System.out.println(buffer.toString());
    }


}
