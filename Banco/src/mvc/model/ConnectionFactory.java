/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leonardo
 */
public class ConnectionFactory {
    private final String stringConexao = "jdbc:mysql://localhost/bancodeinvestimentos";
    private final String usuario = "root";
    private final String senha = "root";
    
    public Connection getConnection(){
        
        try{
            return DriverManager.getConnection(stringConexao,usuario, senha);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }
}
