//package juesheng_news_test;
//
//import com.ddwx.cl.model.JueShengNews;
//import com.ddwx.cl.service.JueShengNewsServiceI;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * 描述：
// *
// * @author chen_q_i@163.com
// * 2017/10/30 : 14:40.
// * @version : 1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
//public class JueShengTest {
//
//
//    @org.springframework.beans.factory.annotation.Autowired(required = true)
//    private JueShengNewsServiceI jueShengNewsServiceI;
//
//    @Test
//    public void edit() {
//        System.out.println("hahha");
//    }
//
//    @Test
//    public void insert() {
//        JueShengNews jueShengNews = new JueShengNews();
//        jueShengNews.setContent("niniiiiiiii");
//        jueShengNews.setId(1L);
//        jueShengNews.setTag("hahhaha");
//        jueShengNews.setTitle("十九大");
//        jueShengNewsServiceI.insert(jueShengNews);
//    }
//
//
//    @Test
//    public void reptile() throws IOException {
//
//        List<JueShengNews> urls = getBaseUrl();
//
////       getContentByUrl(urls.get(0));
//
//        urls.forEach(news -> {
//            try {
//                JueShengNews jueShengNews = getContentByUrl(news);
//                if (jueShengNews.getDatetime() == null) {
//                    System.out.println("========================null============");
//                } else {
//                    jueShengNewsServiceI.insert(jueShengNews);
//
//                    System.out.println("title====" + jueShengNews.getTitle() + "\n" + " imgPath====" + jueShengNews.getImgpath());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//
//
//    //    查询一级目录
//    public List<JueShengNews> getBaseUrl() throws IOException {
//
//        List<JueShengNews> news = new ArrayList<>(20);
//        Document doc;
//        doc = Jsoup.connect("http://jiaoyu.juesheng.com/?channel=8&identity=3&grade=1,2,3&province=1&track_code=dongdianweixiaoapp&pindao=0")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
//        Elements elements = doc.select("#newsList").get(0).select("li");
//
//        for (Element aTarget :
//                elements) {
//
//            JueShengNews jueShengNews = new JueShengNews();
//            String newsUrl = aTarget.select("a").attr("href");
//            Elements select = aTarget.select(".clist-side").select("img");
//            String imgPath = select.attr("data-src");
//
//            jueShengNews.setImgpath(imgPath);
//            jueShengNews.setTitle(newsUrl);
//            news.add(jueShengNews);
//        }
//        return news;
//    }
//
//    //获取文章内容
//    public JueShengNews getContentByUrl(JueShengNews jueShengNews) throws IOException {
//        Document doc;
//        String url = jueShengNews.getTitle();
//        doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").timeout(30000).get();
//        Element content = doc.select(".main").get(0);
//
//        String date = content.select(".detail-about-time").get(0).text();
//        System.out.println(date);
//        String now = dateFormert();
//        if (now.equals(date)) {
//            String title = content.select("h1").get(0).text();
//            String body = getBody(content);
//            Elements tagEles = content.select(".detail-tags").get(0).select("a");
//            System.out.println("tageles====" + tagEles.toString());
//            StringBuilder tags = new StringBuilder();
//            for (Element e : tagEles) {
//                String tagText = e.text();
//                System.out.println(tagText);
//                tags.append(",").append(tagText);
//            }
//
//
//            String newTags = tags.length() > 0 ? tags.deleteCharAt(0).toString() : "";
//            jueShengNews.setTag(newTags);
//            jueShengNews.setDatetime(new Date());
//            jueShengNews.setTitle(title);
//            jueShengNews.setContent(body);
//        }
//        return jueShengNews;
//    }
//
//    //    @Test
//    public String dateFormert() {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(currentTime);
////        System.out.println(dateString);
//        return dateString;
//    }
//
//    public String getBody(Element content) throws IOException {
//
//        Element element = content.select(".more-articles").get(0);
//        element.getAllElements().empty();
//        System.out.println(content);
//
//        return content.toString();
//    }
//}
