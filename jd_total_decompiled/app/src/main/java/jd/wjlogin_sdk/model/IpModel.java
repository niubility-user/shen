package jd.wjlogin_sdk.model;

import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.skin.lib.Utils.ConstancesUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class IpModel {
    public String host;
    public String master;
    public String ttl;
    public String updateTime;
    public String[] v4;
    public String[] v6;

    public static IpModel create(String str, String str2) {
        IpModel ipModel = new IpModel();
        ipModel.host = str;
        ipModel.master = str2;
        return ipModel;
    }

    private boolean isEquals(String[] strArr, String[] strArr2) {
        if (strArr == null && strArr2 == null) {
            return true;
        }
        if (strArr == null || strArr2 != null) {
            return (strArr2 == null || strArr != null) && strArr.length == strArr2.length;
        }
        return false;
    }

    public static IpModel toModel(String str) {
        try {
            return toModel(new JSONObject(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean isNeedReplace(IpModel ipModel) {
        String str;
        return (ipModel == null || ipModel.host == null || (str = ipModel.master) == null || str.equals(this.master)) ? false : true;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("host", this.host);
            jSONObject.put("master", this.master);
            jSONObject.put(RemoteMessageConst.TTL, this.ttl);
            jSONObject.put(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, this.updateTime);
            String[] strArr = this.v4;
            int i2 = 0;
            if (strArr != null && strArr.length != 0) {
                JSONArray jSONArray = new JSONArray();
                int i3 = 0;
                while (true) {
                    String[] strArr2 = this.v4;
                    if (i3 >= strArr2.length) {
                        break;
                    }
                    jSONArray.put(strArr2[i3]);
                    i3++;
                }
                jSONObject.put("v4", jSONArray);
            }
            String[] strArr3 = this.v6;
            if (strArr3 != null && strArr3.length != 0) {
                JSONArray jSONArray2 = new JSONArray();
                while (true) {
                    String[] strArr4 = this.v6;
                    if (i2 >= strArr4.length) {
                        break;
                    }
                    jSONArray2.put(strArr4[i2]);
                    i2++;
                }
                jSONObject.put("v6", jSONArray2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static IpModel toModel(JSONObject jSONObject) {
        IpModel ipModel = new IpModel();
        if (jSONObject == null) {
            return ipModel;
        }
        try {
            ipModel.host = jSONObject.optString("host");
            ipModel.master = jSONObject.optString("master");
            ipModel.ttl = jSONObject.optString(RemoteMessageConst.TTL);
            ipModel.updateTime = jSONObject.optString(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME);
            JSONArray optJSONArray = jSONObject.optJSONArray("v4");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                ipModel.v4 = new String[optJSONArray.length()];
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    ipModel.v4[i2] = optJSONArray.optString(i2);
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("v6");
            if (optJSONArray2 != null && optJSONArray2.length() != 0) {
                ipModel.v6 = new String[optJSONArray2.length()];
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    ipModel.v6[i3] = optJSONArray2.optString(i3);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return ipModel;
    }
}
