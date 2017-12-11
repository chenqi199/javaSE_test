package com.ddwx.cl.model;

public class SchoolInfo {
    private Long schoolId;

    private String mobile;

    private String email;

    private String address;

    private String schoolLeader;

    private String remark;
    private Byte schoolType;
    private Long province;

    private Long city;

    private Long district;

    private Integer viewBalance;

    private Integer attendanceBalance;

    private Long functionSwitch;

    private Long ctime;

    private Long utime;


    public SchoolInfo() {
    }

    public SchoolInfo(Long schoolId, String mobile, String email, String address, String schoolLeader,
                      String remark, Long province, Long city, Long district,
                      Integer viewBalance, Integer attendanceBalance, Long functionSwitch, Long ctime, Long utime,Byte schoolType) {
        this.schoolId = schoolId;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.schoolLeader = schoolLeader;
        this.remark = remark;
        this.province = province;
        this.city = city;
        this.district = district;
        this.viewBalance = viewBalance;
        this.attendanceBalance = attendanceBalance;
        this.functionSwitch = functionSwitch;
        this.ctime = ctime;
        this.utime = utime;
        this.schoolType=schoolType;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSchoolLeader() {
        return schoolLeader;
    }

    public void setSchoolLeader(String schoolLeader) {
        this.schoolLeader = schoolLeader == null ? null : schoolLeader.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Long getFunctionSwitch() {
        return functionSwitch;
    }

    public void setFunctionSwitch(Long functionSwitch) {
        this.functionSwitch = functionSwitch;
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
    public int getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Byte schoolType) {
        this.schoolType = schoolType;
    }

    @Override
    public String toString() {
        return "SchoolInfo{" +
                "schoolId=" + schoolId +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", schoolLeader='" + schoolLeader + '\'' +
                ", remark='" + remark + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", viewBalance=" + viewBalance +
                ", attendanceBalance=" + attendanceBalance +
                ", functionSwitch=" + functionSwitch +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}