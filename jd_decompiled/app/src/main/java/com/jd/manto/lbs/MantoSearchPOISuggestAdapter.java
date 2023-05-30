package com.jd.manto.lbs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jd.manto.map.R;
import com.jingdong.manto.jsapi.refact.lbs.MapAddress;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoSearchPOISuggestAdapter extends BaseAdapter {
    private static final int MARk_COLOR = Color.parseColor("#f0250f");
    private Context cxt;
    private List<MapAddress> data;
    private String keyword;

    /* loaded from: classes17.dex */
    public static class ViewHolder {
        TextView address;
        TextView title;
    }

    public MantoSearchPOISuggestAdapter(Context context) {
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

    @Override // android.widget.Adapter
    public int getCount() {
        List<MapAddress> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<MapAddress> getData() {
        return this.data;
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
            view = LayoutInflater.from(this.cxt).inflate(R.layout.map_poi_suggest_list_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.address = (TextView) view.findViewById(R.id.map_poi_item_addr);
            viewHolder.title = (TextView) view.findViewById(R.id.map_poi_item_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MapAddress mapAddress = (MapAddress) getItem(i2);
        TextView textView = viewHolder.title;
        String str = this.keyword;
        String str2 = mapAddress.name;
        int i3 = MARk_COLOR;
        textView.setText(MantoStringUtils.getForegroundSpannable(str, str2, i3));
        viewHolder.address.setText(MantoStringUtils.getForegroundSpannable(this.keyword, mapAddress.address, i3));
        return view;
    }

    public void setData(List<MapAddress> list) {
        this.data = list;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }
}
