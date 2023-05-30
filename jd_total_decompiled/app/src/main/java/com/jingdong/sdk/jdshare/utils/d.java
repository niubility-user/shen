package com.jingdong.sdk.jdshare.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.CmShareChannelInfo;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.utils.HWShareHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.ShareCallbackListenerParcel;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.friend.JDFriendUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class d {
    public static void a(Context context, com.jingdong.c.a.c.f fVar) {
        com.jingdong.c.a.c.b bVar;
        ArrayList arrayList = new ArrayList();
        if (ShareValues.isLandscapeMode()) {
            if (i.c()) {
                if (ShareValues.isNewWeiXinShareUI && fVar.a != 2) {
                    if (fVar.f12284l) {
                        arrayList.add(new com.jingdong.c.a.c.b(1, "Wxfriends", R.drawable.wechat_icon_for_live, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                    if (fVar.f12285m) {
                        arrayList.add(new com.jingdong.c.a.c.b(2, "Wxfriends", R.drawable.wechat_icon_for_live, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                    if (!TextUtils.isEmpty(fVar.b.getUrl())) {
                        arrayList.add(new com.jingdong.c.a.c.b(0, "Wxfriends", R.drawable.wechat_icon_for_live, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                } else {
                    arrayList.add(new com.jingdong.c.a.c.b("Wxfriends", R.drawable.wechat_icon_for_live, "", context.getString(R.string.share_to_wx_friends), true));
                }
                arrayList.add(new com.jingdong.c.a.c.b("Wxmoments", R.drawable.wechat_friend_icon_for_live, "", context.getString(R.string.share_to_wx_friends_circle), true));
            }
            if (e.b()) {
                arrayList.add(new com.jingdong.c.a.c.b("QQfriends", R.drawable.qq_friend_icon_for_live, "", context.getString(R.string.share_to_qq_friends), true));
                arrayList.add(new com.jingdong.c.a.c.b("QQzone", R.drawable.qq_zone_icon_for_live, "", context.getString(R.string.share_to_qzone), true));
            }
            if (WeiboUtils.checkWbSdk()) {
                arrayList.add(new com.jingdong.c.a.c.b("Sinaweibo", R.drawable.weibo_icon_for_live, "", context.getString(R.string.share_to_weibo), true));
            }
        } else {
            if (i.c()) {
                if (ShareValues.isNewWeiXinShareUI && fVar.a != 2) {
                    if (fVar.f12284l) {
                        arrayList.add(new com.jingdong.c.a.c.b(1, "Wxfriends", R.drawable.share_to_wx_friend_icon, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                    if (fVar.f12285m) {
                        arrayList.add(new com.jingdong.c.a.c.b(2, "Wxfriends", R.drawable.share_to_wx_friend_icon, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                    if (!TextUtils.isEmpty(fVar.b.getUrl())) {
                        arrayList.add(new com.jingdong.c.a.c.b(0, "Wxfriends", R.drawable.share_to_wx_friend_icon, "", context.getString(R.string.share_to_wx_friends), true));
                    }
                } else {
                    arrayList.add(new com.jingdong.c.a.c.b("Wxfriends", R.drawable.share_to_wx_friend_icon, "", context.getString(R.string.share_to_wx_friends), true));
                }
                arrayList.add(new com.jingdong.c.a.c.b("Wxmoments", R.drawable.share_to_wx_circle_icon, "", context.getString(R.string.share_to_wx_friends_circle), true));
            }
            if (e.b()) {
                arrayList.add(new com.jingdong.c.a.c.b("QQfriends", R.drawable.share_to_qq_friend_icon, "", context.getString(R.string.share_to_qq_friends), true));
                arrayList.add(new com.jingdong.c.a.c.b("QQzone", R.drawable.share_to_qzone_icon, "", context.getString(R.string.share_to_qzone), true));
            }
            if (WeiboUtils.checkWbSdk()) {
                arrayList.add(new com.jingdong.c.a.c.b("Sinaweibo", R.drawable.share_to_weibo_icon, "", context.getString(R.string.share_to_weibo), true));
            }
        }
        boolean e2 = fVar.e();
        if (e2) {
            OKLog.d("ParseUtil", "is lottery");
        }
        com.jingdong.c.a.c.c cVar = new com.jingdong.c.a.c.c();
        fVar.f12276c = cVar;
        List<String> channelsList = fVar.b.getChannelsList();
        int i2 = 0;
        while (true) {
            if (i2 >= (e2 ? 4 : arrayList.size())) {
                break;
            }
            com.jingdong.c.a.c.b bVar2 = (com.jingdong.c.a.c.b) arrayList.get(i2);
            if (channelsList.contains(bVar2.a)) {
                cVar.b.add(bVar2);
                cVar.a.add(bVar2);
            }
            i2++;
        }
        if (e2) {
            return;
        }
        if (fVar.f12285m && !ShareValues.isNewWeiXinShareUI) {
            for (int i3 = 0; i3 < cVar.b.size(); i3++) {
                com.jingdong.c.a.c.b bVar3 = cVar.b.get(i3);
                if ("Wxfriends".contains(bVar3.a)) {
                    bVar3.f12270h = 2;
                }
            }
        }
        if (fVar.f12283k) {
            List asList = Arrays.asList(fVar.f12281i.optString("keyChannel").split(DYConstants.DY_REGEX_COMMA));
            boolean z = true;
            for (int i4 = 0; i4 < cVar.b.size(); i4++) {
                com.jingdong.c.a.c.b bVar4 = cVar.b.get(i4);
                if (ShareValues.isNewWeiXinShareUI && fVar.a != 2) {
                    if (!"Wxfriends".equals(bVar4.a)) {
                        z = true;
                    }
                    if (asList.contains(bVar4.a) && z) {
                        bVar4.f12266c = true;
                        z = false;
                    }
                } else if (asList.contains(bVar4.a)) {
                    bVar4.f12266c = true;
                }
            }
        }
        List asList2 = Arrays.asList("hwpmgx".split(DYConstants.DY_REGEX_COMMA));
        List<CmShareChannelInfo> list = fVar.b.cmChannelList;
        if (list != null && list.size() > 0) {
            for (CmShareChannelInfo cmShareChannelInfo : list) {
                if (cmShareChannelInfo != null && !TextUtils.isEmpty(cmShareChannelInfo.cmIconName) && (cmShareChannelInfo.cmCIconResId != 0 || !TextUtils.isEmpty(cmShareChannelInfo.cmIconUrl))) {
                    if (!TextUtils.isEmpty(cmShareChannelInfo.cmBizId)) {
                        if (asList2.contains(cmShareChannelInfo.cmBizId)) {
                            com.jingdong.c.a.c.b bVar5 = new com.jingdong.c.a.c.b(cmShareChannelInfo.cmBizId, cmShareChannelInfo.cmCIconResId, cmShareChannelInfo.cmIconUrl, cmShareChannelInfo.cmIconName, false);
                            cVar.f12271c.add(bVar5);
                            cVar.a.add(bVar5);
                        } else {
                            OKLog.d("ParseUtil", "custom share channel id is not register");
                        }
                    }
                }
            }
        }
        if (channelsList.contains(ShareUtil.S_Hw_CaasShare) && !HWShareHelper.isSupportHwCaasShare()) {
            com.jingdong.c.a.c.b bVar6 = new com.jingdong.c.a.c.b(ShareUtil.S_Hw_CaasShare, R.drawable.share_to_hwcaas_icon, "", context.getString(R.string.share_to_hwcaas), false);
            cVar.f12271c.add(bVar6);
            cVar.a.add(bVar6);
        }
        if (channelsList.contains("CopyURL")) {
            if (ShareValues.isLandscapeMode()) {
                bVar = new com.jingdong.c.a.c.b("CopyURL", R.drawable.copy_link_icon_for_live, "", context.getString(R.string.share_to_copy), false);
            } else {
                bVar = new com.jingdong.c.a.c.b("CopyURL", R.drawable.share_to_copy_icon, "", context.getString(R.string.share_to_copy), false);
            }
            cVar.f12271c.add(bVar);
            cVar.a.add(bVar);
        }
        if (fVar.b.getShareImageInfo() != null && (fVar.f() || (fVar.c() && channelsList.contains("QRCode") && channelsList.size() == 1))) {
            OKLog.d("ParseUtil", "need Qr");
            com.jingdong.c.a.c.b bVar7 = new com.jingdong.c.a.c.b("QRCode", R.drawable.share_to_qr_code_icon, "", context.getString(R.string.share_to_big_pic), false);
            cVar.f12271c.add(bVar7);
            cVar.a.add(bVar7);
        }
        d(context, fVar);
    }

    public static List<com.jingdong.c.a.c.b> b(Context context) {
        ArrayList arrayList = new ArrayList();
        if (i.c()) {
            arrayList.add(new com.jingdong.c.a.c.b("Wxfriends", R.drawable.share_to_wx_friend_icon, "", context.getString(R.string.share_to_wx_friends), true));
            arrayList.add(new com.jingdong.c.a.c.b("Wxmoments", R.drawable.share_to_wx_circle_icon, "", context.getString(R.string.share_to_wx_friends_circle), true));
        }
        arrayList.add(new com.jingdong.c.a.c.b("QQfriends", R.drawable.share_to_qq_friend_icon, "", context.getString(R.string.share_to_qq_friends), true));
        if (SwitchQueryFetcher.getSwitchBooleanValue("savePictureSwitch", false) && Build.VERSION.SDK_INT >= 29) {
            arrayList.add(new com.jingdong.c.a.c.b(ShareUtil.S_JD_SAVE_IMG, R.drawable.share_save_img_icon, "", context.getString(R.string.share_to_save_img), false));
        }
        return arrayList;
    }

    public static com.jingdong.c.a.c.f c(@NonNull Intent intent) {
        com.jingdong.c.a.c.f fVar = new com.jingdong.c.a.c.f();
        fVar.a = intent.getIntExtra("action", 0);
        Bundle bundle = (Bundle) intent.getParcelableExtra("bundle");
        if (bundle != null) {
            fVar.b = (ShareInfo) bundle.getParcelable("shareInfo");
        }
        if (intent.hasExtra("bytes")) {
            fVar.f12286n = intent.getByteArrayExtra("bytes");
        }
        if (intent.hasExtra("parcel")) {
            try {
                ShareCallbackListenerParcel shareCallbackListenerParcel = (ShareCallbackListenerParcel) intent.getParcelableExtra("parcel");
                if (shareCallbackListenerParcel != null) {
                    fVar.q = shareCallbackListenerParcel.getCallbackListener();
                    fVar.r = shareCallbackListenerParcel.getClickCallbackListener();
                }
            } catch (Exception e2) {
                ExceptionReporter.reportKeyShareException("ShareCallbackListenerParcel", "", e2.toString(), "");
            }
        }
        fVar.a();
        if (fVar.g()) {
            fVar.p.a = intent.getIntExtra("result", 0);
            if (intent.hasExtra("msg")) {
                fVar.p.b = intent.getStringExtra("msg");
            }
            if (intent.hasExtra("transaction")) {
                fVar.p.a(intent.getStringExtra("transaction"));
            }
        }
        if (fVar.e()) {
            fVar.o.a(intent);
        }
        return fVar;
    }

    private static void d(Context context, @NonNull com.jingdong.c.a.c.f fVar) {
        JDJSONObject jDJSONObject;
        JDJSONArray parseShareFriendListToArray;
        ShareInfo shareInfo;
        int i2;
        if (fVar.f12276c == null || (jDJSONObject = fVar.f12282j) == null || (parseShareFriendListToArray = JDFriendUtils.parseShareFriendListToArray(jDJSONObject)) == null || parseShareFriendListToArray.isEmpty() || fVar.u || (shareInfo = fVar.b) == null || (i2 = shareInfo.showFriendChannel) == 1) {
            return;
        }
        if (i2 == 0 && shareInfo.defaultShowFriendChannel == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(8);
        for (int i3 = 0; i3 < parseShareFriendListToArray.size(); i3++) {
            JDJSONObject jSONObject = parseShareFriendListToArray.getJSONObject(i3);
            if (jSONObject != null) {
                com.jingdong.c.a.c.b bVar = new com.jingdong.c.a.c.b();
                bVar.a = "JDFriends";
                bVar.b = jSONObject.optString("name", "");
                bVar.f12267e = jSONObject.optString("image", "");
                bVar.f12268f = jSONObject.optString("pin", "");
                arrayList.add(bVar);
            }
        }
        if (!arrayList.isEmpty()) {
            com.jingdong.c.a.c.b bVar2 = new com.jingdong.c.a.c.b();
            bVar2.a = "JDFriends";
            bVar2.f12269g = true;
            bVar2.b = context.getString(R.string.share_to_friend_more);
            bVar2.d = R.drawable.share_to_friend_more_icon;
            arrayList.add(bVar2);
        }
        fVar.f12276c.d = arrayList;
    }

    public static void e(ShareInfo shareInfo, Context context) {
        String config;
        OKLog.d("ParseUtil", "weiXinContentParam : " + shareInfo.getWeiXinContentParam());
        f(shareInfo);
        if (!TextUtils.isEmpty(shareInfo.getWeiXinContentParam())) {
            if (ShareUtil.isUseSwitchQuery()) {
                config = SwitchQueryFetcher.getSwitchStringValue("sortWeiXinContentParam", "");
            } else {
                config = JDMobileConfig.getInstance().getConfig("JDShare", "sortWeiXinContentParam", "sort");
            }
            OKLog.d("ParseUtil", "sortString : " + config);
            if (!TextUtils.isEmpty(config)) {
                String[] split = config.split(DYConstants.DY_REGEX_COMMA);
                JDJSONObject parseObject = JDJSON.parseObject(shareInfo.getWeiXinContentParam());
                if (parseObject != null) {
                    OKLog.d("ParseUtil", "paramsJson : " + parseObject.toJSONString());
                    if (shareInfo.hidePlus == 0 && PersonalInfoManager.getInstance().isUserPlusStatus()) {
                        String string = context.getString(R.string.share_plus_user_tag);
                        parseObject.put("plusMember", (Object) string);
                        shareInfo.setUrl(ShareUtil.addShareUrlParam(shareInfo.getUrl(), "utm_user", PersonalConstants.FUNCTION_ID_PLUS_SMEMBER));
                        shareInfo.setSummary(string + shareInfo.getSummary());
                        shareInfo.setWxMomentsContent(string + shareInfo.getWxMomentsContent());
                        OKLog.d("ParseUtil", "plus :" + PersonalInfoManager.getInstance().isUserPlusStatus());
                    }
                    String optString = parseObject.optString(split[0]);
                    String optString2 = parseObject.optString(split[1]);
                    String optString3 = parseObject.optString(split[2]);
                    String optString4 = parseObject.optString(split[3]);
                    String optString5 = parseObject.optString(split[4]);
                    StringBuilder sb = new StringBuilder();
                    if (!TextUtils.isEmpty(optString)) {
                        sb.append(optString);
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    if (!TextUtils.isEmpty(optString2)) {
                        sb.append(optString2);
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    if (!TextUtils.isEmpty(optString3)) {
                        sb.append(optString3);
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    if (!TextUtils.isEmpty(optString4)) {
                        sb.append(optString4);
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    if (!TextUtils.isEmpty(optString5)) {
                        sb.append(optString5);
                        sb.append(LangUtils.SINGLE_SPACE);
                    }
                    sb.append(ShareValues.SHARE_DEFAULT_WX_CONTENT);
                    shareInfo.setWxcontent(sb.toString());
                    String optString6 = parseObject.optString("titleTag");
                    if (!TextUtils.isEmpty(optString6)) {
                        shareInfo.setTitle("\u3010" + optString6 + "\u3011" + shareInfo.getTitle());
                    }
                    OKLog.d("ParseUtil", "\u8f6c\u5316\u540e wxContent : " + shareInfo.getWxcontent());
                    OKLog.d("ParseUtil", "\u8f6c\u5316\u540e title : " + shareInfo.getTitle());
                    return;
                }
                a.i(context, shareInfo);
                return;
            }
            a.i(context, shareInfo);
            return;
        }
        a.i(context, shareInfo);
    }

    private static void f(ShareInfo shareInfo) {
        String str;
        String str2;
        String str3;
        String str4;
        JDJSONObject parseObject;
        String str5 = "";
        if (shareInfo.getWeiXinContentParam() == null || (parseObject = JDJSON.parseObject(shareInfo.getWeiXinContentParam())) == null) {
            str = "";
            str2 = str;
            str3 = str2;
            str4 = str3;
        } else {
            str5 = parseObject.optString("titleTag");
            str2 = parseObject.optString("price");
            str3 = parseObject.optString(CartConstant.CART_KEY_SECKILL);
            str4 = parseObject.optString("ranking");
            str = parseObject.optString("rating");
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("url", (Object) shareInfo.getUrl());
        jDJSONObject.put("titleTag", !TextUtils.isEmpty(str5) ? "1" : "0");
        jDJSONObject.put("price", !TextUtils.isEmpty(str2) ? "1" : "0");
        jDJSONObject.put(CartConstant.CART_KEY_SECKILL, !TextUtils.isEmpty(str3) ? "1" : "0");
        jDJSONObject.put("ranking", !TextUtils.isEmpty(str4) ? "1" : "0");
        jDJSONObject.put("rating", (Object) (TextUtils.isEmpty(str) ? "0" : "1"));
        jDJSONObject.put("sharechan_type", (Object) (TextUtils.isEmpty(shareInfo.getMpId()) ? "1" : "2"));
        String h2 = g.h(shareInfo, jDJSONObject.toJSONString());
        ShareValues.wxJsonParam = h2;
        OKLog.d("ParseUtil", h2);
    }
}
