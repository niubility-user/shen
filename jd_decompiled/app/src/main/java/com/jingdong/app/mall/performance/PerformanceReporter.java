package com.jingdong.app.mall.performance;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import performance.jd.jdreportperformance.JDReportInterface;
import performance.jd.jdreportperformance.entity.StategyEntity;
import performance.jd.jdreportperformance.minterface.InitInformation;

/* loaded from: classes.dex */
public class PerformanceReporter {
    private static InitInformation initCommonInfo;

    /* loaded from: classes.dex */
    public class a implements InitInformation.IPerformanceController {
        a() {
        }

        @Override // performance.jd.jdreportperformance.minterface.InitInformation.IPerformanceController
        public String queryIpByHost(String str) {
            IpModel ipModelByHost;
            if (TextUtils.isEmpty(str) || JDHttpDnsToolkit.getInstance() == null || (ipModelByHost = JDHttpDnsToolkit.getInstance().getIpModelByHost(str)) == null) {
                return null;
            }
            return ipModelByHost.master;
        }
    }

    public static InitInformation getInitCommonInfo() {
        if (initCommonInfo == null) {
            InitInformation initInformation = new InitInformation();
            initCommonInfo = initInformation;
            initInformation.appId = "4";
            initInformation.build = PackageInfoUtil.getVersionCode() + "";
            initCommonInfo.appVersion = PackageInfoUtil.getVersionName();
            initCommonInfo.harmonyVersion = PackageInfoUtil.getHarmonyVersionName();
            InitInformation initInformation2 = initCommonInfo;
            initInformation2.env = "2";
            initInformation2.controller = new a();
            initInformation2.logLevel = 1;
        }
        initCommonInfo.guid = DeviceInfoHelper.getAid();
        initCommonInfo.pin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(initCommonInfo.deviceManufacture)) {
            initCommonInfo.deviceManufacture = BaseInfo.getDeviceManufacture();
        }
        initCommonInfo.screenInfo = BaseInfo.getDisplayMetrics();
        try {
            initCommonInfo.userModel = Integer.parseInt(JDBModeUtils.getCurrentMode());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return initCommonInfo;
    }

    public static boolean getIsNeedReport(Context context, String str, String str2) {
        StategyEntity stategyEntitiy = getStategyEntitiy(context, str, str2);
        if (OKLog.D) {
            if (stategyEntitiy != null) {
                OKLog.d("PerformanceReporter", "requestStrategy: rt:" + stategyEntitiy.rt + ", ret:" + stategyEntitiy.ret + ", param:" + stategyEntitiy.param);
            } else {
                OKLog.d("PerformanceReporter", "requestStrategy: null");
            }
        }
        return stategyEntitiy != null && "1".equals(stategyEntitiy.ret);
    }

    public static boolean getModuleSwitch(StategyEntity stategyEntity, String str) {
        JDJSONObject parseObject;
        JDJSONObject jSONObject;
        if (stategyEntity == null || TextUtils.isEmpty(str) || (parseObject = JDJSON.parseObject(stategyEntity.param)) == null || (jSONObject = parseObject.getJSONObject(str)) == null) {
            return false;
        }
        return "1".equals(jSONObject.optString("switch", "0"));
    }

    public static StategyEntity getStategyEntitiy(Context context, String str, String str2) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PERFORMANCE_REPORTER, true)) {
            return JDReportInterface.getEntity(context, str, str2);
        }
        return null;
    }

    public static void init() {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PERFORMANCE_REPORTER, true)) {
            JDReportInterface.init(JdSdk.getInstance().getApplicationContext(), getInitCommonInfo());
        }
    }

    public static void reportChannelData(com.jingdong.app.mall.performance.a aVar) {
        StategyEntity stategyEntitiy;
        if (aVar == null || TextUtils.isEmpty(aVar.a) || TextUtils.isEmpty(aVar.f11521m)) {
            if (aVar == null) {
                return;
            }
            aVar.toString();
        } else if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PerformanceReportForRN, false) && (stategyEntitiy = getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "10", "3")) != null && "1".equals(stategyEntitiy.ret) && getModuleSwitch(stategyEntitiy, aVar.a)) {
            HashMap<String, String> d = aVar.d();
            d.put("typeId", "10");
            d.put("chId", "3");
            reportData(d);
        }
    }

    public static boolean reportData(ArrayList<HashMap<String, String>> arrayList) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PERFORMANCE_REPORTER, true)) {
            try {
                String currentMode = JDBModeUtils.getCurrentMode();
                if (arrayList != null && !arrayList.isEmpty()) {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (arrayList.get(i2) != null) {
                            arrayList.get(i2).put("userModel", currentMode);
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return JDReportInterface.sendData(JdSdk.getInstance().getApplication(), getInitCommonInfo(), arrayList);
        }
        return false;
    }

    public static void reportPlayerData(HashMap<String, String> hashMap) {
        reportData(hashMap);
    }

    public static void reportData(HashMap<String, String> hashMap) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.PERFORMANCE_REPORTER, true)) {
            if (hashMap != null) {
                try {
                    hashMap.put("userModel", JDBModeUtils.getCurrentMode());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            JDReportInterface.sendData(JdSdk.getInstance().getApplicationContext(), getInitCommonInfo(), hashMap);
        }
    }
}
