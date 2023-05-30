package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;

/* loaded from: classes12.dex */
public class NumTextSwitch extends TextSwitcher {
    private TranslateAnimation addIn;
    private TranslateAnimation addOut;
    private Context context;
    private EditText editText;
    private boolean isAdd;
    private TranslateAnimation reduceIn;
    private TranslateAnimation reduceOut;

    public NumTextSwitch(Context context) {
        super(context);
        this.isAdd = true;
        this.context = context;
        init();
    }

    private void init() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.addIn = translateAnimation;
        translateAnimation.setDuration(200L);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
        this.addOut = translateAnimation2;
        translateAnimation2.setDuration(200L);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
        this.reduceIn = translateAnimation3;
        translateAnimation3.setDuration(200L);
        TranslateAnimation translateAnimation4 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.reduceOut = translateAnimation4;
        translateAnimation4.setDuration(200L);
        setFactory(new ViewSwitcher.ViewFactory() { // from class: com.jingdong.common.widget.NumTextSwitch.1
            @Override // android.widget.ViewSwitcher.ViewFactory
            public View makeView() {
                NumTextSwitch.this.editText = new EditText(NumTextSwitch.this.context);
                NumTextSwitch.this.editText.setGravity(17);
                NumTextSwitch.this.editText.setTextColor(Color.parseColor("#555555"));
                NumTextSwitch.this.editText.setSingleLine(true);
                NumTextSwitch.this.editText.setTextSize(14.0f);
                NumTextSwitch.this.editText.setBackgroundDrawable(null);
                NumTextSwitch.this.editText.setInputType(2);
                NumTextSwitch.this.editText.setPadding(0, 0, 0, 0);
                NumTextSwitch.this.editText.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                NumTextSwitch.this.setFocusable(true);
                NumTextSwitch.this.setFocusableInTouchMode(true);
                return NumTextSwitch.this.editText;
            }
        });
        setInAnimation(this.addIn);
        setOutAnimation(this.addOut);
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setAddText(CharSequence charSequence) {
        if (!this.isAdd) {
            setInAnimation(this.addIn);
            setOutAnimation(this.addOut);
            this.isAdd = true;
        }
        setText(charSequence);
    }

    public void setCanEditor(boolean z) {
        EditText editText = this.editText;
        if (editText != null) {
            editText.setEnabled(z);
        }
    }

    public void setReduceText(CharSequence charSequence) {
        if (this.isAdd) {
            setInAnimation(this.reduceIn);
            setOutAnimation(this.reduceOut);
            this.isAdd = false;
        }
        setText(charSequence);
    }

    public NumTextSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAdd = true;
        this.context = context;
        init();
    }
}
