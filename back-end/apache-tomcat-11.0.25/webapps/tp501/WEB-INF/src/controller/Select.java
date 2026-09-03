package controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.DS;
import model.dao.StudentDAO;
import model.dto.StudentDTO;
import jakarta.servlet.annotation.*;

@WebServlet("/Servlet-Select")
public class Select extends HttpServlet {
    public void service(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {
                
        StudentDAO dao = new StudentDAO(new DS());
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");

        List<StudentDTO> allStudents = dao.findAll();
        
        for (StudentDTO student : allStudents) {
            out.println("<h2> " + student.getId() + "</h2>");
            out.println("<td> " + student.getNom() + "<td> " + student.getPrenom() + "<td> " + student.getGroupe());
        }

        out.println("</body></html>");
    }
}
