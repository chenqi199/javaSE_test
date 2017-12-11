package com.ddwx.cl.service;

import com.ddwx.cl.dao.SchoolInfoMapper;
import com.ddwx.cl.model.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/1 : 14:38.
 * @version : 1.0
 */
@Service
public class SchoolInfoServiceImp implements SchoolInfoServiceI {

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;


    @Override
    public int deleteByPrimaryKey(Long schoolId) {
        return schoolInfoMapper.deleteByPrimaryKey(schoolId);
    }

    @Override
    public int insert(SchoolInfo record) {
        return schoolInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(SchoolInfo record) {
        return schoolInfoMapper.insertSelective(record);
    }

    @Override
    public SchoolInfo selectByPrimaryKey(Long schoolId) {
        return schoolInfoMapper.selectByPrimaryKey(schoolId);
    }

    @Override
    public int updateByPrimaryKeySelective(SchoolInfo record) {
        return schoolInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SchoolInfo record) {
        return schoolInfoMapper.updateByPrimaryKey(record);
    }
}
