package com.jingdong.manto.m.b1;

import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jingdong.manto.f;
import com.jingdong.manto.h;
import com.jingdong.manto.launch.j;
import com.jingdong.manto.q.l;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class e {

    /* loaded from: classes15.dex */
    public interface a {
        void a(boolean z);
    }

    /* loaded from: classes15.dex */
    public static class b implements j.c {
        final String a;
        final String b;

        /* renamed from: c  reason: collision with root package name */
        final a f13305c;
        final com.jingdong.manto.i.d d;

        /* renamed from: e  reason: collision with root package name */
        final String f13306e;

        /* renamed from: f  reason: collision with root package name */
        final h f13307f;

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = b.this.f13305c;
                if (aVar != null) {
                    aVar.a(true);
                }
            }
        }

        b(a aVar, String str, com.jingdong.manto.i.d dVar, h hVar, String str2, String str3) {
            this.f13305c = aVar;
            this.b = str;
            this.d = dVar;
            this.f13307f = hVar;
            this.a = str2;
            this.f13306e = str3;
        }

        @Override // com.jingdong.manto.launch.j.c
        public final void a(com.jingdong.manto.i.c cVar) {
            com.jingdong.manto.i.c cVar2;
            MantoLog.d("MiniProgramNavigator", DynamicPrepareFetcher.KEY_PREPARE_MODEL_LAUNCH);
            if (cVar == null) {
                a aVar = this.f13305c;
                if (aVar != null) {
                    aVar.a(false);
                    return;
                }
                return;
            }
            cVar.a = this.a;
            cVar.f13082e = this.f13306e;
            cVar.f13083f = MantoStringUtils.deleteLeftSlash(this.b);
            com.jingdong.manto.i.d dVar = cVar.f13084g;
            if (dVar != null) {
                dVar.a(this.d);
            } else {
                cVar.f13084g = this.d;
            }
            com.jingdong.manto.i.d dVar2 = this.d;
            if (dVar2 != null) {
                cVar.f13090m = dVar2.f13093e;
            }
            f h2 = this.f13307f.h();
            if (h2 != null && (cVar2 = h2.r) != null) {
                cVar.f13086i = cVar2.f13086i;
            }
            h2.f13013e.c(h2, cVar, null);
            MantoThreadUtils.post(new a(), 1500);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(h hVar, String str, String str2, int i2, String str3, JSONObject jSONObject, a aVar) {
        MantoLog.d("MiniProgramNavigator", "navigate");
        l lVar = hVar.h().f13014f;
        n i3 = (lVar == null || lVar.getFirstPage() == null) ? null : lVar.getFirstPage().i();
        String str4 = i3 != null ? i3.s().t : "";
        com.jingdong.manto.i.d dVar = new com.jingdong.manto.i.d();
        dVar.a = hVar.a();
        dVar.f13093e = jSONObject == null ? "{}" : jSONObject.toString();
        dVar.d = 1;
        dVar.f13092c = str4;
        dVar.b = i2;
        new j(str, str2, new b(aVar, str3, dVar, hVar, str, str2)).d();
    }
}
