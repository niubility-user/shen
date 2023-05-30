package com.jingdong.app.mall.ad;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONArray;

/* loaded from: classes19.dex */
public class c {
    private static volatile c d;
    private volatile d a = null;
    private String b = "";

    /* renamed from: c  reason: collision with root package name */
    private HashMap<String, String> f7899c = new HashMap<>(8);

    /* loaded from: classes19.dex */
    class a extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ InterfaceC0236c a;

        a(c cVar, InterfaceC0236c interfaceC0236c) {
            this.a = interfaceC0236c;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            InterfaceC0236c interfaceC0236c = this.a;
            if (interfaceC0236c != null) {
                interfaceC0236c.a(bitmap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f7900g;

        /* loaded from: classes19.dex */
        class a implements JDImageLoadingListener {
            a(b bVar) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
                if (OKLog.D) {
                    f.r0("AdObserver", "onLoadingCancelled => url: " + str);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (OKLog.D) {
                    f.r0("AdObserver", "onLoadingComplete => url: " + str);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                if (OKLog.D) {
                    f.r0("AdObserver", "onLoadingFailed => url: " + str);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        }

        /* renamed from: com.jingdong.app.mall.ad.c$b$b  reason: collision with other inner class name */
        /* loaded from: classes19.dex */
        class C0235b extends com.jingdong.app.mall.home.o.a.b {
            C0235b() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                c.this.a = null;
            }
        }

        b(long j2) {
            this.f7900g = j2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            com.jingdong.app.mall.ad.a.b = SystemClock.elapsedRealtime() - this.f7900g;
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (Log.D) {
                Log.d("AdObserver", "downloadStartImageByHttp " + fastJsonObject.toString());
            }
            if (fastJsonObject.optInt("code", -1) != 0) {
                return;
            }
            f.y0("start_show_times_daily", fastJsonObject.optInt("showTimesDaily"));
            JDJSONArray optJSONArray = fastJsonObject.optJSONArray("images");
            if (optJSONArray != null && optJSONArray.size() >= 1) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int size = optJSONArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    JDJSONArray optJSONArray2 = optJSONArray.optJSONArray(i2);
                    if (optJSONArray2 == null || optJSONArray2.size() == 0) {
                        return;
                    }
                    int size2 = optJSONArray2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        JDJSONObject optJSONObject = optJSONArray2.optJSONObject(i3);
                        if (optJSONObject == null || optJSONObject.size() == 0) {
                            return;
                        }
                        String optString = optJSONObject.optString("url", "");
                        if (!TextUtils.isEmpty(optString)) {
                            arrayList.add(optString);
                            if (com.jingdong.app.mall.ad.a.d(optString)) {
                                com.jingdong.app.mall.ad.a.j(optString);
                            }
                        }
                        String optString2 = optJSONObject.optString("videoId", "");
                        String optString3 = optJSONObject.optString("videoUrl", "");
                        if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3)) {
                            arrayList2.add(optString2);
                            if (com.jingdong.app.mall.ad.a.e(optString2, optString3) && NetUtils.isWifi()) {
                                com.jingdong.app.mall.ad.a.k(optString2, optString3);
                            }
                        }
                        e.z(optJSONObject.optString("buttonImg"), new a(this));
                    }
                }
                String optString4 = fastJsonObject.optString("saoasaoUrl");
                if (!TextUtils.isEmpty(optString4)) {
                    c.this.s(optString4);
                    c.this.u(optString4, fastJsonObject.optInt("saoasaoSwitch"), fastJsonObject.optInt("saoasaoLocation", 1));
                } else {
                    c.this.u("", 0, 1);
                }
                c.this.w(fastJsonObject.optString("ynDenoise", "0"));
                com.jingdong.app.mall.ad.a.f(arrayList, arrayList2);
                c.this.v(optJSONArray.toString(), fastJsonObject.optInt(DYConstants.DY_COUNTDOWN, 0));
                f.E0(new C0235b());
                return;
            }
            com.jingdong.app.mall.ad.a.f(new ArrayList(), new ArrayList());
            c.this.v("", 0);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (Log.D) {
                Log.d("AdObserver", "downloadStartImageByHttp onError!");
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* renamed from: com.jingdong.app.mall.ad.c$c  reason: collision with other inner class name */
    /* loaded from: classes19.dex */
    public interface InterfaceC0236c {
        void a(Bitmap bitmap);
    }

    public static void f() {
        if (d != null) {
            d.g();
            d = null;
        }
    }

    private void g() {
        this.a = null;
    }

    public static synchronized c l() {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c();
            }
            cVar = d;
        }
        return cVar;
    }

