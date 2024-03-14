import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**EliminarRegistro*/
public class EliminarRegistro {
    public static void main() {
        // Configuración de la conexión a la base de datos
        String url = "jdbc:postgresql://192.168.22.124:5432/dddbb";
        String usuario = "usuario";
        String contraseña = "password";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {
            // ID del registro que quieres eliminar
            int idRegistroAEliminar = 1;

            // Consulta SQL para eliminar el registro
            Scanner scanner = new Scanner(System.in);
            String tabla = scanner.nextLine();
            String sql = "DELETE FROM" + tabla + " WHERE id = ?";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, idRegistroAEliminar);

                // Ejecutar la consulta
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Registro eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró el registro con el ID especificado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
