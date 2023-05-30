package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class PagingEndState extends State<TextView> {
    public PagingEndState(Footer footer) {
        super(footer);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getView() {
        LinearLayout linearLayout = new LinearLayout(this.footer.getContext());
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, DPIUtil.dip2px(50.0f)));
        linearLayout.setGravity(17);
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
        textView.setTextColor(Color.parseColor("#848689"));
        textView.setText("\u5230\u5e95\u4e86~");
        return textView;
    }
}
