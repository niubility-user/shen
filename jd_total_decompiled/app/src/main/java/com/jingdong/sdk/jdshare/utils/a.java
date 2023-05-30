package com.jingdong.sdk.jdshare.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.friend.GetShareFriendListCallback;
import com.jingdong.common.utils.friend.JDFriendUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes7.dex */
public class a {
    private static HashMap<String, String> a = new HashMap<>();

    /* renamed from: com.jingdong.sdk.jdshare.utils.a$a */
    /* loaded from: classes7.dex */
    public class C0724a implements PersonalInfoManager.PersonalInfoRequestListener {
        final /* synthetic */ ShareInfo a;
        final /* synthetic */ Context b;

        C0724a(ShareInfo shareInfo, Context context) {
            this.a = shareInfo;
            this.b = context;
        }

        @Override // com.jingdong.common.utils.PersonalInfoManager.PersonalInfoRequestListener
        public void onEnd() {
            d.e(this.a, this.b);
        }

        @Override // com.jingdong.common.utils.PersonalInfoManager.PersonalInfoRequestListener
        public void onError() {
        }
    }

    /* loaded from: classes7.dex */
    public class b implements GetShareFriendListCallback {
        final /* synthetic */ com.jingdong.c.a.c.f a;

        b(com.jingdong.c.a.c.f fVar) {
            this.a = fVar;
        }

        @Override // com.jingdong.common.utils.friend.GetShareFriendListCallback
        public void onResult(@NotNull JDJSONObject jDJSONObject) {
            this.a.f12282j = jDJSONObject;
        }
    }

    /* loaded from: classes7.dex */
    public class c implements HttpGroup.OnEndListener {

        /* renamed from: g */
        final /* synthetic */ String f15022g;

