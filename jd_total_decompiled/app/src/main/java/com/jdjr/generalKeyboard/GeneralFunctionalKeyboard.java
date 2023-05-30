package com.jdjr.generalKeyboard;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecurityGeneralFunctionalKeyboardBinding;
import com.jdjr.dns.databinding.SecurityLayoutFunctionalTileBinding;
import com.jdjr.generalKeyboard.common.AnimatorUtils;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.BasicNumberKeyboard;
import com.jdjr.generalKeyboard.views.BasicTotalKeyboard;
import com.jdjr.generalKeyboard.views.FunctionalInputView;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import com.jdjr.generalKeyboard.views.KeyboardView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes18.dex */
public class GeneralFunctionalKeyboard extends GeneralKeyboard {
    public static final int ACTION_BUTTON_DELETE = 400;
    public static final int ACTION_BUTTON_EYE = 500;
    public static final int ACTION_BUTTON_VERIFY_CODE = 600;
    public static final int ACTION_TEXT_LEFT = 100;
    public static final int ACTION_TEXT_MIDDLE = 200;
    public static final int ACTION_TEXT_RIGHT = 300;
    private static final String TAG = "GeneralFunctional";
    private ActionCallback actionCallback;
    private Button btnBack;
    private Button btnClose;
    private Button btnEye;
    View.OnClickListener clickListener;
    private CombinedCallback combinedCallback;
    private GeneralKeyboard.KeyboardModeBasic currentBasicMode;
    private GeneralKeyboard.KeyboardModeFunctional currentFunctionalMode;
    private boolean isAuto;
    private KeyboardView keyboardView;
    private LinearLayout llContainer;
    private LinearLayout llFunctionLayout;
    private LinearLayout llKeyboardLayout;
    private RelativeLayout loadingLayout;
    View.OnTouchListener onTouchListener;
    private RelativeLayout rlTitleLayout;
    private FunctionalInputView topInputLayout;
    private TextView tvTitle;

    /* loaded from: classes18.dex */
    public interface ActionCallback {
        void onActionPerform(View view, int i2);
    }

    /* loaded from: classes18.dex */
    public interface CombinedCallback {
        void onClick(GeneralKeyboard.FunctionalActionType functionalActionType);
    }

    public GeneralFunctionalKeyboard(Context context, GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional, GeneralKeyboard.KeyboardModeBasic keyboardModeBasic) {
        this(context, keyboardModeFunctional, keyboardModeBasic, false);
    }

    public void clickEye(View view) {
        initCryptoArgument();
        boolean z = !Boolean.parseBoolean(view.getTag().toString());
        view.setTag(Boolean.valueOf(z));
        view.setSelected(z);
        if (z) {
            setIsShownPlain(true);
        } else {
            setIsShownPlain(false);
        }
        this.topInputLayout.onInputDataChange(getCurrentData(), z);
    }

    private void initData() {
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentFunctionalMode;
        if (keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE || keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE) {
            setMaxInputLen(6);
        }
    }

    public boolean judgeSymbolKey(String str) {
        StringBuilder sb;
        GeneralKeyboard.KeyboardModeBasic keyboardModeBasic = this.currentBasicMode;
        if (keyboardModeBasic != GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_CAN_FINISH && keyboardModeBasic != GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_NO_FINISH) {
            return ((keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_CAN_FINISH || keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_NO_FINISH) && (sb = this.mSB) != null && sb.length() > 0 && str.equals(JshopConst.JSHOP_PROMOTIO_X) && this.mSB.toString().contains(JshopConst.JSHOP_PROMOTIO_X)) ? false : true;
        }
        StringBuilder sb2 = this.mSB;
        if ((sb2 == null || sb2.length() == 0) && str.equals(OrderISVUtil.MONEY_DECIMAL)) {
            return false;
        }
        StringBuilder sb3 = this.mSB;
        return sb3 == null || sb3.length() <= 0 || !str.equals(OrderISVUtil.MONEY_DECIMAL) || !this.mSB.toString().contains(OrderISVUtil.MONEY_DECIMAL);
    }

