package android.cool.com.daydayup2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by cl on 2017/5/3.
 */

public class SetTimeAdapter extends RecyclerView.Adapter<SetTimeAdapter.ViewHolder> {
    private List<settimeitem> mList;
    Context context;

    public SetTimeAdapter(List<settimeitem> list, Context context) {
        mList = list;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settimelist, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.settimeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position <= 5) {
                    setTime(position);
                }
            }
        });
        return holder;

    }

   /* private void setDate(final int position) {
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                String thedata = SDate.getDateBack(new SDate(year, monthOfYear + 1, dayOfMonth));
                mList.set(position,new settimeitem(thedata,"开学日期"));
                notifyDataSetChanged();
                List<School> schools= DataSupport.where("Tip=?","1-2").find(School.class);
                if(schools.size()>0){
                    schools.get(0).setData(thedata);
                    schools.get(0).save();
                }

            }
        }, SDate.getDate(mList.get(position).getTime()).getYear(), SDate.getDate(mList.get(position).getTime()).getMonth(), SDate.getDate(mList.get(position).getTime()).getDay()).show();

    }
*/


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    settimeitem settimeitem=mList.get(position);
        holder.tip.setText(settimeitem.getTip());
        holder.time.setText(settimeitem.getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View settimeview;
        TextView tip;
        TextView time;
        public ViewHolder(View view) {
            super(view);
            settimeview=view;
            tip=(TextView) view.findViewById(R.id.tip);
            time=(TextView) view.findViewById(R.id.time);
        }
    }
    private void setTime(final int position) {
        // TODO Auto-generated method stub
        new TimePickerDialog(context ,new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String thetime=STime.getTimeBack(new STime(hourOfDay,minute));
                String theclass=TipUtil.getTheClass(position);
                mList.set(position,new settimeitem(thetime,theclass));
                notifyDataSetChanged();

                List<School> schools= DataSupport.where("Tip=?",theclass).find(School.class);
                if(schools.size()>0){
                    schools.get(0).setTime(thetime);
                    schools.get(0).save();
                }





                // TODO Auto-generated method stub

            }},STime.getTime(mList.get(position).getTime()).getHour(),STime.getTime(mList.get(position).getTime()).getMinutes(),true).show();

    }
  /*  private void setTipTime(final int position) {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(context).setTitle("请选择提醒时间").setSingleChoiceItems(new String[]{"5分钟","10分钟","15分钟","20分钟","不提醒"},0,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String valueTip="";
                        switch(which)
                        {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                                valueTip=(which+1)*5+"";
                                break;
                            case 4:
                                valueTip="不提醒";
                                break;
                        }
                        mList.set(position,new settimeitem(valueTip,"提醒时间"));
                        notifyDataSetChanged();
                        List<School> schools= DataSupport.where("Tip=?","3-4").find(School.class);
                        if(schools.size()>0){
                            schools.get(0).setData(valueTip);
                            schools.get(0).save();
                        }
                        dialog.dismiss();
                    }
                }
        ).show();






    }*/
 /*   private void setTheTheme(final int position) {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(context).setTitle("请选择风格主题").setSingleChoiceItems(new String[]{"阿狸", "dota"}, 0,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        String valueTip="";
                        switch(which)
                        {
                            case 0:
                                valueTip="阿狸";
                                break;
                            case 1:
                                valueTip="dota";
                                break;
                        }
                        mList.set(position,new settimeitem(valueTip,"风格主题"));
                        notifyDataSetChanged();
                        List<School> schools= DataSupport.where("Tip=?","5-6").find(School.class);
                        if(schools.size()>0){
                            schools.get(0).setData(String.valueOf(which));
                            schools.get(0).save();
                        }
                        dialog.dismiss();

                    }
                }
        ).show();

    }*/

}
