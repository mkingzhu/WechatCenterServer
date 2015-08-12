package com.mkingzhu.wechat.center.server.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.mp.api.WxMpService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WxMpEndpointController {
    @Autowired
    private WxMpService wxMpService;

    @RequestMapping(value = "mp/mp.client", method = { RequestMethod.GET, RequestMethod.POST })
    public void mpClient(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (StringUtils.isEmpty(signature)
                || StringUtils.isEmpty(nonce)
                || StringUtils.isEmpty(timestamp)) {
            printResult(response, "非法请求");
            return;
        }

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            printResult(response, "非法请求");
            return;
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            printResult(response, echostr);
            return;
        }
    }

    private void printResult(HttpServletResponse response, String message) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(message);
        } catch (Exception ignore) {
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception ignore) {
            }
        }
    }
}
