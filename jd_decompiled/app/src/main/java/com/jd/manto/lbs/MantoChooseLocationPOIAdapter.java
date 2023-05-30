package com.jd.manto.lbs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.manto.map.R;
import com.jingdong.manto.jsapi.refact.lbs.MapAddress;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoChooseLocationPOIAdapter extends BaseAdapter {
    Context cxt;
    List<MapAddress> data;
    private int selectPosition;

    /* loaded from: classes17.dex */
    public static class ViewHolder {
        TextView address;
        ImageView checkBox;
        TextView title;
    }

    public MantoChooseLocationPOIAdapter(Context context) {
        this.cxt = context;
    }

    public void appendData(List<MapAddress> list) {
        List<MapAddress> list2 = this.data;
        if (list2 == null) {
            this.data = list;
        } else {
            list2.addAll(list);
        }
    }

    public MapAddress getChoosedPOI() {
        List<MapAddress> list = this.data;
        if (list != null) {
            return list.get(this.selectPosition);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<MapAddress> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.data.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.cxt).inflate(R.layout.map_poi_list_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.address = (TextView) view.findViewById(R.id.map_poi_item_addr);
            viewHolder.title = (TextView) view.findViewById(R.id.map_poi_item_title);
            viewHolder.checkBox = (ImageView) view.findViewById(R.id.map_poi_item_checkbox);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MapAddress mapAddress = (MapAddress) getItem(i2);
        viewHolder.title.setText(mapAddress.name);
        viewHolder.address.setText(mapAddress.address);
        viewHolder.checkBox.setVisibility(this.selectPosition == i2 ? 0 : 8);
        return view;
    }

    public void setData(List<MapAddress> list) {
        this.data = list;
        setSelectPosition(0);
    }

    public void setSelectPosition(int i2) {
        this.selectPosition = i2;
    }
}
