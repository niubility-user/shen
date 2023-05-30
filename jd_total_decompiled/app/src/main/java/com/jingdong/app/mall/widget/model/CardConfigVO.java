package com.jingdong.app.mall.widget.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public class CardConfigVO implements Serializable {
    public String burriedExpLabel;
    public CardConfig cardConfig;

    /* loaded from: classes4.dex */
    public static class CardConfig implements Serializable {
        public String backgroundPicture;
        public String changeColour;
        public String changeIcon;
        public List<NegativeOneCardChannelVO> channelList;
        public String deputyTitle;
        public String deputyTitleColour;
        public String jdIcon;
        public String title;
        public String titleColour;

        /* loaded from: classes4.dex */
        public static class NegativeOneCardChannelVO implements Serializable {
            public String buttonIcon;
            public boolean isShow;
            public String jumpProtocol;
            public String title;
        }
    }
}
