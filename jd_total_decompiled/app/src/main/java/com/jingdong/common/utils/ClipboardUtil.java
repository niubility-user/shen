package com.jingdong.common.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.widget.ToastUtils;

/* loaded from: classes6.dex */
public class ClipboardUtil {
    public static void clipContent(Context context, String str, String str2) {
        ClipboardManager clipboardManager;
        if (context != null) {
            clipboardManager = (ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
        } else {
            clipboardManager = (ClipboardManager) JdSdk.getInstance().getApplication().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
        }
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(str, str2));
        }
    }

    public static void clipContentWithToast(Context context, String str, String str2, String str3) {
        clipContent(context, str, str2);
        ToastUtils.shortToast(str3);
    }
}
