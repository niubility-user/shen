package com.jd.lib.productdetail.mainimage.holder.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.ClickableMovementMethod;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import java.lang.reflect.Field;

/* loaded from: classes15.dex */
public class PdMImageExpandableTextView extends PdAutoChangeTextSize {

    /* renamed from: g  reason: collision with root package name */
    public boolean f4878g;

    /* renamed from: h  reason: collision with root package name */
    public int f4879h;

    /* renamed from: i  reason: collision with root package name */
    public int f4880i;

    /* renamed from: j  reason: collision with root package name */
    public SpannableStringBuilder f4881j;

    /* renamed from: k  reason: collision with root package name */
    public SpannableStringBuilder f4882k;

    /* renamed from: l  reason: collision with root package name */
    public SpannableString f4883l;

    /* renamed from: m  reason: collision with root package name */
    public SpannableString f4884m;

    /* renamed from: n  reason: collision with root package name */
    public SpannableString f4885n;
    public SpannableString o;
    public boolean p;
    public boolean q;
    public b r;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMImageExpandableTextView.d(PdMImageExpandableTextView.this);
        }
    }

    /* loaded from: classes15.dex */
    public interface b {
        void a(boolean z);
    }

    public PdMImageExpandableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4878g = false;
        this.f4879h = 3;
        this.f4880i = 0;
        c();
    }

    public static void d(PdMImageExpandableTextView pdMImageExpandableTextView) {
        if (pdMImageExpandableTextView.p) {
            boolean z = !pdMImageExpandableTextView.f4878g;
            pdMImageExpandableTextView.f4878g = z;
            if (z) {
                super.setMaxLines(pdMImageExpandableTextView.f4879h);
                pdMImageExpandableTextView.setText(pdMImageExpandableTextView.f4882k);
                b bVar = pdMImageExpandableTextView.r;
                if (bVar != null) {
                    bVar.a(false);
                    return;
                }
                return;
            }
            super.setMaxLines(Integer.MAX_VALUE);
            pdMImageExpandableTextView.setText(pdMImageExpandableTextView.f4881j);
            b bVar2 = pdMImageExpandableTextView.r;
            if (bVar2 != null) {
                bVar2.a(true);
            }
        }
    }

    public final float a(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return f2;
        }
        try {
            for (Field field : getClass().getDeclaredFields()) {
                if (TextUtils.equals(str, field.getName())) {
                    return field.getFloat(this);
                }
            }
            return f2;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return f2;
        }
    }

    public SpannableString b(int i2) {
        SpannableString spannableString;
        Drawable drawable = getContext().getResources().getDrawable(i2);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.jd.lib.productdetail.mainimage.holder.helper.a aVar = new com.jd.lib.productdetail.mainimage.holder.helper.a(drawable);
        if (PDUtils.isUseEmptySpace()) {
            spannableString = new SpannableString("  ");
        } else {
            spannableString = new SpannableString("\b\b");
        }
        spannableString.setSpan(aVar, 1, spannableString.length(), 17);
        return spannableString;
    }

    public final void c() {
        super.setOnClickListener(new a());
        setMovementMethod(ClickableMovementMethod.getInstance());
        this.f4883l = b(R.drawable.lib_pd_mainimage_title_floorarrow_down);
        this.f4884m = b(R.drawable.lib_pd_mainimage_title_floorarrow_down_dark);
        this.f4885n = b(R.drawable.lib_pd_mainimage_title_floorarrow_up);
        this.o = b(R.drawable.lib_pd_mainimage_title_floorarrow_up_dark);
        setIncludeFontPadding(false);
    }

    public void e(boolean z) {
        this.q = z;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i2) {
        this.f4879h = i2;
        super.setMaxLines(i2);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
    }

    public PdMImageExpandableTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4878g = false;
        this.f4879h = 3;
        this.f4880i = 0;
        c();
    }
}
