package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 项目名: sky-take-out
 * 文件名: CommonController
 * 创建者: LZS
 * 创建时间:2024/2/25 10:20
 * 描述:
 **/
@RestController
@Slf4j
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upLoad(MultipartFile file){
        log.info("文件上传:{}",file);
        try {
            //获取源文件名
            String originalFilename = file.getOriginalFilename();
            //获取文件后缀
            String extention = originalFilename.substring(originalFilename.indexOf("."));
            // 设置新文件名
            String objetName = UUID.randomUUID().toString()+extention;
            //文件的请求路径，该函数的作用是上传文件数据和请求路径，通过该请求路径就能访问图片
            String filePath = aliOssUtil.upload(file.getBytes(), objetName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
