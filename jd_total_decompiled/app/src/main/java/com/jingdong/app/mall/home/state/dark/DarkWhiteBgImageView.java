package com.jingdong.app.mall.home.state.dark;

import android.content.Context;
import android.graphics.Canvas;
import com.jingdong.app.mall.home.base.HomeDraweeView;

/* loaded from: classes4.dex */
public class DarkWhiteBgImageView extends HomeDraweeView {

    /* renamed from: g  reason: collision with root package name */
    private boolean f10864g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f10865h;

    public DarkWhiteBgImageView(Context context) {
        super(context);
        this.f10864g = false;
        this.f10865h = false;
    }

    public void c(boolean z) {
        boolean z2 = this.f10865h;
        this.f10865h = z;
        if (z ^ z2) {
            postInvalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f10865h) {
            canvas.drawColor(-1);
        } else if (a.h() && this.f10864g) {
            canvas.drawColor(-1);
        }
        super.onDraw(canvas);
    }
}
