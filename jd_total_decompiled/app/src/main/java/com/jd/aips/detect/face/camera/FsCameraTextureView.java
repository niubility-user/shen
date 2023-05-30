package com.jd.aips.detect.face.camera;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class FsCameraTextureView extends TextureView {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ int f1631h = 0;
    private FsCameraProxy a;
    private float b;

    /* renamed from: c  reason: collision with root package name */
    private int f1632c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    int f1633e;

    /* renamed from: f  reason: collision with root package name */
    int f1634f;

    /* renamed from: g  reason: collision with root package name */
    private final TextureView.SurfaceTextureListener f1635g;

    public FsCameraTextureView(Context context) {
        this(context, null);
    }

    public Matrix calculateTextureTransform(float f2, float f3) {
        float f4;
        int i2;
        float f5;
        int i3 = this.a.getmdegrees();
        if (i3 != 0 && i3 != 180) {
            f4 = this.a.getmPreviewWidth();
            i2 = this.a.getmPreviewHeight();
        } else {
            f4 = this.a.getmPreviewHeight();
            i2 = this.a.getmPreviewWidth();
        }
        float f6 = f2 / f3;
        float f7 = f4 / i2;
        float f8 = 1.0f;
        if (f6 < f7) {
            f5 = f7 / f6;
        } else {
            f8 = f6 / f7;
            f5 = 1.0f;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f5, f8);
        matrix.postTranslate((f2 - (f5 * f2)) / 2.0f, (f3 - (f8 * f3)) / 2.0f);
        return matrix;
    }

    public FsCameraProxy getCameraProxy() {
        return this.a;
    }

    @Override // android.view.TextureView, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f1633e = i2;
        this.f1634f = i3;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            this.a.focusOnPoint((int) motionEvent.getX(), (int) motionEvent.getY(), getWidth(), getHeight());
            return true;
        } else if (motionEvent.getPointerCount() > 1) {
            return true;
        } else {
            int action = motionEvent.getAction() & 255;
            if (action == 2) {
                float a = a(motionEvent);
                float f2 = this.b;
                if (a > f2) {
                    this.a.handleZoom(true);
                } else if (a < f2) {
                    this.a.handleZoom(false);
                }
                this.b = a;
            } else if (action == 5) {
                this.b = a(motionEvent);
            }
            return super.onTouchEvent(motionEvent);
        }
    }

    public FsCameraTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FsCameraTextureView);
        this.f1632c = obtainStyledAttributes.getInteger(R.styleable.FsCameraTextureView_previewAdapteSize, 0);
        this.d = obtainStyledAttributes.getInteger(R.styleable.FsCameraTextureView_addCallbackBufferEnable, 1);
        obtainStyledAttributes.recycle();
    }

    public FsCameraTextureView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1632c = 0;
        this.d = 1;
        this.f1633e = 0;
        this.f1634f = 0;
        this.f1635g = new TextureView.SurfaceTextureListener() { // from class: com.jd.aips.detect.face.camera.FsCameraTextureView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i3, int i4) {
                String str = "onSurfaceTextureAvailable. width: " + i3 + ", height: " + i4;
                FsCameraTextureView fsCameraTextureView = FsCameraTextureView.this;
                int i5 = FsCameraTextureView.f1631h;
                fsCameraTextureView.getClass();
                FsCameraTextureView.this.getClass();
                FsCameraTextureView.this.a.openCamera(i3, i4);
                String str2 = "onSurfaceTextureAvailable SurfaceTexture. width: " + FsCameraTextureView.this.f1633e + ", height: " + FsCameraTextureView.this.f1634f;
                FsCameraTextureView.this.setTransform(FsCameraTextureView.this.calculateTextureTransform(r5.f1633e, r5.f1634f));
                FsCameraTextureView.this.a.startPreview(surfaceTexture);
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                FsCameraTextureView.this.a.releaseCamera();
                return false;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i3, int i4) {
                String str = "onSurfaceTextureSizeChanged. width: " + i3 + ", height: " + i4;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        };
        a(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setSurfaceTextureListener(this.f1635g);
        this.a = new FsCameraProxy((Activity) context, this.f1632c, this.d);
    }

    private static float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }
}
