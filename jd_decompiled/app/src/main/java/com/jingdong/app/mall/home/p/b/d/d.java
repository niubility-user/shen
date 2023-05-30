package com.jingdong.app.mall.home.p.b.d;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.r.e.f;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class d extends com.jingdong.app.mall.home.r.e.b {
    private static final JDJSONObject d = new JDJSONObject();
    private BaseModel a;
    private f b;

    /* renamed from: c */
    private com.jingdong.app.mall.home.p.b.e.a f10528c;

    public d(BaseModel baseModel, JDJSONObject jDJSONObject) {
        super(jDJSONObject.getJSONObject("data"));
        if (this.srcJson == null) {
            return;
        }
        String jsonString = getJsonString("bSideStyle");
        if (TextUtils.equals(jsonString, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_LIVE)) {
            this.f10528c = new com.jingdong.app.mall.home.p.b.e.a(d, com.jingdong.app.mall.home.p.b.a.CORE_LIVE_2X2.getNodeName());
        } else if (TextUtils.equals(jsonString, "fullImg")) {
            this.f10528c = new com.jingdong.app.mall.home.p.b.e.a(d, com.jingdong.app.mall.home.p.b.a.CORE_IMG_2X2.getNodeName());
        }
        com.jingdong.app.mall.home.p.b.e.a aVar = this.f10528c;
        if (aVar == null) {
            c.f10522h.set(true);
            return;
        }
        aVar.l(0);
        this.f10528c.k(baseModel.h());
        this.a = baseModel;
        this.b = new f(this.srcJson);
    }

    private void a(String str) {
        com.jingdong.app.mall.home.r.c.a.i().H(this.b.j());
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.app.mall.home.r.c.a.i().K(com.jingdong.app.mall.home.r.c.b.c(str));
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONArray d2 = com.jingdong.app.mall.home.r.c.b.d();
        d2.put(com.jingdong.app.mall.home.r.c.b.c(str));
        com.jingdong.app.mall.home.r.c.a.y("Home_SeckillExpo", "", d2.toString());
    }

    public View b(RelativeLayout relativeLayout, BaseModel baseModel, boolean z) {
        com.jingdong.app.mall.home.p.b.e.b o;
        if (baseModel == null || this.b == null || this.f10528c == null || (o = baseModel.o()) == null) {
            return null;
        }
        baseModel.G(this);
        com.jingdong.app.mall.home.p.b.e.b bVar = new com.jingdong.app.mall.home.p.b.e.b();
        bVar.i(o.d());
        bVar.g(this.b);
        com.jingdong.app.mall.home.p.b.b a = this.f10528c.a();
        if (com.jingdong.app.mall.home.p.b.a.EMPTY == a) {
            return null;
        }
        com.jingdong.app.mall.home.deploy.view.base.b crateParser = a.crateParser(relativeLayout.getContext());
        BaseModel c2 = crateParser.c();
        c2.F(true);
        c2.I(z);
        crateParser.j(this.f10528c, c2);
        crateParser.h(this.f10528c);
        crateParser.a(relativeLayout, bVar);
        c.f10522h.set(true);
        String l2 = this.b.l();
        if (z) {
            d(l2);
        } else {
            a(l2);
        }
        View d2 = crateParser.d();
        d2.setId(R.id.home_card_b_side);
        return d2;
    }

    public boolean c(View view, BaseModel baseModel) {
        return m.x(view) && baseModel == this.a;
    }
}
