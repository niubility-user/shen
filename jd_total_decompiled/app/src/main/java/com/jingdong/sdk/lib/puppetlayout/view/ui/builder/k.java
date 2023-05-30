package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.view.ui.JDImageWidget;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ColorUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class k extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private JDImageWidget f15249k;

    /* renamed from: l  reason: collision with root package name */
    private String f15250l = "0";

    /* renamed from: m  reason: collision with root package name */
    private int f15251m = -1;

    /* renamed from: n  reason: collision with root package name */
    private int f15252n = -1;
    private boolean o = false;
    private float p = -1.0f;
    private float q = -1.0f;
    private String r = null;
    private String s = "1";

    private String u(SimpleDraweeView simpleDraweeView, HashMap<String, String> hashMap) {
        String str;
        if (hashMap != null && hashMap.get("placeOldImage") != null && hashMap.get("placeOldImage").equals("1")) {
            this.f15250l = "1";
            str = (String) simpleDraweeView.getTag(R.id.com_jd_sdk_lib_puppetlayout_jdimage_url);
        } else {
            this.f15250l = "0";
            str = null;
        }
        if (TextUtils.isEmpty(str) || !Fresco.getImagePipeline().isInBitmapMemoryCache(Uri.parse(str)) || simpleDraweeView.getController() == null) {
            return null;
        }
        return str;
    }

    private String v() {
        if (this.f15249k == null) {
            return null;
        }
        String str = "1".equals(this.f15250l) ? (String) this.f15249k.getTag(R.id.com_jd_sdk_lib_puppetlayout_jdimage_url) : null;
        if (TextUtils.isEmpty(str) || !Fresco.getImagePipeline().isInBitmapMemoryCache(Uri.parse(str)) || this.f15249k.getController() == null) {
            return null;
        }
        return str;
    }

    private boolean w(HashMap<String, String> hashMap) {
        return (hashMap == null || hashMap.get("autoPlay") == null || !hashMap.get("autoPlay").equals("1")) ? false : true;
    }

    private void x(GenericDraweeHierarchy genericDraweeHierarchy) {
        if (genericDraweeHierarchy == null) {
            return;
        }
        String str = this.s;
        if (!TextUtils.isEmpty(str)) {
            if ("0".equals(str)) {
                genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                return;
            } else if ("1".equals(str)) {
                genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
                return;
            } else if ("3".equals(str)) {
                genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER);
                return;
            } else if ("2".equals(str)) {
                genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
                return;
            } else {
                return;
            }
        }
        genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
    }

    private void y(SimpleDraweeView simpleDraweeView, String str, HashMap<String, String> hashMap) {
        AbstractDraweeController build;
        String u = u(simpleDraweeView, hashMap);
        if (!TextUtils.isEmpty(u)) {
            build = Fresco.newDraweeControllerBuilder().setLowResImageRequest(ImageRequest.fromUri(u)).setOldController(simpleDraweeView.getController()).setUri(Uri.parse(str)).setAutoPlayAnimations(w(hashMap)).build();
        } else {
            build = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setAutoPlayAnimations(w(hashMap)).build();
        }
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        if (hierarchy != null) {
            RoundingParams fromCornersRadius = RoundingParams.fromCornersRadius(0.0f);
            int i2 = this.f15251m;
            if (i2 != -1) {
                fromCornersRadius.setRoundAsCircle(i2 == 1);
            }
            float f2 = this.p;
            if (f2 != -1.0f) {
                fromCornersRadius.setBorderWidth(f2);
            }
            if (this.o) {
                fromCornersRadius.setBorderColor(this.f15252n);
            }
            float f3 = this.q;
            if (f3 != -1.0f) {
                fromCornersRadius.setCornersRadius(f3);
            }
            hierarchy.setRoundingParams(fromCornersRadius);
            if (!TextUtils.isEmpty(u)) {
                hierarchy.setPlaceholderImage((Drawable) null);
                hierarchy.setFadeDuration(0);
            } else {
                z(hierarchy);
            }
            x(hierarchy);
            simpleDraweeView.setHierarchy(hierarchy);
        }
        simpleDraweeView.setController(build);
        simpleDraweeView.setTag(R.id.com_jd_sdk_lib_puppetlayout_jdimage_url, str);
    }

    private void z(GenericDraweeHierarchy genericDraweeHierarchy) {
        String str;
        String str2;
        if (genericDraweeHierarchy == null || TextUtils.isEmpty(this.r)) {
            return;
        }
        String[] split = this.r.split("/");
        String packageName = this.f15249k.getContext().getPackageName();
        if (split.length >= 2) {
            str = split[1];
            str2 = split[0];
        } else {
            str = split.length == 1 ? split[0] : "";
            str2 = packageName;
        }
        int identifier = this.f15249k.getContext().getResources().getIdentifier(str, "drawable", str2);
        if (identifier != 0) {
            genericDraweeHierarchy.setPlaceholderImage(identifier, ScalingUtils.ScaleType.FOCUS_CROP);
            genericDraweeHierarchy.setPlaceholderImageFocusPoint(new PointF(0.5f, 0.5f));
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        JDImageWidget jDImageWidget = new JDImageWidget(context);
        this.f15249k = jDImageWidget;
        this.a = jDImageWidget;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        char c2;
        GenericDraweeHierarchy hierarchy;
        GenericDraweeHierarchy hierarchy2;
        if (super.q(str, str2, str3)) {
            return true;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1348102103:
                if (str.equals("roundBorderColor")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -1329817972:
                if (str.equals("roundBorderWidth")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case -859610604:
                if (str.equals("imageUrl")) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case 114148:
                if (str.equals("src")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case 109250890:
                if (str.equals("scale")) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case 387575632:
                if (str.equals("roundAsCircle")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case 598246771:
                if (str.equals("placeholder")) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case 809179125:
                if (str.equals("roundCornerRadius")) {
                    c2 = 7;
                    break;
                }
                c2 = '\uffff';
                break;
            case 815053360:
                if (str.equals("contentModel")) {
                    c2 = '\b';
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
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        this.f15252n = Color.parseColor(ColorUtils.translate2ArgbColor(str2));
                        this.o = true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (this.o) {
                    try {
                        GenericDraweeHierarchy hierarchy3 = this.f15249k.getHierarchy();
                        if (hierarchy3 != null && hierarchy3.getRoundingParams() != null) {
                            RoundingParams roundingParams = hierarchy3.getRoundingParams();
                            roundingParams.setBorderColor(this.f15252n);
                            hierarchy3.setRoundingParams(roundingParams);
                            break;
                        }
                    } catch (Exception e3) {
                        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                            e3.printStackTrace();
                            break;
                        }
                    }
                }
                break;
            case 1:
                if (!"0".equals(str2)) {
                    try {
                        this.p = LayoutUtils.getDpValue(str2);
                        GenericDraweeHierarchy hierarchy4 = this.f15249k.getHierarchy();
                        if (hierarchy4 != null && hierarchy4.getRoundingParams() != null) {
                            RoundingParams roundingParams2 = hierarchy4.getRoundingParams();
                            float f2 = this.p;
                            if (f2 != -1.0f) {
                                roundingParams2.setBorderWidth(f2);
                            }
                            hierarchy4.setRoundingParams(roundingParams2);
                            break;
                        }
                    } catch (Exception e4) {
                        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                            e4.printStackTrace();
                            break;
                        }
                    }
                }
                break;
            case 2:
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        for (String str4 : str2.split(DYConstants.DY_REGEX_COMMA)) {
                            if (!TextUtils.isEmpty(str4)) {
                                if (str4.startsWith("url:")) {
                                    hashMap.put("url", str4.substring(4, str4.length()));
                                } else {
                                    String[] split = str4.split(":");
                                    if (split.length == 2 && split[0] != null && split[1] != null) {
                                        hashMap.put(split[0], split[1]);
                                    }
                                }
                            }
                        }
                        String str5 = hashMap.get("type");
                        if (!TextUtils.isEmpty(str5)) {
                            String str6 = "";
                            if ("local".equals(str5)) {
                                String str7 = hashMap.get("android");
                                if (!TextUtils.isEmpty(str7)) {
                                    String[] split2 = str7.split("/");
                                    String packageName = this.f15249k.getContext().getPackageName();
                                    if (split2.length >= 2) {
                                        str6 = split2[1];
                                        packageName = split2[0];
                                    } else if (split2.length == 1) {
                                        str6 = split2[0];
                                    }
                                    int identifier = this.f15249k.getContext().getResources().getIdentifier(str6, "drawable", packageName);
                                    if (identifier != 0) {
                                        String str8 = hashMap.get("androidScaleType");
                                        if (!TextUtils.isEmpty(str8)) {
                                            if ("1".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.FIT_XY);
                                            } else if ("2".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.FIT_START);
                                            } else if ("3".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                            } else if ("4".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.FIT_END);
                                            } else if ("5".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.CENTER);
                                            } else if ("6".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                            } else if ("7".equals(str8)) {
                                                this.f15249k.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                            }
                                        }
                                        this.f15249k.setImageResource(identifier);
                                        break;
                                    }
                                }
                            } else if ("net".equals(str5)) {
                                String str9 = hashMap.get("url");
                                if (str9 != null) {
                                    str6 = str9;
                                }
                                y(this.f15249k, str6, hashMap);
                                break;
                            } else if ("unify".equals(str5)) {
                                if (!TextUtils.isEmpty(hashMap.get("iconId"))) {
                                    UnIconConfigHelper.displayIcon(hashMap.get("iconId"), this.f15249k);
                                    break;
                                }
                            } else {
                                "domain".equals(str5);
                                break;
                            }
                        }
                    }
                } catch (Exception e5) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e5.printStackTrace();
                        break;
                    }
                }
                break;
            case 3:
                this.f15249k.b(str2);
                break;
            case 4:
                this.f15249k.a(str2);
                break;
            case 5:
                this.f15251m = "1".equals(str2) ? 1 : 0;
                GenericDraweeHierarchy hierarchy5 = this.f15249k.getHierarchy();
                if (hierarchy5 != null && hierarchy5.getRoundingParams() != null) {
                    RoundingParams roundingParams3 = hierarchy5.getRoundingParams();
                    int i2 = this.f15251m;
                    if (i2 != -1) {
                        roundingParams3.setRoundAsCircle(i2 == 1);
                    }
                    hierarchy5.setRoundingParams(roundingParams3);
                    break;
                }
                break;
            case 6:
                if (!TextUtils.isEmpty(str2)) {
                    HashMap hashMap2 = new HashMap();
                    for (String str10 : str2.split(DYConstants.DY_REGEX_COMMA)) {
                        if (!TextUtils.isEmpty(str10)) {
                            String[] split3 = str10.split(":");
                            if (split3.length == 2 && split3[0] != null && split3[1] != null) {
                                hashMap2.put(split3[0], split3[1]);
                            }
                        }
                    }
                    String str11 = (String) hashMap2.get("android");
                    this.r = str11;
                    if (!TextUtils.isEmpty(str11) && (hierarchy = this.f15249k.getHierarchy()) != null) {
                        if (!TextUtils.isEmpty(v())) {
                            hierarchy.setPlaceholderImage((Drawable) null);
                            break;
                        } else {
                            z(hierarchy);
                            break;
                        }
                    }
                }
                break;
            case 7:
                if (!"0".equals(str2)) {
                    try {
                        this.q = LayoutUtils.getDpValue(str2);
                        GenericDraweeHierarchy hierarchy6 = this.f15249k.getHierarchy();
                        if (hierarchy6 != null && hierarchy6.getRoundingParams() != null) {
                            RoundingParams roundingParams4 = hierarchy6.getRoundingParams();
                            float f3 = this.q;
                            if (f3 != -1.0f) {
                                roundingParams4.setCornersRadius(f3);
                            }
                            hierarchy6.setRoundingParams(roundingParams4);
                            break;
                        }
                    } catch (Exception e6) {
                        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                            e6.printStackTrace();
                            break;
                        }
                    }
                }
                break;
            case '\b':
                this.s = str2;
                if (!TextUtils.isEmpty(str2) && (hierarchy2 = this.f15249k.getHierarchy()) != null) {
                    x(hierarchy2);
                    break;
                }
                break;
        }
        return true;
    }
}
