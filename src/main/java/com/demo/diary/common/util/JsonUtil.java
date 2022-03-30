package com.demo.diary.common.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
    public JsonUtil(){

    }

    public static <T> T getEntityClazz(Object content,Class<T> clazz){
        if (StringUtil.isEmpty(content) && content instanceof String && ((String) content).startsWith("{")){
            content = JSON.parseObject(String.valueOf(content));
        }
        String jsonString = JSON.toJSONString(content);
        T t = JSON.parseObject(jsonString,clazz);
        return t;
    }
}
