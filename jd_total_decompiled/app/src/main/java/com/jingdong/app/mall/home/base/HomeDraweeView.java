package com.jingdong.app.mall.home.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;

/* loaded from: classes4.dex */
public class HomeDraweeView extends SimpleDraweeView {
    public HomeDraweeView(Context context) {
        super(context);
        checkThread();
    }

    private void checkThread() {
        if (k.v() && f.p0()) {
            f.o("inSubThread: " + Log.getStackTraceString(new Throwable()));
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        checkThread();
    }

    public HomeDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        checkThread();
    }

    public HomeDraweeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        checkThread();
    }
}
