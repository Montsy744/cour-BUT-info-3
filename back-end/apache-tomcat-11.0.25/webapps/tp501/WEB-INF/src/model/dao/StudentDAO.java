package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.DS;
import model.dto.StudentDTO;

public class StudentDAO {
    private final DS ds;
    public StudentDAO(final DS ds) { this.ds = ds; }
    
    public List<StudentDTO> findAll() {
        Connection conn = ds.getConnection();

        ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from etudiant");
            ) {
                while (rs.next()) {
                    result.add(
                        new StudentDTO(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("groupe")
                        )
                    );
                }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public boolean create (
            final StudentDTO student
    ) {
        Connection conn = ds.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement("insert into etudiant (id,nom,prenom,groupe) values (?,?,?,?);")) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getNom());
            pstmt.setString(3, student.getPrenom());
            pstmt.setString(4, student.getGroupe());

            pstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
