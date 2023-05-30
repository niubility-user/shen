package com.jingdong.manto.ui.auth;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public class MantoDeviceAuthDialog extends MantoAuthDialog implements a.b {
    private String appName;
    private String authContent;
    private Button btAccept;
    private Button btReject;
    private MantoAuthDialog.Callback callback;
    private View contentView;
    private Context context;
    private TextView ivTitle;
    private TextView tvAuthContent;

    public MantoDeviceAuthDialog(@NonNull Context context, String str, String str2, @NonNull MantoAuthDialog.Callback callback) {
        super(context);
        this.context = context;
        this.callback = callback;
        this.appName = str;
        this.authContent = str2;
    }

    private void setDarkMode(int i2) {
        Button button;
        Resources resources;
        int i3;
        if (i2 == 0) {
            int color = getContext().getResources().getColor(R.color.manto_day_text_weight);
            int color2 = getContext().getResources().getColor(R.color.manto_day_text_light);
            this.contentView.setBackgroundResource(R.drawable.manto_auth_background);
            this.ivTitle.setTextColor(color);
            this.tvAuthContent.setTextColor(color);
            this.btReject.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_auth_reject_button));
            this.btReject.setTextColor(color2);
            button = this.btAccept;
            resources = getContext().getResources();
            i3 = R.drawable.manto_auth_accept_button;
        } else {
            int color3 = getContext().getResources().getColor(R.color.manto_dark_text_light);
            int color4 = getContext().getResources().getColor(R.color.manto_dark_text_weight);
            this.contentView.setBackgroundResource(R.drawable.manto_auth_background_dark);
            this.ivTitle.setTextColor(color4);
            this.tvAuthContent.setTextColor(color4);
            this.btReject.setBackgroundResource(R.drawable.manto_auth_reject_button_dark);
            this.btReject.setTextColor(color3);
            button = this.btAccept;
            resources = getContext().getResources();
            i3 = R.drawable.manto_auth_accept_button_dark;
        }
        button.setBackgroundDrawable(resources.getDrawable(i3));
    }

    @Override // com.jingdong.manto.ui.auth.MantoAuthDialog, android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        MantoAuthDialog.Callback callback = this.callback;
        if (callback != null) {
            callback.onCancel();
        }
        a.b().b(this);
    }

    void initView() {
        Window window = getWindow();
        this.contentView = View.inflate(this.context, R.layout.manto_auth_device_layout, null);
        if (window != null) {
            window.requestFeature(1);
        }
        setContentView(this.contentView);
        if (window != null) {
            window.setLayout(MantoDensityUtils.dip2pixel(getContext(), R2.attr.SimpleEnablePureScrollMode), -2);
            window.setBackgroundDrawableResource(17170445);
        }
        setCanceledOnTouchOutside(false);
        this.ivTitle = (TextView) this.contentView.findViewById(R.id.iv_title);
        TextView textView = (TextView) this.contentView.findViewById(R.id.tv_auth_content);
        this.tvAuthContent = textView;
        textView.setText(String.format("%s\u4e3a\u4e86\u7ed9\u60a8\u63d0\u4f9b\u5b8c\u6574\u670d\u52a1\uff0c\u60f3%s\uff0c\u662f\u5426\u5141\u8bb8\uff1f", this.appName, this.authContent));
        Button button = (Button) this.contentView.findViewById(R.id.bt_reject);
        this.btReject = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.contentView.findViewById(R.id.bt_accept);
        this.btAccept = button2;
        button2.setOnClickListener(this);
        a.b().a(this);
    }

    @Override // com.jingdong.manto.ui.auth.MantoAuthDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_accept) {
            this.callback.onAccept();
        } else if (id == R.id.bt_reject) {
            this.callback.onReject();
        } else {
            this.callback.onCancel();
        }
        dismiss();
        a.b().b(this);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        setDarkMode(i2);
    }
}
