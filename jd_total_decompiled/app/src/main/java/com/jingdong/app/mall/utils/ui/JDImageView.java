package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class JDImageView extends SimpleDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private float f11849g;

    public JDImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11849g = context.obtainStyledAttributes(attributeSet, R.styleable.image).getFloat(0, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        float f2 = this.f11849g;
        if (f2 >= 1.0f) {
            setMeasuredDimension(size, Math.round(size / f2));
        } else {
            setMeasuredDimension(Math.round(size2 * f2), size2);
        }
    }

    @Override // com.facebook.drawee.view.DraweeView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return false;
    }
}
