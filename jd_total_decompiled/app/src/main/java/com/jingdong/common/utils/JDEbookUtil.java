package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.tencent.smtt.sdk.ProxyConfig;

/* loaded from: classes6.dex */
public class JDEbookUtil {
    public static final String EBOOK_ACTION = "ebook";
    public static final String FROM_EBOOK = "from_ebook";
    private static final String JD_EBOOK_COMPONENT_NAME = "com.jingdong.app.reader";
    private static final String KEY_CLIENT_VERSION = "clientVersion";
    private static final String KEY_SCREEN = "screenSize";

    public static String getJDEbookVersionName(Context context) {
        PackageInfo packageInfo = CommonBase.getPackageInfo(context, JD_EBOOK_COMPONENT_NAME);
        return packageInfo != null ? packageInfo.versionName : "";
    }

    public static void gotoEbookM(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put("clientVersion", getJDEbookVersionName(activity.getBaseContext()));
        uRLParamMap.put(KEY_SCREEN, DPIUtil.getWidth() + ProxyConfig.MATCH_ALL_SCHEMES + DPIUtil.getHeight());
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        Bundle bundle = new Bundle();
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", str);
        bundle.putString("from", FROM_EBOOK);
        DeepLinkMHelper.startWebActivity(activity, bundle);
    }
}
