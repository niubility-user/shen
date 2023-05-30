package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.R;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class ErrorState extends State<TextView> implements IImageView {
    private LinearLayout ll;

    public ErrorState(BaseFooter baseFooter) {
        super(baseFooter);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.IImageView
    public ImageView generateImageView() {
        ImageView imageView = new ImageView(this.footer.getContext());
        imageView.setImageResource(R.drawable.y_03);
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
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.c
            {
                ErrorState.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ErrorState.this.b(view);
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
        textView.setGravity(17);
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_BFBFBF));
        textView.setLineSpacing(0.0f, 1.2f);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\n").append((CharSequence) "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(17, true), 0, 7, 34);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), 7, spannableStringBuilder.length(), 34);
        textView.setText(spannableStringBuilder);
        return textView;
    }
}
