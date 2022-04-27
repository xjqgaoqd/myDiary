package com.demo.diary.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.util.StringUtil;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.pojo.Diary;
import com.demo.diary.service.IDiaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping("/diary")
@CrossOrigin
@Api(tags = "日记接口")
public class DiaryController {

    public static final Logger log = LoggerFactory.getLogger(DiaryController.class);
    @Autowired
    private IDiaryService diaryService;

    @ApiOperation("新增日记接口")
    @PostMapping("/addDiary")
    public WrappedResult addDiary(@RequestBody Map<String, Object> diaryMap){
        try {
            String diaryId = diaryService.addDiary(diaryMap);
            return WrappedResult.successWrapedResult(diaryId);
        }catch (Exception e){
            return WrappedResult.failedWrappedResult("新增日记失败");
        }
    }

    @ApiOperation("根据条件查询")
    @PostMapping("/queryDiaryByCondition")
    public WrappedResult queryDiaryByCondition(@RequestBody ParameterCondition<Diary> parameterCondition){
        try {
            return diaryService.queryDiaryByCondition(parameterCondition);
        }catch (Exception e){
            return WrappedResult.failedWrappedResult("查询日记异常");
        }
    }

    @ApiOperation("根据id删除")
    @PostMapping("/deleteDiaryByids")
    public WrappedResult deleteDiaryByids(@RequestBody List<String> ids){
        try {
            diaryService.deleteDiaryByids(ids);
            return WrappedResult.successWrapedResult("服务调用成功");
        }catch (Exception e){
            return WrappedResult.failedWrappedResult("查询日记异常");
        }
    }

    @ApiOperation("更新日记")
    @PostMapping("/updateDiaryById")
    public WrappedResult updateDiaryById(@RequestBody Map<String, Object> updateMap){
        try {
            if (!updateMap.containsKey("id") || StringUtil.isEmpty(updateMap.get("id"))){
                return WrappedResult.failedWrappedResult("id为空");
            }
            diaryService.updateByMap(updateMap);
            return WrappedResult.successWrapedResult("服务调用成功");
        }catch (Exception e){
            return WrappedResult.failedWrappedResult("更新异常");
        }
    }

    @ApiOperation("按模板导出日记")
    @GetMapping("/exportDiary")
    public void exportDiary(@RequestParam("diary_id") String diaryId , HttpServletRequest request, HttpServletResponse response){
        try {
            diaryService.downLoadDiary(diaryId);
        }catch (Exception e){
            log.error("导出异常",e);
        }
    }
}
