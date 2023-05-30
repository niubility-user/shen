package com.jingdong.sdk.dialingtest.f;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.dialingtest.c.c.d;
import com.jingdong.sdk.dialingtest.d.c.b;
import com.tencent.smtt.sdk.ProxyConfig;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class a implements d.b {
        final /* synthetic */ com.jingdong.sdk.dialingtest.d.c.b a;
        final /* synthetic */ String b;

        a(com.jingdong.sdk.dialingtest.d.c.b bVar, String str) {
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
                        com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "get operator dns ip success ,ip :" + str);
                    } catch (Exception e2) {
                        com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", e2.toString());
                    }
                }
            }
            d.g(this.a, this.b, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final String f14818g;

        /* renamed from: h  reason: collision with root package name */
        private final boolean f14819h;

        /* renamed from: i  reason: collision with root package name */
        private final int f14820i;

        /* renamed from: j  reason: collision with root package name */
        private final int f14821j;

        /* renamed from: k  reason: collision with root package name */
        private final int f14822k;

        /* renamed from: l  reason: collision with root package name */
        private final com.jingdong.sdk.dialingtest.d.c.a f14823l;

        public b(String str, boolean z, int i2, int i3, int i4, com.jingdong.sdk.dialingtest.d.c.a aVar) {
            this.f14818g = str;
            this.f14819h = z;
            this.f14820i = i2;
            this.f14821j = i3;
            this.f14822k = i4;
            this.f14823l = aVar;
        }

        private String a(String str, List<String> list) {
            if (this.f14821j == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            if (list != null && list.size() != 0 && !TextUtils.isEmpty(str)) {
                while (i2 < list.size()) {
                    if (!TextUtils.isEmpty(list.get(i2))) {
                        sb.append(str);
                    } else {
                        sb.append(ProxyConfig.MATCH_ALL_SCHEMES);
                    }
                    if (i2 != list.size() - 1) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    i2++;
                }
            } else {
                while (i2 < this.f14821j - 1) {
                    sb.append("*,");
                    i2++;
                }
                sb.append(ProxyConfig.MATCH_ALL_SCHEMES);
            }
            return sb.toString();
        }

        private String b(List<String> list) {
            if (this.f14821j == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            if (list != null && list.size() != 0) {
                while (i2 < list.size()) {
                    String str = list.get(i2);
                    if (!TextUtils.isEmpty(str)) {
                        sb.append(str);
                    } else {
                        sb.append(ProxyConfig.MATCH_ALL_SCHEMES);
                    }
                    if (i2 != list.size() - 1) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    i2++;
                }
            } else {
                while (i2 < this.f14821j - 1) {
                    sb.append("*,");
                    i2++;
                }
                sb.append(ProxyConfig.MATCH_ALL_SCHEMES);
            }
            return sb.toString();
        }

        private boolean c(com.jingdong.sdk.dialingtest.c.a.a aVar, com.jingdong.sdk.dialingtest.d.c.a aVar2) {
            if (aVar != null && aVar2 != null) {
                if (TextUtils.isEmpty(aVar.d) && TextUtils.isEmpty(aVar.f14712e)) {
                    if (!TextUtils.isEmpty(aVar.b)) {
                        aVar2.f14795h = "" + aVar.f14711c;
                        aVar2.f14796i = aVar.b;
                        aVar2.f14797j = "invalid.exit.value";
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "console, errCode: " + aVar2.f14795h + " errLog: " + aVar.b);
                        return false;
                    } else if (aVar.f14711c != 0 && TextUtils.isEmpty(aVar.a)) {
                        aVar2.f14795h = "" + aVar.f14711c;
                        aVar2.f14796i = "invalid exit value with empty errMsg";
                        aVar2.f14797j = "invalid.exit.value";
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "console,errCode: " + aVar2.f14795h + " errLog: " + aVar.b);
                        return false;
                    } else if (TextUtils.isEmpty(aVar.a)) {
                        aVar2.f14795h = "-1";
                        aVar2.f14796i = "ping success with empty console output";
                        aVar2.f14797j = "console.empty.output";
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "ping success with empty console output");
                        return false;
                    } else {
                        return true;
                    }
                }
                aVar2.f14795h = "-1";
                aVar2.f14797j = aVar.d;
                aVar2.f14796i = aVar.f14712e;
                com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "exception: " + aVar.d + " exMsg: " + aVar.f14712e);
            }
            return false;
        }

        private boolean d(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (split.length >= 3 && split[0].toLowerCase().startsWith("ping") && (split[0].toLowerCase().endsWith("bytes of data.") || split[0].toLowerCase().endsWith("data bytes"))) {
                for (int i2 = 0; i2 < split.length; i2++) {
                    String str2 = split[i2] == null ? "" : split[i2];
                    String g2 = com.jingdong.sdk.dialingtest.f.a.g(str2);
                    String i3 = com.jingdong.sdk.dialingtest.f.a.i(str2);
                    if (!TextUtils.isEmpty(g2) && !TextUtils.isEmpty(i3) && str2.contains(RemoteMessageConst.TTL)) {
                        return true;
                    }
                }
                return false;
            }
            com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "err cmdOutput: " + str);
            return false;
        }

        private List<String> e(String str) {
            int parseInt;
            int round;
            ArrayList arrayList = new ArrayList();
            if (TextUtils.isEmpty(str)) {
                return arrayList;
            }
            String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (split.length >= 3) {
                if (split[0].toLowerCase().startsWith("ping") && (split[0].toLowerCase().endsWith("bytes of data.") || split[0].toLowerCase().endsWith("data bytes"))) {
                    HashSet hashSet = new HashSet(3);
                    for (int i2 = 0; i2 < split.length; i2++) {
                        try {
                            String str2 = split[i2] == null ? "" : split[i2];
                            String g2 = com.jingdong.sdk.dialingtest.f.a.g(str2);
                            String i3 = com.jingdong.sdk.dialingtest.f.a.i(str2);
                            if (!TextUtils.isEmpty(g2) && !TextUtils.isEmpty(i3) && !hashSet.contains(g2) && (parseInt = Integer.parseInt(g2)) != 0 && parseInt <= this.f14821j && (round = Math.round(Float.parseFloat(i3))) >= 0 && round <= this.f14822k * 1000) {
                                hashSet.add(g2);
                                arrayList.add(String.valueOf(round));
                            }
                        } catch (Exception e2) {
                            com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", e2.toString());
                        }
                    }
                    while (arrayList.size() < this.f14821j) {
                        arrayList.add("");
                    }
                    return arrayList;
                }
            }
            com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "err cmdOutput: " + str);
            return arrayList;
        }

        private String f(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (split.length >= 3) {
                if (split[0].toLowerCase().startsWith("ping") && (split[0].toLowerCase().endsWith("bytes of data.") || split[0].toLowerCase().endsWith("data bytes"))) {
                    String str2 = "";
                    for (int i2 = 0; i2 < split.length; i2++) {
                        str2 = com.jingdong.sdk.dialingtest.f.a.j(split[i2] == null ? "" : split[i2]);
                        if (com.jingdong.sdk.dialingtest.f.a.d(str2)) {
                            break;
                        }
                    }
                    return str2;
                }
            }
            com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "err cmdOutput: " + str);
            return "";
        }

        private void g(String str) {
            String f2;
            String b;
            String str2;
            JSONArray jSONArray = new JSONArray();
            boolean z = false;
            int i2 = 0;
            while (true) {
                i2++;
                if (i2 > this.f14820i) {
                    com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "trace route ttl is over the max hop");
                    break;
                }
                String b2 = com.jingdong.sdk.dialingtest.f.a.b(str, 1, i2, this.f14822k);
                com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "trace cmd: " + b2);
                com.jingdong.sdk.dialingtest.c.a.b bVar = new com.jingdong.sdk.dialingtest.c.a.b();
                bVar.c(b2);
                com.jingdong.sdk.dialingtest.c.a.a b3 = bVar.b();
                if (!c(b3, this.f14823l)) {
                    com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "exception occurred");
                    com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", b3.toString());
                    break;
                }
                boolean d = d(b3.a);
                if (d) {
                    f2 = str;
                } else {
                    f2 = f(b3.a);
                    if (TextUtils.isEmpty(f2)) {
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "extract IP failed: " + b3.toString());
                        f2 = "";
                    }
                }
                if (!TextUtils.isEmpty(f2)) {
                    String b4 = com.jingdong.sdk.dialingtest.f.a.b(f2, this.f14821j, 0, this.f14822k);
                    com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "ping intermediate cmd: " + b4);
                    com.jingdong.sdk.dialingtest.c.a.b bVar2 = new com.jingdong.sdk.dialingtest.c.a.b();
                    bVar2.c(b4);
                    com.jingdong.sdk.dialingtest.c.a.a b5 = bVar2.b();
                    if (!c(b5, this.f14823l)) {
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "exception occurred");
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", b5.toString());
                        break;
                    }
                    List<String> e2 = e(b5.a);
                    if (e2 == null || e2.size() == 0) {
                        com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "extract time failed: " + b5.toString());
                    }
                    str2 = a(f2, e2);
                    b = b(e2);
                } else {
                    String a = a(f2, null);
                    b = b(null);
                    str2 = a;
                }
                JSONObject jSONObject = new JSONObject();
                com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "hop " + i2 + ": " + str2 + " <->" + b);
                try {
                    jSONObject.put("seq", "" + i2);
                    jSONObject.put("ip", str2);
                    jSONObject.put("time", b);
                    jSONArray.put(jSONObject);
                    if (d) {
                        break;
                    }
                } catch (Exception e3) {
                    com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", e3.toString());
                    com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "exception occurred");
                }
            }
            z = true;
            if (!z) {
                this.f14823l.f14798k = jSONArray.toString();
            }
            com.jingdong.sdk.dialingtest.e.a.c.e(this.f14823l);
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, Object> l2;
            com.jingdong.sdk.dialingtest.d.c.a aVar = this.f14823l;
            String str = this.f14818g;
            aVar.f14791c = str;
            if (this.f14819h && (l2 = com.jingdong.sdk.dialingtest.f.a.l(str)) != null) {
                String str2 = l2.get("time") == null ? "" : (String) l2.get("time");
                InetAddress[] inetAddressArr = l2.get("remoteInet") == null ? null : (InetAddress[]) l2.get("remoteInet");
                this.f14823l.f14792e = str2;
                if (inetAddressArr != null && inetAddressArr.length >= 1) {
                    str = d.f(inetAddressArr);
                    if (!TextUtils.isEmpty(str)) {
                        this.f14823l.d = str;
                    }
                }
                com.jingdong.sdk.dialingtest.d.c.a aVar2 = this.f14823l;
                aVar2.f14795h = "-99";
                aVar2.f14796i = "Dns resolve error";
                com.jingdong.sdk.dialingtest.e.a.c.e(aVar2);
                return;
            }
            if (!com.jingdong.sdk.dialingtest.f.a.d(str)) {
                com.jingdong.sdk.dialingtest.d.c.a aVar3 = this.f14823l;
                aVar3.f14795h = "-100";
                aVar3.f14796i = "ip format error";
                com.jingdong.sdk.dialingtest.e.a.c.e(aVar3);
                return;
            }
            com.jingdong.sdk.dialingtest.c.e.a.a("TrTestUtil", "des ip: " + str);
            g(str);
        }
    }

    public static void b(Handler handler, Object obj) {
        if (obj != null && (obj instanceof com.jingdong.sdk.dialingtest.d.c.b)) {
            com.jingdong.sdk.dialingtest.d.c.b bVar = (com.jingdong.sdk.dialingtest.d.c.b) obj;
            if (bVar.f14806k < 1) {
                return;
            }
            String e2 = com.jingdong.sdk.dialingtest.f.a.e();
            if (bVar.d()) {
                d(bVar, e2);
            } else {
                g(bVar, e2, "");
            }
            bVar.f14806k--;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 2003;
                obtainMessage.obj = bVar;
                handler.sendMessageDelayed(obtainMessage, bVar.f14800e * 1000);
            }
        }
    }

    private static void c(b.a aVar, int i2, int i3, int i4, com.jingdong.sdk.dialingtest.d.c.a aVar2) {
        if (TextUtils.isEmpty(aVar.b)) {
            com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", "trace route test host is empty");
            return;
        }
        b bVar = null;
        try {
            bVar = new b(aVar.b, aVar.a.equals("domain"), i2, i3, i4, aVar2);
        } catch (Throwable th) {
            com.jingdong.sdk.dialingtest.c.e.a.c("TrTestUtil", th.toString());
        }
        if (bVar == null) {
            return;
        }
        com.jingdong.sdk.dialingtest.c.d.a.e().d(bVar);
    }

    private static void d(com.jingdong.sdk.dialingtest.d.c.b bVar, String str) {
        com.jingdong.sdk.dialingtest.c.c.d dVar = new com.jingdong.sdk.dialingtest.c.c.d();
        dVar.d(com.jingdong.sdk.dialingtest.f.a.a());
        dVar.b(1000);
        dVar.f(true);
        dVar.h("GET");
        dVar.c(new a(bVar, str));
        dVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(InetAddress[] inetAddressArr) {
        String str = "";
        if (inetAddressArr == null || inetAddressArr.length == 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT != 28 && com.jingdong.sdk.dialingtest.f.a.h()) {
            int length = inetAddressArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                InetAddress inetAddress = inetAddressArr[i2];
                if (inetAddress != null && !inetAddress.isLoopbackAddress()) {
                    String b2 = com.jingdong.sdk.dialingtest.e.b.b.a().b(inetAddress);
                    if (!TextUtils.isEmpty(b2) && !b2.toLowerCase().startsWith("fe80")) {
                        str = b2;
                        break;
                    }
                }
                i2++;
            }
        }
        if (TextUtils.isEmpty(str)) {
            for (InetAddress inetAddress2 : inetAddressArr) {
                if (inetAddress2 != null && !inetAddress2.isLoopbackAddress()) {
                    String a2 = com.jingdong.sdk.dialingtest.e.b.b.a().a(inetAddress2);
                    if (!TextUtils.isEmpty(a2)) {
                        return a2;
                    }
                }
            }
            return str;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(com.jingdong.sdk.dialingtest.d.c.b bVar, String str, String str2) {
        if (bVar == null || TextUtils.isEmpty(str)) {
            return;
        }
        for (int i2 = 0; i2 < bVar.f14805j.size(); i2++) {
            com.jingdong.sdk.dialingtest.d.c.a aVar = new com.jingdong.sdk.dialingtest.d.c.a();
            aVar.a = str;
            aVar.f14794g = str2;
            c(bVar.f14805j.get(i2), bVar.f14802g, bVar.f14803h, bVar.f14804i, aVar);
        }
    }
}
