package in.spicedigitlal.pojo;

public class CustomerRequestPojo {

	public CustomerRequestPojo() 
	{
		// T,lonODO Auto-generated constructor stub
	}
	
	private String imsi,st,did,imei,hmk,hmd,os,pkg,ver,tkn,rot,mod,mcc,mnc,lac,clid,acc,lat,lon,peml,lang,mno,aadhr,requestId;

	public String getImsi() 
	{
		return imsi;
	}

	public String getRequestId() 
	{
		return requestId;
	}

	public String getAadhr() {
		return aadhr;
	}

	public void setAadhr(String aadhr) {
		this.aadhr = aadhr;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getHmk() {
		return hmk;
	}

	public void setHmk(String hmk) {
		this.hmk = hmk;
	}

	public String getHmd() {
		return hmd;
	}

	public void setHmd(String hmd) {
		this.hmd = hmd;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getTkn() {
		return tkn;
	}

	public void setTkn(String tkn) {
		this.tkn = tkn;
	}

	public String getRot() {
		return rot;
	}

	public void setRot(String rot) {
		this.rot = rot;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getClid() {
		return clid;
	}

	public void setClid(String clid) {
		this.clid = clid;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getPeml() {
		return peml;
	}

	public void setPeml(String peml) {
		this.peml = peml;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}
	
	
}
	/*Request
	{
	    "imsi": "",
	    "st": "",
	    "did": "1234",   (Required in case of app)
	    "imei": "",
	    "hmk": "samunsg",
	    "hmd": "s6",
	    "os": "",
	   "pkg": "in.spicedigital.umang",
	    "ver": "",
	    "tkn": "63783433-3942-4bed-b029-3a8da67d7c4e", (Mandatory)
	    "rot": "no",
	    "mod": "app", (Mandatory) app or web
	    "mcc": "",
	    "mnc": "",
	    "lac": "",
	    "clid": "",
	    "acc": "",
	    "lat": "",
	    "lon": "",
	    "peml": "",
	    "lang": "en",
	    "mno": ""
	}
	 
	 
	 
	 
	 
	Response
	 
	{
	  "rs": "F",
	  "rc": "PE004",
	  "rd": "Sorry, looks like the request is not processed. Please try again.",
	  "pd": null
	}*/
	

