package com.jingdong.common.unification.album;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.deeplinkhelper.DeepLinkAlbumHelper;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class UnAlbumHelper {
    public static boolean isUseSystemAlbum(@NonNull AlbumParam albumParam) {
        return isUseSystemAlbum(albumParam.source, albumParam.isUseSystemAlbum);
    }

    public static boolean openNewAlbum(String str) {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        if (TextUtils.equals(str, AlbumConstant.FROM_EVALUATE)) {
            str = "evaluate";
        }
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("unification", "album", str), "1")) {
            String config = JDMobileConfig.getInstance().getConfig("unification", "album", "deviceBlacklist");
            if (TextUtils.isEmpty(config)) {
                return true;
            }
            String brand = UnAndroidUtils.getBrand();
            String model = UnAndroidUtils.getModel();
            if (!TextUtils.isEmpty(brand) && !TextUtils.isEmpty(model)) {
                if (config.contains(brand.toUpperCase() + "-" + model.toUpperCase())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void startPreviewByLocalMedia(Activity activity, ArrayList<LocalMedia> arrayList, int i2, boolean z, int i3) {
        if (activity == null || arrayList == null) {
            return;
        }
        DeepLinkAlbumHelper.startPreviewActivityByLocalMedia(activity, arrayList, i2, z, i3);
    }

    public static void startPreviewByPath(Activity activity, ArrayList<String> arrayList, int i2, boolean z, int i3) {
        if (activity == null || arrayList == null) {
            return;
        }
        DeepLinkAlbumHelper.startPreviewActivityByPath(activity, arrayList, i2, z, i3);
    }

    public static boolean isUseSystemAlbum(String str, boolean z) {
        String config = JDMobileConfig.getInstance().getConfig("unification", "systemAlbum", "useSystemAlbum");
        if (TextUtils.isEmpty(config)) {
            config = "2";
        }
        if (TextUtils.equals("1", config)) {
            return true;
        }
        if (TextUtils.equals("0", config)) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            String config2 = JDMobileConfig.getInstance().getConfig("unification", "systemAlbum", "openModel");
            if (!TextUtils.isEmpty(config2) && config2.contains(str)) {
                return true;
            }
            String config3 = JDMobileConfig.getInstance().getConfig("unification", "systemAlbum", "closeModel");
            if (!TextUtils.isEmpty(config3) && config3.contains(str)) {
                return false;
            }
        } else {
            String config4 = JDMobileConfig.getInstance().getConfig("unification", "systemAlbum", "defaultNoSource");
            if (!TextUtils.isEmpty(config4)) {
                return config4.equals("1");
            }
        }
        return z;
    }
}