    private boolean o(d dVar) {
        if (dVar != null) {
            if (CommonBase.getIntFromPreference("noise_reduction" + dVar.s, 0) == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean p(d dVar) {
        return CommonBase.getIntFromPreference("key_occupy_id", 0) == dVar.f7904e && CommonBase.getIntFromPreference("occupy_times", 0) >= dVar.f7905f;
    }

    private boolean q(d dVar) {
        if (dVar == null || dVar.f7905f < 1) {
            return true;
        }
        int M = f.M("start_show_times_daily", 0);
        if (M <= 0 || com.jingdong.app.mall.home.floor.view.b.f.d.f("start_image_all_times", M)) {
            boolean z = CommonBase.getIntFromPreference("start_image_show_id", 0) == dVar.f7904e && CommonBase.getIntFromPreference("start_image_show_times", 0) >= dVar.f7905f;
            StringBuilder sb = new StringBuilder();
            sb.append("start_image_show_id_");
            sb.append(dVar.f7904e);
            return z || (com.jingdong.app.mall.home.floor.view.b.f.d.f(sb.toString(), dVar.f7905f) ^ true);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String r(JDJSONArray jDJSONArray) {
        int i2;
        boolean z;
        Log.d("parseStartExpoJson", "\u5f00\u59cb\u89e3\u6790");
        if (jDJSONArray == null || jDJSONArray.size() == 0 || this.a == null || this.a.f7910k == null) {
            return "";
        }
        int i3 = 0;
        com.jingdong.app.mall.ad.b bVar = this.a.f7910k.get(0);
        if (bVar == null) {
            return "";
        }
        Date date = new Date();
        JSONArray d2 = com.jingdong.app.mall.home.r.c.b.d();
        int size = jDJSONArray.size();
        Date date2 = null;
        Date date3 = null;
        int i4 = 0;
        while (i4 < size) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONArray(i4).optJSONObject(i3);
            String optString = optJSONObject.optString("onlineTime", "");
            String optString2 = optJSONObject.optString("referralsTime", "");
            int i5 = (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) ? -1 : 1;
            com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(optJSONObject.optString("sourceValueJson"));
            try {
                SimpleDateFormat simpleDateFormat = d.D;
                date2 = simpleDateFormat.parse(optString);
                date3 = simpleDateFormat.parse(optString2);
                i2 = i5;
            } catch (ParseException e2) {
                e2.printStackTrace();
                i2 = -1;
            }
            if (date2 == null || date2.after(date) || date3 == null || date3.before(date)) {
                i2 = 2;
            }
            if (i2 == 1) {
                int optInt = optJSONObject.optInt("groupId", 0);
                String optString3 = optJSONObject.optString("url", "");
                if (optInt == this.a.f7904e && optString3.equals(bVar.f7898c)) {
                    z = true;
                    c2.a("day", String.valueOf(i2));
                    c2.a("isshow", !z ? "1" : "0");
                    d2.put(c2);
                    i4++;
                    i3 = 0;
                }
            }
            z = false;
            c2.a("day", String.valueOf(i2));
            c2.a("isshow", !z ? "1" : "0");
            d2.put(c2);
            i4++;
            i3 = 0;
        }
        Log.d("getStartExpoJson", d2.toString());
        return d2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setAttempts(2);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setNeedShareImage(false);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    private void t(int i2, String str, String str2) {
        if (CommonBase.getIntFromPreference(str, 0) == i2) {
            f.w0(str2, CommonBase.getIntFromPreference(str2, 0) + 1);
            return;
        }
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt(str, i2);
        edit.putInt(str2, 1);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(String str, int i2, int i3) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt("start_image_show_scan", i2);
        edit.putString("start_image_scan_url", str);
        edit.putInt("start_image_saoasao_location", i3);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(String str, int i2) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putInt("start_image_countdown", i2);
        edit.putString("start_image_json", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(String str) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("start_image_ynDenoise", str);
        edit.apply();
    }

    public void h() {
        if (!f.j0() && f.k0()) {
            com.jingdong.app.mall.ad.a.a = SystemClock.elapsedRealtime() - com.jingdong.app.mall.c.b();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setFunctionId("start");
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setEffect(0);
            httpSetting.putJsonParam("appType", Integer.valueOf(com.jingdong.app.mall.home.state.old.a.d()));
            httpSetting.putJsonParam("homeAreaCode", String.valueOf(com.jingdong.app.mall.home.u.a.w().v()));
            f.b0(httpSetting);
            httpSetting.setListener(new b(elapsedRealtime));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public d i() {
        if (this.a != null && !this.a.b()) {
            boolean z = (!this.a.a() || o(this.a) || q(this.a)) ? false : true;
            boolean z2 = (this.a.a() || p(this.a)) ? false : true;
            if (z || z2) {
                return this.a;
            }
        }
        JDJSONArray n2 = com.jingdong.app.mall.ad.a.n();
        if (n2 == null || n2.size() == 0) {
            return null;
        }
        for (int i2 = 0; i2 < n2.size(); i2++) {
            JDJSONObject optJSONObject = n2.optJSONArray(i2).optJSONObject(0);
            d dVar = new d();
            if (dVar.d(optJSONObject) && (!(dVar.a() && (o(dVar) || q(dVar))) && (dVar.a() || !p(dVar)))) {
                dVar.c(optJSONObject);
                this.a = dVar;
                this.b = r(n2);
                this.f7899c.clear();
                this.f7899c.put("extension_id", dVar.x);
                return this.a;
            }
        }
        this.a = null;
        return this.a;
    }

    public void j(com.jingdong.app.mall.ad.b bVar, InterfaceC0236c interfaceC0236c) {
        File file = new File(bVar.b);
        com.jingdong.app.mall.home.floor.ctrl.f.i(com.jingdong.app.mall.ad.a.c(file, bVar.f7898c) ? com.jingdong.app.mall.home.m.a.a(file) : "", new a(this, interfaceC0236c));
    }

    public HashMap<String, String> k() {
        return this.f7899c;
    }

    public int m(String str) {
        if (CommonBase.getIntFromPreference(str, 0) == 0) {
            return 1;
        }
        return CommonBase.getIntFromPreference("start_image_show_times", 0);
    }

    public String n(d dVar, String str, String str2, int i2) {
        try {
            JDJSONArray parseArray = JDJSON.parseArray(this.b);
            if (parseArray == null) {
                parseArray = new JDJSONArray();
            }
            for (int i3 = 0; i3 < parseArray.size(); i3++) {
                JDJSONObject optJSONObject = parseArray.optJSONObject(i3);
                if ("1".equals(optJSONObject.optString("isshow"))) {
                    optJSONObject.put("type", (Object) str);
                    optJSONObject.put("showcondition", (Object) str2);
                    optJSONObject.put("showtimes", (Object) Integer.valueOf(i2));
                }
            }
            if (parseArray.size() < 1 && dVar != null) {
                JDJSONObject parseObject = JDJSON.parseObject(dVar.t);
                parseObject.put("day", (Object) "1");
                parseObject.put("isshow", (Object) "1");
                parseObject.put("type", (Object) str);
                parseObject.put("showcondition", (Object) str2);
                parseObject.put("showtimes", (Object) Integer.valueOf(i2));
                parseArray.add(parseObject);
            }
            return parseArray.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void x(int i2) {
        com.jingdong.app.mall.home.floor.view.b.f.d.a("start_image_all_times", Integer.MAX_VALUE);
        t(i2, "start_image_show_id", "start_image_show_times");
        com.jingdong.app.mall.home.floor.view.b.f.d.a("start_image_show_id_" + i2, Integer.MAX_VALUE);
    }

    public void y(boolean z) {
    }
}
