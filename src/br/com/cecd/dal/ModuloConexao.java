/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cecd.dal;
import java.sql.*;

/**
 *
 * @author Tiago
 */
public class ModuloConexao {
    //metodo para a conexão com o bd
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //chamar o drive
        String driver="com.mysql.cj.jdbc.Driver";
       
        // armazenando infos referentes ao banco
        
      
       String url="banco de dados";
       String user ="";
       String password ="";
        
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            //apoio de erro
              System.out.println(e);
            return null;
        }
        
        
    };
    
    
    
}
