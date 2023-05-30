package com.tencent.smtt.sdk;

import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.UrlRequest;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes9.dex */
public class UrlRequestBuilderImpl extends UrlRequest.Builder {
    private static final String a = "UrlRequestBuilderImpl";
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final UrlRequest.Callback f17785c;
    private final Executor d;

    /* renamed from: e  reason: collision with root package name */
    private String f17786e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17788g;

    /* renamed from: i  reason: collision with root package name */
    private String f17790i;

    /* renamed from: j  reason: collision with root package name */
    private byte[] f17791j;

    /* renamed from: k  reason: collision with root package name */
    private String f17792k;

    /* renamed from: l  reason: collision with root package name */
    private String f17793l;

    /* renamed from: f  reason: collision with root package name */
    private final ArrayList<Pair<String, String>> f17787f = new ArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private int f17789h = 3;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        }
        if (callback == null) {
            throw new NullPointerException("Callback is required.");
        }
        if (executor == null) {
            throw new NullPointerException("Executor is required.");
        }
        this.b = str;
        this.f17785c = callback;
        this.d = executor;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl addHeader(String str, String str2) {
        if (str != null) {
            if (str2 != null) {
                if (HttpHeaders.ACCEPT_ENCODING.equalsIgnoreCase(str)) {
                    return this;
                }
                this.f17787f.add(Pair.create(str, str2));
                return this;
            }
            throw new NullPointerException("Invalid header value.");
        }
        throw new NullPointerException("Invalid header name.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest build() throws NullPointerException {
        int i2;
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            return null;
        }
        DexLoader b = a2.c().b();
        Class<?> cls = Integer.TYPE;
        Class<?> cls2 = Boolean.TYPE;
        UrlRequest urlRequest = (UrlRequest) b.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", new Class[]{String.class, cls, UrlRequest.Callback.class, Executor.class, cls2, String.class, ArrayList.class, String.class, byte[].class, String.class, String.class}, this.b, Integer.valueOf(this.f17789h), this.f17785c, this.d, Boolean.valueOf(this.f17788g), this.f17786e, this.f17787f, this.f17790i, this.f17791j, this.f17792k, this.f17793l);
        if (urlRequest == null) {
            i2 = 7;
            urlRequest = (UrlRequest) b.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", new Class[]{String.class, cls, UrlRequest.Callback.class, Executor.class, cls2, String.class, ArrayList.class, String.class}, this.b, Integer.valueOf(this.f17789h), this.f17785c, this.d, Boolean.valueOf(this.f17788g), this.f17786e, this.f17787f, this.f17790i);
        } else {
            i2 = 7;
        }
        if (urlRequest == null) {
            Class<?>[] clsArr = new Class[i2];
            clsArr[0] = String.class;
            clsArr[1] = cls;
            clsArr[2] = UrlRequest.Callback.class;
            clsArr[3] = Executor.class;
            clsArr[4] = cls2;
            clsArr[5] = String.class;
            clsArr[6] = ArrayList.class;
            Object[] objArr = new Object[i2];
            objArr[0] = this.b;
            objArr[1] = Integer.valueOf(this.f17789h);
            objArr[2] = this.f17785c;
            objArr[3] = this.d;
            objArr[4] = Boolean.valueOf(this.f17788g);
            objArr[5] = this.f17786e;
            objArr[6] = this.f17787f;
            urlRequest = (UrlRequest) b.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", clsArr, objArr);
        }
        if (urlRequest == null) {
            urlRequest = (UrlRequest) b.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "UrlRequest_getX5UrlRequestProvider", new Class[]{String.class, cls, UrlRequest.Callback.class, Executor.class, cls2, String.class, ArrayList.class, String.class, byte[].class, String.class, String.class}, this.b, Integer.valueOf(this.f17789h), this.f17785c, this.d, Boolean.valueOf(this.f17788g), this.f17786e, this.f17787f, this.f17790i, this.f17791j, this.f17792k, this.f17793l);
        }
        if (urlRequest != null) {
            return urlRequest;
        }
        throw new NullPointerException("UrlRequest build fail");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl disableCache() {
        this.f17788g = true;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl setDns(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("host and address are required.");
        }
        this.f17792k = str;
        this.f17793l = str2;
        try {
            u a2 = u.a();
            if (a2 != null && a2.b()) {
                a2.c().b().invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "setDns", new Class[]{String.class, String.class}, this.f17792k, this.f17793l);
            }
        } catch (Exception unused) {
        }
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setHttpMethod(String str) {
        if (str != null) {
            this.f17786e = str;
            return this;
        }
        throw new NullPointerException("Method is required.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl setPriority(int i2) {
        this.f17789h = i2;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setRequestBody(String str) {
        if (str != null) {
            this.f17790i = str;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setRequestBodyBytes(byte[] bArr) {
        if (bArr != null) {
            this.f17791j = bArr;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }
}
