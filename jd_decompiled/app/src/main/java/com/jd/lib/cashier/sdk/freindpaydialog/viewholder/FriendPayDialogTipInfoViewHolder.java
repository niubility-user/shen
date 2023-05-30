package com.jd.lib.cashier.sdk.freindpaydialog.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.g.g.d;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class FriendPayDialogTipInfoViewHolder extends RecyclerView.ViewHolder {
    public TextView a;
    private FriendPayDialogActivity b;

    public FriendPayDialogTipInfoViewHolder(@NotNull View view) {
        super(view);
        this.a = (TextView) view.findViewById(R.id.lib_cashier_friend_pay_tip_info);
    }

    private void d() {
        try {
            this.a.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        } catch (Exception e2) {
            r.d("FriendPayDialogTipInfoViewHolder", e2.getMessage());
        }
    }

    private void f(String str) {
        TextView textView = this.a;
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.b.getString(R.string.lib_cashier_sdk_friend_pay_dialog_tip_info);
        }
        textView.setText(str);
    }

    public void b(FriendPayDialogActivity friendPayDialogActivity, d dVar) {
        this.b = friendPayDialogActivity;
        e(dVar);
    }

    public void e(d dVar) {
        if (dVar != null) {
            f(dVar.a);
            d();
        }
    }
}
