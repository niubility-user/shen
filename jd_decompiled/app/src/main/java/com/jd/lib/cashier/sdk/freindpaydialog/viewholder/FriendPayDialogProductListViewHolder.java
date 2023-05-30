package com.jd.lib.cashier.sdk.freindpaydialog.viewholder;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.freindpaydialog.adapter.FriendPayDialogFloorAdapter;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.g.g.b;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class FriendPayDialogProductListViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: l  reason: collision with root package name */
    private static final String f3442l = "FriendPayDialogProductListViewHolder";
    public ImageView a;
    public TextView b;

    /* renamed from: c  reason: collision with root package name */
    public TextView f3443c;
    public TextView d;

    /* renamed from: e  reason: collision with root package name */
    public TextView f3444e;

    /* renamed from: f  reason: collision with root package name */
    public ViewGroup f3445f;

    /* renamed from: g  reason: collision with root package name */
    public ViewGroup f3446g;

    /* renamed from: h  reason: collision with root package name */
    public View f3447h;

    /* renamed from: i  reason: collision with root package name */
    public ImageView f3448i;

    /* renamed from: j  reason: collision with root package name */
    private FriendPayDialogActivity f3449j;

    /* renamed from: k  reason: collision with root package name */
    private FriendPayDialogFloorAdapter f3450k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FriendPayDialogProductListViewHolder.this.f3450k.l();
        }
    }

    public FriendPayDialogProductListViewHolder(@NotNull View view) {
        super(view);
        this.a = (ImageView) view.findViewById(R.id.lib_cashier_friend_pay_order_info_product_pic);
        this.b = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_order_info_product_info);
        this.f3443c = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_order_info_product_price);
        this.d = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_order_info_product_num);
        this.f3446g = (ViewGroup) view.findViewById(R.id.lib_cashier_friend_pay_dialog_all_count);
        this.f3448i = (ImageView) view.findViewById(R.id.lib_cashier_credit_pay_all_count_arrow_img);
        this.f3444e = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_order_info_product_all_num);
        this.f3447h = view.findViewById(R.id.lib_cashier_friend_pay_dialog_split_line_bottom);
        this.f3445f = (ViewGroup) view.findViewById(R.id.lib_cashier_friend_pay_dialog_product_parent_root);
    }

    private void d(String str) {
        if (this.a == null) {
            return;
        }
        ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
        imageLoaderOptions.placeHolderType = 17;
        imageLoaderOptions.showDefault = true;
        k.b(this.a, str, imageLoaderOptions, false);
    }

    private void e() {
        j0.b(this.f3446g);
        j0.b(this.f3447h);
    }

    private void f() {
        j0.b(this.f3447h);
    }

    private void h(String str) {
        try {
            if (JDDarkUtil.isDarkMode()) {
                this.b.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1D1E1E));
                this.f3443c.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
                this.d.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
                this.f3447h.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_302E2E));
                this.f3446g.setBackgroundResource(R.drawable.lib_cashier_sdk_friend_pay_diaolog_bottom_corner_dark_bg);
            } else {
                this.b.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1D1E1E));
                this.f3443c.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
                this.d.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
                this.f3447h.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_14000000));
                this.f3446g.setBackgroundResource(R.drawable.lib_cashier_sdk_bottom_corner_bg);
            }
            if (TextUtils.equals(str, "1")) {
                this.f3445f.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_product_list_top_corner_dark_bg : R.drawable.lib_cashier_sdk_top_corner_bg);
            } else if (TextUtils.equals(str, "2")) {
                this.f3445f.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_product_list_bottom_corner_dark_bg : R.drawable.lib_cashier_sdk_bottom_corner_bg);
            } else if (TextUtils.equals(str, "3")) {
                this.f3445f.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_product_top_bottom_corner_dark_bg : R.drawable.lib_cashier_sdk_top_bottom_corner_bg);
            } else {
                this.f3445f.setBackgroundColor(JDDarkUtil.isDarkMode() ? Color.parseColor(JDDarkUtil.COLOR_0A0909) : -1);
            }
        } catch (Exception e2) {
            r.d(f3442l, e2.getMessage());
        }
    }

    private void j(boolean z) {
        if (z) {
            this.f3448i.setBackgroundResource(R.drawable.lib_cashier_sdk_icon_friend_pay_product_list_up);
        } else {
            this.f3448i.setBackgroundResource(R.drawable.lib_cashier_sdk_icon_friend_pay_product_list_down);
        }
        this.f3448i.getLayoutParams();
    }

    private void k(String str) {
        TextView textView = this.f3444e;
        if (textView == null) {
            return;
        }
        textView.setText("\u5171" + str + "\u4ef6\u5546\u54c1");
    }

    private void l(String str) {
        TextView textView = this.b;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }

    private void m(String str) {
        if (this.d == null) {
            return;
        }
        if (this.f3449j != null) {
            str = this.f3449j.getString(R.string.lib_cashier_sdk_friend_pay_order_info_num) + str;
        }
        this.d.setText(str);
        m0.a(this.d, (byte) 3);
    }

    private void n(String str) {
        FriendPayDialogActivity friendPayDialogActivity;
        TextView textView = this.f3443c;
        if (textView == null || (friendPayDialogActivity = this.f3449j) == null) {
            return;
        }
        textView.setText(h0.a(friendPayDialogActivity, str, "\u00a5"));
        m0.a(this.f3443c, (byte) 3);
    }

    private void o() {
        j0.d(this.f3446g);
        j0.d(this.f3447h);
    }

    private void p() {
        j0.d(this.f3447h);
    }

    public void c(FriendPayDialogActivity friendPayDialogActivity, FriendPayDialogFloorAdapter friendPayDialogFloorAdapter, b bVar) {
        this.f3449j = friendPayDialogActivity;
        this.f3450k = friendPayDialogFloorAdapter;
        i(bVar);
        g();
    }

    public void g() {
        this.f3446g.setOnClickListener(new a());
    }

    public void i(b bVar) {
        String str = "3";
        if (bVar != null) {
            m(bVar.f3474c);
            l(bVar.a);
            d(bVar.d);
            n(bVar.b);
            if (bVar.f3477g) {
                if (bVar.f3475e > 1) {
                    if (bVar.f3479i) {
                        e();
                        this.f3445f.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_bg);
                        str = "1";
                    } else {
                        this.f3445f.setBackgroundResource(R.drawable.lib_cashier_sdk_top_bottom_corner_bg);
                        o();
                        j(bVar.f3479i);
                        k(bVar.f3475e + "");
                    }
                    p();
                } else {
                    f();
                    e();
                    this.f3445f.setBackgroundResource(R.drawable.lib_cashier_sdk_top_bottom_corner_bg);
                }
            } else if (bVar.f3478h) {
                if (bVar.f3479i) {
                    p();
                    j(bVar.f3479i);
                    o();
                    k(bVar.f3475e + "");
                } else {
                    e();
                    f();
                }
                this.f3445f.setBackgroundResource(R.drawable.lib_cashier_sdk_bottom_corner_bg);
                str = "2";
            } else {
                e();
                p();
                this.f3445f.setBackgroundColor(-1);
                str = "0";
            }
        }
        h(str);
    }
}
