package com.jingdong.manto.q;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes16.dex */
public final class i {
    private View a;
    private int b;
    private ViewTreeObserver.OnGlobalLayoutListener d;

    /* renamed from: e  reason: collision with root package name */
    private Handler f14031e;

    /* renamed from: g  reason: collision with root package name */
    private int f14033g;

    /* renamed from: h  reason: collision with root package name */
    private int f14034h;

    /* renamed from: j  reason: collision with root package name */
    private List<c> f14036j;

    /* renamed from: c  reason: collision with root package name */
    private boolean f14030c = false;

    /* renamed from: f  reason: collision with root package name */
    private Runnable f14032f = null;

    /* renamed from: i  reason: collision with root package name */
    private boolean f14035i = false;

    /* loaded from: classes16.dex */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            i.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        b(int i2, int i3) {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.b();
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a(int i2);

        void g();
    }

    public i(Activity activity) {
        this.d = null;
        this.f14031e = null;
        try {
            this.f14034h = com.jingdong.manto.utils.e.a(activity);
            this.f14036j = new CopyOnWriteArrayList();
            this.f14031e = new Handler(Looper.getMainLooper());
            View childAt = ((FrameLayout) activity.findViewById(16908290)).getChildAt(0);
            this.a = childAt;
            ViewTreeObserver viewTreeObserver = childAt.getViewTreeObserver();
            a aVar = new a();
            this.d = aVar;
            viewTreeObserver.addOnGlobalLayoutListener(aVar);
            this.f14033g = MantoStatusBarUtil.getStatusBarHeight(activity);
        } catch (Exception e2) {
            MantoLog.e("KeyBoardHelper", e2.getMessage());
        }
    }

    private int a() {
        Rect rect = new Rect();
        this.a.getWindowVisibleDisplayFrame(rect);
        return Build.VERSION.SDK_INT >= 19 ? (rect.bottom - rect.top) + this.f14033g : rect.bottom - rect.top;
    }

    private void a(int i2) {
        List<c> list = this.f14036j;
        if (list != null) {
            for (c cVar : list) {
                if (cVar != null) {
                    cVar.a(i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        List<c> list = this.f14036j;
        if (list != null) {
            for (c cVar : list) {
                if (cVar != null) {
                    cVar.g();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Handler handler;
        try {
            int a2 = a();
            if (a2 != this.b) {
                int height = this.a.getRootView().getHeight();
                int i2 = (height - a2) - (this.f14035i ? this.f14034h : 0);
                int a3 = com.jingdong.manto.utils.g.a(this.a.getContext()) + this.f14033g;
                if (i2 > height / 4) {
                    this.f14030c = true;
                    a(i2);
                } else if (this.f14030c && (handler = this.f14031e) != null) {
                    if (this.f14032f == null) {
                        this.f14032f = new b(height, a3);
                    }
                    handler.removeCallbacksAndMessages(this.f14032f);
                    this.f14031e.postDelayed(this.f14032f, 251L);
                }
                this.b = a2;
            }
        } catch (Exception e2) {
            MantoLog.e("KeyBoardHelper", e2.getMessage());
        }
    }

    public void a(c cVar) {
        List<c> list = this.f14036j;
        if (list == null || list.contains(cVar)) {
            return;
        }
        this.f14036j.add(cVar);
    }

    public void a(boolean z) {
        this.f14035i = z;
    }

    public void b(c cVar) {
        List<c> list = this.f14036j;
        if (list != null) {
            list.remove(cVar);
        }
    }

    public void c() {
        View view;
        if (Build.VERSION.SDK_INT >= 16 && this.d != null && (view = this.a) != null && view.getViewTreeObserver() != null) {
            this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this.d);
        }
        if (this.f14032f != null) {
            this.f14032f = null;
        }
        List<c> list = this.f14036j;
        if (list != null) {
            list.clear();
        }
        Handler handler = this.f14031e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f14031e = null;
        }
    }
}
