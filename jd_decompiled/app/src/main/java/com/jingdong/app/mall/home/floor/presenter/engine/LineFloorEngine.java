package com.jingdong.app.mall.home.floor.presenter.engine;

import android.os.SystemClock;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.k;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.LineFloorEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.g;
import com.jingdong.app.mall.home.o.a.i;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.p;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LineFloorEngine extends FloorEngine<LineFloorEntity> {

    /* renamed from: e */
    private static String f9660e = "";

    /* renamed from: f */
    private static long f9661f;
    private com.jingdong.app.mall.home.floor.view.b.c b;

    /* renamed from: c */
    private com.jingdong.app.mall.home.floor.view.b.a[] f9662c;
    private p d;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ h f9663g;

        /* renamed from: h */
        final /* synthetic */ t f9664h;

        /* renamed from: i */
        final /* synthetic */ LineFloorEntity f9665i;

        a(h hVar, t tVar, LineFloorEntity lineFloorEntity) {
            LineFloorEngine.this = r1;
            this.f9663g = hVar;
            this.f9664h = tVar;
            this.f9665i = lineFloorEntity;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (this.f9663g.V > 0 || k.d()) {
                return;
            }
            h hVar = this.f9663g;
            hVar.V++;
            if (this.f9664h == t.LINE_1_TO_4_GROUP_BUYING) {
                LineFloorEngine.this.q(new i(), this.f9665i, true);
            } else if (hVar.N && hVar.U == 0) {
                k.m(new i(), this.f9665i, true);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b extends g.b {
        final /* synthetic */ LineFloorEntity a;

        b(LineFloorEntity lineFloorEntity) {
            LineFloorEngine.this = r1;
            this.a = lineFloorEntity;
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        public void onLbsChanged(i iVar) {
            if (iVar.g(f.B(), this.a.getLbsDistance())) {
                LineFloorEngine.this.q(iVar, this.a, false);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c implements f.d {
        final /* synthetic */ LineFloorEntity a;

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g */
            final /* synthetic */ JDJSONObject f9667g;

            a(JDJSONObject jDJSONObject) {
                c.this = r1;
                this.f9667g = jDJSONObject;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                c cVar = c.this;
                if (!LineFloorEngine.this.o(this.f9667g, cVar.a) || LineFloorEngine.this.d == null) {
                    return;
                }
                LineFloorEngine.this.d.Y();
                MallFloorEvent.i(true);
            }
        }

        c(LineFloorEntity lineFloorEntity) {
            LineFloorEngine.this = r1;
            this.a = lineFloorEntity;
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onEnd(JDJSONObject jDJSONObject) {
            if ("0".equals(jDJSONObject.getString("code"))) {
                f.E0(new a(jDJSONObject));
            }
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    private void k(JDJSONObject jDJSONObject, d dVar, LineFloorEntity lineFloorEntity) {
        com.jingdong.app.mall.home.r.e.i iVar;
        ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList;
        if (jDJSONObject == null || jDJSONObject.size() < 1) {
            return;
        }
        try {
            iVar = new com.jingdong.app.mall.home.r.e.i(jDJSONObject, false);
            arrayList = iVar.f10682c;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (arrayList != null && arrayList.size() >= 4 && dVar != null) {
            dVar.f10682c = iVar.f10682c;
            String optString = jDJSONObject.optString("siteName");
            String optString2 = jDJSONObject.optString("siteId");
            JumpEntity jumpEntity = null;
            try {
                JDJSONObject optJSONObject = jDJSONObject.optJSONObject("jump");
                if (optJSONObject != null) {
                    jumpEntity = (JumpEntity) optJSONObject.toJavaObject(JumpEntity.class);
                }
            } catch (Exception e3) {
                f.s0(this, e3);
            }
            com.jingdong.app.mall.home.floor.view.b.g.a titleInfo = lineFloorEntity.getTitleInfo();
            if (titleInfo == null) {
                titleInfo = new com.jingdong.app.mall.home.floor.view.b.g.a();
            }
            titleInfo.o(optString);
            titleInfo.n(jumpEntity);
            lineFloorEntity.setSiteId(optString2);
        }
    }

    public static String l() {
        return f9660e;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0056 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x002e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean m(com.jingdong.app.mall.home.r.e.h r19, com.jingdong.app.mall.home.r.e.d r20, com.jingdong.app.mall.home.floor.model.entity.LineFloorEntity r21, boolean r22) {
        /*
            r18 = this;
            r0 = r18
            r7 = r20
            r8 = r21
            if (r22 == 0) goto L13
            java.lang.String r1 = r20.e()
            com.jd.framework.json.JDJSONObject r1 = com.jingdong.app.mall.home.floor.common.i.k.h(r1)
            r0.k(r1, r7, r8)
        L13:
            java.util.ArrayList<com.jingdong.app.mall.home.r.e.f> r1 = r7.f10682c
            r2 = 0
            if (r1 == 0) goto Lb8
            int r3 = r1.size()
            if (r3 > 0) goto L20
            goto Lb8
        L20:
            r21.clearLineList()
            java.util.Iterator r9 = r1.iterator()
            r10 = 0
            java.lang.String r1 = ""
            r11 = r1
            r1 = r10
            r12 = 0
            r13 = 0
        L2e:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto Lb1
            java.lang.Object r2 = r9.next()
            r6 = r2
            com.jingdong.app.mall.home.r.e.f r6 = (com.jingdong.app.mall.home.r.e.f) r6
            r14 = 4
            if (r12 != r14) goto L40
            goto Lb1
        L40:
            com.jingdong.app.mall.home.floor.view.b.a r2 = com.jingdong.app.mall.home.floor.view.b.a.UNKNOWN
            com.jingdong.app.mall.home.floor.view.b.a[] r3 = r0.f9662c
            if (r3 != 0) goto L4c
            com.jingdong.app.mall.home.floor.view.b.a r3 = com.jingdong.app.mall.home.floor.common.i.u.f(r6)
        L4a:
            r15 = r3
            goto L53
        L4c:
            int r4 = r3.length
            if (r4 <= r13) goto L52
            r3 = r3[r13]
            goto L4a
        L52:
            r15 = r2
        L53:
            if (r15 != r2) goto L56
            goto L2e
        L56:
            int r5 = r15.getWeight()
            int r2 = r15.getSubWeight()
            if (r2 == r5) goto L66
            if (r1 != 0) goto L66
            int r13 = r13 + 1
            r1 = r6
            goto L2e
        L66:
            if (r2 != r5) goto L6b
            r16 = r10
            goto L6d
        L6b:
            r16 = r1
        L6d:
            if (r2 == r5) goto L7f
            com.jingdong.app.mall.home.floor.view.b.c r2 = r0.b
            r1 = r15
            r3 = r19
            r4 = r20
            r17 = r5
            r5 = r16
            com.jingdong.app.mall.home.floor.view.linefloor.base.a r1 = r1.getLineItem(r2, r3, r4, r5, r6)
            goto L91
        L7f:
            r17 = r5
            com.jingdong.app.mall.home.floor.view.b.c r2 = r0.b
            r16 = 0
            r1 = r15
            r3 = r19
            r4 = r20
            r5 = r6
            r6 = r16
            com.jingdong.app.mall.home.floor.view.linefloor.base.a r1 = r1.getLineItem(r2, r3, r4, r5, r6)
        L91:
            if (r1 == 0) goto Lae
            boolean r2 = r1.D()
            if (r2 != 0) goto L9a
            goto Lae
        L9a:
            int r12 = r12 + r17
            if (r12 <= r14) goto La1
            int r12 = r12 - r17
            goto Lae
        La1:
            int r13 = r13 + 1
            java.lang.String r2 = r15.getFirstTypeStr()
            java.lang.String r11 = r11.concat(r2)
            r8.addLineItem(r1)
        Lae:
            r1 = r10
            goto L2e
        Lb1:
            r1 = r19
            r8.setLineInfo(r11, r12, r7, r1)
            r1 = 1
            return r1
        Lb8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.presenter.engine.LineFloorEngine.m(com.jingdong.app.mall.home.r.e.h, com.jingdong.app.mall.home.r.e.d, com.jingdong.app.mall.home.floor.model.entity.LineFloorEntity, boolean):boolean");
    }

    private void p(h hVar, LineFloorEntity lineFloorEntity, t tVar) {
        if (hVar.Z || hVar.a0 || hVar.V > 0 || !lineFloorEntity.needAsyncRequestNow()) {
            return;
        }
        f.F0(new a(hVar, tVar, lineFloorEntity), 50L);
    }

    public static void r() {
        f9660e = k.i();
    }

    public void j(p pVar) {
        this.d = pVar;
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: n */
    public void e(h hVar, d dVar, LineFloorEntity lineFloorEntity) {
        if (hVar == null || dVar == null || lineFloorEntity == null) {
            return;
        }
        super.e(hVar, dVar, lineFloorEntity);
        this.b = dVar.q.getLineType();
        this.f9662c = dVar.q.getLineTypeEnumArr();
        lineFloorEntity.setTitleInfo(hVar.M ? new com.jingdong.app.mall.home.floor.view.b.g.a(hVar) : null);
        lineFloorEntity.setAsynSwitch(hVar.O);
        lineFloorEntity.setSiteType(hVar.P);
        lineFloorEntity.setLbsDistance(hVar.R);
        lineFloorEntity.setSiteId(hVar.Q);
        lineFloorEntity.setAsyncCoreFloor(hVar.N);
        lineFloorEntity.setClkLog(hVar.getJsonString("clkLog"));
        lineFloorEntity.setExpoLog(hVar.getJsonString("expoLog"));
        m(hVar, dVar, lineFloorEntity, hVar.a0);
        p(hVar, lineFloorEntity, dVar.q);
    }

    public boolean o(JDJSONObject jDJSONObject, LineFloorEntity lineFloorEntity) {
        h floorModel = lineFloorEntity.getFloorModel();
        d elements = lineFloorEntity.getElements();
        if (floorModel == null || elements == null) {
            return false;
        }
        k.n(elements.e(), jDJSONObject);
        return m(floorModel, elements, lineFloorEntity, true);
    }

    public void q(i iVar, LineFloorEntity lineFloorEntity, boolean z) {
        if (SystemClock.elapsedRealtime() - f9661f >= 10000 && !f.j0()) {
            if (z) {
                g.g(new b(lineFloorEntity));
                return;
            }
            f9661f = SystemClock.elapsedRealtime();
            r();
            f.C0("jxppBannerInfo", k.j(iVar, lineFloorEntity), new c(lineFloorEntity));
        }
    }
}
