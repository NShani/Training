package com.wso2.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

    FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        
        PrintWriter out = servletResponse.getWriter();
        out.println("my-param (InitParameter): " + filterConfig.getInitParameter("my-param"));
        out.println("<br/><br/>Parameters:<br/>");
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        String ipAddress = servletRequest.getRemoteAddr();
        if (parameterNames.hasMoreElements()) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = servletRequest.getParameter(name);
                out.println("name:" + name + ", value: " + value + "<br/>");
                out.println("IP"+ipAddress+", Time "
                        + new Date().toString());
            }
        } else {
            out.println("---None---<br/>");
        }
        out.println("<br/>Start Regular Content:<br/><hr/>");
        filterChain.doFilter(servletRequest, servletResponse);
        out.println("<br/><hr/>End Regular Content:<br/>");

    }

}
