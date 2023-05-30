package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.task.viewkit.EventModelKey;
import com.jingdong.common.entity.JumpEntityProxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class JumpEntity implements Serializable {
    public static final String PARAM_KEY_URL = "url";
    private static final String TAG = "JumpEntity";
    public String des;
    public String eventId;
    public String extInfo;
    public JumpEntityProxy.Params mParams;
    public String params;
    private ShareEntity shareInfo;
    public String srv;
    public String srvJson;

    public static JumpEntity create(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null || jSONObjectProxy.length() <= 0) {
            return null;
        }
        JumpEntity jumpEntity = new JumpEntity();
        jumpEntity.des = jSONObjectProxy.optString("des", "");
        jumpEntity.srv = jSONObjectProxy.optString(EventModelKey.SRV, "");
        jumpEntity.srvJson = jSONObjectProxy.optString("srvJson", "");
        jumpEntity.params = jSONObjectProxy.optString("params", "");
        return jumpEntity;
    }

    public boolean addParam(String str, Object obj) {
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(this.params);
            if (jDJSONObject != null) {
                jDJSONObject.put(str, obj);
                this.params = jDJSONObject.toJSONString();
                return true;
            }
            return false;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    public Object getExtValue(Object obj) {
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(this.extInfo);
            if (jDJSONObject != null) {
                return jDJSONObject.get(obj);
            }
            return null;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public Object getParamValue(Object obj) {
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(this.params);
            if (jDJSONObject != null) {
                return jDJSONObject.get(obj);
            }
            return null;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public ShareEntity getShareInfo() {
        return this.shareInfo;
    }

    public String getSrv() {
        return TextUtils.isEmpty(this.srv) ? "" : this.srv;
    }

    public String getSrvJson() {
        return TextUtils.isEmpty(this.srvJson) ? "" : this.srvJson;
    }

    public void setShareInfo(ShareEntity shareEntity) {
        this.shareInfo = shareEntity;
    }

    public String toJsonString() {
        return "{\"des\":\"" + this.des + "\",\"srv\":\"" + this.srv + "\",\"params\":" + this.params + ",\"extInfo\":\"" + this.extInfo + "\"}";
    }
}
