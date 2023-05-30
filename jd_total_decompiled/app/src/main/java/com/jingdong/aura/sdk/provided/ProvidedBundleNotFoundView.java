package com.jingdong.aura.sdk.provided;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.R;
import com.jingdong.aura.sdk.update.b.j;
import com.jingdong.aura.sdk.update.updater.IUpdateListener;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.LangUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/* loaded from: classes4.dex */
public class ProvidedBundleNotFoundView extends LinearLayout {
    private a A;
    private a B;
    private a C;
    private a D;
    private d E;
    private c F;
    private a G;
    private g H;
    private Handler I;
    private View.OnClickListener J;
    IUpdateListener a;
    BroadcastReceiver b;

    /* renamed from: c */
    private String f12205c;
    private Button d;

    /* renamed from: e */
    private Button f12206e;

    /* renamed from: f */
    private ProgressBar f12207f;

    /* renamed from: g */
    private TextView f12208g;

    /* renamed from: h */
    private Button f12209h;

    /* renamed from: i */
    private TextView f12210i;

    /* renamed from: j */
    private TextView f12211j;

    /* renamed from: k */
    private RelativeLayout f12212k;

    /* renamed from: l */
    private List<String> f12213l;

    /* renamed from: m */
    private long f12214m;

    /* renamed from: n */
    private float f12215n;
    private long o;
    private int p;
    private String q;
    private List<Integer> r;
    private Stack<String> s;
    private String t;
    private HashMap<String, Integer> u;
    private boolean v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    /* loaded from: classes4.dex */
    public abstract class a {
        String a = "\u6539\u8d44\u6e90\u5305";
        int b = 0;

        /* renamed from: c */
        int f12216c = 13;
        int d;

        /* renamed from: e */
        int f12217e;

        /* renamed from: f */
        int f12218f;

        /* renamed from: g */
        String f12219g;

        /* renamed from: h */
        int f12220h;

        /* renamed from: i */
        int f12221i;

        /* renamed from: j */
        int f12222j;

        /* renamed from: k */
        int f12223k;

        /* renamed from: l */
        String f12224l;

        /* renamed from: m */
        int f12225m;

        /* renamed from: n */
        int f12226n;
        int o;
        int p;
        int q;
        int r;
        int s;
        boolean t;
        boolean u;
        a v;

        a() {
            ProvidedBundleNotFoundView.this = r4;
            int i2 = R.color.c_000000;
            this.d = i2;
            this.f12217e = 10;
            this.f12218f = 0;
            this.f12219g = "\u9a6c\u4e0a\u4e0b\u8f7d\u5b8c\u6210";
            this.f12220h = 0;
            this.f12221i = 13;
            this.f12222j = i2;
            this.f12223k = 0;
            this.f12224l = "\u91cd\u8bd5";
            this.f12225m = 0;
            this.f12226n = R.drawable.aura_provided_download_ctl;
            this.o = R.color.c_FFFFFF;
            this.p = 4;
            this.q = 4;
            this.r = 0;
            this.s = 0;
            this.t = true;
            this.u = true;
        }

        abstract void a();
    }

