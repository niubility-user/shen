package com.jdjr.risk.jdcn.common.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.jdjr.risk.jdcn.common.utils.JDCNLogUtils;
import com.jdjr.risk.jdcn.common.utils.JDCNScreenUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class JDCNCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int MSG_RESIZE = 17;
    private Context mContext;
    private MainHandler mHandler;
    private int mHeight;
    private JDCNSurfaceViewCallback mPreviewSelfCallback;
    private boolean mStarted;
    private SurfaceHolder mSurfaceHolder;
    private int mWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public static class MainHandler extends Handler {
        WeakReference<JDCNCameraSurfaceView> mHost;

        MainHandler(JDCNCameraSurfaceView jDCNCameraSurfaceView) {
            this.mHost = new WeakReference<>(jDCNCameraSurfaceView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            JDCNCameraSurfaceView jDCNCameraSurfaceView = this.mHost.get();
            if (jDCNCameraSurfaceView == null || message.what != 17) {
                return;
            }
            jDCNCameraSurfaceView._resizeSurface((Camera.Size) message.obj);
        }
    }

    public JDCNCameraSurfaceView(Context context) {
        super(context);
        this.mWidth = -1;
        this.mHeight = -1;
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _resizeSurface(Camera.Size size) {
        float f2;
        float f3;
        if (size == null) {
            return;
        }
        int[] realScreenWidthHeight = JDCNScreenUtils.getRealScreenWidthHeight(this.mContext);
        int i2 = size.height;
        float f4 = i2 / realScreenWidthHeight[0];
        int i3 = size.width;
        float f5 = i3 / realScreenWidthHeight[1];
        if (f4 > f5) {
            f3 = i3 / f5;
            f2 = i2 / f5;
        } else {
            f2 = i2 / f4;
            f3 = i3 / f4;
        }
        resize((int) f2, (int) f3);
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.mPreviewSelfCallback;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.previewBound(size.width, size.height);
        }
    }

    private void init(Context context) {
        this.mHandler = new MainHandler(this);
        this.mStarted = false;
        this.mContext = context;
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        this.mSurfaceHolder.setKeepScreenOn(true);
        this.mSurfaceHolder.setType(3);
    }

    private void resize(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
        getHolder().setFixedSize(i2, i3);
        requestLayout();
        invalidate();
    }

    public void addCallback() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
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
        int i5 = this.mWidth;
        if (-1 != i5 && -1 != (i4 = this.mHeight)) {
            setMeasuredDimension(i5, i4);
        } else {
            super.onMeasure(i2, i3);
        }
    }

    public void onStart() {
        if (this.mStarted) {
            return;
        }
        addCallback();
        this.mStarted = true;
    }

    public void onStop() {
        this.mStarted = false;
    }

    public void resizeSurface(Camera.Size size) {
        MainHandler mainHandler = this.mHandler;
        mainHandler.sendMessage(mainHandler.obtainMessage(17, size));
    }

    public void setPreviewSelfCallback(JDCNSurfaceViewCallback jDCNSurfaceViewCallback) {
        this.mPreviewSelfCallback = jDCNSurfaceViewCallback;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.mPreviewSelfCallback;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewChanged(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.mPreviewSelfCallback;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewCreated(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        JDCNLogUtils.d("gggl", "surfaceDestroyed!!!");
        surfaceHolder.removeCallback(this);
        JDCNSurfaceViewCallback jDCNSurfaceViewCallback = this.mPreviewSelfCallback;
        if (jDCNSurfaceViewCallback != null) {
            jDCNSurfaceViewCallback.onSurfaceViewDestoryed();
        }
    }

    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWidth = -1;
        this.mHeight = -1;
        init(context);
    }

    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mWidth = -1;
        this.mHeight = -1;
        init(context);
    }

    @TargetApi(21)
    public JDCNCameraSurfaceView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mWidth = -1;
        this.mHeight = -1;
        init(context);
    }
}
