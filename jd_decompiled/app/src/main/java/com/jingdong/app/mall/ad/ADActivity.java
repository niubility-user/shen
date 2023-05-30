package com.jingdong.app.mall.ad;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.ad.c;
import com.jingdong.app.mall.ad.widget.AdButtonLottieView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.platform.business.personal.R2;
import de.greenrobot.event.EventBus;
import java.util.HashMap;

/* loaded from: classes19.dex */
public class ADActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.ad.d f7881g;

    /* renamed from: h  reason: collision with root package name */
    private com.jingdong.app.mall.ad.b f7882h;

    /* renamed from: k  reason: collision with root package name */
    private RelativeLayout f7885k;

    /* renamed from: m  reason: collision with root package name */
    private HashMap<String, String> f7887m;

    /* renamed from: n  reason: collision with root package name */
    private RelativeLayout f7888n;
    private boolean o;
    private float p;

    /* renamed from: i  reason: collision with root package name */
    private int f7883i = 1;

    /* renamed from: j  reason: collision with root package name */
    private int f7884j = 0;

    /* renamed from: l  reason: collision with root package name */
    private String f7886l = "";

    /* loaded from: classes19.dex */
    class a implements c.InterfaceC0236c {

        /* renamed from: com.jingdong.app.mall.ad.ADActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes19.dex */
        class RunnableC0233a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Bitmap f7889g;

            RunnableC0233a(Bitmap bitmap) {
                this.f7889g = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.app.mall.ad.c.l().x(ADActivity.this.f7881g.f7904e);
                ADActivity.this.f7883i = com.jingdong.app.mall.ad.c.l().m("start_image_show_times");
                ImageView imageView = (ImageView) ADActivity.this.findViewById(R.id.ad_image);
                imageView.setImageBitmap(this.f7889g);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setContentDescription("\u95ea\u5c4f\u5e7f\u544a");
                imageView.setOnTouchListener(ADActivity.this);
                imageView.setOnClickListener(ADActivity.this);
                ADActivity.this.f7886l = com.jingdong.app.mall.ad.c.l().n(ADActivity.this.f7881g, "0", "1", ADActivity.this.f7883i);
                JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "StartPhoto_Popup", "0_null_" + ADActivity.this.f7882h.a + "_1_" + ADActivity.this.f7884j + CartConstant.KEY_YB_INFO_LINK + ADActivity.this.f7883i, "StartPhoto_Main", RunnableC0233a.class.getName(), "", ADActivity.this.f7886l, ADActivity.this.f7887m);
                if (!TextUtils.isEmpty(ADActivity.this.f7881g.x)) {
                    JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "StartPhoto_PopupAD", "0_null_" + ADActivity.this.f7882h.a + "_1_" + ADActivity.this.f7884j + CartConstant.KEY_YB_INFO_LINK + ADActivity.this.f7883i, "StartPhoto_Main", RunnableC0233a.class.getName(), "", ADActivity.this.f7886l, ADActivity.this.f7887m);
                }
                ADActivity.this.T(this.f7889g);
            }
        }

        a() {
        }

        @Override // com.jingdong.app.mall.ad.c.InterfaceC0236c
        public void a(Bitmap bitmap) {
            if (bitmap == null) {
                ADActivity.this.finish();
                return;
            }
            ADActivity aDActivity = ADActivity.this;
            aDActivity.postUrl(aDActivity.f7881g.f7911l);
            ADActivity.this.post(new RunnableC0233a(bitmap));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements e.b {
        final /* synthetic */ SimpleDraweeView a;
        final /* synthetic */ TextView b;

        b(ADActivity aDActivity, SimpleDraweeView simpleDraweeView, TextView textView) {
            this.a = simpleDraweeView;
            this.b = textView;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            this.a.setVisibility(8);
            this.b.setVisibility(0);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            this.a.setVisibility(0);
            this.b.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ADActivity.this.U()) {
                ADActivity.this.Q();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public class d extends CountDownTimer {
        TextView a;

        public d(long j2, long j3, TextView textView) {
            super(j2, j3);
            this.a = textView;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            ADActivity.this.finish();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            if (j2 > 0) {
                this.a.setText(String.valueOf((int) Math.ceil(j2 / 1000)));
            }
            if (Log.D) {
                Log.d("ADActivity", "CountDown left time: ===>>> " + j2);
            }
        }
    }

    private void D() {
        TextView textView = new TextView(JdSdk.getInstance().getApplicationContext());
        textView.setId(R.id.home_ad_clickBtn);
        textView.setSingleLine();
        textView.setGravity(17);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextSize(0, L(40));
        textView.setTextColor(-1);
        textView.setText(this.f7881g.A);
        textView.setBackgroundResource(R.drawable.splash_skip_btn_bg);
        Drawable drawable = getResources().getDrawable(R.drawable.splash_skip_btn_arrow);
        drawable.setBounds(0, 0, L(32), L(28));
        textView.setCompoundDrawables(null, null, drawable, null);
        textView.setCompoundDrawablePadding(L(16));
        textView.setPadding(L(100), 0, L(100), 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, L(112));
        layoutParams.addRule(13);
        this.f7888n.addView(textView, layoutParams);
        textView.setVisibility(8);
        textView.setOnClickListener(this);
        HomeDraweeView homeDraweeView = new HomeDraweeView(JdSdk.getInstance().getApplicationContext());
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        homeDraweeView.setId(R.id.home_ad_clickBtnImg);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(L(R2.attr.calendarViewShown), L(112));
        layoutParams2.addRule(13);
        this.f7888n.addView(homeDraweeView, layoutParams2);
        homeDraweeView.setOnClickListener(this);
        e.p(homeDraweeView, this.f7881g.B, e.b, new b(this, homeDraweeView, textView));
    }

    private void E(int i2, int i3) {
        RelativeLayout relativeLayout = new RelativeLayout(JdSdk.getInstance().getApplicationContext());
        this.f7888n = relativeLayout;
        relativeLayout.setId(R.id.home_ad_clickLayout);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i3);
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        layoutParams.bottomMargin = L(R2.anim.popup_center_enter);
        this.f7885k.addView(this.f7888n, layoutParams);
        this.f7888n.setAlpha(0.0f);
    }

    private void F() {
        AdButtonLottieView adButtonLottieView = new AdButtonLottieView(JdSdk.getInstance().getApplicationContext(), getClass().getSimpleName());
        TextView textView = new TextView(JdSdk.getInstance().getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(L(R2.attr.calendarViewShown), L(132));
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        layoutParams.bottomMargin = L(40);
        this.f7888n.addView(textView, layoutParams);
        textView.setOnTouchListener(this);
        textView.setOnClickListener(new c());
        if (adButtonLottieView.e()) {
            this.f7888n.addView(adButtonLottieView, new RelativeLayout.LayoutParams(-1, -1));
            adButtonLottieView.setText(N());
            adButtonLottieView.playAnimation();
            return;
        }
        textView.setSingleLine();
        textView.setGravity(17);
        textView.setTextSize(0, L(40));
        textView.setTextColor(-1);
        textView.setText(N());
        textView.setBackgroundResource(R.drawable.splash_skip_btn_bg);
    }

    private void H() {
        if (G()) {
            E(-1, L(this.f7881g.z == 1 ? 286 : 220));
            if (this.f7881g.z == 0) {
                D();
            } else {
                this.f7888n.setOnClickListener(this);
            }
        } else if (U()) {
            E(L(600), L(300));
            F();
        }
    }

    private void I() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        if (dVar.a == 1) {
            int i2 = dVar.b;
            int i3 = i2 < 1 ? 3000 : i2 * 1000;
            TextView textView = (TextView) findViewById(R.id.ad_countdown);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.weight = L(50);
            layoutParams.height = L(50);
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(0, L(32));
            textView.setVisibility(0);
            new d(i3, 1000L, textView).start();
        }
    }

    private void J() {
        int i2 = this.f7881g.f7903c;
        if (i2 == 1 || i2 == 2) {
            View findViewById = findViewById(R.id.ad_skip);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(this.f7881g.f7903c == 2 ? 10 : 12);
            layoutParams.setMargins(0, L(128), L(32), L(50));
            findViewById.setLayoutParams(layoutParams);
            findViewById.setOnClickListener(this);
            findViewById.setContentDescription("\u8df3\u8fc7");
            TextView textView = (TextView) findViewById(R.id.ad_skip_btn);
            textView.setVisibility(0);
            textView.setPadding(this.f7881g.a == 1 ? L(6) : L(20), 0, L(20), L(1));
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams2.height = L(50);
            textView.setLayoutParams(layoutParams2);
            textView.setTextSize(0, L(28));
        }
    }

    private int L(int i2) {
        return com.jingdong.app.mall.home.floor.common.d.b(this, i2);
    }

    private int M(float f2, float f3, float f4, float f5) {
        int L = L(R2.anim.popup_center_enter);
        if (f2 != 0.0f && f3 != 0.0f && f4 != 0.0f && f5 != 0.0f) {
            try {
                L = (int) (L - (((int) ((f3 * Math.max(f4 / f2, f5 / f3)) - f5)) >> 1));
                if (L < L(20)) {
                    this.o = true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.o = true;
            }
        }
        return L;
    }

    private String N() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        return (dVar == null || TextUtils.isEmpty(dVar.A)) ? "\u6ed1\u52a8\u6216\u70b9\u51fb\u67e5\u770b\u8be6\u60c5" : f.j(10, this.f7881g.A);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        R(false);
    }

    private void R(boolean z) {
        JumpEntity jumpEntity = this.f7881g.f7909j;
        if (jumpEntity != null) {
            String str = z ? "StartPhoto_Slide" : "StartPhoto_StartPic";
            JumpUtil.execJump(this, jumpEntity, 1);
            this.f7886l = com.jingdong.app.mall.ad.c.l().n(this.f7881g, "0", "1", this.f7883i);
            com.jingdong.app.mall.home.r.c.a.u(str, "0_null_" + this.f7882h.a + "_1_" + this.f7884j + CartConstant.KEY_YB_INFO_LINK + this.f7883i, this.f7886l, "StartPhoto_Main", this.f7887m, "");
            EventBus.getDefault().post(new com.jingdong.app.mall.home.floor.common.e("adActivityOnClick"));
        }
        postUrl(this.f7881g.f7912m);
        S();
        finish();
    }

    private void S() {
        if ("1".equals(this.f7881g.v)) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putInt("noise_reduction" + this.f7881g.s, 1);
            edit.apply();
        }
    }

    public boolean G() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        return (dVar == null || dVar.y != 2 || P() || O()) ? false : true;
    }

    public boolean K() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        if (dVar == null) {
            return false;
        }
        return dVar.y == 1 || P() || O();
    }

    public boolean O() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        return dVar != null && dVar.y == 2 && dVar.z == 1 && this.o;
    }

    public boolean P() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        if (dVar == null) {
            return true;
        }
        return dVar.y == 2 && dVar.z == 0 && TextUtils.isEmpty(dVar.A);
    }

    public void T(Bitmap bitmap) {
        if (this.f7888n == null) {
            return;
        }
        int M = M(bitmap.getWidth(), bitmap.getHeight(), this.f7885k.getWidth(), this.f7885k.getHeight());
        if (!G() && !U()) {
            this.f7888n.setVisibility(8);
            return;
        }
        int max = Math.max(M, L(SwitchQueryFetcher.getSwitchIntValue("mp_ad_bottom_min_margin", 0)));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f7888n.getLayoutParams();
        layoutParams.bottomMargin = max;
        this.f7888n.setLayoutParams(layoutParams);
        this.f7888n.setAlpha(1.0f);
    }

    public boolean U() {
        com.jingdong.app.mall.ad.d dVar = this.f7881g;
        return dVar != null && dVar.y == 3;
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        super.finish();
        com.jingdong.app.mall.ad.c.f();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.home_ad_clickBtnImg && id != R.id.home_ad_clickBtn && id != R.id.home_ad_clickLayout) {
            if (id == R.id.ad_image) {
                if (K()) {
                    Q();
                }
            } else if (id == R.id.ad_skip) {
                com.jingdong.app.mall.home.r.c.a.t("StartPhoto_Skip", "0_null_" + this.f7882h.a + "_1_" + this.f7884j + CartConstant.KEY_YB_INFO_LINK + this.f7883i, this.f7881g.t, "StartPhoto_Main");
                S();
                finish();
            }
        } else if (G()) {
            Q();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i2;
        setUseBasePV(false);
        super.onCreate(bundle);
        com.jingdong.app.mall.ad.d i3 = com.jingdong.app.mall.ad.c.l().i();
        this.f7881g = i3;
        if (i3 != null && i3.f7910k.size() == 1 && ((i2 = this.f7881g.d) == 0 || i2 == 3)) {
            if (com.jingdong.app.mall.home.floor.common.d.f9279g != DpiUtil.getAppWidth(this)) {
                finish();
                return;
            }
            com.jingdong.app.mall.ad.d dVar = this.f7881g;
            if (dVar.f7903c == 0 && dVar.a == 0) {
                finish();
                return;
            }
            this.statusBarTransparentEnable = true;
            setContentView(R.layout.activity_ad_full_screen);
            this.f7885k = (RelativeLayout) findViewById(R.id.content);
            this.f7882h = this.f7881g.f7910k.get(0);
            this.f7887m = com.jingdong.app.mall.ad.c.l().k();
            H();
            J();
            I();
            com.jingdong.app.mall.ad.c.l().j(this.f7882h, new a());
            return;
        }
        finish();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.p = motionEvent.getY();
            return false;
        } else if (action != 1) {
            return false;
        } else {
            if (this.p - motionEvent.getY() <= L(100) || !U()) {
                return false;
            }
            R(true);
            return true;
        }
    }

    public void postUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setType(6000);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        getHttpGroupaAsynPool().add(httpSetting);
    }
}
