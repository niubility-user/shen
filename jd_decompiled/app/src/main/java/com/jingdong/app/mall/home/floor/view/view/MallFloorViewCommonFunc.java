package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.a;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;

/* loaded from: classes4.dex */
public class MallFloorViewCommonFunc {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.view.view.MallFloorViewCommonFunc$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM;

        static {
            int[] iArr = new int[a.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM = iArr;
            try {
                iArr[a.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.RIGHT_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.CENTER_TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.CENTER_BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.LEFT_BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.RIGHT_BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[a.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static void addRuleAndMarginToParams(int[] iArr, Point point2, RelativeLayout.LayoutParams layoutParams) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 : iArr) {
            if (i6 == 9) {
                i2 = point2.x;
            }
            if (i6 == 10) {
                i3 = point2.y;
            }
            if (i6 == 11) {
                i4 = point2.x;
            }
            if (i6 == 12) {
                i5 = point2.y;
            }
            layoutParams.addRule(i6);
        }
        layoutParams.setMargins(i2, i3, i4, i5);
    }

    public static ViewGroup.LayoutParams checkPointParams(View view, Point point2) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return null;
        }
        int i2 = point2.x;
        if (i2 == layoutParams.width && point2.y == layoutParams.height) {
            return null;
        }
        layoutParams.width = i2;
        layoutParams.height = point2.y;
        return layoutParams;
    }

    public static DarkMaskImageView generatorMaskImageView(Context context, int i2, int i3) {
        DarkMaskImageView darkMaskImageView = new DarkMaskImageView(context);
        darkMaskImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        darkMaskImageView.setLayoutParams(new RelativeLayout.LayoutParams(i2, i3));
        return darkMaskImageView;
    }

    public static int[][] getRuleByPosParam(a aVar, Point point2, int[][] iArr, RelativeLayout.LayoutParams layoutParams) {
        return getRuleByPosParam(aVar, point2, iArr, point2.x, point2.y, 0, 0, layoutParams);
    }

    public static void setTextViewParams(Context context, GradientTextView gradientTextView, int i2, int i3, int i4, int i5, int[] iArr, Point point2, float f2, int i6) {
        setTextViewParams(context, gradientTextView, i2, i3, i4, i5, iArr, point2, f2, i6, true);
    }

    public static void setTextViewParams(Context context, GradientTextView gradientTextView, int i2, int i3, int i4, int i5, int[] iArr, Point point2, float f2, int i6, boolean z) {
        gradientTextView.setTextColor(i2);
        Resources resources = context.getResources();
        int i7 = R.dimen.home_title_topbottom_padding;
        gradientTextView.setPadding(0, (int) resources.getDimension(i7), 0, (int) context.getResources().getDimension(i7));
        gradientTextView.setTextSize(0, f2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        addRuleAndMarginToParams(iArr, point2, layoutParams);
        int i8 = layoutParams.leftMargin;
        if (i8 > 0) {
            if (i3 > 0) {
                layoutParams.width = i3 + i8;
            }
            gradientTextView.setPadding(i8, gradientTextView.getPaddingTop(), gradientTextView.getPaddingRight(), gradientTextView.getPaddingBottom());
            layoutParams.leftMargin = 0;
        }
        gradientTextView.setBackgroundColor(i5);
        gradientTextView.setMaxLines(i6);
        gradientTextView.setEllipsize(z ? TextUtils.TruncateAt.END : null);
        gradientTextView.setLayoutParams(layoutParams);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int[][] getRuleByPosParam(a aVar, Point point2, int[][] iArr, int i2, int i3, int i4, int i5, RelativeLayout.LayoutParams layoutParams) {
        if (iArr == null) {
            iArr = new int[][]{new int[]{9, -1}, new int[]{10, -1}};
        }
        switch (AnonymousClass1.$SwitchMap$com$jingdong$app$mall$home$floor$common$utils$Enums$OVERLAY_FLOOR_POS_ENUM[aVar.ordinal()]) {
            case 2:
                iArr[0][0] = 11;
                i4 = point2.x;
                i2 = 0;
                break;
            case 3:
                i4 = 0;
                i5 = 0;
                break;
            case 4:
            case 5:
                iArr[1][0] = 12;
                i5 = point2.y;
                i3 = 0;
                break;
            case 6:
                iArr[0][0] = 11;
                iArr[1][0] = 12;
                i4 = point2.x;
                i5 = point2.y;
                i2 = 0;
                i3 = 0;
                break;
            case 7:
                iArr[0][0] = 13;
                iArr[1][0] = -1;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                break;
        }
        if (layoutParams != null) {
            for (int[] iArr2 : iArr) {
                layoutParams.addRule(iArr2[0], iArr2[1]);
            }
            layoutParams.setMargins(i2, i3, i4, i5);
        }
        return iArr;
    }
}
