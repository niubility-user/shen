package com.jingdong.sdk.platform.business.personal.floor;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.widget.IconPageIndicator;
import com.jingdong.sdk.platform.widget.RoundAngleFrameLayout;

/* loaded from: classes10.dex */
public class CommonMultiIconFloor_ViewBinding implements Unbinder {
    private CommonMultiIconFloor target;

    @UiThread
    public CommonMultiIconFloor_ViewBinding(CommonMultiIconFloor commonMultiIconFloor, View view) {
        this.target = commonMultiIconFloor;
        commonMultiIconFloor.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'viewPager'", ViewPager.class);
        commonMultiIconFloor.indicator = (IconPageIndicator) Utils.findRequiredViewAsType(view, R.id.indicator, "field 'indicator'", IconPageIndicator.class);
        commonMultiIconFloor.floorBackground = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.floor_background, "field 'floorBackground'", SimpleDraweeView.class);
        commonMultiIconFloor.floorContainer = (RoundAngleFrameLayout) Utils.findRequiredViewAsType(view, R.id.floor_container, "field 'floorContainer'", RoundAngleFrameLayout.class);
        commonMultiIconFloor.rootLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.root_layout, "field 'rootLayout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommonMultiIconFloor commonMultiIconFloor = this.target;
        if (commonMultiIconFloor != null) {
            this.target = null;
            commonMultiIconFloor.viewPager = null;
            commonMultiIconFloor.indicator = null;
            commonMultiIconFloor.floorBackground = null;
            commonMultiIconFloor.floorContainer = null;
            commonMultiIconFloor.rootLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
