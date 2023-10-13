package com.wl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wl.entry.Device;
import com.wl.hnb.R;

import java.util.List;


public class DataAdapterdevice extends BaseAdapter {
    private int[] colors = new int[]{0xff626569, 0xff4f5257};
    SparseBooleanArray selected;
    boolean isSingle = true;
    int old = -1;

    private class ViewHolder {


        public TextView devicename;
        public TextView typename;
        public TextView carnumber;
        public TextView stlyecar;
        public TextView typecar;
        public TextView date;

    }

    private List<Object> list;

    private LayoutInflater mInflater;
    private Context mContext;

    public DataAdapterdevice(Context context, List<Object> data) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_mydevice_display, parent, false);
            viewHolder.devicename = (TextView) view.findViewById(R.id.devicename);
            viewHolder.typename = (TextView) view.findViewById(R.id.typename);
            viewHolder.carnumber = (TextView) view.findViewById(R.id.carnumber);
            viewHolder.stlyecar = (TextView) view.findViewById(R.id.stlyecar);
            viewHolder.typecar = (TextView) view.findViewById(R.id.typecar);
            viewHolder.date = (TextView) view.findViewById(R.id.date);


            view.setTag(viewHolder);
        } else {
            //int colorPos = position % colors.length;
            //view.setBackgroundColor(colors[colorPos]);
            //view.setBackgroundColor(Color.parseColor("#90ee90"));
            //convertView.setBackgroundResource(R.drawable.selector);

            viewHolder = (ViewHolder) view.getTag();
        }


        Device info = (Device) list.get(position);

        viewHolder.devicename .setText(String.valueOf(info.devicename));
        viewHolder.typename .setText(String.valueOf(info.typename));
        viewHolder.carnumber.setText(String.valueOf(info.carnumber));
        viewHolder.stlyecar .setText(String.valueOf(info.stlyecar));
        viewHolder.typecar .setText(String.valueOf(info.typecar));
        viewHolder.date .setText(String.valueOf(info.date));



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






