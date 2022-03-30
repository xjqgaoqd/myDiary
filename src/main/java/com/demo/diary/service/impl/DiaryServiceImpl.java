package com.demo.diary.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.demo.diary.common.util.JsonUtil;
import com.demo.diary.common.util.ParameterCondition;
import com.demo.diary.common.util.StringUtil;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.mapper.DiaryContentMapper;
import com.demo.diary.mapper.DiaryMapper;
import com.demo.diary.mapper.LocalFileMapper;
import com.demo.diary.pojo.Diary;
import com.demo.diary.pojo.DiaryContent;
import com.demo.diary.pojo.LocalFile;
import com.demo.diary.pojo.User;
import com.demo.diary.service.IDiaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements IDiaryService {

    @Autowired
    private DiaryContentMapper diaryContentMapper;

    @Autowired
    private LocalFileMapper localFileMapper;

    @Override
    public void addDiary(Map<String,Object> map) throws Exception {
        try {
            Diary diary = new Diary();
            if (StringUtil.isEmpty(map.get("writeDate"))){
                diary.setWriteDate(new Date());
            }
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            diary.setWriteDate(ft.parse(map.get("writeDate").toString()));
            if (StringUtil.isEmpty(map.get("userId"))){
                return;
            }
            diary.setUserId(map.get("userId").toString());
            diary.setIsLock(0);
            diary.setKeyword(map.containsKey("keyWord") && !StringUtil.isEmpty(map.get("keyWord")) ? String.valueOf(map.get("keyWord")) : "");
            diary.setRemark(map.containsKey("remark") && !StringUtil.isEmpty(map.get("remark")) ? String.valueOf(map.get("remark")) : "1");
            diary.setMoodId(map.containsKey("moodId") && !StringUtil.isEmpty(map.get("moodId")) ? String.valueOf(map.get("moodId")) : "01");
            diary.setStatus(0);
            diary.setSite(map.containsKey("site") && !StringUtil.isEmpty(map.get("site")) ? String.valueOf(map.get("site")) : "");
            diary.setId(UUID.randomUUID().toString());
            diary.setScore(map.containsKey("score") && !StringUtil.isEmpty(map.get("score")) ? Integer.parseInt(map.get("score").toString()) : 1);

            Integer insetDiary = getBaseMapper().insert(diary);
            if (!insetDiary.equals(-1)){
                DiaryContent diaryContent = new DiaryContent();
                diaryContent.setDiaryId(diary.getId());
                diaryContent.setContent(String.valueOf(map.get("content")));
                diaryContentMapper.insert(diaryContent);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public WrappedResult queryDiaryByCondition(ParameterCondition<Diary> param) throws Exception {
        try {
            Diary diary = JsonUtil.getEntityClazz(param.getFilter(),Diary.class);
            PageHelper.startPage(param.getPageIndex(),param.getPageSize());
            if (StringUtil.isEmpty(diary.getStartTime())){
                diary.setStartTime("1974-01-01");
            }
            if (StringUtil.isEmpty(diary.getEndTime())){
                diary.setEndTime("2099-01-01");
            }
            List<Map<String, Object>> pages = getBaseMapper().queryDiaryListByCondition(diary);
            for (Map<String, Object> page : pages){
                Map queryMap = new HashMap();
                queryMap.put("diary_id",page.get("id").toString());
                List<LocalFile> fileList = localFileMapper.selectByMap(queryMap);
                if (!fileList.isEmpty()){
                    page.put("file",fileList);
                }
            }
            pages.stream().map(i -> i.put("writeDate",new SimpleDateFormat("yyyy-MM-dd").format(i.get("write_date")))).collect(Collectors.toList());

            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pages);
            return WrappedResult.successWrapedResult(pageInfo.getList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteDiaryByids(List<String> ids) throws Exception {
        try {
            for (String id: ids){
                Diary diary = getBaseMapper().selectById(id);
                diary.setStatus(-1);
                getBaseMapper().updateById(diary);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateByMap(Map<String, Object> map) throws Exception {
        try {
            Diary diary = getBaseMapper().selectById(map.get("id").toString());
            if (map.containsKey("moodId") && !StringUtil.isEmpty(map.get("moodId"))){
                diary.setMoodId(map.get("moodId").toString());
            }
            if (map.containsKey("keyword") && !StringUtil.isEmpty(map.get("keyword"))){
                diary.setKeyword(map.get("keyword").toString());
            }
            if (map.containsKey("site") && !StringUtil.isEmpty(map.get("site"))){
                diary.setSite(map.get("site").toString());
            }
            if (map.containsKey("isLock") && !StringUtil.isEmpty(map.get("isLock"))){
                diary.setIsLock(Integer.parseInt(map.get("isLock").toString()));
            }
            if (map.containsKey("score") && !StringUtil.isEmpty(map.get("score"))){
                diary.setScore(Integer.parseInt(map.get("score").toString()));
            }
            getBaseMapper().updateById(diary);
            if (map.containsKey("content") && !StringUtil.isEmpty(map.get("content"))) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("diary_id", map.get("id").toString());
                DiaryContent diaryContent = diaryContentMapper.selectOne(queryWrapper);
                diaryContent.setContent(map.get("content").toString());
                diaryContentMapper.update(diaryContent, queryWrapper);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void downLoadDiary(String diaryId) throws Exception {
        try {
            Diary diary = new Diary();
            diary.setId(diaryId);
            ParameterCondition param = new ParameterCondition<>();
            param.setPageIndex(1);
            param.setPageSize(10);
            param.setFilter(diary);
            List<Map<String, Object>> result = (List<Map<String, Object>>) this.queryDiaryByCondition(param).getResultValue();
            Map<String, Object> resultMap = result.get(0);
            List fileList = JSON.parseObject(JSON.toJSONString(resultMap.get("file")),List.class);
            if (!fileList.isEmpty()){
                for (Object file : fileList){
                    JSON json = (JSON) JSON.toJSON(file);
                    LocalFile localFile = JSONObject.toJavaObject(json,LocalFile.class);
                    String path = localFile.getPath()+localFile.getFileName();
                    resultMap.put("local", new PictureRenderData(80, 100, path));
                }
            }
            XWPFTemplate template = XWPFTemplate.compile("D:/testout.docx")
                    .render(resultMap);
            FileOutputStream out;
            StringBuilder path = new StringBuilder();
            path.append("D:"+diary.getId()+".docx");
            out = new FileOutputStream(path.toString());
            template.write(out);
            out.flush();
            out.close();
            template.close();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
