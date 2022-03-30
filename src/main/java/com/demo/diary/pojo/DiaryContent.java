package com.demo.diary.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("tb_diary_content")
public class DiaryContent implements Serializable {

    private static final long serialVersionUID=1L;

    private String diaryId;

    /** 日记内容 */
    private String content;
}