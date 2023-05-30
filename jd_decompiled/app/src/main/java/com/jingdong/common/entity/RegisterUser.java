package com.jingdong.common.entity;

import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.LinkedList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class RegisterUser {
    private static final String TAG = "RegisterUser";
    public String sRegFirstPwd;
    public String sRegMail;
    public String sRegSecPwd;
    public String sRegUserName;
    public String sUuid;

    public RegisterUser(JSONObjectProxy jSONObjectProxy, int i2) {
        try {
            this.sRegUserName = jSONObjectProxy.getString(MobileCertConstants.USERNAME);
            this.sRegFirstPwd = jSONObjectProxy.getString("pwd");
            this.sRegSecPwd = jSONObjectProxy.getString("pwd2");
            this.sRegMail = jSONObjectProxy.getString("mail");
            this.sUuid = jSONObjectProxy.getString("uuid");
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public static LinkedList<RegisterUser> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        try {
            LinkedList<RegisterUser> linkedList = new LinkedList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                linkedList.add(new RegisterUser(jSONArrayPoxy.getJSONObject(i3), i2));
            }
            return linkedList;
        } catch (JSONException e2) {
            if (OKLog.E) {
                OKLog.e("Ware", e2);
                return null;
            }
            return null;
        }
    }
}
