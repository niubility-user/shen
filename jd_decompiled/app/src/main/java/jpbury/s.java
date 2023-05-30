package jpbury;

import com.jdpay.bury.IdExtension;
import com.jdpay.bury.proguard.SerializerFieldsKeep;
import java.util.ArrayList;
import java.util.List;

@SerializerFieldsKeep
/* loaded from: classes11.dex */
public class s {
    private final p commonMap;
    private final transient boolean errorFlag;
    private final List<t> eventList;
    private final transient boolean forceUpload;
    private final transient int logLevel;
    private final transient f session;
    private final long timestamp;
    private final String version = "1";

    private s(j jVar, f fVar, String str, String str2, String str3, long j2, String str4, String str5, String str6, String str7, IdExtension idExtension, int i2, boolean z, boolean z2) {
        this.errorFlag = z2;
        this.session = fVar;
        this.commonMap = new p(jVar, fVar.e(), i2);
        ArrayList arrayList = new ArrayList();
        this.eventList = arrayList;
        arrayList.add(new t(str, str2, str3, j2, str4, str5, str6, str7, idExtension, i2, z));
        this.timestamp = System.currentTimeMillis();
        this.logLevel = i2;
        this.forceUpload = z;
    }

    public static s a(g gVar) {
        return a(gVar, false);
    }

    public static s a(g gVar, boolean z) {
        return new s(gVar.e().d(), gVar.e(), gVar.g(), gVar.a(), gVar.c(), gVar.f(), gVar.h(), "", "", "", gVar.d(), gVar.b(), gVar.i(), z);
    }

    public static s a(h hVar) {
        return new s(hVar.f().d(), hVar.f(), "method", hVar.a(), hVar.e(), hVar.h(), hVar.i(), hVar.g(), hVar.b(), hVar.c(), null, hVar.d(), hVar.j(), false);
    }

    public p a() {
        return this.commonMap;
    }

    public List<t> b() {
        return this.eventList;
    }

    public int c() {
        return this.logLevel;
    }

    public f d() {
        return this.session;
    }

    public long e() {
        return this.timestamp;
    }

    public String f() {
        return this.commonMap.a().d();
    }

    public String g() {
        return "1";
    }

    public boolean h() {
        return this.errorFlag;
    }

    public boolean i() {
        return this.forceUpload;
    }
}
