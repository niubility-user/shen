package com.jingdong.app.mall.home.floor.presenter.engine;

import android.os.SystemClock;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.k;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.common.i.u;
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
import java.util.Iterator;

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

    /* JADX WARN: Removed duplicated region for block: B:165:0x0056 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x002e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m(h hVar, d dVar, LineFloorEntity lineFloorEntity, boolean z) {
        com.jingdong.app.mall.home.floor.view.b.a aVar;
        com.jingdong.app.mall.home.floor.view.b.a aVar2;
        int i2;
        com.jingdong.app.mall.home.floor.view.linefloor.base.a lineItem;
        if (z) {
            k(k.h(dVar.e()), dVar, lineFloorEntity);
        }
        ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = dVar.f10682c;
        if (arrayList == null || arrayList.size() <= 0) {
            return false;
        }
        lineFloorEntity.clearLineList();
        Iterator<com.jingdong.app.mall.home.r.e.f> it = arrayList.iterator();
        String str = "";
        com.jingdong.app.mall.home.r.e.f fVar = null;
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            com.jingdong.app.mall.home.r.e.f next = it.next();
            if (i3 == 4) {
                break;
            }
            com.jingdong.app.mall.home.floor.view.b.a aVar3 = com.jingdong.app.mall.home.floor.view.b.a.UNKNOWN;
            com.jingdong.app.mall.home.floor.view.b.a[] aVarArr = this.f9662c;
            if (aVarArr == null) {
                aVar2 = u.f(next);
            } else if (aVarArr.length > i4) {
                aVar2 = aVarArr[i4];
            } else {
                aVar = aVar3;
                if (aVar != aVar3) {
                    int weight = aVar.getWeight();
                    int subWeight = aVar.getSubWeight();
                    if (subWeight == weight || fVar != null) {
                        com.jingdong.app.mall.home.r.e.f fVar2 = subWeight == weight ? null : fVar;
                        if (subWeight != weight) {
                            i2 = weight;
                            lineItem = aVar.getLineItem(this.b, hVar, dVar, fVar2, next);
                        } else {
                            i2 = weight;
                            lineItem = aVar.getLineItem(this.b, hVar, dVar, next, null);
                        }
                        if (lineItem != null && lineItem.D()) {
                            i3 += i2;
                            if (i3 > 4) {
                                i3 -= i2;
                            } else {
                                i4++;
                                str = str.concat(aVar.getFirstTypeStr());
                                lineFloorEntity.addLineItem(lineItem);
                            }
                        }
                        fVar = null;
                    } else {
                        i4++;
                        fVar = next;
                    }
                }
            }
            aVar = aVar2;
            if (aVar != aVar3) {
            }
        }
        lineFloorEntity.setLineInfo(str, i3, dVar, hVar);
        return true;
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
