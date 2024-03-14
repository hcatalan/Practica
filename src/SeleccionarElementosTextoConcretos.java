import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SeleccionarElementosTextoConcretos {

    private static final String JDBC_URL = "jdbc:postgresql://192.168.22.124:5432/ddbb";
    private static final String USER = "usuario";
    private static final String PASSWORD = "password";

    /**Conectarse base de datos*/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**Seleccionar Elementos con condiciones*/
    public static void seleccionarElementosConCondicion(String tabla, String condicion) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM " + tabla + " WHERE " + condicion;

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Aquí puedes obtener los resultados y procesarlos según tus necesidades.
                        System.out.println("ID: " + resultSet.getInt("id") + ", Nombre: " + resultSet.getString("nombre"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

