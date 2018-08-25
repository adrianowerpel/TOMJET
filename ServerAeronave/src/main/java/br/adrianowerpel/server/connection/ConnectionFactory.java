/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.adrianowerpel.server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author patricia
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws ClassNotFoundException,SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbViagensAereas","root","123456");
    }
    
}
