package com.jd.lib.cashier.sdk.pay.floors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.h.g.b0;
import com.jd.lib.cashier.sdk.pay.bean.PublicGoodPlan;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class CashierPayTitleFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, b0> {

    /* renamed from: i  reason: collision with root package name */
    private TextView f4132i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f4133j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f4134k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements ImageLoadingListener {
        a() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingCancelled(String str) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingComplete(String str, Bitmap bitmap) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingFailed(String str) {
            if (CashierPayTitleFloor.this.f4133j != null) {
                CashierPayTitleFloor.this.f4133j.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_regulator_title_top_corner_dark_bg : R.drawable.lib_cashier_sdk_regulator_title_top_corner_bg);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingStarted(String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements ImageLoadingListener {
        b() {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingCancelled(String str) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingComplete(String str, Bitmap bitmap) {
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingFailed(String str) {
            if (CashierPayTitleFloor.this.f4134k != null) {
                CashierPayTitleFloor.this.f4134k.setBackgroundResource(R.drawable.lib_cashier_sdk_jd_pay_title_logo);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingStarted(String str) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f4135j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ PublicGoodPlan f4136k;

        c(CashierPayTitleFloor cashierPayTitleFloor, Context context, PublicGoodPlan publicGoodPlan) {
            this.f4135j = context;
            this.f4136k = publicGoodPlan;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            new com.jd.lib.cashier.sdk.pay.dialog.i().b(this.f4135j, this.f4136k);
            if (this.f4135j instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().P((CashierPayActivity) this.f4135j);
            }
        }
    }

    public CashierPayTitleFloor(View view) {
        super(view);
    }

    private void e() {
        TextView textView = this.f4132i;
        if (textView == null || textView.getVisibility() != 0) {
            return;
        }
        this.f4132i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_848383));
    }

    private void f(String str, String str2, String str3) {
        if (this.f4133j != null) {
            if (!TextUtils.equals("1", str3)) {
                this.f4133j.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_regulator_title_top_corner_dark_bg : R.drawable.lib_cashier_sdk_regulator_title_top_corner_bg);
            } else {
                k.a(R.id.lib_cashier_pay_title_bg, JDDarkUtil.isDarkMode() ? str2 : str, this.f4133j, null, false, new a());
            }
        }
    }

    private void g(String str) {
        ImageView imageView = this.f4134k;
        if (imageView != null) {
            k.a(R.id.lib_cashier_sdk_payment_title_icon_tag, str, imageView, null, false, new b());
        }
    }

    private void i(PublicGoodPlan publicGoodPlan) {
        TextView textView;
        Context context = getConvertView().getContext();
        if (context == null || (textView = this.f4132i) == null || textView.getVisibility() == 8) {
            return;
        }
        if (publicGoodPlan != null && !TextUtils.isEmpty(publicGoodPlan.mainTitle) && publicGoodPlan.items.size() > 0 && !TextUtils.isEmpty(publicGoodPlan.confirmBtn)) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light);
            this.f4132i.setCompoundDrawablePadding(DpiUtil.dip2px(context, 4.0f));
            this.f4132i.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.h.e.a.d().Q((CashierPayActivity) context);
            }
            this.f4132i.setOnClickListener(new c(this, context, publicGoodPlan));
            return;
        }
        this.f4132i.setOnClickListener(null);
        this.f4132i.setCompoundDrawables(null, null, null, null);
    }

    private void j(String str) {
        if (this.f4132i == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.f4132i.setVisibility(8);
            return;
        }
        this.f4132i.setVisibility(0);
        this.f4132i.setText(str);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, b0 b0Var) {
        if (b0Var != null) {
            g(b0Var.a);
            f(b0Var.b, b0Var.f3559c, b0Var.d);
            j(b0Var.f3560e);
            i(b0Var.f3561f);
            e();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f4134k == null) {
            this.f4134k = (ImageView) view.findViewById(R.id.lib_cashier_pay_title_jd_pay_icon);
        }
        if (this.f4132i == null) {
            this.f4132i = (TextView) view.findViewById(R.id.lib_cashier_pay_title_desc);
        }
        if (this.f4133j == null) {
            this.f4133j = (ImageView) view.findViewById(R.id.lib_cashier_pay_title_bg);
        }
    }
}
