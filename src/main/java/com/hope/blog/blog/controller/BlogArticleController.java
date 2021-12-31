package com.hope.blog.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.hope.blog.blog.dto.request.BlogArticleSearchRequestDto;
import com.hope.blog.blog.dto.response.BlogArticleListResponseDto;
import com.hope.blog.blog.model.BlogArticle;
import com.hope.blog.blog.service.BlogArticleService;
import com.hope.blog.common.api.CommonPage;
import com.hope.blog.common.api.CommonResult;
import com.hope.blog.common.api.ResultCode;
import com.hope.blog.log.handle.OperationLog;
import com.hope.blog.utils.MarkdownUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 博客文章表 前端控制器
 * </p>
 *
 * @author lijin
 * @since 2021-07-02
 */
@Api(tags = "博客文章表")
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController {

    @Resource
    private BlogArticleService iBlogArticleService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/list")
    public CommonResult<CommonPage<BlogArticleListResponseDto>> findListByPage(@ApiParam @RequestBody BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        IPage<BlogArticleListResponseDto> list = iBlogArticleService.findListByPage(blogArticleSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/{id}")
    public CommonResult<BlogArticle> getById(@PathVariable String id) {
        BlogArticle entity = iBlogArticleService.getById(id);
        entity.setContent(MarkdownUtil.markdownToHtml(entity.getContent()));
        return CommonResult.success(entity);
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    @OperationLog(value = "新增文章")
    public CommonResult<ResultCode> add(@ApiParam @RequestBody @Valid  BlogArticle entity) {
        boolean success = iBlogArticleService.saveOrUpdate(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除单条记录
     */
    @ApiOperation(value = "删除单条记录")
    @GetMapping(value = "/delete/{id}")
    @OperationLog(value = "删除文章（物理删除）")
    public CommonResult<ResultCode> delete(@PathVariable String id) {
        boolean success = iBlogArticleService.removeById(id);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改单条记录
     */
    @ApiOperation(value = "修改单条记录")
    @PostMapping(value = "/update")
    @OperationLog(value = "修改文章")
    public CommonResult<ResultCode> update(@ApiParam @RequestBody @Valid BlogArticle entity) {
        boolean success = iBlogArticleService.updateById(entity);
        if (success) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    // 门户

    /**
     * 热门文章
     */
    @ApiOperation(value = "热门文章")
    @GetMapping(value = "/getHotArticle")
    public CommonResult<List<BlogArticle>> getHotArticle() {
        List<BlogArticle> entity = iBlogArticleService.getHotArticle();
        return CommonResult.success(entity);
    }


    /**
     * 关键词搜索
     */
    @ApiOperation(value = "关键词搜索")
    @PostMapping(value = "/keyword")
    public CommonResult<CommonPage<BlogArticleListResponseDto>> keyword(@ApiParam @RequestBody BlogArticleSearchRequestDto blogArticleSearchRequestDto) {
        IPage<BlogArticleListResponseDto> list = iBlogArticleService.keyword(blogArticleSearchRequestDto);
        return CommonResult.success(CommonPage.restPage(list));
    }
}

