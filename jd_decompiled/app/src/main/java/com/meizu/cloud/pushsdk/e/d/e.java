package com.meizu.cloud.pushsdk.e.d;

import android.net.TrafficStats;
import com.google.common.net.HttpHeaders;
import com.jd.jdcache.util.UrlHelper;
import com.meizu.cloud.pushsdk.e.d.k;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes14.dex */
public class e implements com.meizu.cloud.pushsdk.e.d.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends l {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.meizu.cloud.pushsdk.e.h.d f15798g;

        a(HttpURLConnection httpURLConnection, com.meizu.cloud.pushsdk.e.h.d dVar) {
            this.f15798g = dVar;
        }

        @Override // com.meizu.cloud.pushsdk.e.d.l
        public com.meizu.cloud.pushsdk.e.h.d g() {
            return this.f15798g;
        }
    }

    private static l b(HttpURLConnection httpURLConnection) throws IOException {
        if (httpURLConnection.getDoInput()) {
            return new a(httpURLConnection, com.meizu.cloud.pushsdk.e.h.g.b(com.meizu.cloud.pushsdk.e.h.g.f(e(httpURLConnection.getResponseCode()) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream())));
        }
        return null;
    }

    private static void d(HttpURLConnection httpURLConnection, i iVar) throws IOException {
        j a2 = iVar.a();
        if (a2 != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, a2.g().toString());
            com.meizu.cloud.pushsdk.e.h.c a3 = com.meizu.cloud.pushsdk.e.h.g.a(com.meizu.cloud.pushsdk.e.h.g.c(httpURLConnection.getOutputStream()));
            a2.f(a3);
            a3.close();
        }
    }

    protected static boolean e(int i2) {
        return i2 >= 200 && i2 < 300;
    }

    private HttpURLConnection f(i iVar) throws IOException {
        URL url = new URL(iVar.f().toString());
        if (MinSdkChecker.isSupportNotificationChannel()) {
            TrafficStats.setThreadStatsTag(2006537699);
        }
        HttpURLConnection c2 = c(url);
        c2.setConnectTimeout(60000);
        c2.setReadTimeout(60000);
        c2.setUseCaches(false);
        c2.setDoInput(true);
        return c2;
    }

    private static void g(HttpURLConnection httpURLConnection, i iVar) throws IOException {
        String str;
        String str2;
        int c2 = iVar.c();
        if (c2 != 0) {
            if (c2 == 1) {
                str2 = "POST";
            } else if (c2 == 2) {
                str2 = UrlHelper.METHOD_PUT;
            } else if (c2 == 3) {
                str = UrlHelper.METHOD_DELETE;
            } else if (c2 == 4) {
                str = UrlHelper.METHOD_HEAD;
            } else if (c2 != 5) {
                throw new IllegalStateException("Unknown method type.");
            } else {
                str2 = UrlHelper.METHOD_PATCH;
            }
            httpURLConnection.setRequestMethod(str2);
            d(httpURLConnection, iVar);
            return;
        }
        str = "GET";
        httpURLConnection.setRequestMethod(str);
    }

    @Override // com.meizu.cloud.pushsdk.e.d.a
    public k a(i iVar) throws IOException {
        HttpURLConnection f2 = f(iVar);
        for (String str : iVar.d().e()) {
            String b = iVar.b(str);
            com.meizu.cloud.pushsdk.e.b.a.c("current header name " + str + " value " + b);
            f2.addRequestProperty(str, b);
        }
        g(f2, iVar);
        int responseCode = f2.getResponseCode();
        String responseMessage = f2.getResponseMessage();
        k.b bVar = new k.b();
        bVar.b(responseCode);
        bVar.c(iVar.d());
        bVar.f(responseMessage);
        bVar.d(iVar);
        bVar.e(b(f2));
        return bVar.g();
    }

    protected HttpURLConnection c(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }
}
