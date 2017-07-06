package com.cn.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chenqi on 2017/5/14 0014.
 */

interface DaoI {
    public void doSave();

    public Object getById(String id);
}

//DaoI实现类,真实主体类，即被代理类
class DaoImpl implements DaoI {
    @Override
    public void doSave() {
        System.out.println("执行保存方法【doSave】");
    }

    /**
     * @param id
     * @Author chenqi
     * @date 2017/5/15 0015 : 23:18
     * @descrption :
     */
    @Override
    public Object getById(String id) {
        System.out.println("执行根据ID查找对象方法【getById】");
        return null;
    }
}

//InvocationHandler实现类
class DaoProxy implements InvocationHandler {
    //被代理类的对象
    private Object target;

    //绑定被代理对象
    public Object bind(Object target) {
        this.target = target;
        //返回实现了被代理类所实现的所有接口的Object对象，即动态代理，需要强制转型
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    //日志记录方法
    private void log(String method) {
        System.out.println("进行日志记录，方法为：" + method);
    }

    //事物提交方法
    private void commit() {
        System.out.println("事物提交");
    }

    /**
     * <p>Discription:覆盖InvocationHandler接口中的invoke()方法</p>
     *
     * @param proxy  需要代理的对象
     * @param method 真实主体要调用的执行方法
     * @param args   调用方法时传递的参数
     * @return
     * @throws Throwable
     * @author : lcma
     * @update : 2016年10月9日下午2:46:29
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //日志记录操作
        this.log(method.getName());
        //使用反射中的invoke()对方法进行动态调用
        Object obj = method.invoke(this.target, args);
        //过滤出以do开头的方法，该方法对数据库进行修改，进行事物提交操作
        if (method.getName().matches("do[a-zA-Z0-9]+")) {
            this.commit();
        }
        return obj;
    }


}

//测试类
public class AutoProxy {
    public static void main(String[] args) {
        //获得代理的实例
        DaoI dao = (DaoI) new DaoProxy().bind(new DaoImpl());
        //调用被代理类中的保存方法
        dao.doSave();
        System.out.println("--------------分割线-----------------");
        //获取被代理类中的获取方法
        dao.getById("123");
    }
}

