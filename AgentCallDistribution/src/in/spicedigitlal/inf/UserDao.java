package in.spicedigitlal.inf;

import java.sql.SQLException;

import in.spicedigitlal.pojo.AgentAllocateRequestPojo;
import in.spicedigitlal.pojo.AgentAllocateResponsePojo;
import in.spicedigitlal.pojo.LoginInfoRequestPojo;
import in.spicedigitlal.pojo.LoginInfoResponse;
import in.spicedigitlal.pojo.UserModify;
public interface UserDao 
{
	public  int validationDao(String sessionId,String msisdn,String lang,String dept,String token,String valid,String username,String password,String customerName)throws SQLException;	
	public int manageAgentAdd(String agentid,String username,String password,String type) throws SQLException;
	public int manageAgentDelete(String agentid) throws SQLException;
	public UserModify manageAgentModify(String agentId)  throws SQLException;
	public int updateAgentStatusLogin(LoginInfoRequestPojo infoRequestPojo)  throws SQLException;
	public int updateAgentStatusLogout(LoginInfoRequestPojo infoRequestPojo)  throws SQLException;
	public AgentAllocateResponsePojo allocationIVR(AgentAllocateRequestPojo infoRequestPojo) throws SQLException;
	public AgentAllocateResponsePojo allocationCHAT(AgentAllocateRequestPojo infoRequestPojo) throws SQLException;
	public AgentAllocateResponsePojo deallocation(String reqId) throws SQLException;
	public int StatusUpdate(String agentId,int status,String channel,String reqType) throws SQLException;
	public String getUserMsisidn(String userId)  throws SQLException;
	
	public boolean insertAgentDeallocate(String ref_id,String channel,String agent_id) throws SQLException;
	public int updateAgentDeallocate(String ref_id) throws SQLException;

}
