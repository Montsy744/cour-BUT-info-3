package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class MyTimeFilter extends HttpFilter {
    public void doFilter (
            final HttpServletRequest req,
            final HttpServletResponse res,
            final FilterChain chain
    )
            throws IOException, ServletException
    {
        final long startTime = System.currentTimeMillis();
        chain.doFilter(req, res);
        System.out.println("le temps d'éxécution est de " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }
}
