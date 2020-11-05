package com.lmf.Login.api.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lmf.Login.api.service.impl.LoginServiceImpl;
import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 20:58
 * description:
 */
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private LoginServiceImpl service;

    //点赞
    @PostMapping("dz.do")
    public R dz(@RequestBody LoginAddDto dto){
        return service.dz(dto);
    }
    //查询
    //服务降级
    @SentinelResource(value = "count.do",fallback = "error1")
    @GetMapping("count.do")
    public R all(){
        System.out.println(1/0);
        return service.all();
    }
    //降级方法 如果对应的接口出现了故障，那么就执行这个方法
    public R error1(){
        return R.fail("亲，服务暂时不可用");
    }
}
