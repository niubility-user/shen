package com.tencent.mm.opensdk.channel.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.b;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: com.tencent.mm.opensdk.channel.a.a$a */
    /* loaded from: classes9.dex */
    public static class C0805a {
        public String a;
        public String b;

        /* renamed from: c */
        public String f17599c;
        public long d;

        /* renamed from: e */
        public Bundle f17600e;
    }

    public static int a(Bundle bundle, String str, int i2) {
        if (bundle == null) {
            return i2;
        }
        try {
            return bundle.getInt(str, i2);
        } catch (Exception e2) {
            Log.e("MicroMsg.IntentUtil", "getIntExtra exception:" + e2.getMessage());
            return i2;
        }
    }

    public static Object a(int i2, String str) {
        try {
            switch (i2) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Long.valueOf(str);
                case 3:
                    return str;
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return Float.valueOf(str);
                case 6:
                    return Double.valueOf(str);
                default:
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    return null;
            }
        } catch (Exception e2) {
            Log.e("MicroMsg.SDK.PluginProvider.Resolver", "resolveObj exception:" + e2.getMessage());
            return null;
        }
    }

    public static String a(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e2) {
            Log.e("MicroMsg.IntentUtil", "getStringExtra exception:" + e2.getMessage());
            return null;
        }
    }

    public static boolean a(Context context, C0805a c0805a) {
        String str;
        if (context == null || c0805a == null) {
            str = "send fail, invalid argument";
        } else if (!b.b(c0805a.b)) {
            String str2 = null;
            if (!b.b(c0805a.a)) {
                str2 = c0805a.a + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c0805a.b);
            Bundle bundle = c0805a.f17600e;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, Build.SDK_INT);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, c0805a.f17599c);
            intent.putExtra(ConstantsAPI.APP_SUPORT_CONTENT_TYPE, c0805a.d);
            intent.putExtra(ConstantsAPI.CHECK_SUM, a(c0805a.f17599c, (int) Build.SDK_INT, packageName));
            context.sendBroadcast(intent, str2);
            Log.d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str2);
            return true;
        } else {
            str = "send fail, action is null";
        }
        Log.e("MicroMsg.SDK.MMessage", str);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x015b: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:486:0x015b */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:512:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:516:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:518:0x0167 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:520:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:522:0x016e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:524:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:526:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0148 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:538:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0119 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v32 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] a(String str, int i2) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        HttpURLConnection httpURLConnection;
        ByteArrayOutputStream byteArrayOutputStream3;
        HttpURLConnection httpURLConnection2;
        InputStream inputStream;
        HttpURLConnection httpURLConnection3;
        InputStream inputStream2;
        Exception e2;
        HttpURLConnection httpURLConnection4;
        InputStream inputStream3;
        IOException e3;
        HttpURLConnection httpURLConnection5;
        InputStream inputStream4;
        MalformedURLException e4;
        HttpURLConnection httpURLConnection6;
        InputStream inputStream5;
        ByteArrayOutputStream byteArrayOutputStream4;
        ByteArrayOutputStream byteArrayOutputStream5;
        ByteArrayOutputStream byteArrayOutputStream6;
        HttpURLConnection httpURLConnection7;
        HttpURLConnection httpURLConnection8;
        HttpURLConnection httpURLConnection9;
        HttpURLConnection httpURLConnection10;
        InputStream inputStream6 = null;
        if (str != null) {
            int length = str.length();
            try {
                if (length != 0) {
                    try {
                        httpURLConnection10 = (HttpURLConnection) new URL(str).openConnection();
                    } catch (MalformedURLException e5) {
                        e4 = e5;
                        httpURLConnection6 = null;
                        inputStream5 = null;
                    } catch (IOException e6) {
                        e3 = e6;
                        httpURLConnection5 = null;
                        inputStream4 = null;
                    } catch (Exception e7) {
                        e2 = e7;
                        httpURLConnection4 = null;
                        inputStream3 = null;
                    } catch (Throwable th) {
                        th = th;
                        httpURLConnection3 = null;
                        inputStream2 = null;
                    }
                    try {
                        if (httpURLConnection10 == null) {
                            Log.e("MicroMsg.SDK.NetUtil", "open connection failed.");
                            if (httpURLConnection10 != null) {
                                try {
                                    httpURLConnection10.disconnect();
                                } catch (Throwable unused) {
                                }
                            }
                            return null;
                        }
                        try {
                            httpURLConnection10.setRequestMethod("GET");
                            httpURLConnection10.setConnectTimeout(i2);
                            httpURLConnection10.setReadTimeout(i2);
                            if (httpURLConnection10.getResponseCode() >= 300) {
                                Log.e("MicroMsg.SDK.NetUtil", "httpURLConnectionGet 300");
                                try {
                                    httpURLConnection10.disconnect();
                                } catch (Throwable unused2) {
                                }
                                return null;
                            }
                            InputStream inputStream7 = httpURLConnection10.getInputStream();
                            try {
                                ByteArrayOutputStream byteArrayOutputStream7 = new ByteArrayOutputStream();
                                try {
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = inputStream7.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        byteArrayOutputStream7.write(bArr, 0, read);
                                    }
                                    byte[] byteArray = byteArrayOutputStream7.toByteArray();
                                    Log.d("MicroMsg.SDK.NetUtil", "httpGet end");
                                    try {
                                        httpURLConnection10.disconnect();
                                    } catch (Throwable unused3) {
                                    }
                                    try {
                                        inputStream7.close();
                                    } catch (Throwable unused4) {
                                    }
                                    try {
                                        byteArrayOutputStream7.close();
                                    } catch (Throwable unused5) {
                                    }
                                    return byteArray;
                                } catch (MalformedURLException e8) {
                                    inputStream5 = inputStream7;
                                    e4 = e8;
                                    byteArrayOutputStream6 = byteArrayOutputStream7;
                                    httpURLConnection9 = httpURLConnection10;
                                    Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e4.getMessage());
                                    if (httpURLConnection9 != null) {
                                    }
                                    if (inputStream5 != null) {
                                    }
                                    if (byteArrayOutputStream6 != null) {
                                    }
                                    return null;
                                } catch (IOException e9) {
                                    inputStream4 = inputStream7;
                                    e3 = e9;
                                    byteArrayOutputStream5 = byteArrayOutputStream7;
                                    httpURLConnection8 = httpURLConnection10;
                                    Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e3.getMessage());
                                    if (httpURLConnection8 != null) {
                                    }
                                    if (inputStream4 != null) {
                                    }
                                    if (byteArrayOutputStream5 != null) {
                                    }
                                    return null;
                                } catch (Exception e10) {
                                    inputStream3 = inputStream7;
                                    e2 = e10;
                                    byteArrayOutputStream4 = byteArrayOutputStream7;
                                    httpURLConnection7 = httpURLConnection10;
                                    Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e2.getMessage());
                                    if (httpURLConnection7 != null) {
                                    }
                                    if (inputStream3 != null) {
                                    }
                                    if (byteArrayOutputStream4 != null) {
                                    }
                                    return null;
                                } catch (Throwable th2) {
                                    byteArrayOutputStream2 = byteArrayOutputStream7;
                                    inputStream = inputStream7;
                                    th = th2;
                                    httpURLConnection2 = httpURLConnection10;
                                    byteArrayOutputStream3 = byteArrayOutputStream2;
                                    inputStream6 = inputStream;
                                    httpURLConnection = httpURLConnection2;
                                    if (httpURLConnection != 0) {
                                    }
                                    if (inputStream6 != null) {
                                    }
                                    if (byteArrayOutputStream3 != null) {
                                    }
                                    throw th;
                                }
                            } catch (MalformedURLException e11) {
                                inputStream5 = inputStream7;
                                e4 = e11;
                                httpURLConnection6 = httpURLConnection10;
                                byteArrayOutputStream6 = null;
                                httpURLConnection9 = httpURLConnection6;
                                Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e4.getMessage());
                                if (httpURLConnection9 != null) {
                                    try {
                                        httpURLConnection9.disconnect();
                                    } catch (Throwable unused6) {
                                    }
                                }
                                if (inputStream5 != null) {
                                    try {
                                        inputStream5.close();
                                    } catch (Throwable unused7) {
                                    }
                                }
                                if (byteArrayOutputStream6 != null) {
                                    try {
                                        byteArrayOutputStream6.close();
                                    } catch (Throwable unused8) {
                                    }
                                }
                                return null;
                            } catch (IOException e12) {
                                inputStream4 = inputStream7;
                                e3 = e12;
                                httpURLConnection5 = httpURLConnection10;
                                byteArrayOutputStream5 = null;
                                httpURLConnection8 = httpURLConnection5;
                                Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e3.getMessage());
                                if (httpURLConnection8 != null) {
                                    try {
                                        httpURLConnection8.disconnect();
                                    } catch (Throwable unused9) {
                                    }
                                }
                                if (inputStream4 != null) {
                                    try {
                                        inputStream4.close();
                                    } catch (Throwable unused10) {
                                    }
                                }
                                if (byteArrayOutputStream5 != null) {
                                    try {
                                        byteArrayOutputStream5.close();
                                    } catch (Throwable unused11) {
                                    }
                                }
                                return null;
                            } catch (Exception e13) {
                                inputStream3 = inputStream7;
                                e2 = e13;
                                httpURLConnection4 = httpURLConnection10;
                                byteArrayOutputStream4 = null;
                                httpURLConnection7 = httpURLConnection4;
                                Log.e("MicroMsg.SDK.NetUtil", "httpGet ex:" + e2.getMessage());
                                if (httpURLConnection7 != null) {
                                    try {
                                        httpURLConnection7.disconnect();
                                    } catch (Throwable unused12) {
                                    }
                                }
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (Throwable unused13) {
                                    }
                                }
                                if (byteArrayOutputStream4 != null) {
                                    try {
                                        byteArrayOutputStream4.close();
                                    } catch (Throwable unused14) {
                                    }
                                }
                                return null;
                            } catch (Throwable th3) {
                                inputStream2 = inputStream7;
                                th = th3;
                                httpURLConnection3 = httpURLConnection10;
                                inputStream6 = inputStream2;
                                byteArrayOutputStream3 = null;
                                httpURLConnection = httpURLConnection3;
                                if (httpURLConnection != 0) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Throwable unused15) {
                                    }
                                }
                                if (inputStream6 != null) {
                                    try {
                                        inputStream6.close();
                                    } catch (Throwable unused16) {
                                    }
                                }
                                if (byteArrayOutputStream3 != null) {
                                    try {
                                        byteArrayOutputStream3.close();
                                    } catch (Throwable unused17) {
                                    }
                                }
                                throw th;
                            }
                        } catch (MalformedURLException e14) {
                            e4 = e14;
                            inputStream5 = null;
                            httpURLConnection6 = httpURLConnection10;
                        } catch (IOException e15) {
                            e3 = e15;
                            inputStream4 = null;
                            httpURLConnection5 = httpURLConnection10;
                        } catch (Exception e16) {
                            e2 = e16;
                            inputStream3 = null;
                            httpURLConnection4 = httpURLConnection10;
                        } catch (Throwable th4) {
                            th = th4;
                            inputStream2 = null;
                            httpURLConnection3 = httpURLConnection10;
                        }
                    } catch (MalformedURLException e17) {
                        e4 = e17;
                        inputStream5 = null;
                        byteArrayOutputStream6 = null;
                        httpURLConnection9 = httpURLConnection10;
                    } catch (IOException e18) {
                        e3 = e18;
                        inputStream4 = null;
                        byteArrayOutputStream5 = null;
                        httpURLConnection8 = httpURLConnection10;
                    } catch (Exception e19) {
                        e2 = e19;
                        inputStream3 = null;
                        byteArrayOutputStream4 = null;
                        httpURLConnection7 = httpURLConnection10;
                    } catch (Throwable th5) {
                        th = th5;
                        byteArrayOutputStream3 = null;
                        httpURLConnection = httpURLConnection10;
                        if (httpURLConnection != 0) {
                        }
                        if (inputStream6 != null) {
                        }
                        if (byteArrayOutputStream3 != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream2 = byteArrayOutputStream;
                inputStream = length;
                httpURLConnection2 = str;
            }
        }
        Log.e("MicroMsg.SDK.NetUtil", "httpGet, url is null");
        return null;
    }

    public static byte[] a(String str, int i2, String str2) {
        String str3;
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i2);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        byte[] bytes = stringBuffer.toString().substring(1, 9).getBytes();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i3 = 0;
            for (byte b : digest) {
                int i4 = i3 + 1;
                cArr2[i3] = cArr[(b >>> 4) & 15];
                i3 = i4 + 1;
                cArr2[i4] = cArr[b & 15];
            }
            str3 = new String(cArr2);
        } catch (Exception unused) {
            str3 = null;
        }
        return str3.getBytes();
    }
}
