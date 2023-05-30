package com.jd.lib.cashier.sdk.pay.recyclerview;

import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    private final FragmentActivity a;
    private final RecyclerView b;

    /* renamed from: c */
    private final GridLayoutSmoothScroller f4174c;

    /* renamed from: com.jd.lib.cashier.sdk.pay.recyclerview.a$a */
    /* loaded from: classes14.dex */
    public class ViewTreeObserverOnGlobalLayoutListenerC0145a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: g */
        final /* synthetic */ List f4175g;

        ViewTreeObserverOnGlobalLayoutListenerC0145a(List list) {
            a.this = r1;
            this.f4175g = list;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ViewTreeObserver viewTreeObserver = a.this.b.getViewTreeObserver();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            } else if (i2 >= 14) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            a.this.e(this.f4175g);
        }
    }

    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f4177g;

        b(int i2) {
            a.this = r1;
            this.f4177g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.LayoutManager layoutManager = a.this.b.getLayoutManager();
            a.this.f4174c.setTargetPosition(this.f4177g);
            if (layoutManager != null) {
                layoutManager.startSmoothScroll(a.this.f4174c);
            }
        }
    }

    public a(FragmentActivity fragmentActivity, RecyclerView recyclerView) {
        this.a = fragmentActivity;
        this.b = recyclerView;
        this.f4174c = new GridLayoutSmoothScroller(fragmentActivity);
    }

    private void d(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0145a(list));
    }

    public void e(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        RecyclerView.LayoutManager layoutManager;
        int i2 = 0;
        Pair pair = null;
        int i3 = -1;
        for (int i4 = 0; i4 < list.size(); i4++) {
            com.jd.lib.cashier.sdk.d.a.e.a aVar = list.get(i4);
            if (aVar instanceof x) {
                x xVar = (x) aVar;
                if (xVar.a().selected && aVar.getItemType() == 200001 && g.a(xVar.a().code)) {
                    i3 = i4;
                }
            }
            if (aVar.getItemType() == 200005 && (layoutManager = this.b.getLayoutManager()) != null) {
                View findViewByPosition = layoutManager.findViewByPosition(i4);
                if (i3 != -1) {
                    pair = new Pair(findViewByPosition, Integer.valueOf(i3));
                }
            }
        }
        if (pair == null) {
            while (true) {
                if (i2 >= list.size()) {
                    break;
                }
                com.jd.lib.cashier.sdk.d.a.e.a aVar2 = list.get(i2);
                if ((aVar2 instanceof x) && ((x) aVar2).a().selected) {
                    RecyclerView.LayoutManager layoutManager2 = this.b.getLayoutManager();
                    if (layoutManager2 != null) {
                        pair = new Pair(layoutManager2.findViewByPosition(i2), Integer.valueOf(i2));
                    }
                } else {
                    i2++;
                }
            }
        }
        if (pair != null) {
            int intValue = ((Integer) pair.second).intValue();
            Object obj = pair.first;
            if (obj != null) {
                View view = (View) obj;
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                int height = view.getHeight();
                int height2 = this.b.getHeight();
                view.getLocationOnScreen(iArr);
                this.b.getLocationOnScreen(iArr2);
                int i5 = (int) (height * 0.25f);
                int i6 = iArr2[1] - iArr[1];
                int i7 = (iArr[1] + height) - (iArr2[1] + height2);
                if (i6 >= i5 || i7 >= i5) {
                    g(intValue);
                    return;
                }
                return;
            }
            g(intValue);
        }
    }

    private void g(int i2) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.post(new b(i2));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0046, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x005b, code lost:
        if (r7 == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0043, code lost:
        if (r8 != false) goto L96;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void f(List<com.jd.lib.cashier.sdk.d.a.e.a> list, boolean z, boolean z2) {
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayEntity cashierPayEntity;
        if (!g0.a(this.a) || this.b == null || list == null || list.isEmpty() || (cashierPayEntity = (b2 = ((CashierPayViewModel) ViewModelProviders.of(this.a).get(CashierPayViewModel.class)).b()).K) == null) {
            return;
        }
        String str = cashierPayEntity.baitiaoAutoSlideFlag;
        if (!TextUtils.equals("0", str)) {
            if (!TextUtils.equals("1", str)) {
                if (!TextUtils.equals("2", str)) {
                    if (TextUtils.equals("3", str)) {
                        if (!z2) {
                        }
                    }
                }
            }
            if (b2 == null && b2.K != null && z) {
                d(list);
                return;
            }
            return;
        }
        z = false;
        if (b2 == null) {
        }
    }
}
