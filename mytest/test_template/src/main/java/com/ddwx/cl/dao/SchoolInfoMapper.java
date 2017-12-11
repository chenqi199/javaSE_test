package com.ddwx.cl.dao;

import com.ddwx.cl.model.SchoolInfo;

public interface SchoolInfoMapper {
    int deleteByPrimaryKey(Long schoolId);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);
}