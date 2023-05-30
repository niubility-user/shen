package com.jingdong.app.mall.home.n.g.w;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;

/* loaded from: classes4.dex */
public class e extends com.jingdong.app.mall.home.r.e.b {
    private com.jingdong.app.mall.home.n.a a;
    private String b;

    /* renamed from: c */
    private String f10392c;
    private String d;

    /* renamed from: e */
    private JumpEntity f10393e;

    /* renamed from: f */
    private com.jingdong.app.mall.home.n.g.v.c f10394f;

    /* renamed from: g */
    private com.jingdong.app.mall.home.n.g.v.d f10395g;

    /* renamed from: h */
    private String f10396h;

    /* renamed from: i */
    private int f10397i;

    /* renamed from: j */
    private CategoryEntity.FloorDecorateInfo f10398j;

    public e(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject);
        this.a = aVar;
        this.b = getJsonString(BottomNavigationConstant.KEY_FLOOR_NAME);
        this.f10392c = getJsonString("headImg");
        this.f10396h = getJsonString("showHead");
        getJsonString("rightImg");
        this.d = getJsonString("rightText");
        JumpEntity jumpEntity = (JumpEntity) getObject("rightJump", JumpEntity.class);
        this.f10393e = jumpEntity;
        com.jingdong.app.mall.home.n.g.v.d c2 = com.jingdong.app.mall.home.n.g.v.d.c(jumpEntity == null ? "" : jumpEntity.getSrvJson());
        this.f10395g = c2;
        if (c2.has("materialId")) {
            return;
        }
        this.f10395g.put("materialId", "");
    }

    public int a() {
        if (com.jingdong.app.mall.home.n.a.C_FLASH_DOWN == this.a) {
            return 0;
        }
        return com.jingdong.app.mall.home.floor.common.d.d(24) - 1;
    }

    public com.jingdong.app.mall.home.n.a b() {
        return this.a;
    }

    public String c() {
        if (this.f10398j != null && TextUtils.equals(this.f10396h, "3")) {
            String decorateIcon = this.f10398j.getDecorateIcon();
            return TextUtils.isEmpty(decorateIcon) ? this.f10392c : decorateIcon;
        }
        return this.f10392c;
    }

    public int[] d() {
        CategoryEntity.FloorDecorateInfo floorDecorateInfo = this.f10398j;
        return floorDecorateInfo == null ? new int[]{-14277082} : floorDecorateInfo.getTextColor(-14277082);
    }

    public String e() {
        com.jingdong.app.mall.home.n.g.v.d h2 = this.f10394f.h();
        h2.put(VideoPerfEntity.FIELD_PRV, this.f10395g);
        return h2.toString();
    }

    public int f() {
        CategoryEntity.FloorDecorateInfo floorDecorateInfo = this.f10398j;
        if (floorDecorateInfo == null) {
            return -7566196;
        }
        return floorDecorateInfo.getTextColor(-7566196)[0];
    }

    public String g() {
        return this.f10396h;
    }

    public String getDecorateBgUrl() {
        CategoryEntity.FloorDecorateInfo floorDecorateInfo = this.f10398j;
        if (floorDecorateInfo == null || com.jingdong.app.mall.home.n.a.C_ICON == this.a) {
            return null;
        }
        return floorDecorateInfo.getTopDecorateUrl();
    }

    public int h() {
        return this.f10397i;
    }

    public String i() {
        return this.f10392c;
    }

    public JumpEntity j() {
        return this.f10393e;
    }

    public String k() {
        return this.d;
    }

    public String l() {
        return this.b;
    }

    public void m(CategoryEntity.FloorDecorateInfo floorDecorateInfo) {
        this.f10398j = floorDecorateInfo;
    }

    public void n(com.jingdong.app.mall.home.n.g.v.c cVar) {
        this.f10394f = cVar;
    }

    public void o(int i2) {
        this.f10397i = i2;
    }

    public void p(boolean z) {
    }
}
