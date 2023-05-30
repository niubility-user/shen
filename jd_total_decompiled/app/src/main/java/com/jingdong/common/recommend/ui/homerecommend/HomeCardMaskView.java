package com.jingdong.common.recommend.ui.homerecommend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/* loaded from: classes6.dex */
public class HomeCardMaskView extends SimpleDraweeView {
    public HomeCardMaskView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public HomeCardMaskView(Context context) {
        super(context);
    }

    public HomeCardMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HomeCardMaskView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public HomeCardMaskView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
