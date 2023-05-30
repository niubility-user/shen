package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: classes6.dex */
public class JDDrawerLayout extends DrawerLayout {
    private static final String TAG = "JDDrawerLayout";
    private boolean mIsDisallowIntercept;

    public JDDrawerLayout(Context context) {
        super(context);
        this.mIsDisallowIntercept = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (motionEvent.getPointerCount() > 1 && this.mIsDisallowIntercept) {
                requestDisallowInterceptTouchEvent(false);
                boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
                requestDisallowInterceptTouchEvent(true);
                return dispatchTouchEvent;
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            return false;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            return false;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.mIsDisallowIntercept = z;
        super.requestDisallowInterceptTouchEvent(z);
    }

    public JDDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsDisallowIntercept = false;
    }

    public JDDrawerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsDisallowIntercept = false;
    }
}
