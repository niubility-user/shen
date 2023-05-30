package com.jingdong.pdj.libcore.point;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.pdj.libcore.home.HourlyGoFragment;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.PerfMonitor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import point.DJPointVisibleUtil;
import point.interfaces.DJDefaultRadioWithDelayInitializer;
import point.interfaces.DJDefaultReportInitializer;
import point.interfaces.DJPointData;
import point.interfaces.DJRectVisibleInterface;

/* loaded from: classes7.dex */
public class HourlyGoHomeMaiDianUpload {
    private static final String ET_MODEL = "et_model";
    private static final String ET_MODEL_CLICK = "lstm";
    private static final String ET_MODEL_EXPOSURE = "lstms";
    public static final String HOURLY_DETAIL_PAGE_NAME = "HourlyGoDetailFragment";
    private static final String HOURLY_GO_MAIDIAN = "hourly_go_maidian";
    public static final String HOURLY_HOME_PAGE_ID = "Home_Nearby_Main";
    public static final String HOURLY_HOME_PAGE_NAME = "HourlyGoHomeFragment";
    private static final String TAG = "HourlyGoHomeMaiDianUpload";
    private static final ConcurrentHashMap<String, LinkedList<PointData>> onceEventIdMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Integer> unOnceCountMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, PointData> unOnceParamsMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, LinkedList<PointData>> unOnceEventIdMap = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(Context context, String str, DJPointData dJPointData) {
        if (dJPointData instanceof PointData) {
            PointData pointData = (PointData) dJPointData;
            if (pointData.bySelfReport) {
                return;
            }
            sendExpoMtaData(pointData);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
        if (android.text.TextUtils.isEmpty(r4.expo_event_id) != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void checkPointParams(boolean z, PointData pointData) {
        boolean z2 = false;
        if (pointData != null) {
            try {
                if (!TextUtils.isEmpty(pointData.page_id) && !TextUtils.isEmpty(pointData.page_name)) {
                    if (z) {
                        if (TextUtils.isEmpty(pointData.event_id)) {
                        }
                        if (z2) {
                            return;
                        }
                        String str = z ? "click" : "exposure";
                        if (!isDebugModel()) {
                            if (pointData != null) {
                                PerfMonitor.getInstance().addExtraStr(HourlyGoFragment.HOURLY_FRAGMENT_NAME, HOURLY_GO_MAIDIAN, str.concat(pointData.pointLog()));
                                return;
                            }
                            return;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(" missing params:");
                        sb.append(pointData == null ? "pointData is null" : pointData.toString());
                        ToastUtil.showToast(sb.toString());
                        return;
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        z2 = true;
        if (z2) {
        }
    }

    private static void checkPvParams(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                if (isDebugModel()) {
                    ToastUtil.showToast("pv missing params:pageName=" + str + " pageId=" + str2);
                    return;
                }
                PerfMonitor.getInstance().addExtraStr(HourlyGoFragment.HOURLY_FRAGMENT_NAME, HOURLY_GO_MAIDIAN, "pv:pageName=" + str + " pageId=" + str2);
            }
        } catch (Exception unused) {
        }
    }

    private static String getExposureParams(String str, String str2, JDJSONArray jDJSONArray) {
        if (TextUtils.isEmpty(str)) {
            return jDJSONArray != null ? jDJSONArray.toJSONString() : "";
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put(str, (Object) jDJSONArray);
            if (!TextUtils.isEmpty(str2) && jDJSONArray != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(jDJSONArray.size());
                jDJSONObject.put(str2, (Object) sb.toString());
            }
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
        }
        return jDJSONObject.toJSONString();
    }

    public static String getPageName(boolean z) {
        return z ? "HourlyGoDetailFragment" : "HourlyGoHomeFragment";
    }

    public static void initPoint() {
        if (DJPointVisibleUtil.mDJDefaultRadioWithDelayInitializer == null) {
            DJPointVisibleUtil.setDJDefaultRadioWithDelayInitializer(new DJDefaultRadioWithDelayInitializer() { // from class: com.jingdong.pdj.libcore.point.b
                @Override // point.interfaces.DJDefaultRadioWithDelayInitializer
                public final void initialize(DJRectVisibleInterface dJRectVisibleInterface) {
                    dJRectVisibleInterface.setEpMTADelayRatio(0.3f).setEpMTADelayDuration(0L);
                }
            });
        }
        if (DJPointVisibleUtil.mDJDefaultReportInitializer == null) {
            DJPointVisibleUtil.setDJDefaultReportInitializer(new DJDefaultReportInitializer() { // from class: com.jingdong.pdj.libcore.point.a
                @Override // point.interfaces.DJDefaultReportInitializer
                public final void report(Context context, String str, DJPointData dJPointData) {
                    HourlyGoHomeMaiDianUpload.b(context, str, dJPointData);
                }
            });
        }
    }

    private static boolean isDebugModel() {
        return JdSdk.getInstance().getBuildConfigDebug();
    }

    public static void onViewClickPoint(PointData pointData) {
        checkPointParams(true, pointData);
        if (pointData == null || TextUtils.isEmpty(pointData.event_id)) {
            return;
        }
        OKLog.d(TAG, "onViewClickPoint=" + pointData.toString());
        HashMap hashMap = null;
        try {
            if (pointData.et_model_param != null) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("lstm", pointData.et_model_param);
                String jSONString = JDJSON.toJSONString(jDJSONObject, SerializerFeature.DisableCircularReferenceDetect);
                hashMap = new HashMap();
                hashMap.put("et_model", jSONString);
            }
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), pointData.event_id, null, "", pointData.page_id, pointData.page_name, "", "", HourlyFloorMaiDianJson.buildElementToJson(pointData.json_param), hashMap);
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
        }
    }

    public static void reportPv(Context context, String str, String str2, String... strArr) {
        String str3;
        checkPvParams(str, str2);
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2 += 2) {
                int i3 = i2 + 1;
                if (i3 < length) {
                    jDJSONObject.put(strArr[i2], (Object) strArr[i3]);
                }
            }
            str3 = jDJSONObject.toJSONString();
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
            str3 = null;
        }
        OKLog.d(TAG, "reportPv-pageId=" + str2 + " pageName=" + str + " page_params=" + str3);
        JDMtaUtils.sendPagePv(context, str, str3, str2, "");
    }

    public static void sendExpoMtaData(PointData pointData) {
        checkPointParams(false, pointData);
        if (pointData == null || TextUtils.isEmpty(pointData.expo_event_id) || pointData.isCache) {
            return;
        }
        OKLog.d(TAG, "sendExpoMtaData=" + pointData.toString());
        try {
            if (pointData.loopModel) {
                String concat = pointData.expo_event_id.concat(pointData.json_param);
                ConcurrentHashMap<String, Integer> concurrentHashMap = unOnceCountMap;
                concurrentHashMap.put(concat, Integer.valueOf(concurrentHashMap.containsKey(concat) ? 1 + concurrentHashMap.get(concat).intValue() : 1));
                unOnceParamsMap.put(concat, pointData);
            } else if (pointData.togetherReport) {
                setExposureData(pointData.expo_event_id, pointData);
            } else {
                uploadExposure(pointData.togetherKey, pointData.togetherSizeKey, pointData.expo_event_id, pointData.page_id, pointData.page_name, pointData.json_param, pointData.et_model_param);
            }
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
        }
    }

    private static void setExposureData(String str, PointData pointData) {
        List<String> list;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            ConcurrentHashMap<String, LinkedList<PointData>> concurrentHashMap = onceEventIdMap;
            LinkedList<PointData> linkedList = concurrentHashMap.containsKey(str) ? concurrentHashMap.get(str) : null;
            if (linkedList == null) {
                linkedList = new LinkedList<>();
            }
            if (pointData != null && (list = pointData.json_param_list) != null && !list.isEmpty()) {
                for (int i2 = 0; i2 < pointData.json_param_list.size(); i2++) {
                    linkedList.add(new PointData(pointData.page_id, pointData.page_name, pointData.event_id, pointData.expo_event_id, pointData.json_param_list.get(i2), pointData.togetherReport, pointData.isCache, pointData.isDetailPage));
                }
            } else {
                linkedList.add(pointData);
            }
            if (linkedList.size() >= 10) {
                sysPointMatchSize10(onceEventIdMap, str, linkedList);
            } else {
                onceEventIdMap.put(str, linkedList);
            }
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
        }
    }

