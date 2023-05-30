package com.jingdong.common.utils.caas;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.huawei.caas.caasservice.HwCaasHandler;
import com.huawei.caas.caasservice.HwCaasServiceCallBack;
import com.huawei.caas.caasservice.HwCaasServiceManager;
import com.huawei.caas.caasservice.HwCaasUtils;
import com.huawei.caas.caasservice.HwCallStateCallBack;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class CaasKitHelper {
    private static final int LOCATION_X = 60;
    private static final int LOCATION_Y = 50;
    private static final String TAG = "CaasKitHelper";
    private static final int VIEWHEIGHT = 248;
    private static final int VIEWWIDTH = 256;
    private static CaasKitHelper sCaasKitHelper;
    private HwCaasHandler mHwCaasHandler;
    private HwCaasServiceManager mHwCaasServiceManager;
    private boolean mIsCaasKitInit = false;
    private boolean mIsCaasKitInitSuccess = false;
    private boolean mIsHasCaaSContacts = false;
    private CaasInitListener caasInitListener = null;
    private HwCaasUtils.CallState currentState = HwCaasUtils.CallState.INVALID;
    private Set<String> needPauseActivityNames = new HashSet();
    private boolean isPausing = false;
    private int version = 100;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private HwCallStateCallBack mCallStateCallBack = new HwCallStateCallBack() { // from class: com.jingdong.common.utils.caas.CaasKitHelper.1
        @Override // com.huawei.caas.caasservice.HwCallStateCallBack
        public void notifyCallState(HwCaasUtils.CallState callState) {
            if (OKLog.D) {
                OKLog.d(CaasKitHelper.TAG, "notifyCallState currentState" + CaasKitHelper.this.currentState);
            }
            CaasKitHelper.this.currentState = callState;
        }
    };
    private HwCaasServiceCallBack mCallBack = new HwCaasServiceCallBack() { // from class: com.jingdong.common.utils.caas.CaasKitHelper.2
        @Override // com.huawei.caas.caasservice.HwCaasServiceCallBack
        public void initFail(int i2) {
            CaasKitHelper.this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.utils.caas.CaasKitHelper.2.2
                @Override // java.lang.Runnable
                public void run() {
                    if (CaasKitHelper.this.caasInitListener != null) {
                        CaasKitHelper.this.caasInitListener.onInitResult(false);
                    }
                }
            });
            CaasKitHelper.this.mIsCaasKitInitSuccess = false;
            if (OKLog.D) {
                OKLog.d(CaasKitHelper.TAG, "initFail  mIsCaasKitInitSuccess:false  retCode\uff1a " + i2);
            }
        }

        @Override // com.huawei.caas.caasservice.HwCaasServiceCallBack
        public void initSuccess(HwCaasHandler hwCaasHandler) {
            CaasKitHelper.this.mHwCaasHandler = hwCaasHandler;
            if (CaasKitHelper.this.mHwCaasHandler != null) {
                CaasKitHelper.this.mHwCaasHandler.setCallerAppName(StringUtil.app_name);
                CaasKitHelper.this.mHwCaasHandler.setContactViewStyle(HwCaasUtils.ContactsViewStyle.FULL_SCREEN);
                CaasKitHelper.this.mHwCaasHandler.setCallStateCallBack(CaasKitHelper.this.mCallStateCallBack);
                CaasKitHelper caasKitHelper = CaasKitHelper.this;
                caasKitHelper.mIsHasCaaSContacts = caasKitHelper.mHwCaasHandler.hasCaaSContacts(HwCaasUtils.ContactsType.SCREEN_SHARING_CONTACTS);
                CaasKitHelper caasKitHelper2 = CaasKitHelper.this;
                caasKitHelper2.mIsCaasKitInitSuccess = caasKitHelper2.mIsHasCaaSContacts;
            }
            CaasKitHelper.this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.utils.caas.CaasKitHelper.2.1
                @Override // java.lang.Runnable
                public void run() {
                    if (CaasKitHelper.this.caasInitListener != null) {
                        CaasKitHelper.this.caasInitListener.onInitResult(CaasKitHelper.this.mIsCaasKitInitSuccess);
                    }
                }
            });
            if (OKLog.D) {
                OKLog.d(CaasKitHelper.TAG, "initSuccess \u521d\u59cb\u5316\u6210\u529f\uff0c\u4f46\u9700\u8981\u770b\u8054\u7cfb\u4eba mIsHasCaaSContacts: " + CaasKitHelper.this.mIsHasCaaSContacts + "  mIsCaasKitInitSuccess:" + CaasKitHelper.this.mIsCaasKitInitSuccess);
            }
        }

        @Override // com.huawei.caas.caasservice.HwCaasServiceCallBack
        public void releaseSuccess() {
            CaasKitHelper.this.mHwCaasHandler = null;
        }
    };
    private Context mContext = JdSdk.getInstance().getApplicationContext();

    private CaasKitHelper() {
    }

    public static synchronized CaasKitHelper getInstance() {
        CaasKitHelper caasKitHelper;
        synchronized (CaasKitHelper.class) {
            if (sCaasKitHelper == null) {
                sCaasKitHelper = new CaasKitHelper();
            }
            caasKitHelper = sCaasKitHelper;
        }
        return caasKitHelper;
    }

    public void caasKitInit(CaasInitListener caasInitListener) {
        if (OKLog.D) {
            OKLog.d(TAG, "caas version:100");
        }
        if (Build.VERSION.SDK_INT > 28 && "1".equals(JDMobileConfig.getInstance().getConfig("hwcaas", "caaskit", "switch"))) {
            this.caasInitListener = caasInitListener;
            if (!this.mIsCaasKitInit) {
                HwCaasServiceManager init = HwCaasServiceManager.init();
                this.mHwCaasServiceManager = init;
                init.initHandler(this.mContext, 2, this.mCallBack);
                if (OKLog.D) {
                    OKLog.d(TAG, "caasKitInit mIsCaasKitInit = false");
                }
                this.mIsCaasKitInit = true;
                return;
            }
            if (caasInitListener != null) {
                caasInitListener.onInitResult(this.mIsCaasKitInitSuccess);
            }
            if (OKLog.D) {
                OKLog.d(TAG, "caasKitInit mIsCaasKitInit = true,  mIsCaasKitInitSuccess: " + this.mIsCaasKitInitSuccess);
            }
        } else if (OKLog.D) {
            OKLog.d(TAG, "caasKitInit \u4e0d\u6ee1\u8db3\u6761\u4ef6 mIsCaasKitInitSuccess: false");
        }
    }

    public void caasKitRelease() {
        if (this.mIsCaasKitInit) {
            HwCaasServiceManager hwCaasServiceManager = this.mHwCaasServiceManager;
            if (hwCaasServiceManager != null) {
                hwCaasServiceManager.release();
                this.mHwCaasServiceManager = null;
            }
            this.mIsCaasKitInit = false;
            this.mIsCaasKitInitSuccess = false;
        }
    }

    public HwCaasUtils.CallState getCurrentState() {
        return this.currentState;
    }

    public boolean isCaasKitInit() {
        return this.mIsCaasKitInitSuccess;
    }

    public boolean isPausing() {
        return this.isPausing;
    }

    public void pauseShare() {
        HwCaasHandler hwCaasHandler = this.mHwCaasHandler;
        if (hwCaasHandler == null || !hwCaasHandler.sendEventToCaasService(4)) {
            return;
        }
        this.isPausing = true;
    }

    public void pauseShareOnActivityCreated(String str) {
        this.needPauseActivityNames.add(str);
        pauseShare();
    }

    public void pauseShareSpecial() {
        HwCaasHandler hwCaasHandler = this.mHwCaasHandler;
        if (hwCaasHandler != null) {
            hwCaasHandler.sendEventToCaasService(4);
        }
    }

    public void removeCaasInitListener() {
        this.caasInitListener = null;
    }

    public void resumeShare() {
        HwCaasHandler hwCaasHandler = this.mHwCaasHandler;
        if (hwCaasHandler != null && hwCaasHandler.sendEventToCaasService(5) && this.isPausing) {
            this.isPausing = false;
        }
    }

    public void resumeShareOnActivityDestroyed() {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity == null || !this.needPauseActivityNames.contains(currentMyActivity.getClass().getName())) {
            resumeShare();
        }
    }

    public boolean sendHide() {
        HwCaasHandler hwCaasHandler = this.mHwCaasHandler;
        if (hwCaasHandler != null) {
            return hwCaasHandler.sendEventToCaasService(3);
        }
        return false;
    }

    public void sendShow() {
        HwCaasHandler hwCaasHandler = this.mHwCaasHandler;
        if (hwCaasHandler == null || !this.mIsHasCaaSContacts) {
            return;
        }
        hwCaasHandler.sendEventToCaasService(2);
    }
}
