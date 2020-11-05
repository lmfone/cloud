package com.lmf.login.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 20:30
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAddDto {
    private int uid;
    private int cid;
}
