import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/compteur")
public class Compteur extends HttpServlet {
    private int global_compter = 0;
    public final int getGlobal_compter() { return global_compter; }
    public void addGlobal_compter() { this.global_compter ++; }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {


        final HttpSession session = request.getSession(true);
        Integer cpt_local = (Integer) session.getAttribute("compteur");
        cpt_local = (cpt_local == null ? 1 : cpt_local.intValue() + 1);
        this.addGlobal_compter();
        session.setAttribute("compteur", cpt_local);

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<p> Compteur Global : " + this.getGlobal_compter() + "</p>");
        out.println("<p> Compteur Local : " + cpt_local + "</p>");
        out.println("</body></html>");
    }
}
