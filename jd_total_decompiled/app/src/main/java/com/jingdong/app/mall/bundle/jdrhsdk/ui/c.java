package com.jingdong.app.mall.bundle.jdrhsdk.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.app.mall.bundle.jdrhsdk.R;
import com.jingdong.app.mall.bundle.jdrhsdk.d.e;
import java.util.Locale;

/* loaded from: classes2.dex */
public class c extends com.jingdong.app.mall.bundle.jdrhsdk.ui.a {

    /* renamed from: g  reason: collision with root package name */
    private RelativeLayout f8191g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f8192h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f8193i;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f8195k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f8196l;

    /* renamed from: m  reason: collision with root package name */
    private CountDownTimer f8197m;

    /* renamed from: j  reason: collision with root package name */
    private InterfaceC0253c f8194j = null;

    /* renamed from: n  reason: collision with root package name */
    private int f8198n = 3;

    /* loaded from: classes2.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.v(false);
            if (c.this.f8194j != null) {
                c.this.f8194j.a();
            }
            if (c.this.f8196l != null) {
                e.k("http://m.360buyimg.com/mobilecal/jfs/t1/66400/2/24929/33673/63fc76e5F4923cab4/041d53bf81b55c7e.png", c.this.f8196l);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class b extends CountDownTimer {
        b(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            c.this.v(true);
            c cVar = c.this;
            cVar.u(cVar.getResources().getString(R.string.jdrhsdk_refresh));
            c.this.f8193i.setText("\u524d\u65b9\u62e5\u6324\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5");
            c.this.f8198n = 0;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            c.this.f8191g.setEnabled(false);
            c.this.u(String.format(Locale.ENGLISH, "\u7b49\u5f85\u5237\u65b0\uff08%d\uff09", Long.valueOf((j2 / 1000) + 1)));
        }
    }

    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.ui.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0253c {
        void a();
    }

    private void q(View view, int i2) {
        Drawable background = view.getBackground();
        if (background instanceof StateListDrawable) {
            Drawable current = background.getCurrent();
            if (current instanceof GradientDrawable) {
                float f2 = i2;
                ((GradientDrawable) current).setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(String str) {
        FragmentActivity activity;
        RelativeLayout relativeLayout;
        int length;
        int i2;
        if (this.f8191g == null || this.f8192h == null || getActivity() == null) {
            return;
        }
        this.f8192h.setText(str);
        if (e.v(getActivity())) {
            activity = getActivity();
            relativeLayout = this.f8191g;
            length = (str.length() * 12) + 24;
            i2 = 28;
        } else {
            activity = getActivity();
            relativeLayout = this.f8191g;
            length = (str.length() * 40) + 300;
            i2 = 120;
        }
        d(activity, relativeLayout, length, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(boolean z) {
        RelativeLayout relativeLayout = this.f8191g;
        if (relativeLayout != null) {
            relativeLayout.setEnabled(z);
            int color = getResources().getColor(R.color.jdrhsdk_gray);
            int color2 = getResources().getColor(R.color.jdrhsdk_jdred);
            TextView textView = this.f8192h;
            if (z) {
                color = color2;
            }
            textView.setTextColor(color);
        }
    }

    private void z() {
        this.f8198n = 3;
        b bVar = new b(this.f8198n * 1000, 1000L);
        this.f8197m = bVar;
        bVar.start();
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.ui.a
    protected void m(Activity activity) {
        RelativeLayout relativeLayout;
        int b2;
        boolean v = e.v(activity);
        e(activity, this.f8195k, 0, 160, 0, 0);
        if (v) {
            c(activity, this.f8196l, 160);
            l(activity, this.f8193i, 14);
            l(activity, this.f8192h, 12);
            ViewGroup.LayoutParams layoutParams = this.f8193i.getLayoutParams();
            layoutParams.height = e.n(activity, 14);
            this.f8193i.setLayoutParams(layoutParams);
            d(activity, this.f8191g, (this.f8192h.getText().length() * 12) + 24, 28);
            e.i(this.f8192h);
            e.i(this.f8193i);
            relativeLayout = this.f8191g;
            b2 = e.n(activity, 24);
        } else {
            c(activity, this.f8196l, 300);
            l(activity, this.f8193i, 40);
            l(activity, this.f8192h, 40);
            ViewGroup.LayoutParams layoutParams2 = this.f8193i.getLayoutParams();
            layoutParams2.height = e.b(activity, 40);
            this.f8193i.setLayoutParams(layoutParams2);
            d(activity, this.f8191g, (this.f8192h.getText().length() * 40) + 300, 120);
            e.i(this.f8192h);
            e.i(this.f8193i);
            relativeLayout = this.f8191g;
            b2 = e.b(activity, 120);
        }
        q(relativeLayout, b2);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jdrhsdk_fragement_error_handle, (ViewGroup) null);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.jdrhsdk_error_refresh_button);
        this.f8191g = relativeLayout;
        relativeLayout.setOnClickListener(new a());
        this.f8192h = (TextView) inflate.findViewById(R.id.jdrhsdk_error_refresh_button_text);
        this.f8193i = (TextView) inflate.findViewById(R.id.jdrhsdk_refresh_text);
        this.f8195k = (LinearLayout) inflate.findViewById(R.id.jdrhsdk_fragment_error_main);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.jdrhsdk_fragment_error_icon);
        this.f8196l = imageView;
        e.k("http://m.360buyimg.com/mobilecal/jfs/t1/66400/2/24929/33673/63fc76e5F4923cab4/041d53bf81b55c7e.png", imageView);
        b(getActivity());
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CountDownTimer countDownTimer = this.f8197m;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f8197m = null;
        }
    }

    public void r(InterfaceC0253c interfaceC0253c) {
        this.f8194j = interfaceC0253c;
    }

    public void x() {
        TextView textView = this.f8193i;
        if (textView != null) {
            textView.setText("\u5237\u65b0\u5931\u8d25\uff0c\u8bf73s\u540e\u91cd\u8bd5");
        }
        if (this.f8191g != null) {
            u("\u7b49\u5f85\u5237\u65b0\uff083\uff09");
        }
        v(false);
        z();
    }
}
