package com.jingdong.app.mall.network;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.title.theme.JdThemeTitle;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class NetworkDiagnoseActivity extends BaseActivity {
    private static final String B = NetworkDiagnoseActivity.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private JdThemeTitle f11389g;

    /* renamed from: h  reason: collision with root package name */
    private LinearLayout f11390h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f11391i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f11392j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f11393k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f11394l;

    /* renamed from: m  reason: collision with root package name */
    private g f11395m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f11396n;
    private TextView o;
    private TextView p;
    private TextView q;
    private long t;
    private long u;
    private volatile List<String> v;
    private volatile List<String> x;
    private String z;
    private boolean r = false;
    private AtomicBoolean s = new AtomicBoolean(false);
    private final Object w = new Object();
    private final Object y = new Object();
    private Runnable A = new f();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NetworkDiagnoseActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NetworkDiagnoseActivity.this.J();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NetworkDiagnoseActivity.this.I();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            try {
                JDRouter.build(NetworkDiagnoseActivity.this, "router://JDMyJdModule/showPlatformFeedBackVC?from=wangluozhenduan").open();
            } catch (Throwable th) {
                OKLog.d(NetworkDiagnoseActivity.B, th.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements com.jingdong.sdk.dialingtest.e.a.b {
        e() {
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.b
        public void a() {
            OKLog.d("lake", "really Finish " + (System.currentTimeMillis() - NetworkDiagnoseActivity.this.t));
            if (NetworkDiagnoseActivity.this.s != null) {
                NetworkDiagnoseActivity.this.s.set(true);
            }
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.b
        public void b(String str) {
            synchronized (NetworkDiagnoseActivity.this.w) {
                if (NetworkDiagnoseActivity.this.v != null) {
                    NetworkDiagnoseActivity.this.v.add(str);
                    OKLog.d("lake", "add http data");
                }
            }
        }

        @Override // com.jingdong.sdk.dialingtest.e.a.b
        public void c(String str) {
            synchronized (NetworkDiagnoseActivity.this.y) {
                if (NetworkDiagnoseActivity.this.x != null) {
                    NetworkDiagnoseActivity.this.x.add(str);
                    OKLog.d("lake", "add ping data");
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    class f implements Runnable {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    NetworkDiagnoseActivity.this.N();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (System.currentTimeMillis() - NetworkDiagnoseActivity.this.t > Final.HALF_MINUTE || NetworkDiagnoseActivity.this.s.get()) {
                if (NetworkDiagnoseActivity.this.u <= 9000.0d) {
                    NetworkDiagnoseActivity.G(NetworkDiagnoseActivity.this, 68L);
                } else {
                    NetworkDiagnoseActivity.H(NetworkDiagnoseActivity.this, 5.666666666666667d);
                }
            } else if (NetworkDiagnoseActivity.this.u > 6000.0d) {
                if (NetworkDiagnoseActivity.this.u < 6000.0d || NetworkDiagnoseActivity.this.u >= 8000.0d) {
                    if (NetworkDiagnoseActivity.this.u >= 8000.0d && NetworkDiagnoseActivity.this.u < 9500.0d) {
                        NetworkDiagnoseActivity.H(NetworkDiagnoseActivity.this, 3.4d);
                    }
                } else {
                    NetworkDiagnoseActivity.H(NetworkDiagnoseActivity.this, 6.8d);
                }
            } else {
                NetworkDiagnoseActivity.H(NetworkDiagnoseActivity.this, 20.4d);
            }
            if (NetworkDiagnoseActivity.this.u < 10000) {
                if (NetworkDiagnoseActivity.this.f11395m != null) {
                    NetworkDiagnoseActivity.this.f11395m.b(((float) NetworkDiagnoseActivity.this.u) / 10000.0f);
                }
                NetworkDiagnoseActivity.this.getHandler().postDelayed(NetworkDiagnoseActivity.this.A, 17L);
                return;
            }
            if (NetworkDiagnoseActivity.this.f11395m != null) {
                NetworkDiagnoseActivity.this.f11395m.b(1.0f);
            }
            OKLog.d("lake", "go Finish " + (System.currentTimeMillis() - NetworkDiagnoseActivity.this.t));
            NetworkDiagnoseActivity.this.getHandler().postDelayed(new a(), 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class g extends LinearLayout {

        /* renamed from: g  reason: collision with root package name */
        private Context f11403g;

        /* renamed from: h  reason: collision with root package name */
        private LinearLayout f11404h;

        public g(Context context) {
            super(context);
            this.f11403g = context;
            a();
        }

        private void a() {
            setOrientation(0);
            setGravity(16);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(DPIUtil.getWidthByDesignValue750(this.f11403g, 24));
            gradientDrawable.setStroke(DPIUtil.getWidthByDesignValue750(this.f11403g, 5), Color.parseColor("#192E2D2D"));
            setBackground(gradientDrawable);
            this.f11404h = new LinearLayout(this.f11403g);
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(DPIUtil.getWidthByDesignValue750(this.f11403g, 30));
            gradientDrawable2.setGradientType(0);
            gradientDrawable2.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            gradientDrawable2.setColors(new int[]{Color.parseColor("#FF4F18"), Color.parseColor("#FF2000"), Color.parseColor("#F10000")});
            this.f11404h.setBackground(gradientDrawable2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DPIUtil.getWidthByDesignValue750(this.f11403g, 0), DPIUtil.getWidthByDesignValue750(this.f11403g, 15));
            layoutParams.leftMargin = DPIUtil.getWidthByDesignValue750(this.f11403g, 12);
            layoutParams.rightMargin = DPIUtil.getWidthByDesignValue750(this.f11403g, 12);
            addView(this.f11404h, layoutParams);
        }

        public void b(float f2) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f11404h.getLayoutParams();
            layoutParams.width = (int) ((getWidth() - (DPIUtil.getWidthByDesignValue750(this.f11403g, 12) * 2)) * f2);
            this.f11404h.setLayoutParams(layoutParams);
        }
    }

    static /* synthetic */ long G(NetworkDiagnoseActivity networkDiagnoseActivity, long j2) {
        long j3 = networkDiagnoseActivity.u + j2;
        networkDiagnoseActivity.u = j3;
        return j3;
    }

    static /* synthetic */ long H(NetworkDiagnoseActivity networkDiagnoseActivity, double d2) {
        double d3 = networkDiagnoseActivity.u;
        Double.isNaN(d3);
        long j2 = (long) (d3 + d2);
        networkDiagnoseActivity.u = j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (TextUtils.isEmpty(this.z)) {
            String L = L();
            this.z = L;
            if (!TextUtils.isEmpty(L)) {
                try {
                    ((ClipboardManager) getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setText(this.z);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        ToastUtils.showToast(this, "\u5df2\u590d\u5236\u5230\u526a\u5207\u677f");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.r) {
            return;
        }
        this.r = true;
        this.f11395m.setVisibility(0);
        this.f11396n.setVisibility(0);
        this.t = System.currentTimeMillis();
        this.u = 0L;
        getHandler().post(this.A);
        K();
    }

    private void K() {
        com.jingdong.sdk.dialingtest.a.b(new e());
    }

    private String L() {
        JDJSONArray jDJSONArray = new JDJSONArray();
        try {
            if (this.v != null) {
                Iterator<String> it = this.v.iterator();
                while (it.hasNext()) {
                    jDJSONArray.add(JDJSON.parse(it.next()));
                }
            }
            if (this.x != null) {
                Iterator<String> it2 = this.x.iterator();
                while (it2.hasNext()) {
                    jDJSONArray.add(JDJSON.parse(it2.next()));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return com.jingdong.sdk.dialingtest.a.c(jDJSONArray.toJSONString());
    }

    private int M(int i2) {
        return DPIUtil.getWidthByDesignValue750((Activity) this, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        this.f11394l.setVisibility(8);
        this.f11395m.setVisibility(8);
        this.f11396n.setVisibility(8);
        this.f11390h.setPadding(M(88), 0, M(88), 0);
        this.f11391i.setImageResource(R.drawable.ac4);
        this.f11392j.setText("\u8bca\u65ad\u5b8c\u6210\uff0c\u5df2\u751f\u6210\u8bca\u65ad\u62a5\u544a");
        this.f11393k.setText("\u60a8\u53ef\u4ee5\u9009\u62e9\u590d\u5236\u5230\u526a\u5207\u677f\u540e\uff0c\u901a\u8fc7\u5176\u4ed6\u65b9\u5f0f\u7ed9\u5230\u6211\u4eec\u7684\u5de5\u4f5c\u4eba\u5458\uff0c\u6211\u4eec\u4f1a\u5c3d\u5feb\u4e3a\u60a8\u5b9a\u4f4d\u95ee\u9898");
        this.o.setVisibility(0);
        this.p.setVisibility(0);
        this.q.setVisibility(0);
    }

    private void O() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rq);
        JdThemeTitle jdThemeTitle = new JdThemeTitle(this);
        this.f11389g = jdThemeTitle;
        jdThemeTitle.setCustomOpen(false);
        this.f11389g.setTitleText("\u7f51\u7edc\u8bca\u65ad");
        this.f11389g.setLeft1DrawableId(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
        this.f11389g.setStatusBarHint(true);
        this.f11389g.setRightTv1Visibility(4);
        this.f11389g.getLeft1ImageView().setPadding(0, 15, 50, 15);
        this.f11389g.loadTheme();
        linearLayout.addView(this.f11389g, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -1));
        View view = new View(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        linearLayout2.addView(view, layoutParams);
        LinearLayout linearLayout3 = new LinearLayout(this);
        this.f11390h = linearLayout3;
        linearLayout3.setPadding(M(120), 0, M(120), 0);
        this.f11390h.setGravity(1);
        this.f11390h.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 0);
        layoutParams2.weight = 4.0f;
        linearLayout2.addView(this.f11390h, layoutParams2);
        this.f11391i = new SimpleDraweeView(this);
        this.f11390h.addView(this.f11391i, new LinearLayout.LayoutParams(M(180), M(203)));
        TextView textView = new TextView(this);
        this.f11392j = textView;
        textView.setTextSize(0, M(30));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = M(29);
        this.f11390h.addView(this.f11392j, layoutParams3);
        TextView textView2 = new TextView(this);
        this.f11393k = textView2;
        textView2.setTextSize(0, M(28));
        this.f11393k.setTextColor(Color.parseColor("#BFBFBF"));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.topMargin = M(20);
        this.f11390h.addView(this.f11393k, layoutParams4);
        TextView textView3 = new TextView(this);
        this.f11394l = textView3;
        textView3.setText("\u7f51\u7edc\u68c0\u6d4b");
        this.f11394l.setTextSize(0, M(28));
        this.f11394l.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setSize(M(224), M(70));
        gradientDrawable.setCornerRadius(M(38));
        gradientDrawable.setShape(0);
        gradientDrawable.setStroke(M(1), Color.parseColor("#BFBFBF"));
        this.f11394l.setBackground(gradientDrawable);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(M(224), M(70));
        layoutParams5.topMargin = M(28);
        this.f11390h.addView(this.f11394l, layoutParams5);
        this.f11394l.setVisibility(8);
        this.f11395m = new g(this);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, M(30));
        layoutParams6.topMargin = M(36);
        this.f11390h.addView(this.f11395m, layoutParams6);
        this.f11395m.setVisibility(8);
        TextView textView4 = new TextView(this);
        this.f11396n = textView4;
        textView4.setText("\u5f00\u59cb\u4e3a\u60a8\u8bca\u65ad\uff0c\u8bf7\u7a0d\u5019...");
        this.f11396n.setTextSize(0, M(28));
        this.f11396n.setTextColor(Color.parseColor("#BFBFBF"));
        this.f11396n.setGravity(17);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams7.gravity = 3;
        layoutParams7.topMargin = M(26);
        this.f11390h.addView(this.f11396n, layoutParams7);
        this.f11396n.setVisibility(8);
        TextView textView5 = new TextView(this);
        this.o = textView5;
        textView5.setText("\u590d\u5236\u5230\u526a\u5207\u677f");
        this.o.setTextSize(0, M(28));
        this.o.setGravity(17);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(M(38));
        gradientDrawable2.setShape(0);
        gradientDrawable2.setStroke(M(1), Color.parseColor("#BFBFBF"));
        this.o.setBackground(gradientDrawable2);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(M(272), M(70));
        layoutParams8.topMargin = M(30);
        this.f11390h.addView(this.o, layoutParams8);
        this.o.setVisibility(8);
        TextView textView6 = new TextView(this);
        this.p = textView6;
        textView6.setText("\u60a8\u4e5f\u53ef\u4ee5\u5c06\u9875\u9762\u5f02\u5e38\u622a\u56fe\u7b49\u4fe1\u606f\u901a\u8fc7\u7528\u6237\u53cd\u9988\u6e20\u9053\u8fdb\u884c\u53cd\u9988");
        this.p.setTextSize(0, M(28));
        this.p.setTextColor(Color.parseColor("#BFBFBF"));
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams9.topMargin = M(55);
        this.f11390h.addView(this.p, layoutParams9);
        this.p.setVisibility(8);
        TextView textView7 = new TextView(this);
        this.q = textView7;
        textView7.setText("\u7528\u6237\u53cd\u9988");
        this.q.setTextSize(0, M(28));
        this.q.setGravity(17);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setCornerRadius(M(38));
        gradientDrawable3.setShape(0);
        gradientDrawable3.setStroke(M(1), Color.parseColor("#BFBFBF"));
        this.q.setBackground(gradientDrawable3);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(M(272), M(70));
        layoutParams10.topMargin = M(30);
        this.f11390h.addView(this.q, layoutParams10);
        this.q.setVisibility(8);
    }

    private void P() {
        JdThemeTitle jdThemeTitle = this.f11389g;
        if (jdThemeTitle != null) {
            jdThemeTitle.setLeftIv1ClickListener(new a());
        }
        TextView textView = this.f11394l;
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        TextView textView2 = this.o;
        if (textView2 != null) {
            textView2.setOnClickListener(new c());
        }
        TextView textView3 = this.q;
        if (textView3 != null) {
            textView3.setOnClickListener(new d());
        }
    }

    private void Q() {
        this.f11391i.setImageResource(R.drawable.y_03);
        this.f11392j.setText("\u6253\u5f00\u9875\u9762\u5f02\u5e38");
        this.f11393k.setText("\u8bf7\u70b9\u51fb\"\u7f51\u7edc\u68c0\u6d4b\"");
        this.f11394l.setVisibility(0);
    }

    private void initData() {
        this.v = new ArrayList();
        this.x = new ArrayList();
        this.z = "";
    }

    @Override // com.jingdong.common.BaseActivity
    public void checkNetwork() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.statusBarTransparentEnable = true;
        setContentView(R.layout.activity_network_diagnose);
        O();
        Q();
        P();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.A != null) {
            getHandler().removeCallbacks(this.A);
        }
        super.onDestroy();
        OKLog.d("lake", "onDestroy");
    }
}
