package com.jingdong.manto.m.p0.e.d;

import android.text.TextUtils;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.q.n;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends l0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.p0.e.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class C0595a extends com.jingdong.manto.m.p0.e.c {
        final /* synthetic */ h a;
        final /* synthetic */ String b;

        C0595a(h hVar, String str) {
            this.a = hVar;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.m.p0.e.b.a(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements n.i0 {
        final /* synthetic */ String a;
        final /* synthetic */ n b;

        /* renamed from: com.jingdong.manto.m.p0.e.d.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0596a extends com.jingdong.manto.m.p0.e.c {
            C0596a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.manto.m.p0.e.b.e(b.this.a);
                com.jingdong.manto.m.p0.e.b.a(b.this.a, false);
            }
        }

        /* renamed from: com.jingdong.manto.m.p0.e.d.a$b$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class C0597b extends com.jingdong.manto.m.p0.e.c {
            C0597b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.manto.m.p0.e.b.a(b.this.a);
            }
        }

        b(String str, n nVar) {
            this.a = str;
            this.b = nVar;
        }

        @Override // com.jingdong.manto.q.n.i0
        public void a() {
            com.jingdong.manto.m.p0.e.b.a(this.a, true);
        }

        @Override // com.jingdong.manto.q.n.i0
        public void b() {
            new C0596a().a();
        }

        @Override // com.jingdong.manto.q.n.i0
        public void c() {
            new C0597b().a();
            this.b.b(this);
        }

        @Override // com.jingdong.manto.q.n.i0
        public void d() {
            com.jingdong.manto.m.p0.e.b.a(this.a, true);
        }
    }

    static String a(h hVar) {
        String b2 = com.jingdong.manto.m.p0.e.b.b(hVar.a());
        new C0595a(hVar, b2).a();
        n pageView = c0.getPageView(hVar);
        if (pageView != null) {
            pageView.a(new b(b2, pageView));
        }
        return b2;
    }

    @Override // com.jingdong.manto.m.l0
    public String a(h hVar, JSONObject jSONObject) {
        String a = a(hVar);
        if (TextUtils.isEmpty(a)) {
            return putErrMsg("fail:create audio player fail");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("audioId", a);
        return putErrMsg(IMantoBaseModule.SUCCESS, hashMap);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "createAudioInstance";
    }
}
