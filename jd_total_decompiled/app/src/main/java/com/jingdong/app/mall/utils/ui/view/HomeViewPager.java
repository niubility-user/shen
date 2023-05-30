package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class HomeViewPager extends ViewPager implements View.OnTouchListener {

    /* renamed from: g  reason: collision with root package name */
    private ViewGroup f11974g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f11975h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11976i;

    public HomeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11975h = true;
        this.f11976i = true;
        setOnTouchListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.f11975h) {
            super.onAttachedToWindow();
        }
        this.f11975h = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            ViewGroup viewGroup = this.f11974g;
            if (viewGroup != null) {
                viewGroup.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            ViewGroup viewGroup2 = this.f11974g;
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

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (this.f11976i) {
            super.scrollTo(i2, i3);
        }
    }
}
