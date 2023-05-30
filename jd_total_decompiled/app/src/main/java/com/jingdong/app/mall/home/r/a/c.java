package com.jingdong.app.mall.home.r.a;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.i;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes4.dex */
public class c {
    public static float t = 1.0f;
    private final TreeSet<com.jingdong.app.mall.home.r.a.a> a = new TreeSet<>(new d());
    private final TreeSet<com.jingdong.app.mall.home.r.a.b> b = new TreeSet<>(new d());

    /* renamed from: c */
    private final List<com.jingdong.app.mall.home.r.a.a> f10591c = new LinkedList();
    protected Set<String> d = new HashSet();

    /* renamed from: e */
    protected Set<String> f10592e = new HashSet();

    /* renamed from: f */
    protected int f10593f = 0;

    /* renamed from: g */
    protected int f10594g = 0;

    /* renamed from: h */
    protected int f10595h = 1;

    /* renamed from: i */
    protected int f10596i = 1;

    /* renamed from: j */
    protected boolean f10597j = false;

    /* renamed from: k */
    protected boolean f10598k = false;

    /* renamed from: l */
    protected boolean f10599l = false;

    /* renamed from: m */
    public int f10600m = 0;

    /* renamed from: n */
    public int f10601n = 0;
    private boolean o = false;
    private ConcurrentHashMap<String, Integer> p = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> q = new ConcurrentHashMap<>();
    private boolean r = true;
    private final f s = new f();

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
            c.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            c cVar = c.this;
            cVar.q(cVar.f10600m, cVar.f10601n, -1);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Animator.AnimatorListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.home.r.a.a f10603g;

        /* renamed from: h */
        final /* synthetic */ int f10604h;

        /* renamed from: i */
        final /* synthetic */ int f10605i;

