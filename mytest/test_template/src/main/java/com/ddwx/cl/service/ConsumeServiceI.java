package com.ddwx.cl.service;

import com.ddwx.cl.model.Consume;

import java.util.List;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/2 : 18:51.
 * @version : 1.0
 */
public interface ConsumeServiceI {


    List<Consume> getConsumes ();
    List<Consume> getConsumeByOperator (Long operator,Integer before,Integer after);

   int updateByPrimaryKey(Consume consume);

    Consume selectByPrimaryKey(Long consumeId);


}
