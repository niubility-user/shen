package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDLVProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBody;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.ui.JDDialog;

/* loaded from: classes3.dex */
public class ClickUrlSpanForYuYue extends URLSpan {
    String clickEventId;
    private boolean haveUnderLineText;
    private Context mContext;
    String mEventParam;
    private JDDialog mJdDialog;
    private String mManageKey;
    private String mShopId;
    String mSkuId;

    public ClickUrlSpanForYuYue(Context context, String str, String str2, String str3, JDDialog jDDialog) {
        super(str);
        this.haveUnderLineText = false;
        this.mContext = context;
        this.mSkuId = str;
        this.mShopId = str2;
        this.mManageKey = str3;
        this.mJdDialog = jDDialog;
    }

    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        PDLVProtocol pDLVProtocol = new PDLVProtocol(this.mManageKey);
        PdLVBody.Builder builder = new PdLVBody.Builder();
        builder.add("skuId", this.mSkuId).add("comefrom", "followShop").add("shopId", this.mShopId);
        pDLVProtocol.setInputParams(builder.getJsonValue());
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).addHttpGroupWithNPSSetting(pDLVProtocol.request());
        }
        JDDialog jDDialog = this.mJdDialog;
        if (jDDialog != null) {
            jDDialog.dismiss();
        }
        TextUtils.isEmpty(this.clickEventId);
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
