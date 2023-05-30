package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.log.SLog;
import com.tencent.tauth.IRequestListener;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class HttpUtils {

    /* loaded from: classes9.dex */
    public static class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    /* loaded from: classes9.dex */
    public static class a {
        public final String a;
        public final int b;

        private a(String str, int i2) {
            this.a = str;
            this.b = i2;
        }
    }

    private HttpUtils() {
    }

    private static void a(Context context, QQToken qQToken, String str) {
        if (str.indexOf("add_share") > -1 || str.indexOf("upload_pic") > -1 || str.indexOf("add_topic") > -1 || str.indexOf("set_user_face") > -1 || str.indexOf("add_t") > -1 || str.indexOf("add_pic_t") > -1 || str.indexOf("add_pic_url") > -1 || str.indexOf("add_video") > -1) {
            com.tencent.connect.a.a.a(context, qQToken, "requireApi", str);
        }
    }

    private static Map<String, byte[]> b(Bundle bundle) {
        HashMap hashMap = new HashMap(0);
        if (bundle != null && bundle.size() != 0) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof byte[]) {
                    hashMap.put(str, (byte[]) obj);
                }
            }
        }
        return hashMap;
    }

    public static String encodeUrl(Bundle bundle) {
        return encodeUrl(a(bundle));
    }

    public static int getErrorCodeFromException(IOException iOException) {
        if (iOException instanceof CharConversionException) {
            return -20;
        }
        if (iOException instanceof MalformedInputException) {
            return -21;
        }
        if (iOException instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException instanceof EOFException) {
            return -26;
        }
        if (iOException instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException instanceof HttpRetryException) {
            return -29;
        }
        if (iOException instanceof SocketTimeoutException) {
            return -8;
        }
        if (iOException instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException instanceof MalformedURLException) {
            return -3;
        }
        if (iOException instanceof InvalidClassException) {
            return -33;
        }
        if (iOException instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException instanceof NotActiveException) {
            return -35;
        }
        if (iOException instanceof NotSerializableException) {
            return -36;
        }
        if (iOException instanceof OptionalDataException) {
            return -37;
        }
        if (iOException instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException instanceof ProtocolException) {
            return -40;
        }
        if (iOException instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException instanceof SSLKeyException) {
            return -42;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException instanceof BindException) {
            return -45;
        }
        if (iOException instanceof ConnectException) {
            return -46;
        }
        if (iOException instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException instanceof SyncFailedException) {
            return -49;
        }
        if (iOException instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException instanceof UnknownHostException) {
            return -51;
        }
        if (iOException instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException instanceof UnsupportedEncodingException) {
            return -53;
        }
        return iOException instanceof ZipException ? -54 : -2;
    }

    public static a getProxy(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return null;
        }
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.getType() == 0) {
            String b = b(context);
            int a2 = a(context);
            if (!TextUtils.isEmpty(b) && a2 >= 0) {
                return new a(b, a2);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JSONObject request(QQToken qQToken, Context context, String str, Bundle bundle, String str2) throws IOException, JSONException, NetworkUnavailableException, HttpStatusException {
        QQToken qQToken2;
        String str3;
        String str4;
        JSONObject jSONObject;
        int i2;
        int i3;
        long j2;
        long j3;
        JSONObject jSONObject2;
        SocketTimeoutException socketTimeoutException;
        JSONObject jSONObject3;
        int i4;
        SLog.i("openSDK_LOG.HttpUtils", "OpenApi request");
        if (m.b(context)) {
            if (str.toLowerCase().startsWith("http")) {
                qQToken2 = qQToken;
                str3 = str;
                str4 = str3;
            } else {
                str3 = j.a().a(context, "https://openmobile.qq.com/") + str;
                str4 = j.a().a(context, "https://openmobile.qq.com/") + str;
                qQToken2 = qQToken;
            }
            a(context, qQToken2, str);
            JSONObject jSONObject4 = null;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            int i5 = 0;
            int a2 = i.a(context, qQToken.getAppId()).a("Common_HttpRetryCount");
            SLog.v("OpenConfig_test", "config 1:Common_HttpRetryCount            config_value:" + a2 + "   appid:" + qQToken.getAppId() + "     url:" + str4);
            int i6 = a2 == 0 ? 3 : a2;
            SLog.v("OpenConfig_test", "config 1:Common_HttpRetryCount            result_value:" + i6 + "   appid:" + qQToken.getAppId() + "     url:" + str4);
            long j4 = elapsedRealtime;
            while (true) {
                int i7 = i5 + 1;
                try {
                    try {
                        try {
                            com.tencent.open.a.g a3 = a(str3, str2, bundle);
                            int d = a3.d();
                            SLog.i("openSDK_LOG.HttpUtils", "request statusCode " + d);
                            if (d == 200) {
                                try {
                                    jSONObject2 = m.d(a3.a());
                                    try {
                                        i4 = jSONObject2.getInt("ret");
                                    } catch (JSONException unused) {
                                        i4 = -4;
                                    }
                                    long c2 = a3.c();
                                    j2 = a3.b();
                                    i3 = i4;
                                    j3 = c2;
                                    break;
                                } catch (SocketTimeoutException e2) {
                                    socketTimeoutException = e2;
                                    jSONObject3 = jSONObject4;
                                    i2 = i7;
                                    socketTimeoutException.printStackTrace();
                                    i3 = -8;
                                    j3 = 0;
                                    j2 = 0;
                                    if (i2 >= i6) {
                                        j4 = SystemClock.elapsedRealtime();
                                        if (i2 >= i6) {
                                            jSONObject2 = jSONObject3;
                                            com.tencent.open.b.h.a().a(str4, j4, j3, j2, i3);
                                            return jSONObject2;
                                        }
                                        i5 = i2;
                                        jSONObject4 = jSONObject3;
                                    } else {
                                        com.tencent.open.b.h.a().a(str4, j4, 0L, 0L, -8);
                                        throw socketTimeoutException;
                                    }
                                }
                            } else {
                                jSONObject = jSONObject4;
                                i2 = i7;
                                try {
                                    com.tencent.open.b.h.a().a(str4, j4, 0L, 0L, d);
                                    throw new HttpStatusException(d);
                                    break;
                                } catch (SocketTimeoutException e3) {
                                    e = e3;
                                    socketTimeoutException = e;
                                    jSONObject3 = jSONObject;
                                    socketTimeoutException.printStackTrace();
                                    i3 = -8;
                                    j3 = 0;
                                    j2 = 0;
                                    if (i2 >= i6) {
                                    }
                                }
                            }
                        } catch (MalformedURLException e4) {
                            e4.printStackTrace();
                            com.tencent.open.b.h.a().a(str4, j4, 0L, 0L, -3);
                            throw e4;
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            com.tencent.open.b.h.a().a(str4, j4, 0L, 0L, getErrorCodeFromException(e5));
                            throw e5;
                        }
                    } catch (JSONException e6) {
                        e6.printStackTrace();
                        com.tencent.open.b.h.a().a(str4, j4, 0L, 0L, -4);
                        throw e6;
                    }
                } catch (SocketTimeoutException e7) {
                    e = e7;
                    jSONObject = jSONObject4;
                    i2 = i7;
                }
                i5 = i2;
                jSONObject4 = jSONObject3;
            }
            com.tencent.open.b.h.a().a(str4, j4, j3, j2, i3);
            return jSONObject2;
        }
        throw new NetworkUnavailableException(NetworkUnavailableException.ERROR_INFO);
    }

    public static void requestAsync(final QQToken qQToken, final Context context, final String str, final Bundle bundle, final String str2, final IRequestListener iRequestListener) {
        SLog.i("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        l.a(new Runnable() { // from class: com.tencent.open.utils.HttpUtils.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject request = HttpUtils.request(QQToken.this, context, str, bundle, str2);
                    IRequestListener iRequestListener2 = iRequestListener;
                    if (iRequestListener2 != null) {
                        iRequestListener2.onComplete(request);
                        SLog.i("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                } catch (HttpStatusException e2) {
                    IRequestListener iRequestListener3 = iRequestListener;
                    if (iRequestListener3 != null) {
                        iRequestListener3.onHttpStatusException(e2);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException" + e2.toString());
                    }
                } catch (NetworkUnavailableException e3) {
                    IRequestListener iRequestListener4 = iRequestListener;
                    if (iRequestListener4 != null) {
                        iRequestListener4.onNetworkUnavailableException(e3);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException" + e3.toString());
                    }
                } catch (MalformedURLException e4) {
                    IRequestListener iRequestListener5 = iRequestListener;
                    if (iRequestListener5 != null) {
                        iRequestListener5.onMalformedURLException(e4);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException" + e4.toString());
                    }
                } catch (SocketTimeoutException e5) {
                    IRequestListener iRequestListener6 = iRequestListener;
                    if (iRequestListener6 != null) {
                        iRequestListener6.onSocketTimeoutException(e5);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException" + e5.toString());
                    }
                } catch (IOException e6) {
                    IRequestListener iRequestListener7 = iRequestListener;
                    if (iRequestListener7 != null) {
                        iRequestListener7.onIOException(e6);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException" + e6.toString());
                    }
                } catch (JSONException e7) {
                    IRequestListener iRequestListener8 = iRequestListener;
                    if (iRequestListener8 != null) {
                        iRequestListener8.onJSONException(e7);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException" + e7.toString());
                    }
                } catch (Exception e8) {
                    IRequestListener iRequestListener9 = iRequestListener;
                    if (iRequestListener9 != null) {
                        iRequestListener9.onUnknowException(e8);
                        SLog.e("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException" + e8.toString());
                    }
                }
            }
        });
    }

    /* loaded from: classes9.dex */
    public static class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";
        public final int statusCode;

        public HttpStatusException(String str) {
            super(str);
            this.statusCode = -1;
        }

        public HttpStatusException(int i2) {
            super(ERROR_INFO + i2);
            this.statusCode = i2;
        }
    }

    public static String encodeUrl(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb.append(URLEncoder.encode(str));
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(URLEncoder.encode(map.get(str)));
        }
        return sb.toString();
    }

    private static String b(Context context) {
        if (Build.VERSION.SDK_INT < 11) {
            if (context != null) {
                String host = Proxy.getHost(context);
                return TextUtils.isEmpty(host) ? Proxy.getDefaultHost() : host;
            }
            return Proxy.getDefaultHost();
        }
        return System.getProperty("http.proxyHost");
    }

    private static com.tencent.open.a.g a(String str, String str2, Bundle bundle) throws IOException {
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        if (str2.equalsIgnoreCase("GET")) {
            Map<String, String> a2 = a(bundle2);
            Bundle b = com.tencent.open.log.d.b(bundle2);
            if (b != bundle2) {
                SLog.i("openSDK_LOG.HttpUtils", "-->openUrl encodedParam =" + b.toString() + " -- url = " + str);
            } else {
                SLog.i("openSDK_LOG.HttpUtils", "-->openUrl encodedParam =" + a2.toString() + " -- url = " + str);
            }
            return com.tencent.open.a.f.a().a(str, a2);
        } else if (str2.equalsIgnoreCase("POST")) {
            Map<String, String> a3 = a(bundle2);
            Map<String, byte[]> b2 = b(bundle2);
            if (b2 != null && b2.size() != 0) {
                SLog.w("openSDK_LOG.HttpUtils", "openUrl: has binary " + b2.size());
                return com.tencent.open.a.f.a().a(str, a3, b2);
            }
            return com.tencent.open.a.f.a().b(str, a3);
        } else {
            SLog.e("openSDK_LOG.HttpUtils", "openUrl: http method " + str2 + " is not supported.");
            throw new IOException("http method is not supported.");
        }
    }

    private static Map<String, String> a(Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null && bundle.size() != 0) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (!(obj instanceof String) && !(obj instanceof String[])) {
                    SLog.w("openSDK_LOG.HttpUtils", "parseBundleToMap: the type " + obj.getClass() + " is unsupported");
                } else if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        if (i2 != 0) {
                            sb.append(DYConstants.DY_REGEX_COMMA);
                        }
                        sb.append(strArr[i2]);
                    }
                    hashMap.put(str, sb.toString());
                } else {
                    hashMap.put(str, (String) obj);
                }
            }
        }
        return hashMap;
    }

    private static int a(Context context) {
        if (Build.VERSION.SDK_INT < 11) {
            if (context != null) {
                int port = Proxy.getPort(context);
                return port < 0 ? Proxy.getDefaultPort() : port;
            }
            return Proxy.getDefaultPort();
        }
        String property = System.getProperty("http.proxyPort");
        if (!TextUtils.isEmpty(property)) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }
}
