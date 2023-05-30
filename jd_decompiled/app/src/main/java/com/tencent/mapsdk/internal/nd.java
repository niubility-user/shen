package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.IntersectionOverlayInfo;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlayOptions;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class nd extends IntersectionOverlayInfo implements uc {
    public nd(IntersectionOverlayOptions intersectionOverlayOptions) {
        this.mBounds = intersectionOverlayOptions.getBounds();
        if (intersectionOverlayOptions.getData() != null) {
            this.mData = Arrays.copyOf(intersectionOverlayOptions.getData(), intersectionOverlayOptions.getData().length);
        } else {
            this.mData = new byte[0];
        }
        this.mVisibility = intersectionOverlayOptions.isVisibility();
        this.mDistance = intersectionOverlayOptions.getDistance();
        this.mIsDarkMode = intersectionOverlayOptions.isDarkMode();
        this.mRoundedCorner = intersectionOverlayOptions.isRoundedCorner();
    }
}
