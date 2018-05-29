/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author macarena
 */
public class GestorBD {

    private static Connection conn = null;
    private static String rutaDB = "//localhost:3306/practica4";
    private static String login = "";
    private static String passwd = "";

    public static void setLogin(String login) {
        GestorBD.login = login;
    }

    public static void setPasswd(String passwd) {
        GestorBD.passwd = passwd;
    }

    public static Connection abrirConexion() {
        if (conn != null) {
            cerrarConexion();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql:" + rutaDB, login, passwd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
