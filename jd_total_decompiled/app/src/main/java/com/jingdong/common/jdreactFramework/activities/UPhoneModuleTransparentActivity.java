package com.jingdong.common.jdreactFramework.activities;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes5.dex */
public class UPhoneModuleTransparentActivity extends BaseActivity {
    public static UPhoneModuleTransparentActivity instance;
    static long lastTime;
    boolean isFirst = true;
    boolean myResumed;

    public static void initTime() {
        lastTime = System.currentTimeMillis();
    }

    public static boolean isTimeOut() {
        return System.currentTimeMillis() - lastTime > 1000;
    }

    public String getTopActivity() {
        List runningTasksWithAOP = BaseInfo.getRunningTasksWithAOP((ActivityManager) getSystemService("activity"), 1);
        if (runningTasksWithAOP != null) {
            ComponentName componentName = ((ActivityManager.RunningTaskInfo) runningTasksWithAOP.get(0)).topActivity;
            return componentName.getPackageName() + OrderISVUtil.MONEY_DECIMAL + componentName.getClassName();
        }
        return null;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instance = this;
        this.isFirst = true;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.isFirst = true;
        instance = null;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.myResumed = false;
        super.onPause();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getTopActivity();
        this.myResumed = true;
        Intent intent = getIntent();
        boolean booleanExtra = intent != null ? intent.getBooleanExtra("isFormUPhoneModule", false) : false;
        if (!this.isFirst || !booleanExtra || isTimeOut()) {
            showDReactUPhoneModule();
        }
        this.isFirst = false;
        getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.jdreactFramework.activities.UPhoneModuleTransparentActivity.1
            {
                UPhoneModuleTransparentActivity.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                UPhoneModuleTransparentActivity uPhoneModuleTransparentActivity = UPhoneModuleTransparentActivity.this;
                if (uPhoneModuleTransparentActivity.myResumed) {
                    uPhoneModuleTransparentActivity.finish();
                }
            }
        }, 2000L);
    }

    public void showDReactUPhoneModule() {
        ReactActivityUtils.showDReactUPhoneModuleActivity();
        finish();
    }
}
