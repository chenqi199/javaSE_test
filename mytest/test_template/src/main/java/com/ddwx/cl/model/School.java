package com.ddwx.cl.model;

import java.math.BigDecimal;

public class School {
    private Long schoolId;

    private String schoolName;

    private String mobile;

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                ", superiorId=" + superiorId +
                ", agentId=" + agentId +
                ", roleId=" + roleId +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", schoolLeaderId=" + schoolLeaderId +
                ", schoolLeader='" + schoolLeader + '\'' +
                ", schoolType=" + schoolType +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", viewPrice=" + viewPrice +
                ", attendancePrice=" + attendancePrice +
                ", viewBalance=" + viewBalance +
                ", attendanceBalance=" + attendanceBalance +
                ", recipeType=" + recipeType +
                ", videoType=" + videoType +
                ", probation=" + probation +
                ", payType=" + payType +
                ", courseType=" + courseType +
                '}';
    }

    private String telephone;

    private String email;

    private Long ctime;

    private Long utime;

    private Long superiorId;

    private Long agentId;

    private Integer roleId;

    private String address;

    private Byte status;

    public Byte getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(Byte onlineType) {
        this.onlineType = onlineType;
    }

    private Byte onlineType;

    private String remark;

    private Long schoolLeaderId;

    private String schoolLeader;

    private Byte schoolType;

    private Long province;

    private Long city;

    private Long district;

    private BigDecimal viewPrice;

    private BigDecimal attendancePrice;

    private Integer viewBalance;

    private Integer attendanceBalance;

    private Byte recipeType;

    private Byte videoType;

    private Byte probation;

    private Byte payType;

    private Byte courseType;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    public Long getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getSchoolLeaderId() {
        return schoolLeaderId;
    }

    public void setSchoolLeaderId(Long schoolLeaderId) {
        this.schoolLeaderId = schoolLeaderId;
    }

    public String getSchoolLeader() {
        return schoolLeader;
    }

    public void setSchoolLeader(String schoolLeader) {
        this.schoolLeader = schoolLeader == null ? null : schoolLeader.trim();
    }

    public Byte getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Byte schoolType) {
        this.schoolType = schoolType;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }

    public BigDecimal getViewPrice() {
        return viewPrice;
    }

    public void setViewPrice(BigDecimal viewPrice) {
        this.viewPrice = viewPrice;
    }

    public BigDecimal getAttendancePrice() {
        return attendancePrice;
    }

    public void setAttendancePrice(BigDecimal attendancePrice) {
        this.attendancePrice = attendancePrice;
    }

    public Integer getViewBalance() {
        return viewBalance;
    }

    public void setViewBalance(Integer viewBalance) {
        this.viewBalance = viewBalance;
    }

    public Integer getAttendanceBalance() {
        return attendanceBalance;
    }

    public void setAttendanceBalance(Integer attendanceBalance) {
        this.attendanceBalance = attendanceBalance;
    }

    public Byte getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(Byte recipeType) {
        this.recipeType = recipeType;
    }

    public Byte getVideoType() {
        return videoType;
    }

    public void setVideoType(Byte videoType) {
        this.videoType = videoType;
    }

    public Byte getProbation() {
        return probation;
    }

    public void setProbation(Byte probation) {
        this.probation = probation;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Byte getCourseType() {
        return courseType;
    }

    public void setCourseType(Byte courseType) {
        this.courseType = courseType;
    }
}