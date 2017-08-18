package in.spicedigital.utility;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;



public class UserDelete
{
 
 
  public static int userDelete(String uname, String pwd)
  {
  
	 ConnectionConfiguration connConfig = new ConnectionConfiguration(
     "10.247.74.53", 5222);
	 XMPPConnection connection = new XMPPConnection(connConfig);
    try 
    {
     connection.connect();
     connection.login(uname, pwd);
     AccountManager accountManager = connection.getAccountManager();
     accountManager.deleteAccount();
     connection.disconnect();
    }
    catch (Exception e)
    {
     e.printStackTrace();
     return -1;
    }
    return 0;
  }
  

  public static void main(String args[])
  {
	  int resp= userDelete("harsh","harsh");
	  System.out.println("status is=="+resp);
  }
}
