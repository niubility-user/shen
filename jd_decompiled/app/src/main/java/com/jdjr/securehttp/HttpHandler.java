package com.jdjr.securehttp;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jdjr.tools.JDJRLog;
import com.jdpay.net.http.HTTP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* loaded from: classes18.dex */
public class HttpHandler {
    private static final String TAG = "HttpHandler";

    private void setTrustManager(final String str, HttpURLConnection httpURLConnection) {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.jdjr.securehttp.HttpHandler.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                    JDJRLog.d("setTrustManager", "checkClientTrusted");
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str2) throws CertificateException {
                    JDJRLog.d("setTrustManager", "checkServerTrusted");
                    HttpsUtils.checkHttpsCert(x509CertificateArr, str);
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLContext.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new HostnameVerifier() { // from class: com.jdjr.securehttp.HttpHandler.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str2, SSLSession sSLSession) {
                    try {
                        return HttpsUtils.isHostMatch((X509Certificate) sSLSession.getPeerCertificates()[0], str2);
                    } catch (SSLPeerUnverifiedException e2) {
                        e2.printStackTrace();
                        return false;
                    }
                }
            });
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        }
    }

    public JDJRResultMessage sendHttpRequest(String str, String str2, int i2) {
        String str3 = GeneralErrorCode.NETWORK_ERROR;
        JDJRLog.i(TAG, "sendPostHttp: reqData=" + str);
        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
            if (i2 == 0) {
                httpURLConnection.setRequestProperty("ET", IShareAdapter.SHARE_ACTION_PANE);
            } else if (i2 == 1) {
                httpURLConnection.setRequestProperty("ET", DYConstants.LETTER_H);
            } else if (i2 == 2) {
                httpURLConnection.setRequestProperty("ET", "SM");
            }
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("connection", "close");
            System.setProperty("http.keepAlive", DYConstants.DY_FALSE);
            if (!TextUtils.isEmpty(str2) && str2.contains("https")) {
                setTrustManager(str2, httpURLConnection);
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8");
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                inputStream.close();
                bufferedReader.close();
                httpURLConnection.disconnect();
                str3 = "00000";
            } else {
                JDJRLog.e(TAG, "http status code:" + httpURLConnection.getResponseCode());
                httpURLConnection.disconnect();
            }
        } catch (UnsupportedEncodingException e2) {
            JDJRLog.e(TAG, "sendHttpRequest UnsupportedEncodingException:" + e2.getMessage());
            e2.printStackTrace();
        } catch (MalformedURLException e3) {
            JDJRLog.e(TAG, "sendHttpRequest MalformedURLException:" + e3.getMessage());
            e3.printStackTrace();
        } catch (ProtocolException e4) {
            JDJRLog.e(TAG, "sendHttpRequest ProtocolException:" + e4.getMessage());
            e4.printStackTrace();
        } catch (IOException e5) {
            JDJRLog.e(TAG, "sendHttpRequest IOException:" + e5.getMessage());
            e5.printStackTrace();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        if (sb.length() != 0) {
            return new JDJRResultMessage(sb.toString().getBytes(), str3);
        }
        if (sb.length() == 0 && str3 == "00000") {
            return new JDJRResultMessage("".getBytes(), GeneralErrorCode.EMPTY_RESPONSE);
        }
        return new JDJRResultMessage("".getBytes(), str3);
    }
}
