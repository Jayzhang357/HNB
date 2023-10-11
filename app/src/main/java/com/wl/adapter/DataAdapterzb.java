package com.wl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wl.entry.JZXX;
import com.wl.entry.JZZT;
import com.wl.entry.Zhengben;
import com.wl.hnb.R;

import java.util.List;


public class DataAdapterzb extends BaseAdapter {
    private int[] colors = new int[]{0xff626569, 0xff4f5257};
    SparseBooleanArray selected;
    boolean isSingle = true;
    int old = -1;

    private class ViewHolder {
        private TextView month;
        private ImageView type;
        private TextView name;
        private TextView time;
        private TextView money;
        private LinearLayout content;

    }

    private List<Object> list;

    private LayoutInflater mInflater;
    private Context mContext;

    public DataAdapterzb(Context context, List<Object> data) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = data;
        mContext = context;
        selected = new SparseBooleanArray();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_zb_display, parent, false);
            viewHolder.content = (LinearLayout) view.findViewById(R.id.content);
            viewHolder.month = (TextView) view.findViewById(R.id.month);
            viewHolder.type = (ImageView) view.findViewById(R.id.type);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            viewHolder.money = (TextView) view.findViewById(R.id.money);


            view.setTag(viewHolder);
        } else {
            //int colorPos = position % colors.length;
            //view.setBackgroundColor(colors[colorPos]);
            //view.setBackgroundColor(Color.parseColor("#90ee90"));
            //convertView.setBackgroundResource(R.drawable.selector);

            viewHolder = (ViewHolder) view.getTag();
        }


        Zhengben info = (Zhengben) list.get(position);
        if (String.valueOf(info.month).length() > 0) {

            viewHolder.month.setText(String.valueOf(info.month));
            viewHolder.month.setVisibility(View.VISIBLE);
            viewHolder.content.setVisibility(View.GONE);
        } else {
            viewHolder.month.setVisibility(View.GONE);
            viewHolder.content.setVisibility(View.VISIBLE);

            if (info.type == 0) viewHolder.type.setImageResource(R.drawable.dill_icon_income);
            else
                viewHolder.type.setImageResource(R.drawable.dill_icon_pay);
            viewHolder.name.setText(String.valueOf(info.name));
            viewHolder.time.setText(String.valueOf(info.time));

            viewHolder.money.setText(String.valueOf(info.money));

        }
        //    viewHolder.content10.setText(String.valueOf(info.Lon_B));

        if (selected.get(position)) {
            //view.setBackgroundResource(Color.BLUE);
            view.setBackgroundColor(Color.parseColor("#9999cc"));

        } else {
            //view.setBackgroundResource(Color.WHITE);
            view.setBackgroundColor(Color.TRANSPARENT);

        }
       /* if (position == selectItem) {  
        	view.setBackgroundColor(Color.RED);  
        }   
        else {  
        	view.setBackgroundColor(Color.TRANSPARENT);  
        }   */

        return view;
    }

    public void setSelectedItem(int selected) {
        if (isSingle = true && old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selected, true);
        old = selected;
    }
    
   /* public  void setSelectItem(int selectItem) {  
        this.selectItem = selectItem;  
   }  
   private int  selectItem=-1;  */

}






