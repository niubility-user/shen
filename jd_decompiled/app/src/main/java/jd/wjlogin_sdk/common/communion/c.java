package jd.wjlogin_sdk.common.communion;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import jd.wjlogin_sdk.util.p;

/* loaded from: classes11.dex */
public class c {
    private static final String a = "WJLogin.ShareKeysUtil";

    public static List<String> a() {
        ArrayList arrayList = new ArrayList();
        String[] e2 = jd.wjlogin_sdk.config.a.c().e();
        if (e2 != null && e2.length > 0) {
            for (String str : e2) {
                p.b(a, "uri = " + str);
                if (!TextUtils.isEmpty(str)) {
                    String a2 = b.a(jd.wjlogin_sdk.common.b.a(), str);
                    p.b(a, "key = " + a2);
                    if (!TextUtils.isEmpty(a2)) {
                        arrayList.add(a2);
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<String> b() {
        String packageName = jd.wjlogin_sdk.common.b.a().getPackageName();
        ArrayList arrayList = new ArrayList();
        String[] e2 = jd.wjlogin_sdk.config.a.c().e();
        if (e2 != null && e2.length > 0) {
            for (String str : e2) {
                p.b(a, "uri = " + str);
                if (!TextUtils.isEmpty(str) && !packageName.equals(str)) {
                    String a2 = b.a(jd.wjlogin_sdk.common.b.a(), str);
                    p.b(a, "key = " + a2);
                    if (!TextUtils.isEmpty(a2)) {
                        arrayList.add(a2);
                    }
                }
            }
        }
        return arrayList;
    }
}
