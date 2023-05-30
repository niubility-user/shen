package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.R;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class NoDataState extends State<TextView> implements IImageView {
    private LinearLayout ll;

    public NoDataState(BaseFooter baseFooter) {
        super(baseFooter);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IImageView
    public ImageView generateImageView() {
        ImageView imageView = new ImageView(this.footer.getContext());
        imageView.setImageResource(R.drawable.fx_nodata_dog);
        return imageView;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public View getView() {
        LinearLayout linearLayout = this.ll;
        if (linearLayout != null) {
            return linearLayout;
        }
        Context context = this.footer.getContext();
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.ll = linearLayout2;
        linearLayout2.setGravity(17);
        this.ll.setOrientation(1);
        this.ll.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        TextView textView = new TextView(context);
        textView.setText("\u91cd\u65b0\u52a0\u8f7d");
        textView.setBackgroundResource(R.drawable.button_d_01);
        textView.setTextColor(Color.parseColor("#686868"));
        textView.setTextSize(1, 14.0f);
        textView.setPadding(DPIUtil.dip2px(30.0f), DPIUtil.dip2px(10.0f), DPIUtil.dip2px(30.0f), DPIUtil.dip2px(10.0f));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = DPIUtil.dip2px(26.0f);
        textView.setLayoutParams(layoutParams);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.e
            {
                NoDataState.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoDataState.this.b(view);
            }
        });
        this.ll.addView(generateImageView());
        this.ll.addView(getCustomView());
        this.ll.addView(textView);
        return this.ll;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.State, com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    /* renamed from: onClick */
    public void b(View view) {
        super.a(view);
        BaseFooter baseFooter = this.footer;
        if (baseFooter == null) {
            return;
        }
        baseFooter.changeState(States.NOTHING);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IUi
    public TextView getCustomView() {
        TextView textView = new TextView(this.footer.getContext());
        textView.setText("\u6682\u65e0\u6570\u636e~");
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_BFBFBF));
        textView.setTextSize(1, 17.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = DPIUtil.dip2px(10.0f);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}
