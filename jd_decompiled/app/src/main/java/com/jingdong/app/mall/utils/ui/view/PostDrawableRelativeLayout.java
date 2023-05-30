package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;

/* loaded from: classes4.dex */
public class PostDrawableRelativeLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    a f11989g;

    /* loaded from: classes4.dex */
    public interface a {
        void a();
    }

    public PostDrawableRelativeLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a aVar = this.f11989g;
        if (aVar != null) {
            aVar.a();
        }
    }

    public PostDrawableRelativeLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
