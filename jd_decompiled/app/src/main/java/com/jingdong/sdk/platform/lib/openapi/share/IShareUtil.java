package com.jingdong.sdk.platform.lib.openapi.share;

import android.app.Activity;
import com.jingdong.sdk.platform.lib.entity.share.ShareItem;

/* loaded from: classes10.dex */
public interface IShareUtil {
    public static final String S_COPY_URL = "CopyURL";
    public static final String S_FACEBOOK = "Facebook";
    public static final String S_QQ_FRIENDS = "QQfriends";
    public static final String S_QQ_ZONE = "QQzone";
    public static final String S_QRCODE = "QRCode";
    public static final String S_SINA_WEIBO = "Sinaweibo";
    public static final String S_WX_FRIENDS = "Wxfriends";
    public static final String S_WX_MOMENTS = "Wxmoments";

    void panel(Activity activity, ShareItem shareItem);
}
