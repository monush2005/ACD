package in.spicedigitlal.impl;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import in.spicedigitlal.inf.UserDao;
import in.spicedigital.dao.UserDaoImpl;
import in.spicedigital.utility.ErrorClass;
import in.spicedigital.utility.MapperUtil;
import in.spicedigital.utility.ObjectUtility;
import in.spicedigitlal.inf.AllocationDeallocationApi;
import in.spicedigitlal.pojo.AgentAllocateRequestPojo;
import in.spicedigitlal.pojo.AgentAllocateResponsePojo;




public class AllocationDeallocationApiImpl implements AllocationDeallocationApi
{
	static Logger logger = LogManager
			.getLogger(AllocationDeallocationApiImpl.class.getName());
	
	public AgentAllocateResponsePojo agentAllocateDeallocate(String str)
	{
		logger.info("allocateDeallocate  Request " + str);
		System.out.println("allocateDeallocate  Request:::"+str);
		AgentAllocateResponsePojo infoResponse =new AgentAllocateResponsePojo();
		AgentAllocateRequestPojo infoRequestPojo = new AgentAllocateRequestPojo();
		UserDao objUserDao=new UserDaoImpl();
		boolean dbResponse=false;
		try 
		{
			infoRequestPojo = MapperUtil.readAsObjectOf(
			AgentAllocateRequestPojo.class, str);
			System.out.println("----" + infoRequestPojo.getAction());
		
			if(infoRequestPojo.getAction().equalsIgnoreCase("ALLOCATE"))
			{
				if ( infoRequestPojo == null ||ObjectUtility.isEmptyString(infoRequestPojo.getAction()) || ObjectUtility.isEmptyString(infoRequestPojo.getRequestID())  || ObjectUtility.isEmptyString(infoRequestPojo.getChannel()) )
				{
					infoResponse.setStatus(ErrorClass.allocationInvalidRequest);
					infoResponse.setRequestID(infoRequestPojo.getRequestID());
					return infoResponse;
				}
				
				if(infoRequestPojo.getChannel().equalsIgnoreCase("IVR"))
				{
					infoResponse=objUserDao.allocationIVR(infoRequestPojo);
				}
				
				else
				if(infoRequestPojo.getChannel().equalsIgnoreCase("CHAT"))
				{
					infoResponse=objUserDao.allocationCHAT(infoRequestPojo);
				}
				System.out.println("agent ID="+infoResponse.getStatus()+","+infoResponse.getRequestID());
				if(infoResponse.getAgentID() != null && !infoResponse.getAgentID().isEmpty()){
					logger.info("going in insert database agent info");
					dbResponse=objUserDao.insertAgentDeallocate(infoRequestPojo.getRequestID(),infoRequestPojo.getChannel(),infoResponse.getAgentID());
					
					if(dbResponse){
						logger.info("send trigger to crm for the agent allocate ");
						CrmTrigger.allocation(infoRequestPojo.getRequestID(), "ALLOCATE", infoRequestPojo.getMsisdn(),infoResponse.getAgentID()
								, infoRequestPojo.getCategory(), "", infoRequestPojo.getLanguage(), infoRequestPojo.getChannel());
					}
					else{
						logger.info("exception in msg");
						objUserDao.deallocation(infoRequestPojo.getRequestID());
						infoResponse.setStatus("-1");
						return infoResponse;
	}
				}
				
					
			}
			if(infoRequestPojo.getAction().equalsIgnoreCase("DEALLOCATE") && (infoRequestPojo.getChannel().equalsIgnoreCase("IVR") || infoRequestPojo.getChannel().equalsIgnoreCase("CHAT"))){
				logger.info("agent deallocation from chat and ivr chaanel");
				int result=objUserDao.updateAgentDeallocate(infoRequestPojo.getRequestID());
				if(result==0){
				infoResponse.setStatus("0");
				infoResponse.setRequestID(infoRequestPojo.getRequestID());
				CrmTrigger.allocation(infoRequestPojo.getRequestID(), "DEALLOCATE", infoRequestPojo.getMsisdn(),infoRequestPojo.getAgentID()
				, infoRequestPojo.getCategory(), "", infoRequestPojo.getLanguage(), infoRequestPojo.getChannel());
				}else{
					infoResponse.setStatus("-1");
					infoResponse.setRequestID(infoRequestPojo.getRequestID());
				    }
				
			}	
			
			 if(infoRequestPojo.getAction().equalsIgnoreCase("DEALLOCATE") && (infoRequestPojo.getChannel().equalsIgnoreCase("CRM") || infoRequestPojo.getChannel().equalsIgnoreCase("CHAT")))
			 {
				 logger.info("deaccocate from the channel crm");
				infoResponse=objUserDao.deallocation(infoRequestPojo.getRequestID());
				logger.info("dbstatus after deaclote"+infoResponse.toString());
			 }
	
		} catch (Exception e) 
		{
			infoResponse.setStatus("-1");
			e.printStackTrace();
			return infoResponse;
		}
		return infoResponse;

	}
}
