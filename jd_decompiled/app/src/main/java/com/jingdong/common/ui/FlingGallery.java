package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/* loaded from: classes6.dex */
public class FlingGallery extends FrameLayout {
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOICTY = 400;
    private Adapter mAdapter;
    private FlingGalleryAnimation mAnimation;
    private int mAnimationDuration;
    private Context mContext;
    private float mCurrentOffset;
    private int mCurrentPosition;
    private int mCurrentViewNumber;
    private Interpolator mDecelerateInterpolater;
    private int mFlingDirection;
    private int mGalleryWidth;
    private GestureDetector mGestureDetector;
    private boolean mIsDragging;
    private boolean mIsGalleryCircular;
    private boolean mIsTouched;
    private long mScrollTimestamp;
    private float mSnapBorderRatio;
    private int mViewPaddingWidth;
    private FlingGalleryView[] mViews;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FlingGalleryAnimation extends Animation {
        private boolean mIsAnimationInProgres = false;
        private int mRelativeViewNumber = 0;
        private int mInitialOffset = 0;
        private int mTargetOffset = 0;
        private int mTargetDistance = 0;

        public FlingGalleryAnimation() {
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f2, Transformation transformation) {
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            int i2 = this.mInitialOffset + ((int) (this.mTargetDistance * f2));
            for (int i3 = 0; i3 < 3; i3++) {
                if ((this.mTargetDistance > 0 && i3 != FlingGallery.this.getNextViewNumber(this.mRelativeViewNumber)) || (this.mTargetDistance < 0 && i3 != FlingGallery.this.getPrevViewNumber(this.mRelativeViewNumber))) {
                    FlingGallery.this.mViews[i3].setOffset(i2, 0, this.mRelativeViewNumber);
                }
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j2, Transformation transformation) {
            if (super.getTransformation(j2, transformation)) {
                return (FlingGallery.this.mIsTouched || FlingGallery.this.mIsDragging) ? false : true;
            }
            FlingGallery.this.mViews[0].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
            FlingGallery.this.mViews[1].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
            FlingGallery.this.mViews[2].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
            this.mIsAnimationInProgres = false;
            return false;
        }

        public void prepareAnimation(int i2) {
            int i3 = this.mRelativeViewNumber;
            if (i3 != i2) {
                if (this.mIsAnimationInProgres) {
                    if ((this.mTargetDistance < 0 ? (char) 1 : '\uffff') == (i2 == FlingGallery.this.getPrevViewNumber(i3) ? (char) 1 : '\uffff')) {
                        FlingGallery.this.mViews[0].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
                        FlingGallery.this.mViews[1].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
                        FlingGallery.this.mViews[2].setOffset(this.mTargetOffset, 0, this.mRelativeViewNumber);
                    }
                }
                this.mRelativeViewNumber = i2;
            }
            this.mInitialOffset = FlingGallery.this.mViews[this.mRelativeViewNumber].getCurrentOffset();
            FlingGallery flingGallery = FlingGallery.this;
            int i4 = this.mRelativeViewNumber;
            int viewOffset = flingGallery.getViewOffset(i4, i4);
            this.mTargetOffset = viewOffset;
            this.mTargetDistance = viewOffset - this.mInitialOffset;
            setDuration(FlingGallery.this.mAnimationDuration);
            setInterpolator(FlingGallery.this.mDecelerateInterpolater);
            this.mIsAnimationInProgres = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FlingGalleryView {
        private View mExternalView = null;
        private LinearLayout mInternalLayout;
        private FrameLayout mInvalidLayout;
        private FrameLayout mParentLayout;
        private int mViewNumber;

        public FlingGalleryView(int i2, FrameLayout frameLayout) {
            this.mInvalidLayout = null;
            this.mInternalLayout = null;
            this.mViewNumber = i2;
            this.mParentLayout = frameLayout;
            FrameLayout frameLayout2 = new FrameLayout(FlingGallery.this.mContext);
            this.mInvalidLayout = frameLayout2;
            frameLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            LinearLayout linearLayout = new LinearLayout(FlingGallery.this.mContext);
            this.mInternalLayout = linearLayout;
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.mParentLayout.addView(this.mInternalLayout);
        }

        public int getCurrentOffset() {
            return this.mInternalLayout.getScrollX();
        }

        public void recycleView(int i2) {
            View view = this.mExternalView;
            if (view != null) {
                this.mInternalLayout.removeView(view);
            }
            if (FlingGallery.this.mAdapter != null) {
                if (i2 >= FlingGallery.this.getFirstPosition() && i2 <= FlingGallery.this.getLastPosition()) {
                    this.mExternalView = FlingGallery.this.mAdapter.getView(i2, this.mExternalView, this.mInternalLayout);
                } else {
                    this.mExternalView = this.mInvalidLayout;
                }
            }
            View view2 = this.mExternalView;
            if (view2 != null) {
                this.mInternalLayout.addView(view2, new LinearLayout.LayoutParams(-1, -1));
            }
        }

        public void requestFocus() {
            this.mInternalLayout.requestFocus();
        }

        public void setOffset(int i2, int i3, int i4) {
            this.mInternalLayout.scrollTo(FlingGallery.this.getViewOffset(this.mViewNumber, i4) + i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FlingGestureDetector implements GestureDetector.OnGestureListener {
        private FlingGestureDetector() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            FlingGallery.this.mIsTouched = true;
            FlingGallery.this.mFlingDirection = 0;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 250.0f) {
                if (motionEvent2.getX() - motionEvent.getX() > 120.0f && Math.abs(f2) > 400.0f) {
                    FlingGallery.this.movePrevious();
                }
                if (motionEvent.getX() - motionEvent2.getX() <= 120.0f || Math.abs(f2) <= 400.0f) {
                    return false;
                }
                FlingGallery.this.moveNext();
                return false;
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            FlingGallery.this.mFlingDirection = 0;
            FlingGallery.this.processGesture();
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            FlingGallery.this.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent2.getAction() == 2) {
                if (!FlingGallery.this.mIsDragging) {
                    FlingGallery.this.mIsTouched = true;
                    FlingGallery.this.mIsDragging = true;
                    FlingGallery.this.mFlingDirection = 0;
                    FlingGallery.this.mScrollTimestamp = System.currentTimeMillis();
                    FlingGallery.this.mCurrentOffset = r11.mViews[FlingGallery.this.mCurrentViewNumber].getCurrentOffset();
                }
                float currentTimeMillis = (FlingGallery.this.mGalleryWidth / (FlingGallery.this.mAnimationDuration / 1000.0f)) * (((float) (System.currentTimeMillis() - FlingGallery.this.mScrollTimestamp)) / 1000.0f);
                float x = motionEvent.getX() - motionEvent2.getX();
                float f4 = (-1.0f) * currentTimeMillis;
                if (x < f4) {
                    x = f4;
                }
                if (x <= currentTimeMillis) {
                    currentTimeMillis = x;
                }
                int round = Math.round(FlingGallery.this.mCurrentOffset + currentTimeMillis);
                if (round >= FlingGallery.this.mGalleryWidth) {
                    round = FlingGallery.this.mGalleryWidth;
                }
                if (round <= FlingGallery.this.mGalleryWidth * (-1)) {
                    round = FlingGallery.this.mGalleryWidth * (-1);
                }
                FlingGallery.this.mViews[0].setOffset(round, 0, FlingGallery.this.mCurrentViewNumber);
                FlingGallery.this.mViews[1].setOffset(round, 0, FlingGallery.this.mCurrentViewNumber);
                FlingGallery.this.mViews[2].setOffset(round, 0, FlingGallery.this.mCurrentViewNumber);
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            FlingGallery.this.mFlingDirection = 0;
            return false;
        }
    }

    public FlingGallery(Context context) {
        super(context);
        this.mViewPaddingWidth = 0;
        this.mAnimationDuration = 250;
        this.mSnapBorderRatio = 0.5f;
        this.mIsGalleryCircular = true;
        this.mGalleryWidth = 0;
        this.mIsTouched = false;
        this.mIsDragging = false;
        this.mCurrentOffset = 0.0f;
        this.mScrollTimestamp = 0L;
        this.mFlingDirection = 0;
        this.mCurrentPosition = 0;
        this.mCurrentViewNumber = 0;
        init(context);
    }

    private int getNextPosition(int i2) {
        int i3 = i2 + 1;
        if (i3 > getLastPosition()) {
            return this.mIsGalleryCircular ? getFirstPosition() : getLastPosition() + 1;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNextViewNumber(int i2) {
        if (i2 == 2) {
            return 0;
        }
        return i2 + 1;
    }

    private int getPrevPosition(int i2) {
        int i3 = i2 - 1;
        if (i3 < getFirstPosition()) {
            return this.mIsGalleryCircular ? getLastPosition() : getFirstPosition() - 1;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPrevViewNumber(int i2) {
        if (i2 == 0) {
            return 2;
        }
        return i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewOffset(int i2, int i3) {
        int i4 = this.mGalleryWidth + this.mViewPaddingWidth;
        if (i2 == getPrevViewNumber(i3)) {
            return i4;
        }
        if (i2 == getNextViewNumber(i3)) {
            return i4 * (-1);
        }
        return 0;
    }

    private void init(Context context) {
        this.mContext = context;
        this.mAdapter = null;
        FlingGalleryView[] flingGalleryViewArr = new FlingGalleryView[3];
        this.mViews = flingGalleryViewArr;
        flingGalleryViewArr[0] = new FlingGalleryView(0, this);
        this.mViews[1] = new FlingGalleryView(1, this);
        this.mViews[2] = new FlingGalleryView(2, this);
        this.mAnimation = new FlingGalleryAnimation();
        GestureDetector gestureDetector = new GestureDetector(new FlingGestureDetector());
        this.mGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(true);
        this.mDecelerateInterpolater = AnimationUtils.loadInterpolator(this.mContext, 17432582);
        setStaticTransformationsEnabled(true);
    }

    public int getFirstPosition() {
        return 0;
    }

    public int getGalleryCount() {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return 0;
        }
        return adapter.getCount();
    }

    public int getLastPosition() {
        if (getGalleryCount() == 0) {
            return 0;
        }
        return getGalleryCount() - 1;
    }

    void moveNext() {
        this.mFlingDirection = -1;
        processGesture();
    }

    void movePrevious() {
        this.mFlingDirection = 1;
        processGesture();
    }

    public boolean onGalleryTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 && (this.mIsTouched || this.mIsDragging)) {
            processScrollSnap();
            processGesture();
        }
        return onTouchEvent;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 21) {
            movePrevious();
            return true;
        } else if (i2 != 22) {
            return super.onKeyDown(i2, keyEvent);
        } else {
            moveNext();
            return true;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mGalleryWidth = i4 - i2;
        if (z) {
            this.mViews[0].setOffset(0, 0, this.mCurrentViewNumber);
            this.mViews[1].setOffset(0, 0, this.mCurrentViewNumber);
            this.mViews[2].setOffset(0, 0, this.mCurrentViewNumber);
        }
    }

    void processGesture() {
        int i2;
        int i3;
        int i4 = this.mCurrentViewNumber;
        this.mIsTouched = false;
        this.mIsDragging = false;
        if (this.mFlingDirection <= 0 || (this.mCurrentPosition <= getFirstPosition() && !this.mIsGalleryCircular)) {
            i2 = 0;
            i3 = 0;
        } else {
            i4 = getPrevViewNumber(this.mCurrentViewNumber);
            this.mCurrentPosition = getPrevPosition(this.mCurrentPosition);
            i2 = getNextViewNumber(this.mCurrentViewNumber);
            i3 = getPrevPosition(this.mCurrentPosition);
        }
        if (this.mFlingDirection < 0 && (this.mCurrentPosition < getLastPosition() || this.mIsGalleryCircular)) {
            i4 = getNextViewNumber(this.mCurrentViewNumber);
            this.mCurrentPosition = getNextPosition(this.mCurrentPosition);
            i2 = getPrevViewNumber(this.mCurrentViewNumber);
            i3 = getNextPosition(this.mCurrentPosition);
        }
        if (i4 != this.mCurrentViewNumber) {
            this.mCurrentViewNumber = i4;
            this.mViews[i2].recycleView(i3);
        }
        this.mViews[this.mCurrentViewNumber].requestFocus();
        this.mAnimation.prepareAnimation(this.mCurrentViewNumber);
        startAnimation(this.mAnimation);
        this.mFlingDirection = 0;
    }

    void processScrollSnap() {
        int i2 = this.mGalleryWidth;
        int i3 = i2 - ((int) (i2 * this.mSnapBorderRatio));
        int currentOffset = this.mViews[this.mCurrentViewNumber].getCurrentOffset();
        if (currentOffset <= i3 * (-1)) {
            this.mFlingDirection = 1;
        }
        if (currentOffset >= i3) {
            this.mFlingDirection = -1;
        }
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
        this.mCurrentPosition = 0;
        this.mCurrentViewNumber = 0;
        this.mViews[0].recycleView(0);
        this.mViews[1].recycleView(getNextPosition(this.mCurrentPosition));
        this.mViews[2].recycleView(getPrevPosition(this.mCurrentPosition));
        this.mViews[0].setOffset(0, 0, this.mCurrentViewNumber);
        this.mViews[1].setOffset(0, 0, this.mCurrentViewNumber);
        this.mViews[2].setOffset(0, 0, this.mCurrentViewNumber);
    }

    public void setAnimationDuration(int i2) {
        this.mAnimationDuration = i2;
    }

    public void setIsGalleryCircular(boolean z) {
        if (this.mIsGalleryCircular != z) {
            this.mIsGalleryCircular = z;
            if (this.mCurrentPosition == getFirstPosition()) {
                this.mViews[getPrevViewNumber(this.mCurrentViewNumber)].recycleView(getPrevPosition(this.mCurrentPosition));
            }
            if (this.mCurrentPosition == getLastPosition()) {
                this.mViews[getNextViewNumber(this.mCurrentViewNumber)].recycleView(getNextPosition(this.mCurrentPosition));
            }
        }
    }

    public void setPaddingWidth(int i2) {
        this.mViewPaddingWidth = i2;
    }

    public void setSnapBorderRatio(float f2) {
        this.mSnapBorderRatio = f2;
    }

    public FlingGallery(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViewPaddingWidth = 0;
        this.mAnimationDuration = 250;
        this.mSnapBorderRatio = 0.5f;
        this.mIsGalleryCircular = true;
        this.mGalleryWidth = 0;
        this.mIsTouched = false;
        this.mIsDragging = false;
        this.mCurrentOffset = 0.0f;
        this.mScrollTimestamp = 0L;
        this.mFlingDirection = 0;
        this.mCurrentPosition = 0;
        this.mCurrentViewNumber = 0;
        init(context);
    }

    public FlingGallery(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mViewPaddingWidth = 0;
        this.mAnimationDuration = 250;
        this.mSnapBorderRatio = 0.5f;
        this.mIsGalleryCircular = true;
        this.mGalleryWidth = 0;
        this.mIsTouched = false;
        this.mIsDragging = false;
        this.mCurrentOffset = 0.0f;
        this.mScrollTimestamp = 0L;
        this.mFlingDirection = 0;
        this.mCurrentPosition = 0;
        this.mCurrentViewNumber = 0;
        init(context);
    }
}
