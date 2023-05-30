package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.annotation.ColorInt;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDMtaEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareFurnitureInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareJdServerPlusEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYanBaoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.views.CustomTypefaceSpan;
import com.jingdong.app.mall.bundle.styleinfoview.views.VerticalImageSpan;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.entity.productdetail.PdSizeGuide;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppUtil;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.utils.DPIUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import jpbury.t;

/* loaded from: classes3.dex */
public class PDUtils extends PDHelper {
    public static final String APPLET_SHARE_IMAGE_NAME = "lib_pd_applet_share_image.png";
    public static final String CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    private static final float[] CORNER_RIGHT_UP;
    public static final String DETAIL_POP_LAYER = "DETAILPOPLAYER";
    private static final int GIFT_RADIUS;
    public static final String JDDD_TOAST_KEY_COUNT_NEW = "jddd_toast_key_count_new";
    public static final String JDDD_TOAST_KEY_TIME_NEW = "jddd_toast_key_time_new";
    public static final String SHARE_BIG_IMAGE_NAME = "lib_pd_share_image.png";
    private static final float[] corner;
    public static final float[] cornerDown;
    private static final float[] cornerUp;
    public static boolean is_JX = false;
    public static boolean is_SCROLL = true;
    private static JDLocationCacheOption mLocationCacheOption = null;
    private static String mServerFurniture = null;
    private static String mServerJdSerPlus = null;
    private static String mServerYanbao = null;
    static long managerKey = 0;
    public static final int pluginVersion = 90200;
    public static final int radius;
    public static int screenWidth;
    static long timeClick;

    static {
        int dip2px = dip2px(12.0f);
        radius = dip2px;
        int dip2px2 = dip2px(21.0f);
        GIFT_RADIUS = dip2px2;
        float f2 = dip2px;
        corner = new float[]{f2, f2, f2, f2, f2, f2, f2, f2};
        cornerDown = new float[]{0.0f, 0.0f, 0.0f, 0.0f, f2, f2, f2, f2};
        cornerUp = new float[]{f2, f2, f2, f2, 0.0f, 0.0f, 0.0f, 0.0f};
        float f3 = dip2px2;
        CORNER_RIGHT_UP = new float[]{0.0f, 0.0f, f3, f3, f3, f3, 0.0f, 0.0f};
        timeClick = 0L;
    }

