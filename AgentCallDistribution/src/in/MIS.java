package in;



	import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;// $p!c#vpnm0h@l!@w$r

//import org.apache.commons.io.FileUtils;


	public class MIS {
	    public static void main(String[] args) throws IOException {
	    	 HashMap<String,String> a=new HashMap<String,String>();

	    
	        String target_dir = "/home/tomcat/CmppChatbotClient/src/ResponseLog/18-3-2017/";//"E:\\t1";
	        File dir = new File(target_dir);
	        File[] files = dir.listFiles();
	        try {
	        for (File f : files) {
	            if(f.isFile()) {
	            	System.out.println("------"+f.getName() +"-- Hello");
	                BufferedReader inputStream = null;

	                ArrayList<String> a1=new 	  ArrayList();
	                List<String> lines =a1;// new ArrayList();//FileUtils.readLines(f);
	                	int count=0;
	                	
	                	for (String line : lines) 
	    				{
	    					//System.out.println(line);
	    					String abc[]=line.split(",");
	    					if(abc.length>5)
	    					{
	    						//System.out.println("------"+abc[1]);
//	    					    if(abc[1].indexOf("agent")!=-1)
//    						{
//	    					    	String msg=abc[3].split("=")[1];
//	    							msg=URLDecoder.decode(msg,"UTF-8");
	    							String time=abc[0].trim().split(" ")[0];
	    							String uniueId=abc[2].split("=")[1];
	    							String agentId=abc[4].split("=")[1];  
	    							String user=abc[5].split("=")[1];  
	    							a.put(uniueId, time+"_"+agentId+"_"+user);
	    							System.out.println("-----111-"+a.get(uniueId));
	    					//	}
	    					//}
	    					}
	                	
	                	
	                	
	    				}
	                }}
	        
	        FileWriter fstream;
	        BufferedWriter out;

	        // create your filewriter and bufferedreader
	        fstream = new FileWriter("/home/tomcat/values.txt");
	        out = new BufferedWriter(fstream);
	        
	        Iterator<Entry<String, String>> it = a.entrySet().iterator();

	        // then use the iterator to loop through the map, stopping when we reach the
	        // last record in the map or when we have printed enough records
	        while (it.hasNext() ) {

	            // the key/value pair is stored here in pairs
	            Map.Entry<String, String> pairs = it.next();
	            System.out.println("Value is " + pairs.getValue());

	            // since you only want the value, we only care about pairs.getValue(), which is written to out
	            out.append(pairs.getValue() + "\n");

	            
	        }
	        // lastly, close the file and end
	        out.close();
	        }
	                catch(Exception e){
	                	e.printStackTrace();
	                	
	                }
	                finally {
	                	Iterator entries = a.entrySet().iterator();
	                	while (entries.hasNext()) {
	                		 Map.Entry me2 = (Map.Entry) entries.next();
	                	    String a1=(String) me2.getKey();
	                	    System.out.println("Key: "+me2.getKey() + " & Value: " + me2.getValue());
	                	       
	                
//	                    if (inputStream != null) {
//	                        inputStream.close();
//	                    }
	                
	            }
	            }
	        }
	    }
	
	                
	        
	    

	

