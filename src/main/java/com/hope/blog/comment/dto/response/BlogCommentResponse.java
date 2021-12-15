package com.hope.blog.comment.dto.response;

import com.hope.blog.comment.model.BlogCommentReplay;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Created by lijin on  2021/11/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogCommentResponse {

    @ApiModelProperty(value = "评论id")
    private String id;

    @ApiModelProperty(value = "评论用户")
    private String userId;

    @ApiModelProperty(value = "评论用户昵称")
    private String nickName;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论用户头像")
    private String userAvatar;

    @ApiModelProperty(value = "评论时间")
    private Date createTime;

    @ApiModelProperty(value = "回复列表")
    private List<BlogCommentReplay> commentReplayList;
}
