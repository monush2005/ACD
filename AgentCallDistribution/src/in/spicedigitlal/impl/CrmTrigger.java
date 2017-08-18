package in.spicedigitlal.impl;


import in.spicedigital.listener.PropertiesParameterConfig;
import in.spicedigital.utility.HitUrl;
import in.spicedigitlal.pojo.AlocationDellocation;
import in.spicedigitlal.pojo.ResponsePojo;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class CrmTrigger 
{
public static void allocation(String requestId,String action,String msisdn,String cceid,String category,String deptid,String lang,String channel)
{
	ObjectMapper mapper = new ObjectMapper();
	String json = "";
	HashMap map=new HashMap<>();
	try
	{
		PropertiesParameterConfig parameterConfig = PropertiesParameterConfig.getPropertiesParameterObject();
		if(map.isEmpty())
		{
			map= parameterConfig.getHashMap();
		}
		System.out.println("inside allocation");
		List<AlocationDellocation> list=new ArrayList<AlocationDellocation>();
		AlocationDellocation objAlocationDellocation=new AlocationDellocation();
		objAlocationDellocation.setRequestId(requestId);
		objAlocationDellocation.setAction(action);
		objAlocationDellocation.setMsisdn(msisdn);
		objAlocationDellocation.setCceid(cceid);
		objAlocationDellocation.setCategory(category);
		objAlocationDellocation.setDeptid(deptid);
		objAlocationDellocation.setLang(lang);
		objAlocationDellocation.setChannel(channel);
		list.add(objAlocationDellocation);
		ResponsePojo objResp =new ResponsePojo();
		objResp.setAllocateAgentList(list);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		json = mapper.writeValueAsString(objResp);
		System.out.println("jsnon array is=="+json);
		HitUrl.hitUrl(json,"CRM",(String)map.get("CrmUrl"),(String)map.get("AuthBrCrmUrl"));
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("exception in allocation=="+e);
	}	
}

}

