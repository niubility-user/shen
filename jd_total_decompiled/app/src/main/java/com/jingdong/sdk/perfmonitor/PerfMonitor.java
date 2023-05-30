package com.jingdong.sdk.perfmonitor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.Trace;
import com.jingdong.sdk.perfmonitor.b.a;
import com.jingdong.sdk.perfmonitor.b.c;
import com.jingdong.sdk.perfmonitor.b.d;
import com.jingdong.sdk.perfmonitor.b.e;
import com.jingdong.sdk.perfmonitor.b.f;
import com.jingdong.sdk.perfmonitor.b.g;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes12.dex */
public final class PerfMonitor {

    /* renamed from: j  reason: collision with root package name */
    private static volatile PerfMonitor f15266j;
    private JDPerfActivityLifecycleCallbacks a;
    private FragmentManager.FragmentLifecycleCallbacks b;

    /* renamed from: c  reason: collision with root package name */
    private a f15267c;
    private c d;

    /* renamed from: e  reason: collision with root package name */
    private d f15268e;

    /* renamed from: f  reason: collision with root package name */
    private f f15269f;

    /* renamed from: g  reason: collision with root package name */
    private String f15270g;

    /* renamed from: h  reason: collision with root package name */
    private ConcurrentHashMap<String, Trace> f15271h;

