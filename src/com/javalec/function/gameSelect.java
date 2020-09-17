package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.setup.db_Share_Var;

public class gameSelect {

    private final String url_mysql = db_Share_Var.DBNAME;
    private final String id_mysql = db_Share_Var.DBUSER;
    private final String pw_mysql = db_Share_Var.DBPASS;
    
    public String store_menu_String(String store_name) {
    	String str = "<html>";
    	int count = 0;
    	
    	try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();
            
    		String WhereDefault = "SELECT md_name FROM gameMD where store_name = '" + store_name + "';";

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

            while(rs.next()){
            	str = str + " " + rs.getString(1);
            	count++;
            	if(count % 6 == 0)
            		str = str + "<br>";
            }
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    	
    	return str;
    }	
}
