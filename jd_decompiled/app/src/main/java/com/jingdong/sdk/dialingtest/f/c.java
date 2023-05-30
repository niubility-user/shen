package com.jingdong.sdk.dialingtest.f;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.dialingtest.c.b.b;
import com.jingdong.sdk.dialingtest.c.c.d;
import com.jingdong.sdk.dialingtest.d.b.b;
import java.net.InetAddress;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class a implements d.b {
        final /* synthetic */ com.jingdong.sdk.dialingtest.d.b.b a;
        final /* synthetic */ String b;

        a(com.jingdong.sdk.dialingtest.d.b.b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        @Override // com.jingdong.sdk.dialingtest.c.c.d.b
        public void a(com.jingdong.sdk.dialingtest.c.c.c cVar) {
            JSONObject a;
            String str = "";
            if (cVar != null && cVar.a == 200 && (a = cVar.a()) != null) {
                String optString = a.optString("data", "");
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        str = new JSONObject(optString).optString("localDns", "");
                        com.jingdong.sdk.dialingtest.c.e.a.a("PingTestUtil", "get operator dns ip success ,ip :" + str);
                    } catch (Exception e2) {
                        com.jingdong.sdk.dialingtest.c.e.a.a("PingTestUtil", e2.toString());
                    }
                }
            }
            c.j(this.a, this.b, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class b implements b.a {
        final /* synthetic */ int a;
        final /* synthetic */ com.jingdong.sdk.dialingtest.d.b.a b;

        b(int i2, com.jingdong.sdk.dialingtest.d.b.a aVar) {
            this.a = i2;
            this.b = aVar;
        }

        @Override // com.jingdong.sdk.dialingtest.c.b.b.a
        public void a(com.jingdong.sdk.dialingtest.c.b.a aVar) {
            com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", aVar.toString());
            c.i(aVar, this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.jingdong.sdk.dialingtest.f.c$c  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public static class RunnableC0715c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final String f14813g;

        /* renamed from: h  reason: collision with root package name */
        private final boolean f14814h;

        /* renamed from: i  reason: collision with root package name */
        private final int f14815i;

        /* renamed from: j  reason: collision with root package name */
        private final int f14816j;

        /* renamed from: k  reason: collision with root package name */
        private final com.jingdong.sdk.dialingtest.d.b.a f14817k;

        RunnableC0715c(String str, boolean z, int i2, int i3, com.jingdong.sdk.dialingtest.d.b.a aVar) {
            this.f14813g = str;
            this.f14814h = z;
            this.f14815i = i2;
            this.f14816j = i3;
            this.f14817k = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, Object> l2;
            com.jingdong.sdk.dialingtest.d.b.a aVar = this.f14817k;
            String str = this.f14813g;
            aVar.f14772c = str;
            if (this.f14814h && (l2 = com.jingdong.sdk.dialingtest.f.a.l(str)) != null) {
                String str2 = l2.get("time") == null ? "" : (String) l2.get("time");
                InetAddress[] inetAddressArr = l2.get("remoteInet") == null ? null : (InetAddress[]) l2.get("remoteInet");
                this.f14817k.f14773e = str2;
                if (inetAddressArr != null && inetAddressArr.length >= 1) {
                    str = com.jingdong.sdk.dialingtest.f.a.c(inetAddressArr);
                    if (!TextUtils.isEmpty(str)) {
                        this.f14817k.d = str;
                    }
                }
                com.jingdong.sdk.dialingtest.d.b.a aVar2 = this.f14817k;
                aVar2.f14776h = "-99";
                aVar2.f14777i = "Dns resolve error";
                com.jingdong.sdk.dialingtest.e.a.c.d(aVar2);
                return;
            }
            if (com.jingdong.sdk.dialingtest.f.a.d(str)) {
                c.m(str, this.f14815i, this.f14816j, this.f14817k);
                return;
            }
            com.jingdong.sdk.dialingtest.d.b.a aVar3 = this.f14817k;
            aVar3.f14776h = "-100";
            aVar3.f14777i = "ip format error";
            com.jingdong.sdk.dialingtest.e.a.c.d(aVar3);
        }
    }

    public static void a(Handler handler, Object obj) {
        if (obj != null && (obj instanceof com.jingdong.sdk.dialingtest.d.b.b)) {
            com.jingdong.sdk.dialingtest.d.b.b bVar = (com.jingdong.sdk.dialingtest.d.b.b) obj;
            if (bVar.f14789j < 1) {
                return;
            }
            String e2 = com.jingdong.sdk.dialingtest.f.a.e();
            if (bVar.d()) {
                d(bVar, e2);
            } else {
                j(bVar, e2, "");
            }
            bVar.f14789j--;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 2002;
                obtainMessage.obj = bVar;
                handler.sendMessageDelayed(obtainMessage, bVar.f14784e * 1000);
            }
        }
    }

    private static void c(b.a aVar, int i2, int i3, com.jingdong.sdk.dialingtest.d.b.a aVar2) {
        if (TextUtils.isEmpty(aVar.b)) {
            com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", "ping test host is empty");
            return;
        }
        RunnableC0715c runnableC0715c = null;
        try {
            runnableC0715c = new RunnableC0715c(aVar.b, aVar.a.equals("domain"), i2, i3, aVar2);
        } catch (Exception e2) {
            com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", e2.toString());
        }
        if (runnableC0715c == null) {
            return;
        }
        com.jingdong.sdk.dialingtest.c.d.a.e().d(runnableC0715c);
    }

    private static void d(com.jingdong.sdk.dialingtest.d.b.b bVar, String str) {
        com.jingdong.sdk.dialingtest.c.c.d dVar = new com.jingdong.sdk.dialingtest.c.c.d();
        dVar.d(com.jingdong.sdk.dialingtest.f.a.a());
        dVar.b(1000);
        dVar.f(true);
        dVar.h("GET");
        dVar.c(new a(bVar, str));
        dVar.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void g(java.lang.String r19, int r20, int r21, boolean r22, com.jingdong.sdk.dialingtest.d.b.a r23) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.dialingtest.f.c.g(java.lang.String, int, int, boolean, com.jingdong.sdk.dialingtest.d.b.a):void");
    }

    private static boolean h(com.jingdong.sdk.dialingtest.c.a.a aVar, com.jingdong.sdk.dialingtest.d.b.a aVar2) {
        if (aVar == null) {
            return false;
        }
        if (TextUtils.isEmpty(aVar.d) && TextUtils.isEmpty(aVar.f14712e)) {
            if (!TextUtils.isEmpty(aVar.b)) {
                aVar2.f14776h = "" + aVar.f14711c;
                aVar2.f14777i = aVar.b;
                aVar2.f14778j = "invalid.exit.value";
                com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", "console, errCode: " + aVar2.f14776h + " errLog: " + aVar.b);
                return false;
            } else if (aVar.f14711c != 0 && TextUtils.isEmpty(aVar.a)) {
                aVar2.f14776h = "" + aVar.f14711c;
                aVar2.f14777i = "invalid exit value with empty errMsg";
                aVar2.f14778j = "invalid.exit.value";
                com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", "console,errCode: " + aVar2.f14776h + " errLog: " + aVar.b);
                return false;
            } else if (TextUtils.isEmpty(aVar.a)) {
                aVar2.f14776h = "-1";
                aVar2.f14777i = "ping success with empty console output";
                aVar2.f14778j = "console.empty.output";
                com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", "ping success with empty console output");
                return false;
            } else {
                String[] split = aVar.a.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                if (split.length >= 3 && split[0].toLowerCase().startsWith("ping") && (split[0].toLowerCase().endsWith("bytes of data.") || split[0].toLowerCase().endsWith("data bytes"))) {
                    return true;
                }
                aVar2.f14776h = "-1";
                String str = aVar.a;
                aVar2.f14777i = str;
                aVar2.f14778j = "console.abnormal.output";
                com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", str);
                return false;
            }
        }
        aVar2.f14776h = "-1";
        aVar2.f14778j = aVar.d;
        aVar2.f14777i = aVar.f14712e;
        com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", "exception: " + aVar.d + " exMsg: " + aVar.f14712e);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(com.jingdong.sdk.dialingtest.c.b.a aVar, int i2, com.jingdong.sdk.dialingtest.d.b.a aVar2) {
        if (aVar == null || aVar2 == null || i2 == 0 || aVar2.a(aVar) != i2) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < aVar2.q.size(); i7++) {
            try {
                com.jingdong.sdk.dialingtest.c.b.a aVar3 = aVar2.q.get(i7);
                if (aVar3 != null) {
                    JSONObject jSONObject = new JSONObject();
                    int i8 = aVar3.f14715c;
                    if (aVar3.b) {
                        if (i5 == 0 && i4 == 0 && i6 == 0) {
                            i4 = i8;
                            i5 = i4;
                            i6 = i5;
                        } else {
                            if (i8 < i5) {
                                i5 = i8;
                            }
                            if (i8 > i4) {
                                i4 = i8;
                            }
                            i6 += i8;
                        }
                        i3++;
                    }
                    jSONObject.put("seq", String.valueOf(aVar3.a));
                    jSONObject.put("type", aVar3.d);
                    jSONObject.put("time", String.valueOf(i8));
                    jSONObject.put("errCode", aVar3.f14716e);
                    jSONObject.put("errMsg", aVar3.f14717f);
                    jSONArray.put(jSONObject);
                }
            } catch (Throwable th) {
                com.jingdong.sdk.dialingtest.c.e.a.c("PingTestUtil", th.toString());
            }
        }
        aVar2.f14779k = jSONArray.toString();
        aVar2.f14781m = i3 == 0 ? "" : "" + i4;
        aVar2.f14780l = i3 == 0 ? "" : "" + i5;
        aVar2.f14782n = i3 == 0 ? "" : "" + (i6 / i3);
        aVar2.o = "" + (((i2 - i3) * 100) / i2);
        com.jingdong.sdk.dialingtest.e.a.c.d(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void j(com.jingdong.sdk.dialingtest.d.b.b bVar, String str, String str2) {
        if (bVar == null || TextUtils.isEmpty(str) || bVar.f14788i == null) {
            return;
        }
        for (int i2 = 0; i2 < bVar.f14788i.size(); i2++) {
            b.a aVar = bVar.f14788i.get(i2);
            com.jingdong.sdk.dialingtest.d.b.a aVar2 = new com.jingdong.sdk.dialingtest.d.b.a();
            aVar2.a = str;
            aVar2.f14775g = str2;
            boolean z = bVar.f14790k;
            aVar2.r = z;
            if (z) {
                aVar2.p = com.jingdong.sdk.dialingtest.b.k().f14710f;
            }
            c(aVar, bVar.f14786g, bVar.f14787h, aVar2);
        }
    }

    private static void k(String str, int i2, int i3, com.jingdong.sdk.dialingtest.d.b.a aVar) {
        String str2;
        if (aVar == null) {
            return;
        }
        String str3 = com.jingdong.sdk.dialingtest.f.a.k(str) ? "ping6 " : "ping ";
        String str4 = "";
        if (i2 == 0) {
            str2 = "";
        } else {
            str2 = " -c " + i2 + LangUtils.SINGLE_SPACE;
        }
        if (i3 != 0) {
            str4 = " -W " + i3 + LangUtils.SINGLE_SPACE;
        }
        String str5 = str3 + str2 + " -A " + str4 + str;
        com.jingdong.sdk.dialingtest.c.a.b bVar = new com.jingdong.sdk.dialingtest.c.a.b();
        bVar.c(str5);
        com.jingdong.sdk.dialingtest.c.a.a b2 = bVar.b();
        if (b2 == null) {
            return;
        }
        if (h(b2, aVar)) {
            g(b2.a, i2, i3, com.jingdong.sdk.dialingtest.f.a.k(str), aVar);
        }
        com.jingdong.sdk.dialingtest.e.a.c.d(aVar);
    }

    private static void l(String str, int i2, int i3, com.jingdong.sdk.dialingtest.d.b.a aVar) {
        if (aVar == null) {
            return;
        }
        for (int i4 = 1; i4 <= i2; i4++) {
            com.jingdong.sdk.dialingtest.c.b.b bVar = new com.jingdong.sdk.dialingtest.c.b.b();
            bVar.d(str);
            bVar.b(i4);
            bVar.e(i3 * 1000);
            bVar.c(new b(i2, aVar));
            bVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m(String str, int i2, int i3, com.jingdong.sdk.dialingtest.d.b.a aVar) {
        if (TextUtils.isEmpty(str) || aVar == null || i2 <= 0) {
            return;
        }
        if (com.jingdong.sdk.dialingtest.f.a.k(str) && Build.VERSION.SDK_INT == 28) {
            l(str, i2, i3, aVar);
        } else {
            k(str, i2, i3, aVar);
        }
    }
}
