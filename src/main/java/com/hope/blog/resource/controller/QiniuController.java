package com.hope.blog.resource.controller;

import com.hope.blog.resource.service.QiniuContentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 七牛云文件存储 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Api(tags = "七牛云文件存储")
@RestController
@RequestMapping("/resource/qiniuContent")
    public class QiniuController {

    @Autowired
    private QiniuContentService iQiniuContentService;


}

