package com.mkingzhu.wechat.center.server.webservice.service;

import javax.jws.WebService;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkingzhu.wechat.center.server.webservice.facade.WechatTokenService;

@WebService
public class WechatTokenServiceImpl implements WechatTokenService {
    @Autowired
    private WxMpService wxMpService;

    @Override
    public String getAccessToken()
            throws WxErrorException {
        return wxMpService.getAccessToken();
    }

    @Override
    public String getJsapiTicket()
            throws WxErrorException {
        return wxMpService.getJsapiTicket();
    }

    @Override
    public WxJsapiSignature createJsapiSignature(String url)
            throws WxErrorException {
        return wxMpService.createJsapiSignature(url);
    }
}
