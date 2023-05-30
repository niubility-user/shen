package com.jingdong.manto.l;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.utils.MantoUtils;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes15.dex */
public class f {
    private static final l a = new d();

    public static Bitmap a(String str, com.jingdong.manto.jsapi.coverview.b bVar, String str2, MantoCore mantoCore) {
        if (bVar != null) {
            String a2 = com.jingdong.manto.pkg.b.b.a(str2);
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            String str3 = str + File.pathSeparator + a2;
            l lVar = a;
            Bitmap a3 = lVar.a(str3);
            if (a3 == null) {
                InputStream a4 = bVar.a(mantoCore, a2);
                if (a4 == null) {
                    return null;
                }
                Bitmap a5 = com.jingdong.manto.sdk.b.a(a4);
                lVar.a(str3, a5);
                MantoUtils.qualityClose(a4);
                return a5;
            }
            return a3;
        }
        return null;
    }
}
