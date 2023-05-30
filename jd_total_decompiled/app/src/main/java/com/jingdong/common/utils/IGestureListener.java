package com.jingdong.common.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewParent;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class IGestureListener implements GestureDetector.OnGestureListener {
    private TouchFlingActionListener onListener;
    private ViewParent parent;

    /* loaded from: classes6.dex */
    public interface TouchFlingActionListener {
        void next();

        void previous();

        void startActivity();
    }

    public IGestureListener(ViewParent viewParent, TouchFlingActionListener touchFlingActionListener) {
        this.onListener = touchFlingActionListener;
        this.parent = viewParent;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onDown");
            return false;
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onFling velocityX=" + f2 + ",velocityY=" + f3);
        }
        if (motionEvent != null && motionEvent2 != null) {
            if (motionEvent.getX() - motionEvent2.getX() > 5.0f) {
                this.onListener.previous();
                return false;
            } else if (motionEvent.getX() - motionEvent2.getX() < -5.0f) {
                this.onListener.next();
            }
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onLongPress");
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onScroll distanceX=" + f2 + ",distanceY=" + f3);
        }
        this.parent.requestDisallowInterceptTouchEvent(true);
        if (Math.abs(f2) > Math.abs(f3)) {
            if (motionEvent != null && motionEvent2 != null) {
                if (motionEvent.getX() - motionEvent2.getX() > 50.0f) {
                    this.onListener.previous();
                    return false;
                } else if (motionEvent.getX() - motionEvent2.getX() < -50.0f) {
                    this.onListener.next();
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onShowPress");
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (OKLog.D) {
            OKLog.d("IGestureListener", "onSingleTapUp");
        }
        this.onListener.startActivity();
        return false;
    }
}
