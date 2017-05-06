package android.cool.com.daydayup2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cl on 2017/5/4.
 */

public class SetClassAdapter extends RecyclerView.Adapter<SetClassAdapter.ViewHolder> {
    private List<setclassitem> mList;
    Context context;
    private String className="";
    private String classPlace="";
    private String classWeek="";
    public SetClassAdapter(Context context, List<setclassitem> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setclasslist, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch(position)
                {
                    case 0:
                        showClassNameDialog(position);
                        break;
                    case 1:
                        showClassPlaceDialog(position);
                        break;
                    case 2:
                        showWeekPickerDialog(position);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            title= (TextView) itemView.findViewById(R.id.title_);
            content= (TextView) itemView.findViewById(R.id.content);
        }
    }
    public void showClassNameDialog(final int position) {
        // TODO Auto-generated method stub
        final EditText editText=new EditText(context);
        new AlertDialog.Builder(context).setTitle("请输入课名").setIcon(android.R.drawable.ic_dialog_info).setView(editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                className=" "+editText.getText().toString();

                mList.set(0,new setclassitem(className,"课名"));
                notifyDataSetChanged();

            }
        }).setNegativeButton("取消", null).show();

    }
    public void showClassPlaceDialog(final int position) {
        // TODO Auto-generated method stub
        final EditText editText=new EditText(context);
        new AlertDialog.Builder(context).setTitle("请输入地点").setIcon(android.R.drawable.ic_dialog_info).setView(editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                classPlace=" "+editText.getText().toString();
                mList.set(1,new setclassitem(classPlace,"地点"));
                notifyDataSetChanged();

            }
        }).setNegativeButton("取消", null).show();



    }
    public void showWeekPickerDialog(final int position) {
        // TODO Auto-generated method stub
        final boolean check[]=new boolean[25];
       classWeek="";
        Arrays.fill(check,false);
        final String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
        new AlertDialog.Builder(context).setTitle("请选择周次").setMultiChoiceItems(values, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               if(isChecked){
                   check[which]=true;
               }
                else{
                   check[which]=false;
               }
            }
        }).setPositiveButton("确定",new DialogInterface.OnClickListener(

        ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             for(int i=1;i<26;i++){
                 if(check[i-1]){
                     classWeek=classWeek+i;
                     if((i-1)!=25){
                         classWeek=classWeek+" ";
                     }
                 }

             }
                mList.set(2,new setclassitem(classWeek,"周次"));
                notifyDataSetChanged();
            }
        }).setNegativeButton("取消",null).show();
    }
}
