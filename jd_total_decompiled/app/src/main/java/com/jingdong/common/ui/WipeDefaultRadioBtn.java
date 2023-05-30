package com.jingdong.common.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.jd.base.history.widget.R;
import com.jingdong.common.UnLog;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes6.dex */
public class WipeDefaultRadioBtn extends RadioButton implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "WipeDefaultRadioBtn";
    private Drawable mSelectedDrawable;

    public WipeDefaultRadioBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mSelectedDrawable = getCompoundDrawables()[0];
        if (UnLog.D) {
            UnLog.d(TAG, " init---> mSelectedDrawable : " + this.mSelectedDrawable);
        }
        if (this.mSelectedDrawable == null) {
            this.mSelectedDrawable = getResources().getDrawable(R.drawable.add_to_cart);
        }
        Drawable drawable = this.mSelectedDrawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.mSelectedDrawable.getIntrinsicHeight());
        setSelectedIconVisible(false);
        setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setSelectedIconVisible(false);
    }

    public void setSelectedIconVisible(boolean z) {
        setCompoundDrawables(z ? this.mSelectedDrawable : null, null, null, null);
    }
}
