package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.app.mall.home.floor.model.entity.TrendEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.TrendEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.common.entity.JumpEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class z extends b<TrendEntity, TrendEngine, IMallFloorUI> {

    /* renamed from: h  reason: collision with root package name */
    private ArrayList<String> f10792h = new ArrayList<>();

    /* renamed from: i  reason: collision with root package name */
    private boolean f10793i;

    public void P(int i2, int i3) {
        JumpEntity a;
        if (i2 > i3 || j()) {
            return;
        }
        for (int max = Math.max(i2, 0); max <= i3; max++) {
            com.jingdong.app.mall.home.r.e.k.e Q = Q(max);
            if (Q != null && (a = Q.a()) != null && !this.f10792h.contains(a.srvJson)) {
                this.f10792h.add(a.srvJson);
            }
        }
    }

    public com.jingdong.app.mall.home.r.e.k.e Q(int i2) {
        return ((TrendEntity) this.d).getItemAt(i2);
    }

    public List<com.jingdong.app.mall.home.r.e.k.e> R() {
        return ((TrendEntity) this.d).getItemList();
    }

    public boolean S() {
        return this.f10793i;
    }

    public void T() {
        if (j()) {
            return;
        }
        if (this.f10793i) {
            JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
            Iterator<String> it = this.f10792h.iterator();
            while (it.hasNext()) {
                d.put(com.jingdong.app.mall.home.r.c.b.c(it.next()));
            }
            com.jingdong.app.mall.home.r.c.a.y("Home_TrendFloorExpo", "", d.toString());
            this.f10792h.clear();
        }
        this.f10793i = false;
    }

    public void U(boolean z) {
        this.f10793i = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((TrendEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.cleanUI();
        iMallFloorUI.onSetVisible(false);
    }
}
