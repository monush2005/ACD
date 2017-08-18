package in.spicedigital.dao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnection 
{
   public static Connection getConn()
   {
	   try
	   {
		   Context cn=new InitialContext();
		   DataSource ds=(DataSource)cn.lookup("java:/comp/env/jdbc/dbDataSource");
		   	return ds.getConnection();
	   }		
	   catch (Exception e) 
	   {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         return null;
	    }
	   /*Connection c = null;
	      try 
	      {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/testdb",
	            "postgres", "root");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      return c;*/
   }
}