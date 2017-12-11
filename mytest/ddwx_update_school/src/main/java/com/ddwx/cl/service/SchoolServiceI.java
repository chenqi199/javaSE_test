package com.ddwx.cl.service;

import com.ddwx.cl.model.School;

import java.util.List;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/3 : 10:44.
 * @version : 1.0
 */
public interface SchoolServiceI {

    int deleteByPrimaryKey(Long schoolId);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    List<School> selectAllSchool();
}
