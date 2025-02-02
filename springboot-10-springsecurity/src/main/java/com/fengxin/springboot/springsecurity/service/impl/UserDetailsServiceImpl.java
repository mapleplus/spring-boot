package com.fengxin.springboot.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengxin.springboot.springsecurity.mapper.UserMapper;
import com.fengxin.springboot.springsecurity.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FENGXIN
 * @date 2024/9/3
 * @project springboot-part
 * @description 自定义UserDetailsService 从数据库校验用户登陆
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // 注入UserMapper
    @Resource
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        // 查询数据
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq (User::getUserName , username);
        User user = userMapper.selectOne (queryWrapper);
        // 异常处理 将会在事务中处理异常
        if (user == null) {
            throw new UsernameNotFoundException ("用户名不存在！");
        }
        // List<String> authoritiesList = new ArrayList<> (List.of ("test","hello"));
        // 获取用户的权限
        List<String> authoritiesList = userMapper.selectPermByUserId (user.getId ());
        // 返回UserDetails的实现类 封装相应的权限
        return new UserDetailsImpl (user,authoritiesList);
    }
}
