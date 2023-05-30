package com.jingdong.app.mall.home.r.f.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jingdong.app.mall.home.floor.model.entity.FlexCubeEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FlexCubeEngine;
import com.jingdong.app.mall.home.floor.view.view.MallFloorFlexCube;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class l extends b<FlexCubeEntity, FlexCubeEngine, MallFloorFlexCube> {

    /* renamed from: h */
    private View f10768h;

    /* renamed from: i */
    private boolean f10769i;

    /* renamed from: j */
    private String f10770j;

    /* renamed from: k */
    private final CopyOnWriteArrayList<JSONObject> f10771k = new CopyOnWriteArrayList<>();

    /* renamed from: l */
    private final ConcurrentHashMap<String, com.jingdong.app.mall.home.r.c.b> f10772l = new ConcurrentHashMap<>();

    private View T(Context context, FlexCubeModel flexCubeModel) {
        String style = flexCubeModel.getStyle(0);
        View view = FlexCube.getView(context, style);
        if (view instanceof IFloorView) {
            ((IFloorView) view).initView(style);
            return view;
        }
        return null;
    }

    public void P(JSONObject jSONObject) {
        if (this.f10771k.contains(jSONObject)) {
            return;
        }
        this.f10771k.add(jSONObject);
    }

    public void Q(JSONObject jSONObject) {
        String optString = jSONObject.optString("expo");
        if (!TextUtils.isEmpty(optString)) {
            com.jingdong.app.mall.home.r.c.a.i().H(optString);
        }
        String optString2 = jSONObject.optString("expoJson");
        if (TextUtils.isEmpty(optString2)) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b bVar = this.f10772l.get(optString2);
        if (bVar == null) {
            bVar = com.jingdong.app.mall.home.r.c.b.c(optString2);
            this.f10772l.put(optString2, bVar);
        }
        com.jingdong.app.mall.home.r.c.a.i().K(bVar);
    }

    public FlexCubeModel R() {
        return ((FlexCubeEntity) this.d).getFlexCubeModel();
    }

    public View S() {
        return this.f10768h;
    }

    public long U() {
        return ((FlexCubeEntity) this.d).getTimeEnd();
    }

    public String V() {
        return ((FlexCubeEntity) this.d).getTimeLayout();
    }

    public long W() {
        return ((FlexCubeEntity) this.d).getTimeRemain();
    }

    public boolean X() {
        return ((FlexCubeEntity) this.d).isShowTime();
    }

    public void Y(boolean z) {
        this.f10769i = z;
    }

    public void Z() {
        MallFloorFlexCube mallFloorFlexCube = (MallFloorFlexCube) c();
        if (mallFloorFlexCube == null) {
            return;
        }
        if (this.f10768h == null && ((FlexCubeEntity) this.d).isValid()) {
            this.f10768h = T(mallFloorFlexCube.getContext(), ((FlexCubeEntity) this.d).getFlexCubeModel());
        }
        this.f10770j = null;
        if (this.f10768h != null && ((FlexCubeEntity) this.d).isValid()) {
            mallFloorFlexCube.onSetVisible(true);
            mallFloorFlexCube.onRefreshView();
            return;
        }
        mallFloorFlexCube.cleanUI();
        mallFloorFlexCube.onSetVisible(false);
    }

    public void a0() {
        if (!this.f10769i || TextUtils.isEmpty(this.f10770j) || this.f10771k.size() <= 0) {
            return;
        }
        JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
        Iterator<JSONObject> it = this.f10771k.iterator();
        while (it.hasNext()) {
            String optString = it.next().optString("expoJson");
            if (!TextUtils.isEmpty(optString)) {
                d.put(com.jingdong.app.mall.home.r.c.b.c(optString));
            }
        }
        com.jingdong.app.mall.home.r.c.a.y(this.f10770j, "", d.toString());
        this.f10771k.clear();
        this.f10769i = false;
    }

    public void b0(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f10770j = str;
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        Z();
    }
}
