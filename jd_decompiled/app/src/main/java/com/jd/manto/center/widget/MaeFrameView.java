package com.jd.manto.center.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import com.jd.manto.center.R;

/* loaded from: classes17.dex */
public class MaeFrameView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f6566g;

    /* renamed from: h  reason: collision with root package name */
    private final int f6567h;

    /* renamed from: i  reason: collision with root package name */
    private RelativeLayout f6568i;

    /* renamed from: j  reason: collision with root package name */
    private View f6569j;

    /* renamed from: k  reason: collision with root package name */
    private View f6570k;

    /* renamed from: l  reason: collision with root package name */
    private View f6571l;

    /* loaded from: classes17.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f6572g;

        a(MaeFrameView maeFrameView, View view) {
            this.f6572g = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6572g.setVisibility(8);
        }
    }

    public MaeFrameView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a(int i2, @DrawableRes int i3, String str, View.OnClickListener onClickListener) {
        View view;
        if (i2 == 2) {
            if (this.f6570k == null) {
                this.f6570k = ((ViewStub) findViewById(R.id.vb_no_data)).inflate();
            }
            view = this.f6570k;
        } else if (i2 != 3) {
            f(0, true);
            return;
        } else {
            if (this.f6571l == null) {
                this.f6571l = ((ViewStub) findViewById(R.id.vb_error_data)).inflate();
            }
            view = this.f6571l;
        }
        TextView textView = (TextView) view.findViewById(R.id.message);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        if (textView != null && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        if (i3 > 0 && imageView != null) {
            imageView.setImageResource(i3);
        }
        if (onClickListener != null) {
            view.setOnClickListener(onClickListener);
        }
        f(i2, false);
    }

    private void f(int i2, boolean z) {
        if (this.f6566g == 1) {
            getChildAt(1).setVisibility(4);
        }
        if (this.f6568i != null) {
            if (z || i2 != this.f6566g) {
                View childAt = getChildAt(i2);
                View childAt2 = getChildAt(this.f6566g);
                childAt.setAlpha(0.0f);
                childAt.animate().setDuration(200L).alpha(1.0f);
                childAt.clearAnimation();
                childAt2.clearAnimation();
                childAt2.setVisibility(8);
                childAt.setVisibility(0);
                this.f6566g = i2;
                if (i2 == 0 || i2 == 1) {
                    return;
                }
                getChildAt(0).setVisibility(4);
            }
        }
    }

    public void b() {
        f(0, false);
    }

    public void c() {
        f(4, false);
    }

    public void d(@DrawableRes int i2, String str, View.OnClickListener onClickListener) {
        a(2, i2, str, onClickListener);
    }

    public void e(String str) {
        a(2, -1, str, null);
    }

    public void g() {
        if (this.f6569j == null) {
            this.f6569j = ((ViewStub) findViewById(R.id.vb_progress)).inflate();
        }
        f(1, false);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        while (4 < getChildCount()) {
            View childAt = getChildAt(4);
            removeView(childAt);
            this.f6568i.addView(childAt, childAt.getLayoutParams());
        }
        if (this.f6567h > 0) {
            View inflate = RelativeLayout.inflate(getContext(), this.f6567h, null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView(inflate, 4, layoutParams);
            View childAt2 = getChildAt(4);
            if (childAt2 != null) {
                postDelayed(new a(this, childAt2), 20L);
            }
        }
    }

    public MaeFrameView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        View.inflate(getContext(), R.layout.manto_center_frame_view, this);
        this.f6568i = (RelativeLayout) findViewById(R.id.container);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.manto_center_FrameView);
        this.f6567h = obtainStyledAttributes.getResourceId(R.styleable.manto_center_FrameView_manto_center_fv_customLayout, -1);
        obtainStyledAttributes.recycle();
    }
}
