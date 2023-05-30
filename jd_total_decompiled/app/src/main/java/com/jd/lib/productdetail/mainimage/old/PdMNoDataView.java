package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMNoDataView extends ConstraintLayout {

    /* renamed from: g */
    public Context f5059g;

    /* renamed from: h */
    public SimpleDraweeView f5060h;

    /* renamed from: i */
    public AppCompatTextView f5061i;

    /* renamed from: j */
    public PdAutoChangeTextSize f5062j;

    /* renamed from: k */
    public int f5063k;

    /* renamed from: l */
    public int f5064l;

    /* renamed from: m */
    public String f5065m;

    /* renamed from: n */
    public String f5066n;
    public boolean o;

    public PdMNoDataView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.f5059g = context;
    }

    public void a(String str) {
        this.f5066n = str;
        if (this.f5062j != null) {
            if (!TextUtils.isEmpty(str)) {
                this.f5062j.setText(this.f5066n);
            }
            this.f5062j.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.f5059g, R.color.lib_pd_image_color_262626, this.o));
            this.f5062j.setBackgroundResource(this.o ? R.drawable.lib_pd_mainimage_no_data_btn_dark_bg : R.drawable.lib_pd_mainimage_no_data_btn_bg);
        }
    }

    public void b(View.OnClickListener onClickListener) {
        PdAutoChangeTextSize pdAutoChangeTextSize = this.f5062j;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setOnClickListener(onClickListener);
        }
    }

    public void c(boolean z) {
        this.o = z;
    }

    public void d(String str) {
        this.f5065m = str;
        if (this.f5061i != null) {
            if (!TextUtils.isEmpty(str)) {
                this.f5061i.setText(this.f5065m);
            }
            this.f5061i.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.f5059g, R.color.lib_pd_image_color_808080, this.o));
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5060h = (SimpleDraweeView) findViewById(R.id.lib_pd_error_icon);
        this.f5061i = (AppCompatTextView) findViewById(R.id.lib_pd_error_msg);
        this.f5062j = (PdAutoChangeTextSize) findViewById(R.id.lib_pd_retry_btn);
    }

    public PdMNoDataView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.o = false;
        this.f5059g = context;
    }
}
