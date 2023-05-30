package com.jingdong.c.b.d;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.c.b.f;
import com.jingdong.c.b.h;
import com.jingdong.c.b.i;
import com.jingdong.c.b.j;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes10.dex */
public final class b implements f {
    @Override // com.jingdong.c.b.f
    public final j a(f.a aVar) {
        j jVar;
        h.a("Enter CacheInterceptor intercept()");
        com.jingdong.c.b.b a = aVar.a();
        i.c(a);
        a.c();
        String str = i.a().a.get("uuid");
        if (TextUtils.isEmpty(str)) {
            String b = i.a().b("imei");
            String b2 = i.a().b(Constant.KEY_MAC);
            if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
                str = String.format("%s-%s", b, b2);
            }
        }
        if (!TextUtils.isEmpty(str)) {
            String b3 = i.a().b("imei");
            String b4 = i.a().b(Constant.KEY_MAC);
            Pair pair = (TextUtils.isEmpty(b3) && TextUtils.isEmpty(b4)) ? null : new Pair(b3, b4);
            if ((pair == null || TextUtils.isEmpty((CharSequence) pair.first) || TextUtils.isEmpty((CharSequence) pair.second)) ? false : TextUtils.equals(str, String.format("%s-%s", pair.first, pair.second))) {
                jVar = new j(a, true);
            } else if (!a.g()) {
                jVar = new j(a, false);
            }
            jVar.b = str;
            jVar.f12306c = true;
            return jVar;
        }
        return aVar.b();
    }
}
