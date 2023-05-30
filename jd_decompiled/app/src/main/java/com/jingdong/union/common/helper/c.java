package com.jingdong.union.common.helper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.union.UnionLoadingActivity;
import com.jingdong.union.a.e;
import com.jingdong.union.a.f;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.common.config.UnionConstants;
import com.jingdong.union.dependency.IJumpSubCallBack;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class c implements com.jingdong.union.dependency.c {
    private String a;
    private String b;

    /* renamed from: c */
    private String f15633c;
    private String d;

    /* renamed from: e */
    private String f15634e;

    /* renamed from: g */
    private Handler f15636g;

    /* renamed from: h */
    private IJumpSubCallBack f15637h;

    /* renamed from: i */
    private Bundle f15638i;

    /* renamed from: k */
    private String f15640k;

    /* renamed from: f */
    private int f15635f = 0;

    /* renamed from: j */
    private boolean f15639j = false;

    /* loaded from: classes12.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ HttpSetting f15641g;

        /* renamed from: h */
        final /* synthetic */ Context f15642h;

        a(HttpSetting httpSetting, Context context) {
            c.this = r1;
            this.f15641g = httpSetting;
            this.f15642h = context;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            f.b("RequestSecoudUrlHelper", "onEnd(), response = " + httpResponse.getString());
            if (httpResponse == null) {
                c cVar = c.this;
                cVar.f(this.f15642h, cVar.a, c.this.b, c.this.f15633c, UnionConstants.STATE_NETERR, "\u63a5\u53e3\u8fd4\u56de\u7684\u6570\u636e\u4e3a\u7a7a");
                return;
            }
            int optInt = httpResponse.getFastJsonObject().optInt("ret", 0);
            if (optInt == 1) {
                c cVar2 = c.this;
                cVar2.f(this.f15642h, cVar2.a, c.this.b, c.this.f15633c, 1, "\u8bf7\u6c42\u6210\u529f");
                return;
            }
            c cVar3 = c.this;
            cVar3.f(this.f15642h, cVar3.a, c.this.b, c.this.f15633c, optInt, "\u63a5\u53e3\u8fd4\u56de\u7684\u5176\u4ed6\u9519\u8bef\uff0cret=" + optInt);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str;
            if (httpError != null) {
                str = httpError.getErrorCode() + ":" + httpError.toString();
            } else {
                str = "\u672a\u77e5\u7f51\u7edc\u9519\u8beferror=null";
            }
            c cVar = c.this;
            cVar.f(this.f15642h, cVar.a, c.this.b, c.this.f15633c, UnionConstants.STATE_NETERR, str);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            f.b("RequestSecoudUrlHelper", "onReady() url = " + this.f15641g.getRequestUrl());
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ Context f15644g;

        /* renamed from: h */
        final /* synthetic */ String f15645h;

        /* renamed from: i */
        final /* synthetic */ String f15646i;

        /* renamed from: j */
        final /* synthetic */ String f15647j;

        /* renamed from: k */
        final /* synthetic */ int f15648k;

        b(Context context, String str, String str2, String str3, int i2) {
            c.this = r1;
            this.f15644g = context;
            this.f15645h = str;
            this.f15646i = str2;
            this.f15647j = str3;
            this.f15648k = i2;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (c.this.f15639j) {
                c.this.n(this.f15644g);
                return;
            }
            if (c.this.f15637h != null) {
                c.this.f15637h.onResult(this.f15644g, this.f15645h, this.f15646i, this.f15647j, this.f15648k);
            }
            c.this.f15639j = true;
            c.this.n(this.f15644g);
        }
    }

    private String b(Context context, Bundle bundle, Map map) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        if (map == null) {
            try {
                map = o(context);
            } catch (UnsupportedEncodingException e2) {
                f.e("RequestSecoudUrlHelper", e2.toString());
            } catch (NullPointerException e3) {
                f.e("RequestSecoudUrlHelper", e3.toString());
            } catch (Exception e4) {
                f.e("RequestSecoudUrlHelper", e4.toString());
            }
        }
        String b2 = com.jingdong.union.a.b.b(map, "H92jik23L#%jd5gN");
        map.put("sign", b2);
        sb2.append(JdUnionBase.getSecoundUrl());
        sb2.append("?");
        for (Map.Entry entry : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (str2 == null) {
                    str2 = "";
                }
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
            }
        }
        return sb2.toString();
    }

    private void d(Context context, Bundle bundle) {
        if (bundle != null) {
            this.a = bundle.getString(UnionConstants.BUNDLE_SKUID, "");
            this.b = bundle.getString("vender_id", "");
            this.f15633c = bundle.getString(UnionConstants.BUNDLE_ACTURL, "");
            this.d = bundle.getString(UnionConstants.BUNDLE_REFER, "");
            this.f15634e = bundle.getString(UnionConstants.BUNDLE_CURRENT, "");
            this.f15635f = bundle.getInt("union_request_timeout", 0);
            Bundle bundle2 = new Bundle(bundle);
            e.c(context, "jingdongunionsdk_1626424295026|4", bundle2, bundle2);
            return;
        }
        Bundle bundle3 = new Bundle();
        bundle3.putString("msg", "getBundleData \u4e2dbundle\u662fnull");
        e.c(context, "jingdongunionsdk_1626424295026|5", bundle3, bundle3);
    }

    public void f(Context context, String str, String str2, String str3, int i2, String str4) {
        e.b(context, this.f15638i, str, str2, str3, this.d, this.f15634e, i2, str4);
        a().post(new b(context, str, str2, str3, i2));
    }

    private Map<String, String> k(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("Activity-Path", this.f15634e);
        hashMap.put("unpl", this.f15640k);
        hashMap.put("jda", JdUnionBase.getBaseAdvertUtils().getJda());
        return hashMap;
    }

    private void l(Context context, Bundle bundle) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        httpSetting.setPost(false);
        httpSetting.setAttempts(1);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setNeedExtraStatisticParam(false);
        int i2 = this.f15635f;
        if (i2 > 0) {
            httpSetting.setCallTimeout(i2);
        }
        httpSetting.setRequestUrl(b(context, bundle, o(context)));
        httpSetting.setHeaderMap(k(context));
        httpSetting.setListener(new a(httpSetting, context));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void n(Context context) {
        if (context instanceof UnionLoadingActivity) {
            UnionLoadingActivity unionLoadingActivity = (UnionLoadingActivity) context;
            if (unionLoadingActivity.isFinishing()) {
                return;
            }
            unionLoadingActivity.finish();
        }
    }

    private Map<String, String> o(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("token", JdUnionBase.getToken());
        hashMap.put("source", "2");
        if (!TextUtils.isEmpty(this.a)) {
            hashMap.put("sku", this.a);
        }
        if (!TextUtils.isEmpty(this.b)) {
            hashMap.put("venderId", this.b);
        }
        if (!TextUtils.isEmpty(this.f15633c)) {
            hashMap.put("actUrl", this.f15633c);
        }
        hashMap.put(UnionConstants.BUNDLE_REFER, this.d);
        String eufv = JdUnionBase.getUuid().getEufv();
        if (!TextUtils.isEmpty(eufv)) {
            hashMap.put("jdUuid", eufv);
            hashMap.put("eufv", "1");
        } else {
            hashMap.put("jdUuid", JdUnionBase.getUuid().getUuid());
        }
        hashMap.put("androidId", JdUnionBase.getAndroidId().getAndroidId());
        return hashMap;
    }

    @Override // com.jingdong.union.dependency.c
    public void a(Context context) {
        if (context == null) {
            context = JdUnionBase.getContext();
        }
        f(context, this.a, this.b, this.f15633c, 0, "\u7528\u6237\u4e3b\u52a8\u53d6\u6d88");
    }

    public void e(Context context, Bundle bundle, IJumpSubCallBack iJumpSubCallBack) {
        this.f15637h = iJumpSubCallBack;
        this.f15638i = bundle;
        if (context == null) {
            context = JdUnionBase.getContext();
        }
        Context context2 = context;
        if (bundle != null && context2 != null) {
            d(context2, bundle);
            String unpl = JdUnionBase.getBaseAdvertUtils().getUnpl();
            this.f15640k = unpl;
            if (TextUtils.isEmpty(unpl)) {
                f(context2, this.a, this.b, this.f15633c, UnionConstants.STATE_UNPLEMPTY, "\u8bf7\u6c42\u63a5\u53e3\u65f6unpl\u4e3a\u7a7a");
                return;
            } else if (TextUtils.isEmpty(this.a) && TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.f15633c)) {
                f(context2, this.a, this.b, this.f15633c, -10000, "skuId\u3001venderId\u3001actUrl \u4e0d\u80fd\u540c\u65f6\u4e3a\u7a7a");
                return;
            } else {
                l(context2, bundle);
                return;
            }
        }
        if (bundle == null) {
            this.f15638i = new Bundle();
        }
        f(context2, this.a, this.b, this.f15633c, -10000, "\u8bf7\u6c42\u63a5\u53e3\u65f6bundle\u6216context\u4e3a\u7a7a");
    }

    private Handler a() {
        if (this.f15636g == null) {
            this.f15636g = new Handler(Looper.getMainLooper());
        }
        return this.f15636g;
    }
}
