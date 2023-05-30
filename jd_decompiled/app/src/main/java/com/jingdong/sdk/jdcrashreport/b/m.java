package com.jingdong.sdk.jdcrashreport.b;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;

/* loaded from: classes7.dex */
class m {

    /* renamed from: e  reason: collision with root package name */
    private static String f14866e = "";

    /* renamed from: f  reason: collision with root package name */
    private static final List<String> f14867f = Arrays.asList("uuid", HybridSDK.APP_VERSION_CODE, HybridSDK.D_BRAND, HybridSDK.D_MODEL, HybridSDK.OS_VERSION, "screen", "networkType");
    private HttpURLConnection a;
    private b b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f14868c;
    private String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class b {

        /* renamed from: c  reason: collision with root package name */
        private Map<String, String> f14869c;
        private Map<String, String> d;

        /* renamed from: e  reason: collision with root package name */
        private JSONObject f14870e;
        private String a = "";
        private String b = "";

        /* renamed from: f  reason: collision with root package name */
        private c f14871f = c.GET;

        /* renamed from: g  reason: collision with root package name */
        private int f14872g = 15000;

        /* renamed from: h  reason: collision with root package name */
        private int f14873h = 10000;

        /* JADX INFO: Access modifiers changed from: package-private */
        public b a(int i2) {
            this.f14872g = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b b(c cVar) {
            this.f14871f = cVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b c(String str) {
            this.a = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b d(Map<String, String> map) {
            this.f14869c = map;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b e(JSONObject jSONObject) {
            this.f14870e = jSONObject;
            if (jSONObject == null) {
                this.f14870e = new JSONObject();
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public m f() {
            return new m(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b i(int i2) {
            this.f14873h = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b j(String str) {
            this.b = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b k(Map<String, String> map) {
            this.d = map;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public enum c {
        GET,
        POST
    }

    private void b(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        this.a = httpURLConnection;
        httpURLConnection.setReadTimeout(this.b.f14872g);
        this.a.setConnectTimeout(this.b.f14873h);
        this.a.setRequestMethod(this.b.f14871f.name());
        this.a.setDoInput(true);
        this.a.setDoOutput(true);
        this.a.setUseCaches(false);
        if (this.b.d == null || this.b.d.size() <= 0) {
            return;
        }
        for (Map.Entry entry : this.b.d.entrySet()) {
            this.a.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    private URL d() {
        StringBuilder sb = new StringBuilder(this.b.a);
        sb.append("?");
        for (Map.Entry<String, String> entry : this.f14868c.entrySet()) {
            sb.append(entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(entry.getValue(), "utf-8"));
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        if (TextUtils.isEmpty(f14866e)) {
            f14866e = com.jingdong.sdk.jdcrashreport.c.f();
        }
        String a2 = u.a(this.f14868c, this.d, f14866e);
        r.b("JDCrashReport.DefaultHttpClient", "sign " + a2);
        if (!TextUtils.isEmpty(a2)) {
            sb.append(IStatInfoConfig.REPORT_PARAM_SIGN);
            sb.append(a2);
        } else {
            sb.deleteCharAt(sb.length() - 1);
        }
        return new URL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.a);
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append(this.b.b);
        sb.append(" <--- ");
        for (Map.Entry entry : this.b.f14869c.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append((String) entry.getValue());
            sb.append(DYConstants.DY_REGEX_COMMA);
        }
        r.b("JDCrashReport.DefaultHttpClient", sb.toString());
        r.b("JDCrashReport.DefaultHttpClient", this.b.b + " url <--- " + this.a.getURL().toString());
        this.a.connect();
        if (this.a.getRequestMethod().equals(c.POST.name()) && this.d != null) {
            r.b("JDCrashReport.DefaultHttpClient", this.b.b + " body <--- " + this.b.f14870e.toString());
            r.b("JDCrashReport.DefaultHttpClient", this.b.b + " body <--- " + this.d);
            OutputStream outputStream = this.a.getOutputStream();
            outputStream.write(("body=" + URLEncoder.encode(this.d, "utf-8")).getBytes());
            outputStream.close();
        }
        String headerField = this.a.getHeaderField("Content-Encoding");
        r.f("JDCrashReport.DefaultHttpClient", "responseCode: " + this.a.getResponseCode());
        if (200 == this.a.getResponseCode()) {
            if ("gzip".equals(headerField)) {
                inputStream = new GZIPInputStream(this.a.getInputStream());
            } else {
                inputStream = this.a.getInputStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb2.append(readLine);
                } else {
                    bufferedReader.close();
                    r.f("JDCrashReport.DefaultHttpClient", this.b.b + " response <--- " + ((Object) sb2));
                    return String.valueOf(sb2);
                }
            }
        } else {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.a.getErrorStream()));
            StringBuilder sb3 = new StringBuilder();
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    break;
                }
                sb3.append(readLine2);
            }
            bufferedReader2.close();
            r.h("JDCrashReport.DefaultHttpClient", this.b.b + " error <--- " + ((Object) sb3));
            throw new IllegalStateException(sb3.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        try {
            HttpURLConnection httpURLConnection = this.a;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                this.a = null;
            }
        } catch (Throwable th) {
            r.h("JDCrashReport.DefaultHttpClient", th.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private m(b bVar) {
        this.a = null;
        this.f14868c = new HashMap();
        if (bVar != null && !TextUtils.isEmpty(bVar.a)) {
            this.b = bVar;
            if (bVar.f14869c == null) {
                this.b.f14869c = new HashMap();
            }
            com.jingdong.sdk.jdcrashreport.c.c(this.b.f14869c, this.b.b);
            for (Map.Entry entry : this.b.f14869c.entrySet()) {
                if (f14867f.contains(entry.getKey())) {
                    this.f14868c.put(entry.getKey(), com.jingdong.sdk.jdcrashreport.b.a.a.a(com.jingdong.sdk.jdcrashreport.b.a.a.c(((String) entry.getValue()).getBytes())));
                } else {
                    this.f14868c.put(entry.getKey(), entry.getValue());
                }
            }
            this.d = com.jingdong.sdk.jdcrashreport.b.a.a.a(com.jingdong.sdk.jdcrashreport.b.a.a.c(this.b.f14870e.toString().getBytes()));
            b(d());
            return;
        }
        throw new IllegalAccessException("please check http client config!");
    }
}
