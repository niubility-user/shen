package com.jingdong.app.mall.faxianV2;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.share.ShareKey;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.FaxianEntry;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JdStorySpUtil;
import com.jingdong.common.widget.custom.discovery.DiscoveryFollowConstant;
import com.jingdong.common.widget.custom.discovery.MyFrameUtil;
import com.jingdong.common.widget.custom.discovery.RedPointManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class a {
    public static volatile boolean a;
    private static b b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.faxianV2.a$a  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public class C0263a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f8428g;

        C0263a(boolean z) {
            this.f8428g = z;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str;
            if (httpResponse != null) {
                try {
                    if (httpResponse.getFastJsonObject() == null || this.f8428g) {
                        return;
                    }
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    long optLong = fastJsonObject.optLong("time", 0L);
                    if (0 != optLong) {
                        CommonBase.getJdSharedPreferences().edit().putLong(RedPointManager.FAXIAN_LAST_UPDATE_TIME, optLong).commit();
                    }
                    int optInt = fastJsonObject.optInt("discoveryupdate");
                    String optString = fastJsonObject.optString("markText");
                    String optString2 = fastJsonObject.optString("markId");
                    if (TextUtils.isEmpty(optString2)) {
                        optString2 = "";
                    }
                    if (a.b != null) {
                        a.b.validateRedPoint(optInt, optString);
                    }
                    String valueOf = TextUtils.isEmpty(optString) ? String.valueOf(optInt) : "2";
                    if (MyFrameUtil.getInstance().getCurrentMyActivity() != null && MyFrameUtil.getInstance().getCurrentMyActivity().getClass().getName().equals("com.jingdong.app.mall.MainFrameActivity")) {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("redpoint", (Object) valueOf);
                        if (!TextUtils.isEmpty(optString2)) {
                            jDJSONObject.put("markId", (Object) optString2);
                        }
                        JDMtaUtils.sendExposureDataWithExt(MyFrameUtil.getInstance().getCurrentMyActivity(), "NavigationBar_DiscoverExpo", valueOf, "", "\u5e95\u90e8\u5bfc\u822a", "", jDJSONObject.toJSONString(), null);
                    }
                    if (TextUtils.isEmpty(optString)) {
                        str = String.valueOf(optInt);
                    } else {
                        str = "2&" + optString2;
                    }
                    SharedPreferencesUtil.putString("NavigationBar_Discover_RedPoint_2", str);
                } catch (Exception e2) {
                    Log.d("RedPointManager", e2.getMessage());
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            if (this.f8428g) {
                httpSettingParams.putJsonParam("clickMark", 1);
            }
            httpSettingParams.putJsonParam("discoverytime", Long.valueOf(CommonBase.getJdSharedPreferences().getLong(RedPointManager.FAXIAN_LAST_UPDATE_TIME, 0L)));
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(0, a.e(FaxianEntry.JD_ENTRY_GOODSTUFF, CommonBase.getJdSharedPreferences().getString("GOOD_STUFF_UPDATETIME", "0"), null));
                jSONArray.put(1, a.e(FaxianEntry.JD_ENTRY_INVENTORY, CommonBase.getJdSharedPreferences().getString("INVENTORY_UPDATETIME", "0"), null));
                jSONArray.put(2, a.e("story", JdStorySpUtil.getString(Constants.STORY_SP_LASTEST_REFRESH_PUBTIME), JdStorySpUtil.getString(Constants.NOTICE_SP_LASTEST_REFRESH_PUBTIME)));
                jSONArray.put(3, a.e("storetrend", null, null));
                jSONArray.put(4, a.e("discoveryFollow", CommonBase.getJdSharedPreferences().getString(DiscoveryFollowConstant.FOLLOW_UPDATE_TIME, "0"), null));
                jSONArray.put(5, a.e("discIndex", CommonBase.getJdSharedPreferences().getString(DiscoveryFollowConstant.DISC_UPDATE_TIME, "0"), null));
                httpSettingParams.putJsonParam(ShareKey.shareChannel, jSONArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes3.dex */
    public interface b {
        void validateRedPoint(int i2, String str);
    }

    private static boolean c() {
        String config;
        try {
            config = JDMobileConfig.getInstance().getConfig("JDFinderCache", "RedPoint", "sendReq", "1");
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        return !"1".equals(config);
    }

    public static void d(boolean z) {
        if (c()) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("jdDiscoveryRedPoint");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new C0263a(z));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject e(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channel", str);
            if (str2 != null) {
                jSONObject.put("lastTime", str2);
            }
            if (str3 != null) {
                jSONObject.put("lastReadNotice", str3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static void f(b bVar) {
        b = bVar;
    }
}
