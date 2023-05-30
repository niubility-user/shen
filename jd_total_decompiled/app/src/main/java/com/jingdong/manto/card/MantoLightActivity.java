package com.jingdong.manto.card;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.manto.R;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.r.d;
import com.jingdong.manto.sdk.api.IMantoLightMode;
import com.jingdong.manto.utils.g;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.manto.widget.f;
import com.tencent.mm.opensdk.constants.ConstantsAPI;

/* loaded from: classes15.dex */
public class MantoLightActivity extends FragmentActivity implements View.OnClickListener {
    private MantoCardManager a;
    private MantoCardView b;

    /* renamed from: c */
    private RelativeLayout f13007c;
    private View d;

    /* renamed from: e */
    private View f13008e;

    /* renamed from: f */
    private boolean f13009f;

    /* renamed from: g */
    private boolean f13010g;

    /* loaded from: classes15.dex */
    class a implements View.OnClickListener {
        a() {
            MantoLightActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MantoLightActivity.this.finish();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements CardLaunchCallback {

        /* loaded from: classes15.dex */
        class a implements Runnable {
            a() {
                b.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    MantoLightActivity.this.f13007c.removeView(MantoLightActivity.this.f13008e);
                } catch (Exception unused) {
                }
            }
        }

        b(long j2) {
            MantoLightActivity.this = r1;
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onBeginLaunch() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onCreateRuntime() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onInitRuntime() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onLaunchError(MantoPreLaunchProcess.LaunchError launchError) {
            MantoLightActivity.this.b(launchError.title, launchError.msg, launchError.word);
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onLaunchSuccess() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onPrepareSuccess(boolean z) {
            MantoLightActivity.this.runOnUiThread(new a());
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onShowSplash() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onStart() {
        }
    }

    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f13011c;

        c(String str, String str2, String str3) {
            MantoLightActivity.this = r1;
            this.a = str;
            this.b = str2;
            this.f13011c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            MantoLightActivity.this.f13007c.setVisibility(8);
            MantoLightActivity.this.a(this.a, this.b, this.f13011c);
            MantoLightActivity.this.d.setVisibility(0);
            MantoLightActivity.this.f13009f = true;
        }
    }

    public void a(String str, String str2, String str3) {
        findViewById(R.id.manto_open_error_back).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.manto_open_error_title);
        ImageView imageView = (ImageView) findViewById(R.id.manto_open_error_back_image);
        View findViewById = findViewById(R.id.manto_open_error_title_bar);
        View view = this.d;
        TextView textView2 = (TextView) findViewById(R.id.manto_open_error_message);
        TextView textView3 = (TextView) findViewById(R.id.manto_open_error_message2);
        textView.setText(str);
        textView2.setText(str2);
        textView3.setText(str3);
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (com.jingdong.manto.k.a.b().a() != 0) {
            int color3 = getResources().getColor(R.color.manto_dark_text_light);
            int color4 = getResources().getColor(R.color.manto_dark_text_weight);
            int color5 = getResources().getColor(R.color.manto_dark_background_light);
            int color6 = getResources().getColor(R.color.manto_dark_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, color5, false);
            textView.setTextColor(color4);
            imageView.setColorFilter(color);
            findViewById.setBackgroundColor(color5);
            view.setBackgroundColor(color6);
            textView2.setTextColor(color4);
            textView3.setTextColor(color3);
            return;
        }
        Resources resources = getResources();
        int i2 = R.color.manto_day_text_weight;
        int color7 = resources.getColor(i2);
        int color8 = getResources().getColor(i2);
        int color9 = getResources().getColor(R.color.manto_day_background_light);
        int color10 = getResources().getColor(R.color.manto_day_background_weight);
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        textView.setTextColor(color7);
        imageView.setColorFilter(color2);
        findViewById.setBackgroundColor(color9);
        view.setBackgroundColor(color10);
        textView2.setTextColor(color7);
        textView3.setTextColor(color8);
    }

    public void b(String str, String str2, String str3) {
        runOnUiThread(new c(str, str2, str3));
    }

    void a() {
        int statusBarHeight = MantoStatusBarUtil.getStatusBarHeight(this);
        View view = new View(com.jingdong.a.g());
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, statusBarHeight));
        view.setId(R.id.manto_fake_status_bar);
        View view2 = this.d;
        if (view2 instanceof LinearLayout) {
            ((LinearLayout) view2).addView(view, 0);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        IMantoLightMode iMantoLightMode = (IMantoLightMode) com.jingdong.a.n(IMantoLightMode.class);
        if (iMantoLightMode != null && !this.f13010g) {
            iMantoLightMode.onFinish(this);
            this.f13010g = true;
        }
        super.finish();
        overridePendingTransition(R.anim.manto_slide_in_left, R.anim.manto_slide_out_right);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public synchronized Resources getResources() {
        Resources resources;
        resources = super.getResources();
        try {
            Configuration configuration = resources.getConfiguration();
            if (configuration != null && configuration.fontScale > 1.0d) {
                configuration.fontScale = 1.0f;
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            }
        } catch (Throwable unused) {
        }
        return resources;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onActivityResult(i2, i3, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.f13009f) {
            super.onBackPressed();
            return;
        }
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.manto_ui_nav_back || view.getId() == R.id.manto_open_error_back) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration != null && configuration.fontScale > 1.0f) {
            configuration.setToDefaults();
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.manto_slide_in_right, R.anim.manto_slide_out_left);
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "\u53c2\u6570\u9519\u8bef", 0).show();
            finish();
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Toast.makeText(this, "\u53c2\u6570\u9519\u8bef2", 0).show();
            finish();
            return;
        }
        LaunchParam launchParam = (LaunchParam) extras.getParcelable(ConstantsAPI.Token.WX_LAUNCH_PARAM_KEY);
        if (launchParam == null) {
            Toast.makeText(this, "\u6ca1\u6709\u542f\u52a8\u53c2\u6570", 0).show();
            finish();
            return;
        }
        MantoStatusBarUtil.setStatusBarColor(this, 0, true);
        setContentView(R.layout.manto_ui_light_container);
        this.f13007c = (RelativeLayout) findViewById(R.id.manto_light_container);
        this.d = findViewById(R.id.manto_light_error);
        this.a = new MantoCardManager(this);
        MantoCardView mantoCardView = new MantoCardView(getApplicationContext());
        this.b = mantoCardView;
        this.a.addCardView(mantoCardView);
        boolean equals = TextUtils.equals(launchParam.mpMode, "2");
        boolean equals2 = TextUtils.equals(launchParam.mpMode, "3");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(14);
        this.f13008e = LayoutInflater.from(this).inflate(R.layout.manto_game_actionbar, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, g.a(this));
        layoutParams2.setMargins(0, MantoStatusBarUtil.getStatusBarHeight(this), 0, 0);
        this.f13008e.findViewById(R.id.manto_actionbar_home).setOnClickListener(new a());
        this.f13007c.addView(this.b, layoutParams);
        if (!equals) {
            this.f13007c.addView(this.f13008e, 1, layoutParams2);
        }
        CardRequestParameter cardRequestParameter = new CardRequestParameter();
        cardRequestParameter.setCardID(launchParam.appId);
        cardRequestParameter.setDebugType(launchParam.debugType);
        cardRequestParameter.setCardHeight(layoutParams.height);
        cardRequestParameter.setCardWidth(layoutParams.width);
        this.b.setCardRequestParameter(cardRequestParameter);
        launchParam.uiConfig = new UIConfig.Builder().setHideTabBar(false).setHideSplash(true).setHideNavigationBar(equals).setHideCapsule(equals2).build();
        long currentTimeMillis = System.currentTimeMillis();
        d.f14143n = System.currentTimeMillis() - d.d;
        this.b.launchMiniProgram(launchParam, new b(currentTimeMillis));
        f.a(this).a((f.d) null);
        a();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onDestroy();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onPause();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        MantoCardManager mantoCardManager = this.a;
        if (mantoCardManager != null) {
            mantoCardManager.onResume();
        }
    }
}
