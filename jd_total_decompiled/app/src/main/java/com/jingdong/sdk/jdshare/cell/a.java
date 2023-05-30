package com.jingdong.sdk.jdshare.cell;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.appshare.R;
import com.jingdong.common.utils.ImageUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class a extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    private List<com.jingdong.c.a.c.b> f14995g;

    /* renamed from: com.jingdong.sdk.jdshare.cell.a$a  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    static class C0720a {
        ImageView a;
        TextView b;

        /* renamed from: c  reason: collision with root package name */
        TextView f14996c;

        C0720a() {
        }
    }

    public a(Context context, List<com.jingdong.c.a.c.b> list) {
        this.f14995g = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<com.jingdong.c.a.c.b> list = this.f14995g;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        if (i2 < this.f14995g.size()) {
            return this.f14995g.get(i2);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        C0720a c0720a = new C0720a();
        if (view == null) {
            view = ImageUtil.inflate(R.layout.share_layout_item, null);
            c0720a.a = (SimpleDraweeView) view.findViewById(R.id.share_layout_item_img);
            c0720a.b = (TextView) view.findViewById(R.id.share_layout_item_text);
            c0720a.f14996c = (TextView) view.findViewById(R.id.share_key_superScript);
            view.setTag(c0720a);
        } else {
            c0720a = (C0720a) view.getTag();
        }
        if (i2 < this.f14995g.size()) {
            com.jingdong.c.a.c.b bVar = this.f14995g.get(i2);
            c0720a.a.setBackgroundResource(bVar.d);
            c0720a.b.setText(bVar.b);
            c0720a.f14996c.setVisibility(bVar.f12266c ? 0 : 8);
        }
        return view;
    }
}
