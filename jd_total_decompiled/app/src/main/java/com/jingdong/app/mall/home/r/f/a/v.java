package com.jingdong.app.mall.home.r.f.a;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.NewcomerFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorNewcomer;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.g;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class v extends com.jingdong.app.mall.home.r.f.a.b<NewcomerFloorEntity, NewcomerFloorEngine, MallFloorNewcomer> {

    /* loaded from: classes4.dex */
    public class a extends g.b {
        a() {
            v.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        protected void onFail(JDLocationError jDLocationError) {
            v.this.U(null);
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        public void onLbsChanged(com.jingdong.app.mall.home.o.a.i iVar) {
            v.this.U(iVar);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements f.d {
        b() {
            v.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onEnd(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null || !"0".equals(jDJSONObject.getString("code"))) {
                return;
            }
            com.jingdong.app.mall.home.r.e.b bVar = new com.jingdong.app.mall.home.r.e.b(jDJSONObject);
            v vVar = v.this;
            ((NewcomerFloorEngine) v.this.f10738e).j(bVar, vVar.f10739f, (NewcomerFloorEntity) vVar.d);
            v vVar2 = v.this;
            com.jingdong.app.mall.home.r.e.d dVar = vVar2.f10739f;
            vVar2.v(dVar.mParentModel, dVar);
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public static String Q(com.jingdong.app.mall.home.o.a.i iVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            com.jingdong.app.mall.home.o.a.f.y(jSONObject);
            com.jingdong.app.mall.home.o.a.f.e0(jSONObject, iVar);
            jSONObject.put("fQueryStamp", com.jingdong.app.mall.home.b.f8602m + "");
            jSONObject.put("userCategory", com.jingdong.app.mall.home.floor.common.i.l.f());
            jSONObject.put("homeAreaCode", String.valueOf(com.jingdong.app.mall.home.u.a.w().v()));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void K(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        super.K(iMallFloorUI, mallFloorEvent);
        if (!mallFloorEvent.d() && ((NewcomerFloorEntity) this.d).isValid() && ((NewcomerFloorEntity) this.d).isShow() && ((NewcomerFloorEntity) this.d).isRequestAsyncData() && iMallFloorUI.isCurrentData()) {
            T();
        }
    }

    public JDJSONArray P() {
        return ((NewcomerFloorEntity) this.d).getData();
    }

    public boolean R() {
        return ((NewcomerFloorEntity) this.d).isHeightFloor();
    }

    public boolean S() {
        return ((NewcomerFloorEntity) this.d).isShow();
    }

    public void T() {
        if (com.jingdong.app.mall.home.o.a.f.j0()) {
            return;
        }
        com.jingdong.app.mall.home.o.a.g.g(new a());
    }

    public void U(com.jingdong.app.mall.home.o.a.i iVar) {
        com.jingdong.app.mall.home.o.a.f.C0("newCustomerInfo", Q(iVar), new b());
    }

    public void V() {
        ((NewcomerFloorEntity) this.d).sendExpoMta();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((NewcomerFloorEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
