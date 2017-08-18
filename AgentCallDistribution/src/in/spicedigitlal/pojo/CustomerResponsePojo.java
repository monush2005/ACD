package in.spicedigitlal.pojo;

public class CustomerResponsePojo 
{
	private String rs,rc,rd,mno;

	CustomerResponsePojoPD pd=new CustomerResponsePojoPD();
	

	public CustomerResponsePojoPD getPd() {
		return pd;
	}

	public void setPd(CustomerResponsePojoPD pd) {
		this.pd = pd;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}


	
	
}

