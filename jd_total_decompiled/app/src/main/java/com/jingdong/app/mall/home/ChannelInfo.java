package com.jingdong.app.mall.home;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ChannelInfo {
    public static final String IS_OPEN_APP = "1";
    public static final String OPEN_APP_KEY = "openApp";
    private static final String TAG = "ChannelInfo";
    private static final long TEN_MINUTE = 600000;
    public static final int TYPE_KEPLER = 1;
    public static final int TYPE_TTT = 2;
    private static ChannelInfo sBabelFirstInfo;
    final int from;
    final JSONObject info;
    long saveTime;
    public static final String BABEL_CHANNEL_INTERVAL = "key_babel_channel_interval";
    private static long sBabelInterval = CommonBase.getLongFromPreference(BABEL_CHANNEL_INTERVAL, 0);
    private static final SparseArray<ChannelInfo> sChannelCache = new SparseArray<>();

    public ChannelInfo(int i2, JSONObject jSONObject) {
        this.from = i2;
        this.info = jSONObject;
    }

    public static ChannelInfo getBabelFirstInfo() {
        return sBabelFirstInfo;
    }

    public static ChannelInfo getChannelInfo() {
        return getChannelInfo(1);
    }

    private static void print(String str, ChannelInfo channelInfo) {
        if (!OKLog.D || channelInfo == null) {
            return;
        }
        OKLog.d(TAG, str + channelInfo.toString());
    }

    private static void saveBabelInfoFirst(ChannelInfo channelInfo) {
        JSONObject jSONObject = channelInfo.info;
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        if (sBabelFirstInfo == null) {
            sBabelFirstInfo = channelInfo;
            print("save First info : ", channelInfo);
        } else if (TextUtils.equals("1", jSONObject.optString("openApp"))) {
            sBabelFirstInfo = channelInfo;
            print("save First info : ", channelInfo);
        } else {
            if (channelInfo.saveTime - sBabelFirstInfo.saveTime > Math.max(600000L, sBabelInterval)) {
                sBabelFirstInfo = channelInfo;
            }
            print("save Current info : ", channelInfo);
        }
    }

    public static void saveChannelInfo(ChannelInfo channelInfo) {
        if (channelInfo != null) {
            channelInfo.saveTime = SystemClock.elapsedRealtime();
            sChannelCache.put(channelInfo.from, channelInfo);
            if (channelInfo.from == 2) {
                saveBabelInfoFirst(channelInfo);
            }
        }
    }

    public static void setBabelInterval(long j2) {
        sBabelInterval = j2;
    }

    public int getFrom() {
        return this.from;
    }

    public JSONObject getInfo() {
        return this.info;
    }

    public String getInfoString(String str) {
        JSONObject jSONObject = this.info;
        return jSONObject == null ? "" : jSONObject.optString(str);
    }

    public long getSaveTime() {
        return this.saveTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChannelInfo{, saveTime=");
        sb.append(this.saveTime);
        sb.append("from=");
        sb.append(this.from);
        sb.append(", info=");
        JSONObject jSONObject = this.info;
        sb.append(jSONObject == null ? DYConstants.DY_NULL_STR : jSONObject.toString());
        sb.append('}');
        return sb.toString();
    }

    public static ChannelInfo getChannelInfo(int i2) {
        return sChannelCache.get(i2);
    }
}
