package com.jingdong.app.mall.home.n;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.adapter.CaItemAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.floor.floorsub.CaBannerIconSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaBrandSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaCouponSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaDemoSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaFlashDownSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaFlashSaleSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaHotSaleSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaIconSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaLineMoreSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaMoreIconSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaMoreSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaRankingMergeSubFloor;
import com.jingdong.app.mall.home.category.floor.floorsub.CaSelectSubFloor;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class b {
    public static final b S_BANNER_ICON;
    public static final b S_BRAND;
    public static final b S_COUPON;
    public static final b S_EMPTY_0;
    public static final b S_FLASH_DOWN;
    public static final b S_FLASH_SALE;
    public static final b S_HORIZONTAL;
    public static final b S_HOT_SALE;
    public static final b S_ICON;
    public static final b S_JUMP_MORE;
    public static final b S_LINE2;
    public static final b S_LINE4;
    public static final b S_MORE_ICON;
    public static final b S_NORMAL_SALE;
    public static final b S_ONE_1;
    public static final b S_RANKING_MERGE;
    public static final b S_SELECT;
    public static final b S_TWO_2;

    /* renamed from: g */
    private static final /* synthetic */ b[] f10325g;
    private int mFloorHeight;
    private int mFloorIntType;
    private String[] mFloorStrType;
    private int mFloorWidth;
    private int mSpanSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum j extends b {
        j(String str, int i2, int i3, String... strArr) {
            super(str, i2, i3, strArr, (j) null);
        }

        @Override // com.jingdong.app.mall.home.n.b
        public BaseCaRecycleItem createFloorView(Context context) {
            return new CaDemoSubFloor(context);
        }

        @Override // com.jingdong.app.mall.home.n.b
        public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
            return new com.jingdong.app.mall.home.n.g.x.d(jDJSONObject, this);
        }
    }

    static {
        j jVar = new j("S_EMPTY_0", 0, 0, "S_EMPTY_0");
        S_EMPTY_0 = jVar;
        b bVar = new b("S_JUMP_MORE", 1, 0, -2, "S_JUMP_MORE") { // from class: com.jingdong.app.mall.home.n.b.k
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaMoreSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.l(jDJSONObject, this);
            }
        };
        S_JUMP_MORE = bVar;
        b bVar2 = new b("S_SELECT", 2, 0, 124, -1, "S_SELECT") { // from class: com.jingdong.app.mall.home.n.b.l
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaSelectSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.n(jDJSONObject, this);
            }
        };
        S_SELECT = bVar2;
        b bVar3 = new b("S_MORE_ICON", 3, 4, -1, 204, "S_MORE_ICON") { // from class: com.jingdong.app.mall.home.n.b.m
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaMoreIconSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.j(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public int getItemSpanSize() {
                return 1;
            }
        };
        S_MORE_ICON = bVar3;
        b bVar4 = new b("S_ICON", 4, 5, -1, R2.anim.pop_left_top_out, "S_ICON") { // from class: com.jingdong.app.mall.home.n.b.n
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaIconSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.h(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public int getItemSpanSize() {
                return 1;
            }
        };
        S_ICON = bVar4;
        b bVar5 = new b("S_BRAND", 5, 4, -1, 110, "S_BRAND") { // from class: com.jingdong.app.mall.home.n.b.o
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaBrandSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.b(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public int getItemSpanSize() {
                return 1;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public void parseFloorInfo(@NonNull com.jingdong.app.mall.home.n.g.u.e eVar, int i2) {
                super.parseFloorInfo(eVar, i2);
                com.jingdong.app.mall.home.o.a.f.n(eVar);
                if ("0".equals(((com.jingdong.app.mall.home.n.g.x.b) eVar).u())) {
                    eVar.q(66);
                }
            }
        };
        S_BRAND = bVar5;
        b bVar6 = new b("S_RANKING_MERGE", 6, 3, -1, -1, "S_RANKING_MERGE") { // from class: com.jingdong.app.mall.home.n.b.p
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaRankingMergeSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.m(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public int getItemSpanSize() {
                return 1;
            }
        };
        S_RANKING_MERGE = bVar6;
        b bVar7 = new b("S_FLASH_SALE", 7, 0, R2.anim.slide_out_to_right, R2.attr.actionMenuTextColor, "S_FLASH_SALE") { // from class: com.jingdong.app.mall.home.n.b.q
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected BaseCaRecycleItem createFloorView(Context context) {
                return new CaFlashSaleSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.f(jDJSONObject, this);
            }
        };
        S_FLASH_SALE = bVar7;
        b bVar8 = new b("S_FLASH_DOWN", 8, 0, R2.attr.blendSrc, R2.attr.amountMinSize, "S_FLASH_DOWN") { // from class: com.jingdong.app.mall.home.n.b.r
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected BaseCaRecycleItem createFloorView(Context context) {
                return new CaFlashDownSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.e(jDJSONObject, this);
            }
        };
        S_FLASH_DOWN = bVar8;
        b bVar9 = new b("S_COUPON", 9, 0, -2, R2.anim.pop_left_top_out, "S_COUPON") { // from class: com.jingdong.app.mall.home.n.b.a
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected BaseCaRecycleItem createFloorView(Context context) {
                return new CaCouponSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.c(jDJSONObject, this);
            }
        };
        S_COUPON = bVar9;
        b bVar10 = new b("S_HOT_SALE", 10, 0, 280, -1, "S_HOT_SALE") { // from class: com.jingdong.app.mall.home.n.b.b
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected BaseCaRecycleItem createFloorView(Context context) {
                return new CaHotSaleSubFloor(context, b.S_HOT_SALE);
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.g(jDJSONObject, this);
            }
        };
        S_HOT_SALE = bVar10;
        b bVar11 = new b("S_NORMAL_SALE", 11, 0, 280, -1, "S_NORMAL_SALE") { // from class: com.jingdong.app.mall.home.n.b.c
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected BaseCaRecycleItem createFloorView(Context context) {
                return new CaHotSaleSubFloor(context, b.S_NORMAL_SALE);
            }

            @Override // com.jingdong.app.mall.home.n.b
            protected com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.g(jDJSONObject, this);
            }
        };
        S_NORMAL_SALE = bVar11;
        b bVar12 = new b("S_LINE4", 12, 4, -1, 240, "S_LINE4") { // from class: com.jingdong.app.mall.home.n.b.d
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                com.jingdong.app.mall.home.n.g.w.c cVar = new com.jingdong.app.mall.home.n.g.w.c();
                cVar.m(-2, 56, new Rect(0, R2.anim.settlement_dialog_right_enter, 0, 0));
                cVar.k(-1);
                cVar.i(150);
                cVar.l(28);
                cVar.j();
                com.jingdong.app.mall.home.n.g.w.c cVar2 = new com.jingdong.app.mall.home.n.g.w.c();
                cVar2.m(-2, 34, new Rect(0, 154, 0, 0));
                cVar2.k(-1);
                cVar2.h(false);
                cVar2.i(140);
                cVar2.l(22);
                com.jingdong.app.mall.home.n.g.w.a aVar = new com.jingdong.app.mall.home.n.g.w.a();
                aVar.o(cVar);
                aVar.n(cVar2);
                aVar.m(22);
                aVar.j(R2.anim.pop_left_top_out, -1, 24, null);
                aVar.k(R2.anim.out_to_bottom_slow, R2.anim.out_to_bottom_slow, new Rect(0, 6, 0, 0));
                aVar.p(162, 162, 22, new Rect(0, 3, 0, 0));
                return new CaLineMoreSubFloor(context, aVar);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.i(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public int getItemSpanSize() {
                return 1;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public void parseFloorInfo(@NonNull com.jingdong.app.mall.home.n.g.u.e eVar, int i2) {
                super.parseFloorInfo(eVar, i2);
                if ((eVar instanceof com.jingdong.app.mall.home.n.g.x.i) && ((com.jingdong.app.mall.home.n.g.x.i) eVar).x() == 1) {
                    setFloorHeight(eVar.g().getJsonInt("height", 240));
                }
            }
        };
        S_LINE4 = bVar12;
        b bVar13 = new b("S_LINE2", 13, 2, -1, 220, "S_LINE2") { // from class: com.jingdong.app.mall.home.n.b.e
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                com.jingdong.app.mall.home.n.g.w.c cVar = new com.jingdong.app.mall.home.n.g.w.c();
                cVar.m(-2, 52, new Rect(0, R2.anim.pop_left_top_out, 0, 0));
                cVar.k(-1);
                cVar.i(200);
                cVar.l(32);
                cVar.j();
                com.jingdong.app.mall.home.n.g.w.c cVar2 = new com.jingdong.app.mall.home.n.g.w.c();
                cVar2.m(-2, 30, null);
                cVar2.k(-1);
                cVar2.i(180);
                cVar2.l(24);
                cVar2.h(false);
                cVar2.j();
                com.jingdong.app.mall.home.n.g.w.a aVar = new com.jingdong.app.mall.home.n.g.w.a();
                aVar.o(cVar);
                aVar.n(cVar2);
                aVar.m(11);
                aVar.j(R2.attr.actionModeShareDrawable, -1, 24, null);
                aVar.k(140, 140, new Rect(35, 32, 0, 0));
                aVar.l(140, 140, new Rect(R2.anim.slide_in_from_bottom, 32, 0, 0));
                aVar.p(324, R2.anim.popwin_anim_alpha_out, 18, new Rect(20, 15, 0, 0));
                return new CaLineMoreSubFloor(context, aVar);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.i(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public void parseFloorInfo(@NonNull com.jingdong.app.mall.home.n.g.u.e eVar, int i2) {
                super.parseFloorInfo(eVar, i2);
                if ((eVar instanceof com.jingdong.app.mall.home.n.g.x.i) && ((com.jingdong.app.mall.home.n.g.x.i) eVar).x() == 1) {
                    setFloorHeight(eVar.g().getJsonInt("height", 220));
                }
            }
        };
        S_LINE2 = bVar13;
        b bVar14 = new b("S_BANNER_ICON", 14, 0, 170, 208, "S_BANNER_ICON") { // from class: com.jingdong.app.mall.home.n.b.f
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaBannerIconSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.a(jDJSONObject, this);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public void parseFloorInfo(@NonNull com.jingdong.app.mall.home.n.g.u.e eVar, int i2) {
                JDJSONArray C;
                super.parseFloorInfo(eVar, i2);
                com.jingdong.app.mall.home.n.g.u.c g2 = eVar.g();
                if ((g2 instanceof com.jingdong.app.mall.home.n.g.a) && (C = ((com.jingdong.app.mall.home.n.g.a) g2).C()) != null) {
                    setItemWidth(C.size() == 4 ? 170 : R2.anim.lib_cashier_sdk_fragment_right_out);
                }
            }
        };
        S_BANNER_ICON = bVar14;
        b bVar15 = new b("S_HORIZONTAL", 15, 0, 240, "S_HORIZONTAL") { // from class: com.jingdong.app.mall.home.n.b.g
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaDemoSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.d(jDJSONObject, this);
            }
        };
        S_HORIZONTAL = bVar15;
        b bVar16 = new b("S_ONE_1", 16, 1, "S_ONE_1") { // from class: com.jingdong.app.mall.home.n.b.h
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaDemoSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.d(jDJSONObject, this);
            }
        };
        S_ONE_1 = bVar16;
        b bVar17 = new b("S_TWO_2", 17, 2, "S_TWO_2") { // from class: com.jingdong.app.mall.home.n.b.i
            {
                j jVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.n.b
            public BaseCaRecycleItem createFloorView(Context context) {
                return new CaDemoSubFloor(context);
            }

            @Override // com.jingdong.app.mall.home.n.b
            public com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject) {
                return new com.jingdong.app.mall.home.n.g.x.d(jDJSONObject, this);
            }
        };
        S_TWO_2 = bVar17;
        f10325g = new b[]{jVar, bVar, bVar2, bVar3, bVar4, bVar5, bVar6, bVar7, bVar8, bVar9, bVar10, bVar11, bVar12, bVar13, bVar14, bVar15, bVar16, bVar17};
    }

    /* synthetic */ b(String str, int i2, int i3, int i4, int i5, String[] strArr, j jVar) {
        this(str, i2, i3, i4, i5, strArr);
    }

    private static int a() {
        int i2 = CaItemAdapter.f8641l;
        CaItemAdapter.f8641l = i2 + 1;
        return i2;
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f10325g.clone();
    }

    protected abstract BaseCaRecycleItem createFloorView(Context context);

    protected abstract com.jingdong.app.mall.home.n.g.u.e createTypeModel(JDJSONObject jDJSONObject);

    public int getFloorHeight() {
        int i2 = this.mFloorHeight;
        return i2 < 0 ? i2 : com.jingdong.app.mall.home.floor.common.d.d(i2);
    }

    public int getFloorIntType() {
        return this.mFloorIntType;
    }

    public BaseCaRecycleItem getFloorView(Context context) {
        return createFloorView(context);
    }

    public int getFloorWidth() {
        int i2 = this.mFloorWidth;
        return i2 < 0 ? i2 : com.jingdong.app.mall.home.floor.common.d.d(i2);
    }

    public int getItemSpanSize() {
        return this.mSpanSize;
    }

    public int getSpanSize() {
        return this.mSpanSize;
    }

    public com.jingdong.app.mall.home.n.g.u.e getTypeModel(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.g.u.c cVar, int i2) {
        com.jingdong.app.mall.home.n.g.u.e createTypeModel = createTypeModel(jDJSONObject);
        createTypeModel.s(cVar);
        parseFloorInfo(createTypeModel, i2);
        return createTypeModel;
    }

    public void parseFloorInfo(@NonNull com.jingdong.app.mall.home.n.g.u.e eVar, int i2) {
        eVar.o(i2);
    }

    public final void parseFloorType(Map<String, b> map, SparseArray<b> sparseArray) {
        for (String str : this.mFloorStrType) {
            if (map.containsKey(str)) {
                com.jingdong.app.mall.home.o.a.f.o("Error has same type : ", str);
                return;
            }
            map.put(str, this);
        }
        sparseArray.put(this.mFloorIntType, this);
    }

    public void setFloorHeight(int i2) {
        this.mFloorHeight = i2;
    }

    public void setItemWidth(int i2) {
        this.mFloorWidth = i2;
    }

    /* synthetic */ b(String str, int i2, int i3, int i4, String[] strArr, j jVar) {
        this(str, i2, i3, i4, strArr);
    }

    /* synthetic */ b(String str, int i2, int i3, String[] strArr, j jVar) {
        this(str, i2, i3, strArr);
    }

    private b(String str, int i2, int i3, String... strArr) {
        this(str, i2, i3, -1, -1, strArr);
    }

    private b(String str, @IntRange(from = -2, to = 2147483647L) int i2, int i3, int i4, String... strArr) {
        this(str, i2, i3, i4, -1, strArr);
    }

    private b(String str, @IntRange(from = -2, to = 2147483647L) int i2, @IntRange(from = -2, to = 2147483647L) int i3, int i4, int i5, String... strArr) {
        super(str, i2);
        this.mSpanSize = i3;
        this.mFloorWidth = i4;
        this.mFloorHeight = i5;
        this.mFloorIntType = a();
        this.mFloorStrType = strArr;
    }
}
