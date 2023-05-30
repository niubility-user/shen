package com.jdjr.generalKeyboard;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.GeneralFunctionalKeyboard;
import com.jdjr.generalKeyboard.common.JDJRKeyboardModel;
import com.jdjr.generalKeyboard.common.JDJRResultMessage;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class JDJRFunctionKeyboard {
    private GeneralFunctionalKeyboard.ActionCallback actionCallback;
    private JDJRKeyboardCallback callback;
    private GeneralCombinedKeyboard combinedKeyboard;
    private int currentPosition;
    private GeneralFunctionalKeyboard firstKeyboard;
    private JDJRKeyboardModel firstKeyboardModel;
    private boolean isNightMode;
    private List<GeneralFunctionalKeyboard> keyboardList;
    private Context mContext;
    private GeneralFunctionalKeyboard secondKeyboard;
    private JDJRKeyboardModel secondKeyboardModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jdjr.generalKeyboard.JDJRFunctionKeyboard$4  reason: invalid class name */
    /* loaded from: classes18.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType;

        static {
            int[] iArr = new int[KeyboardType.values().length];
            $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType = iArr;
            try {
                iArr[KeyboardType.PAYMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[KeyboardType.IDENTITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[KeyboardType.LONG_PAY_PWD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[KeyboardType.SHORT_PAY_PWD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[KeyboardType.UNFIXED_VERIFY_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[KeyboardType.FIXED_VERIFY_CODE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes18.dex */
    public enum ActionType {
        HIDE,
        BACK,
        ACTION_LEFT,
        ACTION_MIDDLE,
        ACTION_RIGHT,
        SEND_VERIFY_CODE,
        NEXT,
        FINISH
    }

    /* loaded from: classes18.dex */
    public interface JDJRKeyboardCallback {
        void onCallback(KeyboardType keyboardType, ActionType actionType);
    }

    /* loaded from: classes18.dex */
    public enum KeyboardType {
        PAYMENT,
        IDENTITY,
        LONG_PAY_PWD,
        SHORT_PAY_PWD,
        UNFIXED_VERIFY_CODE,
        FIXED_VERIFY_CODE
    }

    public JDJRFunctionKeyboard(Activity activity, JDJRKeyboardModel jDJRKeyboardModel) {
        this(activity, jDJRKeyboardModel, null, false);
    }

    private int getCurrentCipherMode(KeyboardType keyboardType) {
        int i2 = AnonymousClass4.$SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[keyboardType.ordinal()];
        return (i2 == 1 || i2 == 5 || i2 == 6) ? 0 : 1;
    }

    private GeneralFunctionalKeyboard getKeyboardByType(JDJRKeyboardModel jDJRKeyboardModel) {
        switch (AnonymousClass4.$SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[jDJRKeyboardModel.getKeyboardType().ordinal()]) {
            case 1:
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_PAYMENT, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_CAN_FINISH, this.isNightMode);
            case 2:
                if (jDJRKeyboardModel.isHasFinishButton()) {
                    return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_CAN_FINISH, this.isNightMode);
                }
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_NO_FINISH, this.isNightMode);
            case 3:
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_COMMON_PWD, GeneralKeyboard.KeyboardModeBasic.BASE_TOTAL, this.isNightMode);
            case 4:
                if (jDJRKeyboardModel.isHasFinishButton()) {
                    return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_CAN_FINISH, this.isNightMode);
                }
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_SQUARE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH, this.isNightMode);
            case 5:
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_VERIFY_CODE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_CAN_FINISH, this.isNightMode);
            case 6:
                if (jDJRKeyboardModel.isHasFinishButton()) {
                    return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_CAN_FINISH, this.isNightMode);
                }
                return new GeneralFunctionalKeyboard(this.mContext, GeneralKeyboard.KeyboardModeFunctional.FUNCTION_SIX_UNDERLINE, GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH, this.isNightMode);
            default:
                return null;
        }
    }

    private void initKeyboard() {
        JDJRKeyboardModel jDJRKeyboardModel = this.firstKeyboardModel;
        if (jDJRKeyboardModel != null && jDJRKeyboardModel.getKeyboardType() != null) {
            GeneralFunctionalKeyboard keyboardByType = getKeyboardByType(this.firstKeyboardModel);
            this.firstKeyboard = keyboardByType;
            setupKeyboard(keyboardByType, this.firstKeyboardModel);
            this.firstKeyboard.setFunctionalKeyboardCallback(new GeneralKeyboard.FunctionalKeyboardCallback() { // from class: com.jdjr.generalKeyboard.JDJRFunctionKeyboard.1
                @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard.FunctionalKeyboardCallback
                public void onActionClick(GeneralKeyboard.FunctionalActionType functionalActionType, String str) {
                    if (JDJRFunctionKeyboard.this.callback == null) {
                        return;
                    }
                    if (functionalActionType == GeneralKeyboard.FunctionalActionType.FINISH) {
                        JDJRFunctionKeyboard.this.firstKeyboard.setLoadingShow();
                        if (JDJRFunctionKeyboard.this.secondKeyboardModel != null) {
                            JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.firstKeyboardModel.getKeyboardType(), ActionType.NEXT);
                        } else {
                            JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.firstKeyboardModel.getKeyboardType(), ActionType.FINISH);
                        }
                    } else if (functionalActionType == GeneralKeyboard.FunctionalActionType.GET_VERIFY_CODE) {
                        JDJRFunctionKeyboard jDJRFunctionKeyboard = JDJRFunctionKeyboard.this;
                        jDJRFunctionKeyboard.onCallSendVerifyCode(jDJRFunctionKeyboard.firstKeyboardModel);
                    } else if (functionalActionType == GeneralKeyboard.FunctionalActionType.HIDE) {
                        JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.firstKeyboardModel.getKeyboardType(), ActionType.HIDE);
                    }
                }
            });
        }
        JDJRKeyboardModel jDJRKeyboardModel2 = this.secondKeyboardModel;
        if (jDJRKeyboardModel2 != null && jDJRKeyboardModel2.getKeyboardType() != null) {
            GeneralFunctionalKeyboard keyboardByType2 = getKeyboardByType(this.secondKeyboardModel);
            this.secondKeyboard = keyboardByType2;
            setupKeyboard(keyboardByType2, this.secondKeyboardModel);
            this.secondKeyboard.setFunctionalKeyboardCallback(new GeneralKeyboard.FunctionalKeyboardCallback() { // from class: com.jdjr.generalKeyboard.JDJRFunctionKeyboard.2
                @Override // com.jdjr.generalKeyboard.views.GeneralKeyboard.FunctionalKeyboardCallback
                public void onActionClick(GeneralKeyboard.FunctionalActionType functionalActionType, String str) {
                    if (JDJRFunctionKeyboard.this.callback == null) {
                        return;
                    }
                    if (functionalActionType == GeneralKeyboard.FunctionalActionType.FINISH) {
                        JDJRFunctionKeyboard.this.secondKeyboard.setLoadingShow();
                        JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.secondKeyboardModel.getKeyboardType(), ActionType.FINISH);
                    } else if (functionalActionType == GeneralKeyboard.FunctionalActionType.BACK) {
                        if (JDJRFunctionKeyboard.this.combinedKeyboard != null) {
                            JDJRFunctionKeyboard.this.combinedKeyboard.onBackKeyClick();
                            JDJRFunctionKeyboard.this.currentPosition = 0;
                            JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.secondKeyboardModel.getKeyboardType(), ActionType.BACK);
                        }
                    } else if (functionalActionType == GeneralKeyboard.FunctionalActionType.GET_VERIFY_CODE) {
                        JDJRFunctionKeyboard jDJRFunctionKeyboard = JDJRFunctionKeyboard.this;
                        jDJRFunctionKeyboard.onCallSendVerifyCode(jDJRFunctionKeyboard.secondKeyboardModel);
                    } else if (functionalActionType == GeneralKeyboard.FunctionalActionType.HIDE) {
                        JDJRFunctionKeyboard.this.callback.onCallback(JDJRFunctionKeyboard.this.secondKeyboardModel.getKeyboardType(), ActionType.HIDE);
                    }
                }
            });
        }
        if (this.firstKeyboard == null || this.secondKeyboard == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.keyboardList = arrayList;
        arrayList.add(this.firstKeyboard);
        this.keyboardList.add(this.secondKeyboard);
        this.combinedKeyboard = new GeneralCombinedKeyboard(this.mContext, this.keyboardList);
    }

    private boolean isShowPlain(KeyboardType keyboardType) {
        int i2 = AnonymousClass4.$SwitchMap$com$jdjr$generalKeyboard$JDJRFunctionKeyboard$KeyboardType[keyboardType.ordinal()];
        return i2 == 1 || i2 == 5 || i2 == 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCallSendVerifyCode(JDJRKeyboardModel jDJRKeyboardModel) {
        if (jDJRKeyboardModel.getKeyboardType() == KeyboardType.FIXED_VERIFY_CODE || jDJRKeyboardModel.getKeyboardType() == KeyboardType.UNFIXED_VERIFY_CODE) {
            this.callback.onCallback(jDJRKeyboardModel.getKeyboardType(), ActionType.SEND_VERIFY_CODE);
        }
    }

    private void setupKeyboard(GeneralFunctionalKeyboard generalFunctionalKeyboard, final JDJRKeyboardModel jDJRKeyboardModel) {
        generalFunctionalKeyboard.setTitle(jDJRKeyboardModel.getTitle());
        generalFunctionalKeyboard.setHintText(jDJRKeyboardModel.getHint());
        generalFunctionalKeyboard.setDescription(jDJRKeyboardModel.getDescription());
        generalFunctionalKeyboard.setCountdownStatus(jDJRKeyboardModel.isAutoCountDown());
        generalFunctionalKeyboard.setIsCipherMode(getCurrentCipherMode(jDJRKeyboardModel.getKeyboardType()));
        generalFunctionalKeyboard.setIsShownPlain(isShowPlain(jDJRKeyboardModel.getKeyboardType()));
        generalFunctionalKeyboard.setCountdownDuration((int) jDJRKeyboardModel.getCountButtonText());
        generalFunctionalKeyboard.setMaxInputLen(jDJRKeyboardModel.getMaxInputLength());
        generalFunctionalKeyboard.setOkButtonText(jDJRKeyboardModel.getOKButtonText());
        generalFunctionalKeyboard.setBackgroundThemeResource(jDJRKeyboardModel.getOKButtonBackgroundColor());
        generalFunctionalKeyboard.setBackgroundThemeResource(jDJRKeyboardModel.getBackgroundThemeResource());
        generalFunctionalKeyboard.setBackgroundThemeResource(jDJRKeyboardModel.getBackgroundThemeColor());
        generalFunctionalKeyboard.setCryptoAlg(jDJRKeyboardModel.getCryptoAlg());
        generalFunctionalKeyboard.showKeyPressBg(jDJRKeyboardModel.isShowPressBg());
        this.actionCallback = new GeneralFunctionalKeyboard.ActionCallback() { // from class: com.jdjr.generalKeyboard.JDJRFunctionKeyboard.3
            @Override // com.jdjr.generalKeyboard.GeneralFunctionalKeyboard.ActionCallback
            public void onActionPerform(View view, int i2) {
                if (JDJRFunctionKeyboard.this.callback != null) {
                    if (i2 == 100) {
                        JDJRFunctionKeyboard.this.callback.onCallback(jDJRKeyboardModel.getKeyboardType(), ActionType.ACTION_LEFT);
                    } else if (i2 == 200) {
                        JDJRFunctionKeyboard.this.callback.onCallback(jDJRKeyboardModel.getKeyboardType(), ActionType.ACTION_MIDDLE);
                    } else if (i2 != 300) {
                    } else {
                        if ((JDJRFunctionKeyboard.this.mContext == null || !JDJRFunctionKeyboard.this.mContext.getResources().getString(R.string.security_get_verify_code).equals(jDJRKeyboardModel.getRightFuncText().toString())) && !JDJRFunctionKeyboard.this.mContext.getResources().getString(R.string.security_resend).equals(jDJRKeyboardModel.getRightFuncText().toString())) {
                            JDJRFunctionKeyboard.this.callback.onCallback(jDJRKeyboardModel.getKeyboardType(), ActionType.ACTION_RIGHT);
                        } else {
                            JDJRFunctionKeyboard.this.callback.onCallback(jDJRKeyboardModel.getKeyboardType(), ActionType.SEND_VERIFY_CODE);
                        }
                    }
                }
            }
        };
        if (!TextUtils.isEmpty(jDJRKeyboardModel.getLeftFuncText())) {
            generalFunctionalKeyboard.setActionText(jDJRKeyboardModel.getLeftFuncText(), 100, this.actionCallback);
        }
        if (!TextUtils.isEmpty(jDJRKeyboardModel.getMiddleFuncText())) {
            generalFunctionalKeyboard.setActionText(jDJRKeyboardModel.getMiddleFuncText(), 200, this.actionCallback);
        }
        if (TextUtils.isEmpty(jDJRKeyboardModel.getRightFuncText())) {
            return;
        }
        generalFunctionalKeyboard.setActionText(jDJRKeyboardModel.getRightFuncText(), 300, this.actionCallback);
    }

    public boolean checkEqual() {
        if (this.keyboardList.get(0) == null || this.keyboardList.get(1) == null) {
            return false;
        }
        return this.keyboardList.get(1).getCheckResult(this.keyboardList.get(0), this.keyboardList.get(1));
    }

    public boolean checkRegexMatch(String str) {
        if (this.combinedKeyboard != null) {
            return this.keyboardList.get(this.currentPosition).checkRegexMatch(str);
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            return generalFunctionalKeyboard.checkRegexMatch(str);
        }
        return false;
    }

    public void clearCombinedKeyboard() {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            generalCombinedKeyboard.clearAllKeyboard();
        }
    }

    public void clearKeyboard() {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            generalCombinedKeyboard.clearKeyboard();
            return;
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.clearKeyboard();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            if (this.currentPosition == 1) {
                generalCombinedKeyboard.onBackKeyClick();
                this.currentPosition = 0;
                return true;
            }
            return generalCombinedKeyboard.dispatchKeyEvent(keyEvent);
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            return generalFunctionalKeyboard.dispatchKeyEvent(keyEvent);
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            return generalCombinedKeyboard.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public JDJRResultMessage getCryptoData() {
        if (this.combinedKeyboard != null) {
            return this.keyboardList.get(this.currentPosition).getCryptoData();
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            return generalFunctionalKeyboard.getCryptoData();
        }
        return null;
    }

    public float getKeyboardHeight() {
        Context context = this.mContext;
        if (context == null) {
            return 0.0f;
        }
        return context.getResources().getDimension(R.dimen.security_keyboard_functional_popup_transY);
    }

    public byte[] getSourceData() {
        if (this.combinedKeyboard != null) {
            return this.keyboardList.get(this.currentPosition).getSourceData();
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            return generalFunctionalKeyboard.getSourceData();
        }
        return null;
    }

    public void hide() {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            generalCombinedKeyboard.hide();
            return;
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.hide();
        }
    }

    public void hideLoading() {
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.setLoadingCancel();
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard2 = this.secondKeyboard;
        if (generalFunctionalKeyboard2 != null) {
            generalFunctionalKeyboard2.setLoadingCancel();
        }
    }

    public void onNextClick() {
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard == null || this.currentPosition == 1) {
            return;
        }
        generalCombinedKeyboard.onNextKeyClick();
        this.keyboardList.get(0).setLoadingCancel();
        this.currentPosition = 1;
    }

    public void releaseCppKeyboard() {
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.releaseCppKeyboard();
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard2 = this.secondKeyboard;
        if (generalFunctionalKeyboard2 != null) {
            generalFunctionalKeyboard2.releaseCppKeyboard();
        }
    }

    public void setCallback(JDJRKeyboardCallback jDJRKeyboardCallback) {
        this.callback = jDJRKeyboardCallback;
    }

    public void setSelection() {
        if (this.combinedKeyboard != null) {
            this.keyboardList.get(this.currentPosition).setSelection();
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.setSelection();
        }
    }

    public void show() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        GeneralCombinedKeyboard generalCombinedKeyboard = this.combinedKeyboard;
        if (generalCombinedKeyboard != null) {
            this.currentPosition = 0;
            generalCombinedKeyboard.show((Activity) context);
            return;
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.show((Activity) context);
        }
    }

    public void showKeyPressBg(boolean z) {
        GeneralFunctionalKeyboard generalFunctionalKeyboard = this.firstKeyboard;
        if (generalFunctionalKeyboard != null) {
            generalFunctionalKeyboard.showKeyPressBg(z);
        }
        GeneralFunctionalKeyboard generalFunctionalKeyboard2 = this.secondKeyboard;
        if (generalFunctionalKeyboard2 != null) {
            generalFunctionalKeyboard2.showKeyPressBg(z);
        }
    }

    public void turnToFirstKeyboard() {
        GeneralCombinedKeyboard generalCombinedKeyboard;
        if (this.currentPosition != 1 || (generalCombinedKeyboard = this.combinedKeyboard) == null) {
            return;
        }
        generalCombinedKeyboard.onBackKeyClick();
        this.currentPosition = 0;
    }

    public JDJRFunctionKeyboard(Activity activity, JDJRKeyboardModel jDJRKeyboardModel, boolean z) {
        this(activity, jDJRKeyboardModel, null, z);
    }

    public JDJRFunctionKeyboard(Activity activity, JDJRKeyboardModel jDJRKeyboardModel, JDJRKeyboardModel jDJRKeyboardModel2) {
        this(activity, jDJRKeyboardModel, jDJRKeyboardModel2, false);
    }

    public JDJRFunctionKeyboard(Activity activity, JDJRKeyboardModel jDJRKeyboardModel, JDJRKeyboardModel jDJRKeyboardModel2, boolean z) {
        this.currentPosition = 0;
        this.isNightMode = false;
        this.mContext = activity;
        this.firstKeyboardModel = jDJRKeyboardModel;
        this.secondKeyboardModel = jDJRKeyboardModel2;
        this.isNightMode = z;
        initKeyboard();
    }
}
