package com.demo.diary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.diary.pojo.TestUser;
import com.demo.diary.pojo.User;

import java.util.List;

public interface TestUserMapper extends BaseMapper<TestUser> {

    List<TestUser> queryTestUserListByCondition(TestUser user);
}
