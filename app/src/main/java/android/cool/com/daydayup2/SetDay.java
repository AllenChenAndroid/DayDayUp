package android.cool.com.daydayup2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SetDay extends AppCompatActivity {
int week=0;
    String weektip="";
    List<School> schools;
    List <String> classlist;
    List <setdayitem> list;
    private GestureDetector mGestureDetector;
    String day="Mon";
    SetDayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_day);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_day);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Toast.makeText(this,"左右滑动可改变星期",Toast.LENGTH_LONG).show();
        schools= DataSupport.select("Tip","Mon","Tue","Wed","Thu","Fri","Sar","Sun").find(School.class);
        final TextView weekView= (TextView) findViewById(R.id.week);
        week=1;
        weektip="星期一";
        setTitle("设定课表");
        weekView.setText(weektip);
        classlist=new ArrayList<String>();
        list=new ArrayList<setdayitem>();
        getData("Mon");
        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.rec_setday);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter=new SetDayAdapter(this,list,day);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        GestureDetector.SimpleOnGestureListener listener=new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(e1.getX()-e2.getX()>100&&Math.abs(velocityX)>200){
                    setWeek(week+1);
                    weekView.setText(weektip);
                    getData(day);
                    adapter.notifyDataSetChanged();
                    adapter.setDay(day);


                }
                else if(e2.getX()-e1.getX()>100&&Math.abs(velocityX)>200)
                {
                    setWeek(week-1);
                    weekView.setText(weektip);
                    getData(day);
                    adapter.notifyDataSetChanged();
                    adapter.setDay(day);

                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        };
        mGestureDetector = new GestureDetector(this, listener);
        Button bt_day= (Button) findViewById(R.id.bt_day);
        bt_day.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetDay.this,SetPro.class);
                SetDay.this.startActivity(intent);
                SetDay.this.finish();
            }
        });
    }
    private void getData(String day) {
        // TODO Auto-generated method stub
       classlist.clear();
        for(int i=0;i<schools.size();i++){
            if(day.equals("Mon")){
            classlist.add(schools.get(i).getMon());}
            else if(day.equals("Tue")){
                classlist.add(schools.get(i).getTue());
            }
            else if(day.equals("Wed")){
                classlist.add(schools.get(i).getWed());
            }
            else if(day.equals("Thu")){
                classlist.add(schools.get(i).getThu());
            }
            else if(day.equals("Fri")){
                classlist.add(schools.get(i).getFri());
            }
            else if(day.equals("Sar")){
                classlist.add(schools.get(i).getSar());
            }
            else if(day.equals("Sun")){
                classlist.add(schools.get(i).getSun());
            }
        }
        list.clear();

        for(int i=0;i<classlist.size();i++){

          SClass sClass=SClass.getInformation(classlist.get(i));
            list.add(new setdayitem(sClass.getTheplace(),sClass.getTheclass(),TipUtil.getTipWithFormate(i),sClass.getWeekTime()));
        }


    }
   private void setWeek(int week){
       this.week=week;

       switch (week) {
           case 0:
               this.week = 6;
               weektip = "星期六";
               day = "Sar";
               break;
           case 1:
               this.week = 1;
               weektip = "星期一";
               day = "Mon";
               break;
           case 2:
               this.week = 2;
               weektip = "星期二";
               day = "Tue";
               break;
           case 3:
               this.week = 3;
               weektip = "星期三";
               day = "Wed";
               break;
           case 4:
               this.week = 4;
               weektip = "星期四";
               day = "Thu";
               break;
           case 5:
               this.week = 5;
               weektip = "星期五";
               day = "Fri";
               break;
           case 6:
               this.week = 6;
               weektip = "星期六";
               day = "Sar";
               break;
           case 7:
               this.week = 1;
               weektip = "星期一";
               day = "Mon";
               break;
       }

           getData(day);
           //list.clear();





   }
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
               adapter.AdapterOnResult(requestCode,resultCode,data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(SetDay.this,MainActivity.class);
                SetDay.this.startActivity(intent);
                SetDay.this.finish();
        }
        return true;
    }
}
