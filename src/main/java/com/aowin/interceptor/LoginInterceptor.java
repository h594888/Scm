package com.aowin.interceptor;

import com.aowin.domain.ScmUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Jiaozehua
 * @Date 2022/11/22 21:00
 * @Description TODO
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {


        System.out.println("...LoginInterceptor...");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//
//        System.out.println(method.getName());

        //检查是否需要跳出路径，有则跳过认证
//        if ("login".equals(method.getName())) {
//            return true;
//        }

        ScmUser scmUser = (ScmUser) request.getSession().getAttribute("loginUser");

        if(scmUser != null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/login_in.html");
            return false;
        }
    }
}
