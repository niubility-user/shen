package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes11.dex */
public final class x implements d {
    private static String a = "SpCache";
    private static String b = "com.vivo.push.cache";

    /* renamed from: c  reason: collision with root package name */
    private SharedPreferences f18312c;

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        if (this.f18312c == null) {
            this.f18312c = context.getSharedPreferences(b, 0);
            return true;
        }
        return true;
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) {
        SharedPreferences.Editor edit = this.f18312c.edit();
        if (edit != null) {
            edit.putString(str, str2);
            b.a(edit);
            p.d(a, "putString by ".concat(String.valueOf(str)));
            return;
        }
        p.b(a, "putString error by ".concat(String.valueOf(str)));
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        String string = this.f18312c.getString(str, str2);
        p.d(a, "getString " + str + " is " + string);
        return string;
    }

    public final void a() {
        SharedPreferences.Editor edit = this.f18312c.edit();
        if (edit != null) {
            edit.clear();
            b.a(edit);
        }
        p.d(a, "system cache is cleared");
    }
}
