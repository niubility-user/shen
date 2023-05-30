package com.jingdong.manto.q;

import android.os.Build;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.c;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes16.dex */
public class d {
    private com.jingdong.manto.widget.input.i a;

    /* renamed from: c */
    private MantoHandler f14014c = new MantoHandler(Looper.getMainLooper());
    private List<com.jingdong.manto.q.b> b = new CopyOnWriteArrayList();

    /* loaded from: classes16.dex */
    public class a extends com.jingdong.manto.sdk.thread.b<Boolean> {

        /* renamed from: h */
        final /* synthetic */ int f14015h;

        /* renamed from: i */
        final /* synthetic */ float[] f14016i;

        /* renamed from: j */
        final /* synthetic */ int f14017j;

        /* renamed from: k */
        final /* synthetic */ Boolean f14018k;

        /* renamed from: l */
        final /* synthetic */ int f14019l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j2, Boolean bool, int i2, float[] fArr, int i3, Boolean bool2, int i4) {
            super(j2, bool);
            d.this = r1;
            this.f14015h = i2;
            this.f14016i = fArr;
            this.f14017j = i3;
            this.f14018k = bool2;
            this.f14019l = i4;
        }

        @Override // com.jingdong.manto.sdk.thread.b
        /* renamed from: b */
        public Boolean a() {
            return Boolean.valueOf(d.this.b(this.f14015h, this.f14016i, this.f14017j, this.f14018k, this.f14019l));
        }
    }

    /* loaded from: classes16.dex */
    public class b extends com.jingdong.manto.sdk.thread.b<Boolean> {

        /* renamed from: h */
        final /* synthetic */ View f14021h;

        /* renamed from: i */
        final /* synthetic */ int f14022i;

        /* renamed from: j */
        final /* synthetic */ int f14023j;

        /* renamed from: k */
        final /* synthetic */ float[] f14024k;

        /* renamed from: l */
        final /* synthetic */ int f14025l;

        /* renamed from: m */
        final /* synthetic */ boolean f14026m;

        /* renamed from: n */
        final /* synthetic */ int f14027n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(long j2, Boolean bool, View view, int i2, int i3, float[] fArr, int i4, boolean z, int i5) {
            super(j2, bool);
            d.this = r1;
            this.f14021h = view;
            this.f14022i = i2;
            this.f14023j = i3;
            this.f14024k = fArr;
            this.f14025l = i4;
            this.f14026m = z;
            this.f14027n = i5;
        }

        @Override // com.jingdong.manto.sdk.thread.b
        /* renamed from: b */
        public Boolean a() {
            return Boolean.valueOf(d.this.a(this.f14021h, this.f14022i, this.f14023j, this.f14024k, this.f14025l, this.f14026m, this.f14027n));
        }
    }

    /* loaded from: classes16.dex */
    public class c extends com.jingdong.manto.sdk.thread.b<Boolean> {

        /* renamed from: h */
        final /* synthetic */ int f14028h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(long j2, Boolean bool, int i2) {
            super(j2, bool);
            d.this = r1;
            this.f14028h = i2;
        }

        @Override // com.jingdong.manto.sdk.thread.b
        /* renamed from: b */
        public Boolean a() {
            return Boolean.valueOf(d.this.g(this.f14028h));
        }
    }

    public d(com.jingdong.manto.widget.input.i iVar) {
        this.a = iVar;
    }

    private int a(int i2, int i3) {
        int i4 = 1;
        for (com.jingdong.manto.q.b bVar : this.b) {
            if (i2 == bVar.b && i3 >= bVar.f14010f) {
                i4++;
            }
        }
        return i4;
    }

    private List<com.jingdong.manto.q.b> a(com.jingdong.manto.q.b bVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        for (com.jingdong.manto.q.b bVar2 : this.b) {
            if (bVar2.b == bVar.f14009e) {
                copyOnWriteArrayList.addAll(a(bVar2));
            }
        }
        copyOnWriteArrayList.add(bVar);
        return copyOnWriteArrayList;
    }

    private void b(com.jingdong.manto.q.b bVar) {
        this.b.removeAll(a(bVar));
    }

    public final c.a a(int i2, boolean z) {
        return com.jingdong.manto.utils.c.a().a(hashCode() + "#" + i2, z);
    }

    public final boolean a(int i2) {
        return b(i2) != null;
    }

    public final boolean a(int i2, float[] fArr, int i3, Boolean bool, int i4) {
        a aVar = new a(200L, bool, i2, fArr, i3, bool, i4);
        return Thread.currentThread() == Looper.getMainLooper().getThread() ? aVar.a((MantoHandler) null).booleanValue() : aVar.a(this.f14014c).booleanValue();
    }

    final boolean a(View view, int i2, int i3, float[] fArr, int i4, boolean z, int i5) {
        ViewGroup d;
        if (view == null || fArr == null || fArr.length < 5 || (d = d(i3)) == null || c(i2) != null) {
            return false;
        }
        int i6 = (int) fArr[0];
        int i7 = (int) fArr[1];
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) fArr[2], (int) fArr[3]);
        if (i4 >= 0) {
            view.setVisibility(i4 == 0 ? 0 : 4);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            d.addView(view, d.getChildCount(), layoutParams);
            view.setZ(i5);
        } else {
            int a2 = a(i3, i5);
            int i8 = a2 >= 0 ? a2 : 0;
            if (i8 > d.getChildCount()) {
                i8 = d.getChildCount();
            }
            d.addView(view, i8, layoutParams);
        }
        com.jingdong.manto.q.b bVar = new com.jingdong.manto.q.b(view, i2, i3, i5, z);
        this.b.add(bVar);
        if (i4 == 0 && z && (d instanceof g)) {
            com.jingdong.manto.widget.input.i iVar = this.a;
            com.jingdong.manto.q.c cVar = new com.jingdong.manto.q.c(iVar);
            cVar.f14011c = view;
            cVar.d = i6;
            cVar.f14012e = i7;
            cVar.a = iVar.getScrollX();
            int scrollY = this.a.getScrollY();
            cVar.b = scrollY;
            i6 += cVar.a;
            i7 += scrollY;
            ((g) d).b(cVar);
            bVar.d = cVar;
        }
        view.setX(i6);
        view.setY(i7);
        return true;
    }

    public final com.jingdong.manto.q.b b(int i2) {
        for (com.jingdong.manto.q.b bVar : this.b) {
            if (bVar.f14009e == i2) {
                return bVar;
            }
        }
        return null;
    }

    final boolean b(int i2, float[] fArr, int i3, Boolean bool, int i4) {
        float f2;
        float f3;
        boolean z;
        com.jingdong.manto.widget.input.i iVar;
        com.jingdong.manto.q.b b2 = b(i2);
        if (b2 == null) {
            return false;
        }
        View view = b2.a.get();
        ViewGroup d = d(b2.b);
        if (d == null) {
            return false;
        }
        if (i3 >= 0) {
            view.setVisibility(i3 == 0 ? 0 : 4);
        }
        if (fArr == null || fArr.length <= 4) {
            if (b2.f14010f != i4) {
                b2.f14010f = i4;
                if (Build.VERSION.SDK_INT >= 21) {
                    view.setZ(i4);
                }
            }
            return true;
        }
        int i5 = (int) fArr[0];
        int i6 = (int) fArr[1];
        int i7 = (int) fArr[2];
        int i8 = (int) fArr[3];
        boolean booleanValue = bool != null ? bool.booleanValue() : b2.f14008c;
        if (b2.f14010f != i4) {
            this.b.remove(b2);
            d.removeView(view);
            if (a(view, i2, b2.b, fArr, i3, booleanValue, i4)) {
                return true;
            }
            b(b2);
            return false;
        }
        com.jingdong.manto.q.c cVar = b2.d;
        if (b2.b == 0 && (d instanceof g) && booleanValue != (z = b2.f14008c)) {
            if (z) {
                if (booleanValue) {
                    if (cVar == null) {
                        iVar = this.a;
                        cVar = new com.jingdong.manto.q.c(iVar);
                        cVar.f14011c = view;
                        cVar.d = i5;
                        cVar.f14012e = i6;
                        cVar.a = iVar.getScrollX();
                        cVar.b = this.a.getScrollY();
                        b2.d = cVar;
                    }
                    ((g) d).b(cVar);
                }
                ((g) d).a(cVar);
            } else {
                if (booleanValue) {
                    if (cVar == null) {
                        iVar = this.a;
                        cVar = new com.jingdong.manto.q.c(iVar);
                        cVar.f14011c = view;
                        cVar.d = i5;
                        cVar.f14012e = i6;
                        cVar.a = iVar.getScrollX();
                        cVar.b = this.a.getScrollY();
                        b2.d = cVar;
                    }
                    ((g) d).b(cVar);
                }
                ((g) d).a(cVar);
            }
        }
        b2.f14008c = booleanValue;
        if (booleanValue && cVar != null) {
            cVar.d = i5;
            cVar.f14012e = i6;
            cVar.a = this.a.getScrollX();
            int scrollY = this.a.getScrollY();
            cVar.b = scrollY;
            f3 = cVar.a + i5;
            f2 = scrollY + i6;
        } else {
            f2 = i6;
            f3 = i5;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i7;
        layoutParams.height = i8;
        view.setX(f3);
        view.setY(f2);
        view.requestLayout();
        return true;
    }

    public final boolean b(View view, int i2, int i3, float[] fArr, int i4, boolean z, int i5) {
        b bVar = new b(200L, Boolean.FALSE, view, i2, i3, fArr, i4, z, i5);
        return Thread.currentThread() == Looper.getMainLooper().getThread() ? bVar.a((MantoHandler) null).booleanValue() : bVar.a(this.f14014c).booleanValue();
    }

    public final View c(int i2) {
        com.jingdong.manto.q.b b2 = b(i2);
        if (b2 == null) {
            return null;
        }
        return b2.a.get();
    }

    final ViewGroup d(int i2) {
        if (i2 == 0) {
            return this.a;
        }
        com.jingdong.manto.q.b b2 = b(i2);
        if (b2 == null) {
            return null;
        }
        return (ViewGroup) b2.a.get();
    }

    public final c.a e(int i2) {
        return com.jingdong.manto.utils.c.a().b(hashCode() + "#" + i2);
    }

    public final boolean f(int i2) {
        return new c(200L, Boolean.FALSE, i2).a(Thread.currentThread() == Looper.getMainLooper().getThread() ? null : this.f14014c).booleanValue();
    }

    final boolean g(int i2) {
        com.jingdong.manto.q.b b2 = b(i2);
        if (b2 == null) {
            return false;
        }
        b(b2);
        ViewGroup d = d(b2.b);
        if (d == null) {
            return false;
        }
        this.b.remove(b2);
        d.removeView(b2.a.get());
        if (b2.b == 0 && (d instanceof g) && b2.f14008c) {
            ((g) d).a(b2.d);
            return true;
        }
        return true;
    }
}
