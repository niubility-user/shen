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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void checkPointParams(boolean r3, com.jingdong.pdj.libcore.point.PointData r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L26
            java.lang.String r1 = r4.page_id     // Catch: java.lang.Exception -> L6d
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L6d
            if (r1 != 0) goto L26
            java.lang.String r1 = r4.page_name     // Catch: java.lang.Exception -> L6d
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L6d
            if (r1 != 0) goto L26
            if (r3 == 0) goto L1e
            java.lang.String r1 = r4.event_id     // Catch: java.lang.Exception -> L6d
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L6d
            if (r1 == 0) goto L27
            goto L26
        L1e:
            java.lang.String r1 = r4.expo_event_id     // Catch: java.lang.Exception -> L6d
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L6d
            if (r1 == 0) goto L27
        L26:
            r0 = 1
        L27:
            if (r0 == 0) goto L6d
            if (r3 == 0) goto L2e
            java.lang.String r3 = "click"
            goto L30
        L2e:
            java.lang.String r3 = "exposure"
        L30:
            boolean r0 = isDebugModel()     // Catch: java.lang.Exception -> L6d
            if (r0 == 0) goto L57
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6d
            r0.<init>()     // Catch: java.lang.Exception -> L6d
            r0.append(r3)     // Catch: java.lang.Exception -> L6d
            java.lang.String r3 = " missing params:"
            r0.append(r3)     // Catch: java.lang.Exception -> L6d
            if (r4 != 0) goto L48
            java.lang.String r3 = "pointData is null"
            goto L4c
        L48:
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Exception -> L6d
        L4c:
            r0.append(r3)     // Catch: java.lang.Exception -> L6d
            java.lang.String r3 = r0.toString()     // Catch: java.lang.Exception -> L6d
            com.jingdong.common.utils.ToastUtil.showToast(r3)     // Catch: java.lang.Exception -> L6d
            return
        L57:
            if (r4 == 0) goto L6d
            com.jingdong.sdk.perfmonitor.PerfMonitor r0 = com.jingdong.sdk.perfmonitor.PerfMonitor.getInstance()     // Catch: java.lang.Exception -> L6d
            java.lang.String r1 = "com.jd.lib.hourlygo.home.fragment.HourlyGoHomeFragment"
            java.lang.String r2 = "hourly_go_maidian"
            java.lang.String r4 = r4.pointLog()     // Catch: java.lang.Exception -> L6d
            java.lang.String r3 = r3.concat(r4)     // Catch: java.lang.Exception -> L6d
            r0.addExtraStr(r1, r2, r3)     // Catch: java.lang.Exception -> L6d
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload.checkPointParams(boolean, com.jingdong.pdj.libcore.point.PointData):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void uploadExposure(java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.Object r20, java.lang.Object r21) {
        /*
            r0 = r20
            r1 = r21
            boolean r2 = r0 instanceof com.jd.framework.json.JDJSONArray
            r3 = 0
            if (r2 == 0) goto L18
            com.jd.framework.json.JDJSONArray r0 = (com.jd.framework.json.JDJSONArray) r0
            com.jd.framework.json.JDJSONArray r0 = com.jingdong.pdj.libcore.point.HourlyFloorMaiDianJson.buildElementInJsonArray(r0)
            r2 = r15
            r4 = r16
            java.lang.String r0 = getExposureParams(r15, r4, r0)
        L16:
            r10 = r0
            goto L24
        L18:
            boolean r2 = r0 instanceof java.lang.String
            if (r2 == 0) goto L23
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r0 = com.jingdong.pdj.libcore.point.HourlyFloorMaiDianJson.buildElementToJson(r0)
            goto L16
        L23:
            r10 = r3
        L24:
            boolean r0 = r1 instanceof com.jd.framework.json.JDJSONArray
            if (r0 == 0) goto L2c
            r0 = r1
            com.jd.framework.json.JDJSONArray r0 = (com.jd.framework.json.JDJSONArray) r0
            goto L3a
        L2c:
            boolean r0 = r1 instanceof com.jd.framework.json.JDJSONObject
            if (r0 == 0) goto L39
            com.jd.framework.json.JDJSONArray r0 = new com.jd.framework.json.JDJSONArray
            r0.<init>()
            r0.add(r1)
            goto L3a
        L39:
            r0 = r3
        L3a:
            if (r0 == 0) goto L62
            int r1 = r0.size()
            if (r1 <= 0) goto L62
            com.jd.framework.json.JDJSONObject r1 = new com.jd.framework.json.JDJSONObject
            r1.<init>()
            java.lang.String r2 = "lstms"
            r1.put(r2, r0)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            r0 = 1
            com.alibaba.fastjson.serializer.SerializerFeature[] r0 = new com.alibaba.fastjson.serializer.SerializerFeature[r0]
            r2 = 0
            com.alibaba.fastjson.serializer.SerializerFeature r4 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            r0[r2] = r4
            java.lang.String r0 = com.jd.framework.json.JDJSON.toJSONString(r1, r0)
            java.lang.String r1 = "et_model"
            r3.put(r1, r0)
        L62:
            r14 = r3
            java.lang.String r0 = com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "uploadExposure event_id"
            r1.<init>(r2)
            r2 = r17
            r1.append(r2)
            java.lang.String r3 = " page_params="
            r1.append(r3)
            r1.append(r10)
            java.lang.String r3 = " extMap="
            r1.append(r3)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            com.jingdong.sdk.oklog.OKLog.d(r0, r1)
            com.jingdong.jdsdk.JdSdk r0 = com.jingdong.jdsdk.JdSdk.getInstance()
            android.content.Context r4 = r0.getApplicationContext()
            r6 = 0
            java.lang.String r9 = ""
            java.lang.String r11 = ""
            java.lang.String r12 = ""
            java.lang.String r13 = ""
            r5 = r17
            r7 = r18
            r8 = r19
            com.jingdong.jdsdk.mta.JDMtaUtils.sendExposureDataWithExt(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload.uploadExposure(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Object, java.lang.Object):void");
    }
}
