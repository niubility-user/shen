package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.TextFontUtils;
import com.jdjr.generalKeyboard.common.UIUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class VerifyCodeEditText extends LinearLayout implements View.OnClickListener {
    private static final int HANDLE_COUNT_DOWN = 7;
    private Button btnCountdown;
    private int countDown;
    public int countdownBgResource;
    public Drawable drCountdown;
    private EditText etVerifyCode;
    private Callback mCallback;
    private Context mContext;
    private String mCountButtonText;
    private MyHandler mHandler;
    private boolean mIsCountDownStart;
    private View mRootView;
    private RelativeLayout rlVerifyLayout;
    private String strSendAgain;
    private int totalCountDownSec;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public interface Callback {
        void onActionClick(View view);
    }

    /* loaded from: classes18.dex */
    public static class MyHandler extends Handler {
        WeakReference<VerifyCodeEditText> weakReference;

        MyHandler(VerifyCodeEditText verifyCodeEditText) {
            this.weakReference = new WeakReference<>(verifyCodeEditText);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VerifyCodeEditText verifyCodeEditText;
            super.handleMessage(message);
            if (message.what != 7 || (verifyCodeEditText = this.weakReference.get()) == null) {
                return;
            }
            if (verifyCodeEditText.countDown <= 1) {
                verifyCodeEditText.btnCountdown.setText(verifyCodeEditText.mContext.getResources().getString(R.string.security_resend));
                Drawable drawable = verifyCodeEditText.countdownBgResource == -1 ? verifyCodeEditText.drCountdown : verifyCodeEditText.mContext.getResources().getDrawable(verifyCodeEditText.countdownBgResource);
                if (Build.VERSION.SDK_INT >= 16) {
                    verifyCodeEditText.btnCountdown.setBackground(drawable);
                }
                verifyCodeEditText.btnCountdown.setTextColor(verifyCodeEditText.mContext.getResources().getColor(R.color.color_FFFFFF));
                verifyCodeEditText.mIsCountDownStart = false;
                verifyCodeEditText.countDown = verifyCodeEditText.totalCountDownSec;
                verifyCodeEditText.btnCountdown.setClickable(true);
                return;
            }
            VerifyCodeEditText.access$110(verifyCodeEditText);
            verifyCodeEditText.btnCountdown.setText(verifyCodeEditText.countDown + verifyCodeEditText.strSendAgain);
            sendEmptyMessageDelayed(7, 1000L);
        }
    }

    public VerifyCodeEditText(Context context) {
        this(context, null);
    }

    static /* synthetic */ int access$110(VerifyCodeEditText verifyCodeEditText) {
        int i2 = verifyCodeEditText.countDown;
        verifyCodeEditText.countDown = i2 - 1;
        return i2;
    }

    private void beginCountDown() {
        this.btnCountdown.setText(this.countDown + this.strSendAgain);
        if (KeyboardUiMode.isDark()) {
            this.btnCountdown.setBackgroundResource(R.drawable.security_verify_code_button_gray_bg_dark);
            this.btnCountdown.setTextColor(this.mContext.getResources().getColor(R.color.keyboard_color_countdown_btn_txt_dark));
        } else {
            this.btnCountdown.setBackgroundResource(R.drawable.security_verify_code_button_gray_bg);
            this.btnCountdown.setTextColor(this.mContext.getResources().getColor(R.color.keyboard_color_countdown_btn_txt));
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.sendEmptyMessageDelayed(7, 1000L);
        this.mIsCountDownStart = true;
        this.btnCountdown.setClickable(false);
    }

    public void endCountDown() {
        this.btnCountdown.setText(this.mCountButtonText);
        this.mIsCountDownStart = false;
        this.countDown = this.totalCountDownSec;
        this.mHandler.removeCallbacksAndMessages(null);
        this.btnCountdown.setClickable(true);
    }

    public void initCountDown(boolean z) {
        if (z) {
            if (this.mIsCountDownStart) {
                return;
            }
            beginCountDown();
            return;
        }
        endCountDown();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_countdown) {
            beginCountDown();
            this.mCallback.onActionClick(view);
        }
    }

    public void onInputChange(String str, boolean z) {
        if (str == null) {
            return;
        }
        if (z) {
            this.etVerifyCode.setInputType(2);
            if (str.length() > 0) {
                this.etVerifyCode.setTextSize(25.0f);
                Typeface uDC1BoldFont = TextFontUtils.getUDC1BoldFont(this.mContext);
                if (uDC1BoldFont != null) {
                    this.etVerifyCode.setTypeface(uDC1BoldFont);
                }
            } else {
                this.etVerifyCode.setTextSize(18.0f);
                this.etVerifyCode.setTypeface(Typeface.DEFAULT_BOLD);
            }
        } else {
            this.etVerifyCode.setInputType(18);
        }
        this.etVerifyCode.setText(str);
        this.etVerifyCode.setSelection(str.length());
    }

    public void refresh() {
        if (KeyboardUiMode.isDark()) {
            this.rlVerifyLayout.setBackgroundResource(R.drawable.security_verify_code_edittext_bg_dark);
            EditText editText = this.etVerifyCode;
            Resources resources = getResources();
            int i2 = R.color.keyboard_color_input_text_dark;
            editText.setTextColor(resources.getColor(i2));
            this.etVerifyCode.setHintTextColor(getResources().getColor(i2));
            this.btnCountdown.setBackgroundResource(R.drawable.security_verify_code_button_gray_bg_dark);
            this.btnCountdown.setTextColor(getResources().getColor(R.color.keyboard_color_countdown_btn_txt_dark));
            return;
        }
        this.rlVerifyLayout.setBackgroundResource(R.drawable.security_verify_code_edittext_bg);
        this.etVerifyCode.setTextColor(getResources().getColor(R.color.keyboard_color_input_text));
        this.etVerifyCode.setHintTextColor(getResources().getColor(R.color.color_FFDADADA));
        this.btnCountdown.setBackgroundResource(R.drawable.security_verify_code_button_gray_bg);
        this.btnCountdown.setTextColor(getResources().getColor(R.color.keyboard_color_countdown_btn_txt));
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setCountdownBgColor(String str) {
        if ("red".equals(str)) {
            this.countdownBgResource = R.drawable.security_verify_code_button_red_bg;
        } else if ("gold".equals(str)) {
            this.countdownBgResource = R.drawable.security_verify_code_button_gold_bg;
        } else if ("blue".equals(str)) {
            this.countdownBgResource = R.drawable.security_verify_code_button_blue_bg;
        } else {
            this.countdownBgResource = -1;
            this.drCountdown = UIUtils.getRightCornerDrawable(this.mContext, str);
        }
    }

    public void setCountdownDuration(int i2) {
        this.totalCountDownSec = i2;
        this.countDown = i2;
    }

    public void setHint(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.etVerifyCode.setHint(charSequence);
    }

    public void setSelection() {
        this.etVerifyCode.requestFocus();
        this.etVerifyCode.findFocus();
        EditText editText = this.etVerifyCode;
        editText.setSelection(TextUtils.isEmpty(editText.getText()) ? 0 : this.etVerifyCode.getText().length());
    }

    public void setTextTypeface(Typeface typeface) {
        if (typeface != null) {
            this.etVerifyCode.setTypeface(typeface);
        }
    }

    public VerifyCodeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerifyCodeEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.totalCountDownSec = 59;
        this.countDown = 59;
        int i3 = R.drawable.security_verify_code_button_blue_bg;
        this.countdownBgResource = i3;
        this.mContext = context;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.security_compoment_verify_code_edittext, (ViewGroup) this, true);
        this.mRootView = inflate;
        this.rlVerifyLayout = (RelativeLayout) inflate.findViewById(R.id.rl_verify_container);
        EditText editText = (EditText) this.mRootView.findViewById(R.id.et_verify_code);
        this.etVerifyCode = editText;
        editText.setTypeface(Typeface.DEFAULT_BOLD);
        this.etVerifyCode.requestFocus();
        this.etVerifyCode.findFocus();
        this.etVerifyCode.setOnTouchListener(new View.OnTouchListener() { // from class: com.jdjr.generalKeyboard.views.VerifyCodeEditText.1
            {
                VerifyCodeEditText.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                VerifyCodeEditText.this.etVerifyCode.requestFocus();
                VerifyCodeEditText.this.etVerifyCode.setSelection(TextUtils.isEmpty(VerifyCodeEditText.this.etVerifyCode.getText()) ? 0 : VerifyCodeEditText.this.etVerifyCode.getText().length());
                return true;
            }
        });
        Button button = (Button) this.mRootView.findViewById(R.id.btn_countdown);
        this.btnCountdown = button;
        button.setOnClickListener(this);
        this.mHandler = new MyHandler(this);
        this.strSendAgain = this.mContext.getString(R.string.security_s);
        this.mCountButtonText = this.mContext.getString(R.string.security_get_verify_code);
        this.drCountdown = this.mContext.getResources().getDrawable(i3);
        refresh();
    }

    public void setCountdownBgColor(int i2) {
        if (i2 < 0) {
            return;
        }
        this.countdownBgResource = i2;
    }
}
