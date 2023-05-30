package com.jingdong.common.network;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.BitmapkitUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Properties;

/* loaded from: classes5.dex */
public class NetworkSetting {
    private static final String TAG = "NetworkSetting";
    private static BitmapkitUtils mBitmapkitUtils;

    private static void initCBT() {
        if (Configuration.getBooleanProperty(Configuration.CBTMODE, Boolean.FALSE).booleanValue()) {
            if (OKLog.D) {
                OKLog.d("MyApplication", "initCBT()-->do");
            }
            Properties properties = Configuration.getProperties();
            if (properties == null) {
                return;
            }
            SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
            String string = jdSharedPreferences.getString("host", "");
            if (!TextUtils.isEmpty(string)) {
                if (OKLog.D) {
                    OKLog.d("MyApplication", "initCBT()-->set Host" + string);
                }
                properties.setProperty("host", string);
            }
            String string2 = jdSharedPreferences.getString(Configuration.M_HOST, "");
            if (!TextUtils.isEmpty(string2)) {
                properties.setProperty(Configuration.M_HOST, string2);
            }
            String string3 = jdSharedPreferences.getString(Configuration.PAI_HOST, "");
            if (!TextUtils.isEmpty(string3)) {
                properties.setProperty(Configuration.PAI_HOST, string3);
            }
            String string4 = jdSharedPreferences.getString(Configuration.MSG_HOST, "");
            if (!TextUtils.isEmpty(string4)) {
                properties.setProperty(Configuration.MSG_HOST, string4);
            }
            String string5 = jdSharedPreferences.getString(Configuration.PLUG_HOST, "");
            if (!TextUtils.isEmpty(string5)) {
                properties.setProperty(Configuration.PLUG_HOST, string5);
            }
            if (Boolean.valueOf(jdSharedPreferences.getBoolean("testMode", false)).booleanValue()) {
                properties.setProperty("testMode", DYConstants.DY_TRUE);
            } else {
                properties.setProperty("testMode", DYConstants.DY_FALSE);
            }
        }
    }

    public static synchronized void networkSetting() {
        synchronized (NetworkSetting.class) {
            if (mBitmapkitUtils != null) {
                return;
            }
            mBitmapkitUtils = new BitmapkitUtils();
        }
    }
}
