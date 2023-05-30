package com.jingdong.sdk.jdcrashreport.common;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.e;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.jdcrashreport.b.c;
import com.jingdong.sdk.jdcrashreport.b.i;
import com.jingdong.sdk.jdcrashreport.b.n;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.s;
import com.jingdong.sdk.jdcrashreport.b.t;
import com.jingdong.sdk.jdcrashreport.b.u;
import com.jingdong.sdk.jdcrashreport.b.x;
import com.jingdong.sdk.jdcrashreport.d;
import com.tencent.mapsdk.internal.l4;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class CrashInfo implements Serializable {
    private static final long serialVersionUID = 3508487671432546078L;
    public Map<String, JSONObject> allThreadStack;
    public String appArch;
    public String appId;
    public String buildCode;
    public String busiType;
    public String clientVersion;
    public String crashLine;
    public String crashSdkVersion;
    public String crashStack;
    public String crashTime;
    public String crashType;
    public String currentPageInfo;
    public String eigenvalue;
    public LinkedHashMap<String, String> extraInfo;
    public LinkedHashMap<String, String> feedback;
    public String flutterSdkVersion;
    public String h5Urls;
    public String isAnalysis;
    public String isForeground;
    public String lastH5Url;
    public String moduleName;
    public String msgType;
    public String pageInfo;
    public String partner;
    public String processName;
    public String sdkVersion;
    public String sysLog;
    public String threadName;
    public String userErrorMsg;
    public String userId;
    public String uts;
    public String moduleVersion = "";
    public String commitId = "";
    public String appTheme = "1";

    public static CrashInfo createCrashInfo() {
        CrashInfo crashInfo = new CrashInfo();
        crashInfo.initial();
        return crashInfo;
    }

    private static CrashInfo createEmptyCrashInfo() {
        return new CrashInfo();
    }

    private String feedback2UpLoadJsonObject() {
        if (this.feedback == null) {
            return "{}";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isCache", this.feedback.get("cache"));
            jSONObject.put("isForegroundRunning", this.feedback.get("isForegroundRunning"));
            jSONObject.put("isRoot", this.feedback.get("isRoot"));
            jSONObject.put("phoneNumber", this.feedback.get("phoneNumber"));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", this.feedback.get("processName"));
            jSONObject2.put("id", this.feedback.get("processId"));
            jSONObject.put("processInfo", jSONObject2);
            JSONArray jSONArray = new JSONArray();
            String str = this.feedback.get(PerformanceManager.CUP);
            if (!TextUtils.isEmpty(str)) {
                for (String str2 : str.split(DYConstants.DY_REGEX_COMMA)) {
                    jSONArray.put(str2.trim());
                }
            }
            jSONObject.put("cpuArch", jSONArray);
            jSONObject.put("networkType", this.feedback.get("networkType"));
            try {
                jSONObject.put("runningTimeInfo", new JSONObject(this.feedback.get("runningTimeInfo")));
            } catch (Exception unused) {
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("online", this.feedback.get("online"));
            jSONObject3.put("capacity", this.feedback.get("capacity"));
            jSONObject.put("batteryInfo", jSONObject3);
            jSONObject.put("romName", this.feedback.get("romName"));
            jSONObject.put("crashTimes", this.feedback.get("allCrashTimes"));
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("totalRAMSize", this.feedback.get("totalRAMSize"));
            jSONObject4.put("availableRAMSize", this.feedback.get("availableRAMSize"));
            jSONObject4.put("appUsageMemory", this.feedback.get("appUsageMemory"));
            jSONObject4.put("isLowMemory", this.feedback.get("isLowMemory"));
            jSONObject4.put("storageSize", this.feedback.get("storageSize"));
            jSONObject4.put("storageFreeSize", this.feedback.get("storageFreeSize"));
            jSONObject.put("memoryInfo", jSONObject4);
            JSONObject jSONObject5 = new JSONObject();
            LinkedHashMap<String, String> linkedHashMap = this.extraInfo;
            if (linkedHashMap != null) {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    jSONObject5.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject.put("extraInfo", jSONObject5);
            jSONObject.put("userErrorMsg", this.userErrorMsg);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static CrashInfo generateCrashInfo(Thread thread, Throwable th) {
        CrashInfo createCrashInfo = createCrashInfo();
        createCrashInfo.allThreadStack = c.d(thread);
        createCrashInfo.busiType = "java";
        createCrashInfo.isForeground = String.valueOf(c.j(d.I()));
        createCrashInfo.processName = c.a(Process.myPid()) + DYConstants.DY_REGEX_AT + thread.getName() + "(" + thread.getId() + ")";
        createCrashInfo.threadName = thread.getName() + "    [ id:" + thread.getId() + "  state:" + thread.getState() + " ]";
        if (th != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            while (th != null) {
                sb2.setLength(0);
                boolean z = th.getCause() == null;
                sb.append("---");
                sb.append(th.toString());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                StackTraceElement[] stackTrace = th.getStackTrace();
                if (z) {
                    createCrashInfo.crashType = th.toString();
                }
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb2.append(stackTraceElement.toString());
                    sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
                sb.append((CharSequence) sb2);
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                th = th.getCause();
            }
            createCrashInfo.crashLine = "";
            createCrashInfo.crashStack = sb.toString().trim();
        } else {
            createCrashInfo.crashType = "";
            createCrashInfo.crashLine = "";
        }
        return createCrashInfo;
    }

    private String generateEigenvalue() {
        String str = this.crashType;
        if (str == null) {
            str = "";
        }
        String str2 = this.crashLine;
        if (str2 == null) {
            str2 = "";
        }
        if (!this.busiType.equals("java") && !this.busiType.equals("rn")) {
            if (this.busiType.equals("native")) {
                String str3 = this.processName;
                int indexOf = str3.indexOf(58);
                if (indexOf > 0) {
                    str3 = this.processName.substring(0, indexOf);
                }
                String str4 = str3.replaceAll("\\.", "\\\\.") + "[^/.]+/";
                str2 = str2.replaceAll("#[0-9]+ pc ([a-f]|[0-9])+", "").replaceAll(str4, str3 + "/").replaceAll("[+ ][0-9]+", "").trim();
            } else if (this.busiType.equals("anr")) {
                str = str.replaceAll("[0-9]+", "");
                str2 = str2.replaceAll("[0-9]+", "");
            } else if (this.busiType.equals(JDReactConstant.FLUTTER_PATH)) {
                str = str.replaceAll("[0-9]+", "");
                str2 = str2.replaceAll("[0-9]+", "").trim();
            }
        } else {
            int indexOf2 = str.indexOf(":");
            if (indexOf2 > 0) {
                str = str.substring(0, indexOf2);
            }
            str2 = str2.replaceAll("[0-9]+", "");
        }
        String str5 = str + "<-->" + str2;
        try {
            return u.b(str5.getBytes());
        } catch (Exception unused) {
            return Base64.encodeToString(str5.getBytes(), 2);
        }
    }

    private static String getCrashLine(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if ("native".equals(str2)) {
            return getNativeCrashLine(str);
        }
        int lastIndexOf = str.lastIndexOf("\n\n");
        if (lastIndexOf >= 0) {
            str = str.substring(lastIndexOf + 2);
        }
        int indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (indexOf >= 0) {
            str = str.substring(indexOf + 1);
        }
        r.b("JDCrashReport", "stack: \n" + str);
        List<Pattern> V = d.V();
        if (V != null && V.size() > 0) {
            Iterator<Pattern> it = V.iterator();
            while (it.hasNext()) {
                Matcher matcher = it.next().matcher(str);
                if (matcher.find()) {
                    return matcher.group();
                }
            }
        }
        int indexOf2 = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return indexOf2 >= 0 ? str.substring(0, indexOf2).trim() : "";
    }

    private String getModuleName() {
        if (!TextUtils.isEmpty(this.moduleName) && !"unknown".equalsIgnoreCase(this.moduleName)) {
            return this.moduleName;
        }
        try {
            if (!TextUtils.isEmpty(this.crashLine)) {
                if (this.crashLine.startsWith("#")) {
                    int lastIndexOf = this.crashLine.lastIndexOf("/");
                    if (lastIndexOf >= 0) {
                        String trim = this.crashLine.substring(lastIndexOf + 1).trim();
                        int indexOf = trim.indexOf(40);
                        return indexOf > 0 ? trim.substring(0, indexOf).trim() : trim;
                    }
                    String str = this.crashLine;
                    return str.substring(str.indexOf("pc "));
                }
                String split = split(this.crashLine);
                if (!TextUtils.isEmpty(split)) {
                    return split;
                }
            }
        } catch (Throwable unused) {
        }
        return "Unknown";
    }

    private static String getNativeCrashLine(String str) {
        int indexOf = str.indexOf("backtrace:");
        if (indexOf != -1) {
            String[] split = str.substring(indexOf).split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            Pattern compile = Pattern.compile("^#\\d+\\spc\\s\\S+\\s{2}\\S+" + d.I().getPackageName() + "\\S+(\\s\\(.*\\))?$");
            for (String str2 : split) {
                Matcher matcher = compile.matcher(str2.trim());
                if (matcher.matches()) {
                    return matcher.group().trim();
                }
            }
            for (String str3 : split) {
                String trim = str3.trim();
                if (trim.startsWith("#")) {
                    return trim;
                }
            }
            return "";
        }
        return "";
    }

    private void initFeedback() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        this.feedback = linkedHashMap;
        linkedHashMap.put("cache", DYConstants.DY_FALSE);
        this.feedback.put("processName", c.a(Process.myPid()));
        this.feedback.put("processId", String.valueOf(Process.myPid()));
        this.feedback.put("isForegroundRunning", String.valueOf(c.t()));
        this.feedback.put("totalRAMSize", s.d());
        this.feedback.put("availableRAMSize", s.e());
        this.feedback.put("appUsageMemory", s.h());
        this.feedback.put("isLowMemory", String.valueOf(s.c()));
        this.feedback.put("storageSize", String.valueOf(s.f()));
        this.feedback.put("storageFreeSize", String.valueOf(s.g()));
        this.feedback.put(PerformanceManager.CUP, n.h());
        this.feedback.put("networkType", n.k());
        this.feedback.put("romName", n.m() + "(" + n.l() + ")");
        this.feedback.put(HybridSDK.OS_VERSION, n.d());
        this.feedback.put("allCrashTimes", String.valueOf(i.a("crash_times", 0)));
        int a = i.a("last_crash_consecutive_count", 0);
        if (a > 0) {
            this.feedback.put("consecutiveCrashTimes", String.valueOf(a));
        }
        this.feedback.put("runningTimeInfo", c.s());
        this.feedback.put("online", c.v());
        this.feedback.put("capacity", c.w());
        this.feedback.put("isRoot", String.valueOf(c.u()));
        this.feedback.put("crashSdkVersion", String.format(Locale.getDefault(), "%s", "2.6.3"));
    }

    private void initial() {
        try {
            this.msgType = "1";
            this.crashTime = x.d(new Date());
            this.partner = d.K();
            this.clientVersion = d.L();
            this.buildCode = String.valueOf(d.M());
            this.pageInfo = t.h();
            this.currentPageInfo = t.l();
            this.h5Urls = t.j();
            this.lastH5Url = t.k();
            this.sdkVersion = String.valueOf(Build.VERSION.SDK_INT);
            this.isForeground = String.valueOf(c.j(d.I()));
            this.allThreadStack = new HashMap();
            this.sysLog = c.b(Process.myPid(), 600);
            this.crashSdkVersion = "2.6.3";
            this.appId = d.N();
            this.userId = d.P();
            this.uts = d.Q();
            this.appArch = n.b(d.I());
            this.appTheme = d.U();
            initFeedback();
            this.extraInfo = new LinkedHashMap<>();
            this.userErrorMsg = "";
            this.isAnalysis = DYConstants.DY_FALSE;
        } catch (Throwable th) {
            r.c("JDCrashReport", "Initial CrashInfo failed", th);
        }
    }

    public static CrashInfo parse(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        try {
            CrashInfo createEmptyCrashInfo = createEmptyCrashInfo();
            createEmptyCrashInfo.msgType = jSONObject.optString(NotificationMessageSummary.MSGTYPE);
            createEmptyCrashInfo.busiType = jSONObject.optString("busiType");
            createEmptyCrashInfo.crashTime = jSONObject.optString("crashTime");
            createEmptyCrashInfo.processName = jSONObject.optString("processName");
            createEmptyCrashInfo.threadName = jSONObject.optString("threadName");
            createEmptyCrashInfo.isForeground = jSONObject.optString("isForeground");
            createEmptyCrashInfo.crashType = jSONObject.optString("crashType");
            createEmptyCrashInfo.crashStack = jSONObject.optString("crashStack");
            String optString = jSONObject.optString("crashLine");
            createEmptyCrashInfo.crashLine = optString;
            if (TextUtils.isEmpty(optString)) {
                createEmptyCrashInfo.crashLine = getCrashLine(createEmptyCrashInfo.crashStack, createEmptyCrashInfo.busiType);
            }
            createEmptyCrashInfo.isAnalysis = jSONObject.optString("isAnalysis");
            try {
                jSONObject2 = new JSONObject(jSONObject.optString("allThreadStack"));
            } catch (Exception unused) {
                jSONObject2 = new JSONObject();
            }
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject2.optJSONObject(next);
                if (createEmptyCrashInfo.allThreadStack == null) {
                    createEmptyCrashInfo.allThreadStack = new HashMap();
                }
                createEmptyCrashInfo.allThreadStack.put(next, optJSONObject);
            }
            try {
                jSONObject3 = new JSONObject(jSONObject.optString("extraInfo"));
            } catch (Exception unused2) {
                jSONObject3 = new JSONObject();
            }
            Iterator<String> keys2 = jSONObject3.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                String optString2 = jSONObject3.optString(next2);
                if (createEmptyCrashInfo.extraInfo == null) {
                    createEmptyCrashInfo.extraInfo = new LinkedHashMap<>();
                }
                createEmptyCrashInfo.extraInfo.put(next2, optString2);
            }
            try {
                jSONObject4 = new JSONObject(jSONObject.optString("feedback"));
            } catch (Exception unused3) {
                jSONObject4 = new JSONObject();
            }
            Iterator<String> keys3 = jSONObject4.keys();
            while (keys3.hasNext()) {
                String next3 = keys3.next();
                String optString3 = jSONObject4.optString(next3);
                if (createEmptyCrashInfo.feedback == null) {
                    createEmptyCrashInfo.feedback = new LinkedHashMap<>();
                }
                createEmptyCrashInfo.feedback.put(next3, optString3);
            }
            createEmptyCrashInfo.sysLog = jSONObject.optString("sysLog");
            createEmptyCrashInfo.partner = jSONObject.optString(Configuration.PARTNER);
            createEmptyCrashInfo.buildCode = jSONObject.optString("buildCode");
            createEmptyCrashInfo.clientVersion = jSONObject.optString(HybridSDK.APP_VERSION);
            createEmptyCrashInfo.pageInfo = jSONObject.optString("pageInfo");
            createEmptyCrashInfo.currentPageInfo = jSONObject.optString("currentPageInfo");
            createEmptyCrashInfo.lastH5Url = jSONObject.optString("lastH5Url");
            createEmptyCrashInfo.h5Urls = jSONObject.optString("h5Urls");
            createEmptyCrashInfo.sdkVersion = jSONObject.optString(l4.f16791e);
            createEmptyCrashInfo.crashSdkVersion = jSONObject.optString("crashSdkVersion");
            createEmptyCrashInfo.appId = jSONObject.optString("appId");
            createEmptyCrashInfo.userId = jSONObject.optString("userId");
            createEmptyCrashInfo.uts = jSONObject.optString("uts");
            createEmptyCrashInfo.appArch = jSONObject.optString("appArch");
            createEmptyCrashInfo.userErrorMsg = jSONObject.optString("userErrorMsg");
            createEmptyCrashInfo.moduleVersion = jSONObject.optString("moduleVersion");
            createEmptyCrashInfo.commitId = jSONObject.optString(JDReactConstant.COMMITID);
            createEmptyCrashInfo.moduleName = jSONObject.optString("moduleName");
            createEmptyCrashInfo.flutterSdkVersion = jSONObject.optString("flutterSdkVersion");
            String optString4 = jSONObject.optString("appTheme");
            if (TextUtils.isEmpty(optString4)) {
                optString4 = "1";
            }
            createEmptyCrashInfo.appTheme = optString4;
            return createEmptyCrashInfo;
        } catch (Throwable th) {
            r.c("JDCrashReport", "Parse CrashInfo Failed", th);
            return null;
        }
    }

    private static String split(String str) {
        Matcher matcher = Pattern.compile("(([_a-zA-Z\\d]+\\.)+)(([_a-zA-Z\\d]+)(\\$[_a-zA-Z\\d]+)*\\.)([_a-zA-Z<>\\d]+)\\(([\\S\\s]+)").matcher(str);
        String group = matcher.matches() ? matcher.group(4) : "";
        int indexOf = str.indexOf("(") + 1;
        int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
        return (indexOf <= 0 || lastIndexOf <= 0 || indexOf >= lastIndexOf) ? group : str.substring(indexOf, lastIndexOf);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0096, code lost:
        if (r13.equals("d") == false) goto L172;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String sysLog2UpLoadJsonObject() {
        JSONObject jSONObject = new JSONObject();
        String str = this.sysLog;
        if (str != null && str.length() > 0) {
            Pattern compile = Pattern.compile("(\\d{2}-\\d{2})\\s+(\\S+\\.\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\S)\\s+(.*)");
            String[] split = this.sysLog.split("\\n");
            int length = split.length;
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            for (int i2 = 1; i2 < length; i2++) {
                String str2 = split[i2];
                Matcher matcher = compile.matcher(str2);
                if (matcher.matches()) {
                    char c2 = 5;
                    if (matcher.groupCount() > 5) {
                        String group = matcher.group(5);
                        group.hashCode();
                        switch (group.hashCode()) {
                            case 68:
                                if (group.equals(AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE)) {
                                    c2 = 0;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 69:
                                if (group.equals("E")) {
                                    c2 = 1;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 73:
                                if (group.equals("I")) {
                                    c2 = 2;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 86:
                                if (group.equals("V")) {
                                    c2 = 3;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 87:
                                if (group.equals("W")) {
                                    c2 = 4;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 100:
                                break;
                            case 101:
                                if (group.equals(e.a)) {
                                    c2 = 6;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 105:
                                if (group.equals("i")) {
                                    c2 = 7;
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 118:
                                if (group.equals("v")) {
                                    c2 = '\b';
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            case 119:
                                if (group.equals(JshopConst.JSHOP_PROMOTIO_W)) {
                                    c2 = '\t';
                                    break;
                                }
                                c2 = '\uffff';
                                break;
                            default:
                                c2 = '\uffff';
                                break;
                        }
                        switch (c2) {
                            case 0:
                            case 5:
                                sb3.append(str2);
                                sb3.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                continue;
                            case 1:
                            case 6:
                                sb5.append(str2);
                                sb5.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                continue;
                            case 2:
                            case 7:
                                sb2.append(str2);
                                sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                continue;
                            case 3:
                            case '\b':
                                sb.append(str2);
                                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                continue;
                            case 4:
                            case '\t':
                                sb4.append(str2);
                                sb4.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                                continue;
                        }
                    }
                }
            }
            try {
                jSONObject.put("verbose", sb.toString());
                jSONObject.put("info", sb2.toString());
                jSONObject.put("debug", sb3.toString());
                jSONObject.put("warn", sb4.toString());
                jSONObject.put("error", sb5.toString());
                jSONObject.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, this.sysLog);
            } catch (Exception e2) {
                r.g("sysLog2UpLoadJsonObject", e2);
                jSONObject = new JSONObject();
            }
        }
        return jSONObject.toString();
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationMessageSummary.MSGTYPE, String.valueOf(this.msgType));
            jSONObject.put("busiType", String.valueOf(this.busiType));
            jSONObject.put("crashTime", String.valueOf(this.crashTime));
            jSONObject.put("processName", String.valueOf(this.processName));
            jSONObject.put("threadName", String.valueOf(this.threadName));
            jSONObject.put("isForeground", String.valueOf(this.isForeground));
            jSONObject.put("crashType", String.valueOf(this.crashType));
            jSONObject.put("crashLine", String.valueOf(this.crashLine));
            jSONObject.put("crashStack", String.valueOf(this.crashStack));
            jSONObject.put("sysLog", String.valueOf(this.sysLog));
            jSONObject.put(Configuration.PARTNER, String.valueOf(this.partner));
            jSONObject.put(HybridSDK.APP_VERSION, String.valueOf(this.clientVersion));
            jSONObject.put("buildCode", String.valueOf(this.buildCode));
            jSONObject.put("pageInfo", String.valueOf(this.pageInfo));
            jSONObject.put("currentPageInfo", String.valueOf(this.currentPageInfo));
            jSONObject.put("h5Urls", String.valueOf(this.h5Urls));
            jSONObject.put("lastH5Url", String.valueOf(this.lastH5Url));
            jSONObject.put(l4.f16791e, String.valueOf(this.sdkVersion));
            jSONObject.put("crashSdkVersion", String.valueOf(this.crashSdkVersion));
            jSONObject.put("appId", String.valueOf(this.appId));
            jSONObject.put("userId", String.valueOf(this.userId));
            jSONObject.put("uts", String.valueOf(this.uts));
            jSONObject.put("appArch", String.valueOf(this.appArch));
            jSONObject.put("userErrorMsg", String.valueOf(this.userErrorMsg));
            jSONObject.put("moduleVersion", String.valueOf(this.moduleVersion));
            jSONObject.put(JDReactConstant.COMMITID, String.valueOf(this.commitId));
            jSONObject.put("moduleName", getModuleName());
            jSONObject.put("isAnalysis", String.valueOf(this.isAnalysis));
            jSONObject.put("flutterSdkVersion", String.valueOf(this.flutterSdkVersion));
            jSONObject.put("appTheme", String.valueOf(this.appTheme));
            JSONObject jSONObject2 = new JSONObject();
            LinkedHashMap<String, String> linkedHashMap = this.feedback;
            if (linkedHashMap != null) {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject.put("feedback", String.valueOf(jSONObject2));
            JSONObject jSONObject3 = new JSONObject();
            Map<String, JSONObject> map = this.allThreadStack;
            if (map != null) {
                for (Map.Entry<String, JSONObject> entry2 : map.entrySet()) {
                    jSONObject3.put(entry2.getKey(), entry2.getValue());
                }
            }
            jSONObject.put("allThreadStack", String.valueOf(jSONObject3));
            if (this.extraInfo != null) {
                JSONObject jSONObject4 = new JSONObject();
                for (Map.Entry<String, String> entry3 : this.extraInfo.entrySet()) {
                    jSONObject4.put(entry3.getKey(), entry3.getValue());
                }
                jSONObject.put("extraInfo", String.valueOf(jSONObject4));
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            r.c("JDCrashReport", "Convert CrashInfo to String failed", th);
            return "{}";
        }
    }

    public JSONObject toUploadJsonObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationMessageSummary.MSGTYPE, String.valueOf(this.msgType));
        jSONObject.put("busiType", String.valueOf(this.busiType));
        jSONObject.put("crashTime", String.valueOf(this.crashTime));
        jSONObject.put("processName", String.valueOf(this.processName));
        jSONObject.put("threadName", String.valueOf(this.threadName));
        jSONObject.put("isForeground", String.valueOf(this.isForeground));
        String str = this.crashType;
        String str2 = this.crashStack;
        if (this.msgType.equals("5")) {
            str = this.crashType.replaceFirst("java.lang.Throwable: ", "");
            str2 = this.crashStack.replaceFirst("java.lang.Throwable: ", "");
        }
        jSONObject.put("crashType", str);
        jSONObject.put("crashStack", str2);
        jSONObject.put("crashLine", String.valueOf(this.crashLine));
        jSONObject.put("sysLog", sysLog2UpLoadJsonObject());
        jSONObject.put(Configuration.PARTNER, String.valueOf(this.partner));
        jSONObject.put(HybridSDK.APP_VERSION, String.valueOf(this.clientVersion));
        jSONObject.put("buildCode", String.valueOf(this.buildCode));
        jSONObject.put("pageInfo", String.valueOf(this.pageInfo));
        jSONObject.put("currentPageInfo", String.valueOf(this.currentPageInfo));
        jSONObject.put("lastH5Url", String.valueOf(this.lastH5Url));
        jSONObject.put("h5Urls", String.valueOf(this.h5Urls));
        jSONObject.put(l4.f16791e, String.valueOf(this.sdkVersion));
        jSONObject.put("crashSdkVersion", String.valueOf(this.crashSdkVersion));
        jSONObject.put("appId", String.valueOf(this.appId));
        jSONObject.put("userId", String.valueOf(this.userId));
        jSONObject.put("uts", String.valueOf(this.uts));
        jSONObject.put("appArch", String.valueOf(this.appArch));
        jSONObject.put("feedback", feedback2UpLoadJsonObject());
        if (this.allThreadStack == null) {
            this.allThreadStack = new HashMap();
        }
        jSONObject.put("allThreadStack", String.valueOf(new JSONObject(this.allThreadStack)));
        jSONObject.put("moduleName", getModuleName());
        jSONObject.put("moduleVersion", String.valueOf(this.moduleVersion));
        jSONObject.put(JDReactConstant.COMMITID, String.valueOf(this.commitId));
        jSONObject.put("isAnalysis", String.valueOf(this.isAnalysis));
        jSONObject.put("eigenvalue", generateEigenvalue());
        jSONObject.put("flutterSdkVersion", String.valueOf(this.flutterSdkVersion));
        jSONObject.put("appTheme", String.valueOf(this.appTheme));
        return jSONObject;
    }
}
