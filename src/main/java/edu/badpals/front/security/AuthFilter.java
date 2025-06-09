package edu.badpals.front.security;

import edu.badpals.front.dto.UserDto;
import edu.badpals.front.mysc.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        if ( uri.startsWith("/css/") || uri.startsWith("/js/") ||
                uri.startsWith("/images/") ||  uri.startsWith("/login") || uri.startsWith("/register")) {
            chain.doFilter(request, response);
            return;
        }

        if (session != null && session.getAttribute(Constants.LOGGED_USER) != null) {
            if (uri.startsWith("/manage/")){
                try{
                    String id = uri.split("/")[2];
                    if (Long.valueOf(id) == ((UserDto) session.getAttribute(Constants.LOGGED_USER)).getId()){
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect("/menu");
                    }
                } catch (Exception e){
                    res.sendRedirect("/menu");
                }
            } else {
                chain.doFilter(request, response);
            }

        } else {
            res.sendRedirect("/login");
        }
    }
}