package com.jd.lib.productdetail.mainimage.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.customview.widget.ViewDragHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;

/* loaded from: classes15.dex */
public class PdTopImageDragLayout extends ConstraintLayout {
    public boolean mDragEnable;
    private ViewDragHelper mDragHelper;
    public boolean mIsDrag;
    public int mTopCoverViewId;
    private SimpleDraweeView mTryReadImage;

    public PdTopImageDragLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDragEnable = false;
        this.mIsDrag = false;
        this.mTopCoverViewId = -1;
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "topimage-sysm-drag", "enable"), DYConstants.DY_TRUE)) {
            this.mDragEnable = true;
        }
        if (this.mDragEnable) {
            this.mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() { // from class: com.jd.lib.productdetail.mainimage.view.PdTopImageDragLayout.1
                public int left = 0;
                public int top = 0;

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public int clampViewPositionHorizontal(@NonNull View view, int i2, int i3) {
                    int paddingLeft = PdTopImageDragLayout.this.getPaddingLeft();
                    return Math.min(Math.max(i2, paddingLeft), (PdTopImageDragLayout.this.getWidth() - view.getWidth()) - PdTopImageDragLayout.this.getPaddingRight());
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
                    View findViewById;
                    Window window;
                    PdTopImageDragLayout pdTopImageDragLayout = PdTopImageDragLayout.this;
                    int i4 = 0;
                    if (pdTopImageDragLayout.mTopCoverViewId != -1) {
                        View decorView = (pdTopImageDragLayout.getContext() == null || !(PdTopImageDragLayout.this.getContext() instanceof Activity) || (window = ((Activity) PdTopImageDragLayout.this.getContext()).getWindow()) == null) ? null : window.getDecorView();
                        if (decorView != null && (findViewById = decorView.findViewById(PdTopImageDragLayout.this.mTopCoverViewId)) != null) {
                            findViewById.getLocationOnScreen(r2);
                            int[] iArr = {0, iArr[1] + findViewById.getHeight()};
                            int[] iArr2 = new int[2];
                            PdTopImageDragLayout.this.getLocationOnScreen(iArr2);
                            i4 = Math.max(iArr[1] - iArr2[1], 0);
                        }
                    }
                    return Math.min(Math.max(i2, PdTopImageDragLayout.this.getPaddingTop() + i4), (PdTopImageDragLayout.this.getHeight() - view.getHeight()) - PdTopImageDragLayout.this.getPaddingBottom());
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public int getViewHorizontalDragRange(@NonNull View view) {
                    return PdTopImageDragLayout.this.getMeasuredWidth() - view.getMeasuredWidth();
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public int getViewVerticalDragRange(@NonNull View view) {
                    return PdTopImageDragLayout.this.getMeasuredHeight() - view.getMeasuredHeight();
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public void onViewPositionChanged(@NonNull View view, int i2, int i3, int i4, int i5) {
                    super.onViewPositionChanged(view, i2, i3, i4, i5);
                    this.left = i2;
                    this.top = i3;
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public void onViewReleased(@NonNull View view, float f2, float f3) {
                    super.onViewReleased(view, f2, f3);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(view.getLayoutParams().width, view.getLayoutParams().height);
                    layoutParams.leftToLeft = 0;
                    layoutParams.topToTop = 0;
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = Math.max(0, this.left - PdTopImageDragLayout.this.getPaddingLeft());
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = this.top;
                    view.setLayoutParams(layoutParams);
                }

                @Override // androidx.customview.widget.ViewDragHelper.Callback
                public boolean tryCaptureView(@NonNull View view, int i2) {
                    return view == PdTopImageDragLayout.this.mTryReadImage;
                }
            });
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mDragEnable && this.mDragHelper.findTopChildUnder((int) motionEvent.getX(), (int) motionEvent.getY()) == this.mTryReadImage) {
            getParent().requestDisallowInterceptTouchEvent(true);
            this.mIsDrag = true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mTryReadImage = (SimpleDraweeView) findViewById(R.id.lib_pd_mainimage_try_read_icon);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mDragEnable) {
            if (!this.mIsDrag) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            return this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mDragEnable && this.mIsDrag) {
            this.mDragHelper.processTouchEvent(motionEvent);
            int action = motionEvent.getAction();
            if (action == 1 || action == 3) {
                this.mIsDrag = false;
            }
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setmTopCoverViewId(int i2) {
        this.mTopCoverViewId = i2;
    }
}
