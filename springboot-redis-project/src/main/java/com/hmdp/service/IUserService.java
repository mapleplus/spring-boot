package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.entity.User;
import jakarta.servlet.http.HttpSession;

/**
 * @author FENGXIN
 */
public interface IUserService extends IService<User> {
    
    Result sendCode (String phone , HttpSession session);
    
    Result login (LoginFormDTO loginForm , HttpSession session);
    
    Result sign ();
    
    Result signCount ();
    
}
