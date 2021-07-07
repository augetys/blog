package com.hope.blog.resource.controller;

import com.hope.blog.resource.service.LocalStorageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 本地存储 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-07
 */
@Api(tags = "本地存储")
@RestController
@RequestMapping("/resource/localStorage")
    public class LocalStorageController {

    @Autowired
    private LocalStorageService iLocalStorageService;

}

