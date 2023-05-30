package com.jingdong.manto.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.q.q;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.j;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class d extends FrameLayout {

    /* renamed from: j */
    private static final String f14344j = d.class.getSimpleName();
    public LinearLayout a;
    public ImageView b;

    /* renamed from: c */
    public String f14345c;
    public int d;

    /* renamed from: e */
    public int f14346e;

    /* renamed from: f */
    public LinkedList<g> f14347f;

    /* renamed from: g */
    int f14348g;

    /* renamed from: h */
    public h f14349h;

    /* renamed from: i */
    public a.h f14350i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        final /* synthetic */ g a;

        a(g gVar) {
            d.this = r1;
            this.a = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = d.this;
            dVar.a(dVar, this.a.f14358h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ g b;

        b(int i2, g gVar) {
            d.this = r1;
            this.a = i2;
            this.b = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = d.this;
            dVar.a(dVar.a.getChildAt(this.a), this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Runnable {
        c() {
            d.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i2 = 0; i2 < d.this.f14347f.size(); i2++) {
                d dVar = d.this;
                dVar.a(dVar.a.getChildAt(i2), d.this.f14347f.get(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.widget.d$d */
    /* loaded from: classes16.dex */
    public class RunnableC0692d implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f14352c;
        final /* synthetic */ String d;

        RunnableC0692d(String str, String str2, String str3, String str4) {
            d.this = r1;
            this.a = str;
            this.b = str2;
            this.f14352c = str3;
            this.d = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = d.this;
            dVar.b(dVar, this.a, this.b);
            d.a(d.this, this.f14352c, this.d);
            d.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e extends AnimatorListenerAdapter {
        final /* synthetic */ Runnable a;

        e(Runnable runnable) {
            d.this = r1;
            this.a = runnable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Runnable runnable = this.a;
            if (runnable != null) {
                d.this.post(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class f implements Runnable {
        final /* synthetic */ a.h a;

        f(a.h hVar) {
            d.this = r1;
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d.this.f14347f.size() != this.a.f13061g.size()) {
                return;
            }
            d dVar = d.this;
            a.h hVar = this.a;
            dVar.f14350i = hVar;
            dVar.f14346e = MantoDensityUtils.parseColor(hVar.f13058c, -16777216);
            d dVar2 = d.this;
            dVar2.d = MantoDensityUtils.parseColor(dVar2.f14350i.d, -16777216);
            d dVar3 = d.this;
            a.h hVar2 = dVar3.f14350i;
            d.a(dVar3, hVar2.f13059e, hVar2.f13060f);
            for (int i2 = 0; i2 < d.this.f14347f.size(); i2++) {
                g gVar = d.this.f14347f.get(i2);
                a.i iVar = d.this.f14350i.f13061g.get(i2);
                try {
                    gVar.b = j.a(iVar.f13062c);
                    gVar.a = j.a(iVar.d);
                } catch (Exception e2) {
                    MantoLog.e(d.f14344j, e2.getMessage());
                }
                gVar.f14359i = iVar.b;
                gVar.f14358h = iVar.a;
                d dVar4 = d.this;
                dVar4.a(dVar4.a.getChildAt(i2), gVar);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class g {
        public Bitmap a;
        public Bitmap b;

        /* renamed from: c */
        public boolean f14354c = false;
        public boolean d;

        /* renamed from: e */
        public String f14355e;

        /* renamed from: f */
        public int f14356f;

        /* renamed from: g */
        public int f14357g;

        /* renamed from: h */
        public String f14358h;

        /* renamed from: i */
        public String f14359i;

        public g() {
            a();
        }

        public final void a() {
            this.d = false;
            this.f14355e = "";
            this.f14356f = 0;
            this.f14357g = -1;
        }
    }

    /* loaded from: classes16.dex */
    public interface h {
        void a(int i2, String str);
    }

    public d(Context context, a.h hVar, q qVar) {
        super(context);
        this.f14347f = new LinkedList<>();
        this.f14348g = -1;
        this.f14350i = hVar;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        ImageView imageView = new ImageView(context);
        this.b = imageView;
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.b);
        LinearLayout linearLayout = new LinearLayout(context);
        this.a = linearLayout;
        linearLayout.setOrientation(0);
        this.a.setGravity(17);
        this.a.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        addView(this.a);
        this.f14346e = MantoDensityUtils.parseColor(hVar.f13058c, -16777216);
        this.d = MantoDensityUtils.parseColor(hVar.d, -16777216);
        a(this, hVar.f13059e, hVar.f13060f);
        for (a.i iVar : hVar.f13061g) {
            g gVar = new g();
            try {
                gVar.b = j.a(iVar.f13062c);
                gVar.a = j.a(iVar.d);
            } catch (Exception e2) {
                MantoLog.e(f14344j, e2.getMessage());
            }
            gVar.f14359i = iVar.b;
            String str = iVar.a;
            gVar.f14358h = str;
            if (TextUtils.isEmpty(str)) {
                MantoLog.e(f14344j, "illegal data");
                return;
            }
            LinearLayout linearLayout2 = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.manto_tab_item_layout, (ViewGroup) this.a, false);
            linearLayout2.setGravity(17);
            a(linearLayout2, gVar);
            linearLayout2.setOnClickListener(new a(gVar));
            this.a.addView(linearLayout2);
            this.f14347f.add(gVar);
        }
    }

    public void a(d dVar, String str) {
        h hVar = dVar.f14349h;
        if (hVar != null) {
            hVar.a(a(str), str);
        }
    }

    static void a(d dVar, String str, String str2) {
        int i2;
        LayerDrawable layerDrawable;
        int i3;
        float dip2pixel = MantoDensityUtils.dip2pixel(dVar.getContext(), 1) / 2.0f;
        int i4 = dip2pixel > 1.0f ? (int) dip2pixel : 1;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(com.jingdong.manto.ui.d.a(str, -1));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(i4, Color.parseColor("white".equals(str2) ? "#33ffffff" : JDDarkUtil.COLOR_33000000));
        LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{gradientDrawable, gradientDrawable2});
        int i5 = -i4;
        if ("top".equals(dVar.f14345c)) {
            layerDrawable = layerDrawable2;
            i3 = i5;
            i2 = i5;
        } else {
            i2 = 0;
            layerDrawable = layerDrawable2;
            i3 = i5;
        }
        layerDrawable.setLayerInset(1, i3, i2, i5, i5);
        dVar.b.setImageDrawable(layerDrawable2);
    }

    public int a(String str) {
        for (int i2 = 0; i2 < this.f14350i.f13061g.size(); i2++) {
            a.i iVar = this.f14350i.f13061g.get(i2);
            MantoLog.i("", "indexOfTabUrl: " + iVar.a);
            if (TextUtils.equals(str, iVar.a)) {
                return i2;
            }
        }
        return -1;
    }

    public void a(int i2) {
        if (i2 == this.f14348g || i2 < 0 || i2 >= this.a.getChildCount()) {
            return;
        }
        int i3 = this.f14348g;
        if (i3 < 0) {
            i3 = 0;
        }
        View childAt = this.a.getChildAt(i3);
        View childAt2 = this.a.getChildAt(i2);
        this.f14347f.get(i3).f14354c = false;
        this.f14347f.get(i2).f14354c = true;
        a(childAt, this.f14347f.get(i3));
        a(childAt2, this.f14347f.get(i2));
        this.f14348g = i2;
    }

    public void a(int i2, g gVar) {
        post(new b(i2, gVar));
    }

    public final void a(Animator animator, Runnable runnable) {
        animator.addListener(new e(runnable));
        animator.start();
    }

    public void a(View view, g gVar) {
        float f2;
        Bitmap bitmap;
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_text);
        TextView textView2 = (TextView) view.findViewById(R.id.tab_item_badge);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.tab_item_red_dot);
        View findViewById = view.findViewById(R.id.tab_item_jon);
        if ("top".equals(this.f14345c)) {
            view.setLayoutParams(new LinearLayout.LayoutParams(0, MantoDensityUtils.dip2pixel(getContext(), 40), 1.0f));
            imageView.setVisibility(8);
            textView.setTextSize(1, 14.0f);
            if (gVar.f14354c) {
                findViewById.setBackgroundColor(this.d);
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(4);
            }
        } else {
            if (gVar.b != null && !TextUtils.isEmpty(gVar.f14359i)) {
                view.setLayoutParams(new LinearLayout.LayoutParams(0, MantoDensityUtils.dip2pixel(getContext(), 54), 1.0f));
                imageView.setVisibility(0);
                imageView.getLayoutParams().width = MantoDensityUtils.dip2pixel(getContext(), 32);
                imageView.getLayoutParams().height = MantoDensityUtils.dip2pixel(getContext(), 28);
                textView.setVisibility(0);
                f2 = 12.0f;
            } else if (gVar.b != null) {
                view.setLayoutParams(new LinearLayout.LayoutParams(0, MantoDensityUtils.dip2pixel(getContext(), 50), 1.0f));
                imageView.setVisibility(0);
                imageView.getLayoutParams().width = MantoDensityUtils.dip2pixel(getContext(), 32);
                imageView.getLayoutParams().height = MantoDensityUtils.dip2pixel(getContext(), 32);
                textView.setVisibility(8);
            } else {
                view.setLayoutParams(new LinearLayout.LayoutParams(0, MantoDensityUtils.dip2pixel(getContext(), 50), 1.0f));
                imageView.setVisibility(8);
                textView.setVisibility(0);
                f2 = 16.0f;
            }
            textView.setTextSize(1, f2);
        }
        if (TextUtils.isEmpty(gVar.f14355e)) {
            textView2.setVisibility(4);
        } else {
            textView2.setVisibility(0);
        }
        textView2.setText(gVar.f14355e);
        int i2 = gVar.f14357g;
        if (-1 != i2) {
            textView2.setTextColor(i2);
        }
        Drawable background = textView2.getBackground();
        if (background != null) {
            background.setColorFilter(gVar.f14356f, PorterDuff.Mode.SRC_ATOP);
        }
        if (gVar.d) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(4);
        }
        if (!gVar.f14354c || (bitmap = gVar.a) == null) {
            bitmap = gVar.b;
        }
        imageView.setImageBitmap(bitmap);
        textView.setText(gVar.f14359i);
        textView.setTextColor(gVar.f14354c ? this.d : this.f14346e);
    }

    public void a(a.h hVar) {
        post(new f(hVar));
    }

    public final void a(String str, String str2, String str3, String str4) {
        post(new RunnableC0692d(str, str2, str3, str4));
    }

    public void b() {
        post(new c());
    }

    public void b(d dVar, String str, String str2) {
        dVar.f14346e = com.jingdong.manto.ui.d.a(str, -16777216);
        dVar.d = com.jingdong.manto.ui.d.a(str2, -16777216);
    }
}
