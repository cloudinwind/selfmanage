package com.cloudinwind.selfmanage.controller;

import com.cloudinwind.selfmanage.annotation.UserLoginToken;
import com.cloudinwind.selfmanage.dto.PaginationDTO;
import com.cloudinwind.selfmanage.dto.UserDTO;
import com.cloudinwind.selfmanage.exception.CustomizeErrorCode;
import com.cloudinwind.selfmanage.exception.CustomizeException;
import com.cloudinwind.selfmanage.mapper.UserAccountMapper;
import com.cloudinwind.selfmanage.mapper.UserInfoMapper;
import com.cloudinwind.selfmanage.mapper.UserMapper;
import com.cloudinwind.selfmanage.entity.*;
import com.cloudinwind.selfmanage.service.NotificationService;
import com.cloudinwind.selfmanage.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ForumProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @Value("${vaptcha.vid}")
    private String vaptcha_vid;
    @Value("${sms.enable}")
    private Integer smsEnable;

    // 跳转到消息管理页面
    @UserLoginToken
    @GetMapping("/user/message")
    public String messeage(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page",defaultValue = "1")Integer page,
                           @RequestParam(name = "size",defaultValue = "5")Integer size){
        UserDTO user = (UserDTO)request.getAttribute("loginUser");

        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "message");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("navtype", "notifynav");

        return "user/message";
    }

    // 跳转到帖子管理页面
    @UserLoginToken
    @GetMapping("/user/p/{action}")
    public String p(HttpServletRequest request,
                    @PathVariable(name = "action") String action,
                    Model model,
                    @RequestParam(name = "page",defaultValue = "1")Integer page,
                    @RequestParam(name = "size",defaultValue = "10")Integer size){
        UserDTO user = (UserDTO)request.getAttribute("loginUser");


        if("myPosts".equals(action)){
            model.addAttribute("section", "myPosts");
            model.addAttribute("sectionName", "我的帖子");
            PaginationDTO pagination = questionService.listByUserId(user.getId(), page, size);
            model.addAttribute("pagination",pagination);
            model.addAttribute("navtype", "communitynav");
        }
        if("likes".equals(action)){
            PaginationDTO paginationDTO = questionService.listByExample(user.getId(), page, size,"likes");
            model.addAttribute("section", "likes");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("sectionName", "我的收藏");
            model.addAttribute("navtype", "communitynav");
        }

        return "user/p";
    }

    // 跳转到账户中心或基本设置页面
    @UserLoginToken
    @GetMapping("/user/set/{action}")
    public String getSetPage(HttpServletRequest request,
                             @PathVariable(name = "action") String action,
                             Model model) {
        UserDTO loginUser = (UserDTO)request.getAttribute("loginUser");
        if(loginUser == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        User user = userMapper.selectByPrimaryKey(loginUser.getId());
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(loginUser.getId());
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        UserAccount userAccount = userAccounts.get(0);
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(loginUser.getId());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        UserInfo userInfo = userInfos.get(0);
        model.addAttribute("user", user);
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("userInfo", userInfo);
        // 跳转到基本设置
        if("info".equals(action)|| StringUtils.isBlank(action)){
            model.addAttribute("section", "info");
            model.addAttribute("sectionName", "我的资料");
            model.addAttribute("sectionInfo", "绑定邮箱账号后，您可以使用邮箱账号登录本站，也可用邮箱账号找回密码\n");
            model.addAttribute("navtype", "communitynav");
            return "user/set";
        }
        // 跳转到账户中心
        if("account".equals(action)){
            model.addAttribute("section", "account");
            model.addAttribute("sectionName", "绑定/更新邮箱账号");
            model.addAttribute("sectionInfo", "绑定邮箱账号后，您可以使用邮箱账号登录本站，也可用邮箱账号找回密码\n");
            model.addAttribute("navtype", "communitynav");
            model.addAttribute("smsEnable", smsEnable);
            model.addAttribute("vaptcha_vid", vaptcha_vid);
            return "user/account";
        }
        return "user/set";
    }



}
