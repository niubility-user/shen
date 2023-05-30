package com.jingdong.sdk.platform.lib.openapi;

import android.content.Context;
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
public class OpenApiConfig {
    private static OpenApiConfig apiConfig = new OpenApiConfig();
    private OpenApiEngine engine;

    /* loaded from: classes10.dex */
    public static class Builder {
        private Context context;
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
        private ILoadingView iLoadingView;
        private ILoginApi iLoginApi;
        private ILoginUserBase iLoginUserBase;
        private ILoginUserHelper iLoginUserHelper;
        private IShareUtil iShareUtil;
        private IDBHelperUtil idbHelperUtil;
        private IJDMtaUtils ijdMtaUtils;
        private IXView ixView;
        private IPlatformLifecyleCompact platformLifecyleCompact;

        private Builder(Context context) {
            this.context = context;
        }

        public static Builder newBuilder(Context context) {
            return new Builder(context);
        }

        public OpenApiEngine build() {
            return new OpenApiEngine(this);
        }

        public Builder setIdbHelperUtil(IDBHelperUtil iDBHelperUtil) {
            this.idbHelperUtil = iDBHelperUtil;
            return this;
        }

        public Builder setIjdMtaUtils(IJDMtaUtils iJDMtaUtils) {
            this.ijdMtaUtils = iJDMtaUtils;
            return this;
        }

        public Builder setIxView(IXView iXView) {
            this.ixView = iXView;
            return this;
        }

        public Builder setPlatformLifecyleCompact(IPlatformLifecyleCompact iPlatformLifecyleCompact) {
            this.platformLifecyleCompact = iPlatformLifecyleCompact;
            return this;
        }

        public Builder setiActivityUtil(IActivityUtil iActivityUtil) {
            this.iActivityUtil = iActivityUtil;
            return this;
        }

        public Builder setiAddressUtil(IAddressUtil iAddressUtil) {
            this.iAddressUtil = iAddressUtil;
            return this;
        }

        public Builder setiAdvertUtils(IAdvertUtils iAdvertUtils) {
            this.iAdvertUtils = iAdvertUtils;
            return this;
        }

        public Builder setiApplicationContext(IApplicationContext iApplicationContext) {
            this.iApplicationContext = iApplicationContext;
            return this;
        }

        public Builder setiAttrEnumPath(IAttrEnumPath iAttrEnumPath) {
            this.iAttrEnumPath = iAttrEnumPath;
            return this;
        }

        public Builder setiAuraBundleConfig(IAuraBundleConfig iAuraBundleConfig) {
            this.iAuraBundleConfig = iAuraBundleConfig;
            return this;
        }

        public Builder setiCartIcon(ICartIcon iCartIcon) {
            this.iCartIcon = iCartIcon;
            return this;
        }

        public Builder setiCartTable(ICartTable iCartTable) {
            this.iCartTable = iCartTable;
            return this;
        }

        public Builder setiClientInfo(IClientInfo iClientInfo) {
            this.iClientInfo = iClientInfo;
            return this;
        }

        public Builder setiDeeplink(IDeeplink iDeeplink) {
            this.iDeeplink = iDeeplink;
            return this;
        }

        public Builder setiDeviceInfo(IDeviceInfo iDeviceInfo) {
            this.iDeviceInfo = iDeviceInfo;
            return this;
        }

        public Builder setiFavouritesHelper(IFavouritesHelper iFavouritesHelper) {
            this.iFavouritesHelper = iFavouritesHelper;
            return this;
        }

        public Builder setiFontsUtil(IFontsUtil iFontsUtil) {
            this.iFontsUtil = iFontsUtil;
            return this;
        }

        public Builder setiFunctionId(IFunctionId iFunctionId) {
            this.iFunctionId = iFunctionId;
            return this;
        }

        public Builder setiHostConfig(IHostConfig iHostConfig) {
            this.iHostConfig = iHostConfig;
            return this;
        }

        public Builder setiJumpUtil(IJumpUtil iJumpUtil) {
            this.iJumpUtil = iJumpUtil;
            return this;
        }

        public Builder setiLoadingView(ILoadingView iLoadingView) {
            this.iLoadingView = iLoadingView;
            return this;
        }

        public Builder setiLoginApi(ILoginApi iLoginApi) {
            this.iLoginApi = iLoginApi;
            return this;
        }

        public Builder setiLoginUserBase(ILoginUserBase iLoginUserBase) {
            this.iLoginUserBase = iLoginUserBase;
            return this;
        }

