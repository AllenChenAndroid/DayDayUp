package android.cool.com.daydayup2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LitePal.getDatabase();
        if(isFirstStart()==true){
            initDataBase();
        }
        List<School> schools= DataSupport.select("Tip","Time").find(School.class);
        List<settimeitem> list=new ArrayList<>();
        for(int i=0;i<schools.size();i++){
            list.add(new settimeitem(schools.get(i).getTime(),schools.get(i).getTip()));
        }

        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.recy_settime);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        SetTimeAdapter adapter=new SetTimeAdapter(list,this);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        Button bt=(Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent =new Intent();
                intent.setClass(MainActivity.this, SetDay.class);
                startActivity(intent);
                MainActivity.this.finish();
            }});
    }
    private boolean isFirstStart(){
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        Boolean first=pref.getBoolean("first",true);
        return first;
    }
    private void initDataBase() {
        School school[]=new School[6];
        for(int i=0;i<6;i++){
            school[i]=new School();
            school[i].setMon("");
            school[i].setTue("");
            school[i].setWed("");
            school[i].setThu("");
            school[i].setFri("");
            school[i].setSar("");
            school[i].setSun("");
        }
        school[0].setTip("1-2");
        school[0].setTime("08:00");

        school[0].save();
        school[1].setTip("3-4");
        school[1].setTime("10:00");

        school[1].save();
        school[2].setTip("5-6");
        school[2].setTime("13:20");

        school[2].save();
        school[3].setTip("7-8");
        school[3].setTime("15:20");

        school[3].save();
        school[4].setTip("9-10");
        school[4].setTime("18:00");

        school[4].save();
        school[5].setTip("11-12");
        school[5].setTime("20:00");

        school[5].save();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putBoolean("first",false);
        editor.apply();
    }
}
