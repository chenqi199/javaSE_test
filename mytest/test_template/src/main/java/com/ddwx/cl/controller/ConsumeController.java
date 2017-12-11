package com.ddwx.cl.controller;

import com.ddwx.cl.model.Consume;
import com.ddwx.cl.model.School;
import com.ddwx.cl.service.ConsumeServiceI;
import com.ddwx.cl.service.SchoolServiceI;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/3 : 16:08.
 * @version : 1.0
 */
@Controller
public class ConsumeController {
    Logger log = LoggerFactory.getLogger(ConsumeController.class);
    @Autowired
    private ConsumeServiceI consumeServiceI;

    @Autowired
    private SchoolServiceI schoolServiceI;


    @RequestMapping("/consume")
    public String test() {
        System.out.println("hahha");
        List<Consume> consumes = consumeServiceI.getConsumes();
        int allWrong = 0;
        int allWrongCard = 0;
        Map<Long, Integer> schoolWrong = new HashMap<>();
        for (int i = 0; i < consumes.size(); i++) {
            Consume consume = consumes.get(i);
            Long schoolId = consume.getOperator();
            List<Consume> consumeByOperator = consumeServiceI.getConsumeByOperator(schoolId, consume.getBefore(), consume.getAfter());
            int countOneSchoolWrong = 0;
            int oneSchoolWrongCard = 0;

            for (int j = 0; j < consumeByOperator.size(); j++) {
                if (j > 0) {
                    Consume consumeBefore = consumeByOperator.get(j - 1);
                    Consume consumethis = consumeByOperator.get(j);
                    boolean wrong = consumeBefore.getCtime() - consumethis.getCtime() < 20000;
                    if (wrong) {
                        countOneSchoolWrong++;
                        oneSchoolWrongCard += consumethis.getCount();
                        log.info("出错的记录为====>" + consumethis + "\n" + "前一条记录是====>" + consumeBefore);
                        Integer beforeConsumeAfter = consumeBefore.getAfter();
                        consumethis.setBefore(beforeConsumeAfter);
                        consumethis.setAfter(beforeConsumeAfter - consumethis.getCount());
                        consumeServiceI.updateByPrimaryKey(consume);
                    }
                }
            }

            Set<Long> schoolIdSet = schoolWrong.keySet();
            if (oneSchoolWrongCard != 0) {
                if (schoolIdSet.contains(schoolId)) {
                    Integer readyWrong = schoolWrong.get(schoolId);
                    schoolWrong.put(schoolId, readyWrong + oneSchoolWrongCard);
                } else {
                    schoolWrong.put(schoolId, oneSchoolWrongCard);
                }

                School school = schoolServiceI.selectByPrimaryKey(schoolId);
                log.info("schoolWrong===={}, schoolId = {}", oneSchoolWrongCard, school.getSchoolId());
                log.info("------------------------------------------------------------------------------");
            }

            allWrongCard += oneSchoolWrongCard;
            allWrong += countOneSchoolWrong;

        }
        log.info("allWrong====" + allWrong);
        log.info("allWrongCard====" + allWrongCard);
        Map<Long, Integer> longIntegerMap = sortMapByValue(schoolWrong);
        Iterator<Map.Entry<Long, Integer>> iterator = longIntegerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Integer> next = iterator.next();
            Long schoolId = next.getKey();
            Integer cards = next.getValue();
            School school = schoolServiceI.selectByPrimaryKey(schoolId);
            school.setViewBalance(school.getViewBalance() - cards);
            schoolServiceI.updateByPrimaryKey(school);
            school = schoolServiceI.selectByPrimaryKey(schoolId);
            log.info("school===={},viewBalance={}, cards = {}, agent = {}", school.getSchoolName(), school.getViewBalance(), cards, school.getAgentId());
        }
        return new JSONObject().put("success",true).toString();

    }


    private Map<Long, Integer> sortMapByValue(Map<Long, Integer> oriMap) {
        Map<Long, Integer> sortedMap = new LinkedHashMap<>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(oriMap.entrySet());
            entryList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
            Iterator<Map.Entry<Long, Integer>> iter = entryList.iterator();
            Map.Entry<Long, Integer> tmpEntry;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }


}
