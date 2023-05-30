package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class PagingErrorState extends State<TextView> {
    public PagingErrorState(Footer footer) {
        super(footer);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getView() {
        LinearLayout linearLayout = new LinearLayout(this.footer.getContext());
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, DPIUtil.dip2px(50.0f)));
        linearLayout.setGravity(17);
        linearLayout.addView(getCustomView());
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.f
            {
                PagingErrorState.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PagingErrorState.this.b(view);
            }
        });
        return linearLayout;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.State, com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    /* renamed from: onClick */
    public void b(View view) {
        super.a(view);
        BaseFooter baseFooter = this.footer;
        if (baseFooter == null) {
            return;
        }
        baseFooter.changeState(States.PAGING_LOADING);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public TextView getCustomView() {
        TextView textView = new TextView(this.footer.getContext());
        textView.setTextColor(Color.parseColor("#848689"));
        textView.setText("\u7f51\u7edc\u5f02\u5e38\uff0c\u70b9\u51fb\u91cd\u65b0\u52a0\u8f7d");
        return textView;
    }
}
