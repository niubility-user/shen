package com.jingdong.common.web.ui;

import android.content.Context;
import android.util.AttributeSet;
import com.jingdong.common.web.R;

/* loaded from: classes12.dex */
public class JShopX5WebView extends JDWebView {
    public JShopX5WebView(Context context) {
        super(context);
    }

    @Override // com.jingdong.common.web.ui.JDWebView
    protected int getInflateLayoutRes() {
        return R.layout.jshop_webview;
    }

    public JShopX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JShopX5WebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
