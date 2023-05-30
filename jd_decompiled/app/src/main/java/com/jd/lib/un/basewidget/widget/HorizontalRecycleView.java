package com.jd.lib.un.basewidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes16.dex */
public class HorizontalRecycleView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private RecyclerView f5683g;

    public HorizontalRecycleView(@NonNull Context context) {
        this(context, null);
    }

    private void a() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        this.f5683g = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.f5683g.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        addView(this.f5683g);
    }

    private void b() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.f5683g.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (layoutParams2 == null) {
            this.f5683g.setLayoutParams(new ViewGroup.LayoutParams(layoutParams.width, layoutParams.height));
            return;
        }
        int i2 = layoutParams.width;
        if (i2 == layoutParams2.width && layoutParams.height == layoutParams2.height) {
            return;
        }
        layoutParams2.width = i2;
        layoutParams2.height = layoutParams.height;
        this.f5683g.setLayoutParams(layoutParams2);
    }

    public void c(RecyclerView.Adapter adapter) {
        this.f5683g.setAdapter(adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        b();
    }

    public HorizontalRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
