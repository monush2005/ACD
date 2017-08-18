package in.spicedigital.controller;

import in.spicedigitlal.impl.AllocationDeallocationApiImpl;
import in.spicedigitlal.inf.AllocationDeallocationApi;
import in.spicedigitlal.pojo.AgentAllocateResponsePojo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/agent")
public class AllocationDeallocation
{
	static Logger logger =LogManager.getLogger(AllocationDeallocation.class.getName()); 
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/allocateDeallocate")
	public String allocateDeallocate(String jsonRequest) 
	{
		logger.info("allocateDeallocate  Request "+jsonRequest);
		String output = "";
		String resp = "";
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			AllocationDeallocationApi manageAgent = new AllocationDeallocationApiImpl();
			AgentAllocateResponsePojo infoResponse= manageAgent.agentAllocateDeallocate(jsonRequest);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			resp = mapper.writeValueAsString(infoResponse);
			System.out.println("");
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
		  logger.info("allocateDeallocate Error"+jsonRequest +", error "+e.getMessage());
		}
		logger.info("allocateDeallocate  Response "+resp);
		
		return resp;

	}

}
