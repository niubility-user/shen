package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecurityFunctionalCommonPwdBinding;
import com.jdjr.dns.databinding.SecurityFunctionalPaymentBinding;
import com.jdjr.dns.databinding.SecurityFunctionalSixSquareBinding;
import com.jdjr.dns.databinding.SecurityFunctionalSixUnderlineBinding;
import com.jdjr.dns.databinding.SecurityFunctionalVerifyCodeBinding;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.FlexibleEditText;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import com.jdjr.generalKeyboard.views.VerifyCodeEditText;

/* loaded from: classes18.dex */
public class FunctionalInputView extends FrameLayout implements View.OnClickListener {
    public ActionCallback actionCallback;
    private CountDownTimer countDownTimer;
    private int countdownDuration;
    private GeneralKeyboard.KeyboardModeFunctional currentKeyboardMode;
    private FlexibleEditText flexibleEditText;
    public View inputView;
    private Context mContext;
    private SixInputLayout sixInputLayout;
    private TextView tvActionLeft;
    private TextView tvActionMiddle;
    private TextView tvActionRight;
    private TextView tvTips;
    private View vVerticalLine;
    private VerifyCodeEditText verifyCodeEditText;

    /* loaded from: classes18.dex */
    public interface ActionCallback {
        void onButtonActionClick(View view, int i2);

        void onTextActionClick(View view, int i2);
    }

    public FunctionalInputView(@NonNull Context context, GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional) {
        super(context);
        this.countdownDuration = 60;
        this.mContext = context;
        this.currentKeyboardMode = keyboardModeFunctional;
        initViews();
    }

    private void beginCountdown() {
        if (this.countDownTimer == null) {
            return;
        }
        if (this.mContext.getResources().getString(R.string.security_get_verify_code).equals(this.tvActionRight.getText().toString()) || this.mContext.getResources().getString(R.string.security_resend).equals(this.tvActionRight.getText().toString())) {
            this.countDownTimer.start();
        }
    }

    private void handleFlexibleEditAction() {
        FlexibleEditText flexibleEditText = this.flexibleEditText;
        if (flexibleEditText == null) {
            return;
        }
        flexibleEditText.setTag(this.currentKeyboardMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_PAYMENT ? "payment" : "pwd");
        this.flexibleEditText.setCallback(new FlexibleEditText.Callback() { // from class: com.jdjr.generalKeyboard.views.FunctionalInputView.2
            @Override // com.jdjr.generalKeyboard.views.FlexibleEditText.Callback
            public void onDelClick(View view) {
                FunctionalInputView.this.actionCallback.onButtonActionClick(null, 400);
            }

            @Override // com.jdjr.generalKeyboard.views.FlexibleEditText.Callback
            public void onEyeClick(View view, boolean z) {
                FunctionalInputView.this.actionCallback.onButtonActionClick(view, 500);
            }
        });
    }

