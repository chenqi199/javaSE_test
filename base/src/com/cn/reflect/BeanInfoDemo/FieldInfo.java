package com.cn.reflect.BeanInfoDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldInfo {

    private Field field;

    private String name;

    private String dbName;

    private Method writeMethod;

    private Method readMethod;

    public FieldInfo(Field field, String name, String dbName, Method writeMethod, Method readMethod) {
        super();
        this.field = field;
        this.name = name;
        this.dbName = dbName;
        this.writeMethod = writeMethod;
        this.readMethod = readMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof FieldInfo) {
            FieldInfo other = (FieldInfo) obj;
            if (this.getName().equals(other.getName())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public String toString() {
        return "FieldInfo [field=" + field + ", name=" + name + ", dbName=" + dbName + ", writeMethod=" + writeMethod
                + ", readMethod=" + readMethod + "]";
    }

}
