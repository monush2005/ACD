package in.spicedigital.controller;


import in.spicedigitlal.impl.AgentLoginApiImpl;
import in.spicedigitlal.impl.ManageAgentImpl;
import in.spicedigitlal.inf.AgentLoginApi;
import in.spicedigitlal.inf.ManageAgentApi;
import in.spicedigitlal.pojo.LoginInfoResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/crm")
public class CrmApi
{
	static Logger logger =LogManager.getLogger(CrmApi.class.getName()); 	   
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/updateAgentStatus")
	public String agentLogin(String jsonRequest) {
		logger.info("updateAgentStatus Request"+jsonRequest);
		String resp = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			AgentLoginApi agentLoginInfo = new AgentLoginApiImpl();
			LoginInfoResponse infoResponse= agentLoginInfo.agentLoginInfo(jsonRequest);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			resp = mapper.writeValueAsString(infoResponse);
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
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/manageAgent")
	public String manageAgent(String jsonRequest) 
	{
		System.out.println("indide manage agent");
		String resp = "";
		logger.info("manageAgent Request "+jsonRequest);
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			ManageAgentApi manageAgent = new ManageAgentImpl();
			LoginInfoResponse infoResponse= manageAgent.manageAgent(jsonRequest);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	        resp= mapper.writeValueAsString(infoResponse);
	        System.out.println("====="+resp);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.info("manageAgent Error"+jsonRequest +", error "+e.getMessage());
		}
		logger.info("manageAgent Response "+resp);
		return resp;
	}
}
