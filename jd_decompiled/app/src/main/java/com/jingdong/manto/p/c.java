package com.jingdong.manto.p;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.i.b;
import com.jingdong.manto.i.e;
import com.jingdong.manto.utils.MantoLog;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import okhttp3.Headers;
import okhttp3.Request;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes16.dex */
public enum c {
    REQUEST,
    SOCKET,
    UPLOAD,
    DOWNLOAD;

    /* loaded from: classes16.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[c.values().length];
            a = iArr;
            try {
                iArr[c.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[c.SOCKET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[c.UPLOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[c.DOWNLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int a(e eVar, h hVar, c cVar) {
        int i2;
        int i3;
        String format;
        b.C0514b c0514b;
        b.C0514b c0514b2;
        b.C0514b c0514b3;
        b.C0514b c0514b4;
        int i4 = a.a[cVar.ordinal()];
        if (i4 == 1) {
            i2 = hVar.h().t.f13046c.a;
            com.jingdong.manto.i.b bVar = eVar.p;
            i3 = (bVar == null || (c0514b = bVar.f13073c) == null) ? 0 : c0514b.f13078f;
            format = String.format("request app timeout: %d, sys timeout: %d", Integer.valueOf(i2), Integer.valueOf(i3));
        } else if (i4 == 2) {
            i2 = hVar.h().t.f13046c.b;
            com.jingdong.manto.i.b bVar2 = eVar.p;
            i3 = (bVar2 == null || (c0514b2 = bVar2.f13073c) == null) ? 0 : c0514b2.f13078f;
            format = String.format("socket app timeout: %d, sys timeout: %d", Integer.valueOf(i2), Integer.valueOf(i3));
        } else if (i4 == 3) {
            i2 = hVar.h().t.f13046c.f13056c;
            com.jingdong.manto.i.b bVar3 = eVar.p;
            i3 = (bVar3 == null || (c0514b3 = bVar3.f13073c) == null) ? 0 : c0514b3.f13079g;
            format = String.format("upload app timeout: %d, sys timeout: %d", Integer.valueOf(i2), Integer.valueOf(i3));
        } else if (i4 != 4) {
            i3 = 0;
            i2 = 0;
            if (i2 > 0 || i3 > 0) {
                if (i2 <= 0 || i3 <= 0) {
                    if (i2 > 0 || i3 <= 0) {
                        return 0;
                    }
                    return Math.min(i2, i3);
                }
                return i3;
            }
            return i2;
        } else {
            i2 = hVar.h().t.f13046c.d;
            com.jingdong.manto.i.b bVar4 = eVar.p;
            i3 = (bVar4 == null || (c0514b4 = bVar4.f13073c) == null) ? 0 : c0514b4.a;
            format = String.format("download app timeout: %d, sys timeout: %d", Integer.valueOf(i2), Integer.valueOf(i3));
        }
        MantoLog.i("NetworkHelper", format);
        if (i2 > 0) {
        }
        if (i2 <= 0) {
        }
        if (i2 > 0) {
        }
        return 0;
    }

    private static LinkedList<InputStream> a(String str) {
        return new LinkedList<>();
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("formData");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, optJSONObject.getString(next));
                }
            }
        } catch (Exception e2) {
            MantoLog.e("NetworkHelper", "parse form exception", e2);
        }
        return hashMap;
    }

    public static Map<String, String> a(JSONObject jSONObject, e eVar) {
        b.C0514b c0514b;
        b.C0514b c0514b2;
        ArrayList<String> arrayList;
        String lowerCase;
        String str;
        Map<String, String> b = b(jSONObject);
        com.jingdong.manto.i.b bVar = eVar.p;
        if (bVar != null && (c0514b2 = bVar.f13073c) != null) {
            HashMap hashMap = new HashMap();
            int i2 = c0514b2.f13080h;
            if (i2 == 1) {
                ArrayList<String> arrayList2 = c0514b2.d;
                if (arrayList2 != null) {
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        Iterator<String> it = arrayList2.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            if (next != null && b.remove(next.toLowerCase()) != null) {
                                MantoLog.v("NetworkHelper", String.format("remove item: key %s value %s", next.toLowerCase(), b.remove(next.toLowerCase())));
                            }
                        }
                    }
                }
                b = hashMap;
            } else {
                if (i2 == 2 && (arrayList = c0514b2.f13077e) != null) {
                    if (arrayList != null) {
                        hashMap = new HashMap();
                        Iterator<String> it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            String next2 = it2.next();
                            if (next2 != null && (str = b.get((lowerCase = next2.toLowerCase()))) != null) {
                                MantoLog.v("NetworkHelper", String.format("put item: key(%s), value(%s)", lowerCase, str));
                                hashMap.put(lowerCase, str);
                            }
                        }
                    }
                }
                b = hashMap;
            }
        }
        b.remove("referer");
        com.jingdong.manto.i.b bVar2 = eVar.p;
        String str2 = (bVar2 == null || (c0514b = bVar2.f13073c) == null) ? "" : c0514b.f13076c;
        if (TextUtils.isEmpty(str2)) {
            str2 = "service.vapp.jd.com";
        }
        b.put("referer", "https://" + str2 + "/" + eVar.a + "/" + eVar.q + "/page-frame.html");
        return b;
    }

    public static void a(Request.Builder builder, Map<String, String> map) {
        if (builder == null || map == null) {
            return;
        }
        try {
            builder.headers(Headers.of(map));
        } catch (Exception unused) {
            MantoLog.e("headers", "headers maybe has Unexpected char.");
        }
    }

    public static boolean a(e eVar, boolean z) {
        if (!z) {
            return !eVar.f13094c;
        }
        MantoLog.v("NetworkHelper", "skip check domains");
        return false;
    }

    public static boolean a(ArrayList<String> arrayList, String str) {
        return a(arrayList, str, false);
    }

    public static boolean a(ArrayList<String> arrayList, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashMap<String, String> e2 = e(str);
        String str2 = e2.get("host");
        String str3 = e2.get("scheme");
        String str4 = e2.get(IMediaPlayer.OnNativeInvokeListener.ARG_PORT);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap<String, String> e3 = e(it.next());
            if (str2.equalsIgnoreCase(e3.get("host")) && str3.equalsIgnoreCase(e3.get("scheme")) && (z || str4.equalsIgnoreCase(e3.get(IMediaPlayer.OnNativeInvokeListener.ARG_PORT)))) {
                break;
            }
        }
        return true;
    }

    public static b b(String str) {
        LinkedList<InputStream> a2 = a(str);
        if (a2.isEmpty()) {
            return null;
        }
        b bVar = new b();
        Iterator<InputStream> it = a2.iterator();
        while (it.hasNext()) {
            InputStream next = it.next();
            if (bVar.f13913c != null) {
                try {
                    Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(next);
                    next.close();
                    bVar.f13913c.setCertificateEntry(((X509Certificate) generateCertificate).getSubjectDN().toString(), generateCertificate);
                } catch (Throwable unused) {
                    next.close();
                }
            }
        }
        bVar.a();
        return bVar;
    }

    private static Map<String, String> b(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("header");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next != null) {
                        hashMap.put(next.toLowerCase(), optJSONObject.getString(next));
                    }
                }
            }
        } catch (Exception e2) {
            MantoLog.e("NetworkHelper", "parse header exception", e2);
        }
        return hashMap;
    }

    public static SSLContext c(String str) {
        if (b(str) == null) {
            return null;
        }
        TrustManager[] trustManagerArr = {b(str)};
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, trustManagerArr, null);
            return sSLContext;
        } catch (Exception e2) {
            MantoLog.e("NetworkHelper", "ssl init exxception: ", e2);
            return null;
        }
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashMap<String, String> e2 = e(str);
        String str2 = e2.get("scheme");
        if (!TextUtils.isEmpty(e2.get("host")) && !TextUtils.isEmpty(str2)) {
            try {
                new Request.Builder().url(str);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static HashMap<String, String> e(String str) {
        String str2;
        String str3;
        String str4 = "";
        try {
            Uri parse = Uri.parse(str);
            str2 = parse.getHost();
            try {
                str3 = parse.getScheme();
                try {
                    str4 = String.valueOf(parse.getPort());
                } catch (Exception e2) {
                    e = e2;
                    MantoLog.e("NetworkHelper", e.getMessage());
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("host", str2);
                    hashMap.put("scheme", str3);
                    hashMap.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, str4);
                    return hashMap;
                }
            } catch (Exception e3) {
                e = e3;
                str3 = "";
            }
        } catch (Exception e4) {
            e = e4;
            str2 = "";
            str3 = str2;
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("host", str2);
        hashMap2.put("scheme", str3);
        hashMap2.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, str4);
        return hashMap2;
    }
}
