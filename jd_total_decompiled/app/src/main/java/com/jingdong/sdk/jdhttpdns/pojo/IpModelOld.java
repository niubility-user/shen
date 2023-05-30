package com.jingdong.sdk.jdhttpdns.pojo;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import com.jingdong.sdk.jdhttpdns.utils.TimeUtils;
import com.jingdong.sdk.jdhttpdns.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes7.dex */
public final class IpModelOld {
    public static final String PORT_HTTP = "80";
    public static final String PORT_HTTPS = "443";
    private String host;
    private String ip;
    private boolean isSupportHttp2;
    private String port;
    private String ttl;
    private String updateTime;

    public IpModelOld() {
        this.ttl = "0";
        this.isSupportHttp2 = false;
    }

    public static List<IpModelOld> parse(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    String optString = jSONObject.optString("ip");
                    if (!TextUtils.isEmpty(optString)) {
                        optString = optString.trim();
                    }
                    String str2 = optString;
                    if (Util.validIPAddress(str2)) {
                        String optString2 = jSONObject.optString("host");
                        if (!TextUtils.isEmpty(optString2)) {
                            optString2 = optString2.trim();
                        }
                        String optString3 = jSONObject.optString(RemoteMessageConst.TTL);
                        if (!TextUtils.isEmpty(optString3)) {
                            optString3 = optString3.trim();
                        }
                        String optString4 = jSONObject.optString(IMediaPlayer.OnNativeInvokeListener.ARG_PORT);
                        if (!TextUtils.isEmpty(optString4)) {
                            optString4 = optString4.trim();
                        }
                        arrayList.add(new IpModelOld(optString2, str2, optString3, optString4, TimeUtils.getCurrentTime()));
                    }
                }
            }
        } catch (Exception e2) {
            if (DNSLog.D) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public String getHost() {
        return this.host;
    }

    public String getIp() {
        return this.ip;
    }

    public String getPort() {
        return this.port;
    }

    public String getTtl() {
        return this.ttl;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public boolean isSupportHttp2() {
        return TextUtils.equals(this.port, PORT_HTTPS) && Build.VERSION.SDK_INT >= 21;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public void setPort(String str) {
        this.port = str;
    }

    public void setSupportHttp2(boolean z) {
        this.isSupportHttp2 = z;
    }

    public void setTtl(String str) {
        this.ttl = str;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }

    public JSONObject toJsonOject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("host", this.host);
            jSONObject.put("ip", this.ip);
            jSONObject.put(RemoteMessageConst.TTL, this.ttl);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, this.port);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public IpModelOld(String str, String str2, String str3, String str4) {
        this(str, str2, str3, PORT_HTTP, str4);
    }

    public IpModelOld(String str, String str2, String str3, String str4, String str5) {
        this.ttl = "0";
        this.isSupportHttp2 = false;
        this.host = str;
        if (InetAddressUtils.isIPv6Address(str2)) {
            this.ip = String.format("[%s]", str2);
        } else {
            this.ip = str2;
        }
        this.ttl = str3;
        this.port = str4;
        this.updateTime = str5;
    }
}
