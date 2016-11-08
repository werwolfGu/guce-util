package com.guce.filter;

import sun.text.normalizer.Trie;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/10/29.
 */
public class PageRedirect implements Filter {
    private Trie trie;
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

//        req.getRequestDispatcher("/pages/nail/index.html").forward(request,response);
        String url = req.getRequestURI();
        if(url.endsWith("index.html")){
            chain.doFilter(request,response);
            return;
        }
        httpResponse.sendRedirect(req.getContextPath() + "/pages/nail/index.html");
        return;
    }

    public void destroy() {

    }
}
