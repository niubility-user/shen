package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class JDViewPager extends ViewPager {

    /* renamed from: g  reason: collision with root package name */
    private boolean f11860g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f11861h;

    /* renamed from: i  reason: collision with root package name */
    private float f11862i;

    /* renamed from: j  reason: collision with root package name */
    private float f11863j;

    /* renamed from: k  reason: collision with root package name */
    private float f11864k;

    /* renamed from: l  reason: collision with root package name */
    private float f11865l;

    /* renamed from: m  reason: collision with root package name */
    private int f11866m;

    public JDViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11860g = true;
        this.f11861h = true;
        this.f11862i = 0.5f;
        this.f11863j = 2.0f;
        this.f11866m = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.f11860g) {
            super.onAttachedToWindow();
        }
        this.f11860g = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                this.f11864k = motionEvent.getX();
                this.f11865l = motionEvent.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
            } else if (action == 1) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (action == 2) {
                float x = motionEvent.getX() - this.f11864k;
                float y = motionEvent.getY() - this.f11865l;
                if (Math.abs(x) > this.f11866m && Math.abs(y) > this.f11866m) {
                    Log.s("JDViewPager --> onInterceptTouchEvent ACTION_MOVE : Math.abs(x - mDownX) : " + Math.abs(x) + " | Math.abs(y - mDownY) : " + Math.abs(y));
                    if (Math.abs(y) / Math.abs(x) > this.f11863j) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (Math.abs(y) / Math.abs(x) > this.f11862i) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
            } else if (action == 3) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("JDViewPager", "onInterceptTouchEvent : JDViewPager ---> ");
                e2.printStackTrace();
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("JDViewPager", " ---> ");
                e2.printStackTrace();
            }
            return false;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (this.f11861h) {
            super.scrollTo(i2, i3);
        }
    }
}
