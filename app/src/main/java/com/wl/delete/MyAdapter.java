package com.wl.delete;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.wl.entry.Device;
import com.wl.hnb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prj on 2016/10/25.
 */
public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Object> mList;

    public MyAdapter(Context context,  List<Object> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mInflater.inflate(R.layout.recyclerview__devivce_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        Device info = (Device) mList.get(position);

    /*    viewHolder.setImageResource(R.id.image,R.drawable.cm_bj_1);*/
        viewHolder.setText(R.id.devicename,String.valueOf(info.devicename));
        viewHolder.setText(R.id.typename,String.valueOf(info.typename));
        viewHolder.setText(R.id.carnumber,String.valueOf(info.carnumber));
        viewHolder.setText(R.id.stlyecar,String.valueOf(info.stlyecar));
        viewHolder.setText(R.id.typecar,String.valueOf(info.typecar));
        viewHolder.setText(R.id.date,String.valueOf(info.date));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }
}
