package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.ReactRootView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.CommonUtilEx;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.JDReactPackage;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class JDReactNativePaySucessActivity extends JDReactNativeBaseCommonActivity implements View.OnClickListener {
    private static final String TAG = "JDReactNativePaySucessActivity";
    SimpleDraweeView llBtnBack;
    FrameLayout reactRootViewHolder;
    TextView rightText;
    private String fromActivity = "";
    private String skuId = "";

    private void clickCompleteAction() {
        String data = JDReactNativeSharedDataModule.getData("from");
        this.fromActivity = data;
        if (data != null) {
            if ("PhoneCharge".equals(data)) {
                JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "1", "ChargeOrder_PaySuccess");
                finish();
            } else if ("FlowCharge".equals(this.fromActivity)) {
                JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "2", "ChargeOrder_PaySuccess");
                finish();
            } else if (!"GameCharge".equals(this.fromActivity) && !"gameProp".equals(this.fromActivity)) {
                if ("QbCharge".equals(this.fromActivity)) {
                    JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "3", "ChargeOrder_PaySuccess");
                    finish();
                } else if ("fuelCard".equals(this.fromActivity)) {
                    JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "4", "ChargeOrder_PaySuccess");
                    finish();
                } else if ("oneTreasure".equals(this.fromActivity)) {
                    JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "0", "ChargeOrder_PaySuccess");
                    CommonUtilEx.getInstance().gotoMainFrameClearAllTask(this);
                    try {
                        Thread.sleep(200L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    ReactActivityUtils.startJDReactOneTreasureActivity(this, new Intent());
                } else if ("fromSearch".equals(this.fromActivity)) {
                    if (TextUtils.isEmpty(this.skuId)) {
                        return;
                    }
                    try {
                        startProductDetailActivity(getThisActivity(), Long.valueOf(Long.parseLong(this.skuId)), "", null);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    finish();
                } else if ("OrderListOrDetail".equals(this.fromActivity)) {
                    s.d(this);
                } else {
                    finish();
                }
            } else {
                JDMtaUtils.onClickWithPageId(this, "MPaySuccess_Finish", getClass().getName(), "", "3", "ChargeOrder_PaySuccess");
                finish();
            }
        }
    }

    public static void startProductDetailActivity(Activity activity, Long l2, String str, SourceEntity sourceEntity) {
        if (activity == null || l2 == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("id", l2.longValue());
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("title", str);
        }
        startProductDetailActivity(activity, bundle, sourceEntity);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public int getLayoutResource() {
        return R.layout.reactnative_layout_paysuccess;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactModuleEntity getReactEntity() {
        return new JDReactModuleEntity("PaySuccess", JDReactConstant.AVAILABILITY_PAYSUCCESS, null);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public void initView(ReactRootView reactRootView, String str, boolean z, String str2, String str3) {
        setContentView(R.layout.reactnative_layout_paysuccess);
        this.llBtnBack = (SimpleDraweeView) findViewById(R.id.fe);
        this.reactRootViewHolder = (FrameLayout) findViewById(R.id.reactRootViewPaySuccess);
        this.rightText = (TextView) findViewById(R.id.activity_paysuccess_title_right_text);
        this.fromActivity = JDReactNativeSharedDataModule.getData("from");
        this.skuId = JDReactNativeSharedDataModule.getData("skuId");
        String str4 = this.fromActivity;
        if (str4 != null) {
            if ("PhoneCharge".equals(str4)) {
                this.rightText.setText("\u5b8c\u6210");
            } else if ("GameCharge".equals(this.fromActivity)) {
                this.rightText.setText("\u5b8c\u6210");
            } else if ("QbCharge".equals(this.fromActivity)) {
                this.rightText.setText("\u5b8c\u6210");
            } else {
                this.rightText.setText("\u5b8c\u6210");
            }
        }
        Log.d(TAG, "mReactInstanceManager build complete");
        this.llBtnBack.setVisibility(8);
        this.llBtnBack.setOnClickListener(this);
        this.rightText.setOnClickListener(this);
        this.reactRootViewHolder.addView(reactRootView, -1, -1);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        clickCompleteAction();
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.activity_paysuccess_title_right_text) {
            clickCompleteAction();
        } else if (id != R.id.fe) {
        } else {
            finish();
        }
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            clickCompleteAction();
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        String str;
        setUseBasePV(false);
        super.onResume();
        String str2 = this.fromActivity;
        if (str2 != null) {
            if ("PhoneCharge".equals(str2)) {
                str = "1";
            } else if ("FlowCharge".equals(this.fromActivity)) {
                str = "2";
            } else if ("GameCharge".equals(this.fromActivity) || "QbCharge".equals(this.fromActivity) || "gameProp".equals(this.fromActivity)) {
                str = "3";
            } else if ("fuelCard".equals(this.fromActivity)) {
                str = "4";
            } else if ("oneTreasure".equals(this.fromActivity)) {
                str = "0";
            }
            JDMtaUtils.sendPagePv(this, this, str, "ChargeOrder_PaySuccess", this.shop_id);
        }
        str = "";
        JDMtaUtils.sendPagePv(this, this, str, "ChargeOrder_PaySuccess", this.shop_id);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public boolean showLoading() {
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity
    public JDReactPackage getReactPackage() {
        return new JDReactExtendPackage();
    }

    public static void startProductDetailActivity(Activity activity, Bundle bundle, SourceEntity sourceEntity) {
        if (activity == null || bundle == null) {
            return;
        }
        bundle.putSerializable("source", sourceEntity);
        DeeplinkProductDetailHelper.startProductDetailWithFlag(activity, bundle, 67108864);
    }
}
