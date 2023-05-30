package com.jd.skin.lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jd.skin.lib.Utils.JDSkinSharedPreferencesUtils;
import com.jd.skin.lib.db.AppStateType;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes18.dex */
public class LoginAndExitReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (OKLog.D) {
            OKLog.d("JDSkinSDK", "JDSkinSDK--BroadcastReceiver-->" + JDSkinSDK.getInstance().getIsNeedLogined());
        }
        if (JDSkinSDK.getInstance().getIsNeedLogined()) {
            if (TextUtils.equals(intent.getAction(), "com.jingdong.action.user.login.in")) {
                JDSkinSDK.getInstance().setLoginState(true);
                JDSkinSDK.getInstance().getResData(AppStateType.APP_START);
            } else if (TextUtils.equals(intent.getAction(), "com.jingdong.action.user.login.out")) {
                JDSkinSDK.getInstance().setLoginState(false);
                JDSkinSharedPreferencesUtils.putString(context, ConstancesUtils.SP_CURRENT_SKIN_ID, "0");
                JDSkinSDK.getInstance().getResData(AppStateType.APP_START);
            } else if (TextUtils.equals(intent.getAction(), "com.jingdong.action.user.switch")) {
                JDSkinSharedPreferencesUtils.clear(context);
                JDSkinSDK.getInstance().reset();
                JDSkinSDK.getInstance().getResData(AppStateType.APP_START);
            }
        }
    }
}
