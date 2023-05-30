package com.jd.lib.productdetail.tradein.estimate;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes16.dex */
public class TradeInEstimateOldDevice extends CardView {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f5306g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5307h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f5308i;

    /* renamed from: j  reason: collision with root package name */
    public ConstraintLayout f5309j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f5310k;

    /* renamed from: l  reason: collision with root package name */
    public TextView f5311l;

    /* renamed from: m  reason: collision with root package name */
    public View f5312m;

    /* renamed from: n  reason: collision with root package name */
    public int f5313n;
    public TradeInEstimateData.Data o;
    public boolean p;
    public TradeInDialogFragment q;
    public View.OnClickListener r;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("extra.key.from.estimate.page", true);
            bundle.putBoolean("extra.key.from.tradein.page", TradeInEstimateOldDevice.this.p);
            TradeInEstimateOldDevice.this.q.moveToStep(TradeInStep.SELECT_OLD_DEVICE, bundle);
            View.OnClickListener onClickListener = TradeInEstimateOldDevice.this.r;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public TradeInEstimateOldDevice(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries) {
        TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem;
        if (oldProductInquiries != null) {
            this.f5308i.setText(oldProductInquiries.productName);
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            createSimple.setRoundingParams(new RoundingParams().setCornersRadius(PDUtils.dip2px(6.0f)));
            JDImageUtils.displayImage(oldProductInquiries.fullImageUrl, this.f5306g, createSimple);
            if (TextUtils.isEmpty(oldProductInquiries.oldProductLogoText)) {
                oldProductInquiries.oldProductLogoText = "\u65e7\u673a";
            }
            this.f5307h.setText(oldProductInquiries.oldProductLogoText);
            if (this.f5313n == 1) {
                this.f5312m.setVisibility(0);
            } else {
                TradeInEstimateData.Data data = this.o;
                if (data != null && (tagItem = data.tagInfo) != null && tagItem.tagType == 2) {
                    this.f5312m.setVisibility(8);
                } else {
                    this.f5312m.setVisibility(0);
                }
            }
            TradeInEstimateData.Data data2 = this.o;
            if (data2 != null && data2.localSubsidyInfo != null) {
                this.f5308i.setMaxLines(1);
                this.f5309j.setVisibility(0);
                if (!TextUtils.isEmpty(this.o.localSubsidyInfo.evaluationInfoText)) {
                    this.f5310k.setText(this.o.localSubsidyInfo.evaluationInfoText);
                    this.f5310k.setVisibility(0);
                } else {
                    this.f5310k.setVisibility(8);
                }
                if (!TextUtils.isEmpty(this.o.localSubsidyInfo.subsidyInfoText)) {
                    this.f5311l.setText(this.o.localSubsidyInfo.subsidyInfoText);
                    this.f5311l.setVisibility(0);
                    return;
                }
                this.f5311l.setVisibility(8);
                return;
            }
            this.f5308i.setMaxLines(2);
            this.f5309j.setVisibility(8);
            return;
        }
        this.f5307h.setText("\u65e7\u673a");
        this.f5312m.setVisibility(8);
        this.f5309j.setVisibility(8);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5307h = (TextView) findViewById(R.id.tradein_estimate_old_device_tag);
        this.f5306g = (SimpleDraweeView) findViewById(R.id.tradein_estimate_old_device_pic);
        this.f5308i = (TextView) findViewById(R.id.tradein_estimate_old_device_name);
        this.f5309j = (ConstraintLayout) findViewById(R.id.tradein_estimate_old_device_subsidy);
        this.f5310k = (TextView) findViewById(R.id.tradein_estimate_local_device_evaluation);
        this.f5311l = (TextView) findViewById(R.id.tradein_estimate_local_device_subsidy);
        View findViewById = findViewById(R.id.tradein_estimate_btn_back);
        this.f5312m = findViewById;
        findViewById.setOnClickListener(new a());
    }
}
