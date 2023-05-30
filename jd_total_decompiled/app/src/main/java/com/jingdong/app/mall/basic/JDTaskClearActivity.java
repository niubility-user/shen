package com.jingdong.app.mall.basic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.app.mall.utils.MyActivity;

/* loaded from: classes19.dex */
public class JDTaskClearActivity extends MyActivity {
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (TextUtils.equals(intent != null ? intent.getStringExtra("target_page") : null, "personal")) {
            CommonUtilEx.getInstance().gotoPersonalPage(this);
        } else {
            CommonUtilEx.getInstance().gotoHomePage(this);
        }
        finish();
    }
}
