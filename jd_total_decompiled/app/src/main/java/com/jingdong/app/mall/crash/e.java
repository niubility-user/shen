package com.jingdong.app.mall.crash;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.ImageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class e extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    private List<d> f8338g;

    /* loaded from: classes3.dex */
    public static class a {
        public TextView a;
        public TextView b;

        /* renamed from: c  reason: collision with root package name */
        public TextView f8339c;
    }

    public void a(List<d> list) {
        this.f8338g = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<d> list = this.f8338g;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<d> list = this.f8338g;
        if (list != null) {
            return list.get(i2);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = ImageUtil.inflate(R.layout.activity_callontime_item, null);
            aVar = new a();
            aVar.b = (TextView) view.findViewById(R.id.tv_classname);
            aVar.a = (TextView) view.findViewById(R.id.tv_startTime);
            aVar.f8339c = (TextView) view.findViewById(R.id.tv_time);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        d dVar = this.f8338g.get(i2);
        String str = dVar.b;
        if (!TextUtils.isEmpty(str)) {
            str = str.substring(str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1, str.length());
        }
        aVar.b.setText(str);
        aVar.a.setText(dVar.a + "ms");
        aVar.f8339c.setText(dVar.f8337c);
        return view;
    }
}
