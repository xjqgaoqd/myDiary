package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("db_ruoyi.t_test")
public class TestUser {
    @TableId(type = IdType.AUTO)
    private String id;

    private String name;

    private Integer age;
}
