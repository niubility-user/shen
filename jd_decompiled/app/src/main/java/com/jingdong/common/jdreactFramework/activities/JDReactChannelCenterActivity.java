package com.jingdong.common.jdreactFramework.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.privacy.JDPrivacyAgreeEvent;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.utils.JDPrivacyHelper;
import de.greenrobot.event.EventBus;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactChannelCenterActivity extends JDReactNativeCommonActivity {
    private static final String MODULE_NAME = "JDReactChannelCenter";
    private ImageView mPrivacyShadow;

    private void addPrivacyView(ViewGroup viewGroup) {
        if (this.mPrivacyShadow == null) {
            ImageView imageView = new ImageView(this);
            this.mPrivacyShadow = imageView;
            imageView.setBackgroundResource(R.drawable.uh);
            this.mPrivacyShadow.setScaleType(ImageView.ScaleType.FIT_XY);
            this.mPrivacyShadow.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactChannelCenterActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                }
            });
            viewGroup.addView(this.mPrivacyShadow, new ViewGroup.LayoutParams(-1, -1));
            EventBus.getDefault().register(this);
        }
    }

    private void checkPrivacyView() {
        Window window;
        if (JDPrivacyHelper.isAcceptPrivacy(this) || (window = getWindow()) == null) {
            return;
        }
        try {
            View peekDecorView = window.peekDecorView();
            if (peekDecorView instanceof ViewGroup) {
                addPrivacyView((ViewGroup) peekDecorView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        setbundleParam(MODULE_NAME, MODULE_NAME, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean("hideBack", true);
        bundle.putString("displayVersion", "8.0.0");
        return new JDReactModuleEntity(MODULE_NAME, MODULE_NAME, bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(this, MODULE_NAME);
        if (pluginDir == null || pluginDir.pluginDir == null) {
            str = null;
        } else {
            str = pluginDir.pluginDir + File.separator + MODULE_NAME + ".jsbundle";
        }
        if (!TextUtils.isEmpty(str)) {
            getIntent().putExtra("pluginPath", str);
        }
        super.onCreate(bundle);
        checkPrivacyView();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity
    public void onEventMainThread(BaseEvent baseEvent) {
        ImageView imageView = this.mPrivacyShadow;
        if (imageView == null || !(baseEvent instanceof JDPrivacyAgreeEvent)) {
            return;
        }
        imageView.setVisibility(8);
    }
}
