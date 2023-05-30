package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.b;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.g;
import com.jingdong.app.mall.home.o.a.i;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.j;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CategoryEngine extends FloorEngine<CategoryEntity> {

    /* renamed from: c  reason: collision with root package name */
    private static HashMap<String, Boolean> f9659c = new HashMap<>();
    private j b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends g.b {
        final /* synthetic */ CategoryEntity.CaItem a;

        /* renamed from: com.jingdong.app.mall.home.floor.presenter.engine.CategoryEngine$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0295a implements f.d {
            C0295a() {
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onEnd(JDJSONObject jDJSONObject) {
                a aVar = a.this;
                CategoryEngine.this.n(jDJSONObject, aVar.a);
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.app.mall.home.o.a.f.d
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        }

        a(CategoryEntity.CaItem caItem) {
            this.a = caItem;
        }

        @Override // com.jingdong.app.mall.home.o.a.g.b
        protected void onLbsChanged(i iVar) {
            f.C0("allCityBuyApi", CategoryEngine.this.m(iVar, this.a), new C0295a());
        }
    }

    private boolean l(CategoryEntity.CaItem caItem) {
        if (caItem.isCityBuyTab()) {
            if (caItem.isDirect()) {
                f9659c.put(caItem.getPmId(), Boolean.TRUE);
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String m(i iVar, CategoryEntity.CaItem caItem) {
        if (iVar == null || caItem == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            f.y(jSONObject);
            f.e0(jSONObject, iVar);
            jSONObject.put("pmid", caItem.getPmId());
            jSONObject.put("fQueryStamp", b.f8602m + "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(JDJSONObject jDJSONObject, CategoryEntity.CaItem caItem) {
        j jVar;
        if (jDJSONObject == null || jDJSONObject.size() < 1 || !"0".equals(jDJSONObject.getString("code"))) {
            return;
        }
        boolean equals = "1".equals(jDJSONObject.optString("isDirect"));
        f9659c.put(caItem.getPmId(), Boolean.valueOf(equals));
        if (!equals || (jVar = this.b) == null) {
            return;
        }
        jVar.d0(caItem);
    }

    private void p(CategoryEntity.CaItem caItem, CategoryEntity categoryEntity) {
        String pmId = caItem.getPmId();
        if (f9659c.containsKey(pmId)) {
            Boolean bool = f9659c.get(pmId);
            caItem.setDirect(bool != null && bool.booleanValue());
        } else if (categoryEntity.getAsyncState() && !f.j0()) {
            g.g(new a(caItem));
        }
    }

    public void k(j jVar) {
        this.b = jVar;
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void e(h hVar, d dVar, CategoryEntity categoryEntity) {
        int size;
        if (hVar == null || categoryEntity == null) {
            return;
        }
        categoryEntity.setDataFromCache(hVar.Z);
        com.jingdong.app.mall.home.n.h.a.j(hVar.getJsonString("pullIntervalTime"));
        com.jingdong.app.mall.home.n.h.a.k(hVar.getJsonString("cateIntervalTime"));
        JDJSONArray jsonArr = hVar.getJsonArr("content");
        if (jsonArr != null && (size = jsonArr.size()) >= 6) {
            com.jingdong.app.mall.home.a.t.o(hVar);
            c(dVar, categoryEntity);
            int d = com.jingdong.app.mall.home.floor.view.b.h.a.d(hVar.getJsonString("selectedTextColor"), -1);
            categoryEntity.setSelectColor(d);
            categoryEntity.setUnSelectColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(hVar.getJsonString("rightCornerColor"), -1));
            categoryEntity.setSmileColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(hVar.getJsonString("selectedArcColor"), d));
            categoryEntity.setRightText(hVar.getJsonString("rightCorner"));
            categoryEntity.setRightImg(hVar.getJsonString("rightCornerImg"));
            categoryEntity.setRightWidth(hVar.getJsonInt("rightCornerImgWidth", 0));
            categoryEntity.setAnimation(hVar.getJsonInt("mAnimation"));
            categoryEntity.setAnimationCount(hVar.getJsonInt("animationCount"));
            categoryEntity.setAnimationPlayCount(hVar.getJsonInt("animationPlayCount"));
            categoryEntity.setRightJump((JumpEntity) hVar.getObject("jump", JumpEntity.class));
            categoryEntity.setAsyncState("1".equals(hVar.getJsonString("asynSwitch", "0")));
            categoryEntity.setHideSmileLine(TextUtils.equals(hVar.getJsonString("isDisplayArc", "1"), "0"));
            categoryEntity.setTabMargin(hVar.getJsonInt("tabMargin", 0));
            categoryEntity.setTextSize(Math.min(Math.max(c.h(hVar.getJsonString("selectedFontSize"), 30), 26), 40), Math.min(Math.max(c.h(hVar.getJsonString("unSelectedFontSize"), 30), 26), 40));
            CategoryEntity.CaItem caItem = null;
            for (int i2 = 0; i2 < size; i2++) {
                CategoryEntity.CaItem caItem2 = new CategoryEntity.CaItem(jsonArr.getJSONObject(i2), i2, categoryEntity);
                if (l(caItem2) && caItem == null) {
                    caItem = caItem2;
                }
                categoryEntity.addItem(caItem2, i2, hVar.A);
            }
            JDJSONObject jsonObject = hVar.getJsonObject(AppStateModule.APP_STATE_BACKGROUND);
            if (jsonObject != null && jsonObject.size() > 0 && !com.jingdong.app.mall.home.state.dark.a.h()) {
                CategoryEntity.sDecorateInfo = new CategoryEntity.DecorateInfo(jsonObject);
            } else {
                CategoryEntity.sDecorateInfo = null;
            }
            if (caItem != null) {
                p(caItem, categoryEntity);
            }
        }
    }
}
