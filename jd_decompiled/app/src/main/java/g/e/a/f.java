package g.e.a;

import android.content.Context;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class f {
    private String a;

    /* renamed from: c  reason: collision with root package name */
    private InputStream f19451c;
    private b b = b.b;
    private final Map<String, String> d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    private final List<com.huawei.agconnect.core.a> f19452e = new ArrayList();

    public e a(Context context) {
        return new g.e.a.h.c.d(context, this.a, this.b, this.f19451c, this.d, this.f19452e, null);
    }

    public f b(InputStream inputStream) {
        this.f19451c = inputStream;
        return this;
    }
}
