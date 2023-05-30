package com.jdpay.safekeyboard.keyboard;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.EditText;
import com.jdjr.generalKeyboard.common.JDJRResultMessage;
import com.jdpay.safekeyboard.keyboard.AbstractKeyboard;
import com.jdpay.safekeyboard.keyboard.SafeKeyboard;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class KeyboardController extends AbstractKeyboard {
    private AbstractKeyboard.CallBack callBack;
    private FinishCallback finishCallback;
    private AbstractKeyboard keyboard;
    private WeakReference<Activity> mActivity;

    /* renamed from: com.jdpay.safekeyboard.keyboard.KeyboardController$1  reason: invalid class name */
    /* loaded from: classes18.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jdpay$safekeyboard$keyboard$KeyboardController$KeyboardType;

        static {
            int[] iArr = new int[KeyboardType.values().length];
            $SwitchMap$com$jdpay$safekeyboard$keyboard$KeyboardController$KeyboardType = iArr;
            try {
                iArr[KeyboardType.SAFEKEYBOARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jdpay$safekeyboard$keyboard$KeyboardController$KeyboardType[KeyboardType.SAFEKEYBOARDLONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jdpay$safekeyboard$keyboard$KeyboardController$KeyboardType[KeyboardType.OTHERKEYBOARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes18.dex */
    public enum KeyboardType {
        SAFEKEYBOARD,
        SAFEKEYBOARDLONG,
        OTHERKEYBOARD
    }

    public KeyboardController(Activity activity, KeyboardType keyboardType, int i2) {
        this.mActivity = new WeakReference<>(activity);
        int i3 = AnonymousClass1.$SwitchMap$com$jdpay$safekeyboard$keyboard$KeyboardController$KeyboardType[keyboardType.ordinal()];
        if (i3 == 1) {
            this.keyboard = new SafeKeyboard.Builder(this.mActivity.get()).setKeyboardType(2).setColor("red").setEncrypte(i2).setNumberLength(6).setShowPlain(false).build();
        } else if (i3 == 2) {
            this.keyboard = new SafeKeyboard.Builder(this.mActivity.get()).setKeyboardType(0).setColor("red").setEncrypte(i2).setOKButtonEnable(true).setShowPlain(false).build();
        } else if (i3 != 3) {
            this.keyboard = new SafeKeyboard.Builder(this.mActivity.get()).setKeyboardType(2).setColor("red").setEncrypte(1).setNumberLength(6).setShowPlain(true).build();
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean checkRegexFind(String str) {
        return this.keyboard.checkRegexFind(str);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean checkRegexMatch(String str) {
        return this.keyboard.checkRegexMatch(str);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void clearContent() {
        this.keyboard.clearContent();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.keyboard.dispatchKeyEvent(keyEvent);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public JDJRResultMessage getCryptoData() {
        return this.keyboard.getCryptoData();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public int getInputLength() {
        return this.keyboard.getInputLength();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public float getKeyboardHeight() {
        return this.keyboard.getKeyboardHeight();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public Object getReallKeyboard() {
        return this.keyboard.getReallKeyboard();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public byte[] getSourceData() {
        return this.keyboard.getSourceData();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public String getTempCipherText() {
        return this.keyboard.getTempCipherText();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void hideKeyboard() {
        this.keyboard.hideKeyboard();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void init() {
        this.keyboard.init();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean isKeyboardShowing() {
        return this.keyboard.isKeyboardShowing();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void onDestroy() {
        this.keyboard.onDestroy();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void releaseKeyboard() {
        this.keyboard.releaseKeyboard();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setCallback(AbstractKeyboard.CallBack callBack) {
        this.keyboard.setCallback(callBack);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setDefaultValue(String str) {
        this.keyboard.setDefaultValue(str);
    }

    public void setFinishCallback(FinishCallback finishCallback) {
        this.finishCallback = finishCallback;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setSystemShowSoftInputDisable(EditText editText) {
        this.keyboard.setSystemShowSoftInputDisable(editText);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void show() {
        this.keyboard.show();
    }
}
