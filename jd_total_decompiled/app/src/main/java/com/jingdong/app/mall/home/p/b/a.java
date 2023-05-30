package com.jingdong.app.mall.home.p.b;

import android.content.Context;
import com.jingdong.app.mall.home.deploy.view.layout.banner2x4.DBanner2x4Parser;
import com.jingdong.app.mall.home.deploy.view.layout.core1x2.DCore1x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.core1x2l.DCore1x2LParser;
import com.jingdong.app.mall.home.deploy.view.layout.core1x2r.DCore1x2RParser;
import com.jingdong.app.mall.home.deploy.view.layout.core2x2.DCore2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.coreimg.DCoreImgParser;
import com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.DCoreLive2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.empty.DEmptyParser;
import com.jingdong.app.mall.home.deploy.view.layout.newcomer2x4.DNewcomer2x4Parser;
import com.jingdong.app.mall.home.deploy.view.layout.rlayout.DReLayoutParser;
import com.jingdong.app.mall.home.deploy.view.layout.sale1x2.DSale1x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.sale2x2.DSale2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.seckill2x2.DSeckill2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.seckill2x4.DSeckill2x4Parser;
import com.jingdong.app.mall.home.deploy.view.layout.seckillNewcomer2x4.DSeckillNewcomer2x4Parser;
import com.jingdong.app.mall.home.deploy.view.layout.year1x2.DYear1x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.year2x2.DYear2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.year2x2_1.DYear2x2_1Parser;
import com.jingdong.app.mall.home.deploy.view.layout.year2x4.DYear2x4Parser;
import com.jingdong.app.mall.home.deploy.view.layout.yearBanner2x2.DCoreBanner2x2Parser;
import com.jingdong.app.mall.home.deploy.view.layout.yearCoupon2x4.DYearCoupon2x4Parser;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class a implements com.jingdong.app.mall.home.p.b.b {
    public static final a BANNER_2X4;
    public static final a CORE_1X2;
    public static final a CORE_1X2L;
    public static final a CORE_1X2R;
    public static final a CORE_2X2;
    public static final a CORE_BANNER_2X2;
    public static final a CORE_IMG_2X2;
    public static final a CORE_LIVE_2X2;
    public static final a EMPTY;
    public static final a NEWCOMER_2x4;
    public static final a R_IMAGE;
    public static final a R_LAYOUT;
    public static final a R_TEXT;
    public static final a SALE_1X2;
    public static final a SALE_2X2;
    public static final a SECKILL_2X2;
    public static final a SECKILL_2X4;
    public static final a SECKILL_NEW_2X4;
    public static final a YEAR_1X2;
    public static final a YEAR_2X2;
    public static final a YEAR_2X2_1;
    public static final a YEAR_2X4;
    public static final a YEAR_COUPON_2X4;

    /* renamed from: g */
    private static final /* synthetic */ a[] f10503g;
    String[] mNodeName;

    /* loaded from: classes4.dex */
    public enum k extends a {
        k(String str, int i2, String... strArr) {
            super(str, i2, strArr, null);
        }

        @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
        public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
            return new DEmptyParser(context);
        }
    }

    static {
        k kVar = new k("EMPTY", 0, "empty");
        EMPTY = kVar;
        a aVar = new a("BANNER_2X4", 1, "banner2x4") { // from class: com.jingdong.app.mall.home.p.b.a.p
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DBanner2x4Parser(context);
            }
        };
        BANNER_2X4 = aVar;
        a aVar2 = new a("SECKILL_2X2", 2, "seckill2x2") { // from class: com.jingdong.app.mall.home.p.b.a.q
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DSeckill2x2Parser(context);
            }
        };
        SECKILL_2X2 = aVar2;
        a aVar3 = new a("SECKILL_2X4", 3, "seckill2x4") { // from class: com.jingdong.app.mall.home.p.b.a.r
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DSeckill2x4Parser(context);
            }
        };
        SECKILL_2X4 = aVar3;
        a aVar4 = new a("SECKILL_NEW_2X4", 4, "seckillNew2x4") { // from class: com.jingdong.app.mall.home.p.b.a.s
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DSeckillNewcomer2x4Parser(context);
            }
        };
        SECKILL_NEW_2X4 = aVar4;
        a aVar5 = new a("CORE_2X2", 5, "core2x2") { // from class: com.jingdong.app.mall.home.p.b.a.t
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCore2x2Parser(context);
            }
        };
        CORE_2X2 = aVar5;
        a aVar6 = new a("CORE_BANNER_2X2", 6, "coreBanner2x2") { // from class: com.jingdong.app.mall.home.p.b.a.u
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCoreBanner2x2Parser(context);
            }
        };
        CORE_BANNER_2X2 = aVar6;
        a aVar7 = new a("CORE_IMG_2X2", 7, "fullImg") { // from class: com.jingdong.app.mall.home.p.b.a.v
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCoreImgParser(context);
            }
        };
        CORE_IMG_2X2 = aVar7;
        a aVar8 = new a("CORE_LIVE_2X2", 8, "coreLive2x2") { // from class: com.jingdong.app.mall.home.p.b.a.w
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCoreLive2x2Parser(context);
            }
        };
        CORE_LIVE_2X2 = aVar8;
        a aVar9 = new a("CORE_1X2", 9, "core1x2") { // from class: com.jingdong.app.mall.home.p.b.a.a
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCore1x2Parser(context);
            }
        };
        CORE_1X2 = aVar9;
        a aVar10 = new a("CORE_1X2L", 10, "core1x2L") { // from class: com.jingdong.app.mall.home.p.b.a.b
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCore1x2LParser(context);
            }
        };
        CORE_1X2L = aVar10;
        a aVar11 = new a("CORE_1X2R", 11, "core1x2R") { // from class: com.jingdong.app.mall.home.p.b.a.c
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DCore1x2RParser(context);
            }
        };
        CORE_1X2R = aVar11;
        a aVar12 = new a("SALE_1X2", 12, "sale1x2") { // from class: com.jingdong.app.mall.home.p.b.a.d
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DSale1x2Parser(context);
            }
        };
        SALE_1X2 = aVar12;
        a aVar13 = new a("SALE_2X2", 13, "sale2x2") { // from class: com.jingdong.app.mall.home.p.b.a.e
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DSale2x2Parser(context);
            }
        };
        SALE_2X2 = aVar13;
        a aVar14 = new a("YEAR_1X2", 14, "year1x2") { // from class: com.jingdong.app.mall.home.p.b.a.f
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DYear1x2Parser(context);
            }
        };
        YEAR_1X2 = aVar14;
        a aVar15 = new a("YEAR_2X2", 15, "year2x2") { // from class: com.jingdong.app.mall.home.p.b.a.g
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DYear2x2Parser(context);
            }
        };
        YEAR_2X2 = aVar15;
        a aVar16 = new a("YEAR_2X2_1", 16, "year2x2_1") { // from class: com.jingdong.app.mall.home.p.b.a.h
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DYear2x2_1Parser(context);
            }
        };
        YEAR_2X2_1 = aVar16;
        a aVar17 = new a("YEAR_2X4", 17, "sSale2x4") { // from class: com.jingdong.app.mall.home.p.b.a.i
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DYear2x4Parser(context);
            }
        };
        YEAR_2X4 = aVar17;
        a aVar18 = new a("YEAR_COUPON_2X4", 18, "cSale2x4") { // from class: com.jingdong.app.mall.home.p.b.a.j
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DYearCoupon2x4Parser(context);
            }
        };
        YEAR_COUPON_2X4 = aVar18;
        a aVar19 = new a("NEWCOMER_2x4", 19, "new2x4") { // from class: com.jingdong.app.mall.home.p.b.a.l
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DNewcomer2x4Parser(context);
            }
        };
        NEWCOMER_2x4 = aVar19;
        a aVar20 = new a("R_LAYOUT", 20, "rLayout") { // from class: com.jingdong.app.mall.home.p.b.a.m
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new DReLayoutParser(context);
            }
        };
        R_LAYOUT = aVar20;
        a aVar21 = new a("R_TEXT", 21, "wText") { // from class: com.jingdong.app.mall.home.p.b.a.n
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new com.jingdong.app.mall.home.deploy.view.widget.text.a(context);
            }
        };
        R_TEXT = aVar21;
        a aVar22 = new a("R_IMAGE", 22, "wImg") { // from class: com.jingdong.app.mall.home.p.b.a.o
            {
                k kVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.p.b.a, com.jingdong.app.mall.home.p.b.b
            public com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context) {
                return new com.jingdong.app.mall.home.deploy.view.widget.image.a(context);
            }
        };
        R_IMAGE = aVar22;
        f10503g = new a[]{kVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21, aVar22};
    }

    /* synthetic */ a(String str, int i2, String[] strArr, k kVar) {
        this(str, i2, strArr);
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f10503g.clone();
    }

    @Override // com.jingdong.app.mall.home.p.b.b
    public abstract com.jingdong.app.mall.home.deploy.view.base.b crateParser(Context context);

    public String getNodeName() {
        return getNodeName(0);
    }

    public void initNodeMap(Map<String, com.jingdong.app.mall.home.p.b.b> map) {
        for (String str : this.mNodeName) {
            com.jingdong.app.mall.home.p.a.c(str, this);
        }
    }

    private a(String str, int i2, String... strArr) {
        super(str, i2);
        this.mNodeName = strArr;
    }

    public String getNodeName(int i2) {
        String[] strArr = this.mNodeName;
        return i2 < strArr.length ? strArr[i2] : "empty";
    }
}
