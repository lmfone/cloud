package com.lmf.login.provider.service.impl;

import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.common.vo.R;
import com.lmf.login.entity.Login;
import com.lmf.login.provider.config.RedisKeyConfig;
import com.lmf.login.provider.dao.LoginDao;
import com.lmf.login.provider.service.intf.LoginService;
import com.lmf.login.provider.third.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 22:35
 * description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao dao;


    @Override
    public R Loginv1(LoginAddDto dto) {
        //发起点赞--->
        if(dto!=null && dto.getCid()>0 && dto.getUid()>0) {
            //1.校验是否点赞
            Login Login = dao.querySingle(dto);
            if (Login == null) {
                //需要点赞
                //新增
                if (dao.insert(dto) > 0) {
                    return R.ok();
                } else {
                    return R.fail("亲，点赞失败");
                }
            } else {
                //取消点赞
                //删除
                if (dao.del(dto) > 0) {
                    return R.ok();
                } else {
                    return R.fail("亲，取消点赞失败，稍后再来");
                }
            }
        }else{
            return R.fail("亲，参数非法");
        }
    }
    //更改Redis---直接操作Mysql
    public R Loginv2Old(LoginAddDto dto) {
        //Redis
        if(dto!=null && dto.getCid()>0 && dto.getUid()>0) {
            String key= RedisKeyConfig.Login_UID+dto.getCid();
            //校验是否点过赞
            if(RedissonUtil.checkKey(key)){
                //之前有过点赞
                //自己是否点赞过
                if(RedissonUtil.checkZSet(key,dto.getUid())){
                    //点过赞
                    //取消点赞
                    if(RedissonUtil.delZSet(key,dto.getUid()+"")){
                        dao.del(dto);
                        //成功
                        return R.ok();
                    }else{
                        return R.fail("亲,网络故障");
                    }
                }else{
                    RedissonUtil.setZset(key,System.currentTimeMillis(),dto.getUid()+"");
                    dao.insert(dto);
                    return R.ok();
                }
            }else{
                //文章 首次点赞
                RedissonUtil.setZset(key,System.currentTimeMillis(),dto.getUid()+"");
                //Mysql
                dao.insert(dto);
                return R.ok();
            }
        }
        return R.fail("请输入合法的参数");
    }
    @Override
    public R Loginv2(LoginAddDto dto) {
        //Redis
        if(dto!=null && dto.getCid()>0 && dto.getUid()>0) {
            String key=RedisKeyConfig.Login_UID+dto.getCid();
            //校验是否点过赞
            if(RedissonUtil.checkKey(key)){
                //之前有过点赞
                //自己是否点赞过
                if(RedissonUtil.checkZSet(key,dto.getUid())){
                    //点过赞
                    //取消点赞
                    if(RedissonUtil.delZSet(key,dto.getUid()+"")){
                        //成功
                        return R.ok();
                    }else{
                        return R.fail("亲,网络故障");
                    }
                }else{
                    RedissonUtil.setZset(key,System.currentTimeMillis(),dto.getUid()+"");
                    return R.ok();
                }
            }else{
                //文章 首次点赞
                RedissonUtil.setZset(key,System.currentTimeMillis(),dto.getUid()+"");
                //Mysql
                return R.ok();
            }
        }
        return R.fail("请输入合法的参数");
    }
    @Override
    public R Loginv3(LoginAddDto dto) {
        return null;
    }

    @Override
    public R queryCount() {
        return R.ok(dao.queryCount());
    }
}
