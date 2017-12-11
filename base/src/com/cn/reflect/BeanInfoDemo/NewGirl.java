package com.cn.reflect.BeanInfoDemo;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/19 : 10:37.
 * @version : 1.0
 */
public class NewGirl {
    private String name;
    private long utime;
    private  long ctime;
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NewGirl{" +
                "name='" + name + '\'' +
                ", utime=" + utime +
                ", ctime=" + ctime +
                ", id=" + id +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
