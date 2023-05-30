package com.jingdong.app.mall.jdpay;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.UserUtil;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes4.dex */
public class JPMidActivity extends MyActivity {
    private void u(@NonNull Intent intent, boolean z, boolean z2) {
        if (z && z2) {
            intent.putExtra("dispatchType", 0);
            intent.putExtra("sessionKey", UserUtil.getWJLoginHelper().getA2());
        } else {
            intent.putExtra("dispatchType", 1);
        }
        try {
            startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        intent.setClassName(f.f19954c, "com.jdpaysdk.pay.JPIPCActivity");
        u(intent, UserUtil.getWJLoginHelper().hasLogin(), JDPrivacyHelper.isAcceptPrivacy(this) || JDPrivacyManager.privacyAgreed);
    }
}
