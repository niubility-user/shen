package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetFileResolver;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetHead;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetJceResolver;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetJsonResolver;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;
import com.tencent.mapsdk.internal.j2.a;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class g3<R extends j2.a> implements j2<R> {
    private volatile R a;
    private boolean b = false;

    /* renamed from: c */
    private boolean f16577c = true;
    private boolean d = true;

    /* renamed from: e */
    private Map<String, String> f16578e;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            NetMethod.values();
            int[] iArr = new int[4];
            a = iArr;
            try {
                NetMethod netMethod = NetMethod.GET;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                NetMethod netMethod2 = NetMethod.POST;
                iArr2[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class b {
        public String a;
        public String b;

        /* renamed from: c */
        public String f16579c;
        public String d;

        /* renamed from: e */
        public String[] f16580e;

        /* renamed from: f */
        public HashMap<String, String> f16581f;

        /* renamed from: g */
        public String f16582g;

        /* renamed from: h */
        public String f16583h;

        /* renamed from: i */
        public int f16584i;

        /* renamed from: j */
        public boolean f16585j;

        /* renamed from: k */
        public NetMethod f16586k;

        /* renamed from: l */
        public x3 f16587l;

        /* renamed from: m */
        public int[] f16588m;

        private b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        public String toString() {
            return "RequestEntity{service='" + this.a + "', request='" + this.b + "', method=" + this.f16586k + ", heads=" + this.f16581f + ", authority=" + this.d + ", queryKeys=" + Arrays.toString(this.f16580e) + ", constQuery='" + this.f16582g + "', useAgent='" + this.f16583h + "', resolver='" + this.f16587l + "', retry=" + this.f16584i + ", useExtraQuery=" + this.f16585j + "\nurl='" + this.f16579c + "'}";
        }
    }

    /* loaded from: classes9.dex */
    public class c implements InvocationHandler {
        private Class<? extends g3> a;

        public c(Class<? extends g3> cls) {
            g3.this = r1;
            this.a = cls;
        }

        private b a(Method method) {
            b bVar = new b(null);
            NetJceResolver netJceResolver = (NetJceResolver) method.getAnnotation(NetJceResolver.class);
            NetFileResolver netFileResolver = (NetFileResolver) method.getAnnotation(NetFileResolver.class);
            NetJsonResolver netJsonResolver = (NetJsonResolver) method.getAnnotation(NetJsonResolver.class);
            NetRequest netRequest = (NetRequest) method.getAnnotation(NetRequest.class);
            if (netJceResolver != null) {
                bVar.f16587l = new v3(netJceResolver.inJce(), netJceResolver.outJce());
                bVar.f16588m = netJceResolver.queryRange();
            } else if (netFileResolver != null) {
                bVar.f16587l = new u3(netFileResolver.outFile());
                bVar.f16588m = netFileResolver.queryRange();
            } else if (netJsonResolver != null) {
                bVar.f16587l = new w3(netJsonResolver.outModel());
                bVar.f16588m = netJsonResolver.queryRange();
            }
            if (netRequest != null) {
                bVar.a = this.a.getSimpleName();
                bVar.f16586k = netRequest.method();
                bVar.b = method.getName();
                bVar.d = netRequest.authority();
                bVar.f16583h = netRequest.userAgent();
                bVar.f16580e = netRequest.queryKeys();
                bVar.f16584i = netRequest.retry();
                bVar.f16585j = netRequest.useExtraQuery();
                NetHead head = netRequest.head();
                String[] keys = head.keys();
                String[] values = head.values();
                if (keys.length > 0 && keys.length == values.length) {
                    bVar.f16581f = new HashMap<>();
                    for (int i2 = 0; i2 < keys.length; i2++) {
                        bVar.f16581f.put(keys[i2], values[i2]);
                    }
                }
                StringBuilder sb = new StringBuilder();
                String a = g3.this.a(bVar.d);
                if (!TextUtils.isEmpty(a)) {
                    sb.append(a);
                }
                String path = netRequest.path();
                if (path.length() != 0) {
                    sb.append("/");
                    sb.append(path);
                }
                bVar.f16582g = netRequest.constQuery();
                bVar.f16579c = sb.toString();
            }
            return bVar;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            b a = a(method);
            if (!g3.this.b()) {
                ma.g(la.f16820g, "The Service[" + this.a.getSimpleName() + "] is block!!  Please check the ServiceProtocol for corrected, or contact the Tencent MapSdk Office to enable it. ");
                if (a.f16586k == NetMethod.URL) {
                    return "";
                }
                return null;
            } else if (a.f16586k != NetMethod.URL) {
                x3 x3Var = a.f16587l;
                if (x3Var != null) {
                    return a.f16587l.a(g3.this.b(a, x3Var.a(a.f16588m, objArr)));
                }
                return g3.this.b(a, objArr);
            } else {
                String str = a.f16579c;
                String a2 = g3.this.a(a, objArr);
                if (a2.length() != 0) {
                    str = str + "?" + a2;
                }
                a.f16579c = str;
                ma.c(la.f16820g, a.toString());
                return str;
            }
        }
    }

    public String a(@NonNull b bVar, Object... objArr) {
        Map<String, String> map;
        String[] strArr = bVar.f16580e;
        StringBuilder sb = new StringBuilder();
        if (strArr != null && objArr != null && strArr.length <= objArr.length) {
            for (String str : strArr) {
                sb.append(str);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append("%s");
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
        }
        String str2 = bVar.f16582g;
        if (!TextUtils.isEmpty(str2)) {
            for (String str3 : str2.split(ContainerUtils.FIELD_DELIMITER)) {
                String[] split = str3.split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (split.length == 2) {
                    sb.append(split[0]);
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(split[1]);
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
        }
        if (bVar.f16585j && (map = this.f16578e) != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : this.f16578e.entrySet()) {
                sb.append(entry.getKey());
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(entry.getValue());
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
        }
        int lastIndexOf = sb.lastIndexOf(ContainerUtils.FIELD_DELIMITER);
        if (lastIndexOf >= 0 && lastIndexOf == sb.length() - 1) {
            sb.deleteCharAt(lastIndexOf);
        }
        return String.format(sb.toString(), objArr);
    }

    public String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (e7.b(str)) {
            str = h();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String i2 = i();
        if (!TextUtils.isEmpty(i2)) {
            sb.append(i2);
            sb.append("://");
        }
        sb.append(str);
        return sb.toString();
    }

    public NetResponse b(b bVar, Object... objArr) {
        if (bVar != null) {
            String str = bVar.f16579c;
            try {
                String a2 = a(bVar, objArr);
                if (!TextUtils.isEmpty(a2)) {
                    str = str + "?" + a2;
                }
                bVar.f16579c = str;
                ma.c(la.f16820g, bVar.toString());
                int ordinal = bVar.f16586k.ordinal();
                if (ordinal == 0) {
                    byte[] bArr = new byte[0];
                    if (objArr.length > 0) {
                        int length = objArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            }
                            Object obj = objArr[i2];
                            if (obj instanceof byte[]) {
                                bArr = (byte[]) obj;
                                break;
                            }
                            i2++;
                        }
                    }
                    return NetManager.getInstance().builder().userAgent(bVar.f16583h).forceHttps(false).url(str).retryNum(bVar.f16584i).header(bVar.f16581f).postData(bArr).doPost();
                } else if (ordinal == 1) {
                    return NetManager.getInstance().builder().forceHttps(false).userAgent(bVar.f16583h).url(str).retryNum(bVar.f16584i).header(bVar.f16581f).doGet();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private R k() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments.length > 0 && (actualTypeArguments[0] instanceof Class)) {
                Class<?> cls = (Class) actualTypeArguments[0];
                ClassLoader classLoader = cls.getClassLoader();
                Class<?>[] clsArr = new Class[1];
                if (cls.isInterface()) {
                    clsArr[0] = cls;
                } else {
                    clsArr = cls.getInterfaces();
                }
                return (R) Proxy.newProxyInstance(classLoader, clsArr, new c(getClass()));
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public String a() {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public void a(Map<String, String> map) {
        Map<String, String> map2 = this.f16578e;
        if (map2 != null) {
            map2.putAll(map);
        } else {
            this.f16578e = map;
        }
    }

    @Override // com.tencent.mapsdk.internal.j2
    public boolean b() {
        return this.d;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public boolean c() {
        return this.b;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public final R d() {
        if (this.a != null) {
            return this.a;
        }
        this.a = k();
        return this.a;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public boolean e() {
        return this.f16577c;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public String f() {
        return null;
    }

    public final String h() {
        return c() ? f() : g();
    }

    public final String i() {
        return e() ? "https" : "http";
    }

    public final String j() {
        StringBuilder sb = new StringBuilder();
        String h2 = h();
        if (TextUtils.isEmpty(h2)) {
            return null;
        }
        String i2 = i();
        if (!TextUtils.isEmpty(i2)) {
            sb.append(i2);
            sb.append("://");
        }
        sb.append(h2);
        return sb.toString();
    }

    @Override // com.tencent.mapsdk.internal.i2.a
    public void setAllow(boolean z) {
        this.d = z;
    }

    @Override // com.tencent.mapsdk.internal.i2.a
    public void setUseHttps(boolean z) {
        this.f16577c = z;
    }

    @Override // com.tencent.mapsdk.internal.i2.a
    public void setUseTest(boolean z) {
        this.b = z;
    }
}
