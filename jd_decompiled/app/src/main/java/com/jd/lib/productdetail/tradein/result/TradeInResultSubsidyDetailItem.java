package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.l.f;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes16.dex */
public class TradeInResultSubsidyDetailItem extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f5448g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5449h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f5450i;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ TradeInResultData.TradeInFloorData f5451g;

        /* renamed from: com.jd.lib.productdetail.tradein.result.TradeInResultSubsidyDetailItem$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        public class DialogInterfaceOnClickListenerC0161a implements DialogInterface.OnClickListener {
            public DialogInterfaceOnClickListenerC0161a(a aVar) {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        }

        public a(TradeInResultData.TradeInFloorData tradeInFloorData) {
            this.f5451g = tradeInFloorData;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultData.TradeInFloorData tradeInFloorData = this.f5451g;
            if (tradeInFloorData == null) {
                return;
            }
            if (tradeInFloorData.iContent4OldWareSubsidy != null) {
                f.a aVar = new f.a(TradeInResultSubsidyDetailItem.this.getContext());
                TradeInResultData.OldWareSubsidy oldWareSubsidy = this.f5451g.iContent4OldWareSubsidy;
                aVar.b = oldWareSubsidy.title;
                aVar.d = oldWareSubsidy.contentList;
                aVar.f5390c = oldWareSubsidy.buttonText;
                aVar.f5391e = new DialogInterfaceOnClickListenerC0161a(this);
                aVar.a().show();
            } else if (TextUtils.isEmpty(tradeInFloorData.iContent)) {
            } else {
                com.jd.lib.productdetail.tradein.l.j jVar = new com.jd.lib.productdetail.tradein.l.j(TradeInResultSubsidyDetailItem.this.getContext());
                TradeInResultData.TradeInFloorData tradeInFloorData2 = this.f5451g;
                jVar.a(tradeInFloorData2.text1, tradeInFloorData2.iContent);
                jVar.show();
            }
        }
    }

    public TradeInResultSubsidyDetailItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(TradeInResultData.TradeInFloorData tradeInFloorData) {
        if (tradeInFloorData != null) {
            this.f5448g.setText(tradeInFloorData.text1);
            TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight = tradeInFloorData.rightInfo;
            if (barterFloorRight != null && !TextUtils.isEmpty(barterFloorRight.getText1())) {
                this.f5450i.setText(tradeInFloorData.rightInfo.getText1());
            }
            if (!TextUtils.isEmpty(tradeInFloorData.coupon)) {
                this.f5449h.setText(tradeInFloorData.coupon);
                this.f5449h.setVisibility(0);
            } else {
                this.f5449h.setVisibility(8);
            }
            if (TextUtils.isEmpty(tradeInFloorData.iContent) && tradeInFloorData.iContent4OldWareSubsidy == null) {
                this.f5448g.setCompoundDrawables(null, null, null, null);
                this.f5448g.setOnClickListener(null);
                return;
            }
            Drawable drawable = getResources().getDrawable(R.drawable.tradein_icon_i);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            this.f5448g.setCompoundDrawables(null, null, drawable, null);
            this.f5448g.setOnClickListener(new a(tradeInFloorData));
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5448g = (TextView) findViewById(R.id.tradein_result_subsidy_detail_title);
        this.f5450i = (TextView) findViewById(R.id.tradein_result_subsidy_detail_value);
        this.f5449h = (TextView) findViewById(R.id.tradein_result_coupon_title_label);
        FontsUtil.changeTextFont(this.f5450i);
    }
}
