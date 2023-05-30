package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.adapter.JXJProductAdapter;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.ProductInfo;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class f {
    public static final f a = new f();

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3896g;

        a(Dialog dialog) {
            this.f3896g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3896g.dismiss();
        }
    }

    /* loaded from: classes14.dex */
    static final class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public static final b f3897g = new b();

        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }

    private f() {
    }

    private final View a(Context context, Payment payment) {
        boolean contains$default;
        View contentView = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_dialog_jxj_payment, (ViewGroup) null, false);
        TextView tvSize = (TextView) contentView.findViewById(R.id.tv_jxj_dialog_size);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_jxj_dialog_list);
        TextView tvSubtitleName = (TextView) contentView.findViewById(R.id.tv_jxj_dialog_subtitle_name);
        TextView tvAmount = (TextView) contentView.findViewById(R.id.tv_jxj_dialog_subtitle_amount);
        TextView tvMemo = (TextView) contentView.findViewById(R.id.tv_jxj_dialog_memo);
        r.b("JXJDialogUtils", " CLICK_PAYMENT_ITEM_JXJ_ICON show payment = " + payment);
        Intrinsics.checkExpressionValueIsNotNull(tvSize, "tvSize");
        tvSize.setText(payment.skuSizeInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvSubtitleName, "tvSubtitleName");
        tvSubtitleName.setText(payment.jxjMoneyText);
        Intrinsics.checkExpressionValueIsNotNull(tvMemo, "tvMemo");
        tvMemo.setText(payment.jxjDesc);
        Intrinsics.checkExpressionValueIsNotNull(tvAmount, "tvAmount");
        tvAmount.setText(payment.moneyFlag + payment.canUseBalance);
        String imgAddress = payment.imgAddress;
        List<ProductInfo> list = payment.productInfos;
        Intrinsics.checkExpressionValueIsNotNull(list, "payment.productInfos");
        for (ProductInfo productInfo : list) {
            if (!TextUtils.isEmpty(productInfo.productImgUrl)) {
                String str = productInfo.productImgUrl;
                Intrinsics.checkExpressionValueIsNotNull(str, "it.productImgUrl");
                Intrinsics.checkExpressionValueIsNotNull(imgAddress, "imgAddress");
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) imgAddress, false, 2, (Object) null);
                if (!contains$default) {
                    productInfo.productImgUrl = imgAddress + productInfo.productImgUrl;
                }
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        recyclerView.setAdapter(new JXJProductAdapter(payment.productInfos));
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return contentView;
    }

    private final void b(View view) {
        if (view != null) {
            View findViewById = view.findViewById(R.id.line_jxj_dialog);
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_size)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_subtitle_name)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_subtitle_amount)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_memo)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
            ((TextView) view.findViewById(R.id.tv_jxj_dialog_confirm)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public final void c(@NotNull Context context, @NotNull Payment payment) {
        View a2 = a(context, payment);
        b(a2);
        Dialog j2 = com.jd.lib.cashier.sdk.core.utils.j.j(context, a2, b.f3897g);
        ((TextView) a2.findViewById(R.id.tv_jxj_dialog_confirm)).setOnClickListener(new a(j2));
        r.b("JXJDialogUtils", " CLICK_PAYMENT_ITEM_JXJ_ICON show dialog");
        j2.show();
    }
}
