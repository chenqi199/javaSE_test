package com.cn.myhttptest;

import java.io.UnsupportedEncodingException;

/**
 * Created by chen on 2016/11/22.
 */
public class HTTPResponse {

    private int status_code;

    private String encode;

    private byte[] content;



    public HTTPResponse(int status, String encoding, byte[] content) {
        super();
        this.status_code = status;
        if (null == encoding) {
            this.encode = "utf-8";
        } else {

            this.encode = encoding;
        }
        this.content = content;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    public String getContentStr() throws UnsupportedEncodingException {
        return new String(content, encode);
    }
}
