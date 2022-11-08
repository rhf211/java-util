package com.example.demo.service;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.example.demo.entity.Result;

/**
 * @author: 阮晗飞
 * @date: 2022/9/7
 */
public interface AppletsService {
    String login(String code);

    WxMaUserInfo userInfo(String sessionId, String signature, String rawData, String encryptedData, String iv);

    WxMaPhoneNumberInfo getPhone(String sessionId, String encryptedData, String iv);

}
