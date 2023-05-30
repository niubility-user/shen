package com.jingdong.app.mall.home;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class j {
    private static long a;
    private static String b;

    /* renamed from: c */
    private static AtomicBoolean f10307c = new AtomicBoolean(false);

    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {
        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject != null) {
                JDHomeFragment z0 = JDHomeFragment.z0();
                if (z0 != null) {
                    b bVar = new b();
                    JDJSONArray optJSONArray = fastJsonObject.optJSONArray("searchWordList");
                    bVar.a = optJSONArray == null ? null : optJSONArray.toString();
                    JDJSONArray optJSONArray2 = fastJsonObject.optJSONArray("biSearchWordList");
                    bVar.b = optJSONArray2 != null ? optJSONArray2.toString() : null;
                    bVar.f10308c = fastJsonObject.optInt("rollIntervalTime", 0);
                    bVar.d = TextUtils.equals("1", fastJsonObject.optString("dynamicSwitch"));
                    z0.C1(bVar);
                }
                SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                String optString = fastJsonObject.optString("iconType", "1");
                if ("2".equals(optString)) {
                    edit.putString("searchBoxWord_iconImg1", fastJsonObject.optString("smartIconImg", ""));
                    edit.putString("searchBoxWord_iconImg3", fastJsonObject.optString("smartIconImg", ""));
                } else if ("1".equals(optString)) {
                    edit.putString("searchBoxWord_iconImg1", fastJsonObject.optString("photoIconImg", ""));
                    edit.putString("searchBoxWord_iconImg3", fastJsonObject.optString("photoIconImg", ""));
                }
                edit.putInt("searchBoxWordRefreshTime", fastJsonObject.optInt("refreshTime", 600) * 1000);
                edit.putString("searchDeviceId", fastJsonObject.optString(NewFillOrderConstant.RATE));
                edit.putString("iconType", optString);
                edit.putString("searchHistoryType", fastJsonObject.optString("searchHistoryType"));
                edit.apply();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 != null) {
                z0.C1(null);
            }
            long unused = j.a = 0L;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes4.dex */
    public static class b {
        public String a;
        public String b;

        /* renamed from: c */
        public int f10308c;
        public boolean d;
    }

    public static void b() {
        JDHomeFragment z0;
        if (com.jingdong.app.mall.home.o.a.f.j0() && (z0 = JDHomeFragment.z0()) != null) {
            try {
                JDJSONObject h2 = s.h();
                String optString = h2 != null ? h2.optString("xSearchWord", "\u6625\u665a") : "\u6625\u665a";
                if (TextUtils.equals(optString, b)) {
                    return;
                }
                b bVar = new b();
                JDJSONArray jDJSONArray = new JDJSONArray();
                JDJSONObject jDJSONObject = new JDJSONObject();
                b = optString;
                String[] split = TextUtils.split(optString, DYConstants.DY_REGEX_COMMA);
                jDJSONObject.put("isXDef", (Object) Boolean.TRUE);
                jDJSONObject.put("showWord", (Object) split[0]);
                jDJSONObject.put("realWord", (Object) split[split.length - 1]);
                jDJSONArray.add(jDJSONObject);
                bVar.a = jDJSONArray.toString();
                bVar.f10308c = 1000;
                bVar.d = false;
                z0.C1(bVar);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void c() {
        a = System.currentTimeMillis();
        e();
    }

    public static boolean d() {
        if (System.currentTimeMillis() - a >= CommonBase.getIntFromPreference("searchBoxWordRefreshTime", ThemeTitleDataController.DELAY_TIME) || f10307c.get()) {
            a = System.currentTimeMillis();
            e();
            return true;
        }
        return false;
    }

    private static void e() {
        if (com.jingdong.app.mall.home.o.a.f.j0()) {
            a = 0L;
        } else if (!com.jingdong.app.mall.home.o.a.f.k0()) {
            a = 0L;
        } else {
            f10307c.set(false);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setListener(new a());
            httpSetting.setFunctionId("searchBoxWord");
            com.jingdong.app.mall.home.o.a.f.b0(httpSetting);
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setUseFastJsonParser(true);
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void f(SearchWordEntity searchWordEntity, boolean z) {
        String str;
        String str2;
        String str3;
        String str4 = "";
        if (searchWordEntity != null) {
            str4 = searchWordEntity.showWord;
            str2 = searchWordEntity.realWord;
            str3 = searchWordEntity.sourceValue;
            str = searchWordEntity.pvInfo;
        } else {
            str = "";
            str2 = str;
            str3 = str2;
        }
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("hintKeyWord", str4);
        edit.putString("realKeyWord", str2);
        edit.putString("sourceValue", str3);
        edit.putString("searchPvInfo", str);
        edit.apply();
    }

    public static void g() {
        f10307c.set(true);
    }

    public static void h(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putString("searchFrontExpIds", jDJSONObject.optString("frontExpids"));
            edit.apply();
        }
    }
}
