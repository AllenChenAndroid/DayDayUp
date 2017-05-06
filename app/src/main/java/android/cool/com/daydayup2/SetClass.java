package android.cool.com.daydayup2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SetClass extends AppCompatActivity {
    int tip=0;
    String day="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_class);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_class);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tip=bundle.getInt("thetip");
        day=bundle.getString("theday");
         String thename=bundle.getString("thename");
        String theplace=bundle.getString("theplace");
        String theweek=bundle.getString("theweek");
        final List<setclassitem> list=new ArrayList<>();
        list.add(new setclassitem(thename,"课名"));
        list.add(new setclassitem(theplace,"地点"));
        list.add(new setclassitem(theweek,"周次"));
        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.rec_setclass);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        SetClassAdapter adapter=new SetClassAdapter(this,list);
        recyclerview.setAdapter(adapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        final Button po_bt= (Button) findViewById(R.id.po_bt);
        po_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle1=new Bundle();
                bundle1.putString("thename",list.get(0).getContent());
                bundle1.putString("theplace",list.get(1).getContent());
                bundle1.putString("theweek",list.get(2).getContent());
                String content=SClass.getInformationBack(new SClass(list.get(0).getContent(),list.get(1).getContent(),list.get(2).getContent()));
                List<School>  schools= DataSupport.findAll(School.class);
                if(day.equals("Mon")){
                    schools.get(tip).setMon(content);}
                else if(day.equals("Tue")){
                    schools.get(tip).setTue(content);
                }
                else if(day.equals("Wed")){
                    schools.get(tip).setWed(content);
                }
                else if(day.equals("Thu")){
                    schools.get(tip).setThu(content);
                }
                else if(day.equals("Fri")){
                    schools.get(tip).setFri(content);
                }
                else if(day.equals("Sar")){
                    schools.get(tip).setSar(content);
                }
                else if(day.equals("Sun")){
                    schools.get(tip).setSun(content);
                }
                schools.get(tip).save();
                Intent intent1=new Intent();
                intent1.putExtras(bundle1);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(SetClass.this,SetDay.class);
                SetClass.this.startActivity(intent);
                SetClass.this.finish();
        }
        return true;
    }
}
