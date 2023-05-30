package com.jingdong.common.unification.uniconfig;

import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class UnIconConfigHelper {
    public static int curVersion;
    public static long lastVersion;

    /* JADX WARN: Removed duplicated region for block: B:24:0x0062 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Drawable createTextDrawable(String str, String str2, Bitmap bitmap, byte[] bArr, boolean z, boolean z2, int i2) {
        boolean z3;
        int parseInt;
        int parseInt2;
        UnTextNineDrawable unTextNineDrawable = new UnTextNineDrawable(a.g().d().getResources(), bitmap, bArr, new Rect(), null, z);
        IconConfigModel iconConfigModel = UnIconConfigController.getController().getIconConfigModel(str, z, i2);
        IconExtraConfigModel iconExtraConfigModel = iconConfigModel == null ? null : iconConfigModel.config;
        if (iconExtraConfigModel != null) {
            if (!TextUtils.isEmpty(iconExtraConfigModel.textColor)) {
                try {
                    unTextNineDrawable.setTextColor(Color.parseColor(iconExtraConfigModel.textColor));
                } catch (Exception unused) {
                }
            }
            if (!TextUtils.isEmpty(iconExtraConfigModel.edgeL)) {
                try {
                    parseInt = Integer.parseInt(iconExtraConfigModel.edgeL);
                } catch (NumberFormatException unused2) {
                }
                if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
                    try {
                        parseInt2 = Integer.parseInt(iconExtraConfigModel.edgeR);
                    } catch (NumberFormatException unused3) {
                    }
                    if (parseInt != 0 && parseInt2 == 0) {
                        if (!TextUtils.isEmpty(iconExtraConfigModel.edge)) {
                            try {
                                int parseInt3 = Integer.parseInt(iconExtraConfigModel.edge);
                                unTextNineDrawable.setPadding(dip2px(parseInt3 / 2), 0, dip2px(parseInt3 / 2), 0);
                                try {
                                    if (UnLog.D) {
                                        UnLog.d("UniconConfigHelper", "set text padding:" + parseInt3);
                                    }
                                } catch (NumberFormatException unused4) {
                                }
                            } catch (NumberFormatException unused5) {
                            }
                        }
                    } else {
                        unTextNineDrawable.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
                    }
                    z3 = true;
                    if (!z3) {
                        unTextNineDrawable.setPadding(dip2px(5.0f), 0, dip2px(5.0f), 0);
                    }
                    unTextNineDrawable.setTextSize(1, getFontSize(iconConfigModel, z), z2);
                    if (!TextUtils.isEmpty(str2)) {
                        unTextNineDrawable.setText(str2);
                    }
                    return unTextNineDrawable;
                }
                parseInt2 = 0;
                if (parseInt != 0) {
                }
                unTextNineDrawable.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
                z3 = true;
                if (!z3) {
                }
                unTextNineDrawable.setTextSize(1, getFontSize(iconConfigModel, z), z2);
                if (!TextUtils.isEmpty(str2)) {
                }
                return unTextNineDrawable;
            }
            parseInt = 0;
            if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
            }
            parseInt2 = 0;
            if (parseInt != 0) {
            }
            unTextNineDrawable.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
            z3 = true;
            if (!z3) {
            }
            unTextNineDrawable.setTextSize(1, getFontSize(iconConfigModel, z), z2);
            if (!TextUtils.isEmpty(str2)) {
            }
            return unTextNineDrawable;
        }
        z3 = false;
        if (!z3) {
        }
        unTextNineDrawable.setTextSize(1, getFontSize(iconConfigModel, z), z2);
        if (!TextUtils.isEmpty(str2)) {
        }
        return unTextNineDrawable;
    }

    public static int dip2px(float f2) {
        float density = DpiUtil.getDensity(a.g().d());
        double dip2px = DpiUtil.dip2px(a.g().d(), f2);
        Double.isNaN(dip2px);
        double d = density;
        Double.isNaN(d);
        return (int) ((dip2px * 3.0d) / d);
    }

    public static void displayIcon(String str, ImageView imageView) {
        Drawable drawable;
        if (TextUtils.isEmpty(str) || imageView == null || (drawable = getDrawable(str)) == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public static Bitmap getBitmap(String str) {
        return getBitmap(str, null, false, 0);
    }

    public static String getBuriedStr() {
        return UnIconConfigController.getController().getBuriedStr();
    }

    public static int getCurVersionCode() {
        int i2 = curVersion;
        if (i2 != 0) {
            return i2;
        }
        try {
            PackageInfo packageInfo = a.g().d().getPackageManager().getPackageInfo(a.g().d().getPackageName(), 0);
            if (packageInfo != null) {
                int i3 = packageInfo.versionCode;
                curVersion = i3;
                return i3;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return 0;
    }

    public static Drawable getDrawable(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, (BitmapFactory.Options) null, false));
    }

    public static String getExperimentId() {
        return UnIconConfigController.getController().getExpId();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001f A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static float getFontSize(IconConfigModel iconConfigModel, boolean z) {
        float f2;
        IconExtraConfigModel iconExtraConfigModel;
        if (iconConfigModel != null && (iconExtraConfigModel = iconConfigModel.config) != null) {
            int i2 = iconExtraConfigModel.fontSize;
            if (i2 > 0) {
                f2 = i2;
            } else {
                String str = iconConfigModel.id;
                if (str != null && str.endsWith(UnIconConfigConstants.UN_ICON_ID_B)) {
                    f2 = 10.0f;
                }
            }
            if (z) {
                return f2;
            }
            return 12.0f;
        }
        f2 = 9.0f;
        if (z) {
        }
    }

    public static String getIconInfo(String str) {
        return getIconInfo(str, false, 0);
    }

    public static String getIconUrl(String str) {
        return getIconUrl(str, false, 0);
    }

    public static long getLastDataVersion() {
        return DefaultDataController.cache_version;
    }

    public static TextView getMultiLineTv(String str, String str2, boolean z) {
        return getMultiLineTv(str, str2, z, 0);
    }

    @Deprecated
    public static String getPath4DraweeView(String str) {
        return UnIconConfigController.getController().getIconPath4DraweeView(str, false, 0);
    }

    public static SpannableString getSpanableString(List<String> list, String str, TextView textView) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = getBitmap(it.next());
            if (bitmap != null) {
                arrayList.add(bitmap);
            }
        }
        return UnSpannaleUtils.getImageSpan(textView.getContext(), arrayList, str);
    }

    public static Bitmap getTextBitmap(String str, String str2) {
        return getTextBitmap(str, str2, false);
    }

    public static String getTextColor(String str) {
        return UnIconConfigController.getController().getTextColor(str, false, 0);
    }

    public static IconExtraConfigModel getTextConfig(String str) {
        return UnIconConfigController.getController().getTextConfig(str, false, 0);
    }

    public static Drawable getTextDrawable(String str, String str2) {
        return getTextDrawable(str, str2, (BitmapFactory.Options) null);
    }

    public static TextView getTextView(String str, String str2) {
        TextView textView = new TextView(a.g().d());
        setTextViewProperties(str, textView);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        }
        return textView;
    }

    public static TextView getTextViewOrNull(String str, String str2) {
        return getTextViewOrNull(str, str2, false);
    }

    private static int getTopPadding(boolean z, IconConfigModel iconConfigModel) {
        String str;
        if (z) {
            float f2 = 1.0f;
            if (iconConfigModel != null && iconConfigModel.config != null && (str = iconConfigModel.id) != null && str.endsWith(UnIconConfigConstants.UN_ICON_ID_B)) {
                f2 = 1.5f;
            }
            return dip2px(f2);
        }
        return 0;
    }

    public static long getUniConfigDataVersion() {
        return UnSharedPreferencesUtils.getLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DATA_VERSION, 0L);
    }

    public static void setTextViewProperties(String str, TextView textView) {
        setTextViewProperties(str, textView, false);
    }

    public static Bitmap getBitmap(String str, boolean z) {
        return getBitmap(str, null, z, 0);
    }

    public static String getIconInfo(String str, boolean z, int i2) {
        return UnIconConfigController.getController().getIconInfoJson(str, z, i2);
    }

    public static String getIconUrl(String str, boolean z, int i2) {
        return UnIconConfigController.getController().getIconUrl(str, z, i2);
    }

    public static TextView getMultiLineTv(String str, String str2, boolean z, int i2) {
        TextView textView = new TextView(a.g().d());
        setTextViewProperties(str, textView, z, i2, true);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        }
        return textView;
    }

    public static String getPath4DraweeView(String str, boolean z, int i2) {
        return UnIconConfigController.getController().getIconPath4DraweeView(str, z, i2);
    }

    public static Bitmap getTextBitmap(String str, String str2, boolean z) {
        return getTextBitmap(str, str2, z, 0);
    }

    public static String getTextColor(String str, boolean z, int i2) {
        return UnIconConfigController.getController().getTextColor(str, z, i2);
    }

    public static IconExtraConfigModel getTextConfig(String str, boolean z, int i2) {
        return UnIconConfigController.getController().getTextConfig(str, z, i2);
    }

    public static Drawable getTextDrawable(String str, String str2, boolean z) {
        return getTextDrawable(str, str2, (BitmapFactory.Options) null, false, z);
    }

    public static TextView getTextViewOrNull(String str, String str2, boolean z) {
        return getTextViewOrNull(str, str2, z, 0);
    }

    public static void setTextViewProperties(String str, TextView textView, boolean z) {
        setTextViewProperties(str, textView, z, 0);
    }

    public static Bitmap getBitmap(String str, boolean z, int i2) {
        return getBitmap(str, null, z, i2);
    }

    public static Drawable getDrawable(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, (BitmapFactory.Options) null, z));
    }

    public static Bitmap getTextBitmap(String str, String str2, boolean z, int i2) {
        return getTextBitmap(str, str2, true, z, i2);
    }

    public static Drawable getTextDrawable(String str, String str2, boolean z, boolean z2, int i2) {
        return getTextDrawable(str, str2, null, z2, z, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0064 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static TextView getTextViewOrNull(String str, String str2, boolean z, int i2) {
        boolean z2;
        int parseInt;
        int parseInt2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            TextView textView = new TextView(a.g().d());
            Drawable drawable = getDrawable(str);
            if (drawable != null) {
                textView.setBackgroundDrawable(drawable);
                IconConfigModel iconConfigModel = UnIconConfigController.getController().getIconConfigModel(str, z, i2);
                IconExtraConfigModel iconExtraConfigModel = iconConfigModel != null ? iconConfigModel.config : null;
                if (iconExtraConfigModel != null) {
                    if (!TextUtils.isEmpty(iconExtraConfigModel.textColor)) {
                        try {
                            textView.setTextColor(Color.parseColor(iconExtraConfigModel.textColor));
                        } catch (Exception unused) {
                        }
                    }
                    if (!TextUtils.isEmpty(iconExtraConfigModel.edgeL)) {
                        try {
                            parseInt = Integer.parseInt(iconExtraConfigModel.edgeL);
                        } catch (NumberFormatException unused2) {
                        }
                        if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
                            try {
                                parseInt2 = Integer.parseInt(iconExtraConfigModel.edgeR);
                            } catch (NumberFormatException unused3) {
                            }
                            if (parseInt != 0 && parseInt2 == 0) {
                                if (!TextUtils.isEmpty(iconExtraConfigModel.edge)) {
                                    try {
                                        int parseInt3 = Integer.parseInt(iconExtraConfigModel.edge);
                                        textView.setPadding(dip2px(parseInt3 / 2), 0, dip2px(parseInt3 / 2), 0);
                                        try {
                                            if (UnLog.D) {
                                                UnLog.d("UniconConfigHelper", "set text padding:" + parseInt3);
                                            }
                                        } catch (NumberFormatException unused4) {
                                        }
                                    } catch (NumberFormatException unused5) {
                                    }
                                }
                            } else {
                                textView.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
                            }
                            z2 = true;
                            if (!z2) {
                                textView.setPadding(dip2px(5.0f), 0, dip2px(5.0f), 0);
                            }
                            if (!TextUtils.isEmpty(str2)) {
                                textView.setText(str2);
                            }
                            textView.setGravity(17);
                            textView.setTextSize(1, getFontSize(iconConfigModel, z));
                            textView.setIncludeFontPadding(false);
                            return textView;
                        }
                        parseInt2 = 0;
                        if (parseInt != 0) {
                        }
                        textView.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
                        z2 = true;
                        if (!z2) {
                        }
                        if (!TextUtils.isEmpty(str2)) {
                        }
                        textView.setGravity(17);
                        textView.setTextSize(1, getFontSize(iconConfigModel, z));
                        textView.setIncludeFontPadding(false);
                        return textView;
                    }
                    parseInt = 0;
                    if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
                    }
                    parseInt2 = 0;
                    if (parseInt != 0) {
                    }
                    textView.setPadding(dip2px(parseInt / 2), 0, dip2px(parseInt2 / 2), 0);
                    z2 = true;
                    if (!z2) {
                    }
                    if (!TextUtils.isEmpty(str2)) {
                    }
                    textView.setGravity(17);
                    textView.setTextSize(1, getFontSize(iconConfigModel, z));
                    textView.setIncludeFontPadding(false);
                    return textView;
                }
                z2 = false;
                if (!z2) {
                }
                if (!TextUtils.isEmpty(str2)) {
                }
                textView.setGravity(17);
                textView.setTextSize(1, getFontSize(iconConfigModel, z));
                textView.setIncludeFontPadding(false);
                return textView;
            }
            return null;
        } catch (Exception e2) {
            if (UnLog.D) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public static void setTextViewProperties(String str, TextView textView, boolean z, int i2) {
        setTextViewProperties(str, textView, z, i2, false);
    }

    public static void displayIcon(String str, ImageView imageView, boolean z) {
        Drawable drawable;
        if (TextUtils.isEmpty(str) || imageView == null || (drawable = getDrawable(str, z)) == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public static Bitmap getBitmap(String str, BitmapFactory.Options options) {
        return getBitmap(str, options, false, 0);
    }

    public static Bitmap getTextBitmap(String str, String str2, boolean z, boolean z2, int i2) {
        Bitmap bitmap;
        if (TextUtils.isEmpty(str) || (bitmap = getBitmap(str, null, z2, i2)) == null) {
            return null;
        }
        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        if (NinePatch.isNinePatchChunk(ninePatchChunk)) {
            try {
                return UnDrawableUtil.scaleBitmap(UnDrawableUtil.ninePathDrawable2Bitmap((UnTextNineDrawable) createTextDrawable(str, str2, bitmap, ninePatchChunk, z2, z, i2)), z2);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
                return null;
            }
        }
        return bitmap;
    }

    public static Drawable getTextDrawable(String str, String str2, BitmapFactory.Options options) {
        return getTextDrawable(str, str2, options, false, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x005a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void setTextViewProperties(String str, TextView textView, boolean z, int i2, boolean z2) {
        boolean z3;
        int parseInt;
        int parseInt2;
        if (TextUtils.isEmpty(str) || textView == null) {
            return;
        }
        Drawable drawable = getDrawable(str);
        if (drawable != null) {
            textView.setBackgroundDrawable(drawable);
        }
        IconConfigModel iconConfigModel = UnIconConfigController.getController().getIconConfigModel(str, z, i2);
        IconExtraConfigModel iconExtraConfigModel = iconConfigModel == null ? null : iconConfigModel.config;
        if (iconExtraConfigModel != null) {
            if (!TextUtils.isEmpty(iconExtraConfigModel.textColor)) {
                try {
                    textView.setTextColor(Color.parseColor(iconExtraConfigModel.textColor));
                } catch (Exception unused) {
                }
            }
            if (!TextUtils.isEmpty(iconExtraConfigModel.edgeL)) {
                try {
                    parseInt = Integer.parseInt(iconExtraConfigModel.edgeL);
                } catch (NumberFormatException unused2) {
                }
                if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
                    try {
                        parseInt2 = Integer.parseInt(iconExtraConfigModel.edgeR);
                    } catch (NumberFormatException unused3) {
                    }
                    if (parseInt != 0 && parseInt2 == 0) {
                        if (!TextUtils.isEmpty(iconExtraConfigModel.edge)) {
                            try {
                                int parseInt3 = Integer.parseInt(iconExtraConfigModel.edge);
                                textView.setPadding(dip2px(parseInt3 / 2), getTopPadding(z2, iconConfigModel), dip2px(parseInt3 / 2), getTopPadding(z2, iconConfigModel));
                                try {
                                    if (UnLog.D) {
                                        UnLog.d("UniconConfigHelper", "set text padding:" + parseInt3);
                                    }
                                } catch (NumberFormatException unused4) {
                                }
                            } catch (NumberFormatException unused5) {
                            }
                        }
                    } else {
                        textView.setPadding(dip2px(parseInt / 2), getTopPadding(z2, iconConfigModel), dip2px(parseInt2 / 2), getTopPadding(z2, iconConfigModel));
                    }
                    z3 = true;
                    if (!z3) {
                        textView.setPadding(dip2px(5.0f), getTopPadding(z2, iconConfigModel), dip2px(5.0f), getTopPadding(z2, iconConfigModel));
                    }
                    textView.setGravity(17);
                    textView.setTextSize(1, getFontSize(iconConfigModel, z));
                    textView.setIncludeFontPadding(false);
                }
                parseInt2 = 0;
                if (parseInt != 0) {
                }
                textView.setPadding(dip2px(parseInt / 2), getTopPadding(z2, iconConfigModel), dip2px(parseInt2 / 2), getTopPadding(z2, iconConfigModel));
                z3 = true;
                if (!z3) {
                }
                textView.setGravity(17);
                textView.setTextSize(1, getFontSize(iconConfigModel, z));
                textView.setIncludeFontPadding(false);
            }
            parseInt = 0;
            if (!TextUtils.isEmpty(iconExtraConfigModel.edgeR)) {
            }
            parseInt2 = 0;
            if (parseInt != 0) {
            }
            textView.setPadding(dip2px(parseInt / 2), getTopPadding(z2, iconConfigModel), dip2px(parseInt2 / 2), getTopPadding(z2, iconConfigModel));
            z3 = true;
            if (!z3) {
            }
            textView.setGravity(17);
            textView.setTextSize(1, getFontSize(iconConfigModel, z));
            textView.setIncludeFontPadding(false);
        }
        z3 = false;
        if (!z3) {
        }
        textView.setGravity(17);
        textView.setTextSize(1, getFontSize(iconConfigModel, z));
        textView.setIncludeFontPadding(false);
    }

    public static Bitmap getBitmap(String str, BitmapFactory.Options options, boolean z) {
        return getBitmap(str, options, z, 0);
    }

    public static Drawable getDrawable(String str, boolean z, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, null, z, i2));
    }

    public static Drawable getTextDrawable(String str, String str2, BitmapFactory.Options options, boolean z) {
        return getTextDrawable(str, str2, options, z, true);
    }

    public static TextView getTextView(String str, String str2, boolean z) {
        TextView textView = new TextView(a.g().d());
        setTextViewProperties(str, textView, z);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        }
        return textView;
    }

    public static Bitmap getBitmap(String str, BitmapFactory.Options options, boolean z, int i2) {
        return UnIconConfigController.getController().getBitmap(str, options, z, i2);
    }

    public static SpannableString getSpanableString(List<String> list, String str, TextView textView, boolean z) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = getBitmap(it.next(), z);
            if (bitmap != null) {
                arrayList.add(bitmap);
            }
        }
        return UnSpannaleUtils.getImageSpan(textView.getContext(), arrayList, str);
    }

    public static Drawable getTextDrawable(String str, String str2, BitmapFactory.Options options, boolean z, boolean z2) {
        return getTextDrawable(str, str2, options, z, z2, 0);
    }

    public static void displayIcon(String str, ImageView imageView, boolean z, int i2) {
        Drawable drawable;
        if (TextUtils.isEmpty(str) || imageView == null || (drawable = getDrawable(str, z, i2)) == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    public static Drawable getDrawable(String str, BitmapFactory.Options options) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, options, false));
    }

    public static Drawable getTextDrawable(String str, String str2, BitmapFactory.Options options, boolean z, boolean z2, int i2) {
        Bitmap bitmap;
        if (TextUtils.isEmpty(str) || (bitmap = getBitmap(str, options, z, i2)) == null) {
            return null;
        }
        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        if (!NinePatch.isNinePatchChunk(ninePatchChunk)) {
            return UnDrawableUtil.createDrawable(bitmap);
        }
        return createTextDrawable(str, str2, bitmap, ninePatchChunk, z, z2, i2);
    }

    public static Drawable getDrawable(String str, BitmapFactory.Options options, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, options, z));
    }

    public static TextView getTextView(String str, String str2, boolean z, int i2) {
        TextView textView = new TextView(a.g().d());
        setTextViewProperties(str, textView, z, i2);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        }
        return textView;
    }

    public static Drawable getDrawable(String str, BitmapFactory.Options options, boolean z, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return UnDrawableUtil.createDrawable(getBitmap(str, options, z, i2));
    }

    public static SpannableString getSpanableString(List<String> list, String str, TextView textView, boolean z, int i2) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Bitmap bitmap = getBitmap(it.next(), z, i2);
            if (bitmap != null) {
                arrayList.add(bitmap);
            }
        }
        return UnSpannaleUtils.getImageSpan(textView.getContext(), arrayList, str);
    }
}
