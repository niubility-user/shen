package com.jingdong.manto.widget.input;

import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class l implements n.c0 {

    /* renamed from: j  reason: collision with root package name */
    private static l f14473j;

    /* renamed from: k  reason: collision with root package name */
    private static final Map<com.jingdong.manto.q.n, l> f14474k = new HashMap();
    private String a = "InputPageOffsetHelper";
    private com.jingdong.manto.q.n b;

    /* renamed from: c  reason: collision with root package name */
    public Map<c, l> f14475c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f14476e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f14477f;

    /* renamed from: g  reason: collision with root package name */
    private int f14478g;

    /* renamed from: h  reason: collision with root package name */
    private Runnable f14479h;

    /* renamed from: i  reason: collision with root package name */
    private Runnable f14480i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View f2;
            com.jingdong.manto.q.r rVar;
            if (!l.this.b.f() || (f2 = l.f(l.this)) == null) {
                return;
            }
            f2.scrollTo(0, 0);
            if (l.this.f14478g != 0 && (rVar = l.this.b.t) != null) {
                View view = rVar.getView();
                view.scrollBy(view.getScrollX(), -l.this.f14478g);
            }
            l.i(l.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class a implements Runnable {
            final /* synthetic */ com.jingdong.manto.widget.input.z.b a;
            final /* synthetic */ int b;

            a(b bVar, com.jingdong.manto.widget.input.z.b bVar2, int i2) {
                this.a = bVar2;
                this.b = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.b(this.b);
            }
        }

        b() {
        }

        private int a() {
            View f2 = l.f(l.this);
            if (f2 != null) {
                return f2.getScrollY();
            }
            return 0;
        }

        private void a(int i2) {
            View f2;
            if (i2 >= 0 && (f2 = l.f(l.this)) != null) {
                f2.scrollTo(0, i2);
                l.h(l.this);
            }
        }

        private void a(com.jingdong.manto.widget.input.z.b bVar, int i2) {
            MantoThreadUtils.post(new a(this, bVar, i2));
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i2;
            View view;
            View view2;
            View view3;
            com.jingdong.manto.widget.input.z.b a2 = o.a(l.this.b);
            if (a2 == null || !l.this.b.f()) {
                return;
            }
            l.this.f14478g = 0;
            if (a2.a() == null || a2.c() == null) {
                return;
            }
            EditText c2 = a2.c();
            View a3 = a2.a();
            if (Build.VERSION.SDK_INT >= 16 && a3.getHeight() <= a3.getMinimumHeight() && l.e(l.this) < 5) {
                l.this.a(false);
                return;
            }
            l.this.f14476e = 0;
            a(a2, a3.getHeight());
            if (a2.b()) {
                int[] iArr = new int[2];
                c2.getLocationOnScreen(iArr);
                if (iArr[1] < l.this.d && (c2 instanceof f)) {
                    MantoLog.i(l.this.a, "<<<<<<<should scroll to edittext top>>>>>>>>>>>");
                    com.jingdong.manto.q.r rVar = l.this.b.t;
                    if (rVar == null || (view3 = rVar.getView()) == null) {
                        return;
                    }
                    view3.scrollTo(view3.getScrollX(), ((AbsoluteLayout.LayoutParams) c2.getLayoutParams()).y);
                    return;
                }
                int i3 = iArr[1];
                if (i3 <= l.g(l.this)) {
                    int height = c2.getHeight() + i3;
                    a3.getLocationOnScreen(iArr);
                    int i4 = iArr[1];
                    MantoLog.w(l.this.a, String.format("panelTop= %d  editTop = %d", Integer.valueOf(i4), Integer.valueOf(i3)));
                    if (i3 > i4) {
                        MantoLog.e(l.this.a, "edit input below keyboard");
                    }
                    com.jingdong.manto.widget.input.z.d dVar = (com.jingdong.manto.widget.input.z.d) c2;
                    if (!dVar.a() || c2.getLayout() == null) {
                        i2 = i3;
                    } else {
                        i2 = dVar.a(c2.getLayout().getLineForOffset(c2.getSelectionStart())) + i3;
                        MantoLog.i(l.this.a, "lastLineTop: " + i2);
                        int a4 = dVar.a(c2.getLayout().getLineForOffset(c2.getSelectionStart()) + 1) + i3;
                        MantoLog.i(l.this.a, "lastLineBottom: " + a4);
                        if (i2 - i3 >= c2.getHeight()) {
                            i2 = height - c2.getLineHeight();
                        }
                        if (a4 - i3 < c2.getHeight()) {
                            height = a4;
                        }
                    }
                    int e2 = height + a2.e();
                    MantoLog.i(l.this.a, "contentTop : " + i2);
                    MantoLog.i(l.this.a, "panelTop : " + i4);
                    MantoLog.i(l.this.a, "contentBottom : " + e2);
                    if (i4 >= e2) {
                        MantoLog.v(l.this.a, ">>>>>>>>>step 33333<<<<<<<<<<<<");
                        if (e2 >= 0) {
                            MantoLog.v(l.this.a, "contentBottom>=0");
                            return;
                        }
                        MantoLog.v(l.this.a, "contentBottom<0");
                        int min = Math.min(e2, i2 - l.this.d);
                        MantoLog.i(l.this.a, "scrollOffset : " + min);
                        com.jingdong.manto.q.r rVar2 = l.this.b.t;
                        if (rVar2 == null || (view = rVar2.getView()) == null) {
                            return;
                        }
                        if (dVar.e() || !dVar.a()) {
                            MantoLog.w(l.this.a, String.format("offsetRunner : =======NumberWebInput getScrollOffset=%d, panelScrollY=%d", Integer.valueOf(a()), Integer.valueOf(min)));
                            a(a() + min);
                            return;
                        }
                        int max = Math.max(0, Math.min((MantoDensityUtils.convertToDeviceSizeByInt(rVar2.getContentHeight()) - rVar2.getHeight()) - view.getScrollY(), min));
                        if (max == 0) {
                            MantoLog.v(l.this.a, "reset webViewScrollOffset from :" + max);
                            max = l.this.d - view.getScrollY();
                            MantoLog.v(l.this.a, "reset webViewScrollOffset to :" + max);
                        }
                        MantoLog.e(l.this.a, String.format("webView scroll to\u3000%d", Integer.valueOf(view.getScrollY() + max)));
                        view.scrollBy(view.getScrollX(), max);
                        MantoLog.e(l.this.a, String.format("webView scrollOffset\u3000%d", Integer.valueOf(max)));
                        return;
                    }
                    MantoLog.i(l.this.a, "topMargin=: " + l.this.d);
                    String str = l.this.a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("keyboardTop2ContentBottomDistance=: ");
                    int i5 = e2 - i4;
                    sb.append(i5);
                    MantoLog.i(str, sb.toString());
                    int max2 = Math.max(0, Math.min(i5, i2 - l.this.d));
                    MantoLog.i(l.this.a, "scrollOffset : " + max2);
                    com.jingdong.manto.q.r rVar3 = l.this.b.t;
                    if (rVar3 == null || (view2 = rVar3.getView()) == null) {
                        return;
                    }
                    if (dVar.e() || !dVar.a()) {
                        MantoLog.w(l.this.a, String.format("offsetRunner : =======NumberWebInput getScrollOffset=%d, panelScrollY=%d", Integer.valueOf(a()), Integer.valueOf(max2)));
                        a(a() + max2);
                        return;
                    }
                    int convertToDeviceSizeByInt = MantoDensityUtils.convertToDeviceSizeByInt(rVar3.getContentHeight());
                    int max3 = Math.max(0, Math.min((convertToDeviceSizeByInt - rVar3.getHeight()) - view2.getScrollY(), max2));
                    l.this.f14478g = max3;
                    MantoLog.e(l.this.a, String.format("webView scroll to\u3000%d", Integer.valueOf(view2.getScrollY() + max3)));
                    if (max3 == 0 && i3 < 0) {
                        MantoLog.v(l.this.a, ">>>>>>>>>new value<<<<<<<<<<<<");
                        int min2 = Math.min((convertToDeviceSizeByInt - rVar3.getHeight()) - view2.getScrollY(), max2);
                        view2.scrollBy(view2.getScrollX(), min2);
                        MantoLog.e(l.this.a, String.format("webView scrollOffset\u3000%d", Integer.valueOf(min2)));
                        return;
                    }
                    MantoLog.v(l.this.a, ">>>>>>>>>old value<<<<<<<<<<<<");
                    view2.scrollBy(view2.getScrollX(), max3);
                    int a5 = (max2 - max3) + a();
                    MantoLog.e(l.this.a, String.format("webView scrollOffset\u3000%d", Integer.valueOf(max3)));
                    MantoLog.e(l.this.a, String.format("innerView scrollOffset\u3000%d", Integer.valueOf(a5)));
                    a(a5);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();

        void b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public l() {
        a();
        this.b = null;
        this.d = 0;
    }

    private l(com.jingdong.manto.q.n nVar) {
        a();
        this.b = nVar;
        nVar.a(this);
        this.d = (Build.VERSION.SDK_INT >= 21 ? MantoDensityUtils.getStatusBarHeight(com.jingdong.manto.c.a(), MantoDensityUtils.dip2pixel(com.jingdong.manto.c.a(), 25)) : 0) + MantoDensityUtils.dip2pixel(nVar.f14071i, 10);
    }

    public static l a(com.jingdong.manto.q.n nVar) {
        if (nVar == null || !nVar.f()) {
            if (f14473j == null) {
                f14473j = new m();
            }
            return f14473j;
        }
        Map<com.jingdong.manto.q.n, l> map = f14474k;
        l lVar = map.get(nVar);
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(nVar);
        map.put(nVar, lVar2);
        return lVar2;
    }

    public static l a(Reference<com.jingdong.manto.q.n> reference) {
        return a(reference == null ? null : reference.get());
    }

    private void a() {
        this.f14475c = new HashMap();
        this.f14476e = 0;
        this.f14477f = false;
        this.f14478g = 0;
        this.f14479h = new a();
        this.f14480i = new b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.f14476e = 0;
            this.f14477f = false;
        }
        if (this.b.f()) {
            if (this.f14477f) {
                this.f14476e = 0;
            } else if (this.f14476e != 0 && Build.VERSION.SDK_INT >= 16) {
                this.b.l().postOnAnimationDelayed(this.f14480i, 100L);
            } else {
                this.b.l().post(this.f14480i);
            }
        }
    }

    static int e(l lVar) {
        int i2 = lVar.f14476e + 1;
        lVar.f14476e = i2;
        return i2;
    }

    static View f(l lVar) {
        if (lVar.b.f()) {
            return lVar.b.o();
        }
        return null;
    }

    static int g(l lVar) {
        Display defaultDisplay = ((WindowManager) lVar.b.l().getContext().getSystemService("window")).getDefaultDisplay();
        Point point2 = new Point();
        defaultDisplay.getSize(point2);
        return point2.y;
    }

    static void h(l lVar) {
        if (lVar.f14475c.size() > 0) {
            for (c cVar : (c[]) lVar.f14475c.keySet().toArray(new c[lVar.f14475c.size()])) {
                cVar.a();
            }
        }
    }

    static void i(l lVar) {
        if (lVar.f14475c.size() > 0) {
            for (c cVar : (c[]) lVar.f14475c.keySet().toArray(new c[lVar.f14475c.size()])) {
                cVar.b();
            }
        }
    }

    public final void a(c cVar) {
        if (cVar != null) {
            this.f14475c.remove(cVar);
        }
    }

    public void b() {
        if (this.b.f()) {
            this.f14477f = true;
            this.b.l().post(this.f14479h);
        }
    }

    public void c() {
        a(true);
    }

    @Override // com.jingdong.manto.q.n.c0
    public void onDestroy() {
        this.b.b(this);
        f14474k.remove(this.b);
    }
}
