package com.jingdong.app.mall.bundle.jdrhsdk.ui;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.jdrhsdk.R;
import com.jingdong.app.mall.bundle.jdrhsdk.d.e;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes2.dex */
public class b extends com.jingdong.app.mall.bundle.jdrhsdk.ui.a {

    /* renamed from: g  reason: collision with root package name */
    private InterfaceC0252b f8182g = null;

    /* renamed from: h  reason: collision with root package name */
    private TextView f8183h;

    /* renamed from: i  reason: collision with root package name */
    private String f8184i;

    /* renamed from: j  reason: collision with root package name */
    private Button f8185j;

    /* renamed from: k  reason: collision with root package name */
    private String f8186k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f8187l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f8188m;

    /* renamed from: n  reason: collision with root package name */
    private LinearLayout f8189n;

    /* loaded from: classes2.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.s();
        }
    }

    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.ui.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0252b {
        void a();
    }

    private GradientDrawable n(int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        gradientDrawable.setColors(new int[]{-45718, -381927});
        float f2 = i2;
        gradientDrawable.setCornerRadii(new float[]{f2, f2, f2, f2, f2, f2, f2, f2});
        gradientDrawable.setStroke(0, 0);
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        InterfaceC0252b interfaceC0252b = this.f8182g;
        if (interfaceC0252b != null) {
            interfaceC0252b.a();
        }
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.ui.a
    protected void m(Activity activity) {
        int i2;
        int i3;
        int i4;
        Button button;
        int i5;
        if (e.v(activity)) {
            d(activity, this.f8187l, 100, 100);
            i2 = 0;
            i3 = 0;
            i4 = 0;
            e(activity, this.f8187l, 0, 24, 0, 0);
            e(activity, this.f8189n, 0, R2.anim.mtrl_bottom_sheet_slide_in, 0, 0);
            e.i(this.f8188m);
            l(activity, this.f8188m, 18);
            e.i(this.f8183h);
            l(activity, this.f8183h, 14);
            e.g(this.f8185j);
            h(activity, this.f8185j, 16);
            this.f8185j.setBackground(n(e.n(activity, 24)));
            d(activity, this.f8185j, R2.attr.actionModeTheme, 48);
            button = this.f8185j;
            i5 = 48;
        } else {
            d(activity, this.f8187l, 280, 280);
            i2 = 0;
            i3 = 0;
            i4 = 0;
            e(activity, this.f8187l, 0, 124, 0, 0);
            e(activity, this.f8189n, 0, R2.attr.behavior_autoHide, 0, 0);
            e.i(this.f8188m);
            l(activity, this.f8188m, 40);
            e.i(this.f8183h);
            l(activity, this.f8183h, 32);
            e.g(this.f8185j);
            this.f8185j.setBackground(n(e.n(activity, 66)));
            h(activity, this.f8185j, 40);
            d(activity, this.f8185j, R2.attr.blendSrc, 120);
            button = this.f8185j;
            i5 = 60;
        }
        e(activity, button, i2, i5, i3, i4);
    }

    public void o(InterfaceC0252b interfaceC0252b) {
        this.f8182g = interfaceC0252b;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jdrhsdk_fragment_verify_handle, (ViewGroup) null);
        this.f8187l = (ImageView) inflate.findViewById(R.id.jdrhsdk_fragment_verfify_main_icon);
        this.f8189n = (LinearLayout) inflate.findViewById(R.id.jdrhsdk_fragment_verify_main_text_button);
        this.f8188m = (TextView) inflate.findViewById(R.id.jdrhsdk_fragment_dangerous_text);
        e.k("https://m.360buyimg.com/mobilecal/jfs/t1/8911/7/22125/3750/63e468efFb9264b81/641bf73afa94757f.webp", this.f8187l);
        Button button = (Button) inflate.findViewById(R.id.jdrhsdk_fragment_verify_button);
        this.f8185j = button;
        button.setOnClickListener(new a());
        String str = this.f8186k;
        if (str != null) {
            this.f8185j.setText(str);
        }
        TextView textView = (TextView) inflate.findViewById(R.id.jdrhsdk_fragement_please_text);
        this.f8183h = textView;
        String str2 = this.f8184i;
        if (str2 != null) {
            textView.setText(str2);
        }
        m(getActivity());
        return inflate;
    }

    public void r(String str) {
        this.f8186k = str;
        Button button = this.f8185j;
        if (button != null) {
            button.setText(str);
        }
    }

    public void t(String str) {
        this.f8184i = str;
        TextView textView = this.f8183h;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
