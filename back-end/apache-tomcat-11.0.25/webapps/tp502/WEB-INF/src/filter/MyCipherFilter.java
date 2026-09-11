package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class MyCipherFilter extends HttpFilter {
    @Override
    protected void doFilter(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain
    )
            throws IOException, ServletException
    {
        class WrapperRequest extends HttpServletRequestWrapper {
            public WrapperRequest(final ServletRequest request) {
                super((HttpServletRequest) request);
            }

            @Override
            public final String getParameter(final String name) {
                if (getParameterMap().get(name) == null) return null;
                if (name.equals("password")) return getParameterMap().get(name)[0].toUpperCase();
                else return getParameterMap().get(name)[0];
            }
        }
        final WrapperRequest wrappedRequest = new WrapperRequest(request);
        chain.doFilter(wrappedRequest, response);

    }
}
