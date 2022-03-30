package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("tb_diary_pwd")
public class DiaryPwd implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private Long userId;

    private String password;

    private String salt;
}
