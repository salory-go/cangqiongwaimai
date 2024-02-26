package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名: sky-take-out
 * 文件名: OssConfiguation
 * 创建者: LZS
 * 创建时间:2024/2/26 18:19
 * 描述:
 **/
@Configuration
@Slf4j
public class OssConfiguation {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties properties){
        log.info("开始上传阿里云文件上传工具对象：{}",properties);
        return new AliOssUtil(properties.getEndpoint(),properties.getAccessKeyId(),properties.getAccessKeySecret(),properties.getBucketName());
    }
}
