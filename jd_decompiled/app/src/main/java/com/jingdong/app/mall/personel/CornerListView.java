package com.jingdong.app.mall.personel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class CornerListView extends ListView {
    public CornerListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int pointToPosition;
        if (motionEvent.getAction() == 0 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1) {
            if (pointToPosition == 0) {
                if (pointToPosition == getAdapter().getCount() - 1) {
                    setSelector(R.drawable.more_acitivity_item_selector_top_corners);
                } else {
                    setSelector(R.drawable.more_acitivity_item_selector_top_corners);
                }
            } else if (pointToPosition == getAdapter().getCount() - 1) {
                setSelector(R.drawable.more_activity_item_selector_bottom_corners);
            } else {
                setSelector(R.drawable.more_activity_item_selector_no_corners);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public CornerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
