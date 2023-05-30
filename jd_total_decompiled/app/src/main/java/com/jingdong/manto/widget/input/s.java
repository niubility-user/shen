package com.jingdong.manto.widget.input;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes16.dex */
public final class s extends LinearLayout {
    NumberKeyboardView a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    b f14492c;
    EditText d;

    /* loaded from: classes16.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            s.this.a();
            s.this.setVisibility(8);
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a();
    }

    private s(Context context) {
        super(context);
        this.b = false;
        super.setId(R.id.manto_input_number_panel);
        setOrientation(1);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(getContext(), 230));
        NumberKeyboardView numberKeyboardView = new NumberKeyboardView(getContext());
        numberKeyboardView.findViewById(R.id.manto_intput_keyboard_close).setOnClickListener(new a());
        this.a = numberKeyboardView;
        addView(numberKeyboardView, layoutParams);
    }

    public static s a(View view) {
        return (s) view.getRootView().findViewById(R.id.manto_input_number_panel);
    }

    public static s b(View view) {
        s a2 = a(view);
        if (a2 != null) {
            a2.a.b();
            return a2;
        }
        x b2 = x.b(view);
        if (b2 == null) {
            return null;
        }
        s sVar = new s(view.getContext());
        b2.f14496f.add(sVar.a.q);
        b2.a(sVar, false);
        return sVar;
    }

    private void b() {
        b bVar;
        if (this.b || (bVar = this.f14492c) == null) {
            return;
        }
        this.b = true;
        bVar.a();
        this.b = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        EditText editText = this.d;
        if (editText != null) {
            editText.clearFocus();
            this.d = null;
            this.f14492c = null;
            this.b = false;
        }
        NumberKeyboardView numberKeyboardView = this.a;
        if (numberKeyboardView != null) {
            numberKeyboardView.a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i2, int i3) {
        if (isShown()) {
            i3 = View.MeasureSpec.makeMeasureSpec(MantoDensityUtils.dip2pixel(getContext(), 230), 1073741824);
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public final void setId(int i2) {
    }

    public final void setInputEditText(EditText editText) {
        this.d = editText;
        this.a.setInputEditText(editText);
    }

    @Override // android.view.View
    public final void setVisibility(int i2) {
        if (getVisibility() == i2 && (getVisibility() == 0 || getVisibility() == 8)) {
            return;
        }
        if (i2 == 0) {
            super.setVisibility(i2);
            return;
        }
        super.setVisibility(8);
        b();
    }
}
