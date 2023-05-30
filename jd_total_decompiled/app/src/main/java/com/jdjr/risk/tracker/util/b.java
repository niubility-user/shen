package com.jdjr.risk.tracker.util;

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
import java.util.Map;

/* loaded from: classes18.dex */
public class b {
    private static String a(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return new String(byteArray, "UTF-8");
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String a(String str, String str2) {
        return a(str, "POST", null, str2);
    }

    public static String a(String str, String str2, Map<String, String> map, String str3) {
        String str4 = "";
        try {
            HttpURLConnection a = a(new URL(str));
            if (map != null && map.size() > 0) {
                for (String str5 : map.keySet()) {
                    a.addRequestProperty(str5, map.get(str5));
                }
            }
            a.setRequestMethod(str2);
            if (str3 != null && str3.length() > 0) {
                a(a, str3);
            }
            if (a.getResponseCode() == 200) {
                InputStream inputStream = a.getInputStream();
                str4 = a(inputStream);
                inputStream.close();
                a.disconnect();
            }
            a.disconnect();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return str4;
    }

    private static HttpURLConnection a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(9000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.addRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
        httpURLConnection.addRequestProperty("charset", "utf-8");
        byte[] bytes = str.getBytes("utf-8");
        httpURLConnection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bytes);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
}