        /* renamed from: j */
        final /* synthetic */ int f10606j;

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {
            a() {
                b.this = r1;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                boolean z = true;
                if (Log.D) {
                    com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "playNextAnimation: onAnimationEnd:", b.this.f10603g);
                }
                synchronized (c.this.f10591c) {
                    c.this.f10591c.remove(b.this.f10603g);
                }
                synchronized (c.this.s) {
                    if (c.this.s.a) {
                        return;
                    }
                    synchronized (c.this.a) {
                        Iterator it = c.this.a.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            } else if (((com.jingdong.app.mall.home.r.a.a) it.next()).equals(b.this.f10603g)) {
                                break;
                            }
                        }
                        if (!z) {
                            it = c.this.a.iterator();
                        }
                        b bVar = b.this;
                        c.this.A(it, bVar.f10604h, bVar.f10605i, bVar.f10606j);
                    }
                }
            }
        }

        b(com.jingdong.app.mall.home.r.a.a aVar, int i2, int i3, int i4) {
            c.this = r1;
            this.f10603g = aVar;
            this.f10604h = i2;
            this.f10605i = i3;
            this.f10606j = i4;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (Log.D) {
                com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "playNextAnimation: animator:", this.f10603g);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            com.jingdong.app.mall.home.o.a.f.u0(new a());
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (animator != null) {
                if (Log.D) {
                    com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "playNextAnimation: onAnimationStart:", this.f10603g);
                }
                c.this.E(this.f10603g.getModelId());
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.home.r.a.c$c */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class C0316c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.values().length];
            a = iArr;
            try {
                iArr[e.Bereplaced.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.IsDictator.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[e.PlayOrderBy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[e.Other.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class d implements Comparator<com.jingdong.app.mall.home.r.a.a> {
        d() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(com.jingdong.app.mall.home.r.a.a aVar, com.jingdong.app.mall.home.r.a.a aVar2) {
            if (aVar == aVar2) {
                return 0;
            }
            if (aVar == null) {
                return -1;
            }
            if (aVar2 == null) {
                return 1;
            }
            int priority = aVar.getPriority();
            int priority2 = aVar2.getPriority();
            if (priority < priority2) {
                return -1;
            }
            if (priority != priority2 || ((aVar.isDictator() && aVar2.isDictator()) || aVar.isDictator())) {
                return 1;
            }
            return (!aVar2.isDictator() && aVar.getFloorPos() >= aVar2.getFloorPos()) ? 1 : -1;
        }
    }

    /* loaded from: classes4.dex */
    public enum e {
        IsDictator,
        PlayOrderBy,
        Bereplaced,
        Other
    }

    /* loaded from: classes4.dex */
    public static class f {
        boolean a = false;

        f() {
        }
    }

    static {
        new CopyOnWriteArraySet();
    }

    public c() {
        try {
            float floatValue = ((Float) ValueAnimator.class.getDeclaredMethod("getDurationScale", new Class[0]).invoke(new ValueAnimator(), new Object[0])).floatValue();
            t = floatValue <= 0.0f ? 1.0f : floatValue;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:427:0x011d, code lost:
        N(false);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.jingdong.app.mall.home.r.a.a] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void A(Iterator<com.jingdong.app.mall.home.r.a.a> it, int i2, int i3, int i4) {
        com.jingdong.app.mall.home.r.a.b bVar;
        if (it == null || u()) {
            return;
        }
        while (true) {
            synchronized (this.a) {
                try {
                    bVar = it.hasNext() ? it.next() : null;
                } catch (ConcurrentModificationException unused) {
                    if (Log.D) {
                        Log.d("MallHomeAnimationCtrl", "playNextAnimation ConcurrentModificationException");
                    }
                    bVar = null;
                }
            }
            if (bVar == null) {
                return;
            }
            com.jingdong.app.mall.home.r.a.b bVar2 = bVar instanceof com.jingdong.app.mall.home.r.a.b ? bVar : null;
            if (Log.D) {
                com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "playNextAnimation:", bVar);
            }
            if ((bVar2 == null || !bVar2.isSplashAnimation()) && (this.o || i.i())) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation:mXViewShowed || mSplashFragmentShowed");
                }
            } else if (i4 >= 0 && bVar.getPriority() != i4) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation:(priority >= 0) && (animation.getPriority() != priority)");
                }
            } else if (bVar2 != null && bVar2.isNeedWait()) {
                g(bVar2);
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation:isNeedWait");
                    return;
                }
                return;
            } else if (!bVar.isInDisplayArea(i2, i3)) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation: not in display area");
                }
            } else if (!bVar.isMatchOtherStartCondition()) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation: not match other condition");
                }
                if (bVar2 != null && bVar2.isSplashAnimation()) {
                    bVar2.stopPlay();
                }
            } else if (x(bVar.getPriority())) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "playNextAnimation: orderby item playing");
                }
            } else {
                int i5 = C0316c.a[bVar.getType().ordinal()];
                if (i5 != 1 && i5 != 2) {
                    if (i5 == 3) {
                        break;
                    }
                    synchronized (this.f10591c) {
                        if (!this.f10591c.contains(bVar)) {
                            bVar.startPlay();
                            this.f10591c.add(bVar);
                            synchronized (this.b) {
                                this.b.remove(bVar);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }
        bVar.addAnimatorListener(new b(bVar, i2, i3, i4));
        synchronized (this.f10591c) {
            if (!this.f10591c.contains(bVar)) {
                bVar.startPlay();
                this.f10591c.add(bVar);
                synchronized (this.b) {
                    this.b.remove(bVar);
                }
            }
        }
    }

    public static void F(String str, String str2) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        Set<String> stringSet = jdSharedPreferences.getStringSet(str2, null);
        HashSet hashSet = new HashSet();
        if (stringSet != null) {
            hashSet.addAll(stringSet);
        }
        hashSet.add(str);
        jdSharedPreferences.edit().putStringSet(str2, hashSet).apply();
    }

    private int H(int i2) {
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public static boolean s(String str) {
        return t(str, "MallHomeFlipperTextView");
    }

    public static boolean t(String str, String str2) {
        Set<String> stringSet = CommonBase.getJdSharedPreferences().getStringSet(str2, null);
        if (stringSet == null) {
            return false;
        }
        return stringSet.contains(str);
    }

    private boolean u() {
        synchronized (this.f10591c) {
            int size = this.f10591c.size();
            for (int i2 = 0; i2 < size; i2++) {
                com.jingdong.app.mall.home.r.a.a aVar = this.f10591c.get(i2);
                if (aVar.isDictator()) {
                    if (aVar.isInDisplayArea(this.f10600m, this.f10601n)) {
                        return true;
                    }
                    aVar.stopPlay();
                    return false;
                }
            }
            return false;
        }
    }

    private boolean v(int i2, int i3, int i4, int i5) {
        int i6;
        if (i3 < 0 || i2 >= i3) {
            if (i4 < 0 || i2 < i4) {
                synchronized (this.a) {
                    Iterator<com.jingdong.app.mall.home.r.a.a> descendingIterator = this.a.descendingIterator();
                    i6 = 0;
                    while (descendingIterator != null && descendingIterator.hasNext()) {
                        if (descendingIterator.next().getPriority() == i2) {
                            i6++;
                        }
                    }
                }
                return i6 >= i5;
            }
            return false;
        }
        return false;
    }

    private boolean x(int i2) {
        synchronized (this.f10591c) {
            int size = this.f10591c.size();
            for (int i3 = 0; i3 < size; i3++) {
                com.jingdong.app.mall.home.r.a.a aVar = this.f10591c.get(i3);
                if (aVar.getType() == e.PlayOrderBy) {
                    if (i2 >= aVar.getPriority()) {
                        return true;
                    }
                    aVar.stopPlay();
                    return false;
                }
            }
            return false;
        }
    }

    public void B() {
        Set<String> stringSet = CommonBase.getJdSharedPreferences().getStringSet("MallHomeFlipperTextView", null);
        if (stringSet == null) {
            return;
        }
        for (String str : stringSet) {
            if (Log.D) {
                com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "prepareToRefreshAnimationState1:", str);
            }
            Set<String> set = this.d;
            if ((set != null && set.contains(str)) || this.f10592e.contains(str)) {
                if (Log.D) {
                    com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "prepareToRefreshAnimationState2:", str);
                }
                this.f10592e.remove(str);
                this.d.add(str);
            } else {
                this.f10592e.add(str);
            }
        }
    }

    public void C(int i2, int i3) {
        this.f10600m = i2;
        this.f10601n = i3;
    }

    public void D() {
        this.f10598k = false;
    }

    public void E(String str) {
        F(str, "MallHomeFlipperTextView");
        this.d.add(str);
        this.f10592e.remove(str);
    }

    public void G(int i2) {
        synchronized (this.s) {
            this.s.a = true;
        }
        synchronized (this.f10591c) {
            int size = this.f10591c.size();
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < size; i3++) {
                com.jingdong.app.mall.home.r.a.a aVar = this.f10591c.get(i3);
                aVar.pause(i2);
                e type = aVar.getType();
                if (type != e.IsDictator && type != e.PlayOrderBy) {
                    arrayList.add(aVar);
                }
            }
            this.f10591c.removeAll(arrayList);
        }
        this.f10598k = true;
        if (Log.D) {
            Log.i("MallHomeAnimationCtrl", "pauseAll");
        }
    }

    public void I(int i2) {
        this.f10594g = H(i2);
    }

    public void J() {
        this.f10597j = true;
    }

    public void K(String str, int i2) {
        L(this.p, str, i2);
    }

    protected void L(ConcurrentHashMap<String, Integer> concurrentHashMap, String str, int i2) {
        concurrentHashMap.put(str, Integer.valueOf(i2));
    }

    public void M(int i2) {
        this.f10593f = H(i2);
    }

    public void N(boolean z) {
        synchronized (this.f10591c) {
            for (int i2 = 0; i2 < this.f10591c.size(); i2++) {
                com.jingdong.app.mall.home.r.a.a aVar = this.f10591c.get(i2);
                aVar.addAnimatorListener(null);
                aVar.stopPlay();
            }
            this.f10591c.clear();
        }
        this.r = z;
        this.f10598k = true;
        if (Log.D) {
            Log.i("MallHomeAnimationCtrl", "stopAll:" + z);
        }
    }

    public void e(String str) {
        if (Log.D) {
            Log.i("MallHomeAnimationCtrl", "addModuleIdToCheckList:" + str);
        }
        this.d.add(m.g(str));
    }

    public boolean f(com.jingdong.app.mall.home.r.a.a aVar) {
        if (aVar == null || j(aVar)) {
            return false;
        }
        int priority = aVar.getPriority();
        if (aVar instanceof com.jingdong.app.mall.home.r.a.b) {
            this.f10599l |= ((com.jingdong.app.mall.home.r.a.b) aVar).isSplashAnimation();
        }
        if (v(priority, 2, 3, this.f10594g) || v(priority, 5, -1, this.f10593f) || v(priority, 1, 2, this.f10595h) || v(priority, 3, 4, this.f10596i)) {
            return false;
        }
        synchronized (this.a) {
            if (!this.a.contains(aVar)) {
                if (Log.D) {
                    Log.i("MallHomeAnimationCtrl", "addToAnimationTree:" + aVar);
                }
                this.a.add(aVar);
            }
        }
        return true;
    }

    public void g(com.jingdong.app.mall.home.r.a.b bVar) {
        synchronized (this.b) {
            if (!this.b.contains(bVar)) {
                this.b.add(bVar);
            }
        }
    }

    public boolean h(com.jingdong.app.mall.home.r.a.b bVar) {
        if (this.f10597j) {
            synchronized (this.b) {
                Iterator<com.jingdong.app.mall.home.r.a.b> descendingIterator = this.b.descendingIterator();
                if (descendingIterator != null && descendingIterator.hasNext() && descendingIterator.next() == bVar) {
                    p();
                }
            }
            return true;
        }
        return false;
    }

    public void i() {
        if (this.r && this.f10598k) {
            this.r = false;
            this.f10598k = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:126:0x0056, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0057, code lost:
        r7 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected boolean j(com.jingdong.app.mall.home.r.a.a aVar) {
        boolean z;
        boolean z2;
        int priority = aVar.getPriority();
        int subPriority = aVar instanceof com.jingdong.app.mall.home.r.a.b ? ((com.jingdong.app.mall.home.r.a.b) aVar).getSubPriority() : -1;
        e type = aVar.getType();
        String modelId = aVar.getModelId();
        synchronized (this.a) {
            com.jingdong.app.mall.home.r.a.a aVar2 = null;
            Iterator<com.jingdong.app.mall.home.r.a.a> descendingIterator = this.a.descendingIterator();
            while (true) {
                z = false;
                z2 = true;
                if (descendingIterator == null || !descendingIterator.hasNext()) {
                    break;
                }
                aVar2 = descendingIterator.next();
                if (type == e.Bereplaced && aVar2.getPriority() == priority) {
                    com.jingdong.app.mall.home.o.a.f.n(aVar2);
                    if (((com.jingdong.app.mall.home.r.a.b) aVar2).getSubPriority() > subPriority) {
                    }
                } else if (modelId != null && modelId.equals(aVar2.getModelId())) {
                    break;
                }
            }
            if (z && aVar2 != null) {
                aVar2.stopPlay();
                this.a.remove(aVar2);
                if (Log.D) {
                    Log.d("MallHomeAnimationCtrl", "checkCanBeReplace mPrepairTreeSet.remove");
                }
            }
        }
        if (Log.D) {
            Log.i("MallHomeAnimationCtrl", "checkCanBeReplace:" + z2);
        }
        return z2;
    }

    protected boolean k(ConcurrentHashMap<String, Integer> concurrentHashMap, int i2, int i3) {
        Set<Map.Entry<String, Integer>> entrySet = concurrentHashMap.entrySet();
        if (entrySet == null) {
            return true;
        }
        Iterator<Map.Entry<String, Integer>> it = entrySet.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            Integer value = it.next().getValue();
            if (value != null && i3 >= value.intValue()) {
                i4++;
            }
        }
        return i4 <= i2;
    }

    public boolean l(int i2) {
        return k(this.p, this.f10595h, i2);
    }

    public void m() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        Set<String> stringSet = jdSharedPreferences.getStringSet("MallHomeFlipperTextView", null);
        if (stringSet == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (String str : stringSet) {
            if (Log.D) {
                com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "clearPrepareClearKeySet:", str);
            }
            if (!this.f10592e.contains(str)) {
                if (Log.D) {
                    com.jingdong.app.mall.home.o.a.f.r0("MallHomeAnimationCtrl", "clearPrepareClearKeySet1:", str);
                }
                hashSet.add(str);
            }
        }
        jdSharedPreferences.edit().putStringSet("MallHomeFlipperTextView", hashSet).apply();
        this.f10592e.clear();
    }

    public void n() {
        N(false);
        TreeSet<com.jingdong.app.mall.home.r.a.a> treeSet = this.a;
        if (treeSet != null) {
            synchronized (treeSet) {
                this.a.clear();
            }
        }
        synchronized (this.b) {
            this.b.clear();
        }
        Set<String> set = this.d;
        if (set != null) {
            set.clear();
        }
        this.f10592e.clear();
        this.f10597j = false;
        this.f10598k = false;
        this.p.clear();
        this.q.clear();
    }

    public void o() {
        if (this.r) {
            return;
        }
        B();
        if (Log.D) {
            Log.i("MallHomeAnimationCtrl", "doRefreshStateAndPlayAnimation:" + this.f10598k + "-" + this.o + "-" + i.i());
        }
        if (this.f10598k || this.o || i.i()) {
            return;
        }
        p();
    }

    public void p() {
        com.jingdong.app.mall.home.o.a.f.u0(new a());
    }

    public void q(int i2, int i3, int i4) {
        if (!this.f10597j || this.r) {
            return;
        }
        synchronized (this.s) {
            this.s.a = false;
        }
        synchronized (this.a) {
            A(this.a.iterator(), i2, i3, i4);
        }
    }

    public boolean r(com.jingdong.app.mall.home.r.a.a aVar) {
        boolean contains;
        synchronized (this.a) {
            contains = this.a.contains(aVar);
        }
        return contains;
    }

    protected boolean w(ConcurrentHashMap<String, Integer> concurrentHashMap, String str) {
        return concurrentHashMap.get(str) != null;
    }

    public boolean y(String str) {
        return w(this.p, str);
    }

    public void z() {
        this.f10598k = false;
        this.r = false;
        p();
    }
}
