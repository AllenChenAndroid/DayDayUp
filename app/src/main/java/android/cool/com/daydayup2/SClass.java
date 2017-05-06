package android.cool.com.daydayup2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SClass {
	private String theclass;
	private String theplace;
	private String weekTime;
	public SClass(String theclass, String theplace, String weekTime) {
		this.theclass = theclass;
		this.theplace = theplace;
		this.weekTime = weekTime;
	}
	public String getTheclass() {
		return theclass;
	}
	public String getTheplace() {
		return theplace;
	}
	public String getWeekTime() {
		return weekTime;
	}
	 public static SClass getInformation(String information)
	 {


		 ArrayList<String> array=new ArrayList<String>();
		 String can="";
		 if(information!=null){
		 for(int i=0;i<information.length();i++)
		 {
			 if(information.charAt(i)!='&')
			 {
				 can+=information.charAt(i);
			 }
			 else
			 {
				 array.add(can);
				 can="";
			 }
		 }}
		 array.add(can);
		 array.add("");
		 array.add("0");
		 return new SClass(array.get(0),array.get(1),array.get(2));
		
		 
	 }
	 public static String getInformationBack(SClass sClass)
		{
			String v=sClass.getTheclass().trim()+"&"+sClass.getTheplace().trim()+"&"+sClass.getWeekTime();
			return v;
		}
	 public static Boolean ifClass(int w,int wth)
	 {
		 
		 boolean isClass=false;
		 switch(w)
		 {
		 case 0:
			 isClass=true;
			 break;
		 case 1:
			 if(wth%2==1)
				 isClass=true;
			 break;
		 case 2:
			 if(wth%2==0)
				 isClass=true;
			 break;
		 case 3:
			 break;
	    default:
	    	if(wth>=w/1000&&wth<=w%100)
	    		isClass=true;
	    	
				 
		 }
		 return isClass;
	 }
	 public static String getWeekOfToday()
	 {
		 Date dt=new Date();
		 String []weekDays={"Sun","Mon","Tue","Wed","Thu","Fri","Sar"};
		 Calendar cal=Calendar.getInstance();
		 cal.setTime(dt);
		 int w=cal.get(Calendar.DAY_OF_WEEK)-1;
		 if(w<0)
			 w=0;
		 return weekDays[w];
		 
	 }
	 public static String getWeek(int week)
	 {
		 String w="";
		 switch(week)
		 {
			 case 0:
				 w="全部";
				 break;
			 case 1:
				 w="单周";
				 break;
			 case 2:
				 w="双周";
				 break;
			 default:
				 w=week/1000+"-"+week%100;
				 break;
		 }
		 return w;
	 }

}
