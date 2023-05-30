package com.jd.stat.common;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes18.dex */
public class i {
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
        if (r6 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b7, code lost:
        if (r6 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b9, code lost:
        r6.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bc, code lost:
        return r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00cc  */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] a() {
        /*
            Method dump skipped, instructions count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.i.a():java.lang.String[]");
    }

    private static Pair<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new Pair<>("", "");
        }
        String[] split = str.split(ContainerUtils.KEY_VALUE_DELIMITER);
        if (split.length != 2) {
            return new Pair<>("", "");
        }
        return new Pair<>(split[0], split[1]);
    }
}
