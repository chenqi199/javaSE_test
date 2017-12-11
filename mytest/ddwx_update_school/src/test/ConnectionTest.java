import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by chenqi on 2017/7/11 0011 : 上午 11:37.
 *
 * @version : 1.0
 * @description :
 */
public class ConnectionTest {
    String url="jdbc:mysql://localhost:3306/ddwx_update_consume";
    String name="root";
    String pwd="1024";

    @Test
    public void closeAll() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,name,pwd);
        System.out.println(conn);
    }

    public String subImagUrl(String imageUrl){
        if(imageUrl.indexOf("com")>0)
            imageUrl=imageUrl.substring(imageUrl.indexOf("com")+3);
        return imageUrl;
    }
    @Test
    public void testUrl() {
      String url = "http://www.ddweixiao.com/uploads/allimg/150713/1-150G31030400-L.jpg";
        System.out.println(subImagUrl(url));
    }
}
