package com.ddwx.utils;

import java.io.*;
import java.util.Properties;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/1 : 9:03.
 */
public class PropertyMgr {

    private Properties props;
    private File file;

    public PropertyMgr(String propertyFile) throws IOException {
        props = new Properties();
        file = new File(propertyFile);
        if (file.exists()) {
            props.load(new FileInputStream(file));
        } else {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream(
                    propertyFile));
        }
    }

    public String getProperty(String key) {
        String value = props.getProperty(key);
        if (value != null) {
            try {
                value = new String(value.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
        try {
            props.store(new FileOutputStream(file), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static String getZimgUrl() throws IOException {
        String zimgUrl = "";
        try {
            PropertyMgr propertyMgr = new PropertyMgr("content.properties");
            zimgUrl = propertyMgr.getProperty("zimg.image.url");
        }catch (IOException e){
            e.printStackTrace();
        }
        return  zimgUrl;
   }






}
