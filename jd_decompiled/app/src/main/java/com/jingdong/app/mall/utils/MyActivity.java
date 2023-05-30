package com.jingdong.app.mall.utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;

@SuppressLint({"NewApi"})
/* loaded from: classes4.dex */
public class MyActivity extends BaseActivity {
    protected long lastTimes;

    public boolean isRepeatClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastTimes < 1000) {
            return true;
        }
        this.lastTimes = currentTimeMillis;
        return false;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (Log.D) {
            Log.d("MyActivity", "onCreate() TaskId = " + getTaskId() + " -->> " + getClass().getName());
            StringBuilder sb = new StringBuilder();
            sb.append("onCreate() -->> getIntent : ");
            sb.append(getIntent());
            Log.d("MyActivity", sb.toString());
        }
        super.onCreate(bundle);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
