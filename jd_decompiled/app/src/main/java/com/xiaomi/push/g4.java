package com.xiaomi.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;

/* loaded from: classes11.dex */
public class g4 extends h4 {

    /* renamed from: m  reason: collision with root package name */
    private int f18641m;

    /* renamed from: n  reason: collision with root package name */
    private Bitmap f18642n;
    private CharSequence o;
    private PendingIntent p;
    private int q;
    private int r;

    public g4(Context context, int i2, String str) {
        super(context, i2, str);
        this.f18641m = 16777216;
        this.q = 16777216;
        this.r = 16777216;
    }

    private Drawable G(int i2, int i3, int i4, float f2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(new float[]{f2, f2, f2, f2, f2, f2, f2, f2}, null, null));
        shapeDrawable.getPaint().setColor(i2);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawable.setIntrinsicWidth(i3);
        shapeDrawable.setIntrinsicHeight(i4);
        return shapeDrawable;
    }

    private void K(RemoteViews remoteViews, int i2, int i3, int i4, boolean z) {
        int i5 = i(6.0f);
        remoteViews.setViewPadding(i2, i5, 0, i5, 0);
        int i6 = z ? -1 : -16777216;
        remoteViews.setTextColor(i3, i6);
        remoteViews.setTextColor(i4, i6);
    }

    public g4 H(Bitmap bitmap) {
        if (y() && bitmap != null) {
            if (bitmap.getWidth() != 984 || bitmap.getHeight() < 177 || bitmap.getHeight() > 207) {
                g.j.a.a.a.c.o("colorful notification bg image resolution error, must [984*177, 984*207]");
            } else {
                this.f18642n = bitmap;
            }
        }
        return this;
    }

    public g4 I(CharSequence charSequence, PendingIntent pendingIntent) {
        if (y()) {
            super.m(0, charSequence, pendingIntent);
            this.o = charSequence;
            this.p = pendingIntent;
        }
        return this;
    }

    public g4 J(String str) {
        if (y() && !TextUtils.isEmpty(str)) {
            try {
                this.q = Color.parseColor(str);
            } catch (Exception unused) {
                g.j.a.a.a.c.o("parse colorful notification button bg color error");
            }
        }
        return this;
    }

    public g4 L(String str) {
        if (y() && !TextUtils.isEmpty(str)) {
            try {
                this.f18641m = Color.parseColor(str);
            } catch (Exception unused) {
                g.j.a.a.a.c.o("parse colorful notification bg color error");
            }
        }
        return this;
    }

    public g4 M(String str) {
        if (y() && !TextUtils.isEmpty(str)) {
            try {
                this.r = Color.parseColor(str);
            } catch (Exception unused) {
                g.j.a.a.a.c.o("parse colorful notification image text color error");
            }
        }
        return this;
    }

    @Override // com.xiaomi.push.h4, com.xiaomi.push.f4
    public void h() {
        RemoteViews l2;
        Bitmap bitmap;
        boolean z;
        RemoteViews l3;
        RemoteViews l4;
        Drawable G;
        if (!y()) {
            x();
            return;
        }
        super.h();
        Resources resources = c().getResources();
        String packageName = c().getPackageName();
        int a = a(resources, "icon", "id", packageName);
        if (this.d == null) {
            r(a);
        } else {
            l().setImageViewBitmap(a, this.d);
        }
        int a2 = a(resources, "title", "id", packageName);
        int a3 = a(resources, "content", "id", packageName);
        l().setTextViewText(a2, this.f18693e);
        l().setTextViewText(a3, this.f18694f);
        if (!TextUtils.isEmpty(this.o)) {
            int a4 = a(resources, "buttonContainer", "id", packageName);
            int a5 = a(resources, "button", "id", packageName);
            int a6 = a(resources, "buttonBg", "id", packageName);
            l().setViewVisibility(a4, 0);
            l().setTextViewText(a5, this.o);
            l().setOnClickPendingIntent(a4, this.p);
            if (this.q != 16777216) {
                int i2 = i(70.0f);
                int i3 = i(29.0f);
                l().setImageViewBitmap(a6, com.xiaomi.push.service.n.n(G(this.q, i2, i3, i3 / 2.0f)));
                l().setTextColor(a5, u(this.q) ? -1 : -16777216);
            }
        }
        int a7 = a(resources, "bg", "id", packageName);
        int a8 = a(resources, "container", "id", packageName);
        if (this.f18641m != 16777216) {
            if (a8.b(c()) >= 10) {
                l4 = l();
                G = G(this.f18641m, R2.attr.height, 192, 30.0f);
            } else {
                l4 = l();
                G = G(this.f18641m, R2.attr.height, 192, 0.0f);
            }
            l4.setImageViewBitmap(a7, com.xiaomi.push.service.n.n(G));
            l3 = l();
            z = u(this.f18641m);
        } else if (this.f18642n == null) {
            if (Build.VERSION.SDK_INT >= 24) {
                l().setViewVisibility(a, 8);
                l().setViewVisibility(a7, 8);
                try {
                    k0.e(this, "setStyle", r9.c(c(), "android.app.Notification$DecoratedCustomViewStyle").getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                    g.j.a.a.a.c.o("load class DecoratedCustomViewStyle failed");
                }
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("miui.customHeight", true);
            d(bundle);
            e(l());
        } else {
            if (a8.b(c()) >= 10) {
                l2 = l();
                bitmap = k(this.f18642n, 30.0f);
            } else {
                l2 = l();
                bitmap = this.f18642n;
            }
            l2.setImageViewBitmap(a7, bitmap);
            Map<String, String> map = this.f18695g;
            if (map != null && this.r == 16777216) {
                M(map.get("notification_image_text_color"));
            }
            int i4 = this.r;
            z = i4 == 16777216 || !u(i4);
            l3 = l();
        }
        K(l3, a8, a2, a3, z);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("miui.customHeight", true);
        d(bundle2);
        e(l());
    }

    @Override // com.xiaomi.push.h4
    protected String q() {
        return "notification_colorful";
    }

    @Override // com.xiaomi.push.h4
    protected boolean t() {
        if (a8.i()) {
            Resources resources = c().getResources();
            String packageName = c().getPackageName();
            return (a(resources, "icon", "id", packageName) == 0 || a(resources, "title", "id", packageName) == 0 || a(resources, "content", "id", packageName) == 0) ? false : true;
        }
        return false;
    }

    @Override // com.xiaomi.push.h4
    protected String w() {
        return "notification_colorful_copy";
    }
}
