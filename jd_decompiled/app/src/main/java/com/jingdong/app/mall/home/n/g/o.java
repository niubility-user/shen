package com.jingdong.app.mall.home.n.g;

import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class o extends com.jingdong.app.mall.home.n.g.u.d {
    @JvmField
    @NotNull
    public static com.jingdong.app.mall.home.n.g.w.d B = new com.jingdong.app.mall.home.n.g.w.d();
    @JvmField
    @NotNull
    public static com.jingdong.app.mall.home.n.g.w.b C;
    @Nullable
    private String A;
    private final JDJSONArray z;

    static {
        com.jingdong.app.mall.home.n.g.w.b bVar = new com.jingdong.app.mall.home.n.g.w.b();
        C = bVar;
        bVar.d = false;
        bVar.g(new Rect(20, 90, 20, 0));
        B.a = new com.jingdong.app.mall.home.floor.common.f(122, 122);
        B.a.F(new Rect(0, 0, 0, 0));
        B.b = new com.jingdong.app.mall.home.floor.common.f(140, 72);
        B.b.F(new Rect(0, 132, 0, 0));
        B.b.K(new Rect(5, 0, 5, 0));
        com.jingdong.app.mall.home.n.g.w.d dVar = B;
        dVar.d = 24;
        dVar.f10389c = -14277082;
    }

    public o(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar, bVarArr);
        this.z = getJsonArr("iconList");
    }

    @Nullable
    public final String Q() {
        return this.A;
    }

    public final void R(int i2, int i3, @Nullable JDJSONObject jDJSONObject) {
        if (this.z == null) {
            return;
        }
        this.a.v(jDJSONObject);
        J("Category_Main_Subcategory_More_Expo", this.a, "\u66f4\u591a\u5206\u7c7b");
        v(true);
        this.x = i2 == 0;
        this.y = i3 >= this.z.size();
        com.jingdong.app.mall.home.n.b bVar = this.s[0];
        while (i2 < i3 && i2 < this.z.size()) {
            this.u.add(bVar.getTypeModel(this.z.getJSONObject(i2), this, i2));
            i2++;
        }
        int d = this.x ? com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10358k) : 0;
        int d2 = this.y ? com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.n.g.u.c.f10358k) : 0;
        if (this.x) {
            E(C.d(), com.jingdong.app.mall.home.floor.common.d.d(26));
            this.A = getJsonString(BottomNavigationConstant.KEY_FLOOR_NAME);
            this.f10356n = !TextUtils.isEmpty(r6);
        }
        s(d, d2);
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void o(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    protected void q() {
    }
}
