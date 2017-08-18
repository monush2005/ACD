package in.spicedigital.controller;

import in.spicedigital.history.History;
import in.spicedigitlal.impl.AgentLoginApiImpl;
import in.spicedigitlal.inf.AgentLoginApi;
import in.spicedigitlal.pojo.LoginInfoResponse;
import in.spicedigitlal.pojo.ResponseHistory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/user")
public class UserRequest
{
	static Logger logger =LogManager.getLogger(CrmApi.class.getName()); 	   
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/history")
	public String agentLogin(String jsonRequest)
	{
		logger.info("updateAgentStatus Request"+jsonRequest);
		String resp = "";
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			History objHistory = new History();
			ResponseHistory objREsHistory= objHistory.getHistory(jsonRequest);
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			resp = mapper.writeValueAsString(objREsHistory);
			System.out.println("=== " + resp);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				logger.info("updateAgentStatus Error"+jsonRequest +", error "+e.getMessage());
			}
			logger.info("updateAgentStatus Response "+resp);
			return resp;
			// return Response.status(200).entity(output).build();

	    }
	
    }

