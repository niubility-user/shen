package com.jingdong.manto.widget.input;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class g {
    public final Activity a;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public int f14457c = 0;

    public g(Activity activity) {
        this.a = activity;
    }

    public static boolean a(Context context) {
        String str;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, "qemu.hw.mainkeys");
        } catch (Exception unused) {
        }
        if ("1".equals(str)) {
            return false;
        }
        if ("0".equals(str)) {
            return true;
        }
        return z;
    }

    public final void a() {
        if (!this.b || this.a.isFinishing() || Build.VERSION.SDK_INT >= 20) {
            return;
        }
        x a = x.a(this.a);
        if (a == null) {
            MantoLog.w("FixInputIssuesActivityH", "fixLayoutHeightIfNeed get null rootLayout");
        } else {
            a(a);
        }
    }

    public final void a(x xVar) {
        Point point2 = new Point();
        this.a.getWindowManager().getDefaultDisplay().getSize(point2);
        int visibleTopPosition = MantoDensityUtils.getVisibleTopPosition(this.a);
        int i2 = point2.y;
        if (this.a.getWindow() != null && (this.a.getWindow().getAttributes().flags & 1024) > 0) {
            visibleTopPosition = 0;
        }
        int i3 = i2 - visibleTopPosition;
        MantoLog.i("FixInputIssuesActivityH", String.format("fixLayoutHeightBelow20 forceHeight %d", Integer.valueOf(i3)));
        xVar.setForceHeight(i3);
    }
}
