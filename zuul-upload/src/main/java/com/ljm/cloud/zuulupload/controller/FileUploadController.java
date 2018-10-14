package com.ljm.cloud.zuulupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lijunming
 * @date 2018/9/24 下午7:12
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file")MultipartFile file)throws IOException {
        byte []bytes=file.getBytes();
        File fileToSave=new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes,fileToSave);
        return fileToSave.getAbsolutePath();
    }
}

