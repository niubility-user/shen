package com.jingdong.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;

/* loaded from: classes6.dex */
public class WXEntryReqJump {
    private static final String TAG = "WXEntryActivity.WXEntryReqJump";

    public static void onWXEntryReq(Context context, BaseReq baseReq) {
        if (context == null || baseReq == null || !(baseReq instanceof ShowMessageFromWX.Req)) {
            return;
        }
        Bundle bundle = new Bundle();
        ((ShowMessageFromWX.Req) baseReq).toBundle(bundle);
        String string = bundle.getString("_wxobject_message_ext");
        if (OKLog.D) {
            OKLog.d(TAG, " onWXEntryReq   req:  " + baseReq);
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            Uri parse = Uri.parse(string);
            String scheme = parse.getScheme();
            if (!TextUtils.isEmpty(scheme) && JumpUtil.isJdScheme(scheme)) {
                if (OKLog.D) {
                    OKLog.d(TAG, " onWXEntryReq   openapp: " + string);
                }
                context.startActivity(new Intent("android.intent.action.VIEW", parse));
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, " WXEntryReqJump_UrlNoMatch   not openapp: " + string);
            }
            ExceptionReporter.reportWebViewCommonError("WXEntryReqJump_UrlNoMatch", string, "weixin jumpUrl not match", "the url which weixin jump to jd is not matchable, url: " + string);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (OKLog.E) {
                OKLog.e(TAG, " WXEntryReqJump_UrlError   Exception  \u65e0\u6cd5\u89e3\u6790 =>> " + string + "   | " + e2);
            }
            ExceptionReporter.reportWebViewCommonError("WXEntryReqJump_UrlError", string, "weixin jumpUrl error", "the url which weixin jump to jd is invalid, detail:  " + e2);
        }
    }
}
