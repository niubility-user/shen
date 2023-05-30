package com.jingdong.manto.widget.k;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.sdk.api.IPickerInterface;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class f extends RelativeLayout {
    private com.jingdong.manto.widget.k.a a;
    private FrameLayout b;

    /* renamed from: c */
    private IPickerInterface f14533c;
    private final int d;

    /* renamed from: e */
    private a.b f14534e;

    /* renamed from: f */
    private TextView f14535f;

    /* renamed from: g */
    View.OnClickListener f14536g;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        a(f fVar) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* loaded from: classes16.dex */
    public class b implements a.b {
        final /* synthetic */ IPickerInterface a;
        final /* synthetic */ Context b;

        /* renamed from: c */
        final /* synthetic */ FrameLayout f14537c;
        final /* synthetic */ TextView d;

        /* renamed from: e */
        final /* synthetic */ TextView f14538e;

        /* renamed from: f */
        final /* synthetic */ RelativeLayout f14539f;

        b(IPickerInterface iPickerInterface, Context context, FrameLayout frameLayout, TextView textView, TextView textView2, RelativeLayout relativeLayout) {
            f.this = r1;
            this.a = iPickerInterface;
            this.b = context;
            this.f14537c = frameLayout;
            this.d = textView;
            this.f14538e = textView2;
            this.f14539f = relativeLayout;
        }

        @Override // com.jingdong.manto.k.a.b
        public void onDeepModeChanged(int i2) {
            IPickerInterface iPickerInterface = this.a;
            int contentColor = iPickerInterface != null ? iPickerInterface.getContentColor(this.b, i2) : this.b.getResources().getColor(R.color.manto_white);
            IPickerInterface iPickerInterface2 = this.a;
            int confirmColor = iPickerInterface2 != null ? iPickerInterface2.getConfirmColor(this.b, i2) : this.b.getResources().getColor(R.color.manto_picker_confirm);
            IPickerInterface iPickerInterface3 = this.a;
            int cancelColor = iPickerInterface3 != null ? iPickerInterface3.getCancelColor(this.b, i2) : this.b.getResources().getColor(R.color.manto_picker_cancel);
            IPickerInterface iPickerInterface4 = this.a;
            int backgroudColor = iPickerInterface4 != null ? iPickerInterface4.getBackgroudColor(this.b, i2) : this.b.getResources().getColor(R.color.manto_half_transparent);
            this.f14537c.setBackgroundColor(contentColor);
            this.d.setTextColor(cancelColor);
            this.f14538e.setTextColor(confirmColor);
            this.f14539f.setBackgroundColor(contentColor);
            f.this.setBackgroundColor(backgroudColor);
        }
    }

    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        c() {
            f.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.a(EnumC0704f.HideByCancel, true);
        }
    }

    /* loaded from: classes16.dex */
    public class d implements View.OnClickListener {
        d() {
            f.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.a(EnumC0704f.HideByCancel, true);
        }
    }

    /* loaded from: classes16.dex */
    public class e implements View.OnClickListener {
        e() {
            f.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.a(EnumC0704f.HideByConfirm, true);
        }
    }

    /* renamed from: com.jingdong.manto.widget.k.f$f */
    /* loaded from: classes16.dex */
    public enum EnumC0704f {
        HideByCancel,
        HideByConfirm
    }

    public f(Context context, IPickerInterface iPickerInterface) {
        super(context);
        this.d = MantoDensityUtils.dip2pixel(getContext(), 18);
        this.f14536g = new a(this);
        this.f14533c = iPickerInterface;
        setId(R.id.manto_picker_container);
        setClickable(true);
        setLongClickable(true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(getContext(), 251));
        layoutParams.addRule(12);
        FrameLayout frameLayout = new FrameLayout(context);
        int i2 = R.id.manto_picker_content;
        frameLayout.setId(i2);
        IPickerInterface iPickerInterface2 = this.f14533c;
        int contentColor = iPickerInterface2 != null ? iPickerInterface2.getContentColor(context, 0) : context.getResources().getColor(R.color.manto_white);
        frameLayout.setBackgroundColor(contentColor);
        frameLayout.setOnClickListener(this.f14536g);
        this.b = frameLayout;
        addView(frameLayout, layoutParams);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        IPickerInterface iPickerInterface3 = this.f14533c;
        int cancelColor = iPickerInterface3 != null ? iPickerInterface3.getCancelColor(context, 0) : context.getResources().getColor(R.color.manto_picker_cancel);
        TextView a2 = a(getResources().getString(R.string.manto_cancel), cancelColor);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(9);
        relativeLayout.addView(a2, layoutParams2);
        IPickerInterface iPickerInterface4 = this.f14533c;
        TextView a3 = a(getResources().getString(R.string.manto_confirm), iPickerInterface4 != null ? iPickerInterface4.getConfirmColor(context, 0) : context.getResources().getColor(R.color.manto_picker_confirm));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams3.addRule(11);
        TextView textView = new TextView(context);
        this.f14535f = textView;
        textView.setTextSize(16.0f);
        this.f14535f.setSingleLine(true);
        this.f14535f.setTextColor(cancelColor);
        this.f14535f.setGravity(17);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams4.addRule(13);
        int dip2pixel = MantoDensityUtils.dip2pixel(getContext(), 70);
        layoutParams4.rightMargin = dip2pixel;
        layoutParams4.leftMargin = dip2pixel;
        relativeLayout.addView(this.f14535f, layoutParams4);
        relativeLayout.setBackgroundColor(contentColor);
        relativeLayout.addView(a3, layoutParams3);
        relativeLayout.setOnClickListener(this.f14536g);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, MantoDensityUtils.dip2pixel(getContext(), 56));
        layoutParams5.addRule(2, i2);
        addView(relativeLayout, layoutParams5);
        this.f14534e = new b(iPickerInterface, context, frameLayout, a2, a3, relativeLayout);
        com.jingdong.manto.k.a.b().a(this.f14534e);
        setOnClickListener(new c());
        a2.setOnClickListener(new d());
        a3.setOnClickListener(new e());
    }

    private TextView a(String str, int i2) {
        TextView textView = new TextView(getContext());
        int i3 = this.d;
        textView.setPadding(i3, 0, i3, 0);
        textView.setTextColor(i2);
        textView.setText(str);
        textView.setTextSize(16.0f);
        textView.setGravity(17);
        return textView;
    }

    public static f a(View view) {
        return (f) view.getRootView().findViewById(R.id.manto_picker_container);
    }

    public void a(EnumC0704f enumC0704f, boolean z) {
        setVisibility(8);
        com.jingdong.manto.widget.k.a aVar = this.a;
        if (aVar != null) {
            this.b.removeView(aVar.c());
            if (z && this.a.a() != null) {
                if (enumC0704f == EnumC0704f.HideByConfirm) {
                    this.a.a().a(this.a.b());
                } else {
                    this.a.a().onCancel();
                }
            }
        }
        com.jingdong.manto.k.a.b().b(this.f14534e);
    }

    public void a() {
        a(EnumC0704f.HideByCancel, false);
    }

    public void a(com.jingdong.manto.widget.k.a aVar) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        a(aVar, layoutParams);
    }

    public void a(com.jingdong.manto.widget.k.a aVar, FrameLayout.LayoutParams layoutParams) {
        if (aVar == null) {
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = aVar;
        Object obj = this.a;
        if (obj == null) {
            obj = DYConstants.DY_NULL_STR;
        }
        objArr[1] = obj;
        MantoLog.d("PickerViewContainer", String.format("show <picker:%s, before:%s>", objArr));
        if (this.a != null) {
            a(EnumC0704f.HideByCancel, false);
        }
        this.a = aVar;
        this.b.addView(aVar.c(), layoutParams);
        setVisibility(0);
        com.jingdong.manto.k.a.b().a(this.f14534e);
    }

    public void b() {
        if (isShown()) {
            return;
        }
        com.jingdong.manto.k.a.b().b(this.f14534e);
    }

    public com.jingdong.manto.widget.k.a getCurPicker() {
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
        MantoLog.w("PickerViewContainer", "view detachfromwindow, so cleanup picker cache");
        com.jingdong.manto.m.g1.f.b();
    }

    public void setHeaderText(String str) {
        TextView textView = this.f14535f;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
