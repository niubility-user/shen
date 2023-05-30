package com.jingdong.common.jdreactFramework.floatingview;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.jingdong.jdreactframewok.R;

/* loaded from: classes5.dex */
public class EnFloatingView extends FloatingMagnetView {
    private final ImageView mIcon;
    private final TextView mText;

    public EnFloatingView(@NonNull Context context) {
        this(context, R.layout.en_floating_view);
    }

    public void setIconImage(@DrawableRes int i2) {
        this.mIcon.setImageResource(i2);
        this.mIcon.setVisibility(0);
        this.mText.setVisibility(8);
    }

    public void setText(String str) {
        this.mText.setText(str);
        this.mText.setVisibility(0);
        this.mIcon.setVisibility(8);
    }

    public EnFloatingView(@NonNull Context context, @LayoutRes int i2) {
        super(context, null);
        FrameLayout.inflate(context, i2, this);
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.mText = (TextView) findViewById(R.id.text);
    }
}
