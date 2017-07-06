package com.cn.myhttptest;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by chen on 2016/11/22.
 */
public class BaseHttpUtil {
    private static Logger logger = Logger.getLogger(BaseHttpUtil.class.toString());
    private CloseableHttpClient httpClient;

    public BaseHttpUtil() {
        this.httpClient = HttpClientManager.getDefaultClient();
    }

    public BaseHttpUtil(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * post 提交，表单形式
     * @param headerList
     * @param url
     * @param param_map
     * @param encode
     * @return
     * @throws IOException
     */
    public HTTPResponse getByPostNV(List<Header> headerList, String url, Map<String, String> param_map, String encode) throws IOException {

        if (null == encode) {
            encode = "utf-8";
        }
        HttpPost post = newHttpPost(headerList, url, param_map, encode);
        return execute(post,encode);
    }

    /**
     * post 字符串
     * @param headerList
     * @param url
     * @param param
     * @param encode
     * @return
     * @throws IOException
     */
    public HTTPResponse getByPostString(List<Header> headerList, String url, String param, String encode) throws IOException {

        if (null == encode) {
            encode = "utf-8";
        }
        HttpPost post = newHttpPost(headerList, url, param, encode);
        return execute(post,encode);
    }


    /**
     * 执行，因为采用的是可重用的方式，所以没有关闭client
     * @param post
     * @param encode
     * @return
     * @throws IOException
     */
    private HTTPResponse execute(HttpPost post,String encode) throws IOException {

        CloseableHttpResponse response = httpClient.execute(post);
        if (null == encode) {
            encode = "utf-8";
        }
        try{
            HttpEntity entity = response.getEntity();
            HTTPResponse myRespose = new HTTPResponse(response.getStatusLine().getStatusCode(), encode, EntityUtils.toByteArray(entity));

            return myRespose;
        }finally {
            response.close();//用池来管理关闭的连接应该是代理的连接
            post.reset();
        }

    }

    /**
     * 表单的httppost
     * @param headerList
     * @param url
     * @param param_map
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    private HttpPost newHttpPost(List<Header> headerList, String url, Map<String, String> param_map, String encode) throws UnsupportedEncodingException {

        HttpPost post = new HttpPost(url);
        if (StringUtils.isBlank(encode)) {
            encode = "utf-8";
        }

        //添加头
        if (null != headerList) {
            for (Header aHeader : headerList) {
                post.addHeader(new BasicHeader(aHeader.getName(), aHeader.getValue()));
            }
        }

        if (null != param_map) {

            List<NameValuePair> nvp = new ArrayList<>();
            for (Map.Entry<String, String> entry : param_map.entrySet()) {
                nvp.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            post.setEntity(new UrlEncodedFormEntity(nvp,encode));
        }
        return post;
    }

    /**
     * 字符串类型的post
     * @param headerList
     * @param url
     * @param param
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    private HttpPost newHttpPost(List<Header> headerList, String url, String param, String encode) throws UnsupportedEncodingException {

        HttpPost post = new HttpPost(url);

        //添加头
        if (null != headerList) {
            for (Header aHeader : headerList) {
                post.addHeader(new BasicHeader(aHeader.getName(), aHeader.getValue()));
            }
        }

     if (StringUtils.isNotBlank(param)) {

         StringEntity entity = new StringEntity(param, encode);
         post.setEntity(entity);
     }
        return post;
    }

}
