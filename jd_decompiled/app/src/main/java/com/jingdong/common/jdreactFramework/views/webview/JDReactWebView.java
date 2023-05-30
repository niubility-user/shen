package com.jingdong.common.jdreactFramework.views.webview;

import android.content.Context;
import android.util.AttributeSet;
import com.jingdong.app.mall.R;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.JDWebViewBuilder;

/* loaded from: classes5.dex */
public class JDReactWebView extends JDWebView {
    public JDReactWebView(Context context) {
        super(context);
    }

    @Override // com.jingdong.common.web.ui.JDWebView
    protected int getInflateLayoutRes() {
        return R.layout.jdreact_x5_webview;
    }

    public JDReactWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDReactWebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public JDReactWebView(Context context, JDWebViewBuilder jDWebViewBuilder) {
        super(context, jDWebViewBuilder);
    }
}
