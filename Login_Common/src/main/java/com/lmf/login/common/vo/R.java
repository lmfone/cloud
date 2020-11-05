package com.lmf.login.common.vo;

import lombok.Data;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 20:32
 * description:
 */
@Data
public class R<T> {
    private int code;//返回码
    private String msg;//
    private T data;//

    public static <T> R setR(int c,String m,T obj){
        R r = new R();
        r.setCode(c);
        r.setData(obj);
        r.setMsg(m);
        return r;
    }
    //
    public static R ok(){
        return setR(1,"OK",null);
    }
    public static <T> R ok(T t){
        return setR(1,"OK",t);
    }
    public static R fail(){
        return setR(2,"Fail",null);
    }
    public static R fail(String msg){
        return setR(2,msg,null);
    }

}
