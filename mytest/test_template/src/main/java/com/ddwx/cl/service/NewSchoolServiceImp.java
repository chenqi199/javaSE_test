package com.ddwx.cl.service;

import com.ddwx.cl.dao.NewSchoolMapper;
import com.ddwx.cl.model.NewSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/1 : 14:32.
 * @version : 1.0
 */
@Service
public class NewSchoolServiceImp implements NewSchoolServiceI {
    @Autowired
    private NewSchoolMapper newSchoolMapper;
    @Override
    public int deleteByPrimaryKey(Long schoolId) {
        return newSchoolMapper.deleteByPrimaryKey(schoolId);
    }

    @Override
    public int insert(NewSchool record) {
        return newSchoolMapper.insert(record);
    }

    @Override
    public int insertSelective(NewSchool record) {
        return newSchoolMapper.insert(record);
    }

    @Override
    public NewSchool selectByPrimaryKey(Long schoolId) {
        return newSchoolMapper.selectByPrimaryKey(schoolId);
    }

    @Override
    public int updateByPrimaryKeySelective(NewSchool record) {
        return newSchoolMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NewSchool record) {
        return newSchoolMapper.updateByPrimaryKey(record);
    }
}
