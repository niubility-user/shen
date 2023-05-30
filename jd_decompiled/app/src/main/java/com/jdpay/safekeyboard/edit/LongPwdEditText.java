package com.jdpay.safekeyboard.edit;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcelable;
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
import com.jdpay.safekeyboard.keyboard.FinishLongCallback;
import com.jdpay.safekeyboard.keyboard.KeyboardController;
import com.jdpay.system.SystemInfo;

/* loaded from: classes18.dex */
public class LongPwdEditText extends AppCompatEditText {
    private FinishLongCallback finishCallback;
    private KeyboardController keyboardControler;
    private View layout_root;
    private AbstractKeyboard.CallBack mCallBack;
    private Context mContext;
    private int mHeight;
    private int mWidth;

    public LongPwdEditText(Context context) {
        super(context);
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.LongPwdEditText.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (LongPwdEditText.this.layout_root != null) {
                    LongPwdEditText longPwdEditText = LongPwdEditText.this;
                    longPwdEditText.scrollTo(0, longPwdEditText.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onInputAppend(str);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onFinish(str);
                }
            }
        };
        this.mContext = context;
        init();
    }

    private void check() {
        post(new Runnable() { // from class: com.jdpay.safekeyboard.edit.LongPwdEditText.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LongPwdEditText.this.layout_root = ((ViewGroup) ContextHelper.getActivity(LongPwdEditText.this.getContext()).findViewById(16908290)).getChildAt(0);
                    float bottom = LongPwdEditText.this.layout_root.getBottom();
                    float keyboardHeight = LongPwdEditText.this.keyboardControler.getKeyboardHeight();
                    LongPwdEditText.this.getLocationInWindow(new int[2]);
                    if (bottom - keyboardHeight <= r2[1]) {
                        LongPwdEditText longPwdEditText = LongPwdEditText.this;
                        longPwdEditText.scrollTo((int) (((r2[1] + 200) - bottom) + keyboardHeight), longPwdEditText.layout_root);
                    }
                } catch (Exception e2) {
                    Log.getStackTraceString(e2);
                }
            }
        });
    }

    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/UDC1.05-Bold.otf"));
        setBackgroundDrawable(getResources().getDrawable(R.drawable.jdpay_edit_keyboard_sms_bg));
        setSelected(false);
        KeyboardController keyboardController = new KeyboardController(ContextHelper.getActivity(getContext()), KeyboardController.KeyboardType.SAFEKEYBOARDLONG, 1);
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

    public int getInputLength() {
        KeyboardController keyboardController = this.keyboardControler;
        if (keyboardController != null) {
            return keyboardController.getInputLength();
        }
        return 0;
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

    @Override // android.widget.TextView
    public boolean onTextContextMenuItem(int i2) {
        if (i2 == 16908322) {
            return true;
        }
        return super.onTextContextMenuItem(i2);
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

    public void setFinishCallback(FinishLongCallback finishLongCallback) {
        this.finishCallback = finishLongCallback;
    }

    public void showKeyboard() {
        this.keyboardControler.show();
        check();
    }

    public LongPwdEditText(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCallBack = new AbstractKeyboard.CallBack() { // from class: com.jdpay.safekeyboard.edit.LongPwdEditText.1
            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onDeleteAll() {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onDeleteAll();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onHide() {
                if (LongPwdEditText.this.layout_root != null) {
                    LongPwdEditText longPwdEditText = LongPwdEditText.this;
                    longPwdEditText.scrollTo(0, longPwdEditText.layout_root);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputAppend(String str) {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onInputAppend(str);
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onInputDelete() {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onInputDelete();
                }
            }

            @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard.CallBack
            public void onSure(String str) {
                if (LongPwdEditText.this.finishCallback != null) {
                    LongPwdEditText.this.finishCallback.onFinish(str);
                }
            }
        };
        this.mContext = context;
        init();
    }
}
