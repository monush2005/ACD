package in.spicedigital.controller;


import in.spicedigitlal.impl.ValidationApiImpl;
import in.spicedigitlal.inf.ValidationApi;
import in.spicedigitlal.pojo.CustomerResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/customer")
public class ValidateSession
{
		static Logger logger =LogManager.getLogger(CrmApi.class.getName()); 
		@POST
		@Consumes({ MediaType.APPLICATION_JSON })
		@Produces({ MediaType.APPLICATION_JSON })
		@Path("/chat")
		public String getMsg(String jsonRequest) 
		{
			logger.info("customer chat  Request"+jsonRequest);
			System.out.println("json req is=="+jsonRequest);
			String resp = "";
			ObjectMapper mapper = new ObjectMapper();
			try 
			{
				ValidationApi objValidationApi=new ValidationApiImpl();
				CustomerResponse objCustomerResponse=objValidationApi.validation(jsonRequest);
				mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		        resp= mapper.writeValueAsString(objCustomerResponse);
				System.out.println("=== " + resp);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				logger.info("customer chat  Error"+jsonRequest +", error "+e.getMessage());
			}
			logger.info("customer chat  Response "+resp);
			return resp;
		}

}
