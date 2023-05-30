package com.jd.viewkit.helper;

import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.view.JDViewKitVideoView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* loaded from: classes18.dex */
public class JDViewKitChannelModel {
    public static final String AUTO_PLAY_TYPE_ALL = "2";
    public static final String AUTO_PLAY_TYPE_WIFI = "1";
    private JDViewKitFloorModel jdViewKitFloorModel;
    private JDViewKitBaseLayout rootView;
    private int rootWidth;
    private int viewWidth;
    private List<JDViewKitVideoView> autoVideoViewList = new ArrayList();
    private List<JDViewKitVideoView> wifiAutoVideoViewList = new ArrayList();

    public void addVideoView(String str, JDViewKitVideoView jDViewKitVideoView) {
        if ("2".equals(str)) {
            this.autoVideoViewList.add(jDViewKitVideoView);
        } else if ("1".equals(str)) {
            this.wifiAutoVideoViewList.add(jDViewKitVideoView);
        }
    }

    public boolean autoPlay(boolean z, boolean z2) {
        int size = this.autoVideoViewList.size();
        int size2 = this.wifiAutoVideoViewList.size();
        if (size > 0 || size2 > 0) {
            try {
                Random random = new Random();
                JDViewKitVideoView jDViewKitVideoView = null;
                if (size > 0) {
                    if (z && z2 && size2 > 0) {
                        int nextInt = random.nextInt(size + size2);
                        if (nextInt >= size) {
                            int i2 = nextInt - size;
                            if (i2 < size2) {
                                jDViewKitVideoView = this.wifiAutoVideoViewList.get(i2);
                            }
                        } else {
                            jDViewKitVideoView = this.autoVideoViewList.get(nextInt);
                        }
                    } else {
                        jDViewKitVideoView = this.autoVideoViewList.get(random.nextInt(size));
                    }
                } else if (z && z2) {
                    jDViewKitVideoView = this.wifiAutoVideoViewList.get(random.nextInt(size2));
                }
                if (jDViewKitVideoView != null) {
                    return jDViewKitVideoView.handleAutoPlay();
                }
                return false;
            } catch (Exception e2) {
                String str = "autoPlay: " + e2.getMessage();
                return false;
            }
        }
        return false;
    }

    public void clearAutoVideoViewLists() {
        this.autoVideoViewList.clear();
        this.wifiAutoVideoViewList.clear();
    }

    public JDViewKitFloorModel getJdViewKitFloorModel() {
        return this.jdViewKitFloorModel;
    }

    public JDViewKitBaseLayout getRootView() {
        return this.rootView;
    }

    public int getRootWidth() {
        return this.rootWidth;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public void setJdViewKitFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        this.jdViewKitFloorModel = jDViewKitFloorModel;
    }

    public void setRootView(JDViewKitBaseLayout jDViewKitBaseLayout) {
        this.rootView = jDViewKitBaseLayout;
    }

    public void setRootWidth(int i2) {
        this.rootWidth = i2;
    }

    public void setViewWidth(int i2) {
        this.viewWidth = i2;
    }
}
