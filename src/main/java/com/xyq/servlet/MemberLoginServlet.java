package com.xyq.servlet;

import com.xyq.service.back.IMemberServiceBack;
import com.xyq.service.back.impl.MemberServiceBackImpl;
import com.xyq.util.EncryptUtil;
import com.xyq.util.factory.ServiceFactory;
import com.xyq.util.servlet.DispatcherServlet;
import com.xyq.vo.Member;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/MemberLoginServlet/*")
public class MemberLoginServlet extends DispatcherServlet {
    private Member member = new Member();

    public String login(){
        member.setPassword(EncryptUtil.getPwd(member.getPassword()));//需要针对于MD5的加密处理
        IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);
        try {
            Map<String, Object> result = memberServiceBack.login(member);
            boolean flag = (Boolean) result.get("flag");
            if(flag){//登陆检测
                setSessionAttribute("name",result.get("name"));
                setSessionAttribute("sflag",result.get("sflag"));
                setUrlAndMsg("index.page","login.success.msg");
            }else {//登陆失败
                setUrlAndMsg("login.page","login.failure.msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward.page";
    }

    public Member getMember() {
        return member;
    }

    public String getDefaultColumn() {
        return null;
    }

    public String getUploadDir() {
        return null;
    }

    public String getType() {
        return null;
    }
}
