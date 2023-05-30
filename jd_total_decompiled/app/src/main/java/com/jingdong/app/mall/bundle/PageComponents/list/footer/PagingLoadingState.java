package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class PagingLoadingState extends State<TextView> {
    public PagingLoadingState(Footer footer) {
        super(footer);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getView() {
        Context context = this.footer.getContext();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, DPIUtil.dip2px(50.0f)));
        linearLayout.setGravity(17);
        JDProgressBar jDProgressBar = new JDProgressBar(context);
        jDProgressBar.setLayoutParams(new ViewGroup.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f)));
        linearLayout.addView(jDProgressBar);
        linearLayout.addView(getCustomView());
        return linearLayout;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.State, com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    /* renamed from: onClick */
    public void a(View view) {
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public TextView getCustomView() {
        TextView textView = new TextView(this.footer.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = DPIUtil.dip2px(4.0f);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(Color.parseColor("#848689"));
        textView.setText("\u52a0\u8f7d\u4e2d...");
        return textView;
    }
}
