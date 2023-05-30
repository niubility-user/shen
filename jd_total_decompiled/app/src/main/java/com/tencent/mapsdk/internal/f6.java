package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.net.HttpHeaders;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.NetUtil;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.ca;
import com.tencent.mapsdk.internal.o1;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class f6 {
    private static volatile boolean a;

    /* loaded from: classes9.dex */
    public static class a extends ba.c<JSONObject> {
        public final /* synthetic */ e a;
        public final /* synthetic */ Context b;

        /* renamed from: c */
        public final /* synthetic */ SharedPreferences f16502c;

        public a(e eVar, Context context, SharedPreferences sharedPreferences) {
            this.a = eVar;
            this.b = context;
            this.f16502c = sharedPreferences;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a.b = (f) JsonUtils.parseToModel(jSONObject, f.class, new Object[0]);
                f6.b(this.b, this.f16502c, this.a);
                JSONObject modelToJson = JsonUtils.modelToJson(this.a);
                if (modelToJson != null) {
                    ma.c(la.f16822i, "\u4fdd\u5b58\u4e0a\u62a5\u6587\u4ef6\u81f3\u672c\u5730");
                    ha.a(this.f16502c).a("reportFile", modelToJson.toString());
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends ba.i<JSONObject> {
        public final /* synthetic */ e b;

        /* renamed from: c */
        public final /* synthetic */ o1.b f16503c;

        public b(e eVar, o1.b bVar) {
            this.b = eVar;
            this.f16503c = bVar;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public JSONObject call() {
            NetResponse uploadToken = ((t2) ((f3) l2.a(f3.class)).d()).uploadToken(this.b.c(), this.f16503c.g(), this.f16503c.h());
            ma.c(la.f16822i, "\u54cd\u5e94\u72b6\u6001\uff1a" + uploadToken.statusCode);
            if (uploadToken.available()) {
                String a = e7.a(uploadToken.data, uploadToken.charset);
                ma.c(la.f16822i, "\u83b7\u53d6\u7f51\u7edctoken\u6570\u636e\uff1a" + a);
                if (TextUtils.isEmpty(a)) {
                    return null;
                }
                return new JSONObject(a).optJSONObject("detail");
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class c implements View.OnClickListener {
        public final /* synthetic */ e a;
        public final /* synthetic */ Context b;

        /* renamed from: c */
        public final /* synthetic */ ca.a f16504c;
        public final /* synthetic */ SharedPreferences d;

        /* loaded from: classes9.dex */
        public class a implements Callback<Boolean> {
            public a() {
                c.this = r1;
            }

            @Override // com.tencent.map.tools.Callback
            /* renamed from: a */
            public void callback(Boolean bool) {
                if (!bool.booleanValue()) {
                    ma.c(la.f16822i, "\u6e05\u7406\u672c\u5730\u7f13\u5b58");
                    ha.a(c.this.d).a("reportFile", "");
                }
                c.this.f16504c.a();
                boolean unused = f6.a = false;
            }
        }

        public c(e eVar, Context context, ca.a aVar, SharedPreferences sharedPreferences) {
            this.a = eVar;
            this.b = context;
            this.f16504c = aVar;
            this.d = sharedPreferences;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (f6.a || !this.a.a(this.b)) {
                ma.c(la.f16822i, "\u6b63\u5728\u4e0a\u4f20\u4e2d");
                return;
            }
            this.f16504c.a("\u4e0a\u62a5\u4e2d", (View.OnClickListener) null);
            this.a.a(new a());
            boolean unused = f6.a = true;
        }
    }

    /* loaded from: classes9.dex */
    public static class d implements Callback<Boolean> {
        public final /* synthetic */ SharedPreferences a;

        public d(SharedPreferences sharedPreferences) {
            this.a = sharedPreferences;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(Boolean bool) {
            if (!bool.booleanValue()) {
                ma.c(la.f16822i, "\u6e05\u7406\u672c\u5730\u7f13\u5b58");
                ha.a(this.a).a("reportFile", "");
            }
            boolean unused = f6.a = false;
        }
    }

    /* loaded from: classes9.dex */
    public static class e extends JsonComposer {
        @Json(name = "name")
        public String a;
        @Json(name = "token")
        public f b;
        @Json(name = "create_time")

        /* renamed from: c */
        public long f16505c;
        @Json(ignore = true)
        public o1 d;

        /* loaded from: classes9.dex */
        public class a extends ba.c<Boolean> {
            public final /* synthetic */ Callback a;

            public a(Callback callback) {
                e.this = r1;
                this.a = callback;
            }

            @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
            /* renamed from: a */
            public void callback(Boolean bool) {
                Callback callback = this.a;
                if (callback != null) {
                    callback.callback(bool);
                }
            }
        }

        /* loaded from: classes9.dex */
        public class b extends ba.i<Boolean> {
            public b() {
                e.this = r1;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Boolean call() {
                return Boolean.valueOf(e.this.g());
            }
        }

        public e(o1 o1Var) {
            this(o1Var, "");
        }

        public e(o1 o1Var, String str) {
            this.a = str;
            this.d = o1Var;
            this.f16505c = System.currentTimeMillis();
        }

        private byte[] e() {
            StringBuilder sb = new StringBuilder();
            o1.b q = this.d.q();
            sb.append(b7.a(q.j(), q.k()));
            sb.append("&engine_draw_version=");
            sb.append(this.d.p());
            sb.append("&engine_data_version=");
            sb.append(this.d.n());
            sb.append("&camera=");
            sb.append(this.d.j().getMap().getCameraPosition());
            ma.c(la.f16822i, "\u65e5\u5fd7\u6570\u636e:" + ((Object) sb));
            return sb.toString().getBytes();
        }

        public boolean g() {
            byte[] h2;
            File[] listFiles;
            if (this.d == null) {
                return false;
            }
            File file = new File(lc.b(this.d.getContext()).h());
            File a2 = fa.a(file, d());
            ma.c(la.f16822i, "\u521b\u5efa\u4e0a\u4f20\u6587\u4ef6\u76ee\u5f55:" + a2);
            File b2 = fa.b(a2, "base-info.txt");
            fa.b(b2, e());
            ma.c(la.f16822i, "\u6536\u96c6\u65e5\u5fd7\u6570\u636e\u81f3\u6587\u4ef6:" + b2);
            fa.a(new File(lc.b(this.d.getContext()).c(this.d.q().j())), new File(a2, "config"));
            String f2 = this.d.o().f();
            if (!TextUtils.isEmpty(f2)) {
                File b3 = fa.b(a2, "engine-crash-info.txt");
                ma.c(la.f16822i, "\u6536\u96c6\u5f15\u64ceCrash\u81f3\u6587\u4ef6:" + b3);
                fa.b(b3, f2.getBytes());
            }
            String b4 = this.d.o().b();
            if (!TextUtils.isEmpty(b4)) {
                File b5 = fa.b(a2, "engine-log-info.txt");
                ma.c(la.f16822i, "\u6536\u96c6\u5f15\u64ce\u65e5\u5fd7\u81f3\u6587\u4ef6:" + b5);
                fa.b(b5, b4.getBytes());
            }
            File g2 = u.f().g();
            if (g2 != null && g2.exists() && g2.isDirectory() && (listFiles = g2.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2 != null && file2.exists() && file2.isFile()) {
                        fa.a(file2, new File(a2, "plugin"));
                    }
                }
            }
            String a3 = ma.a();
            if (!TextUtils.isEmpty(a3)) {
                File file3 = new File(a3);
                if (file3.exists() && file3.isDirectory()) {
                    File[] c2 = fa.c(file3, ".*.log.*");
                    if (c2 != null) {
                        for (File file4 : c2) {
                            if (file4 != null && file4.exists() && file4.isFile()) {
                                fa.a(file4, new File(a2, "logs"));
                            }
                        }
                    }
                    File[] c3 = fa.c(file3, "archive-.*.zip");
                    if (c3 != null) {
                        for (File file5 : c3) {
                            if (file5 != null && file5.exists() && file5.isFile()) {
                                fa.a(file5, new File(a2, "archives"));
                            }
                        }
                    }
                }
            }
            File c4 = ia.c(a2, file.getAbsolutePath());
            ma.c(la.f16822i, "\u6253\u5305\u6210zip\u6587\u4ef6:" + c4);
            if (c4 == null || (h2 = fa.h(c4)) == null) {
                return false;
            }
            ma.c(la.f16822i, "zip\u6587\u4ef6\u5927\u5c0f:" + h2.length);
            if (h2.length > 0) {
                ma.c(la.f16822i, "\u5f00\u59cb\u4e0a\u4f20\u6587\u4ef6\u5230\uff1a" + f());
                NetResponse doRequest = NetManager.getInstance().doRequest(new NetRequest(NetMethod.PUT, f()).setPostData(h2).setMapHeaders(HttpHeaders.CONTENT_LENGTH, "" + h2.length).setMapHeaders(HttpHeaders.AUTHORIZATION, this.b.a).setMapHeaders("x-cos-content-sha1", wa.a(c4)).setTimeout(120000));
                ma.c(la.f16822i, "\u7ed3\u675f\u4e0a\u4f20\u6587\u4ef6");
                fa.d(c4);
                fa.d(a2);
                int i2 = doRequest.statusCode;
                ma.c(la.f16822i, "\u4e0a\u4f20\u72b6\u6001:" + i2);
                return i2 == 200;
            }
            return false;
        }

        public long a() {
            return this.f16505c;
        }

        public void a(Callback<Boolean> callback) {
            ba.a((ba.i) new b()).b(null, new a(callback));
        }

        public boolean a(Context context) {
            f fVar;
            return (!NetUtil.isWifi(context) || (fVar = this.b) == null || fVar.a()) ? false : true;
        }

        public long b() {
            f fVar = this.b;
            if (fVar != null) {
                return Long.parseLong(fVar.b);
            }
            return 0L;
        }

        public String c() {
            return d() + ".zip";
        }

        public String d() {
            return "android-" + this.a + "-" + this.f16505c;
        }

        public String f() {
            return "https://" + this.b.f16506c + "/" + c();
        }
    }

    /* loaded from: classes9.dex */
    public static class f extends JsonComposer {
        @Json(name = "token")
        public String a;
        @Json(name = "expire")
        public String b;
        @Json(name = "host")

        /* renamed from: c */
        public String f16506c;

        public boolean a() {
            try {
                return Long.parseLong(this.b) < SystemClock.uptimeMillis() / 1000;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }
    }

    public static boolean a(o1 o1Var) {
        e eVar;
        f fVar;
        if (o1Var == null || o1Var.A() || o1Var.v().b()) {
            return false;
        }
        Context context = o1Var.getContext();
        o1.b q = o1Var.q();
        String f2 = q.f();
        SharedPreferences a2 = ha.a(context, "uploadConfig." + q.c());
        String string = a2.getString("reportFile", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                ma.c(la.f16822i, "\u83b7\u53d6\u672c\u5730\u4e0a\u62a5\u6587\u4ef6\uff1a" + string);
                eVar = (e) JsonUtils.parseToModel(new JSONObject(string), e.class, o1Var);
            } catch (Exception unused) {
            }
            if (eVar != null || !f2.equals(eVar.a)) {
                ma.c(la.f16822i, "\u91cd\u65b0\u521b\u5efa\u4e0a\u62a5\u6587\u4ef6");
                eVar = new e(o1Var, f2);
            }
            fVar = eVar.b;
            if (fVar == null && !fVar.a()) {
                ma.c(la.f16822i, "\u4f7f\u7528\u672c\u5730\u4e0a\u62a5\u6587\u4ef6");
                return b(context, a2, eVar);
            }
            ma.c(la.f16822i, "\u8bf7\u6c42token");
            ba.a((ba.i) new b(eVar, q)).b(null, new a(eVar, context, a2));
            return false;
        }
        eVar = null;
        if (eVar != null) {
        }
        ma.c(la.f16822i, "\u91cd\u65b0\u521b\u5efa\u4e0a\u62a5\u6587\u4ef6");
        eVar = new e(o1Var, f2);
        fVar = eVar.b;
        if (fVar == null) {
        }
        ma.c(la.f16822i, "\u8bf7\u6c42token");
        ba.a((ba.i) new b(eVar, q)).b(null, new a(eVar, context, a2));
        return false;
    }

    public static boolean b(Context context, SharedPreferences sharedPreferences, e eVar) {
        if (eVar != null && context != null) {
            if (ma.d(la.f16822i) && Build.VERSION.SDK_INT < 30) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("\u65e5\u5fd7\u540d\u79f0:");
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    sb.append(eVar.d());
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    String format = SimpleDateFormat.getInstance().format(new Date(eVar.a()));
                    sb.append("\u521b\u5efa\u65f6\u95f4:");
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    sb.append(format);
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    String format2 = SimpleDateFormat.getInstance().format(new Date(eVar.b() * 1000));
                    sb.append("\u8fc7\u671f\u65f6\u95f4:");
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    sb.append(format2);
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    ca.a a2 = ca.a(context, "\u8c03\u8bd5\u6a21\u5f0f", sb.toString(), 1);
                    return a2.a(true).a("\u4e0a\u62a5(\u4ec5WIFI)", new c(eVar, context, a2, sharedPreferences)).b();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (!a && eVar.a(context)) {
                eVar.a(new d(sharedPreferences));
                a = true;
            }
        }
        return false;
    }
}
