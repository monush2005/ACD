package in.spicedigitlal.impl;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import in.spicedigitlal.inf.UserDao;
import in.spicedigital.dao.UserDaoImpl;
import in.spicedigital.utility.MapperUtil;
import in.spicedigital.utility.ObjectUtility;
import in.spicedigitlal.inf.AgentLoginApi;
import in.spicedigitlal.pojo.LoginInfoRequestPojo;
import in.spicedigitlal.pojo.LoginInfoResponse;

public class AgentLoginApiImpl implements AgentLoginApi {

	static Logger logger =LogManager.getLogger(AgentLoginApiImpl.class.getName()); 	 
	public LoginInfoResponse agentLoginInfo(String sstr)
	{
		logger.info("updateAgentStatus Request"+sstr);		
		LoginInfoResponse infoResponse = new LoginInfoResponse();
		LoginInfoRequestPojo infoRequestPojo = new LoginInfoRequestPojo();
		UserDao objUserDao=new UserDaoImpl();
		int status=-1;
		try 
		{
			System.out.println("agentlogininfo request"+sstr);
			infoRequestPojo = MapperUtil.readAsObjectOf(LoginInfoRequestPojo.class, sstr); 	
			if(infoRequestPojo == null || ObjectUtility.isEmptyString(infoRequestPojo.getRequestID()) || ObjectUtility.isEmptyString(infoRequestPojo.getAction()) 
					|| ObjectUtility.isEmptyString(infoRequestPojo.getAgentID()) || ObjectUtility.isEmptyString(infoRequestPojo.getMachineIP())
					|| ObjectUtility.isEmptyString(infoRequestPojo.getTerminalId()) || ObjectUtility.isEmptyString(infoRequestPojo.getType()))
			{
					infoResponse.setStatus("-1");
					infoResponse.setResponseID(infoRequestPojo.getRequestID());
					return infoResponse;
			}
				
			if (infoRequestPojo.getAction().equalsIgnoreCase("login"))
			{
				status=objUserDao.updateAgentStatusLogin(infoRequestPojo);
			}
			else
			if(infoRequestPojo.getAction().equalsIgnoreCase("logout"))
			{
				status=objUserDao.updateAgentStatusLogout(infoRequestPojo);
			}
			infoResponse.setStatus(Integer.toString(status));
			infoResponse.setResponseID(infoRequestPojo.getRequestID());

		} catch (Exception e) {
			infoResponse.setStatus("-1");
			infoResponse.setResponseID(infoRequestPojo.getRequestID());
			e.printStackTrace();
			logger.info("updateAgentStatus Error"+sstr +", error "+e.getMessage());
			return infoResponse;
		}
		return infoResponse;

	}
}
