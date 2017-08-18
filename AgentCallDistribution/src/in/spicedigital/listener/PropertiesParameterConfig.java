package in.spicedigital.listener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class PropertiesParameterConfig 
{
    HashMap hashMap;

    public HashMap getHashMap() 
    {
        return hashMap;
    }

    public void setHashMap(HashMap hashMap) 
    {
        this.hashMap = hashMap;
    }
    private static PropertiesParameterConfig objPropertiesParameter = null;

    private PropertiesParameterConfig() {
    }

    public static PropertiesParameterConfig getPropertiesParameterObject() 
    {
        if (objPropertiesParameter == null) {
            try {
                objPropertiesParameter = new PropertiesParameterConfig();
                objPropertiesParameter.setHashMap(objPropertiesParameter.getParameters("/home/tomcat/chatappbase/AgentCallDistribution/WEB-INF/classes/Config.cfg"));
                //objPropertiesParameter.setHashMap(objPropertiesParameter.getParameters("E:/Config.cfg"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return objPropertiesParameter;
    }
    private HashMap getParameters(String strFileName) 
    {
        String strStingArray[] = null;
        HashMap hashMap = new HashMap();
        try 
        {
            BufferedReader objBufferedReader = new BufferedReader(new FileReader(strFileName));
            String sCurrentLine;
            while ((sCurrentLine = objBufferedReader.readLine()) != null) 
            {
                strStingArray = sCurrentLine.split("=");
                if (strStingArray.length == 2) 
                {
                    hashMap.put(strStingArray[0], strStingArray[1]);
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return hashMap;
    }
}
