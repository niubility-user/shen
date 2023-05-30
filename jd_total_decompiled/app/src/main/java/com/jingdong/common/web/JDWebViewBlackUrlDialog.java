package com.jingdong.common.web;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import java.util.List;

/* loaded from: classes6.dex */
public class JDWebViewBlackUrlDialog extends JDDialog {
    public JDWebViewBlackUrlDialog(Context context, String str) {
        super(context);
        init(str);
    }

    private void init(String str) {
        setContentView(R.layout.jd_webview_balck_url);
        TextView textView = (TextView) findViewById(R.id.jd_webView_subTitle);
        Button button = (Button) findViewById(R.id.jd_webView_cancel_dialog);
        Button button2 = (Button) findViewById(R.id.jd_webView_chrome_open);
        ((TextView) findViewById(R.id.jd_webView_mainTitle)).setText("\u63d0\u793a");
        button.setText("\u53d6\u6d88");
        button2.setText("\u6d4f\u89c8\u5668\u6253\u5f00");
        final Uri parse = !TextUtils.isEmpty(str) ? Uri.parse(str) : null;
        String str2 = "\u60a8\u6b63\u5728\u8bbf\u95ee\u4e0d\u53d7\u6211\u4eec\u63a7\u5236\u7684\u9875\u9762\uff0c\u8bf7\u6ce8\u610f\u5b89\u5168 ";
        if (parse != null) {
            str2 = "\u60a8\u6b63\u5728\u8bbf\u95ee\u4e0d\u53d7\u6211\u4eec\u63a7\u5236\u7684\u9875\u9762\uff0c\u8bf7\u6ce8\u610f\u5b89\u5168 " + parse.getScheme() + "://" + parse.getHost();
        }
        textView.setText(str2);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.JDWebViewBlackUrlDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Uri uri = parse;
                if (uri != null) {
                    JDWebViewBlackUrlDialog.this.toBrowser(uri);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.JDWebViewBlackUrlDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDWebViewBlackUrlDialog.this.hide();
            }
        });
    }

    public boolean isIntentAvailable(Intent intent) {
        List<ResolveInfo> queryIntentActivities = JdSdk.getInstance().getApplication().getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    public Intent newBrowserIntent(Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (z) {
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
        intent.setFlags(268435456);
        return intent;
    }

    public void toBrowser(Uri uri) {
        Intent newBrowserIntent = newBrowserIntent(uri, true);
        try {
            if (isIntentAvailable(newBrowserIntent)) {
                if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
                    BaseFrameUtil.getInstance().getCurrentMyActivity().startActivityNoException(newBrowserIntent);
                }
            } else if (BaseFrameUtil.getInstance().getCurrentMyActivity() != null) {
                BaseFrameUtil.getInstance().getCurrentMyActivity().startActivityNoException(newBrowserIntent(uri, false));
            }
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
        }
    }
}
