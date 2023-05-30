package com.jingdong.app.mall.home.r.f.a;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.model.entity.BBannerEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.BBannerEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.search.view.PriceHelper;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class n extends b<BBannerEntity, BBannerEngine, MallFloorBBanner> {

    /* renamed from: h */
    private int f10773h;

    /* renamed from: i */
    private final com.jingdong.app.mall.home.floor.common.f f10774i = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_fragment_right_out, -1);

    private int[] P() {
        int[] iArr = new int[2];
        com.jingdong.app.mall.home.floor.view.b.h.a.h(com.jingdong.app.mall.home.floor.view.b.h.a.e(((BBannerEntity) this.d).getAllColor(), -49152), iArr);
        return iArr;
    }

    private String Q() {
        return ((BBannerEntity) this.d).getAllTagImg();
    }

    private JDJSONObject T(int i2) {
        JDJSONArray commonItems = ((BBannerEntity) this.d).getCommonItems();
        if (commonItems == null || i2 >= commonItems.size()) {
            return null;
        }
        return commonItems.getJSONObject(i2);
    }

    private Product W(int i2) {
        List<Product> msProducts = ((BBannerEntity) this.d).getMsProducts();
        if (msProducts == null || i2 >= msProducts.size()) {
            return null;
        }
        return msProducts.get(i2);
    }

    private int[] Y() {
        int[] iArr = new int[2];
        com.jingdong.app.mall.home.floor.view.b.h.a.h(com.jingdong.app.mall.home.floor.view.b.h.a.e(((BBannerEntity) this.d).getPriceColor(), -381927), iArr);
        return iArr;
    }

    private String e0() {
        return ((BBannerEntity) this.d).getSkuTagImg();
    }

    private void l0(View view, int i2, int i3) {
        JumpEntity jumpEntity = null;
        try {
            JDJSONObject jSONObject = T(i2).getJSONObject("jump");
            if (jSONObject != null) {
                jumpEntity = (JumpEntity) jSONObject.toJavaObject(JumpEntity.class);
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
        JumpEntity jumpEntity2 = jumpEntity;
        if (jumpEntity2 == null || com.jingdong.app.mall.home.floor.common.i.l.k()) {
            return;
        }
        com.jingdong.app.mall.home.floor.common.i.l.c(jumpEntity2, b0(i2));
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jumpEntity2.srvJson);
        c2.put("skuposition", String.valueOf(i3));
        com.jingdong.app.mall.home.floor.common.i.l.onClickJsonEvent(view, jumpEntity2, "", "", c2.toString(), i2 + 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void n0(View view, int i2, int i3) {
        String str;
        String str2 = "";
        Product W = W(i2);
        if (W == null || W.jump == null) {
            return;
        }
        try {
            str = Z().get(i2).toString();
            try {
                str = com.jingdong.app.mall.home.floor.common.h.a.b(view, str);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                String str3 = str;
                com.jingdong.app.mall.home.floor.common.i.l.c(W.jump, W.getImageUrl());
                com.jingdong.app.mall.home.floor.common.i.l.e(view.getContext(), W.jump);
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(com.jingdong.app.mall.home.floor.common.i.l.a)) {
                }
                sb.append(str2);
                sb.append(i3);
                sb.append(CartConstant.KEY_YB_INFO_LINK);
                sb.append(W.getId());
                com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str3, RecommendMtaUtils.Home_PageId, null, sb.toString());
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
        }
        String str32 = str;
        com.jingdong.app.mall.home.floor.common.i.l.c(W.jump, W.getImageUrl());
        com.jingdong.app.mall.home.floor.common.i.l.e(view.getContext(), W.jump);
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(com.jingdong.app.mall.home.floor.common.i.l.a)) {
            str2 = com.jingdong.app.mall.home.floor.common.i.l.a + CartConstant.KEY_YB_INFO_LINK;
        }
        sb2.append(str2);
        sb2.append(i3);
        sb2.append(CartConstant.KEY_YB_INFO_LINK);
        sb2.append(W.getId());
        com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str32, RecommendMtaUtils.Home_PageId, null, sb2.toString());
    }

    public SkuLabel.Info R(int i2) {
        JDJSONObject T;
        if (j0() && (T = T(i2)) != null) {
            String optString = T.optString("allowancePrice", "");
            SkuLabel.Info a = SkuLabel.Info.a();
            a.l(null, 8);
            a.q(P(), 20);
            a.m(optString);
            if (TextUtils.isEmpty(Q())) {
                a.u(false);
            } else {
                a.d(Q());
            }
            return a;
        }
        return null;
    }

    public String S() {
        return ((BBannerEntity) this.d).getBgImg();
    }

    public int U() {
        return ((BBannerEntity) this.d).getCornerRadius();
    }

    public String V() {
        String showName = ((BBannerEntity) this.d).getShowName();
        return (k0() && TextUtils.isEmpty(showName)) ? "\u4eac\u4e1c\u79d2\u6740" : showName;
    }

    public String X() {
        return ((BBannerEntity) this.d).getAtmImg();
    }

    public JSONArray Z() {
        return ((BBannerEntity) this.d).getProductExpoJsonArr();
    }

    public String a0() {
        return ((BBannerEntity) this.d).getShowName();
    }

    public String b0(int i2) {
        if (k0()) {
            Product W = W(i2);
            return W != null ? W.getImageUrl() : "";
        }
        JDJSONObject T = T(i2);
        return T != null ? T.optString("img", "") : "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SkuLabel.Info c0(int i2) {
        String optString;
        String optString2;
        String str;
        String str2;
        String string = StringUtil.getString(R.string.product_entity_no_price);
        if (k0()) {
            Product W = W(i2);
            if (W == null) {
                return null;
            }
            optString = com.jingdong.app.mall.home.r.e.b.getJsonString(W.prdObject, "benefitWord", "");
            optString2 = com.jingdong.app.mall.home.r.e.b.getJsonString(W.prdObject, "finalPrice", "");
            str = W.getMiaoShaPrice();
        } else if (f0() || h0() || j0()) {
            JDJSONObject T = T(i2);
            if (T == null) {
                return null;
            }
            optString = T.optString("title", "");
            optString2 = T.optString("price", "");
            str = "";
        } else {
            str = "";
            optString = str;
            optString2 = optString;
        }
        if (TextUtils.isEmpty(optString)) {
            optString = (TextUtils.isEmpty(optString2) || TextUtils.equals(optString2, string)) ? optString2 : com.jingdong.app.mall.home.category.floor.feedssub.a.e(optString2);
        }
        if (!TextUtils.isEmpty(optString)) {
            str = optString;
        } else if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, string)) {
            str = com.jingdong.app.mall.home.category.floor.feedssub.a.e(str);
        }
        String str3 = TextUtils.isEmpty(str) ? "" : str;
        int i3 = j0() ? 28 : 30;
        boolean z = str3.startsWith(StringUtil.getString(R.string.yangjiao)) || str3.startsWith(PriceHelper.PRODUCT_PRICE_LABEL_FULL);
        if (!z || h0() || j0()) {
            str2 = null;
        } else {
            SpannableString spannableString = new SpannableString(str3);
            spannableString.setSpan(new AbsoluteSizeSpan(com.jingdong.app.mall.home.floor.common.d.d(22)), 0, 1, 17);
            spannableString.setSpan(new AbsoluteSizeSpan(com.jingdong.app.mall.home.floor.common.d.d(i3)), 1, str3.length(), 17);
            str2 = spannableString;
        }
        SkuLabel.Info a = SkuLabel.Info.a();
        a.l(null, 8);
        int[] Y = Y();
        if (!z) {
            i3 = 24;
        }
        a.q(Y, i3);
        if (str2 != null) {
            str3 = str2;
        }
        a.m(str3);
        if (!TextUtils.isEmpty(e0()) && !j0()) {
            a.d(e0());
        } else {
            a.u(false);
        }
        return a;
    }

    public com.jingdong.app.mall.home.floor.common.f d0(int i2) {
        int i3;
        if (k0() || f0()) {
            i3 = R2.anim.out_to_bottom_slow;
        } else {
            i3 = j0() ? R2.anim.mtrl_bottom_sheet_slide_in : 154;
        }
        int i4 = j0() ? 140 : R2.anim.lib_cashier_sdk_fragment_right_out;
        this.f10774i.Q(i4);
        this.f10774i.E(i3 + (i2 * i4), 0, 0, 0);
        return this.f10774i;
    }

    public boolean f0() {
        return this.f10773h == 3;
    }

    public boolean g0(int i2) {
        return k0() ? W(i2) != null : T(i2) != null;
    }

    public boolean h0() {
        return this.f10773h == 2;
    }

    public boolean i0(int i2) {
        JDJSONObject T = T(i2);
        return h0() && T != null && TextUtils.equals(T.optString("status"), "1");
    }

    public boolean j0() {
        return this.f10773h == 4;
    }

    public boolean k0() {
        return this.f10773h == 1;
    }

    public void m0(View view, int i2, int i3) {
        if (k0()) {
            n0(view, i2, i3);
        } else {
            l0(view, i2, i3);
        }
    }

    public void o0(int i2) {
        this.f10773h = i2;
        ((BBannerEntity) this.d).setFloorType(i2);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.b
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        super.v(hVar, dVar);
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null) {
            return;
        }
        if (((BBannerEntity) this.d).isValid()) {
            iMallFloorUI.onSetVisible(true);
            iMallFloorUI.onRefreshView();
            return;
        }
        iMallFloorUI.onSetVisible(false);
    }
}
