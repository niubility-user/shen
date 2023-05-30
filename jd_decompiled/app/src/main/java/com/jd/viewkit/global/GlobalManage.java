package com.jd.viewkit.global;

import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.thirdinterface.JDViewKitVideoService;
import com.jd.viewkit.tool.NumberTool;

/* loaded from: classes18.dex */
public class GlobalManage {
    private static final String TAG = "GlobalManage";
    private static volatile GlobalManage singleton;
    private int screenWidth;
    private JDViewKitVideoService videoService;

    private GlobalManage() {
    }

    public static GlobalManage getInstance() {
        if (singleton == null) {
            synchronized (GlobalManage.class) {
                if (singleton == null) {
                    singleton = new GlobalManage();
                }
            }
        }
        return singleton;
    }

    public int getRealPx(int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitChannelModel != null && jDViewKitChannelModel.getViewWidth() > 0 && jDViewKitChannelModel.getRootWidth() > 0) {
            double d = i2;
            Double.isNaN(d);
            double rootWidth = jDViewKitChannelModel.getRootWidth();
            Double.isNaN(rootWidth);
            double viewWidth = jDViewKitChannelModel.getViewWidth();
            Double.isNaN(viewWidth);
            return (int) (((d * 1.0d) / rootWidth) * viewWidth);
        } else if (getScreenWidth() < 0.001d) {
            return 0;
        } else {
            return NumberTool.size2px(i2, getScreenWidth());
        }
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public JDViewKitVideoService getVideoService() {
        return this.videoService;
    }

    public void setScreenWidth(int i2) {
        if (i2 == 0) {
            return;
        }
        this.screenWidth = i2;
    }

    public void setVideoService(JDViewKitVideoService jDViewKitVideoService) {
        this.videoService = jDViewKitVideoService;
    }
}
