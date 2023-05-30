package com.jingdong.app.mall.widget.editWidget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    private TextView f12054g;

    /* renamed from: h  reason: collision with root package name */
    private List<String> f12055h;

    public a(List<String> list) {
        this.f12055h = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f12055h.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f12055h.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_more_setting_item, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.ass);
        this.f12054g = textView;
        List<String> list = this.f12055h;
        if (list != null) {
            textView.setText(list.get(i2));
        }
        return inflate;
    }
}