    private void handleVerifyCodeEditAction() {
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText == null) {
            return;
        }
        verifyCodeEditText.setCallback(new VerifyCodeEditText.Callback() { // from class: com.jdjr.generalKeyboard.views.FunctionalInputView.1
            @Override // com.jdjr.generalKeyboard.views.VerifyCodeEditText.Callback
            public void onActionClick(View view) {
                FunctionalInputView.this.actionCallback.onButtonActionClick(view, 600);
            }
        });
    }

    private void initCountdown(long j2) {
        this.countDownTimer = new CountDownTimer(j2 * 1000, 1000L) { // from class: com.jdjr.generalKeyboard.views.FunctionalInputView.3
            @Override // android.os.CountDownTimer
            public void onFinish() {
                FunctionalInputView.this.setCountText();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j3) {
                Resources resources;
                int i2;
                if (FunctionalInputView.this.tvActionRight == null) {
                    return;
                }
                TextView textView = FunctionalInputView.this.tvActionRight;
                if (KeyboardUiMode.isDark()) {
                    resources = FunctionalInputView.this.getResources();
                    i2 = R.color.keyboard_color_action_text_dark;
                } else {
                    resources = FunctionalInputView.this.getResources();
                    i2 = R.color.keyboard_color_action_text;
                }
                textView.setTextColor(resources.getColor(i2));
                FunctionalInputView.this.tvActionRight.setClickable(false);
                FunctionalInputView.this.tvActionRight.setText((j3 / 1000) + " \u79d2");
            }
        };
    }

    private void initSubViews() {
        View view = this.inputView;
        if (view != null) {
            this.tvTips = (TextView) view.findViewById(R.id.tv_tips);
            this.tvActionLeft = (TextView) this.inputView.findViewById(R.id.tv_action_left);
            this.tvActionMiddle = (TextView) this.inputView.findViewById(R.id.tv_action_middle);
            this.tvActionRight = (TextView) this.inputView.findViewById(R.id.tv_action_right);
            this.vVerticalLine = this.inputView.findViewById(R.id.v_vertical_line);
            handleFlexibleEditAction();
            handleVerifyCodeEditAction();
            this.tvActionLeft.setOnClickListener(this);
            this.tvActionMiddle.setOnClickListener(this);
            this.tvActionRight.setOnClickListener(this);
            this.inputView.setOnClickListener(this);
        }
    }

    private void initViews() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentKeyboardMode;
        if (keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE) {
            SecurityFunctionalSixSquareBinding securityFunctionalSixSquareBinding = (SecurityFunctionalSixSquareBinding) DataBindingUtil.inflate(from, R.layout.security_functional_six_square, null, false);
            this.inputView = securityFunctionalSixSquareBinding.getRoot();
            this.sixInputLayout = securityFunctionalSixSquareBinding.sixInput;
            KeyboardUiMode.setKeyboardBindings(securityFunctionalSixSquareBinding);
        } else if (keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE) {
            SecurityFunctionalSixUnderlineBinding securityFunctionalSixUnderlineBinding = (SecurityFunctionalSixUnderlineBinding) DataBindingUtil.inflate(from, R.layout.security_functional_six_underline, null, false);
            this.inputView = securityFunctionalSixUnderlineBinding.getRoot();
            this.sixInputLayout = securityFunctionalSixUnderlineBinding.sixInput;
            KeyboardUiMode.setKeyboardBindings(securityFunctionalSixUnderlineBinding);
        } else if (keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_PAYMENT) {
            SecurityFunctionalPaymentBinding securityFunctionalPaymentBinding = (SecurityFunctionalPaymentBinding) DataBindingUtil.inflate(from, R.layout.security_functional_payment, null, false);
            this.inputView = securityFunctionalPaymentBinding.getRoot();
            this.flexibleEditText = securityFunctionalPaymentBinding.flexibleEditText;
            KeyboardUiMode.setKeyboardBindings(securityFunctionalPaymentBinding);
        } else if (keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE) {
            SecurityFunctionalVerifyCodeBinding securityFunctionalVerifyCodeBinding = (SecurityFunctionalVerifyCodeBinding) DataBindingUtil.inflate(from, R.layout.security_functional_verify_code, null, false);
            this.inputView = securityFunctionalVerifyCodeBinding.getRoot();
            this.verifyCodeEditText = securityFunctionalVerifyCodeBinding.verifyCodeEditText;
            KeyboardUiMode.setKeyboardBindings(securityFunctionalVerifyCodeBinding);
        } else {
            SecurityFunctionalCommonPwdBinding securityFunctionalCommonPwdBinding = (SecurityFunctionalCommonPwdBinding) DataBindingUtil.inflate(from, R.layout.security_functional_common_pwd, null, false);
            this.inputView = securityFunctionalCommonPwdBinding.getRoot();
            this.flexibleEditText = securityFunctionalCommonPwdBinding.flexibleEditText;
            KeyboardUiMode.setKeyboardBindings(securityFunctionalCommonPwdBinding);
        }
        initSubViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCountText() {
        TextView textView = this.tvActionRight;
        if (textView == null) {
            return;
        }
        textView.setText("\u91cd\u65b0\u53d1\u9001");
        this.tvActionRight.setClickable(true);
        this.tvActionRight.setTextColor(Color.parseColor("#4D7BFE"));
    }

    private void setSplitLineVisible() {
        if (this.tvActionMiddle.getVisibility() == 0 && this.tvActionRight.getVisibility() == 0) {
            this.vVerticalLine.setVisibility(0);
        } else {
            this.vVerticalLine.setVisibility(4);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tv_action_left) {
            this.actionCallback.onTextActionClick(view, 100);
        } else if (view.getId() == R.id.tv_action_middle) {
            this.actionCallback.onTextActionClick(view, 200);
        } else if (view.getId() == R.id.tv_action_right) {
            this.actionCallback.onTextActionClick(view, 300);
            beginCountdown();
        }
    }

    public void onInputDataChange(String str, boolean z) {
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentKeyboardMode;
        if (keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE && keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE) {
            if (keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_PAYMENT && keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_COMMON_PWD) {
                this.verifyCodeEditText.onInputChange(str, z);
                return;
            } else {
                this.flexibleEditText.onInputChange(str, z);
                return;
            }
        }
        this.sixInputLayout.onInputChange(str, z);
    }

    public void release() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.countDownTimer = null;
        }
    }

    public View setActionText(CharSequence charSequence, int i2) {
        TextView textView = this.tvActionLeft;
        if (i2 == 200) {
            textView = this.tvActionMiddle;
        } else if (i2 == 300) {
            textView = this.tvActionRight;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(0);
            textView.setText(charSequence);
            if (this.mContext.getResources().getString(R.string.security_get_verify_code).equals(this.tvActionRight.getText().toString()) || this.mContext.getResources().getString(R.string.security_resend).equals(this.tvActionRight.getText().toString())) {
                initCountdown(this.countdownDuration);
            }
        } else {
            textView.setVisibility(4);
        }
        setSplitLineVisible();
        return textView;
    }

    public void setCountdownBgColor(String str) {
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.setCountdownBgColor(str);
        }
    }

    public void setCountdownDuration(int i2) {
        this.countdownDuration = i2;
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.setCountdownDuration(i2 - 1);
        }
    }

    public void setCountdownStatus(boolean z) {
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.initCountDown(z);
        }
        if (z) {
            beginCountdown();
        }
        if (z) {
            this.actionCallback.onButtonActionClick(null, 600);
        }
    }

    public void setDescriptionText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.tvTips.setText(charSequence);
            this.tvTips.setVisibility(0);
            return;
        }
        this.tvTips.setVisibility(4);
    }

    public void setInputHint(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        FlexibleEditText flexibleEditText = this.flexibleEditText;
        if (flexibleEditText != null) {
            flexibleEditText.setHint(charSequence);
        }
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.setHint(charSequence);
        }
    }

    public void setInputSelection() {
        FlexibleEditText flexibleEditText = this.flexibleEditText;
        if (flexibleEditText != null) {
            flexibleEditText.setSelection();
        }
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.setSelection();
        }
    }

    public void setTopActionCallback(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    public void showAt(View view) {
        if (view == null || !(view instanceof ViewGroup)) {
            return;
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 < viewGroup.getChildCount()) {
                viewGroup.removeView(viewGroup.getChildAt(i2));
                i2++;
            } else {
                view.setLayoutParams(view.getLayoutParams());
                viewGroup.addView(this.inputView);
                return;
            }
        }
    }

    public void stopCountdown() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            setCountText();
        }
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.endCountDown();
        }
    }

    public void setCountdownBgColor(int i2) {
        VerifyCodeEditText verifyCodeEditText = this.verifyCodeEditText;
        if (verifyCodeEditText != null) {
            verifyCodeEditText.setCountdownBgColor(i2);
        }
    }

    public FunctionalInputView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.countdownDuration = 60;
    }

    public FunctionalInputView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.countdownDuration = 60;
    }
}
