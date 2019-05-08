/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclassofajava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DbConnect {
     private static Connection connection=null;
    public static Connection DbConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/javapractical?zeroDateTimeBehavior=convertToNull","root","1234");
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
        
    }
}
