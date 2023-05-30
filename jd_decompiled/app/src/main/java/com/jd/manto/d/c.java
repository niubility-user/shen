package com.jd.manto.d;

import android.content.Context;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import java.util.ArrayList;

/* loaded from: classes17.dex */
public class c implements ICustomMenuInterface {
    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableAbout() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableAboutShare() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableDebugSwitch() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableFeedBack() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disablePerformanceSwitch() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableShare() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableShortCut() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public boolean disableToggleFavor() {
        return false;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public ArrayList<ICustomMenuInterface.CustomMenuData> getCustomMenus(Context context) {
        return null;
    }

    @Override // com.jingdong.manto.sdk.api.ICustomMenuInterface
    public void onMenuClicked(Context context, ICustomMenuInterface.CustomData customData, int i2, String str) {
    }
}
