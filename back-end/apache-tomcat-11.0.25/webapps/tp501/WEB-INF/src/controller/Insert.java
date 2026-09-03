    package controller;

    import java.io.*;
    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import model.DS;
    import model.dao.StudentDAO;
    import jakarta.servlet.annotation.*;
    import model.dto.StudentDTO;

    @WebServlet("/insert")
    public class Insert extends HttpServlet {
        public void service(final HttpServletRequest req, final HttpServletResponse res)
                throws ServletException, IOException {

            final StudentDAO dao = new StudentDAO(new DS());

            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<html><body>");
            out.println("<a href=\"form.html\">retour a l'insertion</a>");

            final boolean isCreate = dao.create(
                    new StudentDTO(
                            Integer.parseInt(req.getParameter("id")),
                            req.getParameter("nom"),
                            req.getParameter("prenom"),
                            req.getParameter("groupe")
                    )
            );

            if(isCreate) {
                out.println("<h1> étudiant créer </h1>");
            } else {
                out.println("<h1> création impossible rééssayer ultérieurement </h1>");
            }



            out.println("</body></html>");
        }
    }
