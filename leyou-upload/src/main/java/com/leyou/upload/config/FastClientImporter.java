package com.leyou.upload.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: fastdfs客户端配置类
 * @author: kangyong
 * @date: 2020/8/3 23:42
 * @version: v1.0
 */
@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注入bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {

}
