package com.ddwx.utils.UploadPicture;

import com.ddwx.utils.ImageInfo;
import com.google.gson.Gson;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/5 : 14:27.
 */
@Component
public class HttpInteractive {
    public static final String CHAR_UTF8 = "UTF-8";
    /**
     * 取内容超时时间
     */
    private final int SOCKET_TIMEOUT = 5000;
    /**
     * 连接超时时间
     */
    private final int CONN_TIMEOUT = 1000;
    private Logger logger;
    private CloseableHttpClient httpclient;

    public HttpInteractive() {
        super();
        logger = LoggerFactory.getLogger(getClass());
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory()).build();
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        clientConnectionManager.setMaxTotal(50);
        clientConnectionManager.setDefaultMaxPerRoute(25);
        clientConnectionManager.setValidateAfterInactivity(5);
        CookieStore cookieStore = new BasicCookieStore();
//
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        httpclient = HttpClients.custom().setConnectionManager(clientConnectionManager)
                .setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
    }

    public String uploadImage(String action, File file) throws ParseException,
            IOException {
        HttpPost post = null;
        post = new HttpPost(action);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONN_TIMEOUT)
                .build();
        post.setConfig(requestConfig);

        InputStream is = new FileInputStream(file);
        BasicHttpEntity httpEntity = new BasicHttpEntity();

        httpEntity.setContentType("jpg");
        httpEntity.setContent(is);
        httpEntity.setContentLength(file.length());
        post.setHeader("Content-type", "jpg");
        post.setEntity(httpEntity);

        CloseableHttpResponse response =  httpclient.execute(post);

        String json = null;
        try {
            if (response.getStatusLine().getStatusCode() == org.apache.commons.httpclient.HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            response.close();
        }
        return json;
    }

//    public String upload(String action, String fileName, ExternalType type)
//            throws ParseException, IOException {
//        HttpPost post = null;
//        switch (type) {
//            case CAMERA:
//                post = new HttpPost(action);
//                break;
//            case MICRO_SCHOOL:
//                post = new HttpPost(action);
//                break;
//            default:
//                break;
//        }
//        FileBody bin = new FileBody(new File(fileName));
//        HttpEntity reqEntity = MultipartEntityBuilder.create()
//                .addPart("image", bin).build();
//        post.setEntity(reqEntity);
//        CloseableHttpResponse response = (CloseableHttpResponse) httpclient.execute(post);
//
//        String json = null;
//
//        try {
//            if (response.getStatusLine().getStatusCode() == org.apache.commons.httpclient.HttpStatus.SC_OK) {
//                HttpEntity entity = response.getEntity();
//                json = EntityUtils.toString(entity);
//                EntityUtils.consume(entity);
//            }
//        } finally {
//            response.close();
//        }
//        return json;
//    }

    public static enum ExternalType {
        CAMERA, MICRO_SCHOOL, IMAGESESER
    }

    public String getMd5(String imageJson) {
        Gson gson = new Gson();
        ImageInfo imageInfo = gson.fromJson(imageJson, ImageInfo.class);
        String md5 = "";
        if (imageInfo.getRet()) {
            md5 = imageInfo.getInfo().getMd5();
        }
        return md5;
    }

    public String getImgPath(String action, File file) throws IOException {
        String json = uploadImage(action, file);
        if (json.contains("\"ret\":true")){
            return getMd5(json);
        }else {
            return null;
        }
    }

