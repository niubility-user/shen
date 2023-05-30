package com.jd.lib.cashier.sdk.core.commonfloor.viewholder;

import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashSet;
import java.util.LinkedHashSet;

/* loaded from: classes14.dex */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: g  reason: collision with root package name */
    private final SparseArray<View> f2949g;

    /* renamed from: h  reason: collision with root package name */
    public View f2950h;

    public BaseViewHolder(View view) {
        super(view);
        this.f2949g = new SparseArray<>();
        new LinkedHashSet();
        new LinkedHashSet();
        new HashSet();
        this.f2950h = view;
    }

    public View getConvertView() {
        return this.f2950h;
    }

    public <T extends View> T getView(int i2) {
        T t = (T) this.f2949g.get(i2);
        if (t == null) {
            T t2 = (T) this.f2950h.findViewById(i2);
            this.f2949g.put(i2, t2);
            return t2;
        }
        return t;
    }
}
