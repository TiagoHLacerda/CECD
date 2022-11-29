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
        
      
       String url="jdbc:mysql://cecd.c9ng1pnpwct5.us-east-2.rds.amazonaws.com:3306/BancoDadosCECD?characterEnconding=utf-8";
       String user ="admin";
       String password ="LiviaSofia";
        
        
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
