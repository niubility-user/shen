package com.jd.aips.common.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.jd.aips.common.utils.ScreenUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class JDCNCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Context a;
    private SurfaceHolder b;

    /* renamed from: c  reason: collision with root package name */
    private int f1565c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1566e;

    /* renamed from: f  reason: collision with root package name */
    private JDCNSurfaceViewCallback f1567f;

    /* renamed from: g  reason: collision with root package name */
    private MainHandler f1568g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class MainHandler extends Handler {
        WeakReference<JDCNCameraSurfaceView> a;

        MainHandler(JDCNCameraSurfaceView jDCNCameraSurfaceView) {
            this.a = new WeakReference<>(jDCNCameraSurfaceView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            JDCNCameraSurfaceView jDCNCameraSurfaceView = this.a.get();
            if (jDCNCameraSurfaceView == null || message.what != 17) {
                return;
            }
            JDCNCameraSurfaceView.a(jDCNCameraSurfaceView, (Camera.Size) message.obj);
        }
    }

    public JDCNCameraSurfaceView(Context context) {
        super(context);
        this.f1565c = -1;
        this.d = -1;
        a(context);
    }

    private void a(Context context) {
        this.f1568g = new MainHandler(this);
        this.f1566e = false;
        this.a = context;
        SurfaceHolder holder = getHolder();
        this.b = holder;
        holder.addCallback(this);
        this.b.setKeepScreenOn(true);
        this.b.setType(3);
    }

    public void addCallback() {
        SurfaceHolder surfaceHolder = this.b;
        if (surfaceHolder != null) {
            surfaceHolder.addCallback(this);
        }
    }

    public SurfaceHolder getSurfaceHolder() {
        return getHolder();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5 = this.f1565c;
        if (-1 != i5 && -1 != (i4 = this.d)) {
            setMeasuredDimension(i5, i4);
        } else {
            super.onMeasure(i2, i3);
        }
    }

    public void onStart() {
        if (this.f1566e) {
            return;
        }
        addCallback();
        this.f1566e = true;
    }

    public void onStop() {
        this.f1566e = false;
    }

    public void resizeSurface(Camera.Size size) {
        MainHandler mainHandler = this.f1568g;
        mainHandler.sendMessage(mainHandler.obtainMessage(17, size));
    }

    public void setPreviewSelfCallback(JDCNSurfaceViewCallback jDCNSurfaceViewCallback) {
        this.f1567f = jDCNSurfaceViewCallback;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.f1567f;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewChanged(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.f1567f;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewCreated(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        surfaceHolder.removeCallback(this);
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.f1567f;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewDestoryed();
        }
    }

    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1565c = -1;
        this.d = -1;
        a(context);
    }

    static void a(JDCNCameraSurfaceView jDCNCameraSurfaceView, Camera.Size size) {
        float f2;
        float f3;
        if (size == null) {
            return;
        }
        int[] realScreenWidthHeight = ScreenUtil.getRealScreenWidthHeight(jDCNCameraSurfaceView.a);
        float f4 = size.height;
        float f5 = f4 / realScreenWidthHeight[0];
        float f6 = size.width;
        float f7 = f6 / realScreenWidthHeight[1];
        if (f5 > f7) {
            f3 = f6 / f7;
            f2 = f4 / f7;
        } else {
            f2 = f4 / f5;
            f3 = f6 / f5;
        }
        int i2 = (int) f2;
        int i3 = (int) f3;
        jDCNCameraSurfaceView.f1565c = i2;
        jDCNCameraSurfaceView.d = i3;
        jDCNCameraSurfaceView.getHolder().setFixedSize(i2, i3);
        jDCNCameraSurfaceView.requestLayout();
        jDCNCameraSurfaceView.invalidate();
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = jDCNCameraSurfaceView.f1567f;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.previewBound(size.width, size.height);
        }
    }

    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1565c = -1;
        this.d = -1;
        a(context);
    }

    @TargetApi(21)
    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f1565c = -1;
        this.d = -1;
        a(context);
    }
}