//    /**
//     * 获取聊天组ID
//     * @param action
//     * @param params
//     * @return
//     * @throws IOException
//     */
//    public long getChatGroupId(String action,Map<String, String> params) throws IOException {
//        String json = post(action,params);
//        if (json.contains("\"success\":true")){
//            Gson gson = new Gson();
//            _ChatGroup chatGroup  = gson.fromJson(json,_ChatGroup.class);
//            if (chatGroup.isSuccess()){
//                return chatGroup.getGroupId();
//            }
//        }
//        return 0;
//    }

    /*public String upload(String action, String fileName) throws IOException, ClientProtocolException {
        //请求处理页面
        HttpPost httpPost = new HttpPost(config.getURL(action));
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONN_TIMEOUT)
                .build();
        httpPost.setConfig(requestConfig);

        FileBody bin = new FileBody(new File(fileName));

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("image", bin)
                .build();

        httpPost.setEntity(reqEntity);

        HttpResponse response = httpclient.execute(httpPost);

        String json = null;
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity);
                // ensure it is fully consumed
                EntityUtils.consume(entity);
            }
        } finally {
            httpPost.releaseConnection();
        }
        return json;
    }
*/
    /*public String post(String action, Map<String, String> params)
            throws IOException, ClientProtocolException {
        return this.postUrl(config.getURL(action), params);
    }

    public String push(String action, Map<String, String> params)
            throws IOException, ClientProtocolException {
        return this.postUrl(config.getPushURL(action), params);
    }*/

    private String postUrl(String url, Map<String, String> params) throws IOException, ClientProtocolException {
//        logger.info("post: " + url);
        HttpPost httpPost = new HttpPost(url);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONN_TIMEOUT)
                .build();
        httpPost.setConfig(requestConfig);
        // params
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        }

        HttpResponse response = httpclient.execute(httpPost);

        String json = null;
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity);
                // ensure it is fully consumed
                EntityUtils.consume(entity);
            }
        } finally {
            httpPost.releaseConnection();
        }
        logger.info("post return: " + json);
        return json;
    }

//    public String get(String action, Map<String, String> params)
//            throws IOException, ClientProtocolException {
//        return getUrl(config.getURL(action), params);
//    }

    private String getUrl(String url, Map<String, String> params)
            throws IOException, ClientProtocolException {
        logger.info("get: " + url);
        String URL = null;
        // params
        if (params != null && !params.isEmpty()) {
            StringBuilder URLBuilder = new StringBuilder();
            URLBuilder.append(url);
            URLBuilder.append('?');
            for (String key : params.keySet()) {
                URLBuilder.append(key);
                URLBuilder.append('=');
                URLBuilder.append(params.get(key));
                URLBuilder.append('&');
            }
            URL = URLBuilder.substring(0, URLBuilder.length() - 1);
        } else {
            URL = url;
        }

        HttpGet httpGet = new HttpGet(URL);
        // 设置超时
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONN_TIMEOUT)
                .build();
        httpGet.setConfig(requestConfig);
        HttpResponse response = httpclient.execute(httpGet);

        String json = null;
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                json = EntityUtils.toString(entity);
                // ensure it is fully consumed
                EntityUtils.consume(entity);
            }
        } finally {
            httpGet.releaseConnection();
        }
        logger.info("get return: " + json);
        return json;
    }

//    public String post(String action, Map<String, String> params,
//                       String charName) {
//        Charset charset = Charset.forName(charName);
//        HttpPost post = null;
//        post = new HttpPost(config.getCameraURL(action));
//        // 设置超时
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(SOCKET_TIMEOUT)
//                .setConnectTimeout(CONN_TIMEOUT)
//                .build();
//        post.setConfig(requestConfig);
//        if (params != null && !params.isEmpty()) {
//            List<NameValuePair> nvp = new ArrayList<NameValuePair>();
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                nvp.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//            post.setEntity(new UrlEncodedFormEntity(nvp, charset));
//        }
//        String json = null;
//        try {
//            CloseableHttpResponse response = (CloseableHttpResponse) httpclient.execute(post);
//            try {
//                if (response.getStatusLine().getStatusCode() == org.apache.commons.httpclient.HttpStatus.SC_OK) {
//                    HttpEntity entity = response.getEntity();
//                    json = EntityUtils.toString(entity);
//                    EntityUtils.consume(entity);
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//        return json;
//    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpInteractive http = new HttpInteractive();
//        String ret = http.post("", null);
//        System.out.println(ret);
    }



}
