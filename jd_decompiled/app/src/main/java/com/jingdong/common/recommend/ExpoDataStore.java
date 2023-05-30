package com.jingdong.common.recommend;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.recommend.entity.ExpoData;
import com.jingdong.common.recommend.entity.RecomPerformanceData;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ExpoDataStore {
    private static final String RECEXPOMTACTRL = "recExpoMtaCtrl";
    private static final String TAG = "ExpoDataStore";
    private String eventid;
    private Map<String, ExpoData> expoDataMaps;
    private ArrayList<ExpoData> expoDatas;
    private ArrayList<ExpoData> expoJsonDatas;
    private int expoNum;
    private boolean isFilterRepeat;
    private int mFrom;
    private String pageParam;
    private String pageid;
    private Map<Integer, RecomPerformanceData> performanceDataMap;

    private ExpoDataStore(String str, String str2) {
        this.performanceDataMap = new HashMap();
        this.expoNum = 100;
        this.pageParam = "";
        this.mFrom = -1;
        this.eventid = str;
        this.pageid = str2;
        this.expoDatas = new ArrayList<>();
        this.expoDataMaps = new HashMap();
        this.expoJsonDatas = new ArrayList<>();
    }

    public static ExpoDataStore createExpoDataStore(String str, String str2, String str3) {
        return new ExpoDataStore(str, str2, str3);
    }

    public static ExpoDataStore createExpoDataStpre(String str, String str2) {
        return new ExpoDataStore(str, str2);
    }

    public static String dealRecomProductJsonString(String str, int i2, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            StringBuilder sb = new StringBuilder(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\"skutime\":");
            sb2.append("\"");
            sb2.append(String.valueOf(System.currentTimeMillis() / 1000));
            sb2.append("\",");
            if (i2 == 9) {
                sb2.append("\"tagid\":");
                sb2.append("\"");
                sb2.append(str2);
                sb2.append("\",");
            }
            sb.insert(1, (CharSequence) sb2);
            return sb.toString();
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return "";
            }
            return "";
        }
    }

    private StringBuilder getExpoJsonMta() {
        ArrayList<ExpoData> arrayList = this.expoJsonDatas;
        if (arrayList != null && arrayList.size() >= 1) {
            int size = this.expoJsonDatas.size();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i2 = 0; i2 < size; i2++) {
                ExpoData expoData = this.expoJsonDatas.get(i2);
                String exposureSourceValue = expoData.getExposureSourceValue();
                if (exposureSourceValue != null && exposureSourceValue.length() > 0) {
                    StringBuilder sb2 = new StringBuilder(exposureSourceValue);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\"skutime\":");
                    sb3.append("\"");
                    sb3.append(expoData.time);
                    sb3.append("\",");
                    sb2.insert(1, (CharSequence) sb3);
                    sb.append((CharSequence) sb2);
                    if (i2 != size - 1) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                }
            }
            sb.append("]");
            return sb;
        }
        return new StringBuilder();
    }

    private void sendExpoMtaData(Context context, StringBuilder sb, StringBuilder sb2) {
        try {
            if (TextUtils.isEmpty(sb) && TextUtils.isEmpty(sb2)) {
                return;
            }
            JDMtaUtils.sendExposureDataWithExt(context, this.eventid, sb.toString(), this.pageid, context.getClass().getName(), this.pageParam, sb2.toString(), "", "", "", null);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public String getEventid() {
        return this.eventid;
    }

    public RecomPerformanceData getPerformanceItem(int i2) {
        if (!this.performanceDataMap.containsKey(Integer.valueOf(i2))) {
            this.performanceDataMap.put(Integer.valueOf(i2), new RecomPerformanceData());
        }
        return this.performanceDataMap.get(Integer.valueOf(i2));
    }

    public void putExoJsonData(int i2, ExpoData expoData) {
        if (expoData == null || this.expoJsonDatas.size() >= this.expoNum || TextUtils.isEmpty(expoData.exposureSourceValue)) {
            return;
        }
        expoData.getnowtime();
        this.expoJsonDatas.add(expoData);
        this.mFrom = i2;
    }

    @Deprecated
    public void putExpoData(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.isFilterRepeat) {
            if (this.expoDataMaps.size() >= this.expoNum || this.expoDataMaps.containsKey(str)) {
                return;
            }
        } else if (this.expoDatas.size() >= this.expoNum) {
            return;
        }
        ExpoData expoData = new ExpoData();
        expoData.sku = str;
        expoData.reqsign = str2;
        expoData.sid = str3;
        expoData.flow = str4;
        expoData.source = str5;
        expoData.plus = str6;
        if (this.isFilterRepeat) {
            this.expoDataMaps.put(str, expoData);
        } else {
            this.expoDatas.add(expoData);
        }
    }

    public void putExpoJsonDada(String str) {
        putExpoJsonDada(str, "-100", this.mFrom, -1);
    }

    public void sendExpoMta(Context context) {
        sendExpoMta(context, false);
    }

    public void sendPerformanceData(Context context) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<Map.Entry<Integer, RecomPerformanceData>> it = this.performanceDataMap.entrySet().iterator();
            while (it.hasNext()) {
                JSONObject expoJson = it.next().getValue().toExpoJson();
                if (expoJson != null) {
                    jSONArray.put(expoJson);
                }
            }
            if (jSONArray.length() > 0) {
                JDMtaUtils.sendExposureDataWithExt(context, this.eventid, "", this.pageid, context.getClass().getName(), this.pageParam, jSONArray.toString(), "", "", "", null);
            }
            this.performanceDataMap.clear();
        } catch (Exception unused) {
        }
    }

    public void setEventid(String str) {
        this.eventid = str;
    }

    public void setExpoNum(int i2) {
        this.expoNum = i2;
    }

    public void setFilterRepeat(boolean z) {
        this.isFilterRepeat = z;
    }

    public void putExpoJsonDada(String str, String str2, int i2, int i3) {
        if (this.expoJsonDatas.size() < this.expoNum && !TextUtils.isEmpty(str)) {
            this.mFrom = i2;
            ExpoData expoData = new ExpoData();
            expoData.exposureSourceValue = str;
            expoData.isBackUp = i3;
            this.expoJsonDatas.add(expoData);
        }
    }

    public void sendExpoMta(Context context, boolean z) {
        ArrayList<ExpoData> arrayList;
        Map<String, ExpoData> map;
        if (ConfigUtil.getStringFromPreference(RECEXPOMTACTRL, "1").equals("0") || this.eventid == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (this.isFilterRepeat) {
            Map<String, ExpoData> map2 = this.expoDataMaps;
            if (map2 != null && !map2.isEmpty()) {
                for (ExpoData expoData : this.expoDataMaps.values()) {
                    sb.append(expoData.time);
                    sb.append("#" + expoData.exposureSourceValue + CartConstant.KEY_YB_INFO_LINK);
                }
                if (sb.length() > 1) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        } else {
            ArrayList<ExpoData> arrayList2 = this.expoDatas;
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                for (int i2 = 0; i2 < this.expoDatas.size(); i2++) {
                    ExpoData expoData2 = this.expoDatas.get(i2);
                    sb.append(expoData2.time);
                    if (i2 == this.expoDatas.size() - 1) {
                        sb.append("#" + expoData2.exposureSourceValue);
                    } else {
                        sb.append("#" + expoData2.exposureSourceValue + CartConstant.KEY_YB_INFO_LINK);
                    }
                }
            }
        }
        sendExpoMtaData(context, sb, getExpoJsonMta());
        if (this.isFilterRepeat && (map = this.expoDataMaps) != null && !map.isEmpty()) {
            this.expoDataMaps.clear();
        } else if (!this.isFilterRepeat && (arrayList = this.expoDatas) != null && !arrayList.isEmpty()) {
            this.expoDatas.clear();
        }
        ArrayList<ExpoData> arrayList3 = this.expoJsonDatas;
        if (arrayList3 == null || arrayList3.isEmpty()) {
            return;
        }
        this.expoJsonDatas.clear();
    }

    private ExpoDataStore(String str, String str2, String str3) {
        this.performanceDataMap = new HashMap();
        this.expoNum = 100;
        this.pageParam = "";
        this.mFrom = -1;
        this.eventid = str;
        this.pageid = str2;
        this.pageParam = str3;
        this.expoDatas = new ArrayList<>();
        this.expoDataMaps = new HashMap();
        this.expoJsonDatas = new ArrayList<>();
    }

    public void putExpoData(String str) {
        putExpoData(str, -1);
    }

    public void putExpoData(String str, int i2) {
        if (this.isFilterRepeat) {
            if (this.expoDataMaps.size() >= this.expoNum || this.expoDataMaps.containsKey(str)) {
                return;
            }
        } else if (this.expoDatas.size() >= this.expoNum) {
            return;
        }
        ExpoData expoData = new ExpoData();
        expoData.exposureSourceValue = str;
        expoData.isBackUp = i2;
        if (this.isFilterRepeat) {
            this.expoDataMaps.put(str, expoData);
        } else {
            this.expoDatas.add(expoData);
        }
    }
}
