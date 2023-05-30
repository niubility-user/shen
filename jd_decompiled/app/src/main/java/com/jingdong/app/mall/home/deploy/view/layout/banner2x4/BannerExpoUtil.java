package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class BannerExpoUtil {
    private static final SparseBooleanArray a = new SparseBooleanArray();

    public static String a(DBanner2x4Model dBanner2x4Model, float f2, String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return str.replace("__IS_FRONT_CACHE__", dBanner2x4Model.J0() ? "1" : "0").replace("__HOME_STATUS__", JDHomeFragment.O0() ? "0" : "2").replace("__EXPOSURE_HEIGHT__", String.valueOf((int) (f2 * 100.0f))).replace("__BROWSE_DURATION__", String.valueOf(SystemClock.elapsedRealtime() - s.f9357c)).replace("__SCREEN_PIXEL_HEIGHT__", String.valueOf(b())).replace("__PIXEL_DISTANCE_FROM_TOP__", String.valueOf(dBanner2x4Model.C0()));
    }

    private static int b() {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return 0;
        }
        return z0.v0();
    }

    public static void c(DBanner2x4Model dBanner2x4Model, DBanner2x4 dBanner2x4, int i2) {
        if (dBanner2x4Model.J0() && i2 == 0) {
            return;
        }
        try {
            SparseBooleanArray sparseBooleanArray = a;
            if (sparseBooleanArray.get(i2)) {
                return;
            }
            sparseBooleanArray.put(i2, true);
            String E0 = dBanner2x4Model.E0(i2);
            String D0 = dBanner2x4Model.D0(i2);
            final String str = i2 + "";
            float e2 = e(dBanner2x4Model, dBanner2x4, E0, 0L);
            a.y("Home_FPicAdsRequest", "", dBanner2x4Model.z0().toString());
            if (!TextUtils.isEmpty(D0)) {
                HashMap hashMap = new HashMap();
                hashMap.put("extension_id", D0);
                a.B("Home_FocusPicAD_Expo", "", dBanner2x4Model.z0().toString(), RecommendMtaUtils.Home_PageId, "", hashMap);
            }
            if (!TextUtils.isEmpty(E0)) {
                String a2 = a(dBanner2x4Model, e2, E0);
                f.v0(a2, new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerExpoUtil.1
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        a.y("Home_FPicAdsRequestSuccess", str, "");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        Throwable exception = httpError.getException();
                        StringBuilder sb = new StringBuilder();
                        sb.append(str.concat(CartConstant.KEY_YB_INFO_LINK));
                        sb.append(httpError.getErrorCode());
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        sb.append(exception == null ? "" : exception.getMessage());
                        a.y("Home_FPicAdsRequestFail", sb.toString(), "");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i3, int i4) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                    }
                });
                if (Log.D) {
                    Log.d("HHH", "exposal url, position:" + i2 + ", post: " + a2);
                }
            }
            dBanner2x4Model.L0(i2);
        } catch (Exception e3) {
            if (Log.E) {
                e3.printStackTrace();
            }
        }
    }

    public static void d() {
        a.clear();
    }

    public static float e(DBanner2x4Model dBanner2x4Model, DBanner2x4 dBanner2x4, String str, long j2) {
        b z0 = dBanner2x4Model.z0();
        float C = dBanner2x4.C();
        f.c(z0, dBanner2x4Model.J0());
        f.b(z0);
        f.d(z0);
        z0.a("frame", DBanner2x4.z() + "");
        z0.a("urlcheck", TextUtils.isEmpty(str) ? "0" : "1");
        z0.a("fpicrate", String.valueOf(C));
        z0.a("showtimegap", j2 + "");
        return C;
    }
}
