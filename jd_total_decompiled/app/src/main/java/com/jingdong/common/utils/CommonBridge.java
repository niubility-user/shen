package com.jingdong.common.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.entity.Commercial;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class CommonBridge {
    public static void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap) {
        forwardWebActivity(iMyActivity, str, uRLParamMap, (Commercial) null);
    }

    public static void forwardWebActivityJugeNF(BaseActivity baseActivity, String str, URLParamMap uRLParamMap, boolean z) {
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", str);
        bundle.putBoolean(BaseActivity.ISFROMNF, z);
        DeepLinkMHelper.startWebActivity(baseActivity, bundle, 268435456);
    }

    public static void forwardWebActivityNotNewTask(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap, Commercial commercial) {
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", str);
        if (commercial != null) {
            Integer num = commercial.ynShare;
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, (num == null ? 0 : num.intValue()) == 1);
            bundle.putString(MBaseKeyNames.SHARE_TITLE, commercial.title);
            bundle.putString("shareUrl", commercial.action);
        }
        DeepLinkMHelper.startWebActivity((Context) iMyActivity, bundle);
    }

    public static void goToMWithUrl(BaseActivity baseActivity, String str) {
        goToMWithUrl(baseActivity, str, null);
    }

    public static void goToMWithUrlNotInFrame(BaseActivity baseActivity, String str) {
        if (OKLog.D) {
            OKLog.d("TEST", " -->> url : " + str);
        }
        if (baseActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        DeepLinkMHelper.startWebActivity(baseActivity, bundle);
    }

    public static void goToMWithUrlShareInfo(BaseActivity baseActivity, String str, ShareInfo shareInfo) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        bundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
        bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, true);
        bundle.putParcelable("shareInfo", shareInfo);
        DeepLinkMHelper.startWebActivity(baseActivity, bundle);
    }

    public static void goToMovie(Context context) {
        DeepLinkMHelper.startWebActivity(context, "https://m-movie.jd.com/va/index.html#!/");
    }

    public static void goToWebActivityForResultWithUrl(BaseActivity baseActivity, String str, Commercial commercial, int i2) {
        if (OKLog.D) {
            OKLog.d("TEST", " -->> url : " + str);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (commercial != null) {
            Integer num = commercial.ynShare;
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, (num == null ? 0 : num.intValue()) == 1);
            bundle.putString(MBaseKeyNames.SHARE_TITLE, commercial.title);
            bundle.putString("shareUrl", commercial.action);
        }
        DeepLinkMHelper.startWebActivityForResult(baseActivity, bundle, i2);
    }

    public static void toBrowserInFrame(BaseActivity baseActivity, String str, URLParamMap uRLParamMap) {
        toBrowserInFrameNew(baseActivity, str, uRLParamMap, null);
    }

    public static void toBrowserInFrameNew(BaseActivity baseActivity, String str, URLParamMap uRLParamMap, ShareInfo shareInfo) {
        if (baseActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", str);
        if (shareInfo != null) {
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, true);
            bundle.putParcelable("shareInfo", shareInfo);
        }
        bundle.putBoolean("com.360buy:clearHistoryFlag", true);
        DeepLinkMHelper.startWebActivity(baseActivity, bundle);
    }

    public static void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap, Commercial commercial) {
        Bundle bundle = new Bundle();
        if (commercial != null) {
            Integer num = commercial.ynShare;
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, (num == null ? 0 : num.intValue()) == 1);
            bundle.putParcelable("shareInfo", new ShareInfo(commercial.shareUrl, commercial.shareTitle, commercial.shareContent, commercial.shareAvatar, ""));
        }
        bundle.putString("urlAction", str);
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        DeepLinkMHelper.startWebActivity((Context) iMyActivity, bundle, 268435456);
    }

    public static void goToMWithUrl(BaseActivity baseActivity, String str, Commercial commercial) {
        if (OKLog.D) {
            OKLog.d("TEST", " -->> url : " + str);
        }
        if (TextUtils.isEmpty(str) || baseActivity == null) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (commercial != null) {
            Integer num = commercial.ynShare;
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, (num == null ? 0 : num.intValue()) == 1);
            bundle.putString(MBaseKeyNames.SHARE_TITLE, commercial.title);
            bundle.putString("shareUrl", commercial.action);
        }
        DeepLinkMHelper.startWebActivity(baseActivity, bundle);
    }

    public static void forwardWebActivity(IMyActivity iMyActivity, String str) {
        forwardWebActivity(iMyActivity, str, new URLParamMap(), false);
    }

    public static void forwardWebActivity(IMyActivity iMyActivity, String str, URLParamMap uRLParamMap, boolean z) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putBoolean("com.360buy:clearHistoryFlag", true);
        }
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", str);
        DeepLinkMHelper.startWebActivity((Context) iMyActivity, bundle);
    }

    public static void forwardWebActivity(Context context, String str, String str2, boolean z, String str3) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putBoolean("com.360buy:clearHistoryFlag", true);
        }
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        bundle.putString("url", str2);
        if (TextUtils.equals(str3, MBaseKeyNames.VALUE_ORIENTATION)) {
            bundle.putString(MBaseKeyNames.KEY_ORIENTATION, str3);
            bundle.putBoolean(MBaseKeyNames.KEy_FROM_GAME, true);
        }
        DeepLinkMHelper.startWebActivityFullScreen(context, bundle);
    }
}
