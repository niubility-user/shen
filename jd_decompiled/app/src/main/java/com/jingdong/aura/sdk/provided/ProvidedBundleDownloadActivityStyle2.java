package com.jingdong.aura.sdk.provided;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StatFs;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.R;
import com.jingdong.aura.sdk.update.b.f;
import com.jingdong.aura.sdk.update.b.i;
import com.jingdong.aura.sdk.update.b.j;
import com.jingdong.aura.sdk.update.updater.IUpdateListener;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ProvidedBundleDownloadActivityStyle2 extends Activity {
    static final String a = ProvidedBundleDownloadActivityStyle2.class.getSimpleName();
    private c A;

    /* renamed from: c */
    private String f12172c;
    private String d;

    /* renamed from: e */
    private String f12173e;

    /* renamed from: f */
    private IBinder f12174f;

    /* renamed from: h */
    private ArrayList<String> f12176h;

    /* renamed from: i */
    private String f12177i;

    /* renamed from: j */
    private Uri f12178j;

    /* renamed from: k */
    private Bundle f12179k;

    /* renamed from: l */
    private String f12180l;

    /* renamed from: m */
    private long f12181m;

    /* renamed from: n */
    private float f12182n;
    private ProvidedCircleProgressBar o;
    private ImageView p;
    private TextView q;
    private TextView r;
    private Button s;
    private Button t;
    private TextView u;
    private TextView v;
    private long w;
    private a x;
    private a y;
    private a z;

    /* renamed from: g */
    private int f12175g = -1;
    private boolean B = false;
    private int[] C = {Color.parseColor("#E42B29"), Color.parseColor("#FF4C2A")};
    private Handler D = new Handler() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.1
        {
            ProvidedBundleDownloadActivityStyle2.this = this;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 101) {
                int intValue = ((Integer) message.obj).intValue() / 10;
                ProvidedBundleDownloadActivityStyle2.this.o.setProgress(intValue);
                ProvidedBundleDownloadActivityStyle2.this.r.setText("loading..." + intValue + "%");
            }
        }
    };
    private IUpdateListener E = new IUpdateListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.2
        {
            ProvidedBundleDownloadActivityStyle2.this = this;
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onDownloadFailure(Exception exc) {
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "onDownloadFailure");
            ProvidedBundleDownloadActivityStyle2.b("aura_provided_notfound_download_failed_style2", ProvidedBundleDownloadActivityStyle2.this.f12173e);
            i.a().a(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.2.1
                {
                    AnonymousClass2.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "changestate----downloadErrorUIState");
                    ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2 = ProvidedBundleDownloadActivityStyle2.this;
                    providedBundleDownloadActivityStyle2.a(providedBundleDownloadActivityStyle2.z);
                }
            });
            com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleDownloadActivityStyle2.this.f12173e, -1, "isMemoryEnough:" + ProvidedBundleDownloadActivityStyle2.this.d() + " diskFreeSize" + ProvidedBundleDownloadActivityStyle2.b(), "ProvidedBundleDownloadActivityStyle2.onDownloadFailure", exc);
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onDownloadFinish(AuraBundleResult auraBundleResult) {
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "onDownloadFinish");
            ProvidedBundleDownloadActivityStyle2.b("aura_provided_notfound_download_success_style2", ProvidedBundleDownloadActivityStyle2.this.f12173e);
            Message obtainMessage = ProvidedBundleDownloadActivityStyle2.this.D.obtainMessage();
            obtainMessage.what = 101;
            obtainMessage.obj = 1000;
            ProvidedBundleDownloadActivityStyle2.this.D.sendMessage(obtainMessage);
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onDownloadPause(boolean z) {
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onDownloadProgress(long j2, long j3) {
            if (ProvidedBundleDownloadActivityStyle2.this.f12181m != 0) {
                j2 = ProvidedBundleDownloadActivityStyle2.this.f12181m;
            }
            if (j2 <= 0) {
                j2 = 1000;
            }
            int i2 = (int) ((1000 * j3) / j2);
            if (i2 > 1000) {
                i2 = 1000;
            }
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "onDownloadProgressChanged" + j2 + LangUtils.SINGLE_SPACE + j3 + LangUtils.SINGLE_SPACE + i2);
            Message obtainMessage = ProvidedBundleDownloadActivityStyle2.this.D.obtainMessage();
            obtainMessage.what = 101;
            obtainMessage.obj = Integer.valueOf(i2);
            ProvidedBundleDownloadActivityStyle2.this.D.sendMessage(obtainMessage);
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onDownloadStart() {
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onInstallFinish(boolean z) {
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "onInstallFinish :".concat(String.valueOf(z)));
            ProvidedBundleDownloadActivityStyle2.b("aura_provided_notfound_install_finished_style2", ProvidedBundleDownloadActivityStyle2.this.f12173e + ":" + z);
            if (z) {
                ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2 = ProvidedBundleDownloadActivityStyle2.this;
                com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, " -->> unRegisterReceiver()");
                try {
                    providedBundleDownloadActivityStyle2.unregisterReceiver(providedBundleDownloadActivityStyle2.b);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                ProvidedBundleDownloadActivityStyle2.this.runOnUiThread(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.2.2
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        if ("installbundle".equals(ProvidedBundleDownloadActivityStyle2.this.f12172c)) {
                            ((IAuraInstallCallBack) AuraServiceLoader.get(ProvidedBundleDownloadActivityStyle2.this, IAuraInstallCallBack.class)).installFinished(ProvidedBundleDownloadActivityStyle2.this.f12173e, true, null);
                            ProvidedBundleDownloadActivityStyle2.i(ProvidedBundleDownloadActivityStyle2.this);
                            ProvidedBundleDownloadActivityStyle2.this.finish();
                            return;
                        }
                        Intent intent = new Intent();
                        ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle22 = ProvidedBundleDownloadActivityStyle2.this;
                        intent.setComponent(new ComponentName(providedBundleDownloadActivityStyle22, providedBundleDownloadActivityStyle22.d));
                        if (ProvidedBundleDownloadActivityStyle2.this.f12178j != null) {
                            intent.setData(ProvidedBundleDownloadActivityStyle2.this.f12178j);
                        }
                        if (ProvidedBundleDownloadActivityStyle2.this.f12179k != null) {
                            intent.putExtras(ProvidedBundleDownloadActivityStyle2.this.f12179k);
                        }
                        ProvidedBundleDownloadActivityStyle2.this.finish();
                        if (ProvidedBundleDownloadActivityStyle2.this.f12175g < 0 || ProvidedBundleDownloadActivityStyle2.this.f12174f == null) {
                            com.jingdong.aura.sdk.update.b.c.a("startActivity: intent:".concat(String.valueOf(intent)));
                            ProvidedBundleDownloadActivityStyle2.this.startActivity(intent);
                            return;
                        }
                        Activity activity = AuraConfig.getActivity(ProvidedBundleDownloadActivityStyle2.this.f12174f);
                        com.jingdong.aura.sdk.update.b.c.a("startActivityForResult: requestCode:" + ProvidedBundleDownloadActivityStyle2.this.f12175g + "intent:" + intent + " activity:" + activity);
                        if (activity != null) {
                            activity.startActivityForResult(intent, ProvidedBundleDownloadActivityStyle2.this.f12175g);
                        } else {
                            ProvidedBundleDownloadActivityStyle2.this.startActivity(intent);
                        }
                    }
                });
                return;
            }
            i.a().a(new Runnable() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.2.3
                {
                    AnonymousClass2.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "changestate----downloadErrorUIState");
                    ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle22 = ProvidedBundleDownloadActivityStyle2.this;
                    providedBundleDownloadActivityStyle22.a(providedBundleDownloadActivityStyle22.z);
                }
            });
            com.jingdong.aura.sdk.update.a.a().f12243m.onException(ProvidedBundleDownloadActivityStyle2.this.f12173e, -1, "install failed,isMemoryEnough:" + ProvidedBundleDownloadActivityStyle2.this.d() + ",diskFreeSize" + ProvidedBundleDownloadActivityStyle2.b(), "ProvidedBundleDownloadActivityStyle2.onInstallFinish", new RuntimeException("install failed"));
        }

        @Override // com.jingdong.aura.sdk.update.updater.IUpdateListener
        public final void onInstallStart() {
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "onInstallStart");
        }
    };
    private View.OnClickListener F = new View.OnClickListener() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.3
        {
            ProvidedBundleDownloadActivityStyle2.this = this;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (view.getId() == R.id.aura_provided_download_direct_controllbtn) {
                ProvidedBundleDownloadActivityStyle2.this.x.a();
            } else if (view.getId() == R.id.provided_bundle_title_direct_back) {
                ProvidedBundleDownloadActivityStyle2.this.x.b();
            }
        }
    };
    BroadcastReceiver b = new BroadcastReceiver() { // from class: com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.4
        {
            ProvidedBundleDownloadActivityStyle2.this = this;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            j.b(ProvidedBundleDownloadActivityStyle2.this);
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "----\u7f51\u7edc\u91cd\u65b0\u5207\u6362----");
            com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "----\u65e0\u7f51\u7edc----");
            if (ProvidedBundleDownloadActivityStyle2.this.x instanceof d) {
                com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "----\u5f53\u524d\u72b6\u6001:DownloadingUIState");
            } else if (!(ProvidedBundleDownloadActivityStyle2.this.x instanceof b)) {
                return;
            } else {
                com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "----\u5f53\u524d\u72b6\u6001:DownloadErrorUIState");
                com.jingdong.aura.sdk.update.b.c.a(ProvidedBundleDownloadActivityStyle2.a, "changestate----downloadingUIState");
                ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2 = ProvidedBundleDownloadActivityStyle2.this;
                providedBundleDownloadActivityStyle2.a(providedBundleDownloadActivityStyle2.y);
            }
            ProvidedBundleDownloadActivityStyle2.this.c();
        }
    };

    /* loaded from: classes4.dex */
    public abstract class a {
        String a = "\u6539\u8d44\u6e90\u5305";
        int b = 0;

        /* renamed from: c */
        int f12183c = 13;
        int d;

        /* renamed from: e */
        int f12184e;

        /* renamed from: f */
        int f12185f;

        /* renamed from: g */
        String f12186g;

        /* renamed from: h */
        int f12187h;

        /* renamed from: i */
        int f12188i;

        /* renamed from: j */
        int f12189j;

        /* renamed from: k */
        int f12190k;

        /* renamed from: l */
        String f12191l;

        /* renamed from: m */
        int f12192m;

        /* renamed from: n */
        int f12193n;
        int o;
        int p;
        int q;
        int r;
        int s;
        a t;

        a() {
            ProvidedBundleDownloadActivityStyle2.this = r4;
            int i2 = R.color.c_000000;
            this.d = i2;
            this.f12184e = 10;
            this.f12185f = 0;
            this.f12186g = "\u9a6c\u4e0a\u4e0b\u8f7d\u5b8c\u6210";
            this.f12187h = 0;
            this.f12188i = 13;
            this.f12189j = i2;
            this.f12190k = 0;
            this.f12191l = "\u91cd\u8bd5";
            this.f12192m = 0;
            this.f12193n = R.drawable.aura_provided_download_ctl;
            this.o = R.color.c_FFFFFF;
            this.p = 4;
            this.q = 4;
            this.r = 4;
            this.s = 4;
        }

        abstract void a();

        abstract void b();
    }

    /* loaded from: classes4.dex */
    class b extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b() {
            super();
            ProvidedBundleDownloadActivityStyle2.this = r3;
            this.a = "";
            this.f12183c = 13;
            this.b = 4;
            this.f12184e = 10;
            this.f12186g = "\u4e0b\u8f7d\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5";
            this.f12187h = 0;
            this.f12188i = 13;
            this.p = 4;
            this.q = 4;
            this.r = 4;
            this.s = 4;
            this.f12191l = "\u91cd\u8bd5";
            this.f12193n = R.drawable.aura_provided_download_ctl;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void a() {
            if (j.b(ProvidedBundleDownloadActivityStyle2.this)) {
                ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2 = ProvidedBundleDownloadActivityStyle2.this;
                providedBundleDownloadActivityStyle2.a(providedBundleDownloadActivityStyle2.y);
                ProvidedBundleDownloadActivityStyle2.this.c();
            }
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void b() {
            ProvidedBundleDownloadActivityStyle2.this.finish();
        }
    }

    /* loaded from: classes4.dex */
    class c extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c() {
            super();
            ProvidedBundleDownloadActivityStyle2.this = r2;
            this.a = "\u4e0b\u8f7d\u4e2d\u65ad";
            this.f12183c = 15;
            this.b = 0;
            this.f12184e = 2;
            this.f12185f = 1;
            this.f12186g = "\u624b\u673a\u7a7a\u95f4\u4e0d\u8db3\n\u8bf7\u6e05\u7406\u540e\u91cd\u8bd5";
            this.f12187h = 0;
            this.f12188i = 13;
            this.p = 4;
            this.q = 4;
            this.r = 4;
            this.s = 4;
            this.f12191l = "\u91cd\u8bd5";
            this.f12193n = R.drawable.aura_provided_download_ctl;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void a() {
            if (ProvidedBundleDownloadActivityStyle2.this.d()) {
                ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2 = ProvidedBundleDownloadActivityStyle2.this;
                providedBundleDownloadActivityStyle2.a(providedBundleDownloadActivityStyle2.y);
                ProvidedBundleDownloadActivityStyle2.this.c();
            }
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void b() {
            ProvidedBundleDownloadActivityStyle2.this.finish();
        }
    }

    /* loaded from: classes4.dex */
    class d extends a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d() {
            super();
            ProvidedBundleDownloadActivityStyle2.this = r1;
            this.b = 4;
            this.f12187h = 4;
            this.f12192m = 4;
            this.p = 0;
            this.q = 0;
            this.r = 0;
            this.s = 0;
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void a() {
            ProvidedBundleDownloadActivityStyle2.this.c();
        }

        @Override // com.jingdong.aura.sdk.provided.ProvidedBundleDownloadActivityStyle2.a
        final void b() {
            ProvidedBundleDownloadActivityStyle2.this.finish();
        }
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        this.x = aVar;
        this.u.setText(aVar.a);
        this.u.setTextSize(this.x.f12183c);
        this.u.setVisibility(this.x.b);
        this.u.setTextColor(getResources().getColor(this.x.d));
        this.u.setTypeface(Typeface.defaultFromStyle(this.x.f12185f));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.u.getLayoutParams();
        layoutParams.topMargin = f.c(this.x.f12184e);
        this.u.setLayoutParams(layoutParams);
        this.v.setText(this.x.f12186g);
        this.v.setVisibility(this.x.f12187h);
        this.v.setTextSize(this.x.f12188i);
        this.v.setTextColor(getResources().getColor(this.x.f12189j));
        this.v.setTypeface(Typeface.defaultFromStyle(this.x.f12190k));
        this.o.setVisibility(this.x.p);
        this.p.setVisibility(this.x.q);
        this.q.setVisibility(this.x.r);
        this.r.setVisibility(this.x.s);
        this.s.setVisibility(this.x.f12192m);
        this.s.setText(this.x.f12191l);
        this.s.setBackgroundResource(this.x.f12193n);
        this.s.setTextColor(getResources().getColor(this.x.o));
    }

    static /* synthetic */ long b() {
        return e();
    }

    public static void b(String str, String str2) {
        com.jingdong.aura.sdk.update.a.a().f12243m.onTrace(str, str2, "ProvidedBundleDownloadActivityStyle2");
    }

    public void c() {
        b("aura_provided_notfound_startdownload_style2", this.f12173e);
        com.jingdong.aura.sdk.update.a.a().f12242l.a(this.f12180l, this.E, 2);
    }

    public boolean d() {
        long e2 = e();
        this.w = e2;
        return e2 >= 10485760 && e2 >= this.f12181m * 3;
    }

    private static long e() {
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

    static /* synthetic */ boolean i(ProvidedBundleDownloadActivityStyle2 providedBundleDownloadActivityStyle2) {
        providedBundleDownloadActivityStyle2.B = true;
        return true;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.B) {
            return;
        }
        ((IAuraInstallCallBack) AuraServiceLoader.get(this, IAuraInstallCallBack.class)).installFinished(this.f12173e, false, null);
        com.jingdong.aura.sdk.update.a.a().f12243m.onException(this.f12173e, -1, "install not finished and finish download page!!", "ProvidedBundleDownloadActivityStyle2.finish", null);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        a aVar;
        super.onCreate(bundle);
        setContentView(R.layout.activity_provided_bundle_download_style2);
        setFinishOnTouchOutside(false);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.d = intent.getStringExtra("aura_target_classname");
        this.f12173e = intent.getStringExtra("aura_target_bundlename");
        this.f12172c = intent.getStringExtra("aura_action");
        this.f12176h = intent.getStringArrayListExtra("aura_not_prepared_bundlename");
        if (Build.VERSION.SDK_INT >= 18) {
            this.f12174f = intent.getExtras().getBinder("mToken");
        }
        this.f12175g = intent.getIntExtra("requestCode", -1);
        this.f12178j = intent.getData();
        this.f12179k = intent.getExtras();
        b("aura_provided_notfound_style2", this.f12173e);
        ArrayList<String> arrayList = this.f12176h;
        if (arrayList == null || arrayList.size() <= 0) {
            finish();
            return;
        }
        this.f12177i = this.f12176h.get(0);
        String updateIdFromBundleName = com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getUpdateIdFromBundleName(this.f12177i);
        this.f12180l = updateIdFromBundleName;
        if (TextUtils.isEmpty(updateIdFromBundleName)) {
            finish();
            return;
        }
        this.f12181m = AuraConfig.getBundleSize(this.f12177i);
        this.f12182n = Math.round(((((float) r0) / 1024.0f) / 1024.0f) * 100.0f) / 100.0f;
        this.o = (ProvidedCircleProgressBar) findViewById(R.id.aura_provided_circleProgressBar);
        this.p = (ImageView) findViewById(R.id.aura_provided_circleProgressImage);
        this.q = (TextView) findViewById(R.id.aura_provided_direct_top_text);
        this.r = (TextView) findViewById(R.id.aura_provided_direct_bottom_text);
        this.u = (TextView) findViewById(R.id.aura_provided_direct_text1);
        this.v = (TextView) findViewById(R.id.aura_provided_direct_text2);
        this.s = (Button) findViewById(R.id.aura_provided_download_direct_controllbtn);
        this.t = (Button) findViewById(R.id.provided_bundle_title_direct_back);
        this.o.setFirstColor(-3355444);
        this.o.setColorArray(this.C);
        this.o.setCircleWidth(4);
        this.s.setOnClickListener(this.F);
        this.t.setOnClickListener(this.F);
        com.jingdong.aura.sdk.update.b.c.a(a, " -->> registerReceiver()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.b, intentFilter);
        this.y = new d();
        this.z = new b();
        c cVar = new c();
        this.A = cVar;
        a aVar2 = this.y;
        a aVar3 = this.z;
        aVar2.t = aVar3;
        aVar3.t = aVar2;
        cVar.t = aVar2;
        if (d()) {
            c();
            aVar = this.y;
        } else {
            aVar = this.A;
        }
        a(aVar);
    }
}
