package com.jingdong.app.mall.aura;

/* loaded from: classes19.dex */
public class AuraState {
    public static final String DEFAULT_STATE = "default";
    public static final String INIT_FAIL = "fail";
    public static final String INIT_SUCCESS = "success";

    /* loaded from: classes19.dex */
    static class a {
        public static String a = "";
        public static String b = "default";

        /* renamed from: c  reason: collision with root package name */
        public static String f7916c = "";
    }

    /* loaded from: classes19.dex */
    static class b {
        public static String a = "";
        public static String b = "default";

        /* renamed from: c  reason: collision with root package name */
        public static String f7917c = "";
    }

    public static String formatAuraEngineState() {
        return String.format("{id:%s,AuraEngineInitState:%s,msg:%s}", a.a, a.b, a.f7916c);
    }

    public static String formatAuraUpdateState() {
        return String.format("{id:%s,AuraUpdateInitState:%s,msg:%s}", b.a, b.b, b.f7917c);
    }

    public static void updateAuraEngineState(String str, String str2, Throwable th) {
        try {
            a.a = str2;
            a.b = str;
            String str3 = "";
            if (th != null && th.getMessage() != null) {
                str3 = th.getMessage();
            }
            a.f7916c = str3;
        } catch (Throwable unused) {
        }
    }

    public static void updateAuraUpdateState(String str, String str2, Throwable th) {
        try {
            b.a = str2;
            b.b = str;
            String str3 = "";
            if (th != null && th.getMessage() != null) {
                str3 = th.getMessage();
            }
            b.f7917c = str3;
        } catch (Throwable unused) {
        }
    }
}
