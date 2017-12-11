package com.ddwx.cl.dao;

import com.ddwx.cl.model.School;

import java.util.List;

public interface SchoolMapper {
    int deleteByPrimaryKey(Long schoolId);

    int insert(School record);
    List<School> selectAllSchool();

    int insertSelective(School record);

    School selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
}