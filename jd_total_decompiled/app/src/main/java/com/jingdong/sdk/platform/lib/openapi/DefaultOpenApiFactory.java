package com.jingdong.sdk.platform.lib.openapi;

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
import com.jingdong.sdk.platform.lib.openapi.ui.IAttrEnumPath;
import com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon;
import com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView;
import com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact;
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
public class DefaultOpenApiFactory {
    private static DefaultOpenApiFactory defaultOpenApiFactory;
    private IActivityUtil iActivityUtil;
    private IAddressUtil iAddressUtil;
    private IAdvertUtils iAdvertUtils;
    private IApplicationContext iApplicationContext;
    private IAttrEnumPath iAttrEnumPath;
    private IAuraBundleConfig iAuraBundleConfig;
    private ICartIcon iCartIcon;
    private ICartTable iCartTable;
    private IClientInfo iClientInfo;
    private IDeeplink iDeeplink;
    private IDeviceInfo iDeviceInfo;
    private IFavouritesHelper iFavouritesHelper;
    private IFontsUtil iFontsUtil;
    private IFunctionId iFunctionId;
    private IHostConfig iHostConfig;
    private IJumpUtil iJumpUtil;
    private ILoginApi iLoginApi;
    private ILoginUserBase iLoginUserBase;
    private ILoginUserHelper iLoginUserHelper;
    private ILoadingView iLottieLoadingView;
    private IShareUtil iShareUtil;
    private IDBHelperUtil idbHelperUtil;
    private IJDMtaUtils ijdMtaUtils;
    private IXView ixView;
    private IPlatformLifecyleCompact platformLifecyleCompact;

    private DefaultOpenApiFactory() {
    }

    public static synchronized DefaultOpenApiFactory getInstance() {
        DefaultOpenApiFactory defaultOpenApiFactory2;
        synchronized (DefaultOpenApiFactory.class) {
            if (defaultOpenApiFactory == null) {
                defaultOpenApiFactory = new DefaultOpenApiFactory();
            }
            defaultOpenApiFactory2 = defaultOpenApiFactory;
        }
        return defaultOpenApiFactory2;
    }

    public IActivityUtil getIActivityUtil() {
        if (this.iActivityUtil == null) {
            this.iActivityUtil = new NullOpenApi();
        }
        return this.iActivityUtil;
    }

    public ILoginUserHelper getILoginUserHelper() {
        if (this.iLoginUserHelper == null) {
            this.iLoginUserHelper = new NullOpenApi();
        }
        return this.iLoginUserHelper;
    }

    public IPlatformLifecyleCompact getIPlatformLifecyleCompact() {
        if (this.platformLifecyleCompact == null) {
            this.platformLifecyleCompact = new NullOpenApi();
        }
        return this.platformLifecyleCompact;
    }

    public IJDMtaUtils getIjdMtaUtils() {
        if (this.ijdMtaUtils == null) {
            this.ijdMtaUtils = new NullOpenApi();
        }
        return this.ijdMtaUtils;
    }

    public IXView getIxView() {
        if (this.ixView == null) {
            this.ixView = new NullOpenApi();
        }
        return this.ixView;
    }

    public IAddressUtil getiAddressUtil() {
        if (this.iAddressUtil == null) {
            this.iAddressUtil = new NullOpenApi();
        }
        return this.iAddressUtil;
    }

    public IAdvertUtils getiAdvertUtils() {
        if (this.iAdvertUtils == null) {
            this.iAdvertUtils = new NullOpenApi();
        }
        return this.iAdvertUtils;
    }

    public IApplicationContext getiApplicationContext() {
        if (this.iApplicationContext == null) {
            this.iApplicationContext = new NullOpenApi();
        }
        return this.iApplicationContext;
    }

    public IAttrEnumPath getiAttrEnumPath() {
        if (this.iAttrEnumPath == null) {
            this.iAttrEnumPath = new NullOpenApi();
        }
        return this.iAttrEnumPath;
    }

    public IAuraBundleConfig getiAuraBundleConfig() {
        if (this.iAuraBundleConfig == null) {
            this.iAuraBundleConfig = new NullOpenApi();
        }
        return this.iAuraBundleConfig;
    }

    public ICartIcon getiCartIcon() {
        if (this.iCartIcon == null) {
            this.iCartIcon = new NullOpenApi();
        }
        return this.iCartIcon;
    }

    public ICartTable getiCartTable() {
        if (this.iCartTable == null) {
            this.iCartTable = new NullOpenApi();
        }
        return this.iCartTable;
    }

    public IClientInfo getiClientInfo() {
        if (this.iClientInfo == null) {
            this.iClientInfo = new NullOpenApi();
        }
        return this.iClientInfo;
    }

    public IDeeplink getiDeeplink() {
        if (this.iDeeplink == null) {
            this.iDeeplink = new NullOpenApi();
        }
        return this.iDeeplink;
    }

    public IDeviceInfo getiDeviceInfo() {
        if (this.iDeviceInfo == null) {
            this.iDeviceInfo = new NullOpenApi();
        }
        return this.iDeviceInfo;
    }

    public IFavouritesHelper getiFavouritesHelper() {
        if (this.iFavouritesHelper == null) {
            this.iFavouritesHelper = new NullOpenApi();
        }
        return this.iFavouritesHelper;
    }

    public IFontsUtil getiFontsUtil() {
        if (this.iFontsUtil == null) {
            this.iFontsUtil = new NullOpenApi();
        }
        return this.iFontsUtil;
    }

    public IFunctionId getiFunctionId() {
        if (this.iFunctionId == null) {
            this.iFunctionId = new NullOpenApi();
        }
        return this.iFunctionId;
    }

    public IHostConfig getiHostConfig() {
        if (this.iHostConfig == null) {
            this.iHostConfig = new NullOpenApi();
        }
        return this.iHostConfig;
    }

    public IJumpUtil getiJumpUtil() {
        if (this.iJumpUtil == null) {
            this.iJumpUtil = new NullOpenApi();
        }
        return this.iJumpUtil;
    }

    public ILoginApi getiLoginApi() {
        if (this.iLoginApi == null) {
            this.iLoginApi = new NullOpenApi();
        }
        return this.iLoginApi;
    }

    public ILoginUserBase getiLoginUserBase() {
        if (this.iLoginUserBase == null) {
            this.iLoginUserBase = new NullOpenApi();
        }
        return this.iLoginUserBase;
    }

    public ILoadingView getiLottieLoadingView() {
        if (this.iLottieLoadingView == null) {
            this.iLottieLoadingView = new NullOpenApi();
        }
        return this.iLottieLoadingView;
    }

    public IShareUtil getiShareUtil() {
        if (this.iShareUtil == null) {
            this.iShareUtil = new NullOpenApi();
        }
        return this.iShareUtil;
    }

    public IDBHelperUtil getidbHelperUtil() {
        if (this.idbHelperUtil == null) {
            this.idbHelperUtil = new NullOpenApi();
        }
        return this.idbHelperUtil;
    }
}
