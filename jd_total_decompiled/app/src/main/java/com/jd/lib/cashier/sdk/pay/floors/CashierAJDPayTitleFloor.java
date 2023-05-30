package com.jd.lib.cashier.sdk.pay.floors;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;

/* loaded from: classes14.dex */
public class CashierAJDPayTitleFloor extends AbstractFloor<com.jd.lib.cashier.sdk.h.d.a, com.jd.lib.cashier.sdk.h.g.b> {

    /* renamed from: i  reason: collision with root package name */
    private ImageView f3980i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f3981j;

    /* renamed from: k  reason: collision with root package name */
    private View f3982k;

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
            if (CashierAJDPayTitleFloor.this.f3981j != null) {
                CashierAJDPayTitleFloor.this.f3981j.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_a_title_top_corner_dark_bg : R.drawable.lib_cashier_sdk_a_title_top_corner_bg);
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
            if (CashierAJDPayTitleFloor.this.f3980i != null) {
                CashierAJDPayTitleFloor.this.f3980i.setBackgroundResource(R.drawable.lib_cashier_sdk_jd_pay_title_logo);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingStarted(String str) {
        }
    }

    public CashierAJDPayTitleFloor(View view) {
        super(view);
    }

    private void e() {
        if (this.f3982k != null) {
            this.f3982k.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_a_top_corner_dark_bg : R.drawable.lib_cashier_sdk_a_top_corner_bg);
        }
    }

    private void f(String str, String str2) {
        if (this.f3981j != null) {
            if (JDDarkUtil.isDarkMode() && TextUtils.isEmpty(str2)) {
                this.f3981j.setBackgroundResource(R.drawable.lib_cashier_sdk_a_title_top_corner_dark_bg);
            } else if (!JDDarkUtil.isDarkMode() && TextUtils.isEmpty(str)) {
                this.f3981j.setBackgroundResource(R.drawable.lib_cashier_sdk_a_title_top_corner_bg);
            } else {
                k.a(R.id.lib_cashier_pay_a_title_bg, JDDarkUtil.isDarkMode() ? str2 : str, this.f3981j, null, false, new a());
            }
        }
    }

    private void g(String str) {
        ImageView imageView = this.f3980i;
        if (imageView != null) {
            k.a(R.id.lib_cashier_sdk_payment_title_icon_tag, str, imageView, null, false, new b());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void b(com.jd.lib.cashier.sdk.h.d.a aVar, com.jd.lib.cashier.sdk.h.g.b bVar) {
        if (bVar != null) {
            g(bVar.a);
            f(bVar.b, bVar.f3558c);
            e();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        if (this.f3980i == null) {
            this.f3980i = (ImageView) view.findViewById(R.id.lib_cashier_pay_a_title_jd_pay_icon);
        }
        if (this.f3982k == null) {
            this.f3982k = view.findViewById(R.id.lib_cashier_sdk_a_jd_pay_title_floor_root);
        }
        if (this.f3981j == null) {
            this.f3981j = (ImageView) view.findViewById(R.id.lib_cashier_pay_a_title_bg);
        }
    }
}
