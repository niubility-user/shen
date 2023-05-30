package com.jingdong.manto;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import androidx.annotation.Keep;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JWebType;

/* loaded from: classes15.dex */
public class BaseWebView extends JDWebView {
    private View.OnTouchListener a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    public ViewGroup f12988c;
    private Handler d;

    /* renamed from: e  reason: collision with root package name */
    public c f12989e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f12990f;

    /* renamed from: g  reason: collision with root package name */
    private float f12991g;

    /* renamed from: h  reason: collision with root package name */
    private float f12992h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ValueCallback b;

        a(String str, ValueCallback valueCallback) {
            this.a = str;
            this.b = valueCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            if (Build.VERSION.SDK_INT >= 19 || BaseWebView.this.getWebType() == JWebType.MV_TYPE_X5_X5) {
                BaseWebView.super.evaluateJavascript(this.a, this.b);
                return;
            }
            try {
                if (this.a.startsWith("javascript:")) {
                    str = this.a;
                } else {
                    str = "javascript:" + this.a;
                }
                BaseWebView.super.loadUrl(str);
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements View.OnTouchListener {
        b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (BaseWebView.this.getWebType() == JWebType.MV_TYPE_X5_X5) {
                int action = motionEvent.getAction();
                if (action == 1 || action == 3) {
                    BaseWebView.this.requestDisallowInterceptTouchEvent(false);
                } else {
                    BaseWebView.this.requestDisallowInterceptTouchEvent(true);
                }
            }
            if (BaseWebView.this.a != null) {
                return BaseWebView.this.a.onTouch(view, motionEvent);
            }
            return false;
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a(int i2, int i3, int i4, int i5);
    }

    public BaseWebView(Context context) {
        this(context, null);
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12990f = true;
        this.b = false;
        this.f12989e = null;
        b();
        this.d = new Handler(Looper.getMainLooper());
    }

    public void a(String str, ValueCallback<String> valueCallback) {
        a aVar = new a(str, valueCallback);
        if (MantoThreadUtils.isMainThread()) {
            aVar.run();
        } else {
            this.d.post(aVar);
        }
    }

    @TargetApi(11)
    public final void b() {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                super.removeJavascriptInterface("searchBoxJavaBridge_");
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    @TargetApi(9)
    public void computeScroll() {
        super.super_computeScroll();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.super_dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.sdk.jweb.JDWebView, com.jingdong.sdk.jweb.JWebView
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a(str, valueCallback);
    }

    @Override // com.jingdong.sdk.jweb.JDWebView, com.jingdong.sdk.jweb.JWebView
    public void loadUrl(String str) {
        super.loadUrl(str);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.super_onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    @TargetApi(9)
    public void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        super.super_onOverScrolled(i2, i3, z, z2);
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.super_onScrollChanged(i2, i3, i4, i5);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean super_onTouchEvent = super_onTouchEvent(motionEvent);
        int i2 = action & 255;
        if (i2 == 0) {
            this.b = false;
            this.f12991g = motionEvent.getX();
            this.f12992h = motionEvent.getY();
        } else if (i2 == 2) {
            if (!this.f12990f) {
                float x = motionEvent.getX();
                float y = motionEvent.getY() - this.f12992h;
                float f2 = x - this.f12991g;
                if (Math.abs(f2) <= 1.0f && Math.abs(y) >= Math.abs(f2)) {
                    return false;
                }
            }
            ViewGroup viewGroup = this.f12988c;
            if (viewGroup != null) {
                if (this.b) {
                    viewGroup.requestDisallowInterceptTouchEvent(false);
                } else {
                    viewGroup.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        return !super_onTouchEvent ? super_onTouchEvent : !this.b;
    }

    @Override // com.jingdong.sdk.jweb.JDWebView
    public void onWebViewScrollChanged(int i2, int i3, int i4, int i5) {
        super.onWebViewScrollChanged(i2, i3, i4, i5);
        c cVar = this.f12989e;
        if (cVar != null) {
            cVar.a(i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    @TargetApi(9)
    public final boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        if (this.f12990f || Math.abs(i2) > 1.0f || Math.abs(i3) < Math.abs(i2)) {
            boolean super_overScrollBy = super_overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
            if (i5 < 0 || (i5 == 0 && i3 < 0)) {
                this.b = true;
            }
            return super_overScrollBy;
        }
        return false;
    }

    public void setCanScroll(boolean z) {
        this.f12990f = z;
    }

    @Keep
    public final void setOnTouchListener() {
        super.setOnTouchListener(new b());
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.a = onTouchListener;
    }
}
