package com.jd.manto.center.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.manto.center.R;

/* loaded from: classes17.dex */
public class DefaultPullLoadFooter extends FrameLayout implements com.jd.manto.center.widget.recycler.c {

    /* renamed from: g  reason: collision with root package name */
    protected View f6563g;

    /* renamed from: h  reason: collision with root package name */
    protected ProgressBar f6564h;

    /* renamed from: i  reason: collision with root package name */
    protected TextView f6565i;

    public DefaultPullLoadFooter(Context context) {
        this(context, null);
    }

    @Override // com.jd.manto.center.widget.recycler.c
    public void a() {
        setVisibility(0);
        this.f6564h.setVisibility(8);
        this.f6565i.setVisibility(4);
        this.f6565i.setText("\u6ca1\u6709\u66f4\u591a\u6570\u636e");
        View findViewById = findViewById(R.id.footer_container);
        if (findViewById == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.manto_center_pkg_recent_footer, (ViewGroup) this, false);
            inflate.bringToFront();
            addView(inflate);
            return;
        }
        findViewById.bringToFront();
    }

    @Override // com.jd.manto.center.widget.recycler.c
    public void b() {
        setVisibility(0);
        this.f6564h.setVisibility(8);
        this.f6565i.setText("\u52a0\u8f7d\u5931\u8d25");
    }

    @Override // com.jd.manto.center.widget.recycler.c
    public void c() {
        setVisibility(0);
        this.f6564h.setVisibility(0);
        this.f6565i.setText("\u52a0\u8f7d\u4e2d...");
    }

    @Override // com.jd.manto.center.widget.recycler.c
    public void d() {
        setVisibility(0);
        this.f6564h.setVisibility(8);
        this.f6565i.setText("\u52a0\u8f7d\u5b8c\u6bd5");
    }

    @Override // com.jd.manto.center.widget.recycler.c
    public View getView() {
        return this;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = -1;
            layoutParams.height = -2;
            setLayoutParams(layoutParams);
        }
    }

    public DefaultPullLoadFooter(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DefaultPullLoadFooter(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f6563g = LayoutInflater.from(context).inflate(R.layout.manto_center_load_more_foot, this);
        this.f6564h = (ProgressBar) findViewById(R.id.around_pb_loading);
        this.f6565i = (TextView) findViewById(R.id.around_tv_text);
        setVisibility(4);
    }
}
