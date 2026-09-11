package jsp;

import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/S1")
public class S1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // via requestDispatcher ca ne relance pas les filter
        response.sendRedirect("S2");
    }
}