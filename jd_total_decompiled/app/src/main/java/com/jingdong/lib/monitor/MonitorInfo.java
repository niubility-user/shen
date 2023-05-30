package com.jingdong.lib.monitor;

import android.os.Build;
import android.os.Process;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MonitorInfo implements Serializable {
    private static final String TAG = "MonitorInfo";
    private static int runStage = -1;
    private static final long serialVersionUID = 3508487671432546078L;
    public Map<String, String> allThreadStack;
    public String analysisType;
    @Deprecated
    public String anrCrashLine;
    @Deprecated
    public String anrProcessName;
    public String buildCode;
    public String clientVersion;
    public String crashLine;
    public String crashStack;
    public String crashTime;
    public String crashType;
    public String currentPageInfo;
    public Map<String, String> feedback;
    public String partner;
    public String processName;
    public String sdkVersion;
    public String msgType = "2";
    public String bisType = ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID;

    public MonitorInfo() {
        try {
            this.crashTime = FormatUtils.formatDate(new Date());
            this.partner = Configuration.getProperty(Configuration.PARTNER);
            this.clientVersion = PackageInfoUtil.getVersionName();
            this.buildCode = String.valueOf(PackageInfoUtil.getVersionCode());
            this.currentPageInfo = com.jingdong.jdsdk.b.a.b();
            this.sdkVersion = Build.VERSION.SDK_INT + "";
            this.allThreadStack = new LinkedHashMap();
            this.processName = "";
            this.crashType = "";
            this.crashLine = "";
            this.crashStack = "";
            this.analysisType = "old";
            this.anrCrashLine = "";
            this.anrProcessName = "";
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
        try {
            initFeedback();
        } catch (Throwable th2) {
            OKLog.e(TAG, th2);
        }
    }

    public static String getString(String str) {
        return str == null ? "" : str;
    }

    private void initFeedback() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.feedback = linkedHashMap;
        linkedHashMap.put("processName", ProcessUtil.getProcessName(Process.myPid()));
        this.feedback.put("processId", Process.myPid() + "");
        this.feedback.put("memInfo", b.a());
        this.feedback.put("current free disk size", FileUtils.getDataDiskFreeSize(true) + "M, " + FileUtils.getDataDiskFreeSize(false) + "M");
        this.feedback.put(PerformanceManager.CUP, Build.CPU_ABI);
        this.feedback.put("romVersion", BaseInfo.getOSName());
        this.feedback.put("runStage", String.valueOf(runStage));
        this.feedback.put("runTime", c.b());
        this.feedback.put("battery", "");
        this.feedback.put("bundles", "");
        this.feedback.put("isRoot", String.valueOf(c.d()));
    }

    public static void setRunStage(int i2) {
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new InvalidParameterException("runStage " + i2 + " is not valid");
        }
        runStage = i2;
    }

    public String toString() {
        return JDJSON.toJSONString(this);
    }

    public JSONObject toUploadJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject(JDJSON.toJSONString(this));
        jSONObject.put("feedback", String.valueOf(this.feedback));
        jSONObject.put("allThreadStack", String.valueOf(this.allThreadStack));
        return jSONObject;
    }
}