    /* loaded from: classes4.dex */
    public class b extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b() {
            super();
            ProvidedBundleNotFoundView.this = r3;
            this.a = "";
            this.f12216c = 13;
            this.b = 4;
            this.f12217e = 10;
            this.f12219g = "\u4e0b\u8f7d\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
            this.f12220h = 0;
            this.f12221i = 13;
            this.p = 4;
            this.q = 4;
            this.f12224l = "\u91cd\u8bd5";
            this.f12226n = R.drawable.aura_provided_download_ctl;
            this.r = 4;
            this.s = 4;
            this.t = false;
            this.u = false;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            if (!j.c(ProvidedBundleNotFoundView.this.getContext())) {
                if (!j.b(ProvidedBundleNotFoundView.this.getContext())) {
                    return;
                }
                if (!ProvidedBundleNotFoundView.this.v) {
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                    return;
                }
            }
            ProvidedBundleNotFoundView.this.d();
        }
    }

    /* loaded from: classes4.dex */
    public class c extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c() {
            super();
            ProvidedBundleNotFoundView.this = r3;
            this.a = "\u4e0b\u8f7d\u4e2d\u65ad";
            this.f12216c = 15;
            this.b = 0;
            this.f12217e = 2;
            this.f12218f = 1;
            this.f12219g = "\u624b\u673a\u7a7a\u95f4\u4e0d\u8db3\n\u8bf7\u6e05\u7406\u540e\u91cd\u8bd5";
            this.f12220h = 0;
            this.f12221i = 13;
            this.p = 4;
            this.q = 4;
            this.f12224l = "\u91cd\u8bd5";
            this.f12226n = R.drawable.aura_provided_download_ctl;
            this.r = 4;
            this.s = 4;
            this.t = false;
            this.u = false;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            if (ProvidedBundleNotFoundView.this.g()) {
                if (!j.c(ProvidedBundleNotFoundView.this.getContext())) {
                    if (!j.b(ProvidedBundleNotFoundView.this.getContext())) {
                        return;
                    }
                    if (!ProvidedBundleNotFoundView.this.v) {
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                        return;
                    }
                }
                ProvidedBundleNotFoundView.this.d();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class d extends a {
        public boolean x;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d() {
            super();
            ProvidedBundleNotFoundView.this = r6;
            this.x = false;
            this.a = "";
            this.f12216c = 13;
            this.b = 4;
            this.f12217e = 2;
            this.f12219g = "\u5df2\u4e0b\u8f7d" + String.valueOf(r6.w / 10) + "%\uff0c\u5373\u5c06\u5b8c\u6210";
            this.f12220h = 0;
            this.f12221i = 13;
            this.p = 0;
            this.q = 0;
            this.f12225m = 4;
            this.r = 0;
            this.s = 4;
            this.t = true;
            this.u = false;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "DownloadPauseUIState:controlBtnClicked");
            ProvidedBundleNotFoundView.this.d();
        }
    }

    /* loaded from: classes4.dex */
    public class e extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e() {
            super();
            ProvidedBundleNotFoundView.this = r5;
            this.a = "\u8be5\u9875\u9762\u5185\u5bb9\u9700\u8981\u8d44\u6e90\u5305\uff0c\u7ea6" + r5.f12215n + "M";
            this.f12216c = 13;
            this.b = 0;
            int i2 = R.color.c_000000;
            this.d = i2;
            this.f12217e = 2;
            this.f12218f = 1;
            this.f12219g = "\u4ec5\u9700\u4e0b\u8f7d\u4e00\u6b21\uff0c\u7a0d\u540e\u5373\u53ef\u4f53\u9a8c";
            this.f12220h = 0;
            this.f12221i = 13;
            this.f12222j = i2;
            this.p = 0;
            this.q = 0;
            this.f12226n = R.drawable.aura_provided_download_ctl;
            this.o = i2;
            this.f12225m = 4;
            this.r = 4;
            this.s = 0;
            this.t = false;
            this.u = true;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "DownloadingUIState:controlBtnClicked");
            ProvidedBundleNotFoundView.this.d();
        }
    }

    /* loaded from: classes4.dex */
    public class f extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f() {
            super();
            ProvidedBundleNotFoundView.this = r3;
            this.a = "\u4e0b\u8f7d\u5b8c\u6210";
            this.f12216c = 15;
            this.b = 0;
            this.f12217e = 10;
            this.f12218f = 1;
            this.f12219g = "\u5237\u65b0\u9875\u9762\u5373\u53ef\u4f53\u9a8c";
            this.f12220h = 0;
            this.f12221i = 13;
            this.p = 4;
            this.q = 4;
            this.f12224l = "\u5237\u65b0\u9875\u9762";
            this.f12226n = R.drawable.aura_provided_download_ctl;
            this.r = 4;
            this.s = 4;
            this.t = false;
            this.u = false;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "FragmentDownloadFinishState:controlBtnClicked");
            ProvidedBundleNotFoundView.this.H.a();
        }
    }

    /* loaded from: classes4.dex */
    public interface g {
        void a();
    }

    /* loaded from: classes4.dex */
    public class h extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h() {
            super();
            ProvidedBundleNotFoundView.this = r5;
            this.a = "\u8be5\u9875\u9762\u5185\u5bb9\u9700\u8981\u8d44\u6e90\u5305";
            this.f12216c = 13;
            this.b = 0;
            this.f12217e = 10;
            this.f12219g = "\u7ea6" + r5.f12215n + "M\uff0c\u4ec5\u9700\u4e0b\u8f7d\u4e00\u6b21";
            this.f12220h = 0;
            this.f12221i = 13;
            this.f12223k = 1;
            this.p = 4;
            this.q = 4;
            this.f12224l = "\u4e0b\u8f7d\u5e76\u4f7f\u7528";
            this.r = 4;
            this.s = 4;
            this.t = false;
            this.u = false;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.a
        final void a() {
            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "InitDownloadUIState:controlBtnClicked");
            ProvidedBundleNotFoundView.z(ProvidedBundleNotFoundView.this);
            ProvidedBundleNotFoundView.this.d();
        }
    }

    public ProvidedBundleNotFoundView(Context context) {
        super(context);
        this.f12205c = "";
        this.p = 0;
        this.r = new LinkedList();
        this.s = new Stack<>();
        this.u = new HashMap<>();
        this.v = false;
        this.z = 3;
        this.H = new g() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.1
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.g
            public final void a() {
            }
        };
        this.I = new Handler() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.2
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 101) {
                    int intValue = ((Integer) message.obj).intValue();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " handler progress:".concat(String.valueOf(intValue)));
                    ProvidedBundleNotFoundView.this.f12207f.setProgress(intValue);
                    int width = ProvidedBundleNotFoundView.this.f12207f.getWidth();
                    ProvidedBundleNotFoundView.this.f12208g.setText((intValue / 10) + "%");
                    int c2 = ((int) (((long) (intValue * width)) / 1000)) + com.jingdong.aura.sdk.update.b.f.c(15.0f);
                    ProvidedBundleNotFoundView.this.f12208g.layout(c2, 0, com.jingdong.aura.sdk.update.b.f.c(18.0f) + c2, com.jingdong.aura.sdk.update.b.f.c(21.0f));
                }
            }
        };
        this.a = new IUpdateListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFailure(Exception exc) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onDownloadFailure----");
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView providedBundleNotFoundView;
                        a aVar;
                        if (ProvidedBundleNotFoundView.this.g()) {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.D;
                        } else {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.F;
                        }
                        providedBundleNotFoundView.a(aVar);
                    }
                });
                com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + " diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onDownloadFailure", exc);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFinish(AuraBundleResult auraBundleResult) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: bundleResult:".concat(String.valueOf(auraBundleResult)));
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: currentState:" + ProvidedBundleNotFoundView.this.A);
                if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    try {
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q) != null) {
                    ProvidedBundleNotFoundView.this.p += ((Integer) ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q)).intValue();
                }
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                if (ProvidedBundleNotFoundView.this.s == null || !ProvidedBundleNotFoundView.this.s.isEmpty()) {
                    return;
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: dependencyBundleQueue:");
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = 999;
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadPause(boolean z) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onPause----");
                if (ProvidedBundleNotFoundView.this.s != null && !ProvidedBundleNotFoundView.this.s.contains(ProvidedBundleNotFoundView.this.q)) {
                    ProvidedBundleNotFoundView.this.s.push(ProvidedBundleNotFoundView.this.q);
                }
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.5
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView.this.f12206e.setVisibility(4);
                        ProvidedBundleNotFoundView.this.d.setVisibility(0);
                        ProvidedBundleNotFoundView.this.d.setClickable(true);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.E = new d();
                        ProvidedBundleNotFoundView.this.E.x = true;
                        ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView2.a(providedBundleNotFoundView2.E);
                    }
                });
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadProgress(long j2, long j3) {
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                long j4 = ProvidedBundleNotFoundView.this.f12214m;
                if (j4 <= 0) {
                    j4 = 999;
                }
                int i2 = ProvidedBundleNotFoundView.this.p + ((int) ((1000 * j3) / j4));
                if (i2 > 1000) {
                    i2 = 999;
                }
                ProvidedBundleNotFoundView.this.w = i2;
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "DownloadProgress " + j4 + LangUtils.SINGLE_SPACE + i2 + LangUtils.SINGLE_SPACE + j3 + LangUtils.SINGLE_SPACE + ProvidedBundleNotFoundView.this.p);
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = Integer.valueOf(i2);
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onStart----");
                if (j.b(ProvidedBundleNotFoundView.this.getContext())) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.6
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                        }
                    });
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallFinish(boolean z) {
                try {
                    ProvidedBundleNotFoundView.this.f12206e.setClickable(false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallFinish");
                if (!z) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.4
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView;
                            a aVar;
                            if (ProvidedBundleNotFoundView.this.g()) {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.D;
                            } else {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.F;
                            }
                            providedBundleNotFoundView.a(aVar);
                        }
                    });
                    com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "install failed,isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + ",diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onInstallFinish", new RuntimeException("install failed"));
                } else if (!ProvidedBundleNotFoundView.this.s.empty()) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.3
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            if (ProvidedBundleNotFoundView.this.s == null || ProvidedBundleNotFoundView.this.s.isEmpty()) {
                                return;
                            }
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.q = (String) providedBundleNotFoundView.s.pop();
                            ProvidedBundleNotFoundView.this.d();
                        }
                    });
                } else {
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    Context context2 = providedBundleNotFoundView.getContext();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " -->> unRegisterReceiver()");
                    try {
                        context2.unregisterReceiver(providedBundleNotFoundView.b);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (XView2Constants.FRAGMENT.equals(ProvidedBundleNotFoundView.this.f12205c)) {
                        ProvidedBundleNotFoundView.this.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.2
                            {
                                AnonymousClass3.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                                providedBundleNotFoundView2.a(providedBundleNotFoundView2.G);
                            }
                        });
                    } else {
                        ProvidedBundleNotFoundView.this.H.a();
                    }
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallStart");
            }
        };
        this.J = new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.4
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (view.getId() != R.id.aura_start_download) {
                    if (view.getId() == R.id.aura_stop_download) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click stopDownloadQueue");
                        ProvidedBundleNotFoundView.b();
                    } else if (view.getId() == R.id.aura_provided_download_controllbtn) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click download control");
                        ProvidedBundleNotFoundView.this.A.a();
                    }
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof d)) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "unPause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else if (j.c(ProvidedBundleNotFoundView.this.getContext()) || !ProvidedBundleNotFoundView.this.y) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "pause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else {
                    ProvidedBundleNotFoundView.this.y = false;
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click init download state");
                }
            }
        };
        this.b = new BroadcastReceiver() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.5
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                boolean b2 = j.b(ProvidedBundleNotFoundView.this.getContext());
                boolean c2 = j.c(ProvidedBundleNotFoundView.this.getContext());
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u7f51\u7edc\u91cd\u65b0\u5207\u6362----");
                if (!b2) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u65e0\u7f51\u7edc----");
                } else if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6682\u505c\u72b6\u6001\u65e0\u9700\u5904\u7406----");
                    if (c2) {
                        return;
                    }
                    ProvidedBundleNotFoundView.this.y = true;
                } else if (ProvidedBundleNotFoundView.this.A instanceof e) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:WifiDownloadingUIState");
                    if (c2) {
                        return;
                    }
                    if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u51fa\u4e8b\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                        return;
                    }
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                    ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView2.a(providedBundleNotFoundView2.C);
                    ProvidedBundleNotFoundView.this.d();
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof b)) {
                    if (com.jingdong.aura.sdk.update.a.a().a.mobileConfig.isWifiAutoDownload() && (ProvidedBundleNotFoundView.this.A instanceof h)) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:InitDownloadUIState");
                        if (c2) {
                            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi\u60c5\u51b5, \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                            ProvidedBundleNotFoundView providedBundleNotFoundView3 = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView3.a(providedBundleNotFoundView3.C);
                            ProvidedBundleNotFoundView.this.d();
                        }
                    }
                } else {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:DownloadErrorUIState");
                    if (c2) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi \u60c5\u51b5,  \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                        ProvidedBundleNotFoundView providedBundleNotFoundView4 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView4.a(providedBundleNotFoundView4.C);
                        ProvidedBundleNotFoundView.this.d();
                    } else if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u521d\u59cb\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView5 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView5.a(providedBundleNotFoundView5.B);
                    } else {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView6 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView6.a(providedBundleNotFoundView6.C);
                        ProvidedBundleNotFoundView.this.d();
                    }
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.provided_bundle_download_view, this);
        c();
    }

    public ProvidedBundleNotFoundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12205c = "";
        this.p = 0;
        this.r = new LinkedList();
        this.s = new Stack<>();
        this.u = new HashMap<>();
        this.v = false;
        this.z = 3;
        this.H = new g() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.1
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.g
            public final void a() {
            }
        };
        this.I = new Handler() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.2
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 101) {
                    int intValue = ((Integer) message.obj).intValue();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " handler progress:".concat(String.valueOf(intValue)));
                    ProvidedBundleNotFoundView.this.f12207f.setProgress(intValue);
                    int width = ProvidedBundleNotFoundView.this.f12207f.getWidth();
                    ProvidedBundleNotFoundView.this.f12208g.setText((intValue / 10) + "%");
                    int c2 = ((int) (((long) (intValue * width)) / 1000)) + com.jingdong.aura.sdk.update.b.f.c(15.0f);
                    ProvidedBundleNotFoundView.this.f12208g.layout(c2, 0, com.jingdong.aura.sdk.update.b.f.c(18.0f) + c2, com.jingdong.aura.sdk.update.b.f.c(21.0f));
                }
            }
        };
        this.a = new IUpdateListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFailure(Exception exc) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onDownloadFailure----");
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView providedBundleNotFoundView;
                        a aVar;
                        if (ProvidedBundleNotFoundView.this.g()) {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.D;
                        } else {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.F;
                        }
                        providedBundleNotFoundView.a(aVar);
                    }
                });
                com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + " diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onDownloadFailure", exc);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFinish(AuraBundleResult auraBundleResult) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: bundleResult:".concat(String.valueOf(auraBundleResult)));
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: currentState:" + ProvidedBundleNotFoundView.this.A);
                if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    try {
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q) != null) {
                    ProvidedBundleNotFoundView.this.p += ((Integer) ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q)).intValue();
                }
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                if (ProvidedBundleNotFoundView.this.s == null || !ProvidedBundleNotFoundView.this.s.isEmpty()) {
                    return;
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: dependencyBundleQueue:");
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = 999;
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadPause(boolean z) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onPause----");
                if (ProvidedBundleNotFoundView.this.s != null && !ProvidedBundleNotFoundView.this.s.contains(ProvidedBundleNotFoundView.this.q)) {
                    ProvidedBundleNotFoundView.this.s.push(ProvidedBundleNotFoundView.this.q);
                }
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.5
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView.this.f12206e.setVisibility(4);
                        ProvidedBundleNotFoundView.this.d.setVisibility(0);
                        ProvidedBundleNotFoundView.this.d.setClickable(true);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.E = new d();
                        ProvidedBundleNotFoundView.this.E.x = true;
                        ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView2.a(providedBundleNotFoundView2.E);
                    }
                });
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadProgress(long j2, long j3) {
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                long j4 = ProvidedBundleNotFoundView.this.f12214m;
                if (j4 <= 0) {
                    j4 = 999;
                }
                int i2 = ProvidedBundleNotFoundView.this.p + ((int) ((1000 * j3) / j4));
                if (i2 > 1000) {
                    i2 = 999;
                }
                ProvidedBundleNotFoundView.this.w = i2;
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "DownloadProgress " + j4 + LangUtils.SINGLE_SPACE + i2 + LangUtils.SINGLE_SPACE + j3 + LangUtils.SINGLE_SPACE + ProvidedBundleNotFoundView.this.p);
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = Integer.valueOf(i2);
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onStart----");
                if (j.b(ProvidedBundleNotFoundView.this.getContext())) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.6
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                        }
                    });
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallFinish(boolean z) {
                try {
                    ProvidedBundleNotFoundView.this.f12206e.setClickable(false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallFinish");
                if (!z) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.4
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView;
                            a aVar;
                            if (ProvidedBundleNotFoundView.this.g()) {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.D;
                            } else {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.F;
                            }
                            providedBundleNotFoundView.a(aVar);
                        }
                    });
                    com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "install failed,isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + ",diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onInstallFinish", new RuntimeException("install failed"));
                } else if (!ProvidedBundleNotFoundView.this.s.empty()) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.3
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            if (ProvidedBundleNotFoundView.this.s == null || ProvidedBundleNotFoundView.this.s.isEmpty()) {
                                return;
                            }
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.q = (String) providedBundleNotFoundView.s.pop();
                            ProvidedBundleNotFoundView.this.d();
                        }
                    });
                } else {
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    Context context2 = providedBundleNotFoundView.getContext();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " -->> unRegisterReceiver()");
                    try {
                        context2.unregisterReceiver(providedBundleNotFoundView.b);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (XView2Constants.FRAGMENT.equals(ProvidedBundleNotFoundView.this.f12205c)) {
                        ProvidedBundleNotFoundView.this.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.2
                            {
                                AnonymousClass3.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                                providedBundleNotFoundView2.a(providedBundleNotFoundView2.G);
                            }
                        });
                    } else {
                        ProvidedBundleNotFoundView.this.H.a();
                    }
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallStart");
            }
        };
        this.J = new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.4
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (view.getId() != R.id.aura_start_download) {
                    if (view.getId() == R.id.aura_stop_download) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click stopDownloadQueue");
                        ProvidedBundleNotFoundView.b();
                    } else if (view.getId() == R.id.aura_provided_download_controllbtn) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click download control");
                        ProvidedBundleNotFoundView.this.A.a();
                    }
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof d)) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "unPause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else if (j.c(ProvidedBundleNotFoundView.this.getContext()) || !ProvidedBundleNotFoundView.this.y) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "pause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else {
                    ProvidedBundleNotFoundView.this.y = false;
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click init download state");
                }
            }
        };
        this.b = new BroadcastReceiver() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.5
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                boolean b2 = j.b(ProvidedBundleNotFoundView.this.getContext());
                boolean c2 = j.c(ProvidedBundleNotFoundView.this.getContext());
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u7f51\u7edc\u91cd\u65b0\u5207\u6362----");
                if (!b2) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u65e0\u7f51\u7edc----");
                } else if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6682\u505c\u72b6\u6001\u65e0\u9700\u5904\u7406----");
                    if (c2) {
                        return;
                    }
                    ProvidedBundleNotFoundView.this.y = true;
                } else if (ProvidedBundleNotFoundView.this.A instanceof e) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:WifiDownloadingUIState");
                    if (c2) {
                        return;
                    }
                    if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u51fa\u4e8b\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                        return;
                    }
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                    ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView2.a(providedBundleNotFoundView2.C);
                    ProvidedBundleNotFoundView.this.d();
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof b)) {
                    if (com.jingdong.aura.sdk.update.a.a().a.mobileConfig.isWifiAutoDownload() && (ProvidedBundleNotFoundView.this.A instanceof h)) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:InitDownloadUIState");
                        if (c2) {
                            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi\u60c5\u51b5, \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                            ProvidedBundleNotFoundView providedBundleNotFoundView3 = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView3.a(providedBundleNotFoundView3.C);
                            ProvidedBundleNotFoundView.this.d();
                        }
                    }
                } else {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:DownloadErrorUIState");
                    if (c2) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi \u60c5\u51b5,  \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                        ProvidedBundleNotFoundView providedBundleNotFoundView4 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView4.a(providedBundleNotFoundView4.C);
                        ProvidedBundleNotFoundView.this.d();
                    } else if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u521d\u59cb\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView5 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView5.a(providedBundleNotFoundView5.B);
                    } else {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView6 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView6.a(providedBundleNotFoundView6.C);
                        ProvidedBundleNotFoundView.this.d();
                    }
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.provided_bundle_download_view, this);
        c();
    }

    public ProvidedBundleNotFoundView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f12205c = "";
        this.p = 0;
        this.r = new LinkedList();
        this.s = new Stack<>();
        this.u = new HashMap<>();
        this.v = false;
        this.z = 3;
        this.H = new g() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.1
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.g
            public final void a() {
            }
        };
        this.I = new Handler() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.2
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 101) {
                    int intValue = ((Integer) message.obj).intValue();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " handler progress:".concat(String.valueOf(intValue)));
                    ProvidedBundleNotFoundView.this.f12207f.setProgress(intValue);
                    int width = ProvidedBundleNotFoundView.this.f12207f.getWidth();
                    ProvidedBundleNotFoundView.this.f12208g.setText((intValue / 10) + "%");
                    int c2 = ((int) (((long) (intValue * width)) / 1000)) + com.jingdong.aura.sdk.update.b.f.c(15.0f);
                    ProvidedBundleNotFoundView.this.f12208g.layout(c2, 0, com.jingdong.aura.sdk.update.b.f.c(18.0f) + c2, com.jingdong.aura.sdk.update.b.f.c(21.0f));
                }
            }
        };
        this.a = new IUpdateListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFailure(Exception exc) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onDownloadFailure----");
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView providedBundleNotFoundView;
                        a aVar;
                        if (ProvidedBundleNotFoundView.this.g()) {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.D;
                        } else {
                            providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            aVar = providedBundleNotFoundView.F;
                        }
                        providedBundleNotFoundView.a(aVar);
                    }
                });
                com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + " diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onDownloadFailure", exc);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadFinish(AuraBundleResult auraBundleResult) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: bundleResult:".concat(String.valueOf(auraBundleResult)));
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: currentState:" + ProvidedBundleNotFoundView.this.A);
                if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    try {
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q) != null) {
                    ProvidedBundleNotFoundView.this.p += ((Integer) ProvidedBundleNotFoundView.this.u.get(ProvidedBundleNotFoundView.this.q)).intValue();
                }
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                if (ProvidedBundleNotFoundView.this.s == null || !ProvidedBundleNotFoundView.this.s.isEmpty()) {
                    return;
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "onDownloadFinish: dependencyBundleQueue:");
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = 999;
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadPause(boolean z) {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onPause----");
                if (ProvidedBundleNotFoundView.this.s != null && !ProvidedBundleNotFoundView.this.s.contains(ProvidedBundleNotFoundView.this.q)) {
                    ProvidedBundleNotFoundView.this.s.push(ProvidedBundleNotFoundView.this.q);
                }
                ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.5
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ProvidedBundleNotFoundView.this.f12206e.setVisibility(4);
                        ProvidedBundleNotFoundView.this.d.setVisibility(0);
                        ProvidedBundleNotFoundView.this.d.setClickable(true);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.E = new d();
                        ProvidedBundleNotFoundView.this.E.x = true;
                        ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView2.a(providedBundleNotFoundView2.E);
                    }
                });
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadProgress(long j2, long j3) {
                com.jingdong.aura.sdk.provided.c.a(ProvidedBundleNotFoundView.this.t, ProvidedBundleNotFoundView.this.p);
                long j4 = ProvidedBundleNotFoundView.this.f12214m;
                if (j4 <= 0) {
                    j4 = 999;
                }
                int i22 = ProvidedBundleNotFoundView.this.p + ((int) ((1000 * j3) / j4));
                if (i22 > 1000) {
                    i22 = 999;
                }
                ProvidedBundleNotFoundView.this.w = i22;
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "DownloadProgress " + j4 + LangUtils.SINGLE_SPACE + i22 + LangUtils.SINGLE_SPACE + j3 + LangUtils.SINGLE_SPACE + ProvidedBundleNotFoundView.this.p);
                Message obtainMessage = ProvidedBundleNotFoundView.this.I.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = Integer.valueOf(i22);
                ProvidedBundleNotFoundView.this.I.sendMessage(obtainMessage);
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onDownloadStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86onStart----");
                if (j.b(ProvidedBundleNotFoundView.this.getContext())) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.6
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.a(providedBundleNotFoundView.C);
                        }
                    });
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallFinish(boolean z) {
                try {
                    ProvidedBundleNotFoundView.this.f12206e.setClickable(false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallFinish");
                if (!z) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.4
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            ProvidedBundleNotFoundView providedBundleNotFoundView;
                            a aVar;
                            if (ProvidedBundleNotFoundView.this.g()) {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.D;
                            } else {
                                providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                                aVar = providedBundleNotFoundView.F;
                            }
                            providedBundleNotFoundView.a(aVar);
                        }
                    });
                    com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleNotFoundView.this.t, -1, "install failed,isMemoryEnough:" + ProvidedBundleNotFoundView.this.g() + ",diskFreeSize" + ProvidedBundleNotFoundView.a(), "ProvidedBundleNotFoundView.onInstallFinish", new RuntimeException("install failed"));
                } else if (!ProvidedBundleNotFoundView.this.s.empty()) {
                    ProvidedBundleNotFoundView.this.I.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.3
                        {
                            AnonymousClass3.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            if (ProvidedBundleNotFoundView.this.s == null || ProvidedBundleNotFoundView.this.s.isEmpty()) {
                                return;
                            }
                            ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView.q = (String) providedBundleNotFoundView.s.pop();
                            ProvidedBundleNotFoundView.this.d();
                        }
                    });
                } else {
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    Context context2 = providedBundleNotFoundView.getContext();
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " -->> unRegisterReceiver()");
                    try {
                        context2.unregisterReceiver(providedBundleNotFoundView.b);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (XView2Constants.FRAGMENT.equals(ProvidedBundleNotFoundView.this.f12205c)) {
                        ProvidedBundleNotFoundView.this.post(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.3.2
                            {
                                AnonymousClass3.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                                providedBundleNotFoundView2.a(providedBundleNotFoundView2.G);
                            }
                        });
                    } else {
                        ProvidedBundleNotFoundView.this.H.a();
                    }
                }
            }

            @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
            public final void onInstallStart() {
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " onInstallStart");
            }
        };
        this.J = new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.4
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (view.getId() != R.id.aura_start_download) {
                    if (view.getId() == R.id.aura_stop_download) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click stopDownloadQueue");
                        ProvidedBundleNotFoundView.b();
                    } else if (view.getId() == R.id.aura_provided_download_controllbtn) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click download control");
                        ProvidedBundleNotFoundView.this.A.a();
                    }
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof d)) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "unPause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else if (j.c(ProvidedBundleNotFoundView.this.getContext()) || !ProvidedBundleNotFoundView.this.y) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "pause state ui click startDownloadQueue");
                    ProvidedBundleNotFoundView.this.d();
                } else {
                    ProvidedBundleNotFoundView.this.y = false;
                    ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "ui click init download state");
                }
            }
        };
        this.b = new BroadcastReceiver() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleNotFoundView.5
            {
                ProvidedBundleNotFoundView.this = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                boolean b2 = j.b(ProvidedBundleNotFoundView.this.getContext());
                boolean c2 = j.c(ProvidedBundleNotFoundView.this.getContext());
                com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u7f51\u7edc\u91cd\u65b0\u5207\u6362----");
                if (!b2) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u65e0\u7f51\u7edc----");
                } else if (ProvidedBundleNotFoundView.this.A instanceof d) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6682\u505c\u72b6\u6001\u65e0\u9700\u5904\u7406----");
                    if (c2) {
                        return;
                    }
                    ProvidedBundleNotFoundView.this.y = true;
                } else if (ProvidedBundleNotFoundView.this.A instanceof e) {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:WifiDownloadingUIState");
                    if (c2) {
                        return;
                    }
                    if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u51fa\u4e8b\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView.a(providedBundleNotFoundView.B);
                        return;
                    }
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                    ProvidedBundleNotFoundView providedBundleNotFoundView2 = ProvidedBundleNotFoundView.this;
                    providedBundleNotFoundView2.a(providedBundleNotFoundView2.C);
                    ProvidedBundleNotFoundView.this.d();
                } else if (!(ProvidedBundleNotFoundView.this.A instanceof b)) {
                    if (com.jingdong.aura.sdk.update.a.a().a.mobileConfig.isWifiAutoDownload() && (ProvidedBundleNotFoundView.this.A instanceof h)) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:InitDownloadUIState");
                        if (c2) {
                            com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi\u60c5\u51b5, \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                            ProvidedBundleNotFoundView providedBundleNotFoundView3 = ProvidedBundleNotFoundView.this;
                            providedBundleNotFoundView3.a(providedBundleNotFoundView3.C);
                            ProvidedBundleNotFoundView.this.d();
                        }
                    }
                } else {
                    com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u5f53\u524d\u72b6\u6001:DownloadErrorUIState");
                    if (c2) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----wifi \u60c5\u51b5,  \u5207\u6362\u4e3awifi\u4e0b\u8f7d\u72b6\u6001----");
                        ProvidedBundleNotFoundView providedBundleNotFoundView4 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView4.a(providedBundleNotFoundView4.C);
                        ProvidedBundleNotFoundView.this.d();
                    } else if (!ProvidedBundleNotFoundView.this.v) {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u521d\u59cb\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView5 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView5.a(providedBundleNotFoundView5.B);
                    } else {
                        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u6d41\u91cf\u60c5\u51b5,  \u5207\u6362\u4e3a\u6d41\u91cf\u4e0b\u8f7d\u72b6\u6001----isUseFlow" + ProvidedBundleNotFoundView.this.v);
                        ProvidedBundleNotFoundView providedBundleNotFoundView6 = ProvidedBundleNotFoundView.this;
                        providedBundleNotFoundView6.a(providedBundleNotFoundView6.C);
                        ProvidedBundleNotFoundView.this.d();
                    }
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.provided_bundle_download_view, this);
        c();
    }

    static /* synthetic */ long a() {
        return getDataDiskFreeSize$1385f3();
    }

    private void a(Context context) {
        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", " -->> registerReceiver()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this.b, intentFilter);
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        this.A = aVar;
        this.f12210i.setText(aVar.a);
        this.f12210i.setTextSize(this.A.f12216c);
        this.f12210i.setVisibility(this.A.b);
        this.f12210i.setTextColor(getContext().getResources().getColor(this.A.d));
        this.f12210i.setTypeface(Typeface.defaultFromStyle(this.A.f12218f));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f12210i.getLayoutParams();
        layoutParams.topMargin = com.jingdong.aura.sdk.update.b.f.c(this.A.f12217e);
        this.f12210i.setLayoutParams(layoutParams);
        this.f12211j.setText(this.A.f12219g);
        this.f12211j.setVisibility(this.A.f12220h);
        this.f12211j.setTextSize(this.A.f12221i);
        this.f12211j.setTextColor(getContext().getResources().getColor(this.A.f12222j));
        this.f12211j.setTypeface(Typeface.defaultFromStyle(this.A.f12223k));
        this.f12207f.setVisibility(this.A.p);
        this.f12208g.setVisibility(this.A.q);
        this.f12209h.setVisibility(this.A.f12225m);
        this.f12209h.setText(this.A.f12224l);
        this.f12209h.setBackgroundResource(this.A.f12226n);
        this.f12209h.setTextColor(getContext().getResources().getColor(this.A.o));
        this.d.setVisibility(this.A.r);
        this.f12206e.setVisibility(this.A.s);
        this.d.setClickable(this.A.t);
        this.f12206e.setClickable(this.A.u);
    }

    static /* synthetic */ void b() {
        com.jingdong.aura.sdk.update.a.a().f12242l.c();
    }

    private void c() {
        this.d = (Button) findViewById(R.id.aura_start_download);
        this.f12206e = (Button) findViewById(R.id.aura_stop_download);
        this.d.setOnClickListener(this.J);
        this.f12206e.setOnClickListener(this.J);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.aura_provided_download_progressbar);
        this.f12207f = progressBar;
        progressBar.setMax(1000);
        this.f12210i = (TextView) findViewById(R.id.aura_provided_text1);
        this.f12211j = (TextView) findViewById(R.id.aura_provided_text2);
        this.f12212k = (RelativeLayout) findViewById(R.id.aura_provided_download_progressbar_layout);
        Button button = (Button) findViewById(R.id.aura_provided_download_controllbtn);
        this.f12209h = button;
        button.setOnClickListener(this.J);
        TextView textView = new TextView(getContext());
        this.f12208g = textView;
        textView.setTextSize(8.0f);
        this.f12208g.setTextColor(Color.parseColor("#FFFFFF"));
        this.f12208g.setGravity(17);
        this.f12208g.setPadding(0, 6, 0, 0);
        ((ViewGroup) findViewById(R.id.aura_provided_download_movalbe_layout)).addView(this.f12208g, new ViewGroup.LayoutParams(getWidth(), com.jingdong.aura.sdk.update.b.f.c(23.0f)));
        this.f12208g.setBackgroundDrawable(getResources().getDrawable(R.drawable.aura_provided_download_progress_icon));
    }

    public void d() {
        Stack<String> stack;
        Stack<String> stack2;
        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "----\u56de\u8c03\u4e86startDownloadQueue----");
        if (!TextUtils.isEmpty(this.q) && (stack2 = this.s) != null && !stack2.contains(this.q)) {
            this.s.push(this.q);
        }
        if (!getIsVisibleToUser() || (stack = this.s) == null || stack.empty()) {
            return;
        }
        this.q = this.s.pop();
        this.a.onDownloadStart();
        com.jingdong.aura.sdk.update.a.a().f12242l.a(this.q, this.a, this.z);
    }

    private void e() {
        this.C = new e();
        this.D = new b();
        this.E = new d();
        this.F = new c();
        this.B = new h();
        this.G = new f();
        a aVar = this.C;
        a aVar2 = this.D;
        aVar.v = aVar2;
        aVar2.v = aVar;
        this.F.v = aVar;
        this.B.v = aVar;
    }

    private void f() {
        this.B = new h();
        this.C = new e();
        this.D = new b();
        this.E = new d();
        this.F = new c();
        this.G = new f();
        a aVar = this.B;
        a aVar2 = this.C;
        aVar.v = aVar2;
        a aVar3 = this.D;
        aVar2.v = aVar3;
        aVar3.v = aVar2;
        this.F.v = aVar2;
    }

    public boolean g() {
        long dataDiskFreeSize$1385f3 = getDataDiskFreeSize$1385f3();
        this.o = dataDiskFreeSize$1385f3;
        return dataDiskFreeSize$1385f3 >= 10485760 && dataDiskFreeSize$1385f3 >= this.f12214m * 3;
    }

    private static long getDataDiskFreeSize$1385f3() {
        long blockSize;
        long availableBlocks;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return blockSize * availableBlocks;
        } catch (Throwable th) {
            th.printStackTrace();
            return 400L;
        }
    }

    static /* synthetic */ boolean z(ProvidedBundleNotFoundView providedBundleNotFoundView) {
        providedBundleNotFoundView.v = true;
        return true;
    }

    public final void a(List<String> list, String str, String str2) {
        this.f12213l = list;
        this.t = str;
        this.f12205c = str2;
        this.d.setVisibility(4);
        this.f12206e.setVisibility(0);
        this.v = false;
        this.y = false;
        if (list != null && !list.isEmpty()) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                this.s.push(com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getUpdateIdFromBundleName(it.next()));
            }
        }
        if (com.jingdong.aura.sdk.provided.c.a(str)) {
            this.p = 0;
            this.f12214m = 0L;
            if (list != null && !list.isEmpty()) {
                Iterator<String> it2 = list.iterator();
                while (it2.hasNext()) {
                    this.f12214m += AuraConfig.getBundleSize(it2.next());
                }
                com.jingdong.aura.sdk.provided.c.a(str, this.f12214m);
            }
        } else {
            this.p = com.jingdong.aura.sdk.provided.c.b(str);
            this.f12214m = com.jingdong.aura.sdk.provided.c.c(str);
        }
        this.f12215n = Math.round(((((float) this.f12214m) / 1024.0f) / 1024.0f) * 100.0f) / 100.0f;
        if (list != null && !list.isEmpty()) {
            for (String str3 : list) {
                long bundleSize = AuraConfig.getBundleSize(str3);
                long j2 = this.f12214m;
                if (j2 != 0) {
                    long j3 = bundleSize * 1000;
                    this.r.add(Integer.valueOf((int) (j3 / j2)));
                    this.u.put(com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getUpdateIdFromBundleName(str3), Integer.valueOf((int) (j3 / this.f12214m)));
                }
            }
        }
        if (!g()) {
            if (com.jingdong.aura.sdk.update.a.a().a.mobileConfig.isWifiAutoDownload() && j.c(getContext())) {
                e();
            } else {
                f();
            }
            a(this.F);
            com.jingdong.aura.sdk.update.a.a().f12243m.onException(str, -1, "isMemoryEnough:true diskFreeSize" + getDataDiskFreeSize$1385f3(), "ProvidedBundleNotFoundView.initState", new RuntimeException("disk size not enough!!"));
            return;
        }
        a(getContext());
        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "init state: wifi:" + j.c(getContext()) + " available:" + j.b(getContext()));
        com.jingdong.aura.sdk.update.b.c.a("ProvidedBundleNotFoundView", "");
        if (!com.jingdong.aura.sdk.update.a.a().a.mobileConfig.isWifiAutoDownload()) {
            f();
            a(this.B);
        } else if (!j.c(getContext()) && j.b(getContext())) {
            f();
            a(this.B);
        } else {
            e();
            d();
            a(this.C);
        }
    }

    public boolean getIsVisibleToUser() {
        return this.x;
    }

    public void setDownLoadFrom(int i2) {
        this.z = i2;
    }

    public void setIsVisibleToUser(boolean z) {
        boolean z2;
        this.x = z;
        if (z && !(this.A instanceof d)) {
            if (j.c(getContext()) || (z2 = this.v)) {
                d();
            } else if (z2) {
            } else {
                a(this.B);
            }
        }
    }

    public void setProvidedBundleDownloadListener(g gVar) {
        this.H = gVar;
    }
}
