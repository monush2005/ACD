package in.spicedigital.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class Constant implements ServletContextListener 
{
	
	
	 
	public static Properties confifProperties;
	
	
	public void loadProperty(ServletContext objServletContext) throws IOException
	{
		confifProperties=new Properties();
		confifProperties.load(objServletContext.getResourceAsStream("/WEB-INF/Config.cfg"));
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	// TODO Auto-generated method stub
	
    }
	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		try
		{
			loadProperty(arg0.getServletContext());
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
   
}

