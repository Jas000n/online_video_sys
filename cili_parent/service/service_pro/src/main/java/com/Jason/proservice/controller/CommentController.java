package com.Jason.proservice.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.common.utils.vo.UCENTERMEMBER;
import com.Jason.proservice.client.UcenterMemberClient;
import com.Jason.proservice.entity.Comment;
import com.Jason.proservice.service.CommentService;
import com.Jason.proservice.service.ProducerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/service/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    private UcenterMemberClient ucenterMemberClient;


    //根据影视id分页查询评论
    @GetMapping("getComments/{videoId}/{current}/{limit}")
    public R getComments(@PathVariable String videoId, @PathVariable Long current,
                         @PathVariable Long limit){
        Page<Comment> pageComment = new Page<>(current,limit);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("episode_id",videoId);
        //调用完方法后，所有分页数据都被封装到pageProducer对象里面
        commentService.page(pageComment,queryWrapper);

        long total = pageComment.getTotal();//总记录数
        List<Comment> records = pageComment.getRecords();//数据list集合

        return R.ok().data("total",total).data("items",records);
    }

    //添加评论的接口
    @PostMapping("addComment")
    public R addComment(@RequestBody Comment comment, HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);
        UCENTERMEMBER ucenterInfo = ucenterMemberClient.getById(memberId);
        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());
        commentService.save(comment);
        return R.ok();
    }

}
