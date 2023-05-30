package com.jingdong.app.mall.home.floor.ctrl.t;

import com.jingdong.common.XView.XViewEntity;

/* loaded from: classes4.dex */
public class d {
    private final XViewEntity a;
    private final String b;

    public d(String str, String str2) {
        XViewEntity xViewEntity = new XViewEntity();
        this.a = xViewEntity;
        xViewEntity.url = str;
        xViewEntity.needAutoDisplay = true;
        this.b = str2;
    }

    public XViewEntity a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
