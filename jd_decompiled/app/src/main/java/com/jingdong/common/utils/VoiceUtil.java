package com.jingdong.common.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class VoiceUtil {
    private static final String TAG = "VoiceUtil";
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    public static void handleVoiceResult(IMyActivity iMyActivity, Intent intent, final WebView webView) {
        ArrayList<String> stringArrayListExtra;
        int size;
        if (OKLog.D) {
            OKLog.d(TAG, " handleVoiceResult -->> ");
        }
        if (iMyActivity == null || intent == null || webView == null || (size = (stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS")).size()) <= 0) {
            return;
        }
        final String[] strArr = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            strArr[i2] = stringArrayListExtra.get(i2);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(iMyActivity.getThisActivity());
        builder.setTitle(com.jingdong.jdsdk.res.StringUtil.voice_search_please_choose);
        builder.setItems(strArr, new DialogInterface.OnClickListener() { // from class: com.jingdong.common.utils.VoiceUtil.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                String str = strArr[i3];
                if (OKLog.D) {
                    OKLog.d(VoiceUtil.TAG, " handleVoiceResult -->> item : " + str);
                }
                webView.loadUrl("javascript:speechInputCallBack('" + str + "')");
                dialogInterface.dismiss();
            }
        });
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.VoiceUtil.5
            @Override // java.lang.Runnable
            public void run() {
                AlertDialog.Builder builder2 = builder;
                if (builder2 != null) {
                    builder2.show();
                }
            }
        });
    }

    public static void showVoiceDialog(Context context) {
        if (OKLog.D) {
            OKLog.d(TAG, " showVoiceDialog -->> ");
        }
        if (context instanceof Activity) {
            final BaseActivity baseActivity = (BaseActivity) context;
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0);
            if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                if (OKLog.D) {
                    OKLog.d(TAG, " showVoiceDialog -->> list.size : " + queryIntentActivities.size());
                }
                startRecognizeActivty(baseActivity);
                return;
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(com.jingdong.jdsdk.res.StringUtil.voice_search_title);
            builder.setMessage(com.jingdong.jdsdk.res.StringUtil.voice_search_message_hint);
            builder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.utils.VoiceUtil.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    BaseActivity.this.startActivityNoException(new Intent("android.intent.action.VIEW", Uri.parse("https://union.m.jd.com/download/go.action?to=http%3A%2F%2Fapp.jd.com%2Fdownload%2Fandroid%2Fvoice.apk&client=android&unionId=12532&key=eb5ba3c113b616165e3d763a1f0ce731")));
                    dialogInterface.dismiss();
                }
            });
            builder.setNegativeButton("\u53d6\u6d88", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.utils.VoiceUtil.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            baseActivity.post(new Runnable() { // from class: com.jingdong.common.utils.VoiceUtil.3
                @Override // java.lang.Runnable
                public void run() {
                    builder.show();
                }
            });
        }
    }

    private static void startRecognizeActivty(BaseActivity baseActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, " startRecognizeActivty -->> ");
        }
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("android.speech.extra.PROMPT", "");
        baseActivity.startActivityForResultNoException(intent, 1234);
    }
}
