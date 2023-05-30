package com.jingdong.app.mall.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.open.BaseEntryActivity;
import com.jingdong.app.mall.open.InterfaceActivity;
import com.jingdong.app.mall.union.UnionActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.kepler.ThirdAppSkuInfo;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.ui.LottieLoadingView;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.common.config.JdUnionConfig;
import com.jingdong.union.dependency.IAndroidId;
import com.jingdong.union.dependency.IDensity;
import com.jingdong.union.dependency.IJdAdvertUtils;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import com.jingdong.union.dependency.ILoadingView;
import com.jingdong.union.dependency.ILoginUser;
import com.jingdong.union.dependency.IMtaUtils;
import com.jingdong.union.dependency.IOaid;
import com.jingdong.union.dependency.IUnionExceptionReport;
import com.jingdong.union.dependency.IUuid;
import com.jingdong.union.dependency.IWebUa;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class m {
    static List a;
    static String[] b = {"Dra_UnionNet_requestAdd", "Dra_UnionNet_requestSetup", "Dra_UnionNet_requestQueue", "Dra_UnionNet_requestStart", "Dra_UnionNet_callStart", "Dra_UnionNet_dnsStart", "Dra_UnionNet_dnsEnd", "Dra_UnionNet_connectStart", "Dra_UnionNet_secureConnectStart", "Dra_UnionNet_secureConnectEnd", "Dra_UnionNet_connectEnd", "Dra_UnionNet_connectFailed", "Dra_UnionNet_connectionAcquired", "Dra_UnionNet_connectionReleased", "Dra_UnionNet_requestHeadersStart", "Dra_UnionNet_requestHeadersEnd", "Dra_UnionNet_requestBodyStart", "Dra_UnionNet_requestBodyEnd", "Dra_UnionNet_responseHeadersStart", "Dra_UnionNet_responseHeadersEnd", "Dra_UnionNet_responseBodyStart", "Dra_UnionNet_responseBodyEnd", "Dra_UnionNet_callEnd", "Dra_UnionNet_callFailed", "Dra_UnionNet_requestComplete", "Dra_UnionNet_requestFinish"};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements IDensity {
        a() {
        }

        @Override // com.jingdong.union.dependency.IDensity
        public float getDensity() {
            return BaseInfo.getDensity();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements IAndroidId {
        b() {
        }

        @Override // com.jingdong.union.dependency.IAndroidId
        public String getAndroidId() {
            return BaseInfo.getAndroidId();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements IJumpDispatchCallBack {
        c() {
        }

        @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
        public void onDispatch(Context context, String str, String str2, Bundle bundle, String str3) {
            if (!StringUtil.isEmpty(str2)) {
                ThirdAppSkuInfo.getInstance().saveSkuId(str3);
                m.b(context, str2, bundle);
            } else {
                m.o(context);
            }
            m.c(context);
        }

        @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
        public void onFaile(Context context, String str) {
            m.o(context);
            m.c(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements IUnionExceptionReport {
        d() {
        }

        @Override // com.jingdong.union.dependency.IUnionExceptionReport
        public void report(Context context, HashMap<String, String> hashMap) {
            ExceptionReporter.sendExceptionData(context, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements IMtaUtils {
        e() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.jingdong.union.dependency.IMtaUtils
        public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, Bundle bundle) {
            String str7;
            JDJSONObject parseObject;
            String name;
            try {
                if (m.l(str)) {
                    return;
                }
            } catch (Throwable unused) {
            }
            String jsonParamFromBundle = JDUnionUtils.getJsonParamFromBundle(bundle);
            if (obj != null) {
                if (obj instanceof String) {
                    name = (String) obj;
                } else {
                    name = obj.getClass().getName();
                }
                str7 = name;
            } else {
                str7 = "";
            }
            JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, "", str7, str4, str6, jsonParamFromBundle, null);
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1316151967:
                    if (str.equals("jingdongunionsdk_1626424295026|11")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1316151966:
                    if (str.equals("jingdongunionsdk_1626424295026|12")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1316151965:
                    if (str.equals("jingdongunionsdk_1626424295026|13")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -920918496:
                    if (str.equals("Start_UnionMoudleParam_Status")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 650280144:
                    if (str.equals("jingdongunionsdk_1626424295026|1")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 650280145:
                    if (str.equals("jingdongunionsdk_1626424295026|2")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 650280146:
                    if (str.equals("jingdongunionsdk_1626424295026|3")) {
                        c2 = 6;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                case 4:
                case 5:
                case 6:
                    JDUnionUtils.setTagToSp(context, "");
                    return;
                case 3:
                    try {
                        if (TextUtils.isEmpty(str2) || (parseObject = JDJSON.parseObject(str2)) == null) {
                            return;
                        }
                        parseObject.put("testUnionStatus", (Object) str);
                        JDUnionUtils.setTagToSp(context, parseObject.toString());
                        return;
                    } catch (Exception unused2) {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements ILoginUser {
        f() {
        }

        @Override // com.jingdong.union.dependency.ILoginUser
        public String getPin() {
            return UserUtil.getWJLoginHelper().getPin();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g implements IJdAdvertUtils {
        g() {
        }

        @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
        public String getJda() {
            return JDMtaUtils.getJda();
        }

        @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
        public String getJdv() {
            return m.d();
        }

        @Override // com.jingdong.union.dependency.IJdAdvertUtils
        public String getSe() {
            return AdvertUtils.getSe();
        }

        @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
        public String getUnpl() {
            return JDMtaUtils.getUnpl();
        }

        @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
        public void setJda(String str) {
        }

        @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
        public void setJdv(String str) {
        }

        @Override // com.jingdong.union.dependency.IJdAdvertUtils
        public void setUnplJdaJdv(String str, String str2, String str3, int i2) {
            if (i2 == 1) {
                AdvertUtils.initDataNew(AdvertUtils.getSe(), str, "unionsdk");
            } else if (i2 == 2) {
                AdvertUtils.initDataNew(m.r(str2, str3, str), null, null, m.q(str2, str3, str), "unionsdk");
            }
            m.f(AdvertUtils.getMParam(), getSe());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class h implements IWebUa {
        h() {
        }

        @Override // com.jingdong.union.dependency.IWebUa
        public String getUa() {
            return WebViewHelper.getJdUaInfo2().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class i implements IOaid {
        i() {
        }

        @Override // com.jingdong.union.dependency.IOaid
        public String getOaid() {
            return BaseInfo.getOAID();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j implements ILoadingView {
        j() {
        }

        @Override // com.jingdong.union.dependency.ILoadingView
        public View getLoadingView(Context context) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16 && i2 != 26 && i2 != 27) {
                return new LottieLoadingView(context);
            }
            return new JDProgressBar(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class k implements IUuid {
        k() {
        }

        @Override // com.jingdong.union.dependency.IUuid
        public String getEufv() {
            JDUUIDEncHelper.EncryptResult readEncryptDeviceUUID = StatisticsReportUtil.readEncryptDeviceUUID();
            if (readEncryptDeviceUUID == null || TextUtils.isEmpty(readEncryptDeviceUUID.eu) || TextUtils.isEmpty(readEncryptDeviceUUID.fv)) {
                return "";
            }
            return readEncryptDeviceUUID.eu + "-" + readEncryptDeviceUUID.fv;
        }

        @Override // com.jingdong.union.dependency.IUuid
        public String getUuid() {
            return DeviceInfoHelper.getAid();
        }
    }

    private static void a(String str) {
        OpenLinkTimeManager.getInstance().addExtraTiming(str, System.currentTimeMillis());
    }

    public static void b(Context context, String str, Bundle bundle) {
        if (Log.D) {
            Log.d("UnionUtils", "dispatchURL desUrl = " + str);
        }
        if (context == null) {
            context = JdSdk.getInstance().getApplicationContext();
        }
        if (str.startsWith("http")) {
            if (bundle != null && bundle.containsKey(OpenAppJumpController.KEY_OPEN_LINK)) {
                int i2 = bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0);
                JDJSONObject openJsonParam = OpenLinkTimeManager.getInstance().getOpenJsonParam(i2);
                if (openJsonParam != null) {
                    openJsonParam.put("fromSource", (Object) "1");
                }
                URLParamMap uRLParamMap = new URLParamMap();
                uRLParamMap.put(RemoteMessageConst.TO, str);
                Bundle bundle2 = new Bundle();
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                bundle2.putSerializable("urlParamMap", serializableContainer);
                bundle2.putString("urlAction", RemoteMessageConst.TO);
                bundle2.putBoolean(OpenAppJumpController.KEY_OPEN_LINK, bundle.getBoolean(OpenAppJumpController.KEY_OPEN_LINK));
                bundle2.putInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, i2);
                if (context == null) {
                    context = JdSdk.getInstance().getApplicationContext();
                }
                DeepLinkMHelper.startWebActivity(context, bundle2);
                return;
            }
            DeepLinkMHelper.startWebActivity(context, str);
            return;
        }
        DeepLinkDispatch.startActivityDirect(context, str, bundle);
    }

    protected static void c(Context context) {
        if (context instanceof BaseEntryActivity) {
            ((Activity) context).finish();
        }
    }

    public static String d() {
        if (StringUtil.isEmpty(AdvertUtils.getMParam())) {
            return "";
        }
        try {
            return new JSONObject(AdvertUtils.getMParam()).getString("jdv");
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void e(Context context) {
        JdUnionBase.init(new JdUnionConfig.Builder().setContext(context).setToken("96mHpavnh9fTQ8v7ZFmEtQ--").setLog(false).setAndroidId(new b()).setDensity(new a()).setUuid(new k()).setiLoadingView(new j()).setOaId(new i()).setiWebUa(new h()).setiJdAdvertUtils(new g()).setiLoginUser(new f()).setiMtaUtils(new e()).setiUnionExceptionReport(new d()).setiJumpDispatchCallBack(new c()).build(), false);
        p();
    }

    public static void f(String str, String str2) {
        String str3 = null;
        try {
            if (!StringUtil.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("jdv")) {
                    str3 = jSONObject.getString("jdv");
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            if (!StringUtil.isEmpty(str2)) {
                JSONObject jSONObject2 = new JSONObject(str2);
                if (StringUtil.isEmpty(str3) && jSONObject2.has("__jdv")) {
                    str3 = jSONObject2.getString("__jdv");
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        if (StringUtil.isEmpty(str3)) {
            return;
        }
        JDMtaUtils.sendJdvInfo(str3);
    }

    public static void g(Context context, Bundle bundle) {
        JDReactModuleEntity reactEntity;
        int i2 = 0;
        OpenLinkTimeManager.getInstance().enterUnion(bundle != null ? bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0) : 0);
        String config = JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionSdkABTest", "useUnionSdk", "0");
        boolean z = OpenLinkTimeManager.getInstance().isCold() && (context instanceof InterfaceActivity) && TextUtils.equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionNoLoadingABTest", "coldNoLoading", "0"), "1") && TextUtils.equals(config, "1");
        if (bundle != null) {
            if (z) {
                bundle.putBoolean(OpenAppConstant.KEY_CLOSEACTIVITY, false);
            } else {
                bundle.putBoolean(OpenAppConstant.KEY_CLOSEACTIVITY, true);
            }
            n(z ? "1" : "0");
            bundle.putString("unionSdkABTest", config);
            bundle.putString("UnionNoLoadingAbTest", z ? "1" : "0");
            String string = bundle.getString(MBaseKeyNames.KEY_REFERER, "");
            if (!StringUtil.isEmpty(string)) {
                bundle.putString("ref", string.trim());
            }
            String string2 = bundle.getString(OpenAppConstant.FLAG_UserActivity, "");
            if (!StringUtil.isEmpty(string2)) {
                bundle.putString("bundleId", string2);
            }
            if (bundle.containsKey(OpenAppConstant.FLAG_UserActivity) && OpenAppConstant.KEY_OuterApp.equals(bundle.getString(OpenAppConstant.FLAG_UserActivity))) {
                bundle.putBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, false);
            }
            if (bundle.containsKey("openAppActivityReferer")) {
                bundle.putString("packageName", bundle.getString("openAppActivityReferer"));
            }
            if (context != null && context.getClass() != null) {
                bundle.putString(WebPerfManager.PAGE_NAME, context.getClass().getName());
            }
            bundle.putString("modeTag", JDUnionUtils.getCurrentModeTag());
            if (!TextUtils.isEmpty(bundle.getString("packageName", ""))) {
                bundle.putBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, false);
            }
            bundle.putString("localConfig", BaseInfo.isRoot() ? "1" : "0");
            if (bundle.containsKey("category") && bundle.containsKey("des")) {
                bundle.putString("openappDes", bundle.getString("des", ""));
            }
            if ((context instanceof JDReactNativeBaseCommonActivity) && (reactEntity = ((JDReactNativeBaseCommonActivity) context).getReactEntity()) != null) {
                bundle.putString("rnModuleName", reactEntity.getmModuleName());
            }
            if ("0".equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionFilter", "isReferAvailble", "1")) && bundle.containsKey("ref")) {
                bundle.remove("ref");
            }
        }
        m(config);
        if ("0".equals(config)) {
            l("Union_startLoadingPage");
            if (context == null) {
                Context applicationContext = JdSdk.getInstance().getApplicationContext();
                Intent intent = new Intent(applicationContext, UnionActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(268435456);
                applicationContext.startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(context, UnionActivity.class);
            intent2.putExtras(bundle);
            context.startActivity(intent2);
            return;
        }
        if (!JdUnionBase.hasInited()) {
            e(JdSdk.getInstance().getApplicationContext());
        }
        if (!JdUnionBase.hasInited()) {
            o(context);
            c(context);
        } else if (z) {
            String config2 = JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionNoLoadingABTest", "waitTimeout", "0");
            if (!TextUtils.equals(config2, "0")) {
                try {
                    int parseInt = Integer.parseInt(config2);
                    i2 = 500;
                    if (parseInt <= 0 || parseInt >= 500) {
                        i2 = parseInt;
                    }
                } catch (Exception unused) {
                }
            }
            JDUnionUtils.jumpUnionNoLoading(context, bundle, i2, null);
        } else {
            JDUnionUtils.jumpUnion(context, bundle);
            c(context);
        }
    }

    public static void h() {
        a("UnionLoadingCreated");
    }

    public static void i() {
        a("UnionLoadingStart");
    }

    public static void j() {
        a("UnionNetReady");
    }

    public static void k() {
        a("UnionNetSucc");
    }

    public static boolean l(String str) {
        List list = a;
        if (list == null || list.size() <= 0) {
            a = Arrays.asList(b);
        }
        if (a.contains(str)) {
            a(str);
            return true;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1316151966:
                if (str.equals("jingdongunionsdk_1626424295026|12")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1316151964:
                if (str.equals("jingdongunionsdk_1626424295026|14")) {
                    c2 = 1;
                    break;
                }
                break;
            case -687139873:
                if (str.equals("Dra_Union_ThreadMain")) {
                    c2 = 2;
                    break;
                }
                break;
            case -561123896:
                if (str.equals("Union_finishLoadingPage")) {
                    c2 = 3;
                    break;
                }
                break;
            case 164473782:
                if (str.equals("Dra_Union_ThreadChild")) {
                    c2 = 4;
                    break;
                }
                break;
            case 650280145:
                if (str.equals("jingdongunionsdk_1626424295026|2")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1367587769:
                if (str.equals("Union_startLoadingPage")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 5:
                k();
                return false;
            case 1:
                j();
                return false;
            case 2:
                a("Dra_Union_ThreadMain");
                return true;
            case 3:
                h();
                return true;
            case 4:
                a("Dra_Union_ThreadChild");
                return true;
            case 6:
                i();
                return true;
            default:
                return false;
        }
    }

    public static void m(String str) {
        OpenLinkTimeManager.getInstance().addJsonParam("UnionSdkABTest", str);
    }

    public static void n(String str) {
        OpenLinkTimeManager.getInstance().addJsonParam("UnionNoLoadingAbTest", str);
    }

    public static void o(Context context) {
        if (context == null) {
            context = JdSdk.getInstance().getApplication();
        }
        DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY).toString(), (Bundle) null, 67108864);
    }

    public static void p() {
        a("UnionSdkInited");
    }

    public static String q(String str, String str2, String str3) {
        JSONObject jSONObject;
        String mParam = AdvertUtils.getMParam();
        try {
            if (Log.D) {
                Log.d("UnionUtils", "mparam is :" + mParam);
            }
            if (StringUtil.isEmpty(mParam)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(mParam);
            }
            if (!StringUtil.isEmpty(str3)) {
                jSONObject.put("unpl", str3);
            }
            if (!StringUtil.isEmpty(str2)) {
                jSONObject.put("jdv", str2);
            }
            if (!StringUtil.isEmpty(str)) {
                jSONObject.put("jda", str);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            if (Log.E) {
                Log.e("UnionUtils", "MParam get error");
            }
            e2.printStackTrace();
            return mParam;
        }
    }

    public static String r(String str, String str2, String str3) {
        JSONObject jSONObject;
        String se = AdvertUtils.getSe();
        if (Log.D) {
            Log.d("UnionUtils", "Se is :" + se);
        }
        if (StringUtil.isEmpty(se)) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(se);
            } catch (JSONException unused) {
                jSONObject = new JSONObject();
            }
        }
        try {
            if (!StringUtil.isEmpty(str3)) {
                jSONObject.put("unpl", str3);
            }
            if (!StringUtil.isEmpty(str2)) {
                jSONObject.put("__jdv", str2);
            }
            if (!StringUtil.isEmpty(str)) {
                jSONObject.put("__jda", str);
            }
            return jSONObject.toString();
        } catch (JSONException e2) {
            if (Log.E) {
                Log.e("UnionUtils", "Se update error");
            }
            e2.printStackTrace();
            return se;
        }
    }
}
