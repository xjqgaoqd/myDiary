package com.demo.diary.controller;

import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.util.RedisUtils;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.pojo.User;
import com.demo.diary.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(path = "/getUserList",method = RequestMethod.POST)
    public WrappedResult getUsers(@RequestBody ParameterCondition<User> parameterCondition){
        try {
            return userService.queryUserListByCondition(parameterCondition);
        } catch (Exception e){
            return WrappedResult.failedWrappedResult("查询异常");
        }
    }


    @PostMapping(value = "/hello1")
    public String hello(@RequestParam (value = "id") String id){
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if(hasKey){
            //获取缓存
            Object object =  redisUtils.get(id);
            log.info("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
//            str = testService.test();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id,str,10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
    }

}
