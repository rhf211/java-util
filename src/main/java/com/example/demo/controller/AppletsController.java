package com.example.demo.controller;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.example.demo.entity.Result;
import com.example.demo.service.AppletsService;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 阮晗飞
 * @date: 2022/9/7
 */
@RestController
@RequestMapping
public class AppletsController {

    @Autowired
    private AppletsService appletsService;

    /**
     * @param code 登陆凭证code
     * @return
     */
    @PostMapping("/login")
    public String login(String code) {
        String sessionId = appletsService.login(code);
        return sessionId;
    }


    /**
     * @param sessionId 分发给用户的登陆凭证ID
     * @param signature wx响应数据签名
     * @param rawData 原始数据字符串
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    @PostMapping("/user/info")
    public WxMaUserInfo userInfo(
            @RequestParam("sessionId")String sessionId, @RequestParam("signature")String signature,
            @RequestParam("rawData")String rawData, @RequestParam("encryptedData") String encryptedData,
            @RequestParam("iv") String iv){
        WxMaUserInfo userInfo = appletsService.userInfo(sessionId, signature, rawData, encryptedData, iv);
        return userInfo;
    }

    /**
     * @param sessionId 分发给用户的登陆凭证ID
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     * @return
     */
    @PostMapping("/get/phone")
    public WxMaPhoneNumberInfo getPhone(@RequestParam("sessionId") String sessionId,
                                                @RequestParam("encryptedData") String encryptedData, @RequestParam("iv") String iv){
        WxMaPhoneNumberInfo phone = appletsService.getPhone(sessionId,encryptedData, iv);
        return phone;
    }


}
