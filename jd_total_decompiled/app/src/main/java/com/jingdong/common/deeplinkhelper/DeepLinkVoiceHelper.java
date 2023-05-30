package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.voice.JDVoiceInputActivity;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkVoiceHelper {
    private static final String HOST_SPEECH_RECOGNIZER = "jdvoiceinputactivity";

    public static void start(Context context) {
        if (context == null) {
            if (OKLog.D) {
                OKLog.d("DeepLinkVoiceHelper", "context\u4e3anull");
                return;
            }
            return;
        }
        String name = context.getClass().getName();
        Bundle bundle = new Bundle();
        bundle.putString("className", name);
        Intent intent = new Intent(context, JDVoiceInputActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Deprecated
    public static void startVoiceInput(Context context) {
        if (context == null) {
            if (OKLog.D) {
                OKLog.d("DeepLinkVoiceHelper", "context\u4e3anull");
            }
        } else if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(32))) {
            String name = context.getClass().getName();
            Bundle bundle = new Bundle();
            bundle.putString("className", name);
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_SPEECH_RECOGNIZER).toString(), bundle);
        }
    }

    public static void start(Context context, String str) {
        if (context == null) {
            if (OKLog.D) {
                OKLog.d("DeepLinkVoiceHelper", "context\u4e3anull");
                return;
            }
            return;
        }
        String name = context.getClass().getName();
        Bundle bundle = new Bundle();
        bundle.putString("className", name);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        bundle.putString("fromType", str);
        Intent intent = new Intent(context, JDVoiceInputActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
