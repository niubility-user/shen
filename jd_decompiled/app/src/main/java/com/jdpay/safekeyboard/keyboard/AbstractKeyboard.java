package com.jdpay.safekeyboard.keyboard;

import android.view.KeyEvent;
import android.widget.EditText;
import com.jdjr.generalKeyboard.common.JDJRResultMessage;

/* loaded from: classes18.dex */
public abstract class AbstractKeyboard {
    public static final int TYPE_POINT_CAN_FINISH = 5;
    public static final int TYPE_POINT_NO_FINISH = 6;
    public static final int TYPE_PURE_CAN_FINISH = 1;
    public static final int TYPE_PURE_NO_FINISH = 2;
    public static final int TYPE_TOTAL = 0;
    public static final int TYPE_X_CAN_FINISH = 3;
    public static final int TYPE_X_NO_FINISH = 4;

    /* loaded from: classes18.dex */
    public interface CallBack {
        void onDeleteAll();

        void onHide();

        void onInputAppend(String str);

        void onInputDelete();

        void onSure(String str);
    }

    public abstract boolean checkRegexFind(String str);

    public abstract boolean checkRegexMatch(String str);

    public abstract void clearContent();

    public abstract boolean dispatchKeyEvent(KeyEvent keyEvent);

    public abstract JDJRResultMessage getCryptoData();

    public abstract int getInputLength();

    public abstract float getKeyboardHeight();

    public abstract Object getReallKeyboard();

    public abstract byte[] getSourceData();

    public abstract String getTempCipherText();

    public abstract void hideKeyboard();

    public abstract void init();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isKeyboardShowing();

    public abstract void onDestroy();

    public abstract void releaseKeyboard();

    public abstract void setCallback(CallBack callBack);

    public abstract void setDefaultValue(String str);

    public abstract void setSystemShowSoftInputDisable(EditText editText);

    public abstract void show();
}
