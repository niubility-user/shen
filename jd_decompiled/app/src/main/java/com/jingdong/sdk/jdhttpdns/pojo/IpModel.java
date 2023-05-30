package com.jingdong.sdk.jdhttpdns.pojo;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import com.jingdong.sdk.jdhttpdns.utils.TimeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class IpModel {
    public String host;
    public String master;
    public String ttl;
    public String updateTime;
    public String[] v4;
    public String[] v6;

    public IpModel(String str, String str2, String str3, String str4) {
        this.ttl = "0";
        this.host = str;
        if (!TextUtils.isEmpty(str2)) {
            if (InetAddressUtils.isIPv6Address(str2)) {
                this.master = String.format("[%s]", str2);
            } else {
                this.master = str2;
            }
        } else {
            this.master = str2;
        }
        this.updateTime = str4;
        this.ttl = str3;
    }

    public static String formatStr(String str) {
        return !TextUtils.isEmpty(str) ? str.trim() : str;
    }

    public static List<IpModel> parse(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    String optString = jSONObject2.optString("master");
                    if (!TextUtils.isEmpty(optString)) {
                        optString = formatStr(optString);
                    }
                    String optString2 = jSONObject2.optString(RemoteMessageConst.TTL);
                    if (!TextUtils.isEmpty(optString2)) {
                        IpModel ipModel = new IpModel(next, optString, formatStr(optString2), TimeUtils.getCurrentTime());
                        JSONObject optJSONObject = jSONObject2.optJSONObject(JDBusinessAddress.TYPE_BACKUP);
                        if (optJSONObject != null) {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("v4");
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("v6");
                            if (optJSONArray != null) {
                                int length = optJSONArray.length();
                                String[] strArr = new String[length];
                                for (int i2 = 0; i2 < length; i2++) {
                                    strArr[i2] = optJSONArray.optString(i2);
                                }
                                ipModel.v4 = strArr;
                            }
                            if (optJSONArray2 != null) {
                                int length2 = optJSONArray2.length();
                                String[] strArr2 = new String[length2];
                                for (int i3 = 0; i3 < length2; i3++) {
                                    strArr2[i3] = optJSONArray2.optString(i3);
                                }
                                ipModel.v6 = strArr2;
                            }
                        }
                        JDHttpDnsToolkit.getInstance().onNotifyResolve(ipModel);
                        DNSLog.d("IPModel", ipModel.toJsonOject().toString());
                        arrayList.add(ipModel);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static List<IpModel> preloadParse(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    String optString = jSONObject2.optString("master");
                    if (!TextUtils.isEmpty(optString)) {
                        optString = formatStr(optString);
                    }
                    String optString2 = jSONObject2.optString(RemoteMessageConst.TTL);
                    if (!TextUtils.isEmpty(optString2)) {
                        IpModel ipModel = new IpModel(next, optString, formatStr(optString2), TimeUtils.getCurrentTime());
                        JSONObject optJSONObject = jSONObject2.optJSONObject(JDBusinessAddress.TYPE_BACKUP);
                        if (optJSONObject != null) {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("v4");
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("v6");
                            if (optJSONArray != null) {
                                int length = optJSONArray.length();
                                String[] strArr = new String[length];
                                for (int i2 = 0; i2 < length; i2++) {
                                    strArr[i2] = optJSONArray.optString(i2);
                                }
                                ipModel.v4 = strArr;
                            }
                            if (optJSONArray2 != null) {
                                int length2 = optJSONArray2.length();
                                String[] strArr2 = new String[length2];
                                for (int i3 = 0; i3 < length2; i3++) {
                                    strArr2[i3] = optJSONArray2.optString(i3);
                                }
                                ipModel.v6 = strArr2;
                            }
                        }
                        JDHttpDnsToolkit.getInstance().onNotifyResolve(ipModel);
                        DNSLog.d("IPModel", ipModel.toJsonOject().toString());
                        arrayList.add(ipModel);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public String getIp() {
        return this.master;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void merge(IpModel ipModel) {
        this.master = ipModel.master;
        this.ttl = ipModel.ttl;
        if (!TextUtils.isEmpty(ipModel.updateTime)) {
            this.updateTime = ipModel.updateTime;
        }
        String[] strArr = ipModel.v6;
        if (strArr != null && strArr.length > 0) {
            this.v6 = strArr;
        }
        String[] strArr2 = ipModel.v4;
        if (strArr2 == null || strArr2.length <= 0) {
            return;
        }
        this.v4 = strArr2;
    }

    public JSONObject toJsonOject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("host", this.host);
            jSONObject.put("ip", this.master);
            jSONObject.put(RemoteMessageConst.TTL, this.ttl);
            jSONObject.put(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, this.updateTime);
            String[] strArr = this.v4;
            if (strArr != null) {
                jSONObject.put("v4", Arrays.asList(strArr));
            }
            String[] strArr2 = this.v6;
            if (strArr2 != null) {
                jSONObject.put("v6", Arrays.asList(strArr2));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public IpModel m24clone() {
        IpModel ipModel = new IpModel(this.host, this.master, this.ttl, this.updateTime);
        ipModel.v4 = this.v4;
        ipModel.v6 = this.v6;
        return ipModel;
    }
}
