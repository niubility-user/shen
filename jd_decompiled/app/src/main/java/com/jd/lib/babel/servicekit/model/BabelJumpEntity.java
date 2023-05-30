package com.jd.lib.babel.servicekit.model;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.babel.task.viewkit.EventModelKey;
import com.jd.lib.babel.task.viewkit.VKEventUtil;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class BabelJumpEntity implements Serializable {
    public static final String PARAM_KEY_URL = "url";
    private static final String TAG = "JumpEntity";
    public int arg;
    public String des;
    public String eventId;
    public String jsonSrv;
    public String needLogin;
    public String params;
    public String srv;
    public String srvData;

    public BabelJumpEntity() {
    }

    public Object getParamValue(String str) {
        try {
            return new JSONObject(this.params).opt(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getSrv() {
        return TextUtils.isEmpty(this.srv) ? "" : this.srv;
    }

    public String toJsonString() {
        return "{\"des\":\"" + this.des + "\",\"srv\":\"" + this.srv + "\",\"params\":" + this.params + "}";
    }

    public BabelJumpEntity(@NonNull JSONObject jSONObject) {
        this.des = jSONObject.optString("des", "");
        this.params = jSONObject.optString("params");
        this.srv = jSONObject.optString(EventModelKey.SRV);
        this.jsonSrv = jSONObject.optString("jsonSrv");
        this.eventId = jSONObject.optString("eventId");
        this.srvData = jSONObject.optString("srvData");
        this.needLogin = jSONObject.optString(VKEventUtil.JUMP_NEEDLOGIN, "");
    }
}