        public Builder setiLoginUserHelper(ILoginUserHelper iLoginUserHelper) {
            this.iLoginUserHelper = iLoginUserHelper;
            return this;
        }

        public Builder setiShareUtil(IShareUtil iShareUtil) {
            this.iShareUtil = iShareUtil;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public static class OpenApiEngine {
        private Context context;
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
        private ILoadingView iLoadingView;
        private ILoginApi iLoginApi;
        private ILoginUserBase iLoginUserBase;
        private ILoginUserHelper iLoginUserHelper;
        private IShareUtil iShareUtil;
        private IDBHelperUtil idbHelperUtil;
        private IJDMtaUtils ijdMtaUtils;
        private IXView ixView;
        private IPlatformLifecyleCompact platformLifecyleCompact;

        public OpenApiEngine(Builder builder) {
            this.context = builder.context;
            this.iAddressUtil = builder.iAddressUtil;
            this.iLoginApi = builder.iLoginApi;
            this.iLoginUserBase = builder.iLoginUserBase;
            this.iAuraBundleConfig = builder.iAuraBundleConfig;
            this.iCartTable = builder.iCartTable;
            this.iFavouritesHelper = builder.iFavouritesHelper;
            this.iHostConfig = builder.iHostConfig;
            this.ijdMtaUtils = builder.ijdMtaUtils;
            this.iDeeplink = builder.iDeeplink;
            this.iCartIcon = builder.iCartIcon;
            this.iDeviceInfo = builder.iDeviceInfo;
            this.iFontsUtil = builder.iFontsUtil;
            this.iApplicationContext = builder.iApplicationContext;
            this.iAdvertUtils = builder.iAdvertUtils;
            this.ixView = builder.ixView;
            this.platformLifecyleCompact = builder.platformLifecyleCompact;
            this.iJumpUtil = builder.iJumpUtil;
            this.iActivityUtil = builder.iActivityUtil;
            this.iShareUtil = builder.iShareUtil;
            this.iLoadingView = builder.iLoadingView;
            this.iAttrEnumPath = builder.iAttrEnumPath;
            this.iClientInfo = builder.iClientInfo;
            this.iFunctionId = builder.iFunctionId;
        }

        public IDBHelperUtil getIdbHelperUtil() {
            if (this.idbHelperUtil == null) {
                this.idbHelperUtil = DefaultOpenApiFactory.getInstance().getidbHelperUtil();
            }
            return this.idbHelperUtil;
        }

        public IJDMtaUtils getIjdMtaUtils() {
            if (this.ijdMtaUtils == null) {
                this.ijdMtaUtils = DefaultOpenApiFactory.getInstance().getIjdMtaUtils();
            }
            return this.ijdMtaUtils;
        }

        public IXView getIxView() {
            if (this.ixView == null) {
                this.ixView = DefaultOpenApiFactory.getInstance().getIxView();
            }
            return this.ixView;
        }

        public IPlatformLifecyleCompact getPlatformLifecyleCompact() {
            if (this.platformLifecyleCompact == null) {
                this.platformLifecyleCompact = DefaultOpenApiFactory.getInstance().getIPlatformLifecyleCompact();
            }
            return this.platformLifecyleCompact;
        }

        public IActivityUtil getiActivityUtil() {
            if (this.iActivityUtil == null) {
                this.iActivityUtil = DefaultOpenApiFactory.getInstance().getIActivityUtil();
            }
            return this.iActivityUtil;
        }

        public IAddressUtil getiAddressUtil() {
            if (this.iAddressUtil == null) {
                this.iAddressUtil = DefaultOpenApiFactory.getInstance().getiAddressUtil();
            }
            return this.iAddressUtil;
        }

        public IAdvertUtils getiAdvertUtils() {
            if (this.iAdvertUtils == null) {
                this.iAdvertUtils = DefaultOpenApiFactory.getInstance().getiAdvertUtils();
            }
            return this.iAdvertUtils;
        }

        public IApplicationContext getiApplicationContext() {
            if (this.iApplicationContext == null) {
                this.iApplicationContext = DefaultOpenApiFactory.getInstance().getiApplicationContext();
            }
            return this.iApplicationContext;
        }

        public IAttrEnumPath getiAttrEnumPath() {
            if (this.iAttrEnumPath == null) {
                this.iAttrEnumPath = DefaultOpenApiFactory.getInstance().getiAttrEnumPath();
            }
            return this.iAttrEnumPath;
        }

        public IAuraBundleConfig getiAuraBundleConfig() {
            if (this.iAuraBundleConfig == null) {
                this.iAuraBundleConfig = DefaultOpenApiFactory.getInstance().getiAuraBundleConfig();
            }
            return this.iAuraBundleConfig;
        }

        public ICartIcon getiCartIcon() {
            if (this.iCartIcon == null) {
                this.iCartIcon = DefaultOpenApiFactory.getInstance().getiCartIcon();
            }
            return this.iCartIcon;
        }

        public ICartTable getiCartTable() {
            if (this.iCartTable == null) {
                this.iCartTable = DefaultOpenApiFactory.getInstance().getiCartTable();
            }
            return this.iCartTable;
        }

        public IClientInfo getiClientInfo() {
            if (this.iClientInfo == null) {
                this.iClientInfo = DefaultOpenApiFactory.getInstance().getiClientInfo();
            }
            return this.iClientInfo;
        }

        public IDeeplink getiDeeplink() {
            if (this.iDeeplink == null) {
                this.iDeeplink = DefaultOpenApiFactory.getInstance().getiDeeplink();
            }
            return this.iDeeplink;
        }

        public IDeviceInfo getiDeviceInfo() {
            if (this.iDeviceInfo == null) {
                this.iDeviceInfo = DefaultOpenApiFactory.getInstance().getiDeviceInfo();
            }
            return this.iDeviceInfo;
        }

        public IFavouritesHelper getiFavouritesHelper() {
            if (this.iFavouritesHelper == null) {
                this.iFavouritesHelper = DefaultOpenApiFactory.getInstance().getiFavouritesHelper();
            }
            return this.iFavouritesHelper;
        }

        public IFontsUtil getiFontsUtil() {
            if (this.iFontsUtil == null) {
                this.iFontsUtil = DefaultOpenApiFactory.getInstance().getiFontsUtil();
            }
            return this.iFontsUtil;
        }

        public IFunctionId getiFunctionId() {
            if (this.iFunctionId == null) {
                this.iFunctionId = DefaultOpenApiFactory.getInstance().getiFunctionId();
            }
            return this.iFunctionId;
        }

        public IHostConfig getiHostConfig() {
            if (this.iHostConfig == null) {
                this.iHostConfig = DefaultOpenApiFactory.getInstance().getiHostConfig();
            }
            return this.iHostConfig;
        }

        public IJumpUtil getiJumpUtil() {
            if (this.iJumpUtil == null) {
                this.iJumpUtil = DefaultOpenApiFactory.getInstance().getiJumpUtil();
            }
            return this.iJumpUtil;
        }

        public ILoadingView getiLoadingView() {
            if (this.iLoadingView == null) {
                this.iLoadingView = DefaultOpenApiFactory.getInstance().getiLottieLoadingView();
            }
            return this.iLoadingView;
        }

        public ILoginApi getiLoginApi() {
            if (this.iLoginApi == null) {
                this.iLoginApi = DefaultOpenApiFactory.getInstance().getiLoginApi();
            }
            return this.iLoginApi;
        }

        public ILoginUserBase getiLoginUserBase() {
            if (this.iLoginUserBase == null) {
                this.iLoginUserBase = DefaultOpenApiFactory.getInstance().getiLoginUserBase();
            }
            return this.iLoginUserBase;
        }

        public ILoginUserHelper getiLoginUserHelper() {
            if (this.iLoginUserHelper == null) {
                this.iLoginUserHelper = DefaultOpenApiFactory.getInstance().getILoginUserHelper();
            }
            return this.iLoginUserHelper;
        }

        public IShareUtil getiShareUtil() {
            if (this.iShareUtil == null) {
                this.iShareUtil = DefaultOpenApiFactory.getInstance().getiShareUtil();
            }
            return this.iShareUtil;
        }
    }

    private OpenApiConfig() {
    }

    private static synchronized OpenApiConfig getInstance() {
        OpenApiConfig openApiConfig;
        synchronized (OpenApiConfig.class) {
            if (apiConfig == null) {
                apiConfig = new OpenApiConfig();
            }
            openApiConfig = apiConfig;
        }
        return openApiConfig;
    }

    public static OpenApiEngine getOpenApiEngine() {
        if (getInstance().engine == null) {
            getInstance().engine = Builder.newBuilder(null).build();
        }
        return getInstance().engine;
    }

    public static void initOpenApiEngine(OpenApiEngine openApiEngine) {
        getInstance().initEngine(openApiEngine);
    }

    public void initEngine(OpenApiEngine openApiEngine) {
        this.engine = openApiEngine;
    }
}
