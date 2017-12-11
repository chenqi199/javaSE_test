package com.ddwx.cl.dao;

import com.ddwx.cl.model.NewSchool;

public interface NewSchoolMapper {
    int deleteByPrimaryKey(Long schoolId);

    int insert(NewSchool record);

    int insertSelective(NewSchool record);

    NewSchool selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(NewSchool record);

    int updateByPrimaryKey(NewSchool record);
}