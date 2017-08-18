package in.spicedigital.utility;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

public class UserCreate
 {
   public  int UserCreat(String usrname,String pwd)
   {
    ConnectionConfiguration connConfig = new ConnectionConfiguration(
      "10.247.74.53", 5222);
    XMPPConnection connection = new XMPPConnection(connConfig);
     try 
     {
      connection.connect();
      connection.login("admin", "chat@umang");
      AccountManager accountManager = connection.getAccountManager();
      accountManager.createAccount(usrname, pwd);
      connection.disconnect();
     }
     catch (Exception e) 
     {
     e.printStackTrace();
     return -1;
     }
     return 0;
   }
   
   
}

