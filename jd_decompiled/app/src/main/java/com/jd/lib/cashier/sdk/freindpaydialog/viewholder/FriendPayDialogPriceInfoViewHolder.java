package com.jd.lib.cashier.sdk.freindpaydialog.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.g.g.a;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class FriendPayDialogPriceInfoViewHolder extends RecyclerView.ViewHolder {
    public TextView a;
    public TextView b;

    /* renamed from: c  reason: collision with root package name */
    private FriendPayDialogActivity f3441c;

    public FriendPayDialogPriceInfoViewHolder(@NotNull View view) {
        super(view);
        initView(view);
    }

    private void d() {
    }

    private void f(String str, String str2) {
        FriendPayDialogActivity friendPayDialogActivity;
        TextView textView = this.a;
        if (textView == null || (friendPayDialogActivity = this.f3441c) == null) {
            return;
        }
        textView.setText(h0.a(friendPayDialogActivity, str2, str));
        m0.a(this.a, (byte) 3);
        this.a.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
    }

    private void g(String str) {
        TextView textView = this.b;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }

    public void b(FriendPayDialogActivity friendPayDialogActivity, a aVar) {
        this.f3441c = friendPayDialogActivity;
        e(aVar);
    }

    public void e(a aVar) {
        if (aVar != null) {
            g(aVar.a);
            f(aVar.f3473c, aVar.b);
            d();
        }
    }

    public void initView(View view) {
        this.a = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_dialog_price_label);
        this.b = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_dialog_title_label);
    }
}
