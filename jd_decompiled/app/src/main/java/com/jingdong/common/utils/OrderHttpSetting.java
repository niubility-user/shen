package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.common.entity.settlement.BaseCurrentOrder;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class OrderHttpSetting extends HttpSetting {
    private static String SETTLEMENT_VERSION_CODE = "settlementVersionCode";
    private static final String TAG = "OrderHttpSetting";
    private static HashMap<String, Object> settlementPublicParams = new HashMap<>();
    private static long settlementVersionCode = -1;

    public OrderHttpSetting() {
        setHost(getOrderHost());
        setModeId(JDGetWayQueueTools.QueueMode.MODE_ORDER);
        long j2 = settlementVersionCode;
        if (j2 >= 0) {
            putJsonParam(SETTLEMENT_VERSION_CODE, Long.valueOf(j2));
        }
        addSettlementPublicParams();
    }

    private void addSettlementPublicParams() {
        Set<Map.Entry<String, Object>> entrySet;
        HashMap<String, Object> hashMap = settlementPublicParams;
        if (hashMap == null || hashMap.size() <= 0 || (entrySet = settlementPublicParams.entrySet()) == null || (r0 = entrySet.iterator()) == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : entrySet) {
            if (entry != null) {
                putJsonParam(entry.getKey(), entry.getValue());
            }
        }
    }

    public static String getOrderHost() {
        return HostConfig.getInstance().getHost(HostConstants.ORDER_HTTPSETTING_HOST);
    }

    public static void putSettlementPublicParams(String str, Object obj) {
        HashMap<String, Object> hashMap = settlementPublicParams;
        if (hashMap != null) {
            hashMap.put(str, obj);
        }
    }

    public static void removeSettlementPublicParams(String str) {
        HashMap<String, Object> hashMap = settlementPublicParams;
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    public static void setSettlementVersionCode(long j2) {
        settlementVersionCode = j2;
    }

    public void setAddTransferJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            putJsonParam(BaseCurrentOrder.ADDTRANSFER_JSON, new JSONObject(str));
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void setAllTransferJson(BaseCurrentOrder baseCurrentOrder) {
        if (baseCurrentOrder == null) {
            return;
        }
        setTransferJson(baseCurrentOrder.transferJson);
        setAddTransferJson(baseCurrentOrder.addTransferJson);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpSetting
    @Deprecated
    public void setJsonParams(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.isNull(SETTLEMENT_VERSION_CODE)) {
                    long j2 = settlementVersionCode;
                    if (j2 >= 0) {
                        jSONObject.put(SETTLEMENT_VERSION_CODE, j2);
                    }
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        super.setJsonParams(jSONObject);
        addSettlementPublicParams();
    }

    public void setTransferJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            putJsonParam(BaseCurrentOrder.TRANSFER_JSON, new JSONObject(str));
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }
}
