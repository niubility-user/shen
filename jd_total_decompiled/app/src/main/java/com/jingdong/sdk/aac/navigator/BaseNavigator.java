package com.jingdong.sdk.aac.navigator;

import androidx.fragment.app.FragmentActivity;
import com.jingdong.sdk.aac.base.ICommon;
import com.jingdong.sdk.aac.base.ManagerRegistry;

/* loaded from: classes7.dex */
public abstract class BaseNavigator implements ICommon {
    private ManagerRegistry managerRegistry;

    public BaseNavigator(String str) {
        this.managerRegistry = new ManagerRegistry(str);
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public String getManagerKey() {
        return this.managerRegistry.getManagerKey();
    }

    @Override // com.jingdong.sdk.aac.base.ICommon
    public <T extends FragmentActivity> T getThisActivity() {
        return (T) this.managerRegistry.getThisActivity();
    }
}
