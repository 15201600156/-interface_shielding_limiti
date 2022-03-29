package com.study.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class InterfaceRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化了........init... " + filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤前........doFilter "); //过滤器前的处理
        filterChain.doFilter(servletRequest, servletResponse);//继续执行后续方法
        System.out.println("过滤后.......doFilter"); //方法执行完成后

    }

    @Override
    public void destroy() {
        System.out.println("销毁了.....destroy");
    }
}
