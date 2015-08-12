package com.mkingzhu.wechat.center.server.webservice.service;

import javax.jws.WebService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkingzhu.wechat.center.server.webservice.facade.WechatTokenService;

@WebService
public class WechatTokenServiceImpl implements WechatTokenService {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public String getAccessToken() {
        try {
            return wxMpService.getAccessToken();
        } catch (WxErrorException e) {
            return "";
        }
    }

    @Override
    public String getJsapiTicket() {
        try {
            return wxMpService.getJsapiTicket();
        } catch (WxErrorException e) {
            return "";
        }
    }
}
