package fr.but3.tp503a;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
*/
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );
        /*
        final String url = "jdbc:postgresql://psqlserv/but3";
        final String user = "hugostraseeleetu";
        final String password = "moi";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Tu es connecté : " + con);
            con.close();
        } catch (final SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            System.out.println("Failed to load JDBC driver.");
            e.printStackTrace();
        }
        */

        final Properties prop = new Properties();
        try (final InputStream input = App.class.getClassLoader().getResourceAsStream("data.txt")) {

            if (input == null) {
                System.out.println("Désolé, impossible de trouver data.txt");
                return;
            }
            prop.load(input);

            System.out.println("Valeur de 'nom' : " + prop.getProperty("nom"));

        } catch (final IOException ex) {
            ex.printStackTrace();
        }

    }
}
