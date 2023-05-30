package com.jingdong.common.jdreactFramework.listener;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDRNDataReportListener implements DataReportListener {
    @Override // com.jingdong.common.jdreactFramework.listener.DataReportListener
    public void onDownLoadDataReport(Map<String, String> map) {
        try {
            String str = "JDReact_Download";
            String jSONObject = new JSONObject(map).toString();
            if (!TextUtils.isEmpty(map.get(JDReactConstant.IntentConstant.MODULE_NAME))) {
                str = "JDReact_Download" + CartConstant.KEY_YB_INFO_LINK + map.get(JDReactConstant.IntentConstant.MODULE_NAME);
            }
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, null, null, null, null, jSONObject, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.DataReportListener
    public void onLoadDataReport(Map<String, String> map) {
        if (map != null) {
            try {
                if (map.isEmpty()) {
                    return;
                }
                String str = TextUtils.equals(map.get("fromWhere"), "embedded") ? "JDReact_Load_Embedded" : "JDReact_Load";
                String jSONObject = new JSONObject(map).toString();
                if (!TextUtils.isEmpty(map.get(JDReactConstant.IntentConstant.MODULE_NAME))) {
                    str = str + CartConstant.KEY_YB_INFO_LINK + map.get(JDReactConstant.IntentConstant.MODULE_NAME);
                }
                JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, null, null, null, null, jSONObject, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
