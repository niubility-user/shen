package com.jingdong.app.mall.utils;

import android.content.Context;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.android.sdk.coreinfo.util.Supplier;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jingdong.app.mall.JDApp;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.permission.entity.SceneStatus;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.baseinfo.BaseInfoConfig;
import com.jingdong.sdk.baseinfo.IBackForegroundCheck;
import com.jingdong.sdk.baseinfo.IBuildConfigGetter;
import com.jingdong.sdk.baseinfo.IDataCacheScheme;
import com.jingdong.sdk.baseinfo.IDensityRelateCheck;
import com.jingdong.sdk.baseinfo.IPrivacyCheck;
import com.jingdong.sdk.baseinfo.IPrivacyInfoCallback;
import com.jingdong.sdk.baseinfo.ResultCallback;
import com.jingdong.sdk.baseinfo.db.PrivacyInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private static boolean a = true;
    private static boolean b = true;

    /* renamed from: c */
    private static long f11780c = 0;
    static int d = -1;

    /* renamed from: e */
    static int f11781e = -1;

    /* renamed from: f */
    private static volatile boolean f11782f;

    /* loaded from: classes4.dex */
    public class a implements IDataCacheScheme {
        a() {
        }

        @Override // com.jingdong.sdk.baseinfo.IDataCacheScheme
        public int getScheme() {
            if (d.d == -1) {
                d.d = SharedPreferencesUtil.getSharedPreferences().getInt("mpaasBaseInfo_DataCacheScheme", 2);
                if (OKLog.D) {
                    OKLog.d("BaseinfoCP", "mDataCacheScheme=" + d.d);
                }
            }
            return d.d;
        }
    }

    /* loaded from: classes4.dex */
    public class b implements IPrivacyInfoCallback {
        b() {
        }

        @Override // com.jingdong.sdk.baseinfo.IPrivacyInfoCallback
        public String getUserPin() {
            return "";
        }

        @Override // com.jingdong.sdk.baseinfo.IPrivacyInfoCallback
        public boolean isNeedRecord(String str, String str2, String str3, long j2) {
            return d.a || d.b;
        }
    }

    /* loaded from: classes4.dex */
    public class c implements IBuildConfigGetter {
        final /* synthetic */ Context a;

        c(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.sdk.baseinfo.IBuildConfigGetter
        public String getAppName() {
            return this.a.getResources().getString(R.string.ao);
        }

        @Override // com.jingdong.sdk.baseinfo.IBuildConfigGetter
        public String getPackageName() {
            return jd.wjlogin_sdk.util.f.f19954c;
        }

        @Override // com.jingdong.sdk.baseinfo.IBuildConfigGetter
        public int getVersionCode() {
            return 98778;
        }

        @Override // com.jingdong.sdk.baseinfo.IBuildConfigGetter
        public String getVersionName() {
            return "12.0.1";
        }
    }

    /* renamed from: com.jingdong.app.mall.utils.d$d */
    /* loaded from: classes4.dex */
    public class C0384d implements IDensityRelateCheck {
        C0384d() {
        }

        @Override // com.jingdong.sdk.baseinfo.IDensityRelateCheck
        public boolean isOriginalCall() {
            return true;
        }
    }

    /* loaded from: classes4.dex */
    public class e implements IBackForegroundCheck {
        e() {
        }

        @Override // com.jingdong.sdk.baseinfo.IBackForegroundCheck
        public boolean isAppForeground() {
            return BackForegroundWatcher.getInstance().isAppInitStateOrForeground();
        }
    }

    /* loaded from: classes4.dex */
    public class f implements IPrivacyCheck {
        final /* synthetic */ Context a;

        f(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.sdk.baseinfo.IPrivacyCheck
        public boolean isUserAgreed() {
            return JDPrivacyHelper.isAcceptPrivacy(this.a);
        }
    }

    /* loaded from: classes4.dex */
    public class g implements JDMoblieConfigListener {
        g() {
        }

        @Override // com.jingdong.app.mall.bundle.mobileConfig.JDMoblieConfigListener
        public void onConfigUpdate() {
            d.n();
            d.o();
            d.j();
        }
    }

    /* loaded from: classes4.dex */
    public class h implements OaidInfoRequestListener {
        h() {
        }

        @Override // com.jd.android.sdk.oaid.OaidInfoRequestListener
        public void onResult(OaidInfo oaidInfo) {
            try {
                JDMtaUtils.setOAID();
                FireEyeUtils.reportOaidCallback();
                OKLog.e("OAID", "startRequestOaidInfo onResult : " + oaidInfo.getOAID());
                com.jingdong.app.mall.log.g.d("OAID", "startRequestOaidInfo onResult : " + oaidInfo.getOAID());
            } catch (Throwable th) {
                OKLog.e("OAID", "startRequestOaidInfo onResult", th);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class i implements ResultCallback<List<PrivacyInfo>> {
        i() {
        }

        @Override // com.jingdong.sdk.baseinfo.ResultCallback
        /* renamed from: a */
        public void onSucceed(List<PrivacyInfo> list) {
            if (list == null || list.size() == 0) {
                return;
            }
            d.r(list);
        }

        @Override // com.jingdong.sdk.baseinfo.ResultCallback
        public void onFailed(Throwable th) {
            Logger.e("BaseInfoHelper", "uploadPrivacyInfo failed", th);
        }
    }

    public static void d(Context context) {
        f11780c = System.currentTimeMillis();
        try {
            BaseInfo.init(new BaseInfoConfig.Builder().setContext(context).setPrivacyCheck(new f(context)).setBackForegroundCheck(new e()).setDensityRelateCheck(new C0384d()).setBuildConfigGetter(new c(context)).setPrivacyInfoCallback(new b()).setDataCacheScheme(new a()).setPrivacyMethodRecordUseDBSwitch(h()).setLocationPermissionCheck(new Supplier() { // from class: com.jingdong.app.mall.utils.a
                @Override // com.jd.android.sdk.coreinfo.util.Supplier
                public final Object get() {
                    Boolean valueOf;
                    valueOf = Boolean.valueOf(PermissionHelper.hasLocationPermissionWithSceneV2("basicShoppingProcess", null) == SceneStatus.HAS_ALL_PERMISSION);
                    return valueOf;
                }
            }).build(), false);
        } catch (Throwable unused) {
        }
    }

    public static /* synthetic */ void f() {
        try {
            q(BaseInfo.getLastTimePrivacyMethodCallRecords());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("BaseInfoHelper", e2);
            }
        }
    }

    public static /* synthetic */ void g() {
        try {
            q(BaseInfo.getPrivacyMethodCallRecordsOnLogout());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("BaseInfoHelper", e2);
            }
        }
    }

    static int h() {
        int i2 = SharedPreferencesUtil.getSharedPreferences().getInt("mpaasBaseInfo_RECORD_SCHEME", 2);
        f11781e = i2;
        return i2;
    }

    public static void i() {
        JDMobileConfig.getInstance().registerListener(new g());
        n();
        o();
        j();
    }

    static void j() {
        String config = JDMobileConfig.getInstance().getConfig("BaseInfo", "switch", "netCallback", "0");
        if (OKLog.D) {
            OKLog.d("BaseInfo.network", "netCallback switch\uff1a " + config);
        }
        if (!"1".equals(config) || f11782f) {
            return;
        }
        f11782f = true;
        BaseInfo.registerNetworkCallback();
    }

    public static void k() {
        try {
            BaseInfo.startRequestOaidInfo(new h());
        } catch (Throwable unused) {
        }
    }

    public static void l(boolean z) {
        a = z;
    }

    public static void m(boolean z) {
        b = z;
    }

    static void n() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("BaseInfo", "switch", "dataCacheScheme", "-1");
            if (OKLog.D) {
                OKLog.d("BaseinfoCP", "dataCacheScheme switch\uff1a " + config);
            }
            if ("-1".equals(config)) {
                return;
            }
            d = Integer.parseInt(config);
            SharedPreferencesUtil.getSharedPreferences().edit().putInt("mpaasBaseInfo_DataCacheScheme", d).apply();
        } catch (Exception e2) {
            OKLog.d("BaseInfoHelper", e2.toString());
        }
    }

    static void o() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("BaseInfo", "switch", "recordScheme", "-1");
            if (OKLog.D) {
                OKLog.d("BaseInfoHelper.record", "recordScheme switch\uff1a " + config);
            }
            int parseInt = Integer.parseInt(config);
            f11781e = parseInt;
            if (parseInt != -1) {
                SharedPreferencesUtil.getSharedPreferences().edit().putInt("mpaasBaseInfo_RECORD_SCHEME", f11781e).apply();
            }
        } catch (Exception e2) {
            OKLog.d("BaseInfoHelper.record", e2.toString());
        }
    }

    public static void p(long j2) {
        BaseInfo.getPrivacyInfosByTimestamp(0L, j2, new i(), false);
    }

    private static void q(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            jSONObject.remove("key");
            jSONObject.remove("pin");
            jSONObject.remove(VerifyTracker.KEY_TIMESTAMP);
        }
        if (jSONArray.length() == 0) {
            Logger.d("BaseInfoHelper.record", "No PrivacyInfo data");
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(JDApp.getInstance(), "PersonalPrivacy_InformationUpload", "", "", "", "", jSONArray.toString(), null);
        Logger.d("BaseInfoHelper.record", "upload:" + jSONArray);
    }

    public static void r(List<PrivacyInfo> list) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        for (PrivacyInfo privacyInfo : list) {
            hashMap.put(privacyInfo.key, privacyInfo.name);
            hashMap2.put(privacyInfo.key, privacyInfo.value);
            if (hashMap3.containsKey(privacyInfo.key)) {
                String str = privacyInfo.key;
                hashMap3.put(str, Integer.valueOf(((Integer) hashMap3.get(str)).intValue() + 1));
            } else {
                hashMap3.put(privacyInfo.key, 1);
            }
        }
        JSONArray jSONArray = new JSONArray();
        for (String str2 : hashMap.keySet()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", hashMap.get(str2));
                jSONObject.put("value", hashMap2.get(str2));
                jSONObject.put("count", String.valueOf(hashMap3.get(str2)));
                jSONArray.put(jSONObject);
                Logger.d("BaseInfoHelper", "upload PrivacyInfo: " + jSONObject.toString());
            } catch (JSONException e2) {
                Logger.e("BaseInfoHelper", "upload PrivacyInfo exception", e2);
            }
        }
        if (jSONArray.length() == 0) {
            Logger.d("BaseInfoHelper", "No PrivacyInfo data");
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(JDApp.getInstance(), "PersonalPrivacy_InformationUpload", "", "", "", "", jSONArray.toString(), null);
        BaseInfo.deletePrivacyInfos((PrivacyInfo[]) list.toArray(new PrivacyInfo[0]), null, false);
    }

    public static void s() {
        try {
            int i2 = f11781e;
            if (i2 == 1) {
                p(f11780c);
            } else if (i2 == 2) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.app.mall.utils.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.f();
                    }
                });
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("BaseInfoHelper", e2);
            }
        }
    }

    public static void t() {
        try {
            int i2 = f11781e;
            if (i2 == 1) {
                p(System.currentTimeMillis());
            } else if (i2 == 2) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.app.mall.utils.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        d.g();
                    }
                });
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("BaseInfoHelper", e2);
            }
        }
    }
}
