package com.jingdong.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.jingdong.common.utils.MultiTouchController;

/* loaded from: classes6.dex */
public class TouchImageView extends View implements MultiTouchController.MultiTouchObjectCanvas<Img> {
    private static final int ANIMATION_DURATION = 600;
    public static final String TAG = "TouchImageView";
    private static final int UI_MODE_ANISOTROPIC_SCALE = 2;
    private static final int UI_MODE_ROTATE = 1;
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.jingdong.common.utils.TouchImageView.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private MultiTouchController.PointInfo currTouchPoint;
    private Img img;
    private boolean isFling;
    private boolean isScaling;
    ControllListener mControllListener;
    private final GesDetector mGesDetector;
    private ScaleAnimation mScaleAnimation;
    private Scroller mScroller;
    private boolean mShowDebugInfo;
    private int mUIMode;
    private MultiTouchController<Img> multiTouchController;

    /* loaded from: classes6.dex */
    public interface ControllListener {
        void onSingleTapUp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class GesDetector {
        private final DownDetector mDownDetector;
        private final GestureDetector mGestureDetector;
        private final Listener mListener;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public class DownDetector {
            private boolean isDown;

            private DownDetector() {
            }

            public void onTouchEvent(MotionEvent motionEvent) {
                int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                if (actionMasked != 0) {
                    if ((actionMasked == 1 || actionMasked == 3 || actionMasked == 5) && this.isDown) {
                        this.isDown = false;
                        GesDetector.this.mListener.onUp();
                    }
                } else if (this.isDown) {
                } else {
                    this.isDown = true;
                    GesDetector.this.mListener.onDown(motionEvent.getX(), motionEvent.getY());
                }
            }
        }

        /* loaded from: classes6.dex */
        private class MyGesListener extends GestureDetector.SimpleOnGestureListener {
            private MyGesListener() {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return GesDetector.this.mListener.onDoubleTap(motionEvent.getX(), motionEvent.getY());
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return GesDetector.this.mListener.onFling(f2, f3);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return GesDetector.this.mListener.onScroll(f2, f3, motionEvent2.getX() - motionEvent.getX(), motionEvent2.getY() - motionEvent.getY());
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return GesDetector.this.mListener.onSingleTapUp(motionEvent.getX(), motionEvent.getY());
            }
        }

        public GesDetector(Context context, Listener listener) {
            this.mListener = listener;
            this.mGestureDetector = new GestureDetector(context, new MyGesListener());
            this.mDownDetector = new DownDetector();
        }

        public void onTouchEvent(MotionEvent motionEvent) {
            this.mGestureDetector.onTouchEvent(motionEvent);
            this.mDownDetector.onTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes6.dex */
    public class Img {
        public static final float MAX_SCALE_FACTOR = 3.0f;
        public static final float MIN_SCALE_FACTOR = 1.0f;
        private Bitmap bitmap;
        public float centerX;
        public float centerY;
        public int displayHeight;
        public int displayWidth;
        private Drawable drawable;
        public int height;
        boolean isOnError = false;
        public float maxX;
        public float maxY;
        public float minX;
        public float minY;
        public float scaleX;
        public float scaleY;
        public int width;

        public Img(Bitmap bitmap) {
            this.bitmap = bitmap;
            this.drawable = new BitmapDrawable(bitmap);
            load();
        }

        private void checkImg() {
            Drawable drawable = this.drawable;
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    this.isOnError = false;
                } else if (this.isOnError) {
                } else {
                    TouchImageView.this.onImgRecycled();
                    this.isOnError = true;
                }
            } else if (this.isOnError) {
            } else {
                TouchImageView.this.onImgRecycled();
                this.isOnError = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean computeFling() {
            /*
                r8 = this;
                int r0 = r8.width
                int r0 = r0 / 2
                float r0 = (float) r0
                float r1 = r8.scaleX
                float r0 = r0 * r1
                int r1 = r8.height
                int r1 = r1 / 2
                float r1 = (float) r1
                float r2 = r8.scaleY
                float r1 = r1 * r2
                r2 = 1073741824(0x40000000, float:2.0)
                float r3 = r0 * r2
                int r4 = r8.displayWidth
                float r5 = (float) r4
                r6 = 1
                r7 = 0
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 <= 0) goto L2f
                float r3 = r8.centerX
                float r5 = r3 - r0
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 > 0) goto L2f
                float r3 = r3 + r0
                float r0 = (float) r4
                int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r0 < 0) goto L2f
                r0 = 1
                goto L30
            L2f:
                r0 = 0
            L30:
                float r2 = r2 * r1
                int r3 = r8.displayHeight
                float r4 = (float) r3
                int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r2 <= 0) goto L48
                float r2 = r8.centerY
                float r4 = r2 - r1
                int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r4 > 0) goto L48
                float r2 = r2 + r1
                float r1 = (float) r3
                int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
                if (r1 < 0) goto L48
                goto L49
            L48:
                r6 = r0
            L49:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.TouchImageView.Img.computeFling():boolean");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean scrollTo(float f2, float f3, float f4, float f5) {
            float max = Math.max(0.5f, Math.min(3.3000002f, f5));
            float max2 = Math.max(0.5f, Math.min(3.3000002f, f4));
            if (max2 == f4 && max == f5) {
                if (max2 != max) {
                    max2 = max;
                }
                float f6 = (this.width / 2) * max2;
                float f7 = (this.height / 2) * max;
                this.centerX = f2;
                this.scaleX = max2;
                this.minX = f2 - f6;
                this.maxX = f6 + f2;
                this.centerY = f3;
                this.scaleY = max;
                this.minY = f3 - f7;
                this.maxY = f7 + f3;
                return true;
            }
            return false;
        }

        public boolean containsPoint(float f2, float f3) {
            return f2 >= this.minX && f2 <= this.maxX && f3 >= this.minY && f3 <= this.maxY;
        }

        public void draw(Canvas canvas) {
            checkImg();
            if (this.isOnError) {
                return;
            }
            canvas.save();
            this.drawable.setBounds((int) this.minX, (int) this.minY, (int) this.maxX, (int) this.maxY);
            this.drawable.draw(canvas);
            canvas.restore();
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean flingTo(float r9, float r10) {
            /*
                r8 = this;
                int r0 = r8.width
                int r0 = r0 / 2
                float r0 = (float) r0
                float r1 = r8.scaleX
                float r0 = r0 * r1
                int r1 = r8.height
                int r1 = r1 / 2
                float r1 = (float) r1
                float r2 = r8.scaleY
                float r1 = r1 * r2
                r2 = 1073741824(0x40000000, float:2.0)
                float r3 = r0 * r2
                int r4 = r8.displayWidth
                float r5 = (float) r4
                r6 = 1
                r7 = 0
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 <= 0) goto L33
                float r3 = r9 - r0
                int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                if (r5 > 0) goto L33
                float r0 = r0 + r9
                float r4 = (float) r4
                int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r4 < 0) goto L33
                r8.centerX = r9
                r8.minX = r3
                r8.maxX = r0
                r9 = 1
                goto L34
            L33:
                r9 = 0
            L34:
                float r2 = r2 * r1
                int r0 = r8.displayHeight
                float r3 = (float) r0
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L50
                float r2 = r10 - r1
                int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r3 > 0) goto L50
                float r1 = r1 + r10
                float r0 = (float) r0
                int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
                if (r0 < 0) goto L50
                r8.centerY = r10
                r8.minY = r2
                r8.maxY = r1
                goto L51
            L50:
                r6 = r9
            L51:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.TouchImageView.Img.flingTo(float, float):boolean");
        }

        public Drawable getDrawable() {
            return this.drawable;
        }

        public void load() {
            if (this.displayWidth <= 0 || this.displayHeight <= 0) {
                this.displayWidth = TouchImageView.this.getWidth();
                this.displayHeight = TouchImageView.this.getHeight();
                this.width = this.bitmap.getWidth();
                this.height = this.bitmap.getHeight();
                float f2 = this.displayWidth / 2;
                float f3 = this.displayHeight / 2;
                float max = Math.max(0.5f, Math.min(3.3000002f, 1.0f));
                float max2 = Math.max(0.5f, Math.min(3.3000002f, 1.0f));
                if (max2 != max) {
                    max2 = max;
                }
                float f4 = (this.width / 2) * max2;
                float f5 = (this.height / 2) * max;
                this.centerX = f2;
                this.scaleX = max2;
                this.minX = f2 - f4;
                this.maxX = f4 + f2;
                this.centerY = f3;
                this.scaleY = max;
                this.minY = f3 - f5;
                this.maxY = f5 + f3;
            }
        }

        public boolean scale(float f2, float f3, float f4, float f5) {
            float max = Math.max(0.5f, Math.min(3.3000002f, f5));
            float max2 = Math.max(0.5f, Math.min(3.3000002f, f4));
            if (max2 != max) {
                max2 = max;
            }
            float f6 = (this.width / 2) * max2;
            float f7 = (this.height / 2) * max;
            this.centerX = f2;
            this.scaleX = max2;
            this.minX = f2 - f6;
            this.maxX = f6 + f2;
            this.centerY = f3;
            this.scaleY = max;
            this.minY = f3 - f7;
            this.maxY = f7 + f3;
            return true;
        }

        public boolean setPos(MultiTouchController.PositionAndScale positionAndScale) {
            return setPos(positionAndScale.getXOff(), positionAndScale.getYOff(), (TouchImageView.this.mUIMode & 2) != 0 ? positionAndScale.getScaleX() : positionAndScale.getScale(), (TouchImageView.this.mUIMode & 2) != 0 ? positionAndScale.getScaleY() : positionAndScale.getScale());
        }

        public void unload() {
            this.drawable = null;
        }

        public void zoomIn() {
            if (setPos(this.centerX, this.centerY, this.scaleX - 0.8f, this.scaleY - 0.8f)) {
                TouchImageView.this.invalidate();
            }
        }

        public void zoomOut() {
            if (setPos(this.centerX, this.centerY, this.scaleX + 0.8f, this.scaleY + 0.8f)) {
                TouchImageView.this.invalidate();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean setPos(float r8, float r9) {
            /*
                r7 = this;
                int r0 = r7.width
                int r0 = r0 / 2
                float r0 = (float) r0
                float r1 = r7.scaleX
                float r0 = r0 * r1
                int r1 = r7.height
                int r1 = r1 / 2
                float r1 = (float) r1
                float r2 = r7.scaleY
                float r1 = r1 * r2
                r2 = 1073741824(0x40000000, float:2.0)
                float r3 = r0 * r2
                int r4 = r7.displayWidth
                float r5 = (float) r4
                r6 = 0
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 <= 0) goto L32
                float r3 = r8 - r0
                int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
                if (r5 > 0) goto L32
                float r0 = r0 + r8
                float r4 = (float) r4
                int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r4 < 0) goto L32
                r7.centerX = r8
                r7.minX = r3
                r7.maxX = r0
                r8 = 1
                goto L33
            L32:
                r8 = 0
            L33:
                if (r8 == 0) goto L50
                float r2 = r2 * r1
                int r0 = r7.displayHeight
                float r3 = (float) r0
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L50
                float r2 = r9 - r1
                int r3 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r3 > 0) goto L50
                float r1 = r1 + r9
                float r0 = (float) r0
                int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
                if (r0 < 0) goto L50
                r7.centerY = r9
                r7.minY = r2
                r7.maxY = r1
            L50:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.TouchImageView.Img.setPos(float, float):boolean");
        }

        private boolean setPos(float f2, float f3, float f4, float f5) {
            float max = Math.max(0.5f, Math.min(3.3000002f, f5));
            float max2 = Math.max(0.5f, Math.min(3.3000002f, f4));
            if (max2 == f4 && max == f5) {
                if (max2 != max) {
                    max2 = max;
                }
                int i2 = this.width;
                float f6 = (i2 / 2) * max2;
                float f7 = (r2 / 2) * max;
                float f8 = i2 * max2;
                float f9 = this.height * max;
                int i3 = this.displayWidth;
                if (f8 <= i3) {
                    f2 = i3 / 2;
                }
                int i4 = this.displayHeight;
                if (f9 <= i4) {
                    f3 = i4 / 2;
                }
                if (f8 <= i3) {
                    f2 = i3 / 2;
                } else if (f2 - f6 > 0.0f) {
                    f2 = f6;
                } else if (f2 + f6 < i3) {
                    f2 = i3 - f6;
                }
                if (f9 <= i4) {
                    f3 = i4 / 2;
                } else if (f3 - f7 > 0.0f) {
                    f3 = f7;
                } else if (f3 + f7 < i4) {
                    f3 = i4 - f7;
                }
                this.centerX = f2;
                this.scaleX = max2;
                this.minX = f2 - f6;
                this.maxX = f6 + f2;
                this.centerY = f3;
                this.scaleY = max;
                this.minY = f3 - f7;
                this.maxY = f7 + f3;
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface Listener {
        boolean onDoubleTap(float f2, float f3);

        void onDown(float f2, float f3);

        boolean onFling(float f2, float f3);

        boolean onScroll(float f2, float f3, float f4, float f5);

        boolean onSingleTapUp(float f2, float f3);

        void onUp();
    }

    /* loaded from: classes6.dex */
    private class MyGesListener implements Listener {
        private boolean mNoUp;

        private MyGesListener() {
            this.mNoUp = false;
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public boolean onDoubleTap(float f2, float f3) {
            return true;
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public void onDown(float f2, float f3) {
            TouchImageView.this.getParent().requestDisallowInterceptTouchEvent(true);
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public boolean onFling(float f2, float f3) {
            if (!TouchImageView.this.isScaling && TouchImageView.this.img != null && TouchImageView.this.img.computeFling()) {
                TouchImageView.this.mScroller.forceFinished(true);
                TouchImageView.this.mScroller.fling((int) TouchImageView.this.img.centerX, (int) TouchImageView.this.img.centerY, ((int) f2) / 2, ((int) f3) / 2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
                TouchImageView.this.isFling = true;
                ViewCompat.postInvalidateOnAnimation(TouchImageView.this);
            }
            return true;
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public boolean onScroll(float f2, float f3, float f4, float f5) {
            if (TouchImageView.this.isScaling || TouchImageView.this.img == null) {
                return true;
            }
            if (TouchImageView.this.img.setPos(TouchImageView.this.img.centerX + (-f2) + 0.5f, TouchImageView.this.img.centerY + (-f3) + 0.5f)) {
                ViewCompat.postInvalidateOnAnimation(TouchImageView.this);
            } else {
                TouchImageView.this.scaleTo(r3.img.displayWidth / 2, TouchImageView.this.img.displayHeight / 2, 1.0f);
                TouchImageView.this.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return true;
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public boolean onSingleTapUp(float f2, float f3) {
            this.mNoUp = true;
            ControllListener controllListener = TouchImageView.this.mControllListener;
            if (controllListener != null) {
                controllListener.onSingleTapUp();
            }
            return true;
        }

        @Override // com.jingdong.common.utils.TouchImageView.Listener
        public void onUp() {
            TouchImageView.this.getParent().requestDisallowInterceptTouchEvent(false);
            if (!this.mNoUp) {
                if (TouchImageView.this.img != null && TouchImageView.this.endDrag()) {
                    ViewCompat.postInvalidateOnAnimation(TouchImageView.this);
                    return;
                }
                return;
            }
            this.mNoUp = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class ScaleAnimation {
        private static final long ANIMATION_START = -1;
        private static final long NO_ANIMATION = -2;
        public float mCurrentScale;
        private float mEndScale;
        private Interpolator mInterpolator;
        private float mStartScale;
        public long mStartTime = -2;
        public int mDuration = 600;

        public ScaleAnimation() {
            setInterpolator(TouchImageView.sInterpolator);
        }

        public boolean calculate(long j2) {
            long j3 = this.mStartTime;
            if (j3 == -2) {
                return false;
            }
            if (j3 == -1) {
                this.mStartTime = j2;
            }
            int i2 = (int) (j2 - this.mStartTime);
            float clamp = clamp(i2 / this.mDuration, 0.0f, 1.0f);
            Interpolator interpolator = this.mInterpolator;
            float f2 = this.mStartScale;
            float f3 = this.mEndScale - f2;
            if (interpolator != null) {
                clamp = interpolator.getInterpolation(clamp);
            }
            float f4 = f2 + (f3 * clamp);
            float f5 = this.mStartScale;
            float f6 = this.mEndScale;
            float f7 = f5 > f6 ? f6 : f5;
            if (f5 <= f6) {
                f5 = f6;
            }
            this.mCurrentScale = clamp(f4, f7, f5);
            if (i2 >= this.mDuration) {
                this.mStartTime = -2L;
            }
            return this.mStartTime != -2;
        }

        float clamp(float f2, float f3, float f4) {
            return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
        }

        public void forceStop() {
            this.mStartTime = -2L;
        }

        public void init(float f2, float f3) {
            this.mStartScale = f2;
            this.mEndScale = f3;
            this.mCurrentScale = f2;
            this.mStartTime = -2L;
        }

        public boolean isActive() {
            return this.mStartTime != -2;
        }

        public void setInterpolator(Interpolator interpolator) {
            this.mInterpolator = interpolator;
        }

        public void start() {
            this.mStartTime = -1L;
        }
    }

    public TouchImageView(Context context) {
        this(context, null);
        initView();
    }

    private void computeAnimation() {
        float f2;
        float f3;
        boolean z;
        Img img = this.img;
        float f4 = img.centerX;
        float f5 = img.centerY;
        float f6 = img.scaleX;
        float f7 = img.scaleY;
        if (this.mScaleAnimation.calculate(SystemClock.uptimeMillis())) {
            f3 = this.mScaleAnimation.mCurrentScale;
            z = true;
            f2 = f3;
        } else {
            f2 = f6;
            f3 = f7;
            z = false;
        }
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            z |= true;
            f4 = this.mScroller.getCurrX();
            f5 = this.mScroller.getCurrY();
        }
        if (z) {
            if (!this.isFling) {
                this.img.scrollTo(f4, f5, f2, f3);
                ViewCompat.postInvalidateOnAnimation(this);
                return;
            } else if (this.img.flingTo(f4, f5)) {
                ViewCompat.postInvalidateOnAnimation(this);
                return;
            } else {
                this.isFling = false;
                this.mScroller.abortAnimation();
                return;
            }
        }
        this.isFling = false;
        this.mScroller.abortAnimation();
        this.mScaleAnimation.forceStop();
    }

    private void drawMultitouchDebugMarks(Canvas canvas) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0056 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean endDrag() {
        /*
            r13 = this;
            com.jingdong.common.utils.TouchImageView$Img r0 = r13.img
            float r1 = r0.centerX
            int r3 = (int) r1
            float r2 = r0.centerY
            int r4 = (int) r2
            float r5 = r0.maxX
            float r6 = r0.minX
            float r7 = r5 - r6
            int r8 = r0.displayWidth
            float r9 = (float) r8
            r10 = 0
            r11 = 0
            r12 = 1
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L1c
            int r8 = r8 / 2
        L1a:
            r1 = 1
            goto L2f
        L1c:
            float r7 = (float) r8
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 >= 0) goto L26
            float r6 = (float) r8
            float r6 = r6 - r5
            float r1 = r1 + r6
        L24:
            int r8 = (int) r1
            goto L1a
        L26:
            int r5 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r5 <= 0) goto L2d
            float r5 = -r6
            float r1 = r1 + r5
            goto L24
        L2d:
            r8 = r3
            r1 = 0
        L2f:
            float r5 = r0.maxY
            float r6 = r0.minY
            float r7 = r5 - r6
            int r0 = r0.displayHeight
            float r9 = (float) r0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L40
            int r0 = r0 / 2
        L3e:
            r2 = 1
            goto L52
        L40:
            float r7 = (float) r0
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 >= 0) goto L4a
            float r0 = (float) r0
            float r0 = r0 - r5
        L47:
            float r2 = r2 + r0
            int r0 = (int) r2
            goto L3e
        L4a:
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 <= 0) goto L50
            float r0 = -r6
            goto L47
        L50:
            r0 = r4
            r2 = 0
        L52:
            int r8 = r8 - r3
            int r0 = r0 - r4
            if (r1 != 0) goto L58
            if (r2 == 0) goto L5a
        L58:
            if (r8 != 0) goto L5e
        L5a:
            if (r0 == 0) goto L5d
            goto L5e
        L5d:
            return r11
        L5e:
            android.widget.Scroller r5 = r13.mScroller
            r5.forceFinished(r12)
            android.widget.Scroller r5 = r13.mScroller
            if (r1 == 0) goto L68
            goto L69
        L68:
            r8 = 0
        L69:
            if (r2 == 0) goto L6d
            r6 = r0
            goto L6e
        L6d:
            r6 = 0
        L6e:
            r7 = 600(0x258, float:8.41E-43)
            r2 = r5
            r5 = r8
            r2.startScroll(r3, r4, r5, r6, r7)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.TouchImageView.endDrag():boolean");
    }

    private void initView() {
        setBackgroundColor(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scaleTo(float f2, float f3, float f4) {
        Img img = this.img;
        if (img == null) {
            return;
        }
        if (!img.containsPoint(f2, f3)) {
            Img img2 = this.img;
            f2 = Math.max(img2.minX, Math.min(f2, img2.maxX));
            Img img3 = this.img;
            f3 = Math.max(img3.minY, Math.min(f3, img3.maxY));
        }
        this.mScaleAnimation.init(this.img.scaleX, f4);
        ScaleAnimation scaleAnimation = this.mScaleAnimation;
        scaleAnimation.mDuration = 300;
        scaleAnimation.start();
        Img img4 = this.img;
        int i2 = (int) img4.centerX;
        int i3 = (int) img4.centerY;
        int i4 = ((int) f2) - i2;
        int i5 = ((int) f3) - i3;
        if (i4 != 0 || i5 != 0) {
            this.mScroller.forceFinished(true);
            this.mScroller.startScroll(i2, i3, i4, i5, 300);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public Img getImg() {
        return this.img;
    }

    public ControllListener getTapListener() {
        return this.mControllListener;
    }

    public void load() {
        Img img = this.img;
        if (img != null) {
            img.load();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Img img = this.img;
        if (img == null) {
            return;
        }
        img.load();
        this.img.draw(canvas);
        if (this.mShowDebugInfo) {
            drawMultitouchDebugMarks(canvas);
        }
        computeAnimation();
    }

    protected void onImgRecycled() {
    }

    @Override // com.jingdong.common.utils.MultiTouchController.MultiTouchObjectCanvas
    public boolean onScale(float f2, float f3, float f4) {
        Img img = this.img;
        if (img != null && img.scale(f2, f3, f4, f4)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    @Override // com.jingdong.common.utils.MultiTouchController.MultiTouchObjectCanvas
    public boolean onScaleBegin(float f2, float f3) {
        this.isScaling = true;
        return true;
    }

    @Override // com.jingdong.common.utils.MultiTouchController.MultiTouchObjectCanvas
    public void onScaleEnd() {
        this.isScaling = false;
        snapback();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGesDetector.onTouchEvent(motionEvent);
        this.multiTouchController.onTouchEvent(motionEvent);
        return true;
    }

    public void selectObject(Img img, MultiTouchController.PointInfo pointInfo) {
        this.currTouchPoint.set(pointInfo);
        invalidate();
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.img = new Img(bitmap);
        invalidate();
    }

    public void setTapListener(ControllListener controllListener) {
        this.mControllListener = controllListener;
    }

    void snapback() {
        if (this.img == null) {
            return;
        }
        boolean endDrag = endDrag();
        float f2 = this.img.scaleX;
        if (f2 > 3.0f) {
            endDrag |= true;
            this.mScaleAnimation.init(f2, 3.0f);
            this.mScaleAnimation.start();
        } else if (f2 < 1.0f) {
            endDrag |= true;
            this.mScaleAnimation.init(f2, 1.0f);
            this.mScaleAnimation.start();
        }
        if (endDrag) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void trackballClicked() {
        this.mUIMode = (this.mUIMode + 1) % 3;
        invalidate();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jingdong.common.utils.MultiTouchController.MultiTouchObjectCanvas
    public Img getDraggableObjectAtPoint(MultiTouchController.PointInfo pointInfo) {
        return this.img;
    }

    @Override // com.jingdong.common.utils.MultiTouchController.MultiTouchObjectCanvas
    public void getPositionAndScale(Img img, MultiTouchController.PositionAndScale positionAndScale) {
        float f2 = img.centerX;
        float f3 = img.centerY;
        int i2 = this.mUIMode;
        boolean z = (i2 & 2) == 0;
        float f4 = img.scaleX;
        float f5 = img.scaleY;
        positionAndScale.set(f2, f3, z, (f4 + f5) / 2.0f, (i2 & 2) != 0, f4, f5, (i2 & 1) != 0);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        initView();
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.img = null;
        this.multiTouchController = new MultiTouchController<>(this);
        this.currTouchPoint = new MultiTouchController.PointInfo();
        this.mShowDebugInfo = false;
        this.mUIMode = 1;
        this.mScaleAnimation = new ScaleAnimation();
        this.isScaling = false;
        this.isFling = false;
        this.mGesDetector = new GesDetector(context, new MyGesListener());
        this.mScroller = new Scroller(context, sInterpolator);
        initView();
    }
}
