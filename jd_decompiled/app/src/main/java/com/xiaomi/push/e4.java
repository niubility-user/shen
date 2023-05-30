package com.xiaomi.push;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.Map;

/* loaded from: classes11.dex */
public class e4 extends h4 {

    /* renamed from: m  reason: collision with root package name */
    private Bitmap f18563m;

    /* renamed from: n  reason: collision with root package name */
    private Bitmap f18564n;
    private int o;

    public e4(Context context, String str) {
        super(context, str);
        this.o = 16777216;
    }

    public e4 G(Bitmap bitmap) {
        if (y() && bitmap != null) {
            if (bitmap.getWidth() != 984 || 184 > bitmap.getHeight() || bitmap.getHeight() > 1678) {
                g.j.a.a.a.c.o("colorful notification banner image resolution error, must belong to [984*184, 984*1678]");
            } else {
                this.f18563m = bitmap;
            }
        }
        return this;
    }

    public e4 H(String str) {
        if (y() && !TextUtils.isEmpty(str)) {
            try {
                this.o = Color.parseColor(str);
            } catch (Exception unused) {
                g.j.a.a.a.c.o("parse banner notification image text color error");
            }
        }
        return this;
    }

    public e4 I(Bitmap bitmap) {
        if (y() && bitmap != null) {
            this.f18564n = bitmap;
        }
        return this;
    }

    @Override // com.xiaomi.push.h4, com.xiaomi.push.f4
    public void h() {
        RemoteViews l2;
        Bitmap bitmap;
        if (!y() || this.f18563m == null) {
            x();
            return;
        }
        super.h();
        Resources resources = c().getResources();
        String packageName = c().getPackageName();
        int a = a(resources, "bg", "id", packageName);
        if (a8.b(c()) >= 10) {
            l2 = l();
            bitmap = k(this.f18563m, 30.0f);
        } else {
            l2 = l();
            bitmap = this.f18563m;
        }
        l2.setImageViewBitmap(a, bitmap);
        int a2 = a(resources, "icon", "id", packageName);
        if (this.f18564n != null) {
            l().setImageViewBitmap(a2, this.f18564n);
        } else {
            r(a2);
        }
        int a3 = a(resources, "title", "id", packageName);
        l().setTextViewText(a3, this.f18693e);
        Map<String, String> map = this.f18695g;
        if (map != null && this.o == 16777216) {
            H(map.get("notification_image_text_color"));
        }
        RemoteViews l3 = l();
        int i2 = this.o;
        l3.setTextColor(a3, (i2 == 16777216 || !u(i2)) ? -1 : -16777216);
        e(l());
        Bundle bundle = new Bundle();
        bundle.putBoolean("miui.customHeight", true);
        d(bundle);
    }

    @Override // com.xiaomi.push.h4
    /* renamed from: o */
    public h4 setLargeIcon(Bitmap bitmap) {
        return this;
    }

    @Override // com.xiaomi.push.h4
    protected String q() {
        return "notification_banner";
    }

    @Override // com.xiaomi.push.h4, android.app.Notification.Builder
    public /* synthetic */ Notification.Builder setLargeIcon(Bitmap bitmap) {
        setLargeIcon(bitmap);
        return this;
    }

    @Override // com.xiaomi.push.h4
    protected boolean t() {
        if (a8.i()) {
            Resources resources = c().getResources();
            String packageName = c().getPackageName();
            return (a(c().getResources(), "bg", "id", c().getPackageName()) == 0 || a(resources, "icon", "id", packageName) == 0 || a(resources, "title", "id", packageName) == 0 || a8.b(c()) < 9) ? false : true;
        }
        return false;
    }

    @Override // com.xiaomi.push.h4
    protected String w() {
        return null;
    }
}
