package com.jdpay.membercode.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.membercode.R;

/* loaded from: classes18.dex */
public class c extends b implements View.OnClickListener {

    /* renamed from: i  reason: collision with root package name */
    private TextView f7568i;

    /* renamed from: j  reason: collision with root package name */
    private Button f7569j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(@NonNull CodeView codeView) {
        super(codeView);
    }

    @Override // com.jdpay.membercode.widget.b
    protected View b(@NonNull Context context) {
        ViewGroup viewGroup = (ViewGroup) View.inflate(context, R.layout.jdpay_mb_error, null);
        this.f7568i = (TextView) viewGroup.findViewById(R.id.text);
        Button button = (Button) viewGroup.findViewById(R.id.retry);
        this.f7569j = button;
        button.setOnClickListener(this);
        return viewGroup;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@Nullable CharSequence charSequence) {
        this.f7568i.setText(charSequence);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.f7569j) {
            this.f7566g.init();
        }
    }
}
