package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DS;
import model.dao.StudentDAO;
import model.dto.StudentDTO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lister")
public class Lister extends HttpServlet {

    private final StudentDAO DAO = new StudentDAO(new DS());
    public StudentDAO getDAO() { return DAO; }

    @Override
    protected void doGet(
            final HttpServletRequest req, 
            final HttpServletResponse res
    ) 
            throws ServletException, IOException 
    {
        res.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = res.getWriter();

        out.println("<body");
        out.println("    <h2>Liste des Étudiants</h2>");
        out.println("    <table");
        out.println("        <thead");
        out.println("            <tr>");
        out.println("                <th scope='col'>ID</th>");
        out.println("                <th scope='col'>Nom</th>");
        out.println("                <th scope='col'>Prénom</th>");
        out.println("                <th scope='col'>Groupe</th>");
        out.println("            </tr>");
        out.println("        </thead>");


        out.println("        <tbody>");
        for ( StudentDTO student : this.getDAO().findAll() ) {
            out.println("            <tr>");
            out.println("                <td>" + student.getId() + "</td>");
            out.println("                <td>" + student.getNom() + "</td>");
            out.println("                <td>" + student.getPrenom() + "</td>");
            out.println("                <td>" + student.getGroupe() + "</td>");
            out.println("            </tr>");
        }
        out.println("        </tbody>");

        out.println("    </table>");
        out.println("</body>");
        out.println("</html>");
    }
}
