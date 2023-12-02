/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trafficsoap;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onsjb
 */
public class Connection {
static String user = "root";
    static String pass = "";
    static String url = "jdbc:mysql://localhost:3306/traffic";
    static String driver = "com.mysql.jdbc.Driver";
 
    public static java.sql.Connection getCon() {
        java.sql.Connection con = null;
 
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Ok");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;  
    }
}
