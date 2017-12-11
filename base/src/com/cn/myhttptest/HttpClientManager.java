package com.cn.myhttptest;

import org.apache.http.Consts;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

/**
 * Created by chen on 2016/11/21.
 */
public class HttpClientManager {

    public static  CloseableHttpClient httpClient = getDefaultClient();
    /**
     * 创建默认的httpclient
     * @return
     */
    static CloseableHttpClient getDefaultClient() {

        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(creatDefaultRegistry());

        manager.setDefaultSocketConfig(getDefaultSocketConf());
        manager.setDefaultConnectionConfig(getDefaultConnConf());
        manager.setValidateAfterInactivity(10);//对回池的连接的再校验

        manager.setMaxTotal(500);
        manager.setDefaultMaxPerRoute(50);

        return HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(getDefaultRequestConf())
                .build();

    }


    /**
     * 设置支持的协议
     * @return
     */
    private static Registry creatDefaultRegistry() {

        SSLContext sslContext = SSLContexts.createSystemDefault();

       return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext))
                .build();

    }


    /**
     * 对soket的限制，100个header，2000字节
     */
    private static SocketConfig getDefaultSocketConf() {

        return SocketConfig.custom().setTcpNoDelay(true).build();//关闭socket的缓冲，确保能够及时将数据写出
    }


    /**
     * 连接的设置，连接传输的数据的设置,可以设置编码
     * @return
     */
    private static ConnectionConfig getDefaultConnConf() {
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(100)
                .setMaxLineLength(2000)
                .build();

       return ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();

    }



    /**
     * default request_config, 默认的，针对每个请求的config，正常每个request可以再设置
     */
    private static RequestConfig getDefaultRequestConf() {

        return RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setConnectionRequestTimeout(5000) //连接从池中阻塞的时间
                .setSocketTimeout(20000) //soket数据传输的时间
                .setConnectTimeout(5000) //建立连接的时间
                .build();


    }

    public static void main(String[] args) {
        getDefaultClient();
    }


}