        c(String str) {
            this.f15022g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject;
            if (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                return;
            }
            int optInt = fastJsonObject.optInt("bcode");
            JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("shareId");
                String optString2 = optJSONObject.optString("shareDeviceMaskId");
                if (optInt == 200 && !TextUtils.isEmpty(optString)) {
                    if (OKLog.D) {
                        OKLog.d("FeatureUtil", "get encrypted pin succeed, pin: " + optString);
                    }
                    a.a.put(this.f15022g, optString);
                    a.a.put("uuid", optString2);
                } else if (OKLog.D) {
                    OKLog.d("FeatureUtil", "get encrypted pin failed");
                }
            }
        }
    }

    public static void b(@NonNull Context context, com.jingdong.c.a.c.f fVar) {
        if (fVar != null) {
            try {
                if (TextUtils.isEmpty(fVar.s)) {
                    return;
                }
                Intent intent = new Intent();
                intent.setAction(fVar.s);
                context.startActivity(intent);
            } catch (Throwable unused) {
            }
        }
    }

    public static void c(@NonNull Context context, @NonNull ShareInfo shareInfo) {
        if (LoginUserBase.hasLogin()) {
            if (PersonalInfoManager.getInstance().isAvailable()) {
                d.e(shareInfo, context);
                return;
            } else {
                PersonalInfoManager.requestPersonalInfo(HttpGroupUtils.getHttpGroupaAsynPool(), new C0724a(shareInfo, context));
                return;
            }
        }
        d.e(shareInfo, context);
    }

    public static void d(boolean z, @NonNull ShareInfo shareInfo, String str, boolean z2) {
        String str2;
        String str3;
        if (z) {
            return;
        }
        if (ShareValues.newAddUrlQuerySwitch || !"QRCode".equals(str)) {
            String url = shareInfo.getUrl();
            if (!TextUtils.isEmpty(url) && UserUtil.getWJLoginHelper().hasLogin()) {
                String pin = UserUtil.getWJLoginHelper().getPin();
                if (TextUtils.isEmpty(pin)) {
                    return;
                }
                String str4 = a.get(pin);
                String str5 = a.get("uuid");
                if (ShareValues.newAddUrlQuerySwitch) {
                    if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str5)) {
                        return;
                    }
                    String shareUrl = ShareUtil.getShareUrl(url, str);
                    int length = shareUrl.length();
                    String str6 = "";
                    if (TextUtils.isEmpty(str4)) {
                        str2 = "";
                    } else {
                        str2 = ShareUtil.addShareUrlParam(shareUrl, "gx", str4);
                        url = ShareUtil.addShareUrlParam(url, "gx", str4);
                    }
                    if (TextUtils.isEmpty(str5)) {
                        str3 = "";
                    } else {
                        if (!TextUtils.isEmpty(str2)) {
                            shareUrl = str2;
                        }
                        str6 = ShareUtil.addShareUrlParam(shareUrl, "gxd", str5);
                        str3 = ShareUtil.addShareUrlParam(url, "gxd", str5);
                    }
                    int length2 = !TextUtils.isEmpty(str6) ? str6.length() : str2.length();
                    if (!TextUtils.isEmpty(str3)) {
                        url = str3;
                    }
                    shareInfo.setUrl(url);
                    if (!TextUtils.isEmpty(str6)) {
                        str2 = str6;
                    }
                    g.m("Share_ShareLenth", str2, str + CartConstant.KEY_YB_INFO_LINK + length + CartConstant.KEY_YB_INFO_LINK + length2, z2, shareInfo.getShareSource());
                } else if (TextUtils.isEmpty(str4)) {
                } else {
                    String shareUrl2 = ShareUtil.getShareUrl(url, str);
                    int length3 = shareUrl2.length();
                    String addShareUrlParam = ShareUtil.addShareUrlParam(shareUrl2, "gx", str4);
                    int length4 = addShareUrlParam.length();
                    if (length4 <= 255) {
                        shareInfo.setUrl(ShareUtil.addShareUrlParam(url, "gx", str4));
                        shareUrl2 = addShareUrlParam;
                    } else {
                        length4 = length3;
                    }
                    g.m("Share_ShareLenth", shareUrl2, str + CartConstant.KEY_YB_INFO_LINK + length3 + CartConstant.KEY_YB_INFO_LINK + length4, z2, shareInfo.getShareSource());
                }
            }
        }
    }

    public static void e() {
        if (UserUtil.getWJLoginHelper().hasLogin()) {
            String pin = UserUtil.getWJLoginHelper().getPin();
            if (TextUtils.isEmpty(pin)) {
                return;
            }
            if (ShareValues.newAddUrlQuerySwitch) {
                if (a.containsKey(pin) && a.containsKey("uuid")) {
                    return;
                }
            } else if (a.containsKey(pin)) {
                return;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("businessCode", (Object) "10004");
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setFunctionId("getEncryptedPinColor");
            httpSetting.setPost(true);
            httpSetting.putJsonParam(jDJSONObject);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setType(1000);
            httpSetting.setListener(new c(pin));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    public static void f(@NonNull com.jingdong.c.a.c.f fVar) {
        JDFriendUtils.getShareFriendList(new b(fVar));
    }

    public static ShareInfo g(@NonNull Context context, ShareInfo shareInfo) {
        j(context, shareInfo);
        if (shareInfo.hidePlus == 0) {
            c(context, shareInfo);
        } else {
            d.e(shareInfo, context);
        }
        return shareInfo;
    }

    public static int h() {
        if (UserUtil.getWJLoginHelper().hasLogin()) {
            String pin = UserUtil.getWJLoginHelper().getPin();
            if (TextUtils.isEmpty(pin)) {
                return 0;
            }
            return (!TextUtils.isEmpty(a.get(pin)) ? a.get(pin).length() : 0) + (TextUtils.isEmpty(a.get("uuid")) ? 0 : a.get("uuid").length()) + 99 + 10;
        }
        return 0;
    }

    public static void i(@NonNull Context context, @NonNull ShareInfo shareInfo) {
        if (shareInfo.hidePlus == 0 && PersonalInfoManager.getInstance().isUserPlusStatus()) {
            String string = context.getString(R.string.share_plus_user_tag);
            shareInfo.setSummary(string + shareInfo.getSummary());
            shareInfo.setWxcontent(string + shareInfo.getWxcontent());
            shareInfo.setWxMomentsContent(string + shareInfo.getWxMomentsContent());
            shareInfo.setUrl(ShareUtil.addShareUrlParam(shareInfo.getUrl(), "utm_user", PersonalConstants.FUNCTION_ID_PLUS_SMEMBER));
        }
    }

    private static void j(@NonNull Context context, @NonNull ShareInfo shareInfo) {
        if (TextUtils.isEmpty(shareInfo.getTitle())) {
            shareInfo.setTitle(context.getString(R.string.share_default_title));
        }
        if (TextUtils.isEmpty(shareInfo.getSummary())) {
            shareInfo.setSummary(context.getString(R.string.share_defaut_summary));
        }
        if (TextUtils.isEmpty(shareInfo.getWxcontent())) {
            shareInfo.setWxcontent(shareInfo.getSummary());
        }
        if (TextUtils.isEmpty(shareInfo.getWxMomentsContent())) {
            shareInfo.setWxMomentsContent(shareInfo.getSummary());
        }
        if (!TextUtils.isEmpty(shareInfo.getUrl())) {
            shareInfo.setUrl(shareInfo.getUrl().replace("3.cn/Ceo4yH", "sq.jd.com/NvQBpa"));
        }
        shareInfo.setTransaction(ShareUtil.urlEncode(shareInfo.getUrl()));
    }
}
