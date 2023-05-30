package com.jingdong.common.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.UnLog;
import java.util.LinkedList;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes6.dex */
public class PhotoView extends ImageView {
    private static final byte MODE_DEFAULT = 0;
    private static final byte MODE_SCALE = 1;
    private static final String TAG = "PhotoView";
    private Matrix changeMatrix;
    private GestureDetector commonGestureDetector;
    private int currentMode;
    private int doubleTapScaleLevel;
    private FlingAnimator flingAnimator;
    private PointF mLastScalePoint;
    private float mScaleFactor;
    private MatrixPool matrixPool;
    private int maxScaleLevel;
    private int minScaleLevel;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    private ScaleAnimator scaleAnimator;
    private ScaleGestureDetector scaleGestureDetector;
    public SkinHelper skinHelper;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class DealCommonGestureCallback extends GestureDetector.SimpleOnGestureListener {
        private DealCommonGestureCallback() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            PhotoView.this.cancelFlingAnim();
            PhotoView.this.cancelScaleAnim();
            PhotoView.this.dispatchDoubleTapChange(motionEvent);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (PhotoView.this.isScaleAnimRunning()) {
                return true;
            }
            PhotoView.this.cancelFlingAnim();
            PhotoView.this.dispatchFlingChange(f2, f3);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            if (PhotoView.this.onLongClickListener != null) {
                PhotoView.this.onLongClickListener.onLongClick(PhotoView.this);
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (PhotoView.this.isScaleAnimRunning()) {
                return true;
            }
            PhotoView.this.cancelFlingAnim();
            PhotoView.this.dispatchScrollChange(-f2, -f3);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (PhotoView.this.onClickListener != null) {
                PhotoView.this.onClickListener.onClick(PhotoView.this);
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class DealScaleGestureCallback extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private DealScaleGestureCallback() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (PhotoView.this.isScaleAnimRunning()) {
                return true;
            }
            PhotoView.this.cancelFlingAnim();
            PhotoView.this.dispatchScaleChange(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (PhotoView.this.isScaleAnimRunning()) {
                return false;
            }
            PhotoView.this.updateMode((byte) 1);
            return true;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            PhotoView.this.updateMode((byte) 0);
            PhotoView.this.cancelFlingAnim();
            PhotoView.this.cancelScaleAnim();
            PhotoView.this.dispatchScaleEndChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FlingAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
        private float[] mVector;

        public FlingAnimator(float f2, float f3) {
            setFloatValues(0.0f, 1.0f);
            setDuration(1000000L);
            addUpdateListener(this);
            this.mVector = new float[]{f2, f3};
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float[] fArr = this.mVector;
            if (fArr[0] != 0.0f && fArr[1] != 0.0f) {
                PhotoView.this.dispatchScrollChange(fArr[0], fArr[1]);
                float[] fArr2 = this.mVector;
                fArr2[0] = fArr2[0] * 0.9f;
                fArr2[1] = fArr2[1] * 0.9f;
                if (Math.sqrt((fArr2[0] * fArr2[0]) + (fArr2[1] * fArr2[1])) < 1.0d) {
                    valueAnimator.cancel();
                    return;
                }
                return;
            }
            valueAnimator.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ScaleAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
        private float[] mEnd;
        private float[] mResult;
        private float[] mStart;

        public ScaleAnimator(PhotoView photoView, Matrix matrix, Matrix matrix2) {
            this(matrix, matrix2, 300L);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.mResult;
                float[] fArr2 = this.mStart;
                fArr[i2] = fArr2[i2] + ((this.mEnd[i2] - fArr2[i2]) * floatValue);
            }
            PhotoView.this.changeMatrix.setValues(this.mResult);
            PhotoView photoView = PhotoView.this;
            photoView.setImageMatrix(photoView.changeMatrix);
        }

        public ScaleAnimator(Matrix matrix, Matrix matrix2, long j2) {
            this.mStart = new float[9];
            this.mEnd = new float[9];
            this.mResult = new float[9];
            setFloatValues(0.0f, 1.0f);
            setDuration(j2);
            addUpdateListener(this);
            matrix.getValues(this.mStart);
            matrix2.getValues(this.mEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class SkinHelper {
        private PhotoView photoView;

        public SkinHelper() {
            this.photoView = PhotoView.this;
        }
    }

    public PhotoView(Context context) {
        super(context);
        this.minScaleLevel = 1;
        this.maxScaleLevel = 4;
        this.doubleTapScaleLevel = 4;
        this.mScaleFactor = 1.0f;
        this.currentMode = 0;
        initConfig();
        initGestureDetector();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFlingAnim() {
        FlingAnimator flingAnimator = this.flingAnimator;
        if (flingAnimator != null) {
            flingAnimator.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelScaleAnim() {
        ScaleAnimator scaleAnimator = this.scaleAnimator;
        if (scaleAnimator != null) {
            scaleAnimator.cancel();
        }
    }

    private Matrix changeMatrixToFitScale(Matrix matrix, float f2, float f3) {
        float f4 = this.mScaleFactor;
        float f5 = this.minScaleLevel * f4;
        float f6 = f4 * this.maxScaleLevel;
        float matrixValue = getMatrixValue(matrix, 0);
        if (matrixValue < f5) {
            return this.matrixPool.borrowObject(getInnerMatrix());
        }
        if (matrixValue > f6) {
            Matrix borrowObject = this.matrixPool.borrowObject(matrix);
            float f7 = f6 / matrixValue;
            borrowObject.postScale(f7, f7, f2, f3);
            return borrowObject;
        }
        return this.matrixPool.borrowObject(matrix);
    }

    private Matrix changeMatrixToFitTranslate(Matrix matrix) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        Matrix borrowObject = this.matrixPool.borrowObject(matrix);
        RectF imageBounds = getImageBounds(borrowObject);
        int width = getWidth();
        int height = getHeight();
        float f8 = width;
        float width2 = f8 - imageBounds.width();
        float f9 = height;
        float height2 = f9 - imageBounds.height();
        float f10 = 0.0f;
        if (width2 > 0.0f && height2 > 0.0f) {
            f10 = (width2 / 2.0f) - imageBounds.left;
            f4 = height2 / 2.0f;
            f5 = imageBounds.top;
        } else {
            if (width2 <= 0.0f && height2 <= 0.0f) {
                float f11 = imageBounds.left;
                if (f11 > 0.0f) {
                    f6 = -f11;
                } else {
                    float f12 = imageBounds.right;
                    f6 = f12 < f8 ? f8 - f12 : 0.0f;
                }
                float f13 = imageBounds.top;
                if (f13 > 0.0f) {
                    f7 = -f13;
                } else {
                    float f14 = imageBounds.bottom;
                    if (f14 < f9) {
                        f7 = f9 - f14;
                    } else {
                        f10 = f6;
                        f3 = 0.0f;
                    }
                }
                f3 = f7;
                f10 = f6;
            } else if (width2 > 0.0f || height2 <= 0.0f) {
                if (width2 > 0.0f && height2 <= 0.0f) {
                    float f15 = (width2 / 2.0f) - imageBounds.left;
                    float f16 = imageBounds.top;
                    if (f16 > 0.0f) {
                        f2 = -f16;
                    } else {
                        float f17 = imageBounds.bottom;
                        if (f17 < f9) {
                            f2 = f9 - f17;
                        } else {
                            f10 = f15;
                        }
                    }
                    f3 = f2;
                    f10 = f15;
                }
                f3 = 0.0f;
            } else {
                float f18 = imageBounds.left;
                if (f18 > 0.0f) {
                    f10 = -f18;
                } else {
                    float f19 = imageBounds.right;
                    if (f19 < f8) {
                        f10 = f8 - f19;
                    }
                }
                f4 = height2 / 2.0f;
                f5 = imageBounds.top;
            }
            borrowObject.postTranslate(f10, f3);
            return borrowObject;
        }
        f3 = f4 - f5;
        borrowObject.postTranslate(f10, f3);
        return borrowObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDoubleTapChange(MotionEvent motionEvent) {
        if (isReady()) {
            float floor = (float) (Math.floor(getMatrixValue(this.changeMatrix, 0) * 100.0f) / 100.0d);
            float f2 = this.mScaleFactor;
            float f3 = this.minScaleLevel * f2;
            float f4 = this.doubleTapScaleLevel * f2;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            float f5 = floor <= f3 ? f4 / floor : f3 / floor;
            Matrix borrowObject = this.matrixPool.borrowObject(this.changeMatrix);
            borrowObject.postScale(f5, f5, x, y);
            Matrix changeMatrixToFitTranslate = changeMatrixToFitTranslate(borrowObject);
            ScaleAnimator scaleAnimator = new ScaleAnimator(this, this.changeMatrix, changeMatrixToFitTranslate);
            this.scaleAnimator = scaleAnimator;
            scaleAnimator.start();
            this.matrixPool.returnObject(changeMatrixToFitTranslate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchFlingChange(float f2, float f3) {
        if (isReady()) {
            FlingAnimator flingAnimator = new FlingAnimator(f2 / 60.0f, f3 / 60.0f);
            this.flingAnimator = flingAnimator;
            flingAnimator.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchScaleChange(float f2, float f3, float f4) {
        if (isReady()) {
            updateLastScalePoint(f3, f4);
            this.changeMatrix.postScale(f2, f2, f3, f4);
            setImageMatrix(this.changeMatrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchScaleEndChange() {
        if (isReady()) {
            PointF pointF = this.mLastScalePoint;
            Matrix changeMatrixToFitTranslate = changeMatrixToFitTranslate(changeMatrixToFitScale(this.changeMatrix, pointF.x, pointF.y));
            ScaleAnimator scaleAnimator = new ScaleAnimator(this, this.changeMatrix, changeMatrixToFitTranslate);
            this.scaleAnimator = scaleAnimator;
            scaleAnimator.start();
            this.matrixPool.returnObject(changeMatrixToFitTranslate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchScrollChange(float f2, float f3) {
        if (isReady()) {
            if (this.currentMode == 0) {
                this.changeMatrix.postTranslate(f2, f3);
                this.changeMatrix = changeMatrixToFitTranslate(this.changeMatrix);
            } else {
                this.changeMatrix.postTranslate(f2, f3);
            }
            setImageMatrix(this.changeMatrix);
        }
    }

    private void fitImageToCenter() {
        Matrix innerMatrix = getInnerMatrix();
        if (innerMatrix != null) {
            this.mScaleFactor = getMatrixValue(innerMatrix, 0);
            this.changeMatrix.set(innerMatrix);
            setImageMatrix(this.changeMatrix);
            this.matrixPool.returnObject(innerMatrix);
        }
    }

    private RectF getImageBounds(@NonNull Matrix matrix) {
        if (isReady() && matrix != null) {
            RectF rectF = new RectF(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrix.mapRect(rectF);
            return rectF;
        }
        return new RectF();
    }

    private Matrix getInnerMatrix() {
        if (isReady()) {
            Drawable drawable = getDrawable();
            RectF rectF = new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            RectF rectF2 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
            Matrix borrowObject = this.matrixPool.borrowObject();
            borrowObject.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            return borrowObject;
        }
        return null;
    }

    private float getMatrixValue(Matrix matrix, int i2) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return fArr[i2];
    }

    private void initConfig() {
        this.skinHelper = new SkinHelper();
        setScaleType(ImageView.ScaleType.MATRIX);
        this.changeMatrix = new Matrix();
        this.matrixPool = new MatrixPool(this);
    }

    private void initGestureDetector() {
        this.commonGestureDetector = new GestureDetector(getContext(), new DealCommonGestureCallback());
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), new DealScaleGestureCallback());
    }

    private boolean isReady() {
        return getWidth() > 0 && getHeight() > 0 && getDrawable() != null && getDrawable().getIntrinsicWidth() > 0 && getDrawable().getIntrinsicHeight() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isScaleAnimRunning() {
        ScaleAnimator scaleAnimator = this.scaleAnimator;
        return scaleAnimator != null && scaleAnimator.isRunning();
    }

    private void updateLastScalePoint(float f2, float f3) {
        PointF pointF = this.mLastScalePoint;
        if (pointF == null) {
            this.mLastScalePoint = new PointF(f2, f3);
        } else {
            pointF.set(f2, f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMode(byte b) {
        this.currentMode = b;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.currentMode == 1 || isScaleAnimRunning()) {
            return false;
        }
        RectF imageBounds = getImageBounds(this.changeMatrix);
        if (imageBounds.isEmpty()) {
            return false;
        }
        return i2 > 0 ? imageBounds.right > ((float) getWidth()) : imageBounds.left < 0.0f;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        if (this.currentMode == 1 || isScaleAnimRunning()) {
            return false;
        }
        RectF imageBounds = getImageBounds(this.changeMatrix);
        if (imageBounds.isEmpty()) {
            return false;
        }
        return i2 > 0 ? imageBounds.bottom > ((float) getHeight()) : imageBounds.top < 0.0f;
    }

    public int getDoubleTapScaleLevel() {
        return this.doubleTapScaleLevel;
    }

    public int getMaxScaleLevel() {
        return this.maxScaleLevel;
    }

    public boolean isZoomed() {
        Matrix innerMatrix;
        if (this.changeMatrix == null || (innerMatrix = getInnerMatrix()) == null) {
            return false;
        }
        return !innerMatrix.equals(this.changeMatrix);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            fitImageToCenter();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.commonGestureDetector.onTouchEvent(motionEvent);
        this.scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    public void reset() {
        fitImageToCenter();
    }

    public void setDoubleTapScaleLevel(int i2) {
        if (i2 >= this.minScaleLevel && i2 <= this.maxScaleLevel) {
            this.doubleTapScaleLevel = i2;
        } else {
            UnLog.e(TAG, "doubleTapScaleLevel\u5176\u8303\u56f4\u5fc5\u987b\u5728minScaleLevel-maxScaleLevel\u4e4b\u95f4\uff01");
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        reset();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        reset();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        super.setImageResource(i2);
        reset();
    }

    @Override // android.widget.ImageView
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        reset();
    }

    public void setMaxScaleLevel(int i2) {
        if (i2 < this.minScaleLevel) {
            UnLog.e(TAG, "maxScaleLevel\u4e0d\u5141\u8bb8\u5c0f\u4e8eminScaleLevel\uff01");
            return;
        }
        this.maxScaleLevel = i2;
        if (this.doubleTapScaleLevel > i2) {
            this.doubleTapScaleLevel = i2;
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class MatrixPool {
        private LinkedList<Matrix> caches;
        private int maxSize;

        public MatrixPool(int i2) {
            this.maxSize = i2;
            this.caches = new LinkedList<>();
        }

        public Matrix borrowObject() {
            if (!this.caches.isEmpty()) {
                return this.caches.poll();
            }
            return new Matrix();
        }

        public boolean returnObject(Matrix matrix) {
            if (matrix == null || this.caches.size() >= this.maxSize) {
                return false;
            }
            matrix.reset();
            this.caches.offer(matrix);
            return true;
        }

        public MatrixPool(PhotoView photoView) {
            this(16);
        }

        public Matrix borrowObject(Matrix matrix) {
            Matrix borrowObject = borrowObject();
            borrowObject.set(matrix);
            return borrowObject;
        }
    }

    public PhotoView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.minScaleLevel = 1;
        this.maxScaleLevel = 4;
        this.doubleTapScaleLevel = 4;
        this.mScaleFactor = 1.0f;
        this.currentMode = 0;
        initConfig();
        initGestureDetector();
    }

    public PhotoView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.minScaleLevel = 1;
        this.maxScaleLevel = 4;
        this.doubleTapScaleLevel = 4;
        this.mScaleFactor = 1.0f;
        this.currentMode = 0;
        initConfig();
        initGestureDetector();
    }
}
