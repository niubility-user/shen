package com.jingdong.common.utils;

import android.app.Activity;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;

/* loaded from: classes6.dex */
public class JDSettingUtils {

    /* loaded from: classes6.dex */
    public interface ClearCacheCallBack {
        void onClearCacheClicked();
    }

    private JDSettingUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearImgCache() {
        new Thread(new Runnable() { // from class: com.jingdong.common.utils.JDSettingUtils.3
            @Override // java.lang.Runnable
            public void run() {
                FileService.clearAllCacheImages();
            }
        }).start();
    }

    public static boolean isWifiVideoAutoPlay() {
        return CommonBase.getBooleanFromPreference(Constants.AUTO_PLAY_VIDEO_WIFI_SETTED_KEY, Boolean.TRUE).booleanValue();
    }

    public static void onClearCache(Activity activity, final ClearCacheCallBack clearCacheCallBack) {
        if (activity == null) {
            return;
        }
        final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(activity, activity.getString(R.string.bundle_setting_local_cache_clear_prompt), activity.getString(R.string.bundle_setting_lib_setting_cancel), activity.getString(R.string.bundle_setting_lib_setting_ok));
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDSettingUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
            }
        });
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDSettingUtils.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new Thread() { // from class: com.jingdong.common.utils.JDSettingUtils.2.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        JDFrescoUtils.clearDiskCache();
                        CleanCacheUtils.cleanCache();
                    }
                }.start();
                JDSettingUtils.clearImgCache();
                ClearCacheCallBack clearCacheCallBack2 = ClearCacheCallBack.this;
                if (clearCacheCallBack2 != null) {
                    clearCacheCallBack2.onClearCacheClicked();
                }
                createJdDialogWithStyle2.cancel();
            }
        });
        createJdDialogWithStyle2.setCancelable(true);
        createJdDialogWithStyle2.setCanceledOnTouchOutside(true);
        createJdDialogWithStyle2.show();
    }

    public static void onForceCleanCache(Activity activity, String str, String str2, String str3, final ClearCacheCallBack clearCacheCallBack) {
        if (activity == null) {
            return;
        }
        final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(activity, str, str2, str3);
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDSettingUtils.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDDialog.this.cancel();
            }
        });
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.JDSettingUtils.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new Thread() { // from class: com.jingdong.common.utils.JDSettingUtils.5.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        JDFrescoUtils.clearDiskCache();
                    }
                }.start();
                JDSettingUtils.clearImgCache();
                AuraBundleConfig.getInstance().cleanAuraCache();
                JDDialog.this.cancel();
                ClearCacheCallBack clearCacheCallBack2 = clearCacheCallBack;
                if (clearCacheCallBack2 != null) {
                    clearCacheCallBack2.onClearCacheClicked();
                }
            }
        });
        createJdDialogWithStyle2.setCancelable(true);
        createJdDialogWithStyle2.setCanceledOnTouchOutside(true);
        createJdDialogWithStyle2.show();
    }
}
