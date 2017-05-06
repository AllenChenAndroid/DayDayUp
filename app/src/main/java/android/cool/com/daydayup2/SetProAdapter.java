package android.cool.com.daydayup2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cl on 2017/5/5.
 */

public class SetProAdapter extends RecyclerView.Adapter<SetProAdapter.ViewHolder> {
    private List<setclassitem> mList;
    Context context;
    String data = "";
    String valueTip="";

    public SetProAdapter(Context context, List<setclassitem> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setclasslist, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position) {
                    case 0:
                        setDate();
                        break;
                    case 1:
                        setTipTime();
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.content.setText(mList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.title_);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    private void setDate() {
        // TODO Auto-generated method stub
        data = mList.get(0).getContent();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                String thedata = SDate.getDateBack(new SDate(year, monthOfYear+1 , dayOfMonth));

                data = thedata;
                mList.set(0,new setclassitem(data,"开学日期"));
                notifyDataSetChanged();

            }
        }, SDate.getDate(data).getYear(), SDate.getDate(data).getMonth()-1, SDate.getDate(data).getDay()).show();





}
    private void setTipTime() {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(context).setTitle("请选择提醒时间").setSingleChoiceItems(new String[]{"5分钟","10分钟","15分钟","20分钟","不提醒"},0,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

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

                    }

                }
        ).setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mList.set(1,new setclassitem(valueTip,"提醒时间"));
                notifyDataSetChanged();
            }
        }).setNegativeButton("取消",null).show();






    }

}

