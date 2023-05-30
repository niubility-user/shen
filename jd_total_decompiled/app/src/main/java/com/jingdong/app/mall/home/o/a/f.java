package com.jingdong.app.mall.home.o.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.error.JDError;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.activity.MainRightWebActivity;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.business.PermissionBridge;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagepop.JDMessagePopManager;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdma.JDMaInterface;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import jpbury.t;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f {
    private static final String a = "f";
    public static Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public static final boolean f10457c = !JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
    private static final JDLocationCacheOption d;

    /* renamed from: e */
    private static final SharedPreferences f10458e;

    /* renamed from: f */
    private static final i f10459f;

    /* renamed from: g */
    private static final AtomicInteger f10460g;

    /* renamed from: h */
    private static final AtomicInteger f10461h;

    /* renamed from: i */
    private static final AtomicBoolean f10462i;

    /* renamed from: j */
    public static SimpleDateFormat f10463j;

    /* renamed from: k */
    private static final Paint f10464k;

    /* renamed from: l */
    private static final Paint f10465l;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ CharSequence[] f10466g;

        a(CharSequence[] charSequenceArr) {
            this.f10466g = charSequenceArr;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            StringBuilder sb = new StringBuilder();
            sb.append("Debug: ");
            for (CharSequence charSequence : this.f10466g) {
                sb.append(charSequence);
            }
            ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), sb.toString());
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ String f10467g;

        b(String str) {
            this.f10467g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            String userPin = LoginUserBase.getUserPin();
            String uuid = DependUtil.getInstance().getDepend().getUUID();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("devicecode", uuid);
                jSONObject.put("eventid", this.f10467g);
                jSONObject.put("uid", userPin);
                i C = f.C();
                jSONObject.put("lat", C.b());
                jSONObject.put(JDMtaUtils.LON, C.c());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            JMA.report(JdSdk.getInstance().getApplication(), jSONObject);
        }
    }

    /* loaded from: classes4.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ d f10468g;

        /* renamed from: h */
        final /* synthetic */ String f10469h;

        c(d dVar, String str) {
            this.f10468g = dVar;
            this.f10469h = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                onError(new HttpError(new JDError("null Response")));
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (k.w()) {
                fastJsonObject = k.s(this.f10469h + ".txt", httpResponse);
            }
            if (fastJsonObject == null) {
                onError(new HttpError(new JDError("null Data")));
                return;
            }
            d dVar = this.f10468g;
            if (dVar != null) {
                dVar.onEnd(fastJsonObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            d dVar = this.f10468g;
            if (dVar != null) {
                dVar.onError(httpError);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            d dVar = this.f10468g;
            if (dVar != null) {
                dVar.onReady(httpSettingParams);
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface d {
        void onEnd(JDJSONObject jDJSONObject);

        void onError(HttpError httpError);

        void onReady(HttpGroup.HttpSettingParams httpSettingParams);
    }

    static {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        d = jDLocationCacheOption;
        f10458e = JdSdk.getInstance().getApplicationContext().getSharedPreferences("JDHomeSp", 0);
        f10459f = new i();
        f10460g = new AtomicInteger(0);
        jDLocationCacheOption.setBusinessId(PermissionBridge.HOME_COMMON_LBS_ID);
        jDLocationCacheOption.setSceneId("basicShoppingProcess");
        f10461h = new AtomicInteger(0);
        f10462i = new AtomicBoolean(false);
        f10463j = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Paint paint = new Paint();
        f10464k = paint;
        f10465l = new Paint();
        paint.setTextSize(20.0f);
    }

    public static void A(JSONObject jSONObject) {
        e0(jSONObject, z());
    }

    public static void A0(String str, String str2) {
        try {
            f10458e.edit().putString(str, str2).apply();
        } catch (Exception e2) {
            s0(a, e2);
        }
    }

    public static i B() {
        return f10459f;
    }

    public static void B0(@NotNull com.jingdong.app.mall.home.o.a.b bVar) {
        b.removeCallbacks(bVar);
    }

    public static i C() {
        JDLocation location = JDLocationCache.getInstance().getLocation(d);
        return new i(location.getLat(), location.getLng());
    }

    public static void C0(String str, String str2, d dVar) {
        D0(str, JsonParser.parseParamsJsonFromString(str2), dVar);
    }

    public static NavigationButton D(int i2) {
        List<NavigationButton> list;
        NavigationButton navigationButton = null;
        try {
            list = NavigationBase.getInstance().buttons;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            for (NavigationButton navigationButton2 : list) {
                if (navigationButton2 != null && navigationButton2.getNavigationId() == i2) {
                    navigationButton = navigationButton2;
                }
            }
            return navigationButton;
        }
        return null;
    }

    public static void D0(String str, JSONObject jSONObject, d dVar) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId(str);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setListener(new c(dVar, str));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static NavigationButton E(String str) {
        return F(str, "");
    }

    public static void E0(@NotNull com.jingdong.app.mall.home.o.a.b bVar) {
        if (o0()) {
            bVar.run();
        } else {
            b.post(bVar);
        }
    }

    public static NavigationButton F(String str, String str2) {
        return G(str, str2);
    }

    public static void F0(@NotNull com.jingdong.app.mall.home.o.a.b bVar, long j2) {
        b.postDelayed(bVar, j2);
    }

    private static NavigationButton G(String str, String str2) {
        NavigationInfo navigationInfo;
        try {
            List<NavigationButton> list = NavigationBase.getInstance().buttons;
            if (list != null && list.size() > 0) {
                for (NavigationButton navigationButton : list) {
                    if (navigationButton != null && (navigationInfo = navigationButton.getNavigationInfo()) != null && TextUtils.equals(navigationInfo.functionId, str)) {
                        if (!TextUtils.isEmpty(str2)) {
                            if (!TextUtils.equals(str2, NavigationBase.getInstance().mSourceId)) {
                                return null;
                            }
                        }
                        return navigationButton;
                    }
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void G0(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                return;
            }
            EventBus.getDefault().register(obj);
        } catch (Exception e2) {
            s0(a, e2);
        }
    }

    public static SpannableString H(String str) {
        String e2 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(str);
        SpannableString spannableString = new SpannableString(e2);
        if (TextUtils.isEmpty(e2)) {
            return spannableString;
        }
        spannableString.setSpan(new RelativeSizeSpan(0.71f), 0, 1, 17);
        return spannableString;
    }

    public static void H0(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                EventBus.getDefault().unregister(obj);
            }
        } catch (Exception e2) {
            s0(a, e2);
        }
    }

    public static SpannableString I(String str, @FloatRange(from = 0.0d) float f2) {
        String e2 = com.jingdong.app.mall.home.category.floor.feedssub.a.e(str);
        SpannableString spannableString = new SpannableString(e2);
        if (TextUtils.isEmpty(e2)) {
            return spannableString;
        }
        spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 17);
        return spannableString;
    }

    public static void I0(TextView textView, int i2) {
        textView.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(i2));
    }

    public static JSONObject J() {
        try {
            i e2 = g.e();
            if (e2 == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HybridSDK.LNG, e2.c());
            jSONObject.put("lat", e2.b());
            return jSONObject;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static void J0(Context context, Bundle bundle) {
        XView2 xView2;
        ConcurrentHashMap<Integer, XView2> concurrentHashMap = XView2Manager.getInstance().getConcurrentHashMap();
        if (concurrentHashMap == null || concurrentHashMap.size() == 0 || (xView2 = concurrentHashMap.get(Integer.valueOf(System.identityHashCode(context)))) == null) {
            return;
        }
        xView2.dispatchPop(context, bundle);
    }

    public static ViewGroup K() {
        Window G0;
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null || (G0 = z0.G0()) == null) {
            return null;
        }
        View peekDecorView = G0.peekDecorView();
        if (peekDecorView instanceof ViewGroup) {
            return (ViewGroup) peekDecorView;
        }
        return null;
    }

    public static void K0(AtomicBoolean atomicBoolean) {
        if ((atomicBoolean == null || atomicBoolean.get()) && !com.jingdong.app.mall.home.floor.common.i.g.m()) {
            if (atomicBoolean != null) {
                atomicBoolean.set(false);
                AtomicInteger atomicInteger = f10461h;
                if (atomicInteger.get() > 0) {
                    atomicInteger.decrementAndGet();
                }
            }
            String str = a;
            StringBuilder sb = new StringBuilder();
            sb.append("PopMessage____start count= ");
            AtomicInteger atomicInteger2 = f10461h;
            sb.append(atomicInteger2.get());
            sb.append(" isClose= ");
            AtomicBoolean atomicBoolean2 = f10462i;
            sb.append(atomicBoolean2.get());
            r0(str, sb.toString());
            if ((atomicBoolean == null || atomicInteger2.get() <= 0) && atomicBoolean2.get()) {
                atomicBoolean2.set(false);
                r0(str, "PopMessage____start success");
                JDMessagePopManager.getInstance().activePopView();
            }
        }
    }

    public static SharedPreferences L(String str) {
        return JdSdk.getInstance().getApplicationContext().getSharedPreferences(str, 0);
    }

    public static Bundle L0(String str, String str2) {
        JDHomeFragment z0;
        Bundle bundle = new Bundle();
        try {
            z0 = JDHomeFragment.z0();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z0 == null) {
            return bundle;
        }
        BaseActivity baseActivity = z0.thisActivity;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("moduleName", XView2Constants.XVIEW2_POP_EVENT_NAME);
        jSONObject.put("methodName", str);
        jSONObject.put(XView2Constants.LAYER_ID, str2);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(XView2Constants.LAYER_ID, str2);
        jSONObject.put("params", jSONObject2);
        bundle.putString("params", jSONObject.toString());
        JumpUtil.execJumpByDes(JumpUtil.XVIEW2_NXVIEW, baseActivity, bundle);
        return bundle;
    }

    public static int M(String str, int i2) {
        return f10458e.getInt(str, i2);
    }

    public static boolean M0() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static long N(String str, int i2) {
        return f10458e.getLong(str, i2);
    }

    public static String O(String str, String str2) {
        return f10458e.getString(str, str2);
    }

    public static int P() {
        return f10460g.get();
    }

    private static String Q(Object obj) {
        if (obj == null) {
            return DYConstants.DY_NULL_STR;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Class) {
            return ((Class) obj).getSimpleName();
        }
        return obj.getClass().getSimpleName();
    }

    public static float R(int i2, boolean z, CharSequence charSequence) {
        Paint paint = new Paint();
        paint.setTextSize(com.jingdong.app.mall.home.floor.common.d.d(i2));
        paint.setFakeBoldText(z);
        return S(paint, charSequence);
    }

    public static float S(Paint paint, CharSequence charSequence) {
        if (paint == null || TextUtils.isEmpty(charSequence)) {
            return 0.0f;
        }
        return paint.measureText(charSequence, 0, charSequence.length());
    }

    public static float T(TextView textView, CharSequence charSequence) {
        if (textView == null || TextUtils.isEmpty(charSequence)) {
            return 0.0f;
        }
        return S(textView.getPaint(), charSequence);
    }

    public static float U(int i2, String str) {
        Paint paint = f10465l;
        paint.setTextSize(i2);
        return paint.measureText(str);
    }

    public static String V() {
        return f10463j.format(new Date());
    }

    public static Rect W(View view) {
        if (view == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
    }

    public static int X(@DrawableRes int i2) {
        return l0() ? i2 : R.drawable.home_webp_empty;
    }

    public static float Y(String str) {
        Paint paint = f10464k;
        float measureText = paint.measureText("\u5bbd");
        if (TextUtils.isEmpty(str) || measureText <= 0.0f) {
            return 0.0f;
        }
        return paint.measureText(str) / measureText;
    }

    public static void Z(boolean z, Object obj, Object... objArr) {
        if (k.v()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("JdHome: ");
                for (Object obj2 : objArr) {
                    sb.append(obj2);
                }
                "JD_Home_".concat(Q(obj));
                sb.toString();
                if (z) {
                    k.e(sb.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(String str) {
        com.jingdong.app.mall.home.w.a.a(new b(str));
    }

    public static void a0(JSONObject jSONObject) {
        try {
            String str = "1";
            jSONObject.put(AppStateModule.APP_STATE_ACTIVE, JdSdk.getInstance().getApplicationContext().getSharedPreferences("jma_sp_file", 0).getBoolean("1b41a7042ce724d9ecaa5a15fe9fab7a", false) ? "1" : "0");
            if (!MainRightWebActivity.S()) {
                str = "0";
            }
            jSONObject.put("magic", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void b(com.jingdong.app.mall.home.r.c.b bVar) {
        if (bVar == null) {
            bVar = com.jingdong.app.mall.home.r.c.b.c("");
        }
        bVar.a("apitimegap", ((SystemClock.elapsedRealtime() - s.f9357c) / 1000) + "");
    }

    public static void b0(HttpSetting httpSetting) {
        if (httpSetting == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        c0(jSONObject);
        httpSetting.putJsonParam("geo", x(jSONObject));
    }

    public static void c(com.jingdong.app.mall.home.r.c.b bVar, boolean z) {
        if (bVar == null) {
            bVar = com.jingdong.app.mall.home.r.c.b.c("");
        }
        bVar.a("iscache", !z ? "0" : "1");
    }

    public static void c0(JSONObject jSONObject) {
        try {
            JDLocation j2 = g.j();
            if (j2 == null) {
                return;
            }
            jSONObject.put(HybridSDK.LNG, String.valueOf(j2.getLng()));
            jSONObject.put("lat", String.valueOf(j2.getLat()));
            jSONObject.put(VerifyTracker.KEY_TIMESTAMP, j2.getUpdateTime());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void d(com.jingdong.app.mall.home.r.c.b bVar) {
        if (bVar == null) {
            bVar = com.jingdong.app.mall.home.r.c.b.c("");
        }
        bVar.a("homestatus", JDHomeFragment.O0() ? "0" : "1");
    }

    public static void d0(JSONObject jSONObject, i iVar) {
        if (iVar != null) {
            try {
                if (iVar.e()) {
                    return;
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("lat", iVar.b());
                jSONObject2.put(HybridSDK.LNG, iVar.c());
                jSONObject.put("geoNearby", x(jSONObject2));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void e(HttpSetting httpSetting) {
        Activity u0 = JDHomeFragment.u0();
        if (u0 instanceof BaseActivity) {
            ((BaseActivity) u0).getHttpGroupaAsynPool().add(httpSetting);
        } else {
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void e0(JSONObject jSONObject, i iVar) {
        f0(jSONObject, iVar, 0L);
    }

    public static void f() {
        f10460g.incrementAndGet();
    }

    public static void f0(JSONObject jSONObject, i iVar, long j2) {
        if (jSONObject == null || iVar == null) {
            return;
        }
        g0(jSONObject, iVar.b(), iVar.c(), j2);
    }

    public static String g(com.jingdong.app.mall.home.r.f.a.f fVar, float f2, String str) {
        try {
            if (!fVar.Q() || TextUtils.isEmpty(str)) {
                return str;
            }
            String str2 = "0";
            String replace = str.replace("__IS_FRONT_CACHE__", fVar.h0() ? "1" : "0");
            if (!JDHomeFragment.O0()) {
                str2 = "2";
            }
            return replace.replace("__HOME_STATUS__", str2).replace("__EXPOSURE_HEIGHT__", String.valueOf((int) (f2 * 100.0f))).replace("__BROWSE_DURATION__", String.valueOf(SystemClock.elapsedRealtime() - s.f9357c));
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void g0(JSONObject jSONObject, String str, String str2, long j2) {
        try {
            if (TextUtils.equals(str, str2)) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lat", str);
            jSONObject2.put(HybridSDK.LNG, str2);
            if (j2 > 0) {
                jSONObject2.put(VerifyTracker.KEY_TIMESTAMP, j2);
            }
            jSONObject.put("geoReal", x(jSONObject2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String h(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.length() >= 6) {
                if (k.x()) {
                    str = "https://m.360buyimg.com/feedbackcollector/jfs/t1/203393/13/6471/7622/614058ecE6fe83e40/bf03bab2d621bf86.png";
                }
                return str.toLowerCase().endsWith(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) ? str.substring(0, str.length() - 3).concat(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) : str;
            }
            return str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void h0(JSONObject jSONObject) {
        try {
            jSONObject.put("vdj", JDMaInterface.getJdv());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void i() {
        if (f10461h.get() > 0) {
            m(null);
        }
    }

    public static void i0(JSONObject jSONObject) {
        try {
            String str = "1";
            jSONObject.put("locState", n.a() ? "1" : "0");
            if (!n.b()) {
                str = "0";
            }
            jSONObject.put("locSceneState", str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String j(int i2, String str) {
        if (TextUtils.isEmpty(str) || str.length() <= i2) {
            return str;
        }
        Paint paint = f10464k;
        double measureText = paint.measureText("\u5bbd");
        double d2 = i2;
        Double.isNaN(d2);
        Double.isNaN(measureText);
        return k(paint, (int) (measureText * (d2 + 0.5d)), str, 0);
    }

    public static boolean j0() {
        return false;
    }

    private static String k(Paint paint, int i2, String str, int i3) {
        if (i2 <= 10 || i3 > 20 || TextUtils.isEmpty(str)) {
            return "";
        }
        if (paint == null) {
            return str;
        }
        float measureText = paint.measureText(str);
        if (measureText < i2) {
            return str;
        }
        int length = str.length();
        int i4 = length - 1;
        if (i4 <= 0) {
            return "";
        }
        if (measureText > (i2 << 1)) {
            i4 = length - (length >> 2);
        }
        return k(paint, i2, str.substring(0, i4), i3 + 1);
    }

    public static boolean k0() {
        return JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
    }

    public static String l(TextView textView, int i2, String str) {
        return textView == null ? str : k(textView.getPaint(), i2, str, 0);
    }

    public static boolean l0() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public static void m(AtomicBoolean atomicBoolean) {
        if (atomicBoolean != null && !atomicBoolean.get()) {
            atomicBoolean.set(true);
            f10461h.incrementAndGet();
        }
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("PopMessage____close count= ");
        sb.append(f10461h.get());
        sb.append(" isClose= ");
        AtomicBoolean atomicBoolean2 = f10462i;
        sb.append(atomicBoolean2.get());
        r0(str, sb.toString());
        if (atomicBoolean2.get() || !JDHomeFragment.O0()) {
            return;
        }
        atomicBoolean2.set(true);
        r0(str, "PopMessage____close success");
        JDMessagePopManager.getInstance().nonUiDismissPopView();
        JDMessagePopManager.getInstance().shieldActivePopView();
    }

    public static boolean m0(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) || lowerCase.contains(".gif.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T n(Object obj) {
        return obj;
    }

    public static boolean n0() {
        return LoginUserBase.hasLogin() || k.w();
    }

    public static void o(CharSequence... charSequenceArr) {
        if (k.v()) {
            E0(new a(charSequenceArr));
        }
    }

    public static boolean o0() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String p(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.toLowerCase().indexOf(".avif")) == -1) {
            return str;
        }
        return str.substring(0, indexOf) + str.substring(indexOf + 5);
    }

    public static boolean p0() {
        return Looper.myLooper() != Looper.getMainLooper();
    }

    public static void q(String str, String str2, String str3) {
        try {
            HashMap hashMap = new HashMap(8);
            hashMap.put("errCode", "912");
            hashMap.put(PerformanceManager.ERR_TYPE, str);
            hashMap.put("errMsg", str2);
            hashMap.put(t.f20145j, str3);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(a, th);
            }
        }
    }

    public static void q0(Object obj, Throwable th) {
        if (k.v()) {
            "JD_Home_".concat(Q(obj));
            Log.getStackTraceString(th);
        }
    }

    public static View r(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).findViewById(16908290);
        }
        Activity u0 = JDHomeFragment.u0();
        if (u0 != null) {
            return u0.findViewById(16908290);
        }
        return new FrameLayout(context);
    }

    public static void r0(Object obj, Object... objArr) {
        Z(false, obj, objArr);
    }

    public static String s(Intent intent, String str) {
        if (intent == null || TextUtils.isEmpty(str)) {
            return "";
        }
        String stringExtra = intent.getStringExtra(str);
        intent.removeExtra(str);
        return stringExtra;
    }

    public static void s0(Object obj, Throwable th) {
        if (k.v()) {
            "JD_Home_".concat(Q(obj));
            Log.getStackTraceString(th);
        }
    }

    public static int t(String str, int i2) {
        return CommonBase.getJdSharedPreferences().getInt(str, i2);
    }

    public static int t0(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static String u(String str, String str2) {
        return CommonBase.getJdSharedPreferences().getString(str, str2);
    }

    public static void u0(@NotNull com.jingdong.app.mall.home.o.a.b bVar) {
        b.post(bVar);
    }

    private static float v(float f2, int i2, int i3, String str, int i4, Paint paint) {
        if (i4 >= 20 || f2 < i2) {
            return -1.0f;
        }
        paint.setTextSize(f2);
        if (paint.measureText(str) < i3) {
            return f2;
        }
        return v(f2 - 0.5f, i2, i3, str, i4 + 1, paint);
    }

    public static void v0(String str, HttpGroup.HttpTaskListener httpTaskListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setType(6000);
        httpSetting.setEffect(0);
        if (httpTaskListener != null) {
            httpSetting.setListener(httpTaskListener);
        }
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static float w(int i2, int i3, int i4, String str) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        if (i4 <= 10) {
            return -1.0f;
        }
        return v(i2, i3, i4, str, 0, f10465l);
    }

    public static void w0(String str, int i2) {
        CommonBase.getJdSharedPreferences().edit().putInt(str, i2).apply();
    }

    public static String x(JSONObject jSONObject) {
        String str = "";
        try {
            str = com.jingdong.app.mall.home.o.a.a.a(jSONObject.toString());
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static void x0(String str, String str2) {
        CommonBase.getJdSharedPreferences().edit().putString(str, str2).apply();
    }

    public static void y(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            c0(jSONObject2);
            jSONObject.put("geo", x(jSONObject2));
            JSONObject J = J();
            if (J != null) {
                jSONObject.put("receiverGeo", x(J));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void y0(String str, int i2) {
        f10458e.edit().putInt(str, i2).apply();
    }

    public static i z() {
        JDLocation location = JDLocationCache.getInstance().getLocation(d);
        if (System.currentTimeMillis() - location.getUpdateTime() < 600000) {
            f10459f.l(location);
        } else {
            f10459f.h();
        }
        return f10459f;
    }

    public static void z0(String str, long j2) {
        f10458e.edit().putLong(str, j2).apply();
    }
}
