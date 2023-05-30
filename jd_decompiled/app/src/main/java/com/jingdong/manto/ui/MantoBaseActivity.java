package com.jingdong.manto.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public abstract class MantoBaseActivity extends FragmentActivity implements MantoActivityResult {
    private static final String TAG = MantoBaseActivity.class.getSimpleName();
    public b mController = new c(this);
    public boolean isDestroyed = false;
    public MantoActivityResult.ResultCallback resultCallback = null;
    private MantoActivityResult.ResultCallback preResultCallback = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public static View getLayoutView() {
        return null;
    }

    public static void showVKB(Activity activity) {
        View currentFocus;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager == null || (currentFocus = activity.getCurrentFocus()) == null || currentFocus.getWindowToken() == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 2);
    }

    public final void addDialog(Dialog dialog) {
        this.mController.a(dialog);
    }

    public void dealContentView(View view) {
        setContentView(view);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.manto_slide_in_left, R.anim.manto_slide_out_right);
    }

    public Activity getActivity() {
        return this;
    }

    public int getLayoutId() {
        return -1;
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

    @Override // com.jingdong.manto.MantoActivityResult
    public MantoActivityResult.ResultCallback getResultCallback() {
        return this.resultCallback;
    }

    public final void hideKeyboard(View view) {
        this.mController.b(view);
    }

    public boolean hideKeyboard() {
        return this.mController.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        MantoActivityResult.ResultCallback resultCallback = this.resultCallback;
        if (resultCallback != null) {
            resultCallback.onActivityResult(i2, i3, intent);
            this.resultCallback = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration != null && configuration.fontScale > 1.0f) {
            configuration.setToDefaults();
        }
        super.onConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mController.a(getBaseContext(), this);
        com.jingdong.manto.b.b(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreateBeforeSetContentView() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mController.e();
        this.isDestroyed = true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (!this.mController.a(i2, keyEvent)) {
            try {
                return super.onKeyUp(i2, keyEvent);
            } catch (Throwable th) {
                MantoLog.e(TAG, "onKeyUp", th);
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        IPermission iPermission = (IPermission) com.jingdong.a.n(IPermission.class);
        if (iPermission != null) {
            iPermission.onRequestPermissionsResult(this, i2, strArr, iArr);
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        if (Build.VERSION.SDK_INT < 11) {
            super.onSaveInstanceState(bundle);
        }
    }

    @Override // com.jingdong.manto.MantoActivityResult
    public void restoreResultCallback() {
        this.resultCallback = this.preResultCallback;
        this.preResultCallback = null;
    }

    @Override // com.jingdong.manto.MantoActivityResult
    public void setResultCallback(MantoActivityResult.ResultCallback resultCallback) {
        this.preResultCallback = this.resultCallback;
        this.resultCallback = resultCallback;
    }

    public void showVKB() {
        this.mController.f();
    }
}
