package com.jingdong.common.jdreactFramework;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactAuraHelper {
    private static String TAG = "JDReactAuraHelper";
    private static JDReactAuraHelper instance;
    private CommonInvokeInterface commonInvokeInterface;

    /* loaded from: classes5.dex */
    public interface CommonInvokeInterface {
        Fragment createMFragment();

        String getAvailabilityPaysuccessConstant();

        boolean getModuleAvailability(String str);

        String getModuleBackupUrl();

        String getReactModulePluginPath(String str);

        String getReactModuleUpdateState(String str);

        boolean isReactModuleForceUpate(String str);

        void launchCardPwdBuyMain(Context context, Bundle bundle);

        void launchCardPwdDetail(Context context, Bundle bundle);

        void launchGamePropBuyActivity(Context context, Intent intent);

        void launchJDReactCommonActivity(Context context, Bundle bundle);

        void launchJshopMineActivity(Context context, Intent intent);

        void launchLivingPayActivity(Context context, Bundle bundle);

        void launchLivingPayOrderDetailActivity(Context context, Bundle bundle);

        void launchLivingPayRecordsActivity(Context context, Bundle bundle);

        void launchMovieHomeActivity(Context context, Intent intent);

        void launchNewProductActivity(Context context, Intent intent);

        void launchPaySuccessActivity(Context context, Intent intent);

        void launchRankListActivity(Context context, Bundle bundle);

        void putData(String str, String str2);

        void sendPhoneChargeCoupon(String str, HashMap hashMap);

        void setPackageManger();

        int weUsePreloadData(String str);
    }

    private JDReactAuraHelper() {
    }

    public static JDReactAuraHelper getInstance() {
        if (instance == null) {
            instance = new JDReactAuraHelper();
        }
        return instance;
    }

    public Fragment createMFragment() {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.createMFragment();
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
            return null;
        }
        return null;
    }

    public String getAvailabilityPaysuccessConstant() {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.getAvailabilityPaysuccessConstant();
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
            return "";
        }
        return "";
    }

    public boolean getModuleAvailability(String str) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.getModuleAvailability(str);
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
            return true;
        }
        return true;
    }

    public String getModuleBackupUrl() {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        return commonInvokeInterface != null ? commonInvokeInterface.getModuleBackupUrl() : "";
    }

    public String getReactModulePluginPath(String str) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.getReactModulePluginPath(str);
        }
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
            return null;
        }
        return null;
    }

    public String getReactModuleUpdateState(String str) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        return commonInvokeInterface != null ? commonInvokeInterface.getReactModuleUpdateState(str) : "";
    }

    public boolean isReactModuleForceUpate(String str) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.isReactModuleForceUpate(str);
        }
        return false;
    }

    public void launchCardPwdBuyMain(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchCardPwdBuyMain(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchCardPwdDetail(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchCardPwdDetail(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchGamePropBuyActivity(Context context, Intent intent) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchGamePropBuyActivity(context, intent);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchJDReactCommonActivity(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchJDReactCommonActivity(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchJshopMineActivity(Context context, Intent intent) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchJshopMineActivity(context, intent);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchLivingPayActivity(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchLivingPayActivity(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchLivingPayOrderDetailActivity(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchLivingPayOrderDetailActivity(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchLivingPayRecordsActivity(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchLivingPayRecordsActivity(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchMovieHomeActivity(Context context, Intent intent) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchMovieHomeActivity(context, intent);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchNewProductActivity(Context context, Intent intent) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchNewProductActivity(context, intent);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "launchNewProductActivity error! commonInvokeInterface is null");
        }
    }

    public void launchPaySuccessActivity(Context context, Intent intent) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchPaySuccessActivity(context, intent);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void launchRankListActivity(Context context, Bundle bundle) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.launchRankListActivity(context, bundle);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void putData(String str, String str2) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.putData(str, str2);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public void sendPhoneChargeCoupon(String str, HashMap hashMap) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.sendPhoneChargeCoupon(str, hashMap);
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "sendPhoneChargeCoupon error! commonInvokeInterface is null");
        }
    }

    public void setCommonInvokeInterface(CommonInvokeInterface commonInvokeInterface, Context context, Application application, JDReactHelper.JDReactHelperCallback jDReactHelperCallback) {
        this.commonInvokeInterface = commonInvokeInterface;
        JDReactHelper.newInstance().setApplicationContext(context);
        JDReactHelper.newInstance().setApplication(application);
        JDReactHelper.newInstance().setJDReactHelperCallback(jDReactHelperCallback);
    }

    public void setPackageManger() {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            commonInvokeInterface.setPackageManger();
        } else if (JDReactHelper.newInstance().isDebug()) {
            JLog.e(TAG, "commonInvokeInterface is null!!");
        }
    }

    public int weUsePreloadData(String str) {
        CommonInvokeInterface commonInvokeInterface = this.commonInvokeInterface;
        if (commonInvokeInterface != null) {
            return commonInvokeInterface.weUsePreloadData(str);
        }
        return 1;
    }
}
