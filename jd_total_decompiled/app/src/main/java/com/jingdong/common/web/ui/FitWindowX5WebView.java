package com.jingdong.common.web.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.Map;

/* loaded from: classes12.dex */
public class FitWindowX5WebView extends X5WebView {
    public FitWindowX5WebView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, new ViewGroup.LayoutParams(DPIUtil.getWidth(), DPIUtil.getHeight()));
    }

    public FitWindowX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FitWindowX5WebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public FitWindowX5WebView(Context context, AttributeSet attributeSet, int i2, boolean z) {
        super(context, attributeSet, i2, z);
    }

    public FitWindowX5WebView(Context context, AttributeSet attributeSet, int i2, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i2, map, z);
    }
}
