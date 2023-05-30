package com.jd.lib.babel.task.common;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class TTTTaskPlugin implements IBridgePlugin {
    public static final String KEY = "storeKey";
    public static final String KEY2 = "taskData";

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable IBridgeCallback iBridgeCallback) {
        str.hashCode();
        if (str.equals("getTaskData")) {
            try {
                iBridgeCallback.onSuccess(new JSONObject(TaskDataUtil.getInstance().readData(new JSONObject(str2).optString(KEY))));
            } catch (Exception e2) {
                iBridgeCallback.onError(e2.getMessage());
            }
            return true;
        } else if (str.equals("storeTaskData")) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString(KEY);
                String optString2 = jSONObject.optString(KEY2);
                if (TextUtils.isEmpty(optString)) {
                    iBridgeCallback.onError("storeKey \u5339\u914d\u4e0d\u4e0a\uff0c\u83b7\u53d6\u5931\u8d25");
                } else if (TextUtils.isEmpty(optString2)) {
                    iBridgeCallback.onError("taskData \u6570\u636e\u4e3a\u7a7a");
                } else {
                    if (TaskDataUtil.getInstance().writeData(optString, CommonUtil.jsonToMapObject(optString2))) {
                        iBridgeCallback.onSuccess("1");
                    } else {
                        iBridgeCallback.onError("writeData \u65b9\u6cd5\u5b58\u50a8\u6570\u636e\u5f02\u5e38OR\u65e0pin");
                    }
                }
            } catch (Exception e3) {
                iBridgeCallback.onError(e3.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }
}
