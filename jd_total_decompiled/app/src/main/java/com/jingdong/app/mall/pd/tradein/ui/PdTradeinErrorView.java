package com.jingdong.app.mall.pd.tradein.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.tradein.R;

/* loaded from: classes4.dex */
public class PdTradeinErrorView extends ConstraintLayout {
    public TextView mBtnRetry;
    public ImageView mIcon;
    public TextView mTextMsg;

    /* loaded from: classes4.dex */
    public enum ErrorType {
        UNKNOWN,
        NONET
    }

    public PdTradeinErrorView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.pd_tradein_common_error_icon);
        this.mTextMsg = (TextView) findViewById(R.id.pd_tradein_common_error_msg);
        this.mBtnRetry = (TextView) findViewById(R.id.pd_tradein_common_error_btn_retry);
    }

    public void setType(ErrorType errorType) {
        if (errorType == ErrorType.UNKNOWN) {
            this.mIcon.setImageResource(com.jingdong.common.R.drawable.pd_feeds_common_error_unknown);
            this.mTextMsg.setText(R.string.pd_tradein_common_error_msg_unknown);
        } else if (errorType == ErrorType.NONET) {
            this.mIcon.setImageResource(com.jingdong.common.R.drawable.pd_feeds_common_error_nonet);
            this.mTextMsg.setText(R.string.pd_tradein_common_error_msg_nonet);
        }
    }

    public PdTradeinErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PdTradeinErrorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
