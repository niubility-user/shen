package com.jingdong.app.mall.home.floor.view.special;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.view.special.a;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.TitleChangeLayout;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.e.j;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes4.dex */
public class MallFloorTitle extends BaseMallSpecialFloor<j> {
    private static JDDisplayImageOptions u;

    /* renamed from: i  reason: collision with root package name */
    private GradientTextView f9976i;

    /* renamed from: j  reason: collision with root package name */
    private SimpleDraweeView f9977j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f9978k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f9979l;

    /* renamed from: m  reason: collision with root package name */
    private SimpleDraweeView f9980m;

    /* renamed from: n  reason: collision with root package name */
    private j f9981n;
    private f o;
    private f p;
    private SimpleDraweeView q;
    private RelativeLayout r;
    private TitleChangeLayout s;
    private f t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FloorEntity f9982g;

        /* renamed from: com.jingdong.app.mall.home.floor.view.special.MallFloorTitle$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0301a implements a.d {
            final /* synthetic */ Context a;

            C0301a(Context context) {
                this.a = context;
            }

            @Override // com.jingdong.app.mall.home.floor.view.special.a.d
            public void onUserClose(String str) {
                h hVar;
                com.jingdong.app.mall.home.a.a(a.this.f9982g.getFloorId());
                JDHomeFragment z0 = JDHomeFragment.z0();
                d dVar = MallFloorTitle.this.f9974h;
                if (dVar == null || z0 == null || (hVar = dVar.mParentModel) == null) {
                    return;
                }
                hVar.c0 = false;
                HomeRecyclerAdapter t0 = z0.t0();
                if (t0 != null) {
                    t0.q(MallFloorTitle.this.f9974h);
                }
                String closeTips = a.this.f9982g.getCloseTips();
                if (TextUtils.isEmpty(closeTips)) {
                    CharSequence text = this.a.getResources().getText(R.string.home_feedback_reason_toast);
                    com.jingdong.app.mall.home.o.a.f.n(text);
                    closeTips = (String) text;
                }
                new com.jingdong.app.mall.home.q.a("\u8d1f\u53cd\u9988\u5173\u95ed", a.this.f9982g.getCloseLog()).b();
                ToastUtils.showToastInCenter(this.a, (byte) 2, closeTips, 0);
                String str2 = a.this.f9982g.getFloorId() + CartConstant.KEY_YB_INFO_LINK + str;
                Context context = this.a;
                JDMtaUtils.sendCommonData(context, "Home_NegativeFloorReason", str2, "", context, "", MallFloorTitle.this.getClass(), "", RecommendMtaUtils.Home_PageId);
            }
        }

        a(FloorEntity floorEntity) {
            this.f9982g = floorEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Context context = MallFloorTitle.this.getContext();
            if (context instanceof BaseActivity) {
                com.jingdong.app.mall.home.floor.view.special.a.f().g((BaseActivity) context, MallFloorTitle.this.r, this.f9982g.getCloseReason(), new C0301a(context));
                JDMtaUtils.sendCommonData(context, "Home_NegativeFloorClose", this.f9982g.getFloorId(), "", context, "", a.class, "", RecommendMtaUtils.Home_PageId);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f9984g;

        b(boolean z) {
            this.f9984g = z;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
            if (MallFloorTitle.this.f9977j.getDrawable() == null) {
                view.setVisibility(8);
                MallFloorTitle.this.f9976i.setVisibility(0);
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            view.setVisibility(0);
            if (this.f9984g) {
                MallFloorTitle.this.f9976i.setVisibility(0);
                MallFloorTitle.this.f9976i.bringToFront();
            } else {
                MallFloorTitle.this.f9976i.setVisibility(8);
                view.bringToFront();
            }
            view.requestLayout();
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            view.setVisibility(8);
            MallFloorTitle.this.f9976i.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f9986g;

        c(h hVar) {
            this.f9986g = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JumpEntity jump;
            if (l.k() || (jump = this.f9986g.getJump()) == null) {
                return;
            }
            com.jingdong.app.mall.home.r.c.a.s("Home_TopRight", "", com.jingdong.app.mall.home.r.c.b.c(jump.getSrvJson()).toString());
            l.e(MallFloorTitle.this.getContext(), jump);
        }
    }

    public MallFloorTitle(Context context) {
        super(context);
        this.o = new f(24, 24);
        this.p = new f(28, 28);
        i();
    }

    private int f(FloorEntity floorEntity) {
        if (StringUtil.isEmpty(floorEntity.getRightCornerArrowImgUrl())) {
            return getContext().getResources().getColor(floorEntity.getRightCornerTextColorResValue());
        }
        return floorEntity.getRightCornerTextColor();
    }

    private void g() {
        TitleChangeLayout titleChangeLayout = this.s;
        if (titleChangeLayout != null) {
            titleChangeLayout.v();
            ViewParent parent = this.s.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.s);
            }
            this.s = null;
        }
    }

    private void h() {
        SimpleDraweeView simpleDraweeView = this.q;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.r;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    private void i() {
        setGravity(16);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        GradientTextView gradientTextView = new GradientTextView(getContext());
        this.f9976i = gradientTextView;
        gradientTextView.setGravity(17);
        this.f9976i.setMaxLines(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        this.f9976i.setLayoutParams(layoutParams2);
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.f9977j = homeDraweeView;
        homeDraweeView.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.f9977j);
        relativeLayout.addView(this.f9976i);
        addView(relativeLayout, layoutParams);
        u = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false).showImageOnFail((Drawable) null).showImageOnLoading((Drawable) null).showImageForEmptyUri((Drawable) null);
    }

    private void k(h hVar, String str) {
        setOnClickListener(new c(hVar));
    }

    private void l(j jVar) {
        TitleChangeLayout titleChangeLayout = this.s;
        if (titleChangeLayout == null) {
            TitleChangeLayout titleChangeLayout2 = new TitleChangeLayout(getContext());
            this.s = titleChangeLayout2;
            titleChangeLayout2.setGravity(16);
            this.t = new f(130, -1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.t.v(), this.t.h());
            layoutParams.addRule(11);
            int i2 = jVar.mParentModel.d;
            layoutParams.rightMargin = i2 == 0 ? com.jingdong.app.mall.home.floor.common.d.d(i2) : 0;
            addView(this.s, layoutParams);
        } else {
            titleChangeLayout.o();
            this.s.setVisibility(0);
            f.c(this.s, this.t);
        }
        this.s.r(jVar);
    }

    private void m(j jVar) {
        FloorEntity floorEntity = jVar.o;
        if (this.r == null) {
            this.r = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams.addRule(11);
            this.r.setLayoutParams(layoutParams);
            this.r.setPadding(com.jingdong.app.mall.home.floor.common.d.d(10), 0, com.jingdong.app.mall.home.floor.common.d.d(10), 0);
            this.q = new HomeDraweeView(getContext());
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.p.v(), this.p.h());
            layoutParams2.addRule(15);
            this.r.addView(this.q, layoutParams2);
            addView(this.r);
        } else {
            this.q.setVisibility(0);
            this.r.setVisibility(0);
            f.c(this.q, this.p);
        }
        String closeButtonImg = floorEntity.getCloseButtonImg();
        SimpleDraweeView simpleDraweeView = this.q;
        JDDisplayImageOptions a2 = com.jingdong.app.mall.home.floor.ctrl.f.a();
        int i2 = R.drawable.home_floor_close_button;
        com.jingdong.app.mall.home.floor.ctrl.f.e(closeButtonImg, simpleDraweeView, a2.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
        this.r.setOnClickListener(new a(floorEntity));
    }

    private void n(FloorEntity floorEntity) {
        int layoutLeftRightMargin = floorEntity.getLayoutLeftRightMargin();
        if (getPaddingLeft() == layoutLeftRightMargin && getPaddingRight() == layoutLeftRightMargin) {
            return;
        }
        setPadding(layoutLeftRightMargin, 0, layoutLeftRightMargin, 0);
    }

    private void o(j jVar) {
        FloorEntity floorEntity = jVar.o;
        if (this.f9978k == null) {
            this.f9978k = new LinearLayout(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            this.f9978k.setLayoutParams(layoutParams);
            TextView textView = new TextView(getContext());
            this.f9979l = textView;
            textView.setGravity(17);
            this.f9979l.setMaxLines(1);
            this.f9979l.setTextColor(getResources().getColor(R.color.c_8B8B8B));
            this.f9978k.addView(this.f9979l);
            this.f9980m = new HomeDraweeView(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.o.v(), this.o.h());
            layoutParams2.gravity = 16;
            this.f9980m.setLayoutParams(layoutParams2);
            this.f9978k.addView(this.f9980m);
            addView(this.f9978k);
        } else {
            f.c(this.f9980m, this.o);
        }
        this.f9978k.setPadding(0, 0, com.jingdong.app.mall.home.floor.common.d.d(10), 0);
        this.f9979l.setPadding(com.jingdong.app.mall.home.floor.common.d.d(10), 0, com.jingdong.app.mall.home.floor.common.d.d(10), 0);
        k(jVar.mParentModel, "Home_TopRight");
        this.f9979l.getLayoutParams().height = floorEntity.getTitleCenterHeight();
        String rightCornerText = floorEntity.getRightCornerText();
        this.f9979l.setText(rightCornerText);
        this.f9979l.setContentDescription(rightCornerText);
        this.f9979l.setTextColor(f(floorEntity));
        this.f9979l.setTextSize(DPIUtil.px2dip(floorEntity.getRightCornerTextSizePx()));
        String rightCornerArrowImgUrl = floorEntity.getRightCornerArrowImgUrl();
        SimpleDraweeView simpleDraweeView = this.f9980m;
        JDDisplayImageOptions a2 = com.jingdong.app.mall.home.floor.ctrl.f.a();
        int i2 = R.drawable.home_title_arrow_def;
        com.jingdong.app.mall.home.floor.ctrl.f.e(rightCornerArrowImgUrl, simpleDraweeView, a2.showImageOnFail(i2).showImageOnLoading(i2));
    }

    private void p(FloorEntity floorEntity) {
        if (floorEntity == null) {
            return;
        }
        this.f9976i.setText(floorEntity.getTitleText());
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftToRight;
        if (floorEntity.isSeparationTitle()) {
            gradientType = GradientTextView.GradientType.LeftTopToRightBottom;
        }
        this.f9976i.setTextGradient(gradientType, floorEntity.getTitleTextColor());
        this.f9976i.setTextSize(0, floorEntity.getTitleTextSizePx());
        Point titleTextPadding = floorEntity.getTitleTextPadding();
        GradientTextView gradientTextView = this.f9976i;
        int i2 = titleTextPadding.x;
        int i3 = titleTextPadding.y;
        gradientTextView.setPadding(i2, i3, i2, i3);
        this.f9977j.setScaleType(floorEntity.getLayoutInnerWidth() < floorEntity.getLayoutWidth() ? ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_CENTER);
        Point titleImgSize = floorEntity.getTitleImgSize();
        this.f9977j.getLayoutParams().height = titleImgSize.y;
        this.f9977j.getLayoutParams().width = titleImgSize.x;
        this.f9977j.setVisibility(0);
        this.f9976i.setVisibility(0);
        this.f9976i.bringToFront();
        e.j(floorEntity.getTitleImgUrl(), this.f9977j, u, false, new b(floorEntity.isSeparationTitle()), null);
    }

    private void q(j jVar) {
        FloorEntity floorEntity;
        if (jVar == null || jVar.mParentModel == null || (floorEntity = jVar.o) == null) {
            return;
        }
        n(floorEntity);
        p(floorEntity);
        if (jVar.mParentModel.E != 3 && floorEntity.hasRightCorner()) {
            o(jVar);
            LinearLayout linearLayout = this.f9978k;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
        } else {
            LinearLayout linearLayout2 = this.f9978k;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            setOnClickListener(null);
        }
        if (floorEntity.hasCloseButton()) {
            m(jVar);
        } else {
            h();
        }
        if (jVar.mParentModel.E == 3) {
            l(jVar);
        } else {
            g();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        j jVar;
        Path path;
        Paint paint;
        if (!com.jingdong.app.mall.home.state.dark.a.h() && (jVar = this.f9981n) != null && (path = jVar.A) != null && (paint = jVar.s) != null) {
            canvas.drawPath(path, paint);
        }
        super.dispatchDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.special.BaseMallSpecialFloor
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public void b(j jVar) {
        q(jVar);
        this.f9981n = jVar;
        jVar.l();
        if (jVar.o.hasCloseButton()) {
            h hVar = jVar.mParentModel;
            com.jingdong.app.mall.home.r.c.a.w(getContext(), "Home_NegativeCloseExpo", hVar != null ? hVar.A : "", "", RecommendMtaUtils.Home_PageId);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.special.BaseMallSpecialFloor, com.jingdong.app.mall.home.widget.b
    public void onViewRecycle() {
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        d dVar;
        if (layoutParams != null && (dVar = this.f9974h) != null) {
            layoutParams.height = dVar.getFloorHeight();
            layoutParams.width = -1;
        }
        super.setLayoutParams(layoutParams);
    }
}
