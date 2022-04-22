package ca.sheridancollege.bichl.config;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * 拦截器-配置响应头部 X-Frame-Options
 * @author potato
 * @date 2021/9/8 11:04
 */
@WebFilter(filterName = "frameFilter", urlPatterns = "/*")
public class FrameConfig implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //必须
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //实际设置
        response.setHeader("X-Frame-Options", "ALLOW-FROM http://af9e-172-98-152-36.ngrok.io , http://groupingnow.ca");
//        response.setHeader("X-Frame-Options", "DENY");
        //调用下一个过滤器（这是过滤器工作原理，不用动）
        chain.doFilter(request, response);
    }
 
    public void init(FilterConfig config) throws ServletException {
    }
 
    public void destroy() {
 
    }
}