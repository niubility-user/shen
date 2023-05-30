package com.jdpay.safekeyboard.edit;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.safekeyboard.R;
import com.jdpay.safekeyboard.keyboard.AbstractKeyboard;
import com.jdpay.safekeyboard.keyboard.FinishCallback;
import com.jdpay.safekeyboard.keyboard.KeyboardController;
import com.jdpay.system.SystemInfo;

/* loaded from: classes18.dex */
public class SmsEdit extends AppCompatEditText {
    private String REX_MEG;
    private FinishCallback finishCallback;
    private KeyboardController keyboardControler;
    private View layout_root;
    private AbstractKeyboard.CallBack mCallBack;
    private Context mContext;
    private int mHeight;
    private int mWidth;
    private int passwordNumber;

    public SmsEdit(Context context) {
        super(context);
        this.passwordNumber = 6;
        this.REX_MEG = "^[0-9]*$";
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.SmsEdit.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (SmsEdit.this.layout_root != null) {
                    SmsEdit smsEdit = SmsEdit.this;
                    smsEdit.scrollTo(0, smsEdit.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                if (SmsEdit.this.keyboardControler.getInputLength() != SmsEdit.this.passwordNumber || SmsEdit.this.finishCallback == null) {
                    return;
                }
                SmsEdit.this.finishCallback.onFinish(new String(SmsEdit.this.keyboardControler.getCryptoData().getResultString()));
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onFinish(str);
                }
            }
        };
        this.mContext = context;
        init();
    }

    private void check() {
        post(new Runnable() { // from class: com.jdpay.safekeyboard.edit.SmsEdit.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SmsEdit.this.layout_root = ((ViewGroup) ContextHelper.getActivity(SmsEdit.this.getContext()).findViewById(16908290)).getChildAt(0);
                    float bottom = SmsEdit.this.layout_root.getBottom();
                    float keyboardHeight = SmsEdit.this.keyboardControler.getKeyboardHeight();
                    SmsEdit.this.getLocationInWindow(new int[2]);
                    if (bottom - keyboardHeight <= r2[1]) {
                        SmsEdit smsEdit = SmsEdit.this;
                        smsEdit.scrollTo((int) (((r2[1] + 200) - bottom) + keyboardHeight), smsEdit.layout_root);
                    }
                } catch (Exception e2) {
                    Log.getStackTraceString(e2);
                }
            }
        });
    }

    private void init() {
        SpannableString spannableString = new SpannableString(getContext().getString(R.string.jdpay_widget_edit_keyboard_input_sms));
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, spannableString.length(), 33);
        setHint(spannableString);
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/UDC1.05-Bold.otf"));
        setBackgroundDrawable(null);
        setSelected(true);
        KeyboardController keyboardController = new KeyboardController(ContextHelper.getActivity(getContext()), KeyboardController.KeyboardType.SAFEKEYBOARD, 0);
        this.keyboardControler = keyboardController;
        keyboardController.init();
        this.keyboardControler.setCallback(this.mCallBack);
        this.keyboardControler.setSystemShowSoftInputDisable(this);
    }

    public boolean checkRegexMatch(String str) {
        return this.keyboardControler.checkRegexMatch(str);
    }

    public void clearContent() {
        this.keyboardControler.clearContent();
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && this.keyboardControler.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public int dp2px(float f2) {
        return (int) ((f2 * SystemInfo.getDensity()) + 0.5f);
    }

    public String getEncryptContent() {
        return new String(this.keyboardControler.getCryptoData().getResultString());
    }

    public float getKeyboardHeight() {
        return this.keyboardControler.getKeyboardHeight();
    }

    public String getTempCipherText() {
        return this.keyboardControler.getTempCipherText();
    }

    public void hideKeyboard() {
        this.keyboardControler.hideKeyboard();
    }

    public boolean isKeyboardShowing() {
        KeyboardController keyboardController = this.keyboardControler;
        if (keyboardController != null) {
            return keyboardController.isKeyboardShowing();
        }
        return false;
    }

    public void onDestroy() {
        this.keyboardControler.onDestroy();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        this.keyboardControler.setDefaultValue(getText().toString());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.keyboardControler.show();
        check();
        return super.onTouchEvent(motionEvent);
    }

    public void scrollTo(int i2, View view) {
        if (i2 > 100) {
            view.scrollTo(0, i2);
        } else {
            view.scrollTo(0, 0);
        }
    }

    public void setFinishCallback(FinishCallback finishCallback) {
        this.finishCallback = finishCallback;
    }

    public void showKeyboard() {
        this.keyboardControler.show();
        check();
    }

    public SmsEdit(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.passwordNumber = 6;
        this.REX_MEG = "^[0-9]*$";
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.SmsEdit.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (SmsEdit.this.layout_root != null) {
                    SmsEdit smsEdit = SmsEdit.this;
                    smsEdit.scrollTo(0, smsEdit.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                if (SmsEdit.this.keyboardControler.getInputLength() != SmsEdit.this.passwordNumber || SmsEdit.this.finishCallback == null) {
                    return;
                }
                SmsEdit.this.finishCallback.onFinish(new String(SmsEdit.this.keyboardControler.getCryptoData().getResultString()));
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (SmsEdit.this.finishCallback != null) {
                    SmsEdit.this.finishCallback.onFinish(str);
                }
            }
        };
        this.mContext = context;
        init();
    }
}
