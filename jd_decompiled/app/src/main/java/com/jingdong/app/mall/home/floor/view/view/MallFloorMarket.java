package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.MarketFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.r.f.a.u;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorMarket extends BaseMallFloor<u> {
    private LinearLayout mBenefitLayout;
    private f mBenefitLayoutSize;
    private GradientTextView mBenefitText;
    private com.jingdong.app.mall.home.r.e.f mBindData;
    protected List<SkuLayout> mCacheViewList;
    private TimeFormatView mDateView;
    private SimpleDraweeView mFloorBg;
    private SimpleDraweeView mIcon;
    private RelativeLayout mLeftLayout;
    private f mLeftLayoutSize;
    private LinearLayout mSkuContent;
    private f mSkuContentSize;
    private LinearLayout mTimeLayout;
    private f mTimeLayoutSize;
    private TextView mTimeText;

    /* loaded from: classes4.dex */
    public static class SkuLayout extends RelativeLayout {
        private MarketFloorEntity.MarketSkuItem bindModel;
        private TextView price;
        private SimpleDraweeView priceBg;
        private f priceBgSize;
        private f priceSize;
        private SimpleDraweeView skuImg;
        private f skuImgSize;

        public SkuLayout(Context context) {
            super(context);
            this.skuImgSize = new f(R2.anim.miaosha_dropdown_in, R2.anim.miaosha_dropdown_in);
            this.priceBgSize = new f(R2.anim.miaosha_dropdown_in, 40);
            this.priceSize = new f(87, 38);
            e.d(this, d.d(12));
            setBackgroundColor(0);
        }

        private void bindPrice(String str) {
            TextView textView = this.price;
            if (textView == null) {
                TextView textView2 = new TextView(getContext());
                this.price = textView2;
                textView2.setMaxLines(1);
                this.price.setGravity(17);
                this.price.setEllipsize(TextUtils.TruncateAt.END);
                RelativeLayout.LayoutParams u = this.priceSize.u(this.price);
                u.addRule(11);
                u.addRule(12);
                addView(this.price, u);
            } else {
                f.d(textView, this.priceSize, true);
            }
            String str2 = this.bindModel.jdPrice;
            if (!TextUtils.isEmpty(str2)) {
                this.price.setVisibility(0);
                this.price.setTextColor(m.j(str, -1));
                this.price.setTypeface(FontsUtil.getTypeFace(getContext()));
                int d = d.d(87);
                SpannableString I = com.jingdong.app.mall.home.o.a.f.I(str2, 0.67f);
                this.price.setMaxWidth(d);
                com.jingdong.app.mall.home.o.a.f.I0(this.price, 30);
                if (com.jingdong.app.mall.home.o.a.f.T(this.price, I) > d) {
                    com.jingdong.app.mall.home.o.a.f.I0(this.price, 26);
                    this.price.setText(com.jingdong.app.mall.home.o.a.f.I(str2, 0.61f));
                    return;
                }
                this.price.setText(I);
                return;
            }
            this.price.setVisibility(8);
        }

        private void bindPriceContent(String str) {
            SimpleDraweeView simpleDraweeView = this.priceBg;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.priceBg = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = this.priceBgSize.u(this.priceBg);
                u.addRule(12);
                addView(this.priceBg, u);
            } else {
                f.d(simpleDraweeView, this.priceBgSize, true);
            }
            com.jingdong.app.mall.home.floor.ctrl.e.m(this.priceBg, str, new ColorDrawable(-28897));
        }

        private void bindSkuImg() {
            SimpleDraweeView simpleDraweeView = this.skuImg;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.skuImg = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                this.skuImg.setBackgroundColor(-1);
                addView(this.skuImg, this.skuImgSize.u(this.skuImg));
            } else {
                f.d(simpleDraweeView, this.skuImgSize, true);
            }
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.skuImg, this.bindModel.img);
        }

        public void bind(MarketFloorEntity.MarketSkuItem marketSkuItem, com.jingdong.app.mall.home.r.e.f fVar) {
            this.bindModel = marketSkuItem;
            bindSkuImg();
            bindPriceContent(fVar.z());
            bindPrice(fVar.K());
        }
    }

    public MallFloorMarket(Context context) {
        super(context);
        this.mSkuContentSize = new f(-2, -1);
        this.mLeftLayoutSize = new f(224, -1);
        this.mTimeLayoutSize = new f(-2, 40);
        this.mBenefitLayoutSize = new f(-2, 40);
        this.mCacheViewList = new ArrayList();
        setBackgroundColor(0);
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorMarket.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloorEntity floorEntity = ((BaseMallColorFloor) MallFloorMarket.this).mFloorBindElement.o;
                com.jingdong.app.mall.home.o.a.f.n(floorEntity);
                ((MarketFloorEntity) floorEntity).onSkuClick(((BaseMallColorFloor) MallFloorMarket.this).mContext, 0);
            }
        });
    }

    private Drawable getDefaultColorBg() {
        float[] r = ((u) this.mPresenter).r();
        float[] fArr = {r[0], r[0], r[1], r[1], r[2], r[2], r[3], r[3]};
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-3148851);
        gradientDrawable.setCornerRadii(fArr);
        gradientDrawable.setStroke(d.d(4), -7942519);
        return gradientDrawable;
    }

    private void initBenefitContent() {
        String i0 = this.mBindData.i0();
        f fVar = new f(20, 20);
        SimpleDraweeView simpleDraweeView = this.mIcon;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mIcon = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            fVar.E(0, 10, 7, 0);
            LinearLayout linearLayout = this.mBenefitLayout;
            SimpleDraweeView simpleDraweeView2 = this.mIcon;
            linearLayout.addView(simpleDraweeView2, 0, fVar.i(simpleDraweeView2));
        } else {
            f.d(simpleDraweeView, fVar, true);
        }
        this.mIcon.setVisibility(0);
        com.jingdong.app.mall.home.floor.ctrl.e.s(this.mIcon, i0, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorMarket.2
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
                MallFloorMarket.this.mIcon.setVisibility(8);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                MallFloorMarket.this.mIcon.setVisibility(8);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        });
        f fVar2 = new f(-2, 40);
        GradientTextView gradientTextView = this.mBenefitText;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.mContext);
            this.mBenefitText = gradientTextView2;
            gradientTextView2.setIncludeFontPadding(false);
            this.mBenefitText.setMaxLines(1);
            this.mBenefitText.setGravity(17);
            this.mBenefitText.setEllipsize(TextUtils.TruncateAt.END);
            LinearLayout linearLayout2 = this.mBenefitLayout;
            GradientTextView gradientTextView3 = this.mBenefitText;
            linearLayout2.addView(gradientTextView3, fVar2.i(gradientTextView3));
        } else {
            f.d(gradientTextView, fVar2, true);
        }
        String W = this.mBindData.W();
        if (!TextUtils.isEmpty(W)) {
            this.mBenefitText.setVisibility(0);
            this.mBenefitText.setMaxWidth(d.d(140));
            this.mBenefitText.setTypeface(FontsUtil.getTypeFace(this.mContext));
            this.mBenefitText.setText(W);
            this.mBenefitText.setTextGradient(GradientTextView.GradientType.LeftToRight, m.p(this.mBindData.Y(), -1, true));
            com.jingdong.app.mall.home.o.a.f.I0(this.mBenefitText, 22);
            return;
        }
        this.mBenefitText.setVisibility(8);
    }

    private void initFloorBg() {
        String d = this.mBindData.d();
        f fVar = new f(-1, 226);
        SimpleDraweeView simpleDraweeView = this.mFloorBg;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mFloorBg = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView2 = this.mFloorBg;
            addView(simpleDraweeView2, 0, fVar.u(simpleDraweeView2));
        } else {
            f.d(simpleDraweeView, fVar, true);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.mFloorBg, d, getDefaultColorBg());
    }

    private void initLeftContent() {
        this.mSkuContentSize.E(0, 0, 18, 0);
        LinearLayout linearLayout = this.mSkuContent;
        if (linearLayout == null) {
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            this.mSkuContent = linearLayout2;
            linearLayout2.setOrientation(0);
            this.mSkuContent.setPadding(0, 5, 0, 0);
            RelativeLayout.LayoutParams u = this.mSkuContentSize.u(this.mSkuContent);
            u.addRule(11);
            addView(this.mSkuContent, u);
        } else {
            f.c(linearLayout, this.mSkuContentSize);
        }
        RelativeLayout relativeLayout = this.mLeftLayout;
        if (relativeLayout == null) {
            RelativeLayout relativeLayout2 = new RelativeLayout(this.mContext);
            this.mLeftLayout = relativeLayout2;
            addView(relativeLayout2, this.mLeftLayoutSize.u(relativeLayout2));
        } else {
            f.c(relativeLayout, this.mLeftLayoutSize);
        }
        this.mTimeLayoutSize.E(0, 106, 0, 0);
        LinearLayout linearLayout3 = this.mTimeLayout;
        if (linearLayout3 == null) {
            LinearLayout linearLayout4 = new LinearLayout(this.mContext);
            this.mTimeLayout = linearLayout4;
            linearLayout4.setOrientation(0);
            RelativeLayout.LayoutParams u2 = this.mTimeLayoutSize.u(this.mTimeLayout);
            u2.addRule(14);
            this.mLeftLayout.addView(this.mTimeLayout, u2);
        } else {
            f.c(linearLayout3, this.mTimeLayoutSize);
        }
        this.mBenefitLayoutSize.E(0, R2.anim.pop_in, 0, 0);
        LinearLayout linearLayout5 = this.mBenefitLayout;
        if (linearLayout5 == null) {
            LinearLayout linearLayout6 = new LinearLayout(this.mContext);
            this.mBenefitLayout = linearLayout6;
            linearLayout6.setOrientation(0);
            RelativeLayout.LayoutParams u3 = this.mBenefitLayoutSize.u(this.mBenefitLayout);
            u3.addRule(14);
            this.mLeftLayout.addView(this.mBenefitLayout, u3);
            return;
        }
        f.c(linearLayout5, this.mBenefitLayoutSize);
    }

    private void initSkuContent() {
        SkuLayout skuLayout;
        List<MarketFloorEntity.MarketSkuItem> Q = ((u) this.mPresenter).Q();
        int size = Q.size();
        int size2 = this.mCacheViewList.size();
        boolean z = size == size2;
        f fVar = new f(R2.anim.miaosha_dropdown_in, R2.anim.push_right_out);
        fVar.E(6, 0, 6, 0);
        if (!z && size2 > 0) {
            cleanUIOnMainThread();
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            MarketFloorEntity.MarketSkuItem marketSkuItem = Q.get(i2);
            com.jingdong.app.mall.home.o.a.f.n(marketSkuItem);
            MarketFloorEntity.MarketSkuItem marketSkuItem2 = marketSkuItem;
            if (z) {
                skuLayout = this.mCacheViewList.get((size - i2) - 1);
                f.c(skuLayout, fVar);
            } else {
                skuLayout = new SkuLayout(this.mContext);
                LinearLayout.LayoutParams i3 = fVar.i(skuLayout);
                i3.gravity = 16;
                this.mCacheViewList.add(skuLayout);
                this.mSkuContent.addView(skuLayout, i3);
            }
            skuLayout.bind(marketSkuItem2, this.mBindData);
            final int i4 = i2 + 1;
            skuLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorMarket.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FloorEntity floorEntity = ((BaseMallColorFloor) MallFloorMarket.this).mFloorBindElement.o;
                    com.jingdong.app.mall.home.o.a.f.n(floorEntity);
                    ((MarketFloorEntity) floorEntity).onSkuClick(((BaseMallColorFloor) MallFloorMarket.this).mContext, i4);
                }
            });
        }
    }

    private void initTimeContent() {
        Typeface typeFace = FontsUtil.getTypeFace(this.mContext);
        int i2 = m.i(this.mBindData.h0(), -15431895);
        f fVar = new f(-2, -1);
        TextView textView = this.mTimeText;
        if (textView == null) {
            TextView textView2 = new TextView(this.mContext);
            this.mTimeText = textView2;
            textView2.setIncludeFontPadding(false);
            this.mTimeText.setSingleLine();
            this.mTimeText.setEllipsize(TextUtils.TruncateAt.END);
            this.mTimeText.setTypeface(typeFace);
            this.mTimeText.setGravity(17);
            LinearLayout linearLayout = this.mTimeLayout;
            TextView textView3 = this.mTimeText;
            linearLayout.addView(textView3, fVar.u(textView3));
        } else {
            f.c(textView, fVar);
        }
        this.mTimeText.setTextColor(i2);
        this.mTimeText.setTextSize(0, d.d(22));
        this.mTimeText.setText(com.jingdong.app.mall.home.o.a.f.j(4, this.mBindData.k0()));
        f fVar2 = new f(110, -1);
        fVar2.F(new Rect(3, 0, 0, 0));
        TimeFormatView timeFormatView = this.mDateView;
        if (timeFormatView == null) {
            TimeFormatView timeFormatView2 = new TimeFormatView(this.mContext);
            this.mDateView = timeFormatView2;
            timeFormatView2.n(typeFace);
            RelativeLayout.LayoutParams u = fVar2.u(this.mDateView);
            u.addRule(15);
            this.mTimeLayout.addView(this.mDateView, u);
        } else {
            f.c(timeFormatView, fVar2);
        }
        this.mDateView.setVisibility(0);
        this.mDateView.k(i2);
        this.mDateView.i(i2);
        this.mDateView.e(-1);
        this.mDateView.h(d.d(27));
        this.mDateView.g(d.d(26));
        this.mDateView.l(d.d(22));
        ((u) this.mPresenter).R(this, this.mBindData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void checkFloorClip(float[] fArr) {
        if (fArr == null || fArr.length < 4) {
            return;
        }
        e.d(this.mFloorBg, (int) fArr[0]);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void cleanUIOnMainThread() {
        LinearLayout linearLayout = this.mSkuContent;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        this.mCacheViewList.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        com.jingdong.app.mall.home.r.e.f P = ((u) this.mPresenter).P();
        this.mBindData = P;
        if (P == null) {
            return;
        }
        initLeftContent();
        initFloorBg();
        initTimeContent();
        initBenefitContent();
        initSkuContent();
    }

    public void setTimeEnd() {
        this.mDateView.setVisibility(8);
        this.mTimeText.setText(com.jingdong.app.mall.home.o.a.f.j(9, this.mBindData.i()));
    }

    public void updateTime(String str, String str2, String str3) {
        this.mDateView.m(str, str2, str3);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public u createPresenter() {
        return new u();
    }
}
