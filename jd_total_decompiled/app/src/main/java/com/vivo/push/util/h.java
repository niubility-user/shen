package com.vivo.push.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* loaded from: classes11.dex */
public final class h implements BaseNotifyDataAdapter {

    /* renamed from: e  reason: collision with root package name */
    private static int f18301e;

    /* renamed from: f  reason: collision with root package name */
    private static int f18302f;
    private Resources a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f18303c;
    private String d;

    private static boolean a(int i2) {
        return (i2 == -1 || i2 == 0) ? false : true;
    }

    private static boolean a(String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            p.d("DefaultNotifyDataAdapter", "systemVersion is not suit ");
            return false;
        }
        return true;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultNotifyIcon() {
        if (a(f18301e)) {
            return f18301e;
        }
        String str = this.d;
        int a = !a(str) ? -1 : a(str, "_notifyicon");
        f18301e = a;
        if (a(a)) {
            return f18301e;
        }
        for (String str2 = this.f18303c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            int identifier = this.a.getIdentifier("vivo_push_rom" + str2 + "_notifyicon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.a.getIdentifier("vivo_push_notifyicon", "drawable", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getDefaultSmallIconId() {
        if (a(f18302f)) {
            return f18302f;
        }
        String str = this.d;
        int a = !a(str) ? -1 : a(str, "_icon");
        f18302f = a;
        if (a(a)) {
            return f18302f;
        }
        for (String str2 = this.f18303c; !TextUtils.isEmpty(str2); str2 = str2.substring(0, str2.length() - 1)) {
            int identifier = this.a.getIdentifier("vivo_push_rom" + str2 + "_icon", "drawable", this.b);
            if (identifier > 0) {
                return identifier;
            }
        }
        return this.a.getIdentifier("vivo_push_icon", "drawable", this.b);
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final int getNotifyMode(InsideNotificationItem insideNotificationItem) {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 1;
    }

    @Override // com.vivo.push.util.BaseNotifyDataAdapter
    public final void init(Context context) {
        this.b = context.getPackageName();
        this.a = context.getResources();
        this.f18303c = j.a();
        this.d = Build.VERSION.RELEASE;
    }

    private int a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String[] split = str.split("\\.");
            if (split != null && split.length > 0) {
                str = split[0];
            }
            try {
                for (int parseInt = Integer.parseInt(str); parseInt > 0; parseInt--) {
                    String str3 = "vivo_push_ard" + parseInt + str2;
                    p.c("DefaultNotifyDataAdapter", "get notify icon : ".concat(String.valueOf(str3)));
                    int identifier = this.a.getIdentifier(str3, "drawable", this.b);
                    if (identifier > 0) {
                        p.c("DefaultNotifyDataAdapter", "find notify icon : ".concat(String.valueOf(str3)));
                        return identifier;
                    }
                }
            } catch (Exception e2) {
                p.a("DefaultNotifyDataAdapter", e2);
            }
        }
        return -1;
    }
}
