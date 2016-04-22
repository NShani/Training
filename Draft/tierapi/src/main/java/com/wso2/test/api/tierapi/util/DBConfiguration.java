package com.wso2.test.api.tierapi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger; 

public class DBConfiguration {
    Logger log;
    public static void main( String[] args ){
        
        DBConfiguration db=new DBConfiguration();
        db.getConnection();
    }
    
    public Connection getConnection(){
        
        try{  
        Class.forName("com.mysql.jdbc.Driver");  
          
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Tier","root","root"); 
      
        return con;
          
        }catch(Exception e){
            System.out.println(e);
        }  
        return null;
    }
}


