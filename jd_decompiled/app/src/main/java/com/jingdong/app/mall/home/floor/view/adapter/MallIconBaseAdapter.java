package com.jingdong.app.mall.home.floor.view.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jingdong.app.mall.home.r.f.a.o;

/* loaded from: classes4.dex */
public abstract class MallIconBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected int a = 0;
    protected o b;

    /* renamed from: c  reason: collision with root package name */
    protected Context f9707c;

    public MallIconBaseAdapter(o oVar, Context context) {
        this.b = oVar;
        this.f9707c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.g0(this.a);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2;
    }

    public boolean h() {
        return this.b.I0();
    }

    public void l(int i2) {
        this.a = i2;
    }
}
