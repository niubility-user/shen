package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLDecoder;

/* loaded from: classes5.dex */
public class DeepLinkBabelHelper {
    public static final String HOST_BABEL_ACTIVITY = "babelactivity";
    public static final String HOST_PHONE_BABEL_ACTIVITY = "phonebabelactivity";

    public static String getUrl(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("url");
        if (string == null) {
            try {
                URLParamMap map = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                return map != null ? URLDecoder.decode(map.get((Object) RemoteMessageConst.TO), "utf-8") : string;
            } catch (Exception unused) {
                return bundle.getString("webUrl");
            }
        }
        return string;
    }

    public static String getUseNewLoad(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("useNewLoad");
        if (string == null) {
            try {
                URLParamMap map = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                return map != null ? map.get((Object) "useNewLoad") : string;
            } catch (Exception unused) {
                return string;
            }
        }
        return string;
    }

    public static void startBabelActivity(Context context, Bundle bundle, int i2) {
        if (!DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(90))) {
            if (OKLog.D) {
                OKLog.d("DeepLinkBabelHelper", "=> MASK_AURA_SWITCH_BABEL close");
                return;
            }
            return;
        }
        String useNewLoad = getUseNewLoad(bundle);
        if ("1".equals(useNewLoad)) {
            bundle.putBoolean("newLinkRoad", true);
        } else if ("0".equals(useNewLoad)) {
            bundle.putBoolean("newLinkRoad", false);
        } else {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("ttt_new_link_string", "");
            if (!TextUtils.isEmpty(switchStringValue)) {
                if (switchStringValue.contains(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
                    bundle.putBoolean("newLinkRoad", true);
                } else {
                    String url = getUrl(bundle);
                    if (!TextUtils.isEmpty(url)) {
                        String babelActivityId = WebViewHelper.getBabelActivityId(url);
                        if (!TextUtils.isEmpty(babelActivityId) && switchStringValue.contains(babelActivityId)) {
                            bundle.putBoolean("newLinkRoad", true);
                        }
                    }
                }
            }
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_BABEL_ACTIVITY).toString(), bundle, i2);
    }

    public static void startBabelVideoActivity(Context context, Bundle bundle) {
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_BABEL_VIDEO_ACTIVITY).toString(), bundle);
    }
}
