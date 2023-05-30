package com.jingdong.app.mall.home.floor.view.b;

import android.content.Context;
import androidx.annotation.IntRange;
import com.jingdong.app.mall.home.floor.view.b.g.h;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.EmptyView;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.LadySecKillView;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.Line1Split2CardView;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.LineSkuView;
import com.jingdong.app.mall.home.floor.view.linefloor.floor.LiveVideoFloor;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.Line1To4Layout;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class a {
    public static final a LINE_1_SPLIT_2_CARD;
    public static final a LINE_OTHER_1;
    public static final a LINE_SALE;
    public static final a LINE_VIDEO;
    public static final a LINE_WEIGHT_1;
    public static final a LINE_WEIGHT_2;
    public static final a UNKNOWN;

    /* renamed from: g */
    private static final /* synthetic */ a[] f9734g;
    private int subWeight;
    private String[] typeStr;
    private int weight;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.view.b.a$a */
    /* loaded from: classes4.dex */
    public enum C0296a extends a {
        C0296a(String str, int i2, int i3, String... strArr) {
            super(str, i2, i3, strArr, (C0296a) null);
        }

        @Override // com.jingdong.app.mall.home.floor.view.b.a
        protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
            return new com.jingdong.app.mall.home.floor.view.linefloor.base.a(cVar, fVar, this);
        }
    }

    static {
        C0296a c0296a = new C0296a("UNKNOWN", 0, 0, "88001");
        UNKNOWN = c0296a;
        a aVar = new a("LINE_SALE", 1, 2, "m2_skill") { // from class: com.jingdong.app.mall.home.floor.view.b.a.b
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new com.jingdong.app.mall.home.floor.view.b.g.b(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new LadySecKillView(context, this);
            }
        };
        LINE_SALE = aVar;
        a aVar2 = new a("LINE_VIDEO", 2, 2, "m2_live") { // from class: com.jingdong.app.mall.home.floor.view.b.a.c
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new h(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new LiveVideoFloor(context, this);
            }
        };
        LINE_VIDEO = aVar2;
        a aVar3 = new a("LINE_WEIGHT_2", 3, 2, "m2") { // from class: com.jingdong.app.mall.home.floor.view.b.a.d
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new com.jingdong.app.mall.home.floor.view.b.g.g(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new LineSkuView(context, this);
            }
        };
        LINE_WEIGHT_2 = aVar3;
        a aVar4 = new a("LINE_WEIGHT_1", 4, 2, 1, "m4") { // from class: com.jingdong.app.mall.home.floor.view.b.a.e
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new com.jingdong.app.mall.home.floor.view.b.g.g(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new LineSkuView(context, this);
            }
        };
        LINE_WEIGHT_1 = aVar4;
        a aVar5 = new a("LINE_1_SPLIT_2_CARD", 5, 2, "1_split_2_card") { // from class: com.jingdong.app.mall.home.floor.view.b.a.f
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new com.jingdong.app.mall.home.floor.view.b.g.e(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new Line1Split2CardView(context, this);
            }
        };
        LINE_1_SPLIT_2_CARD = aVar5;
        a aVar6 = new a("LINE_OTHER_1", 6, 1, 1, "line_other_1") { // from class: com.jingdong.app.mall.home.floor.view.b.a.g
            {
                C0296a c0296a2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar) {
                return new com.jingdong.app.mall.home.floor.view.b.g.f(cVar, fVar, this);
            }

            @Override // com.jingdong.app.mall.home.floor.view.b.a
            protected BaseLineLayout b(Context context) {
                return new Line1To4Layout(context, this);
            }
        };
        LINE_OTHER_1 = aVar6;
        f9734g = new a[]{c0296a, aVar, aVar2, aVar3, aVar4, aVar5, aVar6};
    }

    /* synthetic */ a(String str, int i2, int i3, int i4, String[] strArr, C0296a c0296a) {
        this(str, i2, i3, i4, strArr);
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f9734g.clone();
    }

    protected abstract com.jingdong.app.mall.home.floor.view.linefloor.base.a a(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar);

    protected BaseLineLayout b(Context context) {
        return new EmptyView(context, UNKNOWN);
    }

    protected void c(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.floor.view.linefloor.base.a aVar, com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar) {
    }

    public String getFirstTypeStr() {
        return this.typeStr[0];
    }

    public com.jingdong.app.mall.home.floor.view.linefloor.base.a getLineItem(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar, com.jingdong.app.mall.home.r.e.f fVar, com.jingdong.app.mall.home.r.e.f fVar2) {
        com.jingdong.app.mall.home.floor.view.linefloor.base.a a = a(cVar, fVar);
        a.K(fVar2);
        a.J(hVar, dVar);
        c(cVar, a, hVar, dVar, fVar);
        return a;
    }

    public BaseLineLayout getLineViewByCache(Context context, MallFloorLineMore mallFloorLineMore) {
        return b(context);
    }

    public int getSubWeight() {
        return this.subWeight;
    }

    public int getWeight() {
        return this.weight;
    }

    public final void parseLineType(Map<String, a> map) {
        for (String str : this.typeStr) {
            if (map.containsKey(str)) {
                com.jingdong.app.mall.home.o.a.f.o("Error ", " is already register, please change strType on", str);
                return;
            }
            map.put(str, this);
        }
    }

    /* synthetic */ a(String str, int i2, int i3, String[] strArr, C0296a c0296a) {
        this(str, i2, i3, strArr);
    }

    private a(@IntRange(from = 0, to = 4) String str, int i2, int i3, String... strArr) {
        this(str, i2, i3, i3, strArr);
    }

    private a(@IntRange(from = 0, to = 4) String str, @IntRange(from = 0, to = 4) int i2, int i3, int i4, String... strArr) {
        super(str, i2);
        this.weight = i3;
        this.subWeight = i4;
        this.typeStr = strArr;
    }
}
