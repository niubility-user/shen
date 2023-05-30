package com.cmic.sso.sdk.c.a;

import com.cmic.sso.sdk.c.b.e;
import com.cmic.sso.sdk.c.b.g;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.Closeable;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class a implements b {
    private static com.cmic.sso.sdk.c.c a;

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(2:2|3)|(13:(2:154|(28:156|8|(2:10|11)(2:152|153)|12|13|(3:15|(5:18|19|20|21|16)|81)|83|(1:(7:90|91|92|93|94|95|96)(1:97))|98|(1:100)|101|102|103|(3:105|106|107)(1:147)|108|110|111|112|113|114|(2:115|(1:117)(1:118))|119|120|(1:122)|123|(1:125)(1:136)|126|(2:134|135)(2:132|133)))|113|114|(3:115|(0)(0)|117)|119|120|(0)|123|(0)(0)|126|(1:128)|134|135)|7|8|(0)(0)|12|13|(0)|83|(2:85|(0)(0))|98|(0)|101|102|103|(0)(0)|108|110|111|112|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(23:1|2|3|(13:(2:154|(28:156|8|(2:10|11)(2:152|153)|12|13|(3:15|(5:18|19|20|21|16)|81)|83|(1:(7:90|91|92|93|94|95|96)(1:97))|98|(1:100)|101|102|103|(3:105|106|107)(1:147)|108|110|111|112|113|114|(2:115|(1:117)(1:118))|119|120|(1:122)|123|(1:125)(1:136)|126|(2:134|135)(2:132|133)))|113|114|(3:115|(0)(0)|117)|119|120|(0)|123|(0)(0)|126|(1:128)|134|135)|7|8|(0)(0)|12|13|(0)|83|(2:85|(0)(0))|98|(0)|101|102|103|(0)(0)|108|110|111|112|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01d9, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01da, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01dd, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01de, code lost:
        r13 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01e8, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e9, code lost:
        r18 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ed, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ee, code lost:
        r18 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0236 A[Catch: all -> 0x02a4, TryCatch #2 {all -> 0x02a4, blocks: (B:108:0x0232, B:110:0x0236, B:112:0x023e, B:114:0x0246), top: B:151:0x0232 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0171 A[EDGE_INSN: B:166:0x0171->B:58:0x0171 BREAK  A[LOOP:1: B:55:0x015d->B:57:0x0163], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008b A[Catch: all -> 0x01e8, Exception -> 0x01ed, TryCatch #12 {Exception -> 0x01ed, all -> 0x01e8, blocks: (B:18:0x0085, B:20:0x008b, B:21:0x0093, B:23:0x0099), top: B:158:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1 A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fd A[Catch: all -> 0x01e4, Exception -> 0x01e6, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0129 A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013d A[Catch: all -> 0x01e4, Exception -> 0x01e6, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Exception -> 0x01e6, all -> 0x01e4, blocks: (B:25:0x00a7, B:26:0x00b1, B:28:0x00b7, B:30:0x00bb, B:33:0x00c1, B:35:0x00c8, B:37:0x00d7, B:39:0x00dc, B:40:0x00fd, B:41:0x0107, B:43:0x0129, B:45:0x0134, B:48:0x013d), top: B:160:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0163 A[Catch: all -> 0x01d4, Exception -> 0x01d7, LOOP:1: B:55:0x015d->B:57:0x0163, LOOP_END, TryCatch #5 {Exception -> 0x01d7, blocks: (B:54:0x015b, B:55:0x015d, B:57:0x0163, B:58:0x0171), top: B:153:0x015b }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01ac  */
    @Override // com.cmic.sso.sdk.c.a.b
    @android.annotation.TargetApi(21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.cmic.sso.sdk.c.c.c r20, com.cmic.sso.sdk.c.d.c r21, com.cmic.sso.sdk.a r22) {
        /*
            Method dump skipped, instructions count: 765
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.c.a.a.a(com.cmic.sso.sdk.c.c.c, com.cmic.sso.sdk.c.d.c, com.cmic.sso.sdk.a):void");
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private String a() {
        return com.cmic.sso.sdk.b.a[0] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[2] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[4] + OrderISVUtil.MONEY_DECIMAL + com.cmic.sso.sdk.b.a[6];
    }

    public synchronized SSLSocketFactory a(g gVar, com.cmic.sso.sdk.a aVar) {
        if (gVar instanceof e) {
            com.cmic.sso.sdk.c.c cVar = new com.cmic.sso.sdk.c.c(HttpsURLConnection.getDefaultSSLSocketFactory(), aVar);
            if (a == null) {
                a = cVar;
            }
            return cVar;
        }
        if (a == null) {
            a = new com.cmic.sso.sdk.c.c(HttpsURLConnection.getDefaultSSLSocketFactory(), aVar);
        }
        return a;
    }
}
