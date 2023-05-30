package com.jd.manto.center.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

/* loaded from: classes17.dex */
public abstract class a<T> extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    protected Context f6598g;

    /* renamed from: h  reason: collision with root package name */
    private int f6599h;

    /* renamed from: i  reason: collision with root package name */
    protected List<T> f6600i;

    public a(Context context, List<T> list, int i2) {
        this.f6598g = context;
        this.f6600i = list;
        this.f6599h = i2;
    }

    private b b(View view, ViewGroup viewGroup, int i2) {
        return b.a(this.f6598g, view, viewGroup, this.f6599h, i2);
    }

    public abstract void a(b bVar, T t);

    public void c(List<T> list) {
        if (this.f6600i == null || list == null || list.size() <= 0) {
            return;
        }
        this.f6600i.clear();
        this.f6600i.addAll(list);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.f6600i;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i2) {
        List<T> list = this.f6600i;
        if (list == null) {
            return null;
        }
        return list.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        b b = b(view, viewGroup, i2);
        a(b, getItem(i2));
        return b.b();
    }
}
