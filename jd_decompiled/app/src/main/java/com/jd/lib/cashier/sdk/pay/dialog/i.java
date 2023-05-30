package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.layoutmanager.WrapContentLinearLayoutManager;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.pay.adapter.PublicGoodPlanContentAdapter;
import com.jd.lib.cashier.sdk.pay.bean.PublicGoodPlan;

/* loaded from: classes14.dex */
public class i {
    private PublicGoodPlanContentAdapter a;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3928g;

        a(i iVar, Dialog dialog) {
            this.f3928g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f3928g.dismiss();
        }
    }

    private View a(Context context, View.OnClickListener onClickListener, PublicGoodPlan publicGoodPlan) {
        int height = DpiUtil.getHeight(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        if (height <= 0) {
            layoutParams.height = DpiUtil.dip2px(context, 338.0f);
        } else {
            layoutParams.height = height / 2;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_dialog_public_good_plan, (ViewGroup) null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.lib_cashier_dialog_public_good_plan_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.lib_cashier_dialog_public_good_plan_confirm);
        View findViewById = inflate.findViewById(R.id.lib_cashier_dialog_public_good_plan_root_layout);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_public_good_plan_content);
        findViewById.setLayoutParams(layoutParams);
        textView.setText(publicGoodPlan.mainTitle);
        ((ImageView) inflate.findViewById(R.id.lib_cashier_dialog_public_good_plan_close)).setOnClickListener(onClickListener);
        textView2.setText(publicGoodPlan.confirmBtn);
        textView2.setOnClickListener(onClickListener);
        PublicGoodPlanContentAdapter publicGoodPlanContentAdapter = this.a;
        if (publicGoodPlanContentAdapter == null) {
            this.a = new PublicGoodPlanContentAdapter(context, publicGoodPlan.items);
            WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(context);
            wrapContentLinearLayoutManager.setOrientation(1);
            recyclerView.setLayoutManager(wrapContentLinearLayoutManager);
            recyclerView.setAdapter(this.a);
        } else {
            publicGoodPlanContentAdapter.setList(publicGoodPlan.items);
            this.a.notifyDataSetChanged();
        }
        textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        return inflate;
    }

    public void b(Context context, PublicGoodPlan publicGoodPlan) {
        Dialog b;
        if (context == null || publicGoodPlan == null) {
            return;
        }
        if ((!TextUtils.isEmpty(publicGoodPlan.mainTitle) || publicGoodPlan.items.size() > 0) && (b = com.jd.lib.cashier.sdk.core.utils.j.b(context)) != null) {
            com.jd.lib.cashier.sdk.core.utils.j.a(b, a(context, new a(this, b), publicGoodPlan), -1.0f);
            b.show();
        }
    }
}
