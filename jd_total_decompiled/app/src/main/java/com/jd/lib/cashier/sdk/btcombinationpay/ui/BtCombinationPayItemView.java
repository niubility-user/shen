package com.jd.lib.cashier.sdk.btcombinationpay.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.SplitSkuInfo;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.m0;

/* loaded from: classes14.dex */
public class BtCombinationPayItemView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    private ImageView f2896g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f2897h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f2898i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2899j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f2900k;

    public BtCombinationPayItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    private void a() {
        h();
        TextView textView = this.f2897h;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        }
        TextView textView2 = this.f2898i;
        if (textView2 != null) {
            textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
        }
        TextView textView3 = this.f2899j;
        if (textView3 != null) {
            textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        }
        TextView textView4 = this.f2900k;
        if (textView4 != null) {
            textView4.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
        }
    }

    private void b(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_item_bt_combination_pay_info_view, (ViewGroup) this, true);
        this.f2896g = (ImageView) inflate.findViewById(R.id.bt_combination_pay_item_goods_img);
        this.f2897h = (TextView) inflate.findViewById(R.id.bt_combination_pay_item_goods_desc);
        this.f2898i = (TextView) inflate.findViewById(R.id.bt_combination_pay_item_goods_count);
        this.f2899j = (TextView) inflate.findViewById(R.id.bt_combination_pay_item_goods_price);
        this.f2900k = (TextView) inflate.findViewById(R.id.bt_combination_pay_item_goods_service_fee);
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2898i.setVisibility(0);
            this.f2898i.setText(str);
            return;
        }
        this.f2898i.setVisibility(4);
    }

    private void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2897h.setVisibility(0);
            this.f2897h.setText(str);
            return;
        }
        this.f2897h.setVisibility(4);
    }

    private void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2900k.setVisibility(0);
            this.f2900k.setText(str);
            m0.a(this.f2900k, (byte) 3);
            return;
        }
        this.f2900k.setVisibility(8);
    }

    private void f(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2896g.setVisibility(0);
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 1;
            k.a(R.id.lib_cashier_sdk_bt_combination_icon_tag, str, this.f2896g, imageLoaderOptions, true, null);
            return;
        }
        this.f2896g.setVisibility(4);
    }

    private void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2899j.setVisibility(0);
            this.f2899j.setText(str);
            m0.a(this.f2899j, (byte) 3);
            return;
        }
        this.f2899j.setVisibility(4);
    }

    private void h() {
        if (this.f2896g != null) {
            if (JDDarkUtil.isDarkMode()) {
                this.f2896g.setColorFilter(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_33000000, false));
            } else {
                this.f2896g.setColorFilter(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_05000000, false));
            }
        }
    }

    public void i(SplitSkuInfo splitSkuInfo) {
        if (splitSkuInfo != null) {
            f(splitSkuInfo.imgUrl);
            d(splitSkuInfo.skuName);
            c(splitSkuInfo.num);
            g(splitSkuInfo.skuPrice);
            e(splitSkuInfo.feeInfo);
            a();
        }
    }

    public BtCombinationPayItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(context);
    }
}
