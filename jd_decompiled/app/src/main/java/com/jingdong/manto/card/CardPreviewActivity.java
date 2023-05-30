package com.jingdong.manto.card;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.manto.R;
import com.jingdong.manto.launch.MantoPreLaunchProcess;

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

    /* JADX WARN: Removed duplicated region for block: B:84:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0117  */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate(@androidx.annotation.Nullable android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.card.CardPreviewActivity.onCreate(android.os.Bundle):void");
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
