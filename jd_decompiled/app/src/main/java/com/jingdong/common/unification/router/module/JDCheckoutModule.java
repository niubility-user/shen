package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkAddressHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.corelib.utils.Log;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDCheckoutModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        Bundle bundle2 = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject.opt(next);
            next.hashCode();
            char c2 = '\uffff';
            switch (next.hashCode()) {
                case -1111431691:
                    if (next.equals("sourceType")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -955644008:
                    if (next.equals("skuSource")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -391564376:
                    if (next.equals("orderType")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1124152265:
                    if (next.equals("wareNum")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 1685705191:
                    if (next.equals("otcSkuNum")) {
                        c2 = 4;
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
                    try {
                        bundle2.putInt(next, jSONObject.optInt(next));
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        break;
                    }
                default:
                    try {
                        bundle2.putString(next, jSONObject.optString(next));
                        break;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        break;
                    }
            }
        }
        bundle2.putAll(bundle);
        DeepLinkFillOrderHelper.startFillOrder(context, bundle2);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showCheckoutAddressListPage(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("jsonParam=");
            sb.append(jDJSONObject == null ? "jsonParam is null" : jDJSONObject.toString());
            Log.d("showCheckoutAddressListPage", sb.toString());
        }
        DeepLinkAddressHelper.startAddressListFormApplets(context, jDJSONObject, routerEntry);
    }
}
