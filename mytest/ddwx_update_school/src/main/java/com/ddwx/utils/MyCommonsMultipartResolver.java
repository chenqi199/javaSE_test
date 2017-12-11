package com.ddwx.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/1 : 10:12.
 */
public class MyCommonsMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        //过滤使用百度ueditor的URL
        if (request.getRequestURI().indexOf("ueditor/config")>0){
            return false;
        }
        return super.isMultipart(request);
    }
}