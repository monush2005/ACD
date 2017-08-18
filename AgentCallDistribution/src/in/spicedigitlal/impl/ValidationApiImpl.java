package in.spicedigitlal.impl;

import in.spicedigitlal.inf.UserDao;
import in.spicedigital.dao.UserDaoImpl;
import in.spicedigital.listener.PropertiesParameterConfig;
import in.spicedigital.utility.HitUrl;
import in.spicedigital.utility.MapperUtil;
import in.spicedigital.utility.ObjectUtility;
import in.spicedigital.utility.UserCreate;
import in.spicedigital.utility.UserDelete;
import in.spicedigitlal.inf.ValidationApi;
import in.spicedigitlal.pojo.CustomerRequestPojo;
import in.spicedigitlal.pojo.CustomerResponse;
import in.spicedigitlal.pojo.CustomerResponsePojo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.HashMap;
import in.spicedigitlal.pojo.ValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class ValidationApiImpl implements ValidationApi
{
	static Logger logger =LogManager.getLogger(ValidationApiImpl.class.getName());
	public CustomerResponse validation(String str)
	{
		HashMap map = new HashMap();
		String url="",response="",json="",usrname="",pwd="";
		int check=-1;
		CustomerResponse objCustomerResponse=new CustomerResponse();
		CustomerResponsePojo objCustomerResponsePojo=new CustomerResponsePojo();
		ValidationRequest objValidationRequest=new ValidationRequest();
		 CustomerRequestPojo objCustomerRequestPojo=new CustomerRequestPojo();
		UserDao objUserDao=new UserDaoImpl();
		UserCreate objUserCreate=new UserCreate();
		UserDelete objUserDelete=new UserDelete();
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			PropertiesParameterConfig parameterConfig = PropertiesParameterConfig.getPropertiesParameterObject();
			if(map.isEmpty())
			{
				map = parameterConfig.getHashMap();
			}
			objCustomerRequestPojo = MapperUtil.readAsObjectOf(CustomerRequestPojo.class, str);
		
			if(objCustomerRequestPojo==null|| ObjectUtility.isEmptyString(objCustomerRequestPojo.getTkn()))	
			{
				objCustomerResponse.setStatus("-1");
				 objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
				return objCustomerResponse;
			}
			else
			{
				objValidationRequest.setAadhr(objCustomerRequestPojo.getAadhr());
				objValidationRequest.setAcc(objCustomerRequestPojo.getAcc());
				objValidationRequest.setClid(objCustomerRequestPojo.getClid());
				objValidationRequest.setDid(objCustomerRequestPojo.getDid());
				objValidationRequest.setHmd(objCustomerRequestPojo.getHmd());
				objValidationRequest.setHmk(objCustomerRequestPojo.getHmk());
				objValidationRequest.setImei(objCustomerRequestPojo.getImei());
				objValidationRequest.setImsi(objCustomerRequestPojo.getImsi());
				objValidationRequest.setLac(objCustomerRequestPojo.getLac());
				objValidationRequest.setLang(objCustomerRequestPojo.getLang());
				objValidationRequest.setLat(objCustomerRequestPojo.getLat());
				objValidationRequest.setLon(objCustomerRequestPojo.getLon());
				objValidationRequest.setMcc(objCustomerRequestPojo.getMcc());
				objValidationRequest.setMnc(objCustomerRequestPojo.getMnc());
				objValidationRequest.setMno(objCustomerRequestPojo.getMno());
				objValidationRequest.setMod(objCustomerRequestPojo.getMod());
				objValidationRequest.setOs(objCustomerRequestPojo.getOs());
				objValidationRequest.setPeml(objCustomerRequestPojo.getPeml());
				objValidationRequest.setPkg(objCustomerRequestPojo.getPkg());
				objValidationRequest.setRot(objCustomerRequestPojo.getRot());
				objValidationRequest.setSt(objCustomerRequestPojo.getSt());
				objValidationRequest.setTkn(objCustomerRequestPojo.getTkn());
				objValidationRequest.setVer(objCustomerRequestPojo.getVer());
				
			}
			
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			json = mapper.writeValueAsString(objValidationRequest);
			System.out.println("CRM REQUEST JSON=="+json);
			logger.info("CRM api Validation hit Request"+json);
			System.out.println("json is"+json);	
	
			url=(String)map.get("Validation");
						System.out.println("validation url"+url);
			if(!ObjectUtility.isEmptyString(json))
			{
				response=HitUrl.hitUrl(json,"Validation",url,"");
				logger.info("CRM api Validation hit Request"+json);
				 System.out.println("response is======="+response);
			}
			
			if(!ObjectUtility.isEmptyString(response))
			{
			System.out.println("response is=="+response);	
			objCustomerResponsePojo = MapperUtil.readAsObjectOf(CustomerResponsePojo.class,response);
			System.out.println("inside not null=="+objCustomerResponsePojo.getPd()+","+objCustomerResponsePojo.getRc()+","+objCustomerResponsePojo.getRd()+","+objCustomerResponsePojo.getRs());	
				if(objCustomerResponsePojo !=null)
				{
			System.out.println("inside not null=="+objCustomerResponsePojo.getPd()+","+objCustomerResponsePojo.getRc()+","+objCustomerResponsePojo.getRd()+","+objCustomerResponsePojo.getRs());
				
					if(objCustomerResponsePojo.getRs().equalsIgnoreCase("FL") || ObjectUtility.isEmptyString(objCustomerResponsePojo.getRs()) ||ObjectUtility.isEmptyString(objCustomerResponsePojo.getRd()) ||ObjectUtility.isEmptyString(objCustomerResponsePojo.getRs()) ||ObjectUtility.isEmptyString(objCustomerResponsePojo.getMno()))		
					{
						objCustomerResponse.setStatus("-1");
						objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
					}
					else
					{
						
						long time=System.currentTimeMillis();
						usrname=""+time+objCustomerResponsePojo.getMno();
						pwd=""+time;
						System.out.println("username is=="+usrname+","+pwd);
						int Status=objUserCreate.UserCreat(usrname, pwd);
						
						if(Status==0)
						{
				        check=objUserDao.validationDao(objCustomerRequestPojo.getTkn(),objCustomerResponsePojo.getMno(),objCustomerRequestPojo.getLang(),"", objCustomerRequestPojo.getTkn(), objCustomerResponsePojo.getRs(),usrname,pwd,objCustomerResponsePojo.getPd().getNam());		
						 System.out.println("check=="+check);
						    
						    if(check== -1)
						    {
						    	objCustomerResponse.setStatus("-1");
							objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
						    	System.out.println("value not inserted in database");
						    	objUserDelete.userDelete(usrname,pwd);
						    }
						    else
						    {
						    	objCustomerResponse.setId(usrname);
								objCustomerResponse.setAccessSp(pwd);
								objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
								objCustomerResponse.setStatus("0");
								
						    	
						    }
						}
						else
						{
							objCustomerResponse.setStatus("-1");
							objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
						}
						
					}
				}
			}
			else
			{
				objCustomerResponse.setStatus("-1");
				objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("CustomerValidationUmang="+e);
			objCustomerResponse.setStatus("-1");
			objCustomerResponse.setRequestId(objCustomerRequestPojo.getRequestId());
		    return objCustomerResponse;
		}
		return objCustomerResponse;
	}

	}
