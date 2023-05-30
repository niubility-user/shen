package com.jingdong.sdk.platform.business.puppet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.lib.puppetlayout.ylayout.callback.ActionCallback;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class JDPuppetHandler implements ActionCallback {
    private final Context context;

    public JDPuppetHandler(Context context) {
        this.context = context;
    }

    public static void gotoMWithUrl(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str2);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("locUrl", str);
        }
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void openAppForInner(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.callback.ActionCallback
    public void execute(Action action) {
        HashMap<String, String> hashMap;
        String str;
        if (this.context == null || action == null || (hashMap = action.params) == null || hashMap.size() == 0) {
            return;
        }
        String str2 = action.params.get("actionType");
        str2.hashCode();
        char c2 = '\uffff';
        switch (str2.hashCode()) {
            case -1861361369:
                if (str2.equals(Action.ActionType_EXPO)) {
                    c2 = 0;
                    break;
                }
                break;
            case -108529531:
                if (str2.equals(Action.ActionType_CallBack)) {
                    c2 = 1;
                    break;
                }
                break;
            case R2.attr.yg2_aspectRatio /* 2285 */:
                if (str2.equals("H5")) {
                    c2 = 2;
                    break;
                }
                break;
            case 77292912:
                if (str2.equals(Action.ActionType_Point)) {
                    c2 = 3;
                    break;
                }
                break;
            case 401430359:
                if (str2.equals(Action.ActionType_OpenApp)) {
                    c2 = 4;
                    break;
                }
                break;
        }
        str = "";
        switch (c2) {
            case 0:
                try {
                    String str3 = action.params.get("event_id") == null ? "" : action.params.get("event_id");
                    String str4 = action.params.get("event_param") == null ? "" : action.params.get("event_param");
                    String str5 = action.params.get("page_name") == null ? "" : action.params.get("page_name");
                    String str6 = action.params.get("page_param") != null ? action.params.get("page_param") : "";
                    if (TextUtils.isEmpty(str3)) {
                        return;
                    }
                    JDMtaUtils.sendExposureData(this.context, str5, null, str6, str3, str4, null, null, null);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 1:
                if ("point".equals(action.params.get("type"))) {
                    try {
                        String str7 = action.params.get("event_id") == null ? "" : action.params.get("event_id");
                        String str8 = action.params.get("event_param") == null ? "" : action.params.get("event_param");
                        String str9 = action.params.get("page_name") == null ? "" : action.params.get("page_name");
                        if (action.params.get("page_param") != null) {
                            str = action.params.get("page_param");
                        }
                        if (TextUtils.isEmpty(str7)) {
                            return;
                        }
                        JDMtaUtils.onClick(this.context, str7, str9, str8, str);
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return;
                    }
                } else if ("exposure".equals(action.params.get("type"))) {
                    try {
                        String str10 = action.params.get("event_id") == null ? "" : action.params.get("event_id");
                        String str11 = action.params.get("event_param") == null ? "" : action.params.get("event_param");
                        String str12 = action.params.get("page_name") == null ? "" : action.params.get("page_name");
                        String str13 = action.params.get("page_param") != null ? action.params.get("page_param") : "";
                        if (TextUtils.isEmpty(str10)) {
                            return;
                        }
                        JDMtaUtils.sendExposureData(this.context, str12, null, str13, str10, str11, null, null, null);
                        return;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            case 2:
                gotoMWithUrl(this.context, null, action.params.get("url"));
                return;
            case 3:
                try {
                    String str14 = action.params.get("event_id") == null ? "" : action.params.get("event_id");
                    String str15 = action.params.get("event_param") == null ? "" : action.params.get("event_param");
                    String str16 = action.params.get("page_name") == null ? "" : action.params.get("page_name");
                    if (action.params.get("page_param") != null) {
                        str = action.params.get("page_param");
                    }
                    if (TextUtils.isEmpty(str14)) {
                        return;
                    }
                    JDMtaUtils.onClick(this.context, str14, str16, str15, str);
                    return;
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return;
                }
            case 4:
                openAppForInner(this.context, action.params.get("url"));
                return;
            default:
                return;
        }
    }
}