    public void refreshFinishButton() {
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentFunctionalMode;
        boolean z = keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE || keyboardModeFunctional == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE;
        GeneralKeyboard.KeyboardModeBasic keyboardModeBasic = this.currentBasicMode;
        if (keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_NO_FINISH || keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_NO_FINISH || keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH) {
            return;
        }
        if (z) {
            setOKButtonEnabled(getInputLength() == 6);
        } else {
            setOKButtonEnabled(getInputLength() > 0);
        }
    }

    private void setUpFunctionLayout() {
        FunctionalInputView functionalInputView = new FunctionalInputView(this.mContext, this.currentFunctionalMode);
        this.topInputLayout = functionalInputView;
        functionalInputView.showAt(this.llFunctionLayout);
        this.topInputLayout.setTopActionCallback(new FunctionalInputView.ActionCallback() { // from class: com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.3
            {
                GeneralFunctionalKeyboard.this = this;
            }

            @Override // com.jdjr.generalKeyboard.views.FunctionalInputView.ActionCallback
            public void onButtonActionClick(View view, int i2) {
                if (i2 == 400) {
                    GeneralFunctionalKeyboard.this.clearKeyboard();
                } else if (i2 == 500) {
                    GeneralFunctionalKeyboard.this.btnEye = (Button) view;
                    GeneralFunctionalKeyboard.this.clickEye(view);
                } else if (i2 == 600) {
                    GeneralFunctionalKeyboard.this.setActionClick(GeneralKeyboard.FunctionalActionType.GET_VERIFY_CODE, "00000");
                }
            }

            @Override // com.jdjr.generalKeyboard.views.FunctionalInputView.ActionCallback
            public void onTextActionClick(View view, int i2) {
                if (GeneralFunctionalKeyboard.this.actionCallback != null) {
                    GeneralFunctionalKeyboard.this.actionCallback.onActionPerform(view, i2);
                }
            }
        });
    }

