package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDOrderModule implements IJDModule {
    public void jumpBarrageHistory(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof BaseActivity)) {
            DeepLinkOrderCenterHelper.startBarrageHistoryForResult((BaseActivity) context, bundle, i2);
        } else {
            DeepLinkOrderCenterHelper.startBarrageHistory(context, bundle);
        }
        JDRouterUtil.callBackComplete(callBackListener);
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
    }

    public void showDetail(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        String optString = jSONObject.optString("orderId");
        String optString2 = jSONObject.optString("function");
        boolean optBoolean = jSONObject.optBoolean("isNew");
        String optString3 = jSONObject.optString(NotificationMessageSummary.TEST_ID);
        bundle.putString("orderId", optString);
        bundle.putBoolean("isNew", optBoolean);
        bundle.putString("function", optString2);
        bundle.putString(NotificationMessageSummary.TEST_ID, optString3);
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof BaseActivity)) {
            DeepLinkOrderCenterHelper.startOrderDetailForResult((BaseActivity) context, bundle, i2);
        } else {
            DeepLinkOrderCenterHelper.startOrderDetail(context, bundle);
        }
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString("functionId");
        String optString3 = jSONObject.optString("from");
        String optString4 = jSONObject.optString("fromPage");
        bundle.putString("title", optString);
        bundle.putString("functionId", optString2);
        bundle.putString("from", optString3);
        bundle.putString("fromPage", optString4);
        DeepLinkOrderCenterHelper.startOrderList(context, bundle);
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showMap(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        String optString = jSONObject.optString("orderId");
        String optString2 = jSONObject.optString("fromPage");
        int optInt = jSONObject.optInt("shipmentType");
        bundle.putString("orderId", optString);
        bundle.putString("fromPage", optString2);
        bundle.putInt("shipmentType", optInt);
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof BaseActivity)) {
            DeepLinkOrderCenterHelper.startLogisticsPathForResult((BaseActivity) context, bundle, i2);
        } else {
            DeepLinkOrderCenterHelper.startLogisticsPath(context, bundle);
        }
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showOftenBuy(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        bundle.putString("fromPage", jSONObject.optString("fromPage"));
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof BaseActivity)) {
            DeepLinkOrderCenterHelper.startOftenBuyListForResult((BaseActivity) context, bundle, i2);
        } else {
            DeepLinkOrderCenterHelper.startOftenBuyList(context, bundle);
        }
        JDRouterUtil.callBackComplete(callBackListener);
    }

    public void showRefund(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        String optString = jSONObject.optString("orderId");
        String optString2 = jSONObject.optString("fromPage");
        bundle.putString("orderId", optString);
        bundle.putString("fromPage", optString2);
        int i2 = bundle.getInt("requestCode", 404);
        if (i2 != 404 && (context instanceof BaseActivity)) {
            DeepLinkOrderCenterHelper.startCancelProgressForResult((BaseActivity) context, bundle, i2);
        } else {
            DeepLinkOrderCenterHelper.startCancelProgress(context, bundle);
        }
        if (callBackListener != null) {
            callBackListener.onComplete();
        }
    }

    public void showTrace(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && !TextUtils.isEmpty(jSONObject.optString("orderId"))) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            int i2 = bundle.getInt("requestCode", 404);
            if (i2 != 404 && (context instanceof BaseActivity)) {
                DeepLinkOrderCenterHelper.startLogisticsForResult((BaseActivity) context, bundle, i2);
            } else {
                DeepLinkOrderCenterHelper.startLogistics(context, bundle);
            }
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }
}
