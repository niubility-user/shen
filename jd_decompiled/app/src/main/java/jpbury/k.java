package jpbury;

import com.jdpay.bury.DynamicValue;
import com.tencent.mapsdk.internal.l4;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class k {

    /* renamed from: f */
    private static final String f20134f = "https://cltm.jd.com/event/config";
    private String a = i.f20132e;
    private String b = f20134f;

    /* renamed from: c */
    private boolean f20135c = true;
    private Map<String, String> d = new HashMap();

    /* renamed from: e */
    private Map<String, Class<DynamicValue>> f20136e = new HashMap();

    public j a() {
        j iVar = this.f20135c ? new i(this.a) : new j(this.a);
        iVar.a(this.d);
        for (Map.Entry<String, Class<DynamicValue>> entry : this.f20136e.entrySet()) {
            iVar.a(entry.getKey(), entry.getValue());
        }
        return iVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, Class<DynamicValue> cls) {
        this.f20136e.put(str, cls);
    }

    public void a(String str, String str2) {
        this.d.put(str, str2);
    }

    public void a(Map<String, String> map) {
        this.d.putAll(map);
    }

    public void b() {
        this.f20135c = false;
    }

    public void b(String str) {
        a(l4.f16791e, str);
    }

    public String c() {
        return this.b;
    }

    public void c(String str) {
        this.a = str;
    }
}
