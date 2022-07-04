package com.aytuncbakir.lms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static String toCommaSeperatedString(Object[] items) {
		StringBuilder sb = new StringBuilder();
		
		for(Object item : items) {
			
			sb.append(item).append(",");
		}
		
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString().trim();
	}
	
	
	public static long findDifference(String start_date){
		  
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
  
        // Try Block
        try {
  
            // parse method is used to parse
            // the text from a string to
            // produce the date
        	
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String end_date = dateFormat.format(date);
        	
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
  
           
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Days = (difference_In_Time ) / (1000 * 60 * 60 * 24) % 365;
  
           return difference_In_Days;
        }
  
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
		return 0;
    }
	
	
	


}