    public static void sysCacheExposureData() {
        sysOncePoint(onceEventIdMap);
        sysUnOncePoint();
    }

    private static void sysOncePoint(ConcurrentHashMap<String, LinkedList<PointData>> concurrentHashMap) {
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, LinkedList<PointData>> entry : concurrentHashMap.entrySet()) {
            if (entry != null) {
                sysPointMatchSize10(concurrentHashMap, entry.getKey(), entry.getValue());
            }
        }
    }

    private static void sysPointMatchSize10(ConcurrentHashMap<String, LinkedList<PointData>> concurrentHashMap, String str, LinkedList<PointData> linkedList) {
        if (concurrentHashMap == null || TextUtils.isEmpty(str) || linkedList == null) {
            return;
        }
        try {
            JDJSONArray jDJSONArray = new JDJSONArray();
            JDJSONArray jDJSONArray2 = new JDJSONArray();
            Iterator<PointData> it = linkedList.iterator();
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            while (it.hasNext()) {
                PointData next = it.next();
                if (next instanceof PointData) {
                    PointData pointData = next;
                    if (TextUtils.isEmpty(str4) && !TextUtils.isEmpty(pointData.page_id)) {
                        str4 = pointData.page_id;
                    }
                    if (TextUtils.isEmpty(str5) && !TextUtils.isEmpty(pointData.page_name)) {
                        str5 = pointData.page_name;
                    }
                    if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(pointData.togetherKey)) {
                        str2 = pointData.togetherKey;
                    }
                    if (TextUtils.isEmpty(str3) && !TextUtils.isEmpty(pointData.togetherSizeKey)) {
                        str3 = pointData.togetherSizeKey;
                    }
                    if (!TextUtils.isEmpty(pointData.json_param)) {
                        jDJSONArray.add(pointData.json_param);
                    }
                    Object obj = pointData.et_model_param;
                    if (obj != null) {
                        jDJSONArray2.add(obj);
                    }
                }
            }
            uploadExposure(str2, str3, str, str4, str5, jDJSONArray, jDJSONArray2);
            concurrentHashMap.remove(str);
        } catch (Exception e2) {
            OKLog.d(TAG, e2);
        }
    }

    private static void sysUnOncePoint() {
        ConcurrentHashMap<String, PointData> concurrentHashMap = unOnceParamsMap;
        if (concurrentHashMap == null || unOnceEventIdMap == null || unOnceCountMap == null) {
            return;
        }
        if (!concurrentHashMap.isEmpty()) {
            for (Map.Entry<String, PointData> entry : concurrentHashMap.entrySet()) {
                PointData value = entry.getValue();
                if (value != null) {
                    int intValue = unOnceCountMap.get(entry.getKey()).intValue();
                    JDJSONObject parseObject = JDJSON.parseObject(value.json_param);
                    if (parseObject != null) {
                        parseObject.put("frequency", (Object) Integer.valueOf(intValue));
                        value.json_param = parseObject.toJSONString();
                    }
                }
            }
            Iterator<Map.Entry<String, PointData>> it = unOnceParamsMap.entrySet().iterator();
            while (it.hasNext()) {
                PointData value2 = it.next().getValue();
                if (value2 != null) {
                    String str = value2.expo_event_id;
                    ConcurrentHashMap<String, LinkedList<PointData>> concurrentHashMap2 = unOnceEventIdMap;
                    LinkedList<PointData> linkedList = concurrentHashMap2.containsKey(str) ? concurrentHashMap2.get(str) : null;
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(value2);
                    concurrentHashMap2.put(str, linkedList);
                }
            }
            sysOncePoint(unOnceEventIdMap);
        }
        unOnceParamsMap.clear();
        unOnceEventIdMap.clear();
        unOnceCountMap.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void uploadExposure(String str, String str2, String str3, String str4, String str5, Object obj, Object obj2) {
        String str6;
        String buildElementToJson;
        JDJSONArray jDJSONArray;
        HashMap hashMap = null;
        if (obj instanceof JDJSONArray) {
            buildElementToJson = getExposureParams(str, str2, HourlyFloorMaiDianJson.buildElementInJsonArray((JDJSONArray) obj));
        } else if (obj instanceof String) {
            buildElementToJson = HourlyFloorMaiDianJson.buildElementToJson((String) obj);
        } else {
            str6 = null;
            if (!(obj2 instanceof JDJSONArray)) {
                jDJSONArray = (JDJSONArray) obj2;
            } else if (obj2 instanceof JDJSONObject) {
                jDJSONArray = new JDJSONArray();
                jDJSONArray.add(obj2);
            } else {
                jDJSONArray = null;
            }
            if (jDJSONArray != null && jDJSONArray.size() > 0) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("lstms", (Object) jDJSONArray);
                hashMap = new HashMap();
                hashMap.put("et_model", JDJSON.toJSONString(jDJSONObject, SerializerFeature.DisableCircularReferenceDetect));
            }
            HashMap hashMap2 = hashMap;
            OKLog.d(TAG, "uploadExposure event_id" + str3 + " page_params=" + str6 + " extMap=" + hashMap2);
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str3, null, str4, str5, "", str6, "", "", "", hashMap2);
        }
        str6 = buildElementToJson;
        if (!(obj2 instanceof JDJSONArray)) {
        }
        if (jDJSONArray != null) {
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("lstms", (Object) jDJSONArray);
            hashMap = new HashMap();
            hashMap.put("et_model", JDJSON.toJSONString(jDJSONObject2, SerializerFeature.DisableCircularReferenceDetect));
        }
        HashMap hashMap22 = hashMap;
        OKLog.d(TAG, "uploadExposure event_id" + str3 + " page_params=" + str6 + " extMap=" + hashMap22);
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str3, null, str4, str5, "", str6, "", "", "", hashMap22);
    }
}
