package com.jingdong.app.mall.home.deploy.view.layout.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.floor.common.i.j;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.a;
import com.jingdong.app.mall.home.floor.view.widget.HomeVideoView;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.utils.CommonBase;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class CoreVideoSkuView extends HomeVideoView {
    public static final String r = CoreVideoSkuView.class.getSimpleName();
    private static ConcurrentHashMap<String, CoreVideoSkuView> s = new ConcurrentHashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private String f9002g;

    /* renamed from: h  reason: collision with root package name */
    private View f9003h;

    /* renamed from: i  reason: collision with root package name */
    private CoreModel f9004i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f9005j;

    /* renamed from: k  reason: collision with root package name */
    private AtomicBoolean f9006k;

    /* renamed from: l  reason: collision with root package name */
    private AtomicBoolean f9007l;

    /* renamed from: m  reason: collision with root package name */
    private AtomicBoolean f9008m;

    /* renamed from: n  reason: collision with root package name */
    private AtomicBoolean f9009n;
    private AtomicBoolean o;
    private Handler p;
    private Runnable q;

    public CoreVideoSkuView(Context context) {
        super(context, "61");
        this.f9005j = new AtomicBoolean(false);
        this.f9006k = new AtomicBoolean(false);
        this.f9007l = new AtomicBoolean(false);
        this.f9008m = new AtomicBoolean(false);
        this.f9009n = new AtomicBoolean(false);
        this.o = new AtomicBoolean(false);
        this.p = new Handler(Looper.getMainLooper());
        this.q = new b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.CoreVideoSkuView.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                f.r0(CoreVideoSkuView.r, "\u5012\u8ba1\u65f6\u7ed3\u675f");
                CoreVideoSkuView.this.f9007l.set(true);
                CoreVideoSkuView.this.v();
            }
        };
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        setVisibility(0);
    }

    private void init() {
        setOnPlayerStateListener(new a() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.CoreVideoSkuView.2
            @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onCompletion() {
                f.r0(CoreVideoSkuView.r, "onCompletion");
                CoreVideoSkuView.this.m();
                CoreVideoSkuView.this.f9009n.set(true);
            }

            @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onError(int i2, int i3) {
                f.r0(CoreVideoSkuView.r, "onError");
                CoreVideoSkuView.this.f9005j.set(false);
                return super.onError(i2, i3);
            }

            @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public boolean onInfo(int i2, int i3) {
                if (a(i2) || b(i2)) {
                    f.r0(CoreVideoSkuView.r, "onInfo animate");
                    CoreVideoSkuView.this.f9005j.set(true);
                    CoreVideoSkuView.this.B();
                }
                return super.onInfo(i2, i3);
            }

            @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
            public void onPrepared(long j2) {
                f.r0(CoreVideoSkuView.r, "onPrepared");
                CoreVideoSkuView.this.setVolume(0.0f);
            }
        });
    }

    private void j() {
        f.r0(r, "\u53d6\u6d88\u64ad\u653e");
        this.p.removeCallbacks(this.q);
    }

    private void k() {
        if (n()) {
            r();
        } else {
            q();
        }
    }

    private void l(final String str, String str2) {
        f.r0(r, "\u4e0b\u8f7d\u89c6\u9891");
        j.e("homeSkuVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, str, str2, new j.c() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.CoreVideoSkuView.3
            @Override // com.jingdong.app.mall.home.floor.common.i.j.c
            public void a(boolean z, String str3) {
                if (z) {
                    File file = new File(str3);
                    if (file.exists() && file.length() > 0) {
                        f.r0(CoreVideoSkuView.r, "\u89c6\u9891\u4e0b\u8f7d\u6210\u529f");
                        CoreVideoSkuView.this.z(str3);
                        CoreVideoSkuView.this.y(str, file);
                        return;
                    }
                }
                f.r0(CoreVideoSkuView.r, "\u89c6\u9891\u4e0b\u8f7d\u5931\u8d25");
            }

            @Override // com.jingdong.app.mall.home.floor.common.i.j.c
            public void b() {
                f.r0(CoreVideoSkuView.r, "\u89c6\u9891\u4e0b\u8f7d\u5931\u8d25");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        setVisibility(4);
    }

    private boolean n() {
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 0, true);
    }

    private boolean o(String str, File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        String string = jdSharedPreferences.getString("PATH_".concat(str), null);
        long j2 = jdSharedPreferences.getLong("SIZE_".concat(str), 0L);
        return j2 > 0 && j2 == file.length() && !TextUtils.isEmpty(string) && TextUtils.equals(string, file.getAbsolutePath());
    }

    public static CoreVideoSkuView p(Context context, View view, String str) {
        CoreVideoSkuView coreVideoSkuView = s.get(str);
        if (coreVideoSkuView != null) {
            coreVideoSkuView.A(view);
            return coreVideoSkuView;
        }
        CoreVideoSkuView coreVideoSkuView2 = new CoreVideoSkuView(context);
        coreVideoSkuView2.A(view);
        s.put(str, coreVideoSkuView2);
        return coreVideoSkuView2;
    }

    private void q() {
        if (this.f9009n.get()) {
            return;
        }
        if (this.f9008m.get()) {
            s();
        } else {
            t();
        }
    }

    private void r() {
        if (this.o.get()) {
            this.f9007l.set(false);
            this.f9008m.set(false);
            this.f9009n.set(false);
            this.o.set(false);
            w();
        }
    }

    private void s() {
        j();
        pause();
        m();
        this.f9009n.set(true);
        f.r0(r, "\u64ad\u653e\u7ed3\u675f");
    }

    private void t() {
        j();
        pause();
        m();
        this.o.set(true);
        f.r0(r, "\u4e0b\u6b21\u53ef\u89c1\u65f6\u518d\u91cd\u64ad");
    }

    private void u() {
        seekTo(0);
        start();
        this.f9008m.set(true);
        this.f9004i.x0(true);
        f.r0(r, "\u5f00\u59cb\u64ad\u653e");
    }

    private void w() {
        j();
        f.r0(r, "\u5f00\u59cb\u5012\u8ba1\u65f6\uff1a" + this.f9004i.n0());
        this.p.postDelayed(this.q, ((long) this.f9004i.n0()) * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(String str, File file) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("PATH_".concat(str), file.getAbsolutePath());
        edit.putLong("SIZE_".concat(str), file.length());
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(final String str) {
        f.E0(new b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.widget.CoreVideoSkuView.4
            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                f.r0(CoreVideoSkuView.r, "\u8bbe\u7f6e\u64ad\u653e\u94fe\u63a5");
                CoreVideoSkuView.this.setVideoPathWithoutOpen(str);
                CoreVideoSkuView.this.f9006k.set(true);
                if (CoreVideoSkuView.this.f9007l.get()) {
                    CoreVideoSkuView.this.v();
                }
            }
        });
    }

    public void A(View view) {
        this.f9003h = view;
        f.G0(this);
    }

    public void i(CoreModel coreModel, String str, String str2) {
        s();
        this.f9004i = coreModel;
        this.f9007l.set(false);
        this.f9008m.set(false);
        this.f9009n.set(false);
        this.o.set(false);
        if (!this.f9005j.get() || !TextUtils.equals(str, this.f9002g)) {
            this.f9006k.set(false);
            this.f9002g = str;
            String g2 = j.g("homeSkuVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, str);
            if (o(str, TextUtils.isEmpty(g2) ? null : new File(g2))) {
                z(g2);
            } else {
                l(str, str2);
            }
        }
        w();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1158331917:
                if (type.equals("homePageXViewDisplay")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 436492672:
                if (type.equals("home_splash_close")) {
                    c2 = 2;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1236015766:
                if (type.equals("home_pause")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                s();
                return;
            case 1:
            case 2:
            case 3:
                k();
                return;
            case 4:
                q();
                return;
            default:
                return;
        }
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 == 0) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.0f);
        }
    }

    public void v() {
        String str;
        if (this.f9006k.get() && !this.o.get() && !this.f9009n.get()) {
            if (i.i()) {
                f.r0(r, "\u6b63\u5728\u64ad\u653e\u542f\u52a8\u56fe");
                t();
                return;
            }
            com.jingdong.app.mall.home.floor.ctrl.t.j n2 = com.jingdong.app.mall.home.floor.ctrl.t.i.p().n(3);
            if (n2 != null && n2.d()) {
                f.r0(r, "\u548c\u542f\u52a8xView\u4e92\u65a5");
                s();
                return;
            }
            u();
            return;
        }
        String str2 = r;
        Object[] objArr = new Object[1];
        str = "";
        if (!this.f9006k.get()) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u94fe\u63a5\u8fd8\u4e3a\u8bbe\u7f6e");
            sb.append((this.o.get() || this.f9009n.get()) ? "view\u4e0d\u53ef\u89c1" : "");
            str = sb.toString();
        }
        objArr[0] = str;
        f.r0(str2, objArr);
    }

    public void x() {
        f.r0(r, "\u91ca\u653e\u64ad\u653e\u5668");
        s();
        releaseInThread(true);
        f.H0(this);
    }
}
