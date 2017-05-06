package android.cool.com.daydayup2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;

public class TimeUtil {
	private ArrayList<String> classList;
	private ArrayList<String> timeList;
	private int i=0;
	

public static String getCurrentTime()
{
	String t="";
	String h="";
	String m="";
	Calendar cal=Calendar.getInstance();
	if(cal.get(Calendar.HOUR_OF_DAY)<=9)
	{
		h="0"+cal.get(Calendar.HOUR_OF_DAY);
	}
	else
	{
		h+=cal.get(Calendar.HOUR_OF_DAY);
	}
	if(cal.get(Calendar.MINUTE)<=9)
	{
		m="0"+cal.get(Calendar.MINUTE);
	}
	else
	{
		m+=cal.get(Calendar.MINUTE);
	}
	t=h+":"+m;
	Log.i("current",t);
	return t;
	
	}

public static String minLater(String time, int min) {
	// TODO Auto-generated method stub
	String h="";
	String m="";
	STime stime=STime.getTime(time);
	int hour=stime.getHour();
	int minuter=stime.getMinutes();
	hour=(hour+(minuter+min)/60)%24;
	minuter=(minuter+min)%60;
	if(hour<=9)
	{
		h="0"+hour;
		
	}
	else
	{
		h+=hour;
	}
	if(minuter<=9)
	{
		m="0"+minuter;
	}
	else
	{
		m+=minuter;
	}
	return (h+":"+m);
	
}








}
