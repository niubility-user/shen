package com.jingdong.manto.widget.input.autofill;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.Locale;

/* loaded from: classes16.dex */
public class AutoFillListPopupWindowBase {
    ListAdapter a;
    int d;

    /* renamed from: e  reason: collision with root package name */
    int f14421e;

    /* renamed from: f  reason: collision with root package name */
    boolean f14422f;

    /* renamed from: j  reason: collision with root package name */
    private FrameLayout f14426j;

    /* renamed from: l  reason: collision with root package name */
    View f14428l;

    /* renamed from: m  reason: collision with root package name */
    private Drawable f14429m;

    /* renamed from: n  reason: collision with root package name */
    private AdapterView.OnItemClickListener f14430n;
    private AdapterView.OnItemSelectedListener o;
    boolean p;
    com.jingdong.manto.ui.base.a r;
    c s;
    private final g t;
    private final f u;
    private final e v;
    private final d w;
    private Context y;

    /* renamed from: c  reason: collision with root package name */
    int f14420c = -2;
    int b = -2;

    /* renamed from: g  reason: collision with root package name */
    boolean f14423g = false;

    /* renamed from: h  reason: collision with root package name */
    boolean f14424h = false;

    /* renamed from: i  reason: collision with root package name */
    int f14425i = Integer.MAX_VALUE;

    /* renamed from: k  reason: collision with root package name */
    private int f14427k = 0;
    private Handler z = new Handler(Looper.getMainLooper());
    private Rect q = new Rect();
    private boolean x = false;

    /* loaded from: classes16.dex */
    class a implements Runnable {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
            c cVar;
            if (i2 == -1 || (cVar = AutoFillListPopupWindowBase.this.s) == null) {
                return;
            }
            cVar.a = false;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* loaded from: classes16.dex */
    public static class c extends ListView {
        private boolean a;
        private boolean b;

        public c(Context context, boolean z) {
            super(context, null);
            this.b = z;
            setCacheColorHint(0);
        }

