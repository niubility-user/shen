package com.jd.lib.productdetail.core.views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes15.dex */
public class ClickUrlSpanCopy extends URLSpan {
    private Context context;
    private String copyText;
    private String failureTip;
    private String skuId;
    private String successTip;

    public ClickUrlSpanCopy(Context context, String str, String str2, String str3, String str4) {
        super(str);
        this.skuId = str;
        this.copyText = str2;
        this.context = context;
        this.successTip = str3;
        this.failureTip = str4;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        if (this.context == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.skuId)) {
            PDUtils.onClick("ReservePopup_JDCopy", this.skuId);
        }
        try {
            ((ClipboardManager) this.context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, this.copyText));
            PDUtils.showToastCenterNormal(this.context, this.successTip);
        } catch (Throwable th) {
            if (Log.E) {
                th.printStackTrace();
            }
            PDUtils.showToastCenterNormal(this.context, this.failureTip);
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
    }
}
