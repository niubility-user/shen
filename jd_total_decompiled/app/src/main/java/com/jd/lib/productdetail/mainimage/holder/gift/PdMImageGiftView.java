package com.jd.lib.productdetail.mainimage.holder.gift;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.CustomTypefaceSpan;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMImageGiftView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public AppCompatTextView f4835g;

    /* renamed from: h  reason: collision with root package name */
    public SimpleDraweeView f4836h;

    /* renamed from: i  reason: collision with root package name */
    public AppCompatTextView f4837i;

    /* renamed from: j  reason: collision with root package name */
    public AppCompatTextView f4838j;

    /* renamed from: k  reason: collision with root package name */
    public View f4839k;

    /* renamed from: l  reason: collision with root package name */
    public View f4840l;

    /* renamed from: m  reason: collision with root package name */
    public SimpleDraweeView f4841m;

    /* renamed from: n  reason: collision with root package name */
    public AppCompatTextView f4842n;
    public AppCompatTextView o;
    public View p;
    public View q;
    public View r;
    public View s;
    public View t;
    public LinearLayout u;
    public com.jd.lib.productdetail.mainimage.dialog.a v;
    public PdMainImagePresenter w;

    /* loaded from: classes15.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ View f4843g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ int f4844h;

        /* renamed from: i  reason: collision with root package name */
        public final /* synthetic */ boolean f4845i;

        public a(View view, int i2, boolean z) {
            this.f4843g = view;
            this.f4844h = i2;
            this.f4845i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            View view;
            LinearLayout.LayoutParams layoutParams;
            if (!(PdMImageGiftView.this.getContext() instanceof BaseActivity) || (view = this.f4843g) == null) {
                return;
            }
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.lib_pd_topimage_item_linear_img);
            PdMImageGiftView pdMImageGiftView = PdMImageGiftView.this;
            int i2 = pdMImageGiftView.w.appImageWidth;
            if (pdMImageGiftView.u == null || pdMImageGiftView.f4837i == null || pdMImageGiftView.f4838j == null) {
                return;
            }
            int dip2px = (((i2 - PDUtils.dip2px(117.0f)) - PdMImageGiftView.this.f4837i.getMeasuredHeight()) - PdMImageGiftView.this.f4838j.getMeasuredHeight()) - PdMImageGiftView.this.u.getMeasuredHeight();
            if (dip2px > PDUtils.dip2px(155.0f)) {
                if (this.f4844h != -1) {
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.f4844h, -2);
                    if (this.f4845i) {
                        layoutParams2.leftMargin = PDUtils.dip2px(4.0f);
                    }
                    ViewGroup.LayoutParams layoutParams3 = simpleDraweeView.getLayoutParams();
                    int i3 = this.f4844h;
                    layoutParams3.width = i3;
                    layoutParams3.height = i3;
                    simpleDraweeView.setLayoutParams(layoutParams3);
                    this.f4843g.setLayoutParams(layoutParams2);
                    return;
                }
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(PDUtils.dip2px(109.0f), PDUtils.dip2px(155.0f));
                if (this.f4845i) {
                    layoutParams4.leftMargin = PDUtils.dip2px(4.0f);
                }
                ViewGroup.LayoutParams layoutParams5 = simpleDraweeView.getLayoutParams();
                layoutParams5.width = PDUtils.dip2px(109.0f);
                layoutParams5.height = PDUtils.dip2px(109.0f);
                simpleDraweeView.setLayoutParams(layoutParams5);
                this.f4843g.setLayoutParams(layoutParams4);
                return;
            }
            PdMImageGiftView.this.f4837i.setTextSize(2, 16.0f);
            PdMImageGiftView.this.f4838j.setTextSize(2, 12.0f);
            int i4 = (int) (dip2px / 1.42f);
            if (this.f4844h != -1) {
                ViewGroup.LayoutParams layoutParams6 = simpleDraweeView.getLayoutParams();
                if (this.f4844h > i4) {
                    layoutParams = new LinearLayout.LayoutParams(i4, -2);
                    layoutParams6.width = i4;
                    layoutParams6.height = i4;
                } else {
                    layoutParams = new LinearLayout.LayoutParams(this.f4844h, -2);
                    int i5 = this.f4844h;
                    layoutParams6.width = i5;
                    layoutParams6.height = i5;
                }
                if (this.f4845i) {
                    layoutParams.leftMargin = PDUtils.dip2px(4.0f);
                }
                simpleDraweeView.setLayoutParams(layoutParams6);
                this.f4843g.setLayoutParams(layoutParams);
                return;
            }
            LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(i4, -2);
            if (this.f4845i) {
                layoutParams7.leftMargin = PDUtils.dip2px(4.0f);
            }
            layoutParams7.bottomMargin = PDUtils.dip2px(4.0f);
            ViewGroup.LayoutParams layoutParams8 = simpleDraweeView.getLayoutParams();
            layoutParams8.width = i4;
            layoutParams8.height = i4;
            simpleDraweeView.setLayoutParams(layoutParams8);
            this.f4843g.setLayoutParams(layoutParams7);
        }
    }

    public PdMImageGiftView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final View a(HeadPicGiftInfoEntity.GiftsEntity giftsEntity) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.lib_pd_mainimage_topimage_item_linear_item_layout, (ViewGroup) null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.lib_pd_topimage_item_linear_img);
        AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.lib_pd_topimage_item_linear_title);
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) inflate.findViewById(R.id.lib_pd_topimage_item_gift_num);
        if (giftsEntity != null) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            createSimple.setRoundingParams(RoundingParams.fromCornersRadii(PDUtils.dip2px(6.0f), PDUtils.dip2px(6.0f), 0.0f, 0.0f));
            JDImageLoader.display(giftsEntity.img, simpleDraweeView, createSimple);
            if (!TextUtils.isEmpty(giftsEntity.desc)) {
                appCompatTextView.setText(giftsEntity.desc);
            }
            if (!TextUtils.isEmpty(giftsEntity.num)) {
                String str = "\u9001x" + giftsEntity.num;
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new CustomTypefaceSpan("", FontsUtil.getTypeFace(getContext(), 4099)), str.length() - giftsEntity.num.length(), str.length(), 17);
                spannableString.setSpan(new RelativeSizeSpan(1.33f), str.length() - giftsEntity.num.length(), str.length(), 17);
                appCompatTextView2.setText(spannableString);
            }
        }
        return inflate;
    }

    public final void b(View view, int i2, boolean z) {
        if (view != null) {
            view.post(new a(view, i2, z));
        }
    }

    public final void c(HeadPicGiftInfoEntity.GiftsEntity giftsEntity, View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.lib_pd_topimage_item_gift_goods);
        this.f4841m = (SimpleDraweeView) view.findViewById(R.id.lib_pd_topimage_item_gift_img);
        this.f4842n = (AppCompatTextView) view.findViewById(R.id.lib_pd_topimage_item_gift_name);
        this.o = (AppCompatTextView) view.findViewById(R.id.lib_pd_topimage_item_gift_num);
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.setRoundingParams(RoundingParams.fromCornersRadii(PDUtils.dip2px(6.0f), 0.0f, 0.0f, PDUtils.dip2px(6.0f)));
        if (giftsEntity != null) {
            JDImageLoader.display(giftsEntity.img, this.f4841m, createSimple);
            if (!TextUtils.isEmpty(giftsEntity.desc)) {
                this.f4842n.setText(giftsEntity.desc);
            }
            if (TextUtils.isEmpty(giftsEntity.num)) {
                return;
            }
            String str = "\u9001x" + giftsEntity.num;
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new CustomTypefaceSpan("", FontsUtil.getTypeFace(getContext(), 4099)), str.length() - giftsEntity.num.length(), str.length(), 17);
            spannableString.setSpan(new RelativeSizeSpan(1.33f), str.length() - giftsEntity.num.length(), str.length(), 17);
            this.o.setText(spannableString);
        }
    }

    public void d(HeadPicGiftInfoEntity headPicGiftInfoEntity, PdMainImagePresenter pdMainImagePresenter) {
        this.w = pdMainImagePresenter;
        if (headPicGiftInfoEntity != null) {
            if (!TextUtils.isEmpty(headPicGiftInfoEntity.downText)) {
                this.f4835g.setText(headPicGiftInfoEntity.downText);
            } else {
                this.f4835g.setText(R.string.lib_pd_image_topimage_item_holder_comment_btn_ok_text);
            }
            if (!TextUtils.isEmpty(headPicGiftInfoEntity.titleOne)) {
                this.f4837i.setVisibility(0);
                this.f4837i.setText(headPicGiftInfoEntity.titleOne);
            } else {
                this.f4837i.setVisibility(8);
            }
            if (!TextUtils.isEmpty(headPicGiftInfoEntity.titleTwo)) {
                this.f4838j.setVisibility(0);
                this.f4838j.setText(headPicGiftInfoEntity.titleTwo);
            } else {
                this.f4838j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(headPicGiftInfoEntity.backGiftImg)) {
                this.f4836h.setVisibility(0);
                JDImageLoader.display(headPicGiftInfoEntity.backGiftImg, this.f4836h);
            } else {
                this.f4836h.setVisibility(8);
            }
            ArrayList<HeadPicGiftInfoEntity.GiftsEntity> arrayList = headPicGiftInfoEntity.giftPicShowList;
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            if (headPicGiftInfoEntity.giftPicShowList.size() > 3) {
                View view = this.f4839k;
                if (view == null || !(view instanceof ConstraintLayout)) {
                    return;
                }
                view.setVisibility(0);
                View view2 = this.f4839k;
                this.p = view2.findViewById(R.id.lib_pd_topimage_item_gift);
                this.q = view2.findViewById(R.id.lib_pd_topimage_item_gift2);
                this.s = view2.findViewById(R.id.lib_pd_topimage_item_gift4);
                this.r = view2.findViewById(R.id.lib_pd_topimage_item_gift3);
                HeadPicGiftInfoEntity.GiftsEntity giftsEntity = headPicGiftInfoEntity.giftPicShowList.get(0);
                if (giftsEntity != null) {
                    c(giftsEntity, this.p);
                }
                HeadPicGiftInfoEntity.GiftsEntity giftsEntity2 = headPicGiftInfoEntity.giftPicShowList.get(1);
                if (giftsEntity != null) {
                    c(giftsEntity2, this.q);
                }
                HeadPicGiftInfoEntity.GiftsEntity giftsEntity3 = headPicGiftInfoEntity.giftPicShowList.get(2);
                if (giftsEntity != null) {
                    c(giftsEntity3, this.r);
                }
                HeadPicGiftInfoEntity.GiftsEntity giftsEntity4 = headPicGiftInfoEntity.giftPicShowList.get(3);
                if (giftsEntity != null) {
                    c(giftsEntity4, this.s);
                    return;
                }
                return;
            }
            View view3 = this.f4840l;
            if (view3 == null || !(view3 instanceof LinearLayout)) {
                return;
            }
            view3.setVisibility(0);
            if (((LinearLayout) this.f4840l).getChildCount() > 0) {
                ((LinearLayout) this.f4840l).removeAllViews();
            }
            LinearLayout linearLayout = (LinearLayout) this.f4840l;
            ArrayList<HeadPicGiftInfoEntity.GiftsEntity> arrayList2 = headPicGiftInfoEntity.giftPicShowList;
            if (getContext() != null) {
                int size = arrayList2.size();
                if (size == 1) {
                    View a2 = a(arrayList2.get(0));
                    b(a2, -1, false);
                    linearLayout.addView(a2);
                } else if (size == 2) {
                    int dip2px = this.w.appImageWidth - PDUtils.dip2px(44.0f);
                    if (dip2px > PDUtils.dip2px(218.0f)) {
                        View a3 = a(arrayList2.get(0));
                        View a4 = a(arrayList2.get(1));
                        b(a3, -1, false);
                        b(a4, -1, true);
                        linearLayout.addView(a3);
                        linearLayout.addView(a4);
                        return;
                    }
                    int i2 = dip2px / 2;
                    View a5 = a(arrayList2.get(0));
                    View a6 = a(arrayList2.get(1));
                    b(a5, i2, false);
                    b(a6, i2, true);
                    linearLayout.addView(a5);
                    linearLayout.addView(a6);
                } else if (size == 3) {
                    int dip2px2 = this.w.appImageWidth - PDUtils.dip2px(48.0f);
                    if (dip2px2 > PDUtils.dip2px(327.0f)) {
                        View a7 = a(arrayList2.get(0));
                        View a8 = a(arrayList2.get(1));
                        View a9 = a(arrayList2.get(2));
                        b(a7, -1, false);
                        b(a8, -1, true);
                        b(a9, -1, true);
                        linearLayout.addView(a7);
                        linearLayout.addView(a8);
                        linearLayout.addView(a9);
                        return;
                    }
                    int i3 = dip2px2 / 3;
                    View a10 = a(arrayList2.get(0));
                    View a11 = a(arrayList2.get(1));
                    View a12 = a(arrayList2.get(2));
                    b(a10, i3, false);
                    b(a11, i3, true);
                    b(a12, i3, true);
                    linearLayout.addView(a10);
                    linearLayout.addView(a11);
                    linearLayout.addView(a12);
                }
            }
        }
    }

    public void e(boolean z) {
        if (z) {
            this.f4835g.setVisibility(8);
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            return;
        }
        this.f4835g.setVisibility(0);
        this.t.setVisibility(0);
        this.u.setVisibility(0);
    }

    public void f(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.v = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4835g = (AppCompatTextView) findViewById(R.id.lib_pd_topimage_item_gift_content_more);
        this.f4836h = (SimpleDraweeView) findViewById(R.id.lib_pd_topimage_item_gift_content_icon);
        AppCompatTextView appCompatTextView = (AppCompatTextView) findViewById(R.id.lib_pd_topimage_item_gift_content_title);
        this.f4837i = appCompatTextView;
        FontsUtil.changeTextFont(appCompatTextView, 4097);
        this.f4838j = (AppCompatTextView) findViewById(R.id.lib_pd_topimage_item_gift_content_desc);
        this.u = (LinearLayout) findViewById(R.id.lib_pd_holder_topimage_item_gift_content_btn_ok);
        this.t = findViewById(R.id.lib_pd_topimage_item_gift_content_divider_line);
        this.f4839k = findViewById(R.id.lib_pd_topimage_item_gift_grid_layout);
        this.f4840l = findViewById(R.id.lib_pd_topimage_item_gift_linear_layout);
        this.u.setOnClickListener(new e(this));
    }
}
