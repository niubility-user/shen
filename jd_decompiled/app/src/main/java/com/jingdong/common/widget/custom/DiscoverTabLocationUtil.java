package com.jingdong.common.widget.custom;

import android.content.Context;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public enum DiscoverTabLocationUtil {
    INSTANCE;
    
    private int height;
    private int locationY;

    public int getLocationY(Context context) {
        if (context == null) {
            return this.locationY + this.height;
        }
        return (this.locationY + this.height) - UnStatusBarTintUtil.getStatusBarHeight(context);
    }

    public int getLocationYOffSet(Context context, int i2) {
        return getLocationY(context) + DPIUtil.dip2px(i2);
    }

    public void setLocationY(int i2) {
        this.locationY = i2;
    }

    public void setTabHeight(int i2) {
        this.height = i2;
    }
}
