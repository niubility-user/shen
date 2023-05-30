package com.jd.aips.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class LoadingView extends FrameLayout {
    private TextView a;

    public LoadingView(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.aips_view_loading, this);
        this.a = (TextView) findViewById(R.id.aips_loading_text);
    }

    public void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
        }
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    @TargetApi(21)
    public LoadingView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context);
    }

    public void setText(@StringRes int i2) {
        this.a.setText(i2);
        this.a.setVisibility(0);
    }
}
