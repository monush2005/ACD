package in.spicedigital.history;


import in.spicedigitlal.inf.UserDao;
import in.spicedigital.dao.UserDaoImpl;
import in.spicedigital.config.Constant;
import in.spicedigital.utility.MapperUtil;
import in.spicedigital.utility.ObjectUtility;
import in.spicedigitlal.pojo.AgentAllocateRequestPojo;
import in.spicedigitlal.pojo.AgentHistory;
import in.spicedigitlal.pojo.ChatHistory;
import in.spicedigitlal.pojo.HistoryRequestPojo;
import in.spicedigitlal.pojo.ResponseHistory;
import in.spicedigitlal.pojo.UserHistory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.net.URLDecoder;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class History 
{
	UserDao objUserDao=new UserDaoImpl();
	public ResponseHistory getHistory(String historyRequest) 
	{
		ResponseHistory objResponseHistory=new ResponseHistory();
		objResponseHistory.setRequestId(null);
		objResponseHistory.setStatus("-1");
		objResponseHistory.setDes("Invalid request parameter");
		objResponseHistory.setMsisdn(null);
		try
		{
			HistoryRequestPojo objRequestPojo = MapperUtil.readAsObjectOf(
			HistoryRequestPojo.class, historyRequest);
			String msisdn=objUserDao.getUserMsisidn(objRequestPojo.getMsisdn().trim());
			if(msisdn==null)
			{
				objResponseHistory.setDes("User has no history");
				objResponseHistory.setRequestId(objRequestPojo.getRequestId());
				return objResponseHistory;
			}
			final DateFormat dateFormat = new SimpleDateFormat("d-M-yyy");
			String filePath=Constant.confifProperties.getProperty("RESPONSE_LOG_PATH")+dateFormat.format(new Date())+"/"+msisdn.trim()+".log";
			return parseResponseLog(filePath,msisdn,objRequestPojo.getRequestId().trim());
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			return objResponseHistory;
		}
		
	}
	private  ResponseHistory parseResponseLog(String filename,String msisdn,String requestId)
	{
		 ResponseHistory objResponseHistory=new ResponseHistory();
		 ChatHistory objChatHistory=new ChatHistory();
		 
			String json = "";
			
			try 
	  	    {		   
		  		Set<ChatHistory> list=new LinkedHashSet<ChatHistory>();
				File file = new File(filename);
				System.out.println("name:"+filename);
				if(!file.exists())
				{
					objResponseHistory.setRequestId(requestId);
					objResponseHistory.setStatus("-1");
					objResponseHistory.setDes("User has no history");
					objResponseHistory.setMsisdn(msisdn);
				    return objResponseHistory;
				}
				List<String> lines = FileUtils.readLines(file);
				int count=0;
				List<UserHistory> userHistoryList=new LinkedList<UserHistory>();
				List<AgentHistory> agentHistoryList=new LinkedList<AgentHistory>();
				boolean isUser=true;
				objResponseHistory.setMsisdn(msisdn);
				for (String line : lines) 
				{
					//System.out.println(line);
					String abc[]=line.split(",");
					if(abc.length>5)
					{
						
					    if(abc[1].indexOf("user")!=-1)
						{
						   if(count<0)
						   {
							   objChatHistory=new ChatHistory();
							   userHistoryList=new LinkedList<UserHistory>();
							   count=0;
						   }
							isUser=true;
							
							String msg=abc[3].split("=")[1];
							msg=URLDecoder.decode(msg,"UTF-8");
							//System.out.println(abc[0]);
							String time=abc[0].trim().split(" ")[0];
							UserHistory objUserHistory=new UserHistory();
		if((msg.trim().equalsIgnoreCase("initconversation")) || (msg.trim().equalsIgnoreCase("userdisconnected"))){

}
else
{						
	objUserHistory.setMsg(msg);
							objUserHistory.setTime(time);
							userHistoryList.add(objUserHistory);
							count++;
}
						}
						else if(abc[1].indexOf("agent")!=-1)
						{
							
							isUser=false;
							if(count>0)
							{
								 agentHistoryList=new LinkedList<AgentHistory>();
								count=0;
							}
							String msg=abc[3].split("=")[1];
							msg=URLDecoder.decode(msg,"UTF-8");
							String time=abc[0].trim().split(" ")[0];
							String agentId=abc[4].split("=")[1];
							
							AgentHistory objAgentHistory=new AgentHistory();
if((msg.trim().equals("initconversation")) || (msg.trim().equals("userdisconnected"))){
objAgentHistory.setMsg(msg);
                                                        objAgentHistory.setTime(time);
                                                        objAgentHistory.setAgentId(agentId);
		
}

else{	
objAgentHistory.setMsg(msg);
							objAgentHistory.setTime(time);
							objAgentHistory.setAgentId(agentId);
							agentHistoryList.add(objAgentHistory);
}							
							count--;
						}
						if(isUser)
						{
							objChatHistory.setUser(userHistoryList);
							list.add(objChatHistory);
						}
						else
						{
							 objChatHistory.setAgent(agentHistoryList);
							 list.add(objChatHistory);
						}
					
					 
					}	
				}
				objResponseHistory.setRequestId(requestId);
				objResponseHistory.setStatus("0");
				objResponseHistory.setDes("S");
				objResponseHistory.setHistory(list);
				
				return objResponseHistory;
				
				
				/*mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				json = mapper.writeValueAsString(objResponseHistory);
				System.out.println("jsnon array is=="+json);
				return json;*/
				/*FileRequestPojo objFileRequestPojo=new FileRequestPojo();
				objFileRequestPojo.setMsisdn("9023150734");
				objFileRequestPojo.setRequestId("1234");
				mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				json = mapper.writeValueAsString(objFileRequestPojo);
				System.out.println("jsnon array is=="+json);*/
				
		} 
	  
		catch (IOException e) 
		{
			e.printStackTrace();
			objResponseHistory.setRequestId(requestId);
			objResponseHistory.setStatus("-1");
			objResponseHistory.setDes("F");
			objResponseHistory.setMsisdn(msisdn);
						return objResponseHistory;
			
			
		}
		
	}
	
}

