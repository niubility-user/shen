package com.jingdong.common.listui;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "WrapContentLinearLayout";

    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public WrapContentLinearLayoutManager(Context context, int i2, boolean z) {
        super(context, i2, z);
    }

    public WrapContentLinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
