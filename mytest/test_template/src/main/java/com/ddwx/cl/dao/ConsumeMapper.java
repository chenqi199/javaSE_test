package com.ddwx.cl.dao;

import com.ddwx.cl.model.Consume;

import java.util.List;

public interface ConsumeMapper {
    int deleteByPrimaryKey(Long consumeId);

    int insert(Consume record);

    int insertSelective(Consume record);

    Consume selectByPrimaryKey(Long consumeId);

    int updateByPrimaryKeySelective(Consume record);

    int updateByPrimaryKey(Consume record);

    List<Consume> getConsumes ();
    List<Consume> getConsumeByOperator (Long operator,Integer before,Integer after);


}