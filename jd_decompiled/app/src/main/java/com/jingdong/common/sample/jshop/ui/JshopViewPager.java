package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class JshopViewPager extends ViewPager implements View.OnTouchListener {
    private boolean mFirstLayout;
    public boolean noScroll;
    private ViewGroup parent;

    public JshopViewPager(Context context) {
        super(context);
        this.mFirstLayout = true;
        this.noScroll = false;
        setOnTouchListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mFirstLayout) {
            super.onAttachedToWindow();
        }
        this.mFirstLayout = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            ViewGroup viewGroup = this.parent;
            if (viewGroup != null) {
                viewGroup.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 != null) {
                viewGroup2.requestDisallowInterceptTouchEvent(false);
            }
        }
        try {
            onTouchEvent(motionEvent);
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setParent(ViewGroup viewGroup) {
        this.parent = viewGroup;
    }

    public JshopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.noScroll = false;
        setOnTouchListener(this);
    }

    public JshopViewPager(Context context, ViewGroup viewGroup) {
        super(context);
        this.mFirstLayout = true;
        this.noScroll = false;
        this.parent = viewGroup;
        setOnTouchListener(this);
    }
}
