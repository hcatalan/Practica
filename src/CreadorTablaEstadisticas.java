import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreadorTablaEstadisticas {

    // Cambia estos valores según tu configuración de base de datos PostgreSQL
    static final String URL = "jdbc:postgresql://192.168.22.125:5432/ddbb";
    static final String USUARIO = "usuario";
    static final String CONTRASENA = "password";

    // Método para crear la tabla "estadisticas"
    public static void crearTablaEstadisticas() {
        try {
            // Establecer la conexión con la base de datos
            Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            // Crear una declaración
            Statement stmt = conn.createStatement();

            // Sentencia SQL para crear la tabla
            String sql = "CREATE TABLE estadisticas ("
                    + "Equipo VARCHAR(100) PRIMARY KEY,"
                    + "Partidos_Jugados INT,"
                    + "Partidos_Ganados INT,"
                    + "Partidos_Empatados INT,"
                    + "Partidos_Perdidos INT,"
                    + "Goles INT,"
                    + "Diferencia_Goles INT,"
                    + "Puntos INT"
                    + ")";

            // Ejecutar la sentencia SQL
            stmt.executeUpdate(sql);
            System.out.println("Tabla 'estadisticas' creada correctamente.");

            // Cerrar la conexión y liberar recursos
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al intentar crear la tabla 'estadisticas': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Llamar al método para crear la tabla "estadisticas"
        crearTablaEstadisticas();
    }
}
