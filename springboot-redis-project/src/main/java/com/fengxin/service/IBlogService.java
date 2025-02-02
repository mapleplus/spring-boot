package com.fengxin.service;

import com.fengxin.dto.Result;
import com.fengxin.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 枫
 * @since 2021-12-22
 */
public interface IBlogService extends IService<Blog> {
    
    Result queryHotBlog (Integer current);
    
    Result queryBlogById (Long id);
    
    Result likeBlog (Long id);
    
    Result queryBlogLikes (Long id);
    
    Result saveBlog (Blog blog);
    
    Result queryBlogFollow (Long max , Integer offset);
}
