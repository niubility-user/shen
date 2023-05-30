package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

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
    */
    public int download(String str, DownloadListener downloadListener) {
        JSONObject jSONObject;
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        BufferedReader bufferedReader = null;
        try {
            try {
                URL url = new URL(str);
                this.url = url;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (cookiesFlag) {
                    httpURLConnection.setRequestProperty("Cookie", cookies);
                }
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (Exception unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (TextUtils.isEmpty(stringBuffer.toString())) {
                            return -1;
                        }
                        jSONObject = new JSONObject(stringBuffer.toString());
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception unused2) {
                                throw th;
                            }
                        }
                        if (!TextUtils.isEmpty(stringBuffer.toString())) {
                            JSONObject jSONObject2 = new JSONObject(stringBuffer.toString());
                            if ("0".equals(jSONObject2.getString("code"))) {
                                downloadListener.onDownloadEnd(jSONObject2);
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
                if (TextUtils.isEmpty(stringBuffer.toString())) {
                    return -1;
                }
                jSONObject = new JSONObject(stringBuffer.toString());
            } catch (Exception unused3) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused4) {
            return -1;
        }
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
