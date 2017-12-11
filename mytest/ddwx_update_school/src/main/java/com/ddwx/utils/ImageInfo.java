package com.ddwx.utils;

/**
 * Created by Administrator on 2017/9/1 : 8:58.
 *
 * @version : 1.0
 * @description :
 */

public class ImageInfo {
    private Boolean ret;
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public class Info {
        private String md5;
        private long size;

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

    }

    public Boolean getRet() {
        return ret;
    }

    public void setRet(Boolean ret) {
        this.ret = ret;
    }

}
