package com.jingdong.app.mall.personel;

import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class MyCommonActivity extends MyActivity {

    /* renamed from: g */
    private boolean f11538g = false;

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (Log.D) {
            Log.d("MyCommonActivity", " -->>resultCode :  " + i3);
            Log.d("MyCommonActivity", " -->>requestCode :  " + i2);
        }
        if (1103 == i2) {
            this.f11538g = -1 != i3;
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (Log.D) {
            Log.d("MyCommonActivity", " onResume -->> isLoginFail :  " + this.f11538g);
            Log.d("MyCommonActivity", " onResume-->>hasLogin : " + LoginUserBase.hasLogin());
        }
        if (LoginUserBase.hasLogin() || !this.f11538g) {
            return;
        }
        finish();
        CommonUtilEx.getInstance().backToHomePage(this);
    }
}
