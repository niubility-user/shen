package com.jingdong.app.mall.home.n;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.CaBannerCardFloor;
import com.jingdong.app.mall.home.category.floor.CaBannerIconFloor;
import com.jingdong.app.mall.home.category.floor.CaBrandFloor;
import com.jingdong.app.mall.home.category.floor.CaCouponFloor;
import com.jingdong.app.mall.home.category.floor.CaDividerFloor;
import com.jingdong.app.mall.home.category.floor.CaEmptyFloor;
import com.jingdong.app.mall.home.category.floor.CaFeedsRankFloor;
import com.jingdong.app.mall.home.category.floor.CaFeedsSkuFloor;
import com.jingdong.app.mall.home.category.floor.CaFlashDownFloor;
import com.jingdong.app.mall.home.category.floor.CaFlashSaleFloor;
import com.jingdong.app.mall.home.category.floor.CaFootFloor;
import com.jingdong.app.mall.home.category.floor.CaHorizontalLinearFloor;
import com.jingdong.app.mall.home.category.floor.CaHotSaleFloor;
import com.jingdong.app.mall.home.category.floor.CaIconFloor;
import com.jingdong.app.mall.home.category.floor.CaLoadingFloor;
import com.jingdong.app.mall.home.category.floor.CaMoreIconFloor;
import com.jingdong.app.mall.home.category.floor.CaRecycleGridFloor;
import com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor;
import com.jingdong.app.mall.home.category.floor.CaSelectFloor;
import com.jingdong.app.mall.home.category.floor.CaTitleFloor;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class a {
    public static final a C_BANNER_CARD;
    public static final a C_BANNER_ICON;
    public static final a C_BRAND;
    public static final a C_COUPON;
    public static final a C_DIVIDER;
    public static final a C_EMPTY;
    public static final a C_FEEDS_RANK;
    public static final a C_FEEDS_SKU;
    public static final a C_FLASH_DOWN;
    public static final a C_FLASH_SALE;
    public static final a C_HORIZONTAL;
    public static final a C_HOT_SALE;
    public static final a C_ICON;
    public static final a C_LINE_MORE_111;
    public static final a C_LOADING;
    public static final a C_Line2_FLOOR;
    public static final a C_Line4_FLOOR;
    public static final a C_MORE_FOOT;
    public static final a C_MORE_ICON;
    public static final a C_NORMAL_SALE;
    public static final a C_RANKING2;
    public static final a C_SELECT;
    public static final a C_TITLE;

    /* renamed from: g */
    private static final /* synthetic */ a[] f10324g;
    private boolean fullSpan;
    protected com.jingdong.app.mall.home.n.g.w.b lineMoreInfo;
    protected int mFloorHeight;
    private int mFloorIntType;
    private String mFloorStrType;
    protected com.jingdong.app.mall.home.category.floor.base.b mLastView;
    protected com.jingdong.app.mall.home.n.b[] mSubTypes;

    /* loaded from: classes4.dex */
    public enum k extends a {
        k(String str, int i2, int i3, String str2) {
            super(str, i2, i3, str2, (k) null);
        }

        @Override // com.jingdong.app.mall.home.n.a
        public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
            return new CaEmptyFloor(context, caAdapter);
        }

        @Override // com.jingdong.app.mall.home.n.a
        public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
            return new com.jingdong.app.mall.home.n.g.f(jDJSONObject, this);
        }
    }

    static {
        k kVar = new k("C_EMPTY", 0, 0, "C_EMPTY");
        C_EMPTY = kVar;
        a aVar = new a("C_LOADING", 1, 100, "C_LOADING") { // from class: com.jingdong.app.mall.home.n.a.p
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                if (this.mLastView == null) {
                    this.mLastView = new CaLoadingFloor(context, caAdapter);
                }
                return this.mLastView;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.n(jDJSONObject, this);
            }
        };
        C_LOADING = aVar;
        a aVar2 = new a("C_DIVIDER", 2, 24, "C_DIVIDER") { // from class: com.jingdong.app.mall.home.n.a.q
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaDividerFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.e(jDJSONObject, this);
            }
        };
        C_DIVIDER = aVar2;
        a aVar3 = new a("C_MORE_FOOT", 3, R2.anim.slide_out_to_top, "C_MORE_FOOT") { // from class: com.jingdong.app.mall.home.n.a.r
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaFootFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.j(jDJSONObject, this);
            }
        };
        C_MORE_FOOT = aVar3;
        a aVar4 = new a("C_TITLE", 4, 70, true, "C_TITLE") { // from class: com.jingdong.app.mall.home.n.a.s
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected void b(@NonNull com.jingdong.app.mall.home.n.g.u.c cVar) {
                cVar.t("0906");
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaTitleFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.t(jDJSONObject, this);
            }
        };
        C_TITLE = aVar4;
        a aVar5 = new a("C_MORE_ICON", 5, 204, "C_MORE_ICON") { // from class: com.jingdong.app.mall.home.n.a.t
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaMoreIconFloor(context, caAdapter, this.mSubTypes, com.jingdong.app.mall.home.n.g.o.C);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_MORE_ICON};
                return new com.jingdong.app.mall.home.n.g.o(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_MORE_ICON = aVar5;
        a aVar6 = new a("C_ICON", 6, R2.attr.adSize, "0901") { // from class: com.jingdong.app.mall.home.n.a.u
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaIconFloor(context, caAdapter, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_ICON};
                return new com.jingdong.app.mall.home.n.g.l(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_ICON = aVar6;
        a aVar7 = new a("C_FLASH_SALE", 7, R2.attr.actionMenuTextColor, "0902") { // from class: com.jingdong.app.mall.home.n.a.v
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaFlashSaleFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_FLASH_SALE};
                return new com.jingdong.app.mall.home.n.g.i(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_FLASH_SALE = aVar7;
        a aVar8 = new a("C_BRAND", 8, 256, "0903") { // from class: com.jingdong.app.mall.home.n.a.w
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaBrandFloor(context, caAdapter, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_BRAND};
                return new com.jingdong.app.mall.home.n.g.b(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_BRAND = aVar8;
        a aVar9 = new a("C_SELECT", 9, 96, "0906") { // from class: com.jingdong.app.mall.home.n.a.a
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean checkDarkTitle() {
                return true;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaSelectFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.s(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar10) {
                return i2 != 0;
            }
        };
        C_SELECT = aVar9;
        a aVar10 = new a("C_FLASH_DOWN", 10, R2.attr.app_actionbar_color, "0907") { // from class: com.jingdong.app.mall.home.n.a.b
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean checkDarkTitle() {
                return true;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaFlashDownFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_FLASH_DOWN};
                return new com.jingdong.app.mall.home.n.g.h(jDJSONObject, this, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar11) {
                return false;
            }
        };
        C_FLASH_DOWN = aVar10;
        a aVar11 = new a("C_BANNER_CARD", 11, 0, "0908") { // from class: com.jingdong.app.mall.home.n.a.c
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaBannerCardFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.c(jDJSONObject, this);
            }
        };
        C_BANNER_CARD = aVar11;
        a aVar12 = new a("C_COUPON", 12, R2.anim.pop_left_top_out, "0909") { // from class: com.jingdong.app.mall.home.n.a.d
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean c() {
                return false;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaCouponFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_COUPON};
                return new com.jingdong.app.mall.home.n.g.d(jDJSONObject, this, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar13) {
                return false;
            }
        };
        C_COUPON = aVar12;
        a aVar13 = new a("C_RANKING2", 13, 276, "0910") { // from class: com.jingdong.app.mall.home.n.a.e
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean checkDarkTitle() {
                return true;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaHorizontalLinearFloor(context, caAdapter, this.mSubTypes, com.jingdong.app.mall.home.n.g.p.A);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_RANKING_MERGE};
                return new com.jingdong.app.mall.home.n.g.p(jDJSONObject, this, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar14) {
                return false;
            }
        };
        C_RANKING2 = aVar13;
        a aVar14 = new a("C_Line2_FLOOR", 14, 220, "0911") { // from class: com.jingdong.app.mall.home.n.a.f
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaHorizontalLinearFloor(context, caAdapter, this.mSubTypes, this.lineMoreInfo);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                com.jingdong.app.mall.home.n.g.w.b bVar = new com.jingdong.app.mall.home.n.g.w.b();
                this.lineMoreInfo = bVar;
                bVar.f("Category_Main_Activity2_Expo");
                this.lineMoreInfo.e("Category_Main_Activity2_Detail");
                this.lineMoreInfo.g(new Rect(13, 10, 13, 0));
                com.jingdong.app.mall.home.n.b bVar2 = com.jingdong.app.mall.home.n.b.S_LINE2;
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{bVar2, bVar2};
                this.mFloorHeight = com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "height", 220);
                return new com.jingdong.app.mall.home.n.g.m(jDJSONObject, this, this.mSubTypes, this.lineMoreInfo);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar15) {
                return false;
            }
        };
        C_Line2_FLOOR = aVar14;
        a aVar15 = new a("C_Line4_FLOOR", 15, 240, "0912") { // from class: com.jingdong.app.mall.home.n.a.g
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaHorizontalLinearFloor(context, caAdapter, this.mSubTypes, this.lineMoreInfo);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                com.jingdong.app.mall.home.n.g.w.b bVar = new com.jingdong.app.mall.home.n.g.w.b();
                this.lineMoreInfo = bVar;
                bVar.f("Category_Main_Activity4_Expo");
                this.lineMoreInfo.e("Category_Main_Activity4_Detail");
                this.lineMoreInfo.g(new Rect(19, 10, 19, 0));
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_LINE4};
                this.mFloorHeight = com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "height", 240);
                return new com.jingdong.app.mall.home.n.g.m(jDJSONObject, this, this.mSubTypes, this.lineMoreInfo);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar16) {
                return false;
            }
        };
        C_Line4_FLOOR = aVar15;
        a aVar16 = new a("C_HOT_SALE", 16, R2.attr.behavior_autoHide, "0913") { // from class: com.jingdong.app.mall.home.n.a.h
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean checkDarkTitle() {
                return true;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaHotSaleFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_HOT_SALE};
                return new com.jingdong.app.mall.home.n.g.k(jDJSONObject, this, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar17) {
                return false;
            }
        };
        C_HOT_SALE = aVar16;
        a aVar17 = new a("C_NORMAL_SALE", 17, 402, "0_0") { // from class: com.jingdong.app.mall.home.n.a.i
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaHotSaleFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_NORMAL_SALE};
                return new com.jingdong.app.mall.home.n.g.k(jDJSONObject, this, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public boolean useTopDivider(int i2, a aVar18) {
                return false;
            }
        };
        C_NORMAL_SALE = aVar17;
        a aVar18 = new a("C_BANNER_ICON", 18, 208, "0914") { // from class: com.jingdong.app.mall.home.n.a.j
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaBannerIconFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            protected com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_BANNER_ICON};
                return new com.jingdong.app.mall.home.n.g.a(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_BANNER_ICON = aVar18;
        a aVar19 = new a("C_FEEDS_SKU", 19, -2, false, "0") { // from class: com.jingdong.app.mall.home.n.a.l
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaFeedsSkuFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.g(jDJSONObject, this);
            }
        };
        C_FEEDS_SKU = aVar19;
        a aVar20 = new a("C_FEEDS_RANK", 20, -2, false, "2") { // from class: com.jingdong.app.mall.home.n.a.m
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaFeedsRankFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.g(jDJSONObject, this);
            }
        };
        C_FEEDS_RANK = aVar20;
        a aVar21 = new a("C_HORIZONTAL", 21, 200, "C_HORIZONTAL") { // from class: com.jingdong.app.mall.home.n.a.n
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaRecycleLinearFloor(context, caAdapter);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{com.jingdong.app.mall.home.n.b.S_HORIZONTAL};
                return new com.jingdong.app.mall.home.n.g.r(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_HORIZONTAL = aVar21;
        a aVar22 = new a("C_LINE_MORE_111", 22, 240, "C_LINE_MORE_111") { // from class: com.jingdong.app.mall.home.n.a.o
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter) {
                return new CaRecycleGridFloor(context, caAdapter, this.mSubTypes);
            }

            @Override // com.jingdong.app.mall.home.n.a
            public com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject) {
                com.jingdong.app.mall.home.n.b bVar = com.jingdong.app.mall.home.n.b.S_ONE_1;
                this.mSubTypes = new com.jingdong.app.mall.home.n.b[]{bVar, bVar, bVar};
                return new com.jingdong.app.mall.home.n.g.q(jDJSONObject, this, this.mSubTypes);
            }
        };
        C_LINE_MORE_111 = aVar22;
        f10324g = new a[]{kVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21, aVar22};
    }

    /* synthetic */ a(String str, int i2, int i3, String str2, k kVar) {
        this(str, i2, i3, str2);
    }

    private static int a() {
        int i2 = CaAdapter.p;
        CaAdapter.p = i2 + 1;
        return i2;
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f10324g.clone();
    }

    protected void b(@NonNull com.jingdong.app.mall.home.n.g.u.c cVar) {
        cVar.t(this.mFloorStrType);
    }

    public boolean c() {
        return true;
    }

    public boolean checkDarkTitle() {
        return false;
    }

    protected abstract com.jingdong.app.mall.home.category.floor.base.b createFloorView(Context context, CaAdapter caAdapter);

    protected abstract com.jingdong.app.mall.home.n.g.u.c createTypeModel(JDJSONObject jDJSONObject);

    public int getFloorHeight() {
        return com.jingdong.app.mall.home.floor.common.d.d(this.mFloorHeight);
    }

    public int getFloorIntType() {
        return this.mFloorIntType;
    }

    public com.jingdong.app.mall.home.category.floor.base.b getFloorView(Context context, CaAdapter caAdapter) {
        return createFloorView(context, caAdapter);
    }

    public com.jingdong.app.mall.home.category.floor.base.b getLastView() {
        return this.mLastView;
    }

    public com.jingdong.app.mall.home.n.g.u.c getTypeModel(JDJSONObject jDJSONObject, CategoryEntity.CaItem caItem, long j2, int i2) {
        com.jingdong.app.mall.home.n.g.u.c createTypeModel = createTypeModel(jDJSONObject);
        b(createTypeModel);
        createTypeModel.y(i2);
        createTypeModel.u(j2);
        parseFloorInfo(caItem, createTypeModel);
        return createTypeModel;
    }

    public boolean isFloorType() {
        int i2 = this.mFloorHeight;
        return i2 > 30 || i2 < 0;
    }

    public boolean isFullSpan() {
        return this.fullSpan;
    }

    public void parseFloorInfo(CategoryEntity.CaItem caItem, @NonNull com.jingdong.app.mall.home.n.g.u.c cVar) {
        cVar.p(caItem);
    }

    public final void parseFloorType(Map<String, a> map, SparseArray<a> sparseArray) {
        if (map.containsKey(this.mFloorStrType)) {
            com.jingdong.app.mall.home.o.a.f.o("Error has same type : ", this.mFloorStrType);
            return;
        }
        map.put(this.mFloorStrType, this);
        sparseArray.put(this.mFloorIntType, this);
    }

    public boolean useTopDivider(int i2, a aVar) {
        return aVar == null || aVar.c();
    }

    /* synthetic */ a(String str, int i2, int i3, boolean z, String str2, k kVar) {
        this(str, i2, i3, z, str2);
    }

    private a(@IntRange(from = -2, to = 2147483647L) String str, int i2, int i3, boolean z, String str2) {
        super(str, i2);
        this.mFloorHeight = i3;
        this.mFloorIntType = a();
        this.fullSpan = z;
        this.mFloorStrType = str2;
    }

    private a(@IntRange(from = -2, to = 2147483647L) String str, int i2, int i3, String str2) {
        this(str, i2, i3, true, str2);
    }
}
