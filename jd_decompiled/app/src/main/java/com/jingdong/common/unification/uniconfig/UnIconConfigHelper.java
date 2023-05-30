package com.jingdong.common.unification.uniconfig;

import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.DpiUtil;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.drawable.Drawable createTextDrawable(java.lang.String r8, java.lang.String r9, android.graphics.Bitmap r10, byte[] r11, boolean r12, boolean r13, int r14) {
        /*
            com.jingdong.common.unification.uniconfig.UnTextNineDrawable r7 = new com.jingdong.common.unification.uniconfig.UnTextNineDrawable
            com.jd.lib.un.business.widget.a r0 = com.jd.lib.un.business.widget.a.g()
            android.content.Context r0 = r0.d()
            android.content.res.Resources r1 = r0.getResources()
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r5 = 0
            r0 = r7
            r2 = r10
            r3 = r11
            r6 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            com.jingdong.common.unification.uniconfig.UnIconConfigController r10 = com.jingdong.common.unification.uniconfig.UnIconConfigController.getController()
            com.jingdong.common.unification.uniconfig.IconConfigModel r8 = r10.getIconConfigModel(r8, r12, r14)
            if (r8 != 0) goto L27
            r10 = 0
            goto L29
        L27:
            com.jingdong.common.unification.uniconfig.IconExtraConfigModel r10 = r8.config
        L29:
            r11 = 1
            r14 = 0
            if (r10 == 0) goto Lb2
            java.lang.String r0 = r10.textColor
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L40
            java.lang.String r0 = r10.textColor     // Catch: java.lang.Exception -> L3f
            int r0 = android.graphics.Color.parseColor(r0)     // Catch: java.lang.Exception -> L3f
            r7.setTextColor(r0)     // Catch: java.lang.Exception -> L3f
            goto L40
        L3f:
        L40:
            java.lang.String r0 = r10.edgeL
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L4f
            java.lang.String r0 = r10.edgeL     // Catch: java.lang.NumberFormatException -> L4f
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L4f
            goto L50
        L4f:
            r0 = 0
        L50:
            java.lang.String r1 = r10.edgeR
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L5f
            java.lang.String r1 = r10.edgeR     // Catch: java.lang.NumberFormatException -> L5f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L5f
            goto L60
        L5f:
            r1 = 0
        L60:
            if (r0 != 0) goto L9f
            if (r1 == 0) goto L65
            goto L9f
        L65:
            java.lang.String r0 = r10.edge
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb2
            java.lang.String r10 = r10.edge     // Catch: java.lang.NumberFormatException -> Lb2
            int r10 = java.lang.Integer.parseInt(r10)     // Catch: java.lang.NumberFormatException -> Lb2
            int r0 = r10 / 2
            float r0 = (float) r0     // Catch: java.lang.NumberFormatException -> Lb2
            int r0 = dip2px(r0)     // Catch: java.lang.NumberFormatException -> Lb2
            int r1 = r10 / 2
            float r1 = (float) r1     // Catch: java.lang.NumberFormatException -> Lb2
            int r1 = dip2px(r1)     // Catch: java.lang.NumberFormatException -> Lb2
            r7.setPadding(r0, r14, r1, r14)     // Catch: java.lang.NumberFormatException -> Lb2
            boolean r0 = com.jingdong.common.UnLog.D     // Catch: java.lang.NumberFormatException -> Lb0
            if (r0 == 0) goto Lb0
            java.lang.String r0 = "UniconConfigHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> Lb0
            r1.<init>()     // Catch: java.lang.NumberFormatException -> Lb0
            java.lang.String r2 = "set text padding:"
            r1.append(r2)     // Catch: java.lang.NumberFormatException -> Lb0
            r1.append(r10)     // Catch: java.lang.NumberFormatException -> Lb0
            java.lang.String r10 = r1.toString()     // Catch: java.lang.NumberFormatException -> Lb0
            com.jingdong.common.UnLog.d(r0, r10)     // Catch: java.lang.NumberFormatException -> Lb0
            goto Lb0
        L9f:
            int r0 = r0 / 2
            float r10 = (float) r0
            int r10 = dip2px(r10)
            int r1 = r1 / 2
            float r0 = (float) r1
            int r0 = dip2px(r0)
            r7.setPadding(r10, r14, r0, r14)
        Lb0:
            r10 = 1
            goto Lb3
        Lb2:
            r10 = 0
        Lb3:
            if (r10 != 0) goto Lc2
            r10 = 1084227584(0x40a00000, float:5.0)
            int r0 = dip2px(r10)
            int r10 = dip2px(r10)
            r7.setPadding(r0, r14, r10, r14)
        Lc2:
            float r8 = getFontSize(r8, r12)
            r7.setTextSize(r11, r8, r13)
            boolean r8 = android.text.TextUtils.isEmpty(r9)
            if (r8 != 0) goto Ld2
            r7.setText(r9)
        Ld2:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.UnIconConfigHelper.createTextDrawable(java.lang.String, java.lang.String, android.graphics.Bitmap, byte[], boolean, boolean, int):android.graphics.drawable.Drawable");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float getFontSize(com.jingdong.common.unification.uniconfig.IconConfigModel r1, boolean r2) {
        /*
            if (r1 == 0) goto L1b
            com.jingdong.common.unification.uniconfig.IconExtraConfigModel r0 = r1.config
            if (r0 == 0) goto L1b
            int r0 = r0.fontSize
            if (r0 <= 0) goto Lc
            float r1 = (float) r0
            goto L1d
        Lc:
            java.lang.String r1 = r1.id
            if (r1 == 0) goto L1b
            java.lang.String r0 = "_b"
            boolean r1 = r1.endsWith(r0)
            if (r1 == 0) goto L1b
            r1 = 1092616192(0x41200000, float:10.0)
            goto L1d
        L1b:
            r1 = 1091567616(0x41100000, float:9.0)
        L1d:
            if (r2 == 0) goto L21
            r1 = 1094713344(0x41400000, float:12.0)
        L21:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.UnIconConfigHelper.getFontSize(com.jingdong.common.unification.uniconfig.IconConfigModel, boolean):float");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.widget.TextView getTextViewOrNull(java.lang.String r6, java.lang.String r7, boolean r8, int r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            android.widget.TextView r0 = new android.widget.TextView     // Catch: java.lang.Exception -> Lde
            com.jd.lib.un.business.widget.a r2 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Exception -> Lde
            android.content.Context r2 = r2.d()     // Catch: java.lang.Exception -> Lde
            r0.<init>(r2)     // Catch: java.lang.Exception -> Lde
            android.graphics.drawable.Drawable r2 = getDrawable(r6)
            if (r2 == 0) goto Ldd
            r0.setBackgroundDrawable(r2)
            com.jingdong.common.unification.uniconfig.UnIconConfigController r2 = com.jingdong.common.unification.uniconfig.UnIconConfigController.getController()
            com.jingdong.common.unification.uniconfig.IconConfigModel r6 = r2.getIconConfigModel(r6, r8, r9)
            if (r6 != 0) goto L29
            goto L2b
        L29:
            com.jingdong.common.unification.uniconfig.IconExtraConfigModel r1 = r6.config
        L2b:
            r9 = 1
            r2 = 0
            if (r1 == 0) goto Lb4
            java.lang.String r3 = r1.textColor
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L42
            java.lang.String r3 = r1.textColor     // Catch: java.lang.Exception -> L41
            int r3 = android.graphics.Color.parseColor(r3)     // Catch: java.lang.Exception -> L41
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> L41
            goto L42
        L41:
        L42:
            java.lang.String r3 = r1.edgeL
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L51
            java.lang.String r3 = r1.edgeL     // Catch: java.lang.NumberFormatException -> L51
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L51
            goto L52
        L51:
            r3 = 0
        L52:
            java.lang.String r4 = r1.edgeR
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L61
            java.lang.String r4 = r1.edgeR     // Catch: java.lang.NumberFormatException -> L61
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L61
            goto L62
        L61:
            r4 = 0
        L62:
            if (r3 != 0) goto La1
            if (r4 == 0) goto L67
            goto La1
        L67:
            java.lang.String r3 = r1.edge
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto Lb4
            java.lang.String r1 = r1.edge     // Catch: java.lang.NumberFormatException -> Lb4
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> Lb4
            int r3 = r1 / 2
            float r3 = (float) r3     // Catch: java.lang.NumberFormatException -> Lb4
            int r3 = dip2px(r3)     // Catch: java.lang.NumberFormatException -> Lb4
            int r4 = r1 / 2
            float r4 = (float) r4     // Catch: java.lang.NumberFormatException -> Lb4
            int r4 = dip2px(r4)     // Catch: java.lang.NumberFormatException -> Lb4
            r0.setPadding(r3, r2, r4, r2)     // Catch: java.lang.NumberFormatException -> Lb4
            boolean r3 = com.jingdong.common.UnLog.D     // Catch: java.lang.NumberFormatException -> Lb2
            if (r3 == 0) goto Lb2
            java.lang.String r3 = "UniconConfigHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> Lb2
            r4.<init>()     // Catch: java.lang.NumberFormatException -> Lb2
            java.lang.String r5 = "set text padding:"
            r4.append(r5)     // Catch: java.lang.NumberFormatException -> Lb2
            r4.append(r1)     // Catch: java.lang.NumberFormatException -> Lb2
            java.lang.String r1 = r4.toString()     // Catch: java.lang.NumberFormatException -> Lb2
            com.jingdong.common.UnLog.d(r3, r1)     // Catch: java.lang.NumberFormatException -> Lb2
            goto Lb2
        La1:
            int r3 = r3 / 2
            float r1 = (float) r3
            int r1 = dip2px(r1)
            int r4 = r4 / 2
            float r3 = (float) r4
            int r3 = dip2px(r3)
            r0.setPadding(r1, r2, r3, r2)
        Lb2:
            r1 = 1
            goto Lb5
        Lb4:
            r1 = 0
        Lb5:
            if (r1 != 0) goto Lc4
            r1 = 1084227584(0x40a00000, float:5.0)
            int r3 = dip2px(r1)
            int r1 = dip2px(r1)
            r0.setPadding(r3, r2, r1, r2)
        Lc4:
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto Lcd
            r0.setText(r7)
        Lcd:
            r7 = 17
            r0.setGravity(r7)
            float r6 = getFontSize(r6, r8)
            r0.setTextSize(r9, r6)
            r0.setIncludeFontPadding(r2)
            return r0
        Ldd:
            return r1
        Lde:
            r6 = move-exception
            boolean r7 = com.jingdong.common.UnLog.D
            if (r7 == 0) goto Le6
            r6.printStackTrace()
        Le6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.UnIconConfigHelper.getTextViewOrNull(java.lang.String, java.lang.String, boolean, int):android.widget.TextView");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setTextViewProperties(java.lang.String r6, android.widget.TextView r7, boolean r8, int r9, boolean r10) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto Le1
            if (r7 != 0) goto La
            goto Le1
        La:
            android.graphics.drawable.Drawable r0 = getDrawable(r6)
            if (r0 == 0) goto L13
            r7.setBackgroundDrawable(r0)
        L13:
            com.jingdong.common.unification.uniconfig.UnIconConfigController r0 = com.jingdong.common.unification.uniconfig.UnIconConfigController.getController()
            com.jingdong.common.unification.uniconfig.IconConfigModel r6 = r0.getIconConfigModel(r6, r8, r9)
            if (r6 != 0) goto L1f
            r9 = 0
            goto L21
        L1f:
            com.jingdong.common.unification.uniconfig.IconExtraConfigModel r9 = r6.config
        L21:
            r0 = 1
            r1 = 0
            if (r9 == 0) goto Lba
            java.lang.String r2 = r9.textColor
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L38
            java.lang.String r2 = r9.textColor     // Catch: java.lang.Exception -> L37
            int r2 = android.graphics.Color.parseColor(r2)     // Catch: java.lang.Exception -> L37
            r7.setTextColor(r2)     // Catch: java.lang.Exception -> L37
            goto L38
        L37:
        L38:
            java.lang.String r2 = r9.edgeL
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L47
            java.lang.String r2 = r9.edgeL     // Catch: java.lang.NumberFormatException -> L47
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L47
            goto L48
        L47:
            r2 = 0
        L48:
            java.lang.String r3 = r9.edgeR
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L57
            java.lang.String r3 = r9.edgeR     // Catch: java.lang.NumberFormatException -> L57
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L57
            goto L58
        L57:
            r3 = 0
        L58:
            if (r2 != 0) goto L9f
            if (r3 == 0) goto L5d
            goto L9f
        L5d:
            java.lang.String r2 = r9.edge
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto Lba
            java.lang.String r9 = r9.edge     // Catch: java.lang.NumberFormatException -> Lba
            int r9 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> Lba
            int r2 = r9 / 2
            float r2 = (float) r2     // Catch: java.lang.NumberFormatException -> Lba
            int r2 = dip2px(r2)     // Catch: java.lang.NumberFormatException -> Lba
            int r3 = getTopPadding(r10, r6)     // Catch: java.lang.NumberFormatException -> Lba
            int r4 = r9 / 2
            float r4 = (float) r4     // Catch: java.lang.NumberFormatException -> Lba
            int r4 = dip2px(r4)     // Catch: java.lang.NumberFormatException -> Lba
            int r5 = getTopPadding(r10, r6)     // Catch: java.lang.NumberFormatException -> Lba
            r7.setPadding(r2, r3, r4, r5)     // Catch: java.lang.NumberFormatException -> Lba
            boolean r2 = com.jingdong.common.UnLog.D     // Catch: java.lang.NumberFormatException -> Lb8
            if (r2 == 0) goto Lb8
            java.lang.String r2 = "UniconConfigHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> Lb8
            r3.<init>()     // Catch: java.lang.NumberFormatException -> Lb8
            java.lang.String r4 = "set text padding:"
            r3.append(r4)     // Catch: java.lang.NumberFormatException -> Lb8
            r3.append(r9)     // Catch: java.lang.NumberFormatException -> Lb8
            java.lang.String r9 = r3.toString()     // Catch: java.lang.NumberFormatException -> Lb8
            com.jingdong.common.UnLog.d(r2, r9)     // Catch: java.lang.NumberFormatException -> Lb8
            goto Lb8
        L9f:
            int r2 = r2 / 2
            float r9 = (float) r2
            int r9 = dip2px(r9)
            int r2 = getTopPadding(r10, r6)
            int r3 = r3 / 2
            float r3 = (float) r3
            int r3 = dip2px(r3)
            int r4 = getTopPadding(r10, r6)
            r7.setPadding(r9, r2, r3, r4)
        Lb8:
            r9 = 1
            goto Lbb
        Lba:
            r9 = 0
        Lbb:
            if (r9 != 0) goto Ld2
            r9 = 1084227584(0x40a00000, float:5.0)
            int r2 = dip2px(r9)
            int r3 = getTopPadding(r10, r6)
            int r9 = dip2px(r9)
            int r10 = getTopPadding(r10, r6)
            r7.setPadding(r2, r3, r9, r10)
        Ld2:
            r9 = 17
            r7.setGravity(r9)
            float r6 = getFontSize(r6, r8)
            r7.setTextSize(r0, r6)
            r7.setIncludeFontPadding(r1)
        Le1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.UnIconConfigHelper.setTextViewProperties(java.lang.String, android.widget.TextView, boolean, int, boolean):void");
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
