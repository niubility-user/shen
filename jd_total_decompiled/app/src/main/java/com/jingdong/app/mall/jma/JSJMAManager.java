package com.jingdong.app.mall.jma;

import android.text.TextUtils;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.common.web.IRouterParams;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JSJMAManager {
    public static JSONObject getBlog(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    new JSONObject(iRouterParams.getRouterParam());
                }
                return new JSONObject(JMAUtils.getBlog());
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static void getSoftFingerprint(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                new JSONObject(iRouterParams.getRouterParam());
            }
            iRouterParams.onCallBack(JMA.getSoftFingerprint(iRouterParams.getContext()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
