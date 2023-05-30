package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.o.a.e;

/* loaded from: classes4.dex */
public class HorizontalRecyclerView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    private LinearLayoutManager f10081g;

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.f10081g = linearLayoutManager;
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.f10081g.setOrientation(0);
        setLayoutManager(this.f10081g);
        e.a(this);
    }
}
