package com.jingdong.common.unification.customtheme.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkConfig;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper;

/* loaded from: classes6.dex */
public class LoginAndExitReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), "com.jingdong.action.user.login.in")) {
            UnCustomThemeHelper.getInstance().setLogin(true);
            UnCustomThemeHelper.getInstance().passiveRequest();
            UnWatermarkBusinessHelper.getInstance().request(null);
        } else if (TextUtils.equals(intent.getAction(), "com.jingdong.action.user.login.out")) {
            UnCustomThemeHelper.getInstance().setLogin(false);
            WatermarkConfig.getConfig().setCanAdd(false);
            UnWatermarkBusinessHelper.getInstance().optResumeList();
            UnSharedPreferencesUtils.putBoolean(a.g().d(), UnWatermarkBusinessHelper.SP_WATER_MARK, false);
            String themeId = UnCustomThemeHelper.getInstance().getThemeId();
            if (TextUtils.isEmpty(themeId) || TextUtils.equals(themeId, "0")) {
                return;
            }
            UnCustomThemeHelper.getInstance().reset();
        }
    }
}
