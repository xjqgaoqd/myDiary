package com.demo.diary.controller;

import com.demo.diary.common.vo.WrappedResult;
import com.demo.diary.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    IFileService fileService;

    @PostMapping("/uploadFile")
    public WrappedResult uploadFile(@RequestParam(value = "file",required = false)MultipartFile file,
                                    HttpServletRequest request,
                                    @RequestParam(value = "userId",required = true) String userId,
                                    @RequestParam(value = "diaryId",required = true) String diaryId){
        try {
            return fileService.uploadFile(file,request,userId,diaryId);
        }catch (Exception e){
            return WrappedResult.failedWrappedResult(e.getMessage());
        }
    }
}
