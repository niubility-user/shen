package com.jingdong.app.mall.union;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.m;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.kepler.ThirdAppSkuInfo;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.union.common.config.JdUnionBase;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jpbury.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class UnionActivity extends MyActivity {

    /* renamed from: g */
    private String f11662g = LoginUserBase.getUserPin();

    /* renamed from: h */
    private String f11663h = "";

    /* renamed from: i */
    private String f11664i = "9774d56d682e549c";

    /* renamed from: j */
    private String f11665j;

    /* renamed from: k */
    private String f11666k;

    /* renamed from: l */
    private String f11667l;

    /* renamed from: m */
    private String f11668m;

    /* renamed from: n */
    private String f11669n;
    private String o;
    private Bundle p;

    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ HttpSetting f11670g;

        /* renamed from: com.jingdong.app.mall.union.UnionActivity$a$a */
        /* loaded from: classes4.dex */
        class RunnableC0378a implements Runnable {
            RunnableC0378a() {
                a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                DeepLinkCommonHelper.startMainFrameClearAllTask(UnionActivity.this);
                UnionActivity.this.finish();
            }
        }

        /* loaded from: classes4.dex */
        class b implements Runnable {

            /* renamed from: g */
            final /* synthetic */ String f11673g;

            /* renamed from: h */
            final /* synthetic */ JDJSONObject f11674h;

            b(String str, JDJSONObject jDJSONObject) {
                a.this = r1;
                this.f11673g = str;
                this.f11674h = jDJSONObject;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!StringUtil.isEmpty(this.f11673g)) {
                    ThirdAppSkuInfo.getInstance().saveSkuId(this.f11674h.optString("skuId", ""));
                    UnionActivity unionActivity = UnionActivity.this;
                    unionActivity.D(this.f11673g, unionActivity.p);
                } else {
                    DeepLinkCommonHelper.startMainFrameClearAllTask(UnionActivity.this);
                }
                UnionActivity.this.finish();
            }
        }

        a(HttpSetting httpSetting) {
            UnionActivity.this = r1;
            this.f11670g = httpSetting;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str;
            String str2;
            JDJSONObject jDJSONObject;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8 = VerifyTracker.KEY_TIMESTAMP;
            if (httpResponse == null) {
                Bundle bundle = new Bundle();
                if (UnionActivity.this.p != null) {
                    bundle.putAll(UnionActivity.this.p);
                }
                bundle.putString("errCode", "");
                bundle.putString("errMsg", "response == null");
                bundle.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
                JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "jingdongunionsdk_1626424295026|11", UnionActivity.this.C(bundle), LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
            }
            if (Log.D) {
                Log.d("UnionActivity", "onEnd(), response = " + httpResponse.getString());
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            String optString = fastJsonObject.optString("url");
            try {
                str = URLDecoder.decode(optString, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                String decode = URLDecoder.decode(optString);
                e2.printStackTrace();
                str = decode;
            }
            String str9 = "ret";
            int optInt = fastJsonObject.optInt("ret");
            if (Log.D) {
                Log.d("UnionActivity", "responseJson = " + fastJsonObject.toString());
            }
            String str10 = "type";
            if (optInt <= 0) {
                str2 = str;
                jDJSONObject = fastJsonObject;
                UnionActivity.this.Q("UnionDesResponseRetExc", this.f11670g, httpResponse, null);
                Bundle bundle2 = new Bundle();
                if (UnionActivity.this.p != null) {
                    bundle2.putAll(UnionActivity.this.p);
                }
                bundle2.putString("desUrl", optString);
                bundle2.putString("ret", String.valueOf(optInt));
                bundle2.putString("type", "");
                bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
                JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "jingdongunionsdk_1626424295026|12", UnionActivity.this.C(bundle2), LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
            } else {
                String optString2 = fastJsonObject.optString("unpl");
                String optString3 = fastJsonObject.optString("jdv");
                String optString4 = fastJsonObject.optString("jda");
                int optInt2 = fastJsonObject.optInt("type");
                ArrayList T = UnionActivity.this.T(optInt2, str, optString3, optString2);
                if (T.size() > 0) {
                    str2 = str;
                    jDJSONObject = fastJsonObject;
                    int i2 = 0;
                    while (i2 < T.size()) {
                        String str11 = (String) T.get(i2);
                        if (Log.D) {
                            str6 = str8;
                            StringBuilder sb = new StringBuilder();
                            str7 = str10;
                            sb.append("reportExcFunction = ");
                            sb.append(str11);
                            Log.d("UnionActivity", sb.toString());
                        } else {
                            str6 = str8;
                            str7 = str10;
                        }
                        UnionActivity.this.Q(str11, this.f11670g, httpResponse, null);
                        i2++;
                        str8 = str6;
                        str10 = str7;
                        str9 = str9;
                    }
                    str3 = str8;
                    str4 = str9;
                    str5 = str10;
                } else {
                    str2 = str;
                    str3 = VerifyTracker.KEY_TIMESTAMP;
                    jDJSONObject = fastJsonObject;
                    str4 = "ret";
                    str5 = "type";
                    if (Log.D) {
                        Log.d("UnionActivity", "reportExcFunction.size = " + T.size());
                    }
                }
                UnionActivity.this.N(optString4, optString2, optString3, optInt2);
                UnionActivity.this.M(AdvertUtils.getMParam(), UnionActivity.this.G());
                Bundle bundle3 = new Bundle();
                if (UnionActivity.this.p != null) {
                    bundle3.putAll(UnionActivity.this.p);
                }
                bundle3.putString("desUrl", optString);
                bundle3.putString(str4, String.valueOf(optInt));
                bundle3.putString(str5, String.valueOf(optInt2));
                bundle3.putString(str3, String.valueOf(System.currentTimeMillis()));
                JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "jingdongunionsdk_1626424295026|12", UnionActivity.this.C(bundle3), LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle3);
            }
            m.l("jingdongunionsdk_1626424295026|12");
            UnionActivity.this.runOnUiThread(new b(str2, jDJSONObject));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            Bundle bundle = new Bundle();
            if (UnionActivity.this.p != null) {
                bundle.putAll(UnionActivity.this.p);
            }
            if (httpError != null) {
                bundle.putString("errCode", String.valueOf(httpError.getErrorCode()));
                bundle.putString("errCodeStr", httpError.getErrorCodeStr());
                bundle.putString("errMsg", httpError.toString());
            } else {
                bundle.putString("errMsg", "error == null");
            }
            bundle.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "jingdongunionsdk_1626424295026|11", UnionActivity.this.C(bundle), LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
            if (Log.E) {
                Log.e("UnionActivity", "onError() error = " + httpError.toString());
            }
            UnionActivity.this.runOnUiThread(new RunnableC0378a());
            UnionActivity.this.Q("UnionDesRequestErr", this.f11670g, null, httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            if (Log.D) {
                Log.d("UnionActivity", "onReady()");
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Comparator<Map.Entry<String, String>> {
        b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
            return entry.getKey().toString().compareTo(entry2.getKey());
        }
    }

    /* loaded from: classes4.dex */
    public static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.values().length];
            a = iArr;
            try {
                iArr[d.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d.FROM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[d.M_SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[d.UNION_SOURCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum d {
        DEFAULT(0),
        FROM(1),
        M_SOURCE(2),
        UNION_SOURCE(4);
        
        private int typeValue;

        d(int i2) {
            this.typeValue = i2;
        }

        public int getTypeValue() {
            return this.typeValue;
        }
    }

    public String C(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public void D(String str, Bundle bundle) {
        if (Log.D) {
            Log.d("UnionActivity", "dispatchURL desUrl = " + str);
        }
        if (str.startsWith("http")) {
            if (bundle != null && bundle.containsKey(OpenAppJumpController.KEY_OPEN_LINK)) {
                int i2 = bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0);
                JDJSONObject openJsonParam = OpenLinkTimeManager.getInstance().getOpenJsonParam(i2);
                if (openJsonParam != null) {
                    openJsonParam.put("fromSource", (Object) 1);
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
                DeepLinkMHelper.startWebActivity(this, bundle2);
            } else {
                DeepLinkMHelper.startWebActivity(this, str);
            }
        } else {
            DeepLinkDispatch.startActivityDirect(this, str, bundle);
        }
        finish();
    }

    private String E() {
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

    private String F(Map map) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        Map U = map != null ? map : U();
        String H = H(U, "H92jik23L#%jd5gN");
        U.put("sign", H);
        sb2.append(K());
        sb2.append("?");
        for (Map.Entry<String, String> entry : U.entrySet()) {
            if (!StringUtil.isEmpty(entry.getKey())) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    R("UnionNullPointExc", this.f11665j, "{key = " + key + " , map = " + map.toString() + "}");
                    value = "";
                }
                try {
                    if ("sign".equals(H)) {
                        sb2.append(key + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(value, "utf-8"));
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(key);
                        sb3.append(ContainerUtils.KEY_VALUE_DELIMITER);
                        if ("token".equals(key)) {
                            sb = new StringBuilder();
                            sb.append(value);
                            sb.append(ContainerUtils.FIELD_DELIMITER);
                        } else {
                            sb = new StringBuilder();
                            sb.append(URLEncoder.encode(value, "utf-8"));
                            sb.append(ContainerUtils.FIELD_DELIMITER);
                        }
                        sb3.append(sb.toString());
                        sb2.append(sb3.toString());
                    }
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                } catch (NullPointerException e3) {
                    e3.printStackTrace();
                    if (Log.E) {
                        Log.e("UnionActivity", e3.toString());
                    }
                }
            }
        }
        return sb2.toString();
    }

    public String G() {
        return AdvertUtils.getSe();
    }

    public static String H(Map<String, String> map, String str) {
        String str2 = "";
        try {
            ArrayList arrayList = new ArrayList(map.entrySet());
            Collections.sort(arrayList, new b());
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Map.Entry entry = (Map.Entry) arrayList.get(i2);
                if (!StringUtil.isEmpty((CharSequence) entry.getKey())) {
                    String str3 = (String) entry.getKey();
                    String str4 = (String) entry.getValue();
                    if (!StringUtil.isEmpty(str4) && !"seData".equals(str3) && !"sourceExt".equals(str3)) {
                        if (i2 < arrayList.size() - 1) {
                            sb.append(str4 + ContainerUtils.FIELD_DELIMITER);
                        } else {
                            sb.append(str4);
                        }
                    }
                }
            }
            sb.append(str);
            str2 = sb.toString();
            if (Log.D) {
                Log.d("UnionActivity", "before md5 " + str2);
            }
            return Md5Encrypt.md5(str2).toUpperCase();
        } catch (Exception unused) {
            return str2;
        }
    }

    private String I() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("localConfig", "");
            if (!TextUtils.isEmpty(string)) {
                jDJSONObject.put("localConfig", (Object) string);
            }
            String string2 = extras.getString("openappDes", "");
            if (!TextUtils.isEmpty(string2)) {
                jDJSONObject.put("openappDes", (Object) string2);
            }
            String string3 = extras.getString("rnModuleName", "");
            if (!TextUtils.isEmpty(string3)) {
                jDJSONObject.put("rnModuleName", (Object) string3);
            }
            String string4 = extras.getString("packageName", "");
            if (!TextUtils.isEmpty(string4)) {
                jDJSONObject.put("packageName", (Object) string4);
            }
            String string5 = extras.getString("modeTag", "");
            if (!TextUtils.isEmpty(string5)) {
                jDJSONObject.put("modeTag", (Object) string5);
            }
            String string6 = extras.getString(WebPerfManager.PAGE_NAME, "");
            if (!TextUtils.isEmpty(string6)) {
                jDJSONObject.put(WebPerfManager.PAGE_NAME, (Object) string6);
            }
            jDJSONObject.put("mInside", (Object) Integer.valueOf(extras.getBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, true) ? 1 : 2));
            String string7 = extras.getString("bundleId", "");
            if (!TextUtils.isEmpty(string7)) {
                jDJSONObject.put("bundleId", (Object) string7);
            }
            jDJSONObject.put("OAID", (Object) BaseInfo.getOAID());
            String string8 = extras.getString("keyword");
            if (!TextUtils.isEmpty(string8)) {
                jDJSONObject.put("keyword", (Object) string8);
            }
            String string9 = extras.getString("uawakeId");
            this.f11666k = string9;
            if (!StringUtil.isEmpty(string9)) {
                jDJSONObject.put("uawakeId", (Object) this.f11666k);
            }
            return jDJSONObject.isEmpty() ? "" : jDJSONObject.toJSONString();
        }
        return "";
    }

    private String J() {
        return String.valueOf(new Date().getTime() + 28800000);
    }

    private String K() {
        return Configuration.isBeta() ? "https://u.jd.com/api" : "https://union-click.jd.com/api";
    }

    private String L() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("url");
            if (!TextUtils.isEmpty(string)) {
                jDJSONObject.put("url", (Object) string);
            }
            String string2 = extras.getString("from");
            if (!TextUtils.isEmpty(string2)) {
                jDJSONObject.put("from", (Object) string2);
            }
            String string3 = extras.getString(JshopConst.JSHOP_M_SOURCE_FROM);
            if (!TextUtils.isEmpty(string3)) {
                jDJSONObject.put(JshopConst.JSHOP_M_SOURCE_FROM, (Object) string3);
            }
            String string4 = extras.getString("unionSource");
            if (!TextUtils.isEmpty(string4)) {
                jDJSONObject.put("unionSource", (Object) string4);
            }
            String I = I();
            if (!TextUtils.isEmpty(I)) {
                jDJSONObject.put("ext", (Object) I);
            }
            return jDJSONObject.toJSONString();
        }
        return "";
    }

    public void M(String str, String str2) {
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

    public void N(String str, String str2, String str3, int i2) {
        if (i2 == 1) {
            AdvertUtils.initDataNew(AdvertUtils.getSe(), str2, "unionsdk");
        } else if (i2 == 2) {
            AdvertUtils.initDataNew(W(str, str3, str2), null, null, V(str, str3, str2), "unionsdk");
        }
    }

    private void O() {
        if (Log.D) {
            Log.d("UnionActivity", "onReady()");
        }
        m.l("jingdongunionsdk_1626424295026|14");
    }

    private int P(int i2) {
        int i3 = 0;
        while (i2 != 0) {
            i3++;
            i2 &= i2 - 1;
        }
        return i3;
    }

    public void Q(String str, HttpSetting httpSetting, HttpResponse httpResponse, HttpError httpError) {
        if (httpSetting != null) {
            try {
                HashMap hashMap = new HashMap();
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put("function", str);
                }
                String url = httpSetting.getUrl();
                if (!TextUtils.isEmpty(url)) {
                    hashMap.put("url", url);
                }
                hashMap.put("errCode", "959");
                hashMap.put(PerformanceManager.ERR_TYPE, "2");
                if (httpResponse != null) {
                    hashMap.put("httpResp", "" + Integer.valueOf(httpResponse.getCode()));
                    hashMap.put("errMsg", httpResponse.getString());
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject != null) {
                        hashMap.put(t.f20145j, fastJsonObject.toJSONString());
                    }
                }
                if (httpError != null) {
                    hashMap.put("errMsg", httpError.toString());
                    hashMap.put("httpResp", String.valueOf(httpError.getResponseCode()));
                    hashMap.put(PerformanceManager.ERR_TYPE, "1");
                }
                hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                if (Log.D) {
                    Log.d("UnionActivity", "reportRequestDesUrlException:id\uff1a" + httpSetting.getId() + ", data: " + hashMap.toString());
                }
                ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e("UnionActivity", th);
                }
            }
        }
    }

    private void R(String str, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("function", str);
            }
            hashMap.put("errCode", "959");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            if (!TextUtils.isEmpty(str3)) {
                hashMap.put(t.f20145j, str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                hashMap.put("url", str2);
            }
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            if (Log.D) {
                Log.d("UnionActivity", "reportUnionSourceException: data = " + hashMap.toString());
            }
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e("UnionActivity", th);
            }
        }
    }

    private void S() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        httpSetting.setPost(false);
        httpSetting.setAttempts(1);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setNeedExtraStatisticParam(false);
        httpSetting.setTopPriority(true);
        Map<String, String> U = U();
        httpSetting.setRequestUrl(F(U));
        HashMap hashMap = new HashMap();
        String stringBuffer = WebViewHelper.getJdUaInfo2().toString();
        if (!StringUtil.isEmpty(stringBuffer)) {
            hashMap.put("User-Agent", stringBuffer);
        }
        if (!StringUtil.isEmpty(this.o)) {
            hashMap.put("Referer", this.o);
        }
        httpSetting.setHeaderMap(hashMap);
        httpSetting.setSignature(H(U, "H92jik23L#%jd5gN"));
        httpSetting.setListener(new a(httpSetting));
        O();
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public ArrayList T(int i2, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (StringUtil.isEmpty(str)) {
            arrayList.add("UnionDesResponseUrlExc");
        }
        if (i2 == 1) {
            if (StringUtil.isEmpty(str3)) {
                arrayList.add("UnionDesResponseUnplExc");
            }
        } else if (i2 == 2) {
            if (StringUtil.isEmpty(str3)) {
                arrayList.add("UnionDesResponseUnplExc");
            }
            if (StringUtil.isEmpty(str2)) {
                arrayList.add("UnionDesResponseJdvExc");
            }
        }
        return arrayList;
    }

    private Map<String, String> U() {
        HashMap hashMap = new HashMap();
        hashMap.put("token", "96mHpavnh9fTQ8v7ZFmEtQ--");
        hashMap.put("jda", JDMtaUtils.getJda());
        hashMap.put("jdv", E());
        hashMap.put("seData", G());
        hashMap.put("url", this.f11665j);
        hashMap.put("pin", this.f11662g);
        hashMap.put("type", "2");
        hashMap.put("androidId", this.f11664i);
        JDUUIDEncHelper.EncryptResult readEncryptDeviceUUID = StatisticsReportUtil.readEncryptDeviceUUID();
        if (readEncryptDeviceUUID != null && !TextUtils.isEmpty(readEncryptDeviceUUID.eu) && !TextUtils.isEmpty(readEncryptDeviceUUID.fv)) {
            String str = readEncryptDeviceUUID.eu + "-" + readEncryptDeviceUUID.fv;
            this.f11663h = str;
            hashMap.put("jdUuid", str);
            hashMap.put("eufv", "1");
        } else {
            String aid = DeviceInfoHelper.getAid();
            this.f11663h = aid;
            hashMap.put("jdUuid", aid);
        }
        hashMap.put("appVersion", BaseInfo.getAppVersionName() + "");
        hashMap.put("appBuild", BaseInfo.getAppVersionCode() + "");
        hashMap.put(Constants.PARAM_PLATFORM, "5");
        hashMap.put("time", J());
        X(hashMap);
        if (Log.D) {
            Log.d("UnionActivity", "parameMap = " + hashMap.toString());
        }
        return hashMap;
    }

    private String V(String str, String str2, String str3) {
        JSONObject jSONObject;
        String mParam = AdvertUtils.getMParam();
        try {
            if (Log.D) {
                Log.d("UnionActivity", "mparam is :" + mParam);
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
                Log.e("UnionActivity", "MParam get error");
            }
            e2.printStackTrace();
            return mParam;
        }
    }

    private String W(String str, String str2, String str3) {
        JSONObject jSONObject;
        String G = G();
        if (Log.D) {
            Log.d("UnionActivity", "Se is :" + G);
        }
        if (StringUtil.isEmpty(G)) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(G);
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
                Log.e("UnionActivity", "Se update error");
            }
            e2.printStackTrace();
            return G;
        }
    }

    private void X(Map<String, String> map) {
        String str;
        String str2;
        Bundle extras = getIntent().getExtras();
        String str3 = "";
        if (extras != null) {
            String string = extras.getString("from");
            str2 = extras.getString(JshopConst.JSHOP_M_SOURCE_FROM);
            str3 = extras.getString("unionSource");
            str = string;
        } else {
            str = "";
            str2 = str;
        }
        int i2 = 0;
        d dVar = d.DEFAULT;
        if ((!StringUtil.isEmpty(str3)) != false) {
            dVar = d.UNION_SOURCE;
            i2 = 0 | dVar.typeValue;
        }
        if ((!StringUtil.isEmpty(str2)) != false) {
            dVar = d.M_SOURCE;
            i2 |= dVar.typeValue;
        }
        if ((!StringUtil.isEmpty(str)) != false) {
            dVar = d.FROM;
            i2 |= dVar.typeValue;
        }
        if (Log.D) {
            Log.d("UnionActivity", "sourceType = " + i2 + ", currentTYPE = " + dVar.typeValue);
        }
        int P = P(i2);
        if (P >= 2) {
            if (Log.D) {
                Log.d("UnionActivity", "\u5f02\u5e38\u4e0a\u62a5, numOf1 = " + P);
            }
            R("UnionSourceExc", K(), L());
        }
        int i3 = c.a[dVar.ordinal()];
        if (i3 == 1) {
            if (Log.D) {
                Log.d("UnionActivity", "sourceType = Other");
            }
            this.f11667l = "10";
            this.f11668m = "other";
        } else if (i3 == 2) {
            if (Log.D) {
                Log.d("UnionActivity", "sourceType = from");
            }
            this.f11667l = "9";
            this.f11668m = str;
        } else if (i3 == 3) {
            if (Log.D) {
                Log.d("UnionActivity", "sourceType = M_sourceFrom");
            }
            this.f11667l = "8";
            this.f11668m = str2;
        } else if (i3 != 4) {
            this.f11667l = "10";
            this.f11668m = "other";
        } else {
            if (Log.D) {
                Log.d("UnionActivity", "sourceType = unionSource");
            }
            this.f11667l = "7";
            this.f11668m = str3;
        }
        map.put("source", this.f11667l);
        map.put("sourceValue", this.f11668m);
        String I = I();
        if (TextUtils.isEmpty(I)) {
            return;
        }
        this.f11669n = I;
        map.put("sourceExt", I);
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Log.D) {
            Log.d("UnionActivity", "onCreate");
        }
        setContentView(R.layout.union_activity);
        m.l("Union_finishLoadingPage");
        Bundle extras = getIntent().getExtras();
        this.p = extras;
        if (extras != null) {
            this.f11665j = extras.getString("url", "");
            this.o = this.p.getString("ref");
            String C = C(this.p);
            if (Log.D) {
                Log.d("UnionActivity", "bundle.map = " + C);
            }
            JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "Start_UnionMoudleParam_Status", C, LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, this.p);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            bundle2.putString("msg", "UnionActivity\u4e2dbundle\u4e3anull");
            bundle2.putString("unionSdkABTest", "0");
            JdUnionBase.getMtaUtils().sendCommonData(JdSdk.getInstance().getApplication(), "jingdongunionsdk_1626424295026|13", C(bundle2), LangUtils.SINGLE_SPACE, "UnionActivity", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
        }
        this.f11664i = BaseInfo.getAndroidId();
        S();
    }
}
