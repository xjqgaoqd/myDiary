package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("tb_mood")
public class Mood implements Serializable {
    private static final long serialVersionUID=1L;

    private String id;

    /** 情绪名称 */
    private String name;

    /** 情绪类型 0、积极1、中性2、消极 */
    private Integer type;

    private Integer status;

    private String remark;
}