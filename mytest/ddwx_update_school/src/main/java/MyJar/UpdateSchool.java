package MyJar;

import com.ddwx.cl.model.School;
import com.ddwx.cl.model.SchoolInfo;
import com.ddwx.cl.service.SchoolInfoServiceI;
import com.ddwx.cl.service.SchoolServiceI;
import com.ddwx.utils.ConstantInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;
import java.math.BigInteger;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/12/7 : 18:37.
 * @version : 1.0
 */

public class UpdateSchool {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);

        context.load("classpath:conf/applicationContext.xml");
        context.refresh();
        SchoolServiceI schoolServiceI = context.getBean(SchoolServiceI.class);
        SchoolInfoServiceI schoolInfoServiceI = context.getBean(SchoolInfoServiceI.class);
        try {
            Log4jConfigurer.initLogging("classpath:conf/log4j.xml");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
        Logger log = LoggerFactory.getLogger(UpdateSchool.class);


//        schoolServiceI.selectAllSchool().forEach(school -> log.info(school.toString()));
        log.info("aaa");
        System.out.println("11");

        schoolServiceI.selectAllSchool().forEach(school -> {
            log.info("--------------------------------school update------------");
            long functionSwitch = getSwitch(school);
            SchoolInfo schoolInfo = new SchoolInfo(school.getSchoolId(), school.getMobile(), school.getEmail(), school.getAddress(), school.getSchoolLeader(), school.getRemark(),
                    school.getProvince(), school.getCity(), school.getDistrict(), school.getViewBalance(), school.getAttendanceBalance(),
                    functionSwitch, school.getCtime(), school.getUtime(),school.getSchoolType());
            log.info(school + "\n" +""+ "\n" + schoolInfo);


        });
    }

    public static Long getSwitch(School school) {
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
