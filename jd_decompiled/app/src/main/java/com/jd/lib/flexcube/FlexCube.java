package com.jd.lib.flexcube;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService;
import com.jd.lib.flexcube.layout.FlexLinearFloor;
import com.jd.lib.flexcube.layout.FlexLinearNineFloor;
import com.jd.lib.flexcube.layout.floor.banner.focus.FlexFocusBannerFloor;
import com.jd.lib.flexcube.layout.floor.banner.full.FlexFullBannerFloor;
import com.jd.lib.flexcube.layout.floor.banner.swipe.FlexSwipeCardFloor;
import com.jd.lib.flexcube.layout.floor.banner.vertical.FlexVerticalBannerFloor;
import com.jd.lib.flexcube.layout.floor.scroll.FlexScrollFloor;
import com.jd.lib.flexcube.owidgets.view.video.BableFlexCubeVideoImpl;

/* loaded from: classes14.dex */
public class FlexCube {
    public static final String ASTERISK = "_FLEX_";
    public static final String BANNER = "banner";
    public static final String BANNER_FOCUS = "bannerFocus";
    public static final String BANNER_STACKED = "bannerStacked";
    public static final String BANNER_VERTICAL = "bannerVertical";
    public static final String FLATVIEW = "flatview";
    public static final String GRIDVIEW = "gridView";
    public static final String SCROLL = "scroll";
    private static FlexCube mFlexCube;
    private FlexCubeVideoService videoService;

    public static String getFloorId(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(ASTERISK)) {
            return "";
        }
        String[] split = str.split(ASTERISK);
        return split.length > 0 ? split[0] : "";
    }

    public static synchronized FlexCube getInstance() {
        FlexCube flexCube;
        synchronized (FlexCube.class) {
            if (mFlexCube == null) {
                mFlexCube = new FlexCube();
            }
            flexCube = mFlexCube;
        }
        return flexCube;
    }

    public static String getSubInfo(String str) {
        return getSubInfo(str, 1);
    }

    public static View getView(Context context, String str) {
        String floorId = getFloorId(str);
        floorId.hashCode();
        char c2 = '\uffff';
        switch (floorId.hashCode()) {
            case -1930067700:
                if (floorId.equals(BANNER_FOCUS)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1396342996:
                if (floorId.equals("banner")) {
                    c2 = 1;
                    break;
                }
                break;
            case -907680051:
                if (floorId.equals("scroll")) {
                    c2 = 2;
                    break;
                }
                break;
            case -575783845:
                if (floorId.equals(BANNER_STACKED)) {
                    c2 = 3;
                    break;
                }
                break;
            case 318121739:
                if (floorId.equals(GRIDVIEW)) {
                    c2 = 4;
                    break;
                }
                break;
            case 338714306:
                if (floorId.equals(BANNER_VERTICAL)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1626920350:
                if (floorId.equals(FLATVIEW)) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new FlexFocusBannerFloor(context);
            case 1:
                return new FlexFullBannerFloor(context);
            case 2:
                return new FlexScrollFloor(context);
            case 3:
                return new FlexSwipeCardFloor(context);
            case 4:
                return new FlexLinearNineFloor(context);
            case 5:
                return new FlexVerticalBannerFloor(context);
            case 6:
                return new FlexLinearFloor(context);
            default:
                return null;
        }
    }

    public synchronized FlexCubeVideoService getVideoService() {
        if (this.videoService == null) {
            this.videoService = new BableFlexCubeVideoImpl();
        }
        return this.videoService;
    }

    public void setVideoService(FlexCubeVideoService flexCubeVideoService) {
        this.videoService = flexCubeVideoService;
    }

    public static String getSubInfo(String str, int i2) {
        if (TextUtils.isEmpty(str) || !str.contains(ASTERISK)) {
            return "";
        }
        String[] split = str.split(ASTERISK);
        return split.length > i2 ? split[i2] : "";
    }
}
