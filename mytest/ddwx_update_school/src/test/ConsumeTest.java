import com.ddwx.cl.model.Consume;
import com.ddwx.cl.model.School;
import com.ddwx.cl.service.ConsumeServiceI;
import com.ddwx.cl.service.SchoolServiceI;
import juesheng_news_test.testUtils.JUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/2 : 21:34.
 * @version : 1.0
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml"})
public class ConsumeTest {
    Logger log = LoggerFactory.getLogger(ConsumeTest.class);
    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private ConsumeServiceI consumeServiceI;

    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private SchoolServiceI schoolServiceI;



    @Test
    public void test() {
        System.out.println("hahha");
        List<Consume> consumes = consumeServiceI.getConsumes();
//        consumes.stream().forEach(System.out::println);
        int allWrong= 0;
        int allWrongCard=0;
        Map<Long,Integer> schoolWrong= new HashMap<>();
        for (int i = 0; i < consumes.size(); i++) {
            Consume consume = consumes.get(i);
//            System.out.println(consume);
            Long schoolId = consume.getOperator();
            List<Consume> consumeByOperator = consumeServiceI.getConsumeByOperator(schoolId, consume.getBefore(), consume.getAfter());
//            System.out.println(consumeByOperator.size());
            int countOneSchoolWrong=0;
            int oneSchoolWrongCard=0;

            for (int j = 0; j < consumeByOperator.size(); j++) {
                if (j > 0) {
                    Consume consumeBefore = consumeByOperator.get(j - 1);
                    Consume consumethis = consumeByOperator.get(j);
                    boolean wrong = consumeBefore.getCtime() - consumethis.getCtime() < 20000;
                    if (wrong) {
                        countOneSchoolWrong++;
                        oneSchoolWrongCard+=consumethis.getCount();
                        log.info("出错的记录为====>" + consumethis + "\n" + "前一条记录是====>" + consumeBefore);
                        Integer beforeConsumeAfter = consumeBefore.getAfter();
                        consumethis.setBefore(beforeConsumeAfter);
                        consumethis.setAfter(beforeConsumeAfter-consumethis.getCount());
                        consumeServiceI.updateByPrimaryKey(consume);
                    }
                }
            }
            School school = schoolServiceI.selectByPrimaryKey(schoolId);
//            Integer viewBalance = school.getViewBalance();
//            school.setViewBalance(viewBalance-oneSchoolWrongCard);
            Set<Long> schoolIdSet = schoolWrong.keySet();
            if (oneSchoolWrongCard!=0){
                if (schoolIdSet.contains(schoolId)){
                    Integer readyWrong = schoolWrong.get(schoolId);
                    schoolWrong.put(schoolId,readyWrong+oneSchoolWrongCard);
                }else {
                    schoolWrong.put(schoolId,oneSchoolWrongCard);
                }


                log.info("schoolWrong===={}, schoolId = {}", oneSchoolWrongCard, school.getSchoolId() );
                log.info("------------------------------------------------------------------------------" );
            }
//            log.info("schoolWrong===={}, schoolId = {}", oneSchoolWrongCard, school.getSchoolId() );
//            log.info();
//            schoolServiceI.updateByPrimaryKey(school);

            allWrongCard+=oneSchoolWrongCard;
            allWrong+=countOneSchoolWrong;

        }
        log.info("allWrong===="+allWrong);
        log.info("allWrongCard===="+allWrongCard);
        Map<Long, Integer> longIntegerMap = sortMapByValue(schoolWrong);
        Iterator<Map.Entry<Long, Integer>> iterator = longIntegerMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Long, Integer> next = iterator.next();
            Long schoolId = next.getKey();
            Integer cards = next.getValue();
            School school = schoolServiceI.selectByPrimaryKey(schoolId);
            school.setViewBalance(school.getViewBalance()-cards);
            schoolServiceI.updateByPrimaryKey(school);
            school = schoolServiceI.selectByPrimaryKey(schoolId);
//            if (school.getViewBalance()<cards){
                log.info("school===={},viewBalance={}, cards = {}, agent = {}", school.getSchoolName(),school.getViewBalance(), cards, school.getAgentId());
//            }
        }

    }



    public Map<Long, Integer> sortMapByValue(Map<Long, Integer> oriMap) {
        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<Long, Integer>> entryList = new ArrayList<Map.Entry<Long, Integer>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<Long, Integer>>() {
                        public int compare(Map.Entry<Long, Integer> entry1,
                                           Map.Entry<Long, Integer> entry2) {
                            int value1 = 0, value2 = 0;

                                value1 = entry1.getValue();
                                value2 = entry2.getValue();

                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<Long, Integer>> iter = entryList.iterator();
            Map.Entry<Long, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    @Test
    public  void testList(){
        List<Consume > consumeList =new ArrayList<>(16);
        Consume consume= new Consume();
        consume.setBefore(100);
        consume.setAfter(99);
        consumeList.add(consume);
        Consume consume1 = consumeList.get(0);
        consume1.setAfter(22);
        System.out.println("-----------------"+consumeList.get(0));
    }

    @Test
    public  void updateConsume(){

        Consume consume = consumeServiceI.selectByPrimaryKey(11L);
        log.info("consume===="+consume);
        consume.setBefore(444);
        consume.setAfter(333);
        int update = consumeServiceI.updateByPrimaryKey(consume);
        Consume consume1 = consumeServiceI.selectByPrimaryKey(11L);
        log.info(update+"-----"+consume1);

    }

    @Test
    public  void updateSchool(){

        School school = schoolServiceI.selectByPrimaryKey(1L);
        log.info("school-----"+school);
        Integer viewBalance = school.getViewBalance();
        school.setViewBalance(-10);
       int update= schoolServiceI.updateByPrimaryKey(school);
        School school1 = schoolServiceI.selectByPrimaryKey(1L);
        log.info(update+"-----"+school1);
    }


}



