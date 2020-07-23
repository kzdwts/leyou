package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 文件上传通用服务
 * @author: kangyong
 * @date: 2020/7/23 23:09
 * @version: v1.0
 */
@Service
public class UploadService {

    private static final List<String> CONTENT_TYPE = Arrays.asList("image/jpeg", "image/gif", "image/png");

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPE.contains(contentType)) {
            logger.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        try {
            // 获取文件二进制流
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                logger.info("文件内容错误：{}", originalFilename);
                return null;
            }

            // 上传文件
            file.transferTo(new File("E:\\output" + originalFilename));
            // 生成url返回
            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            logger.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }

        return null;
    }
}
