package com.jingdong.common.messagecenter;

import android.text.TextUtils;
import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class StationMessageUtils {
    private static final String TAG = "StationMessageUtils";
    private static String mPageId;
    private static JDJSONArray whitePage;

    public static void addPageIdLiveDataObserver() {
        try {
            JDMtaUtils.getCurrentPageId().observeForever(new Observer<String>() { // from class: com.jingdong.common.messagecenter.StationMessageUtils.1
                @Override // androidx.lifecycle.Observer
                public void onChanged(String str) {
                    Log.d(StationMessageUtils.TAG, str);
                    StationMessageUtils.setPageIdChanged(str);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String getPageId() {
        Log.d(TAG, mPageId);
        String str = mPageId;
        return str == null ? "" : str;
    }

    private static JDJSONArray getWhitePageArr() {
        JDJSONArray jDJSONArray = whitePage;
        if (jDJSONArray != null) {
            return jDJSONArray;
        }
        try {
            JDJSONArray parseArray = JDJSON.parseArray(StationCacheDataUtils.getStationWhitePage());
            whitePage = parseArray;
            return parseArray;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static void requestToUploadPageView(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("uploadPageView");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.COMMON_NEW_HOST));
        httpSetting.setEffect(1);
        httpSetting.setListener(null);
        httpSetting.setNotifyUser(false);
        httpSetting.setCallTimeout(3000);
        httpSetting.setAppId("MessageCenter");
        httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
        httpSetting.putJsonParam("pageId", str);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void setPageIdChanged(String str) {
        JDJSONArray whitePageArr;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        mPageId = str;
        Log.d(TAG, "page id :" + mPageId);
        if (LoginUserBase.hasLogin() && StationCacheDataUtils.getStationPVSwitch() && (whitePageArr = getWhitePageArr()) != null && whitePageArr.contains(mPageId)) {
            requestToUploadPageView(mPageId);
        }
    }
}
