package com.jingdong;

import android.app.Application;
import android.content.Context;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.i;
import com.jingdong.manto.mainproc.IMainProcChannel;
import com.jingdong.manto.message.MantoAcrossMessageCenter;
import com.jingdong.manto.pkg.AppExecutors;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.sdk.IMantoSdkBase;
import com.jingdong.manto.sdk.MantoSdkManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class a {
    public static boolean a = false;
    public static String b = null;

    /* renamed from: c */
    public static boolean f7578c = true;
    private static d d;

    /* renamed from: e */
    private static IMainProcChannel f7579e;

    /* renamed from: com.jingdong.a$a */
    /* loaded from: classes18.dex */
    public static class C0232a {
        private Context a = null;

        public Context a() {
            if (a.d != null) {
                if (this.a == null) {
                    this.a = a.d.getContext();
                }
                Context context = this.a;
                if (context != null) {
                    if (!(context instanceof Application)) {
                        Context applicationContext = context.getApplicationContext();
                        this.a = applicationContext;
                        if (applicationContext == null) {
                            throw new IllegalArgumentException("context must be ApplicationContext");
                        }
                    }
                    return this.a;
                }
                throw new IllegalArgumentException("applicationContext is null");
            }
            throw new IllegalArgumentException("applicationContext is null");
        }

        public String b(String str) {
            return a.d != null ? a.d.getValue(str) : "";
        }

        public void c(boolean z) {
        }
    }

    /* loaded from: classes18.dex */
    public static class b implements d {
    }

    /* loaded from: classes18.dex */
    public enum c {
        j2v8(2),
        x5(1),
        webview(0);
        
        public int value;

        c(int i2) {
            this.value = i2;
        }
    }

    /* loaded from: classes18.dex */
    public interface d extends IMantoSdkBase {
        Context getContext();

        String getValue(String str);
    }

    public static void A(boolean z) {
        com.jingdong.manto.b.a(z);
    }

    public static void B(String str, AuthInfo authInfo) {
        com.jingdong.manto.b.k().b(str, authInfo);
    }

    public static void C(String str) {
        com.jingdong.manto.b.a(str);
    }

    public static final void b(IMantoBaseModule iMantoBaseModule) {
        OpenJsApiManager.addPageJsApi(iMantoBaseModule);
    }

    public static final void c(IMantoBaseModule iMantoBaseModule) {
        OpenJsApiManager.addServiceJsApi(iMantoBaseModule);
    }

    public static final void d(IMantoBaseModule iMantoBaseModule) {
        OpenJsApiManager.addWebViewJsApi(iMantoBaseModule);
    }

    public static void e(String str, String str2) {
        com.jingdong.manto.b.k().a(str, str2);
    }

    public static AppExecutors f() {
        return com.jingdong.manto.b.d();
    }

    public static Context g() {
        return com.jingdong.manto.b.f();
    }

    public static List<PkgHistoryEntity> h() {
        if (com.jingdong.manto.b.i() != null) {
            return com.jingdong.manto.b.i().d();
        }
        return null;
    }

    public static String i() {
        return com.jingdong.manto.b.j();
    }

    public static IMainProcChannel j() {
        if (f7579e == null) {
            f7579e = new com.jingdong.manto.mainproc.a();
        }
        return f7579e;
    }

    public static PkgDetailEntity k(String str, String str2) {
        return com.jingdong.manto.b.k().c(str, str2);
    }

    public static void l(b bVar, String str, boolean z, boolean z2) {
        b = str;
        a = z;
        f7578c = z2;
        w(bVar);
        com.jingdong.manto.b.a(new C0232a());
    }

    public static void m(boolean z, String str) {
        com.jingdong.manto.pkg.b.c.a(z, str);
    }

    @Nullable
    public static <T extends IMantoSdkBase> T n(Class<T> cls) {
        return (T) MantoSdkManager.instanceOf(cls);
    }

    public static void o(LaunchParam launchParam) {
        i.a(launchParam);
    }

    public static void p(LaunchParam launchParam, Context context) {
        i.a(launchParam, context);
    }

    public static void q() {
        com.jingdong.manto.b.p();
    }

    public static void r() {
        com.jingdong.manto.b.q();
    }

    public static void s() {
        com.jingdong.manto.b.r();
    }

    public static void t() {
        com.jingdong.manto.b.s();
    }

    public static <T extends IMantoSdkBase> void u(Class<T> cls, Class<? extends T> cls2) {
        com.jingdong.manto.b.a(cls, cls2);
    }

    public static void v(Parcelable parcelable, String str) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        MantoAcrossMessageCenter.notifyCommonData(linkedList, parcelable);
    }

    private static void w(d dVar) {
        d = dVar;
    }

    public static void x(Map<String, String> map) {
        com.jingdong.manto.b.a(map);
    }

    public static void y(long j2) {
        com.jingdong.manto.b.a(j2);
    }

    public static void z(c cVar) {
        com.jingdong.manto.b.a(cVar);
    }
}
