package com.jd.libs.xwidget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.Surface;
import android.webkit.ValueCallback;
import com.jd.xbridge.base.IBridgeCallback;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidget;
import com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient;
import java.util.Map;

/* loaded from: classes16.dex */
public abstract class b implements IEmbeddedWidgetClient {
    private final String Tag = "JDHybridWidgetClient";
    private IEmbeddedWidget iEmbeddedWidget;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6232g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IBridgeCallback f6233h;

        /* renamed from: com.jd.libs.xwidget.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0172a implements ValueCallback<String> {
            C0172a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public void onReceiveValue(String str) {
                IBridgeCallback iBridgeCallback = a.this.f6233h;
                if (iBridgeCallback != null) {
                    iBridgeCallback.onSuccess(str);
                }
            }
        }

        a(String str, IBridgeCallback iBridgeCallback) {
            this.f6232g = str;
            this.f6233h = iBridgeCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.iEmbeddedWidget.evaluateJavascript(this.f6232g, new C0172a());
        }
    }

    public void callJS(String str, IBridgeCallback iBridgeCallback) {
        if (this.iEmbeddedWidget != null) {
            new Handler(Looper.getMainLooper()).post(new a(str, iBridgeCallback));
        }
    }

    public void callNative(String str, IBridgeCallback iBridgeCallback) {
    }

    public void getData(Context context, Map<String, String> map) {
    }

    public final void getIEmbeddedWidget(IEmbeddedWidget iEmbeddedWidget) {
        this.iEmbeddedWidget = iEmbeddedWidget;
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onActive() {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onDeactive() {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onDestroy() {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onRectChanged(Rect rect) {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onRequestRedraw() {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onSurfaceCreated(Surface surface) {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onSurfaceDestroyed(Surface surface) {
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.embeddedwidget.interfaces.IEmbeddedWidgetClient
    public void onVisibilityChanged(boolean z) {
    }
}
