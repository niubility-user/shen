package com.jingdong.manto.network.common;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/* loaded from: classes16.dex */
public class c {

    /* loaded from: classes16.dex */
    public static final class a implements Interceptor {
        final b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Response proceed = chain.proceed(chain.request());
            return proceed.newBuilder().body(new e(proceed.body(), this.a)).build();
        }
    }

    private static String a(String str) {
        return "https://service.vapp.jd.com/" + str + "/1/page-frame.html";
    }

    private static String a(Response response) {
        String header = response.header(HttpHeaders.CONTENT_DISPOSITION);
        if (!TextUtils.isEmpty(header)) {
            String[] split = header.split("; ");
            if (split.length > 1) {
                return split[1].replace("filename=", "").replace("\"", "");
            }
        }
        return "";
    }

    private static OkHttpClient.Builder a() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return builder.readTimeout(30L, timeUnit).connectTimeout(15L, timeUnit).writeTimeout(60L, timeUnit);
    }

    public static void a(com.jingdong.manto.network.mantorequests.a aVar, boolean z, String str, boolean z2, b bVar) {
        File file;
        StringBuilder sb;
        File file2;
        OkHttpClient.Builder addNetworkInterceptor = a().addNetworkInterceptor(new a(bVar));
        if (z2) {
            addNetworkInterceptor.writeTimeout(30L, TimeUnit.SECONDS);
        }
        OkHttpClient build = addNetworkInterceptor.build();
        File file3 = null;
        try {
            Call newCall = build.newCall(new Request.Builder().get().url(aVar.c()).addHeader("mimeType", "application/octet-stream").addHeader("referer", a(str)).build());
            String b = aVar.b();
            String a2 = aVar.a();
            Response execute = newCall.execute();
            if (!execute.isSuccessful()) {
                bVar.a(new Exception(String.format("An error occurred when download file %s", a2)));
                return;
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = a(execute);
            }
            if (TextUtils.isEmpty(b)) {
                b = com.jingdong.manto.c.a().getFilesDir().getAbsolutePath() + File.pathSeparator + "manto";
            }
            BufferedSource source = execute.body().source();
            if (a2.indexOf(OrderISVUtil.MONEY_DECIMAL) >= 0) {
                sb = new StringBuilder();
                sb.append(a2.substring(0, a2.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)));
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(System.currentTimeMillis());
                sb.append(a2.substring(a2.lastIndexOf(OrderISVUtil.MONEY_DECIMAL)));
            } else {
                sb = new StringBuilder();
                sb.append(a2);
                sb.append(System.currentTimeMillis());
            }
            file = new File(b, sb.toString());
            try {
                file.delete();
                file.getParentFile().mkdirs();
                file.createNewFile();
                BufferedSink buffer = Okio.buffer(Okio.sink(file));
                source.readAll(buffer);
                buffer.flush();
                source.close();
                buffer.close();
                file2 = new File(b, a2);
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (file2.exists() && !z) {
                    file.delete();
                    bVar.a(new com.jingdong.manto.network.mantorequests.b(aVar, file2.getParent(), file2.getName()));
                }
                file.renameTo(file2);
                bVar.a(new com.jingdong.manto.network.mantorequests.b(aVar, file2.getParent(), file2.getName()));
            } catch (Throwable th2) {
                th = th2;
                file3 = file2;
                if (file3 != null) {
                    file3.delete();
                }
                if (file != null) {
                    file.delete();
                }
                bVar.a(th);
            }
        } catch (Throwable th3) {
            th = th3;
            file = null;
        }
    }
}
