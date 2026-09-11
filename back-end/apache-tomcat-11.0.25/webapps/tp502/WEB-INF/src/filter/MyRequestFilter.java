package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebFilter("/*")
public class MyRequestFilter extends HttpFilter {

    public void doFilter(
            final HttpServletRequest req,
            final HttpServletResponse res,
            final FilterChain chain
    )
            throws IOException, ServletException
    {
        System.out.println(LocalDate.now() + " : " + req.getRequestURL());
        chain.doFilter(req, res);
    }


}
