package com.jingdong.common.jdreactFramework.utils.apm;

import android.text.TextUtils;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.listener.JDReactNativeMtaReportListener;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class JDReactAPM {
    public static final String KEY_PAGE_STAGES_FSRENDER = "fsRenderTime";
    public static final String KEY_PAGE_STAGES_START_TIME = "startTime";
    private static volatile JDReactAPM instance;
    private IJDReactAPMAdapter iAPMAdapter;
    private Map<String, JDReactEvenInfo> recordStats = new ConcurrentHashMap();

    public static JDReactAPM getInstance() {
        if (instance == null) {
            synchronized (JDReactAPM.class) {
                if (instance == null) {
                    instance = new JDReactAPM();
                }
            }
        }
        return instance;
    }

    private void onEvent(String str, String str2, Object obj) {
        IJDReactAPMAdapter iJDReactAPMAdapter = this.iAPMAdapter;
        if (iJDReactAPMAdapter == null) {
            return;
        }
        iJDReactAPMAdapter.onEvent(str, str2, obj);
    }

    public void destroy(String str) {
        JDReactEvenInfo jDReactEvenInfo;
        if (TextUtils.isEmpty(str) || (jDReactEvenInfo = this.recordStats.get(str)) == null) {
            return;
        }
        try {
            if (jDReactEvenInfo.getFsRenderTime() > 0 && jDReactEvenInfo.getStartTime() > 0) {
                long fsRenderTime = jDReactEvenInfo.getFsRenderTime() - jDReactEvenInfo.getStartTime();
                if (fsRenderTime <= 0) {
                    JLog.d(JDReactConstant.ASSERT_DIR, "fstime=" + fsRenderTime);
                    return;
                }
                double d = (fsRenderTime > 1000 || fsRenderTime <= 0) ? 0.0d : 1.0d;
                HashMap hashMap = new HashMap();
                hashMap.put("pageID", str);
                hashMap.put("eventId", "JDReactFSTime");
                hashMap.put("jsonParam", new JSONObject().put("fsTime", fsRenderTime).put(NewFillOrderConstant.RATE, d).toString());
                new JDReactNativeMtaReportListener().sendClickDataWithJsonParam(hashMap);
                this.recordStats.remove(str);
                return;
            }
            JLog.d(JDReactConstant.ASSERT_DIR, "getFsRenderTime=" + jDReactEvenInfo.getFsRenderTime() + ";getStartTime=" + jDReactEvenInfo.getStartTime());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void recordFsRenderTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDReactEvenInfo jDReactEvenInfo = this.recordStats.get(str);
        if (jDReactEvenInfo == null) {
            jDReactEvenInfo = new JDReactEvenInfo();
            this.recordStats.put(str, jDReactEvenInfo);
        }
        if (jDReactEvenInfo.getFsRenderTime() > 0) {
            return;
        }
        jDReactEvenInfo.setFsRenderTime(System.currentTimeMillis());
        onEvent(str, KEY_PAGE_STAGES_FSRENDER, Long.valueOf(jDReactEvenInfo.getFsRenderTime()));
    }

    public void recordStartTime(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDReactEvenInfo jDReactEvenInfo = this.recordStats.get(str);
        if (jDReactEvenInfo == null) {
            jDReactEvenInfo = new JDReactEvenInfo();
            this.recordStats.put(str, jDReactEvenInfo);
        }
        if (jDReactEvenInfo.getStartTime() > 0) {
            return;
        }
        jDReactEvenInfo.setStartTime(System.currentTimeMillis());
        onEvent(str, "startTime", Long.valueOf(jDReactEvenInfo.getStartTime()));
    }

    public void setAPMAdapter(IJDReactAPMAdapter iJDReactAPMAdapter) {
        this.iAPMAdapter = iJDReactAPMAdapter;
    }

    public String toPerfString() {
        return this.recordStats.toString();
    }
}
