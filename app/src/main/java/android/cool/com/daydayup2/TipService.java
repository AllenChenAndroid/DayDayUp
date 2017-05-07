package android.cool.com.daydayup2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TipService extends Service {
    String date;
    String tiptime;
    List<String> list=new ArrayList<>();
    List<String> timelist=new ArrayList<>();
    List<String> realclasslist=new ArrayList<>();
    List<School> schools=new ArrayList<>();
    int id=1;
    public TipService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        date=pref.getString("date","");
        tiptime=pref.getString("tiptime","不提醒");
        if(!tiptime.equals("不提醒")){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getNextClass();
                }
            }).start();
            AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
            int zq=60*1000;
            long triggerAtTime= SystemClock.elapsedRealtime()+zq;
            Intent i=new Intent(this,TipService.class);
            PendingIntent pi=PendingIntent.getService(this,0,i,0);
            manager.set(AlarmManager.ELAPSED_REALTIME,triggerAtTime,pi);
        }
        else{
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getNextClass() {
        int weekday=TodayWeek();
        getClassOfToday(weekday);
        timelist.clear();
        for(int i=0;i<list.size();i++){
        timelist.add(SClass.getInformation(list.get(i)).getWeekTime());
        }

int week=getWeekOfToday(date);
        realclasslist.clear();
        for(int i=0;i<timelist.size();i++){
            String s[]=timelist.get(i).split(" ");
            int j;
            for( j=0;j<s.length;j++){
                int a=Integer.parseInt(s[j]);
                if(a==week){
                    realclasslist.add(list.get(i));
                    break;
                }

            }
            if(j==s.length){
                realclasslist.add("");
            }

        }
String currenttime=TimeUtil.getCurrentTime();
        String latertime=TimeUtil.minLater(currenttime,Integer.parseInt(tiptime));
        for(int i=0;i<realclasslist.size();i++){
            if(!realclasslist.get(i).equals("")){
                if(schools.get(i).getTime().equals(latertime)){
                   String name= SClass.getInformation(realclasslist.get(i)).getTheclass();
                    String place= SClass.getInformation(realclasslist.get(i)).getTheplace();
                 sendNoti(name,place);
                    break;
                }
            }
        }

    }

    private void sendNoti(String name,String place) {
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification noti=new NotificationCompat.Builder(this).setContentText(name+" "+place).setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("下节课将在"+tiptime+"后开始").setWhen(System.currentTimeMillis()).build();
        manager.notify(1,noti);
        //manager.cancelAll();
    }

    private void getClassOfToday(int weekday) {
        schools= DataSupport.select("Mon","Tue","Wed","Thu","Fri","Sar","Sun","Time").find(School.class);
        list.clear();
        switch (weekday){
            case 1:
                for(int i=0;i<schools.size();i++){
                    list.add(schools.get(i).getSun());
                }
                break;
            case 2:
                for(int i=0;i<schools.size();i++){
                    list.add(schools.get(i).getMon());
                }
                break;
            case 3:
            for(int i=0;i<schools.size();i++){
                list.add(schools.get(i).getTue());
            }
                break;
            case 4:
                for(int i=0;i<schools.size();i++){
                    list.add(schools.get(i).getWed());
                }
                break;
            case 5:
                for(int i=0;i<schools.size();i++){
                    list.add(schools.get(i).getThu());
                }
                break;
            case 6:
            for(int i=0;i<schools.size();i++){
                list.add(schools.get(i).getFri());
            }
            break;
            case 7:
                for(int i=0;i<schools.size();i++){
                    list.add(schools.get(i).getSar());
                }
                break;
            default:
                break;



        }

    }

    private int TodayWeek() {
        Date today=new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        return c.get(Calendar.DAY_OF_WEEK);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getWeekOfToday(String date) {
        // TODO Auto-generated method stub
        Calendar calstart=Calendar.getInstance();
        calstart.setFirstDayOfWeek(Calendar.MONDAY);


        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        try{
            calstart.setTime(format.parse(date));
        }catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }

        Calendar calend=Calendar.getInstance();
        calstart.setFirstDayOfWeek(Calendar.MONDAY);
        int week=0;
        while(calstart.compareTo(calend)<=0)
        {
            if(calstart.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
            {
                week++;
            }
            calstart.set(Calendar.DATE,calstart.get(Calendar.DATE)+1);
        }

        return week;
    }
}
