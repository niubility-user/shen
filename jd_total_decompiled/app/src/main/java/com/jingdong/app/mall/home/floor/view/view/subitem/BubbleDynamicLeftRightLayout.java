package com.jingdong.app.mall.home.floor.view.view.subitem;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.BubbleDynamicEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class BubbleDynamicLeftRightLayout extends RelativeLayout {
    private SimpleDraweeView bgIv;
    private final f bgSize;
    private com.jingdong.app.mall.home.r.e.f mElement;
    private BubbleDynamicEntity mEntity;
    private int mIndex;
    private int[] mSkuSize;
    private int[] mTextSize;
    private RelativeLayout skuContainer;
    private SimpleDraweeView skuIv;

    /* renamed from: tv  reason: collision with root package name */
    private GradientTextView f9992tv;
    private f tvSize;

    public BubbleDynamicLeftRightLayout(Context context) {
        super(context);
        this.bgSize = new f(-1, -1);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicLeftRightLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleDynamicLeftRightLayout.this.onItemClick(0);
            }
        });
    }

    private void bindBg(String str) {
        SimpleDraweeView simpleDraweeView = this.bgIv;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.bgIv = homeDraweeView;
            homeDraweeView.setContentDescription(getContext().getString(R.string.home_obstacle_free));
            this.bgIv.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.bgIv, this.bgSize.u(this.bgIv));
        } else {
            f.c(simpleDraweeView, this.bgSize);
        }
        setBackgroundColor(e.a);
        e.p(this.bgIv, str, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicLeftRightLayout.2
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str2, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str2, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str2, View view) {
                BubbleDynamicLeftRightLayout.this.setBackgroundColor(0);
            }
        });
    }

    private void bindSku(boolean z) {
        Rect rect;
        int[] iArr = this.mSkuSize;
        f fVar = new f(iArr[0], iArr[1]);
        if (isLeft()) {
            int[] iArr2 = this.mSkuSize;
            rect = new Rect(iArr2[3], iArr2[2], 0, 0);
        } else {
            int[] iArr3 = this.mSkuSize;
            rect = new Rect(0, iArr3[2], iArr3[3], 0);
        }
        fVar.F(rect);
        RelativeLayout relativeLayout = this.skuContainer;
        if (relativeLayout == null) {
            RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
            this.skuContainer = relativeLayout2;
            relativeLayout2.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = fVar.u(this.skuContainer);
            u.addRule(isLeft() ? 9 : 11);
            addView(this.skuContainer, u);
        } else {
            f.d(relativeLayout, fVar, true);
        }
        if (z) {
            this.skuContainer.setVisibility(8);
            return;
        }
        this.skuContainer.setVisibility(0);
        com.jingdong.app.mall.home.n.h.e.d(this.skuContainer, d.d(this.mEntity.getLrSkuRadius()));
        int[] iArr4 = this.mSkuSize;
        f fVar2 = new f(iArr4[0], iArr4[1]);
        SimpleDraweeView simpleDraweeView = this.skuIv;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.skuIv = homeDraweeView;
            homeDraweeView.setContentDescription(getContext().getString(R.string.home_obstacle_free));
            this.skuIv.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u2 = fVar2.u(this.skuIv);
            u2.addRule(13);
            this.skuContainer.addView(this.skuIv, u2);
        } else {
            f.d(simpleDraweeView, fVar2, true);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.skuIv, d.d(this.mEntity.getLrSkuRadius()));
        this.skuContainer.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicLeftRightLayout.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleDynamicLeftRightLayout.this.onItemClick(1);
            }
        });
        e.l(this.mElement.u(), this.skuIv);
    }

    private void bindText(boolean z) {
        if (z) {
            GradientTextView gradientTextView = this.f9992tv;
            if (gradientTextView != null) {
                gradientTextView.setVisibility(8);
                return;
            }
            return;
        }
        int[] iArr = this.mTextSize;
        int min = Math.min(iArr[1], this.mEntity.floorHeight - iArr[1]);
        int i2 = this.mTextSize[1] - min;
        f fVar = new f(-2, min * 2);
        this.tvSize = fVar;
        fVar.F(new Rect(0, i2, 0, 0));
        GradientTextView gradientTextView2 = this.f9992tv;
        if (gradientTextView2 == null) {
            GradientTextView gradientTextView3 = new GradientTextView(getContext());
            this.f9992tv = gradientTextView3;
            gradientTextView3.setFocusable(false);
            this.f9992tv.getPaint().setFakeBoldText(true);
            this.f9992tv.setBackgroundColor(0);
            this.f9992tv.setPadding(0, -3, 0, -3);
            RelativeLayout.LayoutParams u = this.tvSize.u(this.f9992tv);
            u.addRule(isLeft() ? 9 : 11);
            addView(this.f9992tv, u);
            this.f9992tv.setVisibility(4);
        } else {
            f.d(gradientTextView2, this.tvSize, true);
        }
        this.f9992tv.setTextSize(0, d.d(this.mTextSize[0]));
        if (this.mElement.getJsonInt("showNameSwitch", 0) == 2) {
            this.f9992tv.setSingleLine(false);
            this.f9992tv.setMaxLines(2);
            this.f9992tv.setLineSpacing(0.0f, 0.8f);
            this.f9992tv.setGravity(17);
            GradientTextView gradientTextView4 = this.f9992tv;
            double measureText = gradientTextView4.getPaint().measureText("\u5bbd");
            Double.isNaN(measureText);
            gradientTextView4.setMaxWidth((int) (measureText * 2.5d));
            this.f9992tv.setEllipsize(null);
        } else {
            this.f9992tv.setSingleLine(true);
            this.f9992tv.setLineSpacing(0.0f, 1.0f);
            this.f9992tv.setGravity(16);
            GradientTextView gradientTextView5 = this.f9992tv;
            double measureText2 = gradientTextView5.getPaint().measureText("\u5bbd");
            Double.isNaN(measureText2);
            gradientTextView5.setMaxWidth((int) (measureText2 * 5.5d));
            this.f9992tv.setEllipsize(TextUtils.TruncateAt.END);
        }
        this.f9992tv.setText(this.mElement.O());
        this.f9992tv.setTextGradient(GradientTextView.GradientType.LeftToRight, m.p(this.mElement.C(), -16777216, true));
        int tvMeasuredWidth = getTvMeasuredWidth(this.f9992tv);
        ViewGroup.LayoutParams layoutParams = this.f9992tv.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2 != null) {
            if (isLeft()) {
                layoutParams2.leftMargin = d.d(this.mTextSize[2]) - (tvMeasuredWidth / 2);
            } else {
                layoutParams2.rightMargin = d.d(this.mTextSize[2]) - (tvMeasuredWidth / 2);
            }
            this.f9992tv.setLayoutParams(layoutParams2);
        }
        this.f9992tv.setVisibility(0);
    }

    private int getTvMeasuredWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return textView.getMeasuredWidth();
    }

    private boolean isLeft() {
        return this.mIndex == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(int i2) {
        com.jingdong.app.mall.home.r.e.f fVar;
        JumpEntity jump;
        if (l.k() || (fVar = this.mElement) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        b c2 = b.c(jump.srvJson);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(getContext(), jump, "", jump.getSrv(), c2.toString(), i2);
    }

    public void bindData(int i2, BubbleDynamicEntity bubbleDynamicEntity) {
        this.mIndex = i2;
        this.mEntity = bubbleDynamicEntity;
        this.mElement = isLeft() ? bubbleDynamicEntity.leftElement : bubbleDynamicEntity.rightElement;
        this.mSkuSize = isLeft() ? bubbleDynamicEntity.lSkuSize : bubbleDynamicEntity.rSkuSize;
        this.mTextSize = isLeft() ? bubbleDynamicEntity.lTextSize : bubbleDynamicEntity.rTextSize;
        boolean z = this.mElement.t() == 0;
        bindSku(z);
        com.jingdong.app.mall.home.r.e.f fVar = this.mElement;
        bindBg(z ? fVar.u() : fVar.G());
        bindText(z);
    }
}
