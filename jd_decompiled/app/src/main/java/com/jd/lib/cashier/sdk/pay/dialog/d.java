package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.pay.adapter.GWJAdapter;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinModel;
import com.jd.lib.cashier.sdk.pay.bean.GouWuJinWalletInfo;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class d {
    public static final d a = new d();

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3895g;

        a(Dialog dialog) {
            this.f3895g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = this.f3895g;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    private d() {
    }

    private final View a(Context context, GouWuJinModel gouWuJinModel) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_payment_gwj_dialog, (ViewGroup) null, false);
        TextView textView = (TextView) contentView.findViewById(R.id.tv_gwj_dialog_title);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_gwj_dialog_subtitle);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_gwj_dialog_list);
        TextView textView3 = (TextView) contentView.findViewById(R.id.tv_gwj_dialog_desc);
        TextView textView4 = (TextView) contentView.findViewById(R.id.tv_gwj_dialog_subtitle_money);
        if (textView != null) {
            textView.setText(gouWuJinModel != null ? gouWuJinModel.title : null);
        }
        if (textView2 != null) {
            textView2.setText(gouWuJinModel != null ? gouWuJinModel.subTitle : null);
        }
        if (textView4 != null) {
            textView4.setText(gouWuJinModel != null ? gouWuJinModel.canUseBalance : null);
        }
        List<GouWuJinWalletInfo> list = gouWuJinModel != null ? gouWuJinModel.walletInfos : null;
        if (!(list == null || list.isEmpty())) {
            if (recyclerView != null) {
                recyclerView.setVisibility(0);
            }
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
                recyclerView.setAdapter(new GWJAdapter(gouWuJinModel != null ? gouWuJinModel.walletInfos : null));
            }
        } else if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
        if (textView3 != null) {
            textView3.setText(gouWuJinModel != null ? gouWuJinModel.tip : null);
        }
        m0.a(textView4, (byte) 3);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return contentView;
    }

    private final void b(View view) {
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.tv_gwj_dialog_title);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_gwj_dialog_subtitle);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_gwj_dialog_desc);
            TextView textView4 = (TextView) view.findViewById(R.id.tv_gwj_dialog_subtitle_money);
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            }
            if (textView2 != null) {
                textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            }
            if (textView3 != null) {
                textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
            if (textView4 != null) {
                textView4.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19));
            }
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public final void c(@NotNull Context context, @NotNull GouWuJinModel gouWuJinModel) {
        Dialog b = com.jd.lib.cashier.sdk.core.utils.j.b(context);
        View a2 = a(context, gouWuJinModel);
        ((ImageView) a2.findViewById(R.id.tv_gwj_dialog_close)).setOnClickListener(new a(b));
        b(a2);
        com.jd.lib.cashier.sdk.core.utils.j.a(b, a2, 0.6f);
        if (b != null) {
            b.show();
        }
    }
}
