package com.jingdong.manto.widget.input;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.widget.input.x;

/* loaded from: classes16.dex */
public class NumberKeyboardView extends LinearLayout implements a.b {
    private EditText a;
    private Button b;

    /* renamed from: c  reason: collision with root package name */
    private Button f14391c;
    private Button d;

    /* renamed from: e  reason: collision with root package name */
    private Button f14392e;

    /* renamed from: f  reason: collision with root package name */
    private Button f14393f;

    /* renamed from: g  reason: collision with root package name */
    private Button f14394g;

    /* renamed from: h  reason: collision with root package name */
    private Button f14395h;

    /* renamed from: i  reason: collision with root package name */
    private Button f14396i;

    /* renamed from: j  reason: collision with root package name */
    private Button f14397j;

    /* renamed from: k  reason: collision with root package name */
    private Button f14398k;

    /* renamed from: l  reason: collision with root package name */
    private View f14399l;

    /* renamed from: m  reason: collision with root package name */
    private Button f14400m;

    /* renamed from: n  reason: collision with root package name */
    private ImageView f14401n;
    private int o;
    View p;
    x.c q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EditText editText;
            KeyEvent keyEvent;
            if (NumberKeyboardView.this.a != null) {
                if (view.getId() != R.id.tenpay_keyboard_x) {
                    int i2 = view.getId() == R.id.tenpay_keyboard_1 ? 8 : view.getId() == R.id.tenpay_keyboard_2 ? 9 : view.getId() == R.id.tenpay_keyboard_3 ? 10 : view.getId() == R.id.tenpay_keyboard_4 ? 11 : view.getId() == R.id.tenpay_keyboard_5 ? 12 : view.getId() == R.id.tenpay_keyboard_6 ? 13 : view.getId() == R.id.tenpay_keyboard_7 ? 14 : view.getId() == R.id.tenpay_keyboard_8 ? 15 : view.getId() == R.id.tenpay_keyboard_9 ? 16 : view.getId() == R.id.tenpay_keyboard_0 ? 7 : view.getId() == R.id.tenpay_keyboard_d ? 67 : 0;
                    NumberKeyboardView.this.a.dispatchKeyEvent(new KeyEvent(0, i2));
                    NumberKeyboardView.this.a.dispatchKeyEvent(new KeyEvent(1, i2));
                    return;
                }
                int i3 = NumberKeyboardView.this.o;
                if (i3 == 1) {
                    NumberKeyboardView.this.a.dispatchKeyEvent(new KeyEvent(0, 59));
                    NumberKeyboardView.this.a.dispatchKeyEvent(new KeyEvent(0, 52));
                    editText = NumberKeyboardView.this.a;
                    keyEvent = new KeyEvent(1, 59);
                } else if (i3 != 2) {
                    return;
                } else {
                    editText = NumberKeyboardView.this.a;
                    keyEvent = new KeyEvent(0, 56);
                }
                editText.dispatchKeyEvent(keyEvent);
            }
        }
    }

    /* loaded from: classes16.dex */
    class b implements x.c {
        b() {
        }

        @Override // com.jingdong.manto.widget.input.x.c
        public void a() {
            if (NumberKeyboardView.this.isShown()) {
                return;
            }
            com.jingdong.manto.k.a.b().b(NumberKeyboardView.this);
        }
    }

    public NumberKeyboardView(Context context) {
        super(context);
        this.o = 0;
        this.q = new b();
        a(context);
    }

    public NumberKeyboardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 0;
        this.q = new b();
        a(context);
    }

    private void a(Context context) {
        int a2 = com.jingdong.manto.k.a.b().a();
        if (this.p != null) {
            removeAllViews();
        }
        this.p = LayoutInflater.from(context).inflate(a2 == 1 ? R.layout.manto_input_number_keyboard_dark : R.layout.manto_input_number_keyboard, (ViewGroup) this, true);
        this.f14391c = (Button) this.p.findViewById(R.id.tenpay_keyboard_1);
        this.d = (Button) this.p.findViewById(R.id.tenpay_keyboard_2);
        this.f14392e = (Button) this.p.findViewById(R.id.tenpay_keyboard_3);
        this.f14393f = (Button) this.p.findViewById(R.id.tenpay_keyboard_4);
        this.f14394g = (Button) this.p.findViewById(R.id.tenpay_keyboard_5);
        this.f14395h = (Button) this.p.findViewById(R.id.tenpay_keyboard_6);
        this.f14396i = (Button) this.p.findViewById(R.id.tenpay_keyboard_7);
        this.f14397j = (Button) this.p.findViewById(R.id.tenpay_keyboard_8);
        this.f14398k = (Button) this.p.findViewById(R.id.tenpay_keyboard_9);
        this.f14400m = (Button) this.p.findViewById(R.id.tenpay_keyboard_x);
        this.b = (Button) this.p.findViewById(R.id.tenpay_keyboard_0);
        this.f14399l = this.p.findViewById(R.id.tenpay_keyboard_d);
        ImageView imageView = (ImageView) this.p.findViewById(R.id.manto_input_delete);
        this.f14401n = imageView;
        if (a2 == 1) {
            imageView.setColorFilter(context.getResources().getColor(R.color.manto_dark_text_weight));
        }
        a aVar = new a();
        this.f14391c.setOnClickListener(aVar);
        this.d.setOnClickListener(aVar);
        this.f14392e.setOnClickListener(aVar);
        this.f14393f.setOnClickListener(aVar);
        this.f14394g.setOnClickListener(aVar);
        this.f14395h.setOnClickListener(aVar);
        this.f14396i.setOnClickListener(aVar);
        this.f14397j.setOnClickListener(aVar);
        this.f14398k.setOnClickListener(aVar);
        this.f14400m.setOnClickListener(aVar);
        this.b.setOnClickListener(aVar);
        this.f14399l.setOnClickListener(aVar);
        if (Build.VERSION.SDK_INT >= 14) {
            this.f14391c.setContentDescription("1");
            this.d.setContentDescription("2");
            this.f14392e.setContentDescription("3");
            this.f14393f.setContentDescription("4");
            this.f14394g.setContentDescription("5");
            this.f14395h.setContentDescription("6");
            this.f14396i.setContentDescription("7");
            this.f14397j.setContentDescription("8");
            this.f14398k.setContentDescription("9");
            this.b.setContentDescription("0");
            this.f14400m.setContentDescription("\u5b57\u6bcdX");
            this.f14399l.setContentDescription("\u5220\u9664");
        }
    }

    public void a() {
        com.jingdong.manto.k.a.b().b(this);
    }

    public void b() {
        com.jingdong.manto.k.a.b().a(this);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(getContext());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setInputEditText(null);
    }

    public void setInputEditText(EditText editText) {
        if (editText != null) {
            this.a = editText;
            int imeOptions = editText.getImeOptions();
            CharSequence imeActionLabel = this.a.getImeActionLabel();
            if (!TextUtils.isEmpty(imeActionLabel)) {
                this.f14400m.setText(imeActionLabel);
            }
            if (imeOptions != 1) {
                return;
            }
            this.o = 0;
            if (TextUtils.isEmpty(imeActionLabel)) {
                this.f14400m.setText("");
            }
        }
    }

    public void setSecureAccessibility(View.AccessibilityDelegate accessibilityDelegate) {
        this.b.setAccessibilityDelegate(accessibilityDelegate);
        this.f14391c.setAccessibilityDelegate(accessibilityDelegate);
        this.d.setAccessibilityDelegate(accessibilityDelegate);
        this.f14392e.setAccessibilityDelegate(accessibilityDelegate);
        this.f14393f.setAccessibilityDelegate(accessibilityDelegate);
        this.f14394g.setAccessibilityDelegate(accessibilityDelegate);
        this.f14395h.setAccessibilityDelegate(accessibilityDelegate);
        this.f14396i.setAccessibilityDelegate(accessibilityDelegate);
        this.f14397j.setAccessibilityDelegate(accessibilityDelegate);
        this.f14398k.setAccessibilityDelegate(accessibilityDelegate);
        this.f14400m.setAccessibilityDelegate(accessibilityDelegate);
        this.f14399l.setAccessibilityDelegate(accessibilityDelegate);
    }

    public void setXMode(int i2) {
        this.o = i2;
        if (i2 == 0) {
            this.f14400m.setText("");
        } else if (i2 == 1) {
            this.f14400m.setText("X");
        } else if (i2 != 2) {
            this.o = 0;
        } else {
            this.f14400m.setText(OrderISVUtil.MONEY_DECIMAL);
        }
    }
}
