package com.ddwx.cl.model;

public class Consume {
    private Long consumeId;

    private Long operator;

    private Long byOperator;

    private Byte operateType;

    private Byte type;

    private Long parentId;

    private Integer count;

    private Byte product;

    private Integer before;

    private Integer after;

    private Short year;

    private Byte month;

    private Long ctime;

    private String remark;

    public Long getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Long consumeId) {
        this.consumeId = consumeId;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Long getByOperator() {
        return byOperator;
    }

    public void setByOperator(Long byOperator) {
        this.byOperator = byOperator;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getProduct() {
        return product;
    }

    public void setProduct(Byte product) {
        this.product = product;
    }

    public Integer getBefore() {
        return before;
    }

    public void setBefore(Integer before) {
        this.before = before;
    }

    public Integer getAfter() {
        return after;
    }

    public void setAfter(Integer after) {
        this.after = after;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Consume{" +
                "consumeId=" + consumeId +
                ", operator=" + operator +
                ", byOperator=" + byOperator +
                ", operateType=" + operateType +
                ", type=" + type +
                ", parentId=" + parentId +
                ", count=" + count +
                ", product=" + product +
                ", before=" + before +
                ", after=" + after +
                ", year=" + year +
                ", month=" + month +
                ", ctime=" + ctime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}