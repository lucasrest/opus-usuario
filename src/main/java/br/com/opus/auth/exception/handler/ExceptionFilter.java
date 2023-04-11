package br.com.opus.auth.exception.handler;

import br.com.opus.auth.model.comum.ApiResponse;
import br.com.opus.auth.util.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            fillResponseWithError(response, e);
            return;
        }
    }

    public static void fillResponseWithError(HttpServletResponse response, Exception e) throws IOException {

        response.setStatus(ApiExceptionHandler.getStatus(e));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(JsonUtils.objectToJson(ApiResponse.returnError(e)));
        out.flush();
    }

    @Override
    public void destroy() {

    }
}
