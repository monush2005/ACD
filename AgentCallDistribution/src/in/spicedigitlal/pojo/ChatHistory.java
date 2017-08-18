package in.spicedigitlal.pojo;

import java.util.List;




public class ChatHistory
{
	private List<UserHistory> user;
	private List<AgentHistory> agent;
	public List<UserHistory> getUser() {
		return user;
	}
	public void setUser(List<UserHistory> user) {
		this.user = user;
	}
	public List<AgentHistory> getAgent() {
		return agent;
	}
	public void setAgent(List<AgentHistory> agent) {
		this.agent = agent;
	}



	

}
