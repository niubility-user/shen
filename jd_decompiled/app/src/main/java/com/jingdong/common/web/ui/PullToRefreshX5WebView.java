package com.jingdong.common.web.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebViewPool;
import com.jingdong.corelib.utils.Log;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class PullToRefreshX5WebView extends PullToRefreshBase<X5WebView> {
    private static final String TAG = "PullToRefreshJDWebView";
    private long createWebViewTime;

    public PullToRefreshX5WebView(Context context) {
        super(context);
    }

    private boolean canGetWebViewFromPool(Context context) {
        Intent intent;
        HybridOfflineLoader fetchOfflineLoader;
        return (context instanceof Activity) && (intent = ((Activity) context).getIntent()) != null && (fetchOfflineLoader = WebOfflineLoaderManager.fetchOfflineLoader(intent.getStringExtra(MBaseKeyNames.KEY_OFFLINE_ID))) != null && fetchOfflineLoader.hasOfflineConfig();
    }

    public JDJSONObject getInitPerformance() {
        JDJSONObject jDJSONObject = new JDJSONObject(2);
        jDJSONObject.put("webView", (Object) Long.valueOf(this.createWebViewTime));
        return jDJSONObject;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        if (Log.D) {
            Log.d(TAG, "isReadyForPullEnd   contentHeight: " + (((X5WebView) this.mRefreshableView).getContentHeight() * ((X5WebView) this.mRefreshableView).getScaleY()) + "\ncontent bottom: " + ((X5WebView) this.mRefreshableView).getScrollY() + ((X5WebView) this.mRefreshableView).getHeight());
        }
        return ((float) (((X5WebView) this.mRefreshableView).getWebScrollY() + ((X5WebView) this.mRefreshableView).getHeight())) == ((float) ((X5WebView) this.mRefreshableView).getContentHeight()) * ((X5WebView) this.mRefreshableView).getScale();
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        if (Log.D) {
            Log.d(TAG, "isReadyForPullStart   " + ((X5WebView) this.mRefreshableView).getWebScrollY());
        }
        return ((X5WebView) this.mRefreshableView).getWebScrollY() == 0;
    }

    public PullToRefreshX5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public X5WebView createRefreshableView(Context context, AttributeSet attributeSet) {
        X5WebView x5WebView;
        long currentTimeMillis = System.currentTimeMillis();
        if (canGetWebViewFromPool(context)) {
            x5WebView = WebViewPool.getAvlWebView(context);
        } else {
            X5WebView x5WebView2 = new X5WebView(System.currentTimeMillis(), WebView.mWebViewCreated, context);
            Log.d("WebViewPool", "create new webview(" + x5WebView2.hashCode() + ") but no re-use");
            x5WebView = x5WebView2;
        }
        this.createWebViewTime = System.currentTimeMillis() - currentTimeMillis;
        return x5WebView;
    }

    public PullToRefreshX5WebView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshX5WebView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
