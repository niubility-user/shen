package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes3.dex */
public class PdYuYueMadDialogHolder {
    private TextView couponNum;
    private TextView couponPromotion;
    private ImageView couponReceive;
    private TextView couponTime;
    private TextView couponTitle;
    private TextView couponTitleTextView;
    private View couponWrapper;
    private Context mContext;
    private View mCouponView;
    private JDDialog mJdDialog;
    public TextView yuyueMad;

    public PdYuYueMadDialogHolder(Context context, JDDialog jDDialog) {
        this.yuyueMad = null;
        this.mContext = context;
        this.mJdDialog = jDDialog;
        jDDialog.setContentView(R.layout.libpdstyleinfoview_common_dialog_style_6);
        this.yuyueMad = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_dialog_message);
        this.mCouponView = jDDialog.findViewById(R.id.mad_coupon_wrapper);
        this.couponNum = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_discount_num);
        this.couponPromotion = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_coupon_promotion_text);
        this.couponTitle = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_coupon_title);
        this.couponTitleTextView = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_icon_coupon_title);
        this.couponWrapper = jDDialog.findViewById(R.id.libpdstyleinfoview_coupon_wrapper);
        this.couponTime = (TextView) jDDialog.findViewById(R.id.libpdstyleinfoview_coupon_mad_time);
        this.couponReceive = (ImageView) jDDialog.findViewById(R.id.coupon_receive);
        TextView textView = this.yuyueMad;
        jDDialog.messageView = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        jDDialog.posButton = (Button) jDDialog.findViewById(R.id.jd_dialog_pos_button);
        jDDialog.negButton = (Button) jDDialog.findViewById(R.id.jd_dialog_neg_button);
        jDDialog.titleView = (TextView) jDDialog.findViewById(R.id.jd_dialog_title);
        jDDialog.useCancelClickEvent(jDDialog.posButton);
        jDDialog.useCancelClickEvent(jDDialog.negButton);
    }

    private void bindCouponData(PDOperAppointEntity.CouponContent couponContent) {
        if (!TextUtils.isEmpty(couponContent.sum)) {
            SpannableString priceText = PDUtils.getPriceText(this.mContext, "\u00a5" + couponContent.sum, 0.45f);
            if (priceText != null) {
                this.couponNum.setVisibility(0);
                this.couponNum.setText(priceText);
            }
        } else {
            this.couponNum.setVisibility(4);
        }
        if (!TextUtils.isEmpty(couponContent.useCondition)) {
            this.couponPromotion.setVisibility(0);
            this.couponPromotion.setText(couponContent.useCondition);
        } else {
            this.couponPromotion.setVisibility(8);
        }
        if (!TextUtils.isEmpty(couponContent.text)) {
            this.couponTitleTextView.setText(couponContent.text);
            this.couponTitleTextView.setVisibility(0);
        } else {
            this.couponTitleTextView.setVisibility(8);
        }
        if (this.couponTitleTextView.getVisibility() == 0) {
            this.couponTitle.setText(getSpannableString(this.mContext, (int) this.couponPromotion.getPaint().measureText(couponContent.text), couponContent.useScope));
        } else {
            this.couponTitle.setText(couponContent.useScope);
        }
        this.couponTime.setText(couponContent.expiryDate);
        if (TextUtils.equals(couponContent.type, "1")) {
            this.couponWrapper.setBackgroundResource(R.drawable.libpdstyleinfoview_icon_coupon_discount_bg);
        }
        JDImageUtils.displayImage(couponContent.receiveImg, this.couponReceive, (JDDisplayImageOptions) null, false);
    }

    public static SpannableString getSpannableString(Context context, int i2, String str) {
        if (str == null) {
            str = "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new LeadingMarginSpan.Standard(i2 + DPIUtil.dip2px(context, 10.0f), 0), 0, str.length(), 17);
        return spannableString;
    }

    public void bindData2View(String str, String str2, String str3, String str4, PDOperAppointEntity pDOperAppointEntity) {
        this.mJdDialog.setMessage(str);
        this.mJdDialog.setTitle(str2);
        this.mJdDialog.posButton.setText(str3);
        this.mJdDialog.negButton.setText(str4);
        if (pDOperAppointEntity.couponContent != null) {
            this.yuyueMad.setPadding(0, PDUtils.dip2px(6.0f), 0, 0);
            this.mCouponView.setVisibility(0);
            bindCouponData(pDOperAppointEntity.couponContent);
            return;
        }
        this.yuyueMad.setPadding(0, PDUtils.dip2px(14.0f), 0, 0);
        this.mCouponView.setVisibility(8);
    }
}
