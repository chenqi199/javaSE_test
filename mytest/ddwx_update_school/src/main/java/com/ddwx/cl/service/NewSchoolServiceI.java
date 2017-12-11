package com.ddwx.cl.service;

import com.ddwx.cl.model.NewSchool;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/1 : 14:31.
 * @version : 1.0
 */
public interface NewSchoolServiceI {
    int deleteByPrimaryKey(Long schoolId);

    int insert(NewSchool record);

    int insertSelective(NewSchool record);

    NewSchool selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(NewSchool record);

    int updateByPrimaryKey(NewSchool record);
}
