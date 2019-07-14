package com.xyq.filter;

import com.xyq.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/pages/*")
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if(session.getAttribute("mid")!=null){
            filterChain.doFilter(request,response);
        }else {//应该跳转到错误页,进行信息展示
            String msg = ResourceUtils.get("Messages","login.check.failure.msg");
            String url = ResourceUtils.get("Pages","login.page");
            String forward = ResourceUtils.get("Pages","forward.page");
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            request.getRequestDispatcher(forward).forward(request,response);
        }
    }

    public void destroy() {

    }
}
