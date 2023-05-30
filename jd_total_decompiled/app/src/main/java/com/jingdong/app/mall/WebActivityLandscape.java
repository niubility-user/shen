package com.jingdong.app.mall;

import android.os.Bundle;

/* loaded from: classes19.dex */
public class WebActivityLandscape extends WebActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        super.onCreate(bundle);
    }
}
