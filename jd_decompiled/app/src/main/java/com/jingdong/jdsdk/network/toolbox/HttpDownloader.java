package com.jingdong.jdsdk.network.toolbox;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes14.dex */
public class HttpDownloader {
    private static final int CONNECTION_TIME_OUT = 15000;
    private static String cookies;
    private static boolean cookiesFlag;
    private URL url = null;

    /* loaded from: classes14.dex */
    public interface DownloadListener {
        void onDownloadEnd(Object obj);
    }

    public static String getCookies() {
        return cookies;
    }

    public static boolean isCookiesFlag() {
        return cookiesFlag;
    }

    public static void setCookies(String str) {
        cookies = str;
    }

    public static void setCookiesFlag(boolean z) {
        cookiesFlag = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x005a, code lost:
        if ("0".equals(r9.getString("code")) != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005c, code lost:
        r10.onDownloadEnd(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0060, code lost:
        r3 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00af, code lost:
        if ("0".equals(r9.getString("code")) != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int download(java.lang.String r9, com.jingdong.jdsdk.network.toolbox.HttpDownloader.DownloadListener r10) {
        /*
            r8 = this;
            java.lang.String r0 = "code"
            java.lang.String r1 = "0"
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r3 = 0
            r4 = -1
            r5 = 0
            java.net.URL r6 = new java.net.URL     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            r8.url = r6     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            java.net.URLConnection r9 = r6.openConnection()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            boolean r6 = com.jingdong.jdsdk.network.toolbox.HttpDownloader.cookiesFlag     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            if (r6 == 0) goto L24
            java.lang.String r6 = "Cookie"
            java.lang.String r7 = com.jingdong.jdsdk.network.toolbox.HttpDownloader.cookies     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            r9.setRequestProperty(r6, r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
        L24:
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            java.io.InputStream r9 = r9.getInputStream()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            r7.<init>(r9)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L8f
        L32:
            java.lang.String r9 = r6.readLine()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            if (r9 == 0) goto L3c
            r2.append(r9)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            goto L32
        L3c:
            r6.close()     // Catch: java.lang.Exception -> Lb2
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Exception -> Lb2
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> Lb2
            if (r9 != 0) goto Lb2
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb2
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lb2
            r9.<init>(r2)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r0 = r9.getString(r0)     // Catch: java.lang.Exception -> Lb2
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto L60
        L5c:
            r10.onDownloadEnd(r9)     // Catch: java.lang.Exception -> Lb2
            goto L61
        L60:
            r3 = -1
        L61:
            r4 = r3
            goto Lb2
        L63:
            r9 = move-exception
            r5 = r6
            goto L69
        L66:
            r5 = r6
            goto L8f
        L68:
            r9 = move-exception
        L69:
            if (r5 == 0) goto L6e
            r5.close()     // Catch: java.lang.Exception -> L8e
        L6e:
            java.lang.String r3 = r2.toString()     // Catch: java.lang.Exception -> L8e
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L8e
            if (r3 != 0) goto L8e
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L8e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L8e
            r3.<init>(r2)     // Catch: java.lang.Exception -> L8e
            java.lang.String r0 = r3.getString(r0)     // Catch: java.lang.Exception -> L8e
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> L8e
            if (r0 == 0) goto L8e
            r10.onDownloadEnd(r3)     // Catch: java.lang.Exception -> L8e
        L8e:
            throw r9
        L8f:
            if (r5 == 0) goto L94
            r5.close()     // Catch: java.lang.Exception -> Lb2
        L94:
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Exception -> Lb2
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Exception -> Lb2
            if (r9 != 0) goto Lb2
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb2
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lb2
            r9.<init>(r2)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r0 = r9.getString(r0)     // Catch: java.lang.Exception -> Lb2
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto L60
            goto L5c
        Lb2:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.toolbox.HttpDownloader.download(java.lang.String, com.jingdong.jdsdk.network.toolbox.HttpDownloader$DownloadListener):int");
    }

    public InputStream getInputStreamFromURL(String str) {
        try {
            URL url = new URL(str);
            this.url = url;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            return httpURLConnection.getInputStream();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
