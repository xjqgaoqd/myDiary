package com.demo.diary.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BasePojo {
    /** 创建时间 */
    private Date created;
    /** 最后一次修改时间 */
    private Date updated;
}
