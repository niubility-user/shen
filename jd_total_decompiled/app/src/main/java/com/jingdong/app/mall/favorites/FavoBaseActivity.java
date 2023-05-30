package com.jingdong.app.mall.favorites;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes3.dex */
public class FavoBaseActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    protected boolean f8408g = false;

    /* renamed from: h  reason: collision with root package name */
    private Observer<Integer> f8409h;

    /* loaded from: classes3.dex */
    class a implements Observer<Integer> {
        a() {
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onChanged(@Nullable Integer num) {
            FavoBaseActivity.this.statusBarRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (Log.D) {
            Log.d("FavoBaseActivity", " -->>resultCode :  " + i3);
            Log.d("FavoBaseActivity", " -->>requestCode :  " + i2);
        }
        if (1103 == i2) {
            if (-1 == i3) {
                this.f8408g = false;
            } else {
                this.f8408g = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.statusBarDarkModeEnable = true;
        this.f8409h = new a();
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this, this.f8409h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (Log.D) {
            Log.d("FavoBaseActivity", " onResume -->> isLoginFail :  " + this.f8408g);
            Log.d("FavoBaseActivity", " onResume-->>hasLogin : " + LoginUserBase.hasLogin());
        }
        if (LoginUserBase.hasLogin() || !this.f8408g) {
            return;
        }
        finish();
    }
}
