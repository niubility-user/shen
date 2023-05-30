package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class ClickUrlSpan extends URLSpan {
    String clickEventId;
    private boolean haveUnderLineText;
    private Context mContext;
    String mEventParam;
    String mUrl;

    public ClickUrlSpan(Context context, String str) {
        super(str);
        this.haveUnderLineText = true;
        this.mContext = context;
        this.mUrl = str;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        if (TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        PDBaseDeepLinkHelper.gotoMWithUrl(this.mContext, this.mUrl.trim());
        if (TextUtils.isEmpty(this.clickEventId)) {
            return;
        }
        PDUtils.onClick(this.clickEventId, this.mEventParam);
    }

    public void setEvents(String str, String str2) {
        this.clickEventId = str;
        this.mEventParam = str2;
    }

    public void setUnderlineText(boolean z) {
        this.haveUnderLineText = z;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.haveUnderLineText);
    }
}
