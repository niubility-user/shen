package com.jingdong.sdk.platform.floor.isv;

import android.app.Activity;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes10.dex */
public abstract class BaseLoadFloorOption {
    protected IBaseCooperateExt cooperateExt;
    Object mData;
    boolean mHasFloor;

    public abstract boolean buildTemplate(BaseTemplateEntity baseTemplateEntity);

    @Deprecated
    public IBaseView createView(ViewGroup viewGroup, String str) {
        return null;
    }

    public Object getData() {
        return this.mData;
    }

    public abstract HashMap<String, Class<? extends BaseFloor>> getFloorClass();

    @Deprecated
    public List<String> getRegistViews() {
        return null;
    }

    public HashMap<String, Class<? extends IBaseView>> getViewList() {
        return null;
    }

    public void onMainViewScrolled(Activity activity, int i2) {
    }

    public void onScrollStateChanged(int i2) {
    }

    public void onStartBuildTemplate() {
    }
}
