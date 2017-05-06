package android.cool.com.daydayup2;

public class SDate {
	private int Year;
	private int month;
	private int day;
	public SDate(int year, int month, int day) {
		Year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return Year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
		
	public static SDate getDate(String d)
	{
		int year=1;
		int month=1;
		int day=1;
		d=d.trim();
		year=Integer.parseInt(d.substring(0, 4));
		switch(d.length())
		{
		case 8:
			month=Integer.parseInt(d.charAt(5)+"");
			day=Integer.parseInt(d.charAt(7)+"");
			break;
		case 9:
			if(d.charAt(6)>'9'||d.charAt(6)<'0')
			{
				month=Integer.parseInt(d.charAt(5)+"");
				day=Integer.parseInt(d.substring(7, 9));
			}
			else
			{
				month=Integer.parseInt(d.substring(5, 7));
				day=Integer.parseInt(d.charAt(8)+"");
			}
			break;
		case 10:
			month=Integer.parseInt(d.substring(5, 7));
			day=Integer.parseInt(d.substring(8, 10));
			break;
		}
		return new SDate(year,month,day);
	}
	public static String getDateBack(SDate sDate)
	{
		String date="";
		date=sDate.getYear()+"-"+sDate.getMonth()+"-";
		if(sDate.getDay()>=10)
		{
			date=date+String.valueOf(sDate.getDay());
		}
		else
		{
			date=date+"0"+String.valueOf(sDate.getDay());
		}
		return date;
	}

}
