package com.jingdong.common.unification.title.theme;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import com.jd.lib.un.business.R;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnBitmapConvertUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.UnCustomThemeUtils;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class ThemeTitleHelper {
    public static final String ICON_DIR = "/theme_title";
    private static final String TAG = "ThemeTitleHelper";
    private static volatile String currentFlag;
    private static ConcurrentHashMap<String, ThemeTitleSurface> currentModuleThemes = new ConcurrentHashMap<>();
    private static boolean isOpen = false;
    private static ConcurrentHashMap<IThemeChangeListener, String> currentModulechangeListeners = new ConcurrentHashMap<>();
    public static final Drawable TRANSPARENT_BACKGROUND = new ColorDrawable(0);

    public static boolean bitmapHaveTransColor(String str) {
        Bitmap decodeFile;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile() && (decodeFile = BitmapFactory.decodeFile(str)) != null) {
            Bitmap copy = decodeFile.copy(Bitmap.Config.ARGB_8888, true);
            for (int i2 = 0; i2 < copy.getHeight(); i2++) {
                for (int i3 = 0; i3 < copy.getWidth(); i3++) {
                    if (copy.getPixel(i3, i2) == 0) {
                        return true;
                    }
                }
            }
            if (!copy.isRecycled()) {
                copy.recycle();
            }
            return false;
        }
        return false;
    }

    public static void changeAllThemeTitleData(ThemeTitleGoldenSurface themeTitleGoldenSurface) {
        if (themeTitleGoldenSurface != null) {
            isOpen = themeTitleGoldenSurface.isOpen;
            List<ThemeTitleSurface> list = themeTitleGoldenSurface.surfaces;
            if (UnLog.D) {
                UnLog.d(TAG, "changeAllThemeTitleData list:" + list);
            }
            currentModuleThemes.clear();
            if (isOpen && list != null && list.size() > 0) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    ThemeTitleSurface themeTitleSurface = list.get(i2);
                    if (themeTitleSurface != null && !TextUtils.isEmpty(themeTitleSurface.pageCode)) {
                        currentModuleThemes.put(themeTitleSurface.pageCode, themeTitleSurface);
                    }
                }
            }
            if (UnLog.D) {
                UnLog.d(TAG, "changeAllThemeTitleData:" + currentModuleThemes.toString());
            }
        } else {
            currentModuleThemes.clear();
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            return;
        }
        notifyAllTitleChange();
    }

    public static void changeSomeThemeTitleData(List<ThemeTitleSurface> list, String str) {
        isOpen = true;
        if (list == null || list.size() == 0) {
            return;
        }
        if (UnLog.D) {
            UnLog.d(TAG, "changeSomeThemeTitleData list:" + list.toString());
        }
        if (!TextUtils.equals(str, currentFlag)) {
            currentModuleThemes.clear();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ThemeTitleSurface themeTitleSurface = list.get(i2);
            if (themeTitleSurface != null && !TextUtils.isEmpty(themeTitleSurface.pageCode)) {
                currentModuleThemes.put(themeTitleSurface.pageCode, themeTitleSurface);
                if (TextUtils.equals(str, currentFlag) && !UnCustomThemeHelper.getInstance().customThemeEnable()) {
                    notifySomeTitleChange(themeTitleSurface.pageCode);
                }
            }
        }
        if (!TextUtils.equals(str, currentFlag) && !UnCustomThemeHelper.getInstance().customThemeEnable()) {
            notifyAllTitleChange();
        }
        currentFlag = str;
    }

    public static String creatFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String substring = str.substring(str.lastIndexOf("/") + 1);
        if (UnLog.D) {
            UnLog.d(TAG, "urlname\uff1a" + substring);
        }
        return substring;
    }

    public static String creatFilePath() {
        File file = new File(JDHttpTookit.getEngine().getApplicationContext().getFilesDir(), ICON_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static void displayTitleBg(boolean z, boolean z2, String str, ImageView imageView, final ILoadBgCallback iLoadBgCallback) {
        if (imageView == null) {
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        } else if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            if (z2) {
                int i2 = R.color.un_theme_title_dark_bg;
                createSimple.showImageForEmptyUri(i2);
                createSimple.showImageOnFail(i2);
            } else {
                int i3 = R.drawable.common_title_background;
                createSimple.showImageForEmptyUri(i3);
                createSimple.showImageOnFail(i3);
            }
            createSimple.showImageOnLoading(TRANSPARENT_BACKGROUND);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            createSimple.decodingOptions(options);
            if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
                final ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo("title", str, "imageUrl", z2);
                if (imageInfo != null && imageInfo.isEffected) {
                    String str2 = "file://" + imageInfo.localPath;
                    if (useImageUrl()) {
                        str2 = imageInfo.url;
                    }
                    JDImageUtils.displayImage(str2, imageView, createSimple, new JDImageLoadingListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleHelper.1
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str3, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                            imageInfo.isEffected = true;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                            imageInfo.isEffected = false;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str3, View view) {
                        }
                    });
                    return;
                }
                setDefaultBg(imageView, z2, z);
                if (iLoadBgCallback != null) {
                    iLoadBgCallback.loadBack();
                }
            }
        } else {
            setDefaultBg(imageView, z2, z);
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        }
    }

    public static boolean fillColorToTrans(String str, int i2) {
        Bitmap decodeFile;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile() && (decodeFile = BitmapFactory.decodeFile(str)) != null) {
            Bitmap copy = decodeFile.copy(Bitmap.Config.ARGB_8888, true);
            int i3 = 0;
            for (int i4 = 0; i4 < copy.getHeight(); i4++) {
                for (int i5 = 0; i5 < copy.getWidth(); i5++) {
                    if (copy.getPixel(i5, i4) == 0) {
                        i3++;
                        copy.setPixel(i5, i4, i2);
                    }
                }
            }
            if (i3 == 0) {
                return true;
            }
            return saveBitmap(copy, str);
        }
        return false;
    }

    public static int getBgResource(boolean z) {
        if (z) {
            return R.color.c_F9F9F9;
        }
        return R.color.un_bg_level_2;
    }

    public static int getButtonTextColor(Context context, String str, boolean z) {
        if (context == null) {
            return -1;
        }
        if (a.g().p() && z) {
            return context.getResources().getColor(R.color.white);
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            if (!TextUtils.isEmpty(str)) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                    return context.getResources().getColor(R.color.white);
                }
            }
        } else if (isOpen && !TextUtils.isEmpty(str)) {
            ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
            if (UnLog.D) {
                UnLog.d(TAG, "getButtonTextColor surface:" + themeTitleSurface);
            }
            if (themeTitleSurface != null && themeTitleSurface.isEffected && TextUtils.equals(themeTitleSurface.colorType, "LIGHT")) {
                return context.getResources().getColor(R.color.white);
            }
        }
        return context.getResources().getColor(R.color.un_content_level_1);
    }

    public static String getCDStringById(Context context, String str) {
        String str2;
        str2 = "";
        if (!TextUtils.isEmpty(str) && context != null) {
            String string = context.getString(getContentDescriptionResourceId(str));
            str2 = TextUtils.isEmpty(string) ? "" : string;
            UnLog.d("ContentDescription", str2);
        }
        return str2;
    }

    public static int getContentDescriptionResourceId(String str) {
        if (TextUtils.equals(str, "cart")) {
            return R.string.description_title_cart;
        }
        if (TextUtils.equals(str, "category")) {
            return R.string.description_title_category;
        }
        if (TextUtils.equals(str, "close")) {
            return R.string.description_title_close;
        }
        if (TextUtils.equals(str, "filter")) {
            return R.string.description_title_filter;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_FORWARD_DRAWABLE_ID)) {
            return R.string.description_title_forward;
        }
        if (TextUtils.equals(str, "history")) {
            return R.string.description_title_history;
        }
        if (TextUtils.equals(str, "message")) {
            return R.string.description_title_message;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID)) {
            return R.string.description_title_setting;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_CLOSE_DRAWABLE_ID)) {
            return R.string.description_title_input_close;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_SEARCH_DRAWABLE_ID)) {
            return R.string.description_title_input_search;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_VOICE_DRAWABLE_ID)) {
            return R.string.description_title_input_voice;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) {
            return R.string.description_title_list;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID)) {
            return R.string.description_title_location;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_MORE_HORIZONTAL_DRAWABLE_ID)) {
            return R.string.description_title_more_horizontal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERIODICCHARGE_DRAWABLE_ID)) {
            return R.string.description_title_periodic_charge;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERSION_DRAWABLE_ID)) {
            return R.string.description_title_person;
        }
        if (TextUtils.equals(str, "scan")) {
            return R.string.description_title_scan;
        }
        if (TextUtils.equals(str, "search")) {
            return R.string.description_title_search;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHARECOURTESY_DRAWABLE_ID)) {
            return R.string.description_title_share_courtesy;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHOP_DRAWABLE_ID)) {
            return R.string.description_title_shop;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID)) {
            return R.string.description_title_back;
        }
        if (TextUtils.equals(str, "info")) {
            return R.string.description_title_info;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)) {
            return R.string.description_title_address;
        }
        return -1;
    }

    public static int getDarkDrawable(String str) {
        if (TextUtils.equals(str, "cart")) {
            return R.drawable.common_car_black_normal;
        }
        if (TextUtils.equals(str, "category")) {
            return R.drawable.common_category_black_normal;
        }
        if (TextUtils.equals(str, "close")) {
            return R.drawable.common_close_normal;
        }
        if (TextUtils.equals(str, "filter")) {
            return R.drawable.common_filter_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_FORWARD_DRAWABLE_ID)) {
            return R.drawable.common_forward_normal;
        }
        if (TextUtils.equals(str, "history")) {
            return R.drawable.common_histroy_normal;
        }
        if (TextUtils.equals(str, "message")) {
            return R.drawable.common_icon_message_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID)) {
            return R.drawable.common_icon_setting_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_CLOSE_DRAWABLE_ID)) {
            return R.drawable.common_input_close_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_SEARCH_DRAWABLE_ID)) {
            return R.drawable.common_input_search_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_VOICE_DRAWABLE_ID)) {
            return R.drawable.common_input_voice_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) {
            return R.drawable.common_list_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID)) {
            return R.drawable.common_location_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_MORE_HORIZONTAL_DRAWABLE_ID)) {
            return R.drawable.common_more_horizontal_nomal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERIODICCHARGE_DRAWABLE_ID)) {
            return R.drawable.common_periodiccharge_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERSION_DRAWABLE_ID)) {
            return R.drawable.common_persion_black_normal;
        }
        if (TextUtils.equals(str, "scan")) {
            return R.drawable.common_scan_normal;
        }
        if (TextUtils.equals(str, "search")) {
            return R.drawable.common_search_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHARECOURTESY_DRAWABLE_ID)) {
            return R.drawable.common_sharecourtesy_black;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHOP_DRAWABLE_ID)) {
            return R.drawable.common_shop_black_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID)) {
            return R.drawable.un_common_title_back_normal;
        }
        if (TextUtils.equals(str, "info")) {
            return R.drawable.common_title_info_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_CALENDARS_DRAWABLE_ID)) {
            return R.drawable.common_calendars_dark;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)) {
            return R.drawable.common_address_black_normal;
        }
        return -1;
    }

    public static int getLigthDrawable(String str) {
        if (TextUtils.equals(str, "cart")) {
            return R.drawable.common_car_white_normal;
        }
        if (TextUtils.equals(str, "category")) {
            return R.drawable.common_category_white_normal;
        }
        if (TextUtils.equals(str, "close")) {
            return R.drawable.common_close_white_normal;
        }
        if (TextUtils.equals(str, "filter")) {
            return R.drawable.common_filter_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_FORWARD_DRAWABLE_ID)) {
            return R.drawable.common_forward_white_normal;
        }
        if (TextUtils.equals(str, "history")) {
            return R.drawable.common_histroy_white_normal;
        }
        if (TextUtils.equals(str, "message")) {
            return R.drawable.common_icon_message_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID)) {
            return R.drawable.common_icon_setting_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_CLOSE_DRAWABLE_ID)) {
            return R.drawable.common_input_close_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_SEARCH_DRAWABLE_ID)) {
            return R.drawable.common_input_search_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_INPUT_VOICE_DRAWABLE_ID)) {
            return R.drawable.common_input_voice_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) {
            return R.drawable.common_list_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID)) {
            return R.drawable.common_location_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_MORE_HORIZONTAL_DRAWABLE_ID)) {
            return R.drawable.common_more_horizontal_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERIODICCHARGE_DRAWABLE_ID)) {
            return R.drawable.common_periodiccharge_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_PERSION_DRAWABLE_ID)) {
            return R.drawable.common_persion_white_normal;
        }
        if (TextUtils.equals(str, "scan")) {
            return R.drawable.common_scan_white_normal;
        }
        if (TextUtils.equals(str, "search")) {
            return R.drawable.common_search_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHARECOURTESY_DRAWABLE_ID)) {
            return R.drawable.common_sharecourtesy_white;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_SHOP_DRAWABLE_ID)) {
            return R.drawable.common_shop_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID)) {
            return R.drawable.common_title_back_white_normal;
        }
        if (TextUtils.equals(str, "info")) {
            return R.drawable.common_title_info_white_normal;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_CALENDARS_DRAWABLE_ID)) {
            return R.drawable.common_calendars_light;
        }
        if (TextUtils.equals(str, ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID)) {
            return R.drawable.common_address_white_normal;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:184:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:144:0x0022 -> B:181:0x0061). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Bitmap getLocalBitmap(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Bitmap bitmap = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                } catch (Exception e3) {
                    e = e3;
                    bufferedInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = null;
                    if (fileInputStream != null) {
                    }
                    if (bufferedInputStream != null) {
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedInputStream = null;
                fileInputStream = null;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                bufferedInputStream = null;
            }
            try {
                try {
                    bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    bufferedInputStream.close();
                } catch (Throwable th4) {
                    th = th4;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e8) {
                e = e8;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                return bitmap;
            }
        }
        return bitmap;
    }

    public static String getResourcesToBase64(Context context, String str, String str2) {
        Drawable titleDrawable;
        if (context == null || (titleDrawable = getTitleDrawable(context, str, str2)) == null) {
            return null;
        }
        return Base64.encodeToString(UnBitmapConvertUtils.drawable2Bytes(titleDrawable, Bitmap.CompressFormat.PNG), 0);
    }

    public static String getResourcesUri(Context context, String str, String str2) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return null;
        }
        int titleDrawableResourceId = getTitleDrawableResourceId(str, str2);
        return "android.resource://" + resources.getResourcePackageName(titleDrawableResourceId) + "/" + resources.getResourceTypeName(titleDrawableResourceId) + "/" + resources.getResourceEntryName(titleDrawableResourceId);
    }

    public static String getThemeTitleColorStyle(String str) {
        if (TextUtils.isEmpty(str)) {
            return "DARK";
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
            return (imageInfo == null || !imageInfo.isEffected) ? "DARK" : imageInfo.colorType;
        }
        ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
        return (isOpen && themeTitleSurface != null && themeTitleSurface.isEffected) ? themeTitleSurface.colorType : "DARK";
    }

    public static Drawable getTitleBg(Context context, String str, boolean z) {
        if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", z));
            if (imageInfo != null && imageInfo.isEffected && !TextUtils.isEmpty(imageInfo.localPath)) {
                Bitmap localBitmap = getLocalBitmap(imageInfo.localPath);
                if (localBitmap != null) {
                    return new BitmapDrawable(localBitmap);
                }
                imageInfo.isEffected = false;
            }
        }
        if (context == null) {
            return null;
        }
        if (z) {
            return context.getResources().getDrawable(R.color.un_theme_title_dark_bg);
        }
        return context.getResources().getDrawable(R.drawable.common_title_background);
    }

    public static Drawable getTitleBgAuto(Context context, String str, boolean z) {
        Drawable titleBg;
        return (!z || (titleBg = getTitleBg(context, str, z)) == null) ? getTitleBg(context, str) : titleBg;
    }

    public static Drawable getTitleBgDefalut(Context context, String str, boolean z) {
        if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", z));
            if (imageInfo == null && z) {
                imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", !z));
            }
            if (imageInfo != null && imageInfo.isEffected && !TextUtils.isEmpty(imageInfo.localPath)) {
                Bitmap localBitmap = getLocalBitmap(imageInfo.localPath);
                if (localBitmap != null) {
                    return new BitmapDrawable(localBitmap);
                }
                imageInfo.isEffected = false;
            }
        }
        if (context == null) {
            return null;
        }
        if (z) {
            return context.getResources().getDrawable(R.color.un_theme_title_dark_bg);
        }
        return context.getResources().getDrawable(R.drawable.common_title_background);
    }

    public static String getTitleBgUrl(String str) {
        ThemeTitleSurface themeTitleSurface;
        if (!UnCustomThemeHelper.getInstance().customThemeEnable()) {
            return (!isOpen || TextUtils.isEmpty(str) || (themeTitleSurface = currentModuleThemes.get(str)) == null || !themeTitleSurface.isEffected || TextUtils.isEmpty(themeTitleSurface.localImagePath)) ? "" : themeTitleSurface.localImagePath;
        } else if (!TextUtils.isEmpty(str)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
            return (imageInfo == null || !imageInfo.isEffected || TextUtils.isEmpty(imageInfo.localPath)) ? "" : imageInfo.localPath;
        }
        return "";
    }

    public static Drawable getTitleDrawable(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            int titleDrawableResourceId = getTitleDrawableResourceId(str, str2);
            r0 = titleDrawableResourceId != -1 ? context.getResources().getDrawable(titleDrawableResourceId) : null;
            if (UnLog.D) {
                UnLog.d(TAG, "getTitleDrawable:" + str + ",drawable:" + r0);
            }
        }
        return r0;
    }

    public static int getTitleDrawableResourceId(String str, String str2) {
        ThemeTitleSurface themeTitleSurface;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            if (!TextUtils.isEmpty(str2)) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str2, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                    return getLigthDrawable(str);
                }
            }
        } else if (isOpen && !TextUtils.isEmpty(str2) && (themeTitleSurface = currentModuleThemes.get(str2)) != null && themeTitleSurface.isEffected && TextUtils.equals(themeTitleSurface.colorType, "LIGHT")) {
            return getLigthDrawable(str);
        }
        return getDarkDrawable(str);
    }

    public static String getTitleImageUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
        if (imageInfo == null || !imageInfo.isEffected) {
            return "";
        }
        if (TextUtils.isEmpty(imageInfo.url)) {
            return imageInfo.localPath;
        }
        return imageInfo.url;
    }

    public static String getTitleLocalBgAuto(String str, boolean z) {
        if (z) {
            String titleBgUrl = getTitleBgUrl(str, z);
            if (!TextUtils.isEmpty(titleBgUrl)) {
                return titleBgUrl;
            }
        }
        return getTitleBgUrl(str);
    }

    public static int getTitleTextColor(Context context, String str) {
        if (context == null) {
            return -1;
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            if (!TextUtils.isEmpty(str)) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                    return context.getResources().getColor(R.color.white);
                }
            }
        } else if (isOpen && !TextUtils.isEmpty(str)) {
            ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
            if (UnLog.D) {
                UnLog.d(TAG, "getTitleTextColor surface:" + themeTitleSurface);
            }
            if (themeTitleSurface != null && themeTitleSurface.isEffected && TextUtils.equals(themeTitleSurface.colorType, "LIGHT")) {
                return context.getResources().getColor(R.color.white);
            }
        }
        return context.getResources().getColor(R.color.un_content_level_1);
    }

    public static String getTitleUrlAuto(String str, boolean z) {
        if (z) {
            String titleImageUrl = getTitleImageUrl(str, z);
            if (!TextUtils.isEmpty(titleImageUrl)) {
                return titleImageUrl;
            }
        }
        return getTitleImageUrl(str);
    }

    public static boolean isOpen() {
        return isOpen || UnCustomThemeHelper.getInstance().customThemeEnable();
    }

    public static boolean isThemeTitleEffected(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
            return imageInfo != null && imageInfo.isEffected;
        }
        ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
        return isOpen && themeTitleSurface != null && themeTitleSurface.isEffected;
    }

    public static void loadTitleBg(String str, ImageView imageView, Context context) {
        displayTitleBg(false, str, imageView, null);
    }

    public static void notifyAllTitleChange() {
        if (currentModulechangeListeners.isEmpty()) {
            return;
        }
        for (final Map.Entry<IThemeChangeListener, String> entry : currentModulechangeListeners.entrySet()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleHelper.4
                @Override // java.lang.Runnable
                public void run() {
                    if (entry.getKey() != null) {
                        ((IThemeChangeListener) entry.getKey()).onThemeChange(ThemeTitleHelper.isThemeTitleEffected((String) entry.getValue()), ThemeTitleHelper.getThemeTitleColorStyle((String) entry.getValue()));
                    }
                }
            });
        }
    }

    public static void notifySomeTitleChange(final String str) {
        if (currentModulechangeListeners.isEmpty()) {
            return;
        }
        for (final Map.Entry<IThemeChangeListener, String> entry : currentModulechangeListeners.entrySet()) {
            if (str.equals(entry.getValue())) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleHelper.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (entry.getKey() != null) {
                            ((IThemeChangeListener) entry.getKey()).onThemeChange(ThemeTitleHelper.isThemeTitleEffected(str), ThemeTitleHelper.getThemeTitleColorStyle(str));
                        }
                    }
                });
            }
        }
    }

    public static boolean promotionOpen() {
        return isOpen;
    }

    public static void removeThemeTitleChangeListener(IThemeChangeListener iThemeChangeListener) {
        if (iThemeChangeListener != null) {
            currentModulechangeListeners.remove(iThemeChangeListener);
        }
    }

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        if (!TextUtils.isEmpty(str) && bitmap != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                if (bitmap.isRecycled()) {
                    return true;
                }
                bitmap.recycle();
                return true;
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    public static void setDefaultBg(ImageView imageView, boolean z) {
        setDefaultBg(imageView, z, false);
    }

    public static void setThemeTitleChangeListener(String str, IThemeChangeListener iThemeChangeListener) {
        if (TextUtils.isEmpty(str) || iThemeChangeListener == null) {
            return;
        }
        currentModulechangeListeners.put(iThemeChangeListener, str);
    }

    private static boolean titleTransOpen() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "titleTransOpen");
        return !TextUtils.isEmpty(config) && TextUtils.equals("1", config);
    }

    public static boolean useCustomTheme() {
        String themeId = UnCustomThemeHelper.getInstance().getThemeId();
        return (TextUtils.isEmpty(themeId) || TextUtils.equals(themeId, "0")) ? false : true;
    }

    private static boolean useImageUrl() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "useImageUrl");
        return !TextUtils.isEmpty(config) && TextUtils.equals("1", config);
    }

    public static void loadTitleBg(String str, ImageView imageView, boolean z) {
        displayTitleBg(z, str, imageView, null);
    }

    public static void setDefaultBg(ImageView imageView, boolean z, boolean z2) {
        if (imageView == null) {
            return;
        }
        if (a.g().p() && z) {
            imageView.setImageResource(R.color.un_theme_title_dark_bg);
        } else {
            imageView.setImageResource(getBgResource(z2));
        }
    }

    public static Drawable getTitleDrawable(Context context, String str, String str2, boolean z) {
        if (context != null && !TextUtils.isEmpty(str)) {
            int titleDrawableResourceId = getTitleDrawableResourceId(str, str2, z);
            r0 = titleDrawableResourceId != -1 ? context.getResources().getDrawable(titleDrawableResourceId) : null;
            if (UnLog.D) {
                UnLog.d(TAG, "getTitleDrawable:" + str + ",drawable:" + r0);
            }
        }
        return r0;
    }

    public static String getTitleImageUrl(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", z));
        if (imageInfo == null || !imageInfo.isEffected) {
            return "";
        }
        if (TextUtils.isEmpty(imageInfo.url)) {
            return imageInfo.localPath;
        }
        return imageInfo.url;
    }

    public static String getThemeTitleColorStyle(String str, boolean z) {
        if (a.g().p() && z) {
            if (UnLog.D) {
                UnLog.d(TAG, "colorStyle: darkMode LIGHT");
                return "LIGHT";
            }
            return "LIGHT";
        }
        if (!TextUtils.isEmpty(str)) {
            if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected) {
                    if (UnLog.D) {
                        UnLog.d(TAG, "colorStyle: theme " + imageInfo.colorType);
                    }
                    return imageInfo.colorType;
                }
            } else {
                ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
                if (isOpen && themeTitleSurface != null && themeTitleSurface.isEffected) {
                    return themeTitleSurface.colorType;
                }
            }
        }
        if (UnLog.D) {
            UnLog.d(TAG, "colorStyle: default DARK");
            return "DARK";
        }
        return "DARK";
    }

    public static Drawable getTitleBg(Context context, String str) {
        if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
            if (imageInfo != null && imageInfo.isEffected && !TextUtils.isEmpty(imageInfo.localPath)) {
                Bitmap localBitmap = getLocalBitmap(imageInfo.localPath);
                if (localBitmap != null) {
                    return new BitmapDrawable(localBitmap);
                }
                imageInfo.isEffected = false;
            }
        }
        if (context == null) {
            return null;
        }
        return context.getResources().getDrawable(R.drawable.common_title_background);
    }

    public static String getTitleBgUrl(String str, boolean z) {
        if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", z));
            return (imageInfo == null || !imageInfo.isEffected || TextUtils.isEmpty(imageInfo.localPath)) ? "" : imageInfo.localPath;
        }
        return "";
    }

    public static int getTitleDrawableResourceId(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (z) {
            return getLigthDrawable(str);
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str2)) {
            ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str2, "imageUrl"));
            if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                return getLigthDrawable(str);
            }
        }
        return getDarkDrawable(str);
    }

    public static int getTitleTextColor(Context context, String str, boolean z) {
        if (context == null) {
            return -1;
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            if (!TextUtils.isEmpty(str)) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                    return context.getResources().getColor(R.color.white);
                }
            }
        } else if (isOpen && !TextUtils.isEmpty(str)) {
            ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
            if (UnLog.D) {
                UnLog.d(TAG, "getTitleTextColor surface:" + themeTitleSurface);
            }
            if (themeTitleSurface != null && themeTitleSurface.isEffected && TextUtils.equals(themeTitleSurface.colorType, "LIGHT")) {
                return context.getResources().getColor(R.color.white);
            }
        }
        if (a.g().p() && z) {
            return context.getResources().getColor(R.color.white);
        }
        return context.getResources().getColor(R.color.un_content_level_1);
    }

    public static int getButtonTextColor(Context context, String str) {
        if (context == null) {
            return -1;
        }
        if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
            if (!TextUtils.isEmpty(str)) {
                ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected && TextUtils.equals(imageInfo.colorType, "LIGHT")) {
                    return context.getResources().getColor(R.color.white);
                }
            }
        } else if (isOpen && !TextUtils.isEmpty(str)) {
            ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
            if (UnLog.D) {
                UnLog.d(TAG, "getButtonTextColor surface:" + themeTitleSurface);
            }
            if (themeTitleSurface != null && themeTitleSurface.isEffected && TextUtils.equals(themeTitleSurface.colorType, "LIGHT")) {
                return context.getResources().getColor(R.color.white);
            }
        }
        return context.getResources().getColor(R.color.un_content_level_1);
    }

    public static void displayTitleBg(boolean z, String str, ImageView imageView, ILoadBgCallback iLoadBgCallback) {
        displayTitleBg(false, z, str, imageView, iLoadBgCallback);
    }

    public static void displayTitleBg(String str, ImageView imageView, final ILoadBgCallback iLoadBgCallback) {
        if (imageView == null) {
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        } else if ((!isOpen && !UnCustomThemeHelper.getInstance().customThemeEnable()) || TextUtils.isEmpty(str)) {
            imageView.setImageResource(R.drawable.common_title_background);
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        } else {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            int i2 = R.drawable.common_title_background;
            createSimple.showImageForEmptyUri(i2);
            createSimple.showImageOnFail(i2);
            createSimple.showImageOnLoading(TRANSPARENT_BACKGROUND);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            createSimple.decodingOptions(options);
            if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
                final ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo(UnCustomThemeHelper.getInstance().createImageId("title", str, "imageUrl"));
                if (imageInfo != null && imageInfo.isEffected) {
                    String str2 = "file://" + imageInfo.localPath;
                    if (useImageUrl()) {
                        str2 = imageInfo.url;
                    }
                    JDImageUtils.displayImage(str2, imageView, createSimple, new JDImageLoadingListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleHelper.2
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str3, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                            imageInfo.isEffected = true;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                            imageInfo.isEffected = false;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str3, View view) {
                        }
                    });
                    return;
                }
                imageView.setImageResource(i2);
                if (iLoadBgCallback != null) {
                    iLoadBgCallback.loadBack();
                }
            } else if (!isOpen) {
                imageView.setImageResource(i2);
                if (iLoadBgCallback != null) {
                    iLoadBgCallback.loadBack();
                }
            } else {
                final ThemeTitleSurface themeTitleSurface = currentModuleThemes.get(str);
                if (themeTitleSurface != null && themeTitleSurface.isEffected) {
                    String str3 = themeTitleSurface.localImagePath;
                    if (UnLog.D) {
                        UnLog.d("JdThemeTitle", "file://" + str3);
                    }
                    JDImageUtils.displayImage("file://" + str3, imageView, createSimple, new JDImageLoadingListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleHelper.3
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str4, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                            themeTitleSurface.isEffected = true;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                            themeTitleSurface.isEffected = false;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str4, View view) {
                        }
                    });
                    return;
                }
                imageView.setImageResource(i2);
                if (iLoadBgCallback != null) {
                    iLoadBgCallback.loadBack();
                }
            }
        }
    }
}
