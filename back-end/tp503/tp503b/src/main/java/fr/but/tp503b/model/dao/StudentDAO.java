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
}
