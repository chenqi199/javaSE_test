package com.ddwx.cl.model;

public class NewSchool {
    private Long schoolId;

    private String schoolName;

    private Long ctime;

    private Long utime;

    private Long agentId;

    private Byte status;

    private Byte schoolType;


    public NewSchool() {
    }

    public NewSchool(Long schoolId, String schoolName, Long ctime, Long utime, Long agentId, Byte status, Byte schoolType) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.ctime = ctime;
        this.utime = utime;
        this.agentId = agentId;
        this.status = status;
        this.schoolType = schoolType;
    }

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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Byte schoolType) {
        this.schoolType = schoolType;
    }
}