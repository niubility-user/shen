package com.jingdong.app.mall.utils.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.jd.cashier.app.jdlibcutter.protocol.navigator.CashierNavigator;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.login.ILogin;

/* loaded from: classes4.dex */
public class b {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11814g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11815h;

        /* renamed from: com.jingdong.app.mall.utils.pay.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0386a implements ILogin {
            C0386a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    a aVar = a.this;
                    CashierNavigator.jumpToCreditPayPage(aVar.f11814g, aVar.f11815h);
                }
            }
        }

        a(Context context, Bundle bundle) {
            this.f11814g = context;
            this.f11815h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11814g, this.f11815h, new C0386a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.utils.pay.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0387b implements ILogin {
        final /* synthetic */ Activity a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f11816c;

        C0387b(Activity activity, Bundle bundle, int i2) {
            this.a = activity;
            this.b = bundle;
            this.f11816c = i2;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToCreditPayPage(this.a, this.b, this.f11816c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f11817g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11818h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f11819i;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    c cVar = c.this;
                    CashierNavigator.jumpToCreditPayPage(cVar.f11817g, cVar.f11818h, cVar.f11819i);
                }
            }
        }

        c(Activity activity, Bundle bundle, int i2) {
            this.f11817g = activity;
            this.f11818h = bundle;
            this.f11819i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11817g, this.f11818h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        d(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToQuickPayPage(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11820g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11821h;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    e eVar = e.this;
                    CashierNavigator.jumpToQuickPayPage(eVar.f11820g, eVar.f11821h);
                }
            }
        }

        e(Context context, Bundle bundle) {
            this.f11820g = context;
            this.f11821h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11820g, this.f11821h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements ILogin {
        final /* synthetic */ Activity a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f11822c;

        f(Activity activity, Bundle bundle, int i2) {
            this.a = activity;
            this.b = bundle;
            this.f11822c = i2;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToQuickPayPage(this.a, this.b, this.f11822c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f11823g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11824h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f11825i;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    g gVar = g.this;
                    CashierNavigator.jumpToQuickPayPage(gVar.f11823g, gVar.f11824h, gVar.f11825i);
                }
            }
        }

        g(Activity activity, Bundle bundle, int i2) {
            this.f11823g = activity;
            this.f11824h = bundle;
            this.f11825i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11823g, this.f11824h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        h(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToCashierRiskPage(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11826g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11827h;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    i iVar = i.this;
                    CashierNavigator.jumpToCashierRiskPage(iVar.f11826g, iVar.f11827h);
                }
            }
        }

        i(Context context, Bundle bundle) {
            this.f11826g = context;
            this.f11827h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11826g, this.f11827h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        j(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToFinishPage(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class k implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        k(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToPayPage(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class l implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11828g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11829h;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    l lVar = l.this;
                    CashierNavigator.jumpToPayPage(lVar.f11828g, lVar.f11829h);
                }
            }
        }

        l(Context context, Bundle bundle) {
            this.f11828g = context;
            this.f11829h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11828g, this.f11829h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class m implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11830g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11831h;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    m mVar = m.this;
                    CashierNavigator.jumpToFinishPage(mVar.f11830g, mVar.f11831h);
                }
            }
        }

        m(Context context, Bundle bundle) {
            this.f11830g = context;
            this.f11831h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11830g, this.f11831h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class n implements ILogin {
        final /* synthetic */ Activity a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f11832c;

        n(Activity activity, Bundle bundle, int i2) {
            this.a = activity;
            this.b = bundle;
            this.f11832c = i2;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToPayPage(this.a, this.b, this.f11832c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class o implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f11833g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11834h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f11835i;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    o oVar = o.this;
                    CashierNavigator.jumpToPayPage(oVar.f11833g, oVar.f11834h, oVar.f11835i);
                }
            }
        }

        o(Activity activity, Bundle bundle, int i2) {
            this.f11833g = activity;
            this.f11834h = bundle;
            this.f11835i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11833g, this.f11834h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class p implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        p(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToFriendPayPage(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class q implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11836g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11837h;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    q qVar = q.this;
                    CashierNavigator.jumpToFriendPayPage(qVar.f11836g, qVar.f11837h);
                }
            }
        }

        q(Context context, Bundle bundle) {
            this.f11836g = context;
            this.f11837h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11836g, this.f11837h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class r implements ILogin {
        final /* synthetic */ Activity a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f11838c;

        r(Activity activity, Bundle bundle, int i2) {
            this.a = activity;
            this.b = bundle;
            this.f11838c = i2;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToFriendPayPage(this.a, this.b, this.f11838c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class s implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Activity f11839g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f11840h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f11841i;

        /* loaded from: classes4.dex */
        class a implements ILogin {
            a() {
            }

            @Override // com.jingdong.common.login.ILogin
            public void onSuccess(String str) {
                if ("cashier_host_login".equals(str)) {
                    s sVar = s.this;
                    CashierNavigator.jumpToFriendPayPage(sVar.f11839g, sVar.f11840h, sVar.f11841i);
                }
            }
        }

        s(Activity activity, Bundle bundle, int i2) {
            this.f11839g = activity;
            this.f11840h = bundle;
            this.f11841i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkLoginHelper.startLoginActivity(this.f11839g, this.f11840h, new a(), "cashier_host_login");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class t implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        t(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("cashier_host_login".equals(str)) {
                CashierNavigator.jumpToCreditPayPage(this.a, this.b);
            }
        }
    }

    public static synchronized void a(int i2, Context context, Bundle bundle, int i3) {
        synchronized (b.class) {
            if (i2 != 1024) {
                if (i2 != 2048) {
                    if (i2 != 3072) {
                        if (i2 != 4096) {
                            if (i2 == 5120) {
                                d(context, bundle);
                            } else if (i2 == 8192) {
                                k(context, bundle);
                            }
                        } else if ((context instanceof Activity) && i3 > 0 && i3 != 404) {
                            e((Activity) context, bundle, i3);
                        } else {
                            f(context, bundle);
                        }
                    } else if ((context instanceof Activity) && i3 > 0 && i3 != 404) {
                        i((Activity) context, bundle, i3);
                    } else {
                        j(context, bundle);
                    }
                } else if ((context instanceof Activity) && i3 > 0 && i3 != 404) {
                    g((Activity) context, bundle, i3);
                } else {
                    h(context, bundle);
                }
            } else if ((context instanceof Activity) && i3 > 0 && i3 != 404) {
                b((Activity) context, bundle, i3);
            } else {
                c(context, bundle);
            }
        }
    }

    public static synchronized void b(Activity activity, Bundle bundle, int i2) {
        synchronized (b.class) {
            if (activity != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(activity, bundle, new n(activity, bundle, i2), "cashier_host_login");
                } else {
                    activity.runOnUiThread(new o(activity, bundle, i2));
                }
            }
        }
    }

    public static synchronized void c(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new k(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new l(context, bundle));
                }
            }
        }
    }

    public static synchronized void d(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new j(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new m(context, bundle));
                }
            }
        }
    }

    public static synchronized void e(Activity activity, Bundle bundle, int i2) {
        synchronized (b.class) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        DeepLinkLoginHelper.startLoginActivity(activity, bundle, new C0387b(activity, bundle, i2), "cashier_host_login");
                    } else {
                        activity.runOnUiThread(new c(activity, bundle, i2));
                    }
                }
            }
        }
    }

    public static synchronized void f(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new t(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new a(context, bundle));
                }
            }
        }
    }

    public static synchronized void g(Activity activity, Bundle bundle, int i2) {
        synchronized (b.class) {
            if (activity != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(activity, bundle, new r(activity, bundle, i2), "cashier_host_login");
                } else {
                    activity.runOnUiThread(new s(activity, bundle, i2));
                }
            }
        }
    }

    public static synchronized void h(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new p(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new q(context, bundle));
                }
            }
        }
    }

    public static synchronized void i(Activity activity, Bundle bundle, int i2) {
        synchronized (b.class) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        DeepLinkLoginHelper.startLoginActivity(activity, bundle, new f(activity, bundle, i2), "cashier_host_login");
                    } else {
                        activity.runOnUiThread(new g(activity, bundle, i2));
                    }
                }
            }
        }
    }

    public static synchronized void j(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new d(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new e(context, bundle));
                }
            }
        }
    }

    public static synchronized void k(Context context, Bundle bundle) {
        synchronized (b.class) {
            if (context != null) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    DeepLinkLoginHelper.startLoginActivity(context, bundle, new h(context, bundle), "cashier_host_login");
                } else {
                    new Handler(Looper.getMainLooper()).post(new i(context, bundle));
                }
            }
        }
    }
}
