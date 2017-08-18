
package in.spicedigitlal.impl;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import in.spicedigitlal.inf.UserDao;
import in.spicedigital.dao.UserDaoImpl;
import in.spicedigital.utility.ObjectUtility;
import in.spicedigital.utility.MapperUtil;
import in.spicedigital.utility.UserCreate;
import in.spicedigital.utility.UserDelete;
import in.spicedigitlal.inf.ManageAgentApi;
import in.spicedigitlal.pojo.LoginInfoResponse;
import in.spicedigitlal.pojo.ManageRequestPojo;
import in.spicedigitlal.pojo.UserModify;


public class ManageAgentImpl implements ManageAgentApi 
{
	static Logger logger =LogManager.getLogger(ManageAgentImpl.class.getName());
	public LoginInfoResponse manageAgent(String str) 
	{
		logger.info("manageAgent  Request"+str);
		LoginInfoResponse infoResponse=new LoginInfoResponse();
		ManageRequestPojo infoRequestPojo=new ManageRequestPojo();
		UserModify objUserModify=new UserModify();
		UserCreate objUserCreate=new UserCreate();
		UserDelete obUserDelete=new UserDelete();
		UserDao objUserDao=new UserDaoImpl();
		try
		{
			int dbStatus=-1;
			int userStatus=-1;
			infoRequestPojo = MapperUtil.readAsObjectOf(ManageRequestPojo.class, str);
			if(infoRequestPojo==null|| ObjectUtility.isEmptyString(infoRequestPojo.getAgentID())||ObjectUtility.isEmptyString(infoRequestPojo.getUsername())
				|| ObjectUtility.isEmptyString(infoRequestPojo.getPassword()) || ObjectUtility.isEmptyString(infoRequestPojo.getRequestID()))
			{
				infoResponse.setResponseID(infoRequestPojo.getRequestID());
				infoResponse.setStatus("-1");
		        return infoResponse;
			}
			
			if(infoRequestPojo.getAction().equalsIgnoreCase("ADD")){
				dbStatus=objUserDao.manageAgentAdd(infoRequestPojo.getAgentID(),infoRequestPojo.getUsername(),infoRequestPojo.getPassword(),infoRequestPojo.getType());
				logger.info("inside add agent  DB status::"+dbStatus);
			}
			
			else
			if(infoRequestPojo.getAction().equalsIgnoreCase("MODIFY")){
				 objUserModify=objUserDao.manageAgentModify(infoRequestPojo.getAgentID());
				 logger.info("inside Modify agent  DB status::");
				if(ObjectUtility.isEmptyString(objUserModify.getAgentId()) || ObjectUtility.isEmptyString(objUserModify.getPassword()) ||
						ObjectUtility.isEmptyString(objUserModify.getUsername())){
					dbStatus=-1;
				}
				else{
					dbStatus=objUserDao.manageAgentDelete(infoRequestPojo.getAgentID());
					logger.info("inside Modify agent  DB status::"+dbStatus);
					System.out.println("in modify"+dbStatus);
					if(dbStatus ==0)
					dbStatus=objUserDao.manageAgentAdd(objUserModify.getAgentId(),infoRequestPojo.getUsername(),infoRequestPojo.getPassword(),infoRequestPojo.getType());
				}
			}
			
			else
			if(infoRequestPojo.getAction().equalsIgnoreCase("DELETE")){
				dbStatus=objUserDao.manageAgentDelete(infoRequestPojo.getAgentID());
			}
			
			if(dbStatus == 0 && infoRequestPojo.getType().equalsIgnoreCase("CHAT")){
				if(infoRequestPojo.getAction().equalsIgnoreCase("MODIFY")){
						userStatus=obUserDelete.userDelete(objUserModify.getUsername(),objUserModify.getPassword());
						userStatus=objUserCreate.UserCreat(infoRequestPojo.getUsername(),infoRequestPojo.getPassword());
					}
					else if (infoRequestPojo.getAction().equalsIgnoreCase("DELETE")){
						userStatus=obUserDelete.userDelete(infoRequestPojo.getUsername(),infoRequestPojo.getPassword());
					}
				else if(infoRequestPojo.getAction().equalsIgnoreCase("ADD")){
					userStatus=objUserCreate.UserCreat(infoRequestPojo.getUsername(),infoRequestPojo.getPassword());
					logger.info("Create agent on open firre  DB status::"+userStatus);
				}
				
				if(userStatus == -1){
					dbStatus=objUserDao.manageAgentDelete(infoRequestPojo.getAgentID());
				}
			}
		    else
		    if(dbStatus == 0){
		    	userStatus=0; ///IVR Agent not created in openFire
		     }
			infoResponse.setStatus(userStatus+"");
			infoResponse.setResponseID(infoRequestPojo.getRequestID());
	  }
	   catch(Exception e){
		infoResponse.setStatus("-1");
		infoResponse.setResponseID(infoRequestPojo.getRequestID());
		e.printStackTrace();
		logger.info("manageAgent Error"+str +", error "+e.getMessage());
		return infoResponse;
	 }
	return infoResponse;
  }
}

