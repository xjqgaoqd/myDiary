package com.demo.diary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.diary.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserListByCondition(User user);
}
