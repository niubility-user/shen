package com.jd.dynamic.lib.viewparse.c.e;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.lib.lifecycle.LifecycleOwnerExtend;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.lib.views.ViewLifecycleObserver;
import com.jd.dynamic.lib.views.listeners.VisibilityChangeListener;
import com.jd.dynamic.lib.views.listeners.VisibilityChangeListener2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes13.dex */
public class g0 extends p0<View> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f2392g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2393h;

        a(View view, String str) {
            this.f2392g = view;
            this.f2393h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            g0.this.p(this.f2392g, this.f2393h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f2395g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f2396h;

        b(View view, String str) {
            this.f2395g = view;
            this.f2396h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            g0.this.g(this.f2395g, this.f2396h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean f(Map.Entry entry) {
        return Boolean.valueOf(TextUtils.equals((CharSequence) entry.getKey(), "onCreate") || TextUtils.equals((CharSequence) entry.getKey(), "onStart") || TextUtils.equals((CharSequence) entry.getKey(), "onResume") || TextUtils.equals((CharSequence) entry.getKey(), "onStop") || TextUtils.equals((CharSequence) entry.getKey(), "onPause") || TextUtils.equals((CharSequence) entry.getKey(), "onDestroy") || TextUtils.equals((CharSequence) entry.getKey(), "onActivityResult") || TextUtils.equals((CharSequence) entry.getKey(), "onPageCreate") || TextUtils.equals((CharSequence) entry.getKey(), "onPageEnter") || TextUtils.equals((CharSequence) entry.getKey(), "onPageLeave") || TextUtils.equals((CharSequence) entry.getKey(), "onPageDestroy"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(View view, String str) {
        Object obj;
        int i2 = R.id.dynamic_listen_horizontal_visibility_change;
        Object tag = view.getTag(i2);
        VisibilityChangeListener visibilityChangeListener = tag instanceof VisibilityChangeListener ? (VisibilityChangeListener) tag : null;
        VisibilityChangeListener2 visibilityChangeListener2 = tag instanceof VisibilityChangeListener2 ? (VisibilityChangeListener2) tag : null;
        View scrollView = visibilityChangeListener != null ? visibilityChangeListener.getScrollView() : visibilityChangeListener2 != null ? visibilityChangeListener2.getScrollView() : com.jd.dynamic.lib.utils.m.M(view);
        if (scrollView instanceof CollectionView) {
            if (visibilityChangeListener != null) {
                ((CollectionView) scrollView).getRecyclerView().removeOnScrollListener(visibilityChangeListener);
            }
            CollectionView collectionView = (CollectionView) scrollView;
            RecyclerView.OnScrollListener visibilityChangeListener3 = new VisibilityChangeListener(view, collectionView, null, str, this.b, this.a);
            collectionView.getRecyclerView().addOnScrollListener(visibilityChangeListener3);
            obj = visibilityChangeListener3;
        } else if (!(scrollView instanceof com.jd.dynamic.lib.viewparse.b.carouselView.f)) {
            return;
        } else {
            com.jd.dynamic.lib.viewparse.b.carouselView.f fVar = (com.jd.dynamic.lib.viewparse.b.carouselView.f) scrollView;
            m.c f2 = com.jd.dynamic.lib.utils.m.f(fVar);
            if (f2.f2274c == 0) {
                if (visibilityChangeListener != null) {
                    f2.b.removeOnPageChangeListener(visibilityChangeListener);
                }
                VisibilityChangeListener visibilityChangeListener4 = new VisibilityChangeListener(view, null, fVar, str, this.b, this.a);
                f2.b.addOnPageChangeListener(visibilityChangeListener4);
                view.setTag(i2, visibilityChangeListener4);
            }
            if (1 != f2.f2274c) {
                return;
            }
            if (visibilityChangeListener2 != null) {
                f2.a.unregisterOnPageChangeCallback(visibilityChangeListener2);
            }
            VisibilityChangeListener2 visibilityChangeListener22 = new VisibilityChangeListener2(view, fVar, str, this.b, this.a);
            f2.a.registerOnPageChangeCallback(visibilityChangeListener22);
            obj = visibilityChangeListener22;
        }
        view.setTag(i2, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(View view, Map map) {
        ViewLifecycleObserver viewLifecycleObserver;
        if (view.getContext() instanceof LifecycleOwner) {
            viewLifecycleObserver = new ViewLifecycleObserver(view, map, this.a);
            ((LifecycleOwner) view.getContext()).getLifecycle().addObserver(viewLifecycleObserver);
        } else {
            viewLifecycleObserver = null;
        }
        if (view.getContext() instanceof LifecycleOwnerExtend) {
            if (viewLifecycleObserver == null) {
                viewLifecycleObserver = new ViewLifecycleObserver(view, map, this.a);
            }
            ((LifecycleOwnerExtend) view.getContext()).addLifecycleEventObserver(viewLifecycleObserver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void j(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(HashMap hashMap, final View view, Map.Entry entry) {
        String str = (String) entry.getValue();
        String str2 = (String) entry.getKey();
        str2.hashCode();
        char c2 = '\uffff';
        switch (str2.hashCode()) {
            case -1351902487:
                if (str2.equals("onClick")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1336101728:
                if (str2.equals(DYConstants.DY_ON_TOUCH)) {
                    c2 = 1;
                    break;
                }
                break;
            case 71235917:
                if (str2.equals(DYConstants.DY_ON_LONG_CLICK)) {
                    c2 = 2;
                    break;
                }
                break;
            case 332797073:
                if (str2.equals(DYConstants.DY_ON_TOUCH_MOVE)) {
                    c2 = 3;
                    break;
                }
                break;
            case 1732445090:
                if (str2.equals(DYConstants.DY_ON_TOUCH_DOWN)) {
                    c2 = 4;
                    break;
                }
                break;
            case 1950390299:
                if (str2.equals(DYConstants.DY_ON_TOUCH_UP)) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                final List<String> i2 = com.jd.dynamic.lib.utils.s.i(str);
                view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.dynamic.lib.viewparse.c.e.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        g0.this.s(i2, view, view2);
                    }
                });
                return;
            case 1:
            case 3:
            case 4:
            case 5:
                hashMap.put(entry.getKey(), str);
                return;
            case 2:
                final List<String> i3 = com.jd.dynamic.lib.utils.s.i(str);
                view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jd.dynamic.lib.viewparse.c.e.k
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        boolean o;
                        o = g0.this.o(i3, view, view2);
                        return o;
                    }
                });
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(final Map map, final View view) {
        if (map.isEmpty() || view.getTag(R.id.dynamic_lifecycle_observer) != null) {
            return;
        }
        com.jd.dynamic.lib.utils.m.w(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.r
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.h(view, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(Map map, Map.Entry entry) {
        String str = (String) map.put(entry.getKey(), entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r12 != 3) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ boolean n(String str, View view, String str2, String str3, String str4, View view2, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!TextUtils.isEmpty(str)) {
                List<String> i2 = com.jd.dynamic.lib.utils.s.i(str);
                com.jd.dynamic.lib.utils.t.e("OnTouch", "ACTION_DOWN", str);
                Iterator<String> it = i2.iterator();
                while (it.hasNext()) {
                    com.jd.dynamic.lib.utils.s.b(it.next(), view, this.a, view);
                }
            }
            DynamicMtaUtil.uploadActionMta(this.a.getTemplate(), 0);
            return (view2.hasOnClickListeners() || view2.isLongClickable()) ? false : true;
        }
        if (action != 1) {
            if (action == 2) {
                if (!TextUtils.isEmpty(str2)) {
                    com.jd.dynamic.lib.utils.t.e("OnTouch", "ACTION_MOVE", str2);
                    Iterator<String> it2 = com.jd.dynamic.lib.utils.s.i(str2).iterator();
                    while (it2.hasNext()) {
                        com.jd.dynamic.lib.utils.s.b(it2.next(), view, this.a, view);
                    }
                }
            }
            if (!TextUtils.isEmpty(str4)) {
                com.jd.dynamic.lib.utils.t.e("OnTouch", "ACTION_NONE", str4);
                Iterator<String> it3 = com.jd.dynamic.lib.utils.s.i(str4).iterator();
                while (it3.hasNext()) {
                    com.jd.dynamic.lib.utils.s.b(it3.next(), view, this.a, view);
                }
            }
            return false;
        }
        if (!TextUtils.isEmpty(str3)) {
            com.jd.dynamic.lib.utils.t.e("OnTouch", "ACTION_UP", str3);
            Iterator<String> it4 = com.jd.dynamic.lib.utils.s.i(str3).iterator();
            while (it4.hasNext()) {
                com.jd.dynamic.lib.utils.s.b(it4.next(), view, this.a, view);
            }
        }
        if (!TextUtils.isEmpty(str4)) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean o(List list, final View view, View view2) {
        Observable.from(list).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.h
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.this.t(view, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.p
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.r((Throwable) obj);
            }
        });
        DynamicMtaUtil.uploadActionMta(this.a.getTemplate(), 2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(View view, String str) {
        int i2 = R.id.dynamic_listen_vertical_visibility_change;
        Object tag = view.getTag(i2);
        VisibilityChangeListener visibilityChangeListener = tag instanceof VisibilityChangeListener ? (VisibilityChangeListener) tag : null;
        VisibilityChangeListener2 visibilityChangeListener2 = tag instanceof VisibilityChangeListener2 ? (VisibilityChangeListener2) tag : null;
        View scrollView = visibilityChangeListener != null ? visibilityChangeListener.getScrollView() : visibilityChangeListener2 != null ? visibilityChangeListener2.getScrollView() : com.jd.dynamic.lib.utils.m.V(view);
        if (scrollView instanceof CollectionView) {
            if (visibilityChangeListener != null) {
                ((CollectionView) scrollView).getRecyclerView().removeOnScrollListener(visibilityChangeListener);
            }
            CollectionView collectionView = (CollectionView) scrollView;
            VisibilityChangeListener visibilityChangeListener3 = new VisibilityChangeListener(view, collectionView, null, str, this.b, this.a);
            collectionView.getRecyclerView().addOnScrollListener(visibilityChangeListener3);
            view.setTag(i2, visibilityChangeListener3);
        }
        if (scrollView instanceof com.jd.dynamic.lib.viewparse.b.carouselView.f) {
            com.jd.dynamic.lib.viewparse.b.carouselView.f fVar = (com.jd.dynamic.lib.viewparse.b.carouselView.f) scrollView;
            m.c f2 = com.jd.dynamic.lib.utils.m.f(fVar);
            if (1 == f2.f2274c) {
                if (visibilityChangeListener2 != null) {
                    f2.a.unregisterOnPageChangeCallback(visibilityChangeListener2);
                }
                VisibilityChangeListener2 visibilityChangeListener22 = new VisibilityChangeListener2(view, fVar, str, this.b, this.a);
                f2.a.registerOnPageChangeCallback(visibilityChangeListener22);
                view.setTag(i2, visibilityChangeListener22);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void r(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(List list, final View view, View view2) {
        Observable.from(list).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.e
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.this.v(view, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.n
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.u((Throwable) obj);
            }
        });
        DynamicMtaUtil.uploadActionMta(this.a.getTemplate(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(View view, String str) {
        com.jd.dynamic.lib.utils.s.b(str, view, this.a, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(View view, String str) {
        com.jd.dynamic.lib.utils.s.b(str, view, this.a, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void w(Throwable th) {
    }

    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    public void a(HashMap<String, String> hashMap, final View view) {
        if (view.getTag(R.id.dynamic_lifecycle_observer) == null) {
            final HashMap hashMap2 = new HashMap();
            Observable.from(hashMap.entrySet()).filter(new Func1() { // from class: com.jd.dynamic.lib.viewparse.c.e.o
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    Boolean f2;
                    f2 = g0.f((Map.Entry) obj);
                    return f2;
                }
            }).subscribe(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.j
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g0.m(hashMap2, (Map.Entry) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.i
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    g0.w((Throwable) obj);
                }
            }, new Action0() { // from class: com.jd.dynamic.lib.viewparse.c.e.l
                @Override // rx.functions.Action0
                public final void call() {
                    g0.this.l(hashMap2, view);
                }
            });
        }
        final HashMap hashMap3 = new HashMap();
        Observable.from(hashMap.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.this.k(hashMap3, view, (Map.Entry) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                g0.j((Throwable) obj);
            }
        });
        if (com.jd.dynamic.lib.utils.m.J(hashMap3)) {
            final String str = (String) hashMap3.get(DYConstants.DY_ON_TOUCH_DOWN);
            final String str2 = (String) hashMap3.get(DYConstants.DY_ON_TOUCH_UP);
            final String str3 = (String) hashMap3.get(DYConstants.DY_ON_TOUCH_MOVE);
            final String str4 = (String) hashMap3.get(DYConstants.DY_ON_TOUCH);
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.dynamic.lib.viewparse.c.e.f
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    boolean n2;
                    n2 = g0.this.n(str, view, str3, str2, str4, view2, motionEvent);
                    return n2;
                }
            });
        }
        if (TextUtils.equals(hashMap.get("userInteractionEnabled"), "0")) {
            view.setClickable(false);
            view.setLongClickable(false);
        }
        String str5 = hashMap.get("onVerticalVisibilityChange");
        String str6 = hashMap.get("onHorizontalVisibilityChange");
        if (TextUtils.isEmpty(str5)) {
            int i2 = R.id.dynamic_listen_vertical_visibility_change_event;
            if (view.getTag(i2) instanceof String) {
                str5 = (String) view.getTag(i2);
            }
        }
        if (TextUtils.isEmpty(str6)) {
            int i3 = R.id.dynamic_listen_horizontal_visibility_change_event;
            if (view.getTag(i3) instanceof String) {
                str6 = (String) view.getTag(i3);
            }
        }
        if (!TextUtils.isEmpty(str5)) {
            view.setTag(R.id.dynamic_listen_vertical_visibility_change_event, str5);
            if (com.jd.dynamic.lib.utils.m.V(view) == null) {
                view.post(new a(view, str5));
            } else {
                p(view, str5);
            }
        }
        if (TextUtils.isEmpty(str6)) {
            return;
        }
        view.setTag(R.id.dynamic_listen_horizontal_visibility_change_event, str6);
        if (com.jd.dynamic.lib.utils.m.M(view) == null) {
            view.post(new b(view, str6));
        } else {
            g(view, str6);
        }
    }
}
