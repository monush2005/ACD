package in.spicedigital.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MapperUtil {

	public MapperUtil() 
	{
		
	}
	static com.fasterxml.jackson.databind.ObjectMapper MAPPER = new ObjectMapper();
	public static <T> T readAsObjectOf(Class<T> clazz, String value)
	          throws Exception {
	 try 
	 {
	      return MAPPER.readValue(value, clazz);
	  }
	 catch (Exception e) 
	 {
	    	  e.printStackTrace();
	 }
	 
	 return MAPPER.readValue(value, clazz);
	}
}
