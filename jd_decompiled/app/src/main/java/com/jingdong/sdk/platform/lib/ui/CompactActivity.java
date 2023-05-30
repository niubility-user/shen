package com.jingdong.sdk.platform.lib.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jingdong.sdk.lib.compact.CompactBaseActivity;
import com.jingdong.sdk.platform.lib.openapi.OpenApiHelper;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsActivityCompact;

/* loaded from: classes10.dex */
public class CompactActivity extends CompactBaseActivity {
    private AbsActivityCompact activityCompact;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AbsActivityCompact activityCompact = OpenApiHelper.getActivityCompact(this);
        this.activityCompact = activityCompact;
        if (activityCompact != null) {
            activityCompact.onCreate(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AbsActivityCompact absActivityCompact = this.activityCompact;
        if (absActivityCompact != null) {
            absActivityCompact.onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        AbsActivityCompact absActivityCompact = this.activityCompact;
        if (absActivityCompact != null) {
            absActivityCompact.onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        AbsActivityCompact absActivityCompact = this.activityCompact;
        if (absActivityCompact != null) {
            absActivityCompact.onResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        AbsActivityCompact absActivityCompact = this.activityCompact;
        if (absActivityCompact != null) {
            absActivityCompact.onStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        AbsActivityCompact absActivityCompact = this.activityCompact;
        if (absActivityCompact != null) {
            absActivityCompact.onStop();
        }
    }
}
