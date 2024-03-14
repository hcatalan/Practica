import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorBaseDatos {

    private Connection conexion;

    public GestorBaseDatos(String url, String usuario, String contraseña) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Insertar Registro*/
    public void insertarRegistro(String nombreTabla, String[] columnas, Object[] valores) {
        if (columnas.length != valores.length) {
            throw new IllegalArgumentException("La cantidad de columnas y valores debe ser la misma.");
        }

        StringBuilder query = new StringBuilder("INSERT INTO " + nombreTabla + " (");
        StringBuilder values = new StringBuilder(" VALUES (");

        for (int i = 0; i < columnas.length; i++) {
            query.append(columnas[i]);
            values.append("?");
            if (i < columnas.length - 1) {
                query.append(", ");
                values.append(", ");
            }
        }

        query.append(")");
        values.append(");");

        String insertQuery = query.toString() + values.toString();

        try (PreparedStatement statement = this.conexion.prepareStatement(insertQuery)) {
            for (int i = 0; i < valores.length; i++) {
                statement.setObject(i + 1, valores[i]);
            }

            int filasAfectadas = statement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Cerrar conexion Base de datos*/
    public void cerrarConexion() {
        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        // Ejemplo de uso
        String url = "jdbc:mysql://192.168.22.124.:5432/ddbb";
        String usuario = "usuario";
        String contraseña = "password";

        GestorBaseDatos gestorBD = new GestorBaseDatos(url, usuario, contraseña);

        // Definir la estructura de la tabla
        String[] estructuraTabla = {"nombre", "edad"};
        Object[] valoresRegistro = {"Juan", 25};

        // Insertar un registro en la tabla de usuarios
        gestorBD.insertarRegistro("usuarios", estructuraTabla, valoresRegistro);

        // Cerrar la conexión cuando hayas terminado
        gestorBD.cerrarConexion();
    }
}
