package in.spicedigital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import in.spicedigitlal.pojo.UserModify;
import in.spicedigitlal.inf.UserDao;
import in.spicedigitlal.pojo.AgentAllocateRequestPojo;
import in.spicedigitlal.pojo.AgentAllocateResponsePojo;
import in.spicedigitlal.pojo.LoginInfoRequestPojo;


public class UserDaoImpl implements UserDao
{
	
	public int validationDao(String sessionId,String msisdn,String lang,String dept,String token,String valid,String username,String password,String customerName) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("insert into chat.customer(sessionid,msisdn,cat,dept,token,valid,username,password,customer_name)values(?,?,?,?,?,?,?,?,?)");
			pStatement.setString(1,sessionId);
			pStatement.setString(2, msisdn);
			pStatement.setString(3,lang);
			pStatement.setString(4,dept);
			pStatement.setString(5,token);
			pStatement.setString(6,valid);
			pStatement.setString(7,username);
			pStatement.setString(8,password);
			pStatement.setString(9,customerName);
			System.out.println("query is=="+pStatement);
			pStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
		return 0;
		
	}
		
	public int manageAgentAdd(String agentid,String username,String password,String type) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
        ResultSet rs=null;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("select count(*) as cnt from chat.agent1 where agent_id=?");
			pStatement.setString(1,agentid);
			rs = pStatement.executeQuery();
			if(rs.next())
			{
				if(rs.getInt("cnt") >= 1)
				{
					return -1;
				}
				else
				{
					pStatement= con.prepareStatement("insert into chat.agent1(agent_id,username,password,type)values(?,?,?,?)");
					pStatement.setString(1,agentid);
				        pStatement.setString(2,username);
					pStatement.setString(3,password);
					pStatement.setString(4,type.toUpperCase());
					System.out.println("Query is="+pStatement);
					checkDb=pStatement.executeUpdate();
					System.out.println("checkDb="+checkDb);
					if(checkDb==0)
					{
						return -1;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(rs!=null)
			rs.close();
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
			
		}
		return 0;
 	}
	
	public int manageAgentDelete(String agentid) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("delete from chat.agent1 where agent_id=?");
			pStatement.setString(1,agentid);
			pStatement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
		return 0;
	}
		
	public UserModify manageAgentModify(String agentId)  throws SQLException
	{
		UserModify objUserModify=new UserModify();
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("select username,password,agent_id from chat.agent1 where agent_id=?");
			pStatement.setString(1,agentId);
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				objUserModify.setUsername(rs.getString("username"));
				objUserModify.setPassword(rs.getString("password"));
				objUserModify.setAgentId(rs.getString("agent_id"));
			}
		System.out.println("in dao layer"+objUserModify.getAgentId()+","+objUserModify.getPassword()+","+objUserModify.getUsername());	
		}
		catch(Exception e)
		{
			return objUserModify;
		}
		finally
		{
			if(con!=null)
				con.close();
			if(pStatement!=null)
				pStatement.close();
			if(rs!=null)
				rs.close();
		}
		return objUserModify;
	}
	
	public int updateAgentStatusLogin(LoginInfoRequestPojo infoRequestPojo) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			java.sql.Date date = getCurrentDatetime();
		pStatement = con.prepareStatement("update chat.agent1 set action=?,cat=?,lang=?,machineIP=?,terminalID=?,status=?,counter=?,date_time=?,connectionid=? where agent_id=? and (status=-1 OR status is null OR status=1)");
			pStatement.setString(1,"LOGIN");
			pStatement.setString(2,infoRequestPojo.getCategory());
			pStatement.setString(3,infoRequestPojo.getLanguage());
			pStatement.setString(4,infoRequestPojo.getMachineIP());
			pStatement.setString(5,infoRequestPojo.getTerminalId());
			//pStatement.setString(6,infoRequestPojo.getType().toUpperCase());
			pStatement.setInt(6, 1);
			pStatement.setInt(7, 5);
			pStatement.setDate(8,date);
			pStatement.setString(9,infoRequestPojo.getConnectionId());
			pStatement.setString(10,infoRequestPojo.getAgentID());
			System.out.println("inside update agent login status=="+pStatement);
			checkDb=pStatement.executeUpdate();
			System.out.println("value of checkDB is="+checkDb);
			if(checkDb == 0)
			{
			   return -1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			 if(con!=null)
			con.close();
			 if(pStatement!=null)
			pStatement.close();
		}
		return 0;
		
	}
	
	public java.sql.Date getCurrentDatetime() 
	{
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
	public int updateAgentStatusLogout(LoginInfoRequestPojo infoRequestPojo)  throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("update chat.agent1 set action=?,status=?,counter=5 where agent_id=? AND status >= 1");
			//System.out.println("query is ="+pStatement);
			pStatement.setString(1,"LOGOUT");
			pStatement.setInt(2, -1);
			pStatement.setString(3,infoRequestPojo.getAgentID());
			System.out.println("query is ="+pStatement);
			checkDb=pStatement.executeUpdate();
			System.out.println("checkDb value ="+checkDb);
			if(checkDb == 0)
			  {
				return -1;
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
		return 0;
	}
	
	
	public AgentAllocateResponsePojo allocationIVR(AgentAllocateRequestPojo infoRequestPojo) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		String agentID="",machineIP="",terminalIP="";
		AgentAllocateResponsePojo infoResponse = new AgentAllocateResponsePojo();
		int result=-1;
		try
		{
		con=DbConnection.getConn();
			pStatement = con.prepareStatement("select agent_id,channel from chat.tbl_deallocate where ref_id=? limit 1");
			pStatement.setString(1,infoRequestPojo.getRequestID());
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				result=-1;
			}
			else{
		pStatement = con.prepareStatement("select agent_id,cat,lang,machineip,terminalid,type,counter from chat.agent1 where lang Like ? and cat=? and status=1 and type='IVR' order by id limit 1");
		pStatement.setString(1,"%"+infoRequestPojo.getLanguage()+"%");
		pStatement.setString(2,infoRequestPojo.getCategory());
		rs=pStatement.executeQuery();
		if(rs.next())
		{
			System.out.println("agent if in IVR="+rs.getString("agent_id"));
			agentID=rs.getString("agent_id");
			machineIP=rs.getString("machineip");
			terminalIP=rs.getString("terminalid");
			result=StatusUpdate(agentID,2,rs.getString("type"),"ALLOCATE");
		}
		else
		{
			pStatement=con.prepareStatement("select agent_id,cat,lang,machineip,terminalid,type,counter from chat.agent1 where lang Like ? and status=1 and type='IVR' order by id limit 1");
			pStatement.setString(1,"%"+infoRequestPojo.getLanguage()+"%");
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				System.out.println("agent if in IVR="+rs.getString("agent_id"));
				agentID=rs.getString("agent_id");
				machineIP=rs.getString("machineip");
				terminalIP=rs.getString("terminalid");
				result=StatusUpdate(agentID,2,rs.getString("type"),"ALLOCATE");
			}
			else
			{
				pStatement=con.prepareStatement("select agent_id,cat,lang,machineip,terminalid,type,counter from chat.agent1 where status=1 and type='IVR' order by id limit 1");
				rs=pStatement.executeQuery();
				if(rs.next())
				{
					System.out.println("agent if in IVR="+rs.getString("agent_id"));
					agentID=rs.getString("agent_id");
					machineIP=rs.getString("machineip");
					terminalIP=rs.getString("terminalid");
					result=StatusUpdate(agentID,2,rs.getString("type"),"ALLOCATE");
				}
				else
				{
					infoResponse.setStatus("2");
					infoResponse.setRequestID(infoRequestPojo.getRequestID());
					System.out.println("AL BUSY SORRY");
					return infoResponse;
				}	
			}	
		}
	}
		if(result == 0)
		{
			infoResponse.setStatus("0");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
			infoResponse.setAgentID(agentID);
			infoResponse.setMachineID(machineIP);
			infoResponse.setTerminalID(terminalIP);
		}
		else
		{
			infoResponse.setStatus("-1");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			infoResponse.setStatus("-1");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
			return infoResponse;
		}
		finally
		{
			if(con!=null)
			con.close();
			 if(rs!=null)
			rs.close();
			 if(pStatement!=null)
			pStatement.close();
		}
		return infoResponse;
	}

	
	
	public AgentAllocateResponsePojo allocationCHAT(AgentAllocateRequestPojo infoRequestPojo) throws SQLException
	{
		
		String agentID="",machineIP="",terminalIP="";
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		int result=-1,counter=0;
		AgentAllocateResponsePojo infoResponse = new AgentAllocateResponsePojo();
		try
		{
		con=DbConnection.getConn();
			pStatement = con.prepareStatement("select agent_id,channel from chat.tbl_deallocate where ref_id=? limit 1");
			pStatement.setString(1,infoRequestPojo.getRequestID());
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				result=-1;
			}
			else{	
		pStatement = con.prepareStatement("select agent_id,cat,lang,machineip,connectionid,type,counter from chat.agent1 where lang Like ? and cat=? and status=1  and connectionid !='' and type='CHAT' and counter BETWEEN 1 AND 5 order by id limit 1");
		pStatement.setString(1,"%"+infoRequestPojo.getLanguage()+"%");
		pStatement.setString(2,infoRequestPojo.getCategory());
		rs=pStatement.executeQuery();
		if(rs.next())
		{
			System.out.println("agent if in CHAT="+rs.getString("agent_id"));
			agentID=rs.getString("agent_id");
			machineIP=rs.getString("machineip");
			terminalIP=rs.getString("connectionid");
			counter=rs.getInt("counter");
			result=StatusUpdate(agentID,counter,"CHAT","ALLOCATE");
		}
		else
		{
			//rs.close();
			pStatement=con.prepareStatement("select agent_id,cat,lang,machineip,connectionid,type,counter from chat.agent1 where lang Like ? and status=1  and connectionid !='' and type='CHAT' and counter BETWEEN 1 AND 5 order by id limit 1");
			pStatement.setString(1,"%"+infoRequestPojo.getLanguage()+"%");
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				System.out.println("agent if in chat="+rs.getString("agent_id"));
				agentID=rs.getString("agent_id");
				machineIP=rs.getString("machineip");
				terminalIP=rs.getString("connectionid");
				counter=rs.getInt("counter");
				result=StatusUpdate(agentID,counter,"CHAT","ALLOCATE");
			}
			else
			{
				pStatement=con.prepareStatement("select agent_id,cat,lang,machineip,connectionid,type,counter from chat.agent1 where status=1 and connectionid !='' and type='CHAT' and counter BETWEEN 1 AND 5 order by id limit 1");
				rs=pStatement.executeQuery();
				if(rs.next())
				{
					System.out.println("agent if in chat="+rs.getString("agent_id"));
					agentID=rs.getString("agent_id");
					machineIP=rs.getString("machineip");
					terminalIP=rs.getString("connectionid");
					counter=rs.getInt("counter");
					result=StatusUpdate(agentID,counter,"CHAT","ALLOCATE");
				}
				else
				{
					infoResponse.setStatus("2");
					infoResponse.setRequestID(infoRequestPojo.getRequestID());
					System.out.println("AL BUSY SORRY");
					return infoResponse;
				}	
			}
			
		}
	}
		if(result == 0)
		{
			infoResponse.setStatus("0");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
			infoResponse.setAgentID(agentID);
			infoResponse.setMachineID(machineIP);
			infoResponse.setTerminalID(terminalIP);
			infoResponse.setCounter(counter +"");
		}
		else
		{
			System.out.println("NO AGENTS SORRY");
			infoResponse.setStatus("-1");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
		}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			infoResponse.setStatus("-1");
			infoResponse.setRequestID(infoRequestPojo.getRequestID());
			return infoResponse;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
			if(rs!=null)
			rs.close();
		}
		
		return infoResponse;
	
	}
	
	public int StatusUpdate(String agentId,int status,String channel,String reqType) throws SQLException
	{
		
		System.out.println("indi status update values=="+agentId+","+status+","+channel+","+reqType);
		Connection con=null;
		PreparedStatement pStatement=null;
		int counter=0;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			if(reqType.equalsIgnoreCase("ALLOCATE"))
			{
				if(channel.equalsIgnoreCase("IVR"))
				{
					System.out.println("indi status update values=="+agentId+","+status+","+channel+","+reqType);
					pStatement = con.prepareStatement("update chat.agent1 set status=? where agent_id=? AND status=1");
					pStatement.setInt(1,status);
					 pStatement.setString(2,agentId);
					 System.out.println("Quesry is==="+pStatement);
				}
				else if(channel.equalsIgnoreCase("CHAT"))
				{
					counter=status-1;
					pStatement = con.prepareStatement("update chat.agent1 set counter=? where agent_id=?");
					pStatement.setInt(1,counter);
					 pStatement.setString(2,agentId);
				
				}
			}
			else
			if(reqType.equalsIgnoreCase("DEALLOCATE"))
			{
				if(channel.equalsIgnoreCase("IVR"))
				{
					 System.out.println("deallocaion ivr update thread");
					pStatement = con.prepareStatement("update chat.agent1 set status=? where agent_id=? AND status=2");
					pStatement.setInt(1,1);
					 pStatement.setString(2,agentId);		
				}
			
				else
				if(channel.equalsIgnoreCase("CHAT"))
				{
					System.out.println("deallocaion chat update thread");
					counter=status+1;
					pStatement = con.prepareStatement("update chat.agent1 set counter=? where agent_id=?");
					pStatement.setInt(1,counter);
					pStatement.setString(2,agentId);
					System.out.println("deallocaion chat update thread=="+pStatement);
				}
			}
			checkDb=pStatement.executeUpdate();
			if(checkDb == 0)
			  {	
				return -1;
			  }
		}		
		
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
		return 0;
	}
	
	public AgentAllocateResponsePojo deallocation(String reqId) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		int result=-1;
		String agent_id="",channel="";
		AgentAllocateResponsePojo infoResponse = new AgentAllocateResponsePojo();
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("select agent_id,channel from chat.tbl_deallocate where ref_id=? limit 1");
			pStatement.setString(1,reqId);
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				agent_id=rs.getString("agent_id");
				channel=rs.getString("channel");
			}
			
			if(channel.equalsIgnoreCase("ivr") && (agent_id != null && !agent_id.isEmpty()) && (channel != null && !channel.isEmpty()))
			{
				 System.out.println("deallocaion ivr");
				result=StatusUpdate(agent_id,0,"IVR","DEALLOCATE");
				
			}
			else if(channel.equalsIgnoreCase("chat") && (agent_id != null && !agent_id.isEmpty()) && (channel != null && !channel.isEmpty()))
			{
				System.out.println("deallocaion chat");
				pStatement = con.prepareStatement("select counter from chat.agent1 where agent_id=?");
				pStatement.setString(1,agent_id);
				System.out.println("deallocaion chati=="+pStatement);
				rs=pStatement.executeQuery();
				if(rs.next())
				{
					System.out.println("inside result set deallocaion chat"+rs.getInt("counter"));
					int counter=rs.getInt("counter");
					if(counter<5)
					{
					 result=StatusUpdate(agent_id,counter,"CHAT","DEALLOCATE");
					}
				}
				
			}
			
			if(result == 0){
				infoResponse.setStatus("0");
				infoResponse.setRequestID(reqId);
				deleteAgentDeallocate(reqId);

			}
			else 
				infoResponse.setStatus("-1");
				infoResponse.setRequestID(reqId);
		}
		catch(Exception e)
		{
			infoResponse.setStatus("-1");
			infoResponse.setRequestID(reqId);
			return infoResponse;
		}
		finally
		{
			if(con!=null)
			con.close();
			 if(rs!=null)
			rs.close();
 			 if(pStatement!=null)	
			pStatement.close();
		}
		return infoResponse;
	}
	@Override
	public String getUserMsisidn(String userId) throws SQLException 
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		String msisdn=null;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("select msisdn from chat.customer where sessionid=? limit 1");
			pStatement.setString(1,userId);
			rs=pStatement.executeQuery();
			if(rs.next())
			{
				msisdn=rs.getString("msisdn");
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{	
				con.close();
			}	
			if(pStatement!=null)
				pStatement.close();
			if(rs!=null)
				rs.close();
			
		}
		return msisdn;
	}
	
	
	public boolean insertAgentDeallocate(String ref_id,String channel,String agent_id) throws SQLException{
		Connection con=null;
		PreparedStatement pStatement=null;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			pStatement= con.prepareStatement("insert into chat.tbl_deallocate(ref_id,agent_id,channel,time)values(?,?,?,?)");
			pStatement.setString(1,ref_id);
		    pStatement.setString(2,agent_id);
			pStatement.setString(3,channel.toUpperCase());
			Date date=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE,+60);
			pStatement.setTimestamp(4, new Timestamp(calendar.getTime().getTime()));
			checkDb=pStatement.executeUpdate();
			if(checkDb==0)
			{
				return false;
			}
			
		}
		catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
		finally{
			if(con!=null){	
				con.close();
			}	
			if(pStatement!=null){
				pStatement.close();
			}
	}
		return true;
}
	public int deleteAgentDeallocate(String ref_id) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("delete from chat.tbl_deallocate where ref_id=? and status=1");
			pStatement.setString(1,ref_id);
			pStatement.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
		return 0;
	}

	public int updateAgentDeallocate(String ref_id) throws SQLException
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		int checkDb=-1;
		try
		{
			con=DbConnection.getConn();
			pStatement = con.prepareStatement("update chat.tbl_deallocate set status=? where ref_id=?");
			pStatement.setInt(1,1);
			pStatement.setString(2,ref_id);
			checkDb=pStatement.executeUpdate();
			if(checkDb ==0)
				return -1;
			else
				return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(con!=null)
			con.close();
			if(pStatement!=null)
			pStatement.close();
		}
	}
		
	
}
