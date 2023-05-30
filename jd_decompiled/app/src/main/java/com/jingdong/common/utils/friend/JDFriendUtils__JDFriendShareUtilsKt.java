package com.jingdong.common.utils.friend;

import android.content.Context;
import com.google.gson.Gson;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.deeplinkhelper.imhelper.RecentContactEntity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.JDDDMessageRouterUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\u000b\u001a+\u0010\u0006\u001a\u00020\u00052\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001aM\u0010\u0006\u001a\u00020\u00052\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\f\u00a2\u0006\u0004\b\u0006\u0010\u000e\u001a'\u0010\u0012\u001a\u00020\u00052\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a?\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001a"}, d2 = {"Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jd/framework/json/JDJSONObject;", "friendInfo", "shareInfo", "", "shareToFriend", "(Landroid/content/Context;Lcom/jd/framework/json/JDJSONObject;Lcom/jd/framework/json/JDJSONObject;)V", "", "friendPin", "friendName", "friendImage", "", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "", "postShareParam$JDFriendUtils__JDFriendShareUtilsKt", "(Ljava/util/Map;)V", "postShareParam", "fromPin", "toPin", "name", "avatar", "openShareChat$JDFriendUtils__JDFriendShareUtilsKt", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "openShareChat", "personallib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/common/utils/friend/JDFriendUtils")
/* loaded from: classes6.dex */
public final /* synthetic */ class JDFriendUtils__JDFriendShareUtilsKt {
    private static final void openShareChat$JDFriendUtils__JDFriendShareUtilsKt(Context context, String str, String str2, String str3, String str4) {
        RecentContactEntity recentContactEntity = new RecentContactEntity();
        recentContactEntity.sessionType = 1;
        if (str2 == null) {
            str2 = "";
        }
        recentContactEntity.toPin = str2;
        recentContactEntity.toApp = "im.customer";
        if (str3 == null) {
            str3 = "";
        }
        recentContactEntity.name = str3;
        if (str4 == null) {
            str4 = "";
        }
        recentContactEntity.avatar = str4;
        String json = new Gson().toJson(recentContactEntity);
        JDRouterUrlBuilder methodName = new JDRouterUrlBuilder().setModuleName(JDDDMessageRouterUtil.MODULNAME).setMethodName("openChat");
        if (str == null) {
            str = "";
        }
        JDRouter.build(context, methodName.putStringParam("pin", str).putStringParam("recentContactEntityJsonStr", json).build()).open();
    }

    private static final void postShareParam$JDFriendUtils__JDFriendShareUtilsKt(Map<String, ? extends Object> map) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(JDFriendConstants.SHARE_FUNCTION_ID);
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getPersonalHost());
        if (map != null) {
            for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
                httpSetting.putJsonParam(entry.getKey(), entry.getValue());
            }
        }
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static final void shareToFriend(@Nullable Context context, @Nullable JDJSONObject jDJSONObject, @Nullable JDJSONObject jDJSONObject2) {
        JDFriendUtils.shareToFriend(context, jDJSONObject != null ? jDJSONObject.optString("pin") : null, jDJSONObject != null ? jDJSONObject.optString("name") : null, jDJSONObject != null ? jDJSONObject.optString("image") : null, jDJSONObject2);
    }

    public static final void shareToFriend(@Nullable Context context, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Map<String, Object> map) {
        if (context != null) {
            if (map != null) {
                map.put(JDFriendConstants.REQUEST_PARAM_TARGET_PIN, str);
            }
            postShareParam$JDFriendUtils__JDFriendShareUtilsKt(map);
            openShareChat$JDFriendUtils__JDFriendShareUtilsKt(context, LoginUserBase.getUserPin(), str, str2, str3);
        }
    }
}
