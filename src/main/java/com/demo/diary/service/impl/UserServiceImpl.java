package com.demo.diary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.diary.common.util.JsonUtil;
import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.mapper.UserMapper;
import com.demo.diary.pojo.User;
import com.demo.diary.service.IUserService;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public WrappedResult queryUserListByCondition(ParameterCondition<User> parameterCondition) throws Exception {
        try {
            User user = JsonUtil.getEntityClazz(parameterCondition.getFilter(),User.class);
            PageHelper.startPage(parameterCondition.getPageIndex(),parameterCondition.getPageSize());
            List<User> pages = getBaseMapper().queryUserListByCondition(user);
            PageInfo<User> pageInfo = new PageInfo<>(pages);
            return WrappedResult.successWrapedResult(pageInfo.getList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
