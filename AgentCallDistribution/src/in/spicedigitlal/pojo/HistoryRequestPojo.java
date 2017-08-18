package in.spicedigitlal.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class HistoryRequestPojo
{
	@JsonProperty(required = true)
	private String requestId;
	@JsonProperty(required = true)
	private String msisdn;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}
