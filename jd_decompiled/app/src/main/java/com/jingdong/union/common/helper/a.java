package com.jingdong.union.common.helper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.union.UnionLoadingActivity;
import com.jingdong.union.a.f;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.dependency.IAdvertUtils;
import com.jingdong.union.dependency.IJdAdvertUtils;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class a implements com.jingdong.union.dependency.c {
    private String a;
    private String b;

    /* renamed from: c */
    private String f15610c;
    private String d;

    /* renamed from: e */
    private String f15611e;

    /* renamed from: g */
    private Handler f15613g;

    /* renamed from: h */
    private IJumpDispatchCallBack f15614h;

    /* renamed from: j */
    private Bundle f15616j;

    /* renamed from: f */
    private int f15612f = 0;

    /* renamed from: i */
    private boolean f15615i = false;

    /* renamed from: com.jingdong.union.common.helper.a$a */
    /* loaded from: classes12.dex */
    public enum EnumC0750a {
        DEFAULT(0),
        FROM(1),
        M_SOURCE(2),
        UNION_SOURCE(4);
        
        private int a;

        EnumC0750a(int i2) {
            this.a = i2;
        }
    }

    /* loaded from: classes12.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ Bundle f15620g;

        /* renamed from: h */
        final /* synthetic */ Context f15621h;

        /* renamed from: i */
        final /* synthetic */ HttpSetting f15622i;

        b(Bundle bundle, Context context, HttpSetting httpSetting) {
            a.this = r1;
            this.f15620g = bundle;
            this.f15621h = context;
            this.f15622i = httpSetting;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            f.b("JdUnionBase", "onEnd(), response = " + httpResponse.getString());
            a.this.g(this.f15621h, this.f15622i, this.f15620g, httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            Bundle bundle = new Bundle(this.f15620g);
            if (httpError != null) {
                bundle.putString("errCode", String.valueOf(httpError.getErrorCode()));
                bundle.putString("errCodeStr", httpError.getErrorCodeStr());
                bundle.putString("errMsg", httpError.toString());
            } else {
                bundle.putString("errMsg", "error == null");
            }
            bundle.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            String a = com.jingdong.union.a.c.a(bundle);
            f.b("JdUnionBase", "bundle.map = " + a);
            JdUnionBase.getMtaUtils().sendCommonData(this.f15621h, "jingdongunionsdk_1626424295026|1", a, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
            StringBuilder sb = new StringBuilder();
            sb.append("onError() error = ");
            sb.append(httpError);
            f.e("JdUnionBase", sb.toString() != null ? httpError.toString() : "");
            a.this.h(this.f15621h, a.this.a);
            com.jingdong.union.common.helper.d.d("UnionDesRequestErr", this.f15622i, null, httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f15624g;

        /* renamed from: h */
        final /* synthetic */ String f15625h;

        /* renamed from: i */
        final /* synthetic */ String f15626i;

        /* renamed from: j */
        final /* synthetic */ Bundle f15627j;

        /* renamed from: k */
        final /* synthetic */ String f15628k;

        c(Context context, String str, String str2, Bundle bundle, String str3) {
            a.this = r1;
            this.f15624g = context;
            this.f15625h = str;
            this.f15626i = str2;
            this.f15627j = bundle;
            this.f15628k = str3;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (a.this.f15615i) {
                a.this.q(this.f15624g);
                return;
            }
            if (a.this.f15614h != null) {
                a.this.f15614h.onDispatch(this.f15624g, this.f15625h, this.f15626i, this.f15627j, this.f15628k);
            }
            a.this.f15615i = true;
            a.this.q(this.f15624g);
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f15630g;

        /* renamed from: h */
        final /* synthetic */ String f15631h;

        d(Context context, String str) {
            a.this = r1;
            this.f15630g = context;
            this.f15631h = str;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (a.this.f15615i) {
                a.this.q(this.f15630g);
                return;
            }
            if (a.this.f15614h != null) {
                a.this.f15614h.onFaile(this.f15630g, this.f15631h);
            }
            a.this.f15615i = true;
            a.this.q(this.f15630g);
        }
    }

    /* loaded from: classes12.dex */
    public static /* synthetic */ class e {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[EnumC0750a.values().length];
            a = iArr;
            try {
                iArr[EnumC0750a.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EnumC0750a.FROM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EnumC0750a.M_SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EnumC0750a.UNION_SOURCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private int a(int i2) {
        int i3 = 0;
        while (i2 != 0) {
            i3++;
            i2 &= i2 - 1;
        }
        return i3;
    }

    private Handler b() {
        if (this.f15613g == null) {
            this.f15613g = new Handler(Looper.getMainLooper());
        }
        return this.f15613g;
    }

    private String c(Context context, Bundle bundle, Map map) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        if (map == null) {
            map = y(context, bundle);
        }
        String b2 = com.jingdong.union.a.b.b(map, "H92jik23L#%jd5gN");
        map.put("sign", b2);
        sb2.append(JdUnionBase.getFeachUrl());
        sb2.append("?");
        for (Map.Entry entry : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (str2 == null) {
                    com.jingdong.union.common.helper.d.e("UnionNullPointExc", this.a, "{key = " + str + " , map = " + map.toString() + "}");
                    str2 = "";
                }
                try {
                    if ("sign".equals(b2)) {
                        sb2.append(str + ContainerUtils.KEY_VALUE_DELIMITER + URLEncoder.encode(str2, "utf-8"));
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str);
                        sb3.append(ContainerUtils.KEY_VALUE_DELIMITER);
                        if ("token".equals(str)) {
                            sb = new StringBuilder();
                            sb.append(str2);
                        } else {
                            sb = new StringBuilder();
                            sb.append(URLEncoder.encode(str2, "utf-8"));
                        }
                        sb.append(ContainerUtils.FIELD_DELIMITER);
                        sb3.append(sb.toString());
                        sb2.append(sb3.toString());
                    }
                } catch (UnsupportedEncodingException e2) {
                    f.e("JdUnionBase", e2.toString());
                } catch (NullPointerException e3) {
                    f.e("JdUnionBase", e3.toString());
                }
            }
        }
        return sb2.toString();
    }

    private void e(Context context, Bundle bundle) {
        if (bundle != null) {
            this.a = bundle.getString("url", "");
            this.f15611e = bundle.getString("ref");
            this.f15612f = bundle.getInt("union_request_timeout", 0);
            String a = com.jingdong.union.a.c.a(bundle);
            f.b("JdUnionBase", "bundle.map = " + a);
            JdUnionBase.getMtaUtils().sendCommonData(context, "Start_UnionMoudleParam_Status", a, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        bundle2.putString("msg", "RequestUrlHelper\u4e2dbundle\u4e3anull");
        JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|3", com.jingdong.union.a.c.a(bundle2), LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
    }

    public void g(Context context, HttpSetting httpSetting, Bundle bundle, HttpResponse httpResponse) {
        String str;
        String str2;
        String str3;
        int i2;
        String str4;
        if (httpResponse == null) {
            Bundle bundle2 = new Bundle(bundle);
            bundle2.putString("errCode", "");
            bundle2.putString("errMsg", "response == null");
            bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            String a = com.jingdong.union.a.c.a(bundle2);
            f.b("JdUnionBase", "bundle.map = " + a);
            JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|1", a, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle2);
            h(context, this.a);
            return;
        }
        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
        String optString = fastJsonObject.optString("skuId", "");
        String optString2 = fastJsonObject.optString("url");
        try {
            str = URLDecoder.decode(optString2, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            String decode = URLDecoder.decode(optString2);
            e2.printStackTrace();
            str = decode;
        }
        String str5 = "ret";
        int optInt = fastJsonObject.optInt("ret");
        f.b("JdUnionBase", "responseJson = " + fastJsonObject.toString());
        if (optInt > 0) {
            String optString3 = fastJsonObject.optString("unpl");
            String optString4 = fastJsonObject.optString("jdv");
            String optString5 = fastJsonObject.optString("jda");
            int optInt2 = fastJsonObject.optInt("type");
            ArrayList c2 = com.jingdong.union.common.helper.d.c(optInt2, str, optString4, optString3);
            if (c2.size() > 0) {
                str2 = VerifyTracker.KEY_TIMESTAMP;
                str4 = "type";
                int i3 = 0;
                while (i3 < c2.size()) {
                    String str6 = (String) c2.get(i3);
                    f.b("JdUnionBase", "reportExcFunction = " + str6);
                    com.jingdong.union.common.helper.d.d(str6, httpSetting, httpResponse, null);
                    i3++;
                    str5 = str5;
                    optInt = optInt;
                }
                str3 = str5;
                i2 = optInt;
            } else {
                str2 = VerifyTracker.KEY_TIMESTAMP;
                str3 = "ret";
                i2 = optInt;
                str4 = "type";
                f.b("JdUnionBase", "reportExcFunction.size = " + c2.size());
            }
            m(optString5, optString3, optString4, optInt2);
            Bundle bundle3 = new Bundle(bundle);
            bundle3.putString("desUrl", str);
            bundle3.putString(str3, String.valueOf(i2));
            bundle3.putString(str4, String.valueOf(optInt2));
            bundle3.putString(str2, String.valueOf(System.currentTimeMillis()));
            String a2 = com.jingdong.union.a.c.a(bundle3);
            f.b("JdUnionBase", "bundle.map = " + a2);
            JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|2", a2, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle3);
        } else {
            com.jingdong.union.common.helper.d.d("UnionDesResponseRetExc", httpSetting, httpResponse, null);
            Bundle bundle4 = new Bundle(bundle);
            bundle4.putString("desUrl", str);
            bundle4.putString("ret", String.valueOf(optInt));
            bundle4.putString("type", "");
            bundle4.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            String a3 = com.jingdong.union.a.c.a(bundle4);
            f.b("JdUnionBase", "bundle.map = " + a3);
            JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|2", a3, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle4);
        }
        String str7 = this.a;
        if (TextUtils.isEmpty(str)) {
            h(context, str7);
        } else {
            i(context, str7, str, bundle, optString);
        }
    }

    public void h(Context context, String str) {
        b().post(new d(context, str));
    }

    private void i(Context context, String str, String str2, Bundle bundle, String str3) {
        b().post(new c(context, str, str2, bundle, str3));
    }

    private void m(String str, String str2, String str3, int i2) {
        if (JdUnionBase.getBaseAdvertUtils() instanceof IJdAdvertUtils) {
            ((IJdAdvertUtils) JdUnionBase.getBaseAdvertUtils()).setUnplJdaJdv(str2, str, str3, i2);
        } else if (JdUnionBase.getBaseAdvertUtils() instanceof IAdvertUtils) {
            IAdvertUtils iAdvertUtils = (IAdvertUtils) JdUnionBase.getBaseAdvertUtils();
            if (i2 == 1) {
                iAdvertUtils.setSiUnpl(str2);
            } else if (i2 == 2) {
                iAdvertUtils.setUnpl(str2);
            } else {
                f.a("No match type");
            }
            iAdvertUtils.setJdv(str3);
        }
    }

    private String o() {
        return String.valueOf(new Date().getTime() + 28800000);
    }

    private String p(Context context, Bundle bundle) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (bundle != null) {
            String string = bundle.getString("localConfig", "");
            if (!TextUtils.isEmpty(string)) {
                jDJSONObject.put("localConfig", (Object) string);
            }
            String string2 = bundle.getString("openappDes", "");
            if (!TextUtils.isEmpty(string2)) {
                jDJSONObject.put("openappDes", (Object) string2);
            }
            String string3 = bundle.getString("rnModuleName", "");
            if (!TextUtils.isEmpty(string3)) {
                jDJSONObject.put("rnModuleName", (Object) string3);
            }
            String string4 = bundle.getString("packageName", "");
            if (!TextUtils.isEmpty(string4)) {
                jDJSONObject.put("packageName", (Object) string4);
            }
            String string5 = bundle.getString("modeTag", "");
            if (!TextUtils.isEmpty(string5)) {
                jDJSONObject.put("modeTag", (Object) string5);
            }
            String string6 = bundle.getString(WebPerfManager.PAGE_NAME, "");
            if (!TextUtils.isEmpty(string6)) {
                jDJSONObject.put(WebPerfManager.PAGE_NAME, (Object) string6);
            }
            jDJSONObject.put("mInside", (Object) Integer.valueOf(bundle.getBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, true) ? 1 : 2));
            String string7 = bundle.getString("bundleId", "");
            if (!TextUtils.isEmpty(string7)) {
                jDJSONObject.put("bundleId", (Object) string7);
            }
            jDJSONObject.put("OAID", (Object) JdUnionBase.getOaid().getOaid());
            String string8 = bundle.getString("keyword");
            if (!TextUtils.isEmpty(string8)) {
                jDJSONObject.put("keyword", (Object) string8);
            }
            String string9 = bundle.getString("uawakeId");
            this.b = string9;
            if (!TextUtils.isEmpty(string9)) {
                jDJSONObject.put("uawakeId", (Object) this.b);
            }
            return jDJSONObject.isEmpty() ? "" : jDJSONObject.toJSONString();
        }
        return "";
    }

    public void q(Context context) {
        if (context instanceof UnionLoadingActivity) {
            UnionLoadingActivity unionLoadingActivity = (UnionLoadingActivity) context;
            if (unionLoadingActivity.isFinishing()) {
                return;
            }
            unionLoadingActivity.finish();
        }
    }

    private void r(Context context, Bundle bundle, Map<String, String> map) {
        String str;
        String str2;
        if (map == null) {
            return;
        }
        String str3 = "";
        if (bundle != null) {
            String string = bundle.getString("from");
            str = bundle.getString(JshopConst.JSHOP_M_SOURCE_FROM);
            str2 = string;
            str3 = bundle.getString("unionSource");
        } else {
            str = "";
            str2 = str;
        }
        int i2 = 0;
        EnumC0750a enumC0750a = EnumC0750a.DEFAULT;
        if ((!TextUtils.isEmpty(str3)) != false) {
            enumC0750a = EnumC0750a.UNION_SOURCE;
            i2 = 0 | enumC0750a.a;
        }
        if ((!TextUtils.isEmpty(str)) != false) {
            enumC0750a = EnumC0750a.M_SOURCE;
            i2 |= enumC0750a.a;
        }
        if ((!TextUtils.isEmpty(str2)) != false) {
            enumC0750a = EnumC0750a.FROM;
            i2 |= enumC0750a.a;
        }
        f.b("JdUnionBase", "sourceType = " + i2 + ", currentTYPE = " + enumC0750a.a);
        int a = a(i2);
        if (a >= 2) {
            f.b("JdUnionBase", "\u5f02\u5e38\u4e0a\u62a5, numOf1 = " + a);
            com.jingdong.union.common.helper.d.e("UnionSourceExc", JdUnionBase.getFeachUrl(), u(context, bundle));
        }
        int i3 = e.a[enumC0750a.ordinal()];
        if (i3 == 1) {
            f.b("JdUnionBase", "sourceType = Other");
            this.f15610c = "10";
            this.d = "other";
        } else if (i3 == 2) {
            f.b("JdUnionBase", "sourceType = from");
            this.f15610c = "9";
            this.d = str2;
        } else if (i3 == 3) {
            f.b("JdUnionBase", "sourceType = M_sourceFrom");
            this.f15610c = "8";
            this.d = str;
        } else if (i3 != 4) {
            this.f15610c = "10";
            this.d = "other";
        } else {
            f.b("JdUnionBase", "sourceType = unionSource");
            this.f15610c = "7";
            this.d = str3;
        }
        map.put("source", this.f15610c);
        map.put("sourceValue", this.d);
        String p = p(context, bundle);
        if (TextUtils.isEmpty(p)) {
            return;
        }
        map.put("sourceExt", p);
    }

    private String u(Context context, Bundle bundle) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (bundle != null) {
            String string = bundle.getString("url");
            if (!TextUtils.isEmpty(string)) {
                jDJSONObject.put("url", (Object) string);
            }
            String string2 = bundle.getString("from");
            if (!TextUtils.isEmpty(string2)) {
                jDJSONObject.put("from", (Object) string2);
            }
            String string3 = bundle.getString(JshopConst.JSHOP_M_SOURCE_FROM);
            if (!TextUtils.isEmpty(string3)) {
                jDJSONObject.put(JshopConst.JSHOP_M_SOURCE_FROM, (Object) string3);
            }
            String string4 = bundle.getString("unionSource");
            if (!TextUtils.isEmpty(string4)) {
                jDJSONObject.put("unionSource", (Object) string4);
            }
            String p = p(context, bundle);
            if (!TextUtils.isEmpty(p)) {
                jDJSONObject.put("ext", (Object) p);
            }
            return jDJSONObject.toJSONString();
        }
        return "";
    }

    private void v(Context context) {
        String a = com.jingdong.union.a.c.a(this.f15616j);
        f.b("JdUnionBase", "bundle.map = " + a);
        JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|15", a, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, this.f15616j);
    }

    private void w(Context context, Bundle bundle) {
        String a = com.jingdong.union.a.c.a(bundle);
        f.b("JdUnionBase", "bundle.map = " + a);
        JdUnionBase.getMtaUtils().sendCommonData(context, "jingdongunionsdk_1626424295026|14", a, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
    }

    private void x(Context context, Bundle bundle) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        httpSetting.setPost(false);
        httpSetting.setAttempts(1);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setTopPriority(true);
        httpSetting.setNeedExtraStatisticParam(false);
        int i2 = this.f15612f;
        if (i2 > 0) {
            httpSetting.setCallTimeout(i2);
        }
        httpSetting.setRequestUrl(c(context, bundle, y(context, bundle)));
        HashMap hashMap = new HashMap();
        String ua = JdUnionBase.getWebUa().getUa();
        if (!TextUtils.isEmpty(ua)) {
            hashMap.put("User-Agent", ua);
        }
        if (!TextUtils.isEmpty(this.f15611e)) {
            hashMap.put("Referer", this.f15611e);
        }
        f.a("ua: " + ua + ", referer: " + this.f15611e);
        httpSetting.setHeaderMap(hashMap);
        httpSetting.setListener(new b(bundle, context, httpSetting));
        w(context, bundle);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private Map<String, String> y(Context context, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("token", JdUnionBase.getToken());
        hashMap.put("jda", JdUnionBase.getBaseAdvertUtils().getJda());
        hashMap.put("jdv", JdUnionBase.getBaseAdvertUtils().getJdv());
        if (JdUnionBase.getBaseAdvertUtils() instanceof IJdAdvertUtils) {
            hashMap.put("seData", ((IJdAdvertUtils) JdUnionBase.getBaseAdvertUtils()).getSe());
        }
        hashMap.put("url", this.a);
        hashMap.put("pin", JdUnionBase.getLoginUser().getPin());
        hashMap.put("type", "2");
        hashMap.put("androidId", JdUnionBase.getAndroidId().getAndroidId());
        String eufv = JdUnionBase.getUuid().getEufv();
        if (!TextUtils.isEmpty(eufv)) {
            hashMap.put("jdUuid", eufv);
            hashMap.put("eufv", "1");
        } else {
            hashMap.put("jdUuid", JdUnionBase.getUuid().getUuid());
        }
        hashMap.put("appVersion", com.jingdong.union.a.d.c(JdUnionBase.getContext()) + "");
        hashMap.put("appBuild", com.jingdong.union.a.d.b(JdUnionBase.getContext()) + "");
        hashMap.put(Constants.PARAM_PLATFORM, "5");
        hashMap.put("time", o());
        r(context, bundle, hashMap);
        f.b("JdUnionBase", "parameMap = " + hashMap.toString());
        return hashMap;
    }

    @Override // com.jingdong.union.dependency.c
    public void a(Context context) {
        if (context == null) {
            context = JdUnionBase.getContext();
        }
        h(context, "");
        v(JdUnionBase.getContext());
    }

    public void f(Context context, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        this.f15616j = bundle;
        if (iJumpDispatchCallBack == null) {
            this.f15614h = JdUnionBase.getJumpDispatchCallBack();
        } else {
            this.f15614h = iJumpDispatchCallBack;
        }
        if (context == null) {
            context = JdUnionBase.getContext();
        }
        if (context != null && bundle != null) {
            e(context, bundle);
            x(context, bundle);
            return;
        }
        h(JdUnionBase.getContext(), "");
        v(JdUnionBase.getContext());
    }
}
