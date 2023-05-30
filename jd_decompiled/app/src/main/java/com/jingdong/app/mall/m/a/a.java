package com.jingdong.app.mall.m.a;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import java.util.List;

/* loaded from: classes4.dex */
public class a extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    private Context f11203g;

    /* renamed from: h  reason: collision with root package name */
    private List<com.jingdong.app.mall.m.b.a> f11204h;

    /* renamed from: com.jingdong.app.mall.m.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0346a {
        TextView a;

        C0346a(a aVar) {
        }
    }

    public a(Context context, List<com.jingdong.app.mall.m.b.a> list) {
        this.f11203g = context;
        this.f11204h = list;
    }

    private String a(String str) {
        if (str.trim().length() > 8) {
            return ((Object) str.subSequence(0, 7)) + "...";
        }
        return str;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f11204h.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.f11204h.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        C0346a c0346a;
        com.jingdong.app.mall.m.b.a aVar = (com.jingdong.app.mall.m.b.a) getItem(i2);
        if (view == null) {
            view = View.inflate(this.f11203g, R.layout.feedback_msg_gridview, null);
            c0346a = new C0346a(this);
            c0346a.a = (TextView) view.findViewById(R.id.txt_feedback);
            view.setTag(c0346a);
        } else {
            c0346a = (C0346a) view.getTag();
        }
        if (aVar.isSelect()) {
            c0346a.a.setBackgroundResource(R.drawable.feedback_msg_gv_shape_checked);
            c0346a.a.setTextColor(Color.parseColor("#F23030"));
        } else {
            c0346a.a.setBackgroundResource(R.drawable.feedback_msg_gv_shape);
            c0346a.a.setTextColor(Color.parseColor("#666666"));
        }
        c0346a.a.setText(a(this.f11204h.get(i2).getTabValue()));
        return view;
    }
}
