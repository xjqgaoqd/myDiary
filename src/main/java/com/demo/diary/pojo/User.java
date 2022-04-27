package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    private String id;
    private String username;
    private String password;
    /** 用户类型 0、管理员 1、普通用户 */
    private Integer type;
    /** 用户的状态 1、正常 0、删除 */
    private Integer status;
}