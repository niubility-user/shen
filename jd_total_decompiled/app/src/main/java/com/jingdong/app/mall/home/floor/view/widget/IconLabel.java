package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class IconLabel extends RelativeLayout {

    /* renamed from: k */
    private static final ConcurrentHashMap<String, d> f10090k = new ConcurrentHashMap<>();

    /* renamed from: l */
    private static final int f10091l = R.drawable.home_icon_label_bg;

    /* renamed from: g */
    private final Paint f10092g;

    /* renamed from: h */
    private final f f10093h;

    /* renamed from: i */
    private TextView f10094i;

    /* renamed from: j */
    private NinePatch f10095j;

    /* loaded from: classes4.dex */
    public class a implements c {
        final /* synthetic */ String a;

        a(String str) {
            IconLabel.this = r1;
            this.a = str;
        }

        @Override // com.jingdong.app.mall.home.floor.view.widget.IconLabel.c
        public void onFail() {
            if (TextUtils.equals(this.a, "localHomeLabelBg")) {
                IconLabel.this.h();
            } else {
                IconLabel.this.g("localHomeLabelBg");
            }
        }

        @Override // com.jingdong.app.mall.home.floor.view.widget.IconLabel.c
        public void onSuccess(Bitmap bitmap) {
            IconLabel.this.i(this.a, bitmap);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ c a;

        b(IconLabel iconLabel, c cVar) {
            this.a = cVar;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                this.a.onSuccess(bitmap);
            } else {
                this.a.onFail();
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void onFail();

        void onSuccess(Bitmap bitmap);
    }

    /* loaded from: classes4.dex */
    public static class d {
        public NinePatch a;
        private final int b = com.jingdong.app.mall.home.floor.common.d.f9279g;

        public d(NinePatch ninePatch) {
            this.a = ninePatch;
        }

        public boolean a() {
            return com.jingdong.app.mall.home.floor.common.d.f9279g == this.b && this.a != null;
        }
    }

    public IconLabel(Context context) {
        super(context);
        this.f10092g = new Paint(1);
        this.f10093h = new f(-2, -1);
        this.f10095j = null;
    }

    private void d(String str, int i2, int i3, int i4) {
        TextView textView = this.f10094i;
        if (textView == null) {
            g gVar = new g(getContext(), false);
            gVar.f(1);
            gVar.d(17);
            TextView a2 = gVar.a();
            this.f10094i = a2;
            RelativeLayout.LayoutParams u = this.f10093h.u(a2);
            u.addRule(13);
            addView(this.f10094i, u);
        } else {
            f.d(textView, this.f10093h, true);
        }
        this.f10094i.setTypeface(FontsUtil.getTypeFace(getContext()));
        this.f10094i.setTextColor(i3);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(6);
        this.f10094i.setPadding(d2, 0, d2, 0);
        float w = com.jingdong.app.mall.home.o.a.f.w(com.jingdong.app.mall.home.floor.common.d.d(Math.max(Math.min(28, i2), 15)), com.jingdong.app.mall.home.floor.common.d.d(15), i4 - (d2 * 2), str);
        if (w <= 0.0f) {
            setVisibility(8);
            return;
        }
        this.f10094i.setTextSize(0, w);
        this.f10094i.setText(str);
    }

    private void e(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        NinePatch ninePatch = this.f10095j;
        if (ninePatch != null) {
            ninePatch.draw(canvas, new Rect(0, 0, width, height), this.f10092g);
        }
    }

    private void f(String str, c cVar) {
        if (TextUtils.isEmpty(str) || cVar == null) {
            return;
        }
        if (TextUtils.equals(str, "localHomeLabelBg")) {
            Bitmap c2 = com.jingdong.app.mall.home.floor.ctrl.f.c(getContext().getResources(), f10091l);
            if (c2 != null && !c2.isRecycled()) {
                cVar.onSuccess(c2);
                return;
            } else {
                cVar.onFail();
                return;
            }
        }
        com.jingdong.app.mall.home.floor.ctrl.f.i(str, new b(this, cVar));
    }

    public void h() {
        this.f10095j = null;
        postInvalidate();
        setVisibility(8);
    }

    public void i(String str, Bitmap bitmap) {
        Bitmap x = e.x(bitmap, com.jingdong.app.mall.home.floor.common.d.d(28));
        byte[] y = e.y(x, 0.5f);
        if (y != null) {
            NinePatch ninePatch = new NinePatch(x, y, null);
            this.f10095j = ninePatch;
            f10090k.put(str, new d(ninePatch));
            postInvalidate();
            return;
        }
        h();
    }

    public void c(String str, String str2, int i2, int i3, int i4) {
        if (TextUtils.isEmpty(str)) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        d(str, i2, i3, i4);
        g(str2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            e(canvas);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0("IconLabel", e2);
        }
        super.dispatchDraw(canvas);
    }

    public void g(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "localHomeLabelBg";
        }
        ConcurrentHashMap<String, d> concurrentHashMap = f10090k;
        d dVar = concurrentHashMap.get(str);
        if (dVar != null && dVar.a()) {
            this.f10095j = dVar.a;
            postInvalidate();
            return;
        }
        concurrentHashMap.remove(str);
        f(str, new a(str));
    }
}
