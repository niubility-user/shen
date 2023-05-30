package com.jingdong.app.mall.home.floor.view.view.module;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.a;
import com.jingdong.app.mall.home.floor.common.i.c;
import com.jingdong.app.mall.home.floor.common.i.d;
import com.jingdong.app.mall.home.floor.common.i.e;
import com.jingdong.app.mall.home.floor.common.i.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.common.i.r;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.MallFloorViewCommonFunc;
import com.jingdong.app.mall.home.floor.view.view.SloganTextView;
import com.jingdong.app.mall.home.floor.view.widget.PriceLabelLayout;
import com.jingdong.app.mall.home.r.f.a.b;
import com.jingdong.app.mall.home.r.f.a.q;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.common.ui.RoundConerDrawable;
import com.jingdong.common.utils.StringUtil;

/* loaded from: classes4.dex */
public class MallFloorModuleCommon extends RelativeLayout {
    private static final int MASK_IMG_SIZE = 44;
    protected static final int SEPARATION_LABEL_TAG = 768;
    protected static final int SEPARATION_MASK_TAG = 800;
    protected static final int SEPARATION_SUB_TITLE_IMG_TAG = 544;
    protected static final int SEPARATION_SUB_TITLE_TAG = 512;
    protected static final int SEPARATION_TITLE_TAG = 256;
    private static final int SUBTITLE_IMG_SIZE = 26;
    private String firstSkuImg;
    private ColorDrawable mDefaultSkuBg;
    protected BaseMallFloor<?> mFloor;
    private boolean mNeedWhiteBg;
    protected b mPresenter;
    private boolean mRejectDowngrading;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon$5  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TEXT_MARKS;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_GRAVITY;
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM;

