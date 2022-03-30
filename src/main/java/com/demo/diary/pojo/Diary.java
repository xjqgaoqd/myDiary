package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("tb_diary")
public class Diary implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String userId;

    /** 关联情绪 */
    private String moodId;

    /** 写作日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date writeDate;

    /** 日记关键字 */
    private String keyword;

    /** 地点位置 */
    private String site;

    private String remark;

    /** 是否锁定 0、未锁定 1、锁定 */
    private Integer isLock;

    /** 状态 0、正常 -1、删除，默认为0 */
    private Integer status = 0;

    /** 日记等级(重要程度)，满分 5 分 */
    private Integer score;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;
}
