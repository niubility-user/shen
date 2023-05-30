package com.jingdong.app.mall.home.floor.view.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/* loaded from: classes4.dex */
public class c extends BitmapDrawable {
    public c(Bitmap bitmap) {
        super(bitmap);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        try {
            super.draw(canvas);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public c(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }
}
