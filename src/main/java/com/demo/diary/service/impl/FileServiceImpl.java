package com.demo.diary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.mapper.LocalFileMapper;
import com.demo.diary.pojo.LocalFile;
import com.demo.diary.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<LocalFileMapper, LocalFile> implements IFileService {

    @Override
    public WrappedResult uploadFile(MultipartFile file, HttpServletRequest request,String userId,String diaryId) {
        if (file.isEmpty()) {
            return WrappedResult.failedWrappedResult("文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "D://DiaryPicture/";
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            LocalFile localFile = new LocalFile();
            localFile.setId(UUID.randomUUID().toString());
            localFile.setFileName(fileName);
            localFile.setPath(filePath);
            localFile.setDiaryId(diaryId);
            localFile.setUserId(userId);
            getBaseMapper().insert(localFile);
            return WrappedResult.successWrapedResult("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WrappedResult.failedWrappedResult("上传失败");
    }
}
