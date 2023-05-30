package com.jingdong.sdk.jdshare.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.friend.JDFriendUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes7.dex */
public class f {
    public static void a(Context context, ShareInfo shareInfo, com.jingdong.c.a.a aVar) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("Label", ShareUtil.getShareUrl(shareInfo.getUrl(), "CopyURL")));
        }
        ToastUtils.shortToast(context, R.string.share_copy_success);
        aVar.d(11, "CopyURL", true);
    }

    public static void b(Context context, com.jingdong.c.a.c.b bVar, ShareInfo shareInfo, com.jingdong.c.a.a aVar) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("title", (Object) shareInfo.getTitle());
        jDJSONObject.put("content", (Object) shareInfo.getSummary());
        jDJSONObject.put("icon", (Object) (TextUtils.isEmpty(shareInfo.getIconUrl()) ? context.getString(R.string.share_sdk_default_icon_url) : shareInfo.getIconUrl()));
        jDJSONObject.put("url", (Object) shareInfo.getUrl());
        if (bVar.f12269g) {
            JDFriendUtils.jumpToShareFriendList(context, jDJSONObject);
        } else {
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("pin", (Object) bVar.f12268f);
            jDJSONObject2.put("name", (Object) bVar.b);
            jDJSONObject2.put("image", (Object) bVar.f12267e);
            JDFriendUtils.shareToFriend(context, jDJSONObject2, jDJSONObject);
        }
        aVar.d(11, "JDFriends", true);
    }
}
