package g.e.a.h.c;

import android.content.Context;
import g.e.a.g;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class d implements g.e.a.e {
    private final String a;
    private final Context b;

    /* renamed from: c  reason: collision with root package name */
    private final String f19454c;
    private final g.e.a.b d;

    /* renamed from: e  reason: collision with root package name */
    private final f f19455e;

    /* renamed from: f  reason: collision with root package name */
    private final g f19456f;

    /* renamed from: g  reason: collision with root package name */
    private final Map<String, String> f19457g;

    /* renamed from: h  reason: collision with root package name */
    private final List<com.huawei.agconnect.core.a> f19458h;

    /* renamed from: i  reason: collision with root package name */
    private final Map<String, String> f19459i = new HashMap();

    public d(Context context, String str, g.e.a.b bVar, InputStream inputStream, Map<String, String> map, List<com.huawei.agconnect.core.a> list, String str2) {
        context = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.b = context;
        str = str == null ? context.getPackageName() : str;
        this.f19454c = str;
        if (inputStream != null) {
            this.f19455e = new j(inputStream, str);
            b.a(inputStream);
        } else {
            this.f19455e = new m(context, str);
        }
        this.f19456f = new g(this.f19455e);
        g.e.a.b bVar2 = g.e.a.b.b;
        if (bVar != bVar2 && "1.0".equals(this.f19455e.a("/configuration_version", null))) {
            throw new RuntimeException("The file version does not match,please download the latest agconnect-services.json from the AGC website.");
        }
        this.d = (bVar == null || bVar == bVar2) ? b.f(this.f19455e.a("/region", null), this.f19455e.a("/agcgw/url", null)) : bVar;
        this.f19457g = b.d(map);
        this.f19458h = list;
        this.a = str2 == null ? d() : str2;
    }

    private String b(String str) {
        Map<String, g.a> a = g.e.a.g.a();
        if (a.containsKey(str)) {
            if (this.f19459i.containsKey(str)) {
                return this.f19459i.get(str);
            }
            g.a aVar = a.get(str);
            if (aVar == null) {
                return null;
            }
            String a2 = aVar.a(this);
            this.f19459i.put(str, a2);
            return a2;
        }
        return null;
    }

    private String d() {
        return String.valueOf(("{packageName='" + this.f19454c + "', routePolicy=" + this.d + ", reader=" + this.f19455e.toString().hashCode() + ", customConfigMap=" + new JSONObject(this.f19457g).toString().hashCode() + '}').hashCode());
    }

    @Override // g.e.a.e
    public g.e.a.b a() {
        g.e.a.b bVar = this.d;
        return bVar == null ? g.e.a.b.b : bVar;
    }

    public List<com.huawei.agconnect.core.a> c() {
        return this.f19458h;
    }

    public String e(String str, String str2) {
        if (str == null) {
            return str2;
        }
        String e2 = b.e(str);
        String str3 = this.f19457g.get(e2);
        if (str3 != null) {
            return str3;
        }
        String b = b(e2);
        if (b != null) {
            return b;
        }
        String a = this.f19455e.a(e2, str2);
        return g.c(a) ? this.f19456f.a(a, str2) : a;
    }

    @Override // g.e.a.e
    public Context getContext() {
        return this.b;
    }

    @Override // g.e.a.e
    public String getIdentifier() {
        return this.a;
    }

    @Override // g.e.a.e
    public String getString(String str) {
        return e(str, null);
    }
}
