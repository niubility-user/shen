package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.jdsdk.utils.JdStringUtils;

/* loaded from: classes4.dex */
public class JDCustomEditText extends EditText {
    public JDCustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView
    public boolean onTextContextMenuItem(int i2) {
        if (i2 == 16908322) {
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
            String text = clipboardManager != null ? clipboardManager.getText() : "";
            if (!TextUtils.isEmpty(text)) {
                clipboardManager.setText(JdStringUtils.getPhoneNumber(text.toString()));
            }
        }
        return super.onTextContextMenuItem(i2);
    }
}
