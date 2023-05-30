package com.jingdong.app.mall.darkmode;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.JsonObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.unification.title.theme.JdThemeTitle;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.common.widget.shadow.widget.JDShadowSwitch;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes3.dex */
public class DarkModePreferenceActivity extends MyActivity implements View.OnClickListener {
    private static boolean w;

    /* renamed from: g  reason: collision with root package name */
    private JDShadowSwitch f8351g;

    /* renamed from: h  reason: collision with root package name */
    private JDShadowSwitch f8352h;

    /* renamed from: i  reason: collision with root package name */
    private View f8353i;

    /* renamed from: j  reason: collision with root package name */
    private View f8354j;

    /* renamed from: k  reason: collision with root package name */
    private JdThemeTitle f8355k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f8356l;

    /* renamed from: m  reason: collision with root package name */
    private View f8357m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f8358n;
    private TextView o;
    private View p;
    private View q;
    private View r;
    private View s;
    private volatile boolean t = false;
    private Configuration u = null;
    private DeepDarkChangeManager.OnUIModeChangeListener v = new a();

    /* loaded from: classes3.dex */
    class a implements DeepDarkChangeManager.OnUIModeChangeListener {
        a() {
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            DarkModePreferenceActivity.this.y();
        }
    }

    private void A() {
        if (w) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("DarkModePreferenceActivity", "writeSwitchedState()");
        }
        DeepDarkChangeManager.getInstance().setDarkModeSwitchTurned();
        w = true;
    }

    private void init() {
        this.f8357m = findViewById(R.id.a6p);
        this.f8351g = (JDShadowSwitch) findViewById(R.id.dark_manual_checkbox);
        this.f8352h = (JDShadowSwitch) findViewById(R.id.dark_auto_checkbox);
        this.f8353i = findViewById(R.id.manual_rl);
        this.f8354j = findViewById(R.id.auto_rl);
        JdThemeTitle jdThemeTitle = (JdThemeTitle) findViewById(R.id.q0);
        this.f8355k = jdThemeTitle;
        this.f8356l = jdThemeTitle.getLeft1ImageView();
        this.p = findViewById(R.id.hu);
        this.q = findViewById(R.id.a9p);
        this.f8358n = (TextView) findViewById(R.id.dark_manual_text);
        this.o = (TextView) findViewById(R.id.dark_auto_text);
        this.r = findViewById(R.id.fl_manual_mask);
        this.s = findViewById(R.id.fl_auto_mask);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.f8356l.setOnClickListener(this);
        w = DeepDarkChangeManager.getInstance().getDarkModeSwitchHasTurned();
        if (OKLog.D) {
            OKLog.d("DarkModePreferenceActivity", "onCreate()_hasSwitched=" + w);
        }
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this.v);
        TextView textView = this.f8358n;
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        textView.setTextSize(companion.getInstance().getScaleSize(this, 15.0f));
        this.o.setTextSize(companion.getInstance().getScaleSize(this, 15.0f));
        this.f8355k.getTitleTextView().setTextSize(companion.getInstance().getScaleSize(this, 17.0f));
        View view = this.f8354j;
        int i2 = Build.VERSION.SDK_INT;
        view.setVisibility(i2 <= 28 ? 4 : 0);
        this.q.setVisibility(i2 > 28 ? 0 : 4);
    }

    private Configuration v() {
        int i2 = 0;
        Context context = this;
        while (i2 < 3) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            i2++;
            context = context;
        }
        return context.getResources().getConfiguration();
    }

    private boolean w() {
        Configuration configuration = this.u;
        return configuration != null ? (configuration.uiMode & 48) == 32 : (v().uiMode & 48) == 32;
    }

    private void x() {
        if (OKLog.D) {
            boolean z = DeepDarkChangeManager.getInstance().getUIMode() == 1;
            OKLog.d("DarkModePreferenceActivity", "logSwitcherSpValue_manualCBChecked:" + z + "mAutoCBChecked:" + DeepDarkChangeManager.getInstance().getIsUIModeFollowSystem());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.t) {
            x();
            boolean z = DeepDarkChangeManager.getInstance().getUIMode() == 1;
            boolean isUIModeFollowSystem = DeepDarkChangeManager.getInstance().getIsUIModeFollowSystem();
            this.f8351g.setChecked(z);
            this.f8352h.setChecked(isUIModeFollowSystem);
            if (z) {
                this.f8357m.setBackgroundColor(Color.parseColor("#1A1717"));
                this.f8354j.setBackgroundColor(Color.parseColor("#272424"));
                this.f8353i.setBackgroundColor(Color.parseColor("#272424"));
                this.f8358n.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
                this.o.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
                this.p.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_302E2E));
                this.q.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_302E2E));
            } else {
                this.f8357m.setBackgroundColor(Color.parseColor("#EDEDED"));
                this.f8354j.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.f8353i.setBackgroundColor(Color.parseColor("#FFFFFF"));
                this.f8358n.setTextColor(Color.parseColor("#2E2E2E"));
                this.o.setTextColor(Color.parseColor("#2E2E2E"));
                this.p.setBackgroundColor(Color.parseColor("#E9E9E9"));
                this.q.setBackgroundColor(Color.parseColor("#E9E9E9"));
            }
            this.f8355k.loadTheme();
        }
    }

    private void z(int i2) {
        if (i2 == 1) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("status", Integer.valueOf(DeepDarkChangeManager.getInstance().getUIMode()));
            JDMtaUtils.sendClickDataWithExt(this, "UnionControl_Black_Enable", "", "", "UnionControl_Black", "", "", null, jsonObject.toString(), null);
        } else if (i2 != 2) {
        } else {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("status", Integer.valueOf(DeepDarkChangeManager.getInstance().getIsUIModeFollowSystem() ? 1 : 0));
            JDMtaUtils.sendClickDataWithExt(this, "UnionControl_Black_SystemEnable", "", "", "UnionControl_Black", "", "", null, jsonObject2.toString(), null);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == this.f8356l.getId()) {
            finish();
            return;
        }
        int id = view.getId();
        if (id == R.id.fl_auto_mask) {
            this.f8352h.setChecked(!r6.isChecked());
            DeepDarkChangeManager.getInstance().setIsUIModeFollowSystem(this.f8352h.isChecked());
            DeepDarkChangeManager deepDarkChangeManager = DeepDarkChangeManager.getInstance();
            Configuration configuration = this.u;
            if (configuration == null) {
                configuration = v();
            }
            deepDarkChangeManager.handleUIModeConfiguration(configuration);
            if (!this.f8352h.isChecked()) {
                ToastUtils.shortToast(this, (int) R.string.dark_mode_auto_close_tip);
            }
            A();
            z(2);
        } else if (id != R.id.fl_manual_mask) {
        } else {
            this.f8351g.setChecked(!r6.isChecked());
            boolean isChecked = this.f8351g.isChecked();
            DeepDarkChangeManager.getInstance().saveDeepDarkSwitch(isChecked ? 1 : 0);
            if ((this.f8351g.isChecked() != w()) && this.f8352h.isChecked()) {
                DeepDarkChangeManager.getInstance().setIsUIModeFollowSystem(false);
                this.f8352h.setChecked(false);
                ToastUtils.shortToast(this, (int) R.string.dark_mode_auto_close_tip);
            }
            DeepDarkChangeManager.getInstance().notifyDeepDarkChanged(isChecked ? 1 : 0);
            A();
            z(1);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.u = new Configuration(configuration);
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.statusBarTransparentEnable = true;
        setContentView(R.layout.layout_dark_mode_preference);
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.v != null) {
            DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this.v);
            this.v = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        JDMtaUtils.sendPagePv(this, this, "", "UnionControl_Black", "");
        this.t = true;
        y();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.t = false;
    }
}
