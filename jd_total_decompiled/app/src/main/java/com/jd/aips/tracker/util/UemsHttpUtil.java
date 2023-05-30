package com.jd.aips.tracker.util;

import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes12.dex */
public class UemsHttpUtil {
    public static String a(String str, String str2) {
        String str3 = "";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            if (str2 != null && str2.length() > 0) {
                a(httpURLConnection, str2);
            }
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                str3 = a(inputStream);
                inputStream.close();
                httpURLConnection.disconnect();
            }
            httpURLConnection.disconnect();
        } catch (MalformedURLException | IOException unused) {
        }
        return str3;
    }

    private static String a(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return new String(byteArray, "UTF-8");
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
        httpURLConnection.addRequestProperty("charset", "UTF-8");
        byte[] bytes = str.getBytes("UTF-8");
        httpURLConnection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
}
