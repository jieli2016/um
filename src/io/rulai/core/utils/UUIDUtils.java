package io.rulai.core.utils;

public class UUIDUtils {

	public static String toUUIDString(String simpleUUIDString)
	{
	    String upperCase = simpleUUIDString.toUpperCase();
	    StringBuilder res = new StringBuilder();
	    res.append(upperCase.substring(0, 8));
	    res.append('-');
	    res.append(upperCase.substring(8, 12));
	    res.append('-');
	    res.append(upperCase.substring(12, 16));
	    res.append('-');
	    res.append(upperCase.substring(16, 20));
	    res.append('-');
	    res.append(upperCase.substring(20));
	    
	    return res.toString();
	}
	
	public static String toSimpleUUIDString(String UUID)
	{
	    StringBuilder res = new StringBuilder();
	    res.append(UUID.substring(0, 8));
	    res.append(UUID.substring(9, 13));
	    res.append(UUID.substring(14, 18));
	    res.append(UUID.substring(19, 23));
	    res.append(UUID.substring(24));
	    
	    return res.toString().toLowerCase();
	}

}
