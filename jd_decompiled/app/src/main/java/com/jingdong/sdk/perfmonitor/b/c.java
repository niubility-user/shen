package com.jingdong.sdk.perfmonitor.b;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.b.e;
import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c extends com.jingdong.sdk.perfmonitor.b.e<com.jingdong.sdk.perfmonitor.d.c> {

    /* renamed from: j */
    private SoftReference<Fragment> f15313j;

    /* renamed from: k */
    private SparseArray<String> f15314k;

    /* renamed from: l */
    private SparseArray<List<Integer>> f15315l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f15316g;

        /* renamed from: h */
        final /* synthetic */ String f15317h;

        a(int i2, String str) {
            c.this = r1;
            this.f15316g = i2;
            this.f15317h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.f15314k == null) {
                c.this.f15314k = new SparseArray();
            }
            c.this.f15314k.put(this.f15316g, this.f15317h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f15319g;

        b(int i2) {
            c.this = r1;
            this.f15319g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.f15314k != null) {
                c.this.f15314k.remove(this.f15319g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.perfmonitor.b.c$c */
    /* loaded from: classes12.dex */
    public class RunnableC0741c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15321g;

        RunnableC0741c(String str) {
            c.this = r1;
            this.f15321g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment J = c.this.J();
            if (J != null) {
                String str = c.this.f15314k != null ? (String) c.this.f15314k.get(System.identityHashCode(J)) : null;
                if (str != null) {
                    if (str.equals(this.f15321g)) {
                        c.this.v(e.p.STARTUP);
                    }
                } else if (this.f15321g.equals(J.getClass().getName())) {
                    c.this.v(e.p.STARTUP);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15323g;

        /* renamed from: h */
        final /* synthetic */ String f15324h;

        d(String str, String str2) {
            c.this = r1;
            this.f15323g = str;
            this.f15324h = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment J = c.this.J();
            if (J != null) {
                String str = c.this.f15314k != null ? (String) c.this.f15314k.get(System.identityHashCode(J)) : null;
                if (str != null) {
                    if (str.equals(this.f15323g)) {
                        c.this.w(this.f15324h);
                    }
                } else if (this.f15323g.equals(J.getClass().getName())) {
                    c.this.w(this.f15324h);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class e implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15326g;

        /* renamed from: h */
        final /* synthetic */ String f15327h;

        /* renamed from: i */
        final /* synthetic */ int f15328i;

        /* renamed from: j */
        final /* synthetic */ String f15329j;

        e(String str, String str2, int i2, String str3) {
            c.this = r1;
            this.f15326g = str;
            this.f15327h = str2;
            this.f15328i = i2;
            this.f15329j = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment J = c.this.J();
            if (J != null) {
                String str = c.this.f15314k != null ? (String) c.this.f15314k.get(System.identityHashCode(J)) : null;
                if (str != null) {
                    if (str.equals(this.f15326g)) {
                        c.this.x(this.f15327h, this.f15328i, this.f15329j);
                    }
                } else if (this.f15326g.equals(J.getClass().getName())) {
                    c.this.x(this.f15327h, this.f15328i, this.f15329j);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class f implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15331g;

        /* renamed from: h */
        final /* synthetic */ String f15332h;

        /* renamed from: i */
        final /* synthetic */ String f15333i;

        f(String str, String str2, String str3) {
            c.this = r1;
            this.f15331g = str;
            this.f15332h = str2;
            this.f15333i = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar;
            com.jingdong.sdk.perfmonitor.a.b bVar2;
            Fragment J = c.this.J();
            if (J != null) {
                String str = c.this.f15314k != null ? (String) c.this.f15314k.get(System.identityHashCode(J)) : null;
                if (str != null) {
                    if (!str.equals(this.f15331g) || (bVar2 = c.this.f15351g) == null) {
                        return;
                    }
                    if (bVar2.f15286h == null) {
                        bVar2.f15286h = new JSONObject();
                    }
                    try {
                        c.this.f15351g.f15286h.put(this.f15332h, this.f15333i);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else if (!this.f15331g.equals(J.getClass().getName()) || (bVar = c.this.f15351g) == null) {
                } else {
                    if (bVar.f15286h == null) {
                        bVar.f15286h = new JSONObject();
                    }
                    try {
                        c.this.f15351g.f15286h.put(this.f15332h, this.f15333i);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class g implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15335g;

        /* renamed from: h */
        final /* synthetic */ String f15336h;

        /* renamed from: i */
        final /* synthetic */ long f15337i;

        g(String str, String str2, long j2) {
            c.this = r1;
            this.f15335g = str;
            this.f15336h = str2;
            this.f15337i = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.perfmonitor.a.b bVar;
            Map<String, Long> map;
            com.jingdong.sdk.perfmonitor.a.b bVar2;
            Map<String, Long> map2;
            Fragment J = c.this.J();
            if (J != null) {
                String str = c.this.f15314k != null ? (String) c.this.f15314k.get(System.identityHashCode(J)) : null;
                if (str != null) {
                    if (!str.equals(this.f15335g) || (bVar2 = c.this.f15351g) == null || (map2 = bVar2.f15288j) == null) {
                        return;
                    }
                    map2.put(this.f15336h, Long.valueOf(this.f15337i));
                } else if (!this.f15335g.equals(J.getClass().getName()) || (bVar = c.this.f15351g) == null || (map = bVar.f15288j) == null) {
                } else {
                    map.put(this.f15336h, Long.valueOf(this.f15337i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class h implements Runnable {

        /* renamed from: g */
        final /* synthetic */ long f15339g;

        h(long j2) {
            c.this = r1;
            this.f15339g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f15351g.f15283e = this.f15339g;
        }
    }

    public c(Context context, Reporter reporter) {
        super(reporter);
        this.b = new com.jingdong.sdk.perfmonitor.d.c(context);
    }

    public static String K(@NonNull Fragment fragment) {
        return fragment.getClass().getName();
    }

    public void G(String str, String str2, String str3) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new f(str, str2, str3));
    }

    public void H(Fragment fragment) {
        SparseArray<List<Integer>> sparseArray;
        List<Integer> list;
        if (fragment == null || fragment.getActivity() == null || (sparseArray = this.f15315l) == null || (list = sparseArray.get(System.identityHashCode(fragment.getActivity()))) == null) {
            return;
        }
        list.add(Integer.valueOf(System.identityHashCode(fragment)));
    }

    public void I(String str, String str2, long j2) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new g(str, str2, j2));
    }

    public Fragment J() {
        SoftReference<Fragment> softReference = this.f15313j;
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public boolean L(Fragment fragment) {
        SoftReference<Fragment> softReference = this.f15313j;
        return softReference != null && softReference.get() == fragment;
    }

    public boolean M(Activity activity) {
        T t = this.b;
        if (t != 0) {
            return ((com.jingdong.sdk.perfmonitor.d.c) t).c(com.jingdong.sdk.perfmonitor.b.b.d(activity));
        }
        return false;
    }

    public boolean N(Fragment fragment) {
        SparseArray<List<Integer>> sparseArray;
        List<Integer> list;
        return (fragment == null || fragment.getActivity() == null || (sparseArray = this.f15315l) == null || (list = sparseArray.get(System.identityHashCode(fragment.getActivity()))) == null || !list.contains(Integer.valueOf(System.identityHashCode(fragment)))) ? false : true;
    }

    public void O(Activity activity) {
        if (this.f15315l == null) {
            this.f15315l = new SparseArray<>();
        }
        this.f15315l.put(System.identityHashCode(activity), new LinkedList());
    }

    public void P(Activity activity) {
        SparseArray<List<Integer>> sparseArray = this.f15315l;
        if (sparseArray != null) {
            sparseArray.remove(System.identityHashCode(activity));
        }
    }

    public void Q(Fragment fragment) {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new b(System.identityHashCode(fragment)));
    }

    public void R(Fragment fragment, String str) {
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new a(System.identityHashCode(fragment), str));
    }

    public void S(String str) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new RunnableC0741c(str));
    }

    public void T(String str, String str2) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new d(str, str2));
    }

    public void U(String str, String str2, int i2, String str3) {
        Handler handler = this.f15353i;
        if (handler == null) {
            return;
        }
        handler.post(new e(str, str2, i2, str3));
    }

    public boolean V(Fragment fragment) {
        T t = this.b;
        if (t == 0) {
            return false;
        }
        return ((com.jingdong.sdk.perfmonitor.d.c) t).d(fragment);
    }

    public void W(Fragment fragment) {
        super.i(K(fragment));
        this.f15313j = new SoftReference<>(fragment);
        if (this.f15353i == null) {
            return;
        }
        this.f15353i.post(new h(System.currentTimeMillis()));
    }

    @Override // com.jingdong.sdk.perfmonitor.b.e
    public void u() {
        super.u();
        this.f15313j = null;
        t(e.p.STARTUP);
    }
}
