package com.jingdong.moutaibuy.lib;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.moutaibuy.lib.flow.StepsView;
import com.jingdong.moutaibuy.lib.g.a;
import com.jingdong.moutaibuy.lib.view.MouTaiScanView;
import com.jingdong.moutaibuy.lib.view.b;
import com.jingdong.moutaibuy.lib.workflow.WorkflowModel;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class MouTaiScannerActivity extends AppCompatActivity implements View.OnClickListener, a.InterfaceC0707a, com.jingdong.moutaibuy.lib.e.a {
    private boolean A;
    com.jingdong.moutaibuy.lib.workflow.b B;

    /* renamed from: g  reason: collision with root package name */
    private HashMap<String, Integer> f14551g;

    /* renamed from: h  reason: collision with root package name */
    MouTaiScanView f14552h;

    /* renamed from: i  reason: collision with root package name */
    ImageView f14553i;

    /* renamed from: j  reason: collision with root package name */
    ImageView f14554j;

    /* renamed from: k  reason: collision with root package name */
    ImageView f14555k;

    /* renamed from: l  reason: collision with root package name */
    ImageView f14556l;

    /* renamed from: m  reason: collision with root package name */
    StepsView f14557m;

    /* renamed from: n  reason: collision with root package name */
    ProgressBar f14558n;
    TextView o;
    TextView p;
    TextView q;
    TextView r;
    RelativeLayout s;
    RelativeLayout t;
    RelativeLayout u;
    RelativeLayout v;
    WorkflowModel x;
    private k y;
    private com.jingdong.moutaibuy.lib.g.a z;
    private int w = 0;
    private final Object C = new Object();
    private boolean D = true;
    @SuppressLint({"HandlerLeak"})
    private Handler E = new b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements b.c {
        final /* synthetic */ com.jingdong.moutaibuy.lib.view.b a;

        a(com.jingdong.moutaibuy.lib.view.b bVar) {
            this.a = bVar;
        }

        @Override // com.jingdong.moutaibuy.lib.view.b.c
        public void a() {
            this.a.dismiss();
            MouTaiScannerActivity.this.F();
        }
    }

    /* loaded from: classes16.dex */
    class b extends Handler {
        b() {
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i2 = message.what;
            if (i2 == 10) {
                MouTaiScannerActivity.this.w = message.arg1;
                MouTaiScannerActivity.this.M(true, null, message.arg2);
            } else if (i2 == 11) {
                MouTaiScannerActivity.this.M(false, (String) message.obj, message.arg1);
            } else if (i2 == 14) {
                MouTaiScannerActivity.this.L(true, message.arg1);
            } else if (i2 != 15) {
            } else {
                MouTaiScannerActivity.this.L(false, message.arg1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements Observer<com.jingdong.moutaibuy.lib.workflow.c> {
        c() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onChanged(com.jingdong.moutaibuy.lib.workflow.c cVar) {
            MouTaiScannerActivity.this.O(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f14560g;

        d(String str) {
            this.f14560g = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MouTaiScannerActivity.this.P(this.f14560g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f14562g;

        e(JDDialog jDDialog) {
            this.f14562g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f14562g.dismiss();
            MouTaiScannerActivity.this.E();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class f implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f14564g;

        f(MouTaiScannerActivity mouTaiScannerActivity, JDDialog jDDialog) {
            this.f14564g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f14564g.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class g implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f14565g;

        g(JDDialog jDDialog) {
            this.f14565g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f14565g.dismiss();
            Intent intent = new Intent();
            intent.putExtra("code", 9002);
            intent.putExtra("step", MouTaiScannerActivity.this.w + 1);
            MouTaiScannerActivity.this.setResult(-1, intent);
            MouTaiScannerActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class h extends com.jingdong.moutaibuy.lib.b.a {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f14567h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f14568i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ int f14569j;

        h(int i2, int i3, int i4) {
            this.f14567h = i2;
            this.f14568i = i3;
            this.f14569j = i4;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            synchronized (MouTaiScannerActivity.this.C) {
                if (!this.f14581g) {
                    Message obtain = Message.obtain();
                    obtain.what = this.f14567h;
                    obtain.arg1 = this.f14568i;
                    MouTaiScannerActivity.this.E.sendMessageDelayed(obtain, this.f14569j);
                }
                this.f14581g = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class i extends com.jingdong.moutaibuy.lib.b.a {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ boolean f14571h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f14572i;

        i(boolean z, int i2) {
            this.f14571h = z;
            this.f14572i = i2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            synchronized (MouTaiScannerActivity.this.C) {
                if (!this.f14581g) {
                    MouTaiScannerActivity.this.u.setVisibility(8);
                    MouTaiScannerActivity.this.D = true;
                    if (this.f14571h) {
                        if (MouTaiScannerActivity.this.w >= 8) {
                            MouTaiScannerActivity.this.K(this.f14572i);
                        } else {
                            MouTaiScannerActivity mouTaiScannerActivity = MouTaiScannerActivity.this;
                            mouTaiScannerActivity.N(mouTaiScannerActivity.w);
                        }
                    } else {
                        MouTaiScannerActivity.this.v.setVisibility(0);
                    }
                }
                this.f14581g = true;
            }
        }
    }

    /* loaded from: classes16.dex */
    class j implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f14574g;

        j(JDDialog jDDialog) {
            this.f14574g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f14574g.dismiss();
            Intent intent = new Intent();
            intent.putExtra("code", 9003);
            intent.putExtra("step", MouTaiScannerActivity.this.w + 1);
            MouTaiScannerActivity.this.setResult(-1, intent);
            MouTaiScannerActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class k extends CountDownTimer {
        private boolean a;

        public k(long j2, long j3) {
            super(j2, j3);
            this.a = false;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (this.a) {
                return;
            }
            this.a = true;
            MouTaiScannerActivity.this.r.setText("00 : 00");
            MouTaiScannerActivity.this.J();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            MouTaiScannerActivity mouTaiScannerActivity = MouTaiScannerActivity.this;
            mouTaiScannerActivity.r.setText(mouTaiScannerActivity.G(j2));
        }
    }

    private void H() {
        WorkflowModel workflowModel = (WorkflowModel) new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WorkflowModel.class);
        this.x = workflowModel;
        workflowModel.a().observe(this, new c());
        N(this.w);
    }

    private void I() {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(this, getString(R.string.confirm_quit_title), getString(R.string.confirm_quit_content), getString(R.string.confirm_quit_left_button_text), getString(R.string.confirm_quit_right_button_text));
        createJdDialogWithStyle6.setCancelable(false);
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new f(this, createJdDialogWithStyle6));
        createJdDialogWithStyle6.setOnRightButtonClickListener(new g(createJdDialogWithStyle6));
        createJdDialogWithStyle6.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        this.B.m();
        this.f14552h.r();
        JDDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this, getString(R.string.over_time_title), getString(R.string.over_time_content), getString(R.string.over_time_button_text));
        createJdDialogWithStyle5.setCancelable(false);
        createJdDialogWithStyle5.setOnLeftButtonClickListener(new e(createJdDialogWithStyle5));
        createJdDialogWithStyle5.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(int i2) {
        this.w--;
        k kVar = this.y;
        if (kVar != null) {
            kVar.cancel();
        }
        this.v.setVisibility(8);
        this.f14558n.setVisibility(4);
        com.jingdong.moutaibuy.lib.view.b bVar = new com.jingdong.moutaibuy.lib.view.b(this);
        bVar.e(i2);
        bVar.f(new a(bVar));
        bVar.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(int i2) {
        this.x.b(i2);
        this.B.l(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(com.jingdong.moutaibuy.lib.workflow.c cVar) {
        k kVar = this.y;
        if (kVar != null) {
            kVar.cancel();
        }
        Integer num = this.f14551g.get(String.valueOf(cVar.a + 1));
        long intValue = (num == null ? 180 : num.intValue()) * 1000;
        this.r.setText(G(intValue));
        StepsView stepsView = this.f14557m;
        stepsView.e(cVar.a);
        stepsView.a();
        int i2 = cVar.d;
        if (i2 == -1) {
            this.f14555k.setImageDrawable(null);
        } else {
            this.f14555k.setImageResource(i2);
        }
        this.o.setText(cVar.b);
        this.s.setAlpha(1.0f);
        this.t.setVisibility(8);
        String str = cVar.f14644e;
        JDImageLoader.display(str, this.f14553i, (JDDisplayImageOptions) null, (ImageRequestListener<ImageInfo>) null);
        this.s.setOnClickListener(new d(str));
        this.p.setText(cVar.f14643c);
        this.v.setVisibility(0);
        k kVar2 = new k(intValue, 1000L);
        this.y = kVar2;
        kVar2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(String str) {
        JDImageLoader.display(str, this.f14554j, (JDDisplayImageOptions) null, (ImageRequestListener<ImageInfo>) null);
        this.s.setAlpha(0.0f);
        this.t.setVisibility(0);
    }

    private void initView() {
        int statusBarHeight = UnStatusBarTintUtil.getStatusBarHeight((Activity) this);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar_top);
        this.f14558n = progressBar;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
        layoutParams.setMargins(0, statusBarHeight, 0, 0);
        this.f14558n.setLayoutParams(layoutParams);
        this.r = (TextView) findViewById(R.id.tv_time_counter);
        this.o = (TextView) findViewById(R.id.tv_tip_text);
        this.q = (TextView) findViewById(R.id.tv_exit);
        this.f14553i = (ImageView) findViewById(R.id.thumbnail);
        this.f14554j = (ImageView) findViewById(R.id.expanded_image);
        this.p = (TextView) findViewById(R.id.content);
        this.f14555k = (ImageView) findViewById(R.id.scan_background);
        this.f14552h = (MouTaiScanView) findViewById(R.id.qrcode_scanner);
        this.f14557m = (StepsView) findViewById(R.id.stepsView);
        this.s = (RelativeLayout) findViewById(R.id.rl_thumbnail);
        this.t = (RelativeLayout) findViewById(R.id.rl_expanded_image);
        this.u = (RelativeLayout) findViewById(R.id.rl_scan_result);
        this.f14556l = (ImageView) findViewById(R.id.iv_scan_progress_anim);
        this.v = (RelativeLayout) findViewById(R.id.rl_scan_progress_anim);
        StepsView stepsView = this.f14557m;
        stepsView.i(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        stepsView.c(getResources().getColor(R.color.select_step_indicator_color));
        Resources resources = getResources();
        int i2 = R.color.white;
        stepsView.j(resources.getColor(i2));
        stepsView.g(getResources().getColor(i2));
        stepsView.m(-1);
        stepsView.n(R.color.black);
        stepsView.e(1);
        stepsView.l(com.jd.lib.un.basewidget.widget.simple.e.a.a(2.0f));
        stepsView.d(com.jd.lib.un.basewidget.widget.simple.e.a.a(10.0f));
        stepsView.k(com.jd.lib.un.basewidget.widget.simple.e.a.a(40.0f));
        stepsView.a();
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(800L);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        this.f14556l.startAnimation(rotateAnimation);
        ((Button) findViewById(R.id.next_step)).setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.q.setOnClickListener(this);
    }

    public void E() {
        Intent intent = new Intent();
        intent.putExtra("code", 9001);
        intent.putExtra("step", this.w + 1);
        setResult(-1, intent);
        finish();
    }

    public void F() {
        Intent intent = new Intent();
        intent.putExtra("code", 9000);
        intent.putExtra("step", this.w + 1);
        setResult(-1, intent);
        finish();
    }

    public String G(long j2) {
        String str;
        String str2;
        long j3 = j2 / 1000;
        long j4 = j3 / 60;
        long j5 = j3 % 60;
        if (j4 < 10) {
            str = "0" + j4;
        } else {
            str = j4 + "";
        }
        if (j5 < 10) {
            str2 = "0" + j5;
        } else {
            str2 = j5 + "";
        }
        return str + " : " + str2;
    }

    public void L(boolean z, int i2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.u, "translationX", 0.0f, -this.f14558n.getWidth());
        ofFloat.setDuration(0L);
        ofFloat.addListener(new i(z, i2));
        ofFloat.start();
    }

    public void M(boolean z, String str, int i2) {
        int i3;
        int i4 = 15;
        if (z) {
            this.E.removeMessages(15);
            this.u.setVisibility(8);
            this.D = true;
        }
        if (this.D) {
            this.v.setVisibility(8);
            this.D = false;
            if (z) {
                i4 = 14;
                i3 = 2000;
                this.u.removeAllViews();
                this.u.addView(LayoutInflater.from(this).inflate(R.layout.layout_scan_success, (ViewGroup) null));
            } else {
                i3 = 4000;
                View inflate = LayoutInflater.from(this).inflate(R.layout.layout_scan_fail, (ViewGroup) null);
                ((TextView) inflate.findViewById(R.id.tv_prompt_content)).setText(str);
                this.u.removeAllViews();
                this.u.addView(inflate);
            }
            this.u.setVisibility(0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.u, "translationX", this.f14558n.getWidth(), 0.0f);
            ofFloat.setDuration(0L);
            ofFloat.addListener(new h(i4, i2, i3));
            ofFloat.start();
        }
    }

    @Override // com.jingdong.moutaibuy.lib.e.a
    public void h() {
        Toast.makeText(this, "\u6253\u5f00\u76f8\u673a\u51fa\u9519", 0).show();
    }

    @Override // com.jingdong.moutaibuy.lib.e.a
    public void j(String str) {
        this.B.m();
        this.f14552h.r();
        k kVar = this.y;
        if (kVar != null) {
            kVar.cancel();
        }
        JDDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this, getString(R.string.video_continuity_check_fail_title), getString(R.string.video_continuity_check_fail_content), getString(R.string.over_time_button_text));
        createJdDialogWithStyle5.setCancelable(false);
        createJdDialogWithStyle5.setOnLeftButtonClickListener(new j(createJdDialogWithStyle5));
        createJdDialogWithStyle5.show();
    }

    @Override // com.jingdong.moutaibuy.lib.e.a
    public void l(int i2, String str) {
        if (i2 == this.w) {
            Message obtain = Message.obtain();
            obtain.what = 11;
            obtain.obj = str;
            this.E.sendMessage(obtain);
        }
    }

    @Override // com.jingdong.moutaibuy.lib.e.a
    public void n(int i2, int i3) {
        k kVar = this.y;
        if (kVar != null) {
            kVar.cancel();
        }
        Message obtain = Message.obtain();
        obtain.what = 10;
        obtain.arg1 = i2;
        obtain.arg2 = i3;
        this.E.sendMessage(obtain);
    }

    @Override // com.jingdong.moutaibuy.lib.e.a
    public void o(String str) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        I();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.next_step) {
            this.w++;
            M(true, null, -1);
        } else if (id == R.id.rl_expanded_image) {
            this.s.setAlpha(1.0f);
            this.t.setVisibility(8);
        } else if (id == R.id.tv_exit) {
            I();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.defaultSetTranslucent(this);
        setContentView(R.layout.activity_mou_tai_scanner);
        UnStatusBarTintUtil.setStatusBarDarkMode(this);
        initView();
        com.jingdong.moutaibuy.lib.g.a a2 = com.jingdong.moutaibuy.lib.a.a();
        this.z = a2;
        if (a2 != null) {
            boolean hasPermission = a2.hasPermission(this, "android.permission.CAMERA");
            this.A = hasPermission;
            if (!hasPermission) {
                this.z.a(this, "android.permission.CAMERA", this);
            }
        } else {
            this.A = true;
        }
        String string = getIntent().getExtras().getString("timeList", "");
        int i2 = getIntent().getExtras().getInt("continuityInterval", 1);
        this.f14551g = com.jingdong.moutaibuy.lib.i.b.a(string);
        com.jingdong.moutaibuy.lib.workflow.b bVar = new com.jingdong.moutaibuy.lib.workflow.b(this.f14552h, this, i2);
        this.B = bVar;
        this.f14552h.i(bVar);
        H();
    }

    @Override // com.jingdong.moutaibuy.lib.g.a.InterfaceC0707a
    public void onDenied() {
        Toast.makeText(this, "\u6253\u5f00\u76f8\u673a\u51fa\u9519\uff0c\u8bf7\u6388\u4e88\u76f8\u673a\u6743\u9650\u540e\u91cd\u8bd5", 0).show();
        Intent intent = new Intent();
        intent.putExtra("code", 9002);
        intent.putExtra("step", 0);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f14552h.d();
        super.onDestroy();
    }

    @Override // com.jingdong.moutaibuy.lib.g.a.InterfaceC0707a
    public void onGranted() {
        this.f14552h.k();
        N(this.w);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        com.jingdong.moutaibuy.lib.g.a aVar = this.z;
        if (aVar != null) {
            aVar.onRequestPermissionsResult(this, i2, strArr, iArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.A) {
            this.f14552h.k();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.f14552h.q();
        super.onStop();
    }
}
