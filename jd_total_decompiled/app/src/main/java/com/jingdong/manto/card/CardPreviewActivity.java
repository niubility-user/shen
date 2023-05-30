package com.jingdong.manto.card;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.R;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.UIConfig;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.e;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class CardPreviewActivity extends FragmentActivity implements View.OnClickListener {
    private MantoCardManager a;
    private MantoCardView b;

    /* renamed from: c */
    private TextView f13001c;
    private ImageView d;

    /* loaded from: classes15.dex */
    class a implements CardLaunchCallback {
        a(long j2) {
            CardPreviewActivity.this = r1;
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
            CardPreviewActivity.this.f13001c.setVisibility(0);
            int i2 = launchError.errorCode;
            String str = launchError.msg;
            CardPreviewActivity.this.f13001c.setText("\u6253\u5f00\u5931\u8d25\uff0c\u9519\u8bef\u7801\uff1a" + i2 + "\uff0c\u9519\u8bef\u539f\u56e0\uff1a" + str);
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onLaunchSuccess() {
            Toast.makeText(CardPreviewActivity.this, "\u6253\u5f00\u6210\u529f", 0).show();
            CardPreviewActivity.this.a.reportCardViewExpoData(CardPreviewActivity.this.b);
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onPrepareSuccess(boolean z) {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onShowSplash() {
        }

        @Override // com.jingdong.manto.card.CardLaunchCallback
        public void onStart() {
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        this.a.onActivityResult(i2, i3, intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.manto_ui_nav_back) {
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

    /* JADX WARN: Removed duplicated region for block: B:140:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0117  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(@Nullable Bundle bundle) {
        String str;
        int i2;
        float f2;
        int i3;
        int[] e2;
        int i4;
        super.onCreate(bundle);
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
        setContentView(R.layout.manto_ui_card_preview);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.card_preview_container);
        this.f13001c = (TextView) findViewById(R.id.card_load_info);
        ImageView imageView = (ImageView) findViewById(R.id.manto_ui_nav_back);
        this.d = imageView;
        imageView.setOnClickListener(this);
        this.a = new MantoCardManager(this);
        MantoCardView mantoCardView = new MantoCardView(getApplicationContext());
        this.b = mantoCardView;
        mantoCardView.setCardMode(CardMode.LIMIT_API);
        this.a.addCardView(this.b);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(14);
        try {
            JSONObject jSONObject = new JSONObject(launchParam.extrasJson);
            str = jSONObject.optString("venderId");
            try {
                i2 = Integer.parseInt(jSONObject.optString(BaseEvent.SCENE, "1"));
            } catch (Throwable unused) {
                i2 = 1;
                JSONObject jSONObject2 = new JSONObject(launchParam.cardPreviewParam);
                f2 = MantoDensityUtils.dip2pixel(Float.parseFloat(jSONObject2.optString("r", "0")));
                try {
                    i3 = (int) MantoDensityUtils.dip2pixel(Float.parseFloat(jSONObject2.optString("m", "0")));
                } catch (Throwable unused2) {
                    i3 = 0;
                    if (f2 > 0.0f) {
                    }
                    layoutParams.topMargin = (int) MantoDensityUtils.dip2pixel(10);
                    if (i3 > 0) {
                    }
                    if (launchParam.cardWidth > 0) {
                        e2 = e.e(this);
                        if (e2 == null) {
                        }
                        layoutParams.height = i4;
                    }
                    relativeLayout.addView(this.b, layoutParams);
                    CardRequestParameter cardRequestParameter = new CardRequestParameter();
                    cardRequestParameter.setScene(i2);
                    cardRequestParameter.setVendorId(str);
                    cardRequestParameter.setCardID(launchParam.appId);
                    cardRequestParameter.setDebugType(launchParam.debugType);
                    cardRequestParameter.setCardHeight(layoutParams.height);
                    cardRequestParameter.setCardWidth(layoutParams.width);
                    this.b.setCardRequestParameter(cardRequestParameter);
                    launchParam.mpMode = "-1";
                    launchParam.uiConfig = new UIConfig.Builder().setHideTabBar(true).setHideSplash(true).setHideNavigationBar(true).build();
                    this.b.launchMiniProgram(launchParam, new a(System.currentTimeMillis()));
                }
                if (f2 > 0.0f) {
                }
                layoutParams.topMargin = (int) MantoDensityUtils.dip2pixel(10);
                if (i3 > 0) {
                }
                if (launchParam.cardWidth > 0) {
                }
                relativeLayout.addView(this.b, layoutParams);
                CardRequestParameter cardRequestParameter2 = new CardRequestParameter();
                cardRequestParameter2.setScene(i2);
                cardRequestParameter2.setVendorId(str);
                cardRequestParameter2.setCardID(launchParam.appId);
                cardRequestParameter2.setDebugType(launchParam.debugType);
                cardRequestParameter2.setCardHeight(layoutParams.height);
                cardRequestParameter2.setCardWidth(layoutParams.width);
                this.b.setCardRequestParameter(cardRequestParameter2);
                launchParam.mpMode = "-1";
                launchParam.uiConfig = new UIConfig.Builder().setHideTabBar(true).setHideSplash(true).setHideNavigationBar(true).build();
                this.b.launchMiniProgram(launchParam, new a(System.currentTimeMillis()));
            }
        } catch (Throwable unused3) {
            str = "";
        }
        try {
            JSONObject jSONObject22 = new JSONObject(launchParam.cardPreviewParam);
            f2 = MantoDensityUtils.dip2pixel(Float.parseFloat(jSONObject22.optString("r", "0")));
            i3 = (int) MantoDensityUtils.dip2pixel(Float.parseFloat(jSONObject22.optString("m", "0")));
        } catch (Throwable unused4) {
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            this.b.setCornerRadius(f2, f2, f2, f2);
        }
        layoutParams.topMargin = (int) MantoDensityUtils.dip2pixel(10);
        if (i3 > 0) {
            layoutParams.leftMargin = i3;
            layoutParams.rightMargin = i3;
        }
        if (launchParam.cardWidth > 0 && launchParam.cardHeight > 0) {
            e2 = e.e(this);
            if (e2 == null) {
                int i5 = e2[0] - (i3 * 2);
                if (i5 <= 0) {
                    i5 = e2[0];
                }
                layoutParams.width = i5;
                i4 = (int) (((i5 * 1.0f) / launchParam.cardWidth) * launchParam.cardHeight);
            } else {
                layoutParams.width = launchParam.cardWidth;
                i4 = launchParam.cardHeight;
            }
            layoutParams.height = i4;
        }
        relativeLayout.addView(this.b, layoutParams);
        CardRequestParameter cardRequestParameter22 = new CardRequestParameter();
        cardRequestParameter22.setScene(i2);
        cardRequestParameter22.setVendorId(str);
        cardRequestParameter22.setCardID(launchParam.appId);
        cardRequestParameter22.setDebugType(launchParam.debugType);
        cardRequestParameter22.setCardHeight(layoutParams.height);
        cardRequestParameter22.setCardWidth(layoutParams.width);
        this.b.setCardRequestParameter(cardRequestParameter22);
        launchParam.mpMode = "-1";
        launchParam.uiConfig = new UIConfig.Builder().setHideTabBar(true).setHideSplash(true).setHideNavigationBar(true).build();
        this.b.launchMiniProgram(launchParam, new a(System.currentTimeMillis()));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.a.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.a.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        this.a.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.a.onResume();
    }
}
