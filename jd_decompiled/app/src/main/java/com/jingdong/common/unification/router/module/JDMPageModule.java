package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDMPageModule implements IJDModule {
    private Bundle jsonToBundle(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        Bundle bundle = new Bundle();
        while (keys.hasNext()) {
            String next = keys.next();
            next.hashCode();
            char c2 = '\uffff';
            switch (next.hashCode()) {
                case -2051537185:
                    if (next.equals(MBaseKeyNames.IS_NEED_SHARE)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -2038326778:
                    if (next.equals(MBaseKeyNames.KEy_FROM_GAME)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1996247330:
                    if (next.equals(MBaseKeyNames.IS_FROM_AUTHSDK)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1580413276:
                    if (next.equals(MKeyNames.NEED_CHECK_NATIVE)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1480504526:
                    if (next.equals("showSubPage")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1233542499:
                    if (next.equals("isNeedCookieRet")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -1102774907:
                    if (next.equals(WebEntity.IS_USE_CACHE)) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -260761280:
                    if (next.equals("isRegist")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 117100963:
                    if (next.equals(WebEntity.IS_IGNORE_SHARE)) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 217973120:
                    if (next.equals(MBaseKeyNames.IS_SHOW_MORE_BTN)) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 434769163:
                    if (next.equals(MBaseKeyNames.KEY_FROM_THIRD_PAY)) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 1022906417:
                    if (next.equals(MBaseKeyNames.IS_USE_RIGHT_BUTTON)) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 1043056903:
                    if (next.equals("isTopBarGone")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 1088312467:
                    if (next.equals("needlogin")) {
                        c2 = '\r';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                    bundle.putBoolean(next, jSONObject.optBoolean(next));
                    break;
                default:
                    bundle.putString(next, jSONObject.optString(next));
                    break;
            }
        }
        return bundle;
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String optString = jSONObject.optString("url");
        SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
        URLParamMap map = serializableContainer != null ? serializableContainer.getMap() : null;
        if (TextUtils.isEmpty(optString) && (map == null || !map.containsKey(RemoteMessageConst.TO))) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Bundle jsonToBundle = jsonToBundle(jSONObject);
        jsonToBundle.putAll(bundle);
        if (Log.D) {
            Log.d("JDMPageModule", "bundle:" + jsonToBundle.toString());
        }
        int i2 = bundle.getInt("requestCode", -1);
        if (i2 != -1 && (context instanceof Activity)) {
            DeepLinkMHelper.startWebActivityForResult((Activity) context, jsonToBundle, i2);
        } else {
            DeepLinkMHelper.startWebActivity(context, jsonToBundle);
        }
        JDRouterUtil.callBackComplete(callBackListener);
    }
}
