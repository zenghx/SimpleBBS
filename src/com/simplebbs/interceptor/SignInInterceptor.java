package com.simplebbs.interceptor;

import com.simplebbs.po.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if (uri.contains("/new_post") || uri.contains("/new_comment") || uri.contains("/settings")) {
            HttpSession session = httpServletRequest.getSession();
            UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
            if (user == null) {
                httpServletRequest.setAttribute("msg", "请先登录！");
                httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/page/sign_in.jsp")
                        .forward(httpServletRequest, httpServletResponse);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
