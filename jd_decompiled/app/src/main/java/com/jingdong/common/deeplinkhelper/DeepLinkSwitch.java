package com.jingdong.common.deeplinkhelper;

import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;

/* loaded from: classes5.dex */
public class DeepLinkSwitch {
    private static final String MAIN_APP = "main";
    public static DeepLinkSwitch mInstance;
    private SwitchListener mSwitchListener;

    /* loaded from: classes5.dex */
    public interface SwitchListener {
        boolean isSwitchOpen(long j2);
    }

    public static DeepLinkSwitch getInstance() {
        if (mInstance == null) {
            mInstance = new DeepLinkSwitch();
        }
        return mInstance;
    }

    public boolean isSwitchOpen(long j2) {
        SwitchListener switchListener = this.mSwitchListener;
        if (switchListener == null) {
            return false;
        }
        return switchListener.isSwitchOpen(j2);
    }

    public void setSwitchListener(SwitchListener switchListener) {
        this.mSwitchListener = switchListener;
        DeepLinkDispatch.registSwitch(new DeepLinkDispatch.b() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkSwitch.1
            @Override // com.jingdong.sdk.deeplink.DeepLinkDispatch.b
            public boolean isSwitchOpen(String str) {
                if ("main".equals(str)) {
                    return true;
                }
                return DeepLinkSwitch.getInstance().isSwitchOpen(str);
            }
        });
    }

    public boolean isSwitchOpen(String str) {
        return isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleName(str));
    }
}
