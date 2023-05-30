package com.jingdong.app.mall.home.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;

/* loaded from: classes4.dex */
public class HomeImageView extends AppCompatImageView {
    public HomeImageView(Context context) {
        super(context);
        a();
    }

    private void a() {
        if (k.v() && f.p0()) {
            f.o("inSubThread: " + Log.getStackTraceString(new Throwable()));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i2) {
        try {
            super.setImageResource(i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        a();
    }

    public HomeImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HomeImageView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
