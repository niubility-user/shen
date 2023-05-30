package com.jd.verify.View;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class c extends d {
    private TextView b;

    /* renamed from: c  reason: collision with root package name */
    private Button f7127c;
    private Button d;

    public c(Context context) {
        super(context, R.style.Verify_SSL_Dialog);
        b();
    }

    private void b() {
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_ssl_error);
        this.b = (TextView) findViewById(R.id.jd_dialog_message);
        this.f7127c = (Button) findViewById(R.id.jd_dialog_left_button);
        this.d = (Button) findViewById(R.id.jd_dialog_right_button);
    }

    public void a(String str) {
        this.f7127c.setText(str);
    }

    public void c(String str) {
        this.d.setText(str);
    }

    public void a(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.f7127c.setOnClickListener(onClickListener);
        }
    }

    public void b(String str) {
        if (this.b != null) {
            if (!TextUtils.isEmpty(str)) {
                this.b.setVisibility(0);
                this.b.setText(str);
                return;
            }
            this.b.setVisibility(8);
        }
    }

    public void b(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.d.setOnClickListener(onClickListener);
        }
    }
}
