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
import android.widget.TextView;

import com.wl.entry.Adress;
import com.wl.entry.Zhengben;
import com.wl.hnb.R;

import java.util.List;


public class DataAdapteradress extends BaseAdapter {
    private int[] colors = new int[]{0xff626569, 0xff4f5257};
    SparseBooleanArray selected;
    boolean isSingle = true;
    int old = -1;

    private class ViewHolder {
        private TextView name;

        private TextView adress;


    }

    private List<Object> list;

    private LayoutInflater mInflater;
    private Context mContext;

    public DataAdapteradress(Context context, List<Object> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_adress_display, parent, false);

            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.adress = (TextView) view.findViewById(R.id.adress);



            view.setTag(viewHolder);
        } else {
            //int colorPos = position % colors.length;
            //view.setBackgroundColor(colors[colorPos]);
            //view.setBackgroundColor(Color.parseColor("#90ee90"));
            //convertView.setBackgroundResource(R.drawable.selector);

            viewHolder = (ViewHolder) view.getTag();
        }


        Adress info = (Adress) list.get(position);



            viewHolder.name.setText(String.valueOf(info.name));
            viewHolder.adress.setText(String.valueOf(info.adress));




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






