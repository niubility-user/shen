package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class DeepLink3DProductHelper {
    public static final String EXTRA_AR = "ar";
    public static final String EXTRA_CAN_ADD_CART = "canAddCart";
    public static final String EXTRA_CID = "cid";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_ISCANUSEDONG = "isCanUseDong";
    public static final String EXTRA_ISCANUSEJING = "isCanUseJing";
    public static final String EXTRA_IS_GLOBAL = "isGlobal";
    public static final String EXTRA_MODELURL = "modelUrl";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_SKU_ID = "skuId";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_VENDOR_ID = "vendorId";
    private static final String TAG = "DeepLink3DProductHelper";

    /* loaded from: classes5.dex */
    public interface PreloadingListener {
        void onProgress(long j2);
    }

    public static Object callBundleMethod(String str, String str2, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass(str);
            if (OKLog.D) {
                OKLog.d(TAG, "clz=====" + loadClass);
            }
            if (loadClass == null) {
                return null;
            }
            try {
                if (clsArr != null) {
                    declaredMethod = loadClass.getDeclaredMethod(str2, clsArr);
                } else {
                    declaredMethod = loadClass.getDeclaredMethod(str2, new Class[0]);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "method=====" + declaredMethod);
                }
                if (declaredMethod == null) {
                    return null;
                }
                return declaredMethod.invoke(null, objArr);
            } catch (NoSuchMethodException e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                    return null;
                }
                return null;
            } catch (InvocationTargetException e3) {
                if (OKLog.D) {
                    OKLog.e(TAG, e3);
                    return null;
                }
                return null;
            }
        } catch (ClassNotFoundException e4) {
            if (OKLog.D) {
                OKLog.e(TAG, e4);
                return null;
            }
            return null;
        } catch (IllegalAccessException e5) {
            if (OKLog.D) {
                OKLog.e(TAG, e5);
                return null;
            }
            return null;
        }
    }

    public static void cancel_preload(FrameLayout frameLayout) {
        callBundleMethod("com.jd.lib.threedproduct.CacheManager", "cancel_preload", new Class[]{FrameLayout.class}, new Object[]{frameLayout});
    }

    public static void cleanCache() {
        DeepLinkArVrPublicInterfaceHelper.callBundleMethod("com.jd.lib.threedproduct.CacheManager", "cleanCache", null, null);
        DeepLinkArVrPublicInterfaceHelper.callBundleMethod("com.jd.lib.armakeup.PublicInterface", "cleanCache", null, null);
        if (OKLog.D) {
            OKLog.d(TAG, "settingcleanCache");
        }
    }

    public static FrameLayout getPreloadingWidget(Context context) {
        Object callBundleMethod = callBundleMethod("com.jd.lib.threedproduct.CacheManager", "getPreloadingWidget", new Class[]{Context.class}, new Object[]{context});
        if (callBundleMethod instanceof FrameLayout) {
            return (FrameLayout) callBundleMethod;
        }
        return null;
    }

    private static boolean is3DProductSwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(48));
    }

    public static void preload(Context context, String str, String str2) {
        DeepLinkArVrPublicInterfaceHelper.callBundleMethod("com.jd.lib.threedproduct.CacheManager", HttpDnsConfig.PREDOWNLOAD_PARAMS, new Class[]{Context.class, String.class, String.class}, new Object[]{context, str, str2});
    }

    public static void preloading(FrameLayout frameLayout, ViewGroup viewGroup, String str, boolean z, PreloadingListener preloadingListener) {
        preloading(frameLayout, viewGroup, str, "", z, preloadingListener);
    }

    public static void preloading_action(FrameLayout frameLayout, int i2) {
        callBundleMethod("com.jd.lib.threedproduct.CacheManager", "preloading_action", new Class[]{FrameLayout.class, Integer.class}, new Object[]{frameLayout, Integer.valueOf(i2)});
    }

    public static int preloading_get_status(FrameLayout frameLayout) {
        Object callBundleMethod = callBundleMethod("com.jd.lib.threedproduct.CacheManager", "preloading_get_status", new Class[]{FrameLayout.class}, new Object[]{frameLayout});
        if (callBundleMethod instanceof Integer) {
            return ((Integer) callBundleMethod).intValue();
        }
        return -1;
    }

    public static void start3DProductDetail(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        start3DProductDetail(context, str, str2, str3, str4, str5, str6, str7, str8, "0", false, false, false, false);
    }

    public static void preloading(FrameLayout frameLayout, ViewGroup viewGroup, String str, String str2, boolean z, PreloadingListener preloadingListener) {
        callBundleMethod("com.jd.lib.threedproduct.CacheManager", "preloading", new Class[]{FrameLayout.class, ViewGroup.class, String.class, String.class, Boolean.class, PreloadingListener.class}, new Object[]{frameLayout, viewGroup, str, str2, Boolean.valueOf(z), preloadingListener});
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void start3DProductDetail(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, boolean z2, boolean z3, boolean z4) {
        if (!is3DProductSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-threedproduct switch is close ");
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "skuId=" + str2 + ",cid" + str3 + ",vendorId=" + str9 + ",isCanUseDong=" + z + ", isCanUseJing=" + z2 + ",isGlobal=" + z3);
        }
        try {
            bundle.putLong("skuId", Long.parseLong(str2));
            String[] split = str3.split(";");
            if (split != null && split.length == 3) {
                bundle.putInt("cid", Integer.parseInt(split[2]));
                if (!TextUtils.isEmpty(str9)) {
                    bundle.putInt("vendorId", Integer.parseInt(str9));
                }
                int i2 = 1;
                bundle.putInt(EXTRA_ISCANUSEDONG, z ? 1 : 0);
                if (!z2) {
                    i2 = 0;
                }
                bundle.putInt(EXTRA_ISCANUSEJING, i2);
                bundle.putBoolean(EXTRA_IS_GLOBAL, z3);
                bundle.putString("from", str);
                if (!TextUtils.isEmpty(str4)) {
                    bundle.putString(EXTRA_MODELURL, str4);
                }
                if (!TextUtils.isEmpty(str5)) {
                    bundle.putString(EXTRA_PASSWORD, str5);
                }
                if (!TextUtils.isEmpty(str6)) {
                    bundle.putString(EXTRA_AR, str6);
                }
                if (!TextUtils.isEmpty(str7)) {
                    bundle.putString("title", str7);
                }
                bundle.putString("price", !TextUtils.isEmpty(str8) ? "" : str8);
                bundle.putBoolean(EXTRA_CAN_ADD_CART, z4);
                start3DProductDetail(context, bundle);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-threedproduct cid is " + str3);
            }
            bundle.putString("from", str);
            if (!TextUtils.isEmpty(str4)) {
            }
            if (!TextUtils.isEmpty(str5)) {
            }
            if (!TextUtils.isEmpty(str6)) {
            }
            if (!TextUtils.isEmpty(str7)) {
            }
            bundle.putString("price", !TextUtils.isEmpty(str8) ? "" : str8);
            bundle.putBoolean(EXTRA_CAN_ADD_CART, z4);
            start3DProductDetail(context, bundle);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void start3DProductDetail(Context context, Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "start bundle-threedproduct");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "threedproductactivity", bundle);
    }
}
