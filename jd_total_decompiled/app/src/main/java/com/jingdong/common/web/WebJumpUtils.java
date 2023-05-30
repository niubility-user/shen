package com.jingdong.common.web;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.log.Log;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

/* loaded from: classes6.dex */
public class WebJumpUtils {
    private static final String TAG = "WebJumpUtils";

    public static boolean checkUrlInBlackList(Bundle bundle) {
        Uri parse;
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_HOST_BLACK_LIST, "");
        if (TextUtils.isEmpty(switchStringValue)) {
            return false;
        }
        String[] split = switchStringValue.split(DYConstants.DY_REGEX_COMMA);
        String urlFromBundle = getUrlFromBundle(bundle);
        if (TextUtils.isEmpty(urlFromBundle) || (parse = Uri.parse(urlFromBundle)) == null) {
            return false;
        }
        try {
            return WebHybridUtils.hostList(parse.getHost(), split);
        } catch (Exception e2) {
            Log.d(TAG, e2.getMessage());
            return false;
        }
    }

    public static boolean checkUrlInIllegalList(Bundle bundle) {
        Uri parse;
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_HOST_ILLEGALURL_BLCK_LIST, "");
        if (TextUtils.isEmpty(switchStringValue)) {
            return false;
        }
        String[] split = switchStringValue.split(";");
        String urlFromBundle = getUrlFromBundle(bundle);
        if (TextUtils.isEmpty(urlFromBundle) || (parse = Uri.parse(urlFromBundle)) == null) {
            return false;
        }
        return WebHybridUtils.hostListWithKeyWord(urlFromBundle, parse.getHost(), split);
    }

    public static String getUrlFromBundle(Bundle bundle) {
        String string = bundle.getString("url");
        if (TextUtils.isEmpty(string)) {
            String string2 = bundle.getString("urlAction");
            if (TextUtils.isEmpty(string2)) {
                string2 = RemoteMessageConst.TO;
            }
            SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
            return serializableContainer != null ? Uri.decode(serializableContainer.getMap().get((Object) string2)) : "";
        }
        return string;
    }

    public static String getUrlFromBundleURLDecode(Bundle bundle) {
        String string;
        URLParamMap map;
        if (bundle == null) {
            return "";
        }
        try {
            string = bundle.getString("url");
        } catch (Throwable unused) {
        }
        if (TextUtils.isEmpty(string)) {
            String string2 = bundle.getString("urlAction");
            if (TextUtils.isEmpty(string2)) {
                string2 = RemoteMessageConst.TO;
            }
            SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
            if (serializableContainer != null && (map = serializableContainer.getMap()) != null) {
                String str = map.get((Object) string2);
                if (!TextUtils.isEmpty(str)) {
                    return URLDecoder.decode(str, "UTF-8");
                }
            }
            return "";
        }
        return string;
    }

    public static boolean is2Native(Context context, Bundle bundle) {
        String string;
        if (bundle == null) {
            return false;
        }
        try {
            string = bundle.getString("url");
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
        }
        if (!TextUtils.isEmpty(string)) {
            return is2Native(context, string);
        }
        String string2 = bundle.getString("urlAction");
        if (TextUtils.isEmpty(string2)) {
            string2 = RemoteMessageConst.TO;
        }
        SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
        if (serializableContainer != null) {
            return is2NativeInternal(context, Uri.decode(serializableContainer.getMap().get((Object) string2)), bundle);
        }
        return false;
    }

    private static boolean is2NativeInternal(Context context, String str, Bundle bundle) {
        try {
            Class<?> cls = Class.forName("com.jingdong.common.web.util.M2NativeHelper");
            Class<?> cls2 = Boolean.TYPE;
            return ((Boolean) cls.getMethod("checkM2Native", Context.class, String.class, Bundle.class, cls2, cls2).invoke(null, context, str, bundle, Boolean.FALSE, Boolean.TRUE)).booleanValue();
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, e2.getMessage(), e2);
            return false;
        } catch (IllegalAccessException e3) {
            Log.e(TAG, e3.getMessage(), e3);
            return false;
        } catch (NoSuchMethodException e4) {
            Log.e(TAG, e4.getMessage(), e4);
            return false;
        } catch (InvocationTargetException e5) {
            Log.e(TAG, e5.getMessage(), e5);
            return false;
        }
    }

    public static boolean is2Native(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        return is2NativeInternal(context, str, bundle);
    }
}
