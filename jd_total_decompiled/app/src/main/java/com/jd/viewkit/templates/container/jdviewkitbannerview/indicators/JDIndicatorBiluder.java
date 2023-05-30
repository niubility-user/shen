package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

import android.content.Context;
import com.jd.viewkit.helper.JDViewKitChannelModel;

/* loaded from: classes18.dex */
public class JDIndicatorBiluder {
    public static final int DOT_SUB_TYPE_NEW_NUMBER = 1;
    private static final int DOT_TYPE_OF_NUMBER = 1;
    private static final int DOT_TYPE_OF_OTHER = -1;
    private static final int DOT_TYPE_OF_ROUND = 0;
    private String activeColor;
    private JDViewKitChannelModel channelModel;
    private int dotSubType;
    private int dotType;
    private String normalColor;
    private int totalNum;

    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDBannerIndicatorView build(Context context) {
        JDBannerIndicatorView jDBannerIndicatorRoundView;
        JDBannerIndicatorView jDBannerIndicatorView;
        int i2 = this.dotType;
        if (i2 == 0) {
            jDBannerIndicatorRoundView = new JDBannerIndicatorRoundView(context);
        } else if (i2 != 1) {
            jDBannerIndicatorView = null;
            if (jDBannerIndicatorView != null) {
                jDBannerIndicatorView.initIndicator(0, this.totalNum, this.normalColor, this.activeColor);
            }
            return jDBannerIndicatorView;
        } else {
            jDBannerIndicatorRoundView = this.dotSubType == 1 ? new JDBannerIndicatorNewNumberView(context, this.channelModel) : new JDBannerIndicatorNumberView(context);
        }
        jDBannerIndicatorView = jDBannerIndicatorRoundView;
        if (jDBannerIndicatorView != null) {
        }
        return jDBannerIndicatorView;
    }

    public JDIndicatorBiluder setActiveColor(String str) {
        this.activeColor = str;
        return this;
    }

    public JDIndicatorBiluder setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
        this.channelModel = jDViewKitChannelModel;
        return this;
    }

    public JDIndicatorBiluder setDotSubType(int i2) {
        this.dotSubType = i2;
        return this;
    }

    public JDIndicatorBiluder setDotType(int i2) {
        this.dotType = i2;
        return this;
    }

    public JDIndicatorBiluder setNormalColor(String str) {
        this.normalColor = str;
        return this;
    }

    public JDIndicatorBiluder setTotalNum(int i2) {
        this.totalNum = i2;
        return this;
    }
}
