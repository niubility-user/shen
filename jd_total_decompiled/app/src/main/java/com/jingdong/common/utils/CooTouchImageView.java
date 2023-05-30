package com.jingdong.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RootDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class CooTouchImageView extends SimpleDraweeView implements GenericDraweeHierarchy.ChangeImageListener {
    private static final float SUPER_MAX_MULTIPLIER = 1.25f;
    private static final float SUPER_MIN_MULTIPLIER = 0.75f;
    private static final String TAG = "TouchImageView";
    private Context context;
    private ZoomVariables delayedZoomVariables;
    private GestureDetector.OnDoubleTapListener doubleTapListener;
    private Fling fling;
    private boolean imageRenderedAtLeastOnce;

    /* renamed from: m  reason: collision with root package name */
    private float[] f12449m;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleDetector;
    private ImageView.ScaleType mScaleType;
    private float matchViewHeight;
    private float matchViewWidth;
    private Matrix matrix;
    private float maxScale;
    private float minScale;
    private float normalizedScale;
    private boolean onDrawReady;
    private float prevMatchViewHeight;
    private float prevMatchViewWidth;
    private Matrix prevMatrix;
    private int prevViewHeight;
    private int prevViewWidth;
    private State state;
    private float superMaxScale;
    private float superMinScale;
    private OnTouchImageViewListener touchImageViewListener;
    private OnTouchStateListener touchStateListener;
    private View.OnTouchListener userTouchListener;
    private int viewHeight;
    private int viewWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.utils.CooTouchImageView$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(9)
    /* loaded from: classes6.dex */
    public class CompatScroller {
        boolean isPreGingerbread;
        OverScroller overScroller;
        Scroller scroller;

        public CompatScroller(Context context) {
            if (Build.VERSION.SDK_INT < 9) {
                this.isPreGingerbread = true;
                this.scroller = new Scroller(context);
                return;
            }
            this.isPreGingerbread = false;
            this.overScroller = new OverScroller(context);
        }

        public boolean computeScrollOffset() {
            if (this.isPreGingerbread) {
                return this.scroller.computeScrollOffset();
            }
            this.overScroller.computeScrollOffset();
            return this.overScroller.computeScrollOffset();
        }

        public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if (this.isPreGingerbread) {
                this.scroller.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            } else {
                this.overScroller.fling(i2, i3, i4, i5, i6, i7, i8, i9);
            }
        }

        public void forceFinished(boolean z) {
            if (this.isPreGingerbread) {
                this.scroller.forceFinished(z);
            } else {
                this.overScroller.forceFinished(z);
            }
        }

        public int getCurrX() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrX();
            }
            return this.overScroller.getCurrX();
        }

        public int getCurrY() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrY();
            }
            return this.overScroller.getCurrY();
        }

        public boolean isFinished() {
            if (this.isPreGingerbread) {
                return this.scroller.isFinished();
            }
            return this.overScroller.isFinished();
        }
    }

    /* loaded from: classes6.dex */
    private class DoubleTapZoom implements Runnable {
        private static final float ZOOM_TIME = 500.0f;
        private float bitmapX;
        private float bitmapY;
        private PointF endTouch;
        private AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        private long startTime;
        private PointF startTouch;
        private float startZoom;
        private boolean stretchImageToSuper;
        private float targetZoom;

        DoubleTapZoom(float f2, float f3, float f4, boolean z) {
            CooTouchImageView.this.setState(State.ANIMATE_ZOOM);
            this.startTime = System.currentTimeMillis();
            this.startZoom = CooTouchImageView.this.normalizedScale;
            this.targetZoom = f2;
            this.stretchImageToSuper = z;
            PointF transformCoordTouchToBitmap = CooTouchImageView.this.transformCoordTouchToBitmap(f3, f4, false);
            float f5 = transformCoordTouchToBitmap.x;
            this.bitmapX = f5;
            float f6 = transformCoordTouchToBitmap.y;
            this.bitmapY = f6;
            this.startTouch = CooTouchImageView.this.transformCoordBitmapToTouch(f5, f6);
            this.endTouch = new PointF(CooTouchImageView.this.viewWidth / 2, CooTouchImageView.this.viewHeight / 2);
        }

        private double calculateDeltaScale(float f2) {
            float f3 = this.startZoom;
            double d = f3 + (f2 * (this.targetZoom - f3));
            double d2 = CooTouchImageView.this.normalizedScale;
            Double.isNaN(d);
            Double.isNaN(d2);
            return d / d2;
        }

        private float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startTime)) / ZOOM_TIME));
        }

        private void translateImageToCenterTouchPosition(float f2) {
            PointF pointF = this.startTouch;
            float f3 = pointF.x;
            PointF pointF2 = this.endTouch;
            float f4 = f3 + ((pointF2.x - f3) * f2);
            float f5 = pointF.y;
            float f6 = f5 + (f2 * (pointF2.y - f5));
            PointF transformCoordBitmapToTouch = CooTouchImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            CooTouchImageView.this.matrix.postTranslate(f4 - transformCoordBitmapToTouch.x, f6 - transformCoordBitmapToTouch.y);
        }

        @Override // java.lang.Runnable
        public void run() {
            float interpolate = interpolate();
            CooTouchImageView.this.scaleImage(calculateDeltaScale(interpolate), this.bitmapX, this.bitmapY, this.stretchImageToSuper);
            translateImageToCenterTouchPosition(interpolate);
            CooTouchImageView.this.fixScaleTrans();
            CooTouchImageView cooTouchImageView = CooTouchImageView.this;
            cooTouchImageView.setImageMatrix(cooTouchImageView.matrix);
            if (CooTouchImageView.this.touchImageViewListener != null) {
                CooTouchImageView.this.touchImageViewListener.onMove();
            }
            if (interpolate < 1.0f) {
                CooTouchImageView.this.compatPostOnAnimation(this);
            } else {
                CooTouchImageView.this.setState(State.NONE);
            }
        }
    }

    /* loaded from: classes6.dex */
    private class Fling implements Runnable {
        int currX;
        int currY;
        CompatScroller scroller;

        Fling(int i2, int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            CooTouchImageView.this.setState(State.FLING);
            this.scroller = new CompatScroller(CooTouchImageView.this.context);
            CooTouchImageView.this.matrix.getValues(CooTouchImageView.this.f12449m);
            int i8 = (int) CooTouchImageView.this.f12449m[2];
            int i9 = (int) CooTouchImageView.this.f12449m[5];
            if (CooTouchImageView.this.getImageWidth() > CooTouchImageView.this.viewWidth) {
                i4 = CooTouchImageView.this.viewWidth - ((int) CooTouchImageView.this.getImageWidth());
                i5 = 0;
            } else {
                i4 = i8;
                i5 = i4;
            }
            if (CooTouchImageView.this.getImageHeight() > CooTouchImageView.this.viewHeight) {
                i6 = CooTouchImageView.this.viewHeight - ((int) CooTouchImageView.this.getImageHeight());
                i7 = 0;
            } else {
                i6 = i9;
                i7 = i6;
            }
            this.scroller.fling(i8, i9, i2, i3, i4, i5, i6, i7);
            this.currX = i8;
            this.currY = i9;
        }

        public void cancelFling() {
            if (this.scroller != null) {
                CooTouchImageView.this.setState(State.NONE);
                this.scroller.forceFinished(true);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CooTouchImageView.this.touchImageViewListener != null) {
                CooTouchImageView.this.touchImageViewListener.onMove();
            }
            if (this.scroller.isFinished()) {
                this.scroller = null;
            } else if (this.scroller.computeScrollOffset()) {
                int currX = this.scroller.getCurrX();
                int currY = this.scroller.getCurrY();
                int i2 = currX - this.currX;
                int i3 = currY - this.currY;
                this.currX = currX;
                this.currY = currY;
                CooTouchImageView.this.matrix.postTranslate(i2, i3);
                CooTouchImageView.this.fixTrans();
                CooTouchImageView cooTouchImageView = CooTouchImageView.this;
                cooTouchImageView.setImageMatrix(cooTouchImageView.matrix);
                CooTouchImageView.this.compatPostOnAnimation(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = CooTouchImageView.this.doubleTapListener != null ? CooTouchImageView.this.doubleTapListener.onDoubleTap(motionEvent) : false;
            if (CooTouchImageView.this.state == State.NONE) {
                CooTouchImageView.this.compatPostOnAnimation(new DoubleTapZoom(CooTouchImageView.this.normalizedScale == CooTouchImageView.this.minScale ? CooTouchImageView.this.maxScale : CooTouchImageView.this.minScale, motionEvent.getX(), motionEvent.getY(), false));
                return true;
            }
            return onDoubleTap;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (CooTouchImageView.this.doubleTapListener != null) {
                return CooTouchImageView.this.doubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (CooTouchImageView.this.fling != null) {
                CooTouchImageView.this.fling.cancelFling();
            }
            CooTouchImageView cooTouchImageView = CooTouchImageView.this;
            cooTouchImageView.fling = new Fling((int) f2, (int) f3);
            CooTouchImageView cooTouchImageView2 = CooTouchImageView.this;
            cooTouchImageView2.compatPostOnAnimation(cooTouchImageView2.fling);
            return super.onFling(motionEvent, motionEvent2, f2, f3);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            CooTouchImageView.this.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (CooTouchImageView.this.doubleTapListener != null) {
                return CooTouchImageView.this.doubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return CooTouchImageView.this.performClick();
        }

        /* synthetic */ GestureListener(CooTouchImageView cooTouchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    public interface OnTouchImageViewListener {
        void onMove();
    }

    /* loaded from: classes6.dex */
    public interface OnTouchStateListener {
        void onUpdateTouchState(State state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            CooTouchImageView.this.scaleImage(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (CooTouchImageView.this.touchImageViewListener != null) {
                CooTouchImageView.this.touchImageViewListener.onMove();
                return true;
            }
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            CooTouchImageView.this.setState(State.ZOOM);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            CooTouchImageView.this.setState(State.NONE);
            float f2 = CooTouchImageView.this.normalizedScale;
            boolean z = true;
            if (CooTouchImageView.this.normalizedScale > CooTouchImageView.this.maxScale) {
                f2 = CooTouchImageView.this.maxScale;
            } else if (CooTouchImageView.this.normalizedScale < CooTouchImageView.this.minScale) {
                f2 = CooTouchImageView.this.minScale;
            } else {
                z = false;
            }
            float f3 = f2;
            if (z) {
                CooTouchImageView.this.compatPostOnAnimation(new DoubleTapZoom(f3, r4.viewWidth / 2, CooTouchImageView.this.viewHeight / 2, true));
            }
        }

        /* synthetic */ ScaleListener(CooTouchImageView cooTouchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* loaded from: classes6.dex */
    public enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ZoomVariables {
        public float focusX;
        public float focusY;
        public float scale;
        public ImageView.ScaleType scaleType;

        public ZoomVariables(float f2, float f3, float f4, ImageView.ScaleType scaleType) {
            this.scale = f2;
            this.focusX = f3;
            this.focusY = f4;
            this.scaleType = scaleType;
        }
    }

    public CooTouchImageView(Context context) {
        super(context);
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        setChangeImageListener(this);
        sharedConstructing(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public void compatPostOnAnimation(Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16L);
        }
    }

    private void fitImageToView() {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0 || this.matrix == null || this.prevMatrix == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f2 = intrinsicWidth;
        float f3 = this.viewWidth / f2;
        float f4 = intrinsicHeight;
        float f5 = this.viewHeight / f4;
        int i2 = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    f3 = Math.min(1.0f, Math.min(f3, f5));
                    f5 = f3;
                } else if (i2 != 4) {
                    if (i2 != 5) {
                        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
                    }
                }
                f3 = Math.min(f3, f5);
            } else {
                f3 = Math.max(f3, f5);
            }
            f5 = f3;
        } else {
            f3 = 1.0f;
            f5 = 1.0f;
        }
        int i3 = this.viewWidth;
        float f6 = i3 - (f3 * f2);
        int i4 = this.viewHeight;
        float f7 = i4 - (f5 * f4);
        this.matchViewWidth = i3 - f6;
        this.matchViewHeight = i4 - f7;
        if (!isZoomed() && !this.imageRenderedAtLeastOnce) {
            this.matrix.setScale(f3, f5);
            this.matrix.postTranslate(f6 / 2.0f, f7 / 2.0f);
            this.normalizedScale = 1.0f;
        } else {
            if (this.prevMatchViewWidth == 0.0f || this.prevMatchViewHeight == 0.0f) {
                savePreviousImageValues();
            }
            this.prevMatrix.getValues(this.f12449m);
            float[] fArr = this.f12449m;
            float f8 = this.matchViewWidth / f2;
            float f9 = this.normalizedScale;
            fArr[0] = f8 * f9;
            fArr[4] = (this.matchViewHeight / f4) * f9;
            float f10 = fArr[2];
            float f11 = fArr[5];
            translateMatrixAfterRotate(2, f10, this.prevMatchViewWidth * f9, getImageWidth(), this.prevViewWidth, this.viewWidth, intrinsicWidth);
            translateMatrixAfterRotate(5, f11, this.prevMatchViewHeight * this.normalizedScale, getImageHeight(), this.prevViewHeight, this.viewHeight, intrinsicHeight);
            this.matrix.setValues(this.f12449m);
        }
        fixTrans();
        setImageMatrix(this.matrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.f12449m);
        float imageWidth = getImageWidth();
        int i2 = this.viewWidth;
        if (imageWidth < i2) {
            this.f12449m[2] = (i2 - getImageWidth()) / 2.0f;
        }
        float imageHeight = getImageHeight();
        int i3 = this.viewHeight;
        if (imageHeight < i3) {
            this.f12449m[5] = (i3 - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.f12449m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixTrans() {
        this.matrix.getValues(this.f12449m);
        float[] fArr = this.f12449m;
        float f2 = fArr[2];
        float f3 = fArr[5];
        float fixTrans = getFixTrans(f2, this.viewWidth, getImageWidth());
        float fixTrans2 = getFixTrans(f3, this.viewHeight, getImageHeight());
        if (fixTrans == 0.0f && fixTrans2 == 0.0f) {
            return;
        }
        this.matrix.postTranslate(fixTrans, fixTrans2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFixDragTrans(float f2, float f3, float f4) {
        if (f4 <= f3) {
            return 0.0f;
        }
        return f2;
    }

    private float getFixTrans(float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 <= f3) {
            f6 = f3 - f4;
            f5 = 0.0f;
        } else {
            f5 = f3 - f4;
            f6 = 0.0f;
        }
        if (f2 < f5) {
            return (-f2) + f5;
        }
        if (f2 > f6) {
            return (-f2) + f6;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    private void notifyStateChangeListener(State state) {
        OnTouchStateListener onTouchStateListener = this.touchStateListener;
        if (onTouchStateListener != null) {
            onTouchStateListener.onUpdateTouchState(state);
        }
    }

    private void savePreviousImageValues() {
        Matrix matrix = this.matrix;
        if (matrix == null || this.viewHeight == 0 || this.viewWidth == 0) {
            return;
        }
        matrix.getValues(this.f12449m);
        this.prevMatrix.setValues(this.f12449m);
        this.prevMatchViewHeight = this.matchViewHeight;
        this.prevMatchViewWidth = this.matchViewWidth;
        this.prevViewHeight = this.viewHeight;
        this.prevViewWidth = this.viewWidth;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleImage(double d, float f2, float f3, boolean z) {
        float f4;
        float f5;
        if (z) {
            f4 = this.superMinScale;
            f5 = this.superMaxScale;
        } else {
            f4 = this.minScale;
            f5 = this.maxScale;
        }
        float f6 = this.normalizedScale;
        double d2 = f6;
        Double.isNaN(d2);
        float f7 = (float) (d2 * d);
        this.normalizedScale = f7;
        if (f7 > f5) {
            this.normalizedScale = f5;
            d = f5 / f6;
        } else if (f7 < f4) {
            this.normalizedScale = f4;
            d = f4 / f6;
        }
        float f8 = (float) d;
        this.matrix.postScale(f8, f8, f2, f3);
        fixScaleTrans();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(State state) {
        this.state = state;
        notifyStateChangeListener(state);
    }

    private int setViewSize(int i2, int i3, int i4) {
        if (i2 != Integer.MIN_VALUE) {
            return i2 != 0 ? i3 : i4;
        }
        return Math.min(i4, i3);
    }

    private void sharedConstructing(Context context) {
        super.setClickable(true);
        this.context = context;
        AnonymousClass1 anonymousClass1 = null;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener(this, anonymousClass1));
        this.mGestureDetector = new GestureDetector(context, new GestureListener(this, anonymousClass1));
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.f12449m = new float[9];
        this.normalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.superMinScale = 1.0f * 0.75f;
        this.superMaxScale = 3.0f * SUPER_MAX_MULTIPLIER;
        setImageMatrix(this.matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.NONE);
        this.onDrawReady = false;
        super.setOnTouchListener(new PrivateOnTouchListener(this, anonymousClass1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordBitmapToTouch(float f2, float f3) {
        float f4;
        this.matrix.getValues(this.f12449m);
        Drawable drawable = getDrawable();
        float f5 = 1.0f;
        if (drawable != null) {
            f5 = drawable.getIntrinsicWidth();
            f4 = drawable.getIntrinsicHeight();
        } else {
            f4 = 1.0f;
        }
        return new PointF(this.f12449m[2] + (getImageWidth() * (f2 / f5)), this.f12449m[5] + (getImageHeight() * (f3 / f4)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF transformCoordTouchToBitmap(float f2, float f3, boolean z) {
        float f4;
        float f5;
        this.matrix.getValues(this.f12449m);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            f5 = drawable.getIntrinsicWidth();
            f4 = drawable.getIntrinsicHeight();
        } else {
            f4 = 0.0f;
            f5 = 0.0f;
        }
        float[] fArr = this.f12449m;
        float f6 = fArr[2];
        float f7 = fArr[5];
        float imageWidth = ((f2 - f6) * f5) / getImageWidth();
        float imageHeight = ((f3 - f7) * f4) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), f5);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), f4);
        }
        return new PointF(imageWidth, imageHeight);
    }

    private void translateMatrixAfterRotate(int i2, float f2, float f3, float f4, int i3, int i4, int i5) {
        float f5 = i4;
        if (f4 < f5) {
            float[] fArr = this.f12449m;
            fArr[i2] = (f5 - (i5 * fArr[0])) * 0.5f;
        } else if (f2 > 0.0f) {
            this.f12449m[i2] = -((f4 - f5) * 0.5f);
        } else {
            this.f12449m[i2] = -((((Math.abs(f2) + (i3 * 0.5f)) / f3) * f4) - (f5 * 0.5f));
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        this.matrix.getValues(this.f12449m);
        float f2 = this.f12449m[2];
        if (getImageWidth() < this.viewWidth) {
            return false;
        }
        if (f2 < -1.0f || i2 >= 0) {
            return (Math.abs(f2) + ((float) this.viewWidth)) + 1.0f < getImageWidth() || i2 <= 0;
        }
        return false;
    }

    public boolean canScrollHorizontallyFroyo(int i2) {
        return canScrollHorizontally(i2);
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    @Override // android.widget.ImageView
    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        if (drawable instanceof RootDrawable) {
            Drawable actualImageDrawable = getHierarchy().getActualImageDrawable();
            return (actualImageDrawable == null || !(actualImageDrawable instanceof ForwardingDrawable)) ? actualImageDrawable : ((ForwardingDrawable) actualImageDrawable).getCurrent();
        }
        return drawable;
    }

    public float getMaxZoom() {
        return this.maxScale;
    }

    public float getMinZoom() {
        return this.minScale;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(this.viewWidth / 2, this.viewHeight / 2, true);
        transformCoordTouchToBitmap.x /= intrinsicWidth;
        transformCoordTouchToBitmap.y /= intrinsicHeight;
        return transformCoordTouchToBitmap;
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    @Override // com.facebook.drawee.generic.GenericDraweeHierarchy.ChangeImageListener
    public void onChangeImage() {
        fitImageToView();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        ZoomVariables zoomVariables = this.delayedZoomVariables;
        if (zoomVariables != null) {
            setZoom(zoomVariables.scale, zoomVariables.focusX, zoomVariables.focusY, zoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        try {
            super.onDraw(canvas);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i3);
            int mode2 = View.MeasureSpec.getMode(i3);
            this.viewWidth = setViewSize(mode, size, intrinsicWidth);
            int viewSize = setViewSize(mode2, size2, intrinsicHeight);
            this.viewHeight = viewSize;
            setMeasuredDimension(this.viewWidth, viewSize);
            fitImageToView();
            return;
        }
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.normalizedScale = bundle.getFloat("saveScale");
            float[] floatArray = bundle.getFloatArray("matrix");
            this.f12449m = floatArray;
            this.prevMatrix.setValues(floatArray);
            this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.prevViewHeight = bundle.getInt("viewHeight");
            this.prevViewWidth = bundle.getInt("viewWidth");
            this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.f12449m);
        bundle.putFloatArray("matrix", this.f12449m);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        return bundle;
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // com.facebook.drawee.view.SimpleDraweeView, com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int i2) {
        super.setImageResource(i2);
        savePreviousImageValues();
        fitImageToView();
    }

    @Override // com.facebook.drawee.view.SimpleDraweeView, com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setMaxZoom(float f2) {
        this.maxScale = f2;
        this.superMaxScale = f2 * SUPER_MAX_MULTIPLIER;
    }

    public void setMinZoom(float f2) {
        this.minScale = f2;
        this.superMinScale = f2 * 0.75f;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.doubleTapListener = onDoubleTapListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.touchImageViewListener = onTouchImageViewListener;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != ImageView.ScaleType.FIT_START && scaleType != ImageView.ScaleType.FIT_END) {
            ImageView.ScaleType scaleType2 = ImageView.ScaleType.MATRIX;
            if (scaleType == scaleType2) {
                super.setScaleType(scaleType2);
                return;
            }
            this.mScaleType = scaleType;
            if (this.onDrawReady) {
                setZoom(this);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
    }

    public void setScrollPosition(float f2, float f3) {
        setZoom(this.normalizedScale, f2, f3);
    }

    public void setTouchStateListener(OnTouchStateListener onTouchStateListener) {
        this.touchStateListener = onTouchStateListener;
    }

    public void setZoom(float f2) {
        setZoom(f2, 0.5f, 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class PrivateOnTouchListener implements View.OnTouchListener {
        private PointF last;

        private PrivateOnTouchListener() {
            this.last = new PointF();
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
            if (r1 != 6) goto L24;
         */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean onTouch(View view, MotionEvent motionEvent) {
            CooTouchImageView.this.mScaleDetector.onTouchEvent(motionEvent);
            CooTouchImageView.this.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            State state = CooTouchImageView.this.state;
            State state2 = State.NONE;
            if (state == state2 || CooTouchImageView.this.state == State.DRAG || CooTouchImageView.this.state == State.FLING) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action == 2) {
                            if (CooTouchImageView.this.state == State.DRAG) {
                                float f2 = pointF.x;
                                PointF pointF2 = this.last;
                                float f3 = f2 - pointF2.x;
                                float f4 = pointF.y - pointF2.y;
                                CooTouchImageView.this.matrix.postTranslate(CooTouchImageView.this.getFixDragTrans(f3, r2.viewWidth, CooTouchImageView.this.getImageWidth()), CooTouchImageView.this.getFixDragTrans(f4, r2.viewHeight, CooTouchImageView.this.getImageHeight()));
                                CooTouchImageView.this.fixTrans();
                                this.last.set(pointF.x, pointF.y);
                            }
                        }
                    }
                    CooTouchImageView.this.setState(state2);
                } else {
                    this.last.set(pointF);
                    if (CooTouchImageView.this.fling != null) {
                        CooTouchImageView.this.fling.cancelFling();
                    }
                    CooTouchImageView.this.setState(State.DRAG);
                }
            }
            CooTouchImageView cooTouchImageView = CooTouchImageView.this;
            cooTouchImageView.setImageMatrix(cooTouchImageView.matrix);
            if (CooTouchImageView.this.userTouchListener != null) {
                CooTouchImageView.this.userTouchListener.onTouch(view, motionEvent);
            }
            if (CooTouchImageView.this.touchImageViewListener != null) {
                CooTouchImageView.this.touchImageViewListener.onMove();
            }
            return true;
        }

        /* synthetic */ PrivateOnTouchListener(CooTouchImageView cooTouchImageView, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public void setZoom(float f2, float f3, float f4) {
        setZoom(f2, f3, f4, this.mScaleType);
    }

    public void setZoom(float f2, float f3, float f4, ImageView.ScaleType scaleType) {
        if (!this.onDrawReady) {
            this.delayedZoomVariables = new ZoomVariables(f2, f3, f4, scaleType);
            return;
        }
        if (scaleType != this.mScaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage(f2, this.viewWidth / 2, this.viewHeight / 2, true);
        this.matrix.getValues(this.f12449m);
        this.f12449m[2] = -((f3 * getImageWidth()) - (this.viewWidth * 0.5f));
        this.f12449m[5] = -((f4 * getImageHeight()) - (this.viewHeight * 0.5f));
        this.matrix.setValues(this.f12449m);
        fixTrans();
        setImageMatrix(this.matrix);
    }

    public CooTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        setChangeImageListener(this);
        sharedConstructing(context);
    }

    public CooTouchImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.doubleTapListener = null;
        this.userTouchListener = null;
        this.touchImageViewListener = null;
        setChangeImageListener(this);
        sharedConstructing(context);
    }

    public void setZoom(CooTouchImageView cooTouchImageView) {
        PointF scrollPosition = cooTouchImageView.getScrollPosition();
        setZoom(cooTouchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, cooTouchImageView.getScaleType());
    }
}
