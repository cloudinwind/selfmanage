package com.cloudinwind.selfmanage.controller;

import com.cloudinwind.selfmanage.annotation.UserLoginToken;
import com.cloudinwind.selfmanage.dto.FileDTO;
import com.cloudinwind.selfmanage.dto.UserDTO;
import com.cloudinwind.selfmanage.provider.QCloudProvider;
import com.cloudinwind.selfmanage.util.CookieUtils;
import com.cloudinwind.selfmanage.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wadao
 * @version 2.0
 * @date 2020/5/1 15:17
 * @site niter.cn
 */

@Controller
@Slf4j
public class FileController {
    @Autowired
    private QCloudProvider qCloudProvider;
    @Autowired
    private CookieUtils cookieUtils;
    @Autowired
    private TokenUtils tokenUtils;

    @UserLoginToken
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileDTO upload(HttpServletRequest request,
                          @RequestParam("myFile") List<MultipartFile> multipartFiles) {

        try {

            UserDTO user = (UserDTO)request.getAttribute("loginUser");
            InputStream inputStream;
            String contentType;
            String filename;
            Long contentLength;
            String[] data = new String[multipartFiles.size()];
            int count = 0;
            for (MultipartFile multipartFile : multipartFiles){
                inputStream = multipartFile.getInputStream();
                contentType = multipartFile.getContentType();
                filename = getFileName(contentType);
                contentLength = multipartFile.getSize();
                String url = qCloudProvider.upload(inputStream,contentType,user,filename,contentLength);
                data[count] = url;
                count++;
            }
            FileDTO fileDTO = new FileDTO();
            fileDTO.setErrno(0);
            fileDTO.setData(data);
            return fileDTO;
       } catch (Exception e) {
            log.error("upload error", e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setErrno(1);
            return fileDTO;
        }
    }

    //图片上传接口
    @UserLoginToken
    @PostMapping("/file/layUpload")
    @ResponseBody
    public Map<String,Object> uploadLayImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Map<String,Object> map  = new HashMap<>();
        try {
            UserDTO user = (UserDTO)request.getAttribute("loginUser");

            InputStream inputStream;
            String contentType;
            String filename;
            Long contentLength;
            inputStream = file.getInputStream();
            contentType = file.getContentType();
            filename = getFileName(contentType);
            contentLength = file.getSize();


            String url = qCloudProvider.upload(inputStream,contentType,user,filename,contentLength);
            map.put("code",0);
            map.put("msg","");
            map.put("data",url);
           // System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","上传失败");
            map.put("data",null);
            return map;
        }
    }

    //人脸头像上传接口
    @UserLoginToken
    @PostMapping("/file/avatar")
    @ResponseBody
    public Map<String,Object> uploadAvatar(HttpServletResponse response, HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Map<String,Object> map  = new HashMap<>();
        // String uploadDir = "F:/kdgc_project/Student_Attendence_Application/src/main/webapp/resources/upload/";
        try {
            UserDTO user = (UserDTO)request.getAttribute("loginUser");
            InputStream inputStream;
            String contentType;
            String filename;
            Long contentLength;
            inputStream = file.getInputStream();
            contentType = file.getContentType();
            filename = getFileName(contentType);
            contentLength = file.getSize();
            String url = qCloudProvider.uploadAvatar(inputStream,contentType,user,filename,contentLength);
            map.put("status",0);
            map.put("msg","");
            map.put("url",url);
            user.setAvatarUrl(url);
            Cookie cookie = cookieUtils.getCookie("token",tokenUtils.getToken(user),86400*3);
            response.addCookie(cookie);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",500);
            map.put("msg","上传失败");
            map.put("url",null);
            return map;
        }
    }



    public String getFileName(String contentType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String[] ss = contentType.split("/");
        String str = RandomStringUtils.random(2,
                "abcdefghijklmnopqrstuvwxyz1234567890");
        String name = timeStr + "_" +str+"."+ss[1];
        return name;
    }

}
