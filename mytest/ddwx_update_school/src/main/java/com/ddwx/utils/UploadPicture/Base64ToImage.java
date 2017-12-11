package com.ddwx.utils.UploadPicture;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/5 : 16:17.
 */
public class Base64ToImage {


    Logger log = LoggerFactory.getLogger(Base64ToImage.class);

    /**
     * 生成图片文件
     *
     * @param dataURL
     * @return
     * @throws Exception
     */
    public File base64ToImage(String dataURL) throws Exception {
        if (StringUtils.isNotBlank(dataURL)) {
            dataURL = dataURL.substring(dataURL.indexOf(",") + 1);
            byte[] bytes = new BASE64Decoder().decodeBuffer(dataURL);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0)
                    bytes[i] += 256;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            String path = sdf.format(new Date())+new Random().nextInt(10001) + ".jpg";
            OutputStream out = new FileOutputStream(path);
            out.write(bytes);
            out.flush();
            out.close();
            return new File(path);
        }
        return null;
    }

    /**
     * 删除文件
     * 要删除的文件的文件名
     *
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(File file) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + file.getName() + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + file.getName() + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + file.getName() + "不存在！");
            return false;
        }
    }

}
