package com.jdpay.safekeyboard.edit;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jdpay.safekeyboard.R;
import com.jdpay.safekeyboard.keyboard.FinishCallback;

/* loaded from: classes18.dex */
public class SmsView extends LinearLayout {
    public static final String BUTTON_COLOR_BLUE = "blue";
    public static final String BUTTON_COLOR_RED = "red";
    private Button btn_reTry;
    private CountDownTimer countDonwer;
    private int defaultTime;
    private SmsEdit edit_sms;
    private View.OnClickListener innerClick;
    private View.OnClickListener outterClick;
    private LinearLayout root_layout;

    public SmsView(Context context) {
        super(context);
        this.defaultTime = 61;
        this.innerClick = new View.OnClickListener() { // from class: com.jdpay.safekeyboard.edit.SmsView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmsView.this.start();
                if (SmsView.this.outterClick != null) {
                    SmsView.this.outterClick.onClick(view);
                }
            }
        };
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.jdpay_edit_keyboard_sms_view_layout, this);
        SmsEdit smsEdit = (SmsEdit) findViewById(R.id.edit);
        this.edit_sms = smsEdit;
        smsEdit.setTextAlignment(1);
        this.edit_sms.setGravity(19);
        this.btn_reTry = (Button) findViewById(R.id.btn_reTry);
        this.root_layout = (LinearLayout) findViewById(R.id.root_layout);
        this.btn_reTry.setOnClickListener(this.innerClick);
        this.countDonwer = new CountDownTimer(this.defaultTime * 1000, 1000L) { // from class: com.jdpay.safekeyboard.edit.SmsView.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
                SmsView.this.btn_reTry.setText(SmsView.this.getResources().getString(R.string.jdpay_edit_keyboard_sms_retry_btn_content));
                SmsView.this.btn_reTry.setEnabled(true);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                SmsView.this.btn_reTry.setText(String.format(SmsView.this.getResources().getString(R.string.jdpay_edit_keyboard_sms_retry_btn_counter_content), Long.toString(j2 / 1000)));
                SmsView.this.btn_reTry.setEnabled(false);
            }
        };
    }

    @Deprecated
    private void setBtnColor(String str) {
        if ("blue".equalsIgnoreCase(str)) {
            this.btn_reTry.setTextColor(getResources().getColorStateList(R.color.jdpay_edit_keyboard_sms_btn_text_blue_color));
        } else if ("red".equalsIgnoreCase(str)) {
            this.btn_reTry.setTextColor(getResources().getColorStateList(R.color.jdpay_edit_keyboard_sms_btn_text_red_color));
            this.btn_reTry.setBackground(getResources().getDrawable(R.drawable.jdpay_edit_keyboard_sms_btn_bg));
        } else {
            this.btn_reTry.setTextColor(getResources().getColorStateList(R.color.jdpay_edit_keyboard_sms_btn_text_blue_color));
        }
    }

    @Deprecated
    private void setSmsBgColor(String str) {
        if ("blue".equalsIgnoreCase(str)) {
            this.root_layout.setBackground(getResources().getDrawable(R.drawable.jdpay_edit_keyboard_sms_bg));
        } else if ("red".equalsIgnoreCase(str)) {
            this.root_layout.setBackground(getResources().getDrawable(R.color.jdpay_edit_keyboard_bg));
        }
    }

    public boolean checkRegexMatch(String str) {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            return smsEdit.checkRegexMatch(str);
        }
        return false;
    }

    public void clearContent() {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            smsEdit.clearContent();
        }
    }

    public SmsEdit getEdit() {
        return this.edit_sms;
    }

    public String getEncryptContent() {
        SmsEdit smsEdit = this.edit_sms;
        return smsEdit != null ? smsEdit.getEncryptContent() : "";
    }

    public String getTempCipherText() {
        SmsEdit smsEdit = this.edit_sms;
        return smsEdit != null ? smsEdit.getTempCipherText() : "";
    }

    public void hideKeyboard() {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            smsEdit.hideKeyboard();
        }
    }

    public boolean isKeyboardShowing() {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            return smsEdit.isKeyboardShowing();
        }
        return false;
    }

    public void onDestroy() {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            smsEdit.onDestroy();
        }
    }

    public void setFinishCallback(FinishCallback finishCallback) {
        this.edit_sms.setFinishCallback(finishCallback);
    }

    public void setOutterClick(View.OnClickListener onClickListener) {
        this.outterClick = onClickListener;
    }

    public void showKeyboard() {
        SmsEdit smsEdit = this.edit_sms;
        if (smsEdit != null) {
            smsEdit.showKeyboard();
        }
    }

    public void start() {
        this.btn_reTry.setEnabled(false);
        this.countDonwer.start();
    }

    public void stop() {
        this.countDonwer.cancel();
        this.btn_reTry.setText(getResources().getString(R.string.jdpay_edit_keyboard_get_sms));
        this.btn_reTry.setEnabled(true);
    }

    public SmsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultTime = 61;
        this.innerClick = new View.OnClickListener() { // from class: com.jdpay.safekeyboard.edit.SmsView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmsView.this.start();
                if (SmsView.this.outterClick != null) {
                    SmsView.this.outterClick.onClick(view);
                }
            }
        };
        initView();
    }
}
