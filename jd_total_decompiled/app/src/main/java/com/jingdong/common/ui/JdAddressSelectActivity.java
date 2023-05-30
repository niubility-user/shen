package com.jingdong.common.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.jd.lib.un.address.R;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.address.UnAddressConstants;
import com.jingdong.common.ui.address.UnHomeAddressSelectFragment;
import com.jingdong.common.ui.address.UnNormalAddressSelectFragment;
import com.jingdong.common.ui.address.entity.AddressPageParams;
import com.jingdong.common.ui.homemix.entity.ShopParam;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JdAddressSelectActivity extends FragmentActivity {
    private static final String TAG = JdAddressSelectActivity.class.getSimpleName();
    private FrameLayout frameLayout;
    private UnHomeAddressSelectFragment homeFragment;
    private UnNormalAddressSelectFragment normalFragment;
    private AddressPageParams pageParams;
    private String saveBusiness;
    private String sceneId;
    private String shopId;
    private ShopParam shopParam;
    private String shopType;
    private String sku;
    private String source;
    private String venderId;

    private void initData() {
        AddressPageParams addressPageParams = (AddressPageParams) getIntent().getParcelableExtra(UnAddressConstants.INTENT_PAGE_PARAMS);
        this.pageParams = addressPageParams;
        if (addressPageParams != null) {
            this.saveBusiness = addressPageParams.getSaveBusiness();
            this.source = this.pageParams.getSource();
            this.sceneId = this.pageParams.getSceneId();
            if (TextUtils.isEmpty(this.pageParams.getShopId()) || TextUtils.isEmpty(this.pageParams.getVenderId()) || TextUtils.isEmpty(this.pageParams.getShopType())) {
                return;
            }
            ShopParam shopParam = new ShopParam();
            this.shopParam = shopParam;
            shopParam.shopId = this.pageParams.getShopId();
            this.shopParam.venderId = this.pageParams.getVenderId();
            this.shopParam.shopType = this.pageParams.getShopType();
            this.sku = this.pageParams.getSku();
            return;
        }
        this.sceneId = getIntent().getStringExtra("sceneId");
        ShopParam shopParam2 = (ShopParam) getIntent().getParcelableExtra(UnAddressConstants.INTENT_SHOP_PARAM);
        this.shopParam = shopParam2;
        if (shopParam2 == null) {
            this.venderId = getIntent().getStringExtra("vender_id");
            this.shopId = getIntent().getStringExtra(UnAddressConstants.INTENT_SHOP_ID);
            this.shopType = getIntent().getStringExtra(UnAddressConstants.INTENT_SHOP_TYPE);
            if (!TextUtils.isEmpty(this.venderId) && !TextUtils.isEmpty(this.shopId) && !TextUtils.isEmpty(this.shopType)) {
                ShopParam shopParam3 = new ShopParam();
                this.shopParam = shopParam3;
                shopParam3.shopId = this.shopId;
                shopParam3.venderId = this.venderId;
                shopParam3.shopType = this.shopType;
            }
        }
        this.saveBusiness = getIntent().getStringExtra(UnAddressConstants.INTENT_SAVE_BUSINESS);
        this.source = getIntent().getStringExtra("source");
        this.sku = getIntent().getStringExtra("sku");
    }

    private void initView() {
        AddressPageParams addressPageParams;
        int i2 = R.id.fragment;
        FrameLayout frameLayout = (FrameLayout) findViewById(i2);
        this.frameLayout = frameLayout;
        double appHeight = DPIUtil.getAppHeight(this);
        Double.isNaN(appHeight);
        ((RelativeLayout.LayoutParams) frameLayout.getLayoutParams()).height = (int) (appHeight * 0.85d);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (LoginUserBase.hasLogin() && ((addressPageParams = this.pageParams) == null || !addressPageParams.showLevelAddress)) {
            this.homeFragment = UnHomeAddressSelectFragment.newInstance(this.sku, this.shopParam, this.saveBusiness, this.source, this.sceneId);
            supportFragmentManager.beginTransaction().replace(i2, this.homeFragment).commit();
        } else {
            AddressGlobal addressGlobal = null;
            AddressPageParams addressPageParams2 = this.pageParams;
            if (addressPageParams2 != null && addressPageParams2.getProvinceId() > -1) {
                addressGlobal = new AddressGlobal();
                addressGlobal.setProvinceName(this.pageParams.getProvinceName());
                addressGlobal.setIdProvince(this.pageParams.getProvinceId());
                addressGlobal.setCityName(this.pageParams.getCityName());
                addressGlobal.setIdCity(this.pageParams.getCityId());
                addressGlobal.setAreaName(this.pageParams.getCountyName());
                addressGlobal.setIdArea(this.pageParams.getCountyId());
                addressGlobal.setTownName(this.pageParams.getTownName());
                addressGlobal.setIdTown(this.pageParams.getTownId());
            }
            AddressGlobal addressGlobal2 = addressGlobal;
            String str = this.sku;
            AddressPageParams addressPageParams3 = this.pageParams;
            this.normalFragment = UnNormalAddressSelectFragment.newInstance(str, addressGlobal2, addressPageParams3 == null ? -1 : addressPageParams3.getCanEditLevel(), this.saveBusiness, this.source, this.sceneId);
            supportFragmentManager.beginTransaction().replace(i2, this.normalFragment).commit();
        }
        findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JdAddressSelectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JdAddressSelectActivity.this.routerBack("");
                JdAddressSelectActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (Log.D) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate(bundle);
        setFinishOnTouchOutside(true);
        overridePendingTransition(R.anim.jd_dialog_bottom_enter, R.anim.jd_dialog_bottom_exit);
        setContentView(R.layout.activity_jd_address_select);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        initData();
        if (TextUtils.isEmpty(this.sceneId)) {
            this.sceneId = "locService";
        }
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (Log.D) {
            Log.d(TAG, "onDestroy");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (Log.D) {
            Log.d(TAG, "onPause");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        PermissionHelper.onRequestPermissionsResult(this, i2, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (Log.D) {
            Log.d(TAG, "onRestart");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (Log.D) {
            Log.d(TAG, "onResume");
        }
    }

    public void routerBack(String str) {
        CallBackWithReturnListener callBackWithReturnListener = UnAddressSelectUtils.listener;
        if (callBackWithReturnListener != null) {
            callBackWithReturnListener.onComplete(str);
            UnAddressSelectUtils.listener = null;
        }
    }
}
