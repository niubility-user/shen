package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.TextFontUtils;

/* loaded from: classes18.dex */
public class FlexibleEditText extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_MONEY = 0;
    public static final int TYPE_PWD = 1;
    private Button btnDeleteAll;
    private Button btnEye;
    private int category;
    private EditText etInput;
    private ImageView ivMoney;
    private Callback mCallback;
    private Context mContext;
    private RelativeLayout mRootView;
    private RelativeLayout rlFlexContainer;
    private View vLine;

    /* loaded from: classes18.dex */
    public interface Callback {
        void onDelClick(View view);

        void onEyeClick(View view, boolean z);
    }

    public FlexibleEditText(Context context) {
        this(context, null);
    }

    private void setDeleteIconShow(int i2) {
        if (this.category == 0) {
            this.btnDeleteAll.setVisibility(i2);
            return;
        }
        this.btnDeleteAll.setVisibility(i2);
        this.vLine.setVisibility(i2);
    }

    private void setHintTextSize() {
        this.etInput.setTextSize(18.0f);
        this.etInput.setTypeface(Typeface.DEFAULT_BOLD);
        if ("payment".equals(getTag().toString())) {
            this.etInput.setPadding(0, 0, 0, 0);
        } else {
            this.etInput.setPadding((int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_left), 0, 0, 0);
        }
    }

    private void setTextSize() {
        if ("payment".equals(getTag().toString())) {
            this.etInput.setPadding(0, (int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_top), 0, 0);
            this.etInput.setTextSize(27.0f);
            Typeface uDC1BoldFont = TextFontUtils.getUDC1BoldFont(this.mContext);
            if (uDC1BoldFont != null) {
                this.etInput.setTypeface(uDC1BoldFont);
                return;
            }
            return;
        }
        this.etInput.setPadding((int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_left), 0, 0, (int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_bottom));
        this.etInput.setTextSize(20.0f);
        this.etInput.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_delete_all) {
            this.etInput.setText("");
            this.mCallback.onDelClick(view);
        } else if (view.getId() == R.id.btn_eye) {
            if (view.isSelected()) {
                view.setContentDescription(getResources().getString(R.string.security_eye_closed));
            } else {
                view.setContentDescription(getResources().getString(R.string.security_eye_opened));
            }
            this.mCallback.onEyeClick(view, view.isSelected());
        }
    }

    public void onInputChange(String str, boolean z) {
        if (str == null) {
            return;
        }
        if (z) {
            this.etInput.setInputType(2);
            if (str.length() > 0) {
                setTextSize();
            } else {
                setHintTextSize();
            }
        } else {
            this.etInput.setInputType(18);
            this.etInput.setTypeface(Typeface.DEFAULT_BOLD);
            this.etInput.setPadding((int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_left), (int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_circle_padding_top), 0, 0);
        }
        if (str.length() > 0) {
            setDeleteIconShow(0);
        } else {
            setDeleteIconShow(4);
        }
        this.etInput.setText(str);
        this.etInput.setSelection(str.length());
    }

    public void refresh() {
        if (KeyboardUiMode.isDark()) {
            this.rlFlexContainer.setBackgroundResource(R.drawable.security_flexible_edittext_bg_dark);
            this.ivMoney.setBackgroundResource(R.drawable.security_money_left_icon_dark);
            EditText editText = this.etInput;
            Resources resources = getResources();
            int i2 = R.color.keyboard_color_input_text_dark;
            editText.setTextColor(resources.getColor(i2));
            this.etInput.setHintTextColor(getResources().getColor(i2));
            this.btnEye.setBackgroundResource(R.drawable.security_pwd_visibility_selector_dark);
            this.btnDeleteAll.setBackgroundResource(R.drawable.security_input_delete_right_icon_dark);
            return;
        }
        this.rlFlexContainer.setBackgroundResource(R.drawable.security_flexible_edittext_bg);
        this.ivMoney.setBackgroundResource(R.drawable.security_money_left_icon);
        EditText editText2 = this.etInput;
        Resources resources2 = getResources();
        int i3 = R.color.keyboard_color_input_text;
        editText2.setTextColor(resources2.getColor(i3));
        this.etInput.setHintTextColor(getResources().getColor(i3));
        this.btnEye.setBackgroundResource(R.drawable.security_pwd_visibility_selector);
        this.btnDeleteAll.setBackgroundResource(R.drawable.security_input_delete_right_icon);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setHint(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.etInput.setHint(charSequence);
    }

    public void setSelection() {
        this.etInput.requestFocus();
        this.etInput.findFocus();
        EditText editText = this.etInput;
        editText.setSelection(TextUtils.isEmpty(editText.getText()) ? 0 : this.etInput.getText().length());
    }

    public FlexibleEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexibleEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SecureFlexibleEditText, i2, 0);
        this.category = obtainStyledAttributes.getInteger(R.styleable.SecureFlexibleEditText_security_editTextCategory, 0);
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.security_compoment_flexible_edittext, (ViewGroup) this, true);
        this.mRootView = relativeLayout;
        this.rlFlexContainer = (RelativeLayout) relativeLayout.findViewById(R.id.rl_flex_container);
        this.ivMoney = (ImageView) this.mRootView.findViewById(R.id.iv_left);
        this.vLine = this.mRootView.findViewById(R.id.v_middle_split_line);
        this.btnDeleteAll = (Button) this.mRootView.findViewById(R.id.btn_delete_all);
        Button button = (Button) this.mRootView.findViewById(R.id.btn_eye);
        this.btnEye = button;
        button.setContentDescription(getResources().getString(R.string.security_eye_closed));
        EditText editText = (EditText) this.mRootView.findViewById(R.id.et_middle);
        this.etInput = editText;
        editText.requestFocus();
        this.etInput.findFocus();
        this.etInput.setInputType(2);
        this.etInput.setOnTouchListener(new View.OnTouchListener() { // from class: com.jdjr.generalKeyboard.views.FlexibleEditText.1
            {
                FlexibleEditText.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FlexibleEditText.this.etInput.requestFocus();
                FlexibleEditText.this.etInput.findFocus();
                FlexibleEditText.this.etInput.setSelection(TextUtils.isEmpty(FlexibleEditText.this.etInput.getText()) ? 0 : FlexibleEditText.this.etInput.getText().length());
                return true;
            }
        });
        this.btnDeleteAll.setOnClickListener(this);
        this.btnEye.setOnClickListener(this);
        this.etInput.setTextSize(18.0f);
        this.etInput.setTypeface(Typeface.DEFAULT_BOLD);
        refresh();
        int i3 = this.category;
        if (i3 == 0) {
            this.ivMoney.setVisibility(0);
            this.btnEye.setVisibility(8);
            this.etInput.setPadding(0, 0, 0, 0);
        } else if (i3 == 1) {
            this.btnEye.setVisibility(0);
            this.ivMoney.setVisibility(8);
            this.etInput.setPadding((int) this.mContext.getResources().getDimension(R.dimen.security_total_long_input_padding_left), 0, 0, 0);
        }
        obtainStyledAttributes.recycle();
    }
}
