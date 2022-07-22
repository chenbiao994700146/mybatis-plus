package com.mybatisplus.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * @author chenbiao
 * @Data 2022/6/21 10:59 AM
 */

@Component
public class MyFilter implements Filter {

    /**
     * 初始化方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("我正在初始化init。。。");
    }

    /**
     * 执行方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteHost = servletRequest.getRemoteHost();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = request.getRequestURL();
        String requestURLString=requestURL.toString();
        if(requestURLString.contains("/test")){
            String substring = requestURL.substring(0, requestURL.lastIndexOf("/"));

            substring=substring+"/hello";

            System.out.println("substring"+substring);
            System.out.println("requestURL:"+requestURL);
            System.out.println("remoteHost:"+remoteHost);
            System.out.println("我正在执行doFilter。。");
            request.getRequestDispatcher("/hello").forward(request,response);//重定向
            //response.sendRedirect(substring);//转发

            return;
        }

        //filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("我正在销毁destroy。。。");
    }
}
