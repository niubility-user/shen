package com.jingdong.manto.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.m;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoOpenErrorActivity extends Activity implements View.OnClickListener, a.b {
    private TextView a;
    private ImageView b;

    /* renamed from: c  reason: collision with root package name */
    private View f14255c;
    private View d;

    /* renamed from: e  reason: collision with root package name */
    private TextView f14256e;

    /* renamed from: f  reason: collision with root package name */
    private TextView f14257f;

    /* renamed from: g  reason: collision with root package name */
    private TextView f14258g;

    /* renamed from: h  reason: collision with root package name */
    private String f14259h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f14260i;

    /* renamed from: j  reason: collision with root package name */
    private int f14261j;

    /* renamed from: k  reason: collision with root package name */
    private LaunchParam f14262k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends IMantoHttpListener {
        a() {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            JSONObject optJSONObject;
            String string = MantoOpenErrorActivity.this.getResources().getString(R.string.manto_apply_dev_code_error);
            if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("error")) != null) {
                string = optJSONObject.optString("errorMessage", string);
            }
            MantoOpenErrorActivity.this.a(string);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject != null) {
                String string = MantoOpenErrorActivity.this.getResources().getString(R.string.manto_apply_dev_code_success);
                if (!TextUtils.equals(jSONObject.optString("code"), "0")) {
                    string = jSONObject.optString("errorMessage", string);
                }
                MantoOpenErrorActivity.this.a(string);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;

        /* loaded from: classes16.dex */
        class a implements DialogInterface.OnClickListener {
            a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    dialogInterface.dismiss();
                    MantoOpenErrorActivity.this.f14260i = false;
                    MantoOpenErrorActivity.this.finish();
                } catch (Exception unused) {
                }
            }
        }

        b(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (MantoOpenErrorActivity.this.isFinishing() || MantoOpenErrorActivity.this.f14260i) {
                    return;
                }
                a aVar = new a();
                MantoOpenErrorActivity mantoOpenErrorActivity = MantoOpenErrorActivity.this;
                com.jingdong.manto.widget.dialog.a.a(mantoOpenErrorActivity, mantoOpenErrorActivity.getString(R.string.manto_apply_dev_code), this.a, "\u786e\u5b9a", null, aVar, null, null, null, null).show();
                MantoOpenErrorActivity.this.f14260i = true;
            } catch (Exception e2) {
                MantoOpenErrorActivity.this.f14260i = false;
                MantoLog.e("MantoOpenErrorActivity", e2.getMessage());
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class c {
        private static final Map<String, Class<? extends MantoOpenErrorActivity>> a;

        static {
            HashMap hashMap = new HashMap();
            hashMap.put(":manto0", MantoOpenErrorActivity0.class);
            hashMap.put(":manto1", MantoOpenErrorActivity1.class);
            hashMap.put(":manto2", MantoOpenErrorActivity2.class);
            hashMap.put(":manto3", MantoOpenErrorActivity3.class);
            hashMap.put(":manto4", MantoOpenErrorActivity4.class);
            hashMap.put(":mantoProcess", MantoOpenErrorActivityMT.class);
            a = Collections.unmodifiableMap(hashMap);
        }

        public static void a(MantoPreLaunchProcess.LaunchError launchError) {
            Class<? extends MantoOpenErrorActivity> cls = MantoOpenErrorActivity.class;
            String processName = MantoProcessUtil.getProcessName();
            if (!MantoStringUtils.isEmpty(processName) && (cls = a.get(processName.replaceFirst(MantoProcessUtil.a, ""))) == null) {
                cls = MantoOpenErrorActivity.class;
            }
            Intent intent = new Intent(com.jingdong.manto.c.a(), cls);
            intent.putExtra("errorType", launchError.errorCode);
            intent.putExtra("title", launchError.title);
            intent.putExtra("msg", launchError.msg);
            intent.putExtra("word", launchError.word);
            intent.putExtra("errorCode", launchError.errorCode);
            intent.putExtra("appId", launchError.appId);
            if (launchError.launchParam != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(ConstantsAPI.Token.WX_LAUNCH_PARAM_KEY, launchError.launchParam);
                intent.putExtra("bundle", bundle);
            }
            intent.addFlags(268435456);
            com.jingdong.manto.c.a().startActivity(intent);
        }
    }

    private void a() {
        MantoJDHttpHandler.commit(new m(this.f14259h), new a());
    }

    private void a(int i2) {
        TextView textView;
        Resources resources;
        int i3;
        int color = getResources().getColor(R.color.manto_white);
        int color2 = getResources().getColor(R.color.manto_black);
        if (i2 == 0) {
            Resources resources2 = getResources();
            int i4 = R.color.manto_day_text_weight;
            int color3 = resources2.getColor(i4);
            int color4 = getResources().getColor(i4);
            int color5 = getResources().getColor(R.color.manto_day_background_light);
            int color6 = getResources().getColor(R.color.manto_day_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, -1, true);
            this.a.setTextColor(color3);
            this.b.setColorFilter(color2);
            this.f14255c.setBackgroundColor(color5);
            this.d.setBackgroundColor(color6);
            this.f14256e.setTextColor(color3);
            this.f14257f.setTextColor(color4);
            this.f14258g.setBackgroundResource(R.drawable.manto_ui_apply_dev_code);
            textView = this.f14258g;
            resources = getResources();
            i3 = R.color.manto_open_main_color;
        } else {
            int color7 = getResources().getColor(R.color.manto_dark_text_light);
            int color8 = getResources().getColor(R.color.manto_dark_text_weight);
            int color9 = getResources().getColor(R.color.manto_dark_background_light);
            int color10 = getResources().getColor(R.color.manto_dark_background_weight);
            MantoStatusBarUtil.setStatusBarColor(this, color9, false);
            this.a.setTextColor(color8);
            this.b.setColorFilter(color);
            this.f14255c.setBackgroundColor(color9);
            this.d.setBackgroundColor(color10);
            this.f14256e.setTextColor(color8);
            this.f14257f.setTextColor(color7);
            this.f14258g.setBackgroundResource(R.drawable.manto_ui_apply_dev_code_dark);
            textView = this.f14258g;
            resources = getResources();
            i3 = R.color.manto_dark_open_main_color;
        }
        textView.setTextColor(resources.getColor(i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        runOnUiThread(new b(str));
    }

    @Override // android.app.Activity
    public void finish() {
        if (Build.VERSION.SDK_INT >= 21) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LaunchParam launchParam;
        if (view.getId() != R.id.manto_open_error_back) {
            if (view.getId() != R.id.manto_apply_dev_code) {
                return;
            }
            if (this.f14261j == 10512 && !TextUtils.isEmpty(this.f14259h)) {
                a();
                return;
            } else if (this.f14261j != 40001 || TextUtils.isEmpty(this.f14259h) || (launchParam = this.f14262k) == null) {
                return;
            } else {
                com.jingdong.a.o(launchParam);
            }
        }
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration != null && configuration.fontScale > 1.0f) {
            configuration.setToDefaults();
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle bundleExtra;
        super.onCreate(bundle);
        setContentView(R.layout.manto_open_error_activity);
        findViewById(R.id.manto_open_error_back).setOnClickListener(this);
        this.a = (TextView) findViewById(R.id.manto_open_error_title);
        this.b = (ImageView) findViewById(R.id.manto_open_error_back_image);
        this.f14255c = findViewById(R.id.manto_open_error_title_bar);
        this.d = findViewById(R.id.manto_open_error);
        this.f14256e = (TextView) findViewById(R.id.manto_open_error_message);
        this.f14257f = (TextView) findViewById(R.id.manto_open_error_message2);
        this.f14258g = (TextView) findViewById(R.id.manto_apply_dev_code);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("title");
            String stringExtra2 = intent.getStringExtra("msg");
            String stringExtra3 = intent.getStringExtra("word");
            this.f14261j = intent.getIntExtra("errorCode", -1);
            this.f14259h = intent.getStringExtra("appId");
            this.a.setText(stringExtra);
            this.f14256e.setText(stringExtra2);
            this.f14257f.setText(stringExtra3);
            if (this.f14261j != 10512 || TextUtils.isEmpty(this.f14259h)) {
                if (this.f14261j == 40001 && !TextUtils.isEmpty(this.f14259h) && (bundleExtra = intent.getBundleExtra("bundle")) != null) {
                    LaunchParam launchParam = (LaunchParam) bundleExtra.getParcelable(ConstantsAPI.Token.WX_LAUNCH_PARAM_KEY);
                    this.f14262k = launchParam;
                    if (launchParam != null) {
                        this.f14258g.setText(getResources().getString(R.string.manto_apply_retry));
                    }
                }
            }
            this.f14258g.setVisibility(0);
            this.f14258g.setOnClickListener(this);
        } else {
            Toast.makeText(this, getText(R.string.manto_open_error_msg), 0).show();
            finish();
        }
        com.jingdong.manto.k.a.b().a(this);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.jingdong.manto.k.a.b().b(this);
    }
}
