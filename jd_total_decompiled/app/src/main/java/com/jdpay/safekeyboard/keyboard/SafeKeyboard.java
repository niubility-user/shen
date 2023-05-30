package com.jdpay.safekeyboard.keyboard;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.EditText;
import com.jdjr.generalKeyboard.GeneralBasicKeyboard;
import com.jdjr.generalKeyboard.common.BasicKeyboardCallback;
import com.jdjr.generalKeyboard.common.JDJRResultMessage;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import com.jdpay.safekeyboard.keyboard.AbstractKeyboard;
import java.lang.ref.WeakReference;

/* loaded from: classes18.dex */
public class SafeKeyboard extends AbstractKeyboard {
    private GeneralBasicKeyboard basicKeyboard;
    private final WeakReference<Activity> mActivity;
    private AbstractKeyboard.CallBack mCallBack;
    private String mColor;
    private int mEncrypte;
    private int mNumberLength;
    private int mType;
    private boolean okButtonEnable;
    private boolean showPlain;

    /* loaded from: classes18.dex */
    public static final class Builder {
        public static final String BLUE = "blue";
        public static final int ENCRYPTE = 1;
        public static final boolean HIDE = false;
        public static final int NO_ENCRYPTE = 0;
        public static final String RED = "red";
        public static final boolean SHOW = true;
        WeakReference<Activity> activity;
        int keyboardType = 6;
        int numberLength = 6;
        int encrypte = 0;
        String color = "red";
        public boolean showPlain = false;
        boolean isOKButtonEnable = false;

        public Builder(Activity activity) {
            this.activity = new WeakReference<>(activity);
        }

        public SafeKeyboard build() {
            return new SafeKeyboard(this);
        }

        public Builder setColor(String str) {
            this.color = str;
            return this;
        }

        public Builder setEncrypte(int i2) {
            this.encrypte = i2;
            return this;
        }

        public Builder setKeyboardType(int i2) {
            this.keyboardType = i2;
            return this;
        }

        public Builder setNumberLength(int i2) {
            this.numberLength = i2;
            return this;
        }

        public Builder setOKButtonEnable(boolean z) {
            this.isOKButtonEnable = z;
            return this;
        }

        public Builder setShowPlain(boolean z) {
            this.showPlain = z;
            return this;
        }
    }

    public SafeKeyboard(Builder builder) {
        this.mNumberLength = 6;
        this.mEncrypte = 0;
        this.showPlain = false;
        this.okButtonEnable = false;
        this.mActivity = builder.activity;
        this.mType = builder.keyboardType;
        this.mNumberLength = builder.numberLength;
        this.mEncrypte = builder.encrypte;
        this.mColor = builder.color;
        this.showPlain = builder.showPlain;
        this.okButtonEnable = builder.isOKButtonEnable;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean checkRegexFind(String str) {
        return this.basicKeyboard.checkRegexFind(str);
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean checkRegexMatch(String str) {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.checkRegexMatch(str);
        }
        return false;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void clearContent() {
        this.basicKeyboard.clearShownInput();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.dispatchKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public JDJRResultMessage getCryptoData() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.getCryptoData();
        }
        return new JDJRResultMessage("keyboard is null".getBytes(), "110");
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public int getInputLength() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.getInputLength();
        }
        return 0;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public float getKeyboardHeight() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.getKeyboardHeight();
        }
        return 0.0f;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public byte[] getSourceData() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            return generalBasicKeyboard.getSourceData();
        }
        return "data is null".getBytes();
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public String getTempCipherText() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        return generalBasicKeyboard != null ? generalBasicKeyboard.getTempCipherText() : "";
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void hideKeyboard() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.hide();
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void init() {
        int i2 = this.mType;
        if (i2 == 0) {
            GeneralBasicKeyboard generalBasicKeyboard = new GeneralBasicKeyboard(this.mActivity.get(), GeneralKeyboard.KeyboardModeBasic.BASE_TOTAL);
            this.basicKeyboard = generalBasicKeyboard;
            generalBasicKeyboard.setIsCipherMode(this.mEncrypte);
            this.basicKeyboard.setIsShownPlain(this.showPlain);
            this.basicKeyboard.setOKButtonEnabled(this.okButtonEnable);
            this.basicKeyboard.setBackgroundThemeResource(this.mColor);
            this.basicKeyboard.setBasicKeyboardCallback(new BasicKeyboardCallback() { // from class: com.jdpay.safekeyboard.keyboard.SafeKeyboard.1
                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onDeleteAll() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onDeleteAll();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onHide() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onHide();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onInputAppend(String str) {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onInputAppend(str);
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onInputDelete() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onInputDelete();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onSure(String str) {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onSure(str);
                    }
                }
            });
        } else if (i2 != 2) {
        } else {
            GeneralBasicKeyboard generalBasicKeyboard2 = new GeneralBasicKeyboard(this.mActivity.get(), GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH);
            this.basicKeyboard = generalBasicKeyboard2;
            generalBasicKeyboard2.setIsCipherMode(this.mEncrypte);
            this.basicKeyboard.setMaxInputLen(this.mNumberLength);
            this.basicKeyboard.setIsShownPlain(this.showPlain);
            this.basicKeyboard.setBasicKeyboardCallback(new BasicKeyboardCallback() { // from class: com.jdpay.safekeyboard.keyboard.SafeKeyboard.2
                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onDeleteAll() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onDeleteAll();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onHide() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onHide();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onInputAppend(String str) {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onInputAppend(str);
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onInputDelete() {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onInputDelete();
                    }
                }

                @Override // com.jdjr.generalKeyboard.common.BasicKeyboardCallback
                public void onSure(String str) {
                    if (SafeKeyboard.this.mCallBack != null) {
                        SafeKeyboard.this.mCallBack.onSure(str);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public boolean isKeyboardShowing() {
        return this.basicKeyboard.mIsKeyboardShown;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void onDestroy() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.hide();
            this.basicKeyboard.releaseCppKeyboard();
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void releaseKeyboard() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.releaseCppKeyboard();
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setCallback(AbstractKeyboard.CallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setDefaultValue(String str) {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.setDefaultValue(str);
        }
    }

    public void setKeyboardOKEnable(boolean z) {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.setOKButtonEnabled(z);
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void setSystemShowSoftInputDisable(EditText editText) {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard != null) {
            generalBasicKeyboard.setSystemShowSoftInputDisable(editText);
        }
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public void show() {
        GeneralBasicKeyboard generalBasicKeyboard = this.basicKeyboard;
        if (generalBasicKeyboard.mIsKeyboardShown) {
            return;
        }
        generalBasicKeyboard.show(this.mActivity.get());
    }

    @Override // com.jdpay.safekeyboard.keyboard.AbstractKeyboard
    public GeneralBasicKeyboard getReallKeyboard() {
        return this.basicKeyboard;
    }
}
