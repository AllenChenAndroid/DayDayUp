package android.cool.com.daydayup2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cl on 2017/5/3.
 */

public class SetDayAdapter extends RecyclerView.Adapter<SetDayAdapter.ViewHolder> {
    private List<setdayitem> mList;
    Context context;
    private int theclass;
    private String day;


    public SetDayAdapter(Context context, List<setdayitem> mList,String day) {
        this.context = context;
        this.mList = mList;
        this.day=day;
    }

    @Override
    public SetDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setdaylist, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.setdayview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Bundle bundle=new Bundle();
                bundle.putInt("thetip",position);
                bundle.putString("theday",day);
                bundle.putString("thename",mList.get(position).getName());
                theclass=position;
                bundle.putString("theplace",mList.get(position).getPlace());
                bundle.putString("theweek",mList.get(position).getWeek());

                Intent intent=new Intent(context,SetClass.class);
                intent.putExtras(bundle);
                ((Activity)context).startActivityForResult(intent,0);




            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SetDayAdapter.ViewHolder holder, int position) {
        setdayitem setdayitem=mList.get(position);
        holder.tip.setText(setdayitem.getTip());
        holder.name.setText(setdayitem.getName());
        holder.place.setText(setdayitem.getPlace());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View setdayview;
        TextView tip;
        TextView name;
        TextView place;
        public ViewHolder(View view) {
            super(view);
            setdayview=view;
            tip=(TextView) view.findViewById(R.id.day_tip);
            name= (TextView) view.findViewById(R.id.day_name);
            place= (TextView) view.findViewById(R.id.day_place);

        }
    }
    public void AdapterOnResult(int requestCode, int resultCode, Intent data){
        switch(resultCode)
        {
            case -1:
                Bundle bundle=data.getExtras();
               String thename=bundle.getString("thename");
                String theplace=bundle.getString("theplace");
                String theweek=bundle.getString("theweek");
                mList.set(theclass,new setdayitem(theplace,thename,mList.get(theclass).getTip(),theweek));



        }

    }
    public void setDay(String day){
        this.day=day;
    }
}
