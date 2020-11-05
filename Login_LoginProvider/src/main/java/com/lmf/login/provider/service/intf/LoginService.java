package com.lmf.login.provider.service.intf;

import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.common.vo.R;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 22:35
 * description:
 */
public interface LoginService {
    //第一版 点赞-Mysql
    R Loginv1(LoginAddDto dto);

    //第二版 点赞 引入Redis
    R Loginv2(LoginAddDto dto);

    //第三版 点赞 引入RabbitMQ
    R Loginv3(LoginAddDto dto);

    //查询文章的点赞数量
    R queryCount();
}