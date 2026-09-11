package jsp;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/S2")
public class S2 extends HttpServlet {
    @Override
    protected void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response
    )
            throws ServletException, IOException
    {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<p>All is ok for S2! </p>");;
    }
}
