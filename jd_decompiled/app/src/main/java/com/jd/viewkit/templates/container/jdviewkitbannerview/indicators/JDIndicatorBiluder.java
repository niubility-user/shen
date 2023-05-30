package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorView build(android.content.Context r5) {
        /*
            r4 = this;
            int r0 = r4.dotType
            if (r0 == 0) goto L1b
            r1 = 1
            if (r0 == r1) goto L9
            r5 = 0
            goto L21
        L9:
            int r0 = r4.dotSubType
            if (r0 != r1) goto L15
            com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorNewNumberView r0 = new com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorNewNumberView
            com.jd.viewkit.helper.JDViewKitChannelModel r1 = r4.channelModel
            r0.<init>(r5, r1)
            goto L20
        L15:
            com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorNumberView r0 = new com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorNumberView
            r0.<init>(r5)
            goto L20
        L1b:
            com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorRoundView r0 = new com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorRoundView
            r0.<init>(r5)
        L20:
            r5 = r0
        L21:
            if (r5 == 0) goto L2d
            r0 = 0
            int r1 = r4.totalNum
            java.lang.String r2 = r4.normalColor
            java.lang.String r3 = r4.activeColor
            r5.initIndicator(r0, r1, r2, r3)
        L2d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDIndicatorBiluder.build(android.content.Context):com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorView");
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
