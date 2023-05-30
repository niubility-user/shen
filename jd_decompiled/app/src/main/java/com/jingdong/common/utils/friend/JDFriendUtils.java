package com.jingdong.common.utils.friend;

import android.content.Context;
import android.content.Intent;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"com/jingdong/common/utils/friend/JDFriendUtils__JDFriendShareUtilsKt", "com/jingdong/common/utils/friend/JDFriendUtils__JDFriendUtilsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class JDFriendUtils {
    public static final void getShareFriendList(@NotNull GetShareFriendListCallback getShareFriendListCallback) {
        JDFriendUtils__JDFriendUtilsKt.getShareFriendList(getShareFriendListCallback);
    }

    @NotNull
    public static final JDJSONObject getShareInfoFromIntent(@Nullable Intent intent) {
        return JDFriendUtils__JDFriendUtilsKt.getShareInfoFromIntent(intent);
    }

    public static final void jumpToShareFriendList(@Nullable Context context, @Nullable JDJSONObject jDJSONObject) {
        JDFriendUtils__JDFriendUtilsKt.jumpToShareFriendList(context, jDJSONObject);
    }

    @NotNull
    public static final JDJSONArray parseShareFriendListToArray(@Nullable JDJSONObject jDJSONObject) {
        return JDFriendUtils__JDFriendUtilsKt.parseShareFriendListToArray(jDJSONObject);
    }

    public static final void shareToFriend(@Nullable Context context, @Nullable JDJSONObject jDJSONObject, @Nullable JDJSONObject jDJSONObject2) {
        JDFriendUtils__JDFriendShareUtilsKt.shareToFriend(context, jDJSONObject, jDJSONObject2);
    }

    public static final void shareToFriend(@Nullable Context context, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Map<String, Object> map) {
        JDFriendUtils__JDFriendShareUtilsKt.shareToFriend(context, str, str2, str3, map);
    }
}
