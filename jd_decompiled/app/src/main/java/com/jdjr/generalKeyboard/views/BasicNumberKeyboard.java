package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.ColorRes;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecurityBaseNumberPointCanFinishBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberPointNoFinishBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberPureCanFinishBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberPureNoFinishBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberXCanFinishBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberXNoFinishBinding;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.UIUtils;
import com.jdjr.generalKeyboard.views.GeneralKeyboard;
import com.jdjr.generalKeyboard.views.KeyboardView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;

/* loaded from: classes18.dex */
public class BasicNumberKeyboard extends KeyboardView implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {
    private Button btnOk;
    private String finishText;
    private GeneralKeyboard.KeyboardModeBasic keyboardMode;
    private Context mContext;
    private String[][] mNumberTexts;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jdjr.generalKeyboard.views.BasicNumberKeyboard$2  reason: invalid class name */
    /* loaded from: classes18.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic;

        static {
            int[] iArr = new int[GeneralKeyboard.KeyboardModeBasic.values().length];
            $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic = iArr;
            try {
                iArr[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_CAN_FINISH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_CAN_FINISH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_X_NO_FINISH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_CAN_FINISH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_POINT_NO_FINISH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public BasicNumberKeyboard(Context context) {
        this(context, null);
        this.mContext = context;
        this.finishText = context.getResources().getString(R.string.security_accomplish);
    }

    private void drawKeyText(LinearLayout linearLayout) {
        final View childAt;
        for (int i2 = 0; i2 < this.mNumberTexts.length; i2++) {
            View childAt2 = linearLayout.getChildAt(i2);
            if (childAt2 != null && (childAt2 instanceof ViewGroup)) {
                String[] strArr = this.mNumberTexts[i2];
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    View childAt3 = ((ViewGroup) childAt2).getChildAt(i3);
                    if (childAt3 != null && (childAt3 instanceof ViewGroup) && (childAt = ((ViewGroup) childAt3).getChildAt(0)) != null && (childAt instanceof NumberKeyView)) {
                        childAt.post(new Runnable() { // from class: com.jdjr.generalKeyboard.views.BasicNumberKeyboard.1
                            @Override // java.lang.Runnable
                            public void run() {
                                View view = childAt;
                                ((NumberKeyView) view).setParams(view.getWidth(), childAt.getHeight());
                            }
                        });
                        if (strArr[i3] != null && !strArr[i3].equals("none")) {
                            ((NumberKeyView) childAt).setTextStr(strArr[i3]);
                        }
                    }
                }
            }
        }
    }

    private void initView() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        switch (AnonymousClass2.$SwitchMap$com$jdjr$generalKeyboard$views$GeneralKeyboard$KeyboardModeBasic[this.keyboardMode.ordinal()]) {
            case 1:
                SecurityBaseNumberPureCanFinishBinding securityBaseNumberPureCanFinishBinding = (SecurityBaseNumberPureCanFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_pure_can_finish, null, false);
                this.keyboardView = securityBaseNumberPureCanFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberPureCanFinishBinding);
                break;
            case 2:
                SecurityBaseNumberPureNoFinishBinding securityBaseNumberPureNoFinishBinding = (SecurityBaseNumberPureNoFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_pure_no_finish, null, false);
                this.keyboardView = securityBaseNumberPureNoFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberPureNoFinishBinding);
                break;
            case 3:
                SecurityBaseNumberXCanFinishBinding securityBaseNumberXCanFinishBinding = (SecurityBaseNumberXCanFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_x_can_finish, null, false);
                this.keyboardView = securityBaseNumberXCanFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberXCanFinishBinding);
                break;
            case 4:
                SecurityBaseNumberXNoFinishBinding securityBaseNumberXNoFinishBinding = (SecurityBaseNumberXNoFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_x_no_finish, null, false);
                this.keyboardView = securityBaseNumberXNoFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberXNoFinishBinding);
                break;
            case 5:
                SecurityBaseNumberPointCanFinishBinding securityBaseNumberPointCanFinishBinding = (SecurityBaseNumberPointCanFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_point_can_finish, null, false);
                this.keyboardView = securityBaseNumberPointCanFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberPointCanFinishBinding);
                break;
            case 6:
                SecurityBaseNumberPointNoFinishBinding securityBaseNumberPointNoFinishBinding = (SecurityBaseNumberPointNoFinishBinding) DataBindingUtil.inflate(from, R.layout.security_base_number_point_no_finish, null, false);
                this.keyboardView = securityBaseNumberPointNoFinishBinding.getRoot();
                KeyboardUiMode.setKeyboardBindings(securityBaseNumberPointNoFinishBinding);
                break;
        }
        View view = this.keyboardView;
        if (view != null) {
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.delete_key);
            View view2 = (ImageButton) this.keyboardView.findViewById(R.id.hide_key);
            ImageButton imageButton2 = (ImageButton) this.keyboardView.findViewById(R.id.symbol_key);
            LinearLayout linearLayout = (LinearLayout) this.keyboardView.findViewById(R.id.keyboard_buttons);
            Button button = (Button) this.keyboardView.findViewById(R.id.btnOk);
            this.btnOk = button;
            if (button != null) {
                button.setText(this.finishText);
            }
            imageButton.setOnLongClickListener(this);
            setUpListener(imageButton, view2, this.btnOk, imageButton2);
            imageButton2.setOnTouchListener(this);
            setKeyTouchListener((ViewGroup) this.keyboardView);
            drawKeyText(linearLayout);
        }
        setSureEnabled(false);
    }

    private void setKeyTouchListener(ViewGroup viewGroup) {
        viewGroup.setOnTouchListener(this);
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof NumberKeyView) {
                childAt.setOnTouchListener(this);
                childAt.setOnClickListener(this);
            }
            if (childAt instanceof ViewGroup) {
                setKeyTouchListener((ViewGroup) childAt);
            }
        }
    }

    private void setUpListener(View... viewArr) {
        for (int i2 = 0; i2 < viewArr.length; i2++) {
            if (viewArr[i2] != null) {
                viewArr[i2].setOnClickListener(this);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.keyboardViewCallback == null) {
            return;
        }
        if (view.getId() == R.id.hide_key) {
            this.keyboardViewCallback.onHide(view);
        } else if (view.getId() == R.id.btnOk) {
            this.keyboardViewCallback.onSure(view);
        } else if (view.getId() == R.id.delete_key) {
            this.keyboardViewCallback.onKeyDelete(view);
        } else {
            this.keyboardViewCallback.onKeyInput(view);
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        KeyboardView.KeyboardViewCallback keyboardViewCallback = this.keyboardViewCallback;
        if (keyboardViewCallback != null) {
            keyboardViewCallback.onKeyDeleteAll(view);
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (((view instanceof NumberKeyView) || view.getId() == R.id.symbol_key) && motionEvent.getPointerCount() == 1) {
            int action = motionEvent.getAction();
            if (action == 0) {
                view.setPressed(this.showPressBg);
                view.performClick();
            } else if (action == 1) {
                view.setPressed(false);
                return true;
            }
        }
        return true;
    }

    public void setKeyboardMode(GeneralKeyboard.KeyboardModeBasic keyboardModeBasic) {
        this.keyboardMode = keyboardModeBasic;
        initView();
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(String str) {
        if (this.btnOk == null) {
            return;
        }
        if ("red".equals(str)) {
            this.btnOk.setBackgroundResource(R.drawable.security_general_ok_key_red_bg_selector);
        } else if ("gold".equals(str)) {
            this.btnOk.setBackgroundResource(R.drawable.security_general_ok_key_gold_bg_selector);
        }
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureEnabled(boolean z) {
        Button button = this.btnOk;
        if (button != null) {
            button.setEnabled(z);
            this.btnOk.setSelected(z);
            this.btnOk.setClickable(z);
        }
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureText(CharSequence charSequence) {
        if (this.btnOk != null) {
            String valueOf = String.valueOf(charSequence);
            this.finishText = valueOf;
            this.btnOk.setText(valueOf);
        }
    }

    public BasicNumberKeyboard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BasicNumberKeyboard(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.keyboardMode = GeneralKeyboard.KeyboardModeBasic.BASE_NUMBER_PURE_NO_FINISH;
        this.mNumberTexts = new String[][]{new String[]{"1", "2", "3"}, new String[]{"4", "5", "6"}, new String[]{"7", "8", "9"}, new String[]{OrderISVUtil.MONEY_DECIMAL, "0", "none"}};
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(@ColorRes int... iArr) {
        Button button = this.btnOk;
        if (button == null || iArr == null || iArr.length < 3 || Build.VERSION.SDK_INT < 16) {
            return;
        }
        UIUtils.createSelector(button, this.mContext, iArr[0], iArr[1], iArr[2]);
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(String str, String str2, String str3) {
        Context context;
        Button button = this.btnOk;
        if (button == null || (context = this.mContext) == null || Build.VERSION.SDK_INT < 16) {
            return;
        }
        UIUtils.createSelector(button, context, str, str2, str3);
    }
}
