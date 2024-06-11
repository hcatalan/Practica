import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerController {
    private Connection connection;

    public PlayerController(Connection connection) {
        this.connection = connection;
    }

    /**Añadir Jugador*/
    public void addPlayer() throws SQLException, NumberFormatException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserta nombre: ");
        String nombre = br.readLine();
        System.out.println("Inserta apellidos: ");
        String apellidos = br.readLine();
        System.out.println("Inserta dorsal: ");
        int dorsal = Integer.parseInt(br.readLine());
        System.out.println("Inserta nacimiento: ");
        String data = br.readLine();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date naixement = df.parse(data);
        java.sql.Date sDate = new java.sql.Date(naixement.getTime());
        System.out.println("Inserta posicion: ");
        String posicion = br.readLine();
        System.out.println("Inserta valor de mercado: ");
        int vmercado = Integer.parseInt(br.readLine());
        System.out.println("Inserta equipo: ");
        String equipo = br.readLine();
        String sql = "INSERT INTO player(nombre,apellidos,dorsal,nacimiento,posicion,valormercado,equipo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = this.connection.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2, apellidos);
        pst.setInt(3, dorsal);
        pst.setDate(4, sDate);
        pst.setString(5, posicion);
        pst.setInt(6, vmercado);
        pst.setString(7, equipo);
        pst.executeUpdate();
    }

    /**Generar XML*/
    public void generateXML() throws SQLException, IOException {
        String sql = "SELECT nombre, apellidos, dorsal, nacimiento, posicion, valormercado, equipo FROM player";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        PrintWriter writer = new PrintWriter("players.xml", "UTF-8");
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<players>");

        while (rs.next()) {
            writer.println("  <player>");
            writer.println("    <nombre>" + rs.getString("nombre") + "</nombre>");
            writer.println("    <apellidos>" + rs.getString("apellidos") + "</apellidos>");
            writer.println("    <dorsal>" + rs.getInt("dorsal") + "</dorsal>");
            writer.println("    <nacimiento>" + rs.getDate("nacimiento") + "</nacimiento>");
            writer.println("    <posicion>" + rs.getString("posicion") + "</posicion>");
            writer.println("    <valormercado>" + rs.getInt("valormercado") + "</valormercado>");
            writer.println("    <equipo>" + rs.getString("equipo") + "</equipo>");
            writer.println("  </player>");
        }

        writer.println("</players>");
        writer.close();
    }

    public static void main(String[] args) {
        // Aquí se supone que tienes una conexión a la base de datos configurada y lista para usar
        Connection connection = null; // Configura tu conexión aquí

        PlayerController playerController = new PlayerController(connection);

        try {
            playerController.addPlayer();  // Para añadir un jugador
            playerController.generateXML();  // Para generar el XML con los jugadores
        } catch (SQLException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
