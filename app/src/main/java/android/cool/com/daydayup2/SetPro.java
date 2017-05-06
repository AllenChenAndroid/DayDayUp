package android.cool.com.daydayup2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SetPro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pro);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_pro);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String date=pref.getString("date","2017-3-1");
        String tiptime=pref.getString("tiptime","5");
        final List<setclassitem> list=new ArrayList<>();
        list.add(new setclassitem(date,"开学时间"));
        list.add(new setclassitem(tiptime,"提醒时间"));
        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.rec_pro);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        SetProAdapter adapter=new SetProAdapter(this,list);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        Button button= (Button) findViewById(R.id.bt_pro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("date",list.get(0).getContent());
                editor.putString("tiptime",list.get(1).getContent());
                editor.apply();
                Intent severIntent=new Intent(SetPro.this,TipService.class);
                SetPro.this.startService(severIntent);
                SetPro.this.finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(SetPro.this,SetDay.class);
                SetPro.this.startActivity(intent);
                SetPro.this.finish();
        }
        return true;
    }
}