    public static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f2 = ((width * 1.0f) / 5.0f) / width2;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f2, f2, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e2) {
            e2.getStackTrace();
            return null;
        }
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        String str;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, "qemu.hw.mainkeys");
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        if ("1".equals(str)) {
            return false;
        }
        if ("0".equals(str)) {
            return true;
        }
        return z;
    }

    public static void clickFamilyJsonParam(String str, String str2, String str3, String str4, String str5, ProductDetailEntity productDetailEntity) {
        try {
            if (Log.D) {
                Log.d("PDHelper", "eventId=" + str2 + " eventParam=" + str4 + " pageParam=");
            }
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), str2, str4, "onClick", str, str, str3, null, str5, productDetailEntity.getShopId(), "", "", productDetailEntity.getSkuTag(), null);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static byte[] compressBitmap2Bytes(Bitmap bitmap, int i2, int i3) {
        byte[] bArr = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            if ((bArr.length == 0 || bArr.length > i2) && i3 > 0) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                i3 -= 10;
            }
        }
        return bArr;
    }

    public static Bitmap convertViewToBitmap(int i2, View view) {
        if (view != null && view.getWidth() != 0) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(i2);
                canvas.translate(-view.getScrollX(), -view.getScrollY());
                view.draw(canvas);
                return createBitmap;
            } catch (Throwable th) {
                if (Log.D) {
                    Log.d(t.f20145j, th.getMessage());
                }
            }
        }
        return null;
    }

    public static Bitmap createBitmap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(str);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return null;
        }
    }

    private static Bitmap createScaledBitmap(Bitmap bitmap) {
        int dip2px = dip2px(13.0f);
        float height = bitmap.getHeight();
        float f2 = dip2px / height;
        try {
            return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f2), (int) (height * f2), true);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
                return null;
            }
            return null;
        }
    }

    private static String decrypt(String str, boolean z, String str2) {
        String decryptThreeDESECB;
        try {
            if (z) {
                decryptThreeDESECB = DesCbcCrypto.decrypt(str, str2, (byte[]) null);
            } else {
                decryptThreeDESECB = DesCommonUtils.decryptThreeDESECB(str, str2);
            }
            return decryptThreeDESECB;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return "";
        }
    }

    public static String decryptThreeDESECB(String str, boolean z, String str2) {
        return decrypt(str, z, str2);
    }

    public static int dip2px(float f2) {
        return DPIUtil.dip2px(f2);
    }

    private static String encrypt(String str, boolean z, String str2) {
        String encryptThreeDESECB;
        try {
            if (z) {
                encryptThreeDESECB = DesCbcCrypto.encrypt(str, str2, (byte[]) null);
            } else {
                encryptThreeDESECB = DesCommonUtils.encryptThreeDESECB(str, str2);
            }
            return encryptThreeDESECB;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return "";
        }
    }

    public static String encryptThreeDESECB(String str, boolean z) {
        return encrypt(str, z, JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES));
    }

    public static void exposure(Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            if (Log.D) {
                Log.d("PDHelper", "eventId=" + str2 + " eventParam=" + str4 + " pageParam=" + str3 + " skuTag=" + str7);
            }
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplication().getApplicationContext(), obj, PDHelper.getPageId(str), str3, str2, str4, str5, str6, str7);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void exposureForTc(String str, String str2, String str3) {
        PDHelper.onExposure("com.jd.lib.productdetail.ProductDetailActivity", str3, str, str2, null, null);
    }

    public static void exposureJsonParam(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            if (Log.D) {
                Log.d("PDHelper", "eventId=" + str2 + " eventParam=" + str4 + " pageParam=" + str3 + " skuTag=" + str8 + "jsonParam=" + str5);
            }
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), str2, str4, PDHelper.getPageId(str), str, str3, str5, str6, str7, str8, null);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void exposurePageIdentifier(String str, String str2, String str3, ProductDetailEntity productDetailEntity, String str4) {
        if (productDetailEntity != null) {
            exposureJsonParam("com.jd.lib.productdetail.ProductDetailActivity", str, str2, str3, str4, productDetailEntity.getShopId(), null, productDetailEntity.getSkuTag());
        }
    }

    public static String formatInt(int i2) {
        String str = i2 + "";
        if (i2 >= 10000) {
            StringBuilder sb = new StringBuilder();
            DecimalFormat decimalFormat = new DecimalFormat("0.#");
            double d = i2;
            Double.isNaN(d);
            sb.append(decimalFormat.format(d / 10000.0d));
            sb.append("\u4e07");
            return sb.toString();
        }
        return str;
    }

    public static String formatPrice(String str) {
        String str2;
        Double valueOf;
        try {
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
        if (!TextUtils.isEmpty(str) && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
            str2 = new DecimalFormat("0.00").format(valueOf);
            return "\u00a5" + str2;
        }
        str2 = StringUtil.no_price;
        return "\u00a5" + str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0014, code lost:
        if (r0.doubleValue() <= 0.0d) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String formatPriceForYjf(String str) {
        try {
            if (!TextUtils.isEmpty(str) && (r0 = Double.valueOf(str)) != null) {
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
        str = StringUtil.no_price;
        return "\u00a5" + str;
    }

    public static String formatPriceOnlyV55(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "\u00a5";
        }
        if (!TextUtils.isEmpty(str) && !str.startsWith("-")) {
            if (str.startsWith(str2)) {
                return str;
            }
            return str2 + str;
        }
        return str2 + StringUtil.no_price;
    }

    public static String formatePriceWithOutDot(double d) {
        return new DecimalFormat("0").format(d);
    }

    public static Spanned fromHtml(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }

    public static int getAppHeight(Activity activity) {
        return DPIUtil.getAppHeight(activity);
    }

    public static int getAppWidth(Activity activity) {
        return DPIUtil.getAppWidth(activity);
    }

    public static GradientDrawable getBackgroundDrawable(int i2, String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setStroke(1, parseColor(str));
        gradientDrawable.setColor(parseColor(str));
        return gradientDrawable;
    }

    public static String getBbtf(Bundle bundle) {
        JDJSONObject parseObject;
        if (bundle != null) {
            try {
                String string = bundle.getString(LibPdStyleInfoViewUtils.EXTRAS_JSON_STR);
                return (TextUtils.isEmpty(string) || (parseObject = JDJSON.parseObject(string)) == null) ? "" : parseObject.optString(LibPdStyleInfoViewUtils.BBTF);
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    public static String getBigImgPath(String str) {
        FileService.Directory directory = FileService.getDirectory(1);
        if (directory != null) {
            return directory.getPath() + "/" + str;
        }
        return "";
    }

    public static Drawable getBtnBgSelector(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(";");
            GradientDrawable[] gradientDrawableArr = new GradientDrawable[3];
            if (split.length >= 3) {
                try {
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    for (int i2 = 0; i2 < split.length; i2++) {
                        String[] split2 = split[i2].split(DYConstants.DY_REGEX_COMMA);
                        if (split2.length < 3) {
                            return null;
                        }
                        int[] iArr = new int[3];
                        for (int i3 = 0; i3 < split2.length; i3++) {
                            iArr[i3] = Color.parseColor(split2[i3]);
                        }
                        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
                        if (z) {
                            gradientDrawable.setCornerRadius(dip2px(21.0f));
                        } else {
                            gradientDrawable.setCornerRadius(0.0f);
                        }
                        gradientDrawableArr[i2] = gradientDrawable;
                    }
                    stateListDrawable.addState(new int[]{16842919}, gradientDrawableArr[1]);
                    stateListDrawable.addState(new int[]{16842913}, gradientDrawableArr[1]);
                    stateListDrawable.addState(new int[]{-16842910}, gradientDrawableArr[2]);
                    stateListDrawable.addState(new int[0], gradientDrawableArr[0]);
                    return stateListDrawable;
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d(t.f20145j, e2.getMessage());
                    }
                }
            }
        }
        return null;
    }

    public static Drawable getColorDrawable(String str, String str2, int i2, boolean z) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        GradientDrawable gradientDrawable = getGradientDrawable(str, z);
        GradientDrawable gradientDrawable2 = getGradientDrawable(str2, z);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        if (i2 != 0) {
            stateListDrawable.addState(new int[]{-16842910}, new ColorDrawable(i2));
        }
        stateListDrawable.addState(new int[0], gradientDrawable);
        return stateListDrawable;
    }

    public static Drawable getColorDrawableBg(Drawable drawable, String str, String str2, boolean z) {
        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return gradientDrawable;
            }
            if (z) {
                str = str2;
            }
            gradientDrawable.setColor(parseColor(str));
            return gradientDrawable;
        }
        return null;
    }

    public static int getColorWithTheme(boolean z, @ColorInt int i2, @ColorInt int i3) {
        return z ? i3 : i2;
    }

    public static int getColorWithTheme(boolean z, String str, String str2) {
        if (z) {
            return parseColor(str2);
        }
        return parseColor(str);
    }

    public static String getCommentABTestType() {
        return CommonBase.getJdSharedPreferences().getString("productCommentABTestType", "B");
    }

    public static float[] getCornerArray(int i2, int i3) {
        int dip2px = dip2px(i2);
        if (i3 == 1) {
            float f2 = dip2px;
            return new float[]{f2, f2, f2, f2, 0.0f, 0.0f, 0.0f, 0.0f};
        } else if (i3 == 2) {
            float f3 = dip2px;
            return new float[]{f3, f3, f3, f3, f3, f3, f3, f3};
        } else if (i3 == 3) {
            float f4 = dip2px;
            return new float[]{0.0f, 0.0f, f4, f4, f4, f4, 0.0f, 0.0f};
        } else if (i3 == 4) {
            float f5 = dip2px;
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, f5, f5, f5, f5};
        } else if (i3 == 5) {
            float f6 = dip2px;
            return new float[]{f6, f6, 0.0f, 0.0f, 0.0f, 0.0f, f6, f6};
        } else {
            throw new IllegalStateException("Unexpected value: " + i3);
        }
    }

    public static String getEmptyPrice(String str, WareBusinessData wareBusinessData) {
        return isEmptyPrice(wareBusinessData) ? wareBusinessData.property.showEmptyPriceText : str;
    }

    public static LayerDrawable getFloorBgDrawable(int i2, float[] fArr) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        gradientDrawable.setCornerRadii(fArr);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(Color.parseColor("#F2F2F2"));
        return new LayerDrawable(new GradientDrawable[]{gradientDrawable});
    }

    public static StringBuilder getFurnitureSelectIds(WareFurnitureInfo wareFurnitureInfo) {
        StringBuilder sb;
        int lastIndexOf;
        List<WareBusinessFurnitureGroupEntity> list;
        List<WareBusinessFurnitureItemEntity> list2;
        String str = "";
        if (wareFurnitureInfo == null || (list = wareFurnitureInfo.fiInfo) == null || list.isEmpty()) {
            sb = null;
        } else {
            sb = new StringBuilder();
            for (WareBusinessFurnitureGroupEntity wareBusinessFurnitureGroupEntity : list) {
                if (wareBusinessFurnitureGroupEntity != null && (list2 = wareBusinessFurnitureGroupEntity.items) != null && !list2.isEmpty()) {
                    for (WareBusinessFurnitureItemEntity wareBusinessFurnitureItemEntity : list2) {
                        if (wareBusinessFurnitureItemEntity != null) {
                            str = str.concat(String.valueOf(wareBusinessFurnitureItemEntity.skuId)).concat("#");
                            if (wareBusinessFurnitureItemEntity.isSelected) {
                                sb.append(String.valueOf(wareBusinessFurnitureItemEntity.skuId));
                                sb.append("#");
                            }
                        }
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf("#")) > 0) {
            str = str.substring(0, lastIndexOf - 1);
        }
        mServerFurniture = str;
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    public static GradientDrawable getGiftGradientDrawable(String str, boolean z) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 0) {
            return null;
        }
        int[] iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = parseColor(split[i2]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        if (z) {
            gradientDrawable.setCornerRadii(CORNER_RIGHT_UP);
        } else {
            gradientDrawable.setCornerRadius(0.0f);
        }
        return gradientDrawable;
    }

    public static GradientDrawable getGradientDrawable(String str, boolean z) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 0) {
            return null;
        }
        int[] iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = parseColor(split[i2]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        if (z) {
            gradientDrawable.setCornerRadius(dip2px(21.0f));
        } else {
            gradientDrawable.setCornerRadius(0.0f);
        }
        return gradientDrawable;
    }

    public static int getHeight() {
        return DPIUtil.getHeight(JdSdk.getInstance().getApplicationContext());
    }

    public static boolean getImageInfoEntity() {
        return UnCustomThemeHelper.getInstance().getTitleImageInfo("DETAILPOPLAYER") != null;
    }

    public static CharSequence getImageSpan(Context context, List<String> list, String str) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        StringBuilder sb = new StringBuilder(str);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Bitmap bitmap = UnIconConfigHelper.getBitmap(list.get(i2));
            if (bitmap != null) {
                sb.insert(0, "  ");
                arrayList.add(createScaledBitmap(bitmap));
            }
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            int i4 = i3 * 2;
            spannableString.setSpan(new VerticalImageSpan(VerticalImageSpan.getBitmapDrawble(context, (Bitmap) arrayList.get(i3))), i4, i4 + 1, 17);
        }
        return spannableString;
    }

    public static CharSequence getJPriceText(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return StringUtil.no_price;
        }
        if (TextUtils.equals(StringUtil.no_price, str)) {
            return str;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(StringUtil.no_price, str) && !str.startsWith("\u00a5")) {
            str = "\u00a5" + str;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), 0, 1, 33);
        try {
            int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
            if (lastIndexOf != -1) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), lastIndexOf, str.length(), 33);
            }
        } catch (Exception unused) {
        }
        return spannableStringBuilder;
    }

    public static StringBuilder getJdServiceSelect(WareJdServerPlusEntity wareJdServerPlusEntity) {
        int lastIndexOf;
        List<WareBusinessPlusListEntity> list;
        List<WareBusinessJdServerProduct> list2;
        StringBuilder sb = null;
        String str = "";
        if (wareJdServerPlusEntity != null && (list = wareJdServerPlusEntity.jdSerPlusList) != null && !list.isEmpty()) {
            for (WareBusinessPlusListEntity wareBusinessPlusListEntity : wareJdServerPlusEntity.jdSerPlusList) {
                if (wareBusinessPlusListEntity != null && (list2 = wareBusinessPlusListEntity.products) != null && !list2.isEmpty()) {
                    for (WareBusinessJdServerProduct wareBusinessJdServerProduct : wareBusinessPlusListEntity.products) {
                        if (wareBusinessJdServerProduct != null) {
                            str = str.concat(wareBusinessJdServerProduct.serviceSku).concat("#");
                            if (wareBusinessJdServerProduct.isSelected) {
                                if (sb == null) {
                                    sb = new StringBuilder();
                                }
                                sb.append(wareBusinessJdServerProduct.serviceSku);
                                sb.append("#");
                            }
                        }
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf("#")) > 0) {
            str = str.substring(0, lastIndexOf - 1);
        }
        mServerJdSerPlus = str;
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    /* JADX WARN: Removed duplicated region for block: B:224:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getLayerStyleMaiDian(ProductDetailEntity productDetailEntity) {
        String str;
        WareBusinessData wareBusinessData;
        boolean z = !productDetailEntity.isYanbaoShield() && productDetailEntity.hasYanBao();
        boolean hasFurniture = productDetailEntity.hasFurniture();
        boolean hasJdServerPlus = productDetailEntity.hasJdServerPlus();
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append("0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(hasJdServerPlus ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(hasFurniture ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        getSelectServerSkuid(productDetailEntity);
        if (!TextUtils.isEmpty(mServerYanbao)) {
            sb.append(mServerYanbao);
        } else {
            sb.append("");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (!TextUtils.isEmpty(mServerJdSerPlus)) {
            sb.append(mServerJdSerPlus);
        } else {
            sb.append("");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (!TextUtils.isEmpty(mServerFurniture)) {
            sb.append(mServerFurniture);
        } else {
            sb.append("");
        }
        if (productDetailEntity.getColorSize() == null || productDetailEntity.getColorSize().isEmpty()) {
            str = "0";
        } else {
            try {
                List<PDStyleFilterEntity> colorSize = productDetailEntity.getColorSize();
                int size = colorSize.size();
                str = "0";
                for (int i2 = 0; i2 < size; i2++) {
                    try {
                        List<PDStylePropertyEntity> list = colorSize.get(i2).buttons;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= list.size()) {
                                break;
                            } else if (list.get(i3).isDash) {
                                str = "1";
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (TextUtils.equals(str, "1")) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        ExceptionReporter.reportExceptionToBugly(e);
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        sb.append(str);
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        wareBusinessData = productDetailEntity.mWareBusinessData;
                        if (wareBusinessData == null) {
                        }
                        sb.append("0");
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        if (!hasSizeGuide(productDetailEntity)) {
                        }
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        if (!productDetailEntity.isShowHealth()) {
                        }
                        return sb.toString();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str = "0";
            }
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        wareBusinessData = productDetailEntity.mWareBusinessData;
        if (wareBusinessData == null && wareBusinessData.userSizeInfo != null) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (!hasSizeGuide(productDetailEntity)) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (!productDetailEntity.isShowHealth()) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        return sb.toString();
    }

    public static JDLocationCacheOption getLocationCacheOption() {
        JDLocationCacheOption jDLocationCacheOption;
        synchronized (PDUtils.class) {
            if (mLocationCacheOption == null) {
                JDLocationCacheOption jDLocationCacheOption2 = new JDLocationCacheOption();
                mLocationCacheOption = jDLocationCacheOption2;
                jDLocationCacheOption2.setBusinessId("0f0a16fcbc9f8c5dce1d1ac6807ba957");
            }
            jDLocationCacheOption = mLocationCacheOption;
        }
        return jDLocationCacheOption;
    }

    public static int getNoticeCount() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        long j2 = jdSharedPreferences.getLong("product_detail_sku_notice_time", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        if (j2 > 0 && isSameDay(j2, currentTimeMillis)) {
            i2 = jdSharedPreferences.getInt("product_detail_sku_notice_count", 0);
        }
        jdSharedPreferences.edit().putLong("product_detail_sku_notice_time", currentTimeMillis).commit();
        jdSharedPreferences.edit().putInt("product_detail_sku_notice_count", i2 + 1).commit();
        return i2;
    }

    public static String getPDManagerKey() {
        long currentTimeMillis = managerKey + System.currentTimeMillis();
        managerKey = currentTimeMillis;
        return String.valueOf(currentTimeMillis);
    }

    public static CharSequence getPrice(String str, boolean z) {
        if (!TextUtils.equals(StringUtil.no_price, str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u00a5");
            sb.append(z ? LangUtils.SINGLE_SPACE : "");
            sb.append(str);
            str = sb.toString();
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
        if (indexOf == -1 || !z) {
            indexOf = spannableStringBuilder.length();
        }
        if (z) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.25f), 1, indexOf, 33);
        }
        return spannableStringBuilder;
    }

    public static SpannableString getPriceText(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str)) {
            return spannableString;
        }
        if (str.startsWith("\u00a5")) {
            spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 33);
        }
        int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
        if (indexOf != -1) {
            spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, str.length(), 33);
        }
        return spannableString;
    }

    public static int getScreenWidth() {
        int i2 = screenWidth;
        if (i2 > 0) {
            return i2;
        }
        int width = getWidth();
        screenWidth = width;
        return width;
    }

    public static String getSelectServerSkuid(ProductDetailEntity productDetailEntity) {
        StringBuilder sb = new StringBuilder();
        StringBuilder selectYanbao = getSelectYanbao(productDetailEntity.getWareYanBaoEntity(), productDetailEntity.isYanbaoShield());
        StringBuilder jdServiceSelect = getJdServiceSelect(productDetailEntity.getWareJdServerPlusEntity());
        StringBuilder furnitureSelectIds = getFurnitureSelectIds(productDetailEntity.getWareFurnitureInfo());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (selectYanbao != null && selectYanbao.length() > 0) {
            sb.append(selectYanbao.toString());
        } else {
            sb.append("");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (jdServiceSelect != null && jdServiceSelect.length() > 0) {
            sb.append(jdServiceSelect.toString());
        } else {
            sb.append("");
        }
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        if (furnitureSelectIds != null && furnitureSelectIds.length() > 0) {
            sb.append(furnitureSelectIds.toString());
        } else {
            sb.append("");
        }
        return sb.toString();
    }

    public static StringBuilder getSelectYanbao(WareYanBaoEntity wareYanBaoEntity, boolean z) {
        StringBuilder sb;
        int lastIndexOf;
        List<WareBusinessYanBaoGroupEntity> list;
        List<WareBusinessYanBaoItemEntity> list2;
        String str = "";
        if (z || wareYanBaoEntity == null || (list = wareYanBaoEntity.yanBaoList) == null || list.isEmpty()) {
            sb = null;
        } else {
            sb = new StringBuilder();
            for (WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity : list) {
                if (wareBusinessYanBaoGroupEntity != null && (list2 = wareBusinessYanBaoGroupEntity.products) != null && !list2.isEmpty()) {
                    for (WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity : list2) {
                        if (wareBusinessYanBaoItemEntity != null) {
                            str = str.concat(String.valueOf(wareBusinessYanBaoItemEntity.platformPid)).concat("#");
                            if (wareBusinessYanBaoItemEntity.isSelected) {
                                sb.append(wareBusinessYanBaoItemEntity.platformPid);
                                sb.append("#");
                            }
                        }
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf("#")) > 0) {
            str = str.substring(0, lastIndexOf - 1);
        }
        mServerYanbao = str;
        if (sb != null && sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    public static GradientDrawable getShadowDrawable(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 1) {
            return null;
        }
        int[] iArr = new int[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            iArr[i2] = parseColor(split[i2]);
        }
        return new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
    }

    public static CharSequence getShareWxJPriceText(String str, float f2, String str2) {
        String str3;
        int i2;
        if (!TextUtils.equals(StringUtil.no_price, str)) {
            if (!TextUtils.isEmpty(str2)) {
                str3 = str2 + "\uff1a\u00a5" + str;
                i2 = str2.length() + 2;
            } else {
                str3 = "\u00a5" + str;
                i2 = 1;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str3);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), 0, i2, 33);
            if (!TextUtils.isEmpty(str2)) {
                spannableStringBuilder.setSpan(new StyleSpan(1), 0, str2.length(), 18);
            }
            try {
                int lastIndexOf = str3.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
                if (lastIndexOf != -1) {
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), lastIndexOf, str3.length(), 33);
                }
            } catch (Exception unused) {
            }
            return spannableStringBuilder;
        }
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(str);
        try {
            spannableStringBuilder2.setSpan(new RelativeSizeSpan(0.61f), 0, str.length(), 33);
            spannableStringBuilder2.setSpan(new StyleSpan(1), 0, str.length(), 18);
        } catch (Exception unused2) {
        }
        return spannableStringBuilder2;
    }

    public static String getStackStringFromException(Throwable th) {
        PrintWriter printWriter;
        StringWriter stringWriter;
        if (th != null) {
            StringWriter stringWriter2 = null;
            try {
                stringWriter = new StringWriter();
                try {
                    printWriter = new PrintWriter((Writer) stringWriter, true);
                } catch (Exception unused) {
                    printWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    printWriter = null;
                }
            } catch (Exception unused2) {
                printWriter = null;
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
            }
            try {
                th.printStackTrace(printWriter);
                String stringBuffer = stringWriter.getBuffer().toString();
                try {
                    stringWriter.close();
                } catch (Exception unused3) {
                }
                try {
                    printWriter.close();
                } catch (Exception unused4) {
                }
                return stringBuffer;
            } catch (Exception unused5) {
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused6) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                        return "null message";
                    } catch (Exception unused7) {
                        return "null message";
                    }
                }
                return "null message";
            } catch (Throwable th4) {
                th = th4;
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused8) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                    } catch (Exception unused9) {
                    }
                }
                throw th;
            }
        }
        return "null message";
    }

    public static SpannableString getToHandPrice(String str, float f2, double d) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str)) {
            return spannableString;
        }
        if (str.startsWith("\u00a5")) {
            spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 33);
            spannableString.setSpan(new SuperTextSpan(d), 0, 1, 33);
        }
        int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
        if (indexOf != -1) {
            spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, str.length(), 33);
        }
        return spannableString;
    }

    public static int getValue(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static String getVideoId(String str) {
        if (str != null) {
            try {
                if (str.equals("")) {
                    return null;
                }
                Uri parse = Uri.parse(str);
                JDJSONObject string2JDJsonObject = JumpUtil.string2JDJsonObject(OpenAppUtil.getQueryParameter(parse, "params"), parse);
                if (string2JDJsonObject == null || string2JDJsonObject.isNull("id")) {
                    return null;
                }
                return string2JDJsonObject.getString("id");
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
                return null;
            }
        }
        return null;
    }

    public static int getViewWidth(View view) {
        if (view == null) {
            return 0;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredWidth();
    }

    public static int getVirtualBarHeigh() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) JdSdk.getInstance().getApplication().getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getWidth() {
        return DPIUtil.getWidth(JdSdk.getInstance().getApplicationContext());
    }

    public static void handleYuShouNum(String str, Spannable spannable) {
        try {
            int indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (indexOf > 0) {
                spannable.setSpan(new AbsoluteSizeSpan(10, true), indexOf, str.length(), 18);
                spannable.setSpan(new CustomTypefaceSpan("", Typeface.DEFAULT), indexOf, str.length(), 18);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("Exception", e2.getMessage());
            }
        }
    }

    public static boolean hasShopScore(PdShopInfoEntity pdShopInfoEntity) {
        return (pdShopInfoEntity == null || TextUtils.isEmpty(pdShopInfoEntity.evaluateScore) || TextUtils.isEmpty(pdShopInfoEntity.evaluateGrade) || TextUtils.isEmpty(pdShopInfoEntity.evaluateTxt) || TextUtils.isEmpty(pdShopInfoEntity.afterSaleScore) || TextUtils.isEmpty(pdShopInfoEntity.afterSaleGrade) || TextUtils.isEmpty(pdShopInfoEntity.afterSaleTxt) || TextUtils.isEmpty(pdShopInfoEntity.logisticsScore) || TextUtils.isEmpty(pdShopInfoEntity.logisticsGrade) || TextUtils.isEmpty(pdShopInfoEntity.logisticsTxt)) ? false : true;
    }

    private static boolean hasSizeGuide(ProductDetailEntity productDetailEntity) {
        PdSizeGuide pdSizeGuide;
        if (productDetailEntity.getColorSize() != null) {
            for (PDStyleFilterEntity pDStyleFilterEntity : productDetailEntity.getColorSize()) {
                if (pDStyleFilterEntity != null && (pdSizeGuide = pDStyleFilterEntity.titleExtMap) != null && !TextUtils.isEmpty(pdSizeGuide.entranceCw) && !TextUtils.isEmpty(pdSizeGuide.jumpPic) && !TextUtils.isEmpty(pdSizeGuide.jumpUrl)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isAPI21LevelHight() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static boolean isCanClick() {
        return NetUtils.isNetworkAvailable();
    }

    public static boolean isCutPriceAType() {
        return "A".equals(CommonBase.getJdSharedPreferences().getString("productDetail_cutPrice", "B"));
    }

    public static boolean isEmptyPrice(WareBusinessData wareBusinessData) {
        WarePropertyInfo warePropertyInfo;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.showEmptyPrice) ? false : true;
    }

    @Deprecated
    public static boolean isNormalScreen(Context context) {
        return true;
    }

    private static boolean isSameDay(long j2, long j3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(j2)).equals(simpleDateFormat.format(new Date(j3)));
    }

    public static boolean isShowRightDd(int i2) {
        return !TextUtils.equals(CommonBase.getJdSharedPreferences().getString("jddd_toast_key_time_new", ""), new SimpleDateFormat("yyyyMMdd").format(new Date())) || CommonBase.getJdSharedPreferences().getInt("jddd_toast_key_count_new", 0) < i2;
    }

    public static boolean isSkuNoticeAType() {
        return "A".equals(CommonBase.getJdSharedPreferences().getString("productDetail_productArrival", "B"));
    }

    public static boolean isToday(String str) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(System.currentTimeMillis()));
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(str));
            if (calendar2.get(1) == calendar.get(1)) {
                if (calendar2.get(6) - calendar.get(6) == 0) {
                    return true;
                }
            }
        } catch (ParseException unused) {
        }
        return false;
    }

    public static String longToString(long j2) {
        try {
            return String.valueOf(j2);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return "";
        }
    }

    public static void onClick(String str) {
        PDHelper.onClick(str, null, "com.jd.lib.productdetail.ProductDetailActivity", null, null, null);
    }

    public static void onClickForTc(String str, String str2, String str3) {
        PDHelper.onClick(str, str2, "com.jd.lib.productdetail.ProductDetailActivity", str3);
    }

    public static void onClickJsonParam(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), str, str2, "onClick", PDHelper.getPageId(str3), str3, str4, null, str6, str7, "", "", str5, null);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(th);
        }
    }

    public static void onClickJsonParm(String str, String str2, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            onClickJsonParm(str, null, "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity.skuId, productDetailEntity.getSkuTag(), str2, null, null, null);
        }
    }

    public static int parseColor(String str) {
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return 0;
        }
    }

    public static int px2dp(float f2) {
        return DPIUtil.px2dip(f2);
    }

    public static int px2sp(Context context, float f2) {
        return DPIUtil.px2sp(context, f2);
    }

    public static void recommendClickRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        httpSetting.setPost(false);
        httpSetting.setType(6000);
        httpSetting.setUrl(str);
        HttpGroupUtils.getHttpGroupaAsynPool(1000).add(httpSetting);
    }

    public static boolean repeatClick() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - timeClick > 1000) {
            timeClick = uptimeMillis;
            return true;
        }
        return false;
    }

    public static boolean resetPlusCount() {
        long j2 = SharedPreferencesUtil.getLong("plusTimeFlag", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (j2 > 0) {
            if (isSameDay(j2, currentTimeMillis)) {
                return false;
            }
            SharedPreferencesUtil.remove("plusClickCount");
            SharedPreferencesUtil.remove("plusTimeFlag");
            return true;
        }
        SharedPreferencesUtil.putLong("plusTimeFlag", currentTimeMillis);
        return false;
    }

    public static boolean saveShareBitmap(Bitmap bitmap, int i2, String str, int i3) {
        if (bitmap == null) {
            return false;
        }
        return FileService.saveToSDCard(FileService.getDirectory(1), str, compressBitmap2Bytes(bitmap, i2, i3));
    }

    public static boolean saveToSDCard(File file, byte[] bArr) {
        if (file == null || bArr == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(bArr);
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    ExceptionReporter.reportExceptionToBugly(e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            ExceptionReporter.reportExceptionToBugly(e4);
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            ExceptionReporter.reportExceptionToBugly(e5);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
        }
    }

    public static Bitmap setBitmapBackGroudColor(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(-1);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e2) {
            e2.getStackTrace();
            return null;
        }
    }

    public static WareYanBaoEntity setFurniture2YanBao(WareFurnitureInfo wareFurnitureInfo) {
        List<WareBusinessFurnitureGroupEntity> list;
        List<WareBusinessFurnitureItemEntity> list2;
        if (wareFurnitureInfo == null || (list = wareFurnitureInfo.fiInfo) == null || list.isEmpty()) {
            return null;
        }
        WareYanBaoEntity wareYanBaoEntity = new WareYanBaoEntity();
        wareYanBaoEntity.yanBaoTitle = wareFurnitureInfo.fiTitle;
        wareYanBaoEntity.yanBaoList = new ArrayList();
        wareYanBaoEntity.isFurniture = true;
        for (WareBusinessFurnitureGroupEntity wareBusinessFurnitureGroupEntity : wareFurnitureInfo.fiInfo) {
            if (wareBusinessFurnitureGroupEntity == null || (list2 = wareBusinessFurnitureGroupEntity.items) == null || list2.isEmpty()) {
                return null;
            }
            WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity = new WareBusinessYanBaoGroupEntity();
            wareBusinessYanBaoGroupEntity.sortName = wareBusinessFurnitureGroupEntity.serviceName;
            wareBusinessYanBaoGroupEntity.products = new ArrayList();
            wareBusinessYanBaoGroupEntity.detailUrl = wareBusinessFurnitureGroupEntity.serviceDescUrl;
            for (WareBusinessFurnitureItemEntity wareBusinessFurnitureItemEntity : wareBusinessFurnitureGroupEntity.items) {
                if (wareBusinessFurnitureItemEntity != null) {
                    WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity = new WareBusinessYanBaoItemEntity();
                    wareBusinessYanBaoItemEntity.platformPid = wareBusinessFurnitureItemEntity.skuId;
                    wareBusinessYanBaoItemEntity.tip = wareBusinessFurnitureItemEntity.itemIntroduction;
                    wareBusinessYanBaoItemEntity.price = String.valueOf(wareBusinessFurnitureItemEntity.itemPrice);
                    wareBusinessYanBaoItemEntity.sortName = wareBusinessFurnitureItemEntity.itemName;
                    wareBusinessYanBaoItemEntity.isSelected = wareBusinessFurnitureItemEntity.isSelected;
                    wareBusinessYanBaoGroupEntity.products.add(wareBusinessYanBaoItemEntity);
                }
            }
            wareYanBaoEntity.yanBaoList.add(wareBusinessYanBaoGroupEntity);
        }
        return wareYanBaoEntity;
    }

    public static PopupWindow showPopUp(final Context context, String str, View view, final String str2, final String str3, int i2, int i3) {
        View inflate = ImageUtil.inflate(context, R.layout.libpdstyleinfoview_popup_window, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, dip2px(100.0f), dip2px(54.0f));
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                try {
                    ((ClipboardManager) context.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, str2));
                    PDUtils.showToastCenterNormal(context, str3);
                } catch (Throwable th) {
                    if (Log.E) {
                        th.printStackTrace();
                    }
                    PDUtils.showToastCenterNormal(context, "\u60a8\u7684\u64cd\u4f5c\u7cfb\u7edf\u7248\u672c\u592a\u4f4e\uff0c\u6682\u65f6\u4e0d\u652f\u6301\u526a\u5207\u677f");
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(view, 51, i2, i3 - popupWindow.getHeight());
        return popupWindow;
    }

    public static void showToastCenterIcon(Context context, byte b, String str) {
        ToastUtils.showToastInCenter(context, b, str, 0);
    }

    public static void showToastCenterNormal(Context context, String str) {
        ToastUtils.showToastInCenter(context, str);
    }

    public static void showToastShortNormal(Context context, String str) {
        ToastUtils.shortToast(context, str);
    }

    public static int sp2px(Context context, float f2) {
        return DPIUtil.sp2px(context, f2);
    }

    public static float stringToFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return 1.0f;
        }
    }

    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static long stringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return 0L;
        }
    }

    public static long[] toDHM(long j2) {
        long j3 = j2 / 1000;
        long j4 = j3 / 60;
        long j5 = (j4 / 60) / 24;
        if (j5 < 0) {
            j5 = 0;
        }
        long j6 = j5 * 60;
        long j7 = (((j2 - (((j6 * 60) * 1000) * 24)) / 1000) / 60) / 60;
        if (j7 < 0) {
            j7 = 0;
        }
        Long.signum(j6);
        long j8 = j6 * 24;
        long j9 = j7 * 60;
        long j10 = (j4 - j8) - j9;
        if (j10 < 0) {
            j10 = 0;
        }
        long j11 = ((j3 - (j8 * 60)) - (j9 * 60)) - (60 * j10);
        return new long[]{j5, j7, j10, j11 >= 0 ? j11 : 0L};
    }

    public static SpannableString transDiscountString(String str, float f2, float f3) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        try {
            StringBuilder sb = new StringBuilder(str);
            sb.insert(str.length() - 1, LangUtils.SINGLE_SPACE);
            String sb2 = sb.toString();
            int length = sb2.length() - 2;
            SpannableString spannableString = new SpannableString(sb2);
            if (sb2.indexOf("-") > 0) {
                int indexOf = sb2.indexOf("-");
                int i2 = indexOf + 1;
                String substring = sb2.substring(i2);
                int indexOf2 = sb2.substring(0, indexOf).indexOf(OrderISVUtil.MONEY_DECIMAL);
                int indexOf3 = substring.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf2 != -1) {
                    int i3 = indexOf2 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, i3, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, i2, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i3, indexOf, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, indexOf, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, i2, 18);
                }
                if (indexOf3 != -1) {
                    int i4 = indexOf + indexOf3 + 1 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), i2, i4, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i4, length, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), i2, length, 18);
                }
            } else {
                int indexOf4 = sb2.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf4 != -1) {
                    int i5 = indexOf4 + 1;
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, i5, 18);
                    spannableString.setSpan(new RelativeSizeSpan(f3), i5, length, 18);
                } else {
                    spannableString.setSpan(new RelativeSizeSpan(f2), 0, length, 18);
                }
            }
            return spannableString;
        } catch (Exception unused) {
            return new SpannableString(str);
        }
    }

    public static String decryptThreeDESECB(String str, boolean z) {
        return decrypt(str, z, JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES));
    }

    public static int dip2px(Context context, float f2) {
        return DPIUtil.dip2px(context, f2);
    }

    public static void exposurePageIdentifier(String str, String str2, String str3, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            exposureJsonParam("com.jd.lib.productdetail.ProductDetailActivity", str, str2, str3, productDetailEntity.getJsonParam(), productDetailEntity.getShopId(), null, productDetailEntity.getSkuTag());
        }
    }

    public static String formatePriceWithOutDot(String str) {
        if (str != null) {
            try {
                Double valueOf = Double.valueOf(str);
                return (valueOf == null || valueOf.doubleValue() <= 0.0d) ? str : new DecimalFormat("0").format(valueOf);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                    return str;
                }
                return str;
            }
        }
        return str;
    }

    public static void onClick(String str, String str2) {
        PDHelper.onClick(str, null, "com.jd.lib.productdetail.ProductDetailActivity", str2, null, null);
    }

    public static void onClickJsonParm(String str, String str2, String str3, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            onClickJsonParm(str, str3, "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity.skuId, productDetailEntity.getSkuTag(), str2, null, null, null);
        }
    }

    public static String encryptThreeDESECB(String str, boolean z, String str2) {
        return encrypt(str, z, str2);
    }

    public static int getColorWithTheme(boolean z, String str, @ColorInt int i2) {
        return z ? i2 : parseColor(str);
    }

    public static void onClick(String str, String str2, String str3) {
        PDHelper.onClick(str, null, str2, str3, null, null);
    }

    public static void onClickJsonParm(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
        try {
            if (Log.D) {
                Log.d("PDHelper", "eventId=" + str + " eventParam=" + str2 + " pageParam=" + str4 + " skuTag=" + str5 + " jsonParam=" + str6);
            }
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), str, str2, "onClick", PDHelper.getPageId(str3), str3, str4, str7, str6, "", "", "", str5, hashMap);
        } catch (Throwable th) {
            ExceptionReporter.reportExceptionToBugly(th);
        }
    }

    public static int getColorWithTheme(boolean z, String str, String str2, String str3) {
        if (z) {
            return parseColor(str3);
        }
        return parseColor(str, str2);
    }

    public static void onClick(String str, String str2, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            PDHelper.onClick(str, str2, "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity.skuId, productDetailEntity.getSkuTag(), productDetailEntity.getShopId());
        }
    }

    public static int parseColor(String str, String str2) {
        int i2;
        try {
            i2 = Color.parseColor(str);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            i2 = 0;
        }
        if (i2 == 0) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "#232326";
            }
            try {
                return Color.parseColor(str2);
            } catch (Exception e3) {
                if (Log.D) {
                    Log.d(t.f20145j, e3.getMessage());
                    return i2;
                }
                return i2;
            }
        }
        return i2;
    }

    public static String decrypt(String str) {
        try {
            return DesCbcCrypto.decrypt(str, JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES), (byte[]) null);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
            return "";
        }
    }

    public static GradientDrawable getBackgroundDrawable(int i2, int i3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setStroke(1, i3);
        gradientDrawable.setColor(i3);
        return gradientDrawable;
    }

    public static int getColorWithTheme(boolean z, String str, String str2, @ColorInt int i2) {
        return z ? i2 : parseColor(str, str2);
    }

    public static void exposure(String str, String str2, String str3, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity != null) {
            exposure("com.jd.lib.productdetail.ProductDetailActivity", "com.jd.lib.productdetail.ProductDetailActivity", str, str2, str3, productDetailEntity.getShopId(), null, productDetailEntity.getSkuTag());
        }
    }

    public static void exposureJsonParam(String str, String str2, ProductDetailEntity productDetailEntity) {
        exposureJsonParam(str, str2, null, productDetailEntity);
    }

    public static String formatPrice(String str, String str2) {
        if (str != null) {
            try {
                Double valueOf = Double.valueOf(str);
                return (valueOf == null || valueOf.doubleValue() <= 0.0d) ? str2 : new DecimalFormat("0.00").format(valueOf);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                    return str2;
                }
                return str2;
            }
        }
        return str2;
    }

    public static CharSequence getPrice(String str) {
        if (!TextUtils.equals(StringUtil.no_price, str)) {
            str = "\u00a5" + str;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        try {
            int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (indexOf == -1) {
                indexOf = spannableStringBuilder.length();
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.25f), 1, indexOf, 33);
        } catch (Exception unused) {
        }
        return spannableStringBuilder;
    }

    public static void exposure(String str, String str2, String str3, PDMtaEntity pDMtaEntity) {
        if (pDMtaEntity != null) {
            exposure("com.jd.lib.productdetail.ProductDetailActivity", "com.jd.lib.productdetail.ProductDetailActivity", str, str2, str3, pDMtaEntity.shopId, null, pDMtaEntity.skuTag);
        }
    }

    public static void exposureJsonParam(String str, String str2, String str3, ProductDetailEntity productDetailEntity) {
        try {
            if (Log.D) {
                Log.d("PDHelper", "eventId=" + str + " eventParam=" + str3 + " pageParam=" + productDetailEntity.skuId + " skuTag=" + productDetailEntity.getSkuTag() + "jsonParam=" + str2);
            }
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), str, str3, PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"), "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity.skuId, str2, productDetailEntity.getShopId(), null, productDetailEntity.getSkuTag(), null);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static SpannableString getPriceText(Context context, String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str)) {
            return spannableString;
        }
        if (str.startsWith("\u00a5")) {
            spannableString.setSpan(new RelativeSizeSpan(f2), 0, 1, 33);
            spannableString.setSpan(new CustomTypefaceSpan("", FontsUtil.getTypeFace(context, 4099)), 0, 1, 17);
        }
        int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
        if (indexOf != -1) {
            spannableString.setSpan(new RelativeSizeSpan(f2), indexOf, str.length(), 33);
        }
        return spannableString;
    }

    public static void exposure(String str, String str2, String str3, String str4, String str5) {
        exposure("com.jd.lib.productdetail.ProductDetailActivity", "com.jd.lib.productdetail.ProductDetailActivity", str, str2, str3, str4, null, str5);
    }

    public static GradientDrawable getBackgroundDrawable(int i2, int i3, int i4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setStroke(i4, i3);
        gradientDrawable.setColor(i3);
        return gradientDrawable;
    }

    public static Drawable getColorDrawable(String str, String str2) {
        return getColorDrawable(str, str2, 0, false);
    }

    public static LayerDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, int i2, boolean z) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        if (baseTemplateEntity != null) {
            boolean z2 = baseTemplateEntity.isRoundDown;
            if (z2 && baseTemplateEntity.isRoundUp) {
                gradientDrawable.setCornerRadii(corner);
            } else if (z2) {
                gradientDrawable.setCornerRadii(cornerDown);
            } else if (baseTemplateEntity.isRoundUp) {
                gradientDrawable.setCornerRadii(cornerUp);
            }
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(getColorWithTheme(z, Color.parseColor("#F2F2F2"), Color.parseColor(JDDarkUtil.COLOR_141212)));
        return new LayerDrawable(new GradientDrawable[]{gradientDrawable2, gradientDrawable});
    }

    public static LayerDrawable getFloorBgDrawable(boolean z, boolean z2, int i2, boolean z3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i2);
        if (z2 && z) {
            gradientDrawable.setCornerRadii(corner);
        } else if (z2) {
            gradientDrawable.setCornerRadii(cornerDown);
        } else if (z) {
            gradientDrawable.setCornerRadii(cornerUp);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(getColorWithTheme(z3, Color.parseColor("#F2F2F2"), Color.parseColor(JDDarkUtil.COLOR_141212)));
        return new LayerDrawable(new GradientDrawable[]{gradientDrawable2, gradientDrawable});
    }

    public static LayerDrawable getFloorBgDrawable(BaseTemplateEntity baseTemplateEntity, String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor("#F2F2F2"));
        GradientDrawable[] gradientDrawableArr = new GradientDrawable[2];
        gradientDrawableArr[0] = gradientDrawable;
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(-1);
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 1) {
                int[] iArr = new int[split.length];
                for (int i2 = 0; i2 < split.length; i2++) {
                    iArr[i2] = parseColor(split[i2]);
                }
                gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
            }
        }
        gradientDrawable2.setShape(0);
        if (baseTemplateEntity != null) {
            boolean z = baseTemplateEntity.isRoundDown;
            if (z && baseTemplateEntity.isRoundUp) {
                gradientDrawable2.setCornerRadii(corner);
            } else if (z) {
                gradientDrawable2.setCornerRadii(cornerDown);
            } else if (baseTemplateEntity.isRoundUp) {
                gradientDrawable2.setCornerRadii(cornerUp);
            }
        }
        gradientDrawableArr[1] = gradientDrawable2;
        return new LayerDrawable(gradientDrawableArr);
    }
}
