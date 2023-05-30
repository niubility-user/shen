package com.jd.lib.productdetail.mainimage.presenter;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class PdMtaJsonParamUtil {
    public static final String MAINIMAGE_MTA_KEY = "mainphoto";

    public String getJsonParamByEventId(String str, PdMainImageParams pdMainImageParams, String str2, PdImageFromType pdImageFromType) {
        JDJSONObject jDJSONObject;
        if (pdImageFromType != PdImageFromType.PRODUCTDETAIL && pdMainImageParams != null) {
            try {
                if (pdMainImageParams.mtaJsonMap != null) {
                    if (TextUtils.isEmpty(str2)) {
                        return (pdMainImageParams.mtaJsonMap.get(str) == null || (jDJSONObject = pdMainImageParams.mtaJsonMap.get(str)) == null) ? "" : jDJSONObject.toJSONString();
                    } else if (pdMainImageParams.mtaJsonMap.get(str) != null) {
                        JDJSONObject jDJSONObject2 = pdMainImageParams.mtaJsonMap.get(str);
                        Object parse = JDJSON.parse(str2);
                        if (jDJSONObject2 != null) {
                            jDJSONObject2.put(MAINIMAGE_MTA_KEY, parse);
                            return jDJSONObject2.toJSONString();
                        }
                    }
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
        return str2;
    }
}
