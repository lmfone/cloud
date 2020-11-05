package com.lmf.login.provider.dao;

import com.lmf.login.common.dto.ContentLoginDto;
import com.lmf.login.common.dto.LoginAddDto;
import com.lmf.login.entity.Login;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 22:25
 * description:
 */
@Repository
public interface LoginDao {
    //新增
    @Insert("insert into t_Login(uid,cid,ctime) values(#{uid},#{cid},now())")
    int insert(LoginAddDto dto);
    //批量新增
    int[] insertBatch(List<LoginAddDto> LoginAddDtos);
    //删除
    @Delete("delete from t_Login where uid=#{uid} and cid=#{cid}")
    int del(LoginAddDto dto);
    //查询 某个内容的点赞记录
    @Select("select * from t_Login where cid=#{cid} order by ctime desc")
    @ResultType(Login.class)
    List<Login> queryByCid(int cid);
    //查询是否点赞
    @ResultType(Login.class)
    @Select("select * from t_Login where uid=#{uid} and cid=#{cid} limit 1")
    Login querySingle(LoginAddDto dto);
    //查询点赞的数量
    @Select("select count(*)ct,cid from t_Login group by cid")
    @ResultType(ContentLoginDto.class)
    List<ContentLoginDto> queryCount();
}
