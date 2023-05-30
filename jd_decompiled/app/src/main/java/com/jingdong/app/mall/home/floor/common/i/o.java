package com.jingdong.app.mall.home.floor.common.i;

import android.graphics.Paint;
import android.graphics.Path;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class o {
    public static final o BOTTOM;
    public static final o MIDDLE;
    public static final o TOP;

    /* renamed from: g */
    private static final /* synthetic */ o[] f9338g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public enum a extends o {
        a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.o
        public boolean parseDividerHeight(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.c cVar) {
            com.jingdong.app.mall.home.r.e.h hVar = cVar.mParentModel;
            if (hVar == null) {
                return false;
            }
            int d = com.jingdong.app.mall.home.floor.common.d.d(hVar.f10692c);
            cVar.mFloorHeight = d;
            if (d > 0) {
                list.add(cVar);
                return true;
            }
            return false;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.o
        public void parseDividerInfo(com.jingdong.app.mall.home.r.e.c cVar) {
            com.jingdong.app.mall.home.r.e.h hVar;
            int i2;
            if (cVar == null || (hVar = cVar.mParentModel) == null || (i2 = cVar.mFloorHeight) <= 0 || hVar.m()) {
                return;
            }
            int i3 = hVar.w[0];
            if (o.b(i3)) {
                Paint paint = new Paint(1);
                cVar.B = paint;
                paint.setColor(i3);
                Path path = new Path();
                cVar.A = path;
                path.addRect(0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, i2, Path.Direction.CW);
            }
        }
    }

    static {
        a aVar = new a("TOP", 0);
        TOP = aVar;
        o oVar = new o("MIDDLE", 1) { // from class: com.jingdong.app.mall.home.floor.common.i.o.b
            {
                a aVar2 = null;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.o
            public boolean parseDividerHeight(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.c cVar) {
                com.jingdong.app.mall.home.r.e.h hVar = cVar.mParentModel;
                if (hVar == null || hVar.f10694f <= 0) {
                    return false;
                }
                int d = com.jingdong.app.mall.home.floor.common.d.d(hVar.f10693e);
                if (d <= 0) {
                    d = 2;
                }
                cVar.mFloorHeight = d;
                list.add(cVar);
                return true;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.o
            public void parseDividerInfo(com.jingdong.app.mall.home.r.e.c cVar) {
                com.jingdong.app.mall.home.r.e.h hVar;
                int i2;
                if (cVar == null || (hVar = cVar.mParentModel) == null || (i2 = cVar.mFloorHeight) <= 0) {
                    return;
                }
                boolean m2 = hVar.m();
                int d = com.jingdong.app.mall.home.floor.common.d.d(hVar.d);
                int i3 = hVar.w[0];
                if (o.b(i3) && d > 0 && !m2) {
                    Paint paint = new Paint(1);
                    cVar.D = paint;
                    paint.setColor(i3);
                    Path path = new Path();
                    cVar.C = path;
                    float f2 = i2;
                    path.addRect(0.0f, 0.0f, d, f2, Path.Direction.CW);
                    cVar.C.addRect(com.jingdong.app.mall.home.floor.common.d.f9279g - d, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, f2, Path.Direction.CW);
                }
                int j2 = m.j(hVar.v, hVar.e());
                if (j2 != 0) {
                    Paint paint2 = new Paint(1);
                    cVar.B = paint2;
                    paint2.setColor(j2);
                    Path path2 = new Path();
                    cVar.A = path2;
                    path2.addRect(d, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g - d, i2, Path.Direction.CW);
                }
            }
        };
        MIDDLE = oVar;
        o oVar2 = new o("BOTTOM", 2) { // from class: com.jingdong.app.mall.home.floor.common.i.o.c
            {
                a aVar2 = null;
            }

            private void c(com.jingdong.app.mall.home.r.e.c cVar, int i2, int i3, int i4) {
                if (o.b(i2)) {
                    Paint paint = new Paint(1);
                    cVar.B = paint;
                    paint.setColor(i2);
                    Path path = new Path();
                    cVar.A = path;
                    path.addRect(i3, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g - i3, i4, Path.Direction.CW);
                }
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.o
            public boolean parseDividerHeight(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.c cVar) {
                com.jingdong.app.mall.home.r.e.h hVar = cVar.mParentModel;
                if (hVar == null) {
                    return false;
                }
                int d = com.jingdong.app.mall.home.floor.common.d.d(hVar.b);
                cVar.mFloorHeight = d;
                if (d > 0) {
                    list.add(cVar);
                    return true;
                }
                return false;
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.o
            public void parseDividerInfo(com.jingdong.app.mall.home.r.e.c cVar) {
                com.jingdong.app.mall.home.r.e.h hVar;
                int i2;
                if (cVar == null || (hVar = cVar.mParentModel) == null || (i2 = cVar.mFloorHeight) <= 0) {
                    return;
                }
                int d = com.jingdong.app.mall.home.floor.common.d.d(hVar.d);
                if (hVar.m()) {
                    int[] iArr = {0};
                    m.k(hVar.x, iArr);
                    c(cVar, iArr[0], d, i2);
                    return;
                }
                int i3 = hVar.w[0];
                if (o.b(i3) && d > 0) {
                    Paint paint = new Paint(1);
                    cVar.D = paint;
                    paint.setColor(i3);
                    Path path = new Path();
                    cVar.C = path;
                    float f2 = i2;
                    path.addRect(0.0f, 0.0f, d, f2, Path.Direction.CW);
                    cVar.C.addRect(com.jingdong.app.mall.home.floor.common.d.f9279g - d, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, f2, Path.Direction.CW);
                }
                int[] iArr2 = {hVar.w[0]};
                m.k(hVar.x, iArr2);
                c(cVar, iArr2[0], d, i2);
            }
        };
        BOTTOM = oVar2;
        f9338g = new o[]{aVar, oVar, oVar2};
    }

    private o(String str, int i2) {
        super(str, i2);
    }

    public static boolean b(int i2) {
        return (((-16777216) & i2) == 0 || i2 == com.jingdong.app.mall.home.a.x) ? false : true;
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f9338g.clone();
    }

    public abstract boolean parseDividerHeight(List<? super com.jingdong.app.mall.home.r.e.d> list, com.jingdong.app.mall.home.r.e.c cVar);

    public abstract void parseDividerInfo(com.jingdong.app.mall.home.r.e.c cVar);

    /* synthetic */ o(String str, int i2, a aVar) {
        this(str, i2);
    }
}
