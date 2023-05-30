package com.jingdong.app.mall.search;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.app.mall.privacy.PrivacyBridge;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.utils.JDPrivacyHelper;

/* loaded from: classes4.dex */
public class SearchBridgeActivity extends MyActivity {

    /* loaded from: classes4.dex */
    class a implements JDPrivacyManager.PrivacyCallback {
        a() {
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onClose(boolean z) {
            SearchBridgeActivity.this.w();
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onDismiss() {
        }
    }

    private boolean v(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            String type = intent.getType();
            return "android.intent.action.SEND".equals(action) && type != null && "text/plain".equals(type);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), "com.jd.lib.search.view.Activity.ProductListActivity"));
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", getIntent().getStringExtra("android.intent.extra.TEXT"));
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!v(getIntent())) {
            finish();
        } else if (!JDPrivacyHelper.isAcceptPrivacy(this) && !JDPrivacyManager.privacyAgreed) {
            PrivacyBridge.launchPrivacyDialog(this, new a());
        } else {
            w();
        }
    }
}
