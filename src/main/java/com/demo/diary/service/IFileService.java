package com.demo.diary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.pojo.LocalFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IFileService extends IService<LocalFile> {

    WrappedResult uploadFile(MultipartFile file, HttpServletRequest request,String userId,String diaryId);
}
