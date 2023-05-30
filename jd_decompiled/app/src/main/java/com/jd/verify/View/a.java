package com.jd.verify.View;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class a extends d implements DialogInterface.OnKeyListener {
    private ProgressBar b;

    /* renamed from: c  reason: collision with root package name */
    private Context f7116c;

    public a(Context context) {
        super(context, 16973840);
        this.f7116c = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Window window = getWindow();
        window.setAttributes(window.getAttributes());
        LinearLayout linearLayout = new LinearLayout(this.f7116c);
        this.b = new ProgressBar(this.f7116c);
        linearLayout.setGravity(17);
        this.b.setIndeterminateDrawable(this.f7116c.getResources().getDrawable(R.drawable.verify_progress));
        this.b.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        linearLayout.addView(this.b);
        setContentView(linearLayout);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        return i2 == 4;
    }
}
