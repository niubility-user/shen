package com.jingdong.app.mall.bundle.smile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class SimpleyBaseAdapter<T> extends BaseAdapter {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleyBaseAdapter(Context context, List<T> list) {
    }

    @Override // android.widget.Adapter
    public abstract int getCount();

    @Override // android.widget.Adapter
    public abstract Object getItem(int i2);

    @Override // android.widget.Adapter
    public abstract long getItemId(int i2);

    @Override // android.widget.Adapter
    public abstract View getView(int i2, View view, ViewGroup viewGroup);

    public abstract void notifyDataSetChanged(List<T> list);
}
