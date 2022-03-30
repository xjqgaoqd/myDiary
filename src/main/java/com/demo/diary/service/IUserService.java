package com.demo.diary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.pojo.User;

import java.util.List;

public interface IUserService extends IService<User> {

    WrappedResult queryUserListByCondition(ParameterCondition<User> parameterCondition) throws Exception;

}
