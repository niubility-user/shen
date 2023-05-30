package com.jingdong.common.utils.friend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.unification.router.JDRouter;
import java.net.URLEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\t\u001a!\u0010\r\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\r\u0010\u000e\u001a#\u0010\u0010\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u000e\u001a#\u0010\u0012\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000e\u001a\u0017\u0010\u0015\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;", "callback", "", "getShareFriendList", "(Lcom/jingdong/common/utils/friend/GetShareFriendListCallback;)V", "Lcom/jd/framework/json/JDJSONObject;", "friendListObject", "Lcom/jd/framework/json/JDJSONArray;", "parseShareFriendListToArray", "(Lcom/jd/framework/json/JDJSONObject;)Lcom/jd/framework/json/JDJSONArray;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "shareInfo", "jumpToShareFriendList", "(Landroid/content/Context;Lcom/jd/framework/json/JDJSONObject;)V", "jumpToShareFriendListByRouter$JDFriendUtils__JDFriendUtilsKt", "jumpToShareFriendListByRouter", "jumpToShareFriendListByOpenApp$JDFriendUtils__JDFriendUtilsKt", "jumpToShareFriendListByOpenApp", "Landroid/content/Intent;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "getShareInfoFromIntent", "(Landroid/content/Intent;)Lcom/jd/framework/json/JDJSONObject;", "personallib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/common/utils/friend/JDFriendUtils")
/* loaded from: classes6.dex */
final /* synthetic */ class JDFriendUtils__JDFriendUtilsKt {
    public static final void getShareFriendList(@NotNull GetShareFriendListCallback getShareFriendListCallback) {
        JDFriendManager.INSTANCE.getShareFriendList(getShareFriendListCallback);
    }

    @NotNull
    public static final JDJSONObject getShareInfoFromIntent(@Nullable Intent intent) {
        String stringExtra = intent != null ? intent.getStringExtra("shareInfo") : null;
        if (stringExtra == null || stringExtra.length() == 0) {
            return new JDJSONObject();
        }
        try {
            JDJSONObject parseObject = JDJSON.parseObject(stringExtra);
            Intrinsics.checkExpressionValueIsNotNull(parseObject, "JDJSONObject.parseObject(shareInfoString)");
            return parseObject;
        } catch (Exception unused) {
            return new JDJSONObject();
        }
    }

    public static final void jumpToShareFriendList(@Nullable Context context, @Nullable JDJSONObject jDJSONObject) {
        jumpToShareFriendListByRouter$JDFriendUtils__JDFriendUtilsKt(context, jDJSONObject);
    }

    private static final void jumpToShareFriendListByOpenApp$JDFriendUtils__JDFriendUtilsKt(Context context, JDJSONObject jDJSONObject) {
        String str;
        if (context != null) {
            if (jDJSONObject == null || (str = jDJSONObject.toJSONString()) == null) {
                str = "{}";
            }
            new OpenAppJumpBuilder.Builder(Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"showsharefriendlist\",\"shareInfo\":" + str + '}')).build().jump(context);
        }
    }

    private static final void jumpToShareFriendListByRouter$JDFriendUtils__JDFriendUtilsKt(Context context, JDJSONObject jDJSONObject) {
        String str;
        String encode;
        if (context != null) {
            if (jDJSONObject == null || (str = jDJSONObject.toJSONString()) == null) {
                str = "{}";
            }
            try {
                encode = URLEncoder.encode(str, "utf-8");
                Intrinsics.checkExpressionValueIsNotNull(encode, "URLEncoder.encode(it, \"utf-8\")");
            } catch (Exception unused) {
                encode = Uri.encode(str);
                Intrinsics.checkExpressionValueIsNotNull(encode, "Uri.encode(it)");
            }
            Intrinsics.checkExpressionValueIsNotNull(encode, "(shareInfo?.toJSONString\u2026ncode(it)\n        }\n    }");
            JDRouter.to(context, "router://JDFriendModule/showShareFriendList?shareInfo=" + encode).open();
        }
    }

    @NotNull
    public static final JDJSONArray parseShareFriendListToArray(@Nullable JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        return (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray(JDFriendConstants.DATA_KEY_FRIENDS)) == null) ? new JDJSONArray() : optJSONArray;
    }
}
