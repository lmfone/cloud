package com.lmf.Login.api.service.impl;

import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 21:23
 * description:
 */
@Service
public class LoginServiceImpl {
    @Autowired
    private RestTemplate restTemplate;
    public R dz(LoginAddDto dto){
        return restTemplate.postForObject("http://LoginProvider/provider/Login/dz.do",dto,R.class);
    }
    public R all(){
        return restTemplate.getForObject("http://LoginProvider/provider/Login/count.do",R.class);
    }
}
