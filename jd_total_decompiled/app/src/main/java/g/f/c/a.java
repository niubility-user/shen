package g.f.c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.nfc.tech.NfcV;
import android.text.TextUtils;
import g.f.b.a.b;
import g.f.b.a.c;
import g.f.b.a.d;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import jd.wjlogin_sdk.util.ReplyCode;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    private static Context a = null;
    private static String b = null;

    /* renamed from: c  reason: collision with root package name */
    private static String f19547c = null;
    private static String d = "android.nfc.tech.NfcV";

    /* renamed from: e  reason: collision with root package name */
    private static String f19548e = "https://rfidh5-xl.jd.com/#/pages/again";

    private static int a(Tag tag) {
        NfcV nfcV = NfcV.get(tag);
        try {
            try {
                try {
                    nfcV.connect();
                    if (!nfcV.isConnected()) {
                        d(f19548e);
                        try {
                            nfcV.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return -1;
                    }
                    int b2 = b(tag, nfcV);
                    if (b2 != 0) {
                        String str = "---- ret is:" + b2;
                        nfcV.close();
                    }
                    try {
                        nfcV.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return b2;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    d(f19548e);
                    try {
                        nfcV.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    return -1;
                }
            } catch (Exception e6) {
                e6.printStackTrace();
                d(f19548e);
                try {
                    nfcV.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                return -2;
            }
        } catch (Throwable th) {
            try {
                nfcV.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            throw th;
        }
    }

    private static int b(Tag tag, NfcV nfcV) {
        d c2 = c(g.f.b.b.a.a(tag.getId()), new g.f.c.c.d.a(nfcV));
        if (c2 != null) {
            if (!TextUtils.isEmpty(c2.f19546c)) {
                d(c2.f19546c);
                return 0;
            } else if (!TextUtils.isEmpty(c2.b)) {
                d(c2.b);
                return -1;
            } else {
                d(f19548e);
                return -1;
            }
        }
        d(f19548e);
        return -1;
    }

    public static d c(String str, g.f.c.c.d.a aVar) {
        g.f.b.a.a aVar2 = new g.f.b.a.a();
        byte[] bArr = new byte[4];
        byte g2 = aVar.g(bArr);
        if (g2 != 0) {
            String str2 = "initAuth card comm error=" + ((int) g2);
            return null;
        }
        aVar2.d = bArr[3];
        aVar2.b = str;
        aVar2.f19537f = 1;
        try {
            aVar2.f19538g = g.f.c.d.a.a(b, "starlinkrfidauth");
        } catch (Exception e2) {
            e2.printStackTrace();
            e2.getMessage();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "authBegin");
            jSONObject.put("body", g.f.b.a.a.a(aVar2, true, false));
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        b a2 = b.a(g.f.d.a.a(f19547c, jSONObject));
        if (a2 == null) {
            return null;
        }
        int i2 = a2.a;
        if (i2 != 0) {
            d dVar = new d();
            dVar.a = i2;
            dVar.b = a2.f19540c;
            String str3 = a2.b;
            return dVar;
        }
        int i3 = a2.f19541e;
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        return null;
                    }
                    return e(str, aVar, a2.d, bArr, false, a2.f19542f);
                }
                return e(str, aVar, a2.d, bArr, true, a2.f19542f);
            }
            return g(str, aVar, a2.d, bArr, true, a2.f19542f);
        }
        return g(str, aVar, a2.d, bArr, false, a2.f19542f);
    }

    private static void d(String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"m\",\"url\":\"" + str + "\"}"));
        StringBuilder sb = new StringBuilder();
        sb.append("------@ jumpUrl=");
        sb.append(str);
        sb.toString();
        a.startActivity(intent);
    }

    private static d e(String str, g.f.c.c.d.a aVar, String str2, byte[] bArr, boolean z, String str3) {
        byte f2;
        g.f.b.a.a aVar2 = new g.f.b.a.a();
        byte[] bArr2 = new byte[224];
        if (z && (f2 = aVar.f((byte) 0, ReplyCode.reply0x38, g.f.b.b.a.b(str2), bArr2)) != 0) {
            String str4 = "mutualAuth card comm error=" + ((int) f2);
            return null;
        }
        byte[] bArr3 = new byte[4];
        byte h2 = aVar.h(bArr3);
        if (h2 != 0) {
            String str5 = "mutualAuth card comm error=" + ((int) h2);
            return null;
        }
        aVar2.d = bArr[3];
        aVar2.b = str;
        if (z) {
            aVar2.a = 3;
            aVar2.f19535c = g.f.b.b.a.a(bArr2);
        } else {
            aVar2.a = 4;
        }
        aVar2.f19536e = g.f.b.b.a.a(bArr3);
        aVar2.f19537f = 0;
        aVar2.f19539h = str3;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "authBegin");
            jSONObject.put("body", g.f.b.a.a.a(aVar2, false, z));
        } catch (JSONException e2) {
            e2.printStackTrace();
            String str6 = "mutualAuth authBegin JSONException" + e2.getMessage();
        }
        b a2 = b.a(g.f.d.a.a(f19547c, jSONObject));
        if (a2 == null) {
            return null;
        }
        int i2 = a2.a;
        if (i2 != 0) {
            d dVar = new d();
            dVar.a = i2;
            dVar.b = a2.f19540c;
            String str7 = a2.b;
            return dVar;
        }
        byte[] bArr4 = new byte[8];
        byte e3 = aVar.e(g.f.b.b.a.b(a2.d), bArr4);
        if (e3 != 0) {
            String str8 = "mutualAuth card comm error=" + ((int) e3);
            return null;
        }
        c cVar = new c();
        cVar.d = bArr[3];
        if (z) {
            cVar.f19544e = 3;
            cVar.b = g.f.b.b.a.a(bArr2);
        } else {
            cVar.f19544e = 4;
        }
        cVar.a = str;
        cVar.f19543c = g.f.b.b.a.a(bArr4);
        cVar.f19545f = str3;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("method", "authEnd");
            jSONObject2.put("body", c.a(cVar, z));
        } catch (JSONException e4) {
            e4.printStackTrace();
            String str9 = "mutualAuth authEnd JSONException" + e4.getMessage();
        }
        d a3 = d.a(g.f.d.a.a(f19547c, jSONObject2));
        if (a3 == null) {
            return null;
        }
        return a3;
    }

    public static void f(Context context, Intent intent, String str, String str2) {
        a = context;
        b = str;
        f19547c = str2;
        String str3 = "------@ processIntent:" + intent;
        Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
        List<String> asList = Arrays.asList(tag.getTechList());
        if (asList != null) {
            for (String str4 : asList) {
            }
        }
        if (asList.contains(d)) {
            a(tag);
        }
    }

    private static d g(String str, g.f.c.c.d.a aVar, String str2, byte[] bArr, boolean z, String str3) {
        byte f2;
        byte[] bArr2 = new byte[224];
        if (z && (f2 = aVar.f((byte) 0, ReplyCode.reply0x38, g.f.b.b.a.b(str2), bArr2)) != 0) {
            String str4 = "singleAuth card comm error=" + ((int) f2);
            return null;
        }
        byte[] bArr3 = new byte[8];
        byte d2 = aVar.d(g.f.b.b.a.b(str2), bArr3);
        if (d2 != 0) {
            String str5 = "singleAuth card comm error=" + ((int) d2);
            return null;
        }
        c cVar = new c();
        cVar.d = bArr[3];
        cVar.a = str;
        if (z) {
            cVar.f19544e = 2;
            cVar.b = g.f.b.b.a.a(bArr2);
        } else {
            cVar.f19544e = 1;
        }
        cVar.f19543c = g.f.b.b.a.a(bArr3);
        cVar.f19545f = str3;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "authEnd");
            jSONObject.put("body", c.a(cVar, z));
        } catch (JSONException e2) {
            String str6 = "singleAuth authEnd JSONException" + e2.getMessage();
            e2.printStackTrace();
        }
        d a2 = d.a(g.f.d.a.a(f19547c, jSONObject));
        if (a2 == null) {
            return null;
        }
        return a2;
    }
}
