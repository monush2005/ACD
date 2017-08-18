package in.spicedigitlal.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResponseHistory 
{
	private String msisdn,status,des,requestId;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	private Set<ChatHistory> history;
	
	public String getMsisdn() 
	{
		return msisdn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Set<ChatHistory> getHistory() {
		return history;
	}
	public void setHistory(Set<ChatHistory> history) {
		this.history = history;
	}
	
	
	
	
}
