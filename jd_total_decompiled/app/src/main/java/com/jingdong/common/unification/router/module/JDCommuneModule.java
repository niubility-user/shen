package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDCommuneModule implements IJDModule {
    private static final String ITEM_ID = "itemId";
    private static final String SKU = "sku";

    private void startCommuneCommentList(Context context, int i2, Bundle bundle) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneCommentListForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneCommentList(context, bundle);
        }
    }

    private void startCommuneLabelDetail(Context context, int i2, Bundle bundle) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneLabelDetailForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneLabelDetail(context, bundle);
        }
    }

    private void startCommunePage(Context context, int i2, Bundle bundle, String str) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommunePageForResult((Activity) context, bundle, str, i2);
        } else {
            DeepLinkCommuneHelper.startCommunePage(context, bundle, str);
        }
    }

    private void startCommuneReplyList(Context context, int i2, Bundle bundle) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneReplyListForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneReplyList(context, bundle);
        }
    }

    private void startCommuneUserCenter(Context context, int i2, Bundle bundle) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneUserCenterForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneUserCenter(context, bundle);
        }
    }

    private void startProductCommuneDetail(Context context, int i2, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("sku");
            if (!TextUtils.isEmpty(string)) {
                bundle.putString(DeepLinkCommuneHelper.PRODUCT_ID, string);
                bundle.putString(DeepLinkCommuneHelper.QUESTION_ID, bundle.getString(ITEM_ID));
            }
        }
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneDetailForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneDetail(context, bundle);
        }
    }

    private void startProductCommuneList(Context context, int i2, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("sku");
            if (!TextUtils.isEmpty(string)) {
                bundle.putString(DeepLinkCommuneHelper.PRODUCT_ID, string);
            }
        }
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommuneListForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommuneList(context, bundle);
        }
    }

    private void startProductCommunePublish(Context context, int i2, Bundle bundle) {
        if (i2 != 404 && (context instanceof Activity)) {
            DeepLinkCommuneHelper.startCommunePublishForResult((Activity) context, bundle, i2);
        } else {
            DeepLinkCommuneHelper.startCommunePublish(context, bundle);
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        String optString = jSONObject.optString("subDes");
        if (context != null && !TextUtils.isEmpty(optString)) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
            int i2 = bundle.getInt("requestCode", 404);
            if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_LIST, optString)) {
                startProductCommuneList(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_DETAIL, optString)) {
                startProductCommuneDetail(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_PUBLISH, optString)) {
                startProductCommunePublish(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_USER_CENTER, optString)) {
                startCommuneUserCenter(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_COMMENT_LIST, optString)) {
                startCommuneCommentList(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_REPLY_LIST, optString)) {
                startCommuneReplyList(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_LABEL_DETAIL, optString)) {
                startCommuneLabelDetail(context, i2, bundle);
            } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_MEDICINE_LIST, optString)) {
                startCommunePage(context, i2, bundle, optString);
            } else {
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(RemoteMessageConst.TO, "https://vs.m.jd.com/commentAppdau/index.html");
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                bundle.putSerializable("urlParamMap", serializableContainer);
                bundle.putString("urlAction", RemoteMessageConst.TO);
                DeepLinkCommonHelper.startWebActivity(context, bundle, true);
            }
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }
}
