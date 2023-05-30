package com.jd.cashier.app.jdlibcutter.protocol.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.utils.SoftInputManager;

/* loaded from: classes13.dex */
public class CashierCompactActivity extends FragmentActivity {
    private IActivityConnector mConnector;
    private boolean needResetResourceConfig;
    public boolean statusBarTransparentEnable = false;

    @Override // android.app.Activity
    public void finish() {
        SoftInputManager.hideSoftInput(this);
        super.finish();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onFinish(this);
        }
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        if (this.needResetResourceConfig && resources != null) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            this.needResetResourceConfig = false;
        }
        return resources;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.fontScale > 1.0f) {
            configuration.setToDefaults();
            this.needResetResourceConfig = true;
        }
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        IActivityConnectorCreator activityConnectorCreator = DependInitializer.getActivityConnectorCreator();
        if (activityConnectorCreator != null) {
            this.mConnector = activityConnectorCreator.createActivityConnector();
        }
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.setStatusBarTransparentEnable(this.statusBarTransparentEnable);
            this.mConnector.initStatus();
        }
        super.onCreate(bundle);
        IActivityConnector iActivityConnector2 = this.mConnector;
        if (iActivityConnector2 != null) {
            iActivityConnector2.onCreate(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onDestroy(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onPause(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onRestart(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.initStatus();
        }
        super.onResume();
        IActivityConnector iActivityConnector2 = this.mConnector;
        if (iActivityConnector2 != null) {
            iActivityConnector2.onResume(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onStart(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.onStop(this);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i2) {
        try {
            super.setContentView(i2);
        } catch (Exception unused) {
            if (this.mConnector != null) {
                this.mConnector.onClearBitmapCache();
            }
            super.setContentView(i2);
        }
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.setContentView(this);
        }
    }

    public void setStatusBarTintEnable(boolean z) {
        IActivityConnectorCreator activityConnectorCreator;
        if (this.mConnector == null && (activityConnectorCreator = DependInitializer.getActivityConnectorCreator()) != null) {
            this.mConnector = activityConnectorCreator.createActivityConnector();
        }
        IActivityConnector iActivityConnector = this.mConnector;
        if (iActivityConnector != null) {
            iActivityConnector.setStatusBarTintEnable(z);
        }
    }
}
