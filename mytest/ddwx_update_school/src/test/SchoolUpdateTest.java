import com.ddwx.cl.model.School;
import com.ddwx.cl.model.SchoolInfo;
import com.ddwx.cl.service.NewSchoolServiceI;
import com.ddwx.cl.service.SchoolInfoServiceI;
import com.ddwx.cl.service.SchoolServiceI;
import com.ddwx.utils.ConstantInteger;
import juesheng_news_test.testUtils.JUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigInteger;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/1 : 14:28.
 * @version : 1.0
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml"})
public class SchoolUpdateTest {
    Logger log = LoggerFactory.getLogger(SchoolUpdateTest.class);


    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private SchoolServiceI schoolServiceI;
    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private SchoolInfoServiceI schoolInfoServiceI;
    @org.springframework.beans.factory.annotation.Autowired(required = true)
    private NewSchoolServiceI newSchoolServiceI;


    @Test
    public void update() {


        schoolServiceI.selectAllSchool().forEach(school -> {
            log.info("--------------------------------school update------------");

//            NewSchool newSchool = new NewSchool(school.getSchoolId(), school.getSchoolName(), school.getCtime(),
//                    school.getUtime(), school.getAgentId(), school.getStatus(), school.getSchoolType());
//

            long functionSwitch = getSwitch(school);

            SchoolInfo schoolInfo = new SchoolInfo(school.getSchoolId(), school.getMobile(), school.getEmail(), school.getAddress(), school.getSchoolLeader(), school.getRemark(),
                    school.getProvince(), school.getCity(), school.getDistrict(), school.getViewBalance(), school.getAttendanceBalance(),
                    functionSwitch, school.getCtime(), school.getUtime(),school.getSchoolType());
            log.info(school + "\n" +""+ "\n" + schoolInfo);
//            schoolInfoServiceI.insert(schoolInfo);
//            newSchoolServiceI.insert(newSchool);

        });


    }


    public Long getSwitch(School school) {
        BigInteger b = new BigInteger("0");
        b = school.getRecipeType() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_RECIPE_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_RECIPE_TYPE);

        b = school.getProbation() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_PROBATION_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_PROBATION_TYPE);

        b = school.getPayType() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_PAY_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_PAY_TYPE);

        b = school.getCourseType() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_COURSE_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_COURSE_TYPE);

        b = school.getOnlineType() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_ONLINE_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_ONLINE_TYPE);

        b = school.getVideoType() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_VIDEO_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_VIDEO_TYPE);

        b = school.getStatus() == 1 ? b.setBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_STATUS_TYPE)
                : b.clearBit(ConstantInteger.SCHOOL_FUNCTION_SWITCH_STATUS_TYPE);


        return b.longValue();
    }
}
