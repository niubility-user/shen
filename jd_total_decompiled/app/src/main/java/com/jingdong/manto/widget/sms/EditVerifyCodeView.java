package com.jingdong.manto.widget.sms;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class EditVerifyCodeView extends RelativeLayout {
    private ImageView[] a;
    private TextView[] b;

    /* renamed from: c  reason: collision with root package name */
    View.OnKeyListener f14548c;
    private EditText d;

    /* renamed from: e  reason: collision with root package name */
    public StringBuilder f14549e;

    /* renamed from: f  reason: collision with root package name */
    private Context f14550f;

    /* loaded from: classes16.dex */
    class a implements View.OnKeyListener {
        a() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            if (i2 == 67 && keyEvent.getAction() == 1) {
                EditVerifyCodeView.a(EditVerifyCodeView.this);
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes16.dex */
    class b implements View.OnKeyListener {
        b() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            if (i2 == 67 && keyEvent.getAction() == 1) {
                EditVerifyCodeView.a(EditVerifyCodeView.this);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements TextWatcher {
        c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (editable == null || editable.length() == 0) {
                return;
            }
            MantoLog.i("EditVerifyCodeView", String.format("afterTextChanged:%s", editable.toString()));
            if (EditVerifyCodeView.this.f14549e.length() < 6) {
                EditVerifyCodeView.this.f14549e.append(editable.toString());
                EditVerifyCodeView.b(EditVerifyCodeView.this);
            }
            editable.delete(0, editable.length());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d extends NumberKeyListener {
        d(EditVerifyCodeView editVerifyCodeView) {
        }

        @Override // android.text.method.NumberKeyListener
        @NonNull
        protected char[] getAcceptedChars() {
            return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 3;
        }
    }

    public EditVerifyCodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14549e = new StringBuilder();
        this.a = new ImageView[6];
        this.b = new TextView[6];
        this.f14548c = new a();
        a(context);
    }

    public EditVerifyCodeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14549e = new StringBuilder();
        this.a = new ImageView[6];
        this.b = new TextView[6];
        this.f14548c = new b();
        a(context);
    }

    private void a(Context context) {
        this.f14550f = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.manto_edit_verify_code_layout, (ViewGroup) null);
        this.b[0] = (TextView) inflate.findViewById(R.id.tv0);
        this.b[1] = (TextView) inflate.findViewById(R.id.tv1);
        this.b[2] = (TextView) inflate.findViewById(R.id.tv2);
        this.b[3] = (TextView) inflate.findViewById(R.id.tv3);
        this.b[4] = (TextView) inflate.findViewById(R.id.tv4);
        this.b[5] = (TextView) inflate.findViewById(R.id.tv5);
        this.a[0] = (ImageView) inflate.findViewById(R.id.iv0);
        this.a[1] = (ImageView) inflate.findViewById(R.id.iv1);
        this.a[2] = (ImageView) inflate.findViewById(R.id.iv2);
        this.a[3] = (ImageView) inflate.findViewById(R.id.iv3);
        this.a[4] = (ImageView) inflate.findViewById(R.id.iv4);
        this.a[5] = (ImageView) inflate.findViewById(R.id.iv5);
        EditText editText = (EditText) inflate.findViewById(R.id.et);
        this.d = editText;
        editText.addTextChangedListener(new c());
        this.d.setKeyListener(new d(this));
        this.d.setOnKeyListener(this.f14548c);
        addView(inflate, new RelativeLayout.LayoutParams(-1, -1));
    }

    static void a(EditVerifyCodeView editVerifyCodeView) {
        String sb = editVerifyCodeView.f14549e.toString();
        MantoLog.i("EditVerifyCodeView", "del before str:" + ((Object) editVerifyCodeView.f14549e));
        int length = sb.length();
        if (length != 0) {
            if (length > 0 && length <= 6) {
                editVerifyCodeView.f14549e.delete(length - 1, length);
            }
            int i2 = length - 1;
            editVerifyCodeView.b[i2].setVisibility(4);
            editVerifyCodeView.b[i2].setText("");
            editVerifyCodeView.a[i2].setVisibility(0);
            MantoLog.i("EditVerifyCodeView", "del after str:" + ((Object) editVerifyCodeView.f14549e));
        }
    }

    static void b(EditVerifyCodeView editVerifyCodeView) {
        String sb = editVerifyCodeView.f14549e.toString();
        MantoLog.i("EditVerifyCodeView", "mBuilder:" + ((Object) editVerifyCodeView.f14549e));
        int length = sb.length();
        if (length <= 0 || length > 6) {
            return;
        }
        int i2 = length - 1;
        editVerifyCodeView.b[i2].setVisibility(0);
        editVerifyCodeView.b[i2].setText(String.valueOf(sb.charAt(i2)));
        editVerifyCodeView.a[i2].setVisibility(4);
    }

    public final void setText(String str) {
        StringBuilder sb = this.f14549e;
        sb.delete(0, sb.length());
        this.f14549e.append(str);
        String sb2 = this.f14549e.toString();
        int length = sb2.length();
        MantoLog.i("EditVerifyCodeView", "mBuilder:" + ((Object) this.f14549e));
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                this.b[i2].setVisibility(0);
                this.b[i2].setText(String.valueOf(sb2.charAt(i2)));
                this.a[i2].setVisibility(4);
            }
            return;
        }
        for (int i3 = 0; i3 < 6; i3++) {
            this.b[i3].setVisibility(4);
            this.b[i3].setText("");
            this.a[i3].setVisibility(0);
        }
    }
}
