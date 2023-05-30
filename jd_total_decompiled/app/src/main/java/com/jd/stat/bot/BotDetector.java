package com.jd.stat.bot;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.stat.bot.a;
import com.jd.stat.common.EncryptUtil;
import com.jd.stat.common.TriTouchUtil;
import com.jd.stat.common.b.g;
import com.jd.stat.common.j;
import com.jd.stat.common.n;
import com.jd.stat.common.w;
import com.jd.stat.security.d;
import com.jd.stat.security.jma.JMA;
import com.jd.stat.security.jma.b.c;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.CRC32;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class BotDetector {
    public static final byte SDK_VERSION = 0;
    private static JSONObject a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, String> f6948c = new HashMap();
    private static boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private static Application f6949e = null;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f6950f = true;

    /* renamed from: g  reason: collision with root package name */
    private static String f6951g = "";

    /* renamed from: h  reason: collision with root package name */
    private static List<JSONObject> f6952h = new CopyOnWriteArrayList();

    /* renamed from: i  reason: collision with root package name */
    private static b f6953i = new b();

    /* loaded from: classes18.dex */
    private static class a implements Application.ActivityLifecycleCallbacks {
        private a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            com.jd.stat.common.b.b.b("JDMob.BotDetector", "onActivityStarted:" + activity.getClass().getCanonicalName());
            if (BotDetector.a()) {
                return;
            }
            BotDetector.h();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class b {
        String a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f6954c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f6955e;

        /* renamed from: f  reason: collision with root package name */
        int f6956f;

        /* renamed from: g  reason: collision with root package name */
        Set<String> f6957g;

        /* renamed from: h  reason: collision with root package name */
        String f6958h;

        /* renamed from: i  reason: collision with root package name */
        String f6959i;

        /* renamed from: j  reason: collision with root package name */
        String f6960j;

        /* renamed from: k  reason: collision with root package name */
        String f6961k;

        /* renamed from: l  reason: collision with root package name */
        String f6962l;

        /* renamed from: m  reason: collision with root package name */
        long f6963m;

        /* renamed from: n  reason: collision with root package name */
        String f6964n;
        long o;
        String p;
        long q;
        String[] r;

        private b() {
            this.a = "";
            this.b = 1;
            this.f6954c = 0;
            this.d = 1;
            this.f6955e = 100;
            this.f6956f = 60;
            this.f6957g = new HashSet();
            this.f6958h = "00";
            this.f6959i = "";
            this.f6960j = "1";
            this.f6961k = "2";
            this.f6962l = "";
            this.f6964n = "x,z,*,1";
            this.p = "";
            this.q = 0L;
            this.r = new String[0];
        }

        void a(long j2) {
            if (TextUtils.isEmpty(this.a)) {
                return;
            }
            String[] split = this.a.split("\\.");
            String str = split.length > 0 ? split[0] : "";
            String str2 = split.length > 1 ? split[1] : "";
            if (str.length() >= 2) {
                this.f6958h = str.substring(0, 2);
            }
            if (str.length() >= 7) {
                this.f6959i = str.substring(2, 7);
            }
            if (str.length() >= 9) {
                this.f6960j = str.substring(7, 9);
            }
            if (str.length() >= 10) {
                this.f6961k = str.substring(9, 10);
            }
            String[] split2 = str2.split("\\*");
            if (split2.length > 0) {
                byte[] b = BotDetector.b(split2[0]);
                byte[] bytes = this.f6959i.getBytes();
                for (int i2 = 0; i2 < b.length; i2++) {
                    b[i2] = (byte) (b[i2] ^ bytes[i2 % bytes.length]);
                }
                String str3 = new String(b);
                this.f6962l = str3;
                String[] split3 = str3.split("~");
                if (split3.length > 0) {
                    long parseLong = Long.parseLong(split3[0]);
                    this.f6963m = parseLong;
                    this.q = parseLong - j2;
                }
                if (split3.length > 2) {
                    this.f6964n = split3[2];
                }
                this.r = this.f6964n.split(DYConstants.DY_REGEX_COMMA);
                if (split3.length > 3) {
                    this.o = Long.parseLong(split3[3]);
                }
                if (split3.length > 5) {
                    this.p = split3[5];
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public static class c {
        boolean a;

        c(boolean z) {
            this.a = false;
            this.a = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        if (d.a().s() || d) {
            HashMap hashMap = new HashMap();
            hashMap.put("apple", "1");
            hashMap.put("1apple", "2");
            hashMap.put("banana123", "3");
            getRiskResult("100000", hashMap);
            getJoylog("");
        }
    }

    private static boolean e() {
        if (TextUtils.isEmpty(f6953i.a)) {
            return false;
        }
        b bVar = f6953i;
        return ((bVar.o * 3600) * 1000) + bVar.f6963m > getRealTimestamp();
    }

    private static native byte[] encrypt(String str, byte[] bArr);

    private static String f() {
        String[] strArr = f6953i.r;
        return (strArr == null || strArr.length < 4) ? "z" : b(strArr[2], g());
    }

    private static String g() {
        String[] strArr = f6953i.r;
        if (strArr != null && strArr.length >= 4) {
            String str = strArr[0];
            String str2 = strArr[1];
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                StringBuilder sb = new StringBuilder();
                int indexOf = "uvwxyz".indexOf(str);
                int indexOf2 = "uvwxyz".indexOf(str2);
                if (indexOf2 < 0) {
                    indexOf2 = 5;
                }
                for (int i2 = indexOf >= 0 ? indexOf : 0; i2 <= indexOf2; i2++) {
                    sb.append("uvwxyz".charAt(i2));
                }
                return sb.length() == 0 ? "z" : sb.toString();
            }
        }
        return "z";
    }

    public static Map<String, String> getBusinessData() {
        return f6948c;
    }

    public static String getCFVersion() {
        return TextUtils.isEmpty(f6953i.f6960j) ? "" : f6953i.f6960j;
    }

    public static String getJoylog(String str) {
        if (str == null) {
            str = "";
        }
        if (!d.a().r()) {
            com.jd.stat.common.b.b.a("BotDetector", "getJoylog=\"default\"");
            return "default";
        }
        String c2 = c(str);
        HashMap hashMap = new HashMap();
        hashMap.put("random", c2);
        String str2 = c2 + ProxyConfig.MATCH_ALL_SCHEMES + getRiskResult("1001", hashMap).optString("clog", "-1");
        com.jd.stat.common.b.b.a("BotDetector", "getJoylog=\"" + str2 + "\"");
        return str2;
    }

    public static long getRealTimestamp() {
        return System.currentTimeMillis() + f6953i.q;
    }

    public static JSONObject getRiskResult(String str, Map<String, String> map) {
        com.jd.stat.common.b.b.a("JDMob.BotDetector", "getRiskResult --- start");
        if (!d.a().r()) {
            a(str, map);
            JSONObject a2 = new com.jd.stat.bot.a("0", "0", "default").a();
            com.jd.stat.common.b.b.a("BotDetector", "getRiskResult:" + a2.toString());
            return a2;
        }
        if (str == null) {
            str = "";
        }
        if (map == null) {
            map = new HashMap<>();
        }
        JSONObject jSONObject = a;
        try {
            if (e() && jSONObject != null && a(map, f6948c) && TextUtils.equals(str, b)) {
                com.jd.stat.common.b.b.a("JDMob.BotDetector", jSONObject);
                a(str, map);
                return jSONObject;
            }
            b = str;
            f6948c = map;
            if (!e()) {
                h();
            }
            JSONObject b2 = b(str, map);
            a = b2;
            com.jd.stat.common.b.b.a("JDMob.BotDetector", b2);
            a(str, map);
            com.jd.stat.common.b.b.a("BotDetector", "getRiskResult:" + b2.toString());
            return b2;
        } catch (Throwable th) {
            a(str, map, th);
            a(str, map);
            com.jd.stat.common.b.b.a("BotDetector", "getRiskResult return error.");
            return new com.jd.stat.bot.a("1", "0", "").a();
        }
    }

    public static String getSceneId() {
        String str = b;
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h() {
        b((Runnable) null);
    }

    private static void i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", "RJInit");
            jSONObject.put("uid", com.jd.stat.security.c.f());
            jSONObject.put("appid", com.jd.stat.security.c.g());
            jSONObject.put("sceneid", getSceneId());
            jSONObject.put("data", a(getBusinessData()));
            jSONObject.put("type", 2);
            jSONObject.put("client_time", String.valueOf(System.currentTimeMillis()));
            jSONObject.put("f_name", XView2Constants.XVIEW2_ACTION_INIT);
            jSONObject.put("call_stack_source", g.a((Throwable) null, 1));
            jSONObject.put("error_msg", "");
            jSONObject.put("cf_v", getCFVersion());
            jSONObject.put("cookin_pin", c(com.jd.stat.security.c.f()));
            jSONObject.put("time_correction", String.valueOf(getRealTimestamp()));
            jSONObject.put("is_trust", "2");
            jSONObject.put("index", "");
            jSONObject.put("session_c", "");
            jSONObject.put("real_msg", "");
            jSONObject.put("clientsdkversion", String.valueOf(0));
            JMA.report(com.jd.stat.security.c.a, jSONObject);
        } catch (Exception e2) {
            com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
        }
    }

    public static void init(Application application) {
        f6949e = application;
        if (Build.VERSION.SDK_INT >= 14) {
            if (application == null) {
                application = com.jd.stat.security.c.b;
            }
            if (application != null) {
                application.registerActivityLifecycleCallbacks(new a());
            }
        }
        if (d.a().b()) {
            b(new Runnable() { // from class: com.jd.stat.bot.BotDetector.1
                @Override // java.lang.Runnable
                public void run() {
                    BotDetector.d();
                }
            });
        } else {
            d.a().a(true, new d.b() { // from class: com.jd.stat.bot.BotDetector.2
                @Override // com.jd.stat.security.d.b
                public void a() {
                    BotDetector.b(new Runnable() { // from class: com.jd.stat.bot.BotDetector.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BotDetector.d();
                        }
                    });
                }

                @Override // com.jd.stat.security.d.b
                public void b() {
                    BotDetector.b(new Runnable() { // from class: com.jd.stat.bot.BotDetector.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            BotDetector.d();
                        }
                    });
                }
            }, true);
        }
        i();
    }

    private static String j() {
        String c2 = com.jd.stat.common.c.c(com.jd.stat.security.c.a);
        return TextUtils.isEmpty(c2) ? com.jingdong.jdsdk.a.a.a : c2;
    }

    private static String k() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    private static boolean l() {
        if (TextUtils.isEmpty(f6951g)) {
            return false;
        }
        return d.a().b(f6951g) || m();
    }

    private static boolean m() {
        try {
            String a2 = com.jd.stat.common.a.a(com.jd.stat.security.c.a);
            if (!TextUtils.isEmpty(a2) && !";".equals(a2)) {
                for (String str : a2.split(";")) {
                    if (d.a().c(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static boolean n() {
        JSONObject c2;
        try {
            c2 = w.c();
        } catch (Throwable unused) {
        }
        if (c2.optJSONArray("fieldCache") == null || c2.optJSONArray("fieldCache").length() <= 2) {
            if (c2.optJSONArray("methodCache") == null || c2.optJSONArray("methodCache").length() <= 2) {
                if (c2.optJSONArray("constructorCache") != null) {
                    if (c2.optJSONArray("constructorCache").length() > 2) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return true;
    }

    public static boolean needTriTouch(Activity activity) {
        Set<String> set;
        if (activity == null || (set = f6953i.f6957g) == null) {
            return false;
        }
        return set.contains(activity.getClass().getCanonicalName());
    }

    private static boolean o() {
        if (TextUtils.isEmpty(f6951g)) {
            return false;
        }
        return f6951g.contains("autojs");
    }

    private static boolean p() {
        try {
            if (com.jd.stat.security.c.a != null && d.a().O()) {
                return j.a(com.jd.stat.security.c.a).charAt(0) == '1';
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean q() {
        try {
            String str = com.jd.stat.common.c.f(com.jd.stat.security.c.a).a;
            if (!TextUtils.isEmpty(str)) {
                if (str.charAt(0) == '1') {
                    return true;
                }
                if (str.length() > 2) {
                    if (str.charAt(2) == '1') {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.a("JDMob.BotDetector", th);
        }
        return false;
    }

    private static boolean r() {
        try {
            return n.p();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void setClogDebug(boolean z) {
        d = z;
    }

    private static native String uAlgorithmGetKey(long j2, String str);

    public static void updateRefer(String str) {
        f6951g = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(String str) {
        try {
            return Base64.decode(str, 11);
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    private static String c(String str) {
        String d2 = g.d(str);
        return TextUtils.isEmpty(d2) ? "" : d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Runnable runnable) {
        if (d.a().o()) {
            System.currentTimeMillis();
            new com.jd.stat.security.jma.b.c(new c.a
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE 
                  (wrap: com.jd.stat.security.jma.b.c : 0x0016: CONSTRUCTOR 
                  (wrap: com.jd.stat.security.jma.b.c$a : 0x0013: CONSTRUCTOR (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE]), (r4v0 'runnable' java.lang.Runnable A[DONT_INLINE]) A[MD:(long, java.lang.Runnable):void (m), WRAPPED] call: com.jd.stat.bot.BotDetector.3.<init>(long, java.lang.Runnable):void type: CONSTRUCTOR)
                 A[MD:(com.jd.stat.security.jma.b.c$a):void (m), WRAPPED] (LINE:5) call: com.jd.stat.security.jma.b.c.<init>(com.jd.stat.security.jma.b.c$a):void type: CONSTRUCTOR)
                  (null org.json.JSONObject)
                 type: VIRTUAL call: com.jd.stat.security.jma.b.c.c(org.json.JSONObject):void A[MD:(org.json.JSONObject):void (m)] (LINE:6) in method: com.jd.stat.bot.BotDetector.b(java.lang.Runnable):void, file: classes18.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:765)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:96)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:840)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 23 more
                */
            /*
                com.jd.stat.security.d r0 = com.jd.stat.security.d.a()
                boolean r0 = r0.o()
                if (r0 != 0) goto Lb
                return
            Lb:
                long r0 = java.lang.System.currentTimeMillis()
                com.jd.stat.security.jma.b.c r2 = new com.jd.stat.security.jma.b.c
                com.jd.stat.bot.BotDetector$3 r3 = new com.jd.stat.bot.BotDetector$3
                r3.<init>()
                r2.<init>(r3)
                r4 = 0
                r2.c(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.bot.BotDetector.b(java.lang.Runnable):void");
        }

        static /* synthetic */ boolean a() {
            return e();
        }

        private static String c(long j2, String str) {
            try {
                String valueOf = String.valueOf(j2);
                if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(str)) {
                    char[] charArray = valueOf.toCharArray();
                    char[] charArray2 = str.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < charArray.length; i2++) {
                        sb.append(Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]));
                    }
                    return TextUtils.isEmpty(sb) ? "GKDSJENWQSAA" : sb.toString();
                }
                return "GKDSJENWQSAA";
            } catch (Exception unused) {
                return "GKDSJENWQSAA";
            }
        }

        private static String e(long j2, String str) {
            try {
                String valueOf = String.valueOf(j2);
                if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(str)) {
                    String a2 = a(valueOf, 1);
                    String b2 = b(str, 1);
                    char[] charArray = a2.toCharArray();
                    char[] charArray2 = b2.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < charArray.length; i2++) {
                        sb.append(Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]));
                    }
                    return TextUtils.isEmpty(sb) ? "GKDSJENWQSAA" : sb.toString();
                }
                return "GKDSJENWQSAA";
            } catch (Exception unused) {
                return "GKDSJENWQSAA";
            }
        }

        private static String f(long j2, String str) {
            try {
                EncryptUtil.b();
                return uAlgorithmGetKey(j2, str);
            } catch (Throwable th) {
                com.jd.stat.common.b.b.a("JDMob.BotDetector", th.getMessage());
                return "";
            }
        }

        private static String a(byte[] bArr) {
            try {
                return Base64.encodeToString(bArr, 11);
            } catch (Throwable unused) {
                return "";
            }
        }

        private static String a(c cVar) {
            cVar.a = true;
            String[] strArr = f6953i.r;
            if (strArr == null || strArr.length < 4 || TextUtils.isEmpty(strArr[3])) {
                return "0";
            }
            cVar.a = false;
            return strArr[3];
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00b9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static JSONObject b(String str, Map<String, String> map) {
            String str2;
            long realTimestamp;
            String a2;
            String str3;
            String f2;
            c cVar;
            String a3;
            c cVar2;
            String a4;
            String a5;
            boolean z;
            String str4 = "0";
            try {
                realTimestamp = getRealTimestamp();
                a2 = EncryptUtil.a(10);
                String str5 = f6953i.a;
                if (TextUtils.isEmpty(str5)) {
                    str5 = c(com.jd.stat.security.c.f());
                }
                str3 = str5;
                f2 = f();
                cVar = new c(false);
                a3 = a(cVar);
                cVar2 = new c(false);
                a4 = a(f2, realTimestamp, a2, cVar2);
                str2 = "1";
                try {
                    a5 = a(a(map, str3, realTimestamp, a2, a4, "2"), a3);
                } catch (Throwable th) {
                    th = th;
                    str4 = "0";
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = "1";
            }
            try {
                if (!cVar.a && !TextUtils.isEmpty(a5)) {
                    z = false;
                    String a6 = a(a4, a(z));
                    a.C0217a f3 = new a.C0217a().a(realTimestamp).a("2").b(a2).c(str3).d(f2 + DYConstants.DY_REGEX_COMMA + a3).e(a5).f("");
                    StringBuilder sb = new StringBuilder();
                    sb.append("2");
                    sb.append(!cVar2.a ? "0" : str2);
                    String a7 = f3.g(sb.toString()).h(a6).i(d(a6)).a();
                    str4 = "0";
                    return new com.jd.stat.bot.a(str4, str4, a7).a();
                }
                return new com.jd.stat.bot.a(str4, str4, a7).a();
            } catch (Throwable th3) {
                th = th3;
                com.jd.stat.common.b.b.a("JDMob.BotDetector", th.getMessage());
                return new com.jd.stat.bot.a(str2, str4, "").a();
            }
            z = true;
            String a62 = a(a4, a(z));
            a.C0217a f32 = new a.C0217a().a(realTimestamp).a("2").b(a2).c(str3).d(f2 + DYConstants.DY_REGEX_COMMA + a3).e(a5).f("");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("2");
            sb2.append(!cVar2.a ? "0" : str2);
            String a72 = f32.g(sb2.toString()).h(a62).i(d(a62)).a();
            str4 = "0";
        }

        private static String d(String str) {
            try {
                CRC32 crc32 = new CRC32();
                crc32.update(str.getBytes());
                String l2 = Long.toString(crc32.getValue(), 36);
                if (l2.length() > 7) {
                    return l2.substring(l2.length() - 7);
                }
                if (l2.length() < 7) {
                    int length = 7 - l2.length();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < length; i2++) {
                        sb.append("0");
                    }
                    return ((Object) sb) + l2;
                }
                return l2;
            } catch (Exception unused) {
                return "";
            }
        }

        private static void a(String str, Map<String, String> map, Throwable th) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventid", "RJError");
                jSONObject.put("uid", com.jd.stat.security.c.f());
                jSONObject.put("appid", com.jd.stat.security.c.g());
                jSONObject.put("sceneid", str);
                jSONObject.put("data", a(map));
                jSONObject.put("type", 2);
                jSONObject.put("client_time", String.valueOf(System.currentTimeMillis()));
                jSONObject.put("f_name", "getRiskResult");
                jSONObject.put("call_stack_source", g.a(th));
                jSONObject.put("error_msg", th.getMessage());
                jSONObject.put("cf_v", getCFVersion());
                jSONObject.put("cookin_pin", c(com.jd.stat.security.c.f()));
                jSONObject.put("time_correction", String.valueOf(getRealTimestamp()));
                jSONObject.put("is_trust", "2");
                jSONObject.put("index", "");
                jSONObject.put("session_c", "");
                jSONObject.put("real_msg", "");
                jSONObject.put("clientsdkversion", String.valueOf(0));
                JMA.report(com.jd.stat.security.c.a, jSONObject);
            } catch (Exception e2) {
                com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
            }
        }

        private static String d(long j2, String str) {
            try {
                String valueOf = String.valueOf(j2);
                if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(str)) {
                    char[] charArray = valueOf.toCharArray();
                    char[] charArray2 = str.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < charArray.length; i2++) {
                        sb.append(Integer.toHexString(charArray[i2] & charArray2[i2 % charArray2.length]));
                    }
                    return TextUtils.isEmpty(sb) ? "GKDSJENWQSAA" : sb.toString();
                }
                return "GKDSJENWQSAA";
            } catch (Exception unused) {
                return "GKDSJENWQSAA";
            }
        }

        private static void a(String str, Map<String, String> map) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("eventid", "RJGetRiskResult");
                jSONObject.put("uid", com.jd.stat.security.c.f());
                jSONObject.put("appid", com.jd.stat.security.c.g());
                jSONObject.put("sceneid", str);
                jSONObject.put("data", a(map));
                jSONObject.put("type", 2);
                jSONObject.put("client_time", String.valueOf(System.currentTimeMillis()));
                jSONObject.put("f_name", "getRiskResult");
                jSONObject.put("call_stack_source", g.a((Throwable) null, 1));
                jSONObject.put("error_msg", "");
                jSONObject.put("cf_v", getCFVersion());
                jSONObject.put("cookin_pin", c(com.jd.stat.security.c.f()));
                jSONObject.put("time_correction", String.valueOf(getRealTimestamp()));
                jSONObject.put("is_trust", "2");
                jSONObject.put("index", "");
                jSONObject.put("session_c", "");
                jSONObject.put("real_msg", "");
                jSONObject.put("clientsdkversion", String.valueOf(0));
                JMA.report(com.jd.stat.security.c.a, jSONObject);
            } catch (Exception e2) {
                com.jd.stat.common.b.b.a("bot", "TokenSender reportEvent error:" + e2.getMessage());
            }
        }

        private static String b(String str, String str2) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (charAt == '*') {
                    double random = Math.random();
                    double length = str2.length();
                    Double.isNaN(length);
                    sb.append(str2.charAt((int) (random * length)));
                } else if (str2.indexOf(charAt) >= 0) {
                    sb.append(charAt);
                } else {
                    sb.append('z');
                }
            }
            return sb.length() == 0 ? "z" : sb.toString();
        }

        private static String b(long j2, String str) {
            try {
                String valueOf = String.valueOf(j2);
                if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(str)) {
                    char[] charArray = valueOf.toCharArray();
                    char[] charArray2 = str.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < charArray.length; i2++) {
                        sb.append(Integer.toHexString(charArray[i2] ^ charArray2[i2 % charArray2.length]));
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    for (int i3 = 0; i3 < sb.length(); i3++) {
                        if (i3 % 2 == 0) {
                            sb2.append(sb.charAt(i3));
                        } else {
                            sb3.append(sb.charAt(i3));
                        }
                    }
                    String str2 = sb2.toString() + ((Object) sb3);
                    return TextUtils.isEmpty(str2) ? "GKDSJENWQSAA" : str2;
                }
                return "GKDSJENWQSAA";
            } catch (Exception unused) {
                return "GKDSJENWQSAA";
            }
        }

        private static String a(Map<String, String> map) {
            if (map == null) {
                return "";
            }
            try {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                return jSONObject.toString();
            } catch (Exception unused) {
                return "";
            }
        }

        private static byte[] a(boolean z) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                a(3, byteArrayOutputStream);
                byteArrayOutputStream.write(z ? 82 : 2);
                byteArrayOutputStream.write(0);
                byteArrayOutputStream.write(Integer.parseInt(f6953i.f6960j));
                a(3, byteArrayOutputStream);
                int i2 = 128;
                int i3 = r() ? 128 : 0;
                if (q()) {
                    i3 |= 8;
                }
                byteArrayOutputStream.write(i3);
                int i4 = p() ? 128 : 0;
                if (o()) {
                    i4 |= 8;
                }
                byteArrayOutputStream.write(i4);
                if (!n()) {
                    i2 = 0;
                }
                byteArrayOutputStream.write(i2);
                StringBuilder sb = new StringBuilder();
                sb.append(k());
                sb.append("\u00f6");
                sb.append(com.jd.stat.common.c.b(com.jd.stat.security.c.a));
                sb.append("\u00f6");
                sb.append(j());
                sb.append("\u00f6");
                sb.append(l() ? "1" : "0");
                byte[] bytes = sb.toString().getBytes();
                a(bytes.length, byteArrayOutputStream);
                byteArrayOutputStream.write(bytes);
                int parseInt = Integer.parseInt(TriTouchUtil.getInstance().getClogTriTouch(), 16);
                a(3, byteArrayOutputStream);
                byteArrayOutputStream.write((16711680 & parseInt) >> 16);
                byteArrayOutputStream.write((65280 & parseInt) >> 8);
                byteArrayOutputStream.write(parseInt & 255);
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable unused) {
                return new byte[0];
            }
        }

        private static String b(String str, int i2) {
            if (str == null) {
                return "";
            }
            try {
                if (i2 >= str.length()) {
                    return str;
                }
                String substring = str.substring(0, str.length() - i2);
                return str.substring(str.length() - i2) + substring;
            } catch (Exception unused) {
                return "";
            }
        }

        private static void a(int i2, OutputStream outputStream) throws IOException {
            boolean z;
            do {
                int i3 = i2 & 127;
                i2 >>= 7;
                z = false;
                if (i2 != 0) {
                    i3 |= 128;
                } else {
                    z = true;
                }
                outputStream.write(i3);
            } while (!z);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private static String a(String str, String str2) {
            char c2;
            String e2;
            switch (str2.hashCode()) {
                case 48:
                    if (str2.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 49:
                    if (str2.equals("1")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            if (c2 == 2) {
                e2 = g.e(str);
            } else if (c2 != 3) {
                e2 = c(str);
            } else {
                e2 = g.f(str);
            }
            return (e2 == null || e2.length() == 0) ? "" : e2.toUpperCase();
        }

        private static String a(Map<String, String> map, String str, long j2, String str2, String str3, String str4) {
            ArrayList arrayList = new ArrayList(map.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() { // from class: com.jd.stat.bot.BotDetector.4
                @Override // java.util.Comparator
                /* renamed from: a  reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getKey().compareTo(entry2.getKey());
                }
            });
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!TextUtils.isEmpty((CharSequence) entry.getValue())) {
                    sb.append((String) entry.getKey());
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append((String) entry.getValue());
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            if (sb.length() == 0) {
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append("token=");
            sb.append(str);
            sb.append("&time=");
            sb.append(j2);
            sb.append("&nonce_str=");
            sb.append(str2);
            sb.append("&key=");
            sb.append(str3);
            sb.append("&is_trust=");
            sb.append(str4);
            return sb.toString();
        }

        private static String a(String str, long j2, String str2, c cVar) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < str.length(); i2++) {
                sb.append(a(str.charAt(i2), j2, str2, cVar));
            }
            return sb.toString();
        }

        private static String a(char c2, long j2, String str, c cVar) {
            String f2;
            switch (c2) {
                case 'u':
                    f2 = f(j2, str);
                    break;
                case 'v':
                    f2 = a(j2, str);
                    break;
                case 'w':
                    f2 = b(j2, str);
                    break;
                case 'x':
                    f2 = e(j2, str);
                    break;
                case 'y':
                    f2 = d(j2, str);
                    break;
                case 'z':
                    f2 = c(j2, str);
                    break;
                default:
                    f2 = "GKDSJENWQSAA";
                    break;
            }
            if (TextUtils.isEmpty(f2)) {
                f2 = "GKDSJENWQSAA";
            }
            if ("GKDSJENWQSAA".equals(f2)) {
                cVar.a = true;
            }
            return f2;
        }

        private static String a(long j2, String str) {
            try {
                String valueOf = String.valueOf(j2);
                if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(str)) {
                    char[] charArray = valueOf.toCharArray();
                    char[] charArray2 = str.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < charArray.length; i2++) {
                        sb.append(Integer.toHexString(charArray[i2] | charArray2[i2 % charArray2.length]));
                    }
                    StringBuilder sb2 = new StringBuilder();
                    StringBuilder sb3 = new StringBuilder();
                    for (int i3 = 0; i3 < sb.length(); i3++) {
                        if (i3 % 2 == 0) {
                            sb2.append(sb.charAt(i3));
                        } else {
                            sb3.append(sb.charAt(i3));
                        }
                    }
                    String str2 = sb2.toString() + sb3.toString();
                    return TextUtils.isEmpty(str2) ? "GKDSJENWQSAA" : str2;
                }
                return "GKDSJENWQSAA";
            } catch (Exception unused) {
                return "GKDSJENWQSAA";
            }
        }

        private static String a(String str, int i2) {
            if (str == null) {
                return "";
            }
            try {
                if (i2 >= str.length()) {
                    return str;
                }
                String substring = str.substring(0, i2);
                return str.substring(i2) + substring;
            } catch (Exception unused) {
                return "";
            }
        }

        private static String a(String str, byte[] bArr) {
            try {
                EncryptUtil.b();
                return a(encrypt(str, bArr));
            } catch (Throwable th) {
                com.jd.stat.common.b.b.a("JDMob.BotDetector", th.getMessage());
                return "";
            }
        }

        private static boolean a(Map<String, String> map, Map<String, String> map2) {
            if (map == map2) {
                return true;
            }
            if (map == null || map2 == null || map.size() != map2.size()) {
                return false;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.equals(map2.get(entry.getKey()), entry.getValue())) {
                    return false;
                }
            }
            return true;
        }
    }
