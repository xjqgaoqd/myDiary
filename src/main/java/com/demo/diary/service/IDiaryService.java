package com.demo.diary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.pojo.Diary;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IDiaryService extends IService<Diary> {
    String addDiary(Map<String,Object> map) throws Exception;

    WrappedResult queryDiaryByCondition(ParameterCondition<Diary> param) throws Exception;

    void deleteDiaryByids(List<String> ids) throws Exception;

    void updateByMap(Map<String, Object> map)throws Exception;

    void downLoadDiary(String diaryId)throws Exception;
}
