package com.nadeeshani.jaxrs.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.util.logging.Logger;
/**
 * Hello world!
 *
 */
public class App 
{
    static Logger log = null;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        try{  
            
            Class.forName("com.mysql.jdbc.Driver");
           // Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AppCloudDB","root","root");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tier","root","root");
                
               
           
//            Class.forName("com.mysql.jdbc.Driver");  
//              
//            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Tier","root","root"); 
           
           // System.out.println("Connected to database>>>>>>>>>>>>>>>>");
          //  log.log(null, "Connected to database>>>>>>>>>>>>>>>>");
            //return con;
              
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }  
    }
}
