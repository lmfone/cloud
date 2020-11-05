package com.lmf.login.provider.task;

import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.provider.config.RedisKeyConfig;
import com.lmf.login.provider.dao.LoginDao;
import com.lmf.login.provider.third.RedissonUtil;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

;import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 22:20
 * description:
 */
@Component
public class HelloTask {
    @Autowired
    private LoginDao dao;
    //每隔3秒执行1次
    @Scheduled(cron = "0/3 * * * * ?")
    public void t1(){
        System.out.println("醒醒，不能睡觉："+System.currentTimeMillis()/1000);
    }

    //实现点赞数据同步到Mysql中  每天凌晨4点
    @Scheduled(cron = "0 0 4 ? * *")
    public void syncMysqlLogin(){
        //当前时间的24小时前
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long ctime=calendar.getTimeInMillis();
        List<LoginAddDto> dtoList=new ArrayList<>();
        //实现点赞同步
        Iterable<String> keys= RedissonUtil.getKeys(RedisKeyConfig.Login_UID+"*");
        while (keys.iterator().hasNext()){
            String k=keys.iterator().next();
            Collection<ScoredEntry<Object>> uids=RedissonUtil.getZSet(k,ctime);
            for (ScoredEntry se:uids){
                dtoList.add(new LoginAddDto(Integer.parseInt(se.getValue().toString()),Integer.parseInt(k.substring(k.lastIndexOf(":")+1))));
            }
//            Collection<Object> uids=RedissonUtil.getZSet(k);
//            for(Object u:uids){
//                dtoList.add(new LoginAddDto(Integer.parseInt(u.toString()),Integer.parseInt(k.substring(k.lastIndexOf(":")+1))));
//            }
        }
        //批处理
        dao.insertBatch(dtoList);
    }
}
