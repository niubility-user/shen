package com.jingdong.c.b.d;

import android.text.TextUtils;
import com.jingdong.c.b.f;
import com.jingdong.c.b.h;
import com.jingdong.c.b.i;
import com.jingdong.c.b.j;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes10.dex */
public final class a implements f {
    private static String b() {
        String str;
        Exception e2;
        try {
            str = BaseInfo.getAndroidId();
            try {
            } catch (Exception e3) {
                e2 = e3;
                h.b(e2);
                return str;
            }
        } catch (Exception e4) {
            str = "";
            e2 = e4;
        }
        if (TextUtils.isEmpty(str) || TextUtils.equals("9774d56d682e549c", str)) {
            return "";
        }
        if (str.length() < 15) {
            return "";
        }
        return str;
    }

    @Override // com.jingdong.c.b.f
    public final j a(f.a aVar) {
        h.a("Enter AndroidIDInterceptor intercept()");
        com.jingdong.c.b.b a = aVar.a();
        i.c(a);
        String b = i.a().b("androidId");
        if (TextUtils.isEmpty(b)) {
            a.b();
            b = b();
        }
        if (TextUtils.isEmpty(b)) {
            return aVar.b();
        }
        i.a().d("androidId", b);
        j jVar = new j(a, false);
        jVar.b = b;
        jVar.f12306c = false;
        return jVar;
    }
}
