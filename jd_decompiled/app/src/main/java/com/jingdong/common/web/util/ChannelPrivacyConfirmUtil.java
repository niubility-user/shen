package com.jingdong.common.web.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jingdong.app.mall.privacy.JDPrivacyDialogInfo;
import com.jingdong.app.mall.privacy.JDPrivacyDialogUtil;
import com.jingdong.common.utils.WebViewHelper;

/* loaded from: classes12.dex */
public class ChannelPrivacyConfirmUtil {
    public static final String BABEL_ID_FORMAT = "babel_privacy_%s";
    public static final int MAX_TIMES = 1;
    public static final String PRIVACY_ID_PARAM = "privacyConfirmId";
    public static final String RN_FALLBACK_PARAM = "privacyConfirmShowed";
    public static final String RN_FALLBACK_VALUE = "1";
    private static final String SOURCE = "H5";

    /* loaded from: classes12.dex */
    public interface ChannelPrivacyCallback {
        void onAgree(boolean z);
    }

    public static boolean checkIsNeedPrivacyConfirm(Context context, String str, ChannelPrivacyCallback channelPrivacyCallback) {
        return checkPrivacyConfirmInternal(context, str, true, channelPrivacyCallback);
    }

    public static boolean checkIsNeedPrivacyConfirmWithoutDialog(String str) {
        return checkPrivacyConfirmInternal(null, str, false, null);
    }

    private static boolean checkPrivacyConfirmInternal(@Nullable Context context, String str, boolean z, ChannelPrivacyCallback channelPrivacyCallback) {
        if (!TextUtils.isEmpty(str) && (!z || (context instanceof Activity))) {
            try {
                Uri parse = Uri.parse(str);
                if ("1".equals(parse.getQueryParameter(RN_FALLBACK_PARAM))) {
                    return false;
                }
                String queryParameter = parse.getQueryParameter(PRIVACY_ID_PARAM);
                if (TextUtils.isEmpty(queryParameter)) {
                    queryParameter = getBusinessId(str);
                }
                if (z) {
                    if (showPrivacyDialog((Activity) context, str, queryParameter, channelPrivacyCallback) != null) {
                        return true;
                    }
                } else {
                    JDPrivacyDialogInfo jDPrivacyDialogInfo = new JDPrivacyDialogInfo();
                    jDPrivacyDialogInfo.build("H5", HybridUrlUtils.excludeQuery(str), queryParameter);
                    return jDPrivacyDialogInfo.hasTimes();
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static String getBusinessId(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String babelActivityId = WebViewHelper.getBabelActivityId(str);
        if (!TextUtils.isEmpty(babelActivityId)) {
            return String.format(BABEL_ID_FORMAT, babelActivityId);
        }
        return HybridUrlUtils.excludeQuery(str);
    }

    private static Dialog showPrivacyDialog(Activity activity, String str, String str2, final ChannelPrivacyCallback channelPrivacyCallback) {
        JDPrivacyDialogInfo jDPrivacyDialogInfo = new JDPrivacyDialogInfo();
        jDPrivacyDialogInfo.build("H5", HybridUrlUtils.excludeQuery(str), str2);
        return JDPrivacyDialogUtil.checkPrivacyDialog(activity, jDPrivacyDialogInfo, new JDPrivacyDialogUtil.IDialogListener() { // from class: com.jingdong.common.web.util.ChannelPrivacyConfirmUtil.1
            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onAgree() {
                ChannelPrivacyCallback channelPrivacyCallback2 = ChannelPrivacyCallback.this;
                if (channelPrivacyCallback2 != null) {
                    channelPrivacyCallback2.onAgree(true);
                }
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onDisagree() {
                ChannelPrivacyCallback channelPrivacyCallback2 = ChannelPrivacyCallback.this;
                if (channelPrivacyCallback2 != null) {
                    channelPrivacyCallback2.onAgree(false);
                }
            }

            @Override // com.jingdong.app.mall.privacy.JDPrivacyDialogUtil.IDialogListener
            public void onNotice(String str3) {
            }
        });
    }
}