        final int a(int i2, int i3) {
            int listPaddingTop = getListPaddingTop();
            int listPaddingBottom = getListPaddingBottom();
            getListPaddingLeft();
            getListPaddingRight();
            int dividerHeight = getDividerHeight();
            Drawable divider = getDivider();
            ListAdapter adapter = getAdapter();
            if (adapter == null) {
                return listPaddingTop + listPaddingBottom;
            }
            int i4 = listPaddingBottom + listPaddingTop;
            if (dividerHeight <= 0 || divider == null) {
                dividerHeight = 0;
            }
            int count = adapter.getCount();
            View view = null;
            int i5 = 0;
            for (int i6 = 0; i6 < count; i6++) {
                int itemViewType = adapter.getItemViewType(i6);
                if (itemViewType != i5) {
                    view = null;
                    i5 = itemViewType;
                }
                view = adapter.getView(i6, view, this);
                int i7 = view.getLayoutParams().height;
                view.measure(i2, i7 > 0 ? View.MeasureSpec.makeMeasureSpec(i7, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
                if (i6 > 0) {
                    i4 += dividerHeight;
                }
                i4 += view.getMeasuredHeight();
                if (i4 >= i3) {
                    return i3;
                }
            }
            return i4;
        }

        @Override // android.view.ViewGroup, android.view.View
        public final boolean hasFocus() {
            return this.b || super.hasFocus();
        }

        @Override // android.view.View
        public final boolean hasWindowFocus() {
            return this.b || super.hasWindowFocus();
        }

        @Override // android.view.View
        public final boolean isFocused() {
            return this.b || super.isFocused();
        }

        @Override // android.view.View
        public final boolean isInTouchMode() {
            return (this.b && this.a) || super.isInTouchMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final AutoFillListPopupWindowBase a;

        private d(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2) {
            this.a = autoFillListPopupWindowBase2;
        }

        /* synthetic */ d(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2, a aVar) {
            this(autoFillListPopupWindowBase, autoFillListPopupWindowBase2);
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.a.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class e implements AbsListView.OnScrollListener {
        final AutoFillListPopupWindowBase a;

        private e(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2) {
            this.a = autoFillListPopupWindowBase2;
        }

        /* synthetic */ e(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2, a aVar) {
            this(autoFillListPopupWindowBase, autoFillListPopupWindowBase2);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            c cVar;
            FrameLayout frameLayout;
            int i5;
            AutoFillListPopupWindowBase autoFillListPopupWindowBase = this.a;
            if (autoFillListPopupWindowBase.s == null || autoFillListPopupWindowBase.f14426j == null) {
                return;
            }
            AutoFillListPopupWindowBase autoFillListPopupWindowBase2 = this.a;
            if (autoFillListPopupWindowBase2.a == null || (cVar = autoFillListPopupWindowBase2.s) == null) {
                return;
            }
            if (cVar.getLastVisiblePosition() == this.a.a.getCount() - 1) {
                if (this.a.s.getChildAt(r1.getChildCount() - 1) != null) {
                    if (this.a.s.getChildAt(r1.getChildCount() - 1).getBottom() <= this.a.s.getHeight()) {
                        frameLayout = this.a.f14426j;
                        i5 = 8;
                        frameLayout.setVisibility(i5);
                    }
                }
            }
            frameLayout = this.a.f14426j;
            i5 = 0;
            frameLayout.setVisibility(i5);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || this.a.d() || this.a.r.getContentView() == null) {
                return;
            }
            this.a.z.removeCallbacks(this.a.t);
            this.a.t.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class f implements View.OnTouchListener {
        final AutoFillListPopupWindowBase a;

        private f(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2) {
            this.a = autoFillListPopupWindowBase2;
        }

        /* synthetic */ f(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2, a aVar) {
            this(autoFillListPopupWindowBase, autoFillListPopupWindowBase2);
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            com.jingdong.manto.ui.base.a aVar;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (aVar = this.a.r) != null && aVar.isShowing() && x >= 0 && x < this.a.r.getWidth() && y >= 0 && y < this.a.r.getHeight()) {
                this.a.z.postDelayed(this.a.t, 250L);
                return false;
            } else if (action == 1) {
                this.a.z.removeCallbacks(this.a.t);
                return false;
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes16.dex */
    private class g implements Runnable {
        final AutoFillListPopupWindowBase a;

        private g(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2) {
            this.a = autoFillListPopupWindowBase2;
        }

        /* synthetic */ g(AutoFillListPopupWindowBase autoFillListPopupWindowBase, AutoFillListPopupWindowBase autoFillListPopupWindowBase2, a aVar) {
            this(autoFillListPopupWindowBase, autoFillListPopupWindowBase2);
        }

        @Override // java.lang.Runnable
        public final void run() {
            c cVar = this.a.s;
            if (cVar == null || cVar.getCount() <= this.a.s.getChildCount()) {
                return;
            }
            int childCount = this.a.s.getChildCount();
            AutoFillListPopupWindowBase autoFillListPopupWindowBase = this.a;
            if (childCount <= autoFillListPopupWindowBase.f14425i) {
                autoFillListPopupWindowBase.r.setInputMethodMode(2);
                this.a.e();
            }
        }
    }

    public AutoFillListPopupWindowBase(Context context, AttributeSet attributeSet, int i2) {
        a aVar = null;
        this.t = new g(this, this, aVar);
        this.u = new f(this, this, aVar);
        this.v = new e(this, this, aVar);
        this.w = new d(this, this, aVar);
        this.y = context;
        com.jingdong.manto.ui.base.a aVar2 = new com.jingdong.manto.ui.base.a(context);
        this.r = aVar2;
        aVar2.setInputMethodMode(1);
        Locale locale = this.y.getResources().getConfiguration().locale;
    }

    private void a() {
        FrameLayout frameLayout = this.f14426j;
        if (frameLayout != null) {
            ViewParent parent = frameLayout.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f14426j);
            }
        }
    }

    public final void b() {
        c cVar = this.s;
        if (cVar != null) {
            cVar.a = true;
            cVar.requestLayout();
        }
    }

    public final void c() {
        this.r.dismiss();
        a();
        this.r.setContentView(null);
        this.s = null;
        this.z.removeCallbacks(this.t);
    }

    public final boolean d() {
        return this.r.getInputMethodMode() == 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0124, code lost:
        if (r8 != (-1)) goto L53;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e() {
        /*
            Method dump skipped, instructions count: 590
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.widget.input.autofill.AutoFillListPopupWindowBase.e():void");
    }
}
