package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.h.h.i;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u00100\u001a\u00020\u0015\u00a2\u0006\u0004\b1\u00102J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\u0006J\u0017\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ+\u0010\u0012\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0017\u001a\u00020\u00042\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00040\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001d\u0010\u0013R\u001e\u0010!\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010 R\u001e\u0010%\u001a\n \u001f*\u0004\u0018\u00010\"0\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u001e\u0010(\u001a\n \u001f*\u0004\u0018\u00010&0&8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010'R\u001e\u0010)\u001a\n \u001f*\u0004\u0018\u00010&0&8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010'R\u001e\u0010*\u001a\n \u001f*\u0004\u0018\u00010&0&8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010'R\u001e\u0010-\u001a\n \u001f*\u0004\u0018\u00010+0+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010,R\u001e\u0010/\u001a\n \u001f*\u0004\u0018\u00010\u00150\u00158\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010.\u00a8\u00063"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/DigitalBankViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;", "digitalMoneyBankCard", "", "g", "(Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;)V", JshopConst.JSHOP_PROMOTIO_H, "j", com.jingdong.app.mall.e.a, "", "iconUrl", "i", "(Ljava/lang/String;)V", "Landroidx/fragment/app/FragmentActivity;", "activity", "Landroid/view/View$OnClickListener;", "onClickCouponListener", "d", "(Landroidx/fragment/app/FragmentActivity;Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;Landroid/view/View$OnClickListener;)V", "Lkotlin/Function1;", "Landroid/view/View;", "onClickListener", "f", "(Lkotlin/jvm/functions/Function1;)V", "", "canUse", "c", "(Z)V", "b", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "Landroid/widget/ImageView;", "mImgIcon", "Landroid/widget/RelativeLayout;", com.jingdong.jdsdk.a.a.a, "Landroid/widget/RelativeLayout;", "mItemView", "Landroid/widget/TextView;", "Landroid/widget/TextView;", "mTvTitle", "mTvSubtitle", "mTvCoupon", "Landroid/widget/CheckBox;", "Landroid/widget/CheckBox;", "mCheckBox", "Landroid/view/View;", "mSplitLine", "infoItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class DigitalBankViewHolder extends RecyclerView.ViewHolder {

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/i;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/i;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function1<i, Unit> {
        final /* synthetic */ FragmentActivity $activity;
        final /* synthetic */ DigitalMoneyBankCard $digitalMoneyBankCard;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(DigitalMoneyBankCard digitalMoneyBankCard, FragmentActivity fragmentActivity) {
            super(1);
            this.$digitalMoneyBankCard = digitalMoneyBankCard;
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(i iVar) {
            invoke2(iVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull i iVar) {
            String str = this.$digitalMoneyBankCard.uniqueChannelId;
            if (str == null) {
                str = "";
            }
            com.jd.lib.cashier.sdk.h.e.a.d().B(this.$activity, iVar.b(), str, iVar.d(), iVar.c(), iVar.e(), iVar.f());
        }
    }

    /* loaded from: classes14.dex */
    static final class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Function1 f3832g;

        b(Function1 function1) {
            this.f3832g = function1;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View it) {
            Function1 function1 = this.f3832g;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            function1.invoke(it);
        }
    }

    public DigitalBankViewHolder(@NotNull View view) {
        super(view);
        this.mItemView = (RelativeLayout) view.findViewById(R.id.container_item_dialog_digital_money_bank);
        this.mImgIcon = (ImageView) view.findViewById(R.id.drawee_item_dialog_digital_money_bank_icon);
        this.mTvTitle = (TextView) view.findViewById(R.id.tv_item_dialog_digital_money_bank_title);
        this.mTvSubtitle = (TextView) view.findViewById(R.id.tv_item_dialog_digital_money_bank_subtitle);
        this.mTvCoupon = (TextView) view.findViewById(R.id.tv_item_dialog_digital_money_bank_coupon);
        this.mCheckBox = (CheckBox) view.findViewById(R.id.check_item_dialog_digital_money_bank);
        this.mSplitLine = view.findViewById(R.id.lib_cashier_digital_money_item_split_view);
    }

    private final void d(FragmentActivity activity, DigitalMoneyBankCard digitalMoneyBankCard, View.OnClickListener onClickCouponListener) {
        Resources resources;
        com.jd.lib.cashier.sdk.pay.dialog.e eVar;
        String str = digitalMoneyBankCard != null ? digitalMoneyBankCard.preferentiaNum : null;
        String titleName = (digitalMoneyBankCard == null || (eVar = digitalMoneyBankCard.selectedCoupon) == null) ? null : eVar.getTitleName();
        if (!TextUtils.isEmpty(titleName)) {
            str = titleName;
        }
        if (TextUtils.isEmpty(str)) {
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
            textView3.setText(str);
        }
        TextView textView4 = this.mTvCoupon;
        if (textView4 != null) {
            textView4.setContentDescription(str);
        }
        if ((digitalMoneyBankCard != null ? digitalMoneyBankCard.defaultCouponEntity : null) != null) {
            TextView textView5 = this.mTvCoupon;
            if (textView5 != null) {
                textView5.setClickable(true);
            }
            Drawable drawable = (activity == null || (resources = activity.getResources()) == null) ? null : resources.getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow);
            TextView textView6 = this.mTvCoupon;
            if (textView6 != null) {
                textView6.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            }
            TextView mTvCoupon = this.mTvCoupon;
            Intrinsics.checkExpressionValueIsNotNull(mTvCoupon, "mTvCoupon");
            mTvCoupon.setCompoundDrawablePadding(DpiUtil.dip2px(activity, 2.0f));
            TextView textView7 = this.mTvCoupon;
            if (textView7 != null) {
                textView7.setOnClickListener(onClickCouponListener);
                return;
            }
            return;
        }
        TextView textView8 = this.mTvCoupon;
        if (textView8 != null) {
            textView8.setClickable(false);
        }
        TextView textView9 = this.mTvCoupon;
        if (textView9 != null) {
            textView9.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private final void e(DigitalMoneyBankCard digitalMoneyBankCard) {
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setEnabled(Intrinsics.areEqual(digitalMoneyBankCard.status, "1"));
        }
        CheckBox checkBox2 = this.mCheckBox;
        if (checkBox2 != null) {
            checkBox2.setChecked(digitalMoneyBankCard.selected);
        }
        CheckBox checkBox3 = this.mCheckBox;
        if (checkBox3 != null) {
            checkBox3.setClickable(false);
        }
    }

    private final void g(DigitalMoneyBankCard digitalMoneyBankCard) {
        RelativeLayout relativeLayout;
        if (Intrinsics.areEqual(digitalMoneyBankCard.status, "1")) {
            CheckBox checkBox = this.mCheckBox;
            if (checkBox != null) {
                checkBox.setContentDescription(digitalMoneyBankCard.selected ? "\u5df2\u9009\u5b9a" : "\u672a\u9009\u5b9a");
            }
            TextView textView = this.mTvTitle;
            if (textView != null) {
                textView.setContentDescription(digitalMoneyBankCard.channelName);
            }
            TextView textView2 = this.mTvSubtitle;
            if (textView2 != null) {
                textView2.setContentDescription(digitalMoneyBankCard.tip);
            }
            if (Build.VERSION.SDK_INT < 16 || (relativeLayout = this.mItemView) == null) {
                return;
            }
            relativeLayout.setImportantForAccessibility(1);
            return;
        }
        RelativeLayout relativeLayout2 = this.mItemView;
        if (relativeLayout2 != null) {
            relativeLayout2.setFocusable(false);
        }
        RelativeLayout relativeLayout3 = this.mItemView;
        if (relativeLayout3 != null) {
            relativeLayout3.setFocusableInTouchMode(false);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            RelativeLayout relativeLayout4 = this.mItemView;
            if (relativeLayout4 != null) {
                relativeLayout4.setImportantForAccessibility(2);
                return;
            }
            return;
        }
        RelativeLayout relativeLayout5 = this.mItemView;
        if (relativeLayout5 != null) {
            relativeLayout5.setContentDescription(null);
        }
    }

    private final void h(DigitalMoneyBankCard digitalMoneyBankCard) {
        if (!TextUtils.isEmpty(digitalMoneyBankCard.channelName)) {
            TextView textView = this.mTvTitle;
            if (textView != null) {
                textView.setText(digitalMoneyBankCard.channelName);
            }
            TextView mTvTitle = this.mTvTitle;
            Intrinsics.checkExpressionValueIsNotNull(mTvTitle, "mTvTitle");
            mTvTitle.setVisibility(0);
            return;
        }
        TextView mTvTitle2 = this.mTvTitle;
        Intrinsics.checkExpressionValueIsNotNull(mTvTitle2, "mTvTitle");
        mTvTitle2.setVisibility(4);
    }

    private final void i(String iconUrl) {
        if (TextUtils.isEmpty(iconUrl)) {
            ImageView imageView = this.mImgIcon;
            if (imageView != null) {
                imageView.setVisibility(4);
                return;
            }
            return;
        }
        ImageView imageView2 = this.mImgIcon;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        k.b(this.mImgIcon, iconUrl, null, false);
    }

    private final void j(DigitalMoneyBankCard digitalMoneyBankCard) {
        if (!TextUtils.isEmpty(digitalMoneyBankCard.tip)) {
            TextView textView = this.mTvSubtitle;
            if (textView != null) {
                textView.setText(digitalMoneyBankCard.tip);
            }
            TextView textView2 = this.mTvSubtitle;
            if (textView2 != null) {
                textView2.setVisibility(0);
                return;
            }
            return;
        }
        TextView textView3 = this.mTvSubtitle;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
    }

    public final void b(@NotNull FragmentActivity activity, @NotNull DigitalMoneyBankCard digitalMoneyBankCard, @NotNull View.OnClickListener onClickCouponListener) {
        String str = digitalMoneyBankCard.logo;
        Intrinsics.checkExpressionValueIsNotNull(str, "digitalMoneyBankCard.logo");
        i(str);
        h(digitalMoneyBankCard);
        j(digitalMoneyBankCard);
        e(digitalMoneyBankCard);
        d(activity, digitalMoneyBankCard, onClickCouponListener);
        g(digitalMoneyBankCard);
        com.jd.lib.cashier.sdk.h.h.c.c(activity, new a(digitalMoneyBankCard, activity));
    }

    public final void c(boolean canUse) {
        if (canUse) {
            TextView textView = this.mTvTitle;
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            TextView textView2 = this.mTvSubtitle;
            if (textView2 != null) {
                textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
        } else {
            TextView textView3 = this.mTvTitle;
            if (textView3 != null) {
                textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
            }
            TextView textView4 = this.mTvSubtitle;
            if (textView4 != null) {
                textView4.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
            }
        }
        TextView textView5 = this.mTvCoupon;
        if (textView5 != null) {
            textView5.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        }
        View view = this.mSplitLine;
        if (view != null) {
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        }
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_dark : R.drawable.lib_cashier_sdk_button_i_new);
        }
    }

    public final void f(@NotNull Function1<? super View, Unit> onClickListener) {
        RelativeLayout relativeLayout = this.mItemView;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(new b(onClickListener));
        }
    }
}
