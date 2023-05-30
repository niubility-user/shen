package com.jdjr.generalKeyboard;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.ColorRes;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecurityGeneralBasicKeyboardBinding;
import com.jdjr.generalKeyboard.common.AnimatorUtils;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.BasicNumberKeyboard;
import com.jdjr.generalKeyboard.views.BasicTotalKeyboard;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import com.jdjr.generalKeyboard.views.KeyboardView;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.reflect.Method;

/* loaded from: classes18.dex */
public class GeneralBasicKeyboard extends GeneralKeyboard {
    private static final String TAG = "GeneralBasicKeyboard";
    private SecurityGeneralBasicKeyboardBinding basicKeyboardBinding;
    private KeyboardView currentKeyboardView;
    private GeneralKeyboard.KeyboardModeBasic currentMode;
    private EditText etCurrentEditText;
    public LinearLayout llBasicKeyboard;
    private StringBuilder strText;

    public GeneralBasicKeyboard(Context context, GeneralKeyboard.KeyboardModeBasic keyboardModeBasic) {
        this(context, keyboardModeBasic, false);
    }

    private void baseInputChange(String str) {
        EditText editText = this.etCurrentEditText;
        if (editText == null) {
            return;
        }
        try {
            int selectionStart = editText.getSelectionStart();
            if (!TextUtils.isEmpty(str)) {
                this.strText.insert(selectionStart, str);
                selectionStart++;
            } else if (this.strText.length() > 0 && selectionStart - 1 >= 0) {
                this.strText.deleteCharAt(selectionStart);
            }
            this.etCurrentEditText.setText(this.strText);
            EditText editText2 = this.etCurrentEditText;
            if (selectionStart < 0) {
                selectionStart = 0;
            }
            editText2.setSelection(selectionStart);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setCurrentKeyboardLayout(KeyboardView keyboardView) {
        keyboardView.showAt(this.llBasicKeyboard);
        keyboardView.setKeyboardViewCallback(new KeyboardView.KeyboardViewCallback() { // from class: com.jdjr.generalKeyboard.GeneralBasicKeyboard.1
            {
                GeneralBasicKeyboard.this = this;
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onHide(View view) {
                GeneralBasicKeyboard.this.hide();
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyDelete(View view) {
                if (GeneralBasicKeyboard.this.etCurrentEditText == null) {
                    GeneralBasicKeyboard.this.handleBaseKeyboardDelete(-1);
                } else if (GeneralBasicKeyboard.this.etCurrentEditText.getSelectionStart() > 0) {
                    GeneralBasicKeyboard generalBasicKeyboard = GeneralBasicKeyboard.this;
                    generalBasicKeyboard.handleBaseKeyboardDelete(generalBasicKeyboard.etCurrentEditText.getSelectionStart());
                }
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyDeleteAll(View view) {
                GeneralBasicKeyboard.this.clearShownInput();
                ((GeneralKeyboard) GeneralBasicKeyboard.this).basicKeyboardCallback.onDeleteAll();
                try {
                    if (GeneralBasicKeyboard.this.etCurrentEditText != null) {
                        GeneralBasicKeyboard.this.etCurrentEditText.setText("");
                        GeneralBasicKeyboard.this.strText.delete(0, GeneralBasicKeyboard.this.strText.length());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyInput(View view) {
                if (GeneralBasicKeyboard.this.getInputLength() < GeneralBasicKeyboard.this.getMaxInputLen()) {
                    String str = view.getTag() == null ? "" : (String) view.getTag();
                    if (GeneralBasicKeyboard.this.etCurrentEditText == null) {
                        GeneralBasicKeyboard.this.handleBaseKeyboardAppend(str, -1, true);
                        return;
                    }
                    GeneralBasicKeyboard generalBasicKeyboard = GeneralBasicKeyboard.this;
                    generalBasicKeyboard.handleBaseKeyboardAppend(str, generalBasicKeyboard.etCurrentEditText.getSelectionStart(), true);
                }
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onSure(View view) {
                ((GeneralKeyboard) GeneralBasicKeyboard.this).basicKeyboardCallback.onSure(GeneralBasicKeyboard.this.getCryptoData().getResultString());
            }
        });
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void clearShownInput() {
        super.clearShownInput();
        try {
            EditText editText = this.etCurrentEditText;
            if (editText != null) {
                editText.setText("");
                StringBuilder sb = this.strText;
                sb.delete(0, sb.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String getJDJRCipherText() {
        try {
            if ("00000".equals(getCryptoData().getErrorCode())) {
                String resultString = getCryptoData().getResultString();
                return resultString.substring(resultString.indexOf(95) + 1);
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public float getKeyboardHeight() {
        Context context = this.mContext;
        if (context == null) {
            return 0.0f;
        }
        return context.getResources().getDimension(R.dimen.security_keyboard_base_popup_transY);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void handleBasicAppendCallback(String str, boolean z, boolean z2) {
        if (this.basicKeyboardCallback != null) {
            if (z) {
                baseInputChange(str);
            } else {
                baseInputChange(str.length() > 1 ? str : ProxyConfig.MATCH_ALL_SCHEMES);
            }
        }
        super.handleBasicAppendCallback(str, z, z2);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void handleBasicDeleteCallback() {
        baseInputChange("");
        super.handleBasicDeleteCallback();
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void hide() {
        this.basicKeyboardCallback.onHide();
        KeyboardView keyboardView = this.currentKeyboardView;
        if (keyboardView instanceof BasicTotalKeyboard) {
            ((BasicTotalKeyboard) keyboardView).dismissEnlargeKeyView();
        }
        if (this.mIsKeyboardShown) {
            AnimatorUtils.initDownAnimator(this.llBasicKeyboard, this.mRootView, getResources().getDimension(R.dimen.security_keyboard_base_popup_transY));
        }
        super.hide();
    }

    protected void initLayout() {
        SecurityGeneralBasicKeyboardBinding securityGeneralBasicKeyboardBinding = (SecurityGeneralBasicKeyboardBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.security_general_basic_keyboard, null, false);
        this.basicKeyboardBinding = securityGeneralBasicKeyboardBinding;
        KeyboardUiMode.setKeyboardBindings(securityGeneralBasicKeyboardBinding);
        FrameLayout frameLayout = (FrameLayout) this.basicKeyboardBinding.getRoot();
        this.mRootView = frameLayout;
        this.llBasicKeyboard = this.basicKeyboardBinding.llBasicKeyboard;
        ViewParent parent = frameLayout.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).setBackgroundResource(17170445);
        }
        calculateButtonDimen();
        if (this.currentMode == GeneralKeyboard.KeyboardModeBasic.BASE_TOTAL) {
            this.currentKeyboardView = new BasicTotalKeyboard(this.mContext);
        } else {
            BasicNumberKeyboard basicNumberKeyboard = new BasicNumberKeyboard(this.mContext);
            this.currentKeyboardView = basicNumberKeyboard;
            basicNumberKeyboard.setKeyboardMode(this.currentMode);
        }
        setCurrentKeyboardLayout(this.currentKeyboardView);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void releaseCppKeyboard() {
        super.releaseCppKeyboard();
        KeyboardUiMode.clearBindings();
    }

    public void setBackgroundThemeColor(String... strArr) {
        if (strArr == null || strArr.length < 1) {
            return;
        }
        this.currentKeyboardView.setSureBackgroundResource(strArr[0], strArr.length > 1 ? strArr[1] : null, strArr.length > 2 ? strArr[2] : null);
    }

    public void setBackgroundThemeResource(String str) {
        if ("red".equals(str) || "gold".equals(str)) {
            this.currentKeyboardView.setSureBackgroundResource(str);
        }
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setCryptoAlg(String str) {
        this.cryptoAlg = str;
        super.setCryptoAlg(str);
    }

    public void setDefaultValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        clearShownInput();
        EditText editText = this.etCurrentEditText;
        handleBaseKeyboardAppend(str, editText == null ? 0 : editText.getSelectionStart(), false);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setOKButtonEnabled(boolean z) {
        this.currentKeyboardView.setSureEnabled(z);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setOkButtonText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.currentKeyboardView.setSureText(charSequence);
    }

    public void setSystemShowSoftInputDisable(EditText editText) {
        if (editText == null) {
            return;
        }
        this.etCurrentEditText = editText;
        try {
            Method method = EditText.class.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(editText, Boolean.FALSE);
        } catch (Exception unused) {
        }
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void show(Activity activity) {
        if (this.mIsKeyboardShown) {
            return;
        }
        super.show(activity);
        AnimatorUtils.initUpAnimator(this.llBasicKeyboard, getResources().getDimension(R.dimen.security_keyboard_base_popup_transY));
        this.mIsKeyboardShown = true;
    }

    public void showKeyPressBg(boolean z) {
        KeyboardView keyboardView = this.currentKeyboardView;
        if (keyboardView != null) {
            keyboardView.showKeyPressBg(z);
        }
    }

    public void switchCapsLock(boolean z) {
        KeyboardView keyboardView = this.currentKeyboardView;
        if (keyboardView == null || !(keyboardView instanceof BasicTotalKeyboard)) {
            return;
        }
        ((BasicTotalKeyboard) keyboardView).switchCapsLock(z);
    }

    public GeneralBasicKeyboard(Context context, GeneralKeyboard.KeyboardModeBasic keyboardModeBasic, boolean z) {
        super(context, z);
        this.currentMode = keyboardModeBasic;
        this.strText = new StringBuilder();
        initLayout();
    }

    public void setBackgroundThemeResource(@ColorRes int... iArr) {
        this.currentKeyboardView.setSureBackgroundResource(iArr);
    }
}
