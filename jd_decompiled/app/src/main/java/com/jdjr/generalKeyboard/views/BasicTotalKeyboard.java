package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecurityGeneralKeyboardTotalBinding;
import com.jdjr.dns.databinding.SecurityKeyboardKeyPreviewLayoutBinding;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.UIUtils;
import com.jdjr.generalKeyboard.views.KeyboardView;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;

/* loaded from: classes18.dex */
public class BasicTotalKeyboard extends KeyboardView implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
    private static final int DISMISS = 1;
    public static final String TAG = "TotalKeyboardView";
    public static final int TYPE_LETTER = 2;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_SYMBOL = 3;
    private int SYMBOL_TEXTS_TYPE;
    int baseline;
    private Button btnLetter123;
    private Button btnLetterSure;
    private Button btnLetterSymbol;
    private Button btnMore;
    private Button btnNumberABC;
    private Button btnNumberSure;
    private Button btnNumberSymbol;
    private Button btnSymbol123;
    private Button btnSymbolABC;
    private Button btnSymbolSure;
    private String currentPopText;
    private int currentType;
    private ImageButton ibCapsLock;
    private boolean isCapsLock;
    private HashMap<Rect, View> keyRects;
    private int keyViewBackground;
    private int keyViewTextColor;
    private String[][] letterTexts;
    private int mBigKeyViewWidth;
    private int mBigKeyViewWidthOnNumber;
    private int mBigKeyViewWidthOnSymbol;
    private Context mContext;
    private int mKeyViewHeight;
    private int mKeyViewWidth;
    private LinearLayout mLetterKeyboardView;
    private LinearLayout mNumberKeyboardView;
    private PopupWindow mPreviewPopup;
    private LinearLayout mSymbolKeyboardView;
    private String[][] numberTexts;
    private String[][] symbolTextsMore1;
    private String[][] symbolTextsMore2;
    private FrameLayout totalKeyboard;
    private float x1;
    private float x2;
    private float y1;
    private float y2;

    public BasicTotalKeyboard(Context context) {
        this(context, null);
    }

    private void calculateButtonDimen() {
        Resources resources = this.mContext.getResources();
        int screenWidth = BaseInfo.getScreenWidth();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.security_total_keyboard_row_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.security_total_key_container_padding);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.security_total_keyboard_row_height);
        int i2 = screenWidth - (dimensionPixelSize * 2);
        int i3 = dimensionPixelSize2 * 2;
        this.mKeyViewWidth = (i2 / 10) - i3;
        this.mBigKeyViewWidthOnNumber = (i2 / 7) - i3;
        this.mBigKeyViewWidthOnSymbol = (i2 / 6) - i3;
        this.mKeyViewHeight = dimensionPixelSize3 - i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissPreview(PopupWindow popupWindow) {
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        popupWindow.dismiss();
        setButtonsClickable(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawKeyText(android.view.ViewGroup r13, int r14) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.generalKeyboard.views.BasicTotalKeyboard.drawKeyText(android.view.ViewGroup, int):void");
    }

    private String[][] getTwoDimensionalArray(String[] strArr) {
        String[][] strArr2 = new String[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String[] split = strArr[i2].split(LangUtils.SINGLE_SPACE);
            strArr2[i2] = new String[split.length];
            for (int i3 = 0; i3 < split.length; i3++) {
                strArr2[i2][i3] = split[i3];
            }
        }
        return strArr2;
    }

    private void initField(Context context) {
        this.mContext = context;
    }

    private void initKeyText() {
        this.numberTexts = getTwoDimensionalArray(this.mContext.getResources().getStringArray(R.array.security_totalNumberTexts));
        this.letterTexts = getTwoDimensionalArray(this.mContext.getResources().getStringArray(R.array.security_totalLetterTexts));
        this.symbolTextsMore1 = getTwoDimensionalArray(this.mContext.getResources().getStringArray(R.array.security_totalSymbolTexts_forPay1));
        this.symbolTextsMore2 = getTwoDimensionalArray(this.mContext.getResources().getStringArray(R.array.security_totalSymbolTexts_forPay2));
    }

    private void initLayout() {
        SecurityGeneralKeyboardTotalBinding securityGeneralKeyboardTotalBinding = (SecurityGeneralKeyboardTotalBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.security_general_keyboard_total, null, false);
        KeyboardUiMode.setKeyboardBindings(securityGeneralKeyboardTotalBinding);
        View root = securityGeneralKeyboardTotalBinding.getRoot();
        this.keyboardView = root;
        FrameLayout frameLayout = (FrameLayout) root.findViewById(R.id.total_keyboard);
        this.totalKeyboard = frameLayout;
        frameLayout.setOnTouchListener(this);
        this.mNumberKeyboardView = (LinearLayout) securityGeneralKeyboardTotalBinding.totalNumberKeyboard.getRoot();
        this.mLetterKeyboardView = (LinearLayout) securityGeneralKeyboardTotalBinding.totalLetterKeyboard.getRoot();
        this.mSymbolKeyboardView = (LinearLayout) securityGeneralKeyboardTotalBinding.totalSymbolKeyboard.getRoot();
        this.btnNumberABC = (Button) this.keyboardView.findViewById(R.id.btn_number_ABC);
        this.btnSymbolABC = (Button) this.keyboardView.findViewById(R.id.btn_symbol_ABC);
        this.btnLetter123 = (Button) this.keyboardView.findViewById(R.id.btn_letter_123);
        this.btnSymbol123 = (Button) this.keyboardView.findViewById(R.id.btn_symbol_123);
        this.btnNumberSymbol = (Button) this.keyboardView.findViewById(R.id.btn_number_symbol);
        this.btnLetterSymbol = (Button) this.keyboardView.findViewById(R.id.btn_letter_symbol);
        this.ibCapsLock = (ImageButton) this.keyboardView.findViewById(R.id.btn_key_capslock);
        this.btnNumberSure = (Button) this.keyboardView.findViewById(R.id.btn_number_sure);
        this.btnLetterSure = (Button) this.keyboardView.findViewById(R.id.btn_letter_sure);
        this.btnSymbolSure = (Button) this.keyboardView.findViewById(R.id.btn_symbol_sure);
        Button button = (Button) this.keyboardView.findViewById(R.id.btn_symbol_more);
        this.btnMore = button;
        button.setOnClickListener(this);
        calculateButtonDimen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveKeyViewRect(final View view) {
        if (view.getTag() == null || this.keyRects.containsValue(view)) {
            return;
        }
        view.post(new Runnable() { // from class: com.jdjr.generalKeyboard.views.BasicTotalKeyboard.2
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                BasicTotalKeyboard.this.keyRects.put(rect, view);
            }
        });
    }

    private void setButtonsClickable(boolean z) {
        this.btnLetter123.setClickable(z);
        this.btnLetterSure.setClickable(z);
        this.btnLetterSymbol.setClickable(z);
        this.btnNumberABC.setClickable(z);
        this.btnNumberSure.setClickable(z);
        this.btnNumberSymbol.setClickable(z);
        this.btnSymbol123.setClickable(z);
        this.btnSymbolSure.setClickable(z);
        this.btnSymbolABC.setClickable(z);
        this.btnMore.setClickable(z);
    }

    private void showPreview(TotalKeyView totalKeyView, int i2, int i3) {
        int i4;
        int i5;
        String enlargePopType = totalKeyView.getEnlargePopType();
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.security_keyboard_enlarge_height);
        int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.security_keyboard_middle_enlarge_width);
        int dimension3 = (int) this.mContext.getResources().getDimension(R.dimen.security_keyboard_middle_big_enlarge_width);
        int dimension4 = (int) this.mContext.getResources().getDimension(R.dimen.security_keyboard_giant_enlarge_width);
        int dimension5 = (int) this.mContext.getResources().getDimension(R.dimen.security_total_letter_button_container_padding_top);
        dismissPreview(this.mPreviewPopup);
        TextView textView = (TextView) ((SecurityKeyboardKeyPreviewLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.security_keyboard_key_preview_layout, null, false)).getRoot();
        textView.setText(totalKeyView.getTag().toString());
        textView.measure(0, 0);
        PopupWindow popupWindow = new PopupWindow(this.mContext);
        this.mPreviewPopup = popupWindow;
        popupWindow.setBackgroundDrawable(null);
        this.mPreviewPopup.setTouchable(false);
        if (!enlargePopType.equals(TotalKeyView.TYPE_LEFT_BIG) && !enlargePopType.equals(TotalKeyView.TYPE_MIDDLE_BIG)) {
            if (!enlargePopType.equals(TotalKeyView.TYPE_LEFT_GIANT) && !enlargePopType.equals(TotalKeyView.TYPE_MIDDLE_GIANT) && !enlargePopType.equals(TotalKeyView.TYPE_RIGHT_GIANT)) {
                this.mPreviewPopup.setHeight(dimension);
                this.mPreviewPopup.setWidth(dimension2);
                int i6 = (dimension2 - this.mKeyViewWidth) / 2;
                if (enlargePopType.equals(TotalKeyView.TYPE_LEFT_NORMAL)) {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_left_enlarge_dark : R.drawable.security_keyboard_left_enlarge);
                    i5 = i2 - dimension5;
                } else if (enlargePopType.equals(TotalKeyView.TYPE_RIGHT_NORMAL)) {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_right_enlarge_dark : R.drawable.security_keyboard_right_enlarge);
                    i5 = (i2 - (dimension2 / 2)) + (dimension5 * 2);
                } else {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_middle_enlarge_dark : R.drawable.security_keyboard_middle_enlarge);
                    i5 = i2 - i6;
                }
            } else {
                this.mPreviewPopup.setHeight(dimension);
                this.mPreviewPopup.setWidth(dimension4);
                i4 = (dimension4 - this.mBigKeyViewWidthOnSymbol) / 2;
                if (enlargePopType.equals(TotalKeyView.TYPE_LEFT_GIANT)) {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_left_giant_enlarge_black : R.drawable.security_keyboard_left_giant_enlarge);
                    i5 = i2 - dimension5;
                } else if (enlargePopType.equals(TotalKeyView.TYPE_RIGHT_GIANT)) {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_right_giant_enlarge_black : R.drawable.security_keyboard_right_giant_enlarge);
                    i5 = (i2 - (dimension4 - this.mBigKeyViewWidthOnSymbol)) + dimension5;
                } else {
                    textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_middle_giant_enlarge_black : R.drawable.security_keyboard_middle_giant_enlarge);
                    i5 = i2 - i4;
                }
            }
        } else {
            this.mPreviewPopup.setHeight(dimension);
            this.mPreviewPopup.setWidth(dimension3);
            i4 = (dimension2 - this.mBigKeyViewWidthOnNumber) / 2;
            if (enlargePopType.equals(TotalKeyView.TYPE_LEFT_BIG)) {
                textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_left_big_enlarge_dark : R.drawable.security_keyboard_left_big_enlarge);
                i5 = i2 - dimension5;
            } else {
                textView.setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_keyboard_middle_big_enlarge_dark : R.drawable.security_keyboard_middle_big_enlarge);
                i5 = i2 - i4;
            }
        }
        this.mPreviewPopup.setContentView(textView);
        this.mPreviewPopup.showAtLocation(totalKeyView, 0, i5, ((i3 + this.mKeyViewHeight) + (dimension5 * 2)) - dimension);
        setButtonsClickable(false);
    }

    private void switchCapsLock(ViewGroup viewGroup, boolean z) {
        View childAt;
        this.ibCapsLock.setSelected(z);
        String[][] strArr = this.letterTexts;
        int i2 = this.mKeyViewWidth;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String[] strArr2 = strArr[i3];
            View childAt2 = viewGroup.getChildAt(i3);
            if (childAt2 != null && (childAt2 instanceof ViewGroup)) {
                for (int i4 = 0; i4 < strArr2.length; i4++) {
                    String str = strArr2[i4];
                    if (str != null) {
                        if (!str.equals("none")) {
                            if (z) {
                                str = str.toUpperCase();
                            } else {
                                str = str.toLowerCase();
                            }
                        }
                    }
                    View childAt3 = ((ViewGroup) childAt2).getChildAt(i4);
                    if (childAt3 != null && (childAt3 instanceof ViewGroup) && (childAt = ((ViewGroup) childAt3).getChildAt(0)) != null && (childAt instanceof TotalKeyView)) {
                        TotalKeyView totalKeyView = (TotalKeyView) childAt;
                        totalKeyView.setKeyValue(str);
                        totalKeyView.setBaseline(this.baseline);
                        totalKeyView.setKeyboardType(2);
                        if (!totalKeyView.getEnlargePopType().contains("_normal")) {
                            totalKeyView.setWidth(this.mBigKeyViewWidth);
                        } else {
                            totalKeyView.setWidth(i2);
                        }
                    }
                }
            }
        }
        invalidate();
    }

    public void dismissEnlargeKeyView() {
        dismissPreview(this.mPreviewPopup);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_number_ABC && id != R.id.btn_symbol_ABC) {
            if (id != R.id.btn_letter_123 && id != R.id.btn_symbol_123) {
                if (id != R.id.btn_letter_symbol && id != R.id.btn_number_symbol) {
                    if (id == R.id.btn_key_capslock) {
                        boolean z = !this.isCapsLock;
                        this.isCapsLock = z;
                        switchCapsLock(this.mLetterKeyboardView, z);
                        return;
                    } else if (id != R.id.btn_number_del && id != R.id.btn_letter_del && id != R.id.btn_symbol_del) {
                        if (id != R.id.btn_number_sure && id != R.id.btn_letter_sure && id != R.id.btn_symbol_sure) {
                            if (id == R.id.btn_symbol_more) {
                                this.SYMBOL_TEXTS_TYPE = this.SYMBOL_TEXTS_TYPE != 1 ? 1 : 2;
                                switchKeyboard(3);
                                return;
                            }
                            KeyboardView.KeyboardViewCallback keyboardViewCallback = this.keyboardViewCallback;
                            if (keyboardViewCallback != null) {
                                keyboardViewCallback.onKeyInput(view);
                                return;
                            }
                            return;
                        }
                        KeyboardView.KeyboardViewCallback keyboardViewCallback2 = this.keyboardViewCallback;
                        if (keyboardViewCallback2 != null) {
                            keyboardViewCallback2.onSure(view);
                            return;
                        }
                        return;
                    } else {
                        KeyboardView.KeyboardViewCallback keyboardViewCallback3 = this.keyboardViewCallback;
                        if (keyboardViewCallback3 != null) {
                            keyboardViewCallback3.onKeyDelete(view);
                            return;
                        }
                        return;
                    }
                }
                switchKeyboard(3);
                return;
            }
            switchKeyboard(1);
            return;
        }
        switchKeyboard(2);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_number_del || id == R.id.btn_letter_del || id == R.id.btn_symbol_del) {
            this.keyboardViewCallback.onKeyDeleteAll(view);
            return true;
        }
        return false;
    }

    public void onOverKeyPreViewShow(float f2, float f3) {
        if (this.showPressBg) {
            for (Rect rect : this.keyRects.keySet()) {
                if (rect.contains((int) f2, (int) f3)) {
                    if (!this.keyRects.get(rect).getTag().toString().equals(this.currentPopText)) {
                        showPreview((TotalKeyView) this.keyRects.get(rect), rect.left, rect.top);
                    }
                    this.currentPopText = this.keyRects.get(rect).getTag().toString();
                }
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == this.totalKeyboard) {
            return true;
        }
        if (view != null && (view instanceof TotalKeyView) && motionEvent.getPointerCount() == 1) {
            int action = motionEvent.getAction();
            if (action == 0) {
                if (this.showPressBg) {
                    Rect rect = new Rect();
                    view.getGlobalVisibleRect(rect);
                    showPreview((TotalKeyView) view, rect.left, rect.top);
                    this.x1 = motionEvent.getX();
                    this.y1 = motionEvent.getY();
                }
                view.setPressed(this.showPressBg);
                view.performClick();
                return true;
            } else if (action != 1) {
                if (action == 2 && this.showPressBg) {
                    this.x2 = motionEvent.getX();
                    this.y2 = motionEvent.getY();
                    float f2 = this.x2;
                    float f3 = this.x1;
                    if (f2 - f3 > 65.0f || f3 - f2 > 65.0f) {
                        onOverKeyPreViewShow(motionEvent.getRawX(), motionEvent.getRawY());
                    }
                }
                return false;
            } else {
                if (this.showPressBg) {
                    new Handler().postDelayed(new Runnable() { // from class: com.jdjr.generalKeyboard.views.BasicTotalKeyboard.3
                        @Override // java.lang.Runnable
                        public void run() {
                            BasicTotalKeyboard basicTotalKeyboard = BasicTotalKeyboard.this;
                            basicTotalKeyboard.dismissPreview(basicTotalKeyboard.mPreviewPopup);
                        }
                    }, 200L);
                }
                view.setPressed(false);
                return true;
            }
        }
        return false;
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(String str) {
        if ("red".equals(str)) {
            Button button = this.btnNumberSure;
            int i2 = R.drawable.security_total_function_key_red_bg_selector;
            button.setBackgroundResource(i2);
            this.btnSymbolSure.setBackgroundResource(i2);
            this.btnLetterSure.setBackgroundResource(i2);
        } else if ("gold".equals(str)) {
            Button button2 = this.btnNumberSure;
            int i3 = R.drawable.security_total_function_key_gold_bg_selector;
            button2.setBackgroundResource(i3);
            this.btnSymbolSure.setBackgroundResource(i3);
            this.btnLetterSure.setBackgroundResource(i3);
        }
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureEnabled(boolean z) {
        this.btnNumberSure.setEnabled(z);
        this.btnSymbolSure.setEnabled(z);
        this.btnLetterSure.setEnabled(z);
        this.btnNumberSure.setSelected(z);
        this.btnSymbolSure.setSelected(z);
        this.btnLetterSure.setSelected(z);
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureText(CharSequence charSequence) {
        this.btnNumberSure.setText(charSequence);
        this.btnSymbolSure.setText(charSequence);
        this.btnLetterSure.setText(charSequence);
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void showAt(View view) {
        super.showAt(view);
        this.btnNumberABC.performClick();
    }

    public void switchKeyboard(int i2) {
        this.keyViewBackground = KeyboardUiMode.isDark() ? R.drawable.security_total_key_bg_selector_dark : R.drawable.security_total_key_bg_selector;
        this.keyViewTextColor = this.mContext.getResources().getColor(KeyboardUiMode.isDark() ? R.color.keyboard_color_total_key_text_dark : R.color.keyboard_color_total_key_text);
        if (this.keyRects.size() > 0) {
            this.keyRects.clear();
        }
        this.currentType = i2;
        if (i2 == 1) {
            this.mSymbolKeyboardView.setVisibility(8);
            this.mLetterKeyboardView.setVisibility(8);
            this.mNumberKeyboardView.setVisibility(0);
            this.mBigKeyViewWidth = this.mBigKeyViewWidthOnNumber;
            drawKeyText(this.mNumberKeyboardView, 1);
        } else if (i2 == 2) {
            this.mSymbolKeyboardView.setVisibility(8);
            this.mNumberKeyboardView.setVisibility(8);
            this.mLetterKeyboardView.setVisibility(0);
            drawKeyText(this.mLetterKeyboardView, 2);
            switchCapsLock(this.mLetterKeyboardView, this.isCapsLock);
        } else if (i2 != 3) {
        } else {
            this.mLetterKeyboardView.setVisibility(8);
            this.mNumberKeyboardView.setVisibility(8);
            this.mSymbolKeyboardView.setVisibility(0);
            this.mBigKeyViewWidth = this.mBigKeyViewWidthOnSymbol;
            drawKeyText(this.mSymbolKeyboardView, 3);
        }
    }

    public BasicTotalKeyboard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BasicTotalKeyboard(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.keyRects = new HashMap<>();
        this.baseline = 0;
        this.mPreviewPopup = null;
        this.currentType = 2;
        this.SYMBOL_TEXTS_TYPE = 1;
        initField(context);
        initKeyText();
        initLayout();
        setFocusable(true);
        setSureEnabled(false);
        switchKeyboard(this.currentType);
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(@ColorRes int... iArr) {
        if (iArr == null || iArr.length < 3) {
            return;
        }
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        if (Build.VERSION.SDK_INT >= 16) {
            UIUtils.createSelector(this.btnNumberSure, this.mContext, i2, i3, i4);
            UIUtils.createSelector(this.btnSymbolSure, this.mContext, i2, i3, i4);
            UIUtils.createSelector(this.btnLetterSure, this.mContext, i2, i3, i4);
        }
    }

    @Override // com.jdjr.generalKeyboard.views.KeyboardView
    public void setSureBackgroundResource(String str, String str2, String str3) {
        Context context = this.mContext;
        if (context != null && Build.VERSION.SDK_INT >= 16) {
            UIUtils.createSelector(this.btnNumberSure, context, str, str2, str3);
            UIUtils.createSelector(this.btnSymbolSure, this.mContext, str, str2, str3);
            UIUtils.createSelector(this.btnLetterSure, this.mContext, str, str2, str3);
        }
    }

    public void switchCapsLock(boolean z) {
        this.isCapsLock = z;
        if (this.currentType == 2) {
            switchCapsLock(this.mLetterKeyboardView, z);
        }
    }
}
