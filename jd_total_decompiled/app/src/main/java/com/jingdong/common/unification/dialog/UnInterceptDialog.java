package com.jingdong.common.unification.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.UnAppUtils;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class UnInterceptDialog extends Dialog implements View.OnClickListener {
    private View.OnClickListener cancelListener;
    private View.OnClickListener confirmListener;
    private Context context;
    private String downloadUrl;
    private String openAppUrl;

    public UnInterceptDialog(@NonNull Context context, String str, String str2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context);
        this.openAppUrl = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"sourceValue\":\"weixin\",\"sourceType\":\"awake\",\"des\":\"HomePage\"}";
        this.downloadUrl = "https://wqs.jd.com/downloadApp/download.html?channel=jd-m";
        this.context = context;
        if (!TextUtils.isEmpty(str)) {
            this.openAppUrl = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.downloadUrl = str2;
        }
        this.cancelListener = onClickListener;
        this.confirmListener = onClickListener2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.jd_dialog_pos_button) {
            View.OnClickListener onClickListener = this.cancelListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        } else if (id == R.id.jd_dialog_neg_button) {
            View.OnClickListener onClickListener2 = this.confirmListener;
            if (onClickListener2 != null) {
                onClickListener2.onClick(view);
            }
            if (UnAppUtils.jdIsInstalled(getContext())) {
                Context context = this.context;
                if (context instanceof Activity) {
                    UnAppUtils.startJDWithUrl((Activity) context, this.openAppUrl);
                }
            } else {
                try {
                    this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.downloadUrl)));
                } catch (Exception e2) {
                    if (UnLog.D) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        cancel();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.un_base_dialog_intercept);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(17170445);
        }
        setCanceledOnTouchOutside(false);
        findViewById(R.id.jd_dialog_neg_button).setOnClickListener(this);
        findViewById(R.id.jd_dialog_pos_button).setOnClickListener(this);
    }

    public void setCancelListener(View.OnClickListener onClickListener) {
        this.cancelListener = onClickListener;
    }

    public void setConfirmListener(View.OnClickListener onClickListener) {
        this.confirmListener = onClickListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setOpenAppUrl(String str) {
        this.openAppUrl = str;
    }

    public UnInterceptDialog(@NonNull Context context) {
        super(context);
        this.openAppUrl = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"sourceValue\":\"weixin\",\"sourceType\":\"awake\",\"des\":\"HomePage\"}";
        this.downloadUrl = "https://wqs.jd.com/downloadApp/download.html?channel=jd-m";
        this.context = context;
    }
}
