package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.web.CommonMHybridHelper;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebJumpUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkMHelper {
    private static final String TAG = "DeepLinkMHelper";

    public static void startWebActivity(Context context, Bundle bundle) {
        boolean sendClickEvent = XRender.getInstance().sendClickEvent(WebUtils.addCustomParams(null, bundle));
        if (WebJumpUtils.is2Native(context, bundle)) {
            return;
        }
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_WEBACTIVITY);
        if (WebHybridUtils.hybridEnable && !sendClickEvent) {
            bundle = WebPreLoadHelper.startPreLoadForBundle(WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter), CommonMHybridHelper.sPreloadParamGetter);
        }
        OpenLinkTimeManager.getInstance().enterM(bundle != null ? bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0) : 0);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
    }

    public static void startWebActivityForResult(Activity activity, Bundle bundle, int i2) {
        boolean sendClickEvent = XRender.getInstance().sendClickEvent(WebUtils.addCustomParams(null, bundle));
        if (WebJumpUtils.is2Native(activity, bundle)) {
            return;
        }
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_WEBACTIVITY);
        if (WebHybridUtils.hybridEnable && !sendClickEvent) {
            bundle = WebPreLoadHelper.startPreLoadForBundle(WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter), CommonMHybridHelper.sPreloadParamGetter);
        }
        OpenLinkTimeManager.getInstance().enterM(bundle != null ? bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0) : 0);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2);
    }

    public static void startWebActivityFullScreen(Context context, Bundle bundle) {
        boolean sendClickEvent = XRender.getInstance().sendClickEvent(WebUtils.addCustomParams(null, bundle));
        if (WebJumpUtils.is2Native(context, bundle)) {
            return;
        }
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("WebActivityLandscape");
        if (WebHybridUtils.hybridEnable && !sendClickEvent) {
            bundle = WebPreLoadHelper.startPreLoadForBundle(WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter), CommonMHybridHelper.sPreloadParamGetter);
        }
        OpenLinkTimeManager.getInstance().enterM(bundle != null ? bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0) : 0);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle);
    }

    public static void startWebActivity(Context context, Bundle bundle, int i2) {
        boolean sendClickEvent = XRender.getInstance().sendClickEvent(WebUtils.addCustomParams(null, bundle));
        if (WebJumpUtils.is2Native(context, bundle)) {
            return;
        }
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_WEBACTIVITY);
        if (WebHybridUtils.hybridEnable && !sendClickEvent) {
            bundle = WebPreLoadHelper.startPreLoadForBundle(WebOfflineLoaderManager.createOfflineLoaderForBundle(bundle, CommonMHybridHelper.sOfflineParamGetter), CommonMHybridHelper.sPreloadParamGetter);
        }
        OpenLinkTimeManager.getInstance().enterM(bundle != null ? bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0) : 0);
        DeepLinkDispatch.startActivityDirect(context, host.toString(), bundle, i2);
    }

    public static void startWebActivity(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String addCustomParams = WebUtils.addCustomParams(str, null);
        XRender.getInstance().sendClickEvent(addCustomParams);
        if (WebJumpUtils.is2Native(context, addCustomParams)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, addCustomParams);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        startWebActivity(context, bundle);
    }
}
