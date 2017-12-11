package com.ddwx.cl.service;

import com.ddwx.cl.model.SchoolInfo;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/1 : 14:37.
 * @version : 1.0
 */
public interface SchoolInfoServiceI {

    int deleteByPrimaryKey(Long schoolId);

    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    SchoolInfo selectByPrimaryKey(Long schoolId);

    int updateByPrimaryKeySelective(SchoolInfo record);

    int updateByPrimaryKey(SchoolInfo record);

}
