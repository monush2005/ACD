package in.spicedigital.utility;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HitUrl 
{
	
	public static String hitUrl(String json,String type,String url,String Bearer)
	{  
		HttpClient httpClient = new DefaultHttpClient();
		String urlResponse="";
		try 
		{
		    System.out.println("urllllll:"+url);
		    System.out.println("in test case excution");
		    HttpPost request = new HttpPost(url);
		    StringEntity params =new StringEntity(json);
		    request.addHeader("content-type", "application/json");
		    request.addHeader("Accept","application/json");
		    if(type.equalsIgnoreCase("crm"))
		    {
		    	request.addHeader("Authorization",Bearer.trim());
		    }
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    StringBuilder sb = new StringBuilder();
		    InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(
			new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				sb.append(line);
			}
			urlResponse=sb.toString();
			System.out.println("response is =="+sb.toString());				
		}
		catch (Exception ex) 
		{    
			ex.printStackTrace();
		} 
		finally 
		{
		    httpClient.getConnectionManager().shutdown();
		}
		return urlResponse;
		
	}

}
