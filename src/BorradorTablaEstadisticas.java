import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BorradorTablaEstadisticas {

    // Cambia estos valores según tu configuración de base de datos
    static final String URL = "jdbc:postgresql://192.168.22.125:5432/ddbb";
    static final String USUARIO = "usuario";
    static final String CONTRASENA = "password";

    // Método para borrar la tabla "estadisticas"
    public static void borrarTablaEstadisticas() {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            // Crear una declaración
            Statement stmt = conn.createStatement();

            // Consulta SQL para borrar la tabla
            String sql = "DROP TABLE IF EXISTS estadisticas";

            // Ejecutar la consulta
            stmt.executeUpdate(sql);
            System.out.println("La tabla 'estadisticas' ha sido borrada correctamente.");

            // Cerrar la conexión y liberar recursos
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al intentar borrar la tabla 'estadisticas': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Llamar al método para borrar la tabla "estadisticas"
        borrarTablaEstadisticas();
    }
}
