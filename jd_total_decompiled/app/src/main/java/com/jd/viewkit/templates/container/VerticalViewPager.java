package com.jd.viewkit.templates.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes18.dex */
public class VerticalViewPager extends ViewPager {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class VerticalPageTransformer implements ViewPager.PageTransformer {
        private VerticalPageTransformer() {
        }

        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(View view, float f2) {
            if (f2 < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f2 <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(view.getWidth() * (-f2));
                view.setTranslationY(f2 * view.getHeight());
            } else {
                view.setAlpha(0.0f);
            }
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(2);
    }

    private MotionEvent swapXY(MotionEvent motionEvent) {
        float width = getWidth();
        float height = getHeight();
        motionEvent.setLocation((motionEvent.getY() / height) * width, (motionEvent.getX() / width) * height);
        return motionEvent;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 6) {
            swapXY(motionEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 6) {
            swapXY(motionEvent);
            return true;
        }
        return false;
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
