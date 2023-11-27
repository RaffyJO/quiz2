/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quiz2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Asus
 */
public class DBConnection {
    private static Connection con;

    public void getDBConn() {
        synchronized ("") {
            try {
                if (this.getCon() == null || this.getCon().isClosed()) {
                    try {
                        String url = "jdbc:mysql://localhost:3307/oop_quiz2";     
                        Class.forName("com.mysql.cj.jdbc.Driver"); 
                        setCon(DriverManager.getConnection(url, "root", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
        /**
     * @return the con
     */
    public static Connection getCon() {
        return con;
    }

    /**
     * @param aCon the con to set
     */
    public static void setCon(Connection aCon) {
        con = aCon;
    }
    public static void closeConnection(){
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
