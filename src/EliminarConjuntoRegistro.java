import java.sql.*;
import java.util.Scanner;

public class EliminarConjuntoRegistro {

    private static final String DB_URL = "jdbc:postgresql://192.168.22.125:5432/ddbb";
    private static final String DB_USER = "usuario";
    private static final String DB_PASSWORD = "password";

    /**Borrar registro*/
    public static void deleteRecords(String tableName, String columnName, String condition) {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, condition);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el nombre de la tabla");
        String tableName = scanner.nextLine();
        System.out.println("Escribe el nombre de la columna");
        String columnName = scanner.nextLine();
        System.out.println("Escribe la condicion");
        String condition = scanner.nextLine();

        deleteRecords(tableName, columnName, condition);
    }
}

