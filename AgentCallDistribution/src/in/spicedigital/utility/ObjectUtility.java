package in.spicedigital.utility;

public class ObjectUtility 
{
	
	public static boolean isEmptyString(String value)
	{
		if(value==null||value.trim().length()==0)
		{
			return true;
		}
		return false;
	}

}
