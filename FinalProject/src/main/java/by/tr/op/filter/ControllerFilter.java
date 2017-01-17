package by.tr.op.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ControllerFilter implements Filter{
    private final static String CONTENT_TYPE="text/html;charset=UTF-8";
    private final static String CHARSET="UTF-8";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE);
        request.setCharacterEncoding(CHARSET);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

}
