package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.entity.CashierPayItemInfoEntity;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.b;
import com.jd.lib.cashier.sdk.core.utils.t;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.util.List;

/* loaded from: classes14.dex */
public class CashierAPayItemView extends CashierItemView {

    /* loaded from: classes14.dex */
    class a extends b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2951j;

        a(CashierAPayItemView cashierAPayItemView, View.OnClickListener onClickListener) {
            this.f2951j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2951j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public CashierAPayItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void S(int i2, int i3, int i4, int i5) {
        RelativeLayout relativeLayout = this.f2984g;
        if (relativeLayout != null) {
            relativeLayout.setPadding(i2, i3, i4, i5);
        }
    }

    public void T(Payment payment, Context context) {
        TextView textView;
        GouWuJinModel gouWuJinModel;
        List<GouWuJinWalletInfo> list;
        List<ProductInfo> list2;
        ImageView imageView;
        if (payment == null) {
            return;
        }
        TextView textView2 = this.q;
        if ((textView2 == null || textView2.getVisibility() != 0) && ((textView = this.p) == null || textView.getVisibility() != 0)) {
            return;
        }
        t tVar = new t(context);
        CashierPayItemInfoEntity cashierPayItemInfoEntity = new CashierPayItemInfoEntity();
        cashierPayItemInfoEntity.leftAndRightSpace = 8;
        cashierPayItemInfoEntity.insideSpace = 12;
        cashierPayItemInfoEntity.nameSize = 14;
        cashierPayItemInfoEntity.logoWidth = 20;
        cashierPayItemInfoEntity.statusDescSize = 12;
        cashierPayItemInfoEntity.channelNameTailSize = 10;
        cashierPayItemInfoEntity.channelNameTailPadding = 10;
        cashierPayItemInfoEntity.channelNameMiddleSize = 13;
        ImageView imageView2 = this.f2991n;
        if (imageView2 != null && imageView2.getVisibility() == 0 && (imageView = this.o) != null && imageView.getVisibility() == 0) {
            cashierPayItemInfoEntity.icon1Width = 24;
            cashierPayItemInfoEntity.icon2Width = 24;
        } else {
            ImageView imageView3 = this.f2991n;
            if (imageView3 != null && imageView3.getVisibility() == 0) {
                if ((TextUtils.equals(CashierPayChannelCode.JD_PAY_JXJ, payment.code) && payment.showSkuList && (list2 = payment.productInfos) != null && !list2.isEmpty()) || !(!TextUtils.equals("GOUWUJIN", payment.code) || (gouWuJinModel = payment.gouWuJinModel) == null || (list = gouWuJinModel.walletInfos) == null || list.isEmpty())) {
                    cashierPayItemInfoEntity.icon1Width = 13;
                } else {
                    cashierPayItemInfoEntity.icon1Width = 24;
                }
                cashierPayItemInfoEntity.icon2Width = 0;
            } else {
                cashierPayItemInfoEntity.icon1Width = 0;
                cashierPayItemInfoEntity.icon2Width = 0;
            }
        }
        cashierPayItemInfoEntity.channelNameTailLeftSpace = 3;
        cashierPayItemInfoEntity.channelNameMiddleLeftSpace = 3;
        cashierPayItemInfoEntity.icon1LeftSpace = 4;
        cashierPayItemInfoEntity.icon2LeftSpace = 2;
        cashierPayItemInfoEntity.nameLeftSpace = 12;
        cashierPayItemInfoEntity.checkboxWidth = 16;
        cashierPayItemInfoEntity.tipRightSpace = 12;
        cashierPayItemInfoEntity.moreInfoTipSize = 12;
        cashierPayItemInfoEntity.tipNameSize = 12;
        cashierPayItemInfoEntity.tipArrowWidth = 14;
        TextView textView3 = this.q;
        if (textView3 != null && textView3.getVisibility() == 0) {
            cashierPayItemInfoEntity.moreInfoTipShow = true;
        } else {
            cashierPayItemInfoEntity.preferentiaNumShow = true;
        }
        int a2 = tVar.a(payment, cashierPayItemInfoEntity);
        if (a2 != 0) {
            TextView textView4 = this.q;
            if (textView4 != null && textView4.getVisibility() == 0) {
                ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
                layoutParams.height = -2;
                layoutParams.width = a2;
                this.q.setLayoutParams(layoutParams);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = this.p.getLayoutParams();
            layoutParams2.height = -2;
            layoutParams2.width = a2;
            this.p.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.CashierItemView
    public int b() {
        return R.layout.lib_cashier_sdk_a_item_jd_pay_channel_view;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.CashierItemView
    public void o() {
        super.o();
        this.f2986i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_CCCCCC));
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.CashierItemView
    public void v(String str, boolean z, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            this.p.setContentDescription("");
            this.p.setVisibility(8);
            return;
        }
        this.p.setVisibility(0);
        this.p.setText(str);
        this.p.setContentDescription(str);
        if (z) {
            this.p.setClickable(true);
            Context context = getContext();
            if (context != null) {
                this.p.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_a_pay_channel_coupon_arrow), (Drawable) null);
                this.p.setCompoundDrawablePadding(DpiUtil.dip2px(context, 4.0f));
                this.p.setOnClickListener(new a(this, onClickListener));
                return;
            }
            return;
        }
        this.p.setClickable(false);
        this.p.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public CashierAPayItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
