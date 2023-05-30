package com.xiaomi.push;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.Map;

@SuppressLint({"NewApi"})
/* loaded from: classes11.dex */
public class f4 extends Notification.Builder {
    private Context a;

    public f4(Context context) {
        super(context);
        this.a = context;
    }

    public int a(Resources resources, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return resources.getIdentifier(str, str2, str3);
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder addExtras(Bundle bundle) {
        d(bundle);
        return this;
    }

    public final int b(String str) {
        return a(c().getResources(), str, "id", c().getPackageName());
    }

    @Override // android.app.Notification.Builder
    public Notification build() {
        h();
        return super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context c() {
        return this.a;
    }

    public f4 d(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 20) {
            super.addExtras(bundle);
        }
        return this;
    }

    public f4 e(RemoteViews remoteViews) {
        if (Build.VERSION.SDK_INT >= 24) {
            super.setCustomContentView(remoteViews);
        } else {
            super.setContent(remoteViews);
        }
        return this;
    }

    public f4 f(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                k0.e(this, "setColor", Integer.valueOf(Color.parseColor(str)));
            } catch (Exception e2) {
                g.j.a.a.a.c.D("fail to set color. " + e2);
            }
        }
        return this;
    }

    public f4 g(Map<String, String> map) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
    }

    @Override // android.app.Notification.Builder
    public /* synthetic */ Notification.Builder setCustomContentView(RemoteViews remoteViews) {
        e(remoteViews);
        return this;
    }
}
