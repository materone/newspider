package com.chufan.news;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//字符编码过滤器
@WebFilter(urlPatterns = "/*",filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      request.setCharacterEncoding("GBK");
      response.setCharacterEncoding("GBK");
      System.out.println("In filter GBK");

      filterChain.doFilter(request , response);
  }
  @Override
  public void destroy() {
  }
}