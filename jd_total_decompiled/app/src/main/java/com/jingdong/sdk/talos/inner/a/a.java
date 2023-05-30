package com.jingdong.sdk.talos.inner.a;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* loaded from: classes10.dex */
public abstract class a {

    /* renamed from: c  reason: collision with root package name */
    protected URL f15481c;
    protected String d;

    /* renamed from: i  reason: collision with root package name */
    String f15486i;

    /* renamed from: j  reason: collision with root package name */
    protected byte[] f15487j;

    /* renamed from: k  reason: collision with root package name */
    public e f15488k;
    protected String a = "POST";
    protected String b = "utf-8";

    /* renamed from: e  reason: collision with root package name */
    protected int f15482e = 15000;

    /* renamed from: f  reason: collision with root package name */
    protected int f15483f = 15000;

    /* renamed from: g  reason: collision with root package name */
    protected HashMap<String, String> f15484g = new HashMap<>();

    /* renamed from: h  reason: collision with root package name */
    protected HashMap<String, String> f15485h = new HashMap<>();

    private void e(HttpURLConnection httpURLConnection, ByteArrayOutputStream byteArrayOutputStream) {
        String str;
        httpURLConnection.getLastModified();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields != null) {
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String str2 = "";
                int i2 = 0;
                for (String str3 : entry.getValue()) {
                    if (i2 > 0) {
                        str2 = str2 + "<--->";
                    }
                    str2 = str2 + str3;
                    i2++;
                }
                this.f15485h.put(String.valueOf(entry.getKey()).toLowerCase(Locale.getDefault()), str2);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray == null || byteArray.length == 0) {
            this.f15487j = null;
            return;
        }
        HashMap<String, String> hashMap = this.f15485h;
        if (hashMap != null && (str = hashMap.get("content-encoding")) != null && str.contains("gzip")) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(1024);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream2.write(bArr, 0, read);
            }
            gZIPInputStream.close();
            byteArray = byteArrayOutputStream2.toByteArray();
        }
        this.f15487j = byteArray;
    }

    public final String a() {
        return this.d;
    }

    public abstract void b(OutputStream outputStream);

    public final void c(String str) {
        this.d = str;
    }

    public final void d(String str, String str2) {
        this.f15484g.put(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:251:0x024c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0253 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x025a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void f() {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        HttpURLConnection httpURLConnection;
        SocketTimeoutException e2;
        SocketException e3;
        ConnectException e4;
        InputStream inputStream;
        HttpURLConnection httpURLConnection2;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        inputStream2 = null;
        r0 = null;
        r0 = null;
        r0 = null;
        InputStream inputStream4 = null;
        HttpURLConnection httpURLConnection3 = null;
        InputStream inputStream5 = null;
        InputStream inputStream6 = null;
        InputStream inputStream7 = null;
        try {
            try {
                if (this.f15481c == null) {
                    if (TextUtils.isEmpty(this.d)) {
                        throw new IOException("the request url is empty");
                    }
                    this.f15481c = new URL(this.d);
                }
                URL url = this.f15481c;
                if (url == null) {
                    throw new IOException("the request url is empty");
                }
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection == null) {
                    throw new IOException("get HttpURLConnection is empty");
                }
                try {
                    try {
                        httpURLConnection.setConnectTimeout(this.f15482e);
                        httpURLConnection.setReadTimeout(this.f15483f);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setRequestMethod(this.a);
                        httpURLConnection.setInstanceFollowRedirects(true);
                        HashMap<String, String> hashMap = this.f15484g;
                        if (hashMap != null) {
                            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                            }
                        }
                        try {
                            httpURLConnection.connect();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        } catch (AssertionError e6) {
                            e6.printStackTrace();
                        }
                        b(httpURLConnection.getOutputStream());
                        if (httpURLConnection.getResponseCode() == 200) {
                            inputStream = httpURLConnection.getInputStream();
                            try {
                                byte[] bArr = new byte[1024];
                                byteArrayOutputStream = new ByteArrayOutputStream(1024);
                                while (true) {
                                    try {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(bArr, 0, read);
                                    } catch (FileNotFoundException e7) {
                                        e = e7;
                                        httpURLConnection3 = httpURLConnection;
                                        try {
                                            e eVar = this.f15488k;
                                            if (eVar != null) {
                                                if (eVar instanceof d) {
                                                    ((d) eVar).c(e.getMessage());
                                                } else {
                                                    eVar.b(e.getMessage());
                                                }
                                            }
                                            e.printStackTrace();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (Exception unused) {
                                                }
                                            }
                                            if (byteArrayOutputStream != null) {
                                                try {
                                                    byteArrayOutputStream.close();
                                                } catch (Exception unused2) {
                                                }
                                            }
                                            if (httpURLConnection3 != null) {
                                                try {
                                                    httpURLConnection3.disconnect();
                                                    return;
                                                } catch (AssertionError e8) {
                                                    e8.printStackTrace();
                                                    return;
                                                } catch (Exception unused3) {
                                                    return;
                                                }
                                            }
                                            return;
                                        } catch (Throwable th2) {
                                            httpURLConnection2 = httpURLConnection3;
                                            inputStream4 = inputStream;
                                            th = th2;
                                            if (inputStream4 != null) {
                                                try {
                                                    inputStream4.close();
                                                } catch (Exception unused4) {
                                                }
                                            }
                                            if (byteArrayOutputStream != null) {
                                                try {
                                                    byteArrayOutputStream.close();
                                                } catch (Exception unused5) {
                                                }
                                            }
                                            if (httpURLConnection2 != null) {
                                                try {
                                                    httpURLConnection2.disconnect();
                                                } catch (AssertionError e9) {
                                                    e9.printStackTrace();
                                                } catch (Exception unused6) {
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (ConnectException e10) {
                                        e4 = e10;
                                        inputStream5 = inputStream;
                                        e eVar2 = this.f15488k;
                                        if (eVar2 != null) {
                                            eVar2.b(e4.getMessage());
                                        }
                                        e4.printStackTrace();
                                        if (inputStream5 != null) {
                                            try {
                                                inputStream5.close();
                                            } catch (Exception unused7) {
                                            }
                                        }
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused8) {
                                            }
                                        }
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                                return;
                                            } catch (AssertionError e11) {
                                                e11.printStackTrace();
                                                return;
                                            } catch (Exception unused9) {
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (SocketException e12) {
                                        e3 = e12;
                                        inputStream6 = inputStream;
                                        e eVar3 = this.f15488k;
                                        if (eVar3 != null) {
                                            eVar3.b(e3.getMessage());
                                        }
                                        e3.printStackTrace();
                                        if (inputStream6 != null) {
                                            try {
                                                inputStream6.close();
                                            } catch (Exception unused10) {
                                            }
                                        }
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused11) {
                                            }
                                        }
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                                return;
                                            } catch (AssertionError e13) {
                                                e13.printStackTrace();
                                                return;
                                            } catch (Exception unused12) {
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (SocketTimeoutException e14) {
                                        e2 = e14;
                                        inputStream7 = inputStream;
                                        e eVar4 = this.f15488k;
                                        if (eVar4 != null) {
                                            eVar4.b(e2.getMessage());
                                        }
                                        e2.printStackTrace();
                                        if (inputStream7 != null) {
                                            try {
                                                inputStream7.close();
                                            } catch (Exception unused13) {
                                            }
                                        }
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused14) {
                                            }
                                        }
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                                return;
                                            } catch (AssertionError e15) {
                                                e15.printStackTrace();
                                                return;
                                            } catch (Exception unused15) {
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        inputStream2 = inputStream;
                                        e eVar5 = this.f15488k;
                                        if (eVar5 != null) {
                                            eVar5.b(th.getMessage());
                                        }
                                        th.printStackTrace();
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (Exception unused16) {
                                            }
                                        }
                                        if (byteArrayOutputStream != null) {
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused17) {
                                            }
                                        }
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                                return;
                                            } catch (AssertionError e16) {
                                                e16.printStackTrace();
                                                return;
                                            } catch (Exception unused18) {
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                }
                                e(httpURLConnection, byteArrayOutputStream);
                                e eVar6 = this.f15488k;
                                if (eVar6 != null) {
                                    eVar6.a(new String(this.f15487j, this.b));
                                }
                                inputStream3 = inputStream;
                            } catch (FileNotFoundException e17) {
                                byteArrayOutputStream = null;
                                httpURLConnection3 = httpURLConnection;
                                e = e17;
                            } catch (ConnectException e18) {
                                byteArrayOutputStream = null;
                                inputStream5 = inputStream;
                                e4 = e18;
                            } catch (SocketException e19) {
                                byteArrayOutputStream = null;
                                inputStream6 = inputStream;
                                e3 = e19;
                            } catch (SocketTimeoutException e20) {
                                byteArrayOutputStream = null;
                                inputStream7 = inputStream;
                                e2 = e20;
                            } catch (Throwable th4) {
                                byteArrayOutputStream = null;
                                inputStream2 = inputStream;
                                th = th4;
                            }
                        } else {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                            }
                            bufferedReader.close();
                            e eVar7 = this.f15488k;
                            if (eVar7 != null) {
                                eVar7.b(sb.toString());
                            }
                            byteArrayOutputStream = null;
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (Exception unused19) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused20) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (AssertionError e21) {
                                e21.printStackTrace();
                            } catch (Exception unused21) {
                            }
                        }
                    } catch (FileNotFoundException e22) {
                        byteArrayOutputStream = null;
                        httpURLConnection3 = httpURLConnection;
                        e = e22;
                        inputStream = null;
                    } catch (ConnectException e23) {
                        e4 = e23;
                        byteArrayOutputStream = null;
                    } catch (SocketException e24) {
                        e3 = e24;
                        byteArrayOutputStream = null;
                    } catch (SocketTimeoutException e25) {
                        e2 = e25;
                        byteArrayOutputStream = null;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    byteArrayOutputStream = null;
                }
            } catch (FileNotFoundException e26) {
                e = e26;
                inputStream = null;
                byteArrayOutputStream = null;
            } catch (ConnectException e27) {
                byteArrayOutputStream = null;
                e4 = e27;
                httpURLConnection = null;
            } catch (SocketException e28) {
                byteArrayOutputStream = null;
                e3 = e28;
                httpURLConnection = null;
            } catch (SocketTimeoutException e29) {
                byteArrayOutputStream = null;
                e2 = e29;
                httpURLConnection = null;
            } catch (Throwable th6) {
                byteArrayOutputStream = null;
                th = th6;
                httpURLConnection = null;
            }
        } catch (Throwable th7) {
            th = th7;
            if (inputStream4 != null) {
            }
            if (byteArrayOutputStream != null) {
            }
            if (httpURLConnection2 != null) {
            }
            throw th;
        }
    }
}
