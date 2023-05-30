package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010(\u001a\u00020\u001e\u0012\u0006\u0010.\u001a\u00020-\u00a2\u0006\u0004\b/\u00100J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b\u0015\u0010\u0014R\u001e\u0010\u001a\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001d\u001a\n \u0017*\u0004\u0018\u00010\u001b0\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u001cR\u001e\u0010 \u001a\n \u0017*\u0004\u0018\u00010\u001e0\u001e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001fR\u001e\u0010!\u001a\n \u0017*\u0004\u0018\u00010\u001b0\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u001cR\u001e\u0010$\u001a\n \u0017*\u0004\u0018\u00010\"0\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010#R\u0019\u0010(\u001a\u00020\u001e8\u0006@\u0006\u00a2\u0006\f\n\u0004\b%\u0010\u001f\u001a\u0004\b&\u0010'R\u001e\u0010)\u001a\n \u0017*\u0004\u0018\u00010\u001b0\u001b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001cR\u001e\u0010,\u001a\n \u0017*\u0004\u0018\u00010*0*8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010+\u00a8\u00061"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/CreditCardBankViewHolderPro;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "text", "", "d", "(Ljava/lang/String;)V", "iconUrl", "g", "Landroid/view/View$OnClickListener;", "clickListener", "f", "(Landroid/view/View$OnClickListener;)V", "", "canUse", "c", "(Z)V", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;", "creditCardBankInfo", "b", "(Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;)V", com.jingdong.app.mall.e.a, "Landroid/widget/RelativeLayout;", "kotlin.jvm.PlatformType", com.jingdong.jdsdk.a.a.a, "Landroid/widget/RelativeLayout;", "mItemView", "Landroid/widget/TextView;", "Landroid/widget/TextView;", "mTvTitle", "Landroid/view/View;", "Landroid/view/View;", "mSplitLine", "mTvCoupon", "Landroid/widget/ImageView;", "Landroid/widget/ImageView;", "mImgIcon", JshopConst.JSHOP_PROMOTIO_H, "getInfoItemView", "()Landroid/view/View;", "infoItemView", "mTvSubtitle", "Landroid/widget/CheckBox;", "Landroid/widget/CheckBox;", "mCheckBox", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/view/View;Landroid/content/Context;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CreditCardBankViewHolderPro extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final RelativeLayout mItemView;

    /* renamed from: b  reason: from kotlin metadata */
    private final ImageView mImgIcon;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final TextView mTvTitle;

    /* renamed from: d  reason: from kotlin metadata */
    private final TextView mTvSubtitle;

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final TextView mTvCoupon;

    /* renamed from: f  reason: collision with root package name and from kotlin metadata */
    private final CheckBox mCheckBox;

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private final View mSplitLine;
    @NotNull

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private final View infoItemView;

    public CreditCardBankViewHolderPro(@NotNull View view, @NotNull Context context) {
        super(view);
        this.infoItemView = view;
        this.mItemView = (RelativeLayout) view.findViewById(R.id.container_item_dialog_credit_card_bank);
        this.mImgIcon = (ImageView) view.findViewById(R.id.drawee_item_dialog_credit_card_bank_icon);
        this.mTvTitle = (TextView) view.findViewById(R.id.tv_item_dialog_credit_card_bank_title);
        this.mTvSubtitle = (TextView) view.findViewById(R.id.tv_item_dialog_credit_card_bank_subtitle);
        this.mTvCoupon = (TextView) view.findViewById(R.id.tv_item_dialog_credit_card_bank_coupon);
        this.mCheckBox = (CheckBox) view.findViewById(R.id.check_item_dialog_credit_card_bank);
        this.mSplitLine = view.findViewById(R.id.lib_cashier_credit_card_item_split_view);
    }

    private final void d(String text) {
        if (TextUtils.isEmpty(text)) {
            TextView textView = this.mTvCoupon;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = this.mTvCoupon;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.mTvCoupon;
        if (textView3 != null) {
            textView3.setText(text);
        }
        TextView mTvCoupon = this.mTvCoupon;
        Intrinsics.checkExpressionValueIsNotNull(mTvCoupon, "mTvCoupon");
        mTvCoupon.setContentDescription(text);
        TextView textView4 = this.mTvCoupon;
        if (textView4 != null) {
            textView4.setClickable(false);
        }
    }

    private final void g(String iconUrl) {
        if (TextUtils.isEmpty(iconUrl)) {
            ImageView mImgIcon = this.mImgIcon;
            Intrinsics.checkExpressionValueIsNotNull(mImgIcon, "mImgIcon");
            mImgIcon.setVisibility(4);
            return;
        }
        ImageView mImgIcon2 = this.mImgIcon;
        Intrinsics.checkExpressionValueIsNotNull(mImgIcon2, "mImgIcon");
        mImgIcon2.setVisibility(0);
        k.b(this.mImgIcon, iconUrl, null, false);
    }

    public final void b(@Nullable CreditCard creditCardBankInfo) {
        if (creditCardBankInfo == null) {
            return;
        }
        String str = creditCardBankInfo.logo;
        Intrinsics.checkExpressionValueIsNotNull(str, "creditCardBankInfo.logo");
        g(str);
        if (creditCardBankInfo.cardCanUse) {
            CheckBox mCheckBox = this.mCheckBox;
            Intrinsics.checkExpressionValueIsNotNull(mCheckBox, "mCheckBox");
            mCheckBox.setEnabled(true);
            CheckBox mCheckBox2 = this.mCheckBox;
            Intrinsics.checkExpressionValueIsNotNull(mCheckBox2, "mCheckBox");
            mCheckBox2.setChecked(creditCardBankInfo.selected);
            CheckBox mCheckBox3 = this.mCheckBox;
            Intrinsics.checkExpressionValueIsNotNull(mCheckBox3, "mCheckBox");
            mCheckBox3.setContentDescription(creditCardBankInfo.selected ? "\u5df2\u9009\u5b9a" : "\u672a\u9009\u5b9a");
            this.mTvTitle.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            this.mTvSubtitle.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
            TextView mTvTitle = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle, "mTvTitle");
            mTvTitle.setContentDescription(creditCardBankInfo.bankNameShow);
            TextView mTvSubtitle = this.mTvSubtitle;
            Intrinsics.checkExpressionValueIsNotNull(mTvSubtitle, "mTvSubtitle");
            mTvSubtitle.setContentDescription(creditCardBankInfo.tip);
            if (Build.VERSION.SDK_INT >= 16) {
                RelativeLayout mItemView = this.mItemView;
                Intrinsics.checkExpressionValueIsNotNull(mItemView, "mItemView");
                mItemView.setImportantForAccessibility(1);
            }
        } else {
            CheckBox mCheckBox4 = this.mCheckBox;
            Intrinsics.checkExpressionValueIsNotNull(mCheckBox4, "mCheckBox");
            mCheckBox4.setEnabled(false);
            this.mTvTitle.setTextColor(Color.parseColor("#c2c2c2"));
            this.mTvSubtitle.setTextColor(Color.parseColor("#c2c2c2"));
            RelativeLayout mItemView2 = this.mItemView;
            Intrinsics.checkExpressionValueIsNotNull(mItemView2, "mItemView");
            mItemView2.setFocusable(false);
            RelativeLayout mItemView3 = this.mItemView;
            Intrinsics.checkExpressionValueIsNotNull(mItemView3, "mItemView");
            mItemView3.setFocusableInTouchMode(false);
            if (Build.VERSION.SDK_INT >= 16) {
                RelativeLayout mItemView4 = this.mItemView;
                Intrinsics.checkExpressionValueIsNotNull(mItemView4, "mItemView");
                mItemView4.setImportantForAccessibility(2);
            } else {
                RelativeLayout mItemView5 = this.mItemView;
                Intrinsics.checkExpressionValueIsNotNull(mItemView5, "mItemView");
                mItemView5.setContentDescription(null);
            }
        }
        CheckBox mCheckBox5 = this.mCheckBox;
        Intrinsics.checkExpressionValueIsNotNull(mCheckBox5, "mCheckBox");
        mCheckBox5.setClickable(false);
        TextView mTvTitle2 = this.mTvTitle;
        Intrinsics.checkExpressionValueIsNotNull(mTvTitle2, "mTvTitle");
        mTvTitle2.setText(creditCardBankInfo.bankNameShow);
        TextView mTvSubtitle2 = this.mTvSubtitle;
        Intrinsics.checkExpressionValueIsNotNull(mTvSubtitle2, "mTvSubtitle");
        mTvSubtitle2.setText(creditCardBankInfo.tip);
        d(creditCardBankInfo.preferentiaNum);
    }

    public final void c(boolean canUse) {
        if (canUse) {
            this.mTvTitle.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            this.mTvSubtitle.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        } else {
            this.mTvTitle.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
            this.mTvSubtitle.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
        }
        this.mTvCoupon.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        this.mSplitLine.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        this.mCheckBox.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_dark : R.drawable.lib_cashier_sdk_button_i_new);
    }

    public final void e(@Nullable CreditCard creditCardBankInfo) {
        if (creditCardBankInfo != null && creditCardBankInfo.cardCanUse) {
            View view = this.infoItemView;
            StringBuilder sb = new StringBuilder();
            CheckBox mCheckBox = this.mCheckBox;
            Intrinsics.checkExpressionValueIsNotNull(mCheckBox, "mCheckBox");
            sb.append(mCheckBox.getContentDescription());
            TextView mTvTitle = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle, "mTvTitle");
            sb.append(mTvTitle.getContentDescription());
            TextView mTvSubtitle = this.mTvSubtitle;
            Intrinsics.checkExpressionValueIsNotNull(mTvSubtitle, "mTvSubtitle");
            sb.append(mTvSubtitle.getContentDescription());
            TextView mTvCoupon = this.mTvCoupon;
            Intrinsics.checkExpressionValueIsNotNull(mTvCoupon, "mTvCoupon");
            sb.append(mTvCoupon.getContentDescription());
            view.setContentDescription(sb.toString());
        }
    }

    public final void f(@NotNull View.OnClickListener clickListener) {
        this.mItemView.setOnClickListener(clickListener);
    }
}
