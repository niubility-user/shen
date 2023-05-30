package com.jd.stat.bot;

import com.jd.stat.common.b.b;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f6965c;

    public a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.f6965c = str3;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.a;
            String str2 = "";
            if (str == null) {
                str = "";
            }
            jSONObject.put("status", str);
            String str3 = this.b;
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put("result", str3);
            String str4 = this.f6965c;
            if (str4 != null) {
                str2 = str4;
            }
            jSONObject.put("clog", str2);
        } catch (Exception e2) {
            b.a("RiskResult", e2.getClass().getCanonicalName(), e2);
        }
        return jSONObject;
    }

    /* renamed from: com.jd.stat.bot.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    public static class C0217a {
        private long a = -1;
        private String b = "2";

        /* renamed from: c  reason: collision with root package name */
        private String f6966c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f6967e;

        /* renamed from: f  reason: collision with root package name */
        private String f6968f;

        /* renamed from: g  reason: collision with root package name */
        private String f6969g;

        /* renamed from: h  reason: collision with root package name */
        private String f6970h;

        /* renamed from: i  reason: collision with root package name */
        private String f6971i;

        /* renamed from: j  reason: collision with root package name */
        private String f6972j;

        public String a() {
            StringBuilder sb = new StringBuilder();
            if (this.a == -1) {
                this.a = System.currentTimeMillis();
            }
            sb.append(this.a);
            sb.append("~");
            String str = this.b;
            if (str == null) {
                str = "2";
            }
            sb.append(str);
            String str2 = this.f6966c;
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            String str3 = this.d;
            if (str3 == null) {
                str3 = "";
            }
            sb.append(str3);
            sb.append("~");
            String str4 = this.f6967e;
            if (str4 == null) {
                str4 = "";
            }
            sb.append(str4);
            sb.append("~");
            String str5 = this.f6968f;
            if (str5 == null) {
                str5 = "";
            }
            sb.append(str5);
            sb.append("~");
            String str6 = this.f6969g;
            if (str6 == null) {
                str6 = "";
            }
            sb.append(str6);
            sb.append("~");
            String str7 = this.f6970h;
            if (str7 == null) {
                str7 = "21";
            }
            sb.append(str7);
            sb.append("~");
            String str8 = this.f6971i;
            if (str8 == null) {
                str8 = "";
            }
            sb.append(str8);
            sb.append("~");
            String str9 = this.f6972j;
            sb.append(str9 != null ? str9 : "");
            return sb.toString();
        }

        public C0217a b(String str) {
            this.f6966c = str;
            return this;
        }

        public C0217a c(String str) {
            this.d = str;
            return this;
        }

        public C0217a d(String str) {
            this.f6967e = str;
            return this;
        }

        public C0217a e(String str) {
            this.f6968f = str;
            return this;
        }

        public C0217a f(String str) {
            this.f6969g = str;
            return this;
        }

        public C0217a g(String str) {
            this.f6970h = str;
            return this;
        }

        public C0217a h(String str) {
            this.f6971i = str;
            return this;
        }

        public C0217a i(String str) {
            this.f6972j = str;
            return this;
        }

        @NotNull
        public String toString() {
            return a();
        }

        public C0217a a(long j2) {
            this.a = j2;
            return this;
        }

        public C0217a a(String str) {
            this.b = str;
            return this;
        }
    }
}
