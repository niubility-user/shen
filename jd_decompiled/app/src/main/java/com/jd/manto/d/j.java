package com.jd.manto.d;

import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.manto.sdk.api.IDeepDarkManager;

/* loaded from: classes17.dex */
public class j implements IDeepDarkManager {

    /* loaded from: classes17.dex */
    class a implements DeepDarkChangeManager.OnUIModeChangeListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IDeepDarkManager.IDeepDarkLisener f6659g;

        a(j jVar, IDeepDarkManager.IDeepDarkLisener iDeepDarkLisener) {
            this.f6659g = iDeepDarkLisener;
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            this.f6659g.deepDarkModeChanged(i2);
        }
    }

    @Override // com.jingdong.manto.sdk.api.IDeepDarkManager
    public int getCurreentDeepDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode();
    }

    @Override // com.jingdong.manto.sdk.api.IDeepDarkManager
    public void registerDeepDarkListener(IDeepDarkManager.IDeepDarkLisener iDeepDarkLisener) {
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(new a(this, iDeepDarkLisener));
    }
}
