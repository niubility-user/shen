package com.jingdong.common.widget.custom.discovery;

import com.jd.cashier.app.jdlibcutter.protocol.share.ShareKey;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.FaxianEntry;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JdStorySpUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class RedPointManager {
    public static final String FAXIAN_LAST_UPDATE_TIME = "FAXIAN_LAST_UPDATE_TIME";
    public static final String SHARED_FAXIAN_REDPOINTFLAG = "shared_faxian_redpoint_flag";
    public static volatile boolean isClickFaxianTab;
    private static ValidateRedPointIf validateRedPointIf;

    /* loaded from: classes12.dex */
    public static class FaxianChannel {
        public String channel;
        public int level;
        public NotificationIterm notificationIterm;

        public FaxianChannel(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            this.channel = jDJSONObject.optString("channel");
            this.level = jDJSONObject.optInt("level");
            this.notificationIterm = new NotificationIterm(jDJSONObject.optJSONObject("notificationIterm"));
        }
    }

    /* loaded from: classes12.dex */
    public static class NotificationIterm {
        public String icon;

        public NotificationIterm(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            this.icon = jDJSONObject.optString("icon", null);
        }
    }

    /* loaded from: classes12.dex */
    public interface RedPointCallback {
        void callback(ArrayList<String> arrayList);
    }

    /* loaded from: classes12.dex */
    public interface ValidateRedPointIf {
        void validateRedPoint(int i2, String str);
    }

    public static void checkRedPoint() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("jdDiscoveryRedPoint");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.discovery.RedPointManager.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                long optLong = fastJsonObject.optLong("time", 0L);
                if (0 != optLong) {
                    CommonBase.getJdSharedPreferences().edit().putLong(RedPointManager.FAXIAN_LAST_UPDATE_TIME, optLong).commit();
                }
                int optInt = fastJsonObject.optInt("discoveryupdate");
                String optString = fastJsonObject.optString("markText");
                if (RedPointManager.validateRedPointIf != null) {
                    RedPointManager.validateRedPointIf.validateRedPoint(optInt, optString);
                }
                if (MyFrameUtil.getInstance().getCurrentMyActivity() != null && MyFrameUtil.getInstance().getCurrentMyActivity().getClass().getName().equals("com.jingdong.app.mall.MainFrameActivity")) {
                    JDMtaUtils.sendExposureDataOverLoad(MyFrameUtil.getInstance().getCurrentMyActivity(), "NavigationBar_DiscoverExpo", String.valueOf(optInt), "", "", "FaxianMainFragment");
                    JDMtaUtils.onClickWithPageId(MyFrameUtil.getInstance().getCurrentMyActivity(), "NavigationBar_DiscoverExpo", "FaxianMainFragment", String.valueOf(optInt), "");
                }
                SharedPreferencesUtil.putInt("NavigationBar_Discover_RedPoint", optInt);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                httpSettingParams.putJsonParam("clickMark", Integer.valueOf(RedPointManager.isClickFaxianTab ? 1 : 0));
                httpSettingParams.putJsonParam("discoverytime", Long.valueOf(CommonBase.getJdSharedPreferences().getLong(RedPointManager.FAXIAN_LAST_UPDATE_TIME, 0L)));
                try {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(0, RedPointManager.getChannel(FaxianEntry.JD_ENTRY_GOODSTUFF, CommonBase.getJdSharedPreferences().getString("GOOD_STUFF_UPDATETIME", "0"), null));
                    jSONArray.put(1, RedPointManager.getChannel(FaxianEntry.JD_ENTRY_INVENTORY, CommonBase.getJdSharedPreferences().getString("INVENTORY_UPDATETIME", "0"), null));
                    jSONArray.put(2, RedPointManager.getChannel("story", JdStorySpUtil.getString(Constants.STORY_SP_LASTEST_REFRESH_PUBTIME), JdStorySpUtil.getString(Constants.NOTICE_SP_LASTEST_REFRESH_PUBTIME)));
                    jSONArray.put(3, RedPointManager.getChannel("storetrend", null, null));
                    jSONArray.put(4, RedPointManager.getChannel("discoveryFollow", CommonBase.getJdSharedPreferences().getString(DiscoveryFollowConstant.FOLLOW_UPDATE_TIME, "0"), null));
                    jSONArray.put(5, RedPointManager.getChannel("discIndex", CommonBase.getJdSharedPreferences().getString(DiscoveryFollowConstant.DISC_UPDATE_TIME, "0"), null));
                    httpSettingParams.putJsonParam(ShareKey.shareChannel, jSONArray);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject getChannel(String str, String str2, String str3) {
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

    public static void registerValidateRePointIf(ValidateRedPointIf validateRedPointIf2) {
        validateRedPointIf = validateRedPointIf2;
    }

    public static void unRegisterValidateRePointIf() {
        validateRedPointIf = null;
    }
}
