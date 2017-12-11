package com.ddwx.utils.UploadPicture;

import com.ddwx.utils.PropertyMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/5 : 16:14.
 */
@Component
public class UploadPictureUtils {
    private Logger log = LoggerFactory.getLogger(UploadPictureUtils.class);
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Resource
    private HttpInteractive httpInteractive;


    private Base64ToImage base64ToImage = new Base64ToImage();


   private PropertyMgr propertyMgr = new PropertyMgr("content.properties");
    //图片服务器地址
    String zimgUploadUrl = propertyMgr.getProperty("zimg.upload.url");

    public UploadPictureUtils() throws IOException {
    }

    /**
     * 多图上传
     *
     * @return
     * @throws Exception
     */


    /**
     * 多图上传
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<String> getPicturePth(List<String> files) throws Exception {

        List<String> listPath = new ArrayList<>();

        ArrayList<Future<String>> results = new ArrayList<>();
        for (String img : files) {
            if (img != null) {
                results.add(executorService.submit(new GetPathThread(img)));
            }
        }
        for (Future<String> fsFuture : results) {
            try {
                listPath.add(fsFuture.get());
            } catch (Exception e) {
                log.warn("上传图片出错了===》", e);
            }
        }
        return listPath;
    }
     /**
     * 多图上传
     *
     * @param File files
     * @return
     * @throws Exception
     */
    public List<String> getPicturePthFiles(List<File> files) throws Exception {

        List<String> listPath = new ArrayList<>();

        ArrayList<Future<String>> results = new ArrayList<>();
        for (File img : files) {
            if (img != null) {
                results.add(executorService.submit(new GetPathThread(img)));
            }
        }
        for (Future<String> fsFuture : results) {
            try {
                listPath.add(fsFuture.get());
            } catch (Exception e) {
                log.warn("上传图片出错了===》", e);
            }
        }
        return listPath;
    }








    /**
     * 单图片上传
     *
     * @param String picture
     * @return
     * @throws Exception
     */
    public String getPicturePth(String picture) throws Exception {
        Future<String> result = executorService.submit(new GetPathThread(picture));
        String path = "";
        try {
            path = result.get();
        } catch (Exception e) {
            log.warn("上传图片出错了===》", e);
        }
        return path;
    }

    /**
     * 单图片上传
     *
     * @param File picture
     * @return
     * @throws Exception
     */
    public String getPicturePth(File picture) throws Exception {
        Future<String> result = executorService.submit(new GetPathThread(picture));
        String path = "";
        try {
            path = result.get();
        } catch (Exception e) {
            log.warn("上传图片出错了===》", e);
        }
        return path;
    }





    private class GetPathThread implements Callable<String> {

        private File file;

        private GetPathThread(String picture) throws Exception {
            this.file = base64ToImage.base64ToImage(picture);
        }

        private GetPathThread(File picture) throws Exception {
            this.file =picture;
        }

        @Override
        public String call() throws Exception {
            String path = httpInteractive.getImgPath(zimgUploadUrl, file);
            base64ToImage.deleteFile(file);
            return path;
        }
    }


}
