package com.cn.reflect.BeanInfoDemo;

import com.cn.annotation.PrimaryKey;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/19 : 9:26.
 * @version : 1.0
 */
public class Girl {



    @PrimaryKey
    private  long id;
    private  String name;
    private Integer age;
    private String address;
    private String tel;
    private long utime;
    private  long ctime;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
