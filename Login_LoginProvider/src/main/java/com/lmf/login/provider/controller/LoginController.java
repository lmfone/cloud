package com.lmf.login.provider.controller;

import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.common.vo.R;
import com.lmf.login.provider.service.intf.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 22:26
 * description:
 */
@RestController
@RequestMapping("/provider/Login/") //多级路径
@RefreshScope //实时更新配置的内容
public class LoginController {
    @Autowired
    private LoginService service;
    @Value("${Login.score}") //来自同一配置中心
    private int score;//积分奖励
    //点赞
    @PostMapping("dz.do")
    public R dz(@RequestBody LoginAddDto dto){
        System.out.println("点赞给积分："+score);
        return service.Loginv1(dto);
    }
    //查询
    @GetMapping("count.do")
    public R all(){
        return service.queryCount();
    }
}
