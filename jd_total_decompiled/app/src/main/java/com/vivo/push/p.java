package com.vivo.push;

import android.net.Uri;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes11.dex */
public final class p {
    public static final Uri a = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/config");
    public static final Uri b = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/permission");

    /* renamed from: c  reason: collision with root package name */
    public static final Uri f18289c = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/clientState");
    public static final Uri d = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/debugInfo");

    public static String a(int i2) {
        switch (i2) {
            case 2002:
                return "method_alias_bind";
            case 2003:
                return "method_alias_unbind";
            case 2004:
                return "method_tag_bind";
            case 2005:
                return "method_tag_unbind";
            case 2006:
                return "method_sdk_bind";
            case 2007:
                return "method_sdk_unbind";
            case R2.attr.textAppearanceHeadline5 /* 2008 */:
                return "method_stop";
            default:
                return null;
        }
    }
}
