package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.i0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class a {
    private Dialog a;
    private View b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static final class DialogInterfaceOnDismissListenerC0135a implements DialogInterface.OnDismissListener {
        DialogInterfaceOnDismissListenerC0135a() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            if (a.this.b != null) {
                i0.i(a.this.b);
                a.this.b = null;
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = a.this.a;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    private final View d(FragmentActivity fragmentActivity, String str, String str2) {
        View contentView = LayoutInflater.from(fragmentActivity).inflate(R.layout.lib_cashier_sdk_baitiao_pay_agreement_dialog_content_layout, (ViewGroup) null, false);
        View findViewById = contentView.findViewById(R.id.lib_cashier_baitiao_agreement_content);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "contentView.findViewById\u2026aitiao_agreement_content)");
        ViewGroup viewGroup = (ViewGroup) findViewById;
        TextView mTitleView = (TextView) contentView.findViewById(R.id.lib_cashier_baitiao_agreement_title);
        if (TextUtils.isEmpty(str2)) {
            str2 = fragmentActivity.getString(R.string.lib_cashier_sdk_baitiao_pay_agreement_dialog_title);
            Intrinsics.checkExpressionValueIsNotNull(str2, "context.getString(R.stri\u2026y_agreement_dialog_title)");
        }
        Intrinsics.checkExpressionValueIsNotNull(mTitleView, "mTitleView");
        mTitleView.setText(str2);
        try {
            viewGroup.removeAllViews();
            if (!TextUtils.isEmpty(str)) {
                View f2 = i0.f(fragmentActivity, new LinearLayout.LayoutParams(-1, -1));
                this.b = f2;
                viewGroup.addView(f2);
                i0.h(this.b, str);
            }
            Dialog dialog = this.a;
            if (dialog != null) {
                dialog.setOnDismissListener(new DialogInterfaceOnDismissListenerC0135a());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return contentView;
    }

    public final void e(@NotNull FragmentActivity fragmentActivity, @NotNull String str, @NotNull String str2) {
        if (g0.a(fragmentActivity)) {
            if (this.a == null) {
                this.a = com.jd.lib.cashier.sdk.core.utils.j.b(fragmentActivity);
            }
            Dialog dialog = this.a;
            if (dialog == null || dialog == null || dialog.isShowing()) {
                return;
            }
            View d = d(fragmentActivity, str, str2);
            ((ImageView) d.findViewById(R.id.lib_cashier_credit_pay_protocol_agreement_close)).setOnClickListener(new b());
            com.jd.lib.cashier.sdk.core.utils.j.a(this.a, d, 0.8f);
            Dialog dialog2 = this.a;
            if (dialog2 != null) {
                dialog2.show();
            }
        }
    }
}
