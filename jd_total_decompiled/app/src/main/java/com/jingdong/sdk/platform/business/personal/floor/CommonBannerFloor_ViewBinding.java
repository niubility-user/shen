package com.jingdong.sdk.platform.business.personal.floor;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.widget.IconPageIndicator;
import com.jingdong.sdk.platform.widget.RoundAngleFrameLayout;

/* loaded from: classes10.dex */
public class CommonBannerFloor_ViewBinding implements Unbinder {
    private CommonBannerFloor target;

    @UiThread
    public CommonBannerFloor_ViewBinding(CommonBannerFloor commonBannerFloor, View view) {
        this.target = commonBannerFloor;
        commonBannerFloor.floorViewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.floor_viewpager, "field 'floorViewpager'", ViewPager.class);
        commonBannerFloor.floorIndicator = (IconPageIndicator) Utils.findRequiredViewAsType(view, R.id.floor_indicator, "field 'floorIndicator'", IconPageIndicator.class);
        commonBannerFloor.roundAngleFrameLayout = (RoundAngleFrameLayout) Utils.findRequiredViewAsType(view, R.id.roundcontainer, "field 'roundAngleFrameLayout'", RoundAngleFrameLayout.class);
        commonBannerFloor.rootLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.root_layout, "field 'rootLayout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommonBannerFloor commonBannerFloor = this.target;
        if (commonBannerFloor != null) {
            this.target = null;
            commonBannerFloor.floorViewpager = null;
            commonBannerFloor.floorIndicator = null;
            commonBannerFloor.roundAngleFrameLayout = null;
            commonBannerFloor.rootLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