        static {
            int[] iArr = new int[d.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TEXT_MARKS = iArr;
            try {
                iArr[d.WITH_DOUBLE_QUOTATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TEXT_MARKS[d.WITH_DASH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TEXT_MARKS[d.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[c.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM = iArr2;
            try {
                iArr2[c.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM[c.CENTER_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM[c.LEFT_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM[c.CENTER_BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM[c.OBEY_ENUM_SEPARATION_TITLE_SUBTITLE_POS_ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[e.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_GRAVITY = iArr3;
            try {
                iArr3[e.CENTER_HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr4 = new int[f.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM = iArr4;
            try {
                iArr4[f.CENTER_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.CENTER_TOP_ONLY_MAIN_TITLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.ONLY_SUB_CENTER_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.CENTER_BOTTOM_ONLY_MAIN_TITLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.LEFT_BOTTOM_ONLY_SUB_TITLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.RIGHT_TO_TITLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[f.LEFT_TOP.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr5 = new int[a.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM = iArr5;
            try {
                iArr5[a.CENTER_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.CENTER_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.LEFT_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.jingdong.app.mall.home.r.f.a.b] */
    public MallFloorModuleCommon(Context context, BaseMallFloor<?> baseMallFloor) {
        super(context, null);
        this.mFloor = null;
        this.mPresenter = null;
        this.mDefaultSkuBg = new ColorDrawable(-859201);
        this.mFloor = baseMallFloor;
        this.mPresenter = baseMallFloor.getPresenter();
    }

    private static void addDashMark(RelativeLayout relativeLayout, TextView textView, String str, int i2) {
        View findViewWithTag = relativeLayout.findViewWithTag("add-dash-mark-left");
        View findViewWithTag2 = relativeLayout.findViewWithTag("add-dash-mark-right");
        if (TextUtils.isEmpty(str)) {
            if (findViewWithTag != null) {
                relativeLayout.removeView(findViewWithTag);
            }
            if (findViewWithTag2 != null) {
                relativeLayout.removeView(findViewWithTag2);
                return;
            }
            return;
        }
        if (findViewWithTag == null) {
            findViewWithTag = new View(relativeLayout.getContext());
            findViewWithTag.setTag("add-dash-mark-left");
        }
        ViewGroup.LayoutParams layoutParams = findViewWithTag.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2 == null) {
            layoutParams2 = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(40), com.jingdong.app.mall.home.floor.common.d.d(2));
        }
        layoutParams2.width = com.jingdong.app.mall.home.floor.common.d.d(40);
        layoutParams2.height = com.jingdong.app.mall.home.floor.common.d.d(2);
        layoutParams2.addRule(0, textView.getId());
        layoutParams2.rightMargin = com.jingdong.app.mall.home.floor.common.d.d(6);
        layoutParams2.addRule(6, textView.getId());
        layoutParams2.topMargin = ((int) ((textView.getPaint().descent() - textView.getPaint().ascent()) + 0.5f)) / 2;
        findViewWithTag.setLayoutParams(layoutParams2);
        findViewWithTag.setBackgroundColor(i2);
        if (findViewWithTag.getParent() == null) {
            relativeLayout.addView(findViewWithTag);
        }
        if (findViewWithTag2 == null) {
            findViewWithTag2 = new View(relativeLayout.getContext());
            findViewWithTag2.setTag("add-dash-mark-right");
        }
        ViewGroup.LayoutParams layoutParams3 = findViewWithTag2.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
        if (layoutParams4 == null) {
            layoutParams4 = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(40), com.jingdong.app.mall.home.floor.common.d.d(2));
        }
        layoutParams4.width = com.jingdong.app.mall.home.floor.common.d.d(40);
        layoutParams4.height = com.jingdong.app.mall.home.floor.common.d.d(2);
        layoutParams4.addRule(1, textView.getId());
        layoutParams4.leftMargin = com.jingdong.app.mall.home.floor.common.d.d(6);
        layoutParams4.addRule(6, textView.getId());
        layoutParams4.topMargin = ((int) ((textView.getPaint().descent() - textView.getPaint().ascent()) + 0.5f)) / 2;
        findViewWithTag2.setLayoutParams(layoutParams4);
        findViewWithTag2.setBackgroundColor(i2);
        if (findViewWithTag2.getParent() == null) {
            relativeLayout.addView(findViewWithTag2);
        }
    }

    private static String addDoubleQuotationMark(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return "\"" + str + "\"";
    }

    public static int addItemTitleAndSubTitle(com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, boolean z, RelativeLayout relativeLayout, int i2, int i3, r rVar, int i4) {
        if (relativeLayout instanceof MallFloorModuleBannerAnim) {
            ((MallFloorModuleBannerAnim) relativeLayout).addItemTitleAndSubTitle(fVar, i2);
            return 0;
        }
        return addItemTitleAndSubTitle(fVar, aVar, z, relativeLayout, i2, i3, rVar, 0, true, i4);
    }

    private static String addMarksToTexts(RelativeLayout relativeLayout, TextView textView, String str, int i2, d dVar) {
        int i3 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TEXT_MARKS[dVar.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                return str;
            }
            addDashMark(relativeLayout, textView, str, i2);
            return str;
        }
        return addDoubleQuotationMark(str);
    }

    private void addMaskImg(com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, View view, int i2, int i3) {
        p.a.C0288a c0288a = aVar.Y;
        int i4 = i3 + 800;
        View findViewWithTag = findViewWithTag(Integer.valueOf(i4));
        if (findViewWithTag != null) {
            findViewWithTag.setTag(R.id.mallfloor_check_key, "");
            findViewWithTag.setVisibility(4);
        }
        if (c0288a == null || i2 > 1) {
            return;
        }
        SimpleDraweeView simpleDraweeView = null;
        Point point2 = aVar.H;
        if (findViewWithTag == null && point2 != null) {
            simpleDraweeView = new HomeDraweeView(getContext());
            simpleDraweeView.setTag(Integer.valueOf(i4));
        }
        if (findViewWithTag != null && point2 != null) {
            com.jingdong.app.mall.home.o.a.f.n(findViewWithTag);
            simpleDraweeView = (SimpleDraweeView) findViewWithTag;
        }
        String F = fVar.F();
        if (simpleDraweeView == null || TextUtils.isEmpty(F) || view == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(44), com.jingdong.app.mall.home.floor.common.d.d(44));
        layoutParams.leftMargin = point2.x;
        layoutParams.bottomMargin = point2.y;
        layoutParams.addRule(5, view.getId());
        layoutParams.addRule(8, view.getId());
        simpleDraweeView.setLayoutParams(layoutParams);
        if (simpleDraweeView.getParent() == null) {
            addView(simpleDraweeView);
        }
        simpleDraweeView.setTag(R.id.mallfloor_check_key, F);
        simpleDraweeView.setVisibility(0);
        com.jingdong.app.mall.home.floor.ctrl.e.f(F, simpleDraweeView, com.jingdong.app.mall.home.floor.ctrl.e.f9402h);
    }

    private void addPriceLabel(com.jingdong.app.mall.home.r.e.f fVar, SimpleDraweeView simpleDraweeView, p.a aVar, int i2) {
        String str;
        PriceLabelLayout priceLabelLayout;
        RelativeLayout.LayoutParams layoutParams;
        String str2 = "priceLabel_" + i2;
        View findViewWithTag = findViewWithTag(str2);
        if (!aVar.q) {
            str = null;
        } else if (i2 == 0) {
            str = fVar.I();
        } else {
            str = i2 == 1 ? fVar.J() : "";
        }
        if (com.jingdong.app.mall.home.n.h.c.d(str)) {
            if (findViewWithTag != null) {
                findViewWithTag.setVisibility(8);
                return;
            }
            return;
        }
        if (findViewWithTag instanceof PriceLabelLayout) {
            priceLabelLayout = (PriceLabelLayout) findViewWithTag;
        } else {
            priceLabelLayout = new PriceLabelLayout(getContext());
            priceLabelLayout.setTag(str2);
        }
        ViewGroup.LayoutParams layoutParams2 = priceLabelLayout.getLayoutParams();
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            layoutParams = (RelativeLayout.LayoutParams) layoutParams2;
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        layoutParams.height = com.jingdong.app.mall.home.floor.common.d.d(28);
        layoutParams.bottomMargin = -com.jingdong.app.mall.home.floor.common.d.d(10);
        layoutParams.addRule(8, simpleDraweeView.getId());
        layoutParams.addRule(5, simpleDraweeView.getId());
        layoutParams.addRule(7, simpleDraweeView.getId());
        priceLabelLayout.setLayoutParams(layoutParams);
        priceLabelLayout.setLabelPrice(fVar.K(), str);
        m.b(this, priceLabelLayout, -1);
    }

    private void addSeparationImgToParent(SimpleDraweeView simpleDraweeView) {
        ViewParent parent = simpleDraweeView.getParent();
        if (parent != this) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(simpleDraweeView);
            }
            addView(simpleDraweeView);
        }
    }

    public static void addSloganText(RelativeLayout relativeLayout, Point point2, int[] iArr, final TextView textView, String str, final int i2) {
        SloganTextView sloganTextView;
        int i3 = R.id.mallfloor_floor_title_slogan;
        View findViewById = relativeLayout.findViewById(i3);
        if (point2 != null && str != null && !TextUtils.isEmpty(str)) {
            if (findViewById != null && (findViewById instanceof SloganTextView)) {
                sloganTextView = (SloganTextView) findViewById;
                ViewGroup.LayoutParams layoutParams = sloganTextView.getLayoutParams();
                com.jingdong.app.mall.home.o.a.f.n(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.width = -2;
                layoutParams2.height = com.jingdong.app.mall.home.floor.common.d.d(36);
                layoutParams2.leftMargin = point2.x;
                layoutParams2.topMargin = point2.y;
                layoutParams2.rightMargin = com.jingdong.app.mall.home.floor.common.d.d(16);
                layoutParams2.addRule(1, textView.getId());
            } else {
                sloganTextView = new SloganTextView(relativeLayout.getContext());
                sloganTextView.setId(i3);
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, com.jingdong.app.mall.home.floor.common.d.d(36));
                layoutParams3.leftMargin = point2.x;
                layoutParams3.topMargin = point2.y;
                layoutParams3.rightMargin = com.jingdong.app.mall.home.floor.common.d.d(16);
                layoutParams3.addRule(1, textView.getId());
                relativeLayout.addView(sloganTextView, layoutParams3);
            }
            sloganTextView.setText(str);
            sloganTextView.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(20));
            sloganTextView.setTextGradient(GradientTextView.GradientType.LeftTopToRightBottom, iArr);
            sloganTextView.setMaxLines(1);
            sloganTextView.setEllipsize(TextUtils.TruncateAt.END);
            sloganTextView.setVisibility(4);
            sloganTextView.setSloganArrowVisibility(true);
            final int paddingLeft = sloganTextView.getPaddingLeft() + sloganTextView.getPaddingRight();
            final int measureText = (int) sloganTextView.getPaint().measureText(str);
            sloganTextView.setTag(R.id.mallfloor_floor_title_slogan_text_width, Integer.valueOf(measureText));
            ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
            com.jingdong.app.mall.home.o.a.f.n(layoutParams4);
            final RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) layoutParams4;
            final SloganTextView sloganTextView2 = sloganTextView;
            sloganTextView.post(new Runnable() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon.4
                @Override // java.lang.Runnable
                public void run() {
                    int width = (i2 - layoutParams5.leftMargin) - textView.getWidth();
                    if (width < com.jingdong.app.mall.home.floor.common.d.d(86) + paddingLeft) {
                        sloganTextView2.setVisibility(8);
                        return;
                    }
                    sloganTextView2.setVisibility(0);
                    sloganTextView2.setSloganArrowVisibility(width > (measureText + paddingLeft) + 36);
                }
            });
        } else if (findViewById == null || findViewById.getParent() != relativeLayout) {
        } else {
            relativeLayout.removeView(findViewById);
        }
    }

    public static void addTitleBg(com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, boolean z, p.a.C0288a c0288a, RelativeLayout relativeLayout) {
        ImageView homeDraweeView;
        if (relativeLayout == null || aVar == null || c0288a == null || !z) {
            return;
        }
        int i2 = R.id.mallfloor_floor_titlebg;
        View findViewById = relativeLayout.findViewById(i2);
        Point point2 = aVar.E;
        Point point3 = aVar.F;
        if (c0288a.f9348h) {
            if (findViewById != null && (findViewById instanceof SimpleDraweeView)) {
                homeDraweeView = (SimpleDraweeView) findViewById;
                ViewGroup.LayoutParams checkPointParams = MallFloorViewCommonFunc.checkPointParams(homeDraweeView, point2);
                if (checkPointParams instanceof RelativeLayout.LayoutParams) {
                    MallFloorViewCommonFunc.getRuleByPosParam(aVar.f9345m, point3, null, (RelativeLayout.LayoutParams) checkPointParams);
                    homeDraweeView.setLayoutParams(checkPointParams);
                }
            } else {
                homeDraweeView = new HomeDraweeView(relativeLayout.getContext());
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                homeDraweeView.setId(i2);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(point2.x, point2.y);
                MallFloorViewCommonFunc.getRuleByPosParam(aVar.f9345m, point3, null, layoutParams);
                relativeLayout.addView(homeDraweeView, layoutParams);
            }
            com.jingdong.app.mall.home.floor.ctrl.e.r(homeDraweeView, c0288a.r);
            return;
        }
        relativeLayout.removeView(findViewById);
    }

    public static void addTitleIcon(String str, final RelativeLayout relativeLayout, Point point2, final int i2, int i3, final TextView textView, final int i4) {
        ImageView homeDraweeView;
        if (point2 == null) {
            return;
        }
        int i5 = R.id.mallfloor_floor_title_icon;
        View findViewById = relativeLayout.findViewById(i5);
        if (TextUtils.isEmpty(str)) {
            if (findViewById == null || findViewById.getParent() != relativeLayout) {
                return;
            }
            relativeLayout.removeView(findViewById);
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        final RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (findViewById instanceof SimpleDraweeView) {
            homeDraweeView = (SimpleDraweeView) findViewById;
            ViewGroup.LayoutParams layoutParams3 = homeDraweeView.getLayoutParams();
            com.jingdong.app.mall.home.o.a.f.n(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
            layoutParams4.width = i2;
            layoutParams4.height = i3;
            layoutParams4.leftMargin = point2.x;
            layoutParams4.topMargin = point2.y;
        } else {
            homeDraweeView = new HomeDraweeView(relativeLayout.getContext());
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i2, i3);
            layoutParams5.leftMargin = point2.x;
            layoutParams5.topMargin = point2.y;
            homeDraweeView.setId(i5);
            relativeLayout.addView(homeDraweeView, layoutParams5);
        }
        homeDraweeView.setTag(i5, str);
        homeDraweeView.setTag(R.id.mallfloor_floor_title_icon_text_width, Integer.valueOf(getTextWordWidth(textView)));
        com.jingdong.app.mall.home.floor.ctrl.e.g(str, homeDraweeView, com.jingdong.app.mall.home.floor.ctrl.e.f9402h, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon.3
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                Object tag = view.getTag(R.id.mallfloor_floor_title_icon);
                if (tag == null || !tag.equals(str2)) {
                    return;
                }
                RelativeLayout.LayoutParams layoutParams6 = layoutParams2;
                layoutParams6.leftMargin = i2;
                textView.setLayoutParams(layoutParams6);
                View findViewById2 = relativeLayout.findViewById(R.id.mallfloor_floor_title_slogan);
                if (findViewById2 == null || !(findViewById2 instanceof SloganTextView)) {
                    return;
                }
                SloganTextView sloganTextView = (SloganTextView) findViewById2;
                int measureText = (int) ((i4 - layoutParams2.leftMargin) - textView.getPaint().measureText(textView.getText().toString()));
                int paddingLeft = sloganTextView.getPaddingLeft() + sloganTextView.getPaddingRight();
                if (measureText < com.jingdong.app.mall.home.floor.common.d.d(86) + paddingLeft) {
                    sloganTextView.setVisibility(8);
                } else if (measureText < ((Integer) sloganTextView.getTag(R.id.mallfloor_floor_title_slogan_text_width)).intValue() + paddingLeft + 36) {
                    sloganTextView.setVisibility(0);
                    sloganTextView.setSloganArrowVisibility(false);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
            }
        });
    }

    private void displaySeparationImg(SimpleDraweeView simpleDraweeView, String str, int i2, p.a aVar) {
        boolean z = false;
        if (simpleDraweeView != null && simpleDraweeView.getVisibility() != 0) {
            simpleDraweeView.setVisibility(0);
        }
        if (!aVar.p && (this.mPresenter.B(i2) || this.mPresenter.A(i2))) {
            z = true;
        }
        if (z) {
            com.jingdong.app.mall.home.floor.ctrl.e.r(simpleDraweeView, str);
            return;
        }
        boolean z2 = aVar.y;
        this.mNeedWhiteBg = z2;
        if (z2) {
            com.jingdong.app.mall.home.floor.ctrl.e.m(simpleDraweeView, str, this.mDefaultSkuBg);
        } else {
            com.jingdong.app.mall.home.floor.ctrl.e.d(simpleDraweeView, str);
        }
    }

    private DarkWhiteBgImageView getSeparationItemImg(int i2, int i3, String str, p.a aVar) {
        int i4 = (i2 << 8) ^ i3;
        Point separationItemImgSize = getSeparationItemImgSize(aVar, i3);
        return this.mFloor.getImgViewByCache(this, i4, separationItemImgSize.x, separationItemImgSize.y);
    }

    private static int getTextWordWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        TextPaint paint = textView.getPaint();
        int width = textView.getWidth();
        int measureText = ((int) paint.measureText(textView.getText().toString())) + textView.getPaddingLeft() + textView.getPaddingRight();
        return width > measureText ? width : measureText;
    }

    @NonNull
    private static SimpleDraweeView initTitleImageView(p.a aVar, RelativeLayout relativeLayout, int[] iArr, View view) {
        if (view != null && (view instanceof SimpleDraweeView)) {
            return (SimpleDraweeView) view;
        }
        HomeDraweeView homeDraweeView = new HomeDraweeView(relativeLayout.getContext());
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        homeDraweeView.setId(R.id.mallfloor_floor_title_img);
        Point point2 = aVar.z;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(aVar.V, aVar.W);
        MallFloorViewCommonFunc.addRuleAndMarginToParams(iArr, point2, layoutParams);
        relativeLayout.addView(homeDraweeView, layoutParams);
        return homeDraweeView;
    }

    private void renderImageViewScaleType(SimpleDraweeView simpleDraweeView, int i2) {
        if (!this.mPresenter.z(i2) && !this.mPresenter.A(i2)) {
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    private static void setMainTitleImg(p.a aVar, RelativeLayout relativeLayout, String str, final GradientTextView gradientTextView, int[] iArr) {
        View findViewById = relativeLayout.findViewById(R.id.mallfloor_floor_title_img);
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(gradientTextView.getText())) {
                initTitleImageView(aVar, relativeLayout, iArr, findViewById).setImageDrawable(new JDPlaceholderDrawable(18));
                return;
            } else {
                relativeLayout.removeView(findViewById);
                return;
            }
        }
        com.jingdong.app.mall.home.floor.ctrl.e.g(str, initTitleImageView(aVar, relativeLayout, iArr, findViewById), com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(true).setPlaceholder(18), new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon.2
            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                super.onLoadingComplete(str2, view, bitmap);
                GradientTextView.this.setVisibility(4);
                view.setVisibility(0);
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                super.onLoadingFailed(str2, view, jDFailReason);
                if (TextUtils.isEmpty(GradientTextView.this.getText())) {
                    GradientTextView.this.setVisibility(4);
                    view.setVisibility(0);
                    return;
                }
                GradientTextView.this.setVisibility(0);
                view.setVisibility(4);
            }
        });
    }

    private static void setTextStr(RelativeLayout relativeLayout, GradientTextView gradientTextView, String str, int i2, d dVar) {
        gradientTextView.setText(addMarksToTexts(relativeLayout, gradientTextView, str, i2, dVar));
    }

    public void addItemBackgroundImg(com.jingdong.app.mall.home.r.e.d dVar, String str, View view, boolean z, boolean z2) {
        DarkMaskImageView generatorMaskImageView;
        int i2 = R.id.homefloor_module_bg;
        View findViewById = findViewById(i2);
        boolean z3 = true;
        if ((!TextUtils.isEmpty(str)) != true) {
            if (findViewById != null) {
                removeView(findViewById);
            }
            if (z) {
                onLoadItemBgImgFailed(view);
                return;
            }
            return;
        }
        if (findViewById instanceof DarkMaskImageView) {
            generatorMaskImageView = (DarkMaskImageView) findViewById;
        } else {
            generatorMaskImageView = MallFloorViewCommonFunc.generatorMaskImageView(getContext(), -1, -1);
            generatorMaskImageView.setId(i2);
        }
        ViewParent parent = generatorMaskImageView.getParent();
        if ((parent instanceof ViewGroup) && z2) {
            ((ViewGroup) parent).removeView(generatorMaskImageView);
        }
        if (generatorMaskImageView.getParent() == null) {
            addView(generatorMaskImageView);
        }
        generatorMaskImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        com.jingdong.app.mall.home.floor.ctrl.e.e(generatorMaskImageView, str, z);
        if (!dVar.useRoundBg && this.mRejectDowngrading && (!z2 || n.o(this.firstSkuImg) || this.mNeedWhiteBg)) {
            z3 = false;
        }
        if (z3) {
            setBackgroundColor(0);
        } else {
            setBackgroundColor(-1);
        }
        this.firstSkuImg = "";
    }

    public int addSeparationItemImgs(p.a aVar, com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, int i5) {
        if (this.mFloor == null || this.mPresenter == null) {
            return i4;
        }
        String[] y = fVar.y(aVar.Q);
        if (y == null) {
            return i4 + 1;
        }
        int length = y.length;
        this.firstSkuImg = length == 1 ? y[0] : "";
        aVar.R = ModuleCommonFunc.getSeparationItemImgsGap(aVar.R, i5);
        int imagesAndGapsWidth = (i5 - getImagesAndGapsWidth(aVar, length)) / 2;
        int i6 = i4;
        for (int i7 = 0; i7 < length; i7++) {
            DarkWhiteBgImageView separationItemImg = getSeparationItemImg(i2, i7, y[i7], aVar);
            separationItemImg.setContentDescription(fVar.O());
            renderImageViewScaleType(separationItemImg, i3);
            renderImageViewLayout(separationItemImg, aVar, i7, imagesAndGapsWidth, i6);
            i6++;
            separationItemImg.setId(i6);
            displaySeparationImg(separationItemImg, y[i7], i3, aVar);
            addSeparationImgToParent(separationItemImg);
            addMaskImg(fVar, aVar, separationItemImg, length, i2);
            addPriceLabel(fVar, separationItemImg, aVar, i7);
            if (this.mPresenter instanceof q) {
                this.mFloor.setOnClickJsonListener(separationItemImg, fVar, i7 + 1);
            }
            onAddSeparationImg(i7, separationItemImg);
        }
        return i6 + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[][] getAlignRule(p.a aVar, boolean z, int i2) {
        int[][] iArr = {new int[]{9, -1}, new int[]{10, -1}};
        if (z) {
            return iArr;
        }
        int i3 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[aVar.f9342j.ordinal()];
        if (i3 == 1 || i3 == 2 || i3 == 3) {
            iArr[0][0] = 1;
            iArr[0][1] = i2;
        }
        return iArr;
    }

    protected int getImagesAndGapsWidth(p.a aVar, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += getSeparationItemImgSize(aVar, i4).x;
            if (i4 != i2 - 1) {
                i3 += getImagesGapWidth(aVar, i4);
            }
        }
        return i3;
    }

    protected int getImagesGapWidth(p.a aVar, int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return aVar.R;
                }
                return aVar.T;
            }
            return aVar.S;
        }
        return aVar.R;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getSeparationImgMarginLeft(int i2, p.a aVar, int i3) {
        boolean z = i2 == 0;
        int i4 = aVar.D.x;
        int i5 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[aVar.f9342j.ordinal()];
        return (i5 == 1 || i5 == 2) ? z ? i3 : aVar.R : (i5 == 3 && !z) ? aVar.R : i4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Point getSeparationItemImgSize(p.a aVar, int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return new Point(aVar.K, aVar.L);
                }
                return new Point(aVar.O, aVar.P);
            }
            return new Point(aVar.M, aVar.N);
        }
        return new Point(aVar.K, aVar.L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSkuWithAnimation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAddSeparationImg(int i2, SimpleDraweeView simpleDraweeView) {
    }

    protected void onLoadItemBgImgFailed(View view) {
        view.setBackgroundDrawable(new JDPlaceholderDrawable(17));
    }

    protected void renderImageViewLayout(SimpleDraweeView simpleDraweeView, p.a aVar, int i2, int i3, int i4) {
        int[][] alignRule = getAlignRule(aVar, i2 == 0, i4);
        int separationImgMarginLeft = getSeparationImgMarginLeft(i2, aVar, i3);
        ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        a aVar2 = aVar.f9342j;
        Point point2 = aVar.D;
        MallFloorViewCommonFunc.getRuleByPosParam(aVar2, point2, alignRule, separationImgMarginLeft, point2.y, 0, 0, layoutParams2);
        simpleDraweeView.setLayoutParams(layoutParams2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        super.setBackgroundColor(i2);
    }

    protected void setDefaultDrawable(float f2) {
        setBackgroundDrawable(new RoundConerDrawable(-2236963, f2, 0));
    }

    public void setRejectDowngrading(boolean z) {
        this.mRejectDowngrading = z;
    }

    public static int addItemTitleAndSubTitle(com.jingdong.app.mall.home.r.e.f fVar, p.a aVar, boolean z, RelativeLayout relativeLayout, int i2, int i3, r rVar, int i4, boolean z2, int i5) {
        GradientTextView gradientTextView;
        final GradientTextView gradientTextView2;
        char c2;
        int i6;
        final RelativeLayout.LayoutParams layoutParams;
        if (fVar == null || aVar == null || aVar.f9341i == f.GONE) {
            return 0;
        }
        p.a.C0288a c0288a = aVar.Y;
        int i7 = i4 + 256;
        int i8 = i4 + 512;
        int i9 = i4 + 544;
        View findViewWithTag = relativeLayout.findViewWithTag(Integer.valueOf(i7));
        View findViewWithTag2 = relativeLayout.findViewWithTag(Integer.valueOf(i8));
        View findViewWithTag3 = relativeLayout.findViewWithTag(Integer.valueOf(i9));
        String c0 = fVar.c0();
        if (findViewWithTag != null) {
            findViewWithTag.setVisibility(4);
        }
        if (findViewWithTag2 != null) {
            findViewWithTag2.setVisibility(4);
        }
        addTitleBg(fVar, aVar, z, c0288a, relativeLayout);
        int[] iArr = c0288a.f9351k;
        if (iArr == null || iArr.length == 0) {
            c0288a.f9351k = new int[]{-13684945};
        }
        int[] iArr2 = c0288a.f9352l;
        if (iArr2 == null || iArr2.length == 0) {
            c0288a.f9352l = new int[]{-8684677};
        }
        int[] iArr3 = {9, 10};
        int i10 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_SUBTITLE_POS_ENUM[aVar.f9341i.ordinal()];
        if (i10 == 1 || i10 == 2) {
            iArr3[0] = 14;
        } else if (i10 == 3 || i10 == 4) {
            iArr3[0] = 14;
            iArr3[1] = 12;
        } else if (i10 == 5) {
            iArr3[1] = 12;
        }
        Point point2 = aVar.z;
        if (findViewWithTag == null) {
            gradientTextView = new GradientTextView(relativeLayout.getContext());
            gradientTextView.setTag(Integer.valueOf(i7));
        } else {
            com.jingdong.app.mall.home.o.a.f.n(findViewWithTag);
            gradientTextView = (GradientTextView) findViewWithTag;
        }
        GradientTextView gradientTextView3 = gradientTextView;
        int i11 = i3 + 1;
        gradientTextView3.setId(i3);
        MallFloorViewCommonFunc.setTextViewParams(relativeLayout.getContext(), gradientTextView3, c0288a.f9351k[0], aVar.I, -2, ViewCompat.MEASURED_SIZE_MASK, iArr3, point2, aVar.f9346n, aVar.U, false);
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftTopToRightBottom;
        gradientTextView3.setTextGradient(gradientType, c0288a.f9351k);
        if (aVar.s) {
            gradientTextView3.getPaint().setFakeBoldText(true);
        } else if (m.z(c0288a.f9351k) && fVar.p() != 0) {
            gradientTextView3.getPaint().setFakeBoldText(true);
        } else {
            gradientTextView3.getPaint().setFakeBoldText(false);
        }
        int[] iArr4 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_TITLE_GRAVITY;
        if (iArr4[aVar.f9343k.ordinal()] != 1) {
            gradientTextView3.setGravity(3);
        } else {
            gradientTextView3.setGravity(1);
        }
        setTextStr(relativeLayout, gradientTextView3, fVar.O(), c0288a.f9351k[0], aVar.f9339g);
        boolean z3 = !StringUtil.isEmpty("");
        if (gradientTextView3.getParent() == null) {
            relativeLayout.addView(gradientTextView3);
        }
        f fVar2 = aVar.f9341i;
        f fVar3 = f.ONLY_SUB_CENTER_BOTTOM;
        if (fVar2 == fVar3 || fVar2 == f.LEFT_BOTTOM_ONLY_SUB_TITLE) {
            gradientTextView3.setVisibility(8);
        } else if (!z3) {
            gradientTextView3.setVisibility(gradientTextView3.getText().length() != 0 ? 0 : 4);
            if (z2) {
                gradientTextView3.bringToFront();
            }
        } else {
            gradientTextView3.setVisibility(8);
        }
        addTitleIcon(fVar.i0(), relativeLayout, aVar.Z, aVar.a0, aVar.b0, gradientTextView3, i5);
        setMainTitleImg(aVar, relativeLayout, "", gradientTextView3, iArr3);
        addSloganText(relativeLayout, aVar.e0, aVar.Y.f9353m, gradientTextView3, fVar.W(), i5);
        TextPaint paint = gradientTextView3.getPaint();
        int i12 = aVar.C.y;
        f fVar4 = aVar.f9341i;
        if (fVar4 != fVar3 && fVar4 != f.LEFT_BOTTOM_ONLY_SUB_TITLE && aVar.d0 == c.OBEY_ENUM_SEPARATION_TITLE_SUBTITLE_POS_ENUM) {
            if (fVar4 != f.RIGHT_TO_TITLE) {
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                i12 = point2.y + ((int) ((fontMetrics.descent - fontMetrics.top) + 0.5f)) + aVar.C.y;
            } else {
                iArr3[0] = 1;
            }
        }
        if (findViewWithTag2 == null) {
            gradientTextView2 = new GradientTextView(relativeLayout.getContext());
            if (aVar.t) {
                gradientTextView2.getPaint().setFakeBoldText(true);
            }
            gradientTextView2.setTag(Integer.valueOf(i8));
        } else {
            com.jingdong.app.mall.home.o.a.f.n(findViewWithTag2);
            gradientTextView2 = (GradientTextView) findViewWithTag2;
        }
        int i13 = i11 + 1;
        gradientTextView2.setId(i11);
        int i14 = AnonymousClass5.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$SEPARATION_SUBTITLE_POS_ENUM[aVar.d0.ordinal()];
        if (i14 == 1) {
            c2 = 0;
            i6 = 4;
            iArr3[0] = 9;
            iArr3[1] = 10;
        } else if (i14 == 2) {
            c2 = 0;
            i6 = 4;
            iArr3[0] = 14;
            iArr3[1] = 10;
        } else if (i14 != 3) {
            i6 = 4;
            if (i14 != 4) {
                c2 = 0;
            } else {
                c2 = 0;
                iArr3[0] = 14;
                iArr3[1] = 12;
            }
        } else {
            c2 = 0;
            i6 = 4;
            iArr3[0] = 9;
            iArr3[1] = 12;
        }
        MallFloorViewCommonFunc.setTextViewParams(relativeLayout.getContext(), gradientTextView2, c0288a.f9352l[c2], aVar.J, -2, ViewCompat.MEASURED_SIZE_MASK, iArr3, new Point(aVar.C.x, i12), aVar.o, aVar.U);
        gradientTextView2.setTextGradient(gradientType, c0288a.f9352l);
        if (iArr4[aVar.f9344l.ordinal()] != 1) {
            gradientTextView2.setGravity(3);
        } else {
            gradientTextView2.setGravity(1);
        }
        ViewGroup.LayoutParams layoutParams2 = gradientTextView2.getLayoutParams();
        SimpleDraweeView simpleDraweeView = null;
        if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
            com.jingdong.app.mall.home.o.a.f.n(layoutParams2);
            layoutParams = (RelativeLayout.LayoutParams) layoutParams2;
            if (layoutParams.leftMargin > 0) {
                layoutParams.leftMargin = aVar.C.x;
            }
        } else {
            layoutParams = null;
        }
        Point point3 = aVar.G;
        if (findViewWithTag3 == null && !TextUtils.isEmpty(c0) && point3 != null) {
            simpleDraweeView = new HomeDraweeView(relativeLayout.getContext());
            simpleDraweeView.setTag(Integer.valueOf(i9));
        }
        if (findViewWithTag3 != null && !TextUtils.isEmpty(c0) && point3 != null) {
            com.jingdong.app.mall.home.o.a.f.n(findViewWithTag3);
            simpleDraweeView = (SimpleDraweeView) findViewWithTag3;
        }
        if (findViewWithTag3 != null) {
            findViewWithTag3.setTag(R.id.mallfloor_check_key, "");
            findViewWithTag3.setVisibility(i6);
        }
        if (simpleDraweeView != null && !TextUtils.isEmpty(c0) && layoutParams != null && layoutParams.leftMargin > 0) {
            final Point point4 = new Point(aVar.G.x, com.jingdong.app.mall.home.floor.common.d.d(2) + i12);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(26), com.jingdong.app.mall.home.floor.common.d.d(26));
            MallFloorViewCommonFunc.addRuleAndMarginToParams(iArr3, point4, layoutParams3);
            simpleDraweeView.setLayoutParams(layoutParams3);
            if (simpleDraweeView.getParent() == null) {
                relativeLayout.addView(simpleDraweeView);
            }
            simpleDraweeView.setTag(R.id.mallfloor_check_key, c0);
            simpleDraweeView.setId(i13);
            simpleDraweeView.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.g(c0, simpleDraweeView, com.jingdong.app.mall.home.floor.ctrl.e.f9402h, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon.1
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    Object tag = view.getTag(R.id.mallfloor_check_key);
                    if (tag == null || !tag.equals(str)) {
                        return;
                    }
                    RelativeLayout.LayoutParams layoutParams4 = layoutParams;
                    if (layoutParams4.leftMargin <= 0) {
                        return;
                    }
                    layoutParams4.leftMargin = point4.x + com.jingdong.app.mall.home.floor.common.d.d(26) + com.jingdong.app.mall.home.floor.common.d.d(3);
                    gradientTextView2.setLayoutParams(layoutParams);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            });
        }
        if (aVar.f9341i == f.RIGHT_TO_TITLE) {
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            }
            layoutParams.addRule(1, gradientTextView3.getId());
            gradientTextView2.setLayoutParams(layoutParams);
        }
        setTextStr(relativeLayout, gradientTextView2, fVar.d0(), c0288a.f9352l[0], aVar.f9340h);
        if (gradientTextView2.getParent() == null) {
            relativeLayout.addView(gradientTextView2);
        }
        f fVar5 = aVar.f9341i;
        if (fVar5 != f.CENTER_BOTTOM_ONLY_MAIN_TITLE && fVar5 != f.CENTER_TOP_ONLY_MAIN_TITLE) {
            gradientTextView2.setVisibility(gradientTextView2.getText().length() != 0 ? 0 : 4);
            if (z2) {
                gradientTextView2.bringToFront();
            }
        } else {
            gradientTextView2.setVisibility(8);
        }
        Paint.FontMetrics fontMetrics2 = gradientTextView2.getPaint().getFontMetrics();
        return i12 + ((int) ((fontMetrics2.descent - fontMetrics2.top) + 0.5f));
    }
}
