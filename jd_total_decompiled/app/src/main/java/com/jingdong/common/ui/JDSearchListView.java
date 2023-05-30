package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class JDSearchListView extends ListView {
    private static final String TAG = "JDSearchListView";

    public JDSearchListView(Context context) {
        super(context);
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    protected void layoutChildren() {
        try {
            super.layoutChildren();
        } catch (Exception e2) {
            UnLog.e(TAG, e2.getMessage());
        }
    }

    public JDSearchListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDSearchListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
