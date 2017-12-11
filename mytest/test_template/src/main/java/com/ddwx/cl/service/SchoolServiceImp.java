package com.ddwx.cl.service;

import com.ddwx.cl.dao.SchoolMapper;
import com.ddwx.cl.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/3 : 10:44.
 * @version : 1.0
 */
@Service("schoolServiceI")
public class SchoolServiceImp implements SchoolServiceI {

    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public int deleteByPrimaryKey(Long schoolId) {
        return schoolMapper.deleteByPrimaryKey(schoolId);
    }

    @Override
    public int insert(School record) {
        return 0;
    }

    @Override
    public int insertSelective(School record) {
        return 0;
    }

    @Override
    public School selectByPrimaryKey(Long schoolId) {
        return schoolMapper.selectByPrimaryKey(schoolId);
    }

    @Override
    public int updateByPrimaryKeySelective(School record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(School record) {
        return schoolMapper.updateByPrimaryKey(record);
    }

    /**
     * select all school
     * @return
     */
    @Override
    public List<School> selectAllSchool() {
        return schoolMapper.selectAllSchool();
    }
}
