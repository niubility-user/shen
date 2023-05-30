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
import com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsActivityCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsFragmentCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon;
import com.jingdong.sdk.platform.lib.openapi.ui.IXView;
import com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils;
import com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo;
import com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil;

/* loaded from: classes10.dex */
public interface IOpenApi {
    AbsActivityCompact getActivityCompact(FragmentActivity fragmentActivity);

    IDeeplink getDeepLinkConfig();

    AbsFragmentCompact getFragmentCompact(Fragment fragment);

    IAddressUtil getIAddressUtil();

    IAdvertUtils getIAdvertUtils();

    IApplicationContext getIApplicationContext();

    IAuraBundleConfig getIAuraBundleConfig();

    ICartIcon getICartIcon();

    ICartTable getICartTable();

    IDeviceInfo getIDeviceInfo();

    IFavouritesHelper getIFavouritesHelper();

    IFontsUtil getIFontsUtil();

    IHostConfig getIHostConfig();

    IJDMtaUtils getIJDMtaUtils();

    ILoginApi getILoginApi();

    ILoginUserBase getILoginUserBase();

    IXView getIXView();
}