    private void setUpKeyboardLayout() {
        GeneralKeyboard.KeyboardModeBasic keyboardModeBasic = this.currentBasicMode;
        if (keyboardModeBasic == null) {
            if (this.currentFunctionalMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_COMMON_PWD) {
                this.keyboardView = new BasicTotalKeyboard(this.mContext);
            } else {
                BasicNumberKeyboard basicNumberKeyboard = new BasicNumberKeyboard(this.mContext);
                this.keyboardView = basicNumberKeyboard;
                basicNumberKeyboard.setKeyboardMode(GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_CAN_FINISH);
            }
        } else if (keyboardModeBasic == GeneralKeyboard.KeyboardModeBasic.BASE_TOTAL) {
            this.keyboardView = new BasicTotalKeyboard(this.mContext);
        } else {
            BasicNumberKeyboard basicNumberKeyboard2 = new BasicNumberKeyboard(this.mContext);
            this.keyboardView = basicNumberKeyboard2;
            basicNumberKeyboard2.setKeyboardMode(this.currentBasicMode);
        }
        this.keyboardView.showAt(this.llKeyboardLayout);
        this.keyboardView.setKeyboardViewCallback(new KeyboardView.KeyboardViewCallback() { // from class: com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.4
            {
                GeneralFunctionalKeyboard.this = this;
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onHide(View view) {
                GeneralFunctionalKeyboard.this.hide();
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyDelete(View view) {
                String currentDeleteData = GeneralFunctionalKeyboard.this.getCurrentDeleteData();
                FunctionalInputView functionalInputView = GeneralFunctionalKeyboard.this.topInputLayout;
                if (currentDeleteData == null) {
                    currentDeleteData = "";
                }
                functionalInputView.onInputDataChange(currentDeleteData, ((GeneralKeyboard) GeneralFunctionalKeyboard.this).mIsPlainText);
                GeneralFunctionalKeyboard.this.refreshFinishButton();
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyDeleteAll(View view) {
                GeneralFunctionalKeyboard.this.topInputLayout.onInputDataChange("", ((GeneralKeyboard) GeneralFunctionalKeyboard.this).mIsPlainText);
                GeneralFunctionalKeyboard.this.clearKeyboard();
                GeneralFunctionalKeyboard.this.setActionClick(GeneralKeyboard.FunctionalActionType.DELETE_ALL, "00000");
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onKeyInput(View view) {
                if (GeneralFunctionalKeyboard.this.getInputLength() == GeneralFunctionalKeyboard.this.getMaxInputLen()) {
                    return;
                }
                String str = view.getTag() == null ? "" : (String) view.getTag();
                if (((OrderISVUtil.MONEY_DECIMAL.equals(str) || str.equalsIgnoreCase(JshopConst.JSHOP_PROMOTIO_X)) && !GeneralFunctionalKeyboard.this.judgeSymbolKey(str)) || GeneralFunctionalKeyboard.this.keepTwoAfterDot()) {
                    return;
                }
                boolean z = GeneralFunctionalKeyboard.this.currentBasicMode == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_NO_FINISH || GeneralFunctionalKeyboard.this.currentBasicMode == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_NO_FINISH || GeneralFunctionalKeyboard.this.currentBasicMode == GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH;
                boolean z2 = GeneralFunctionalKeyboard.this.currentFunctionalMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE || GeneralFunctionalKeyboard.this.currentFunctionalMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE;
                GeneralFunctionalKeyboard.this.topInputLayout.onInputDataChange(GeneralFunctionalKeyboard.this.getCurrentAppendData(str), ((GeneralKeyboard) GeneralFunctionalKeyboard.this).mIsPlainText);
                GeneralFunctionalKeyboard.this.refreshFinishButton();
                if (z && z2 && GeneralFunctionalKeyboard.this.getInputLength() == 6) {
                    new Handler().postDelayed(new Runnable() { // from class: com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.4.1
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            GeneralFunctionalKeyboard.this.setActionClick(GeneralKeyboard.FunctionalActionType.FINISH, "00000");
                        }
                    }, 50L);
                }
            }

            @Override // com.jdjr.generalKeyboard.views.KeyboardView.KeyboardViewCallback
            public void onSure(View view) {
                GeneralFunctionalKeyboard.this.setActionClick(GeneralKeyboard.FunctionalActionType.FINISH, "00000");
            }
        });
    }

    public void clearKeyboard() {
        super.clearShownInput();
        this.topInputLayout.onInputDataChange("", this.mIsPlainText);
        refreshFinishButton();
    }

    public void closeEye() {
        if (this.btnEye != null) {
            setIsShownPlain(false);
            this.btnEye.setSelected(false);
            this.btnEye.setContentDescription(getResources().getString(R.string.security_eye_closed));
        }
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void hide() {
        setLoadingCancel();
        clearKeyboard();
        closeEye();
        GeneralKeyboard.FunctionalActionType functionalActionType = GeneralKeyboard.FunctionalActionType.HIDE;
        setActionClick(functionalActionType, "00000");
        if (this.currentFunctionalMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE) {
            this.topInputLayout.setCountdownStatus(false);
        }
        CombinedCallback combinedCallback = this.combinedCallback;
        if (combinedCallback != null) {
            combinedCallback.onClick(functionalActionType);
        } else {
            if (this.mIsKeyboardShown) {
                AnimatorUtils.initDownAnimator(this.llContainer, this.mRootView, getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY));
            }
            super.hide();
        }
        stopCountdown();
    }

    protected void initLayout() {
        SecurityGeneralFunctionalKeyboardBinding securityGeneralFunctionalKeyboardBinding = (SecurityGeneralFunctionalKeyboardBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.security_general_functional_keyboard, null, false);
        KeyboardUiMode.setKeyboardBindings(securityGeneralFunctionalKeyboardBinding);
        FrameLayout frameLayout = (FrameLayout) securityGeneralFunctionalKeyboardBinding.getRoot();
        this.mRootView = frameLayout;
        frameLayout.setOnTouchListener(this.onTouchListener);
        this.rlTitleLayout = (RelativeLayout) securityGeneralFunctionalKeyboardBinding.layoutTitle.getRoot();
        this.llFunctionLayout = securityGeneralFunctionalKeyboardBinding.llFunctionLayout;
        this.llKeyboardLayout = securityGeneralFunctionalKeyboardBinding.llKeyboardLayout;
        this.loadingLayout = (RelativeLayout) securityGeneralFunctionalKeyboardBinding.loadingLayout.getRoot();
        this.llContainer = securityGeneralFunctionalKeyboardBinding.keyboardContainer;
        SecurityLayoutFunctionalTileBinding securityLayoutFunctionalTileBinding = securityGeneralFunctionalKeyboardBinding.layoutTitle;
        this.tvTitle = securityLayoutFunctionalTileBinding.tvTitle;
        Button button = securityLayoutFunctionalTileBinding.btnBack;
        this.btnBack = button;
        this.btnClose = securityLayoutFunctionalTileBinding.btnClose;
        button.setOnClickListener(this.clickListener);
        this.btnClose.setOnClickListener(this.clickListener);
        this.loadingLayout.setOnClickListener(this.clickListener);
        this.rlTitleLayout.setOnClickListener(this.clickListener);
        this.llFunctionLayout.setOnClickListener(this.clickListener);
        calculateButtonDimen();
        setUpFunctionLayout();
        setUpKeyboardLayout();
    }

    protected boolean keepTwoAfterDot() {
        StringBuilder sb;
        if (this.currentFunctionalMode == GeneralKeyboard.KeyboardModeFunctional.FUNCTION_PAYMENT && (sb = this.mSB) != null && sb.toString().contains(OrderISVUtil.MONEY_DECIMAL)) {
            return this.mSB.toString().substring(this.mSB.toString().indexOf(OrderISVUtil.MONEY_DECIMAL) + 1).length() == 2;
        }
        return false;
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void releaseCppKeyboard() {
        super.releaseCppKeyboard();
        this.topInputLayout.release();
        KeyboardUiMode.clearBindings();
    }

    public void setActionClick(GeneralKeyboard.FunctionalActionType functionalActionType, String str) {
        GeneralKeyboard.FunctionalKeyboardCallback functionalKeyboardCallback = this.functionalKeyboardCallback;
        if (functionalKeyboardCallback != null) {
            functionalKeyboardCallback.onActionClick(functionalActionType, str);
        }
    }

    public View setActionText(CharSequence charSequence, int i2, ActionCallback actionCallback) {
        View actionText = this.topInputLayout.setActionText(charSequence, i2);
        this.actionCallback = actionCallback;
        return actionText;
    }

    public void setBackVisibility(int i2) {
        this.btnBack.setVisibility(i2);
    }

    public void setBackgroundThemeResource(String str) {
        this.keyboardView.setSureBackgroundResource(str);
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentFunctionalMode;
        if (keyboardModeFunctional == null || keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE) {
            return;
        }
        this.topInputLayout.setCountdownBgColor(str);
    }

    public void setCombinedCallback(CombinedCallback combinedCallback) {
        this.combinedCallback = combinedCallback;
    }

    public void setCountdown() {
        if (this.isAuto) {
            this.topInputLayout.setCountdownStatus(true);
        }
    }

    public void setCountdownDuration(int i2) {
        this.topInputLayout.setCountdownDuration(i2);
    }

    public void setCountdownStatus(boolean z) {
        this.isAuto = z;
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setCryptoAlg(String str) {
        this.cryptoAlg = str;
        super.setCryptoAlg(str);
    }

    public void setDescription(CharSequence charSequence) {
        this.topInputLayout.setDescriptionText(charSequence);
    }

    public void setHintText(CharSequence charSequence) {
        this.topInputLayout.setInputHint(charSequence);
    }

    public void setLoadingCancel() {
        this.loadingLayout.setVisibility(8);
        this.llFunctionLayout.setVisibility(0);
        this.llKeyboardLayout.setVisibility(0);
    }

    public void setLoadingShow() {
        this.loadingLayout.setVisibility(0);
        this.llFunctionLayout.setVisibility(8);
        this.llKeyboardLayout.setVisibility(8);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setOKButtonEnabled(boolean z) {
        this.keyboardView.setSureEnabled(z);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void setOkButtonText(CharSequence charSequence) {
        this.keyboardView.setSureText(charSequence);
    }

    public void setSelection() {
        this.topInputLayout.setInputSelection();
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.tvTitle.setText(charSequence);
    }

    @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard
    public void show(Activity activity) {
        super.show(activity);
        if (!this.mIsKeyboardShown) {
            AnimatorUtils.initUpAnimator(this.llContainer, getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY));
        }
        this.mIsKeyboardShown = true;
        this.topInputLayout.setInputSelection();
        setCountdown();
    }

    public void showKeyPressBg(boolean z) {
        KeyboardView keyboardView = this.keyboardView;
        if (keyboardView != null) {
            keyboardView.showKeyPressBg(z);
        }
    }

    public void stopCountdown() {
        this.topInputLayout.stopCountdown();
    }

    public GeneralFunctionalKeyboard(Context context, GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional, GeneralKeyboard.KeyboardModeBasic keyboardModeBasic, boolean z) {
        super(context, z);
        this.keyboardView = null;
        this.isAuto = false;
        this.clickListener = new View.OnClickListener() { // from class: com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.1
            {
                GeneralFunctionalKeyboard.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.btn_back) {
                    if (GeneralFunctionalKeyboard.this.loadingLayout.getVisibility() == 8 && ((GeneralKeyboard) GeneralFunctionalKeyboard.this).functionalKeyboardCallback != null) {
                        ((GeneralKeyboard) GeneralFunctionalKeyboard.this).functionalKeyboardCallback.onActionClick(GeneralKeyboard.FunctionalActionType.BACK, "00000");
                    } else {
                        GeneralFunctionalKeyboard.this.setLoadingCancel();
                    }
                    GeneralFunctionalKeyboard.this.closeEye();
                } else if (view.getId() == R.id.btn_close) {
                    GeneralFunctionalKeyboard.this.hide();
                }
            }
        };
        this.onTouchListener = new View.OnTouchListener() { // from class: com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.2
            {
                GeneralFunctionalKeyboard.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                GeneralFunctionalKeyboard generalFunctionalKeyboard = GeneralFunctionalKeyboard.this;
                if (generalFunctionalKeyboard.getCurrentRect(generalFunctionalKeyboard.llContainer).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return false;
                }
                GeneralFunctionalKeyboard generalFunctionalKeyboard2 = GeneralFunctionalKeyboard.this;
                if (generalFunctionalKeyboard2.mIsKeyboardShown) {
                    generalFunctionalKeyboard2.hide();
                    return true;
                }
                return true;
            }
        };
        this.currentFunctionalMode = keyboardModeFunctional;
        this.currentBasicMode = keyboardModeBasic;
        initLayout();
        initData();
    }

    public void setBackgroundThemeResource(int... iArr) {
        if (iArr == null || iArr.length < 3) {
            return;
        }
        this.keyboardView.setSureBackgroundResource(iArr);
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentFunctionalMode;
        if (keyboardModeFunctional == null || keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE) {
            return;
        }
        this.topInputLayout.setCountdownBgColor(iArr[0]);
    }

    public void setBackgroundThemeResource(String... strArr) {
        if (strArr == null || strArr.length < 1) {
            return;
        }
        this.keyboardView.setSureBackgroundResource(strArr[0], strArr.length > 1 ? strArr[1] : null, strArr.length > 2 ? strArr[2] : null);
        GeneralKeyboard.KeyboardModeFunctional keyboardModeFunctional = this.currentFunctionalMode;
        if (keyboardModeFunctional == null || keyboardModeFunctional != GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE) {
            return;
        }
        this.topInputLayout.setCountdownBgColor(strArr[0]);
    }
}
