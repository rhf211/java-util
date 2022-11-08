package com.example.demo.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Result;
import com.example.demo.entity.UserDto;
import com.example.demo.service.AppletsService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 阮晗飞
 * @date: 2022/9/7
 */
@Service
public class AppletsServiceImpl implements AppletsService {

    @Autowired
    WxMaService wxMaService;

    @Override
    public String login(String code) {
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            System.out.println(session.getOpenid());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public WxMaUserInfo userInfo(String sessionId, String signature, String rawData, String encryptedData, String iv) {
        return null;
    }

    @Override
    public WxMaPhoneNumberInfo getPhone(String sessionId, String encryptedData, String iv) {
        return null;
    }



    public static void main(String[] args) throws UnsupportedEncodingException {

    }
}
