package com.jingdong.app.mall;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes19.dex */
public class JDGamePortraitActivity extends WebActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        setNeedSetOrientation(false);
        if (getIntent() != null && getIntent().getExtras() != null) {
            String string = getIntent().getExtras().getString("pageId");
            if (!TextUtils.isEmpty(string)) {
                Log.d("@@@@", string);
                setPageId(string);
            }
        }
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.WebActivity, com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        CommonMFragment commonMFragment = this.mFragment;
        if (commonMFragment != null && commonMFragment.getJdWebView() != null) {
            this.mFragment.getJdWebView().setTopBarGone(true);
            this.mFragment.getJdWebView().setNeedShowProgress(false);
        }
        super.onResume();
    }
}
