package com.jd.lib.productdetail.mainimage.holder.comment;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentUGCInfo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMCommentLabelFlowLayout;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageCommentNewRootView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    public PdMImageCommentView f4719g;

    /* renamed from: h  reason: collision with root package name */
    public PdMImageCommentZcxView f4720h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4721i;

    public PdMImageCommentNewRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x023a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(PdCommentInfo pdCommentInfo, String str, boolean z, String str2, int i2) {
        boolean z2;
        List<PdCommentItemInfo> list;
        boolean z3;
        PdMainImagePresenter pdMainImagePresenter;
        List<PdCommentItemInfo> list2;
        this.f4721i = z;
        if (this.f4720h == null || this.f4719g == null) {
            return;
        }
        if (pdCommentInfo != null && (list2 = pdCommentInfo.commentInfoList) != null && list2.size() >= 2) {
            PdCommentItemInfo pdCommentItemInfo = pdCommentInfo.commentInfoList.get(1);
            if (c(pdCommentInfo.commentInfoList.get(0))) {
                z2 = c(pdCommentItemInfo);
                String str3 = "";
                if (!(z2 || this.f4721i || pdCommentInfo.isBuyerCommentShowSmallerThan(4))) {
                    this.f4720h.setVisibility(8);
                    this.f4719g.setVisibility(0);
                    PdMImageCommentView pdMImageCommentView = this.f4719g;
                    pdMImageCommentView.f4736g = str2;
                    pdMImageCommentView.c(true);
                    pdMImageCommentView.t.setVisibility(8);
                    pdMImageCommentView.u.setVisibility(8);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, -2);
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = PDUtils.dip2px(4.0f);
                    ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = PDUtils.dip2px(20.0f);
                    layoutParams.horizontalBias = 0.0f;
                    layoutParams.matchConstraintDefaultWidth = 1;
                    int i3 = R.id.lib_pd_holder_topimage_item_comment_content_title;
                    layoutParams.bottomToBottom = i3;
                    layoutParams.leftToRight = i3;
                    layoutParams.rightToRight = 0;
                    pdMImageCommentView.f4738i.setLayoutParams(layoutParams);
                    if (pdMImageCommentView.d(pdCommentInfo, i2)) {
                        pdMImageCommentView.c(false);
                        if (!TextUtils.isEmpty(pdCommentInfo.goodRate) && !TextUtils.isEmpty(pdCommentInfo.allCntStr)) {
                            str3 = pdMImageCommentView.getResources().getString(R.string.lib_pd_image_good_dicuss3, pdCommentInfo.goodRate, pdCommentInfo.allCntStr);
                        }
                        pdMImageCommentView.f4738i.setText(str3);
                        pdMImageCommentView.f4738i.setVisibility(0);
                        pdMImageCommentView.f4739j.setVisibility(0);
                        pdMImageCommentView.f4739j.a(pdCommentInfo.commentInfoList.get(0), i2);
                        int i4 = R2.attr.additionBottom;
                        if (pdMImageCommentView.getContext() != null && (pdMImageCommentView.getContext() instanceof Activity) && (pdMainImagePresenter = pdMImageCommentView.v) != null) {
                            i4 = PDUtils.px2dp(pdMainImagePresenter.appImageWidth);
                        }
                        PdCommentItemInfo pdCommentItemInfo2 = pdCommentInfo.commentInfoList.get(1);
                        if (i4 > 340) {
                            pdMImageCommentView.f4740k.setVisibility(0);
                            pdMImageCommentView.f4740k.a(pdCommentItemInfo2, i2);
                            if (i2 == 2 && pdCommentInfo.commentInfoList.size() > 2) {
                                pdMImageCommentView.f4741l.setVisibility(0);
                                pdMImageCommentView.f4741l.a(pdCommentInfo.commentInfoList.get(2), i2);
                            } else {
                                pdMImageCommentView.f4741l.setVisibility(8);
                            }
                        } else {
                            pdMImageCommentView.f4740k.setVisibility(8);
                            pdMImageCommentView.f4741l.setVisibility(8);
                        }
                        ConstraintLayout.LayoutParams layoutParams2 = pdMImageCommentView.q.getLayoutParams() instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) pdMImageCommentView.q.getLayoutParams() : null;
                        if (i2 == 2) {
                            if (layoutParams2 != null) {
                                ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = PDUtils.dip2px(72.0f);
                                ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = PDUtils.dip2px(68.0f);
                            }
                            FontsUtil.changeTextFont(pdMImageCommentView.f4738i, 4099);
                            if (pdMImageCommentView.getContext() != null) {
                                pdMImageCommentView.f4738i.setTextColor(ContextCompat.getColor(pdMImageCommentView.getContext(), R.color.lib_pd_image_757575));
                            }
                        } else {
                            if (layoutParams2 != null) {
                                ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = PDUtils.dip2px(44.0f);
                                ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = PDUtils.dip2px(20.0f);
                            }
                            if (pdMImageCommentView.getContext() != null) {
                                pdMImageCommentView.f4738i.setTextColor(ContextCompat.getColor(pdMImageCommentView.getContext(), R.color.lib_pd_image_color_808080));
                            }
                        }
                        pdMImageCommentView.q.setLayoutParams(layoutParams2);
                        ArrayList<PdCommentUGCInfo> arrayList = pdCommentInfo.ugcTextInfo;
                        if (arrayList != null && arrayList.size() > 0) {
                            pdMImageCommentView.t.setVisibility(0);
                            pdMImageCommentView.t.b(pdCommentInfo.ugcTextInfo);
                            return;
                        }
                        List<PdCommentInfo.CommentTag> list3 = pdCommentInfo.semanticTagList;
                        if (list3 != null && list3.size() > 0) {
                            pdMImageCommentView.u.setVisibility(0);
                            pdMImageCommentView.u.a(pdCommentInfo.semanticTagList);
                            return;
                        } else if (i2 == 2) {
                            ArrayList<PdCommentUGCInfo> arrayList2 = new ArrayList<>();
                            PdCommentUGCInfo pdCommentUGCInfo = new PdCommentUGCInfo();
                            if (pdMImageCommentView.getContext() != null) {
                                pdCommentUGCInfo.text = pdMImageCommentView.getContext().getString(R.string.lib_pd_image_topimage_comment_ugc_default);
                            }
                            arrayList2.add(pdCommentUGCInfo);
                            pdMImageCommentView.t.setVisibility(0);
                            pdMImageCommentView.t.b(arrayList2);
                            return;
                        } else {
                            ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams(0, -2);
                            ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = PDUtils.dip2px(10.0f);
                            ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin = PDUtils.dip2px(20.0f);
                            layoutParams3.horizontalBias = 0.0f;
                            layoutParams3.matchConstraintDefaultWidth = 1;
                            layoutParams3.topToBottom = i3;
                            layoutParams3.leftToLeft = i3;
                            layoutParams3.rightToRight = 0;
                            pdMImageCommentView.f4738i.setLayoutParams(layoutParams3);
                            return;
                        }
                    }
                    pdMImageCommentView.c(true);
                    return;
                }
                this.f4719g.setVisibility(8);
                this.f4720h.setVisibility(0);
                PdMImageCommentZcxView pdMImageCommentZcxView = this.f4720h;
                pdMImageCommentZcxView.s.setVisibility(8);
                pdMImageCommentZcxView.v.setVisibility(8);
                ConstraintLayout.LayoutParams layoutParams4 = new ConstraintLayout.LayoutParams(0, -2);
                ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = PDUtils.dip2px(4.0f);
                ((ViewGroup.MarginLayoutParams) layoutParams4).rightMargin = PDUtils.dip2px(20.0f);
                layoutParams4.horizontalBias = 0.0f;
                layoutParams4.matchConstraintDefaultWidth = 1;
                int i5 = R.id.lib_pd_holder_topimage_item_comment_content_title_new;
                layoutParams4.bottomToBottom = i5;
                layoutParams4.leftToRight = i5;
                layoutParams4.rightToRight = 0;
                pdMImageCommentZcxView.f4749j.setLayoutParams(layoutParams4);
                pdMImageCommentZcxView.f4748i = pdCommentInfo;
                if (pdCommentInfo == null || (list = pdCommentInfo.commentInfoList) == null || list.size() <= 0) {
                    return;
                }
                try {
                    pdMImageCommentZcxView.f4749j.setBackgroundResource(0);
                    pdMImageCommentZcxView.f4750k.setVisibility(8);
                    pdMImageCommentZcxView.f4751l.b(false);
                    pdMImageCommentZcxView.f4752m.b(false);
                } catch (Exception unused) {
                }
                if (!TextUtils.isEmpty(pdCommentInfo.goodRate) && !TextUtils.isEmpty(pdCommentInfo.allCntStr)) {
                    str3 = pdMImageCommentZcxView.getResources().getString(R.string.lib_pd_image_good_dicuss3, pdCommentInfo.goodRate, pdCommentInfo.allCntStr);
                }
                pdMImageCommentZcxView.f4749j.setText(str3);
                if (pdCommentInfo.commentInfoList != null) {
                    RelativeLayout.LayoutParams layoutParams5 = pdMImageCommentZcxView.f4753n.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) pdMImageCommentZcxView.f4753n.getLayoutParams() : null;
                    ConstraintLayout.LayoutParams layoutParams6 = pdMImageCommentZcxView.f4751l.getLayoutParams() instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) pdMImageCommentZcxView.f4751l.getLayoutParams() : null;
                    ConstraintLayout.LayoutParams layoutParams7 = pdMImageCommentZcxView.t.getLayoutParams() instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) pdMImageCommentZcxView.t.getLayoutParams() : null;
                    if (i2 == 2) {
                        if (layoutParams5 != null) {
                            layoutParams5.topMargin = PDUtils.dip2px(72.0f);
                            pdMImageCommentZcxView.f4753n.setPadding(0, PDUtils.dip2px(26.0f), 0, PDUtils.dip2px(20.0f));
                        }
                        if (layoutParams6 != null) {
                            ((ViewGroup.MarginLayoutParams) layoutParams6).topMargin = PDUtils.dip2px(52.0f);
                        }
                        if (layoutParams7 != null) {
                            ((ViewGroup.MarginLayoutParams) layoutParams7).topMargin = PDUtils.dip2px(18.0f);
                        }
                        FontsUtil.changeTextFont(pdMImageCommentZcxView.f4749j, 4099);
                        pdMImageCommentZcxView.f4749j.setTextColor(ContextCompat.getColor(pdMImageCommentZcxView.x, R.color.lib_pd_image_757575));
                        List<PdCommentItemInfo> list4 = pdCommentInfo.commentInfoList;
                        if (list4 != null && list4.size() >= 2) {
                            PdCommentItemInfo pdCommentItemInfo3 = pdCommentInfo.commentInfoList.get(1);
                            if (pdMImageCommentZcxView.d(pdCommentInfo.commentInfoList.get(0))) {
                                z3 = pdMImageCommentZcxView.d(pdCommentItemInfo3);
                                if (!z3) {
                                    pdMImageCommentZcxView.f4751l.setVisibility(0);
                                    pdMImageCommentZcxView.f4751l.a(pdCommentInfo.commentInfoList.get(0), i2);
                                    pdMImageCommentZcxView.f4752m.setVisibility(0);
                                    pdMImageCommentZcxView.f4752m.a(pdCommentInfo.commentInfoList.get(1), i2);
                                } else {
                                    pdMImageCommentZcxView.f4751l.setVisibility(8);
                                    pdMImageCommentZcxView.f4752m.setVisibility(8);
                                }
                            }
                        }
                        z3 = false;
                        if (!z3) {
                        }
                    } else {
                        if (layoutParams5 != null) {
                            layoutParams5.topMargin = PDUtils.dip2px(44.0f);
                            PdMainImagePresenter pdMainImagePresenter2 = pdMImageCommentZcxView.w;
                            if (pdMainImagePresenter2 != null && pdMainImagePresenter2.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                                pdMImageCommentZcxView.f4753n.setPadding(0, PDUtils.dip2px(8.0f), 0, PDUtils.dip2px(8.0f));
                            } else {
                                pdMImageCommentZcxView.f4753n.setPadding(0, PDUtils.dip2px(16.0f), 0, PDUtils.dip2px(16.0f));
                            }
                        }
                        if (layoutParams6 != null) {
                            ((ViewGroup.MarginLayoutParams) layoutParams6).topMargin = PDUtils.dip2px(44.0f);
                        }
                        if (layoutParams7 != null) {
                            PdMainImagePresenter pdMainImagePresenter3 = pdMImageCommentZcxView.w;
                            if (pdMainImagePresenter3 != null && pdMainImagePresenter3.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                                ((ViewGroup.MarginLayoutParams) layoutParams7).topMargin = PDUtils.dip2px(6.0f);
                            } else {
                                ((ViewGroup.MarginLayoutParams) layoutParams7).topMargin = PDUtils.dip2px(14.0f);
                            }
                        }
                        pdMImageCommentZcxView.f4749j.setTextColor(ContextCompat.getColor(pdMImageCommentZcxView.x, R.color.lib_pd_image_color_808080));
                        if (pdCommentInfo.commentInfoList.size() > 0) {
                            int i6 = 0;
                            while (true) {
                                if (i6 < pdCommentInfo.commentInfoList.size()) {
                                    PdCommentItemInfo pdCommentItemInfo4 = pdCommentInfo.commentInfoList.get(i6);
                                    if (pdCommentItemInfo4 != null && !TextUtils.isEmpty(pdCommentItemInfo4.commentScore) && PDUtils.stringToFloat(pdCommentItemInfo4.commentScore) > 3.0f && !TextUtils.isEmpty(pdCommentItemInfo4.commentData)) {
                                        pdMImageCommentZcxView.f4751l.setVisibility(0);
                                        pdMImageCommentZcxView.f4751l.a(pdCommentItemInfo4, i2);
                                        pdMImageCommentZcxView.f4752m.setVisibility(8);
                                        break;
                                    }
                                    pdMImageCommentZcxView.f4751l.setVisibility(8);
                                    pdMImageCommentZcxView.f4752m.setVisibility(8);
                                    i6++;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            pdMImageCommentZcxView.f4751l.setVisibility(8);
                            pdMImageCommentZcxView.f4752m.setVisibility(8);
                        }
                    }
                    if (layoutParams5 != null) {
                        pdMImageCommentZcxView.f4753n.setLayoutParams(layoutParams5);
                    }
                    if (layoutParams6 != null) {
                        pdMImageCommentZcxView.f4751l.setLayoutParams(layoutParams6);
                    }
                    if (layoutParams7 != null) {
                        pdMImageCommentZcxView.t.setLayoutParams(layoutParams7);
                    }
                } else {
                    pdMImageCommentZcxView.f4751l.setVisibility(8);
                    pdMImageCommentZcxView.f4752m.setVisibility(8);
                }
                pdMImageCommentZcxView.c(i2 == 2);
                PdCommentInfo.BuyersCommentInfo buyersCommentInfo = pdCommentInfo.buyersCommentInfo;
                if (buyersCommentInfo != null && buyersCommentInfo.buyersCommentInfoList.size() > 0) {
                    PdMImageZcxView pdMImageZcxView = pdMImageCommentZcxView.t;
                    boolean z4 = pdMImageCommentZcxView.r;
                    pdMImageZcxView.getClass();
                    if (pdCommentInfo.buyersShowInfo != null && pdCommentInfo.buyersCommentInfo.buyersCommentInfoList != null) {
                        pdMImageZcxView.f4762l.removeAllViews();
                        pdMImageZcxView.setVisibility(4);
                        pdMImageZcxView.f4763m = true;
                        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                        int i7 = 19;
                        createSimple.setPlaceholder(19);
                        JDImageUtils.displayImage(str, pdMImageZcxView.f4759i, createSimple);
                        List<PdCommentInfo.BuyersCommentInfoList> list5 = pdCommentInfo.buyersCommentInfo.buyersCommentInfoList;
                        if (pdMImageZcxView.getContext() instanceof Activity) {
                            PdMainImagePresenter pdMainImagePresenter4 = pdMImageZcxView.f4758h;
                            if (pdMainImagePresenter4 != null) {
                                pdMImageZcxView.f4757g = pdMainImagePresenter4.appImageWidth;
                            } else {
                                pdMImageZcxView.f4757g = PDUtils.getAppWidth((Activity) pdMImageZcxView.getContext());
                            }
                            int min = Math.min(4, list5.size());
                            int dip2px = (pdMImageZcxView.f4757g - PDUtils.dip2px(79.0f)) / 4;
                            pdMImageZcxView.f4761k = new ArrayList<>();
                            int i8 = 0;
                            while (i8 < min) {
                                PdCommentInfo.BuyersCommentInfoList buyersCommentInfoList = list5.get(i8);
                                if (buyersCommentInfoList != null && !TextUtils.isEmpty(buyersCommentInfoList.picURL)) {
                                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(pdMImageZcxView.getContext());
                                    LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(dip2px, dip2px);
                                    if (i8 != min - 1) {
                                        layoutParams8.rightMargin = PDUtils.dip2px(9.0f);
                                    } else {
                                        layoutParams8.rightMargin = 0;
                                    }
                                    simpleDraweeView.setLayoutParams(layoutParams8);
                                    simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                                    pdMImageZcxView.f4762l.addView(simpleDraweeView);
                                    pdMImageZcxView.f4761k.add(simpleDraweeView);
                                    simpleDraweeView.setOnClickListener(new l(pdMImageZcxView, z4, pdCommentInfo, i8));
                                    JDDisplayImageOptions createSimple2 = JDDisplayImageOptions.createSimple();
                                    createSimple2.setPlaceholder(i7);
                                    JDImageUtils.loadImage(buyersCommentInfoList.picURL, createSimple2, new m(pdMImageZcxView, simpleDraweeView, layoutParams8, dip2px));
                                }
                                i8++;
                                i7 = 19;
                            }
                        }
                    } else {
                        pdMImageZcxView.setVisibility(8);
                    }
                }
                ArrayList<PdCommentUGCInfo> arrayList3 = pdCommentInfo.ugcTextInfo;
                if (arrayList3 != null && arrayList3.size() > 0) {
                    pdMImageCommentZcxView.s.setVisibility(0);
                    pdMImageCommentZcxView.s.b(pdCommentInfo.ugcTextInfo);
                    return;
                }
                List<PdCommentInfo.CommentTag> list6 = pdCommentInfo.semanticTagList;
                if (list6 != null && list6.size() > 0) {
                    pdMImageCommentZcxView.v.setVisibility(0);
                    PdMCommentLabelFlowLayout pdMCommentLabelFlowLayout = pdMImageCommentZcxView.v.f4717g;
                    if (pdMCommentLabelFlowLayout != null) {
                        pdMCommentLabelFlowLayout.f4988h = PDUtils.dip2px(12.0f);
                        pdMCommentLabelFlowLayout.f4989i = 0;
                    }
                    pdMImageCommentZcxView.v.a(pdCommentInfo.semanticTagList);
                    return;
                } else if (i2 == 2) {
                    ArrayList<PdCommentUGCInfo> arrayList4 = new ArrayList<>();
                    PdCommentUGCInfo pdCommentUGCInfo2 = new PdCommentUGCInfo();
                    pdCommentUGCInfo2.text = pdMImageCommentZcxView.x.getString(R.string.lib_pd_image_topimage_comment_ugc_default);
                    arrayList4.add(pdCommentUGCInfo2);
                    pdMImageCommentZcxView.s.setVisibility(0);
                    pdMImageCommentZcxView.s.b(arrayList4);
                    return;
                } else {
                    ConstraintLayout.LayoutParams layoutParams9 = new ConstraintLayout.LayoutParams(0, -2);
                    ((ViewGroup.MarginLayoutParams) layoutParams9).topMargin = PDUtils.dip2px(10.0f);
                    ((ViewGroup.MarginLayoutParams) layoutParams9).rightMargin = PDUtils.dip2px(20.0f);
                    layoutParams9.horizontalBias = 0.0f;
                    layoutParams9.matchConstraintDefaultWidth = 1;
                    int i9 = R.id.lib_pd_holder_topimage_item_comment_content_title_new;
                    layoutParams9.topToBottom = i9;
                    layoutParams9.leftToLeft = i9;
                    layoutParams9.rightToRight = 0;
                    pdMImageCommentZcxView.f4749j.setLayoutParams(layoutParams9);
                    return;
                }
            }
        }
        z2 = false;
        String str32 = "";
        if (!(z2 || this.f4721i || pdCommentInfo.isBuyerCommentShowSmallerThan(4))) {
        }
    }

    public void b(boolean z, int i2) {
        PdMImageCommentView pdMImageCommentView = this.f4719g;
        pdMImageCommentView.s = z;
        if (i2 == 2) {
            if (z) {
                pdMImageCommentView.p.setVisibility(0);
            } else {
                pdMImageCommentView.p.setVisibility(8);
            }
            View view = pdMImageCommentView.r;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = pdMImageCommentView.f4743n;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            if (!z) {
                View view3 = pdMImageCommentView.f4743n;
                if (view3 != null) {
                    view3.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView = pdMImageCommentView.f4739j;
                if (pdMImageCommentItemView != null) {
                    pdMImageCommentItemView.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView2 = pdMImageCommentView.f4740k;
                if (pdMImageCommentItemView2 != null) {
                    pdMImageCommentItemView2.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView3 = pdMImageCommentView.f4741l;
                if (pdMImageCommentItemView3 != null) {
                    pdMImageCommentItemView3.setClickable(false);
                }
            }
        } else {
            View view4 = pdMImageCommentView.r;
            if (view4 != null) {
                view4.setVisibility(z ? 0 : 8);
                pdMImageCommentView.f4743n.setVisibility(pdMImageCommentView.s ? 0 : 8);
            }
            if (!z) {
                View view5 = pdMImageCommentView.f4743n;
                if (view5 != null) {
                    view5.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView4 = pdMImageCommentView.f4739j;
                if (pdMImageCommentItemView4 != null) {
                    pdMImageCommentItemView4.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView5 = pdMImageCommentView.f4740k;
                if (pdMImageCommentItemView5 != null) {
                    pdMImageCommentItemView5.setClickable(false);
                }
                PdMImageCommentItemView pdMImageCommentItemView6 = pdMImageCommentView.f4741l;
                if (pdMImageCommentItemView6 != null) {
                    pdMImageCommentItemView6.setClickable(false);
                }
            }
            pdMImageCommentView.p.setVisibility(8);
        }
        PdMImageCommentZcxView pdMImageCommentZcxView = this.f4720h;
        pdMImageCommentZcxView.r = z;
        pdMImageCommentZcxView.c(i2 == 2);
    }

    public final boolean c(PdCommentItemInfo pdCommentItemInfo) {
        return (pdCommentItemInfo == null || TextUtils.isEmpty(pdCommentItemInfo.commentScore) || PDUtils.stringToFloat(pdCommentItemInfo.commentScore) <= 3.0f || TextUtils.isEmpty(pdCommentItemInfo.commentData)) ? false : true;
    }

    public void d(PdMainImagePresenter pdMainImagePresenter) {
        PdMImageCommentZcxView pdMImageCommentZcxView = this.f4720h;
        if (pdMImageCommentZcxView != null) {
            pdMImageCommentZcxView.s(pdMainImagePresenter);
        }
        PdMImageCommentView pdMImageCommentView = this.f4719g;
        if (pdMImageCommentView != null) {
            pdMImageCommentView.k(pdMainImagePresenter);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4719g = (PdMImageCommentView) findViewById(R.id.lib_pd_holder_topimage_item_comment);
        this.f4720h = (PdMImageCommentZcxView) findViewById(R.id.lib_pd_holder_topimage_item_comment_zcx);
    }
}
