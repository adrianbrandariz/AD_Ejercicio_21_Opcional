package ad_ejercicio_21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author abrandarizdominguez
 */
public class Conexion {

    String driver = "jdbc:oracle:thin:";
    String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
    String porto = "1521";
    String sid = "orcl";
    String usuario = "hr";
    String password = "hr";
    String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void connect() {
        /*
         * Para conectar co native protocal all java driver: creamos un obxecto 
         * Connection usando o metodo getConnection da clase  DriverManager            
         *
         * Connection conn = DriverManager.getConnection(url);
         */
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Conectado.");
            } else {
                System.out.println("Error en la conexión.");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("Desconectado.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void listar() {
        try {

            ps = conn.prepareStatement("select produtos.* from produtos where prezo>?");
            ps.setInt(1, 5);
            rs = (ResultSet) ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + ", ");
                System.out.print(rsmd.getColumnTypeName(i) + ", ");
                System.out.println(rsmd.getColumnDisplaySize(i));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }

}
