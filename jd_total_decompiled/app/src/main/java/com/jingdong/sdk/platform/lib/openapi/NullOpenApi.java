package com.jingdong.sdk.platform.lib.openapi;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.platform.lib.LoginInfo;
import com.jingdong.sdk.platform.lib.entity.product.AwareInfo;
import com.jingdong.sdk.platform.lib.entity.product.PackInfo;
import com.jingdong.sdk.platform.lib.entity.share.ShareItem;
import com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil;
import com.jingdong.sdk.platform.lib.openapi.app.IApplicationContext;
import com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig;
import com.jingdong.sdk.platform.lib.openapi.db.ICartTable;
import com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink;
import com.jingdong.sdk.platform.lib.openapi.favourites.IFavouritesHelper;
import com.jingdong.sdk.platform.lib.openapi.host.IHostConfig;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginApi;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginCallBack;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase;
import com.jingdong.sdk.platform.lib.openapi.login.ILoginUserHelper;
import com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils;
import com.jingdong.sdk.platform.lib.openapi.share.IShareUtil;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsActivityCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.AbsFragmentCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.IAttrEnumPath;
import com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon;
import com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView;
import com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.IXView;
import com.jingdong.sdk.platform.lib.openapi.ui.NullActivityCompact;
import com.jingdong.sdk.platform.lib.openapi.ui.NullFragmentCompact;
import com.jingdong.sdk.platform.lib.openapi.utils.IActivityUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils;
import com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo;
import com.jingdong.sdk.platform.lib.openapi.utils.IDBHelperUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo;
import com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IFunctionId;
import com.jingdong.sdk.platform.lib.openapi.utils.IJumpUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes10.dex */
public class NullOpenApi implements IAddressUtil, ILoginApi, ILoginUserBase, IAuraBundleConfig, ICartTable, IFavouritesHelper, IHostConfig, IJDMtaUtils, IDeeplink, ICartIcon, IDeviceInfo, IFontsUtil, IApplicationContext, IAdvertUtils, IXView, IPlatformLifecyleCompact, IJumpUtil, IDBHelperUtil, IActivityUtil, ILoginUserHelper, IShareUtil, ILoadingView, IAttrEnumPath, IClientInfo, IFunctionId {
    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public void changeTextFont(@NonNull TextView textView) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public void changeTextFont(@NonNull TextView textView, int i2) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IDBHelperUtil
    public void closeDatabase() {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void delAllCart() {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void delAllPacksCart() {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserHelper
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserHelper
    public void executeLoginRunnable(IMyActivity iMyActivity, Runnable runnable, String str, boolean z) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView
    public void freeLottieMemory(View view) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact
    public AbsActivityCompact getActivityCompact(FragmentActivity fragmentActivity) {
        return new NullActivityCompact(fragmentActivity);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public AddressGlobal getAddressGlobal() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo
    public String getAppid() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.app.IApplicationContext
    public Context getApplicationContext() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig
    public String getBundleNameFromBundleId(int i2) {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.aura.IAuraBundleConfig
    public long getBundleVersionCode(String str) {
        return 0L;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IAdvertUtils
    public String getBusinessId() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public HashMap<String, AwareInfo> getCartListForProduct() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo
    public String getClient() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo
    public String getClientVersion() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IActivityUtil
    public FragmentActivity getCurrentMyActivity() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IDBHelperUtil
    public SQLiteDatabase getDatabase() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IPlatformLifecyleCompact
    public AbsFragmentCompact getFragmentCompact(Fragment fragment) {
        return new NullFragmentCompact(fragment);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFunctionId
    public String getFunctionId(String str) {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.host.IHostConfig
    public String getHost(String str) {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IAttrEnumPath
    public int getIndicatorGravity(String str) {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IAttrEnumPath
    public int getIndicatorInterpolation(String str) {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView
    public View getLoadingView() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public LoginInfo getLoginInfo() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public String getLoginUserName() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.ILoadingView
    public View getLottieLoadingView() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiBold() {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiLight() {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiRegular() {
        return 0;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public HashMap<String, PackInfo> getPacksListForPack() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink
    public String getScheme() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IClientInfo
    public String getSecretKey() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public Typeface getTypeFace(@NonNull Context context) {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public Typeface getTypeFace(@NonNull Context context, int i2) {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IDeviceInfo
    public String getUUID() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public boolean hasLogin() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertAllCart(List<AwareInfo> list) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertAllPacksCart(List<PackInfo> list) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertSingletonCart(AwareInfo awareInfo) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.db.ICartTable
    public void insertSingletonPacksCart(PackInfo packInfo) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.deeplink.IDeeplink
    public boolean isSwitchOpen(String str) {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public void onAddressChanged() {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSavePackOrderPage(String str) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSaveProductOrderPage(String str) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void onSaveProductOrderPageWithSkuTag(String str, String str2) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.share.IShareUtil
    public void panel(Activity activity, ShareItem shareItem) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonDataForPromotionListPage(Context context, String str, String str2, String str3, Object obj, String str4, Class cls, String str5, String str6) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendCommonDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.mta.IJDMtaUtils
    public void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginUserBase
    public void setAlreadySyncCart(boolean z) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.favourites.IFavouritesHelper
    public void setCartToFavorite(boolean z) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.IXView
    public void showFirstPurchaseXView(Fragment fragment, String str) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.login.ILoginApi
    public void startLoginActivity(Context context, Bundle bundle, ILoginCallBack iLoginCallBack, String str) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IJumpUtil
    public void startWebActivity(Context context, Bundle bundle, boolean z) {
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.address.IAddressUtil
    public boolean updateAddressGlobal(AddressGlobal addressGlobal) {
        return false;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon
    public void validateCartIcon() {
    }
}
