package model;

import java.sql.*;

public class DS {
    private Connection connection;


    public DS() {
        try {            
            // Connexion à la base
            String url = "jdbc:postgresql://psqlserv/but3";
            String nom = "hugostraseeleetu"; 
            String mdp = "moi";
            this.connection = DriverManager.getConnection(url, nom, mdp);
        } catch (Exception e) {
            System.out.println("Erreur dans les données lors de la création " + e);
        } 
    }


    public Connection getConnection(){
        return this.connection;
    }
}