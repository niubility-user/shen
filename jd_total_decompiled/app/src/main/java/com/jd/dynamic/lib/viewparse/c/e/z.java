package com.jd.dynamic.lib.viewparse.c.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.yoga.android.YogaLayout;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class z extends p0<View> {

    /* loaded from: classes13.dex */
    class a implements IImageLoader.ImageRequestListener<Bitmap> {
        final /* synthetic */ View a;

        a(z zVar, View view) {
            this.a = view;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Bitmap bitmap) {
            if (bitmap != null) {
                this.a.setBackgroundDrawable(new BitmapDrawable(this.a.getContext().getResources(), bitmap));
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements IImageLoader.ImageRequestListener<Drawable> {
        final /* synthetic */ View a;

        b(z zVar, View view) {
            this.a = view;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(Drawable drawable) {
            if (drawable != null) {
                this.a.setBackgroundDrawable(drawable);
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.a.setBackgroundDrawable(null);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.a.setBackgroundDrawable(null);
        }
    }

    private float f(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0f;
        }
        try {
            return DPIUtil.dip2px(Float.parseFloat(str));
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    private String g(View view, int i2) {
        if (view == null) {
            return null;
        }
        Object tag = view.getTag(i2);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a6, code lost:
        if (r9.equals(com.jd.dynamic.DYConstants.DY_I_45) == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void h(GradientDrawable gradientDrawable, String str, String str2) {
        String[] split;
        GradientDrawable.Orientation orientation;
        if (str == null || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(split));
        char c2 = 0;
        if (arrayList.size() == 1) {
            arrayList.add(arrayList.get(0));
        }
        int[] iArr = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            try {
                iArr[i2] = Color.parseColor((String) it.next());
                i2++;
            } catch (Exception unused) {
                iArr[i2] = -1;
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            str2.hashCode();
            switch (str2.hashCode()) {
                case R2.attr.reactiveGuide_valueId /* 1665 */:
                    break;
                case R2.attr.singleLine /* 1815 */:
                    if (str2.equals(DYConstants.DY_I_90)) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 48723:
                    if (str2.equals(DYConstants.DY_I_135)) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 48873:
                    if (str2.equals(DYConstants.DY_I_180)) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 49653:
                    if (str2.equals(DYConstants.DY_I_225)) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 49803:
                    if (str2.equals(DYConstants.DY_I_270)) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 50583:
                    if (str2.equals(DYConstants.DY_I_315)) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    orientation = GradientDrawable.Orientation.BL_TR;
                    break;
                case 1:
                    orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
                case 2:
                    orientation = GradientDrawable.Orientation.BR_TL;
                    break;
                case 3:
                    orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
                case 4:
                    orientation = GradientDrawable.Orientation.TR_BL;
                    break;
                case 5:
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
                case 6:
                    orientation = GradientDrawable.Orientation.TL_BR;
                    break;
            }
            gradientDrawable.setOrientation(orientation);
            gradientDrawable.setColors(iArr);
        }
        orientation = GradientDrawable.Orientation.LEFT_RIGHT;
        gradientDrawable.setOrientation(orientation);
        gradientDrawable.setColors(iArr);
    }

    private void i(View view, int i2, Object obj) {
        if (view == null) {
            return;
        }
        view.setTag(i2, obj);
    }

    private void j(String str, View view) {
        DynamicSdk.getEngine().getImageLoader().loadNineImage(str, new b(this, view));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01af  */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(HashMap<String, String> hashMap, View view) {
        boolean z;
        boolean z2;
        boolean z3;
        HashMap<String, String> hashMap2;
        String str;
        char c2;
        int i2;
        ResultEntity resultEntity;
        if (hashMap == null) {
            return;
        }
        String str2 = hashMap.containsKey("borderWidth") ? hashMap.get("borderWidth") : "";
        String str3 = hashMap.containsKey("borderColor") ? hashMap.get("borderColor") : "";
        String str4 = hashMap.containsKey("borderRadius") ? hashMap.get("borderRadius") : "";
        String str5 = hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_L) ? hashMap.get(DYConstants.DY_BORDER_RADIUS_T_L) : "";
        String str6 = hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_T_R) ? hashMap.get(DYConstants.DY_BORDER_RADIUS_T_R) : "";
        String str7 = hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_L) ? hashMap.get(DYConstants.DY_BORDER_RADIUS_B_L) : "";
        String str8 = hashMap.containsKey(DYConstants.DY_BORDER_RADIUS_B_R) ? hashMap.get(DYConstants.DY_BORDER_RADIUS_B_R) : "";
        GradientDrawable gradientDrawable = (view == null || !(view.getBackground() instanceof GradientDrawable)) ? new GradientDrawable() : (GradientDrawable) view.getBackground();
        if (hashMap.containsKey(DYConstants.DY_BG_COLOR)) {
            String str9 = hashMap.get(DYConstants.DY_BG_COLOR);
            if (TextUtils.isEmpty(str9)) {
                gradientDrawable.setColor(Color.parseColor("#00000000"));
            } else {
                try {
                    gradientDrawable.setColor(Color.parseColor(str9));
                } catch (Exception unused) {
                }
            }
            z = true;
        } else {
            z = false;
        }
        boolean z4 = view instanceof YogaLayout;
        if (z4) {
            String str10 = hashMap.get(DYConstants.DY_BG_COLOR_LIST);
            if (TextUtils.isEmpty(str10)) {
                str10 = g(view, R.id.dynamic_drawable_color_list);
            } else {
                i(view, R.id.dynamic_drawable_color_list, str10);
            }
            String str11 = hashMap.get(DYConstants.DY_ANGLE);
            if (TextUtils.isEmpty(str11)) {
                str11 = g(view, R.id.dynamic_drawable_color_angel);
                z2 = z;
            } else {
                z2 = z;
                i(view, R.id.dynamic_drawable_color_angel, str11);
            }
            if (!TextUtils.isEmpty(str10)) {
                h(gradientDrawable, str10.replaceAll(DYConstants.DY_LEFT_BRACKETS, "").replaceAll(DYConstants.DY_RIGHT_BRACKETS, ""), str11);
                z3 = true;
                if (TextUtils.isEmpty(str5) || !TextUtils.isEmpty(str6) || !TextUtils.isEmpty(str7) || !TextUtils.isEmpty(str8)) {
                    gradientDrawable.setCornerRadii(new float[]{f(str5), f(str5), f(str6), f(str6), f(str8), f(str8), f(str7), f(str7)});
                    z3 = true;
                } else if (!TextUtils.isEmpty(str4) && !DynamicUtils.isElOrKnownSymbol(str4)) {
                    try {
                        gradientDrawable.setCornerRadius(DPIUtil.dip2px(Float.parseFloat(str4)));
                    } catch (Exception e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidBackgroundParse parseAttribute borderRadius error", com.jd.dynamic.lib.utils.m.j(this.a), com.jd.dynamic.lib.utils.m.O(this.a), e2);
                    }
                    z3 = true;
                }
                if (view != null) {
                    return;
                }
                if (TextUtils.isEmpty(str3)) {
                    Object tag = view.getTag(R.id.dynamic_drawable_border_color);
                    str3 = tag instanceof String ? (String) tag : null;
                } else {
                    view.setTag(R.id.dynamic_drawable_border_color, str3);
                }
                if (TextUtils.isEmpty(str2)) {
                    Object tag2 = view.getTag(R.id.dynamic_drawable_border_width);
                    str2 = tag2 instanceof String ? (String) tag2 : null;
                } else {
                    view.setTag(R.id.dynamic_drawable_border_width, str2);
                }
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2) && !DynamicUtils.isElOrKnownSymbol(str3) && !DynamicUtils.isElOrKnownSymbol(str2)) {
                    try {
                        gradientDrawable.setStroke(DPIUtil.dip2px(Float.parseFloat(str2)), Color.parseColor(str3));
                    } catch (Exception e3) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidBackgroundParse parseAttribute borderColor or borderWidth error", com.jd.dynamic.lib.utils.m.j(this.a), com.jd.dynamic.lib.utils.m.O(this.a), e3);
                    }
                    z3 = true;
                }
                if (view.getBackground() == null || z3) {
                    view.setBackgroundDrawable(gradientDrawable);
                }
                if (!(TextUtils.isEmpty(str5) && TextUtils.isEmpty(str6) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str8)) && z4) {
                    ((YogaLayout) view).setCorner((int) f(str5), (int) f(str6), (int) f(str7), (int) f(str8));
                } else if (!TextUtils.isEmpty(str4) && z4) {
                    try {
                        int dip2px = DPIUtil.dip2px(Float.parseFloat(str4));
                        ((YogaLayout) view).setCorner(dip2px, dip2px, dip2px, dip2px);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidBackgroundParse parseAttribute bgImage error", null, null, e4);
                    }
                }
                if (z4) {
                    int i3 = R.id.dynamic_drawable_bgimage;
                    Object tag3 = view.getTag(i3);
                    hashMap2 = hashMap;
                    if ((hashMap2.containsKey("bgImage") && !TextUtils.isEmpty(hashMap2.get("bgImage")) && !TextUtils.equals(DYConstants.DY_NULL_STR, hashMap2.get("bgImage"))) || (tag3 instanceof String)) {
                        String str12 = hashMap2.get("bgImage");
                        if (TextUtils.isEmpty(str12)) {
                            str12 = (String) tag3;
                        } else {
                            view.setTag(i3, str12);
                        }
                        if (str12.startsWith("http")) {
                            DynamicSdk.getEngine().getImageLoader().loadImage(str12, new a(this, view));
                        } else if (str12.startsWith(DYConstants.DY_ASSETS)) {
                            DynamicTemplateEngine dynamicTemplateEngine = this.a;
                            if (dynamicTemplateEngine != null && (resultEntity = dynamicTemplateEngine.entity) != null && !TextUtils.isEmpty(resultEntity.zipDir)) {
                                j(DYConstants.DY_FILE_PATH_START + this.a.entity.zipDir + File.separator + str12, view);
                            }
                        } else {
                            DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
                            Context context = view.getContext();
                            DynamicTemplateEngine dynamicTemplateEngine3 = this.a;
                            view.setBackgroundResource(com.jd.dynamic.lib.viewparse.d.c(dynamicTemplateEngine2, str12, context, dynamicTemplateEngine3 == null ? null : dynamicTemplateEngine3.mPackageName));
                        }
                        if (!(TextUtils.isEmpty(str5) && TextUtils.isEmpty(str6) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str8)) && z4) {
                            ((YogaLayout) view).setCorner((int) f(str5), (int) f(str6), (int) f(str7), (int) f(str8));
                        } else if (!TextUtils.isEmpty(str4) && z4) {
                            try {
                                if (!DynamicUtils.isElOrKnownSymbol(str4)) {
                                    int dip2px2 = DPIUtil.dip2px(Float.parseFloat(str4));
                                    ((YogaLayout) view).setCorner(dip2px2, dip2px2, dip2px2, dip2px2);
                                }
                            } catch (Exception e5) {
                                e5.printStackTrace();
                                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidBackgroundParse parseAttribute bgImage error", com.jd.dynamic.lib.utils.m.j(this.a), com.jd.dynamic.lib.utils.m.O(this.a), e5);
                            }
                        }
                    }
                } else {
                    hashMap2 = hashMap;
                }
                if (hashMap2.containsKey("alpha") && !TextUtils.isEmpty(hashMap2.get("alpha"))) {
                    String str13 = hashMap2.get("alpha");
                    try {
                        if (!DynamicUtils.isElOrKnownSymbol(str13)) {
                            view.setAlpha(Float.parseFloat(str13));
                        }
                    } catch (Exception e6) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidBackgroundParse parseAttribute alpha error", com.jd.dynamic.lib.utils.m.j(this.a), com.jd.dynamic.lib.utils.m.O(this.a), e6);
                    }
                }
                if (!hashMap2.containsKey("visibility") || (str = hashMap2.get("visibility")) == null || TextUtils.isEmpty(str)) {
                    return;
                }
                str.hashCode();
                switch (str.hashCode()) {
                    case 48:
                        if (str.equals("0")) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 49:
                        if (str.equals("1")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 50:
                        if (str.equals("2")) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        i2 = 4;
                        break;
                    case 1:
                    default:
                        i2 = 0;
                        break;
                    case 2:
                        i2 = 8;
                        break;
                }
                com.jd.dynamic.lib.utils.m.r(view, i2);
                return;
            }
        } else {
            z2 = z;
        }
        z3 = z2;
        if (TextUtils.isEmpty(str5)) {
        }
        gradientDrawable.setCornerRadii(new float[]{f(str5), f(str5), f(str6), f(str6), f(str8), f(str8), f(str7), f(str7)});
        z3 = true;
        if (view != null) {
        }
    }
}