    /* renamed from: i  reason: collision with root package name */
    private g f15272i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.perfmonitor.PerfMonitor$5  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.p.values().length];
            a = iArr;
            try {
                iArr[e.p.STARTUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.p.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class JDPerfActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        JDPerfActivityLifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
            PerfMonitor.this.e(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            PerfMonitor.this.f(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
            PerfMonitor.this.h(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            PerfMonitor.this.i(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            PerfMonitor.this.j(activity, bundle);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
            PerfMonitor.this.k(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
            PerfMonitor.this.l(activity);
        }
    }

    private PerfMonitor() {
    }

    private void g(String str) {
        c cVar;
        if (TextUtils.isEmpty(str) || (cVar = this.d) == null) {
            return;
        }
        cVar.S(str);
    }

    public static PerfMonitor getInstance() {
        if (f15266j == null) {
            synchronized (PerfMonitor.class) {
                if (f15266j == null) {
                    f15266j = new PerfMonitor();
                }
            }
        }
        return f15266j;
    }

    public void addCustomReport(HashMap<String, String> hashMap) {
        d dVar = this.f15268e;
        if (dVar != null) {
            dVar.G(hashMap);
        }
    }

    public void addExtraStr(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || str2 == null || str3 == null || str2.length() > 20) {
            return;
        }
        if (str3.length() > 500) {
            str3 = str3.substring(0, 500);
        }
        a aVar = this.f15267c;
        if (aVar != null && aVar.D(str)) {
            this.f15267c.l(str2, str3);
            return;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.G(str, str2, str3);
        }
    }

    public void addTraceTime(@Nullable String str, @NonNull String str2, long j2) {
        if (str == null) {
            return;
        }
        a aVar = this.f15267c;
        if (aVar != null && aVar.D(str)) {
            this.f15267c.m(str2, j2);
            return;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.I(str, str2, j2);
        }
    }

    void e(@NonNull Activity activity) {
        a aVar = this.f15267c;
        if (aVar != null && aVar.J(activity)) {
            this.f15267c.K(activity);
            this.f15267c.s();
        }
        this.f15270g = null;
        c cVar = this.d;
        if (cVar != null) {
            cVar.O(activity);
        }
        if ((activity instanceof FragmentActivity) && this.b != null) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.b, true);
        }
        ConcurrentHashMap<String, Trace> concurrentHashMap = this.f15271h;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        d dVar = this.f15268e;
        if (dVar == null || !dVar.M(activity)) {
            return;
        }
        this.f15268e.N(activity);
    }

    void f(@NonNull Activity activity) {
        if ((activity instanceof FragmentActivity) && this.b != null) {
            ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.b);
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.P(activity);
        }
    }

    public void fragmentInit(Fragment fragment, String str) {
        c cVar;
        if (fragment == null || str == null || (cVar = this.d) == null) {
            return;
        }
        cVar.R(fragment, str);
    }

    public Trace getTrace(@NonNull String str) {
        ConcurrentHashMap<String, Trace> concurrentHashMap = this.f15271h;
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(str);
    }

    void h(@NonNull Activity activity) {
        a aVar = this.f15267c;
        if (aVar != null) {
            if (aVar.J(activity)) {
                this.f15267c.u();
                if (AnonymousClass5.a[this.f15267c.H(activity).ordinal()] == 1) {
                    this.f15267c.t(e.p.STARTUP);
                }
            }
            this.f15267c.C(activity, System.currentTimeMillis());
        }
        d dVar = this.f15268e;
        if (dVar != null && dVar.M(activity)) {
            this.f15268e.j();
            this.f15268e.H();
        }
        f fVar = this.f15269f;
        if (fVar == null || !fVar.q(activity)) {
            return;
        }
        this.f15269f.j();
        this.f15269f.p();
    }

    void i(@NonNull Activity activity) {
        a aVar = this.f15267c;
        if (aVar != null && aVar.J(activity)) {
            this.f15267c.y();
            if (AnonymousClass5.a[this.f15267c.H(activity).ordinal()] == 2) {
                this.f15267c.v(e.p.AUTO);
            }
        }
        d dVar = this.f15268e;
        if (dVar != null && dVar.M(activity)) {
            this.f15268e.L(activity);
        }
        f fVar = this.f15269f;
        if (fVar == null || !fVar.q(activity)) {
            return;
        }
        this.f15269f.r(activity);
    }

    @Deprecated
    public void install(Application application) {
        install(application, false);
    }

    void j(@NonNull Activity activity, Bundle bundle) {
    }

    void k(@NonNull Activity activity) {
        a aVar = this.f15267c;
        if (aVar == null || !aVar.J(activity)) {
            return;
        }
        this.f15267c.z();
    }

    void l(@NonNull Activity activity) {
    }

    public Trace newTrace(@Nullable String str, @NonNull final String str2) {
        a aVar;
        Trace trace = new Trace((str == null || (aVar = this.f15267c) == null || !aVar.D(str)) ? null : this.f15267c, str2, new Trace.OnTraceEvent() { // from class: com.jingdong.sdk.perfmonitor.PerfMonitor.4
            @Override // com.jingdong.sdk.perfmonitor.Trace.OnTraceEvent
            public void onTraceStart(long j2) {
            }

            @Override // com.jingdong.sdk.perfmonitor.Trace.OnTraceEvent
            public void onTraceStop(long j2) {
                if (PerfMonitor.this.f15271h != null) {
                    PerfMonitor.this.f15271h.remove(str2);
                }
            }
        });
        ConcurrentHashMap<String, Trace> concurrentHashMap = this.f15271h;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(str2, trace);
        }
        return trace;
    }

    @Deprecated
    public void onRender(String str) {
        a aVar;
        if (str == null || (aVar = this.f15267c) == null || !aVar.D(str)) {
            return;
        }
        this.f15267c.v(e.p.STARTUP);
    }

    public void onRequest(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        a aVar = this.f15267c;
        if (aVar != null && aVar.D(str)) {
            this.f15267c.w(str2);
            return;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.T(str, str2);
        }
    }

    public void onResponse(String str, String str2) {
        onResponse(str, str2, 0, "");
    }

    public void onUnitRequest(String str, String str2) {
        g gVar;
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || (gVar = this.f15272i) == null) {
            return;
        }
        gVar.b(str, str2);
    }

    public void onUnitResponse(String str, String str2) {
        onUnitResponse(str, str2, 0, "");
    }

    public void pageTrace(Fragment fragment, String str, long j2) {
    }

    public void refreshCurrentWebViewUrl(String str) {
        this.f15270g = str;
    }

    public void remove(String str) {
        a aVar;
        if (str == null || (aVar = this.f15267c) == null || !aVar.D(str)) {
            return;
        }
        this.f15267c.n();
    }

    public void setUserVisibleHint(Fragment fragment, boolean z) {
        if (fragment == null) {
            return;
        }
        c cVar = this.d;
        if (cVar != null && !z && cVar.L(fragment)) {
            this.d.u();
        }
        if (z || !fragment.isResumed() || fragment.getActivity() == null) {
            return;
        }
        remove(fragment.getActivity().getClass().getName());
    }

    public void uninstall(Application application) {
        JDPerfActivityLifecycleCallbacks jDPerfActivityLifecycleCallbacks = this.a;
        if (jDPerfActivityLifecycleCallbacks != null) {
            application.unregisterActivityLifecycleCallbacks(jDPerfActivityLifecycleCallbacks);
            this.a = null;
        }
        this.f15267c = null;
        this.f15268e = null;
        this.f15269f = null;
        this.f15271h = null;
        this.f15272i = null;
        this.b = null;
    }

    public void install(Application application, boolean z) {
        if (application == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 16) {
            OKLog.w("PerfMonitor", "\u4e0d\u652f\u6301Android 5.0\u4ee5\u4e0b\u7684\u7cfb\u7edf");
        } else if (!z) {
            OKLog.d("PerfMonitor", "\u6027\u80fd\u76d1\u63a7\u5f00\u5173\u5173\u95ed");
        } else {
            final Reporter reporter = new Reporter(application);
            this.f15267c = new a(application, reporter);
            this.f15268e = new d(application, reporter);
            this.d = new c(application, reporter);
            this.f15269f = new f(application, reporter, new f.c() { // from class: com.jingdong.sdk.perfmonitor.PerfMonitor.1
                @Override // com.jingdong.sdk.perfmonitor.b.f.c
                public String getUrl() {
                    return PerfMonitor.this.f15270g;
                }
            });
            JDPerfActivityLifecycleCallbacks jDPerfActivityLifecycleCallbacks = new JDPerfActivityLifecycleCallbacks();
            this.a = jDPerfActivityLifecycleCallbacks;
            application.registerActivityLifecycleCallbacks(jDPerfActivityLifecycleCallbacks);
            ProcessLifecycleOwner.get().getLifecycle().addObserver(new LifecycleObserver() { // from class: com.jingdong.sdk.perfmonitor.PerfMonitor.2
                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                public void onAppGoBackground() {
                    reporter.flush();
                    if (PerfMonitor.this.f15267c != null) {
                        PerfMonitor.this.f15267c.o();
                    }
                }
            });
            this.f15271h = new ConcurrentHashMap<>();
            this.f15272i = new g(reporter);
            this.b = new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.jingdong.sdk.perfmonitor.PerfMonitor.3
                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
                    super.onFragmentCreated(fragmentManager, fragment, bundle);
                    if (PerfMonitor.this.d != null && PerfMonitor.this.d.L(fragment)) {
                        PerfMonitor.this.d.s();
                    }
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentDestroyed(fragmentManager, fragment);
                    if (PerfMonitor.this.d != null) {
                        PerfMonitor.this.d.Q(fragment);
                    }
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentDetached(fragmentManager, fragment);
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentPaused(fragmentManager, fragment);
                    if (PerfMonitor.this.d != null && PerfMonitor.this.d.L(fragment)) {
                        PerfMonitor.this.d.u();
                    }
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
                    super.onFragmentPreAttached(fragmentManager, fragment, context);
                    if (PerfMonitor.this.f15267c == null) {
                        return;
                    }
                    if (fragment.getUserVisibleHint()) {
                        PerfMonitor.this.f15267c.B(c.K(fragment));
                    }
                    if (PerfMonitor.this.d == null || !PerfMonitor.this.d.M(fragment.getActivity()) || PerfMonitor.this.d.N(fragment)) {
                        return;
                    }
                    if (fragment.getUserVisibleHint() && PerfMonitor.this.d.V(fragment)) {
                        PerfMonitor.this.d.W(fragment);
                    }
                    PerfMonitor.this.d.H(fragment);
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentResumed(fragmentManager, fragment);
                    if (PerfMonitor.this.d != null && PerfMonitor.this.d.L(fragment)) {
                        PerfMonitor.this.d.y();
                    }
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentStarted(fragmentManager, fragment);
                    if (PerfMonitor.this.d != null && PerfMonitor.this.d.L(fragment)) {
                        PerfMonitor.this.d.z();
                    }
                }

                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                    super.onFragmentStopped(fragmentManager, fragment);
                }
            };
        }
    }

    public void onResponse(String str, String str2, int i2, String str3) {
        if (str == null || str2 == null) {
            return;
        }
        a aVar = this.f15267c;
        if (aVar != null && aVar.D(str)) {
            this.f15267c.x(str2, i2, str3);
            return;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.U(str, str2, i2, str3);
        }
    }

    public void onUnitResponse(String str, String str2, int i2, String str3) {
        g gVar;
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || (gVar = this.f15272i) == null) {
            return;
        }
        gVar.c(str, str2, i2, str3);
    }

    public void onRender(Context context) {
        a aVar = this.f15267c;
        if (aVar != null) {
            aVar.I(context);
        }
    }

    @Deprecated
    public void addTraceTime(@NonNull String str, long j2) {
        addTraceTime(null, str, j2);
    }

    @Deprecated
    public Trace newTrace(@NonNull String str) {
        return newTrace(null, str);
    }

    public void onRender(Context context, String str) {
        onRender(context);
        g(str);
    }
}
