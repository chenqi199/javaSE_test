package com.ddwx.cl.service;

import com.ddwx.cl.dao.ConsumeMapper;
import com.ddwx.cl.model.Consume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/2 : 21:32.
 * @version : 1.0
 */
@Service("consumeServiceI")
public class ConsumeServiceImp implements ConsumeServiceI{
    @Autowired
    private ConsumeMapper consumeMapper;

    @Override
    public List<Consume> getConsumes() {
        return consumeMapper.getConsumes();
    }

    @Override
    public List<Consume> getConsumeByOperator(Long operator, Integer before, Integer after) {
        return consumeMapper.getConsumeByOperator(operator,before,after);
    }

    @Override
    public int updateByPrimaryKey(Consume consume) {
        return consumeMapper.updateByPrimaryKey(consume);
    }

    @Override
    public Consume selectByPrimaryKey(Long consumeId) {
        return consumeMapper.selectByPrimaryKey(consumeId);
    }
}
