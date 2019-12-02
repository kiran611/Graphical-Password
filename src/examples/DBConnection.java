/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class DBConnection { 
    private static Connection con = null;    
    public static Connection getDBConnection() {
        String userName = "root";
        String password = "root123";
        String url = "jdbc:MySQL://localhost/ronakDB";             
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, password);        
        }
        catch(Exception ex) {
            return null;
        }        
        return con;
    }        
}
