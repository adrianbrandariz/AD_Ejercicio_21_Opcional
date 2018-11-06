package ad_ejercicio_21;

/**
 *
 * @author abrandarizdominguez
 */
public class AD_EJercicio_21 {

    public static void main(String[] args) {
        Conexion conn = new Conexion();
        conn.connect();
        conn.listar();
        conn.close();
    }
    
}
