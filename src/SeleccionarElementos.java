import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SeleccionarElementos {

    private static final String JDBC_URL = "jdbc:postgresql://192.168.22.124:5432/ddbb";
    private static final String USER = "usuario";
    private static final String PASSWORD = "password";

    /**Conectarse base de datos*/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**Seleccionar Elementos para buscar*/
    public static void seleccionarElementos(String tabla, String columna, String textoConcreto) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM " + tabla + " WHERE " + columna + " LIKE ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + textoConcreto + "%"); // Utilizamos "%" para buscar el texto en cualquier parte del contenido.

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

    public static void main() {
        // Ejemplo de uso
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el nombre de la tabla:");
        String tabla = scanner.nextLine();
        System.out.println("Escribe el nombre de la columna:");
        String columna = scanner.nextLine();
        System.out.println("Escribe el texto concreto a buscar:");
        String textoConcreto = scanner.nextLine();

        seleccionarElementos(tabla, columna, textoConcreto);
    }
}