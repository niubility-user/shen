package com.jingdong.manto.jsapi.webview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.jingdong.manto.BaseWebView;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.sdk.api.IWebview;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.MantoStatusBarUtil;

/* loaded from: classes15.dex */
public final class a {
    private View a;
    private int b;
    private ViewTreeObserver.OnGlobalLayoutListener d;

    /* renamed from: e  reason: collision with root package name */
    private Handler f13200e;

    /* renamed from: g  reason: collision with root package name */
    private View f13202g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f13203h;

    /* renamed from: i  reason: collision with root package name */
    private int f13204i;

    /* renamed from: c  reason: collision with root package name */
    private boolean f13199c = false;

    /* renamed from: f  reason: collision with root package name */
    private Runnable f13201f = null;

    /* renamed from: com.jingdong.manto.jsapi.webview.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class ViewTreeObserverOnGlobalLayoutListenerC0531a implements ViewTreeObserver.OnGlobalLayoutListener {
        ViewTreeObserverOnGlobalLayoutListenerC0531a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            a.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13205c;
        final /* synthetic */ int d;

        b(int i2, boolean z, int i3, int i4) {
            this.a = i2;
            this.b = z;
            this.f13205c = i3;
            this.d = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            View view = a.this.f13202g;
            if (view != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("\u8f6f\u952e\u76d8-\u6536\u8d77, \u5185\u5bb9+\u72b6\u6001\u680f\u9ad8\u5ea6=");
                sb.append(this.a);
                sb.append("px, \u5bfc\u822a\u680f\u5360\u9ad8=");
                sb.append(this.b ? 0 : this.f13205c);
                sb.append("px");
                sb.toString();
                String str = "\u8f6f\u952e\u76d8-\u6536\u8d77, webView = " + view.getMeasuredHeight() + "px";
                a.this.a((this.a - this.d) - (this.b ? 0 : this.f13205c));
            }
        }
    }

    public a(Activity activity, IMantoWebViewJS iMantoWebViewJS, boolean z) {
        View view;
        this.d = null;
        this.f13200e = null;
        this.f13203h = false;
        try {
            this.f13200e = new Handler(Looper.getMainLooper());
            this.a = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            String str = "uiRootLayout height:" + this.a.getHeight();
            ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
            ViewTreeObserverOnGlobalLayoutListenerC0531a viewTreeObserverOnGlobalLayoutListenerC0531a = new ViewTreeObserverOnGlobalLayoutListenerC0531a();
            this.d = viewTreeObserverOnGlobalLayoutListenerC0531a;
            viewTreeObserver.addOnGlobalLayoutListener(viewTreeObserverOnGlobalLayoutListenerC0531a);
            if (!(iMantoWebViewJS instanceof IWebview.IMantoWebViewInterface)) {
                view = iMantoWebViewJS instanceof BaseWebView ? (BaseWebView) iMantoWebViewJS : view;
                this.f13203h = z;
                this.f13204i = MantoStatusBarUtil.getStatusBarHeight(activity);
            }
            view = ((IWebview.IMantoWebViewInterface) iMantoWebViewJS).getView();
            this.f13202g = view;
            this.f13203h = z;
            this.f13204i = MantoStatusBarUtil.getStatusBarHeight(activity);
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    private int a() {
        Rect rect = new Rect();
        this.a.getWindowVisibleDisplayFrame(rect);
        return Build.VERSION.SDK_INT >= 19 ? (rect.bottom - rect.top) + this.f13204i : rect.bottom - rect.top;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        this.f13202g.getLayoutParams().height = i2;
        this.f13202g.requestLayout();
        String str = "updateHeight:" + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Handler handler;
        try {
            int a = a();
            if (a != this.b) {
                int height = this.a.getRootView().getHeight();
                int i2 = height - a;
                int a2 = com.jingdong.manto.utils.g.a(this.a.getContext()) + this.f13204i;
                boolean z = this.f13203h;
                if (i2 > height / 4) {
                    this.f13199c = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append("\u8f6f\u952e\u76d8-\u5f39\u51fa, \u5185\u5bb9+\u72b6\u6001\u680f\u9ad8\u5ea6=");
                    int i3 = height - i2;
                    sb.append(i3);
                    sb.append("px, \u5bfc\u822a\u680f\u5360\u9ad8=");
                    sb.append(z ? 0 : a2);
                    sb.append("px");
                    MantoLog.d("webviewC", sb.toString());
                    MantoLog.d("webviewC", "webView \u9ad8\u5ea6 = " + this.f13202g.getHeight() + "px");
                    if (z) {
                        a2 = 0;
                    }
                    a(i3 - a2);
                } else if (this.f13199c && (handler = this.f13200e) != null) {
                    if (this.f13201f == null) {
                        this.f13201f = new b(height, z, a2, i2);
                    }
                    handler.removeCallbacksAndMessages(this.f13201f);
                    this.f13200e.postDelayed(this.f13201f, 251L);
                }
                this.b = a;
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public void b() {
        View view;
        if (Build.VERSION.SDK_INT >= 16 && this.d != null && (view = this.a) != null && view.getViewTreeObserver() != null) {
            this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this.d);
        }
        if (this.f13201f != null) {
            this.f13201f = null;
        }
        Handler handler = this.f13200e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f13200e = null;
        }
    }
}
