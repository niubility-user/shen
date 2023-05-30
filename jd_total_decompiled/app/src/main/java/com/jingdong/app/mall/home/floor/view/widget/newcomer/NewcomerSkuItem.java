package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class NewcomerSkuItem extends NewcomerBaseItem {

    /* renamed from: g  reason: collision with root package name */
    private Context f10290g;

    /* renamed from: h  reason: collision with root package name */
    private NewcomerFloorEntity.NewcomerSkuModel f10291h;

    /* renamed from: i  reason: collision with root package name */
    private DarkWhiteBgImageView f10292i;

    /* renamed from: j  reason: collision with root package name */
    private SkuLabel f10293j;

    /* renamed from: k  reason: collision with root package name */
    private final f f10294k;

    /* renamed from: l  reason: collision with root package name */
    private final f f10295l;

    /* loaded from: classes4.dex */
    public class SkuLabel extends GradientTextView {

        /* renamed from: g  reason: collision with root package name */
        private NinePatch f10296g;

        /* renamed from: h  reason: collision with root package name */
        private int f10297h;

        /* renamed from: i  reason: collision with root package name */
        private String f10298i;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class a extends com.jingdong.app.mall.home.t.a {
            final /* synthetic */ String a;

            a(String str) {
                this.a = str;
            }

            @Override // com.jingdong.app.mall.home.t.a
            public void onBitmapWithUiNull(Bitmap bitmap) {
                if (bitmap == null) {
                    SkuLabel.this.f();
                } else {
                    SkuLabel.this.e(bitmap, this.a);
                }
            }
        }

        public SkuLabel(NewcomerSkuItem newcomerSkuItem, Context context) {
            super(context);
        }

        private void c(Canvas canvas) {
            NinePatch ninePatch = this.f10296g;
            if (ninePatch != null) {
                ninePatch.draw(canvas, new Rect(0, 0, getWidth(), getHeight()));
            }
        }

        private boolean d(String str) {
            return TextUtils.equals(str, this.f10298i) && this.f10297h == getHeight();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(Bitmap bitmap, String str) {
            int d = d.d(34);
            Bitmap x = e.x(bitmap, d);
            byte[] y = e.y(x, 0.5f);
            if (y != null) {
                this.f10296g = new NinePatch(x, y, null);
                this.f10297h = d;
                this.f10298i = str;
            } else {
                this.f10297h = 0;
                this.f10296g = null;
                this.f10298i = null;
            }
            postInvalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            this.f10296g = null;
            this.f10298i = null;
            this.f10297h = 0;
            postInvalidate();
        }

        public void g(String str) {
            if (d(str)) {
                return;
            }
            String b = com.jingdong.app.mall.home.m.a.b(str);
            if (!TextUtils.isEmpty(b) && !b.toLowerCase().endsWith(".gif")) {
                com.jingdong.app.mall.home.floor.ctrl.f.i(b, new a(b));
            } else {
                f();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.view.view.GradientTextView, android.widget.TextView, android.view.View
        public void onDraw(Canvas canvas) {
            c(canvas);
            super.onDraw(canvas);
        }
    }

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NewcomerFloorEntity.onItemClick(NewcomerSkuItem.this.f10290g, NewcomerSkuItem.this.f10291h);
        }
    }

    public NewcomerSkuItem(Context context) {
        super(context);
        this.f10294k = new f(-1, -1);
        this.f10295l = new f(-2, 34);
        this.f10290g = context;
    }

    private void d() {
        if (TextUtils.isEmpty(this.f10291h.skuText)) {
            c.k(true, this.f10293j);
            return;
        }
        c.k(false, this.f10293j);
        this.f10295l.F(new Rect(0, 0, 0, 8));
        this.f10295l.K(new Rect(12, 0, 12, 0));
        SkuLabel skuLabel = this.f10293j;
        if (skuLabel == null) {
            SkuLabel skuLabel2 = new SkuLabel(this, this.f10290g);
            this.f10293j = skuLabel2;
            skuLabel2.setIncludeFontPadding(false);
            this.f10293j.setMaxLines(1);
            this.f10293j.setGravity(17);
            RelativeLayout.LayoutParams u = this.f10295l.u(this.f10293j);
            u.addRule(14);
            u.addRule(12);
            addView(this.f10293j, u);
        } else {
            f.c(skuLabel, this.f10295l);
        }
        this.f10293j.setMaxWidth(d.d(R2.anim.pickerview_dialog_scale_in));
        this.f10293j.setTextGradient(GradientTextView.GradientType.LeftToRight, this.f10291h.textColor);
        g.o(this.f10293j, 22);
        g.k(this.f10293j, true);
        SkuLabel skuLabel3 = this.f10293j;
        skuLabel3.setText(com.jingdong.app.mall.home.o.a.f.l(skuLabel3, d.d(R2.anim.pickerview_dialog_scale_in), this.f10291h.skuText));
        this.f10293j.g(this.f10291h.skuTagImg);
    }

    private void e() {
        DarkWhiteBgImageView darkWhiteBgImageView = this.f10292i;
        if (darkWhiteBgImageView == null) {
            DarkWhiteBgImageView darkWhiteBgImageView2 = new DarkWhiteBgImageView(this.f10290g);
            this.f10292i = darkWhiteBgImageView2;
            darkWhiteBgImageView2.c(true);
            this.f10292i.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f10292i.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = this.f10294k.u(this.f10292i);
            u.addRule(13);
            addView(this.f10292i, u);
        } else {
            f.c(darkWhiteBgImageView, this.f10294k);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.f10292i, d.d(8));
        e.u(this.f10292i, this.f10291h.img);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.newcomer.NewcomerBaseItem
    public void a(NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel) {
        if (newcomerBaseModel == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(newcomerBaseModel);
        this.f10291h = (NewcomerFloorEntity.NewcomerSkuModel) newcomerBaseModel;
        e();
        d();
        setOnClickListener(new a());
    }
}
