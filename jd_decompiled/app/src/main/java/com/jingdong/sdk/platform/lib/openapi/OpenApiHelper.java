package com.jingdong.sdk.platform.lib.openapi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil;
import com.jingdong.sdk.platform.lib.openapi.app.IApplicationContext;
import com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig;
import com.jingdong.sdk.platform.lib.openapi.db.ICartTable;
import com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink;
import com.jingdong.sdk.platform.lib.openapi.favourites.IFavouritesHelper;
import com.jingdong.sdk.platform.lib.openapi.host.IHostConfig;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginApi;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginUserHelper;
import com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils;
import com.jingdong.sdk.platform.lib.openapi.share.IShareUtil;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsActivityCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsFragmentCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.IAttrEnumPath;
import com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon;
import com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView;
import com.jingdong.sdk.platform.lib.openapi.ui.IXView;
import com.jingdong.sdk.platform.lib.openapi.utils.IActivityUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils;
import com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo;
import com.jingdong.sdk.platform.lib.openapi.utils.IDBHelperUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo;
import com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IFunctionId;
import com.jingdong.sdk.platform.lib.openapi.utils.IJumpUtil;

/* loaded from: classes10.dex */
public class OpenApiHelper {
    public static AbsActivityCompact getActivityCompact(FragmentActivity fragmentActivity) {
        return OpenApiConfig.getOpenApiEngine().getPlatformLifecyleCompact().getActivityCompact(fragmentActivity);
    }

    public static IDeeplink getDeepLinkConfig() {
        return OpenApiConfig.getOpenApiEngine().getiDeeplink();
    }

    public static AbsFragmentCompact getFragmentCompact(Fragment fragment) {
        return OpenApiConfig.getOpenApiEngine().getPlatformLifecyleCompact().getFragmentCompact(fragment);
    }

    public static IActivityUtil getIActivityUtil() {
        return OpenApiConfig.getOpenApiEngine().getiActivityUtil();
    }

    public static IAddressUtil getIAddressUtil() {
        return OpenApiConfig.getOpenApiEngine().getiAddressUtil();
    }

    public static IAdvertUtils getIAdvertUtils() {
        return OpenApiConfig.getOpenApiEngine().getiAdvertUtils();
    }

    public static IApplicationContext getIApplicationContext() {
        return OpenApiConfig.getOpenApiEngine().getiApplicationContext();
    }

    public static IAuraBundleConfig getIAuraBundleConfig() {
        return OpenApiConfig.getOpenApiEngine().getiAuraBundleConfig();
    }

    public static ICartIcon getICartIcon() {
        return OpenApiConfig.getOpenApiEngine().getiCartIcon();
    }

    public static ICartTable getICartTable() {
        return OpenApiConfig.getOpenApiEngine().getiCartTable();
    }

    public static IClientInfo getIClientInfo() {
        return OpenApiConfig.getOpenApiEngine().getiClientInfo();
    }

    public static IDeviceInfo getIDeviceInfo() {
        return OpenApiConfig.getOpenApiEngine().getiDeviceInfo();
    }

    public static IFavouritesHelper getIFavouritesHelper() {
        return OpenApiConfig.getOpenApiEngine().getiFavouritesHelper();
    }

    public static IFontsUtil getIFontsUtil() {
        return OpenApiConfig.getOpenApiEngine().getiFontsUtil();
    }

    public static IHostConfig getIHostConfig() {
        return OpenApiConfig.getOpenApiEngine().getiHostConfig();
    }

    public static IJDMtaUtils getIJDMtaUtils() {
        return OpenApiConfig.getOpenApiEngine().getIjdMtaUtils();
    }

    public static ILoginApi getILoginApi() {
        return OpenApiConfig.getOpenApiEngine().getiLoginApi();
    }

    public static ILoginUserBase getILoginUserBase() {
        return OpenApiConfig.getOpenApiEngine().getiLoginUserBase();
    }

    public static IXView getIXView() {
        return OpenApiConfig.getOpenApiEngine().getIxView();
    }

    public static IDBHelperUtil getIdbHelperUtil() {
        return OpenApiConfig.getOpenApiEngine().getIdbHelperUtil();
    }

    public static ILoginUserHelper getLoginUserHelper() {
        return OpenApiConfig.getOpenApiEngine().getiLoginUserHelper();
    }

    public static IAttrEnumPath getiAttrEnumPath() {
        return OpenApiConfig.getOpenApiEngine().getiAttrEnumPath();
    }

    public static IFunctionId getiFunctionId() {
        return OpenApiConfig.getOpenApiEngine().getiFunctionId();
    }

    public static IJumpUtil getiJumpUtil() {
        return OpenApiConfig.getOpenApiEngine().getiJumpUtil();
    }

    public static ILoadingView getiLoadingView() {
        return OpenApiConfig.getOpenApiEngine().getiLoadingView();
    }

    public static IShareUtil getiShareUtil() {
        return OpenApiConfig.getOpenApiEngine().getiShareUtil();
    }
}
