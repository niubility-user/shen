package com.jingdong.common.web.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class SysBug5497Workaround extends BaseWebComponent {
    private final String TAG;
    private ViewTreeObserver.OnGlobalLayoutListener listener;
    private View mChildOfContent;
    private Handler mHandler;
    private Runnable noSoftInputNotifier;
    private boolean softInputPopupOnce;
    private int usableHeightPrevious;

    public SysBug5497Workaround(IWebUiBinder iWebUiBinder, Activity activity) {
        super(iWebUiBinder);
        this.TAG = "WebView-" + getClass().getSimpleName();
        this.softInputPopupOnce = false;
        this.listener = null;
        this.mHandler = null;
        this.noSoftInputNotifier = null;
        try {
            this.mHandler = new Handler(Looper.getMainLooper());
            View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            this.mChildOfContent = childAt;
            ViewTreeObserver viewTreeObserver = childAt.getViewTreeObserver();
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.web.util.SysBug5497Workaround.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    SysBug5497Workaround.this.possiblyResizeChildOfContent();
                }
            };
            this.listener = onGlobalLayoutListener;
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage(), e2);
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWeb(int i2) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        int px2dip = DPIUtil.px2dip(i2);
        Log.d(this.TAG, "\u901a\u77e5H5\u9875\u9762\u65b0\u9ad8\u5ea6 = " + px2dip + "dp");
        this.webUiBinder.getJdWebView().injectJs("javascript:window.AndroidSystemViewNewHeight && AndroidSystemViewNewHeight(" + px2dip + ");");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005d A[Catch: Exception -> 0x00e7, TryCatch #0 {Exception -> 0x00e7, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x0019, B:8:0x001f, B:10:0x002b, B:12:0x0037, B:15:0x0059, B:17:0x005d, B:21:0x007b, B:25:0x00bc, B:34:0x00e4, B:26:0x00c1, B:28:0x00c5, B:30:0x00c9, B:32:0x00cd, B:33:0x00d4), top: B:39:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c1 A[Catch: Exception -> 0x00e7, TryCatch #0 {Exception -> 0x00e7, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x0019, B:8:0x001f, B:10:0x002b, B:12:0x0037, B:15:0x0059, B:17:0x005d, B:21:0x007b, B:25:0x00bc, B:34:0x00e4, B:26:0x00c1, B:28:0x00c5, B:30:0x00c9, B:32:0x00cd, B:33:0x00d4), top: B:39:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void possiblyResizeChildOfContent() {
        boolean z;
        int i2;
        try {
            int computeUsableHeight = computeUsableHeight();
            if (computeUsableHeight != this.usableHeightPrevious) {
                int height = this.mChildOfContent.getRootView().getHeight();
                int i3 = height - computeUsableHeight;
                IWebUiBinder iWebUiBinder = this.webUiBinder;
                int i4 = 0;
                if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
                    z = false;
                } else {
                    z = this.webUiBinder.getJdWebView().isTopBarGone();
                    if (!z && this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
                        i2 = this.webUiBinder.getJdWebView().getNavigatorHolder().getNaviHeight();
                        z = this.webUiBinder.getJdWebView().getNavigatorHolder().isNaviImmersive();
                        if (i3 <= height / 4) {
                            this.softInputPopupOnce = true;
                            String str = this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("\u8f6f\u952e\u76d8-\u5f39\u51fa, \u5185\u5bb9+\u72b6\u6001\u680f\u9ad8\u5ea6=");
                            int i5 = height - i3;
                            sb.append(i5);
                            sb.append("px, \u5bfc\u822a\u680f\u5360\u9ad8=");
                            sb.append(z ? 0 : i2);
                            sb.append("px");
                            Log.d(str, sb.toString());
                            Log.d(this.TAG, "X5WebView\u9ad8\u5ea6 = " + DPIUtil.px2dip(this.webUiBinder.getJdWebView().getWebView().getHeight()) + "dp");
                            if (!z) {
                                i4 = i2;
                            }
                            notifyWeb(i5 - i4);
                        } else if (this.softInputPopupOnce && this.mHandler != null) {
                            if (this.noSoftInputNotifier == null) {
                                this.noSoftInputNotifier = new Runnable() { // from class: com.jingdong.common.web.util.SysBug5497Workaround.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        X5WebView webView = (((BaseWebComponent) SysBug5497Workaround.this).webUiBinder == null || ((BaseWebComponent) SysBug5497Workaround.this).webUiBinder.getJdWebView() == null) ? null : ((BaseWebComponent) SysBug5497Workaround.this).webUiBinder.getJdWebView().getWebView();
                                        if (webView != null) {
                                            Log.d(SysBug5497Workaround.this.TAG, "\u8f6f\u952e\u76d8-\u6536\u8d77, X5WebView\u9ad8\u5ea6 = " + DPIUtil.px2dip(webView.getMeasuredHeight()) + "dp");
                                            SysBug5497Workaround.this.notifyWeb(webView.getMeasuredHeight());
                                        }
                                    }
                                };
                            }
                            this.mHandler.removeCallbacksAndMessages(this.noSoftInputNotifier);
                            this.mHandler.postDelayed(this.noSoftInputNotifier, 300L);
                        }
                        this.usableHeightPrevious = computeUsableHeight;
                    }
                }
                i2 = 0;
                if (i3 <= height / 4) {
                }
                this.usableHeightPrevious = computeUsableHeight;
            }
        } catch (Exception e2) {
            Log.e(this.TAG, e2.getMessage(), e2);
        }
    }

    public void onDestroy() {
        View view;
        if (BaseInfo.getAndroidSDKVersion() >= 16 && this.listener != null && (view = this.mChildOfContent) != null && view.getViewTreeObserver() != null) {
            this.mChildOfContent.getViewTreeObserver().removeOnGlobalLayoutListener(this.listener);
        }
        if (this.noSoftInputNotifier != null) {
            this.noSoftInputNotifier = null;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
    }

    public void setSoftInputPopupOnce(boolean z) {
        this.softInputPopupOnce = z;
    }
}
