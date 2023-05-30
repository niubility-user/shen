package com.jingdong.sdk.jdcrashreport.recover;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;

/* loaded from: classes7.dex */
public final class d extends b implements View.OnClickListener {

    /* renamed from: h  reason: collision with root package name */
    private EditText f14958h;

    /* renamed from: i  reason: collision with root package name */
    private CheckBox f14959i;

    /* renamed from: j  reason: collision with root package name */
    private Button f14960j;

    /* renamed from: k  reason: collision with root package name */
    boolean f14961k = false;

    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public View c(Context context) {
        j(1);
        k(1024, 1024);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F6F6F6));
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setGravity(1);
        linearLayout.setOrientation(1);
        View cVar = new c(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = com.jingdong.sdk.jdcrashreport.b.c.k(84);
        layoutParams.height = com.jingdong.sdk.jdcrashreport.b.c.k(60);
        layoutParams.width = com.jingdong.sdk.jdcrashreport.b.c.k(60);
        linearLayout.addView(cVar, layoutParams);
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = com.jingdong.sdk.jdcrashreport.b.c.k(25);
        layoutParams2.rightMargin = com.jingdong.sdk.jdcrashreport.b.c.k(25);
        layoutParams2.topMargin = com.jingdong.sdk.jdcrashreport.b.c.k(20);
        linearLayout.addView(textView, layoutParams2);
        textView.setText("\u54ce\u5466\uff0c\u9875\u9762\u51fa\u73b0\u95ee\u9898\u4e86~");
        textView.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(8));
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_2E2D2D));
        TextView textView2 = new TextView(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.leftMargin = com.jingdong.sdk.jdcrashreport.b.c.k(25);
        layoutParams3.rightMargin = com.jingdong.sdk.jdcrashreport.b.c.k(25);
        layoutParams3.topMargin = com.jingdong.sdk.jdcrashreport.b.c.k(30);
        linearLayout.addView(textView2, layoutParams3);
        textView2.setText("\u4e3a\u4e86\u6539\u5584APP\u8d28\u91cf\uff0c\u5e0c\u671b\u60a8\u5c06\u5f53\u524d\u7684\u51fa\u9519\u60c5\u51b5\u544a\u77e5\u6211\u4eec\uff0c\u611f\u8c22\u60a8\u7684\u652f\u6301\u3002");
        textView2.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(6));
        textView2.setTextColor(Color.parseColor("#848484"));
        this.f14958h = new EditText(context);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, com.jingdong.sdk.jdcrashreport.b.c.k(130));
        layoutParams4.topMargin = com.jingdong.sdk.jdcrashreport.b.c.k(10);
        linearLayout.addView(this.f14958h, layoutParams4);
        this.f14958h.setPadding(com.jingdong.sdk.jdcrashreport.b.c.k(15), com.jingdong.sdk.jdcrashreport.b.c.k(14), com.jingdong.sdk.jdcrashreport.b.c.k(15), com.jingdong.sdk.jdcrashreport.b.c.k(14));
        this.f14958h.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(7));
        this.f14958h.setHintTextColor(Color.parseColor("#DADADA"));
        this.f14958h.setTextColor(-16777216);
        this.f14958h.setHint("\u8bf7\u8f93\u5165\u5f53\u524d\u51fa\u9519\u7684\u60c5\u51b5\uff0c\u6bd4\u5982\u6b63\u5728\u505a\u7684\u64cd\u4f5c\u3001\u51fa\u73b0\u95ea\u9000\u7684\u9875\u9762\u7b49");
        this.f14958h.setBackgroundColor(Color.parseColor("#FFFFFF"));
        this.f14958h.setGravity(8388659);
        this.f14958h.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(100)});
        f fVar = new f(context);
        this.f14960j = fVar;
        fVar.setId(110911);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams5.leftMargin = com.jingdong.sdk.jdcrashreport.b.c.k(15);
        layoutParams5.rightMargin = com.jingdong.sdk.jdcrashreport.b.c.k(15);
        layoutParams5.topMargin = com.jingdong.sdk.jdcrashreport.b.c.k(50);
        layoutParams5.bottomMargin = com.jingdong.sdk.jdcrashreport.b.c.k(30);
        linearLayout.addView(this.f14960j, layoutParams5);
        this.f14960j.setText("\u786e\u5b9a");
        this.f14960j.setTextColor(Color.parseColor("#FFFFFF"));
        this.f14960j.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(7));
        LinearLayout linearLayout2 = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        linearLayout2.setGravity(1);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2, layoutParams6);
        CheckBox checkBox = new CheckBox(context);
        this.f14959i = checkBox;
        checkBox.setChecked(true);
        linearLayout2.addView(this.f14959i, layoutParams6);
        TextView textView3 = new TextView(context);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams7.leftMargin = com.jingdong.sdk.jdcrashreport.b.c.k(5);
        textView3.setText("\u786e\u5b9a\u540e\u91cd\u542fAPP");
        textView3.setTextSize(com.jingdong.sdk.jdcrashreport.b.c.k(6));
        textView3.setTextColor(Color.parseColor(JDDarkUtil.COLOR_2E2D2D));
        linearLayout2.addView(textView3, layoutParams7);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public void d() {
        super.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public void g() {
        super.g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public void h() {
        super.h();
        if (this.f14961k) {
            a();
        } else {
            this.f14960j.setOnClickListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.jdcrashreport.recover.b
    public void i() {
        super.i();
        if (!this.f14961k) {
            this.f14961k = true;
            l(this.f14958h.getText().toString(), a.NONE);
        }
        a();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        l(this.f14958h.getText().toString(), this.f14959i.isChecked() ? a.RESTART : a.NONE);
        this.f14961k = true;
    }
}
