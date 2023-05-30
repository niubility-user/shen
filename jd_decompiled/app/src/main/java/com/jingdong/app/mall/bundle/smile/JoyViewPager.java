package com.jingdong.app.mall.bundle.smile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes3.dex */
public class JoyViewPager extends ViewPager {
    private static final String TAG = "JoyViewPager";

    public JoyViewPager(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            int currentItem = getCurrentItem();
            String str = "curPosition:=" + currentItem;
            if (currentItem != getAdapter().getCount() - 1 && currentItem != 0) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public JoyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
