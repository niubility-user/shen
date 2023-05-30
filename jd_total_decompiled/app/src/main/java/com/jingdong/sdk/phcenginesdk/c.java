package com.jingdong.sdk.phcenginesdk;

import android.content.Context;
import com.jd.phc.e;
import java.util.Map;

/* loaded from: classes12.dex */
class c implements com.jingdong.sdk.phcenginesdk.a {
    private static e a;

    /* loaded from: classes12.dex */
    class a implements e.a {
        a(c cVar) {
        }

        @Override // com.jd.phc.e.a
        public void a(String str, String str2) {
            String str3 = "errorCode=" + str + ", desc=" + str2;
        }
    }

    @Override // com.jingdong.sdk.phcenginesdk.a
    public Map<String, String> a(Context context, String str) {
        return e.c(context).a(str);
    }

    @Override // com.jingdong.sdk.phcenginesdk.a
    public String b(Context context, Map<String, String> map) {
        return e.c(context).b(map, e.b.RC4_CRC32CHECKSUM);
    }

    @Override // com.jingdong.sdk.phcenginesdk.a
    public void c(Context context, d dVar) {
        try {
            if (a == null) {
                a = e.c(context);
                if (dVar == null) {
                    e.c(context).e(new a(this));
                } else {
                    e.c(context).e(dVar);
                }
                e.c(context).g(false);
            }
        } catch (Throwable unused) {
        }
    }
}
